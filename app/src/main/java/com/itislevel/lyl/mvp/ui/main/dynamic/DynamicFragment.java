package com.itislevel.lyl.mvp.ui.main.dynamic;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.itislevel.lyl.R;
import com.itislevel.lyl.adapter.DynamicViewPagerAdapter;
import com.itislevel.lyl.app.Constants;
import com.itislevel.lyl.base.RootFragment;
import com.itislevel.lyl.mvp.model.bean.DynamicBean;
import com.itislevel.lyl.mvp.model.bean.PushBean;
import com.itislevel.lyl.mvp.model.bean.RefreshHeadBean;
import com.itislevel.lyl.mvp.ui.dynamicmyperson.Dynamic_MypersonActivity;
import com.itislevel.lyl.mvp.ui.email.fragment.EmotionMainFragment;
import com.itislevel.lyl.mvp.ui.funsharing.FunsharingAddActivity;
import com.itislevel.lyl.mvp.ui.location.Location_CityPickerActivity;
import com.itislevel.lyl.mvp.ui.main.childfragment.ChildFragment;
import com.itislevel.lyl.mvp.ui.main.dynamic.childfragment.dfind.DFindFragment;
import com.itislevel.lyl.mvp.ui.main.dynamic.childfragment.dfollow.DFollowFragment;
import com.itislevel.lyl.mvp.ui.main.dynamic.childfragment.toncity.DTonCityFragment;
import com.itislevel.lyl.mvp.ui.pay.PayInfoActivity;
import com.itislevel.lyl.mvp.ui.user.LoginActivity;
import com.itislevel.lyl.utils.ActivityUtil;
import com.itislevel.lyl.utils.GsonUtil;
import com.itislevel.lyl.utils.SAToast;
import com.itislevel.lyl.utils.SharedPreferencedUtils;
import com.itislevel.lyl.utils.ToastUtil;
import com.vondear.rxtools.activity.AndroidBug5497Workaround;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;

import static com.itislevel.lyl.base.NoMVPActivity.ISLIUHAN;
import static com.itislevel.lyl.base.NoMVPActivity.ISLIUHANNUMBER;
import static com.itislevel.lyl.mvp.ui.main.home.HomeFragment.CITY_ID;
import static com.itislevel.lyl.mvp.ui.main.home.HomeFragment.CITY_NAME;
import static com.itislevel.lyl.mvp.ui.main.home.HomeFragment.P_ID;
import static com.itislevel.lyl.mvp.ui.main.home.HomeFragment.P_NAME;
import static com.itislevel.lyl.widget.UIUtil.dip2px;

/**
 * Created by Administrator on 2018\4\27 0027.
 */

public class DynamicFragment extends RootFragment<DynamicPresenter> implements DynamicContract.View, View.OnClickListener {

    @BindView(R.id.home_tb)
    Toolbar home_tb ;
    @BindView(R.id.tabLayout)
    TabLayout tableLayout;

    @BindView(R.id.viewpager)
    ViewPager viewPager;

    @BindView(R.id.dynamic_hear_image)
    CircleImageView dynamic_hear_image;
    @BindView(R.id.fadon_frame)
    FrameLayout fadon_frame;

    public static EmotionMainFragment emotionMainFragment;//表情面板
    public static InputMethodManager inputMethodManager;
    private List<Fragment> fragments = new ArrayList<>();

    private String[] tabTitle = new String[]{"关注","发现","同城"};

    public static int totalTabNum = 4;//假如有四个Fragment页面
    public static int openTabNum = 0;//当前已打开的页面数量
    private  DynamicViewPagerAdapter dynamicViewPagerAdapter;
    private String headerUrl;
    public static int  viewpager_postion=1;
    int mPosition=0;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public  void onenvent(RefreshHeadBean bean)
    {
        //刷新头像
        headerUrl = SharedPreferencedUtils.getStr(Constants.USER_HEADER);
        headerUrl = Constants.IMG_SERVER_PATH + headerUrl;
        Glide.with(getContext())
                .load(headerUrl)
                .asBitmap()
                .error(R.mipmap.person_head)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(dynamic_hear_image);
    }
    @Override
    protected int getLayoutId() {
        return R.layout.dynamic_fragment;
    }
    @Override
    protected void initEventAndData(){
        if(ISLIUHAN)//刘海屏适配
        {
            home_tb.setPadding(0, ISLIUHANNUMBER-50,0,0);
        }
        inputMethodManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        int_title_tablayout();
    }
    private void int_title_tablayout() {
        fadon_frame.setOnClickListener(this);
        dynamic_hear_image.setOnClickListener(this);

        fragments.add(new DFollowFragment().newInstance(0));
        fragments.add(new DFindFragment().newInstance(1));
        fragments.add(new DTonCityFragment().newInstance(2));

        //刷新头像
        headerUrl = SharedPreferencedUtils.getStr(Constants.USER_HEADER);
        headerUrl = Constants.IMG_SERVER_PATH + headerUrl;

        Glide.with(getContext())
                .load(headerUrl)
                .asBitmap()
                .error(R.mipmap.person_head)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(dynamic_hear_image);
        if(tableLayout.getTabCount()>0)
        {
            tableLayout.removeAllTabs();
        }
        for(int i = 0 ; i < tabTitle.length ;i++)
        {
            tableLayout.addTab(tableLayout.newTab().setText(tabTitle[i]));
        }
        //设置顶部标签指示条的颜色和选中页面时标签字体颜色
        tableLayout.setSelectedTabIndicatorColor(Color.parseColor("#ff7a00"));
        tableLayout.setTabTextColors(Color.parseColor("#282828"), Color.parseColor("#ff7a00"));
        //这里注意的是，因为我是在fragment中创建MyFragmentStatePagerAdapter，所以要传getChildFragmentManager()
         dynamicViewPagerAdapter = new DynamicViewPagerAdapter(getChildFragmentManager(), tabTitle,fragments);
        viewPager.setAdapter(dynamicViewPagerAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tableLayout));

        tableLayout.post(new Runnable() {
            @Override
            public void run() {
                setTabIndicatorWidth(getContext(),tableLayout,10,10);
            }
        });
        viewPager.setCurrentItem(1);
        viewPager.setOffscreenPageLimit(3);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                viewpager_postion =position;
                dynamicViewPagerAdapter.getItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        tableLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //在选中的顶部标签时，为viewpager设置currentitem
                viewPager.setCurrentItem(tab.getPosition());
                viewpager_postion =tab.getPosition();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public void firstPage(String msg) {

    }
    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    public void useNightMode(boolean isNight) {

    }

    @Override
    public void stateEmpty() {

    }

    @Override
    public void stateLoading() {

    }

    @Override
    public void stateSuccess() {

    }
    public static void setTabIndicatorWidth(Context context, TabLayout tabLayout, int marginLeft, int marginRight) {
        try {
            //拿到tabLayout的mTabStrip属性
            LinearLayout mTabStrip = (LinearLayout) tabLayout.getChildAt(0);

            for (int i = 0; i < mTabStrip.getChildCount(); i++) {
                View tabView = mTabStrip.getChildAt(i);

                //拿到tabView的mTextView属性  tab的字数不固定一定用反射取mTextView
                Field mTextViewField = tabView.getClass().getDeclaredField("mTextView");
                mTextViewField.setAccessible(true);

                TextView mTextView = (TextView) mTextViewField.get(tabView);

                tabView.setPadding(0, 0, 0, 0);

                //字多宽线就多宽，所以测量mTextView的宽度
                int width = 0;
                width = mTextView.getWidth();
                if (width == 0) {
                    mTextView.measure(0, 0);
                    width = mTextView.getMeasuredWidth();
                }

                //设置tab左右间距 注意这里不能使用Padding 因为源码中线的宽度是根据 tabView的宽度来设置的
                LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) tabView.getLayoutParams();
                params.width = width ;
                //如果想设置第一个距左边距的距离，那么就设置i=0时leftMargin的值，同理，最后一个距右边距的距离，可以设置i=mTabStrip.getChildCount()-1的值
                params.leftMargin = dip2px(context, marginLeft);
                params.rightMargin = dip2px(context, marginRight);
                tabView.setLayoutParams(params);

                tabView.invalidate();
            }
        } catch (NoSuchFieldException e) {
        } catch (IllegalAccessException e) {
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.dynamic_hear_image://个人中心
                boolean islogin = SharedPreferencedUtils.getBool("islogin", false);
                if (!islogin) {
                    ActivityUtil.getInstance().openActivity(getActivity(), LoginActivity.class);
                    return;
                }
                String name = SharedPreferencedUtils.getStr(Constants.USER_NICK_NAME);
                Bundle bundle1  = new Bundle();
                bundle1.putString("FLAGE","WO");
                bundle1.putString("head_image",headerUrl);
                bundle1.putString("name", TextUtils.isEmpty(name) ? "请登录" : "" + name);
                bundle1.putString("userid",  SharedPreferencedUtils.getStr(Constants.USER_ID)+"");
                ActivityUtil.getInstance().openActivity(getActivity(), Dynamic_MypersonActivity
                        .class,bundle1);
                break;
            case R.id.fadon_frame://发布动态
                if(!CITY_ID.equals("0"))
                {
                    Bundle bundle = new Bundle();
                    bundle.putString(Constants.PROVINCE_TITLE, "乐趣分享");
                    bundle.putString(Constants.CITY_TITLE, "乐趣分享");
                    bundle.putString(Constants.COUNTY_TITLE, "乐趣分享");
                    bundle.putBoolean(Constants.IS_SHOW_COUNTY, false);

                    bundle.putString(Constants.PROVINCE_ID, P_ID);
                    bundle.putString(Constants.PROVINCE_NAME, P_NAME);
                    bundle.putString(Constants.CITY_ID, CITY_ID);
                    bundle.putString(Constants.CITY_NAME, CITY_NAME);

                    bundle.putInt(Constants.ACTIVITY_TARGET, Constants.ACTIVITY_FUNSHARING);

                    ActivityUtil.getInstance().openActivity(getActivity(),
                            FunsharingAddActivity.class, bundle);
                }else {
                    SAToast.makeText(getContext(),"请先选择所属地区").show();
                    Bundle bundle = new Bundle();
                    String title = "";
                    bundle.putString(Constants.START_FLAGE, "home");
                    bundle.putString(Constants.CITY_NAME, "定位失败");
                    bundle.putString(Constants.CITY_ID, "0");
                    ActivityUtil.getInstance().openActivity(getActivity(), Location_CityPickerActivity.class,bundle);
                }
                break;
        }
    }
    @Subscribe
    public void OnEvent(DynamicBean isFollowBean)
    {
        if(isFollowBean.getDy_flage().equals("1"))
        {
            fadon_frame.setVisibility(View.GONE);
        }else {
            fadon_frame.setVisibility(View.VISIBLE);
        }
    }
}
