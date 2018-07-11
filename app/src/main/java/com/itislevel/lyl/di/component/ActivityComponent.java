package com.itislevel.lyl.di.component;

import android.app.Activity;


import com.itislevel.lyl.di.module.ActivityModule;
import com.itislevel.lyl.di.scope.ActivityScope;
import com.itislevel.lyl.mvp.ui.about.AboutActivity;
import com.itislevel.lyl.mvp.ui.address.CityActivity;
import com.itislevel.lyl.mvp.ui.address.CountyActivity;
import com.itislevel.lyl.mvp.ui.address.ProvinceActivity;
import com.itislevel.lyl.mvp.ui.backmonkey.FanxianLoginActivity;
import com.itislevel.lyl.mvp.ui.blessing.BlessReceiveBlessGiftActivity;
import com.itislevel.lyl.mvp.ui.blessing.BlessReceiveBlessYuActivity;
import com.itislevel.lyl.mvp.ui.blessing.BlessReceiveGiftActivity;
import com.itislevel.lyl.mvp.ui.blessing.BlessingAddActivity;
import com.itislevel.lyl.mvp.ui.blessing.BlessingDetailActivity;
import com.itislevel.lyl.mvp.ui.blessing.BlessingHomeActivity;
import com.itislevel.lyl.mvp.ui.blessing.BlessingMyActivity;
import com.itislevel.lyl.mvp.ui.blessing.BlessingSendRecordActivity;
import com.itislevel.lyl.mvp.ui.dynamicmyperson.Dynamic_MypersonActivity;
import com.itislevel.lyl.mvp.ui.family.FamilyAddActivity;
import com.itislevel.lyl.mvp.ui.family.FamilyAddedActivity;
import com.itislevel.lyl.mvp.ui.family.FamilyBlessYuListActivity;
import com.itislevel.lyl.mvp.ui.family.FamilyDetailActivity;
import com.itislevel.lyl.mvp.ui.family.FamilyGiftListActivity;
import com.itislevel.lyl.mvp.ui.family.FamilyHomeActivity;
import com.itislevel.lyl.mvp.ui.family.FamilyLetterActivity;
import com.itislevel.lyl.mvp.ui.family.FamilyMySendGiftRecordActivity;
import com.itislevel.lyl.mvp.ui.family.FamilyReceiveBlessActivity;
import com.itislevel.lyl.mvp.ui.family.FamilyReceiveGiftActivity;
import com.itislevel.lyl.mvp.ui.family.FamilyReceiveSacrificeActivity;
import com.itislevel.lyl.mvp.ui.family.writer_letter.FamilyLetterWriterActivity;
import com.itislevel.lyl.mvp.ui.findmonkey.FindMonkeyActivity;
import com.itislevel.lyl.mvp.ui.funsharing.FunsharingAddActivity;
import com.itislevel.lyl.mvp.ui.funsharing.FunsharingDetailActivity;
import com.itislevel.lyl.mvp.ui.funsharing.FunsharingHomeActivity;
import com.itislevel.lyl.mvp.ui.funsharing.FunsharingListActivity;
import com.itislevel.lyl.mvp.ui.funsharing.FunsharingMyActivity;
import com.itislevel.lyl.mvp.ui.funsharing.FunshingMultipleActivity;
import com.itislevel.lyl.mvp.ui.livingexpensess.LivingPropertyDetailActivity;
import com.itislevel.lyl.mvp.ui.livingexpensess.LivingPropertyQeruyListActivity;
import com.itislevel.lyl.mvp.ui.main.cwebfragactivity.CWebActivity;
import com.itislevel.lyl.mvp.ui.main.cwebfragactivity.CWebAllPinActivity;
import com.itislevel.lyl.mvp.ui.main.dynamic.childfragment.DynamicReceiveGiftActivity;
import com.itislevel.lyl.mvp.ui.main.dynamic.childfragment.message.MessageshouActivity;
import com.itislevel.lyl.mvp.ui.main.home.homeright.HomRightActivity;
import com.itislevel.lyl.mvp.ui.main.mine.fan.PersonChonjiluActivity;
import com.itislevel.lyl.mvp.ui.main.mine.fan.PersonFanActivity;
import com.itislevel.lyl.mvp.ui.main.mine.fan.PersonFanUserDetailAcivity;
import com.itislevel.lyl.mvp.ui.main.mine.fan.PersonMonkeyActivity;
import com.itislevel.lyl.mvp.ui.main.mine.fan.PersonShanActivity;
import com.itislevel.lyl.mvp.ui.main.mine.fan.PersonFanjiluActivity;
import com.itislevel.lyl.mvp.ui.mygift.MyGiftActivity;
import com.itislevel.lyl.mvp.ui.housekeep.HouseKeepActivity;
import com.itislevel.lyl.mvp.ui.housekeep.HouseKeepDetailActivity;
import com.itislevel.lyl.mvp.ui.livingexpensess.LivingCallsCostActivity;
import com.itislevel.lyl.mvp.ui.livingexpensess.LivingExpensesActivity;
import com.itislevel.lyl.mvp.ui.livingexpensess.LivingExpensesPayActivity;
import com.itislevel.lyl.mvp.ui.livingexpensess.LivingExpensesRecordActivity;
import com.itislevel.lyl.mvp.ui.livingexpensess.LivingPropertyActivity;
import com.itislevel.lyl.mvp.ui.livingexpensess.LivingPropertyListActivity;
import com.itislevel.lyl.mvp.ui.livingexpensess.LivingPropertyOrderActivity;
import com.itislevel.lyl.mvp.ui.livingexpensess.LivingPropertyQeruyActivity;
import com.itislevel.lyl.mvp.ui.livingexpensess.LivingWaterQueryActivity;
import com.itislevel.lyl.mvp.ui.main.MainActivity;
import com.itislevel.lyl.mvp.ui.main.customer.ConversationActivity;
import com.itislevel.lyl.mvp.ui.main.customer.CustomerOnlineActivity;
import com.itislevel.lyl.mvp.ui.main.customer.CustomerPhoneActivity;
import com.itislevel.lyl.mvp.ui.myaddress.MyAddressActivity;
import com.itislevel.lyl.mvp.ui.mygift.MyGiftListActivity;
import com.itislevel.lyl.mvp.ui.mymessage.MyMessageActivity;
import com.itislevel.lyl.mvp.ui.myteam.MyTeamActivity;
import com.itislevel.lyl.mvp.ui.myteam.TeamAnswerActivity;
import com.itislevel.lyl.mvp.ui.pay.PayInfoActivity;
import com.itislevel.lyl.mvp.ui.property.PropertLoginActivity;
import com.itislevel.lyl.mvp.ui.property.ProperttPersonActivity;
import com.itislevel.lyl.mvp.ui.property.PropertyAllTonActivity;
import com.itislevel.lyl.mvp.ui.property.PropertyHomeActvity;
import com.itislevel.lyl.mvp.ui.property.bill.PropertyBillActivity;
import com.itislevel.lyl.mvp.ui.property.childfragment.historicalbill.HistoricalBillActivity;
import com.itislevel.lyl.mvp.ui.property.collection.ProCollectionActivity;
import com.itislevel.lyl.mvp.ui.property.complaint.PropertyComplatintActivity;
import com.itislevel.lyl.mvp.ui.property.complaint.complintdetail.ComplaintDetailActivity;
import com.itislevel.lyl.mvp.ui.property.homelist.PropertyHomeListActivity;
import com.itislevel.lyl.mvp.ui.property.homelist.PropertyHomeListDetailActivity;
import com.itislevel.lyl.mvp.ui.property.houselist.PropertyHouseListActivity;
import com.itislevel.lyl.mvp.ui.property.payrecord.PayRecordDetailActivity;
import com.itislevel.lyl.mvp.ui.property.payrecord.PropertyPayRecordActivity;
import com.itislevel.lyl.mvp.ui.property.repair.PropertyRepairActivity;
import com.itislevel.lyl.mvp.ui.property.repair.RepairAddCommentActivity;
import com.itislevel.lyl.mvp.ui.property.repair.RepairAllPinActivity;
import com.itislevel.lyl.mvp.ui.property.repair.RepairDetailActivity;
import com.itislevel.lyl.mvp.ui.setting.AccountSafeActivity;
import com.itislevel.lyl.mvp.ui.setting.AccountSafePreActivity;
import com.itislevel.lyl.mvp.ui.setting.CommonproblemActivity;
import com.itislevel.lyl.mvp.ui.setting.FacebackActivity;
import com.itislevel.lyl.mvp.ui.setting.HelpFeedbackActivity;
import com.itislevel.lyl.mvp.ui.setting.OperationmanualActivity;
import com.itislevel.lyl.mvp.ui.setting.ProbleAndFadebackActivity;
import com.itislevel.lyl.mvp.ui.setting.SettingActivity;
import com.itislevel.lyl.mvp.ui.special.OrderDetailActivity;
import com.itislevel.lyl.mvp.ui.special.OrderRefundDetailActivity;
import com.itislevel.lyl.mvp.ui.special.OrderRefundShenQingActivity;
import com.itislevel.lyl.mvp.ui.special.OrderServiceActivity;
import com.itislevel.lyl.mvp.ui.special.SpecialCartActivity;
import com.itislevel.lyl.mvp.ui.special.SpecialConfirmDownOrderActivity;
import com.itislevel.lyl.mvp.ui.special.SpecialGiftActivity;
import com.itislevel.lyl.mvp.ui.special.SpecialGiftDetailActivity;
import com.itislevel.lyl.mvp.ui.special.SpecialGiftListActivity;
import com.itislevel.lyl.mvp.ui.special.SpecialHomeActivity;
import com.itislevel.lyl.mvp.ui.special.SpecialSpecialActivity;
import com.itislevel.lyl.mvp.ui.special.SpecialSpecialListActivity;
import com.itislevel.lyl.mvp.ui.team.TeamAgreementActivity;
import com.itislevel.lyl.mvp.ui.team.TeamHomeActivity;
import com.itislevel.lyl.mvp.ui.team.TeamListActivity;
import com.itislevel.lyl.mvp.ui.team.TeamListMyActivity;
import com.itislevel.lyl.mvp.ui.team.TeamSupplementActivity;
import com.itislevel.lyl.mvp.ui.team.TeamWaitingForVerifyActivity;
import com.itislevel.lyl.mvp.ui.test.ScrollerRecyclerviewActivity;
import com.itislevel.lyl.mvp.ui.test.TestActivity;
import com.itislevel.lyl.mvp.ui.splash.SplashActivity;
import com.itislevel.lyl.mvp.ui.test.TestPlayerActivity;
import com.itislevel.lyl.mvp.ui.troublesolution.TroubleAdviserActivity_back;
import com.itislevel.lyl.mvp.ui.troublesolution.TroubleAdviserAddActivity;
import com.itislevel.lyl.mvp.ui.troublesolution.TroubleAdviserDescActivity;
import com.itislevel.lyl.mvp.ui.troublesolution.TroubleAdviserMyActivity;
import com.itislevel.lyl.mvp.ui.troublesolution.TroubleNormalAddActivity;
import com.itislevel.lyl.mvp.ui.troublesolution.TroubleNormalMyActivity;
import com.itislevel.lyl.mvp.ui.troublesolution.TroublesolutionActivity;

import com.itislevel.lyl.mvp.ui.troublesolution.TroublesolutionListActivity;
import com.itislevel.lyl.mvp.ui.troublesolution.TroublesolutionSelectActivity;
import com.itislevel.lyl.mvp.ui.user.LoginActivity;
import com.itislevel.lyl.mvp.ui.user.QRCodeActivity;
import com.itislevel.lyl.mvp.ui.user.RegisterActivity;
import com.itislevel.lyl.mvp.ui.user.ResetPasswordActivity;
import com.itislevel.lyl.mvp.ui.user.CompleteUserInfoActivity;
import com.itislevel.lyl.mvp.ui.user.UpdateHeaderActivity;
import com.itislevel.lyl.mvp.ui.user.UpdatePasswordActivity;
import com.itislevel.lyl.mvp.ui.user.UpdateUserInfoActivity;
import com.itislevel.lyl.mvp.ui.userfan.UserFanActivity;
import com.itislevel.lyl.mvp.ui.userfan.UserFanDetailActivity;
import com.itislevel.lyl.mvp.ui.userfan.UserFanHistoryActivity;
import com.itislevel.lyl.mvp.ui.userfan.UserPlanActivity;
import com.itislevel.lyl.mvp.ui.usermonkey.UserAllMonkeyActivity;
import com.itislevel.lyl.mvp.ui.usermonkey.UserMonkeyQActivity;
import com.itislevel.lyl.mvp.ui.usermonkey.UserMonkeyShouActivity;
import com.itislevel.lyl.mvp.ui.usermonkey.UserMonkeyTiXianActivity;
import com.itislevel.lyl.mvp.ui.usermonkey.putrecord.PutRecordActivity;
import com.itislevel.lyl.mvp.ui.usermonkey.putrecord.PutRecordDetailActivity;
import com.itislevel.lyl.mvp.ui.usermonkey.setting.SettingChangePwdActivity;
import com.itislevel.lyl.mvp.ui.usermonkey.setting.SettingForgeActivity;
import com.itislevel.lyl.mvp.ui.usermonkey.setting.SettingUserDataActivity;
import com.itislevel.lyl.mvp.ui.usermonkey.setting.SettingUserDataNextActivity;
import com.itislevel.lyl.mvp.ui.usermonkey.setting.UserMonkeySettingActivity;

import dagger.Component;

/**
 * **********************
 * 功 能:为activity 注入依赖
 * 创建人:itisi
 * 邮  箱:itisivip@qq.com
 * 创建时间:2017/7/5 17:43
 * 修改人:itisi
 * 修改时间: 2017/7/5 17:43
 * 修改内容:itisi
 * *********************
 */
@ActivityScope
@Component(dependencies = {AppComponent.class}, modules = ActivityModule.class)
public interface ActivityComponent {
    Activity getActivity();

    void inject(MainActivity activity);

    void inject(SplashActivity activity);

    //测试页面
    void inject(TestActivity activity);

    void inject(TestPlayerActivity activity);
    void inject(ScrollerRecyclerviewActivity activity);

    //h后续创建的activity 接着往下写


    //用户
    void inject(LoginActivity activity);

    void inject(RegisterActivity activity);

    void inject(ResetPasswordActivity activity);

    void inject(CompleteUserInfoActivity activity);

    void inject(UpdateUserInfoActivity activity);

    void inject(UpdateHeaderActivity activity);

    void inject(UpdatePasswordActivity activity);

    //乐趣分享
    void inject(FunsharingHomeActivity activity);

    void inject(FunsharingAddActivity activity);

    void inject(FunsharingMyActivity activity);

    void inject(FunshingMultipleActivity activity);

    void inject(FunsharingListActivity activity);

    void inject(FunsharingDetailActivity activity);


    //在线客服
    void inject(CustomerPhoneActivity activity);

    void inject(CustomerOnlineActivity activity);

    void inject(ConversationActivity activity);


    //我的
    void inject(MyGiftActivity activity);

    void inject(MyGiftListActivity activity);

    void inject(MyMessageActivity activity);

    void inject(MyAddressActivity activity);

    void inject(MyTeamActivity activity);

    void inject(TeamAnswerActivity activity);

    void inject(AboutActivity activity);

    void inject(SettingActivity activity);

    void inject(QRCodeActivity activity);

    void inject(AccountSafeActivity activity);

    void inject(HelpFeedbackActivity activity);

    void inject(AccountSafePreActivity activity);

    void inject(FacebackActivity activity);

    void inject(ProbleAndFadebackActivity activity);
    void inject(CommonproblemActivity activity);
    void inject(OperationmanualActivity activity);

    //地址选择列表
    void inject(ProvinceActivity activity);

    void inject(CityActivity activity);

    void inject(CountyActivity activity);

    //烦恼解答
    void inject(TroublesolutionActivity activity);

    void inject(TroublesolutionSelectActivity activity);

    void inject(TroubleNormalAddActivity activity);

    void inject(TroubleAdviserActivity_back activity);

    void inject(TroublesolutionListActivity activity);

    void inject(TroubleNormalMyActivity activity);

    void inject(TroubleAdviserMyActivity activity);

    void inject(TroubleAdviserAddActivity activity);

    void inject(TroubleAdviserDescActivity activity);

    //生活缴费
    void inject(LivingExpensesActivity activity);

    void inject(LivingExpensesRecordActivity activity);

    void inject(LivingWaterQueryActivity activity);

    void inject(LivingExpensesPayActivity activity);

    void inject(LivingPropertyActivity activity);

    void inject(LivingPropertyQeruyActivity activity);

    void inject(LivingPropertyListActivity activity);

    void inject(LivingPropertyOrderActivity activity);

    void inject(LivingCallsCostActivity activity);

    void inject(LivingPropertyDetailActivity activity);
    void inject(LivingPropertyQeruyListActivity activity);

    //家政服务
    void inject(HouseKeepActivity activity);

    void inject(HouseKeepDetailActivity activity);

    //顾问团队
    void inject(TeamHomeActivity activity);

    void inject(TeamListActivity activity);

    void inject(TeamAgreementActivity activity);

    void inject(TeamListMyActivity activity);

    void inject(TeamSupplementActivity activity);

    void inject(TeamWaitingForVerifyActivity activity);


    //礼品特产
    void inject(SpecialHomeActivity activity);

    void inject(SpecialGiftActivity activity);

    void inject(SpecialSpecialActivity activity);

    void inject(SpecialSpecialListActivity activity);

    void inject(SpecialGiftListActivity activity);

    void inject(SpecialGiftDetailActivity activity);

    void inject(SpecialCartActivity activity);

    void inject(SpecialConfirmDownOrderActivity activity);

    void inject(OrderDetailActivity activity);

    void inject(OrderRefundDetailActivity activity);

    void inject(OrderRefundShenQingActivity activity);

    void inject(OrderServiceActivity activity);


    //亲情祭祀
    void inject(FamilyHomeActivity activity);

    void inject(FamilyAddActivity activity);

    void inject(FamilyAddedActivity activity);

    void inject(FamilyDetailActivity activity);

    void inject(FamilyMySendGiftRecordActivity activity);

    void inject(FamilyReceiveBlessActivity activity);

    void inject(FamilyReceiveSacrificeActivity activity);

    void inject(FamilyBlessYuListActivity activity);

    void inject(FamilyGiftListActivity activity);
    void inject(FamilyReceiveGiftActivity activity);


    //喜事祝福
    void inject(BlessingHomeActivity activity);

    void inject(BlessingAddActivity activity);

    void inject(BlessingDetailActivity activity);

    void inject(BlessingMyActivity activity);

    void inject(BlessingSendRecordActivity activity);

    void inject(BlessReceiveBlessGiftActivity activity);

    void inject(BlessReceiveBlessYuActivity activity);
    void inject(BlessReceiveGiftActivity activity);

    //支付相关页面
    void inject(PayInfoActivity activity);

    //动态个人中心的界面
    void inject(Dynamic_MypersonActivity activity);
    void inject(DynamicReceiveGiftActivity activity);
    //祭事信

    void inject(FamilyLetterActivity activity);


    void inject(FamilyLetterWriterActivity activity);

    void inject(CWebActivity activity);
    void inject(CWebAllPinActivity activity);

    //物业模块
    void inject(PropertyAllTonActivity activity);
    void inject(ProperttPersonActivity activity);
    void inject(PropertLoginActivity activity);
    void inject(PropertyHomeListActivity actvity);//房屋列表
    void inject(PropertyHouseListActivity activity);//物业缴费的房屋列表
    void inject(PropertyBillActivity  activity);//物业缴费的房屋列表
    void inject(PropertyHomeListDetailActivity activity);//房屋列表的详情信息
    void inject(PropertyHomeActvity actvity);
    void inject(PropertyComplatintActivity activity);//投诉模块
    void inject(ComplaintDetailActivity activity);//投诉详情的查看

    void inject(PropertyPayRecordActivity activity);//缴费记录
    void inject(PayRecordDetailActivity activity);

    //维修模块
    void inject(PropertyRepairActivity activity);
    //维修的详情

    void inject(RepairDetailActivity activity);

    void inject(RepairAddCommentActivity activity);

    void inject(RepairAllPinActivity activity);
    void inject(ProCollectionActivity activity);

    void inject(HomRightActivity activity);

    void inject(MessageshouActivity activity);

    void inject(HistoricalBillActivity activity);

    //商品返现模块
    void inject(FindMonkeyActivity activity);

    void inject(PersonFanActivity activity);

    void inject(PersonFanUserDetailAcivity acivity);

    void inject(FanxianLoginActivity activity);

    void inject(PersonShanActivity activity);

    void inject(PersonFanjiluActivity activity);
    void inject(PersonChonjiluActivity activity);
    void inject(PersonMonkeyActivity activity);

    //用户返现
    void inject(UserFanActivity activity);
    void inject(UserFanHistoryActivity activity);
    void inject(UserPlanActivity activity);
    void  inject(UserFanDetailActivity activity);

    //用户钱包模块
    void  inject(UserMonkeyQActivity activity);
    void inject(UserAllMonkeyActivity activity);//全额体现
    void  inject(UserMonkeyShouActivity activity);//收支明细
    void inject(UserMonkeyTiXianActivity activity);

    //用户钱包提现记录
    void inject(PutRecordActivity activity);
    void inject(PutRecordDetailActivity activity);

    //设置模块
    void inject(UserMonkeySettingActivity activity);

    void inject(SettingUserDataActivity activity);//认证信息
    void  inject(SettingUserDataNextActivity activity);//认证之后输入密码
    void inject(SettingChangePwdActivity activity);//修改支付密码
    void inject(SettingForgeActivity activity);
}
