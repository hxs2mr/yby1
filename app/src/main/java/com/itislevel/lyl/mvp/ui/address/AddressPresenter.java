package com.itislevel.lyl.mvp.ui.address;


import com.itislevel.lyl.base.RxPresenter;
import com.itislevel.lyl.common.RxUtil;
import com.itislevel.lyl.mvp.model.DataManager;
import com.itislevel.lyl.mvp.model.bean.AddressBean;
import com.itislevel.lyl.mvp.model.bean.MeiZiBean;
import com.itislevel.lyl.mvp.model.http.response.GankResponse;
import com.itislevel.lyl.mvp.model.http.response.LYLResponse;
import com.orhanobut.logger.Logger;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;
import io.reactivex.observers.ResourceObserver;

/**
 * **********************
 * 功 能:主页-主页
 * 创建人:itisi
 * 邮  箱:itisivip@qq.com
 * 创建时间:2017/7/7 13:58
 * 修改人:itisi
 * 修改时间: 2017/7/7 13:58
 * 修改内容:itisi
 * *********************
 */
public class AddressPresenter extends RxPresenter<AddressContract.View> implements
        AddressContract.Presenter {

    private DataManager mDataManager;

    @Inject
    public AddressPresenter(DataManager dataManager) {
        mDataManager = dataManager;
    }

    @Override
    public void loadData(int num, int page) {
        mDataManager.getMeiZiList(num, page)
                .compose(RxUtil.<GankResponse<List<MeiZiBean>>>rxSchedulerObservableHelper())
                .compose(RxUtil.<List<MeiZiBean>>handlerGankResult())
                .subscribeWith(new ResourceObserver<List<MeiZiBean>>() {
                    @Override
                    public void onNext(@NonNull List<MeiZiBean> meiZiBeen) {
                        mView.showContent("itisi:" + meiZiBeen.get(0).getCreatedAt());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Logger.i("错误信息:" + e.getMessage());
                        if (mView != null) {
                            mView.stateError(e);
                        }
                    }

                    @Override
                    public void onComplete() {
                        Logger.i("complete");
                    }
                });

    }

    @Override
    public void province(String action) {
        mDataManager.province(action)
                .compose(RxUtil.<LYLResponse<List<AddressBean>>>rxSchedulerObservableHelper())
                .compose(RxUtil.<List<AddressBean>>handlerLYLResult())
                .subscribeWith(new ResourceObserver<List<AddressBean>>() {
                    @Override
                    public void onNext(List<AddressBean> addressBeans) {
                        mView.province(addressBeans);
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
    public void city(String action) {
        mDataManager.city(action)
                .compose(RxUtil.<LYLResponse<List<AddressBean>>>rxSchedulerObservableHelper())
                .compose(RxUtil.<List<AddressBean>>handlerLYLResult())
                .subscribeWith(new ResourceObserver<List<AddressBean>>() {
                    @Override
                    public void onNext(List<AddressBean> addressBeans) {
                        mView.city(addressBeans);
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
    public void county(String action) {
        mDataManager.county(action)
                .compose(RxUtil.<LYLResponse<List<AddressBean>>>rxSchedulerObservableHelper())
                .compose(RxUtil.<List<AddressBean>>handlerLYLResult())
                .subscribeWith(new ResourceObserver<List<AddressBean>>() {
                    @Override
                    public void onNext(List<AddressBean> addressBeans) {
                        mView.county(addressBeans);
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
