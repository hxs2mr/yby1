package com.itislevel.lyl.utils;

import android.app.Activity;

import com.itislevel.lyl.R;
import com.orhanobut.logger.Logger;


/**
 ***********************
 * 功 能:转场动画 老式的 5.0 以前的
 * 创建人:itisi
 * 邮  箱:itisivip@qq.com
 * 创建时间:2017/7/7 14:16
 * 修改人:itisi
 * 修改时间: 2017/7/7 14:16
 * 修改内容:itisi
 * *********************
 */
public class SceneAnim {

    public enum AnimType{
        BOTTOM_IN,
        BOTTOM_OUT,
        FADE_IN,
        FADE_OUT,
        LEFT_IN,
        LEFT_OUT,
        RIGHT_IN,
        RIGHT_OUT,
        SCALE_IN,
        SCALE_OUT,
        TOP_IN,
        TOP_OUT,
        ZOOM_IN,
        ZOOM_OUT

    }

    /**
     * 打开一个新的界面 缩放 和透明度动画
     * @param activity
     */
    public static void openActivityWithAnim(Activity activity){
        activity.overridePendingTransition(R.anim.zoom,0);
    }
    public static void openActivityWithAnim(Activity activity, AnimType animInType){
        int animinId=R.anim.zoom;
        int animoutId=R.anim.zoom;

        switch (animInType){
            case BOTTOM_IN:
                animinId=R.anim.bottom_in;
                animoutId=R.anim.top_out;
                break;
            case TOP_IN:
                animinId=R.anim.top_in;
                animoutId=R.anim.bottom_out;
                break;
            case RIGHT_IN:// 感觉这俩反了---不管了 暂时不用
                animinId=R.anim.right_in;
                animoutId=R.anim.left_out;
                break;
            case LEFT_IN:
                animinId=R.anim.left_in;
                animoutId=R.anim.right_out;
                break;

        }
        activity.overridePendingTransition(animinId,0);
    }

    /**
     * 关闭一个新的界面 缩放 和透明度动画
     * @param activity
     */
    public static void closeActivityWithAnim(Activity activity){
        activity.overridePendingTransition(0,R.anim.right_out);
    }


}
