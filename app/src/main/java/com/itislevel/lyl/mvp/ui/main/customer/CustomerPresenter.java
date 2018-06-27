package com.itislevel.lyl.mvp.ui.main.customer;

import com.itislevel.lyl.base.RxPresenter;
import com.itislevel.lyl.common.RxUtil;
import com.itislevel.lyl.mvp.model.DataManager;
import com.itislevel.lyl.mvp.model.bean.CFTabBean;
import com.itislevel.lyl.mvp.model.bean.MeiZiBean;
import com.itislevel.lyl.mvp.model.http.response.GankResponse;
import com.orhanobut.logger.Logger;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;
import io.reactivex.observers.ResourceObserver;

/**
 * desc:功能说明必写
 * user:itisi
 * date:2017/11/21.10:20
 * path:com.itislevel.lyl.mvp.ui.main.customer.CustomerPresenter
 **/
public class CustomerPresenter extends RxPresenter<CustomerContract.View> implements CustomerContract.Presenter{

    private DataManager mDataManager;

    @Inject
    public CustomerPresenter(DataManager dataManager) {
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
    public void loadtable(String msg) {//加载标题
            mDataManager.seleinfocate(msg)
                    .compose(RxUtil.rxSchedulerObservableHelper())
                    .compose(RxUtil.handlerLYLResult())
                    .subscribeWith(new ResourceObserver<List<CFTabBean>>() {
                        @Override
                        public void onNext(List<CFTabBean> list) {
                            mView.loadtable(list);
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
