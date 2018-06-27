package com.itislevel.lyl.mvp.ui.property;

import com.itislevel.lyl.adapter.LiveAddressBean;
import com.itislevel.lyl.base.RxPresenter;
import com.itislevel.lyl.common.RxUtil;
import com.itislevel.lyl.mvp.model.DataManager;
import com.itislevel.lyl.mvp.model.bean.PropertLoginBean;
import com.itislevel.lyl.mvp.model.bean.PropretyLiveBean;
import com.itislevel.lyl.mvp.model.bean.PropretyNoticeBean;
import com.itislevel.lyl.mvp.model.bean.VillageNameBean;
import com.itislevel.lyl.mvp.model.http.response.LYLResponse;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.observers.ResourceObserver;

/**
 * Created by Administrator on 2018\5\22 0022.
 */

public class PropertyPresenter extends RxPresenter<PropertyContract.View>implements PropertyContract.Presenter{
    private DataManager mDataManager;
    @Inject
    public PropertyPresenter(DataManager dataManager)
    {
        this.mDataManager = dataManager;
    }

    @Override
    public void getSSMCode(String action) {
        mDataManager.getSSMCode(action)
                .compose(RxUtil.<LYLResponse<String>>rxSchedulerObservableHelper())
                .compose(RxUtil.<String>handlerLYLResult())
                .subscribeWith(new ResourceObserver<String>() {
                    @Override
                    public void onNext(String msg) {
                        mView.getSSMCode(msg);
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (mView!=null){
                            mView.stateError(e);
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void loginEstates(String action) {
        mDataManager.loginEstates(action)
                .compose(RxUtil.rxSchedulerObservableHelper())
                .compose(RxUtil.handlerLYLResult())
                .subscribeWith(new ResourceObserver<PropertLoginBean>() {
                    @Override
                    public void onNext(PropertLoginBean bean) {
                                mView.loginEstates(bean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (mView!=null){
                            mView.stateError(e);
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void noticeEstates(String action) {
        mDataManager.noticeEstates(action)
                .compose(RxUtil.rxSchedulerObservableHelper())
                .compose(RxUtil.handlerLYLResult())
                .subscribeWith(new ResourceObserver<PropretyNoticeBean>() {
                    @Override
                    public void onNext(PropretyNoticeBean bean) {
                        mView.noticeEstates(bean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (mView!=null){
                            mView.stateError(e);
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void propretyLive(String action) {
        mDataManager.propretyLive(action)
                .compose(RxUtil.rxSchedulerObservableHelper())
                .compose(RxUtil.handlerLYLResult())
                .subscribeWith(new ResourceObserver<PropretyLiveBean>() {
                    @Override
                    public void onNext(PropretyLiveBean list) {
                        mView.propretyLive(list);
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (mView!=null){
                            mView.stateError(e);
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void findVillagename(String action) {
        mDataManager.findVillagename(action)
                .compose(RxUtil.rxSchedulerObservableHelper())
                .compose(RxUtil.handlerLYLResult())
                .subscribeWith(new ResourceObserver<List<VillageNameBean>>() {
                    @Override
                    public void onNext(List<VillageNameBean> villageNameBeans) {
                        mView.findVillagename(villageNameBeans);
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (mView!=null){
                            mView.stateError(e);
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void findLiveaddress(String action) {
        mDataManager.findLiveaddress(action)
                .compose(RxUtil.rxSchedulerObservableHelper())
                .compose(RxUtil.handlerLYLResult())
                .subscribeWith(new ResourceObserver<List<LiveAddressBean>>() {
                    @Override
                    public void onNext(List<LiveAddressBean> liveAddressBeans) {
                        mView.findLiveaddress(liveAddressBeans);
                    }

                    @Override
                    public void onError(Throwable e) {
                    }
                    @Override
                    public void onComplete() {

                    }
                });
    }

}
