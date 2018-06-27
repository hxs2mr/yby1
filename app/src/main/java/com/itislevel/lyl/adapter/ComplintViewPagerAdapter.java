package com.itislevel.lyl.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Created by Administrator on 2018\4\27 0027.
 */

public class ComplintViewPagerAdapter extends FragmentStatePagerAdapter {
    List<Fragment> mFragments;
    private  String[] mTabTitle;
    public ComplintViewPagerAdapter(FragmentManager fm, String[] tabTitle, List<Fragment> fragments) {
        super(fm);
        this.mTabTitle  = tabTitle;
        this.mFragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }
    @Override
    public int getCount() {
        return mTabTitle.length;
    }

}
