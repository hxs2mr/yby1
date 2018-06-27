package com.itislevel.lyl.mvp.ui.main.customer;


import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.SimpleClickListener;
import com.example.zhouwei.library.CustomPopWindow;
import com.google.gson.Gson;
import com.itislevel.lyl.R;
import com.itislevel.lyl.adapter.CustomerViewPagerAdapter;
import com.itislevel.lyl.adapter.DynamicViewPagerAdapter;
import com.itislevel.lyl.adapter.FunsharingCommentAdapter;
import com.itislevel.lyl.adapter.FunsharingCommentReplayAdapter;
import com.itislevel.lyl.adapter.FunsharingListAdapter;
import com.itislevel.lyl.adapter.recyclew.MultipleItemEntity;
import com.itislevel.lyl.app.Constants;
import com.itislevel.lyl.base.RootFragment;
import com.itislevel.lyl.mvp.model.bean.CFTabBean;
import com.itislevel.lyl.mvp.model.bean.FollowGson;
import com.itislevel.lyl.mvp.model.bean.FollowReplayItemBean;
import com.itislevel.lyl.mvp.model.bean.FunshingCommentItemBean;
import com.itislevel.lyl.mvp.model.bean.FunshingItemBean;
import com.itislevel.lyl.mvp.model.bean.FunshingReplayItemBean;
import com.itislevel.lyl.mvp.ui.chatkeyboardview.EmotionMainFragment;
import com.itislevel.lyl.mvp.ui.funsharing.FunsharingHomeActivity;
import com.itislevel.lyl.mvp.ui.main.childfragment.ChildFragment;
import com.itislevel.lyl.mvp.ui.main.home.HomeAdapter.MultipleRecyclerAdapter;
import com.itislevel.lyl.mvp.ui.user.LoginActivity;
import com.itislevel.lyl.net.RestClent;
import com.itislevel.lyl.net.callback.IFailure;
import com.itislevel.lyl.net.callback.ISuccess;
import com.itislevel.lyl.utils.ActivityUtil;
import com.itislevel.lyl.utils.ChatUtil;
import com.itislevel.lyl.utils.GsonUtil;
import com.itislevel.lyl.utils.SAToast;
import com.itislevel.lyl.utils.SharedPreferencedUtils;
import com.itislevel.lyl.utils.ToastUtil;
import com.orhanobut.logger.Logger;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;
import retrofit2.http.PUT;

import static com.itislevel.lyl.base.NoMVPActivity.ISLIUHAN;
import static com.itislevel.lyl.base.NoMVPActivity.ISLIUHANNUMBER;
import static com.itislevel.lyl.widget.UIUtil.dip2px;

/**
 * A simple {@link Fragment} subclass.关注界面
 */
public class CustomerFragment extends RootFragment<CustomerPresenter> implements CustomerContract.View{
    @BindView(R.id.tabLayout)
    TabLayout tableLayout;

    @BindView(R.id.viewpager)
    ViewPager viewPager;

    @BindView(R.id.home_tb)
    Toolbar home_tb ;
    private List<Fragment> fragments = new ArrayList<>();

    public static int mPostion=0;
    private List<CFTabBean> tabTitleList;
    //private String[] tabTitle = new String[]{"头条","美食","健康","居家","运动","母婴"};
    private CustomerViewPagerAdapter customerViewPagerAdapter;
    @Override
    protected int getLayoutId() {
        return R.layout.flow_fragment;
    }
    @Override
    protected void initEventAndData() {

        if(ISLIUHAN)//刘海屏适配
        {
            home_tb.setPadding(0, ISLIUHANNUMBER-50,0,0);
        }
        Avloadetable(); //加载table标题
          /*
          * Waiting接口
          * 动态添加fragment
          */
    }

    private void init_Tab_Vp() {
        for(int i =0 ; i < tabTitleList.size();i++)
        {
            fragments.add(new ChildFragment().newInstance(tabTitleList.get(i).getId()));
        }
        viewPager.setFocusable(true);
        viewPager.setFocusableInTouchMode(true);
        viewPager.requestFocus();
        if(tableLayout.getTabCount()>0)//移除
        {
            tableLayout.removeAllTabs();
        }
        for(int i = 0 ; i < tabTitleList.size() ;i++)
        {
            tableLayout.addTab(tableLayout.newTab().setText(" "+tabTitleList.get(i).getCatename()+" "));
        }
        tableLayout.setSelectedTabIndicatorColor(Color.parseColor("#ff7a00"));
        tableLayout.setTabTextColors(Color.parseColor("#282828"), Color.parseColor("#ff7a00"));
        //这里注意的是，因为我是在fragment中创建MyFragmentStatePagerAdapter，所以要传getChildFragmentManager()
        customerViewPagerAdapter = new CustomerViewPagerAdapter(getChildFragmentManager(), tabTitleList,fragments);
        viewPager.setAdapter(customerViewPagerAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tableLayout));//添加tab监听
        tableLayout.post(new Runnable() {
            @Override
            public void run() {
                setTabIndicatorWidth(getContext(),tableLayout,10,10);//设置宽度
            }
        });
        viewPager.setCurrentItem(0);
        viewPager.setOffscreenPageLimit(tabTitleList.size());
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                mPostion = position;
            }
            @Override
            public void onPageSelected(int position){
                mPostion = position;
                customerViewPagerAdapter.getItem(position);
            }
            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        tableLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //在选中的顶部标签时，为viewpager设置currentitem
                mPostion = tab.getPosition();
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

    private void Avloadetable() {
        Map<String, String> request = new HashMap<>();
        request.put("token", SharedPreferencedUtils.getStr(Constants.USER_TOKEN));
        request.put("usernum", SharedPreferencedUtils.getStr(Constants.USER_NUM));
        mPresenter.loadtable(GsonUtil.obj2JSON(request));
    }

    @Override
    public void showContent(String msg) {

    }
    @Override
    public void loadtable(List<CFTabBean> list) {
        tabTitleList = new ArrayList<>();
        CFTabBean cfTabBean1 = new CFTabBean(0,"头条");
        tabTitleList.add(cfTabBean1);
        for(int i = 0 ; i < list.size();i++)
        {
            tabTitleList.add(list.get(i));
        }
        init_Tab_Vp();
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
    public void stateError() {
        tabTitleList = new ArrayList<>();
        CFTabBean cfTabBean1 = new CFTabBean(0,"头条");
        CFTabBean cfTabBean2 = new CFTabBean(10,"美食");
        CFTabBean cfTabBean3 = new CFTabBean(7,"健康");
        CFTabBean cfTabBean4 = new CFTabBean(14,"居家");
        CFTabBean cfTabBean5 = new CFTabBean(15,"运动");
        CFTabBean cfTabBean6 = new CFTabBean(18,"母婴");

        tabTitleList.add(cfTabBean1);
        tabTitleList.add(cfTabBean2);
        tabTitleList.add(cfTabBean3);
        tabTitleList.add(cfTabBean4);
        tabTitleList.add(cfTabBean5);
        tabTitleList.add(cfTabBean6);
        init_Tab_Vp();
    }

    @Override
    public void stateError(Throwable e) {
        tabTitleList = new ArrayList<>();
        CFTabBean cfTabBean1 = new CFTabBean(0,"头条");
        CFTabBean cfTabBean2 = new CFTabBean(10,"美食");
        CFTabBean cfTabBean3 = new CFTabBean(7,"健康");
        CFTabBean cfTabBean4 = new CFTabBean(14,"居家");
        CFTabBean cfTabBean5 = new CFTabBean(15,"运动");
        CFTabBean cfTabBean6 = new CFTabBean(18,"母婴");

        tabTitleList.add(cfTabBean1);
        tabTitleList.add(cfTabBean2);
        tabTitleList.add(cfTabBean3);
        tabTitleList.add(cfTabBean4);
        tabTitleList.add(cfTabBean5);
        tabTitleList.add(cfTabBean6);
        init_Tab_Vp();
    }

    @Override
    public void stateSuccess() {

    }
    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
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
}
