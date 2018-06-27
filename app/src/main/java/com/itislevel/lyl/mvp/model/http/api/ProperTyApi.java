package com.itislevel.lyl.mvp.model.http.api;

import com.itislevel.lyl.adapter.LiveAddressBean;
import com.itislevel.lyl.mvp.model.bean.BillsBean;
import com.itislevel.lyl.mvp.model.bean.CollectionListBean;
import com.itislevel.lyl.mvp.model.bean.ComSearchBean;
import com.itislevel.lyl.mvp.model.bean.ComplaintTypeBean;
import com.itislevel.lyl.mvp.model.bean.HistoricalBean;
import com.itislevel.lyl.mvp.model.bean.HomeDetailBean;
import com.itislevel.lyl.mvp.model.bean.PayLuBean;
import com.itislevel.lyl.mvp.model.bean.ProperCommentList;
import com.itislevel.lyl.mvp.model.bean.PropertLoginBean;
import com.itislevel.lyl.mvp.model.bean.PropertyBillBean;
import com.itislevel.lyl.mvp.model.bean.PropertyDetailBean;
import com.itislevel.lyl.mvp.model.bean.PropretyLiveBean;
import com.itislevel.lyl.mvp.model.bean.PropretyNoticeBean;
import com.itislevel.lyl.mvp.model.bean.RepairCityListBean;
import com.itislevel.lyl.mvp.model.bean.RepairListBean;
import com.itislevel.lyl.mvp.model.bean.RepairTypeListBean;
import com.itislevel.lyl.mvp.model.bean.SeleBean;
import com.itislevel.lyl.mvp.model.bean.VillageNameBean;
import com.itislevel.lyl.mvp.model.http.response.LYLResponse;
import com.itislevel.lyl.mvp.model.http.response.LYLResponse_PIN;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
/**
 * Created by Administrator on 2018\5\31 0031.
 */

public interface ProperTyApi {
    String HOST="http://app.yobangyo.com/owner/";// http://app.yobangyo.com/owner/           //http://119.27.169.152:6064/user/
    //物业模块
    @POST("loginEstates")
    @FormUrlEncoded
    Observable<LYLResponse<PropertLoginBean>> loginEstates(@Field("action")String action);//物业的登录

    @POST("noticeEstates")
    @FormUrlEncoded
    Observable<LYLResponse<PropretyNoticeBean>> noticeEstates(@Field("action")String action);//物业通知

    @POST("findadvertlist")
    @FormUrlEncoded
    Observable<LYLResponse<PropretyLiveBean>> propretyLive(@Field("action")String action);//物业广告

    @POST("findVillagename")
    @FormUrlEncoded
    Observable<LYLResponse<List<VillageNameBean>>> findVillagename(@Field("action")String action);//小区

    @POST("findLiveaddress")
    @FormUrlEncoded
    Observable<LYLResponse<List<LiveAddressBean>>> findLiveaddress(@Field("action")String action);//单元
    @POST("findBills")
    @FormUrlEncoded
    Observable<LYLResponse<List<PropertyBillBean>>> propertyBill(@Field("action")String action);//查询账单

    @POST("ownerBillPayOrder")
    @FormUrlEncoded
    Observable<LYLResponse<String>> propertyaddOwn(@Field("action")String action);//订单生成

    @POST("findBillsMonth")
    @FormUrlEncoded
    Observable<LYLResponse_PIN<String>> findBillsMonth(@Field("action")String action);//更改缴费方式（月份）

    @POST("personalNews")
    @FormUrlEncoded
    Observable<LYLResponse<List<HomeDetailBean>>> personalNews(@Field("action") String action);//房屋的具体信息


    @POST("complaintType")
    @FormUrlEncoded
    Observable<LYLResponse<List<ComplaintTypeBean>>> complaintType(@Field("action") String action);//获取投诉的类型

    @POST("addComplaint")
    @FormUrlEncoded
    Observable<LYLResponse<String>> addComplaint(@Field("action") String action);//提交投诉


    @POST("findComplaintList")
    @FormUrlEncoded
    Observable<LYLResponse<ComSearchBean>> findComplaintList(@Field("action") String action);//投诉搜索


    @POST("detaComplaintList")
    @FormUrlEncoded
    Observable<LYLResponse<List<PropertyDetailBean>>> detaComplaintList(@Field("action") String action);//详情

    @POST("estatesPayList")
    @FormUrlEncoded
    Observable<LYLResponse<List<PayLuBean>>> estatesPayList(@Field("action") String action);//缴费记录

    //维修工人模块
    @POST("maintenanceList")
    @FormUrlEncoded
    Observable<LYLResponse<RepairListBean>> maintenanceList(@Field("action") String action);//维修工人列表

    @POST("queryarealist")
    @FormUrlEncoded
    Observable<LYLResponse<List<RepairCityListBean>>> queryarealist(@Field("action") String action);//查询区县的列表数据

    @POST("queryrepairallcatelist")
    @FormUrlEncoded
    Observable<LYLResponse<List<RepairCityListBean>>> queryrepairallcatelist(@Field("action") String action);//查询维修类别列表


    @POST("commentEstatesList")
    @FormUrlEncoded
    Observable<LYLResponse<ProperCommentList>> commentEstatesList(@Field("action") String action);//查询评论的列表


    @POST("addCommentEstates")
    @FormUrlEncoded
    Observable<LYLResponse<String>> addCommentEstates(@Field("action") String action);//添加评论

    @POST("addCollectMaintenance")
    @FormUrlEncoded
    Observable<LYLResponse<String>> addCollectMaintenance(@Field("action") String action);//点赞收藏

    @POST("seleCommentConunt ")
    @FormUrlEncoded
    Observable<LYLResponse<SeleBean>> seleCommentConunt (@Field("action") String action);//点赞收藏

    @POST("collectMaintenanceList")
    @FormUrlEncoded
    Observable<LYLResponse<CollectionListBean>> collectMaintenanceList (@Field("action") String action);//收藏列表

    @POST("deleMaintenanceList")
    @FormUrlEncoded
    Observable<LYLResponse<String>> deleMaintenanceList (@Field("action") String action);//删除功能

    @POST("looknumcount")
    @FormUrlEncoded
    Observable<LYLResponse<String>> prolooknumcount (@Field("action") String action);//浏览量接口


    @POST("querybillrecord")
    @FormUrlEncoded
    Observable<LYLResponse<HistoricalBean>> querybillrecord (@Field("action") String action);//历史账单
}
