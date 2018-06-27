package com.itislevel.lyl.utils;

import android.annotation.TargetApi;
import android.app.Activity;
import android.graphics.Rect;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewTreeObserver;

import com.itislevel.lyl.app.App;
import com.itislevel.lyl.app.Constants;
import com.orhanobut.logger.Logger;

/**
 * **********************
 * 功 能:获取软键盘的高度
 * 创建人:itisi
 * 邮  箱:itisivip@qq.com
 * 创建时间:2017/8/3 10:34
 * 修改人:itisi
 * 修改时间: 2017/8/3 10:34
 * 修改内容:itisi
 * *********************
 */
public class SoftKeyboardUtil {
    /**
     * 获取软键盘的高度--很多时候无效 用 observeSoftKeyboard
     * 需设置:android:windowSoftInputMode="adjustResize"
     * @param activity
     * @return
     */
    public static int getSupportSoftInputHeight(Activity activity) {
        Rect rect = new Rect();
        /**
         * decorView是window中的最顶层view，可以从window中通过getDecorView获取到decorView。
         * 通过decorView获取到程序显示的区域，包括标题栏，但不包括状态栏。
         */
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
        //获取屏幕的高度
//        int screenHeight = activity.getWindow().getDecorView().getRootView().getHeight();
        int screenHeight = App.SCREEN_HEIGHT;//屏幕高度
        //计算软件盘的高度
        int softInputHeight = screenHeight - rect.bottom;
        /**
         * 某些Android版本下，没有显示软键盘时减出来的高度总是144，而不是零，
         * 这是因为高度是包括了虚拟按键栏的(例如华为系列)，所以在API Level高于20时，
         * 我们需要减去底部虚拟按键栏的高度（如果有的话）
         */
        if (Build.VERSION.SDK_INT >= 20) {
            // When SDK Level >= 20 (Android L), the softInputHeight will contain the height of softButtonsBar (if has)
            softInputHeight = softInputHeight - getSoftButtonsBarHeight(activity);
        }

        if (softInputHeight < 0) {
            Logger.w("EmotionKeyboard--Warning: value of softInputHeight is below zero!");
        }
        //存一份到本地
        if (softInputHeight > 0) {
            SharedPreferencedUtils.setInt(Constants.SOFT_INPUT_HEIGHT, softInputHeight);//554
        }
        return softInputHeight;
    }

    /**
     * 底部虚拟按键栏的高度
     * 如果有
     * @return
     */
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    public static int getSoftButtonsBarHeight(Activity activity) {
        DisplayMetrics metrics = new DisplayMetrics();
        //这个方法获取可能不是真实屏幕的高度
        activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int usableHeight = metrics.heightPixels;
        //获取当前屏幕的真实高度
        activity.getWindowManager().getDefaultDisplay().getRealMetrics(metrics);
        int realHeight = metrics.heightPixels;
        if (realHeight > usableHeight) {
            return realHeight - usableHeight;
        } else {
            return 0;
        }
    }

    /**
     * 获取软键盘的高度-有效
     * 不包含底部虚拟导航高度
     * @param activity
     * @param listener
     */
    public static void observeSoftKeyboard(Activity activity, final OnSoftKeyboardChangeListener listener) {
        final View decorView = activity.getWindow().getDecorView();
        decorView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            int previousKeyboardHeight = -1;
            @Override
            public void onGlobalLayout() {
                //可视区域的大小
                Rect rect = new Rect();//0 48 -720 1280
                decorView.getWindowVisibleDisplayFrame(rect);
                // 减去状态栏的高度?
                int displayHeight = rect.bottom - rect.top;//1280 -48=1232
                int height = decorView.getHeight();//这个还是屏幕的高度嘛 1280
                int keyboardHeight = height - displayHeight;//1280 -
                if (previousKeyboardHeight != keyboardHeight) {
                    boolean hide = (double) displayHeight / height > 0.8;
                    listener.onSoftKeyBoardChange(keyboardHeight-rect.top, !hide);
                }
                previousKeyboardHeight = height;

            }
        });
    }

    public interface OnSoftKeyboardChangeListener {
        void onSoftKeyBoardChange(int softKeybardHeight, boolean visible);
    }


}
