package com.itislevel.lyl.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import com.itislevel.lyl.mvp.ui.main.dynamic.childfragment.dfind.DFindFragment;
import com.itislevel.lyl.mvp.ui.main.dynamic.childfragment.dfollow.DFollowFragment;
import com.itislevel.lyl.mvp.ui.main.dynamic.childfragment.toncity.DTonCityFragment;

import java.util.List;

/**
 * Created by Administrator on 2018\4\27 0027.
 */

public class DynamicViewPagerAdapter extends FragmentStatePagerAdapter {
    List<Fragment> mFragments;
    private  String[] mTabTitle;
    public DynamicViewPagerAdapter(FragmentManager fm,String[] tabTitle,List<Fragment> fragments) {
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
