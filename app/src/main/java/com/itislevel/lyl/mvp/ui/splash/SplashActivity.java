package com.itislevel.lyl.mvp.ui.splash;

import android.Manifest;
import android.content.Intent;
import android.os.SystemClock;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.ImageView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.ViewPropertyAnimation;
import com.itislevel.lyl.R;
import com.itislevel.lyl.app.Constants;
import com.itislevel.lyl.base.RootActivity;
import com.itislevel.lyl.mvp.ui.main.MainActivity;
import com.itislevel.lyl.mvp.ui.user.LoginActivity;
import com.itislevel.lyl.net.RestClent;
import com.itislevel.lyl.net.callback.IFailure;
import com.itislevel.lyl.net.callback.ISuccess;
import com.itislevel.lyl.utils.ActivityUtil;
import com.itislevel.lyl.utils.GsonUtil;
import com.itislevel.lyl.utils.SharedPreferencedUtils;
import com.itislevel.lyl.utils.ToastUtil;
import com.jaeger.library.StatusBarUtil;
import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.AnimatorSet;
import com.nineoldandroids.animation.ObjectAnimator;
import com.orhanobut.logger.Logger;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public class SplashActivity extends RootActivity<SplashPresenter> implements SplashContract.View {

    private Timer timer =  null;
//    @BindView(R.id.img_id)
//     ImageView img;
//    @BindView(R.id.icon_mark)
//     ImageView ImgMark;
private RxPermissions rxPermissions;
    ViewPropertyAnimation.Animator animator=new ViewPropertyAnimation.Animator(){

        @Override
        public void animate(View view) {
            view.setAlpha(0f);
            ObjectAnimator objAnimator=ObjectAnimator.ofFloat(view,"alpha",0f,1f);
            objAnimator.setDuration(2000);
            objAnimator.start();
        }
    };
    @Override
    protected int getLayoutId() {
        StatusBarUtil.setTranslucent(this, 1);//不加0 是半透明效果
//        getWindow().setBackgroundDrawable(null);
        return R.layout.activity_splash;
    }

    public  void getNetIp() {//获取ip地址
        RestClent.builder()
                .url("http://ip.taobao.com/service/getIpInfo.php?ip=myip")
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        System.out.println("外网的ip地址获取*************************************"+response);
                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onIFailure() {
                    }
                })
                .build()
                .get();
    }

    TimerTask timerTask = new TimerTask() {
        @Override
        public void run() {
            Intent intent = new Intent(SplashActivity.this,MainActivity.class);
            startActivity(intent);
            finish();
        }
    };
    @Override
    protected void initEventAndData() {
        rxPermissions = new RxPermissions(this);

        timer = new Timer();
        timer.schedule(timerTask,2000);
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    private void need_permissions() {//需要的权限
        rxPermissions
                .requestEach(Manifest.permission.CAMERA,
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION)
                .subscribe(permission -> {
                    if (permission.granted) {
                        //权限获取成功
                    } else {
                        ToastUtil.Error("打开定位权限进行定位!");
                    }
                });
    }
    /**
     * 禁止滑动关闭
     * @return
     */
    @Override
    protected boolean setSwipeEnabled() {
        return false;
    }

    @Override
    public void showContent(String msg) {

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

    }
    public interface SplashService{
        @GET("splash/{packagename}")
        Call<ResponseBody> isproject(@Path("packagename") String packagename);

    }
}
