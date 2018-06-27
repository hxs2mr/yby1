package com.itislevel.lyl.mvp.ui.dynamicmyperson;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.itislevel.lyl.R;
import com.itislevel.lyl.adapter.DynamicViewPagerAdapter;
import com.itislevel.lyl.app.Constants;
import com.itislevel.lyl.base.RootActivity;
import com.itislevel.lyl.mvp.model.bean.IsFollowBean;
import com.itislevel.lyl.mvp.model.bean.PersonalCommunBean;
import com.itislevel.lyl.mvp.ui.dynamicmyperson.childpersonfragment.Dynamic_personFragment;
import com.itislevel.lyl.mvp.ui.dynamicmyperson.childpersonfragment.Family_personFragment;
import com.itislevel.lyl.utils.ActivityUtil;
import com.itislevel.lyl.utils.GsonUtil;
import com.itislevel.lyl.utils.SAToast;
import com.itislevel.lyl.utils.SharedPreferencedUtils;
import com.vondear.rxtools.activity.AndroidBug5497Workaround;

import org.greenrobot.eventbus.Subscribe;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.GZIPOutputStream;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import qiu.niorgai.StatusBarCompat;
import retrofit2.http.PUT;

import static com.itislevel.lyl.widget.UIUtil.dip2px;

/**
 * Created by Administrator on 2018\5\3 0003.
 */

public class Dynamic_MypersonActivity extends RootActivity<Dynamic_MypersonPresenter> implements Dynamic_MypersonContract.View, View.OnClickListener{

    @BindView(R.id.dy_tablayout)
    TabLayout tableLayout ;

    @BindView(R.id.dy_viewpager)
    ViewPager viewPager ;

    @BindView(R.id.head_image)
    CircleImageView head_image;

    @BindView(R.id.head_name)
    AppCompatTextView head_name;

    @BindView(R.id.guan_linear)
    LinearLayoutCompat guan_linear;
    @BindView(R.id.guan_zhu_image)
    AppCompatImageView guan_zhu_image;

    @BindView(R.id.guan_zhu_tv)
    AppCompatTextView guan_zhu_tv;

    @BindView(R.id.dy_my_person_guan)
    AppCompatTextView dy_my_person_guan;

    @BindView(R.id.dy_my_person_fen)
    AppCompatTextView dy_my_person_fen;

    @BindView(R.id.back_linear)
    LinearLayoutCompat back_linear;
    private List<Fragment> fragments = new ArrayList<>();
    private String[] tabTitle = new String[]{"TA的动态","TA的祭祀"};

    private DynamicViewPagerAdapter dynamicViewPagerAdapter;
    private Bundle bundle;
    private String image_url="";
    private  String name="";
    public   static  String PERSON_USER_ID="";
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarCompat.translucentStatusBar(this, true);
        AndroidBug5497Workaround.assistActivity(this);
    }
    @Override
    protected int getLayoutId() {
        return R.layout.dynamic_myperson_activity;
    }
    @Override
    protected void initEventAndData() {
        guan_linear.setOnClickListener(this);
        back_linear.setOnClickListener(this);
        bundle= getIntent().getExtras();
        String flage = bundle.getString("FLAGE");
        image_url = bundle.getString("head_image");
        name = bundle.getString("name");
        PERSON_USER_ID = bundle.getString("userid");
        head_name.setText(name);
        init_fen();
        String mint_user_id="0";
        if(SharedPreferencedUtils.getStr(Constants.USER_ID)!=null)
        {
            mint_user_id = SharedPreferencedUtils.getStr(Constants.USER_ID);
        }
        if(!flage.equals("follow"))
        {
          if(PERSON_USER_ID.equals(mint_user_id))
        {
            tabTitle[0]="我的动态";
            tabTitle[1]="我的祭祀";
            guan_linear.setVisibility(View.GONE);
        }else {
            tabTitle[0]="TA的动态";
            tabTitle[1]="TA的祭祀";
        }
        }else {
            tabTitle[0]="TA的动态";
            tabTitle[1]="TA的祭祀";
        }
        Glide.with(this)
                .load(image_url)
                .asBitmap()
                .error(R.mipmap.person_head)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(head_image);
        fragments.add(new Dynamic_personFragment());
        fragments.add(new Family_personFragment());

        viewPager.setFocusable(true);
        viewPager.setFocusableInTouchMode(true);
        viewPager.requestFocus();
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
        tableLayout.setTabTextColors(Color.parseColor("#666666"), Color.parseColor("#ff7a00"));
        //这里注意的是，因为我是在fragment中创建MyFragmentStatePagerAdapter，所以要传getChildFragmentManager()
        dynamicViewPagerAdapter = new DynamicViewPagerAdapter(getSupportFragmentManager(), tabTitle,fragments);
        viewPager.setAdapter(dynamicViewPagerAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tableLayout));
        tableLayout.post(new Runnable() {
            @Override
            public void run() {
                setTabIndicatorWidth(Dynamic_MypersonActivity.this,tableLayout,70,70);
            }
        });

        viewPager.setCurrentItem(0);
        viewPager.setOffscreenPageLimit(2);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                //滑动监听加载数据，一次只加载一个标签页
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
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void init_fen(){
        Map<String, String> request = new HashMap<>();
        request.put("token", SharedPreferencedUtils.getStr(Constants.USER_TOKEN));
        request.put("usernum", SharedPreferencedUtils.getStr(Constants.USER_NUM));
        request.put("userid", PERSON_USER_ID);
            request.put("touserid",SharedPreferencedUtils.getStr(Constants.USER_ID));

            System.out.print("TOKEN**************usernum***********************************"+SharedPreferencedUtils.getStr(Constants.USER_TOKEN)+"       "+ SharedPreferencedUtils.getStr(Constants.USER_NUM));
        mPresenter.personalCommun(GsonUtil.obj2JSON(request));
    }

    @Subscribe
    public void OnEvent(IsFollowBean isFollowBean)
    {
        SAToast.makeText(this,"事件来临").show();
        if(isFollowBean.getIsfollow().equals("1"))
        {
            if(guan_zhu_tv.getText().toString().equals("关注"))
            {
                guan_zhu_tv.setText("取消关注");
                guan_zhu_image.setVisibility(View.VISIBLE);
                guan_zhu_tv.setTextColor(Color.parseColor("#666666"));
                guan_linear.setBackground(getResources().getDrawable(R.drawable.share_item_yeguan));
            }else {
                guan_zhu_tv.setText("关注");
                guan_zhu_image.setVisibility(View.GONE);
                guan_zhu_tv.setTextColor(Color.parseColor("#666666"));
                guan_linear.setBackground(getResources().getDrawable(R.drawable.share_item_yeguan));
            }
        }
    }
    @Override
    public void frist_loader(String msg) {

    }

    @Override
    public void dynamicfollow(String msg) {
        loadingDialog.dismiss();
         SAToast.makeText(this, "关注成功!").show();
    }

    @Override
    public void personalCommun(PersonalCommunBean bean) {//粉丝数 关注
        if(bean.getIsfollow().equals("1"))
        {
            SAToast.makeText(this,"来了").show();
            guan_zhu_tv.setText("取消关注");
            guan_zhu_image.setVisibility(View.GONE);
            guan_zhu_tv.setTextColor(Color.parseColor("#ffffff"));
            guan_linear.setBackground(getResources().getDrawable(R.drawable.share_item_yeguan));
        }
            dy_my_person_guan.setText("关注"+bean.getList1().get(0).get_$COUNT1108());
            dy_my_person_fen.setText("粉丝"+bean.getFabulousnumber());
    }

    @Override
    public void delefollow(String msg) {
        loadingDialog.dismiss();
            SAToast.makeText(this, "取消关注成功!").show();
    }

    @Override
    public void stateEmpty() {

    }

    @Override
    public void stateError() {
        super.stateError();
        SAToast.makeText(this,"错误信息").show();
        loadingDialog.dismiss();
    }

    @Override
    public void stateLoading() {

    }

    @Override
    public void stateSuccess() {

    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.guan_linear:
                if(guan_zhu_tv.getText().toString().equals("关注"))
                {
                    guan_zhu_tv.setText("取消关注");
                    guan_zhu_image.setVisibility(View.GONE);
                    guan_zhu_tv.setTextColor(Color.parseColor("#ffffff"));
                    guan_linear.setBackground(getResources().getDrawable(R.drawable.share_item_yeguan));
                    init_guan(PERSON_USER_ID+"");//关注的网络请求
                }else {
                    guan_zhu_tv.setText("关注");
                    guan_zhu_image.setVisibility(View.VISIBLE);
                    guan_zhu_tv.setTextColor(Color.parseColor("#ffffff"));
                    guan_linear.setBackground(getResources().getDrawable(R.drawable.share_item_yeguan));
                    init_qu_guan(PERSON_USER_ID+"");//关注的网络请求
                }
                break;
            case R.id.back_linear:
                ActivityUtil.getInstance().closeActivity(this);
                break;
        }
    }
    private void init_guan(String touserid)
    {
        loadingDialog.show();
        String user_id = SharedPreferencedUtils.getStr(Constants.USER_NUM);
        Map<String, String> request = new HashMap<>();
        request.put("token", SharedPreferencedUtils.getStr(Constants.USER_TOKEN));
        request.put("usernum", SharedPreferencedUtils.getStr(Constants.USER_NUM));
        request.put("userid", SharedPreferencedUtils.getStr(Constants.USER_ID));
        request.put("touserid",PERSON_USER_ID);
        request.put("modename", "dynamic");
        mPresenter.dynamicfollow(GsonUtil.obj2JSON(request));
    }

    private void init_qu_guan(String touserid)
    {
        loadingDialog.show();
        String user_id = SharedPreferencedUtils.getStr(Constants.USER_NUM);
        Map<String, String> request = new HashMap<>();
        request.put("token", SharedPreferencedUtils.getStr(Constants.USER_TOKEN));
        request.put("usernum", SharedPreferencedUtils.getStr(Constants.USER_NUM));
         request.put("userid", SharedPreferencedUtils.getStr(Constants.USER_ID));
         request.put("touserid",PERSON_USER_ID);
        mPresenter.delefollow(GsonUtil.obj2JSON(request));
    }

    public static void setTabIndicatorWidth(Context context, TabLayout tabLayout, int marginLeft, int marginRight) {
        try {
            //拿到tabLayout的mTabStrip属性
            LinearLayout mTabStrip = (LinearLayout) tabLayout.getChildAt(0);
            mTabStrip.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
            mTabStrip.setDividerDrawable(ContextCompat.getDrawable(context,
                    R.drawable.tab_layout_divider));
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

    public static void setIndicator(TabLayout tabs, int leftDip, int rightDip) {//设置tablyout下划线的宽度
        Class<?> tabLayout = tabs.getClass();
        Field tabStrip = null;
        try {
            tabStrip = tabLayout.getDeclaredField("mTabStrip");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        tabStrip.setAccessible(true);
        LinearLayout llTab = null;
        try {
            llTab = (LinearLayout) tabStrip.get(tabs);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        int left = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, leftDip, Resources.getSystem().getDisplayMetrics());
        int right = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, rightDip, Resources.getSystem().getDisplayMetrics());

        for (int i = 0; i < llTab.getChildCount(); i++) {
            View child = llTab.getChildAt(i);
            child.setPadding(0, 0, 0, 0);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1);
            params.leftMargin = left;
            params.rightMargin = right;
            child.setLayoutParams(params);
            child.invalidate();
        }
    }
}
