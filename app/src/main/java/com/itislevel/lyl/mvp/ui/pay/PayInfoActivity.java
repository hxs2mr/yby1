package com.itislevel.lyl.mvp.ui.pay;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.itislevel.lyl.R;
import com.itislevel.lyl.app.Constants;
import com.itislevel.lyl.base.RootActivity;
import com.itislevel.lyl.mvp.model.bean.BlessOrderBean;
import com.itislevel.lyl.mvp.model.bean.PayALiResultBean;
import com.itislevel.lyl.mvp.model.bean.PayBillBean;
import com.itislevel.lyl.mvp.model.bean.PropertyUpdateStatusBean;
import com.itislevel.lyl.mvp.model.bean.WeiXinPayTestBean;
import com.itislevel.lyl.mvp.ui.blessing.BlessingDetailActivity;
import com.itislevel.lyl.mvp.ui.family.FamilyAddActivity;
import com.itislevel.lyl.mvp.ui.family.FamilyDetailActivity;
import com.itislevel.lyl.mvp.ui.family.FamilyGiftListActivity;
import com.itislevel.lyl.mvp.ui.livingexpensess.LivingExpensesPayActivity;
import com.itislevel.lyl.mvp.ui.livingexpensess.LivingPropertyListActivity;
import com.itislevel.lyl.mvp.ui.property.bill.PropertyBillActivity;
import com.itislevel.lyl.mvp.ui.special.SpecialCartActivity;
import com.itislevel.lyl.mvp.ui.special.SpecialConfirmDownOrderActivity;
import com.itislevel.lyl.mvp.ui.special.SpecialGiftDetailActivity;
import com.itislevel.lyl.mvp.ui.special.SpecialOrderActivity;
import com.itislevel.lyl.mvp.ui.team.TeamListActivity;
import com.itislevel.lyl.mvp.ui.troublesolution.TroubleAdviserMyActivity;
import com.itislevel.lyl.utils.ActivityUtil;
import com.itislevel.lyl.utils.DecimalUtils;
import com.itislevel.lyl.utils.GsonUtil;
import com.itislevel.lyl.utils.SAToast;
import com.itislevel.lyl.utils.SharedPreferencedUtils;
import com.itislevel.lyl.utils.ToastUtil;
import com.itislevel.lyl.utils.rxbus.RxBus;
import com.itislevel.lyl.utils.rxbus.annotation.Subscribe;
import com.itislevel.lyl.utils.rxbus.annotation.UseRxBus;
import com.itislevel.lyl.utils.rxbus.event.EventThread;
import com.orhanobut.logger.Logger;
import com.vondear.rxtools.interfaces.OnRequestListener;
import com.vondear.rxtools.module.alipay.AliPayTools;
import com.vondear.rxtools.module.wechat.pay.WechatPayTools;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 支付确认页面
 * 选择支付方式页面
 */
@UseRxBus
public class PayInfoActivity extends RootActivity<PayInfoPresenter> implements
        PayInfoContract.View {

    Bundle bundle = null;

    String totalPrice = "";
    String goodsDesc = "";
    String goodsDetail = "";

    @BindView(R.id.tv_totalprice)
    TextView tv_totalprice;

    @BindView(R.id.tv_desc)
    TextView tv_desc;

    @BindView(R.id.tv_pay_weixin)
    TextView tv_pay_weixin;
    @BindView(R.id.tv_pay_alipay)
    TextView tv_pay_alipay;
    @BindView(R.id.tv_pay_yinlian)
    TextView tv_pay_yinlian;

    @BindView(R.id.iv_pay_weixin)
    ImageView iv_pay_weixin;
    @BindView(R.id.iv_pay_alipay)
    ImageView iv_pay_alipay;
    @BindView(R.id.iv_pay_yinlian)
    ImageView iv_pay_yinlian;

    /**
     * 支付
     */
    int selectPos = 1;// 0微信支付 1支付宝支付 2银联支付

    private int PAY_FROM_ACTIVITY = 0;//来自于哪个界面

    boolean iswxpaying = false;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_pay_info;
    }

    @Override
    protected void initEventAndData() {
        bundle = getIntent().getExtras();

        setToolbarTvTitle("支付");

        totalPrice = bundle.getString(Constants.PAY_TOTALPRICE);
        goodsDetail = bundle.getString(Constants.PAY_GOODS_DETAIL);
        goodsDesc = bundle.getString(Constants.PAY_GOODS_DESC);

        PAY_FROM_ACTIVITY = bundle.getInt(Constants.PAY_FROM_ACTIVITY);

        tv_desc.setText(goodsDesc);
        tv_totalprice.setText(DecimalUtils.format2(Double.parseDouble(totalPrice)));

    }


    @OnClick({R.id.tv_pay_weixin, R.id.tv_pay_alipay, R.id.tv_pay_yinlian, R.id.btn_pay})
    public void click(View view) {

        switch (view.getId()) {
            case R.id.tv_pay_weixin:
                setSelectNone();
                selectPos = 0;
                iv_pay_weixin.setVisibility(View.VISIBLE);
                break;
            case R.id.tv_pay_alipay:
                setSelectNone();
                selectPos = 1;
                iv_pay_alipay.setVisibility(View.VISIBLE);
                break;
            case R.id.tv_pay_yinlian:
                setSelectNone();
                selectPos = 2;
                iv_pay_yinlian.setVisibility(View.VISIBLE);
                break;
            case R.id.btn_pay:
                loadingDialog.show();
                generateOrder();
                break;
        }
    }


    private void generateOrder() {
        switch (selectPos) {
            case 0:
                wximmediateorder();
                break;
            case 1:
                alipayGenerate(); //喜事 礼品的购买
                break;
            case 2:
                ToastUtil.Info("银联");
                break;
        }
    }


    private void wximmediateorder() {
        iswxpaying = true;
        loadingDialog.show();
        Map<String, String> request = new HashMap<>();
        request.put("token", SharedPreferencedUtils.getStr(Constants.USER_TOKEN));
        request.put("usernum", SharedPreferencedUtils.getStr(Constants.USER_NUM));
        request.put("ordernum", bundle.getString(Constants.PAY_ORDERNUM));
        request.put("modelename", bundle.getString(Constants.PAY_MODULE_NAME));
        mPresenter.weixinPayTest(GsonUtil.obj2JSON(request))    ;
    }

    private void alipayGenerate() {
        iswxpaying = false;
        Map<String, String> request = new HashMap<>();
        request.put("token", SharedPreferencedUtils.getStr(Constants.USER_TOKEN));
        request.put("usernum", SharedPreferencedUtils.getStr(Constants.USER_NUM));
        request.put("ordernum", bundle.getString(Constants.PAY_ORDERNUM));
        request.put("modelename", bundle.getString(Constants.PAY_MODULE_NAME));
        mPresenter.alipayList(GsonUtil.obj2JSON(request));

    }


    private void setSelectNone() {
        iv_pay_alipay.setVisibility(View.GONE);
        iv_pay_weixin.setVisibility(View.GONE);
        iv_pay_yinlian.setVisibility(View.GONE);
    }

    @Override
    public void showContent(String msg) {

    }

    @Override
    public void weixinPayTest(WeiXinPayTestBean testBean) {

        loadingDialog.dismiss();
        Logger.w("订单号:" + testBean.getOrderinfo());
        WechatPayTools.doWXPay(this, testBean.getOrderinfo().getAppid(), GsonUtil.obj2JSON
                (testBean.getOrderinfo()), new
                OnRequestListener() {

                    @Override
                    public void onSuccess(String message) {
                        int itisi = 1;
                    }

                    @Override
                    public void onError(String s) {
                        int a = 0;
                        ToastUtil.Info(s);
                    }
                });
    }

    @Override
    public void alipayList(BlessOrderBean blessOrderBean) {

        loadingDialog.dismiss();

        if( blessOrderBean.getOrderinfo()==null)
        {
            ToastUtil.Info("服务器异常");
            return;
        }
        AliPayTools.aliPay(PayInfoActivity.this, blessOrderBean.getOrderinfo(), new
                OnRequestListener() {
                    @Override
                    public void onSuccess(String msg) {

                        //通知相关界面
                        switch (PAY_FROM_ACTIVITY) {
                            case Constants.PAY_FROM_HAPPY_GIFT://来自于喜事礼物

                                RxBus.getInstance().post(RxBus.getInstance().getTag
                                        (BlessingDetailActivity.class,
                                                RxBus.TAG_UPDATE), "refresh");

                                break;
                            case Constants.PAY_FROM_TROUBLE_ADD://来自于烦恼提问

                                RxBus.getInstance().post(RxBus.getInstance().getTag
                                        (TeamListActivity.class,
                                                RxBus.TAG_UPDATE), "close");


                                bundle.putString(Constants.TROUBLE_TITLE, "官司咨询");
                                ActivityUtil.getInstance().openActivity(PayInfoActivity.this,
                                        TroubleAdviserMyActivity
                                                .class, bundle);

                                break;
                            case Constants.PAY_FROM_FETE_GIFT://来自于祭祀礼品
//                                //关闭礼品页面
                                RxBus.getInstance().post(RxBus.getInstance().getTag
                                        (FamilyGiftListActivity.class,
                                                RxBus.TAG_UPDATE), "test");

                                //详情刷新 并播放音乐
                                RxBus.getInstance().post(RxBus.getInstance().getTag
                                        (FamilyDetailActivity.class,
                                                RxBus.TAG_UPDATE), "itisibuy");
                                break;
                            case Constants.PAY_FROM_FETE_PHTO://来自于祭祀相框-背景
                                RxBus.getInstance().post(RxBus.getInstance().getTag
                                        (FamilyAddActivity.class,
                                                RxBus.TAG_UPDATE), bundle.getString(Constants
                                        .PAY_ORDERNUM));

                                break;
                            case Constants.PAY_FROM_LIVE_PROPERTY://来自于生活交费-物业
                                //关闭界面

                                EventBus.getDefault().post(new PayBillBean("success"));
                              //java.lang.NullPointerException: Attempt to invoke virtual method 'int java.lang.Integer.intValue()' on a null object reference
                            /*    RxBus.getInstance().post(RxBus.getInstance().getTag
                                        (PropertyBillActivity.class, RxBus.TAG_UPDATE), "close");*/

                            //这里按照下面的来写
                                //通知刷新
                              //  RxBus.getInstance().post(RxBus.getInstance().getTag
                              //          (LivingPropertyListActivity.class,
                              //                  RxBus.TAG_UPDATE), "refresh");
                                break;

                            case Constants.PAY_FROM_SPECIAL_GIFT://特产礼品
                                boolean isfromCart = bundle.getBoolean("isfromCart");
                                if (isfromCart) {
                                    RxBus.getInstance().post(RxBus.getInstance().getTag
                                            (SpecialCartActivity.class,
                                                    RxBus.TAG_UPDATE), "close");

                                }

                                RxBus.getInstance().post(RxBus.getInstance().getTag
                                        (SpecialConfirmDownOrderActivity.class,
                                                RxBus.TAG_UPDATE), "close");

                                RxBus.getInstance().post(RxBus.getInstance().getTag
                                        (SpecialGiftDetailActivity.class,
                                                RxBus.TAG_UPDATE), "close");

                                //跳转订单页面
                                ActivityUtil.getInstance().openActivity(PayInfoActivity.this,
                                        SpecialOrderActivity.class);
                                break;

                            case Constants.PAY_FROM_SPECIAL_ORDER://特产订单列表

                                RxBus.getInstance().post(RxBus.getInstance().getTag
                                        (SpecialOrderActivity.class,
                                                RxBus.TAG_UPDATE), "refresh");

                                break;

                        }

                        //关闭当前界面
                        ActivityUtil.getInstance().closeActivity(PayInfoActivity.this);
                    }


                    @Override
                    public void onError(String s) {
                        Logger.w("error:" + s);
                        ToastUtil.Info("支付已取消");

                    }
                });

    }

    @Override
    public void stateEmpty() {

    }

    @Override
    public void stateLoading() {

    }


    @Override
    public void stateSuccess() {

    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Subscribe(tag = RxBus.TAG_UPDATE, thread = EventThread.MAIN_THREAD)
    public void rxrefresh(String msg) {
        ToastUtil.Info("wx:" + msg);
        int a = 0;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (iswxpaying) {

            int wx_pay_code = SharedPreferencedUtils.getInt("wx_pay_code", -3);
            switch (wx_pay_code) {
                case -2:
                    ToastUtil.Info("取消微信支付");
                    break;
                case -1:
                    ToastUtil.Info("参数错误");
                    break;
                case 0:
                    //通知相关界面
                    switch (PAY_FROM_ACTIVITY) {
                        case Constants.PAY_FROM_HAPPY_GIFT://来自于喜事礼物

                            RxBus.getInstance().post(RxBus.getInstance().getTag
                                    (BlessingDetailActivity.class,
                                            RxBus.TAG_UPDATE), "refresh");

                            break;
                        case Constants.PAY_FROM_TROUBLE_ADD://来自于烦恼提问

                            RxBus.getInstance().post(RxBus.getInstance().getTag
                                    (TeamListActivity.class,
                                            RxBus.TAG_UPDATE), "close");



                            bundle.putString(Constants.TROUBLE_TITLE, "官司咨询");
                            ActivityUtil.getInstance().openActivity(PayInfoActivity.this,
                                    TroubleAdviserMyActivity
                                            .class, bundle);

                            break;
                        case Constants.PAY_FROM_FETE_GIFT://来自于祭祀礼品
//                                //关闭礼品页面
                            RxBus.getInstance().post(RxBus.getInstance().getTag
                                    (FamilyGiftListActivity.class,
                                            RxBus.TAG_UPDATE), "test");

                            //详情刷新 并播放音乐
                            RxBus.getInstance().post(RxBus.getInstance().getTag
                                    (FamilyDetailActivity.class,
                                            RxBus.TAG_UPDATE), "itisibuy");
                            break;
                        case Constants.PAY_FROM_FETE_PHTO://来自于祭祀相框-背景
                            RxBus.getInstance().post(RxBus.getInstance().getTag
                                    (FamilyAddActivity.class,
                                            RxBus.TAG_UPDATE), bundle.getString(Constants
                                    .PAY_ORDERNUM));

                            break;
                        case Constants.PAY_FROM_LIVE_PROPERTY://来自于生活交费-物业
                            //关闭界面
                            EventBus.getDefault().post(new PayBillBean("success"));
                         /*   RxBus.getInstance().post(RxBus.getInstance().getTag
                                    (LivingExpensesPayActivity.class,
                                            RxBus.TAG_UPDATE), "close");

                            //通知刷新
                            RxBus.getInstance().post(RxBus.getInstance().getTag
                                    (LivingPropertyListActivity.class,
                                            RxBus.TAG_UPDATE), "refresh");*/
                            break;
                        case Constants.PAY_FROM_SPECIAL_GIFT://特产礼品
                            boolean isfromCart = bundle.getBoolean("isfromCart");
                            if (isfromCart) {
                                RxBus.getInstance().post(RxBus.getInstance().getTag
                                        (SpecialCartActivity.class,
                                                RxBus.TAG_UPDATE), "close");

                            }
                            RxBus.getInstance().post(RxBus.getInstance().getTag
                                    (SpecialConfirmDownOrderActivity.class,
                                            RxBus.TAG_UPDATE), "close");

                            RxBus.getInstance().post(RxBus.getInstance().getTag
                                    (SpecialGiftDetailActivity.class,
                                            RxBus.TAG_UPDATE), "close");

                            //跳转订单页面

                            ActivityUtil.getInstance().openActivity(PayInfoActivity.this,
                                    SpecialOrderActivity.class);
                            break;

                    }

                    //关闭当前界面
                    ActivityUtil.getInstance().closeActivity(PayInfoActivity.this);

                    break;
            }
            SharedPreferencedUtils.setInt("wx_pay_code", -3);
        }

    }
}
