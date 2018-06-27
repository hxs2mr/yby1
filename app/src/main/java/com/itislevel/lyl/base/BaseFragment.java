package com.itislevel.lyl.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.view.ViewGroup;


import com.itislevel.lyl.app.App;
import com.itislevel.lyl.di.component.DaggerFragmentComponent;
import com.itislevel.lyl.di.component.FragmentComponent;
import com.itislevel.lyl.di.module.FragmentModule;
import com.itislevel.lyl.mvp.model.http.exception.ApiException;
import com.itislevel.lyl.mvp.ui.property.PropertLoginActivity;
import com.itislevel.lyl.mvp.ui.user.LoginActivity;
import com.itislevel.lyl.utils.ActivityUtil;
import com.itislevel.lyl.utils.SAToast;
import com.itislevel.lyl.utils.ToastUtil;
import com.kaopiz.kprogresshud.KProgressHUD;

import javax.inject.Inject;

/**
 * **********************
 * 功 能:待MVP的Fragment基类
 * 创建人:itisi
 * 邮  箱:itisivip@qq.com
 * 创建时间:2017/7/5 16:40
 * 修改人:itisi
 * 修改时间: 2017/7/5 16:40
 * 修改内容:itisi
 * *********************
 */

public abstract class BaseFragment<T extends BasePresenter> extends NoMVPFragment implements BaseView  {

    @Inject
    protected T mPresenter;
    protected KProgressHUD loadingDialog;


    protected FragmentComponent getFragmentComponent(){
        return DaggerFragmentComponent.builder()
                .appComponent(App.getAppComponent())
                .fragmentModule(getFragmentModule())
                .build();
    }

    protected FragmentModule getFragmentModule(){
        return new FragmentModule(this);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        initInject();
        mPresenter.attachView(this);
        super.onViewCreated(view, savedInstanceState);

        loadingDialog = KProgressHUD.create(getActivity())
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
//                .setLabel("Please wait")
//                .setDetailsLabel("Downloading data")
                .setCancellable(true)
                .setAnimationSpeed(2)
                .setDimAmount(0.5f);

    }

    @Override
    public void onDestroy() {
        if (mPresenter!=null){
            mPresenter.detachView();
        }
        super.onDestroy();
    }

    @Override
    public void showErrorMsg(String msg) {
        Snackbar.make(((ViewGroup) getActivity().findViewById(android.R.id.content)).getChildAt(0), msg, Snackbar.LENGTH_LONG).show();
    }

    protected abstract void initInject();


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
                ToastUtil.Error("请先登录！");
                //ActivityUtil.getInstance().finishAll();
               ActivityUtil.getInstance().openActivity(getActivity(), LoginActivity.class);
            }else if (apiException.getCode() == -2){
                ToastUtil.Error("请先登录！");
                // ActivityUtil.getInstance().finishAll();
                ActivityUtil.getInstance().openActivity(getActivity(), PropertLoginActivity.class);
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







