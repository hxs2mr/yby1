package com.itislevel.lyl.common;

import io.reactivex.Flowable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.processors.FlowableProcessor;
import io.reactivex.processors.PublishProcessor;

/**
 * **********************
 * 功 能:封装事件总线
 * 创建人:itisi
 * 邮  箱:itisivip@qq.com
 * 创建时间:2017/7/6 10:32
 * 修改人:itisi
 * 修改时间: 2017/7/6 10:32
 * 修改内容:itisi
 * *********************
 */
public class MyRxBus {

    private final FlowableProcessor<Object> bus;

    /**
     * 私有化 构造函数
     */
    private MyRxBus() {
        bus= PublishProcessor.create().toSerialized();
    }

    /**
     * 获取MyRxBus 实例
     * @return
     */
    public static MyRxBus getInstance(){
        return MyRxBusHolder.instance;
    }

    /**
     * 创建内部变量 ---不过为什么这样做?
     */
    private static class MyRxBusHolder{
        private static MyRxBus instance=new MyRxBus();
    }

    /**
     * 发送一个新的事件
     * @param object
     */
    public void post(Object object){
        bus.onNext(object);

    }

    /**
     * 根据传递的eventType类型返回特定的类型(eventType)的观察者
     * @param eventType
     * @param <T>
     * @return
     */
    public <T>Flowable<T>toFlowable(Class<T>eventType){
        return bus.ofType(eventType);
    }

    /**
     * 封装默认订阅
     * @param eventType
     * @param act
     * @param <T>
     * @return
     */
    public <T>Disposable toDefaultFlowable(Class<T>eventType, Consumer<T> act){
        return bus.ofType(eventType).compose(RxUtil.<T>rxSchedulerFlowableHelper()).subscribe(act);
    }

}
