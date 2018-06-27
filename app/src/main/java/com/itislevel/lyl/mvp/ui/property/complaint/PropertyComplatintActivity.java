package com.itislevel.lyl.mvp.ui.property.complaint;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.itislevel.lyl.R;
import com.itislevel.lyl.adapter.ComplintTouAdapter;
import com.itislevel.lyl.adapter.ComplintViewPagerAdapter;
import com.itislevel.lyl.adapter.DynamicViewPagerAdapter;
import com.itislevel.lyl.app.Constants;
import com.itislevel.lyl.base.RootActivity;
import com.itislevel.lyl.base.RootFragment;
import com.itislevel.lyl.mvp.model.bean.ComSearchBean;
import com.itislevel.lyl.mvp.model.bean.ComplaintTypeBean;
import com.itislevel.lyl.mvp.model.bean.FileUploadBean;
import com.itislevel.lyl.mvp.model.bean.PropertyDetailBean;
import com.itislevel.lyl.mvp.ui.main.dynamic.childfragment.dfind.DFindFragment;
import com.itislevel.lyl.mvp.ui.main.dynamic.childfragment.dfollow.DFollowFragment;
import com.itislevel.lyl.mvp.ui.main.dynamic.childfragment.toncity.DTonCityFragment;
import com.itislevel.lyl.mvp.ui.property.complaint.childfragment.PropertyComplaintFragment1;
import com.itislevel.lyl.mvp.ui.property.complaint.childfragment.PropertyComplaintFragment2;
import com.itislevel.lyl.utils.ActivityUtil;
import com.itislevel.lyl.utils.SharedPreferencedUtils;
import com.itislevel.lyl.utils.UtilsStyle;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018\5\29 0029.
 */

public class PropertyComplatintActivity extends RootActivity<ComplaintPresenter> implements ComplaintContract.View
{

    @BindView(R.id.viewpager)
    ViewPager viewPager;
    @BindView(R.id.tabLayout)
    TabLayout tableLayout;

    @BindView(R.id.p_p_back)
    LinearLayoutCompat p_p_back;
    private ComplintViewPagerAdapter dynamicViewPagerAdapter;
    private String[] tabTitle = new String[]{"投诉","查询"};
    private List<Fragment> fragments = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UtilsStyle.setStatusBarMode(this,true); //黑色的主题图标
    }

    @Override
    protected int getLayoutId() {
            return R.layout.activity_complatint;
    }
    @Override
    protected void initEventAndData() {
        int_title_tablayout();
    }
    private void int_title_tablayout() {
        fragments.add(new PropertyComplaintFragment1().newInstance(0));
        fragments.add(new PropertyComplaintFragment2().newInstance(1));
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
        dynamicViewPagerAdapter = new ComplintViewPagerAdapter(getSupportFragmentManager(), tabTitle,fragments);
        viewPager.setAdapter(dynamicViewPagerAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tableLayout));
        viewPager.setCurrentItem(0);
        viewPager.setOffscreenPageLimit(2);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
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
    @OnClick({R.id.p_p_back})
    public  void onclick(View view)
    {
        switch (view.getId())
        {
            case R.id.p_p_back:
                ActivityUtil.getInstance().closeActivity(this);
                break;
        }
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
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

    @Override
    public void uploadHeader(FileUploadBean fileUploadBean) {

    }

    @Override
    public void complaintType(List<ComplaintTypeBean> data) {

    }

    @Override
    public void addComplaint(String data) {

    }

    @Override
    public void findComplaintList(ComSearchBean bean) {

    }

    @Override
    public void detaComplaintList(List<PropertyDetailBean> bean) {

    }
}
