package com.itislevel.lyl.utils;

import com.itislevel.lyl.app.App;
import com.sdsmdg.tastytoast.TastyToast;

/**
 ***********************
 * 功 能:基于TastyToast 的toast再次封装
 * 创建人:itisi
 * 邮  箱:itisivip@qq.com
 * 创建时间:2017/7/7 10:25
 * 修改人:itisi
 * 修改时间: 2017/7/7 10:25
 * 修改内容:itisi
 * *********************
 */
public class ToastUtil {

    public static void Success(String msg){
        SAToast.makeText(App.getInstance(),msg).show();
    }
    public static void Error(String msg){
        SAToast.makeText(App.getInstance(),msg).show();
    }
    public static void Info(String msg){
        //TastyToast.makeText(App.getInstance(),msg,1,TastyToast.INFO).show();

        SAToast.makeText(App.getInstance(),msg).show();
    }
    public static void Warning(String msg){
        SAToast.makeText(App.getInstance(),msg).show();
    }
    public static void Confusing(String msg){
        SAToast.makeText(App.getInstance(),msg).show();
    }

}
