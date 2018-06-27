package com.itislevel.lyl.mvp.ui.myteam;


import com.itislevel.lyl.base.RxPresenter;
import com.itislevel.lyl.common.RxUtil;
import com.itislevel.lyl.mvp.model.DataManager;
import com.itislevel.lyl.mvp.model.bean.MeiZiBean;
import com.itislevel.lyl.mvp.model.bean.MyTeamBean;
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
public class MyTeamPresenter extends RxPresenter<MyTeamContract.View> implements MyTeamContract.Presenter {

    private DataManager mDataManager;

    @Inject
    public MyTeamPresenter(DataManager dataManager) {
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
                        mView.loadData(meiZiBeen);
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
    public void teamProblemList(String action) {
        mDataManager.teamProblemList(action)
                .compose(RxUtil.<LYLResponse<MyTeamBean>>rxSchedulerObservableHelper())
                .compose(RxUtil.<MyTeamBean>handlerLYLResult())
                .subscribeWith(new ResourceObserver<MyTeamBean>() {
                    @Override
                    public void onNext(@NonNull MyTeamBean teamBean  ) {
                        mView.teamProblemList(teamBean);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Logger.e("错误信息:" + e.getMessage());
                        mView.stateError(e);
                    }

                    @Override
                    public void onComplete() {
                        Logger.i("complete");
                    }
                });
    }

    @Override
    public void teamReplay(String action) {
        mDataManager.teamReplay(action)
                .compose(RxUtil.<LYLResponse<String>>rxSchedulerObservableHelper())
                .compose(RxUtil.<String>handlerLYLResult())
                .subscribeWith(new ResourceObserver<String>() {
                    @Override
                    public void onNext(@NonNull String message  ) {
                        mView.teamReplay(message);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Logger.e("错误信息:" + e.getMessage());
                        mView.stateError(e);
                    }

                    @Override
                    public void onComplete() {
                        Logger.i("complete");
                    }
                });
    }
}
