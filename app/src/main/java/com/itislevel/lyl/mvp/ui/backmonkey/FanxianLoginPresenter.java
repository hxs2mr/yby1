package com.itislevel.lyl.mvp.ui.backmonkey;

import com.itislevel.lyl.base.RxPresenter;
import com.itislevel.lyl.common.RxUtil;
import com.itislevel.lyl.mvp.model.DataManager;
import com.itislevel.lyl.mvp.model.bean.FanloginBean;
import com.itislevel.lyl.mvp.model.http.response.LYLResponse;

import javax.inject.Inject;

import io.reactivex.observers.ResourceObserver;

/**
 * 作者： XS
 * 邮箱：1363826037@qq.com
 * 描述:
 * 创建时间:  2018\7\3 0003 09:09
 */
public class FanxianLoginPresenter extends RxPresenter<FanxianLoginContract.View>implements FanxianLoginContract.Presenter {
    private DataManager mDataManager;
    @Inject
    public  FanxianLoginPresenter(DataManager dataManager)
    {
        this.mDataManager = dataManager;
    }

    @Override
    public void merchantlogin(String message) {
        mDataManager.merchantlogin(message)
                .compose(RxUtil.rxSchedulerObservableHelper())
                .compose(RxUtil.handlerLYLResult())
                .subscribeWith(new ResourceObserver<FanloginBean>() {
                    @Override
                    public void onNext(FanloginBean bean) {
                            mView.merchantlogin(bean);
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
}
