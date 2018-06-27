package com.itislevel.lyl.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * desc:功能说明必写
 * user:itisi
 * date:2018-01-23.09:45
 * path:com.itislevel.lyl.adapter.SpecialOrderPagerAdapter
 **/
public class SpecialOrderPagerAdapter extends FragmentPagerAdapter {

    List<Fragment>fragmentList;
    private String[] mTitles;

    public SpecialOrderPagerAdapter(FragmentManager fm,List<Fragment>fragmentList,String[] mTitles) {
        super(fm);
        this.fragmentList=fragmentList;
        this.mTitles=mTitles;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }
}
