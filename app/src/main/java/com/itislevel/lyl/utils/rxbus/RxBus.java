package com.itislevel.lyl.utils.rxbus;

import com.itislevel.lyl.utils.rxbus.annotation.Subscribe;
import com.itislevel.lyl.utils.rxbus.annotation.UseRxBus;
import com.itislevel.lyl.utils.rxbus.event.EventThread;
import com.itislevel.lyl.utils.rxbus.pojo.RxMessage;
import com.itislevel.lyl.utils.rxbus.util.RxSchedulerHelper;
import com.itislevel.lyl.utils.update.DownloadProgress;
import com.orhanobut.logger.Logger;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

/**
 * **********************
 * 功 能:事件总线
 * 提供 订阅 发布功能
 * 创建人:itisi
 * 邮  箱:itisivip@qq.com
 * 创建时间:2017/7/6 14:29
 * 修改人:itisi
 * 修改时间: 2017/7/6 14:29
 * 修改内容:itisi
 * *********************
 */

public class RxBus {
    //事件标记
    public static final int TAG_DEFAULT = -10000;
    public static final int TAG_UPDATE = -10010;
    public static final int TAG_CREATE = -10020;
    public static final int DFIND_TAG_UPDATE = -10021;
    public static final int TAG_OTHER = -10030;
    public static final int TAG_ERROR = -10040;
    public static final int TAG_CHANGE = -10050;

    private static RxBus instance;
    /**
     * tagClass
     */
    private static Map<Class, Integer> tag4Class = new HashMap<>();
    private Subject bus;
    /**
     * 根据object 生成唯一 id
     */
    private Integer tag = -1000;
    /**
     * 存放订阅者信息
     */
    private Map<Object, CompositeDisposable> subscriptions = new HashMap<>();

    private RxBus() {
        bus = PublishSubject.create().toSerialized();
    }

    public static RxBus getInstance() {
        if (instance == null) {
            synchronized (RxBus.class) {
                if (instance == null) {
                    instance = new RxBus();
                }
            }
        }
        return instance;
    }

    //    -------------------发送消息----------------------------
    public void post(@NonNull Object object) {
        post(TAG_DEFAULT, object);
    }

    public void post(@NonNull int tag, @NonNull Object object) {
        bus.onNext(new RxMessage(tag, object));
    }
//    =======================似有方法===============================

    private Observable<Object> toObservable() {
        return toObservable(Object.class);
    }

    private <T> Observable<T> toObservable(Class<T> eventType) {
        return toObservable(TAG_DEFAULT, eventType);
    }

    private <T> Observable<T> toObservable(final int tag, Class<T> eventType) {
        return bus.ofType(RxMessage.class)
                .filter(new Predicate<RxMessage>() {
                    @Override
                    public boolean test(@NonNull RxMessage msg) throws Exception {
                        return msg.tag == tag;
                    }
                })
                .map(new Function<RxMessage, Object>() {
                    @Override
                    public Object apply(@NonNull RxMessage message) throws Exception {
                        return message.object;
                    }
                })
                .cast(eventType);
    }

    //    =======================似有方法===============================
    public <T> Disposable toDefaultObservable(Class<T> eventType, Consumer<T> consumer) {
        return bus.ofType(eventType).compose(RxSchedulerHelper.io_main()).subscribe();
    }

    public Observable<DownloadProgress> getDownloadObservable() {
        return getInstance().toObservable().ofType(DownloadProgress.class).observeOn(AndroidSchedulers.mainThread());
    }

    public void init(@NonNull final Object object) {
        Flowable.just(object)
                .map(new Function<Object, UseRxBus>() {
                    @Override
                    public UseRxBus apply(@NonNull Object o) throws Exception {
                        return o.getClass().getAnnotation(UseRxBus.class);
                    }
                })
                .filter(new Predicate<UseRxBus>() {
                    @Override
                    public boolean test(@NonNull UseRxBus oo) throws Exception {
                        return oo != null;
                    }
                })
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(@NonNull Object oo2) throws Exception {
                        addTag4Class(object.getClass());
                        register(object);
                    }

                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
//                        throwable.printStackTrace();
                        Logger.e(throwable, "2订阅事件,初始化方法出错,也不算错,只是调用了rxbus.init方法,但没有加注解而已@UseRxBus ");
                    }
                });

    }

    private void register(@NonNull final Object subscriber) {
        Flowable.just(subscriber)
                .filter(new Predicate<Object>() {
                    @Override
                    public boolean test(@NonNull Object o) throws Exception {
                        return subscriptions.get(o) == null;
                    }
                })
                .flatMap(new Function<Object, Publisher<Method>>() {
                    @Override
                    public Publisher<Method> apply(@NonNull Object o) throws Exception {
                        return Flowable.fromArray(o.getClass().getDeclaredMethods());
                    }
                })
                .map(new Function<Method, Method>() {
                    @Override
                    public Method apply(@NonNull Method method) throws Exception {
                        method.setAccessible(true);
                        return method;
                    }
                })
                .filter(new Predicate<Method>() {
                    @Override
                    public boolean test(@NonNull Method method) throws Exception {
                        return method.isAnnotationPresent(Subscribe.class);
                    }
                })
                .subscribe(new Consumer<Method>() {
                    @Override
                    public void accept(@NonNull Method method) throws Exception {
                        addSubscription(method, subscriber);
                    }
                });
    }

    /**
     * 添加订阅--追加集合
     *
     * @param method
     * @param subscriber
     */
    private void addSubscription(final Method method, final Object subscriber) {

        Class[] parameterType = method.getParameterTypes();
        Class cla = Object.class;
        if (parameterType.length > 1) {
            cla = parameterType[0];
        }
        //获取注解 有什么用?
        Subscribe sub = method.getAnnotation(Subscribe.class);
        Disposable disposable = toObservable(getTag(subscriber.getClass(), sub.tag()), cla)
                .observeOn(EventThread.getScheuler(sub.thread()))
                .subscribe(new Consumer() {
                    @Override
                    public void accept(@NonNull Object o) throws Exception {
                        method.invoke(subscriber, o);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
//                        throwable.printStackTrace();
                        Logger.e(throwable, "订阅事件异常");
                    }
                });
        putSubscription(subscriber, disposable);
    }

    private void putSubscription(Object subscriber, Disposable disposable) {
        CompositeDisposable subs = subscriptions.get(subscriber);
        if (subs == null) {
            subs = new CompositeDisposable();
            subs.add(disposable);
            subscriptions.put(subscriber, subs);
        }
    }

    private void addTag4Class(Class clazz) {
        tag4Class.put(clazz, tag);
        tag--;
    }

    public int getTag(Class cla, int value) {
        return tag4Class.get(cla).intValue() + value;
    }

    public void unRegister(final Object subscriber) {
        Flowable.just(subscriber)
                .filter(new Predicate<Object>() {
                    @Override
                    public boolean test(@NonNull Object o) throws Exception {
                        return o != null;
                    }
                })
                .map(new Function<Object, CompositeDisposable>() {
                    @Override
                    public CompositeDisposable apply(@NonNull Object o) throws Exception {
                        return subscriptions.get(o);
                    }
                })
                .filter(new Predicate<CompositeDisposable>() {
                    @Override
                    public boolean test(@NonNull CompositeDisposable compositeDisposable) throws Exception {
                        return compositeDisposable != null;
                    }
                })
                .subscribeWith(new Subscriber<CompositeDisposable>() {
                    @Override
                    public void onSubscribe(Subscription s) {
                    }

                    @Override
                    public void onNext(CompositeDisposable compositeDisposable) {
                        compositeDisposable.dispose();
                        subscriptions.remove(subscriber);
                    }

                    @Override
                    public void onError(Throwable t) {
                    }

                    @Override
                    public void onComplete() {
                    }
                });

    }
}
