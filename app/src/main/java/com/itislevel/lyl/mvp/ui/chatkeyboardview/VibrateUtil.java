package com.itislevel.lyl.mvp.ui.chatkeyboardview;

import android.app.Service;
import android.content.Context;
import android.os.Vibrator;
import android.util.Log;

/**
 * **********************
 * 功 能:手机振动器的工具类
 * 创建人:itisi
 * 邮  箱:itisivip@qq.com
 * 创建时间:2017/6/28 11:34
 * 修改人:itisi
 * 修改时间: 2017/6/28 11:34
 * 修改内容:itisi
 * *********************
 */
public class VibrateUtil {
    private static final String TAG = "VibrateUtil";

    /**
     * 按指定的时间周期性震动
     * @param context 上下文
     * @param pattern 震动的时间点
     * @param repeat 重复次数 -1不重复
     *               例如指定pattern为new long[]{400,800,1200,1600}，
     *               就是指定在400ms、800ms、1200ms、1600ms这些时间点交替启动、
     *               关闭手机振动器，其中repeat指定pattern数组的索引，指定pattern数组中从
     *               repeat索引开始的振动进行循环。
     *               -1表示只振动一次，非-1表示从 pattern的指定下标开始重复振动。
     */
    public static void vibrate(Context context, long[] pattern, int repeat) {
        Vibrator systemService = (Vibrator) context.getSystemService(Service.VIBRATOR_SERVICE);
        if (systemService.hasVibrator()) {
            systemService.cancel();
            systemService.vibrate(pattern, repeat);
        } else {
            Log.i(TAG, "shorVibrate: 手机没有振动器");
        }
    }

    /**
     * 指定震动时长
     * @param context 上下文
     * @param time 震动时长 ms
     */
    public static void vibrate(Context context, long time){
        Vibrator systemService = (Vibrator) context.getSystemService(Service.VIBRATOR_SERVICE);
        if (systemService.hasVibrator()){
            systemService.cancel();
            systemService.vibrate(time);
        }else{
            Log.i(TAG, "shorVibrate: 手机没有振动器");
        }
    }

    /**
     * 震动 200 ms
     * @param context 上下文
     */
    public static void vibrate(Context context){
        Vibrator systemService = (Vibrator) context.getSystemService(Service.VIBRATOR_SERVICE);
        if (systemService.hasVibrator()){
            systemService.cancel();
            systemService.vibrate(200);
        }else{
            Log.i(TAG, "shorVibrate: 手机没有振动器");
        }
    }


}
