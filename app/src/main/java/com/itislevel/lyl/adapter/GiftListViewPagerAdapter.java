package com.itislevel.lyl.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import com.itislevel.lyl.mvp.model.bean.FamilySacrificeTypeBean;

import java.util.List;

/**
 * Created by Administrator on 2018\4\27 0027.
 */

public class GiftListViewPagerAdapter extends FragmentStatePagerAdapter {
    List<Fragment> mFragments;
    List<FamilySacrificeTypeBean.ListBean> mCateList;
    public GiftListViewPagerAdapter(FragmentManager fm, List<FamilySacrificeTypeBean.ListBean> cateList, List<Fragment> fragments) {
        super(fm);
        this.mCateList  = cateList;
        this.mFragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }
    @Override
    public int getCount() {
        return mCateList.size();
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
