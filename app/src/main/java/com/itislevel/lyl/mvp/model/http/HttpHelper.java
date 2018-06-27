package com.itislevel.lyl.mvp.model.http;

import com.itislevel.lyl.adapter.LiveAddressBean;
import com.itislevel.lyl.mvp.model.bean.*;
import com.itislevel.lyl.mvp.model.http.response.GankResponse;
import com.itislevel.lyl.mvp.model.http.response.LYLResponse;
import com.itislevel.lyl.mvp.model.http.response.LYLResponse_PIN;
import com.itislevel.lyl.mvp.model.http.response.LYLResponse_Two;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

/**
 * **********************
 * 功 能:定义所有获取数据的接口
 * 创建人:itisi
 * 邮  箱:itisivip@qq.com
 * 创建时间:2017/7/5 17:26
 * 修改人:itisi
 * 修改时间: 2017/7/5 17:26
 * 修改内容:itisi
 * *********************
 */
public interface HttpHelper {
    //测试api
    Observable<GankResponse<List<MeiZiBean>>> getMeiZiList(int num, int page);
    Observable<GankResponse<List<MeiZiMultipleBean>>> getMeiZiListMul(int num, int page);

    // 老友乐 api

    //注册登录 验证码
    Observable<LYLResponse<UserInfoBean>> login(String action);
    Observable<LYLResponse<HomeBean>> firstPage(@Field("action")String action);
    Observable<LYLResponse<PlaceBean>> place(@Field("action")String action);
    Observable<LYLResponse<RegistBean>> register(String action);
    Observable<LYLResponse<String>> getValidateCode(String action);
    Observable<LYLResponse<String>> getSSMCode(String action);

    Observable<LYLResponse<String>> userPerfectPersonal(String action);
    Observable<LYLResponse<String>> userModifyPassword(String action);
    Observable<LYLResponse<String>> userModifyNickname(String action);
    Observable<LYLResponse<String>> userModifyHeader(String action);
    Observable<LYLResponse<MyReceiveAddrBean>> userFindRecAddress(String action);
    Observable<LYLResponse<String>> userUpdateRecAddress(String action);
    Observable<LYLResponse<String>> userGiftAdd(String action);
    Observable<LYLResponse<MyGiftBean>> userGiftList(String action);

    Observable<LYLResponse<String>> userForgetPasswrord(@Field("action") String action);
    Observable<LYLResponse<String>> userUpdatePhone(@Field("action") String action);
    Observable<LYLResponse<String>> userAddFeedback(@Field("action") String action);
    Observable<LYLResponse<List<UserHeaderNickInfo>>> userInfoById(@Field("action") String action);


    //省市县
    Observable<LYLResponse<List<AddressBean>>> province(String action);
    Observable<LYLResponse<List<AddressBean>>> city(String action);
    Observable<LYLResponse<List<AddressBean>>> county(String action);


    //乐趣分享
    Observable<LYLResponse<FunsharingAddBean>> shareAdd(@Field("action") String action);
    Observable<LYLResponse<FunsharingListBean>> shareList(@Field("action") String action);
    Observable<LYLResponse<FunsharingMyBean>> shareListMy(@Field("action") String action);
    Observable<LYLResponse<String>> shareDelect(@Field("action") String action);
    Observable<LYLResponse<FunsharingCommnetAddBean>> commentShareAdd(@Field("action") String action);
    Observable<LYLResponse<String>> commentShareDel(@Field("action") String action);
    Observable<LYLResponse<FunsharingLikeBean>> shareLikeOrCancel(@Field("action") String action);


    //烦恼解答
    Observable<LYLResponse<TroubleListBean>> troubleList(@Field("action") String action);
    Observable<LYLResponse<TroubleAddBean>> troubleAdd(@Field("action") String action);
    Observable<LYLResponse<TroubleListBean>> troubleMyList(@Field("action") String action);

    Observable<LYLResponse<String>> troubleDel(@Field("action") String action);
    Observable<LYLResponse<TroubleCommentAdd>> troubleCommentReplay(@Field("action") String action);
    Observable<LYLResponse<String>> troubleCommentDel(@Field("action") String action);
    Observable<LYLResponse<TroubleTypeBean>> troubleType(@Field("action") String action);



    //生活缴费

    Observable<LYLResponse<PropertyQueryInfoBean>> propertyQuery(@Field("action") String action);
    Observable<LYLResponse<PropertyRecordBean>> propertyQueryOrder(@Field("action") String action);
    Observable<LYLResponse<PropertyUpdateStatusBean>> propertyUpdatePayType(@Field("action") String action);
    Observable<LYLResponse<PropertyUpdateStatusBean>> propertyUpdateOrderState(@Field("action") String action);
    Observable<LYLResponse<String>> propertyGenerateOrder(@Field("action") String action);
    Observable<LYLResponse<PropertyQueryInfo>> propertyQueryByUserid(@Field("action") String action);

    Observable<LYLResponse<PropertyQueryInfo>> propertyQueryList(@Field("action") String action);
    Observable<LYLResponse<PropertyQueryInfoBean>> propertyQueryByUserid1(@Field("action") String action);
    Observable<LYLResponse<SetOwnerPayMonth>> propertySetOwnerPayMonth(@Field("action") String action);

    //家政服务
    Observable<LYLResponse<HouseKeepBean>> findHouse(@Field("action") String action);


    //顾问团队
    Observable<LYLResponse<String>> teamRegister(@Field("action") String action);
    Observable<LYLResponse<TeamStatusBean>> teamStatus(@Field("action") String action);
    Observable<LYLResponse<TeamListBean>> teamList(@Field("action") String action);
    Observable<LYLResponse<String>> teamViewCount(@Field("action") String action);
    Observable<LYLResponse<String>> teamProblemAdd(@Field("action") String action);
    Observable<LYLResponse<MyTeamBean>> teamProblemList(@Field("action") String action);
    Observable<LYLResponse<TroubleAdviserMyBean>> teamMyProblemList(@Field("action") String action);
    Observable<LYLResponse<TeamTypeBean>> teamType(@Field("action") String action);
    Observable<LYLResponse<String>> teamReplay(@Field("action") String action);


    //礼品特产

    Observable<LYLResponse<SpecialTypeBean>> specialType(@Field("action") String action);
    Observable<LYLResponse<SpecialGiftListBean>> specialList(@Field("action") String action);
    Observable<LYLResponse<SpecialGiftByIdBean>> specialById(@Field("action") String action);
    Observable<LYLResponse<String>> specialImmediatelyOrder(@Field("action") String action);
    Observable<LYLResponse<String>> specialShopOrder(@Field("action") String action);
    Observable<LYLResponse<SpecialReceiveAddressBean>> specialReceiveAddress(@Field("action") String action);
    Observable<LYLResponse<SpecialOrderDetailBean>> specialOrderDetail(@Field("action") String action);
    Observable<LYLResponse<SpecialTuiKuanDetailBean>> specialTuiKuanDetail(@Field("action") String action);
    Observable<LYLResponse<String>> specialTuiKuan(@Field("action") String action);
    Observable<LYLResponse<SpecialTuiKuanUpdateBean>> specialTuiKuanUpdate(@Field("action") String action);
    Observable<LYLResponse<String>> specialTuiKuanUpdate2(@Field("action") String action);
    Observable<LYLResponse<SpecialOrderCompleteBean>> specialOrderComplete(@Field("action") String action);
    Observable<LYLResponse<String>> specialOrderGoPay(@Field("action") String action);
    Observable<LYLResponse<String>> specialOrderCancel(@Field("action") String action);
    Observable<LYLResponse<String>> specialShenQingTuiKuan(@Field("action") String action);
    Observable<LYLResponse<String>> specialOrderConfirm(@Field("action") String action);
    Observable<LYLResponse<SpecialReturnBean>> specialReturnList(@Field("action") String action);


    //动态
    Observable<LYLResponse<FollowListBean>> dynamic_follow(@Field("action") String action);

    Observable<LYLResponse<FindistBean>> dynamic_find(@Field("action") String action);

    Observable<LYLResponse<TonCityListBean>> dynamic_ton(@Field("action") String action);

     Observable<LYLResponse<DynimacLinkBean>> dynamicdianzan(@Field("action") String action);
    Observable<LYLResponse<String>>  dynamicfollow(@Field("action")String action);
    Observable<LYLResponse<DynamicCommnetAddBean>> addDynamicComment(@Field("action") String action);//点赞
    Observable<LYLResponse<String>> delDynamicComment(@Field("action")String action);

    Observable<LYLResponse<String>> addDynamic(@Field("action")String action);//发布动态
    Observable<LYLResponse<String>> delDynamicInfo(@Field("action")String action);//删除动态
    Observable<LYLResponse<DynamicPersonBean>>  dynamicList(@Field("action")String action);//个人中心的动态列表

    Observable<LYLResponse<PersonalCommunBean>> personalCommun(@Field("action")String action);

    //亲情祭祀
    Observable<LYLResponse<String>> looknumLetter(@Field("action")String action);

    Observable<LYLResponse<String>> familyAdd(@Field("action") String action);
    Observable<LYLResponse<FamilyListBean>> familyListMy(@Field("action") String action);

    Observable<LYLResponse<FamilyListBean>> familyList(@Field("action") String action);
    Observable<LYLResponse<FamilyFollowListBean>> familyList_follow(@Field("action") String action);
    Observable<LYLResponse<FamilyTonListBean>> familyList_ton(@Field("action") String action);

    Observable<LYLResponse<FamilyBlessListBean>> familyBlessList(@Field("action") String action);
    Observable<LYLResponse<FamilyReceiveGiftBean>> familyReceiveGift(@Field("action") String action);
    Observable<LYLResponse<FamilyGiftListBean>> familyListGift(@Field("action") String action);
    Observable<LYLResponse<FamilySendGiftRecordBean>> familySendGift(@Field("action") String action);
    Observable<LYLResponse<FamilyBlessListRecevieBean>> familyReceiveBless(@Field("action") String action);
    Observable<LYLResponse<String>> familyViewCount(@Field("action") String action);
    Observable<LYLResponse<FamilySacrificeTypeBean>> familyCate(@Field("action") String action);
    Observable<LYLResponse<FamilyUsualLanguageBean>> familyUsualLanguage(@Field("action") String action);
    Observable<LYLResponse<FamilyPhotoFrameBean>> familyPhotoFrame(@Field("action") String action);
    Observable<LYLResponse<String>> familyBlessAdd(@Field("action") String action);
    Observable<LYLResponse<FamilyListBean>> familySearch(@Field("action") String action);
    Observable<LYLResponse<FamilyReceiveGiftBean>> familyReceiveGiftById(@Field("action") String action);
    Observable<LYLResponse<String>> familyDelete(@Field("action")String action);

    Observable<LYLResponse<FamilyPersonListBean>> findMyFeteList(@Field("action")String action);//个人中心我的祭事

    //购买成功存入相框/背景信息
    Observable<LYLResponse<String>> familySaveFPhotoFrameAndBack(@Field("action") String action);

    // 查询相框、背景购买订单信息验证
    Observable<LYLResponse<FamilyQueryFramBackBean>> familyQueryFrameAndBack(@Field("action") String action);
    Observable<LYLResponse<String>> addletter(@Field("action")String action);
    Observable<LYLResponse<LetterBean>> selectletter(@Field("action")String action);

    //喜事祝福
    Observable<LYLResponse<String>> happAdd(@Field("action") String action);
    Observable<LYLResponse<BlessListBean>> happyListMy(@Field("action") String action);
    Observable<LYLResponse<String>> happyViewCount(@Field("action") String action);
    Observable<LYLResponse<String>> happyDel(@Field("action") String action);
    Observable<LYLResponse<BlessListBean>> happyList(@Field("action") String action);
    Observable<LYLResponse<BlessCommentBean>> happyComment(@Field("action") String action);
    Observable<LYLResponse<String>> happyCommentDel(@Field("action") String action);
    Observable<LYLResponse<BlessAddLikeBean>> happyLike(@Field("action") String action);
    Observable<LYLResponse<BlessAddBean>> happyBlessAdd(@Field("action") String action);
    Observable<LYLResponse<BlessReceiveYuBean>> happyBlessReceiveList(@Field("action") String action);
    Observable<LYLResponse<HappyBlessUsualLanguageBean>> happyUsualLanguage(@Field("action") String action);

    Observable<LYLResponse<BlessGiftBean>> happyGiftList(@Field("action") String action);
    Observable<LYLResponse<List<GiftBean>>> happyGiftList1(@Field("action") String action);

    Observable<LYLResponse<BlessReceiveGiftBean>> happyGiftReceiveListMy(@Field("action") String action);
    Observable<LYLResponse<BlessSendGiftBean>> happyGiftSendListMy(@Field("action") String action);

    Observable<LYLResponse<String>> happyCartAdd(@Field("action") String action);
    Observable<LYLResponse<BlessCartListBean>> happyCartList(@Field("action") String action);
    Observable<LYLResponse<CartUpdateBean>> happyCartUpdate(@Field("action") String action);
    Observable<LYLResponse<String>> happyCartDel(@Field("action") String action);
    Observable<LYLResponse<BlessListBean>> happySearch(@Field("action") String action);
    Observable<LYLResponse<BlessDetailGiftListBean>> happyDetailsGiftList(@Field("action") String action);


    //图片上传
    Observable<LYLResponse<FileUploadBean>> uploadHeader(@Part MultipartBody.Part file);


    //支付

    //此接口为测试接口
    Observable<LYLResponse<AliPayBean>> alipayTest(@Field("action") String action);
    Observable<LYLResponse<WeiXinPayTestBean>> weixinPayTest(@Field("action") String action);

//    Observable<LYLResponse<BlessOrderBean>> happyOrderAdd(@Field("action") String action);
//    Observable<LYLResponse<BlessOrderBean>> immediateOrder(@Field("action") String action);

        Observable<LYLResponse<String>> happyOrderAdd(@Field("action") String action);
    Observable<LYLResponse<String>> immediateOrder(@Field("action") String action);

    Observable<LYLResponse<BlessOrderBean>> alipayList(@Field("action") String action);

    Observable<LYLResponse<AppUpdate>> appupdate(@Field("action") String action);

    //质询模块
    Observable<LYLResponse<List<CFTabBean>>> seleinfocate(@Field("action")String action);//查询分类标题

    Observable<LYLResponse<CFChildBean>> cfchilflist(@Field("action")String action);

    Observable<LYLResponse_Two<Integer>> updatepointnum(@Field("action")String action);

    Observable<LYLResponse<String>> looknumFlatcount(@Field("action")String action);

    Observable<LYLResponse_PIN<String>> addFlatComment(@Field("action")String action);

    Observable<LYLResponse<CFPinBean>> cfcommentlist(@Field("action")String action);//添加评论

    Observable<LYLResponse<String>>  cf_addzan(@Field("action")String action);//点赞
    Observable<LYLResponse<String>>  delFlatComment(@Field("action")String action);//删除评论

    //物业模块
    Observable<LYLResponse<PropertLoginBean>> loginEstates(@Field("action")String action);//物业的登录
    Observable<LYLResponse<PropretyNoticeBean>> noticeEstates(@Field("action")String action);//物业通知

    Observable<LYLResponse<PropretyLiveBean>> propretyLive(@Field("action")String action);//物业广告
    Observable<LYLResponse<List<VillageNameBean>>> findVillagename(@Field("action")String action);//小区
    Observable<LYLResponse<List<LiveAddressBean>>> findLiveaddress(@Field("action")String action);//单元

    Observable<LYLResponse<List<PropertyBillBean>>> propertyBill(@Field("action")String action);//查询账单
    Observable<LYLResponse<String>> propertyaddOwn(@Field("action")String action);//订单生成

    Observable<LYLResponse_PIN<String>> findBillsMonth(@Field("action")String action);//更改缴费方式（月份）
    Observable<LYLResponse<List<HomeDetailBean>>> personalNews(@Field("action")String action);//房屋的具体信息

    Observable<LYLResponse<List<ComplaintTypeBean>>> complaintType(@Field("action") String action);//获取投诉的类型

    Observable<LYLResponse<String>> addComplaint(@Field("action") String action);//添加投诉

    Observable<LYLResponse<ComSearchBean>> findComplaintList(@Field("action") String action);//投诉搜索

    Observable<LYLResponse<List<PropertyDetailBean>>> detaComplaintList(@Field("action") String action);//详情

    Observable<LYLResponse<List<PayLuBean>>> estatesPayList(@Field("action") String action);//缴费记录
    Observable<LYLResponse<RepairListBean>> maintenanceList(@Field("action") String action);//维修工人列表

    Observable<LYLResponse<List<RepairCityListBean>>> queryarealist(@Field("action") String action);//查询区县的列表数据
    Observable<LYLResponse<List<RepairCityListBean>>> queryrepairallcatelist(@Field("action") String action);//查询维修类别列表

    Observable<LYLResponse<ProperCommentList>> commentEstatesList(@Field("action") String action);//查询评论的列表

    Observable<LYLResponse<String>> addCommentEstates(@Field("action") String action);//添加评论

    Observable<LYLResponse<String>> addCollectMaintenance(@Field("action") String action);//点赞收藏

    Observable<LYLResponse<SeleBean>> seleCommentConunt (@Field("action") String action);//查看分类

    Observable<LYLResponse<CollectionListBean>> collectMaintenanceList (@Field("action") String action);//收藏列表

    Observable<LYLResponse<String>> deleMaintenanceList (@Field("action") String action);//收藏列表

    Observable<LYLResponse<String>> prolooknumcount (@Field("action") String action);//浏览量接口

    Observable<LYLResponse<String>>  delefollow(@Field("action")String action);//取消关注

    Observable<LYLResponse<MessageBean>>  myDyPushList(@Field("action")String action);//消息推送过来的数据

    Observable<LYLResponse<HistoricalBean>> querybillrecord (@Field("action") String action);//历史账单

    Observable<LYLResponse<String>>  clearMyDyPushList(@Field("action")String action);//清楚推送过来的数据
}
