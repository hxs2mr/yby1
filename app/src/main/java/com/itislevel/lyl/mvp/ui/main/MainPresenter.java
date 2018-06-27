package com.itislevel.lyl.mvp.ui.main;


import com.itislevel.lyl.base.RxPresenter;
import com.itislevel.lyl.common.RxUtil;
import com.itislevel.lyl.mvp.model.DataManager;
import com.itislevel.lyl.mvp.model.bean.AddressBean;
import com.itislevel.lyl.mvp.model.bean.AppUpdate;
import com.itislevel.lyl.mvp.model.bean.UserHeaderNickInfo;
import com.itislevel.lyl.mvp.model.http.response.LYLResponse;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.observers.ResourceObserver;

/**
 * **********************
 * 功 能:必填必填必填必填必填必填
 * 创建人:itisi
 * 邮  箱:itisivip@qq.com
 * 创建时间:2017/7/6 11:09
 * 修改人:itisi
 * 修改时间: 2017/7/6 11:09
 * 修改内容:itisi
 * *********************
 */

public class MainPresenter extends RxPresenter<MainContract.View> implements MainContract.Presenter {
    private DataManager mDataManager;

    @Inject
    public MainPresenter(DataManager dataManager) {
        mDataManager = dataManager;
    }

    @Override
    public void attachView(MainContract.View view) {
        super.attachView(view);
        retisterEvent();
    }

    /**
     * 对retrofit 发情的请求 进行订阅?
     */
    private void retisterEvent() {
    // TODO: 2017/7/6  托管订阅???

    }

    @Override
    public void testShowPresenter(boolean isShow) {
        mView.testShowView("itisi:"+isShow);
    }

    @Override
    public void userInfoById(String action) {
        mDataManager.userInfoById(action)
                .compose(RxUtil.<LYLResponse<List<UserHeaderNickInfo>>>rxSchedulerObservableHelper())
                .compose(RxUtil.<List<UserHeaderNickInfo>>handlerLYLResult())
                .subscribeWith(new ResourceObserver<List<UserHeaderNickInfo>>() {
                    @Override
                    public void onNext(List<UserHeaderNickInfo> addressBeans) {
                        mView.userInfoById(addressBeans);
                    }

                    @Override
                    public void onError(Throwable e) {

                        if (mView != null) {
                            mView.stateError(e);
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void appupdate(String action) {
        mDataManager.appupdate(action)
                .compose(RxUtil.<LYLResponse<AppUpdate>>rxSchedulerObservableHelper())
                .compose(RxUtil.<AppUpdate>handlerLYLResult())
                .subscribeWith(new ResourceObserver<AppUpdate>() {
                    @Override
                    public void onNext(AppUpdate appUpdate) {
                        mView.appupdate(appUpdate);
                    }
                    @Override
                    public void onError(Throwable e) {

                        if (mView != null) {
                            mView.stateError(e);
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
