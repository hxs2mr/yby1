package com.itislevel.lyl.mvp.ui.usermonkey.setting;

import com.itislevel.lyl.base.RxPresenter;
import com.itislevel.lyl.common.RxUtil;
import com.itislevel.lyl.mvp.model.DataManager;
import com.itislevel.lyl.mvp.model.bean.BankCardBean;
import com.itislevel.lyl.mvp.model.bean.BlankListBean;
import com.itislevel.lyl.mvp.model.bean.BlankNameBean;
import com.itislevel.lyl.mvp.model.http.response.LYLResponse;

import javax.inject.Inject;

import io.reactivex.observers.ResourceObserver;

/**
 * 作者： XS
 * 邮箱：1363826037@qq.com
 * 描述:
 * 创建时间:  2018\7\10 0010 11:08
 */
public class MonkeySettingPresenter extends RxPresenter<MonkeySettingContract.View>implements MonkeySettingContract.Presenter {
    private DataManager mDataManager;
    @Inject
    public  MonkeySettingPresenter(DataManager dataManager)
    {
        this.mDataManager = dataManager;
    }

    @Override
    public void queryBankNameByIdCard(String msg) {
            mDataManager.queryBankNameByIdCard(msg)
                    .compose(RxUtil.rxSchedulerObservableHelper())
                    .compose(RxUtil.handlerLYLResult())
                    .subscribeWith(new ResourceObserver<BlankNameBean>() {
                        @Override
                        public void onNext(BlankNameBean blankNameBean) {
                                mView.queryBankNameByIdCard(blankNameBean);
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
    public void queryBankBranchList(String msg) {
            mDataManager.queryBankBranchList(msg)
                    .compose(RxUtil.rxSchedulerObservableHelper())
                    .compose(RxUtil.handlerLYLResult())
                    .subscribeWith(new ResourceObserver<BlankListBean>() {
                        @Override
                        public void onNext(BlankListBean blankListBean) {
                                mView.queryBankBranchList(blankListBean);
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
    public void bankCardVerification(String msg) {
            mDataManager.bankCardVerification(msg)
                    .compose(RxUtil.rxSchedulerObservableHelper())
                    .compose(RxUtil.handlerLYLResult())
                    .subscribeWith(new ResourceObserver<BankCardBean>() {
                        @Override
                        public void onNext(BankCardBean s) {
                                mView.bankCardVerification(s);
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
    public void finishVerification(String data) {
        mDataManager.finishVerification(data)
                .compose(RxUtil.rxSchedulerObservableHelper())
                .compose(RxUtil.handlerLYLResult())
                .subscribeWith(new ResourceObserver<String>() {
                    @Override
                    public void onNext(String s) {
                            mView.finishVerification(s);
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
