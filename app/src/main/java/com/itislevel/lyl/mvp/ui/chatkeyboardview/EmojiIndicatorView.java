package com.itislevel.lyl.mvp.ui.chatkeyboardview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;


import com.itislevel.lyl.R;

import java.util.ArrayList;

/**
 * **********************
 * 功 能:必填必填必填必填必填必填
 * 创建人:itisi
 * 邮  箱:itisivip@qq.com
 * 创建时间:2017/6/23 15:25
 * 修改人:itisi
 * 修改时间: 2017/6/23 15:25
 * 修改内容:itisi
 * *********************
 */

public class EmojiIndicatorView extends LinearLayout {
    private Context mContext;
    private ArrayList<View> mImageViews;//所有指示器集合
    private int size=6;
    private int marginSize=15;
    private int pointSize;//指示器的大小
    private int marginLeft; //间距
    public EmojiIndicatorView(Context context) {
        this(context,null);
    }

    public EmojiIndicatorView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public EmojiIndicatorView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext=context;
        pointSize= DisplayUtils.dp2px(context,size);
        marginLeft= DisplayUtils.dp2px(context,marginSize);
    }

    /**
     * 初始化指示器
     * @param count 指示器的数量
     */
    public void initIndicator(int count){
        mImageViews = new ArrayList<>();
        this.removeAllViews();
        LayoutParams params ;
        for (int i = 0 ; i<count ; i++){
            View v = new View(mContext);
            params = new LayoutParams(pointSize,pointSize);
            if(i!=0)
                params.leftMargin = marginLeft;
            v.setLayoutParams(params);
            if (i == 0){
                v.setBackgroundResource(R.drawable.shape_bg_indicator_point_select);
            }else{
                v.setBackgroundResource(R.drawable.shape_bg_indicator_point_nomal);
            }
            mImageViews.add(v);
            this.addView(v);
        }
    }
    /**
     * 页面移动时切换指示器
     * @param startPosition
     * @param nextPosition
     */
    public void playByStartPointToNext(int startPosition,int nextPosition){
        if(startPosition < 0 || nextPosition < 0 || nextPosition == startPosition){
            startPosition = nextPosition = 0;
        }
        final View ViewStrat =  mImageViews.get(startPosition);
        final View ViewNext =  mImageViews.get(nextPosition);
        ViewNext.setBackgroundResource(R.drawable.shape_bg_indicator_point_select);
        ViewStrat.setBackgroundResource(R.drawable.shape_bg_indicator_point_nomal);
    }
}
