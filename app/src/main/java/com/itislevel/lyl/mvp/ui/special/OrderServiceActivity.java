package com.itislevel.lyl.mvp.ui.special;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
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
import com.itislevel.lyl.utils.ActivityUtil;
import com.itislevel.lyl.utils.GsonUtil;
import com.itislevel.lyl.utils.imageload.ImageLoadConfiguration;
import com.itislevel.lyl.utils.imageload.ImageLoadProxy;
import com.itislevel.lyl.utils.rxbus.annotation.UseRxBus;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 已完成订单 的售后页面
 */
@UseRxBus
public class OrderServiceActivity extends RootActivity<SpecialPresenter>
        implements SpecialContract.View {

//    rl_tuikuan rl_tuihuo  tv_goodsname iv_logo

    @BindView(R.id.rl_tuikuan)
    RelativeLayout rl_tuikuan;

    @BindView(R.id.rl_tuihuo)
    RelativeLayout rl_tuihuo;

    @BindView(R.id.tv_goodsname)
    TextView tv_goodsname;

    @BindView(R.id.iv_logo)
    ImageView iv_logo;

    Bundle bundle;
    private SpecialOrderDetailBean.ListBean listBean;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_order_service;
    }

    @Override
    protected void initEventAndData() {

        setToolbarTvTitle("订单详情");
        bundle=getIntent().getExtras();
        String goodsitem = bundle.getString("goodsitem");
        listBean = GsonUtil.toObject(goodsitem,
                SpecialOrderDetailBean.ListBean.class);

        tv_goodsname.setText(listBean.getGoodsname());
        ImageLoadProxy.getInstance()
                .load(new ImageLoadConfiguration.Builder(App.getInstance())
                        .defaultImageResId(R.mipmap.icon_img_load_pre)
                        .url(Constants.IMG_SERVER_PATH + listBean.getImgurl())
                        .imageView(iv_logo).build());

    }

    @OnClick({R.id.rl_tuikuan,R.id.rl_tuihuo})
    public void click(View view){
        switch (view.getId()){
            case R.id.rl_tuikuan:
                bundle.putBoolean("istuikuan",true);

                break;
            case R.id.rl_tuihuo:
                bundle.putBoolean("istuikuan",false);

                break;
        }

        bundle.putBoolean("isFirstApply",true);
        ActivityUtil.getInstance().openActivity(OrderServiceActivity.this,OrderRefundShenQingActivity.class,bundle);


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
