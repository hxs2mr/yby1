package com.itislevel.lyl.utils;

import android.content.Context;
import android.util.Log;
import java.lang.reflect.Method;

/*
* 在Android P之前的刘海屏手机  只做到适配华为  小米  vivo oppo   后面需要的自己查文档找方法   Android P之前的刘海屏适配
* */
public class PhoneUtil {
    //vivo手机
    public static final int VIVO_NOTCH = 0x00000020;//是否有刘海
    public static final int VIVO_FILLET = 0x00000008;//是否有圆角


    public static String getSystemModel() {//获取手机制作厂商
        return android.os.Build.MANUFACTURER;
    }
    public static boolean hasNotchAtHuawei(Context context) {//华为手机刘海判断
        boolean ret = false;
        try {
            ClassLoader classLoader = context.getClassLoader();
            Class HwNotchSizeUtil = classLoader.loadClass("com.huawei.android.util.HwNotchSizeUtil");
            Method get = HwNotchSizeUtil.getMethod("hasNotchInScreen");
            ret = (boolean) get.invoke(HwNotchSizeUtil);
        } catch (ClassNotFoundException e) {
            Log.e("Notch", "hasNotchAtHuawei ClassNotFoundException");
        } catch (NoSuchMethodException e) {
            Log.e("Notch", "hasNotchAtHuawei NoSuchMethodException");
        } catch (Exception e) {
            Log.e("Notch", "hasNotchAtHuawei Exception");
        } finally {
            return ret;
        }
    }
    //获取刘海尺寸：width、height
    //int[0]值为刘海宽度 int[1]值为刘海高度
    public static int[] getNotchSizeAtHuawei(Context context) {//华为手机的刘海尺寸
        int[] ret = new int[]{0, 0};
        try {
            ClassLoader cl = context.getClassLoader();
            Class HwNotchSizeUtil = cl.loadClass("com.huawei.android.util.HwNotchSizeUtil");
            Method get = HwNotchSizeUtil.getMethod("getNotchSize");
            ret = (int[]) get.invoke(HwNotchSizeUtil);
        } catch (ClassNotFoundException e) {
            Log.e("Notch", "getNotchSizeAtHuawei ClassNotFoundException");
        } catch (NoSuchMethodException e) {
            Log.e("Notch", "getNotchSizeAtHuawei NoSuchMethodException");
        } catch (Exception e) {
            Log.e("Notch", "getNotchSizeAtHuawei Exception");
        } finally {
            return ret;
        }
    }


    //vivo不提供接口获取刘海尺寸，目前vivo的刘海宽为100dp,高为27dp。
    public static boolean hasNotchAtVivo(Context context) {//判断Vivo
        boolean ret = false;
        try {
            ClassLoader classLoader = context.getClassLoader();
            Class FtFeature = classLoader.loadClass("android.util.FtFeature");
            Method method = FtFeature.getMethod("isFeatureSupport", int.class);
            ret = (boolean) method.invoke(FtFeature, VIVO_NOTCH);
        } catch (ClassNotFoundException e) {
            Log.e("Notch", "hasNotchAtVivo ClassNotFoundException");
        } catch (NoSuchMethodException e) {
            Log.e("Notch", "hasNotchAtVivo NoSuchMethodException");
        } catch (Exception e) {
            Log.e("Notch", "hasNotchAtVivo Exception");
        } finally {
            return ret;
        }
    }
/*
* OPPO不提供接口获取刘海尺寸，目前其有刘海屏的机型尺寸规格都是统一的。不排除以后机型会有变化。
其显示屏宽度为1080px，高度为2280px。刘海区域则都是宽度为324px, 高度为80px。
* */
    public static boolean hasNotchAtOPPO(Context context) {//判断OPPO是否有刘海屏
        return context.getPackageManager().hasSystemFeature("com.oppo.feature.screen.heteromorphism");
    }

    /*
    *
    * 小米的状态栏高度会略高于刘海屏的高度，因此可以通过获取状态栏的高度来间接避开刘海屏，获取状态栏的高度代码如下：
    * */

    public static int getStatusBarHeight(Context context) {
        int statusBarHeight = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            statusBarHeight = context.getResources().getDimensionPixelSize(resourceId);
        }
        return statusBarHeight;
    }
}
