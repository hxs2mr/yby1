package com.itislevel.lyl.di.module;


import com.itislevel.lyl.BuildConfig;
import com.itislevel.lyl.app.Constants;
import com.itislevel.lyl.di.qualifier.GankUrl;
import com.itislevel.lyl.di.qualifier.LYLUrl;
import com.itislevel.lyl.di.qualifier.MyUrl;
import com.itislevel.lyl.di.qualifier.PROPERTYUrl;
import com.itislevel.lyl.mvp.model.http.api.GankApi;
import com.itislevel.lyl.mvp.model.http.api.LYLApi;
import com.itislevel.lyl.mvp.model.http.api.MyApi;
import com.itislevel.lyl.mvp.model.http.api.ProperTyApi;
import com.itislevel.lyl.utils.SystemUtil;
import com.orhanobut.logger.Logger;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * **********************
 * 功 能:网络请求module
 * 创建人:itisi
 * 邮  箱:itisivip@qq.com
 * 创建时间:2017/7/5 17:19
 * 修改人:itisi
 * 修改时间: 2017/7/5 17:19
 * 修改内容:itisi
 * *********************
 */
@Module
public class HttpModule {
    @Singleton
    @Provides
    Retrofit.Builder provideRetrofitBuilder() {
        return new Retrofit.Builder();
    }

    @Singleton
    @Provides
    OkHttpClient.Builder provideOkHttpBuilder() {
        return new OkHttpClient.Builder();
    }

    @Singleton
    @Provides
    OkHttpClient provideClient(OkHttpClient.Builder builder) {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Logger.i("request"+new Date()+":"+message);
            }
        });
        if (BuildConfig.DEBUG){//调试模式 --感觉永远都是false
            //调试模式代码
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
            //调试模式代码
        }else{
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.NONE);
        }

        builder.addInterceptor(httpLoggingInterceptor);


        File cacheFile = new File(Constants.PATH_SDCARD);
        Cache cache = new Cache(cacheFile, 1024 * 1024 * 58);
        Interceptor interceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                if (!SystemUtil.isNetworkConnected()) {
                    request = request.newBuilder()
                            .cacheControl(CacheControl.FORCE_CACHE)
                            .build();
                }
                Response response = chain.proceed(request);
                if (SystemUtil.isNetworkConnected()) {
                    int maxAge = 0;
                    // 有网络时, 不缓存, 最大保存时长为0
                    response.newBuilder()
                            .header("Cache-Control", "public, max-age=" + maxAge)
                            .removeHeader("Pragma")
                            .build();

                } else {
                    // 无网络时，设置缓存为4周
                    int maxStale = 60 * 60 * 24 * 28;
                    response.newBuilder()
                            .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                            .removeHeader("Pragma")
                            .build();
                }

//                Logger.i("response:"+response);
                return response;
            }
        };
        //设置统一的请求头部参数
//        builder.addInterceptor(apikey);
        //设置缓存
        builder.addNetworkInterceptor(interceptor);
        builder.addInterceptor(interceptor);
        builder.cache(cache);
        //设置超时
        builder.connectTimeout(10, TimeUnit.SECONDS);
        builder.readTimeout(20, TimeUnit.SECONDS);
        builder.writeTimeout(20, TimeUnit.SECONDS);
        //错误重连
        builder.retryOnConnectionFailure(true);

        return builder.build();

    }


    private Retrofit createRetrofit(Retrofit.Builder builder, OkHttpClient client, String host) {
        return builder
                .baseUrl(host)
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                ;

    }

    // api 接口对象

    //测试接口
    @Singleton
    @Provides
    @GankUrl
    Retrofit provideGankRetrofit(Retrofit.Builder builder, OkHttpClient client) {
        return createRetrofit(builder, client, GankApi.HOST);
    }

    @Singleton
    @Provides
    GankApi provideGankApiService(@GankUrl Retrofit retrofit) {
        return retrofit.create(GankApi.class);
    }

    // 备用
    @Singleton
    @Provides
    @MyUrl
    Retrofit provideMyRetrofit(Retrofit.Builder builder, OkHttpClient client) {
        return createRetrofit(builder, client, MyApi.HOST);
    }

    @Singleton
    @Provides
    MyApi provideMyApiService(@MyUrl Retrofit retrofit) {
        return retrofit.create(MyApi.class);
    }

    // 老友乐 接口 新增 其他接口的时候 需要注意 @LYLUrl 和返回值
    @Singleton
    @Provides
    @LYLUrl
    Retrofit provideLYLRetrofit(Retrofit.Builder builder, OkHttpClient client) {
        return createRetrofit(builder, client, LYLApi.HOST);
    }
    @Singleton
    @Provides
    LYLApi provideLYLApiService(@LYLUrl Retrofit retrofit) {
        return retrofit.create(LYLApi.class);
    }

    //物业接口
    @Singleton
    @Provides
    @PROPERTYUrl
    Retrofit providePropertyRetrofit(Retrofit.Builder builder, OkHttpClient client) {
        return createRetrofit(builder, client, ProperTyApi.HOST);
    }
    //物业接口
    @Singleton
    @Provides
    ProperTyApi providePropertyService(@PROPERTYUrl Retrofit retrofit) {
        return   retrofit.create(ProperTyApi.class);
    }
}
