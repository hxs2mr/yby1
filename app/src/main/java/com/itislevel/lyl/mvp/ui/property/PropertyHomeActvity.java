package com.itislevel.lyl.mvp.ui.property;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.itislevel.lyl.R;
import com.itislevel.lyl.adapter.DynamicViewPagerAdapter;
import com.itislevel.lyl.adapter.LiveAddressBean;
import com.itislevel.lyl.adapter.PropertyViewPagerAdapter;
import com.itislevel.lyl.app.Constants;
import com.itislevel.lyl.base.RootActivity;
import com.itislevel.lyl.base.RootFragment;
import com.itislevel.lyl.mvp.model.bean.PropertLoginBean;
import com.itislevel.lyl.mvp.model.bean.PropretyLiveBean;
import com.itislevel.lyl.mvp.model.bean.PropretyNoticeBean;
import com.itislevel.lyl.mvp.model.bean.VillageNameBean;
import com.itislevel.lyl.mvp.ui.property.childfragment.ChildFragmentHome;
import com.itislevel.lyl.mvp.ui.property.childfragment.ChildFragmentUser;
import com.itislevel.lyl.utils.UtilsStyle;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Administrator on 2018\5\22 0022.物业模块主界面
 */

public class PropertyHomeActvity extends RootActivity<PropertyPresenter> implements PropertyContract.View{

    @BindView(R.id.tabLayout)
    TabLayout tabLayout;

    @BindView(R.id.viewpager)
    ViewPager viewPager;

    private List<Fragment> fragments = new ArrayList<>();
    private String[] tabTitle = new String[]{"首页","我的"};
    private PropertyViewPagerAdapter propertyViewPagerAdapter;

    private TabLayout.Tab one;
    private TabLayout.Tab two;
    public static String location_city_id;
    public static String location_city_name;
    public static  String customer_service_phone;
    private Bundle bundle;
    //Tab图片
    private final int[] TAB_IMGS = new int[]{
            R.drawable.tab1_select,R.drawable.tab2_select};
    @Override
    protected int getLayoutId() {
        return R.layout.activity_property;
    }
    @Override
    protected void initEventAndData() {
        bundle = getIntent().getExtras();
        location_city_id = bundle.getString(Constants.CITY_ID);
        location_city_name = bundle.getString(Constants.CITY_NAME);
        customer_service_phone = bundle.getString("phone");
        setToolbarTvTitle("物业");
        initTV();
    }
    private void initTV() {
          fragments.add(new ChildFragmentHome());
          fragments.add(new ChildFragmentUser());
        if(tabLayout.getTabCount()>0)
        {
            tabLayout.removeAllTabs();
        }
        //设置顶部标签指示条的颜色和选中页面时标签字体颜色
        tabLayout.setSelectedTabIndicatorColor(Color.parseColor("#ff7a00"));
        tabLayout.setTabTextColors(Color.parseColor("#282828"), Color.parseColor("#ff7a00"));
        //这里注意的是，因为我是在fragment中创建MyFragmentStatePagerAdapter，所以要传getChildFragmentManager()
        propertyViewPagerAdapter = new PropertyViewPagerAdapter(getSupportFragmentManager(), tabTitle,fragments);
        viewPager.setAdapter(propertyViewPagerAdapter);
        viewPager.setCurrentItem(0);
        viewPager.setOffscreenPageLimit(1);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                propertyViewPagerAdapter.getItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        tabLayout.setupWithViewPager(viewPager);//绑定viewpager
        //指定Tab的位置
        one = tabLayout.getTabAt(0);
        two = tabLayout.getTabAt(1);//设置图标


    /*    //设置Tab的图标，使用背景选择器
        one.setIcon(R.drawable.tab1_select);
        two.setIcon(R.drawable.tab2_select);*/
        setTabs(this.getLayoutInflater(),TAB_IMGS);
    }


    private void setTabs(LayoutInflater inflater, int[] tabImgs) {
            View view1 = inflater.inflate(R.layout.tab_custom,null);
            one.setCustomView(view1);

            ImageView imgTab1 =view1.findViewById(R.id.img_tab);
            imgTab1.setImageResource(tabImgs[0]);

            TextView tvTitle = view1.findViewById(R.id.tv_tab);
            tvTitle.setText(tabTitle[0]);

        View view2 = inflater.inflate(R.layout.tab_custom,null);
            two.setCustomView(view2);
            ImageView imgTab2 = view2.findViewById(R.id.img_tab);
            imgTab2.setImageResource(tabImgs[1]);

        TextView tvTitle2 =view2.findViewById(R.id.tv_tab);
        tvTitle2.setText(tabTitle[1]);
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
    public void getSSMCode(String action) {

    }

    @Override
    public void loginEstates(PropertLoginBean bean) {

    }

    @Override
    public void noticeEstates(PropretyNoticeBean bean) {

    }

    @Override
    public void propretyLive(PropretyLiveBean list) {

    }

    @Override
    public void findVillagename(List<VillageNameBean> list) {

    }

    @Override
    public void findLiveaddress(List<LiveAddressBean> list) {

    }
}
