package com.itislevel.lyl.utils;

import android.os.SystemClock;

/**
 * author: itisi---
 * created by Administrator on 2017/3/22.
 * desc:实现多次点击的功能
 */
public class ClickTree {
    /**
     * 点击的次数
     */
    private int times;
    private  long[] mHits;
    /**
     *
     * @param times 本次点击的次数
     */
    public ClickTree(int times) {
        this.times = times;
        mHits=new long[times];
    }
    public  boolean completeClickCount(){
        System.arraycopy(mHits,1,mHits,0,mHits.length-1);
        mHits[mHits.length-1]= SystemClock.uptimeMillis();//获取开机到现在的时间
        if(mHits[0]>=(SystemClock.uptimeMillis()-500)){//两次点击的间隔为500ms
            //完成多次点击
            mHits=null;
            mHits=new long[times];
            return true;
        }
        else{
            return false;
        }
    }
}
