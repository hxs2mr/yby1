package com.itislevel.lyl.mvp.ui.special;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

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
import com.itislevel.lyl.utils.imageload.ImageLoadConfiguration;
import com.itislevel.lyl.utils.imageload.ImageLoadProxy;
import com.itislevel.lyl.utils.rxbus.annotation.UseRxBus;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 订单详情 已完成的订单
 */
@UseRxBus
public class OrderDetailActivity extends RootActivity<SpecialPresenter>
        implements SpecialContract.View {

//    tv_status tv_name tv_phone tv_addr_detail
//    iv_logo tv_goodsname tv_price tv_count
//    tv_ordernum tv_createtime

    Bundle bundle;

    @BindView(R.id.tv_status)
    TextView tv_status;
    @BindView(R.id.tv_name)
    TextView tv_name;
    @BindView(R.id.tv_phone)
    TextView tv_phone;
    @BindView(R.id.tv_addr_detail)
    TextView tv_addr_detail;
    @BindView(R.id.tv_goodsname)
    TextView tv_goodsname;
    @BindView(R.id.tv_price)
    TextView tv_price;
    @BindView(R.id.tv_count)
    TextView tv_count;

    @BindView(R.id.tv_ordernum)
    TextView tv_ordernum;
    @BindView(R.id.tv_createtime)
    TextView tv_createtime;

    @BindView(R.id.iv_logo)
    ImageView iv_logo;

    @BindView(R.id.ll_operater)
    LinearLayout ll_operater;

    private SpecialOrderDetailBean.ListBean listBean;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_order_detail;
    }

    @Override
    protected void initEventAndData() {
        bundle = getIntent().getExtras();
        setToolbarTvTitle("订单详情");
        String goodsitem = bundle.getString("goodsitem");
        listBean = GsonUtil.toObject(goodsitem,
                SpecialOrderDetailBean.ListBean.class);

        tv_goodsname.setText(listBean.getGoodsname());
        tv_price.setText("人民币" + listBean.getPrice());
        tv_count.setText("x" + listBean.getCount());
        ImageLoadProxy.getInstance()
                .load(new ImageLoadConfiguration.Builder(App.getInstance())
                        .defaultImageResId(R.mipmap.icon_img_load_pre)
                        .url(Constants.IMG_SERVER_PATH + listBean.getImgurl())
                        .imageView(iv_logo).build());

        tv_name.setText(listBean.getRealname());
        tv_phone.setText(listBean.getPhone());
        tv_addr_detail.setText(listBean.getReceiveaddress());

        tv_ordernum.setText("订单编号:"+listBean.getOrdernum());
        tv_createtime.setText("创建时间:"+DateUtil.timeSpanToDateTime(listBean.getCreatedtime()));

        String status = listBean.getStatus();
        int sta = Integer.parseInt(status);
        switch (sta) {
            case Constants.SPECIAL_ORDER_WAITNG_PAY:
                tv_status.setText("等待买家付款");
                break;
            case Constants.SPECIAL_ORDER_PAYED:
                tv_status.setText("付款成功,等待卖家发货!");
                break;
            case Constants.SPECIAL_ORDER_SENDED:
                tv_status.setText("等待买家收货");
                break;
            case Constants.SPECIAL_ORDER_RECEIVED:
            case Constants.SPECIAL_ORDER_COMPLETE:
                ll_operater.setVisibility(View.VISIBLE);
                tv_status.setText("交易完成");
                break;
            case Constants.SPECIAL_ORDER_REFUNDED:
                tv_status.setText("这个情况比较复杂,没有内置说明");
                break;

        }
    }

    //  tv_contcat tv_service

    @OnClick({R.id.tv_contcat, R.id.tv_service})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.tv_contcat:
                ActivityUtil.getInstance().openActivity(this, CustomerPhoneActivity.class);
                break;
            case R.id.tv_service:
                ActivityUtil.getInstance().openActivity(this, OrderServiceActivity
                        .class, bundle);
                break;

        }
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

    @Override
    public void specialTuiKuanDetail(SpecialTuiKuanDetailBean tuiKuanDetailBean) {

    }

    @Override
    public void specialTuiKuan(String action) {

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
