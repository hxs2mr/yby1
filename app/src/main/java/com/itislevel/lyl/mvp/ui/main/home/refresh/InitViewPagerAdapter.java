package com.itislevel.lyl.mvp.ui.main.home.refresh;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import com.itislevel.lyl.R;

import java.util.List;

/**
 * Created by Administrator on 2018\4\8 0008.
 */

public class InitViewPagerAdapter {
    ViewGroup group;
    List<View> view_list;

    static ImageView[] imageViews;

    Context context;

    ImageView imageView;
    public InitViewPagerAdapter(ViewGroup mgroup,List<View> mview_list, ImageView[] mimageViews, Context mcontext,ImageView mimageView){
        this.group = mgroup ;
        this.view_list = mview_list ;
        this.imageViews = mimageViews ;
        this.context = mcontext ;
        this.imageView = mimageView ;
        initPointer();
    }
    //初始化下面的小圆点的方法
    @SuppressLint("NewApi")
    public void initPointer() {
        //有多少个界面就new多长的数组
        imageViews = new ImageView[view_list.size()];
        for (int i = 0; i < imageViews.length; i++) {
            imageView = new ImageView(context);
            //设置控件的宽高
            imageView.setLayoutParams(new ViewGroup.LayoutParams(20, 20));
            //设置控件的padding属性
            imageView.setPadding(5, 5, 5, 5);
            imageViews[i] = imageView;
            //初始化第一个page页面的图片的原点为选中状态
            if (i == 0) {
                //表示当前图片
                imageViews[i].setBackgroundResource(R.mipmap.page_indicator_focused);
                /**
                 * 在java代码中动态生成ImageView的时候
                 * 要设置其BackgroundResource属性才有效
                 * 设置ImageResource属性无效
                 */
            } else {
                imageViews[i].setBackgroundResource(R.mipmap.page_indicator_unfocused);
            }
            group.addView(imageViews[i]);
        }
    }

    public static class GuidePageChangeListener implements ViewPager.OnPageChangeListener {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }
        //页面滑动完成后执行
        @Override
        public void onPageSelected(int position) {
            //判断当前是在那个page，就把对应下标的ImageView原点设置为选中状态的图片
            for (int i = 0; i < imageViews.length; i++) {
                imageViews[position].setBackgroundResource(R.mipmap.page_indicator_focused);
                if (position != i) {
                    imageViews[i].setBackgroundResource(R.mipmap.page_indicator_unfocused);
                }
            }
        }
        //监听页面的状态，0--静止 1--滑动  2--滑动完成
        @Override
        public void onPageScrollStateChanged(int state) {
        }
    }

}
