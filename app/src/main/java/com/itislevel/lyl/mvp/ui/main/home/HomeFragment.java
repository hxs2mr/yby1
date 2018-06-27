package com.itislevel.lyl.mvp.ui.main.home;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.ContentFrameLayout;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.Poi;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.flyco.dialog.widget.ActionSheetDialog;
import com.itislevel.lyl.R;

import com.itislevel.lyl.adapter.recyclew.MultipleItemEntity;
import com.itislevel.lyl.app.App;
import com.itislevel.lyl.app.Constants;
import com.itislevel.lyl.base.RootFragment;
import com.itislevel.lyl.mvp.model.bean.BannBean;
import com.itislevel.lyl.mvp.model.bean.HomeBean;
import com.itislevel.lyl.mvp.model.bean.JPFetSendBean;
import com.itislevel.lyl.mvp.model.bean.JPushFete;
import com.itislevel.lyl.mvp.model.bean.ModularBean;
import com.itislevel.lyl.mvp.model.bean.PlaceBean;
import com.itislevel.lyl.mvp.model.bus.HomeAdapaterSucc;
import com.itislevel.lyl.mvp.receiver.LocationService;
import com.itislevel.lyl.mvp.ui.address.CityActivity;
import com.itislevel.lyl.mvp.ui.address.ProvinceActivity;
import com.itislevel.lyl.mvp.ui.blessing.BlessingHomeActivity;
import com.itislevel.lyl.mvp.ui.bus.Home_ViewPager;
import com.itislevel.lyl.mvp.ui.family.FamilyHomeActivity;
import com.itislevel.lyl.mvp.ui.funsharing.FunsharingHomeActivity;
import com.itislevel.lyl.mvp.ui.housekeep.HouseKeepActivity;
import com.itislevel.lyl.mvp.ui.livingexpensess.LivingExpensesActivity;
import com.itislevel.lyl.mvp.ui.location.Location_CityPickerActivity;
import com.itislevel.lyl.mvp.ui.location.Location_CityPickerContract;
import com.itislevel.lyl.mvp.ui.main.customer.CustomerOnlineActivity;
import com.itislevel.lyl.mvp.ui.main.home.HomeAdapter.MultipleRecyclerAdapter;
import com.itislevel.lyl.mvp.ui.main.home.HomeModel.HomeDataConVerter;
import com.itislevel.lyl.mvp.ui.main.home.homeright.HomRightActivity;
import com.itislevel.lyl.mvp.ui.main.home.refresh.Home_RefreshHandler;
import com.itislevel.lyl.mvp.ui.main.home.refresh.InitViewPagerAdapter;
import com.itislevel.lyl.mvp.ui.property.PropertyHomeActvity;
import com.itislevel.lyl.mvp.ui.special.SpecialHomeActivity;
import com.itislevel.lyl.mvp.ui.team.TeamHomeActivity;
import com.itislevel.lyl.mvp.ui.troublesolution.TroublesolutionActivity;
import com.itislevel.lyl.mvp.ui.user.LoginActivity;
import com.itislevel.lyl.net.RestClent;
import com.itislevel.lyl.net.callback.IFailure;
import com.itislevel.lyl.net.callback.ISuccess;
import com.itislevel.lyl.utils.ActivityUtil;
import com.itislevel.lyl.utils.ChatUtil;
import com.itislevel.lyl.utils.Dp_to_Px;
import com.itislevel.lyl.utils.GsonUtil;
import com.itislevel.lyl.utils.SAToast;
import com.itislevel.lyl.utils.SharedPreferencedUtils;
import com.itislevel.lyl.utils.ToastUtil;
import com.itislevel.lyl.utils.update.DownloadProgress;
import com.itislevel.lyl.utils.update.RxUpdateUtil;
import com.itislevel.lyl.widget.videorecord.TCVideoRecordActivity;
import com.itislevel.lyl.widget.videorecord.TCVideoSettingActivity;
import com.orhanobut.logger.Logger;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.tencent.rtmp.TXLiveConstants;
import com.tencent.ugc.TXRecordCommon;
import com.vondear.rxtools.RxDeviceTool;
import com.vondear.rxtools.RxImageTool;
import com.vondear.rxtools.activity.AndroidBug5497Workaround;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.OnClick;
import cn.finalteam.rxgalleryfinal.imageloader.GlideImageLoader;
import cn.finalteam.rxgalleryfinal.imageloader.GlideImageLoader1;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.rong.imlib.model.Conversation;
import q.rorbin.badgeview.Badge;
import q.rorbin.badgeview.QBadgeView;
import qiu.niorgai.StatusBarCompat;
import retrofit2.http.PUT;

import static com.itislevel.lyl.base.NoMVPActivity.ISLIUHAN;
import static com.itislevel.lyl.base.NoMVPActivity.ISLIUHANNUMBER;
import static com.itislevel.lyl.utils.Dp_to_Px.dip2px;
import static com.itislevel.lyl.utils.Dp_to_Px.px2dip;
import static com.itislevel.lyl.widget.videorecord.TCAudioControl.TAG;
import static com.itislevel.lyl.widget.videorecord.TCVideoSettingActivity
        .RECORD_CONFIG_MIN_DURATION;
import static com.tencent.bugly.beta.tinker.TinkerManager.getApplication;

/**
 * 首页-主页
 */
public class HomeFragment extends RootFragment<HomePresenter> implements HomeContract.View
        , View.OnClickListener,SwipeRefreshLayout.OnRefreshListener {
    @BindView(R.id.home_recycleview)
    RecyclerView home_recycleview = null;
    @BindView(R.id.home_refresh)
    SwipeRefreshLayout home_refresh = null;

    @BindView(R.id.home_search)
    AppCompatImageView home_search = null;

    @BindView(R.id.home_qr)
    AppCompatImageView home_qr = null;

    @BindView(R.id.home_liao)
    AppCompatImageView home_liao = null;

    @BindView(R.id.home_location_linear)
    LinearLayoutCompat home_location_linear = null;

    @BindView(R.id.home_location_tv)
    AppCompatTextView home_location_tv = null;

    @BindView(R.id.home_location_iv)
    AppCompatImageView home_location_iv = null;

    @BindView(R.id.home_right)
    ContentFrameLayout     home_right;

    @BindView(R.id.home_right_number)
    AppCompatTextView home_right_number;

    @BindView(R.id.home_tollbar)
    Toolbar  home_tollbar;

    List<MultipleItemEntity> data = null;
    //header头部分
    public static Banner banner = null;
    public static ViewPager home_vewpage = null;
    private View head_view = null;
    public static Home_RefreshHandler mRefreshHandler = null;
    private List<View> view_list = null;
    //包裹点点的LinearLayout
    public static ViewGroup group = null;
    private ImageView imageView;
    //定义一个ImageVIew数组，来存放生成的小园点
    private ImageView[] imageViews;

    //header中你的viewpager部分
    private int Layouts[] = {R.layout.home_header_one, R.layout.home_head_two};

    //viewpager中你的xml部分中的控件
    public static LinearLayoutCompat home_one_start_linear1 = null;
    public static LinearLayoutCompat home_one_start_linear2 = null;

    private LinearLayoutCompat head_one_linear_1 = null;
    public static AppCompatImageView head_one_iv_1 = null;
    public static AppCompatTextView head_one_tv_1 = null;

    private LinearLayoutCompat head_one_linear_2 = null;
    public static AppCompatImageView head_one_iv_2 = null;
    public static AppCompatTextView head_one_tv_2 = null;

    private LinearLayoutCompat head_one_linear_3 = null;
    public static AppCompatImageView head_one_iv_3 = null;
    public static AppCompatTextView head_one_tv_3 = null;

    private LinearLayoutCompat head_one_linear_4 = null;
    public static AppCompatImageView head_one_iv_4 = null;
    public static AppCompatTextView head_one_tv_4 = null;

    public static LinearLayoutCompat head_one_linear_5 = null;
    public static AppCompatImageView head_one_iv_5 = null;
    public static AppCompatTextView head_one_tv_5 = null;

    private LinearLayoutCompat head_one_linear_6 = null;
    public static AppCompatImageView head_one_iv_6 = null;
    public static AppCompatTextView head_one_tv_6 = null;

    private LinearLayoutCompat head_one_linear_7 = null;
    public static AppCompatImageView head_one_iv_7 = null;
    public static AppCompatTextView head_one_tv_7 = null;

    private LinearLayoutCompat head_one_linear_8 = null;
    public static AppCompatImageView head_one_iv_8 = null;
    public static AppCompatTextView head_one_tv_8 = null;


    public static AppCompatTextView customer_number1 = null;
    public static AppCompatTextView customer_number2 = null;
    public static AppCompatTextView customer_number3 = null;
    public static AppCompatTextView customer_number4 = null;
    public static AppCompatTextView customer_number5 = null;
    private RxPermissions rxPermissions;
    Badge badgeShare;
    Badge badgeFete;
    Badge badgeBless;
    private int anim;
    private static final String KEY = "current_theme";
    private int theme;
    public static String IP = "";
    public static String CITY_ID = "0";
    public static String CITY_NAME = "";
    public static String P_ID = "0";
    public static String P_NAME = "";
    public static Context context;
    /*  @SuppressLint("HandlerLeak")
       Handler handler = new Handler(){
          @Override
          public void handleMessage(Message msg) {
              super.handleMessage(msg);
              home_location_tv.setText(CITY_NAME);
          }
      };*/
    public static List<HomeBean.BankuaiBean> HOME_MODULE;
    public static List<HomeBean.GuanggaoBean> HOME_BANN;
    public static String HEAD_IMAGE_URL;
    public static String SHOP_TYPE = "1001";
    private AppCompatTextView home_head_zx = null;//在线人数的点击
    private LinearLayoutCompat home_head_zc_linear = null;//顾问注册通道
    public static int MESSAGE_NUMBER = 0;
    public  static  String customer_service_phone="";
    private MultipleRecyclerAdapter patient_mAdapter;

    private List<MultipleItemEntity> home_data_null = null;
    private List<MultipleItemEntity> home_data = null;

    private LocationService locationService;
    @Override
    public int getLayoutId() {
        return R.layout.fragment_home_new;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        rxPermissions = new RxPermissions(getActivity());
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            theme = savedInstanceState.getInt(KEY);
        }

        // -----------location config ------------
        locationService =  new LocationService(getContext());
        //获取locationservice实例，建议应用中只初始化1个location实例，然后使用，可以参考其他示例的activity，都是通过此种方式获取locationservice实例的
        locationService.registerListener(mListener);
        locationService.setLocationOption(locationService.getDefaultLocationClientOption());
    }
    @Override
    public void onStop() {
        super.onStop();
        // TODO Auto-generated method stub
        locationService.unregisterListener(mListener); //注销掉监听
        locationService.stop(); //停止定位服务
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void initEventAndData() {
        home_data = new ArrayList<>();
        init_home_location();//百度定位
        if(ISLIUHAN)//刘海屏适配
        {
            home_tollbar.setPadding(0, ISLIUHANNUMBER-50,0,0);
        }
        HOME_MODULE = new ArrayList<>();
        HOME_BANN = new ArrayList<>();
        EventBus.getDefault().register(this);
        context = getContext();
        head_view = View.inflate(getContext(), R.layout.home_header, null);
        initRefreshLayout_recyclview();
        //mRefreshHandler = Home_RefreshHandler.create(home_refresh, home_recycleview, new HomeDataConVerter());//加载解析的数据
        view_list = new ArrayList<>();
        for (int i = 0; i < Layouts.length; i++) {//添加功能节目
            View v = getLayoutInflater().inflate(Layouts[i], null);
            view_list.add(v);
        }
        // initVie   wListener();
        init_head();
        init_viewpager_id();
        getNetIp();//获取外网ip
        InitViewPagerAdapter adapter =   new InitViewPagerAdapter(group,view_list,imageViews,getContext(),imageView);//初始化viewpager下标
        home_vewpage.setAdapter(new MyAdapter());
        home_vewpage.addOnPageChangeListener(new InitViewPagerAdapter.GuidePageChangeListener());
    }

    private void init_home_location() {
        locationService.start();// 定位SDK

    }

    @Override
    public void onResume() {
        super.onResume();
        //refreshbadge();
    }

    private void initRefreshLayout_recyclview() {//设置刷新的颜色和款式及recyclevew
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        home_recycleview.setLayoutManager(manager);
        //注册成为订阅者
        home_refresh.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_orange_dark,
                android.R.color.holo_red_dark);
        home_refresh.setProgressViewOffset(true, 50, 300);
        home_refresh.setOnRefreshListener(this);

        home_data_null = new ArrayList<>();
        if(patient_mAdapter==null)
        {
            patient_mAdapter= MultipleRecyclerAdapter.create(getActivity(),getContext(),home_data_null);
            patient_mAdapter.openLoadAnimation();
            patient_mAdapter.isFirstOnly(true);
            patient_mAdapter.addHeaderView(head_view);
            View emptyView = View.inflate(getContext(), R.layout.partial_empty_view, null);
            patient_mAdapter.setEmptyView(emptyView);
            home_recycleview.setAdapter(patient_mAdapter);
        }
    }

    private void init_head() {
        home_qr.setOnClickListener(this);
        home_search.setOnClickListener(this);
        home_location_linear.setOnClickListener(this);
        home_right.setOnClickListener(this);

        banner = head_view.findViewById(R.id.home_banner);//头部的banner图
        home_vewpage = head_view.findViewById(R.id.home_viewpager);
        group = head_view.findViewById(R.id.viewGroup);
        home_head_zx = head_view.findViewById(R.id.home_head_zx);
        home_head_zc_linear = head_view.findViewById(R.id.home_head_zc_linear);

        SpannableStringBuilder style = new SpannableStringBuilder(home_head_zx.getText().toString());

        style.setSpan(new ForegroundColorSpan(Color.parseColor("#ff7a00")), 6, home_head_zx.getText().toString().length() - 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        home_head_zx.setText(style);
        home_head_zx.setOnClickListener(this);
        home_head_zc_linear.setOnClickListener(this);
    }

    private void refreshbadge() {
        //乐趣
        int shareInt = SharedPreferencedUtils.getInt(Constants.BADGE_COUNT_SHARE, 0);
        if (shareInt > 0) {
            badgeShare.setBadgeText(shareInt + "");
        } else {
            badgeShare.hide(false);
        }

        //喜事
        int blessInt = SharedPreferencedUtils.getInt(Constants.BADGE_COUNT_BLESS, 0);
        if (blessInt > 0) {
            badgeBless.setBadgeText(blessInt + "");
        } else {
            badgeBless.hide(false);
        }

        //祭祀
        int feteInt = SharedPreferencedUtils.getInt(Constants.BADGE_COUNT_FEGE, 0);
        if (feteInt > 0) {
            badgeFete.setBadgeText(feteInt + "");
        } else {
            badgeFete.hide(false);
        }
    }

    @Override
    public void onRefresh() {
        if(home_location_tv.getText().toString().contains("失败"))
        {
            getNetIp();
        }else {
            Map<String, String> request = new HashMap<>();
            request.put("token", SharedPreferencedUtils.getStr(Constants.USER_TOKEN));
            request.put("usernum", SharedPreferencedUtils.getStr(Constants.USER_NUM));
            request.put("cityid", CITY_ID);
            mPresenter.firstPage(GsonUtil.obj2JSON(request));
        }
    }
    class MyAdapter extends PagerAdapter {//viewpager添加xml布局

        @Override
        public int getCount() {
            return Layouts.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View v = view_list.get(position);
            if (head_one_linear_1 == null) {
                init_onclick_viewpager(view_list, position);
            }
            container.addView(v);
            return v;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            View v = view_list.get(position);
            container.removeView(v);
        }
    }

    private void init_onclick_viewpager(List<View> view_list, int position) {  //ViewPager中的控件的监听事件，当ViewPager的page页发生变化的时候调用
        switch (position) {
            case 0:
                break;
            case 1:
                //后续在开发
                break;
        }
    }


    @Override
    public void initInject() {
        getFragmentComponent().inject(this);
    }


    @Override
    public void showContent(String msg) {
        ToastUtil.Success(msg);
    }

    @Override
    public void firstPage(HomeBean message) {
        customer_service_phone= message.getCustomer_service_phone();//客户电话
        HOME_MODULE = message.getBankuai();
        HOME_BANN = message.getGuanggao();
        ArrayList<String> image_arr = new ArrayList<>();
        int size = HOME_BANN.size();
        for(int i  =0 ; i <size ; i++)
        {
            image_arr.add(message.getGetImgUrl()+HOME_BANN.get(i).getLogo());
        }
        home_data = new HomeDataConVerter().NEW_CONVERT(message);//解析的时候
        patient_mAdapter.setNewData(home_data);
        init_start_iv_tv(message.getGetImgUrl());
        showBanner(image_arr);
        home_refresh.setRefreshing(false);
    }
    @Override
    public void plcae(PlaceBean message) {//获取位置信息
        System.out.println("位置信息*************************************" + message);
        if(!message.getData().getCity_id().contains("x")||!message.getData().getCity_id().contains("X"))
        {
            CITY_ID = message.getData().getCity_id();
        }
        if(!message.getData().getCity().equals("x")||!message.getData().getCity().equals("X"))
        {
            CITY_NAME = message.getData().getCity();
        }
        P_NAME = message.getData().getRegion();
        P_ID = message.getData().getRegion_id();
        home_location_tv.setText("" + CITY_NAME);

        Map<String, String> request = new HashMap<>();
        request.put("token", SharedPreferencedUtils.getStr(Constants.USER_TOKEN));
        request.put("usernum", SharedPreferencedUtils.getStr(Constants.USER_NUM));
        request.put("cityid", CITY_ID);
        mPresenter.firstPage(GsonUtil.obj2JSON(request));
    }

    @Override
    public void useNightMode(boolean isNight) {

    }

    @Override
    public void stateEmpty() {

    }

    @Override
    public void stateLoading() {

    }

    @Override
    public void stateError() {

    }

    @Override
    public void stateError(Throwable e) {
        if (patient_mAdapter.getData() == null || patient_mAdapter.getData().size() <= 0) {
            View emptyView = View.inflate(getContext(), R.layout.partial_empty_view, null);
            patient_mAdapter.setEmptyView(emptyView);
        }
        home_refresh.setRefreshing(false);
        if(!CITY_NAME.equals("")&&CITY_NAME.contains("定位"))
        {
            home_location_tv.setText("定位失败");
        }
        ToastUtil.Info(e.getMessage());
    }

    @Override
    public void stateSuccess() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.head_one_linear_4://乐趣分享
                customer_number4.setVisibility(View.GONE);
                customer_number4.setText("0");
                home_to_next(head_one_tv_4.getText().toString());
                break;
            case R.id.head_one_linear_1://烦恼解答
                customer_number1.setVisibility(View.GONE);
                customer_number1.setText("0");
                home_to_next(head_one_tv_1.getText().toString());
                break;
            case R.id.head_one_linear_6://生活缴费
                home_to_next(head_one_tv_6.getText().toString());
                break;
            case R.id.head_one_linear_3://家政服务
                customer_number3.setVisibility(View.GONE);
                customer_number3.setText("0");
                home_to_next(head_one_tv_3.getText().toString());
                break;
            case R.id.head_one_linear_2://顾问团队
                customer_number2.setVisibility(View.GONE);
                customer_number2.setText("0");
                home_to_next(head_one_tv_2.getText().toString());
                break;
            case R.id.head_one_linear_8://礼品特产\
                home_to_next(head_one_tv_8.getText().toString());
                break;
            case R.id.head_one_linear_5://亲情祭祀
                customer_number5.setVisibility(View.GONE);
                customer_number5.setText("0");
                home_to_next(head_one_tv_5.getText().toString());
                break;
            case R.id.head_one_linear_7://喜事祝福
                home_to_next(head_one_tv_7.getText().toString());
                break;
            case R.id.home_qr://二维码扫描
                need_permissions();
                SAToast.makeText(getContext(),"敬请期待!").show();
                break;
            case R.id.home_location_linear://手动选择城市和再定位
                Bundle bundle = new Bundle();
                String title = "";
                bundle.putString(Constants.START_FLAGE, "home");
                bundle.putString(Constants.CITY_NAME, CITY_NAME);
                bundle.putString(Constants.CITY_ID, CITY_ID);
                ActivityUtil.getInstance().openActivity(getActivity(), Location_CityPickerActivity.class,bundle);

               // init_select_location();//选择城市 进行定位暂时不用
                break;
            case R.id.home_search://客户
                show_call();
          //      login_kehu();
                break;
            case R.id.home_head_zx:
                SAToast.makeText(getContext(),"在线的人数点击事件").show();
                break;
            case R.id.home_head_zc_linear:
                SAToast.makeText(getContext(),"顾问注册的点击事件").show();
                break;
            case R.id.home_right://右上角的图标
                ActivityUtil.getInstance().openActivity(getActivity(),HomRightActivity.class);
                break;
        }

    }
    public void show_call(){
        //实例化建造者
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        //设置警告对话框的标题
        //设置警告显示的图片
//        builder.setIcon(android.R.drawable.ic_dialog_alert);
        //设置警告对话框的提示信息
        builder.setMessage("拨打"+customer_service_phone);
        //设置”正面”按钮，及点击事件
        builder.setPositiveButton("呼叫", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                Uri data = Uri.parse("tel:" +customer_service_phone);
                intent.setData(data);
                startActivity(intent);
            }
        });
        //设置“中立”按钮，及点击事件
        builder.setNeutralButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        //显示对话框
        builder.show();
    }
    private void login_kehu() {
        boolean islogin = SharedPreferencedUtils.getBool("islogin", false);
        if (!islogin) {
            ActivityUtil.getInstance().openActivity(getActivity(), LoginActivity.class);
            return;
        }
        int anInt = SharedPreferencedUtils.getInt(Constants.USER_IS_CUSTOMER, 0);

        if (anInt == 0) {//用户
            ChatUtil.startConversation(getActivity(), Conversation.ConversationType
                    .PRIVATE, "12", "");

        } else {//客服
            Map<String, Boolean> supportedConversation = new HashMap<>();
            supportedConversation.put(Conversation.ConversationType.PRIVATE.getName(),
                    false);
            ChatUtil.startConversationList(getActivity(), supportedConversation);
        }
               ActivityUtil.getInstance().openActivity(getActivity(),CustomerOnlineActivity.class);
    }


    private  void  home_to_next(String s)
    {    Bundle bundle = new Bundle();
        String title = "";

        if(CITY_ID.equals("0")){
            SAToast.makeText(getContext(),"请先选择所属地区").show();
            Bundle bundle1 = new Bundle();
            bundle1.putString(Constants.START_FLAGE, "home");
            bundle1.putString(Constants.CITY_NAME, "定位失败");
            bundle1.putString(Constants.CITY_ID, "0");

            ActivityUtil.getInstance().openActivity(getActivity(), Location_CityPickerActivity.class,bundle1);
        }else {
            if(s.contains("乐趣"))
            {
                SharedPreferencedUtils.setInt(Constants.BADGE_COUNT_SHARE, 0);

                ActivityUtil.getInstance().openActivity(getActivity(), FunsharingHomeActivity.class);
            }else  if(s.contains("家")){
                title = "家政服务";
                bundle.putString(Constants.PROVINCE_TITLE, title);
                bundle.putString(Constants.CITY_TITLE, title);
                bundle.putString(Constants.COUNTY_TITLE, title);
                bundle.putBoolean(Constants.IS_SHOW_COUNTY, true);

                bundle.putString(Constants.PROVINCE_ID, P_ID);
                bundle.putString(Constants.PROVINCE_NAME, P_NAME);
                bundle.putString(Constants.CITY_ID, CITY_ID);
                bundle.putString(Constants.CITY_NAME, CITY_NAME);

                bundle.putInt(Constants.ACTIVITY_TARGET, Constants.ACTIVITY_HOUSE);
                ActivityUtil.getInstance().openActivity(getActivity(), HouseKeepActivity.class,
                        bundle);

            }else  if(s.contains("顾问")){
            /*    title = "顾问团队";
                bundle.putString(Constants.PROVINCE_TITLE, title);
                bundle.putString(Constants.CITY_TITLE, title);
                bundle.putString(Constants.COUNTY_TITLE, title);
                bundle.putBoolean(Constants.IS_SHOW_COUNTY, false);
                bundle.putString(Constants.PROVINCE_ID, P_ID);
                bundle.putString(Constants.PROVINCE_NAME, P_NAME);
                bundle.putString(Constants.CITY_ID, CITY_ID);
                bundle.putString(Constants.CITY_NAME, CITY_NAME);

                bundle.putInt(Constants.ACTIVITY_TARGET, Constants.ACTIVITY_TEAM);
                ActivityUtil.getInstance().openActivity(getActivity(), TeamHomeActivity.class,
                        bundle);*/

                SAToast.makeText(getContext(),"敬请期待!").show();
            }else  if(s.contains("烦恼")){
                ActivityUtil.getInstance().openActivity(getActivity(), TroublesolutionActivity.class);
            }else  if(s.contains("亲情")){
                title = "亲情祭祀";
                SHOP_TYPE = "1000";
                SharedPreferencedUtils.setInt(Constants.BADGE_COUNT_FEGE, 0);

                bundle.putString(Constants.PROVINCE_TITLE, title);
                bundle.putString(Constants.CITY_TITLE, title);
                bundle.putString(Constants.COUNTY_TITLE, title);
                bundle.putBoolean(Constants.IS_SHOW_COUNTY, false);
                bundle.putString(Constants.PROVINCE_ID, P_ID);
                bundle.putString(Constants.PROVINCE_NAME, P_NAME);
                bundle.putString(Constants.CITY_ID, CITY_ID);
                bundle.putString(Constants.CITY_NAME, CITY_NAME);

                bundle.putInt(Constants.ACTIVITY_TARGET, Constants.ACTIVITY_FAMILY);
                ActivityUtil.getInstance().openActivity(getActivity(), FamilyHomeActivity.class,
                        bundle);
            }else  if(s.contains("物")){   // LivingExpensesActivity
                Bundle bundle1 = new Bundle();
                bundle1.putString(Constants.CITY_ID, CITY_ID);
                bundle1.putString(Constants.CITY_NAME, CITY_NAME);
                bundle1.putString("phone", customer_service_phone);
                ActivityUtil.getInstance().openActivity(getActivity(), PropertyHomeActvity.class,bundle1);
            }else  if(s.contains("喜事")){
                SHOP_TYPE ="1001";
                SharedPreferencedUtils.setInt(Constants.BADGE_COUNT_BLESS, 0);
                if(CITY_ID.equals("0"))
                {
                    CITY_ID = "520100";
                    CITY_NAME ="贵阳";
                }
                bundle.putString(Constants.CITY_ID, CITY_ID);
                bundle.putString(Constants.CITY_NAME, CITY_NAME);
                bundle.putString(Constants.PROVINCE_ID, P_ID);
                bundle.putString(Constants.PROVINCE_NAME, P_NAME);
                title = "喜事祝福";
                bundle.putString(Constants.PROVINCE_TITLE, title);
                bundle.putString(Constants.CITY_TITLE, title);
                bundle.putString(Constants.COUNTY_TITLE, title);
                bundle.putBoolean(Constants.IS_SHOW_COUNTY, false);
                bundle.putInt(Constants.ACTIVITY_TARGET, Constants.ACTIVITY_WEDDING);
                ActivityUtil.getInstance().openActivity(getActivity(), BlessingHomeActivity.class,
                        bundle);
            }else  if(s.contains("特产")){
          /*      SHOP_TYPE ="1002";
                ActivityUtil.getInstance().openActivity(getActivity(), SpecialHomeActivity
                        .class);*/
                SAToast.makeText(getContext(),"敬请期待!").show();
            }
        }

    }

    private void updateAPK() {
        String apkUrl = "http://192.168.1.105:8080/itisitest/app.apk";
        String apkVersion = "1.0.0";
        if (RxUpdateUtil.isApkDownloaded(apkVersion)) {
            Logger.i("不下载,取缓存");
            return;
        }

        // 下载新版 apk 文件
        RxUpdateUtil.downloadApkFile(apkUrl, apkVersion)
                .subscribe(new Observer<File>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull File file) {
                        if (file != null) {
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
                        Logger.i("下载进度:" + downloadProgress.getTotal() + "==" +
                                downloadProgress.getProgress());
                    }
                });
    }

    public void downloadApkFile() {
//        NormalDialog normalDialog = new NormalDialog(mActivity);
//        normalDialog.show();

        RxDeviceTool.getScreenWidth(getActivity());

        ActionSheetDialog sheetDialog = new ActionSheetDialog(mActivity, new String[]{"相册",
                "相机"}, mRootView);
        sheetDialog.setTitle("修改头像");
        sheetDialog.show();
        String[] perms = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
        // 如果新版 apk 文件已经下载过了，直接 return，此时不需要开发者调用安装 apk 文件的方法，在 isApkFileDownloaded 里已经调用了安装」
        // TODO: 2017/7/28  测试进度  临时注释
//        if (RxUpdateUtil.isApkDownloaded(mNewVersion)) {
//            Logger.i("不下载,取缓存");
//            return;
//        }
        // 下载新版 apk 文件
//        RxUpdateUtil.downloadApkFile(mApkUrl, mNewVersion)
//                .subscribe(new Observer<File>() {
//                    @Override
//                    public void onSubscribe(@NonNull Disposable d) {
//
//                    }
//
//                    @Override
//                    public void onNext(@NonNull File file) {
//                        if (file != null) {
//                            RxUpdateUtil.installApp(file);
//                        }
//                    }
//
//                    @Override
//                    public void onError(@NonNull Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//                });
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }
    public void refreshBadgeViewCount() {
       // refreshbadge();
    }
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY, theme);
    }
    public  String getNetIp() {//获取ip地址
     home_refresh.setRefreshing(true);
        String ip= "";
                RestClent.builder()
                        .url("http://apis.map.qq.com/ws/location/v1/ip?key=QPSBZ-3MXKO-IHFWZ-SUUWS-AHQXJ-HFBIC")//使用腾讯的位置ip定位
                        .success(new ISuccess() {
                            @Override
                            public void onSuccess(String response) {
                                System.out.println("城市位置为=========******************"+response);
                                if(response!=null&&!response.equals(""))
                                {
                                    if(JSON.parseObject(response).getInteger("status")==0)
                                    {
                                        IP =JSON.parseObject(response).getJSONObject("result").getString("ip");
                                        if(!IP.equals(""))
                                        {
                                            Map<String, String> request = new HashMap<>();
                                            request.put("token", SharedPreferencedUtils.getStr(Constants.USER_TOKEN));
                                            request.put("usernum", SharedPreferencedUtils.getStr(Constants.USER_NUM));
                                            request.put("ip", IP);
                                            mPresenter.plcae(GsonUtil.obj2JSON(request));
                                        }else{
                                            home_location_tv.setText("定位失败");
                                            Map<String, String> request = new HashMap<>();
                                            request.put("token", SharedPreferencedUtils.getStr(Constants.USER_TOKEN));
                                            request.put("usernum", SharedPreferencedUtils.getStr(Constants.USER_NUM));
                                            request.put("cityid", CITY_ID);
                                            mPresenter.firstPage(GsonUtil.obj2JSON(request));
                                        }
                                    }
                                }else {
                                    home_location_tv.setText("定位失败");
                                    Map<String, String> request = new HashMap<>();
                                    request.put("token", SharedPreferencedUtils.getStr(Constants.USER_TOKEN));
                                    request.put("usernum", SharedPreferencedUtils.getStr(Constants.USER_NUM));
                                    request.put("cityid", CITY_ID);
                                    mPresenter.firstPage(GsonUtil.obj2JSON(request));
                                }
                            }
                        })
                        .failure(new IFailure() {
                            @Override
                            public void onIFailure() {
                                home_location_tv.setText("定位失败");
                                Map<String, String> request = new HashMap<>();
                                request.put("token", SharedPreferencedUtils.getStr(Constants.USER_TOKEN));
                                request.put("usernum", SharedPreferencedUtils.getStr(Constants.USER_NUM));
                                request.put("cityid", CITY_ID);
                                mPresenter.firstPage(GsonUtil.obj2JSON(request));
                            }
                        })
                        .build()
                        .get();
        return ip;
    }

    @Subscribe
    public void Event(HomeAdapaterSucc messageEvent)
    {
        String city_id = messageEvent.getId();
        String city_name = messageEvent.getName();
        CITY_NAME=city_name;
        CITY_ID = city_id;
        P_ID= messageEvent.getP_id();
        P_NAME= messageEvent.getP_name();
        home_location_tv.setText(city_name);
   //     mRefreshHandler.loation_refresh(city_id);//第一次加载
       // SAToast.makeText(getContext(),"事件来了11111111111").show();

        Map<String, String> request = new HashMap<>();
        request.put("token", SharedPreferencedUtils.getStr(Constants.USER_TOKEN));
        request.put("usernum", SharedPreferencedUtils.getStr(Constants.USER_NUM));
        request.put("cityid", CITY_ID);
        mPresenter.firstPage(GsonUtil.obj2JSON(request));
    }

    @Subscribe
    public void Event_ViewPage(Home_ViewPager messageEvent)
    {
    }
    @SuppressLint("WrongViewCast")
    private void init_viewpager_id(){
        home_one_start_linear1 = view_list.get(0).findViewById(R.id.home_one_start_linear1);
        home_one_start_linear2 = view_list.get(0).findViewById(R.id.home_one_start_linear2);
        head_one_linear_1 = view_list.get(0).findViewById(R.id.head_one_linear_1);
        head_one_iv_1 = view_list.get(0).findViewById(R.id.head_one_iv_1);
        head_one_tv_1 = view_list.get(0).findViewById(R.id.head_one_tv_1);

        head_one_linear_2 = view_list.get(0).findViewById(R.id.head_one_linear_2);
        head_one_iv_2 = view_list.get(0).findViewById(R.id.head_one_iv_2);
        head_one_tv_2 = view_list.get(0).findViewById(R.id.head_one_tv_2);

        head_one_linear_3 = view_list.get(0).findViewById(R.id.head_one_linear_3);
        head_one_iv_3 = view_list.get(0).findViewById(R.id.head_one_iv_3);
        head_one_tv_3 = view_list.get(0).findViewById(R.id.head_one_tv_3);

        head_one_linear_4 = view_list.get(0).findViewById(R.id.head_one_linear_4);
        head_one_iv_4 = view_list.get(0).findViewById(R.id.head_one_iv_4);
        head_one_tv_4 = view_list.get(0).findViewById(R.id.head_one_tv_4);

        head_one_linear_5 = view_list.get(0).findViewById(R.id.head_one_linear_5);
        head_one_iv_5 = view_list.get(0).findViewById(R.id.head_one_iv_5);
        head_one_tv_5 = view_list.get(0).findViewById(R.id.head_one_tv_5);

        head_one_linear_6 = view_list.get(0).findViewById(R.id.head_one_linear_6);
        head_one_iv_6 = view_list.get(0).findViewById(R.id.head_one_iv_6);
        head_one_tv_6 = view_list.get(0).findViewById(R.id.head_one_tv_6);

        head_one_linear_7 = view_list.get(0).findViewById(R.id.head_one_linear_7);
        head_one_iv_7 = view_list.get(0).findViewById(R.id.head_one_iv_7);
        head_one_tv_7 = view_list.get(0).findViewById(R.id.head_one_tv_7);

        head_one_linear_8 = view_list.get(0).findViewById(R.id.head_one_linear_8);
        head_one_iv_8 = view_list.get(0).findViewById(R.id.head_one_iv_8);
        head_one_tv_8 = view_list.get(0).findViewById(R.id.head_one_tv_8);

        customer_number1= view_list.get(0).findViewById(R.id.customer_number1);
        customer_number2= view_list.get(0).findViewById(R.id.customer_number2);
        customer_number3= view_list.get(0).findViewById(R.id.customer_number3);
        customer_number4= view_list.get(0).findViewById(R.id.customer_number4);
        customer_number5= view_list.get(0).findViewById(R.id.customer_number5);
        head_one_linear_1.setOnClickListener(this);
        head_one_linear_2.setOnClickListener(this);
        head_one_linear_3.setOnClickListener(this);
        head_one_linear_4.setOnClickListener(this);
        head_one_linear_5.setOnClickListener(this);
        head_one_linear_6.setOnClickListener(this);
        head_one_linear_7.setOnClickListener(this);
        head_one_linear_8.setOnClickListener(this);
    }

  @Subscribe
  public  void onevent(JPushFete fete)
  {
        if(head_one_tv_1.getText().toString().contains("祭"))
        {
            int size =  Integer.parseInt(customer_number1.getText().toString().trim())+1;
            customer_number1.setVisibility(View.VISIBLE);
            customer_number1.setText(size+"");
        }else if(head_one_tv_2.getText().toString().contains("祭")){
            int size =  Integer.parseInt(customer_number2.getText().toString().trim())+1;
            customer_number2.setVisibility(View.VISIBLE);
            customer_number2.setText(size+"");
        }else if(head_one_tv_3.getText().toString().contains("祭")){
            int size =  Integer.parseInt(customer_number3.getText().toString().trim())+1;
            customer_number3.setVisibility(View.VISIBLE);
            customer_number3.setText(size+"");
        }else if(head_one_tv_4.getText().toString().contains("祭")){
            int size =  Integer.parseInt(customer_number4.getText().toString().trim())+1;
            customer_number4.setVisibility(View.VISIBLE);
            customer_number4.setText(size+"");
        }else if(head_one_tv_5.getText().toString().contains("祭")){
            int size =  Integer.parseInt(customer_number5.getText().toString().trim())+1;
            customer_number5.setVisibility(View.VISIBLE);
            customer_number5.setText(size+"");
        }
    }
    @Subscribe
    public  void onevent(JPFetSendBean fete)
    {
        if(head_one_tv_1.getText().toString().contains("祭"))
        {
            int size =  Integer.parseInt(customer_number1.getText().toString().trim())+1;
            customer_number1.setVisibility(View.VISIBLE);
            customer_number1.setText(size+"");
        }else if(head_one_tv_2.getText().toString().contains("祭")){
            int size =  Integer.parseInt(customer_number2.getText().toString().trim())+1;
            customer_number2.setVisibility(View.VISIBLE);
            customer_number2.setText(size+"");
        }else if(head_one_tv_3.getText().toString().contains("祭")){
            int size =  Integer.parseInt(customer_number3.getText().toString().trim())+1;
            customer_number3.setVisibility(View.VISIBLE);
            customer_number3.setText(size+"");
        }else if(head_one_tv_4.getText().toString().contains("祭")){
            int size =  Integer.parseInt(customer_number4.getText().toString().trim())+1;
            customer_number4.setVisibility(View.VISIBLE);
            customer_number4.setText(size+"");
        }else if(head_one_tv_5.getText().toString().contains("祭")){
            int size =  Integer.parseInt(customer_number5.getText().toString().trim())+1;
            customer_number5.setVisibility(View.VISIBLE);
            customer_number5.setText(size+"");
        }
    }
    private void need_permissions() {//需要的权限
        rxPermissions
                .requestEach(Manifest.permission.CAMERA,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE)
                .subscribe(permission -> {
                    if (permission.granted) {
                        //权限获取成功
                    } else {
                        ToastUtil.Error("请打开相机及存储权限！");
                    }
                });
    }

    private void showBanner( ArrayList<String> bannerImages)
    {
        banner.setImageLoader(new GlideImageLoader1());
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);//设置banner样式
        banner.setImages(bannerImages);//设置图片集合
        banner.setBannerAnimation(Transformer.Accordion);//设置banner动画效果
        banner.isAutoPlay(true);//设置自动轮播，默认为true
        banner.setDelayTime(3000);//设置轮播时间
        banner.setIndicatorGravity(BannerConfig.RIGHT); //设置指示器位置（当banner模式中有指示器时）
        banner.start();//banner设置方法全部调用完毕时最后调用
    }

    private void init_start_iv_tv(String HEAD_IMAGE_URL) {
        int size =  HOME_MODULE.size();
        if(size<8)
        {
            group.setVisibility(View.GONE);
        }
        if (size<=5)
        {
            LinearLayoutCompat.LayoutParams linearParams = (LinearLayoutCompat.LayoutParams) home_vewpage.getLayoutParams();
            linearParams.height = RxImageTool.dip2px(87);
            home_vewpage.setLayoutParams(linearParams);
        }else {
            LinearLayoutCompat.LayoutParams linearParams = (LinearLayoutCompat.LayoutParams) home_vewpage.getLayoutParams();
            linearParams.height = RxImageTool.dip2px(174);
            home_vewpage.setLayoutParams(linearParams);
        }
        if(size!=0)
        {
            if(size ==8||size>8)
            {
                init_load_Image(HEAD_IMAGE_URL+HOME_MODULE.get(0).getIcon(),head_one_iv_1);
                head_one_tv_1.setText(HOME_MODULE.get(0).getCatename());

                init_load_Image(HEAD_IMAGE_URL+HOME_MODULE.get(1).getIcon(),head_one_iv_2);
                head_one_tv_2.setText(HOME_MODULE.get(1).getCatename());

                init_load_Image(HEAD_IMAGE_URL+HOME_MODULE.get(2).getIcon(),head_one_iv_3);
                head_one_tv_3.setText(HOME_MODULE.get(2).getCatename());
                head_one_tv_4.setText(HOME_MODULE.get(3).getCatename());
                head_one_tv_5.setText(HOME_MODULE.get(4).getCatename());
                head_one_tv_6.setText(HOME_MODULE.get(5).getCatename());
                head_one_tv_7.setText(HOME_MODULE.get(6).getCatename());
                head_one_tv_8.setText(HOME_MODULE.get(7).getCatename());
                init_load_Image(HEAD_IMAGE_URL+HOME_MODULE.get(3).getIcon(),head_one_iv_4);

                init_load_Image(HEAD_IMAGE_URL+HOME_MODULE.get(4).getIcon(),head_one_iv_5);
                init_load_Image(HEAD_IMAGE_URL+HOME_MODULE.get(5).getIcon(),head_one_iv_6);
                init_load_Image(HEAD_IMAGE_URL+HOME_MODULE.get(6).getIcon(),head_one_iv_7);
                init_load_Image(HEAD_IMAGE_URL+HOME_MODULE.get(7).getIcon(),head_one_iv_8);
            }else if(size == 7)
            {
                init_load_Image(HEAD_IMAGE_URL+HOME_MODULE.get(0).getIcon(),head_one_iv_1);
                head_one_tv_1.setText(HOME_MODULE.get(0).getCatename());

                init_load_Image(HEAD_IMAGE_URL+HOME_MODULE.get(1).getIcon(),head_one_iv_2);
                head_one_tv_2.setText(HOME_MODULE.get(1).getCatename());

                init_load_Image(HEAD_IMAGE_URL+HOME_MODULE.get(2).getIcon(),head_one_iv_3);
                head_one_tv_3.setText(HOME_MODULE.get(2).getCatename());
                head_one_tv_4.setText(HOME_MODULE.get(3).getCatename());
                head_one_tv_5.setText(HOME_MODULE.get(4).getCatename());
                head_one_tv_6.setText(HOME_MODULE.get(5).getCatename());
                head_one_tv_7.setText(HOME_MODULE.get(6).getCatename());
                init_load_Image(HEAD_IMAGE_URL+HOME_MODULE.get(3).getIcon(),head_one_iv_4);

                init_load_Image(HEAD_IMAGE_URL+HOME_MODULE.get(4).getIcon(),head_one_iv_5);
                init_load_Image(HEAD_IMAGE_URL+HOME_MODULE.get(5).getIcon(),head_one_iv_6);
                init_load_Image(HEAD_IMAGE_URL+HOME_MODULE.get(6).getIcon(),head_one_iv_7);
            }else if(size == 6)
            {
                init_load_Image(HEAD_IMAGE_URL+HOME_MODULE.get(0).getIcon(),head_one_iv_1);
                head_one_tv_1.setText(HOME_MODULE.get(0).getCatename());
                init_load_Image(HEAD_IMAGE_URL+HOME_MODULE.get(1).getIcon(),head_one_iv_2);
                head_one_tv_2.setText(HOME_MODULE.get(1).getCatename());
                init_load_Image(HEAD_IMAGE_URL+HOME_MODULE.get(2).getIcon(),head_one_iv_3);
                head_one_tv_3.setText(HOME_MODULE.get(2).getCatename());
                head_one_tv_4.setText(HOME_MODULE.get(3).getCatename());
                head_one_tv_5.setText(HOME_MODULE.get(4).getCatename());
                head_one_tv_6.setText(HOME_MODULE.get(5).getCatename());
                init_load_Image(HEAD_IMAGE_URL+HOME_MODULE.get(3).getIcon(),head_one_iv_4);

                init_load_Image(HEAD_IMAGE_URL+HOME_MODULE.get(4).getIcon(),head_one_iv_5);
                init_load_Image(HEAD_IMAGE_URL+HOME_MODULE.get(5).getIcon(),head_one_iv_6);
            }else if(size == 5)
            {
                init_load_Image(HEAD_IMAGE_URL+HOME_MODULE.get(0).getIcon(),head_one_iv_1);
                head_one_tv_1.setText(HOME_MODULE.get(0).getCatename());
                init_load_Image(HEAD_IMAGE_URL+HOME_MODULE.get(1).getIcon(),head_one_iv_2);
                head_one_tv_2.setText(HOME_MODULE.get(1).getCatename());
                init_load_Image(HEAD_IMAGE_URL+HOME_MODULE.get(2).getIcon(),head_one_iv_3);
                head_one_tv_3.setText(HOME_MODULE.get(2).getCatename());
                head_one_tv_4.setText(HOME_MODULE.get(3).getCatename());
                head_one_tv_5.setText(HOME_MODULE.get(4).getCatename());
                init_load_Image(HEAD_IMAGE_URL+HOME_MODULE.get(3).getIcon(),head_one_iv_4);
                init_load_Image(HEAD_IMAGE_URL+HOME_MODULE.get(4).getIcon(),head_one_iv_5);
            }else if(size == 4)
            {
                head_one_linear_5.setVisibility(View.GONE);
                home_one_start_linear2.setVisibility(View.GONE);
                init_load_Image(HEAD_IMAGE_URL+HOME_MODULE.get(0).getIcon(),head_one_iv_1);
                head_one_tv_1.setText(HOME_MODULE.get(0).getCatename());
                init_load_Image(HEAD_IMAGE_URL+HOME_MODULE.get(1).getIcon(),head_one_iv_2);
                head_one_tv_2.setText(HOME_MODULE.get(1).getCatename());
                init_load_Image(HEAD_IMAGE_URL+HOME_MODULE.get(2).getIcon(),head_one_iv_3);
                head_one_tv_3.setText(HOME_MODULE.get(2).getCatename());
                head_one_tv_4.setText(HOME_MODULE.get(3).getCatename());
                init_load_Image(HEAD_IMAGE_URL+HOME_MODULE.get(3).getIcon(),head_one_iv_4);

            }
        }
    }

    private void init_load_Image( String image_url, AppCompatImageView image) {
        Glide.with(getContext())
                .load(image_url)
                .asBitmap()
                .error(R.mipmap.test)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(image);
    }
    private BDAbstractLocationListener mListener = new BDAbstractLocationListener() {

        @Override
        public void onReceiveLocation(BDLocation location) {
            // TODO Auto-generated method stub
            if (null != location && location.getLocType() != BDLocation.TypeServerError) {
                StringBuffer sb = new StringBuffer(256);
                sb.append("time : ");
                /**
                 * 时间也可以使用systemClock.elapsedRealtime()方法 获取的是自从开机以来，每次回调的时间；
                 * location.getTime() 是指服务端出本次结果的时间，如果位置不发生变化，则时间不变
                 */
                sb.append(location.getTime());
                sb.append("\nlocType : ");// 定位类型
                sb.append(location.getLocType());
                sb.append("\nlocType description : ");// *****对应的定位类型说明*****
                sb.append(location.getLocTypeDescription());
                sb.append("\n纬度: ");// 纬度
                sb.append(location.getLatitude());
                sb.append("\n经度 : ");// 经度
                sb.append(location.getLongitude());
                sb.append("\n半径 : ");// 半径
                sb.append(location.getRadius());
                sb.append("\n国家码 : ");// 国家码
                sb.append(location.getCountryCode());
                sb.append("\n国际名称 : ");// 国家名称
                sb.append(location.getCountry());
                sb.append("\n城市编码 : ");// 城市编码
                sb.append(location.getCityCode());
                sb.append("\n城市 : ");// 城市
                sb.append(location.getCity());
                sb.append("\n城市行政区编码 : ");// 区
                sb.append(location.getAdCode());
                sb.append("\n城市行政地址: ");// 区
                sb.append(location.getAddrStr());

                sb.append("\n区 : ");// 区
                sb.append(location.getDistrict());
                sb.append("\n街道 : ");// 街道
                sb.append(location.getStreet());
                sb.append("\n地址信息 : ");// 地址信息
                sb.append(location.getAddrStr());
                sb.append("\nUserIndoorState: ");// *****返回用户室内外判断结果*****
                sb.append(location.getUserIndoorState());
                sb.append("\nDirection(not all devices have value): ");
                sb.append(location.getDirection());// 方向
                sb.append("\nlocationdescribe: ");
                sb.append(location.getLocationDescribe());// 位置语义化信息
                sb.append("\nPoi: ");// POI信息

                if (location.getPoiList() != null && !location.getPoiList().isEmpty()) {
                    for (int i = 0; i < location.getPoiList().size(); i++) {
                        Poi poi = (Poi) location.getPoiList().get(i);
                        sb.append(poi.getName() + ";");
                    }
                }
                if (location.getLocType() == BDLocation.TypeGpsLocation) {// GPS定位结果
                    sb.append("\nspeed : ");
                    sb.append(location.getSpeed());// 速度 单位：km/h
                    sb.append("\nsatellite : ");
                    sb.append(location.getSatelliteNumber());// 卫星数目
                    sb.append("\nheight : ");
                    sb.append(location.getAltitude());// 海拔高度 单位：米
                    sb.append("\ngps status : ");
                    sb.append(location.getGpsAccuracyStatus());// *****gps质量判断*****
                    sb.append("\ndescribe : ");
                    sb.append("gps定位成功");
                } else if (location.getLocType() == BDLocation.TypeNetWorkLocation) {// 网络定位结果
                    // 运营商信息
                    if (location.hasAltitude()) {// *****如果有海拔高度*****
                        sb.append("\nheight : ");
                        sb.append(location.getAltitude());// 单位：米
                    }
                    sb.append("\noperationers : ");// 运营商信息
                    sb.append(location.getOperators());
                    sb.append("\ndescribe : ");
                    sb.append("网络定位成功");
                } else if (location.getLocType() == BDLocation.TypeOffLineLocation) {// 离线定位结果
                    sb.append("\ndescribe : ");
                    sb.append("离线定位成功，离线定位结果也是有效的");
                } else if (location.getLocType() == BDLocation.TypeServerError) {
                    sb.append("\ndescribe : ");
                    sb.append("服务端网络定位失败，可以反馈IMEI号和大体定位时间到loc-bugs@baidu.com，会有人追查原因");
                } else if (location.getLocType() == BDLocation.TypeNetWorkException) {
                    sb.append("\ndescribe : ");
                    sb.append("网络不同导致定位失败，请检查网络是否通畅");
                } else if (location.getLocType() == BDLocation.TypeCriteriaException) {
                    sb.append("\ndescribe : ");
                    sb.append("无法获取有效定位依据导致定位失败，一般是由于手机的原因，处于飞行模式下一般会造成这种结果，可以试着重启手机");
                }
                locationService.unregisterListener(mListener); //注销掉监听
                locationService.stop(); //停止定位服务
                System.out.println("定位之后返回的结果******************************************************"+sb.toString());
            }
        }

    };

}
