package com.itislevel.lyl.mvp.model.http.api;



import com.itislevel.lyl.adapter.MeiZiMultipleAdapter;
import com.itislevel.lyl.mvp.model.bean.MeiZiBean;
import com.itislevel.lyl.mvp.model.bean.MeiZiMultipleBean;
import com.itislevel.lyl.mvp.model.http.response.GankResponse;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * **********************
 * 功 能:干货--接口
 * 创建人:itisi
 * 邮  箱:itisivip@qq.com
 * 创建时间:2017/7/7 16:21
 * 修改人:itisi
 * 修改时间: 2017/7/7 16:21
 * 修改内容:itisi
 * *********************
 */

public interface GankApi {
    String HOST = "http://gank.io/api/";
    /**
     * 妹纸列表
     * http://gank.io/api/data/福利/10/1
     */
    @GET("data/福利/{num}/{page}")
    Observable<GankResponse<List<MeiZiBean>>> getMeiZiList(@Path("num") int num, @Path("page") int page);

    /**
     * 妹纸列表
     * http://gank.io/api/data/福利/10/1
     */
    @GET("data/福利/{num}/{page}")
    Observable<GankResponse<List<MeiZiMultipleBean>>> getMeiZiListMult(@Path("num") int num, @Path("page") int page);

}
