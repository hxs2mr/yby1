package com.itislevel.lyl.mvp.model.http;


import com.itislevel.lyl.adapter.LiveAddressBean;
import com.itislevel.lyl.mvp.model.bean.*;
import com.itislevel.lyl.mvp.model.http.api.GankApi;
import com.itislevel.lyl.mvp.model.http.api.LYLApi;
import com.itislevel.lyl.mvp.model.http.api.MyApi;
import com.itislevel.lyl.mvp.model.http.api.ProperTyApi;
import com.itislevel.lyl.mvp.model.http.response.GankResponse;
import com.itislevel.lyl.mvp.model.http.response.LYLResponse;
import com.itislevel.lyl.mvp.model.http.response.LYLResponse_PIN;
import com.itislevel.lyl.mvp.model.http.response.LYLResponse_Two;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import okhttp3.MultipartBody;

/**
 * **********************
 * 功 能:请求实现类
 * 创建人:itisi
 * 邮  箱:itisivip@qq.com
 * 创建时间:2017/7/5 17:27
 * 修改人:itisi
 * 修改时间: 2017/7/5 17:27
 * 修改内容:itisi
 * *********************
 */

public class RetrofitHelper implements HttpHelper{

    private GankApi mGankApi;
    private ProperTyApi mMyApi;//物业模块的Api
    private LYLApi mLylApi;//APP除物业模块外的Api

    @Inject
    public RetrofitHelper(GankApi gankApi, ProperTyApi myApi, LYLApi lylApi) {
        mGankApi = gankApi;
        mMyApi = myApi;
        mLylApi = lylApi;
    }

    //测试数据
    @Override
    public Observable<GankResponse<List<MeiZiBean>>> getMeiZiList(int num, int page) {
        return mGankApi.getMeiZiList(num, page);
    }

    @Override
    public Observable<GankResponse<List<MeiZiMultipleBean>>> getMeiZiListMul(int num, int page) {
        return mGankApi.getMeiZiListMult(num, page);
    }

    //老友乐 接口
    @Override
    public Observable<LYLResponse<UserInfoBean>> login(String action) {
        return mLylApi.login(action);
    }

    //主界面接口
    @Override
    public Observable<LYLResponse<HomeBean>> firstPage(String action) {
        return mLylApi.firstPage(action);
    }

    //定位接口
    @Override
    public Observable<LYLResponse<PlaceBean>> place(String action) {
        return mLylApi.place(action);
    }

    @Override
    public Observable<LYLResponse<RegistBean>> register(String action) {
        return mLylApi.register(action);
    }

    @Override
    public Observable<LYLResponse<String>> getValidateCode(String action) {
        return mLylApi.getValidateCode(action);
    }

    @Override
    public Observable<LYLResponse<String>> getSSMCode(String action) {
        return mLylApi.getSSMCode(action);
    }

    @Override
    public Observable<LYLResponse<String>> userPerfectPersonal(String action) {
        return mLylApi.userPerfectPersonal(action);
    }

    @Override
    public Observable<LYLResponse<String>> userModifyPassword(String action) {
        return mLylApi.userModifyPassword(action);
    }

    @Override
    public Observable<LYLResponse<String>> userModifyNickname(String action) {
        return mLylApi.userModifyNickname(action);
    }

    @Override
    public Observable<LYLResponse<String>> userModifyHeader(String action) {
        return mLylApi.userModifyHeader(action);
    }

    @Override
    public Observable<LYLResponse<MyReceiveAddrBean>> userFindRecAddress(String action) {
        return mLylApi.userFindRecAddress(action);
    }

    @Override
    public Observable<LYLResponse<String>> userUpdateRecAddress(String action) {
        return mLylApi.userUpdateRecAddress(action);
    }

    @Override
    public Observable<LYLResponse<String>> userGiftAdd(String action) {
        return mLylApi.userGiftAdd(action);
    }

    @Override
    public Observable<LYLResponse<MyGiftBean>> userGiftList(String action) {
        return mLylApi.userGiftList(action);
    }

    @Override
    public Observable<LYLResponse<String>> userForgetPasswrord(String action) {
        return mLylApi.userForgetPasswrord(action);
    }

    @Override
    public Observable<LYLResponse<String>> userUpdatePhone(String action) {
        return mLylApi.userUpdatePhone(action);
    }

    @Override
    public Observable<LYLResponse<String>> userAddFeedback(String action) {
        return mLylApi.userAddFeedback(action);
    }

    @Override
    public Observable<LYLResponse<List<UserHeaderNickInfo>>> userInfoById(String action) {
        return mLylApi.userInfoById(action);
    }

    @Override
    public Observable<LYLResponse<List<AddressBean>>> province(String action) {
        return mLylApi.province(action);
    }

    @Override
    public Observable<LYLResponse<List<AddressBean>>> city(String action) {
        return mLylApi.city(action);
    }

    @Override
    public Observable<LYLResponse<List<AddressBean>>> county(String action) {
        return mLylApi.county(action);
    }

    @Override
    public Observable<LYLResponse<FunsharingAddBean>> shareAdd(String action) {
        return mLylApi.shareAdd(action);
    }

    @Override
    public Observable<LYLResponse<FunsharingListBean>> shareList(String action) {
        return mLylApi.shareList(action);
    }

    @Override
    public Observable<LYLResponse<FunsharingMyBean>> shareListMy(String action) {
        return mLylApi.shareListMy(action);
    }

    @Override
    public Observable<LYLResponse<String>> shareDelect(String action) {
        return mLylApi.shareDelect(action);
    }

    @Override
    public Observable<LYLResponse<FunsharingCommnetAddBean>> commentShareAdd(String action) {
        return mLylApi.commentShareAdd(action);
    }

    @Override
    public Observable<LYLResponse<String>> commentShareDel(String action) {
        return mLylApi.commentShareDel(action);
    }

    @Override
    public Observable<LYLResponse<FunsharingLikeBean>> shareLikeOrCancel(String action) {
        return mLylApi.shareLikeOrCancel(action);
    }

    @Override
    public Observable<LYLResponse<TroubleListBean>> troubleList(String action) {
        return mLylApi.troubleList(action);
    }

    @Override
    public Observable<LYLResponse<TroubleAddBean>> troubleAdd(String action) {
        return mLylApi.troubleAdd(action);
    }

    @Override
    public Observable<LYLResponse<TroubleListBean>> troubleMyList(String action) {
        return mLylApi.troubleMyList(action);
    }

    @Override
    public Observable<LYLResponse<String>> troubleDel(String action) {
        return mLylApi.troubleDel(action);
    }

    @Override
    public Observable<LYLResponse<TroubleCommentAdd>> troubleCommentReplay(String action) {
        return mLylApi.troubleCommentReplay(action);
    }

    @Override
    public Observable<LYLResponse<String>> troubleCommentDel(String action) {
        return mLylApi.troubleCommentDel(action);
    }

    @Override
    public Observable<LYLResponse<TroubleTypeBean>> troubleType(String action) {
        return mLylApi.troubleType(action);
    }

    @Override
    public Observable<LYLResponse<PropertyQueryInfoBean>> propertyQuery(String action) {
        return mLylApi.propertyQuery(action);
    }

    @Override
    public Observable<LYLResponse<PropertyRecordBean>> propertyQueryOrder(String action) {
        return mLylApi.propertyQueryOrder(action);
    }

    @Override
    public Observable<LYLResponse<PropertyUpdateStatusBean>> propertyUpdatePayType(String action) {
        return mLylApi.propertyUpdatePayType(action);
    }

    @Override
    public Observable<LYLResponse<PropertyUpdateStatusBean>> propertyUpdateOrderState(String action) {

        return mLylApi.propertyUpdateOrderState(action);
    }

    @Override
    public Observable<LYLResponse<String>> propertyGenerateOrder(String action) {
        return mLylApi.propertyGenerateOrder(action);
    }

    @Override
    public Observable<LYLResponse<PropertyQueryInfo>> propertyQueryByUserid(String action) {
        return mLylApi.propertyQueryByUserid(action);
    }

    @Override
    public Observable<LYLResponse<PropertyQueryInfo>> propertyQueryList(String action) {
        return mLylApi.propertyQueryList(action);
    }

    @Override
    public Observable<LYLResponse<PropertyQueryInfoBean>> propertyQueryByUserid1(String action) {
        return mLylApi.propertyQueryByUserid1(action);
    }

    @Override
    public Observable<LYLResponse<SetOwnerPayMonth>> propertySetOwnerPayMonth(String action) {
        return mLylApi.propertySetOwnerPayMonth(action);
    }

    @Override
    public Observable<LYLResponse<HouseKeepBean>> findHouse(String action) {
        return mLylApi.findHouse(action);
    }

    @Override
    public Observable<LYLResponse<String>> teamRegister(String action) {
        return mLylApi.teamRegister(action);
    }

    @Override
    public Observable<LYLResponse<TeamStatusBean>> teamStatus(String action) {
        return mLylApi.teamStatus(action);
    }

    @Override
    public Observable<LYLResponse<TeamListBean>> teamList(String action) {
        return mLylApi.teamList(action);
    }

    @Override
    public Observable<LYLResponse<String>> teamViewCount(String action) {
        return mLylApi.teamViewCount(action);
    }

    @Override
    public Observable<LYLResponse<String>> teamProblemAdd(String action) {
        return mLylApi.teamProblemAdd(action);
    }

    @Override
    public Observable<LYLResponse<MyTeamBean>> teamProblemList(String action) {
        return mLylApi.teamProblemList(action);
    }

    @Override
    public Observable<LYLResponse<TroubleAdviserMyBean>> teamMyProblemList(String action) {
        return mLylApi.teamMyProblemList(action);
    }

    @Override
    public Observable<LYLResponse<TeamTypeBean>> teamType(String action) {
        return mLylApi.teamType(action);
    }

    @Override
    public Observable<LYLResponse<String>> teamReplay(String action) {
        return mLylApi.teamReplay(action);
    }

    @Override
    public Observable<LYLResponse<SpecialTypeBean>> specialType(String action) {
        return mLylApi.specialType(action);
    }

    @Override
    public Observable<LYLResponse<SpecialGiftListBean>> specialList(String action) {
        return mLylApi.specialList(action);
    }

    @Override
    public Observable<LYLResponse<SpecialGiftByIdBean>> specialById(String action) {
        return mLylApi.specialById(action);
    }

    @Override
    public Observable<LYLResponse<String>> specialImmediatelyOrder(String action) {
        return mLylApi.specialImmediatelyOrder(action);
    }

    @Override
    public Observable<LYLResponse<String>> specialShopOrder(String action) {
        return mLylApi.specialShopOrder(action);
    }

    @Override
    public Observable<LYLResponse<SpecialReceiveAddressBean>> specialReceiveAddress(String action) {
        return mLylApi.specialReceiveAddress(action);
    }

    @Override
    public Observable<LYLResponse<SpecialOrderDetailBean>> specialOrderDetail(String action) {
        return mLylApi.specialOrderDetail(action);
    }

    @Override
    public Observable<LYLResponse<SpecialTuiKuanDetailBean>> specialTuiKuanDetail(String action) {
        return mLylApi.specialTuiKuanDetail(action);
    }

    @Override
    public Observable<LYLResponse<String>> specialTuiKuan(String action) {
        return mLylApi.specialTuiKuan(action);
    }

    @Override
    public Observable<LYLResponse<SpecialTuiKuanUpdateBean>> specialTuiKuanUpdate(String action) {
        return mLylApi.specialTuiKuanUpdate(action);
    }

    @Override
    public Observable<LYLResponse<String>> specialTuiKuanUpdate2(String action) {
        return mLylApi.specialTuiKuanUpdate2(action);
    }

    @Override
    public Observable<LYLResponse<SpecialOrderCompleteBean>> specialOrderComplete(String action) {
        return mLylApi.specialOrderComplete(action);
    }

    @Override
    public Observable<LYLResponse<String>> specialOrderGoPay(String action) {
        return mLylApi.specialOrderGoPay(action);
    }

    @Override
    public Observable<LYLResponse<String>> specialOrderCancel(String action) {
        return mLylApi.specialOrderCancel(action);
    }

    @Override
    public Observable<LYLResponse<String>> specialShenQingTuiKuan(String action) {
        return mLylApi.specialShenQingTuiKuan(action);
    }

    @Override
    public Observable<LYLResponse<String>> specialOrderConfirm(String action) {
        return mLylApi.specialOrderConfirm(action);
    }

    @Override
    public Observable<LYLResponse<SpecialReturnBean>> specialReturnList(String action) {
        return mLylApi.specialReturnList(action);
    }

    @Override
    public Observable<LYLResponse<FollowListBean>> dynamic_follow(String action) {
        return mLylApi.dynamic_follow(action);
    }

    @Override
    public Observable<LYLResponse<FindistBean>> dynamic_find(String action) {
        return mLylApi.dynamic_find(action);
    }

    @Override
    public Observable<LYLResponse<TonCityListBean>> dynamic_ton(String action) {
        return mLylApi.dynamic_ton(action);
    }

    @Override
    public Observable<LYLResponse<DynimacLinkBean>> dynamicdianzan(String action) {
        return mLylApi.dynamicdianzan(action);
    }

    @Override
    public Observable<LYLResponse<String>> dynamicfollow(String action) {
        return mLylApi.dynamicfollow(action);
    }

    @Override
    public Observable<LYLResponse<DynamicCommnetAddBean>> addDynamicComment(String action) {
        return mLylApi.addDynamicComment(action);
    }

    @Override
    public Observable<LYLResponse<String>> delDynamicComment(String action) {
        return mLylApi.delDynamicComment(action);
    }

    @Override
    public Observable<LYLResponse<String>> addDynamic(String action) {
        return mLylApi.addDynamic(action);
    }

    @Override
    public Observable<LYLResponse<DynamicPersonBean>> dynamicList(String action) {
        return mLylApi.dynamicList(action);
    }

    @Override
    public Observable<LYLResponse<PersonalCommunBean>> personalCommun(String action) {
        return mLylApi.personalCommun(action);
    }

    @Override
    public Observable<LYLResponse<String>> looknumLetter(String action) {
        return mLylApi.looknumLetter(action);
    }

    @Override
    public Observable<LYLResponse<String>> delDynamicInfo(String action) {
        return mLylApi.delDynamicInfo(action);
    }

    @Override
    public Observable<LYLResponse<String>> familyAdd(String action) {
        return mLylApi.familyAdd(action);
    }

    @Override
    public Observable<LYLResponse<FamilyListBean>> familyListMy(String action) {
        return mLylApi.familyListMy(action);
    }

    @Override
    public Observable<LYLResponse<FamilyListBean>> familyList(String action) {
        return mLylApi.familyList(action);
    }

    @Override
    public Observable<LYLResponse<FamilyFollowListBean>> familyList_follow(String action) {
        return mLylApi.familyList_follow(action);
    }

    @Override
    public Observable<LYLResponse<FamilyTonListBean>> familyList_ton(String action) {
        return mLylApi.familyList_ton(action);
    }

    @Override
    public Observable<LYLResponse<FamilyBlessListBean>> familyBlessList(String action) {
        return mLylApi.familyBlessList(action);
    }

    @Override
    public Observable<LYLResponse<FamilyReceiveGiftBean>> familyReceiveGift(String action) {
        return mLylApi.familyReceiveGift(action);
    }

    @Override
    public Observable<LYLResponse<FamilyGiftListBean>> familyListGift(String action) {
        return mLylApi.familyListGift(action);
    }

    @Override
    public Observable<LYLResponse<FamilySendGiftRecordBean>> familySendGift(String action) {
        return mLylApi.familySendGift(action);
    }

    @Override
    public Observable<LYLResponse<FamilyBlessListRecevieBean>> familyReceiveBless(String action) {
        return mLylApi.familyReceiveBless(action);
    }

    @Override
    public Observable<LYLResponse<String>> familyViewCount(String action) {
        return mLylApi.familyViewCount(action);
    }

    @Override
    public Observable<LYLResponse<FamilySacrificeTypeBean>> familyCate(String action) {
        return mLylApi.familyCate(action);
    }

    @Override
    public Observable<LYLResponse<FamilyUsualLanguageBean>> familyUsualLanguage(String action) {
        return mLylApi.familyUsualLanguage(action);
    }

    @Override
    public Observable<LYLResponse<FamilyPhotoFrameBean>> familyPhotoFrame(String action) {
        return mLylApi.familyPhotoFrame(action);
    }

    @Override
    public Observable<LYLResponse<String>> familyBlessAdd(String action) {
        return mLylApi.familyBlessAdd(action);
    }

    @Override
    public Observable<LYLResponse<FamilyListBean>> familySearch(String action) {
        return mLylApi.familySearch(action);
    }

    @Override
    public Observable<LYLResponse<FamilyReceiveGiftBean>> familyReceiveGiftById(String action) {
        return mLylApi.familyReceiveGiftById(action);
    }

    @Override
    public Observable<LYLResponse<String>> familyDelete(String action) {
        return mLylApi.familyDelete(action);
    }

    @Override
    public Observable<LYLResponse<FamilyPersonListBean>> findMyFeteList(String action) {
        return  mLylApi.findMyFeteList(action);
    }

    @Override
    public Observable<LYLResponse<String>> familySaveFPhotoFrameAndBack(String action) {
        return mLylApi.familySaveFPhotoFrameAndBack(action);
    }

    @Override
    public Observable<LYLResponse<FamilyQueryFramBackBean>> familyQueryFrameAndBack(String action) {
        return mLylApi.familyQueryFrameAndBack(action);
    }

    @Override
    public Observable<LYLResponse<String>> addletter(String action) {
        return mLylApi.addletter(action);
    }

    @Override
    public Observable<LYLResponse<LetterBean>> selectletter(String action) {
        return mLylApi.selectletter(action);
    }

    @Override
    public Observable<LYLResponse<String>> happAdd(String action) {
        return mLylApi.happAdd(action);
    }

    @Override
    public Observable<LYLResponse<BlessListBean>> happyListMy(String action) {
        return mLylApi.happyListMy(action);
    }

    @Override
    public Observable<LYLResponse<String>> happyViewCount(String action) {
        return mLylApi.happyViewCount(action);
    }

    @Override
    public Observable<LYLResponse<String>> happyDel(String action) {
        return mLylApi.happyDel(action);
    }

    @Override
    public Observable<LYLResponse<BlessListBean>> happyList(String action) {
        return mLylApi.happyList(action);
    }

    @Override
    public Observable<LYLResponse<BlessCommentBean>> happyComment(String action) {
        return mLylApi.happyComment(action);
    }

    @Override
    public Observable<LYLResponse<String>> happyCommentDel(String action) {
        return mLylApi.happyCommentDel(action);
    }

    @Override
    public Observable<LYLResponse<BlessAddLikeBean>> happyLike(String action) {
        return mLylApi.happyLike(action);
    }

    @Override
    public Observable<LYLResponse<BlessAddBean>> happyBlessAdd(String action) {
        return mLylApi.happyBlessAdd(action);
    }

    @Override
    public Observable<LYLResponse<BlessReceiveYuBean>> happyBlessReceiveList(String action) {
        return mLylApi.happyBlessReceivedList(action);
    }

    @Override
    public Observable<LYLResponse<HappyBlessUsualLanguageBean>> happyUsualLanguage(String action) {
        return mLylApi.happyUsualLanguage(action);
    }

    @Override
    public Observable<LYLResponse<BlessGiftBean>> happyGiftList(String action) {
        return mLylApi.happyGiftList(action);
    }

    @Override
    public Observable<LYLResponse<List<GiftBean>>> happyGiftList1(String action) {
        return mLylApi.happyGiftList1(action);
    }

    @Override
    public Observable<LYLResponse<BlessReceiveGiftBean>> happyGiftReceiveListMy(String action) {
        return mLylApi.happyGiftReceiveListMy(action);
    }

    @Override
    public Observable<LYLResponse<BlessSendGiftBean>> happyGiftSendListMy(String action) {
        return mLylApi.happyGiftSendListMy(action);
    }

    @Override
    public Observable<LYLResponse<String>> happyOrderAdd(String action) {
        return mLylApi.happyOrderAdd(action);
    }

    @Override
    public Observable<LYLResponse<String>> immediateOrder(String action) {
        return mLylApi.immediateOrder(action);
    }

    @Override
    public Observable<LYLResponse<BlessOrderBean>> alipayList(String action) {
        return mLylApi.alipayList(action);
    }

    @Override
    public Observable<LYLResponse<AppUpdate>> appupdate(String action) {
        return mLylApi.appupdate(action);
    }

    @Override
    public Observable<LYLResponse<List<CFTabBean>>> seleinfocate(String action) {
        return mLylApi.seleinfocate(action);
    }

    @Override
    public Observable<LYLResponse<CFChildBean>> cfchilflist(String action) {
        return mLylApi.cfchilflist(action);
    }

    @Override
    public Observable<LYLResponse_Two<Integer>> updatepointnum(String action) {
        return mLylApi.updatepointnum(action);
    }

    @Override
    public Observable<LYLResponse<String>> looknumFlatcount(String action) {
        return mLylApi.looknumFlatcount(action);
    }

    @Override
    public Observable<LYLResponse_PIN<String>> addFlatComment(String action) {
        return mLylApi.addFlatComment(action);
    }

    @Override
    public Observable<LYLResponse<CFPinBean>> cfcommentlist(String action) {
        return mLylApi.cfcommentlist(action);
    }

    @Override
    public Observable<LYLResponse<String>> cf_addzan(String action) {
        return mLylApi.cf_addzan(action);
    }

    @Override
    public Observable<LYLResponse<String>> delFlatComment(String action) {
        return  mLylApi.delFlatComment(action);
    }

    @Override
    public Observable<LYLResponse<PropertLoginBean>> loginEstates(String action) {
        return mMyApi.loginEstates(action);
    }

    @Override
    public Observable<LYLResponse<PropretyNoticeBean>> noticeEstates(String action) {
        return mMyApi.noticeEstates(action);
    }

    @Override
    public Observable<LYLResponse<PropretyLiveBean>> propretyLive(String action) {
        return mMyApi.propretyLive(action);
    }

    @Override
    public Observable<LYLResponse<List<VillageNameBean>>> findVillagename(String action) {
        return mMyApi.findVillagename(action);
    }

    @Override
    public Observable<LYLResponse<List<LiveAddressBean>>> findLiveaddress(String action) {
        return mMyApi.findLiveaddress(action);
    }

    @Override
    public Observable<LYLResponse<List<PropertyBillBean>>> propertyBill(String action) {
        return mMyApi.propertyBill(action);
    }

    @Override
    public Observable<LYLResponse<String>> propertyaddOwn(String action) {
        return mMyApi.propertyaddOwn(action);
    }

    @Override
    public Observable<LYLResponse_PIN<String>> findBillsMonth(String action) {
        return mMyApi.findBillsMonth(action);
    }

    @Override
    public Observable<LYLResponse<List<HomeDetailBean>>> personalNews(String action) {
        return mMyApi.personalNews(action);
    }

    @Override
    public Observable<LYLResponse<List<ComplaintTypeBean>>> complaintType(String action) {
        return mMyApi.complaintType(action);
    }

    @Override
    public Observable<LYLResponse<String>> addComplaint(String action) {
        return mMyApi.addComplaint(action);
    }

    @Override
    public Observable<LYLResponse<ComSearchBean>> findComplaintList(String action) {
        return mMyApi.findComplaintList(action);
    }

    @Override
    public Observable<LYLResponse<List<PropertyDetailBean>>> detaComplaintList(String action) {
        return mMyApi.detaComplaintList(action);
    }

    @Override
    public Observable<LYLResponse<List<PayLuBean>>> estatesPayList(String action) {
        return mMyApi.estatesPayList(action);
    }

    @Override
    public Observable<LYLResponse<RepairListBean>> maintenanceList(String action) {
        return mMyApi.maintenanceList(action);
    }

    @Override
    public Observable<LYLResponse<List<RepairCityListBean>>> queryarealist(String action) {
        return mMyApi.queryarealist(action);
    }

    @Override
    public Observable<LYLResponse<List<RepairCityListBean>>> queryrepairallcatelist(String action) {
        return mMyApi.queryrepairallcatelist(action);
    }

    @Override
    public Observable<LYLResponse<ProperCommentList>> commentEstatesList(String action) {
        return mMyApi.commentEstatesList(action);
    }

    @Override
    public Observable<LYLResponse<String>> addCommentEstates(String action) {
        return mMyApi.addCommentEstates(action);
    }

    @Override
    public Observable<LYLResponse<String>> addCollectMaintenance(String action) {
        return mMyApi.addCollectMaintenance(action);
    }

    @Override
    public Observable<LYLResponse<SeleBean>> seleCommentConunt(String action) {
        return mMyApi.seleCommentConunt(action);
    }

    @Override
    public Observable<LYLResponse<CollectionListBean>> collectMaintenanceList(String action) {
        return mMyApi.collectMaintenanceList(action);
    }

    @Override
    public Observable<LYLResponse<String>> deleMaintenanceList(String action) {
        return mMyApi.deleMaintenanceList(action);
    }

    @Override
    public Observable<LYLResponse<String>> prolooknumcount(String action) {
        return mMyApi.prolooknumcount(action);
    }

    @Override
    public Observable<LYLResponse<String>> delefollow(String action) {
        return mLylApi.delefollow(action);
    }
    @Override
    public Observable<LYLResponse<MessageBean>> myDyPushList(String action) {
        return mLylApi.myDyPushList(action);
    }

    @Override
    public Observable<LYLResponse<HistoricalBean>> querybillrecord(String action) {
        return mMyApi.querybillrecord(action);
    }

    @Override
    public Observable<LYLResponse<String>> clearMyDyPushList(String action) {
        return mLylApi.clearMyDyPushList(action);
    }

    @Override
    public Observable<LYLResponse<String>> happyCartAdd(String action) {
        return mLylApi.happyCartAdd(action);
    }

    @Override
    public Observable<LYLResponse<BlessCartListBean>> happyCartList(String action) {
        return mLylApi.happyCartList(action);
    }

    @Override
    public Observable<LYLResponse<CartUpdateBean>> happyCartUpdate(String action) {
        return mLylApi.happyCartUpdate(action);
    }

    @Override
    public Observable<LYLResponse<String>> happyCartDel(String action) {
        return mLylApi.happyCartDel(action);
    }

    @Override
    public Observable<LYLResponse<BlessListBean>> happySearch(String action) {
        return mLylApi.happySearch(action);
    }

    @Override
    public Observable<LYLResponse<BlessDetailGiftListBean>> happyDetailsGiftList(String action) {
        return  mLylApi.happyDetailsGiftList(action);
    }

    @Override
    public Observable<LYLResponse<FileUploadBean>> uploadHeader(MultipartBody.Part file) {
        return mLylApi.uploadHeader(file);
    }

    @Override
    public Observable<LYLResponse<AliPayBean>> alipayTest(String action) {
        return mLylApi.alipayTest(action);
    }

    @Override
    public Observable<LYLResponse<WeiXinPayTestBean>> weixinPayTest(String action) {
        return mLylApi.weixinPayTest(action);
    }

}
