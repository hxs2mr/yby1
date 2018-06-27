package com.itislevel.lyl.mvp.ui.main.home;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.JSONSerializable;
import com.itislevel.lyl.base.RxPresenter;
import com.itislevel.lyl.common.RxUtil;
import com.itislevel.lyl.mvp.model.DataManager;
import com.itislevel.lyl.mvp.model.bean.FunsharingAddBean;
import com.itislevel.lyl.mvp.model.bean.FunsharingCommnetAddBean;
import com.itislevel.lyl.mvp.model.bean.HomeBean;
import com.itislevel.lyl.mvp.model.bean.MeiZiBean;
import com.itislevel.lyl.mvp.model.bean.PlaceBean;
import com.itislevel.lyl.mvp.model.bean.UserInfoBean;
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
public class HomePresenter extends RxPresenter<HomeContract.View> implements HomeContract.Presenter {

    private DataManager mDataManager;

    @Inject
    public HomePresenter(DataManager dataManager) {
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
                        Logger.i("错误信息:"+e.getMessage());
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
    public void firstPage(String message) {
        mDataManager.firstPage(message)
                .compose(RxUtil.rxSchedulerObservableHelper())
                .compose(RxUtil.handlerLYLResult())
                .subscribeWith(new ResourceObserver<HomeBean>() {
                    @Override
                    public void onNext(@NonNull HomeBean homeBean) {
                        System.out.println("********************************************************************************");
                        System.out.println("********************************************************************************");
                        mView.firstPage(homeBean);
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
    public void plcae(String message) {
            mDataManager.place(message)
                        .compose(RxUtil.rxSchedulerObservableHelper())
                        .compose(RxUtil.handlerLYLResult())
                        .subscribeWith(new ResourceObserver<PlaceBean>() {
                            @Override
                            public void onNext(PlaceBean placeBean) {
                                System.out.println("********************************************************************************");
                                System.out.println("********************************************************************************");
                                System.out.println(placeBean+"");

                                mView.plcae(placeBean);//获取数据完成
                            }

                            @Override
                            public void onError(Throwable e) {
                                Logger.i("错误信息:" + e.getMessage());
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
