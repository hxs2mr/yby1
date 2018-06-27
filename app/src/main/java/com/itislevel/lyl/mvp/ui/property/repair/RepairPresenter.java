package com.itislevel.lyl.mvp.ui.property.repair;

import com.itislevel.lyl.base.BasePresenter;
import com.itislevel.lyl.base.RxPresenter;
import com.itislevel.lyl.common.RxUtil;
import com.itislevel.lyl.mvp.model.DataManager;
import com.itislevel.lyl.mvp.model.bean.ProperCommentList;
import com.itislevel.lyl.mvp.model.bean.RepairCityListBean;
import com.itislevel.lyl.mvp.model.bean.RepairListBean;
import com.itislevel.lyl.mvp.model.bean.RepairTypeListBean;
import com.itislevel.lyl.mvp.model.bean.SeleBean;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.observers.ResourceObserver;

/**
 * Created by Administrator on 2018\6\1 0001.
 */

public class RepairPresenter extends RxPresenter<RepairContract.View> implements RepairContract.Presenter{
    private DataManager mDatamanage;
    @Inject
    public RepairPresenter(DataManager dataManager)
    {
        this.mDatamanage = dataManager;
    }

    @Override
    public void maintenanceList(String bean) {
        mDatamanage.maintenanceList(bean)
                .compose(RxUtil.rxSchedulerObservableHelper())
                .compose(RxUtil.handlerLYLResult())
                .subscribeWith(new ResourceObserver<RepairListBean>() {
                    @Override
                    public void onNext(RepairListBean bean) {
                        mView.maintenanceList(bean);
                    }
                    @Override
                    public void onError(Throwable e) {
                        if(mView!=null)
                        {
                            mView.stateError(e);
                        }
                    }
                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void queryarealist(String bean) {
        mDatamanage.queryarealist(bean)
                .compose(RxUtil.rxSchedulerObservableHelper())
                .compose(RxUtil.handlerLYLResult())
                .subscribeWith(new ResourceObserver<List<RepairCityListBean>>() {
                    @Override
                    public void onNext(List<RepairCityListBean> repairCityListBeans) {
                            mView.queryarealist(repairCityListBeans);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void queryrepairallcatelist(String bean) {
        mDatamanage.queryrepairallcatelist(bean)
                .compose(RxUtil.rxSchedulerObservableHelper())
                .compose(RxUtil.handlerLYLResult())
                .subscribeWith(new ResourceObserver<List<RepairCityListBean>>() {
                    @Override
                    public void onNext(List<RepairCityListBean> repairTypeListBeans) {
                            mView.queryrepairallcatelist(repairTypeListBeans);
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void commentEstatesList(String data) {
            mDatamanage.commentEstatesList(data)
                    .compose(RxUtil.rxSchedulerObservableHelper())
                    .compose(RxUtil.handlerLYLResult())
                    .subscribeWith(new ResourceObserver<ProperCommentList>() {
                        @Override
                        public void onNext(ProperCommentList properCommentList) {
                            mView.commentEstatesList(properCommentList);
                        }

                        @Override
                        public void onError(Throwable e) {
                            if (mView != null)
                            {
                                mView.stateError();
                            }
                        }

                        @Override
                        public void onComplete() {

                        }
                    });
    }
    @Override
    public void addCommentEstates(String action) {
        mDatamanage.addCommentEstates(action)
                .compose(RxUtil.rxSchedulerObservableHelper())
                .compose(RxUtil.handlerLYLResult())
                .subscribeWith(new ResourceObserver<String>() {
                    @Override
                    public void onNext(String s) {
                        mView.addCommentEstates(s);
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (mView != null)
                        {
                            mView.stateError(e);
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void addCollectMaintenance(String action) {
        mDatamanage.addCollectMaintenance(action)
                .compose(RxUtil.rxSchedulerObservableHelper())
                .compose(RxUtil.handlerLYLResult())
                .subscribeWith(new ResourceObserver<String>() {
                    @Override
                    public void onNext(String s) {
                            mView.addCollectMaintenance(s);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void seleCommentConunt(String bean) {
        mDatamanage.seleCommentConunt(bean)
                .compose(RxUtil.rxSchedulerObservableHelper())
                .compose(RxUtil.handlerLYLResult())
                .subscribeWith(new ResourceObserver<SeleBean>() {
                    @Override
                    public void onNext(SeleBean bean) {
                        mView.seleCommentConunt(bean);
                    }
                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void prolooknumcount(String action) {
        mDatamanage.prolooknumcount(action)
                .compose(RxUtil.rxSchedulerObservableHelper())
                .compose(RxUtil.handlerLYLResult())
                .subscribeWith(new ResourceObserver<String>() {
                    @Override
                     public void onNext(String s) {
                            mView.prolooknumcount(s);
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
