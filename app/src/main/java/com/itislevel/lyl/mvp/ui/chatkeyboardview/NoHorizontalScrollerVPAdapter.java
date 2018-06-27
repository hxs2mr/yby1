package com.itislevel.lyl.mvp.ui.chatkeyboardview;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import java.util.List;

/**
 * **********************
 * 功 能:
 * 创建人:itisi
 * 邮  箱:itisivip@qq.com
 * 创建时间:2017/6/23 16:29
 * 修改人:itisi
 * 修改时间: 2017/6/23 16:29
 * 修改内容:itisi
 * *********************
 */

public class NoHorizontalScrollerVPAdapter extends FragmentPagerAdapter {

    private List<Fragment> datas;

    public NoHorizontalScrollerVPAdapter(FragmentManager fm, List<Fragment> datas) {
        super(fm);
        this.datas = datas;
    }

    @Override
    public Fragment getItem(int position) {
        return datas.get(position);
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        // 这里Destroy的是Fragment的视图层次，并不是Destroy Fragment对象
        super.destroyItem(container, position, object);
    }
}
