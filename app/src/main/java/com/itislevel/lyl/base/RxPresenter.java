package com.itislevel.lyl.base;



import com.itislevel.lyl.common.MyRxBus;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * **********************
 * 功 能:基于Rx的Presenter封装,控制订阅的生命周期
 * 创建人:itisi
 * 邮  箱:itisivip@qq.com
 * 创建时间:2017/7/6 10:21
 * 修改人:itisi
 * 修改时间: 2017/7/6 10:21
 * 修改内容:itisi
 * *********************
 */

public class RxPresenter<T extends BaseView> implements BasePresenter<T> {
    protected T mView;
    protected CompositeDisposable mCompositeDisposable;

    /**
     * 托管 订阅者
     * @param disposable
     */
    protected void addSubscribe(Disposable disposable) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(disposable);
    }

    protected <U> void addRxBusSubscribe(Class<U> eventType, Consumer<U> act){
        if (mCompositeDisposable==null){
            mCompositeDisposable=new CompositeDisposable();
        }
        mCompositeDisposable.add(MyRxBus.getInstance().toDefaultFlowable(eventType, act));

    }

    /**
     * 清空订阅者 不应该是删除当前订阅者?
     */
    protected void unSubscribe() {
        if (mCompositeDisposable != null) {
            mCompositeDisposable.clear();
        }
    }

    /**
     * 附加View
     * @param view
     */
    @Override
    public void attachView(T view) {
        mView = view;
    }

    /**
     * 移除view 解除RX
     */
    @Override
    public void detachView() {
        mView=null;
        unSubscribe();
    }
}
