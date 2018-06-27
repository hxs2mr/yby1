package com.itislevel.lyl.mvp.ui.property.collection;

import com.itislevel.lyl.base.RxPresenter;
import com.itislevel.lyl.common.RxUtil;
import com.itislevel.lyl.mvp.model.DataManager;
import com.itislevel.lyl.mvp.model.bean.CollectionListBean;
import com.itislevel.lyl.mvp.ui.property.complaint.ComplaintContract;

import javax.inject.Inject;

import io.reactivex.observers.ResourceObserver;
import okhttp3.MultipartBody;

/**
 * Created by Administrator on 2018\6\12 0012.
 */

public class CollectionPresenter extends RxPresenter<CollectionContract.View>implements CollectionContract.Presenter{
    private DataManager mDataManager;
    @Inject
    public CollectionPresenter(DataManager dataManager)
    {
        this.mDataManager = dataManager;
    }


    @Override
    public void collectMaintenanceList(String action) {
        mDataManager.collectMaintenanceList(action)
                .compose(RxUtil.rxSchedulerObservableHelper())
                .compose(RxUtil.handlerLYLResult())
                .subscribeWith(new ResourceObserver<CollectionListBean>() {
                    @Override
                    public void onNext(CollectionListBean bean) {
                            mView.collectMaintenanceList(bean);
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
    public void deleMaintenanceList(String data) {
        mDataManager.deleMaintenanceList(data)
                .compose(RxUtil.rxSchedulerObservableHelper())
                .compose(RxUtil.handlerLYLResult())
                .subscribeWith(new ResourceObserver<String>() {
                    @Override
                    public void onNext(String s) {
                        mView.deleMaintenanceList(s);
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
}
