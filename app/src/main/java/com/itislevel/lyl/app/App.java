package com.itislevel.lyl.app;

import android.app.Activity;
import android.app.Application;
import android.app.Service;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Vibrator;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;
import android.support.v7.app.AppCompatDelegate;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;


import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.itislevel.lyl.R;
import com.itislevel.lyl.di.component.AppComponent;
import com.itislevel.lyl.di.component.DaggerAppComponent;
import com.itislevel.lyl.di.module.AppModule;
import com.itislevel.lyl.di.module.HttpModule;
import com.itislevel.lyl.init.Frame;
import com.itislevel.lyl.mvp.model.DataManager;
import com.itislevel.lyl.mvp.model.bean.UserHeaderNickInfo;
import com.itislevel.lyl.mvp.model.http.response.LYLResponse;
import com.itislevel.lyl.mvp.receiver.LocationService;
import com.itislevel.lyl.net.AddCookieInterceptor;
import com.itislevel.lyl.utils.GsonUtil;
import com.itislevel.lyl.utils.SharedPreferencedUtils;
import com.itislevel.lyl.utils.imageload.ImageLoadConfiguration;
import com.itislevel.lyl.utils.imageload.ImageLoadProxy;
import com.lzy.ninegrid.NineGridView;
import com.orhanobut.logger.Logger;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreater;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreater;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.tencent.bugly.Bugly;
import com.tencent.bugly.crashreport.CrashReport;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;
import com.vondear.rxtools.RxTool;
import com.vondear.rxtools.module.wechat.share.WechatShareTools;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import cn.finalteam.rxgalleryfinal.RxGalleryFinalApi;
import cn.jpush.android.api.JPushInterface;
import io.reactivex.Observable;
import io.rong.imkit.RongIM;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.Message;
import io.rong.imlib.model.UserInfo;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;


/**
 * **********************
 * 功 能:全局应用程序类
 * 创建人:itisi
 * 邮  箱:itisivip@qq.com
 * 创建时间:2017/7/5 16:28
 * 修改人:itisi
 * 修改时间: 2017/7/5 16:28
 * 修改内容:itisi
 * *********************
 */
public class App extends Application {
    private static App instance;
    private Set<Activity> allActivities;
    public static AppComponent appComponent;

    public static int SCREEN_WIDTH = -1;
    public static int SCREEN_HEIGHT = -1;
    public static float DIMEN_RATE = -1.0F;
    public static int DIMEN_DPI = -1;
    public LocationService locationService;
    public Vibrator mVibrator;
    static {
        //初始化主题
        AppCompatDelegate.setDefaultNightMode(
                AppCompatDelegate.MODE_NIGHT_NO);
    }
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        initAll();
        //初始化屏幕宽高
        getScreenSize();
        Frame.init(this)
                .withApiHost("http://119.27.169.152:6064/user/")
                //.withInterceptor(new DebugInterceptor("index.html",R.raw.test))
                .withWxchaAppId("wxace207babfef510d")//微信的APPID
                .withWxchartSecRet("ec5f7134a2c99e34e9a0f90c896da95d")//微信的scret
                .withJavaScriptinterface("web")
                //添加Cookie同步拦截器
                .withInterceptor(new AddCookieInterceptor())
                .weithWebHost("https://www.baidu.com/")
                .configure();//初始化

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Roboto-RobotoRegular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build());//初始化字体
    }

    private void initAll() {
        /***
         * 初始化定位sdk，建议在Application中创建
         */
        locationService = new LocationService(this);
        mVibrator =(Vibrator)getApplicationContext().getSystemService(Service.VIBRATOR_SERVICE);
        //初始化数据库
//        Realm.init(getApplicationContext());
        //在子线程中完成其他初始化 异常检测 回头再说
//        InitializeService.start(this);
            //Logger.init();//初始化日志信息
//        EaseMobUtil.init(this);//初始化环信

        // TODO: 2017/7/25  第三个参数为SDK调试模式开关，调试模式的行为特性如下：
        /*
            输出详细的Bugly SDK的Log；
            每一条Crash都会被立即上报；
            自定义日志将会在Logcat中输出
            建议在测试阶段建议设置成true，发布时设置为false。

         */
        Bugly.init(getApplicationContext(), "e237fd1e59", false);
        //九宫格图片加载器
        NineGridView.setImageLoader(new GlideImageLoader());


        //云信的初始化
      /*  //融云 会话界面 头像点击事件
        //初始化 融云
        RongIM.init(this);
        *//**
         * 设置消息体内是否携带用户信息。
         * @param state 是否携带用户信息，true 携带，false 不携带。
         *//*
        RongIM.getInstance().setMessageAttachedUserInfo(true);

//        RongIMClient.init(this);

        RongIM.setConversationBehaviorListener(new RongIM.ConversationBehaviorListener() {
            *//**
             * 当点击用户头像后执行。
             * 如果用户自己处理了点击后的逻辑处理，则返回 true，否则返回 false，false 走融云默认处理方式。
             * @param context
             * @param conversationType
             * @param userInfo
             * @return
             *//*
            @Override
            public boolean onUserPortraitClick(Context context, Conversation.ConversationType conversationType, UserInfo userInfo) {
                Logger.i("portraitlick");
                return false;
            }

            *//**
             * 当长按用户头像后执行。
             * 如果用户自己处理了点击后的逻辑处理，则返回 true，否则返回 false，false 走融云默认处理方式。
             * @param context
             * @param conversationType
             * @param userInfo
             * @return
             *//*
            @Override
            public boolean onUserPortraitLongClick(Context context, Conversation.ConversationType
                    conversationType, UserInfo userInfo) {
                Logger.i("portraitlongclick");
                return false;
            }

            *//**
             * 当点击消息时执行。
             * 如果用户自己处理了点击后的逻辑处理，则返回 true， 否则返回 false, false 走融云默认处理方式。
             * @param context
             * @param view
             * @param message
             * @return
             *//*
            @Override
            public boolean onMessageClick(Context context, View view, Message message) {
                Logger.i("messageclick");

                return false;
            }

            *//**
             * 当点击链接消息时执行。
             * 如果用户自己处理了点击后的逻辑处理，则返回 true， 否则返回 false, false 走融云默认处理方式。
             * @param context
             * @param s
             * @return
             *//*
            @Override
            public boolean onMessageLinkClick(Context context, String s) {
                Logger.i("messagelinkclick");

                return false;
            }

            *//**
             * 当长按消息时执行
             * 如果用户自己处理了长按后的逻辑处理，则返回 true，否则返回 false，false 走融云默认处理方式。
             * @param context
             * @param view
             * @param message
             * @return
             *//*
            @Override
            public boolean onMessageLongClick(Context context, View view, Message message) {
                Logger.i("messagelongclick");

                return false;
            }
        });
*/
        setPath();
        RxTool.init(this);
        WechatShareTools.init(this, "wxa95be83575c37610");//初始化

        JPushInterface.init(getApplicationContext());
        SharedPreferencedUtils.setStr("LYL","false");
        PlatformConfig.setWeixin("wx9428227f6e4cf4c2", "d6062a90ed0a567ca20a8dfab85c66a7");
        PlatformConfig.setQQZone("101454867", "56a254269da2bddf4539c18e7ea6596a");
        UMShareAPI.get(this);
    }


    //下拉刷新的头部 和 底部
    //static 代码段可以防止内存泄露
    static {
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreater(new DefaultRefreshHeaderCreater() {
            @Override
            public RefreshHeader createRefreshHeader(Context context, RefreshLayout layout) {
                layout.setPrimaryColorsId(R.color.banncolor, android.R.color.white);//全局设置主题颜色
                return new ClassicsHeader(context);//.setTimeFormat(new DynamicTimeFormat("更新于
                // %s"));//指定为经典Header，默认是 贝塞尔雷达Header
            }
        });
        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreater(new DefaultRefreshFooterCreater() {
            @Override
            public RefreshFooter createRefreshFooter(Context context, RefreshLayout layout) {
                //指定为经典Footer，默认是 BallPulseFooter
                ClassicsFooter classicsFooter = new ClassicsFooter(context).setDrawableSize(20);
                return classicsFooter;
            }
        });
    }

    /**
     * 设置 照片路径 和 裁剪路径
     * //裁剪会自动生成路径；也可以手动设置裁剪的路径；
     */
    private void setPath() {
        RxGalleryFinalApi.setImgSaveRxSDCard(Constants.PATH_GALLERY);
        RxGalleryFinalApi.setImgSaveRxCropSDCard(Constants.PATH_GALLERY_CROP);
    }


    /**
     * Glide 加载
     */
    private class GlideImageLoader implements NineGridView.ImageLoader {

        @Override
        public void onDisplayImage(Context context, ImageView imageView, String url) {
//            Glide.with(context).load(url)//
//                    .placeholder(R.mipmap.ic_launcher)//
//                    .error(R.mipmap.icon_login_logo)//
//                    .into(imageView);

            ImageLoadProxy.getInstance()
                    .load(new ImageLoadConfiguration.Builder(App.getInstance())
                            .defaultImageResId(R.mipmap.icon_img_load_pre)
                            .url(url)
                            .imageView(imageView).build());
       /*     Glide.with(App.getInstance())
                    .load(url)//
                    .asBitmap()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .error(R.mipmap.ic_launcher)//
                    .into(imageView);*/

        }
        public void onDisplayImage(Context context, ImageView imageView, int resId) {
//            Glide.with(context).load(resId)//
//                    .placeholder(R.mipmap.ic_launcher)//
//                    .error(R.mipmap.icon_login_logo)//
//                    .into(imageView);


        ImageLoadProxy.getInstance()
                .load(new ImageLoadConfiguration.Builder(App.getInstance())
                        .defaultImageResId(R.mipmap.icon_img_load_pre)
                        .url(resId)
                        .imageView(imageView).build());

        }
        @Override
        public Bitmap getCacheImage(String url) {
            return null;
        }
    }

    /**
     * 获取全局应用程序上下文
     *
     * @return
     */
    public static synchronized App getInstance() {
        return instance;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);////分割Dex，方法数超过64k
    }

    /**
     * 将当前activity 实例添加到集合中
     *
     * @param activity 当前activity
     */
    public void addActivity(Activity activity) {
        if (allActivities == null) {
            allActivities = new HashSet<>();
        }
        allActivities.add(activity);
    }

    /**
     * 将当前activity 实例从集合中移除
     *
     * @param activity 当前activity
     */
    public void removeActivity(Activity activity) {
        if (allActivities != null) {
            allActivities.remove(activity);
        }
    }

    /**
     * 获得屏幕尺寸
     */
    public void getScreenSize() {
        WindowManager windowManager = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        Display display = windowManager.getDefaultDisplay();
        display.getMetrics(dm);
        DIMEN_RATE = dm.density / 1.0F;
        DIMEN_DPI = dm.densityDpi;
        SCREEN_WIDTH = dm.widthPixels;
        SCREEN_HEIGHT = dm.heightPixels;
        if (SCREEN_WIDTH > SCREEN_HEIGHT) {
            int t = SCREEN_HEIGHT;
            SCREEN_HEIGHT = SCREEN_WIDTH;
            SCREEN_WIDTH = t;
        }
    }

    /**
     * 退出app
     */
    public  void exitApp() {
        if (allActivities != null) {
            synchronized (allActivities) {
                for (Activity activity : allActivities) {
                    activity.finish();
                }
            }
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(0);
        }
    }

    public static AppComponent getAppComponent() {
        if (appComponent == null) {
            appComponent = DaggerAppComponent.builder()
                    .appModule(new AppModule(instance))
                    .httpModule(new HttpModule())
                    .build();
        }
        return appComponent;
    }

}
