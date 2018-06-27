package com.itislevel.lyl.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import com.itislevel.lyl.mvp.model.bean.CFTabBean;

import java.util.List;

/**
 * Created by Administrator on 2018\4\27 0027.
 */

public class CustomerViewPagerAdapter extends FragmentStatePagerAdapter {
    List<Fragment> mFragments;
    private List<CFTabBean> tabTitleList;
    public CustomerViewPagerAdapter(FragmentManager fm,List<CFTabBean> tabTitle, List<Fragment> fragments) {
        super(fm);
        this.tabTitleList  = tabTitle;
        this.mFragments = fragments;
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }
    @Override
    public int getCount() {
        return tabTitleList.size();
    }
    @Override
    public void finishUpdate(ViewGroup container) {
        try{
            super.finishUpdate(container);
        } catch (NullPointerException nullPointerException){
            System.out.println("finishUpdate报错了");
        }
    }
}
