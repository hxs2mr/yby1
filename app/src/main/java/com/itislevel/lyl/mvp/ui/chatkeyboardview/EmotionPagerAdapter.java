package com.itislevel.lyl.mvp.ui.chatkeyboardview;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import java.util.List;

/**
 * **********************
 * 功 能:EmotiomComplateFragment界面Viewpager数据适配器
 * 创建人:itisi
 * 邮  箱:itisivip@qq.com
 * 创建时间:2017/6/23 16:31
 * 修改人:itisi
 * 修改时间: 2017/6/23 16:31
 * 修改内容:itisi
 * *********************
 */

public class EmotionPagerAdapter extends PagerAdapter {
    private List<GridView> mGridViews;

    public EmotionPagerAdapter(List<GridView> gridViews) {
        mGridViews = gridViews;
    }

    @Override
    public int getCount() {
        return mGridViews.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView(mGridViews.get(position));
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
         container.addView(mGridViews.get(position));
        return mGridViews.get(position);
    }
}
