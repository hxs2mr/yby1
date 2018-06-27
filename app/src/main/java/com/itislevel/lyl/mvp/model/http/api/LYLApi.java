package com.itislevel.lyl.mvp.model.http.api;

import com.itislevel.lyl.adapter.LiveAddressBean;
import com.itislevel.lyl.mvp.model.bean.AddressBean;
import com.itislevel.lyl.mvp.model.bean.AliPayBean;
import com.itislevel.lyl.mvp.model.bean.AppUpdate;
import com.itislevel.lyl.mvp.model.bean.BillsBean;
import com.itislevel.lyl.mvp.model.bean.BlessAddBean;
import com.itislevel.lyl.mvp.model.bean.BlessAddLikeBean;
import com.itislevel.lyl.mvp.model.bean.BlessCartListBean;
import com.itislevel.lyl.mvp.model.bean.BlessCommentBean;
import com.itislevel.lyl.mvp.model.bean.BlessDetailGiftListBean;
import com.itislevel.lyl.mvp.model.bean.BlessGiftBean;
import com.itislevel.lyl.mvp.model.bean.BlessListBean;
import com.itislevel.lyl.mvp.model.bean.BlessOrderBean;
import com.itislevel.lyl.mvp.model.bean.BlessReceiveGiftBean;
import com.itislevel.lyl.mvp.model.bean.BlessReceiveYuBean;
import com.itislevel.lyl.mvp.model.bean.BlessSendGiftBean;
import com.itislevel.lyl.mvp.model.bean.CFChildBean;
import com.itislevel.lyl.mvp.model.bean.CFPinBean;
import com.itislevel.lyl.mvp.model.bean.CFTabBean;
import com.itislevel.lyl.mvp.model.bean.CartUpdateBean;
import com.itislevel.lyl.mvp.model.bean.ComSearchBean;
import com.itislevel.lyl.mvp.model.bean.ComplaintTypeBean;
import com.itislevel.lyl.mvp.model.bean.DynamicCommnetAddBean;
import com.itislevel.lyl.mvp.model.bean.DynamicPersonBean;
import com.itislevel.lyl.mvp.model.bean.DynimacLinkBean;
import com.itislevel.lyl.mvp.model.bean.FamilyBlessListBean;
import com.itislevel.lyl.mvp.model.bean.FamilyBlessListRecevieBean;
import com.itislevel.lyl.mvp.model.bean.FamilyFollowListBean;
import com.itislevel.lyl.mvp.model.bean.FamilyGiftListBean;
import com.itislevel.lyl.mvp.model.bean.FamilyListBean;
import com.itislevel.lyl.mvp.model.bean.FamilyPersonListBean;
import com.itislevel.lyl.mvp.model.bean.FamilyPhotoFrameBean;
import com.itislevel.lyl.mvp.model.bean.FamilyQueryFramBackBean;
import com.itislevel.lyl.mvp.model.bean.FamilyReceiveGiftBean;
import com.itislevel.lyl.mvp.model.bean.FamilySacrificeTypeBean;
import com.itislevel.lyl.mvp.model.bean.FamilySendGiftRecordBean;
import com.itislevel.lyl.mvp.model.bean.FamilyTonListBean;
import com.itislevel.lyl.mvp.model.bean.FamilyUsualLanguageBean;
import com.itislevel.lyl.mvp.model.bean.FileUploadBean;
import com.itislevel.lyl.mvp.model.bean.FindistBean;
import com.itislevel.lyl.mvp.model.bean.FollowListBean;
import com.itislevel.lyl.mvp.model.bean.FunsharingAddBean;
import com.itislevel.lyl.mvp.model.bean.FunsharingCommnetAddBean;
import com.itislevel.lyl.mvp.model.bean.FunsharingLikeBean;
import com.itislevel.lyl.mvp.model.bean.FunsharingListBean;
import com.itislevel.lyl.mvp.model.bean.FunsharingMyBean;
import com.itislevel.lyl.mvp.model.bean.GiftBean;
import com.itislevel.lyl.mvp.model.bean.HappyBlessUsualLanguageBean;
import com.itislevel.lyl.mvp.model.bean.HomeBean;
import com.itislevel.lyl.mvp.model.bean.HomeDetailBean;
import com.itislevel.lyl.mvp.model.bean.HouseKeepBean;
import com.itislevel.lyl.mvp.model.bean.LetterBean;
import com.itislevel.lyl.mvp.model.bean.MessageBean;
import com.itislevel.lyl.mvp.model.bean.MyGiftBean;
import com.itislevel.lyl.mvp.model.bean.MyReceiveAddrBean;
import com.itislevel.lyl.mvp.model.bean.MyTeamBean;
import com.itislevel.lyl.mvp.model.bean.PersonalCommunBean;
import com.itislevel.lyl.mvp.model.bean.PlaceBean;
import com.itislevel.lyl.mvp.model.bean.PropertLoginBean;
import com.itislevel.lyl.mvp.model.bean.PropertyBillBean;
import com.itislevel.lyl.mvp.model.bean.PropertyQueryInfo;
import com.itislevel.lyl.mvp.model.bean.PropertyQueryInfoBean;
import com.itislevel.lyl.mvp.model.bean.PropertyRecordBean;
import com.itislevel.lyl.mvp.model.bean.PropertyUpdateStatusBean;
import com.itislevel.lyl.mvp.model.bean.PropretyLiveBean;
import com.itislevel.lyl.mvp.model.bean.PropretyNoticeBean;
import com.itislevel.lyl.mvp.model.bean.RegistBean;
import com.itislevel.lyl.mvp.model.bean.SetOwnerPayMonth;
import com.itislevel.lyl.mvp.model.bean.SpecialGiftByIdBean;
import com.itislevel.lyl.mvp.model.bean.SpecialGiftListBean;
import com.itislevel.lyl.mvp.model.bean.SpecialOrderCompleteBean;
import com.itislevel.lyl.mvp.model.bean.SpecialOrderDetailBean;
import com.itislevel.lyl.mvp.model.bean.SpecialReceiveAddressBean;
import com.itislevel.lyl.mvp.model.bean.SpecialReturnBean;
import com.itislevel.lyl.mvp.model.bean.SpecialTuiKuanDetailBean;
import com.itislevel.lyl.mvp.model.bean.SpecialTuiKuanUpdateBean;
import com.itislevel.lyl.mvp.model.bean.SpecialTypeBean;
import com.itislevel.lyl.mvp.model.bean.TeamListBean;
import com.itislevel.lyl.mvp.model.bean.TeamStatusBean;
import com.itislevel.lyl.mvp.model.bean.TeamTypeBean;
import com.itislevel.lyl.mvp.model.bean.TonCityListBean;
import com.itislevel.lyl.mvp.model.bean.TroubleAddBean;
import com.itislevel.lyl.mvp.model.bean.TroubleAdviserMyBean;
import com.itislevel.lyl.mvp.model.bean.TroubleCommentAdd;
import com.itislevel.lyl.mvp.model.bean.TroubleListBean;
import com.itislevel.lyl.mvp.model.bean.TroubleTypeBean;
import com.itislevel.lyl.mvp.model.bean.UserHeaderNickInfo;
import com.itislevel.lyl.mvp.model.bean.UserInfoBean;
import com.itislevel.lyl.mvp.model.bean.VillageNameBean;
import com.itislevel.lyl.mvp.model.bean.WeiXinPayTestBean;
import com.itislevel.lyl.mvp.model.http.response.LYLResponse;
import com.itislevel.lyl.mvp.model.http.response.LYLResponse_PIN;
import com.itislevel.lyl.mvp.model.http.response.LYLResponse_Two;


import java.util.List;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * desc:基础api
 * user:itisi
 * date:2017/11/27.12:01
 * path:com.itislevel.lyl.mvp.model.http.api.LYLApi
 **/
public interface LYLApi {
//    String HOST = "http://192.168.1.108:8080/lyh_app_interface/user/";
//    String HOST = "http://192.168.1.222:8080/lyh_app_interface/user/";
//String HOST = "http://192.168.1.108:28080/lyh_app_interface/user/";
//    String HOST = "http://192.168.1.104:8888/user/";
    String HOST="http://app.yobangyo.com/user/";//http://app.yobangyo.com/user/           //http://119.27.169.152:6064/user/ //http://192.168.1.119:8080/yby_app_interface/user/

//    String HOST_SHARE = "http://192.168.1.115:8080/lyh_app_interface/";
    String HOST_SHARE = "http://app.yobangyo.com/";//http://app.yobangyo.com/

    //测试
    /**
     * 妹纸列表
     * http://gank.io/api/data/福利/10/1
     * @POST("data/福利/{num}/{page}")
     */

    /******************************************************************* ,************************
     *
     * 用户中心 我的
     *
     *******************************************************************************************/

    /**
     * 登陆
     * token usernum 有就传值，无就传null，必传
     * name password randcode
     *
     * @param action
     * @return
     */
    @POST("login")
    @FormUrlEncoded
    Observable<LYLResponse<UserInfoBean>> login(@Field("action") String action);

    /**
     * 注册
     * token usernum 有就传值，无就传null，必传
     * username phone password randcode recommendphone
     *
     * @param action
     * @return
     */
    @POST("regist")
    @FormUrlEncoded
    Observable<LYLResponse<RegistBean>> register(@Field("action") String action);

    /**
     * 图片验证码
     * token usernum 有就传值，无就传null，必传
     * name 姓名或者电话
     *
     * @param action
     * @return
     */
    @POST("genCaptcha")
    @FormUrlEncoded
    Observable<LYLResponse<String>> getValidateCode(@Field("action") String action);

    /**
     * 短信验证码
     * token usernum 有就传值，无就传null，必传
     * phone
     *
     * @param action
     * @return
     */
    @POST("randcode")
    @FormUrlEncoded
    Observable<LYLResponse<String>> getSSMCode(@Field("action") String action);

    //完善个人信息
    @POST("personal")
    @FormUrlEncoded
    Observable<LYLResponse<String>> userPerfectPersonal(@Field("action") String action);

    //修改密码
    @POST("modify")
    @FormUrlEncoded
    Observable<LYLResponse<String>> userModifyPassword(@Field("action") String action);

    @POST("modifyImg")
    @FormUrlEncoded
    Observable<LYLResponse<String>> userModifyHeader(@Field("action") String action);

    @POST("resetPassword")
    @FormUrlEncoded
    Observable<LYLResponse<String>> userForgetPasswrord(@Field("action") String action);


    //修改昵称
    @POST("nickname")
    @FormUrlEncoded
    Observable<LYLResponse<String>> userModifyNickname(@Field("action") String action);

    //修改电话
    @POST("updatePhone")
    @FormUrlEncoded
    Observable<LYLResponse<String>> userUpdatePhone(@Field("action") String action);

    @POST("addFeedback")
    @FormUrlEncoded
    Observable<LYLResponse<String>> userAddFeedback(@Field("action") String action);


    //我的地址
    @POST("findRecAddress")
    @FormUrlEncoded
    Observable<LYLResponse<MyReceiveAddrBean>> userFindRecAddress(@Field("action") String action);

    //新增-修改我的地址
    @POST("updateRecAddress")
    @FormUrlEncoded
    Observable<LYLResponse<String>> userUpdateRecAddress(@Field("action") String action);

    //我的礼品卷 新增礼品卷 注册的时候调用
    @POST("gift")
    @FormUrlEncoded
    Observable<LYLResponse<String>> userGiftAdd(@Field("action") String action);

    //我的礼品卷 列表
    @POST("listgift")
    @FormUrlEncoded
    Observable<LYLResponse<MyGiftBean>> userGiftList(@Field("action") String action);

    @POST("selectImgNickname")
    @FormUrlEncoded
    Observable<LYLResponse<List<UserHeaderNickInfo>>> userInfoById(@Field("action") String action);



    /*******************************************************************************************
     *
     * 乐趣分享
     *
     *******************************************************************************************/

    //分享发布

    /**
     * 分享-添加评论
     * token usernum 有就传值，无就传null，必传
     * shareid comment userid name content imge 图片二者至少有一个不传，无传null
     * provinceid provincename cityid cityname userid username
     *
     * @param action
     * @return
     */
    @POST("shareAdd")
    @FormUrlEncoded
    Observable<LYLResponse<FunsharingAddBean>> shareAdd(@Field("action") String action);

    //省市县

    /**
     * 省
     *
     * @param action
     * @return
     */
    @POST("province")
    @FormUrlEncoded
    Observable<LYLResponse<List<AddressBean>>> province(@Field("action") String action);

    /**
     * 市
     * id--省的id
     *
     * @param action
     * @return
     */
    @POST("city")
    @FormUrlEncoded
    Observable<LYLResponse<List<AddressBean>>> city(@Field("action") String action);

    /**
     * 县
     * id 市的id
     *
     * @param action
     * @return
     */
    @POST("county")
    @FormUrlEncoded
    Observable<LYLResponse<List<AddressBean>>> county(@Field("action") String action);


    /**
     * 分享列表--首页
     * token usernum 有就传值，无就传null，必传
     * token usernum  pageSize pageIndex
     *
     * @param action
     * @return
     */
    @POST("shareDetails")
    @FormUrlEncoded
    Observable<LYLResponse<FunsharingListBean>> shareList(@Field("action") String action);

    /**
     * 我的分享列表
     * token usernum 有就传值，无就传null，必传
     * userid pageSize pageIndex
     *
     * @param action
     * @return
     */
    @POST("shareList")
    @FormUrlEncoded
    Observable<LYLResponse<FunsharingMyBean>> shareListMy(@Field("action") String action);

    /**
     * 删除我的分享
     * token usernum 有就传值，无就传null，必传
     * id
     *
     * @param action
     * @return
     */
    @POST("shareDelect")
    @FormUrlEncoded
    Observable<LYLResponse<String>> shareDelect(@Field("action") String action);

    /**
     * 分享-添加评论
     * token usernum 有就传值，无就传null，必传
     * shareid comment userid name content imge 图片二者至少有一个不传，无传null
     * provinceid provincename cityid cityname userid username touserid
     *
     * @param action
     * @return
     */
    @POST("commentShareAdd")
    @FormUrlEncoded
    Observable<LYLResponse<FunsharingCommnetAddBean>> commentShareAdd(@Field("action") String action);

    /**
     * 分享-评论删除
     *
     * @param action
     * @return
     */
    @POST("shareCommentDel")
    @FormUrlEncoded
    Observable<LYLResponse<String>> commentShareDel(@Field("action") String action);

    /**
     * 点赞 取消点赞
     * id userid
     *
     * @param action
     * @return
     */
    @POST("spotFabulous")
    @FormUrlEncoded
    Observable<LYLResponse<FunsharingLikeBean>> shareLikeOrCancel(@Field("action") String action);


    /*******************************************************************************************
     *
     * 烦恼解答
     *
     *******************************************************************************************/


    @POST("renovationDetails")
    @FormUrlEncoded
    Observable<LYLResponse<TroubleListBean>> troubleList(@Field("action") String action);

    @POST("addRenovation")
    @FormUrlEncoded
    Observable<LYLResponse<TroubleAddBean>> troubleAdd(@Field("action") String action);

    @POST("myRenovationList")
    @FormUrlEncoded
    Observable<LYLResponse<TroubleListBean>> troubleMyList(@Field("action") String action);

    @POST("delMyRenovation")
    @FormUrlEncoded
    Observable<LYLResponse<String>> troubleDel(@Field("action") String action);

    //烦恼解答 评论 回复
    @POST("addCommentReply")
    @FormUrlEncoded
    Observable<LYLResponse<TroubleCommentAdd>> troubleCommentReplay(@Field("action") String action);

    @POST("delRenovationComment")
    @FormUrlEncoded
    Observable<LYLResponse<String>> troubleCommentDel(@Field("action") String action);

    @POST("secondCate")
    @FormUrlEncoded
    Observable<LYLResponse<TroubleTypeBean>> troubleType(@Field("action") String action);


    /*******************************************************************************************
     *
     * 生活交费
     *
     *******************************************************************************************/

    //1.	物业缴费信息查询
    @POST("queryownerplacelist")
    @FormUrlEncoded
    Observable<LYLResponse<PropertyQueryInfoBean>> propertyQuery(@Field("action") String action);

    //2.	业主缴费账单查询
    @POST("queryownerbillrecord")
    @FormUrlEncoded
    Observable<LYLResponse<PropertyRecordBean>> propertyQueryOrder(@Field("action") String action);

    //3.	设置缴费类型
    @POST("editownerpaytype")
    @FormUrlEncoded
    Observable<LYLResponse<PropertyUpdateStatusBean>> propertyUpdatePayType(@Field("action") String action);
    //4.	修改账单状态
    @POST("editownerbillstatus")
    @FormUrlEncoded
    Observable<LYLResponse<PropertyUpdateStatusBean>> propertyUpdateOrderState(@Field("action") String action);

    //4.	5.	组装物业订单支付信息
    @POST("buildownerorder")
    @FormUrlEncoded
    Observable<LYLResponse<String>> propertyGenerateOrder(@Field("action") String action);

    //4.	5.	组装物业订单支付信息
    @POST("queryestateinfo")
    @FormUrlEncoded
    Observable<LYLResponse<PropertyQueryInfo>> propertyQueryByUserid(@Field("action") String action);


    @POST("queryownerplacelist")
    @FormUrlEncoded
    Observable<LYLResponse<PropertyQueryInfo>> propertyQueryList(@Field("action") String action);

    @POST("queryestateinfo")
    @FormUrlEncoded
    Observable<LYLResponse<PropertyQueryInfoBean>> propertyQueryByUserid1(@Field("action") String action);


    @POST("setOwnerPayMonths")
    @FormUrlEncoded
    Observable<LYLResponse<SetOwnerPayMonth>> propertySetOwnerPayMonth(@Field("action") String action);



    /*******************************************************************************************
     *
     * 家政服务
     *
     *******************************************************************************************/


    @POST("findHouse")
    @FormUrlEncoded
    Observable<LYLResponse<HouseKeepBean>> findHouse(@Field("action") String action);

    /*******************************************************************************************
     *
     * 顾问团队
     *
     *******************************************************************************************/


    //团队注册
    @POST("addAdviserReg")
    @FormUrlEncoded
    Observable<LYLResponse<String>> teamRegister(@Field("action") String action);

    //团队的审核状态
    @POST("findIscheck")
    @FormUrlEncoded
    Observable<LYLResponse<TeamStatusBean>> teamStatus(@Field("action") String action);

    //团队列表
    @POST("selectAdviserReg")
    @FormUrlEncoded
    Observable<LYLResponse<TeamListBean>> teamList(@Field("action") String action);

    //咨询人数
    @POST("updateseeknum")
    @FormUrlEncoded
    Observable<LYLResponse<String>> teamViewCount(@Field("action") String action);

    //顾问提交问题
    @POST("addAdviser")
    @FormUrlEncoded
    Observable<LYLResponse<String>> teamProblemAdd(@Field("action") String action);

    //顾问详情 顾问看到的列表 问题列表 个人中心里面
    @POST("adviserDetailsList")
    @FormUrlEncoded
    Observable<LYLResponse<MyTeamBean>> teamProblemList(@Field("action") String action);

    //团队评论 回复
    @POST("addCommentReplay")
    @FormUrlEncoded
    Observable<LYLResponse<String>> teamReplay(@Field("action") String action);

    //我的提问列表-付费咨询的问题
    @POST("myDetailsList")
    @FormUrlEncoded
    Observable<LYLResponse<TroubleAdviserMyBean>> teamMyProblemList(@Field("action") String action);

    //团队分类
    @POST("checkCate")
    @FormUrlEncoded
    Observable<LYLResponse<TeamTypeBean>> teamType(@Field("action") String action);

    /*******************************************************************************************
     *
     * 特产礼品
     *
     *******************************************************************************************/
    //礼品分类
    @POST("cate")
    @FormUrlEncoded
    Observable<LYLResponse<SpecialTypeBean>> specialType(@Field("action") String action);

    //礼品列表 根据分类id
    @POST("checkRihua")
    @FormUrlEncoded
    Observable<LYLResponse<SpecialGiftListBean>> specialList(@Field("action") String action);

    //单个商品详情
    @POST("checkSingle")
    @FormUrlEncoded
    Observable<LYLResponse<SpecialGiftByIdBean>> specialById(@Field("action") String action);

    //立即下单
    @POST("promptlyOrder")
    @FormUrlEncoded
    Observable<LYLResponse<String>> specialImmediatelyOrder(@Field("action") String action);

    //购物车下单
    @POST("shopOrder")
    @FormUrlEncoded
    Observable<LYLResponse<String>> specialShopOrder(@Field("action") String action);

    //收获地址
    @POST("checkDress")
    @FormUrlEncoded
    Observable<LYLResponse<SpecialReceiveAddressBean>> specialReceiveAddress(@Field("action") String action);

    //订单详情
    @POST("orderDetailsGift")
    @FormUrlEncoded
    Observable<LYLResponse<SpecialOrderDetailBean>> specialOrderDetail(@Field("action") String action);

    //退款详情
    @POST("selectRefundDetails")
    @FormUrlEncoded
    Observable<LYLResponse<SpecialTuiKuanDetailBean>> specialTuiKuanDetail(@Field("action") String action);

    //退货退款---撤销退款
    @POST("delRefund")
    @FormUrlEncoded
    Observable<LYLResponse<String>> specialTuiKuan(@Field("action") String action);

    //退货退款---退款详情查询（修改申请）
    @POST("selectUpdateDetails")
    @FormUrlEncoded
    Observable<LYLResponse<SpecialTuiKuanUpdateBean>> specialTuiKuanUpdate(@Field("action") String action);

    //退款退货  -退款详情修改（修改申请）
    @POST("updateApply")
    @FormUrlEncoded
    Observable<LYLResponse<String>> specialTuiKuanUpdate2(@Field("action") String action);

    //我的订单（查询已完成订单详情）
    @POST("selectSuccessDetails")
    @FormUrlEncoded
    Observable<LYLResponse<SpecialOrderCompleteBean>> specialOrderComplete(@Field("action") String action);

    //我的订单（待付款订单-付款）
    @POST("pendingPay")
    @FormUrlEncoded
    Observable<LYLResponse<String>> specialOrderGoPay(@Field("action") String action);


    //我的订单（待付款—取消订单）
    @POST("updateCancelOrder")
    @FormUrlEncoded
    Observable<LYLResponse<String>> specialOrderCancel(@Field("action") String action);

    //我的订单（待发货订单—申请退款）
    @POST("refundPay")
    @FormUrlEncoded
    Observable<LYLResponse<String>> specialShenQingTuiKuan(@Field("action") String action);

    //我的订单（待付款订单-付款）
    @POST("updateGoodsReceipt")
    @FormUrlEncoded
    Observable<LYLResponse<String>> specialOrderConfirm(@Field("action") String action);

    //我的订单（退货退款）
    @POST("returnGoods")
    @FormUrlEncoded
    Observable<LYLResponse<SpecialReturnBean>> specialReturnList(@Field("action") String action);

/*
*
* 动态模块 关注列表
* */
    @POST("dynamicDetails")
    @FormUrlEncoded
    Observable<LYLResponse<FollowListBean>> dynamic_follow(@Field("action") String action);

    @POST("followDetailsList")
    @FormUrlEncoded
    Observable<LYLResponse<FindistBean>> dynamic_find(@Field("action") String action);

    @POST("dynamicDetails")
    @FormUrlEncoded
    Observable<LYLResponse<TonCityListBean>> dynamic_ton(@Field("action") String action);


    @POST("dynamicFabulous")//点赞
    @FormUrlEncoded
    Observable<LYLResponse<DynimacLinkBean>> dynamicdianzan(@Field("action") String action);

    @POST("follow")//关注
    @FormUrlEncoded
    Observable<LYLResponse<String>>  dynamicfollow(@Field("action")String action);

    @POST("addDynamicComment")//添加评论
    @FormUrlEncoded
    Observable<LYLResponse<DynamicCommnetAddBean>> addDynamicComment(@Field("action") String action);

    @POST("delDynamicComment")//删除评论
    @FormUrlEncoded
    Observable<LYLResponse<String>> delDynamicComment(@Field("action")String action);


    @POST("addDynamic")
    @FormUrlEncoded
    Observable<LYLResponse<String>> addDynamic(@Field("action")String action);//发布动态


    @POST("delDynamicInfo")
    @FormUrlEncoded
    Observable<LYLResponse<String>> delDynamicInfo(@Field("action")String action);//删除动态

    @POST("dynamicList")
    @FormUrlEncoded
    Observable<LYLResponse<DynamicPersonBean>>  dynamicList(@Field("action")String action);//个人中心的动态列表


    @POST("personalCommun")
    @FormUrlEncoded
    Observable<LYLResponse<PersonalCommunBean>> personalCommun(@Field("action")String action);
    /*******************************************************************************************
     *
     * 亲情祭祀
     *
     *******************************************************************************************/

    //浏览量
    @POST("looknumLetter")
    @FormUrlEncoded
    Observable<LYLResponse<String>> looknumLetter(@Field("action")String action);
    //祭事信
    @POST("addletter")
    @FormUrlEncoded
    Observable<LYLResponse<String>> addletter(@Field("action")String action);

    //祭事信列表
    @POST("selectletter")
    @FormUrlEncoded
    Observable<LYLResponse<LetterBean>> selectletter(@Field("action")String action);//到这了  大哥


    //新建祭祀
    @POST("addFete")
    @FormUrlEncoded
    Observable<LYLResponse<String>> familyAdd(@Field("action") String action);

    //我的祭祀列表
    @POST("findFeteMyList")
    @FormUrlEncoded
    Observable<LYLResponse<FamilyListBean>> familyListMy(@Field("action") String action);

    //祭祀列表
    @POST("feteBranchList")//发现
    @FormUrlEncoded
    Observable<LYLResponse<FamilyListBean>> familyList(@Field("action") String action);

    //祭祀列表
    @POST("feteBranchList")//关注
    @FormUrlEncoded
    Observable<LYLResponse<FamilyFollowListBean>> familyList_follow(@Field("action") String action);


    //祭祀列表
    @POST("feteBranchList")//同城
    @FormUrlEncoded
    Observable<LYLResponse<FamilyTonListBean>> familyList_ton(@Field("action") String action);

    //个人中心我的祭事
    @POST("findMyFeteList")
    @FormUrlEncoded
    Observable<LYLResponse<FamilyPersonListBean>> findMyFeteList(@Field("action")String action);


    //祭祀祝福语-祭语
    @POST("findFeteWishes")
    @FormUrlEncoded
    Observable<LYLResponse<FamilyBlessListBean>> familyBlessList(@Field("action") String action);

    //祭祀 收到的礼物-祭品--总的记录
    @POST("feteGiftRecord")
    @FormUrlEncoded
    Observable<LYLResponse<FamilyReceiveGiftBean>> familyReceiveGift(@Field("action") String action);

    //详情页面的收到的礼物列表
    @POST("findFeteGift")
    @FormUrlEncoded
    Observable<LYLResponse<FamilyReceiveGiftBean>> familyReceiveGiftById(@Field("action") String action);

    //礼物列表
    @POST("findGiftList")
    @FormUrlEncoded
    Observable<LYLResponse<FamilyGiftListBean>> familyListGift(@Field("action") String action);

    //我的赠送记录--礼物记录
    @POST("findSendGift")
    @FormUrlEncoded
    Observable<LYLResponse<FamilySendGiftRecordBean>> familySendGift(@Field("action") String
                                                                             action);


    //收到的祝福语-祭语
    @POST("feteWishesRecord")
    @FormUrlEncoded
    Observable<LYLResponse<FamilyBlessListRecevieBean>> familyReceiveBless(@Field("action")
                                                                                   String action);

    //修改浏览量
    @POST("looknumFeteCount")
    @FormUrlEncoded
    Observable<LYLResponse<String>> familyViewCount(@Field("action") String action);

    //分类
    @POST("cate")
    @FormUrlEncoded
    Observable<LYLResponse<FamilySacrificeTypeBean>> familyCate(@Field("action") String action);

    //常用语
    @POST("findUsualLanguage")
    @FormUrlEncoded
    Observable<LYLResponse<FamilyUsualLanguageBean>> familyUsualLanguage(@Field("action") String
                                                                                 action);

    //查看相框
    @POST("selectChooseGift")
    @FormUrlEncoded
    Observable<LYLResponse<FamilyPhotoFrameBean>> familyPhotoFrame(@Field("action") String action);

    //祭祀 新建祝福语 祭语
    @POST("addFeteWishes")
    @FormUrlEncoded
    Observable<LYLResponse<String>> familyBlessAdd(@Field("action") String action);

    //搜索房间
    @POST("searchRoom")
    @FormUrlEncoded
    Observable<LYLResponse<FamilyListBean>> familySearch(@Field("action") String action);

    //购买成功存入相框/背景信息
    @POST("saveframebgtoredis")
    @FormUrlEncoded
    Observable<LYLResponse<String>> familySaveFPhotoFrameAndBack(@Field("action") String action);

    // 查询相框、背景购买订单信息验证
    @POST("queryredisframebg")
    @FormUrlEncoded
    Observable<LYLResponse<FamilyQueryFramBackBean>> familyQueryFrameAndBack(@Field("action")
                                                                                     String action);

    @POST("delFete")
    @FormUrlEncoded
    Observable<LYLResponse<String>> familyDelete(@Field("action")String action);

    /*******************************************************************************************
     *
     * 喜事祝福
     *
     *******************************************************************************************/


    @POST("addHappy")
    @FormUrlEncoded
    Observable<LYLResponse<String>> happAdd(@Field("action") String action);

    //我的喜事列表
    @POST("happyList")
    @FormUrlEncoded
    Observable<LYLResponse<BlessListBean>> happyListMy(@Field("action") String action);

    //跟新喜事祝福浏览量
    @POST("looknumcount")
    @FormUrlEncoded
    Observable<LYLResponse<String>> happyViewCount(@Field("action") String action);

    //删除我的祝福
    @POST("delHappyInfo")
    @FormUrlEncoded
    Observable<LYLResponse<String>> happyDel(@Field("action") String action);

    //祝福列表详情
    @POST("happyDetails")
    @FormUrlEncoded
    Observable<LYLResponse<BlessListBean>> happyList(@Field("action") String action);

    //新增评论
    @POST("addHappyComment")
    @FormUrlEncoded
    Observable<LYLResponse<BlessCommentBean>> happyComment(@Field("action") String action);

    //删除评论
    @POST("delHappyComment")
    @FormUrlEncoded
    Observable<LYLResponse<String>> happyCommentDel(@Field("action") String action);

    //祝福点赞
    @POST("happyFabulous")
    @FormUrlEncoded
    Observable<LYLResponse<BlessAddLikeBean>> happyLike(@Field("action") String action);

    //我要祝福
    @POST("addblessing")
    @FormUrlEncoded
    Observable<LYLResponse<BlessAddBean>> happyBlessAdd(@Field("action") String action);

    //收到的祝福列表
    @POST("checkblessing")
    @FormUrlEncoded
    Observable<LYLResponse<BlessReceiveYuBean>> happyBlessReceivedList(@Field("action") String
                                                                               action);

    //祝福常用语
    @POST("getHappylanguage")
    @FormUrlEncoded
    Observable<LYLResponse<HappyBlessUsualLanguageBean>> happyUsualLanguage(@Field("action")
                                                                                    String action);

    //礼品列表
    @POST("giftsList")
    @FormUrlEncoded
    Observable<LYLResponse<BlessGiftBean>> happyGiftList(@Field("action") String action);

    //礼品列表
    @POST("giftsList")
    @FormUrlEncoded
    Observable<LYLResponse<List<GiftBean>>> happyGiftList1(@Field("action") String action);

    //我收到的礼品记录
    @POST("receivedgift")
    @FormUrlEncoded
    Observable<LYLResponse<BlessReceiveGiftBean>> happyGiftReceiveListMy(@Field("action") String action);

    //我的送礼记录
    @POST("myGiftsList")
    @FormUrlEncoded
    Observable<LYLResponse<BlessSendGiftBean>> happyGiftSendListMy(@Field("action") String action);

    //喜事祝福 购物车


    //添加购物车
    @POST("addShopCart")
    @FormUrlEncoded
    Observable<LYLResponse<String>> happyCartAdd(@Field("action") String action);

    //购物车列表
    @POST("getShopCartList")
    @FormUrlEncoded
    Observable<LYLResponse<BlessCartListBean>> happyCartList(@Field("action") String action);

    //修改购物车-商品数量
    @POST("updateShopCart")
    @FormUrlEncoded
    Observable<LYLResponse<CartUpdateBean>> happyCartUpdate(@Field("action") String action);

    //删除购物车
    @POST("delShopCart")
    @FormUrlEncoded
    Observable<LYLResponse<String>> happyCartDel(@Field("action") String action);

    //   家有喜事搜索
    @POST("searchHappy")
    @FormUrlEncoded
    Observable<LYLResponse<BlessListBean>> happySearch(@Field("action") String action);

    //  喜事详情 礼品列表
    @POST("giftCheckList")
    @FormUrlEncoded
    Observable<LYLResponse<BlessDetailGiftListBean>> happyDetailsGiftList(@Field("action") String
                                                                                  action);

    /*******************************************************************************************
     *
     * 图片上传
     *
     *******************************************************************************************/

    // http://119.27.169.152:8030/img-server/uploadImgs?token=2017ybyimgserver
    //
    @POST("http://saveimg.yobangyo.com/img-server/uploadImgs?token=1522809067997")
    @Multipart
    Observable<LYLResponse<FileUploadBean>> uploadHeader(@Part MultipartBody.Part file); //上传文件(图片)

    /*******************************************************************************************
     *
     * 支付
     *
     *******************************************************************************************/

    //支付宝获取订单 此接口为测试接口 不用-下单
    @POST("alipayTest")
    @FormUrlEncoded
    Observable<LYLResponse<AliPayBean>> alipayTest(@Field("action") String action);

    //微信测试 -下单

    @POST("wximmediateorder")
    @FormUrlEncoded
    Observable<LYLResponse<WeiXinPayTestBean>> weixinPayTest(@Field("action") String action);

    //生成订单-下单
    @POST("buildOrder")
    @FormUrlEncoded
    Observable<LYLResponse<String>> happyOrderAdd(@Field("action") String action);


    //立即支付-下单
    @POST("immediateOrder")
    @FormUrlEncoded
    Observable<LYLResponse<String>> immediateOrder(@Field("action") String action);

    //根据订单号 模块名称 和支付方式 去生成订单
    @POST("alipayList")
    @FormUrlEncoded
    Observable<LYLResponse<BlessOrderBean>> alipayList(@Field("action") String action);


    //app更新
    @POST("appUpDate")
    @FormUrlEncoded
    Observable<LYLResponse<AppUpdate>> appupdate(@Field("action") String action);

    @POST("firstPage")
    @FormUrlEncoded
    Observable<LYLResponse<HomeBean>> firstPage(@Field("action") String action);

    @POST("place")
    @FormUrlEncoded
    Observable<LYLResponse<PlaceBean>> place(@Field("action") String action);


    //咨询模块
    @POST("seleinfocate")
    @FormUrlEncoded
    Observable<LYLResponse<List<CFTabBean>>>seleinfocate(@Field("action")String action);//tab的分类标题


    @POST("informationPageList")
    @FormUrlEncoded
    Observable<LYLResponse<CFChildBean>> cfchilflist(@Field("action")String action);

    @POST("updatepointnum")
    @FormUrlEncoded
    Observable<LYLResponse_Two<Integer>> updatepointnum(@Field("action")String action);

    @POST("looknumFlatcount")
    @FormUrlEncoded
    Observable<LYLResponse<String>> looknumFlatcount(@Field("action")String action);

    @POST("addFlatComment")
    @FormUrlEncoded
    Observable<LYLResponse_PIN<String>> addFlatComment(@Field("action")String action);//添加评论

    @POST("flatcommentlist")
    @FormUrlEncoded
    Observable<LYLResponse<CFPinBean>> cfcommentlist(@Field("action")String action);//添加评论

    @POST("commentFabulous")
    @FormUrlEncoded
    Observable<LYLResponse<String>>  cf_addzan(@Field("action")String action);//点赞

    @POST("delFlatComment")
    @FormUrlEncoded
    Observable<LYLResponse<String>>  delFlatComment(@Field("action")String action);//删除评论

    @POST("delefollow")
    @FormUrlEncoded
    Observable<LYLResponse<String>>  delefollow(@Field("action")String action);//取消关注

    @POST("myDyPushList")
    @FormUrlEncoded
    Observable<LYLResponse<MessageBean>>  myDyPushList(@Field("action")String action);//消息推送过来的数据


    @POST("clearMyDyPushList")
    @FormUrlEncoded
    Observable<LYLResponse<String>>  clearMyDyPushList(@Field("action")String action);//消息推送过来的数据
}
