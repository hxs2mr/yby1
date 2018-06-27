package com.itislevel.lyl.mvp.ui.property.complaint;

import com.itislevel.lyl.base.RxPresenter;
import com.itislevel.lyl.common.RxUtil;
import com.itislevel.lyl.mvp.model.DataManager;
import com.itislevel.lyl.mvp.model.bean.ComSearchBean;
import com.itislevel.lyl.mvp.model.bean.ComplaintTypeBean;
import com.itislevel.lyl.mvp.model.bean.FileUploadBean;
import com.itislevel.lyl.mvp.model.bean.PropertyDetailBean;
import com.itislevel.lyl.mvp.model.http.response.LYLResponse;
import com.orhanobut.logger.Logger;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;
import io.reactivex.observers.ResourceObserver;
import okhttp3.MultipartBody;

/**
 * Created by Administrator on 2018\5\29 0029.
 */

public class ComplaintPresenter extends RxPresenter<ComplaintContract.View>implements ComplaintContract.Presenter{
    private DataManager mDataManager;
    @Inject
    public ComplaintPresenter(DataManager dataManager)
    {
        this.mDataManager = dataManager;
    }

    @Override
    public void uploadHeader(MultipartBody.Part file) {
        mDataManager.uploadHeader(file)
                .compose(RxUtil.<LYLResponse<FileUploadBean>>rxSchedulerObservableHelper())
                .compose(RxUtil.<FileUploadBean>handlerLYLResult())
                .subscribeWith(new ResourceObserver<FileUploadBean>() {
                    @Override
                    public void onNext(@NonNull FileUploadBean fileUploadBean) {
                        mView.uploadHeader(fileUploadBean);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Logger.e("错误信息:" + e.getMessage());
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
    public void complaintType(String action) {

        mDataManager.complaintType(action)
                .compose(RxUtil.rxSchedulerObservableHelper())
                .compose(RxUtil.handlerLYLResult())
                .subscribeWith(new ResourceObserver<List<ComplaintTypeBean>>() {
                    @Override
                    public void onNext(List<ComplaintTypeBean> strings) {
                        mView.complaintType(strings);
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
    public void addComplaint(String data) {
        mDataManager.addComplaint(data)
                .compose(RxUtil.rxSchedulerObservableHelper())
                .compose(RxUtil.handlerLYLResult())
                .subscribeWith(new ResourceObserver<String>() {
                    @Override
                    public void onNext(String s) {
                        mView.addComplaint(s);
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
    public void findComplaintList(String data) {
        mDataManager.findComplaintList(data)
                .compose(RxUtil.rxSchedulerObservableHelper())
                .compose(RxUtil.handlerLYLResult())
                .subscribeWith(new ResourceObserver<ComSearchBean>() {
                    @Override
                    public void onNext(ComSearchBean bean) {
                        mView.findComplaintList(bean);
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
    public void detaComplaintList(String action) {
        mDataManager.detaComplaintList(action)
                .compose(RxUtil.rxSchedulerObservableHelper())
                .compose(RxUtil.handlerLYLResult())
                .subscribeWith(new ResourceObserver<List<PropertyDetailBean>>() {
                    @Override
                    public void onNext(List<PropertyDetailBean> propertyDetailBeans) {
                        mView.detaComplaintList(propertyDetailBeans);
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
