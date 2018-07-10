package com.itislevel.lyl.mvp.ui.main;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.DisplayCutout;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.flyco.animation.BaseAnimatorSet;
import com.flyco.animation.SlideEnter.SlideBottomEnter;
import com.flyco.animation.SlideExit.SlideBottomExit;
import com.flyco.dialog.listener.OnBtnClickL;
import com.flyco.dialog.widget.NormalDialog;
import com.itislevel.lyl.R;
import com.itislevel.lyl.app.App;
import com.itislevel.lyl.app.Constants;
import com.itislevel.lyl.base.RootActivity;
import com.itislevel.lyl.mvp.model.DataManager;
import com.itislevel.lyl.mvp.model.bean.AppUpdate;
import com.itislevel.lyl.mvp.model.bean.JPDynamicSendBean;
import com.itislevel.lyl.mvp.model.bean.JPSuccess;
import com.itislevel.lyl.mvp.model.bean.JPushBean;
import com.itislevel.lyl.mvp.model.bean.MainBean;
import com.itislevel.lyl.mvp.model.bean.PlaceBean;
import com.itislevel.lyl.mvp.model.bean.UserHeaderNickInfo;
import com.itislevel.lyl.mvp.ui.main.customer.CustomerFragment;
import com.itislevel.lyl.mvp.ui.main.dynamic.DynamicFragment;
import com.itislevel.lyl.mvp.ui.main.home.HomeFragment;
import com.itislevel.lyl.mvp.ui.main.mine.MineFragment;
import com.itislevel.lyl.mvp.ui.myteam.MyTeamActivity;
import com.itislevel.lyl.utils.ClickTree;
import com.itislevel.lyl.utils.GsonUtil;
import com.itislevel.lyl.utils.SharedPreferencedUtils;
import com.itislevel.lyl.utils.ToastUtil;
import com.itislevel.lyl.utils.rxbus.RxBus;
import com.itislevel.lyl.utils.rxbus.annotation.Subscribe;
import com.itislevel.lyl.utils.rxbus.annotation.UseRxBus;
import com.itislevel.lyl.utils.rxbus.event.EventThread;
import com.itislevel.lyl.utils.update.DownloadProgress;
import com.itislevel.lyl.utils.update.RxUpdateUtil;
import com.itislevel.lyl.widget.videorecord.TCVideoRecordActivity;
import com.itislevel.lyl.widget.videorecord.TCVideoSettingActivity;
import com.orhanobut.logger.Logger;
import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.tencent.rtmp.TXLiveConstants;
import com.tencent.ugc.TXRecordCommon;
import com.vondear.rxtools.RxDeviceTool;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import cn.jpush.android.api.JPushInterface;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.rong.imlib.model.UserInfo;
import me.leolin.shortcutbadger.ShortcutBadgeException;
import me.leolin.shortcutbadger.ShortcutBadger;
import okhttp3.ResponseBody;
import qiu.niorgai.StatusBarCompat;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

import com.tencent.rtmp.TXLiveBase;
import com.vondear.rxtools.activity.AndroidBug5497Workaround;

import org.greenrobot.eventbus.EventBus;

@UseRxBus
public class MainActivity extends RootActivity<MainPresenter> implements MainContract.View,View.OnClickListener {
    @Inject
    DataManager dataManager;

    @BindView(R.id.fl_main)
    FrameLayout fl_main;

    @BindView(R.id.ll_nav_home)
    LinearLayout ll_nav_home;
    @BindView(R.id.iv_nav_home)
    ImageView iv_nav_home;
    @BindView(R.id.tv_nav_home)
    TextView tv_nav_home;

    @BindView(R.id.ll_nav_customer)
    LinearLayout ll_nav_customer;
    @BindView(R.id.iv_nav_customer)
    ImageView iv_nav_customer;
    @BindView(R.id.tv_nav_customer)
    TextView tv_nav_customer;

    @BindView(R.id.ll_nav_mine)
    LinearLayout ll_nav_mine;
    @BindView(R.id.iv_nav_mine)
    ImageView iv_nav_mine;
    @BindView(R.id.tv_nav_mine)
    TextView tv_nav_mine;

    @BindView(R.id.ll_nav_dynamic)
    LinearLayout ll_nav_dynamic;

    @BindView(R.id.iv_nav_dynamic)
    ImageView iv_nav_dynamic;

    @BindView(R.id.tv_nav_dynamic)
    TextView tv_nav_dynamic;
    @BindView(R.id.main_botoom_linear)
    public
    LinearLayout main_botoom_linear;

    @BindView(R.id.customer_number)
     TextView customer_number;
    //3个主界面
    HomeFragment homeFragment;//主页
    CustomerFragment customerFragment;//关注
    DynamicFragment dynamicFragment;
    MineFragment mineFragment;//我的

    String[] mBottomItems = null;

    ClickTree mClickTree = new ClickTree(2); //点击树
    private List<Fragment> mFragments;//main 中的几个页面的fragment集合
    private Fragment mCurrentFragment;//home中当前显示的fragment
    private FragmentManager mFragmentManager;
    private FragmentTransaction mTransaction;

    // 推送
    private LocalBroadcastManager localBroadcastManager;
    private MyBroadcastReceiver broadcastReceiver;
    private IntentFilter intentFilter;

    private BaseAnimatorSet mBasIn;
    private BaseAnimatorSet mBasOut;

    public static int MESSAGE_NUMBER=0;
    private static  int HOME_FALGE=0;

    public static  int jiaobiao_number=0;
    //    // 再点一次退出程序时间设置
    private static final long WAIT_TIME = 2000L;
    private long TOUCH_TIME = 0;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        jiaobiao_number = 0;
        ShortcutBadger.applyCount(this, 0); //for 1.1.4+//去除角标
        try {
            ShortcutBadger.applyCountOrThrow(this,0); //for 1.1.3
        }catch (ShortcutBadgeException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void initEventAndData() {
        StatusBarCompat.translucentStatusBar(this, true);
        AndroidBug5497Workaround.assistActivity(this);
       /* String sdkver = TXLiveBase.getSDKVersionStr();//暂时先不用  在模拟器上
        Logger.e( "liteav sdk version is : " + sdkver);*/

        mBasIn = new SlideBottomEnter();
        mBasOut = new SlideBottomExit();

        // 推送
        //注册广播接收器
        localBroadcastManager = LocalBroadcastManager.getInstance(this);
        broadcastReceiver = new MyBroadcastReceiver();
        intentFilter = new IntentFilter("myaction");
        localBroadcastManager.registerReceiver(broadcastReceiver, intentFilter);

        if (mBottomItems == null) { //这里估计不会走 因为 在 setToolbarTvTitle 已经获取一次了
            mBottomItems = getResources().getStringArray(R.array.bottomMenu);
        }
        setToolbarTvTitle(mBottomItems[0]);//初始化标题
        mFragmentManager = getSupportFragmentManager();

        initMainPage();
        initBottomNav();
        initViewListener();
       // initPermission();

    /*    getTvTitleView().setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                startVideoRecordActivity();
                return true;

            }
        });*/
        Map<String, String> request = new HashMap<>();

        request.put("token", SharedPreferencedUtils.getStr(Constants.USER_TOKEN));
        request.put("usernum", SharedPreferencedUtils.getStr(Constants.USER_NUM));
        request.put("userid", SharedPreferencedUtils.getStr(Constants.USER_ID));
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://app.yobangyo.com/user/")//http://app.yobangyo.com/user/         //http://119.27.169.152:6064/user/
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        MainService service = retrofit.create(MainService.class);
        Call<ResponseBody> isproject = service.isproject(GsonUtil.obj2JSON(request));

        // mPresenter.appupdate("");//更新检查
         checkNotification();
    }


    private int mBiteRate = 2400; // 码率
    private int mFps = 20; // 帧率
    private int mGop = 3; // 关键帧间隔

    private void startVideoRecordActivity() {
        Intent intent = new Intent(this, TCVideoRecordActivity.class);
        intent.putExtra(TCVideoSettingActivity.RECORD_CONFIG_MIN_DURATION, 5 * 1000);
        intent.putExtra(TCVideoSettingActivity.RECORD_CONFIG_MAX_DURATION, 60 * 1000);
        intent.putExtra(TCVideoSettingActivity.RECORD_CONFIG_ASPECT_RATIO, TXRecordCommon.VIDEO_ASPECT_RATIO_1_1);
        int mRecommendQuality=-1;
        if (mRecommendQuality != -1) {
            // 提供的三挡设置
            intent.putExtra(TCVideoSettingActivity.RECORD_CONFIG_RECOMMEND_QUALITY, mRecommendQuality);
        } else {
            // 自定义设置
            intent.putExtra(TCVideoSettingActivity.RECORD_CONFIG_RESOLUTION, TXRecordCommon.VIDEO_RESOLUTION_360_640);
            intent.putExtra(TCVideoSettingActivity.RECORD_CONFIG_BITE_RATE, mBiteRate);
            intent.putExtra(TCVideoSettingActivity.RECORD_CONFIG_FPS, mFps);
            intent.putExtra(TCVideoSettingActivity.RECORD_CONFIG_GOP, mGop);
        }
        intent.putExtra(TCVideoSettingActivity.RECORD_CONFIG_HOME_ORIENTATION, TXLiveConstants.VIDEO_ANGLE_HOME_DOWN); // 竖屏录制
        intent.putExtra(TCVideoSettingActivity.RECORD_CONFIG_NEED_EDITER, true);//cbEdit.isChecked()
        startActivity(intent);
    }

    // 4.4以上的系统才有效
    private void checkNotification() {
        NotificationManagerCompat manager = NotificationManagerCompat.from(this);
        boolean isOpened = manager.areNotificationsEnabled();
        Logger.w("通知权限是否开启:"+isOpened);
        if (!isOpened){

            final NormalDialog dialog = new NormalDialog(this);
            dialog.content("为了体验更好,请开启显示通知")//
//                    .isTitleShow(false)
                    .title("温馨提示")
                    .style(NormalDialog.STYLE_TWO)//
                    .titleTextSize(23)//
                    .showAnim(mBasIn)//
                    .dismissAnim(mBasOut)//
                    .show();

            dialog.setOnBtnClickL(
                    new OnBtnClickL() {
                        @Override
                        public void onBtnClick() {//cancel
                            dialog.dismiss();
                        }
                    },
                    new OnBtnClickL() {
                        @Override
                        public void onBtnClick() {//ok
                            Intent localIntent = new Intent();
                            localIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            if (Build.VERSION.SDK_INT >= 9) {
                                localIntent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
                                localIntent.setData(Uri.fromParts("package", getPackageName(), null));
                            } else if (Build.VERSION.SDK_INT <= 8) {
                                localIntent.setAction(Intent.ACTION_VIEW);
                                localIntent.setClassName("com.android.settings", "com.android.settings.InstalledAppDetails");
                                localIntent.putExtra("com.android.settings.ApplicationPkgName", getPackageName());
                            }
                            startActivity(localIntent);
                            dialog.dismiss();
                        }
                    });
        }

    }

    List<String> headers = new ArrayList<>();
    List<String> nicks = new ArrayList<>();

    private UserInfo findUserById(String userId) {

        Map<String, String> request = new HashMap<>();

        request.put("token", SharedPreferencedUtils.getStr(Constants.USER_TOKEN));
        request.put("usernum", SharedPreferencedUtils.getStr(Constants.USER_NUM));
        request.put("userid", SharedPreferencedUtils.getStr(Constants.USER_ID));

        UserInfo userInfo = null;

        String userid1 = SharedPreferencedUtils.getStr(Constants.USER_ID);
        if (userId.equals(userid1)) {
            String nickname = SharedPreferencedUtils.getStr(Constants.USER_NICK_NAME);
            String header = Constants.IMG_SERVER_PATH + SharedPreferencedUtils.getStr(Constants
                    .USER_HEADER);
            userInfo = new UserInfo(userid1, nickname, Uri.parse(header));
        } else {
            String nickname = "客服";
            String header = "http://a.hiphotos.baidu" +
                    ".com/image/pic/item/500fd9f9d72a6059f550a1832334349b023bbae3.jpg)";
            userInfo = new UserInfo(userid1, nickname, Uri.parse(header));
        }
        return userInfo;

    }

    @Override
    protected void onResume() {
        super.onResume();

        if (mineFragment != null) {
            mineFragment.refreshBadgeViewCount();
        }

        if (homeFragment!=null){
            homeFragment.refreshBadgeViewCount();
        }


    }

    public interface MainService {
        @POST("selectImgNickname")
        @FormUrlEncoded
        Call<ResponseBody> isproject(@Field("action") String action);

    }

    /**
     * 保存bitmap到SD卡
     * @param bitmap
     * @param imagename
     */
    public static String saveBitmapToSDCard(Bitmap bitmap, String imagename) {
        String path = "/sdcard/" + "img-" + imagename + ".jpg";
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(path);
            if (fos != null) {
                bitmap.compress(Bitmap.CompressFormat.JPEG, 90, fos);
                fos.close();
            }

            return path;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private void initViewListener() {
      /*  iv_nav_home.setOnClickListener(this);
        iv_nav_customer.setOnClickListener(this);
        iv_nav_mine.setOnClickListener(this);*/
        ll_nav_home.setOnClickListener(this);
        ll_nav_customer.setOnClickListener(this);
        ll_nav_mine.setOnClickListener(this);
        ll_nav_dynamic.setOnClickListener(this);

 /*       tv_nav_home.setOnClickListener(this);
        tv_nav_customer.setOnClickListener(this);
        tv_nav_mine.setOnClickListener(this);*/
    }


    private void changeToolbarTitle(int position) {
        setToolbarTvTitle(mBottomItems[position]);
    }
    /**
     * 初始化页面
     */
    private void initMainPage() {
        getFragments();
        setDefaultFragment();
    }
    /**
     * 设置默认页面
     */
    private void setDefaultFragment() {
        mTransaction = mFragmentManager.beginTransaction();
        mTransaction.replace(R.id.fl_main, homeFragment);
        mTransaction.commit();
    }

    /**
     * 初始化页面集合
     */
    private void getFragments() {
        mFragments = new ArrayList<>();
        if (homeFragment == null) {
            homeFragment = new HomeFragment();
        }
        mFragments.add(homeFragment);
        if (customerFragment == null) {
            customerFragment = new CustomerFragment();
        }
        mFragments.add(customerFragment);

        if(dynamicFragment == null)
        {
            dynamicFragment = new DynamicFragment();
        }
        mFragments.add(dynamicFragment);

        if (mineFragment == null) {
            mineFragment = new MineFragment();
        }
        mFragments.add(mineFragment);

    }

    @Override
    protected boolean setIsNavigationIconShow() {
        return false;
    }


    /**
     * 初始化底部导航
     */
    private void initBottomNav() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_nav_home:
                HOME_FALGE=0;
                changeMainPage(0);
                break;
      /*      case R.id.iv_nav_home:

            case R.id.tv_nav_home:
                break;*/
            case R.id.ll_nav_customer:
                HOME_FALGE=1;
                changeMainPage(1);
                break;
/*            case R.id.iv_nav_customer:
            case R.id.tv_nav_customer:
                break;*/
            case R.id.ll_nav_dynamic:
                HOME_FALGE=2;
                customer_number.setVisibility(View.GONE);
                customer_number.setText("0");
                changeMainPage(2);
                break;
            case R.id.ll_nav_mine:
                HOME_FALGE=3;
                changeMainPage(3);
                break;
        /*    case R.id.iv_nav_mine:
            case R.id.tv_nav_mine:
                break;*/
        }
    }

    private void removePage(int position) {
        switch (position) {
            case 0:
                if (mFragments != null) {
                    if (position < mFragments.size()) {
                        mTransaction = mFragmentManager.beginTransaction();
//                        if (customerFragment.isAdded()){
//                            mTransaction.remove(customerFragment);
//                        }
//                        if (mineFragment.isAdded()){
//                            mTransaction.remove(mineFragment);
//                        }
                        if (customerFragment.isAdded()) {
                            mTransaction.hide(customerFragment);
                        }
                        if (dynamicFragment.isAdded()) {
                            mTransaction.hide(dynamicFragment);
                        }
                        if (mineFragment.isAdded()) {
                            mTransaction.hide(mineFragment);
                        }
                        mTransaction.commitAllowingStateLoss();

                    }
                }
                break;
            case 1:
                if (mFragments != null) {
                    if (position < mFragments.size()) {
                        mTransaction = mFragmentManager.beginTransaction();
                        if (homeFragment.isAdded()) {
//                            mTransaction.remove(homeFragment);
                            mTransaction.hide(homeFragment);
                        }
                        if (mineFragment.isAdded()) {
//                            mTransaction.remove(mineFragment);
                            mTransaction.hide(mineFragment);
                        }
                        if(dynamicFragment.isAdded())
                        {
                            mTransaction.hide(dynamicFragment);
                        }
                        mTransaction.commitAllowingStateLoss();

                    }
                }
                break;
            case 2:
                if (mFragments != null) {
                    if (position < mFragments.size()) {
                        mTransaction = mFragmentManager.beginTransaction();
                        if (homeFragment.isAdded()) {
//                            mTransaction.remove(homeFragment);
                            mTransaction.hide(homeFragment);
                        }
                        if (customerFragment.isAdded()) {
//                            mTransaction.remove(customerFragment);
                            mTransaction.hide(customerFragment);
                        }
                        if(mineFragment.isAdded())
                        {
                            mTransaction.hide(mineFragment);
                        }
                        mTransaction.commitAllowingStateLoss();

                    }
                }
                break;
            case 3:
                if (mFragments != null) {
                    if (position < mFragments.size()) {
                        mTransaction = mFragmentManager.beginTransaction();
                        if (homeFragment.isAdded()) {
//                            mTransaction.remove(homeFragment);
                            mTransaction.hide(homeFragment);
                        }
                        if(customerFragment.isAdded())
                        {
                            mTransaction.hide(customerFragment);
                        }
                        if (dynamicFragment.isAdded()) {
//                            mTransaction.remove(customerFragment);
                            mTransaction.hide(dynamicFragment);
                        }
                        mTransaction.commitAllowingStateLoss();
                    }
                }
                break;

        }
    }

    /**
     * 点击底部导航 切换内容页面
     */
    private void changeMainPage(int index) {
        setNavDefault();
        removePage(index);
        System.out.println(index);
        if (index < mFragments.size()) {
            mTransaction = mFragmentManager.beginTransaction();
            mCurrentFragment = mFragments.get(index);
            if (mCurrentFragment.isAdded()) {
//                mTransaction.replace(R.id.fl_main, mCurrentFragment);
                mTransaction.show(mCurrentFragment);
            } else {
                mTransaction.add(R.id.fl_main, mCurrentFragment);
            }
            // transaction.commit();
            mTransaction.commitAllowingStateLoss();
        }
        changeToolbarTitle(index);

        switch (index) {
            case 0:
                tv_nav_home.setTextColor(getResources().getColor(R.color.colorNavAct));
                iv_nav_home.setImageResource(R.mipmap.nav_home_act);
                Drawable homeTop = ContextCompat.getDrawable(this, R.mipmap.nav_home_act);
                tv_nav_home.setCompoundDrawablesWithIntrinsicBounds(null, homeTop, null, null);

                break;
            case 1:
                tv_nav_customer.setTextColor(getResources().getColor(R.color.colorNavAct));
                iv_nav_customer.setImageResource(R.mipmap.nav_customer_act);
                Drawable customerTop = ContextCompat.getDrawable(this, R.mipmap.nav_customer_act);
                tv_nav_customer.setCompoundDrawablesWithIntrinsicBounds(null, customerTop, null,
                        null);

                break;
            case 2:
                tv_nav_dynamic.setTextColor(getResources().getColor(R.color.colorNavAct));
                iv_nav_dynamic.setImageResource(R.mipmap.nav_dynamic_act);
                Drawable dynamicTop = ContextCompat.getDrawable(this, R.mipmap.nav_dynamic_act);
                tv_nav_dynamic.setCompoundDrawablesWithIntrinsicBounds(null, dynamicTop, null, null);

                break;
            case 3:
                tv_nav_mine.setTextColor(getResources().getColor(R.color.colorNavAct));
                iv_nav_mine.setImageResource(R.mipmap.nav_mine_act);
                Drawable mineTop = ContextCompat.getDrawable(this, R.mipmap.nav_mine_act);
                tv_nav_mine.setCompoundDrawablesWithIntrinsicBounds(null, mineTop, null, null);
                break;
        }

    }

    /**
     * 菜单全部设置为默认图标 字体 为选择状态
     */
    private void setNavDefault() {
        tv_nav_home.setTextColor(getResources().getColor(R.color.colorNav));
//        iv_nav_home.setImageResource(R.mipmap.nav_home);

        tv_nav_customer.setTextColor(getResources().getColor(R.color.colorNav));
//        iv_nav_customer.setImageResource(R.mipmap.nav_customer);
        tv_nav_dynamic.setTextColor(getResources().getColor(R.color.colorNav));
        tv_nav_mine.setTextColor(getResources().getColor(R.color.colorNav));
//        iv_nav_mine.setImageResource(R.mipmap.nav_mine);

        Drawable homeTop = ContextCompat.getDrawable(this, R.mipmap.nav_home);
        Drawable customerTop = ContextCompat.getDrawable(this, R.mipmap.nav_customer);
        Drawable dynamicTop = ContextCompat.getDrawable(this, R.mipmap.nav_dynamic);
        Drawable mineTop = ContextCompat.getDrawable(this, R.mipmap.nav_mine);

        tv_nav_home.setCompoundDrawablesWithIntrinsicBounds(null, homeTop, null, null);
        tv_nav_customer.setCompoundDrawablesWithIntrinsicBounds(null, customerTop, null, null);
        tv_nav_dynamic.setCompoundDrawablesWithIntrinsicBounds(null,dynamicTop,null,null);
        tv_nav_mine.setCompoundDrawablesWithIntrinsicBounds(null, mineTop, null, null);
    }

    @Override
    public void testShowView(String smg) {

    }

    @Override
    public void userInfoById(List<UserHeaderNickInfo> userHeaderNickInfos) {

    }
    @Override
    public void appupdate(AppUpdate appUpdate) {
        Logger.w("更新:" + GsonUtil.obj2JSON(appUpdate));
        String apkUrl = appUpdate.getAppurl();
        String apkVersion = appUpdate.getVersion();
//        if (RxUpdateUtil.isApkDownloaded(apkVersion)) {
//            Logger.i("不下载,取缓存");
//            return;
//        }
        int appVersion =  RxDeviceTool.getAppVersionNo(this);//获取当前APP的版本号


        String appVersionName = RxDeviceTool.getAppVersionName(this);
        if (!apkVersion.equals(appVersionName)) {

            final NormalDialog dialog = new NormalDialog(this);
            dialog.content("APP更新了,请下载体验新功能")//
                    .style(NormalDialog.STYLE_TWO)//
                    .titleTextSize(23)//
                    .showAnim(mBasIn)//
                    .dismissAnim(mBasOut)//
                    .show();

            dialog.setOnBtnClickL(
                    new OnBtnClickL() {
                        @Override
                        public void onBtnClick() {//cancel
                            dialog.dismiss();
                        }
                    },
                    new OnBtnClickL() {
                        @Override
                        public void onBtnClick() {//ok
                            // 下载新版 apk 文件
                            RxUpdateUtil.downloadApkFile(apkUrl, apkVersion)
                                    .subscribe(new Observer<File>() {
                                        @Override
                                        public void onSubscribe(@NonNull Disposable d) {

                                        }

                                        @Override
                                        public void onNext(@NonNull File file) {
                                            if (file != null && file.exists()) {
                                                RxUpdateUtil.installApp(file);
                                            }
                                        }

                                        @Override
                                        public void onError(@NonNull Throwable e) {

                                        }

                                        @Override
                                        public void onComplete() {

                                        }
                                    });

                            // 监听下载进度
                            RxUpdateUtil.getDownloadProgressObservable()
                                    .subscribe(new Consumer<DownloadProgress>() {
                                        @Override
                                        public void accept(@NonNull DownloadProgress downloadProgress) throws
                                                Exception {
                                            // 暂时没用
                                            Logger.i("下载进度:" + downloadProgress.getTotal() + "==" +
                                                    downloadProgress.getProgress());
                                        }
                                    });
                            dialog.dismiss();
                        }
                    });


        }

    }

    @Override
    public void stateEmpty() {

    }

    @Override
    public void stateLoading() {

    }


    @Override
    public void stateSuccess() {

    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Subscribe(tag = RxBus.TAG_UPDATE, thread = EventThread.MAIN_THREAD)
    public void testRx(String message) {
        Logger.i("rxbus接受数据了" + message);
    }

    /**
     * 主页禁止侧滑
     *
     * @return
     */
    @Override
    protected boolean setSwipeEnabled() {
        return false;
    }

    @Override
    public void onBackPressed() {
        if (System.currentTimeMillis() - TOUCH_TIME < WAIT_TIME) {

            App.getInstance().exitApp();
           // mActivity.finish();
        } else {
            TOUCH_TIME = System.currentTimeMillis();
            ToastUtil.Info("再按一次退出");
        }

     /*   boolean clickResult = mClickTree.completeClickCount();//这是返回到后台运行
        if (clickResult)    {
//                https://juejin.im/post/58c407ee44d90400698757d8
            //来自上面链接
            Intent launcherIntent = new Intent(Intent.ACTION_MAIN);
            launcherIntent.addCategory(Intent.CATEGORY_HOME);
            startActivity(launcherIntent);
        } else {
            ToastUtil.Info("双击退出程序");

        }*/
    }

    /**
     * 申请权限
     */
    private void initPermission() {
        RxPermissions rxPermissions = new RxPermissions(this);
        // TODO: 2017/7/27 权限申请日志 发布需关闭
        rxPermissions.setLogging(true);
        rxPermissions.requestEach(
                Manifest.permission.CAMERA,
                Manifest.permission.READ_CALENDAR,
                Manifest.permission.READ_CONTACTS,
//                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.RECORD_AUDIO,
                Manifest.permission.CALL_PHONE,
                Manifest.permission.SEND_SMS,
                Manifest.permission.CALL_PHONE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.ACCESS_NOTIFICATION_POLICY,
                Manifest.permission.BIND_NOTIFICATION_LISTENER_SERVICE)
                .subscribe(new Consumer<Permission>() {
                    @Override
                    public void accept(@NonNull Permission permission) throws Exception {
                        String name = permission.name;
                        Logger.i("mainactivity 权限申请:" + name);
                    }
                });

    }

    @Subscribe(tag = RxBus.TAG_UPDATE, thread = EventThread.MAIN_THREAD)
    public void refreshHeader(String message) {
        if ("refreshheader".equals(message)) {
            Logger.i("刷新头像-主页-我的界面");
            mineFragment.refreshHeader();
            mineFragment.refreshBadgeViewCount();
            homeFragment.refreshBadgeViewCount();
            //刷新头像
//            String headerUrl = SharedPreferencedUtils.getStr(Constants.USER_HEADER);
//            if (TextUtils.isEmpty(headerUrl)) {
//                ImageLoadProxy.getInstance()
//                        .load(new ImageLoadConfiguration.Builder(App.getInstance())
//                                .url(R.mipmap.icon_header_test)
//                                .defaultImageResId(R.mipmap.icon_header_test)
//                                .imageView(iv_header).build());
//            } else {
//                headerUrl= Constants.IMG_SERVER_PATH+headerUrl;
//                ImageLoadProxy.getInstance()
//                        .load(new ImageLoadConfiguration.Builder(App.getInstance())
//                                .url(R.mipmap.icon_header_test)
//                                .defaultImageResId(R.mipmap.icon_header_test)
//                                .imageView(iv_header).build());
//            }
        } else if ("push".equals(message)) {
            Logger.w("receive push");
        } else if ("badgecount".equals(message)) {
            mineFragment.refreshBadgeViewCount();
            homeFragment.refreshBadgeViewCount();
        }
    }

    public class MyBroadcastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {

            homeFragment.refreshBadgeViewCount();
            mineFragment.refreshBadgeViewCount();
        }
    }
@org.greenrobot.eventbus.Subscribe
public void onEvent(MainBean bean)
{
    changeMainPage(1);
}
    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        //取消注册广播,防止内存泄漏
        if (localBroadcastManager != null) {
            if (broadcastReceiver != null) {
                localBroadcastManager.unregisterReceiver(broadcastReceiver);
            }
        }
    }

    @org.greenrobot.eventbus.Subscribe
    public void onEvent(JPSuccess bean)//推送来了
    {
        if(HOME_FALGE!=2)
        {
            customer_number.setVisibility(View.VISIBLE);
            int size = Integer.parseInt(customer_number.getText().toString().trim())+1;
            customer_number.setText(size+"");
        }
    }

    @org.greenrobot.eventbus.Subscribe
    public void onEvent1(JPDynamicSendBean bean)//动态发布的推送来了
    {
        if(HOME_FALGE!=2)
        {
            customer_number.setVisibility(View.VISIBLE);
            int size = Integer.parseInt(customer_number.getText().toString().trim())+1;
            customer_number.setText(size+"");
        }
    }
}
