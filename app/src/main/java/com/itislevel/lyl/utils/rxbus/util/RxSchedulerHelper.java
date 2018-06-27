package com.itislevel.lyl.utils.rxbus.util;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.schedulers.Schedulers;

/**
 * **********************
 * 功 能:RxJava 线程调度帮助类
 * 默认生成线程是io 线程 消费线程是 main 线程
 * 创建人:itisi
 * 邮  箱:itisivip@qq.com
 * 创建时间:2017/7/6 14:32
 * 修改人:itisi
 * 修改时间: 2017/7/6 14:32
 * 修改内容:itisi
 * *********************
 */

public class RxSchedulerHelper {
    public static <T>ObservableTransformer<T,T>io_main(){
        return new ObservableTransformer<T,T>(){
            @Override
            public ObservableSource<T> apply(@NonNull Observable<T> upstream) {
                return upstream.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());

            }
        };
    }
}
