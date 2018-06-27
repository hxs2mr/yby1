package com.itislevel.lyl.mvp.ui.chatkeyboardview;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * **********************
 * 功 能:可横向滑动的ViewPager
 * 创建人:itisi
 * 邮  箱:itisivip@qq.com
 * 创建时间:2017/6/23 15:24
 * 修改人:itisi
 * 修改时间: 2017/6/23 15:24
 * 修改内容:itisi
 * *********************
 */

public class NoHorizontalScrollerViewPager extends ViewPager {
    public NoHorizontalScrollerViewPager(Context context) {
        super(context);
    }

    public NoHorizontalScrollerViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 重写拦截事件，返回值设置为false，这时便不会横向滑动了。
     * @param ev
     * @return
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
//        return super.onInterceptTouchEvent(ev);
        return false;
    }

    /**
     * 重写拦截事件，返回值设置为false，这时便不会横向滑动了。
     * @param ev
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
//        return super.onTouchEvent(ev);
        return false;
    }
}
