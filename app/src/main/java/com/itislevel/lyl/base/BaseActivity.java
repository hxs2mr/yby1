package com.itislevel.lyl.base;

import android.support.v7.app.AppCompatDelegate;


import com.itislevel.lyl.app.App;
import com.itislevel.lyl.di.component.ActivityComponent;
import com.itislevel.lyl.di.component.DaggerActivityComponent;
import com.itislevel.lyl.di.module.ActivityModule;
import com.itislevel.lyl.mvp.model.http.exception.ApiException;
import com.itislevel.lyl.mvp.ui.property.PropertLoginActivity;
import com.itislevel.lyl.mvp.ui.user.LoginActivity;
import com.itislevel.lyl.utils.ActivityUtil;
import com.itislevel.lyl.utils.SAToast;
import com.itislevel.lyl.utils.ToastUtil;
import com.itislevel.lyl.utils.rxbus.RxBus;
import com.kaopiz.kprogresshud.KProgressHUD;

import javax.inject.Inject;

/**
 * **********************
 * 功 能:待MVP的Activity基类
 * 创建人:itisi
 * 邮  箱:itisivip@qq.com
 * 创建时间:2017/7/5 16:40
 * 修改人:itisi
 * 修改时间: 2017/7/5 16:40
 * 修改内容:itisi
 * *********************
 */

public abstract class BaseActivity<T extends BasePresenter> extends NoMVPActivity implements
        BaseView {
    @Inject
    protected T mPresenter;
    protected KProgressHUD loadingDialog;
    /**
     * 注入依赖
     */
    protected abstract void initInject();

    protected ActivityComponent getActivityComponent() {
        return DaggerActivityComponent.builder()
                .appComponent(App.getAppComponent())
                .activityModule(getActivityModule())
                .build();
    }

    private ActivityModule getActivityModule() {
        return new ActivityModule(this);
    }


    @Override
    protected void onViewCreated() {
        super.onViewCreated();
        initInject();
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
        RxBus.getInstance().init(this);
        //                .setLabel("Please wait")
//                .setDetailsLabel("Downloading data")
        loadingDialog = KProgressHUD.create(this)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
//                .setLabel("Please wait")
//                .setDetailsLabel("Downloading data")
                .setCancellable(true)
                .setAnimationSpeed(2)
                .setDimAmount(0.5f);

    }

    @Override
    protected void onDestroy() {
        if (mPresenter != null) {
            mPresenter.detachView();
        }
        super.onDestroy();

    }

    /**
     * 主题切换
     *
     * @param isNight
     */
    @Override
    public void useNightMode(boolean isNight) {
        if (isNight) {
            AppCompatDelegate.setDefaultNightMode(
                    AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(
                    AppCompatDelegate.MODE_NIGHT_NO);
        }
        recreate();
    }

    /**
     * 显示错误信息
     *
     * @param msg
     */
    @Override
    public void showErrorMsg(String msg) {
//        SnackbarUtil.show(((ViewGroup) findViewById(android.R.id.content)).getChildAt(0), msg);
//        Snackbar.make(((ViewGroup) findViewById(android.R.id.content)).getChildAt(0), msg,
// Snackbar.LENGTH_LONG).show();
//        stateError(); //重写这个方法 处理即可
//
//        AliPayTools.aliPay();
//        WechatPayTools.wechatPayApp();
    }

    @Override
    public void stateError(Exception e) {

        if (e instanceof Throwable) {
            stateError((Throwable)e);
        } else {
            ToastUtil.Error("网络延迟");
        }
    }

    @Override
    public void stateError(Throwable e) {

        if (loadingDialog.isShowing()) {
            loadingDialog.dismiss();
        }

        if (e instanceof ApiException) {
            ApiException apiException = (ApiException) e;
            if (apiException.getCode() == -1) {
                // TODO: 2017-12-29 有时间再换另外一种方案
                SAToast.makeText(this,"请先登录！").show();
                // ActivityUtil.getInstance().finishAll();
                ActivityUtil.getInstance().openActivity(this, LoginActivity.class);
            }else if (apiException.getCode() == -2){
                SAToast.makeText(this,"请先登录！").show();
                // ActivityUtil.getInstance().finishAll();
                ActivityUtil.getInstance().openActivity(this, PropertLoginActivity.class);
            } else {
                ToastUtil.Error(apiException.getMessage());
            }
        } else {
            ToastUtil.Error("网络访问错误");
        }
    }

    @Override
    public void stateError() {
        if (loadingDialog.isShowing()) {
            loadingDialog.dismiss();
        }
    }
}
