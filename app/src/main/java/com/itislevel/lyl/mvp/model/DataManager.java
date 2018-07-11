package com.itislevel.lyl.mvp.model;



import com.itislevel.lyl.adapter.LiveAddressBean;
import com.itislevel.lyl.mvp.model.bean.*;
import com.itislevel.lyl.mvp.model.db.DBHelper;
import com.itislevel.lyl.mvp.model.http.HttpHelper;
import com.itislevel.lyl.mvp.model.http.response.GankResponse;
import com.itislevel.lyl.mvp.model.http.response.LYLResponse;
import com.itislevel.lyl.mvp.model.http.response.LYLResponse_PIN;
import com.itislevel.lyl.mvp.model.http.response.LYLResponse_Two;
import com.itislevel.lyl.mvp.model.prefs.PreferencesHelper;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.MultipartBody;

/**
 * **********************
 * 功 能:必填必填必填必填必填必填
 * 创建人:itisi
 * 邮  箱:itisivip@qq.com
 * 创建时间:2017/7/5 17:25
 * 修改人:itisi
 * 修改时间: 2017/7/5 17:25
 * 修改内容:itisi
 * *********************
 */

public class DataManager implements HttpHelper,DBHelper,PreferencesHelper{

    HttpHelper mHttpHelper;
    DBHelper mDBHelper;
    PreferencesHelper mPreferencesHelper;

    public DataManager(HttpHelper httpHelper, DBHelper DBHelper, PreferencesHelper preferencesHelper) {
        mHttpHelper = httpHelper;
        mDBHelper = DBHelper;
        mPreferencesHelper = preferencesHelper;
    }

    @Override
    public boolean getNightModeState() {
        return false;
    }

    @Override
    public void setNightModeState(boolean state) {

    }


    //测试
    @Override
    public Observable<GankResponse<List<MeiZiBean>>> getMeiZiList(int num, int page) {
        return mHttpHelper.getMeiZiList(num,page);
    }

    @Override
    public Observable<GankResponse<List<MeiZiMultipleBean>>> getMeiZiListMul(int num, int page) {
        return mHttpHelper.getMeiZiListMul(num,page);
    }

    // 老友乐
    @Override
    public Observable<LYLResponse<UserInfoBean>> login(String action) {
        return mHttpHelper.login(action);
    }


    //
    @Override
    public Observable<LYLResponse<HomeBean>> firstPage(String action) {
        return mHttpHelper.firstPage(action);
    }

    @Override
    public Observable<LYLResponse<PlaceBean>> place(String action) {
        return mHttpHelper.place(action);
    }

    @Override
    public Observable<LYLResponse<RegistBean>> register(String action) {
        return mHttpHelper.register(action);
    }

    @Override
    public Observable<LYLResponse<String>> getValidateCode(String action) {
        return mHttpHelper.getValidateCode(action);
    }

    @Override
    public Observable<LYLResponse<String>> getSSMCode(String action) {
        return mHttpHelper.getSSMCode(action);
    }

    @Override
    public Observable<LYLResponse<String>> userPerfectPersonal(String action) {
        return mHttpHelper.userPerfectPersonal(action);
    }

    @Override
    public Observable<LYLResponse<String>> userModifyPassword(String action) {
        return mHttpHelper.userModifyPassword(action);
    }

    @Override
    public Observable<LYLResponse<String>> userModifyNickname(String action) {
        return mHttpHelper.userModifyNickname(action);
    }

    @Override
    public Observable<LYLResponse<String>> userModifyHeader(String action) {
        return mHttpHelper.userModifyHeader(action);
    }

    @Override
    public Observable<LYLResponse<MyReceiveAddrBean>> userFindRecAddress(String action) {
        return mHttpHelper.userFindRecAddress(action);
    }

    @Override
    public Observable<LYLResponse<String>> userUpdateRecAddress(String action) {
        return mHttpHelper.userUpdateRecAddress(action);
    }

    @Override
    public Observable<LYLResponse<String>> userGiftAdd(String action) {
        return mHttpHelper.userGiftAdd(action);
    }

    @Override
    public Observable<LYLResponse<MyGiftBean>> userGiftList(String action) {
        return mHttpHelper.userGiftList(action);
    }

    @Override
    public Observable<LYLResponse<String>> userForgetPasswrord(String action) {
        return mHttpHelper.userForgetPasswrord(action);
    }

    @Override
    public Observable<LYLResponse<String>> userUpdatePhone(String action) {
        return mHttpHelper.userUpdatePhone(action);
    }

    @Override
    public Observable<LYLResponse<String>> userAddFeedback(String action) {
        return  mHttpHelper.userAddFeedback(action);
    }

    @Override
    public Observable<LYLResponse<List<UserHeaderNickInfo>>> userInfoById(String action) {
        return mHttpHelper.userInfoById(action);
    }

    @Override
    public Observable<LYLResponse<List<AddressBean>>> province(String action) {
        return mHttpHelper.province(action);
    }

    @Override
    public Observable<LYLResponse<List<AddressBean>>> city(String action) {
        return mHttpHelper.city(action);
    }

    @Override
    public Observable<LYLResponse<List<AddressBean>>> county(String action) {
        return mHttpHelper.county(action);
    }

    @Override
    public Observable<LYLResponse<FunsharingAddBean>> shareAdd(String action) {
        return mHttpHelper.shareAdd(action);
    }

    @Override
    public Observable<LYLResponse<FunsharingListBean>> shareList(String action) {
        return mHttpHelper.shareList(action);
    }

    @Override
    public Observable<LYLResponse<FunsharingMyBean>> shareListMy(String action) {
        return mHttpHelper.shareListMy(action);
    }

    @Override
    public Observable<LYLResponse<String>> shareDelect(String action) {
        return mHttpHelper.shareDelect(action);
    }

    @Override
    public Observable<LYLResponse<FunsharingCommnetAddBean>> commentShareAdd(String action) {
        return mHttpHelper.commentShareAdd(action);
    }

    @Override
    public Observable<LYLResponse<String>> commentShareDel(String action) {
        return mHttpHelper.commentShareDel(action);
    }

    @Override
    public Observable<LYLResponse<FunsharingLikeBean>> shareLikeOrCancel(String action) {
        return mHttpHelper.shareLikeOrCancel(action);
    }

    @Override
    public Observable<LYLResponse<TroubleListBean>> troubleList(String action) {
        return mHttpHelper.troubleList(action);
    }

    @Override
    public Observable<LYLResponse<TroubleAddBean>> troubleAdd(String action) {
        return mHttpHelper.troubleAdd(action);
    }

    @Override
    public Observable<LYLResponse<TroubleListBean>> troubleMyList(String action) {
        return mHttpHelper.troubleMyList(action);
    }

    @Override
    public Observable<LYLResponse<String>> troubleDel(String action) {
        return  mHttpHelper.troubleDel(action);
    }

    @Override
    public Observable<LYLResponse<TroubleCommentAdd>> troubleCommentReplay(String action) {
        return  mHttpHelper.troubleCommentReplay(action);
    }

    @Override
    public Observable<LYLResponse<String>> troubleCommentDel(String action) {
        return  mHttpHelper.troubleCommentDel(action);
    }

    @Override
    public Observable<LYLResponse<TroubleTypeBean>> troubleType(String action) {
        return mHttpHelper.troubleType(action);
    }

    @Override
    public Observable<LYLResponse<PropertyQueryInfoBean>> propertyQuery(String action) {
        return mHttpHelper.propertyQuery(action);
    }

    @Override
    public Observable<LYLResponse<PropertyRecordBean>> propertyQueryOrder(String action) {
        return mHttpHelper.propertyQueryOrder(action);
    }

    @Override
    public Observable<LYLResponse<PropertyUpdateStatusBean>> propertyUpdatePayType(String action) {
        return mHttpHelper.propertyUpdatePayType(action);
    }

    @Override
    public Observable<LYLResponse<PropertyUpdateStatusBean>> propertyUpdateOrderState(String action) {

        return mHttpHelper.propertyUpdateOrderState(action);
    }

    @Override
    public Observable<LYLResponse<String>> propertyGenerateOrder(String action) {
        return mHttpHelper.propertyGenerateOrder(action);
    }

    @Override
    public Observable<LYLResponse<PropertyQueryInfo>> propertyQueryByUserid(String action) {
        return mHttpHelper.propertyQueryByUserid(action);
    }

    @Override
    public Observable<LYLResponse<PropertyQueryInfo>> propertyQueryList(String action) {
        return mHttpHelper.propertyQueryList(action);
    }

    @Override
    public Observable<LYLResponse<PropertyQueryInfoBean>> propertyQueryByUserid1(String action) {
        return mHttpHelper.propertyQueryByUserid1(action);
    }

    @Override
    public Observable<LYLResponse<SetOwnerPayMonth>> propertySetOwnerPayMonth(String action) {
        return mHttpHelper.propertySetOwnerPayMonth(action);
    }

    @Override
    public Observable<LYLResponse<HouseKeepBean>> findHouse(String action) {
        return mHttpHelper.findHouse(action);
    }

    @Override
    public Observable<LYLResponse<String>> teamRegister(String action) {
        return mHttpHelper.teamRegister(action);
    }

    @Override
    public Observable<LYLResponse<TeamStatusBean>> teamStatus(String action) {
        return mHttpHelper.teamStatus(action);
    }

    @Override
    public Observable<LYLResponse<TeamListBean>> teamList(String action) {
        return mHttpHelper.teamList(action);
    }

    @Override
    public Observable<LYLResponse<String>> teamViewCount(String action) {
        return mHttpHelper.teamViewCount(action);
    }

    @Override
    public Observable<LYLResponse<String>> teamProblemAdd(String action) {
        return mHttpHelper.teamProblemAdd(action);
    }

    @Override
    public Observable<LYLResponse<MyTeamBean>> teamProblemList(String action) {
        return mHttpHelper.teamProblemList(action);
    }

    @Override
    public Observable<LYLResponse<TroubleAdviserMyBean>> teamMyProblemList(String action) {
        return mHttpHelper.teamMyProblemList(action);
    }

    @Override
    public Observable<LYLResponse<TeamTypeBean>> teamType(String action) {
        return mHttpHelper.teamType(action);
    }

    @Override
    public Observable<LYLResponse<String>> teamReplay(String action) {
        return mHttpHelper.teamReplay(action);
    }

    @Override
    public Observable<LYLResponse<SpecialTypeBean>> specialType(String action) {
        return mHttpHelper.specialType(action);
    }

    @Override
    public Observable<LYLResponse<SpecialGiftListBean>> specialList(String action) {
        return mHttpHelper.specialList(action);
    }

    @Override
    public Observable<LYLResponse<SpecialGiftByIdBean>> specialById(String action) {
        return mHttpHelper.specialById(action);
    }

    @Override
    public Observable<LYLResponse<String>> specialImmediatelyOrder(String action) {
        return mHttpHelper.specialImmediatelyOrder(action);
    }

    @Override
    public Observable<LYLResponse<String>> specialShopOrder(String action) {
        return mHttpHelper.specialShopOrder(action);
    }

    @Override
    public Observable<LYLResponse<SpecialReceiveAddressBean>> specialReceiveAddress(String action) {
        return mHttpHelper.specialReceiveAddress(action);
    }

    @Override
    public Observable<LYLResponse<SpecialOrderDetailBean>> specialOrderDetail(String action) {
        return mHttpHelper.specialOrderDetail(action);
    }

    @Override
    public Observable<LYLResponse<SpecialTuiKuanDetailBean>> specialTuiKuanDetail(String action) {
        return mHttpHelper.specialTuiKuanDetail(action);
    }

    @Override
    public Observable<LYLResponse<String>> specialTuiKuan(String action) {
        return mHttpHelper.specialTuiKuan(action);
    }

    @Override
    public Observable<LYLResponse<SpecialTuiKuanUpdateBean>> specialTuiKuanUpdate(String action) {
        return mHttpHelper.specialTuiKuanUpdate(action);
    }

    @Override
    public Observable<LYLResponse<String>> specialTuiKuanUpdate2(String action) {
        return mHttpHelper.specialTuiKuanUpdate2(action);
    }

    @Override
    public Observable<LYLResponse<SpecialOrderCompleteBean>> specialOrderComplete(String action) {
        return mHttpHelper.specialOrderComplete(action);
    }

    @Override
    public Observable<LYLResponse<String>> specialOrderGoPay(String action) {
        return mHttpHelper.specialOrderGoPay(action);
    }

    @Override
    public Observable<LYLResponse<String>> specialOrderCancel(String action) {
        return mHttpHelper.specialOrderCancel(action);
    }

    @Override
    public Observable<LYLResponse<String>> specialShenQingTuiKuan(String action) {
        return mHttpHelper.specialShenQingTuiKuan(action);
    }

    @Override
    public Observable<LYLResponse<String>> specialOrderConfirm(String action) {
        return mHttpHelper.specialOrderConfirm(action);
    }

    @Override
    public Observable<LYLResponse<SpecialReturnBean>> specialReturnList(String action) {
        return mHttpHelper.specialReturnList(action);
    }

    @Override
    public Observable<LYLResponse<FollowListBean>> dynamic_follow(String action) {
        return mHttpHelper.dynamic_follow(action);
    }

    @Override
    public Observable<LYLResponse<FindistBean>> dynamic_find(String action) {
        return mHttpHelper.dynamic_find(action);
    }

    @Override
    public Observable<LYLResponse<TonCityListBean>> dynamic_ton(String action) {
        return mHttpHelper.dynamic_ton(action);
    }

    @Override
    public Observable<LYLResponse<DynimacLinkBean>> dynamicdianzan(String action) {
        return mHttpHelper.dynamicdianzan(action);
    }

    @Override
    public Observable<LYLResponse<String>> dynamicfollow(String action) {
        return mHttpHelper.dynamicfollow(action);
    }

    @Override
    public Observable<LYLResponse<DynamicCommnetAddBean>> addDynamicComment(String action) {
        return mHttpHelper.addDynamicComment(action);
    }

    @Override
    public Observable<LYLResponse<String>> delDynamicComment(String action) {
        return mHttpHelper.delDynamicComment(action);
    }

    @Override
    public Observable<LYLResponse<String>> addDynamic(String action) {
        return mHttpHelper.addDynamic(action);
    }

    @Override
    public Observable<LYLResponse<String>> delDynamicInfo(String action) {
        return mHttpHelper.delDynamicInfo(action);
    }

    @Override
    public Observable<LYLResponse<DynamicPersonBean>> dynamicList(String action) {
        return mHttpHelper.dynamicList(action);
    }

    @Override
    public Observable<LYLResponse<PersonalCommunBean>> personalCommun(String action) {
        return mHttpHelper.personalCommun(action);
    }

    @Override
    public Observable<LYLResponse<String>> looknumLetter(String action) {
        return mHttpHelper.looknumLetter(action);
    }

    @Override
    public Observable<LYLResponse<String>> familyAdd(String action) {
        return mHttpHelper.familyAdd(action);
    }

    @Override
    public Observable<LYLResponse<FamilyListBean>> familyListMy(String action) {
        return mHttpHelper.familyListMy(action);
    }

    @Override
    public Observable<LYLResponse<FamilyListBean>> familyList(String action) {
        return mHttpHelper.familyList(action);
    }

    @Override
    public Observable<LYLResponse<FamilyFollowListBean>> familyList_follow(String action) {
        return mHttpHelper.familyList_follow(action);
    }

    @Override
    public Observable<LYLResponse<FamilyTonListBean>> familyList_ton(String action) {
        return mHttpHelper.familyList_ton(action);
    }

    @Override
    public Observable<LYLResponse<FamilyBlessListBean>> familyBlessList(String action) {
        return mHttpHelper.familyBlessList(action);
    }

    @Override
    public Observable<LYLResponse<FamilyReceiveGiftBean>> familyReceiveGift(String action) {
        return mHttpHelper.familyReceiveGift(action);
    }

    @Override
    public Observable<LYLResponse<FamilyGiftListBean>> familyListGift(String action) {
        return mHttpHelper.familyListGift(action);
    }

    @Override
    public Observable<LYLResponse<FamilySendGiftRecordBean>> familySendGift(String action) {
        return mHttpHelper.familySendGift(action);
    }

    @Override
    public Observable<LYLResponse<FamilyBlessListRecevieBean>> familyReceiveBless(String action) {
        return mHttpHelper.familyReceiveBless(action);
    }

    @Override
    public Observable<LYLResponse<String>> familyViewCount(String action) {
        return mHttpHelper.familyViewCount(action);
    }

    @Override
    public Observable<LYLResponse<FamilySacrificeTypeBean>> familyCate(String action) {
        return mHttpHelper.familyCate(action);
    }

    @Override
    public Observable<LYLResponse<FamilyUsualLanguageBean>> familyUsualLanguage(String action) {
        return mHttpHelper.familyUsualLanguage(action);
    }

    @Override
    public Observable<LYLResponse<FamilyPhotoFrameBean>> familyPhotoFrame(String action) {
        return mHttpHelper.familyPhotoFrame(action);
    }

    @Override
    public Observable<LYLResponse<String>> familyBlessAdd(String action) {
        return mHttpHelper.familyBlessAdd(action);
    }

    @Override
    public Observable<LYLResponse<FamilyListBean>> familySearch(String action) {
        return mHttpHelper.familySearch(action);
    }

    @Override
    public Observable<LYLResponse<FamilyReceiveGiftBean>> familyReceiveGiftById(String action) {
        return mHttpHelper.familyReceiveGiftById(action);
    }

    @Override
    public Observable<LYLResponse<String>> familyDelete(String action) {
        return mHttpHelper.familyDelete(action);
    }

    @Override
    public Observable<LYLResponse<FamilyPersonListBean>> findMyFeteList(String action) {
        return mHttpHelper.findMyFeteList(action);
    }

    @Override
    public Observable<LYLResponse<String>> familySaveFPhotoFrameAndBack(String action) {
        return mHttpHelper.familySaveFPhotoFrameAndBack(action);
    }

    @Override
    public Observable<LYLResponse<FamilyQueryFramBackBean>> familyQueryFrameAndBack(String action) {
        return mHttpHelper.familyQueryFrameAndBack(action);
    }

    @Override
    public Observable<LYLResponse<String>> addletter(String action) {
        return mHttpHelper.addletter(action);
    }

    @Override
    public Observable<LYLResponse<LetterBean>> selectletter(String action) {
        return mHttpHelper.selectletter(action);
    }

    @Override
    public Observable<LYLResponse<String>> happAdd(String action) {
        return mHttpHelper.happAdd(action);
    }

    @Override
    public Observable<LYLResponse<BlessListBean>> happyListMy(String action) {
        return mHttpHelper.happyListMy(action);
    }

    @Override
    public Observable<LYLResponse<String>> happyViewCount(String action) {
        return mHttpHelper.happyViewCount(action);
    }

    @Override
    public Observable<LYLResponse<String>> happyDel(String action) {
        return mHttpHelper.happyDel(action);
    }

    @Override
    public Observable<LYLResponse<BlessListBean>> happyList(String action) {
        return mHttpHelper.happyList(action);
    }

    @Override
    public Observable<LYLResponse<BlessCommentBean>> happyComment(String action) {
        return mHttpHelper.happyComment(action);
    }

    @Override
    public Observable<LYLResponse<String>> happyCommentDel(String action) {
        return mHttpHelper.happyCommentDel(action);
    }

    @Override
    public Observable<LYLResponse<BlessAddLikeBean>> happyLike(String action) {
        return mHttpHelper.happyLike(action);
    }

    @Override
    public Observable<LYLResponse<BlessAddBean>> happyBlessAdd(String action) {
        return mHttpHelper.happyBlessAdd(action);
    }

    @Override
    public Observable<LYLResponse<BlessReceiveYuBean>> happyBlessReceiveList(String action) {
        return mHttpHelper.happyBlessReceiveList(action);
    }

    @Override
    public Observable<LYLResponse<HappyBlessUsualLanguageBean>> happyUsualLanguage(String action) {
        return mHttpHelper.happyUsualLanguage(action);
    }

    @Override
    public Observable<LYLResponse<BlessGiftBean>> happyGiftList(String action) {
        return mHttpHelper.happyGiftList(action);
    }

    @Override
    public Observable<LYLResponse<List<GiftBean>>> happyGiftList1(String action) {
        return mHttpHelper.happyGiftList1(action);
    }

    @Override
    public Observable<LYLResponse<BlessReceiveGiftBean>> happyGiftReceiveListMy(String action) {
        return mHttpHelper.happyGiftReceiveListMy(action);
    }

    @Override
    public Observable<LYLResponse<BlessSendGiftBean>> happyGiftSendListMy(String action) {
        return mHttpHelper.happyGiftSendListMy(action);
    }

    @Override
    public Observable<LYLResponse<String>> happyOrderAdd(String action) {
        return mHttpHelper.happyOrderAdd(action);
    }

    @Override
    public Observable<LYLResponse<String>> immediateOrder(String action) {
        return mHttpHelper.immediateOrder(action);
    }

    @Override
    public Observable<LYLResponse<BlessOrderBean>> alipayList(String action) {
        return mHttpHelper.alipayList(action);
    }

    @Override
    public Observable<LYLResponse<AppUpdate>> appupdate(String action) {
        return mHttpHelper.appupdate(action);
    }

    @Override
    public Observable<LYLResponse<List<CFTabBean>>> seleinfocate(String action) {
        return mHttpHelper.seleinfocate(action);
    }

    @Override
    public Observable<LYLResponse<CFChildBean>> cfchilflist(String action) {
        return mHttpHelper.cfchilflist(action);
    }

    @Override
    public Observable<LYLResponse_Two<Integer>> updatepointnum(String action) {
        return mHttpHelper.updatepointnum(action);
    }

    @Override
    public Observable<LYLResponse<String>> looknumFlatcount(String action) {
        return mHttpHelper.looknumFlatcount(action);
    }

    @Override
    public Observable<LYLResponse_PIN<String>> addFlatComment(String action) {
        return mHttpHelper.addFlatComment(action);
    }

    @Override
    public Observable<LYLResponse<CFPinBean>> cfcommentlist(String action) {
        return mHttpHelper.cfcommentlist(action);
    }

    @Override
    public Observable<LYLResponse<String>> cf_addzan(String action) {
        return mHttpHelper.cf_addzan(action);
    }

    @Override
    public Observable<LYLResponse<String>> delFlatComment(String action) {
        return mHttpHelper.delFlatComment(action);
    }

    @Override
    public Observable<LYLResponse<PropertLoginBean>> loginEstates(String action) {
        return mHttpHelper.loginEstates(action);
    }

    @Override
    public Observable<LYLResponse<PropretyNoticeBean>> noticeEstates(String action) {
        return mHttpHelper.noticeEstates(action);
    }

    @Override
    public Observable<LYLResponse<PropretyLiveBean>> propretyLive(String action) {
        return mHttpHelper.propretyLive(action);
    }

    @Override
    public Observable<LYLResponse<List<VillageNameBean>>> findVillagename(String action) {
        return mHttpHelper.findVillagename(action);
    }

    @Override
    public Observable<LYLResponse<List<LiveAddressBean>>> findLiveaddress(String action) {
        return mHttpHelper.findLiveaddress(action);
    }

    @Override
    public Observable<LYLResponse<List<PropertyBillBean>>> propertyBill(String action) {
        return mHttpHelper.propertyBill(action);
    }

    @Override
    public Observable<LYLResponse<String>> propertyaddOwn(String action) {
        return mHttpHelper.propertyaddOwn(action);
    }

    @Override
    public Observable<LYLResponse_PIN<String>> findBillsMonth(String action) {
        return mHttpHelper.findBillsMonth(action);
    }

    @Override
    public Observable<LYLResponse<List<HomeDetailBean>>> personalNews(String action) {
        return mHttpHelper.personalNews(action);
    }

    @Override
    public Observable<LYLResponse<List<ComplaintTypeBean>>> complaintType(String action) {
        return mHttpHelper.complaintType(action);
    }

    @Override
    public Observable<LYLResponse<String>> addComplaint(String action) {
        return mHttpHelper.addComplaint(action);
    }

    @Override
    public Observable<LYLResponse<ComSearchBean>> findComplaintList(String action) {
        return mHttpHelper.findComplaintList(action);
    }

    @Override
    public Observable<LYLResponse<List<PropertyDetailBean>>> detaComplaintList(String action) {
        return mHttpHelper.detaComplaintList(action);
    }

    @Override
    public Observable<LYLResponse<List<PayLuBean>>> estatesPayList(String action) {
        return mHttpHelper.estatesPayList(action);
    }

    @Override
    public Observable<LYLResponse<RepairListBean>> maintenanceList(String action) {
        return mHttpHelper.maintenanceList(action);
    }

    @Override
    public Observable<LYLResponse<List<RepairCityListBean>>> queryarealist(String action) {
        return mHttpHelper.queryarealist(action);
    }

    @Override
    public Observable<LYLResponse<List<RepairCityListBean>>> queryrepairallcatelist(String action) {
        return mHttpHelper.queryrepairallcatelist(action);
    }

    @Override
    public Observable<LYLResponse<ProperCommentList>> commentEstatesList(String action) {
        return mHttpHelper.commentEstatesList(action);
    }

    @Override
    public Observable<LYLResponse<String>> addCommentEstates(String action) {
        return mHttpHelper.addCommentEstates(action);
    }

    @Override
    public Observable<LYLResponse<String>> addCollectMaintenance(String action) {
        return mHttpHelper.addCollectMaintenance(action);
    }

    @Override
    public Observable<LYLResponse<SeleBean>> seleCommentConunt(String action) {
        return mHttpHelper.seleCommentConunt(action);
    }

    @Override
    public Observable<LYLResponse<CollectionListBean>> collectMaintenanceList(String action) {
        return mHttpHelper.collectMaintenanceList(action);
    }

    @Override
    public Observable<LYLResponse<String>> deleMaintenanceList(String action) {
        return mHttpHelper.deleMaintenanceList(action);
    }

    @Override
    public Observable<LYLResponse<String>> prolooknumcount(String action) {
        return mHttpHelper.prolooknumcount(action);
    }

    @Override
    public Observable<LYLResponse<String>> delefollow(String action) {
        return mHttpHelper.delefollow(action);
    }

    @Override
    public Observable<LYLResponse<MessageBean>> myDyPushList(String action) {
        return mHttpHelper.myDyPushList(action);
    }

    @Override
    public Observable<LYLResponse<HistoricalBean>> querybillrecord(String action) {
        return mHttpHelper.querybillrecord(action);
    }

    @Override
    public Observable<LYLResponse<String>> clearMyDyPushList(String action) {
        return mHttpHelper.clearMyDyPushList(action);
    }
    @Override
    public Observable<LYLResponse<FanloginBean>> merchantlogin(String action) {
        return mHttpHelper.merchantlogin(action);
    }

    @Override
    public Observable<LYLResponse<ShanHomeBean>> merchantmainpage(String action) {
        return mHttpHelper.merchantmainpage(action);
    }

    @Override
    public Observable<LYLResponse<FanRecodeBean>> rechargeRecord(String action) {
        return mHttpHelper.rechargeRecord(action);
    }

    @Override
    public Observable<LYLResponse<FanXianBean>> cashbackist(String action) {
        return mHttpHelper.cashbackist(action);
    }

    @Override
    public Observable<LYLResponse<String>> onlinerecharge(String action) {
        return mHttpHelper.onlinerecharge(action);
    }

    @Override
    public Observable<LYLResponse<UserFanBean>> cashbackPage(String action) {
        return mHttpHelper.cashbackPage(action);
    }

    @Override
    public Observable<LYLResponse<UserPlanBean>> cashbackstages(String action) {
        return mHttpHelper.cashbackstages(action);
    }

    @Override
    public Observable<LYLResponse<UserPlanDetailBean>> cashbackstagesDetails(String action) {
        return mHttpHelper.cashbackstagesDetails(action);
    }

    @Override
    public Observable<LYLResponse<UserHistoryBean>> cashbackRecord(String action) {
        return mHttpHelper.cashbackRecord(action);
    }

    @Override
    public Observable<LYLResponse<String>> clickreceive(String action) {
        return mHttpHelper.clickreceive(action);
    }

    @Override
    public Observable<LYLResponse<BlankNameBean>> queryBankNameByIdCard(String action) {
        return mHttpHelper.queryBankNameByIdCard(action);
    }

    @Override
    public Observable<LYLResponse<BlankListBean>> queryBankBranchList(String action) {
        return mHttpHelper.queryBankBranchList(action);
    }

    @Override
    public Observable<LYLResponse<BankCardBean>> bankCardVerification(String action) {
        return mHttpHelper.bankCardVerification(action);
    }

    @Override
    public Observable<LYLResponse<String>> finishVerification(String action) {
        return mHttpHelper.finishVerification(action);
    }

    @Override
    public Observable<LYLResponse<String>> happyCartAdd(String action) {
        return mHttpHelper.happyCartAdd(action);
    }

    @Override
    public Observable<LYLResponse<BlessCartListBean>> happyCartList(String action) {
        return mHttpHelper.happyCartList(action);
    }

    @Override
    public Observable<LYLResponse<CartUpdateBean>> happyCartUpdate(String action) {
        return mHttpHelper.happyCartUpdate(action);
    }

    @Override
    public Observable<LYLResponse<String>> happyCartDel(String action) {
        return mHttpHelper.happyCartDel(action);
    }

    @Override
    public Observable<LYLResponse<BlessListBean>> happySearch(String action) {
        return mHttpHelper.happySearch(action);
    }

    @Override
    public Observable<LYLResponse<BlessDetailGiftListBean>> happyDetailsGiftList(String action) {
        return mHttpHelper.happyDetailsGiftList(action);
    }

    @Override
    public Observable<LYLResponse<FileUploadBean>> uploadHeader(MultipartBody.Part file) {
        return mHttpHelper.uploadHeader(file);
    }

    @Override
    public Observable<LYLResponse<AliPayBean>> alipayTest(String action) {
        return mHttpHelper.alipayTest(action);
    }

    @Override
    public Observable<LYLResponse<WeiXinPayTestBean>> weixinPayTest(String action) {
        return mHttpHelper.weixinPayTest(action);
    }

    //***************************数据库操作**********************************
    @Override
    public boolean queryNewsId(int id) {
        return false;
    }

    @Override
    public void closeDB() {
        mDBHelper.closeDB();
    }

    @Override
    public int test_insert() {
        return mDBHelper.test_insert();
    }

    @Override
    public int test_delete() {
        return mDBHelper.test_delete();
    }

    @Override
    public int test_update() {
        return mDBHelper.test_update();
    }

    @Override
    public int test_select() {
        return  mDBHelper.test_select();
    }

}

















