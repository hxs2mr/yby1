package com.itislevel.lyl.mvp.model;

import android.text.TextUtils;

import com.itislevel.lyl.base.BaseView;
import com.itislevel.lyl.mvp.model.http.exception.ApiException;
import com.orhanobut.logger.Logger;

import io.reactivex.observers.ResourceObserver;
import retrofit2.HttpException;

/**
 * **********************
 * 功 能: 对观察者的统一处理处理
 * 创建人:itisi
 * 邮  箱:itisivip@qq.com
 * 创建时间:2017/7/13 10:15
 * 修改人:itisi
 * 修改时间: 2017/7/13 10:15
 * 修改内容:itisi
 * *********************
 */

public abstract class CommonSubscriber<T> extends ResourceObserver<T> { //ResourceSubscriber
    private BaseView mView;//当前视图对象
    private String mErrorMsg;//错误信息
    private boolean isShowErrorState=true;//是否显示错误信息?

    public CommonSubscriber(BaseView view) {
        mView = view;
    }

    public CommonSubscriber(BaseView view, String errorMsg) {
        mView = view;
        mErrorMsg = errorMsg;
    }

    public CommonSubscriber(BaseView view, boolean isShowErrorState) {
        mView = view;
        this.isShowErrorState = isShowErrorState;
    }

    public CommonSubscriber(BaseView view, String errorMsg, boolean isShowErrorState) {
        mView = view;
        mErrorMsg = errorMsg;
        this.isShowErrorState = isShowErrorState;
    }

    @Override
    public void onComplete() {

    }

    @Override
    public void onError(Throwable e) {
        if (mView==null){
            return;
        }
        if (!TextUtils.isEmpty(mErrorMsg)){
            mView.showErrorMsg(mErrorMsg);
        }else if(e instanceof ApiException){
            mView.showErrorMsg(e.toString());
        }else if(e instanceof HttpException){
            mView.showErrorMsg("数据加载失败ヽ(≧Д≦)ノ");
        }else{
            mView.showErrorMsg("未知错误ヽ(≧Д≦)ノ"+e.getMessage());
            Logger.i(e.toString());
        }
        if (isShowErrorState){
            mView.stateError(e);
        }
    }
}
