package com.itislevel.lyl.mvp.ui.special;

import android.graphics.Color;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.flyco.animation.SlideEnter.SlideBottomEnter;
import com.flyco.dialog.listener.OnBtnClickL;
import com.flyco.dialog.widget.NormalDialog;
import com.itislevel.lyl.R;
import com.itislevel.lyl.app.App;
import com.itislevel.lyl.app.Constants;
import com.itislevel.lyl.base.RootActivity;
import com.itislevel.lyl.mvp.model.bean.BlessCartListBean;
import com.itislevel.lyl.mvp.model.bean.CartUpdateBean;
import com.itislevel.lyl.mvp.model.bean.FileUploadBean;
import com.itislevel.lyl.mvp.model.bean.SpecialGiftByIdBean;
import com.itislevel.lyl.mvp.model.bean.SpecialGiftListBean;
import com.itislevel.lyl.mvp.model.bean.SpecialOrderCompleteBean;
import com.itislevel.lyl.mvp.model.bean.SpecialOrderDetailBean;
import com.itislevel.lyl.mvp.model.bean.SpecialReceiveAddressBean;
import com.itislevel.lyl.mvp.model.bean.SpecialReturnBean;
import com.itislevel.lyl.mvp.model.bean.SpecialTuiKuanDetailBean;
import com.itislevel.lyl.mvp.model.bean.SpecialTuiKuanUpdateBean;
import com.itislevel.lyl.mvp.model.bean.SpecialTypeBean;
import com.itislevel.lyl.mvp.ui.main.customer.CustomerPhoneActivity;
import com.itislevel.lyl.utils.ActivityUtil;
import com.itislevel.lyl.utils.DateUtil;
import com.itislevel.lyl.utils.GsonUtil;
import com.itislevel.lyl.utils.SharedPreferencedUtils;
import com.itislevel.lyl.utils.ToastUtil;
import com.itislevel.lyl.utils.imageload.ImageLoadConfiguration;
import com.itislevel.lyl.utils.imageload.ImageLoadProxy;
import com.itislevel.lyl.utils.rxbus.annotation.UseRxBus;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 推脱退款详情页面
 */
@UseRxBus
public class OrderRefundDetailActivity extends RootActivity<SpecialPresenter>
        implements SpecialContract.View {


    Bundle bundle;

    @BindView(R.id.iv_logo)
    ImageView iv_logo;

    @BindView(R.id.tv_goodsname)
    TextView tv_goodsname;

    @BindView(R.id.tv_reason)
    TextView tv_reason;

    @BindView(R.id.tv_tuikuanmoney)
    TextView tv_tuikuanmoney;

    @BindView(R.id.tv_applytime)
    TextView tv_applytime;

    @BindView(R.id.tv_ordernum)
    TextView tv_ordernum;

    @BindView(R.id.tv_result)
    TextView tv_result;

    @BindView(R.id.tv_endtime)
    TextView tv_endtime;

    @BindView(R.id.tv_tip1)
    TextView tv_tip1;
    @BindView(R.id.tv_tip2)
    TextView tv_tip2;
    @BindView(R.id.tv_tip3)
    TextView tv_tip3;


    private SpecialReturnBean.ListBean listBean;



    @Override
    protected int getLayoutId() {
        return R.layout.activity_order_refund_detail;
    }

    @Override
    protected void initEventAndData() {

        bundle = getIntent().getExtras();

        setToolbarTvTitle("退款详情");

        String goodsitem = bundle.getString("goodsitem");
        listBean = GsonUtil.toObject(goodsitem, SpecialReturnBean
                .ListBean.class);

//        ImageLoadProxy.getInstance()
//                .load(new ImageLoadConfiguration.Builder(App.getInstance())
//                        .defaultImageResId(R.mipmap.icon_img_load_pre)
//                        .url(Constants.IMG_SERVER_PATH + listBean.getLogourl())
//                        .imageView(iv_logo).build());
//
//        tv_goodsname.setText(listBean.getGoodsname());
//        tv_reason.setText("退款原因:"+listBean.getRefundreason().toString());
//        tv_ordernum.setText("退款编号:"+listBean.getOrdernum());
//        tv_applytime.setText("申请时间:"+DateUtil.timeSpanToDateTime1(listBean.getApplytime()));
//        tv_tuikuanmoney.setText("退款金额:￥"+listBean.getRefundfee());


        loadData();


    }

    private void loadData() {
        loadingDialog.show();
        Map<String, String> request = new HashMap<>();
        request.put("token", SharedPreferencedUtils.getStr(Constants.USER_TOKEN));
        request.put("usernum", SharedPreferencedUtils.getStr(Constants.USER_NUM));
        request.put("orderid", listBean.getOrderid());

        mPresenter.specialTuiKuanDetail(GsonUtil.obj2JSON(request));


    }

    SlideBottomEnter mBasIn = new SlideBottomEnter();


    @OnClick({R.id.tv_contcat, R.id.tv_chexiao, R.id.tv_edit})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.tv_contcat:
                ActivityUtil.getInstance().openActivity(this, CustomerPhoneActivity.class);

                break;
            case R.id.tv_chexiao:

                NormalDialog dialog = new NormalDialog(this);
                dialog.content("您确定要撤销退款申请吗?")//
                        .isTitleShow(false)
                        .contentGravity(Gravity.CENTER)//
                        .showAnim(mBasIn)//
//                        .dismissAnim(mBasIn)//
                        .btnTextColor(Color.parseColor("#000000"), Color.parseColor("#cd3333"))//
//                        .btnPressColor(Color.parseColor("#ff0000"))//
                        .show();

                dialog.setOnBtnClickL(new OnBtnClickL() {
                    @Override
                    public void onBtnClick() {
                        ToastUtil.Info("已取消");
                        dialog.dismiss();
                    }
                }, new OnBtnClickL() {
                    @Override
                    public void onBtnClick() {

                        chexiaoRefund();

                        dialog.dismiss();
                    }
                });

                break;
            case R.id.tv_edit:


                bundle.putBoolean("isFirstApply",false);
                bundle.putString("ordernum",listBean.getOrdernum());
                bundle.putString("goodsid",listBean.getGoodsid()+"");
                bundle.putString("reftype",listBean.getReftype());
                bundle.putString("count",listBean.getCount()+"");

                ActivityUtil.getInstance().openActivity(this, OrderRefundShenQingActivity.class,bundle);


                break;


        }
    }

    private void chexiaoRefund() {

        loadingDialog.show();

        Map<String, String> request = new HashMap<>();
        request.put("token", SharedPreferencedUtils.getStr(Constants.USER_TOKEN));
        request.put("usernum", SharedPreferencedUtils.getStr(Constants.USER_NUM));
        request.put("orderid", listBean.getOrderid());
        request.put("ordernum", listBean.getOrdernum());

        mPresenter.specialTuiKuan(GsonUtil.obj2JSON(request));

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

    @Override
    public void showContent(String msg) {

    }

    @Override
    public void specialType(SpecialTypeBean specialTypeBean) {

    }

    @Override
    public void specialList(SpecialGiftListBean specialListBean) {

    }

    @Override
    public void specialById(SpecialGiftByIdBean specialByIdBean) {

    }

    @Override
    public void specialImmediatelyOrder(String action) {

    }

    @Override
    public void specialShopOrder(String action) {

    }

    @Override
    public void specialReceiveAddress(SpecialReceiveAddressBean addressBean) {

    }

    @Override
    public void specialOrderDetail(SpecialOrderDetailBean detailBean) {

    }

//    R.id.tv_contcat, R.id.tv_chexiao, R.id.tv_edit

    @BindView(R.id.tv_contcat)
    TextView tv_contcat;

    @BindView(R.id.tv_chexiao)
    TextView tv_chexiao;

    @BindView(R.id.tv_edit)
    TextView tv_edit;

    @Override
    public void specialTuiKuanDetail(SpecialTuiKuanDetailBean tuiKuanDetailBean) {

        loadingDialog.dismiss();

        SpecialTuiKuanDetailBean.ListBean listBean2 = tuiKuanDetailBean.getList().get(0);

        ImageLoadProxy.getInstance()
                .load(new ImageLoadConfiguration.Builder(App.getInstance())
                        .defaultImageResId(R.mipmap.icon_img_load_pre)
                        .url(Constants.IMG_SERVER_PATH + listBean2.getLogourl())
                        .imageView(iv_logo).build());

        tv_goodsname.setText(listBean2.getGoodsname());
        tv_reason.setText("退款原因:" + listBean2.getRefundreason().toString());
        tv_ordernum.setText("退款编号:" + listBean2.getOrdernum());
        tv_applytime.setText("申请时间:" + DateUtil.timeSpanToDateTime1(listBean2.getApplytime()));
        tv_tuikuanmoney.setText("退款金额:￥" + listBean2.getRefundfee());

        // 退款状态  (400.申请退款  401.同意申请  402.拒绝申请  403.同意退款 404.退款成功)
        //  tv_result tv_endtime
        String rstatus = listBean2.getRstatus();
        int sta = Integer.parseInt(rstatus);
        switch (sta) {
            case 400:
                tv_result.setText("请等待商家处理");
                // listBean2.getApplytime()
                String date = DateUtil.formatDate3String(System.currentTimeMillis(),
                        listBean2.getLastRefundTime());
                tv_endtime.setText(date);

                tv_tip1.setVisibility(View.VISIBLE);
                tv_tip2.setVisibility(View.VISIBLE);
                tv_tip3.setVisibility(View.GONE);
                break;
            case 401:
                tv_result.setText("商家已受理");
                tv_endtime.setText(DateUtil.timeSpanToDateTime(listBean2.getLastRefundTime()));

                tv_edit.setVisibility(View.GONE);
                tv_chexiao.setVisibility(View.GONE);

                tv_tip1.setVisibility(View.VISIBLE);
                tv_tip2.setVisibility(View.VISIBLE);
                tv_tip3.setVisibility(View.GONE);

                break;
            case 402:
                tv_result.setText("商家拒绝申请");
                tv_endtime.setText(DateUtil.timeSpanToDateTime(listBean2.getLastRefundTime()));

                tv_tip1.setVisibility(View.GONE);
                tv_tip2.setVisibility(View.GONE);
                tv_tip3.setVisibility(View.VISIBLE);
                tv_tip3.setText("失败原因:"+listBean2.getCheckrem().toString());

                break;
            case 403:
                tv_result.setText("商家已退款");
                tv_endtime.setText(DateUtil.timeSpanToDateTime(listBean2.getLastRefundTime()));

                tv_edit.setVisibility(View.GONE);
                tv_chexiao.setVisibility(View.GONE);

                tv_tip1.setVisibility(View.VISIBLE);
                tv_tip2.setVisibility(View.VISIBLE);
                tv_tip3.setVisibility(View.GONE);

                break;
            case 404:
                tv_result.setText("退款成功,申请关闭");
                tv_endtime.setText(DateUtil.timeSpanToDateTime(listBean2.getLastRefundTime()));

                tv_edit.setVisibility(View.GONE);
                tv_chexiao.setVisibility(View.GONE);

                tv_tip1.setVisibility(View.VISIBLE);
                tv_tip2.setVisibility(View.VISIBLE);
                tv_tip3.setVisibility(View.GONE);

                break;


        }

    }

    @Override
    public void specialTuiKuan(String action) {

        loadingDialog.dismiss();

        ToastUtil.Success("撤销成功");
        ActivityUtil.getInstance().closeActivity(this);

    }

    @Override
    public void specialTuiKuanUpdate(SpecialTuiKuanUpdateBean tuiKuanUpdateBean) {

    }

    @Override
    public void specialTuiKuanUpdate2(String action) {

    }

    @Override
    public void specialOrderComplete(SpecialOrderCompleteBean completeBean) {

    }

    @Override
    public void specialOrderGoPay(String action) {

    }

    @Override
    public void specialOrderCancel(String action) {

    }

    @Override
    public void specialShenQingTuiKuan(String action) {

    }

    @Override
    public void specialOrderConfirm(String action) {

    }

    @Override
    public void specialReturnList(SpecialReturnBean returnBean) {

    }

    @Override
    public void uploadHeader(FileUploadBean fileUploadBean) {

    }

    @Override
    public void happyCartAdd(String message) {

    }

    @Override
    public void happyCartList(BlessCartListBean blessCartListBean) {

    }

    @Override
    public void happyCartUpdate(CartUpdateBean message) {

    }

    @Override
    public void happyCartDel(String message) {

    }
}
