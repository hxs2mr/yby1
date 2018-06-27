package com.itislevel.lyl.mvp.ui.special;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
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
import com.itislevel.lyl.utils.SharedPreferencedUtils;
import com.itislevel.lyl.utils.ToastUtil;
import com.itislevel.lyl.utils.imageload.ImageLoadConfiguration;
import com.itislevel.lyl.utils.imageload.ImageLoadProxy;
import com.itislevel.lyl.utils.rxbus.RxBus;
import com.itislevel.lyl.utils.rxbus.annotation.Subscribe;
import com.itislevel.lyl.utils.rxbus.annotation.UseRxBus;
import com.itislevel.lyl.utils.rxbus.event.EventThread;
import com.jaeger.library.StatusBarUtil;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

@UseRxBus
public class SpecialGiftDetailActivity extends RootActivity<SpecialPresenter>
        implements SpecialContract.View  {


    Bundle bundle=null;
    private String gooditem;

//    iv_goodsimg iv_cart
//    tv_goodsname tv_price tv_youfei
//    tv_duihuan tv_cart tv_goumai


    @BindView(R.id.iv_goodsimg)
    ImageView iv_goodsimg;


    @BindView(R.id.iv_cart)
    ImageView iv_cart;


    @BindView(R.id.tv_goodsname)
    TextView tv_goodsname;

    @BindView(R.id.tv_price)
    TextView tv_price;

    @BindView(R.id.tv_youfei)
    TextView tv_youfei;

    @BindView(R.id.tv_duihuan)
    TextView tv_duihuan;

    @BindView(R.id.tv_cart)
    TextView tv_cart;

    @BindView(R.id.tv_goumai)
    TextView tv_goumai;


    @BindView(R.id.tv_goodsrem)
    TextView tv_goodsrem;



    @BindView(R.id.iv_detail_back)
    ImageView iv_detail_back;
    private SpecialGiftListBean.ListBean listBean;


    @Override
    protected int getLayoutId() {
//        StatusBarUtil.setTranslucent(this, 0);//不加0 是半透明效果
//        getWindow().setBackgroundDrawable(null);

        return R.layout.activity_special_gift_detail;
    }

    @Override
    protected void initEventAndData() {

        bundle=getIntent().getExtras();

        setToolbarTvTitle("商品详情");


        gooditem = bundle.getString("gooditem");
        listBean = GsonUtil.toObject(gooditem, SpecialGiftListBean
                .ListBean.class);

        String imggroup = listBean.getImggroup();
        if (!TextUtils.isEmpty(imggroup)){
            if (imggroup.contains(",")){
                ImageLoadProxy.getInstance()
                        .load(new ImageLoadConfiguration.Builder(App.getInstance())
                                .defaultImageResId(R.mipmap.icon_img_load_pre)
                                .url(Constants.IMG_SERVER_PATH+imggroup.split(",")[0])
                                .imageView(iv_goodsimg).build());
            }else{
                ImageLoadProxy.getInstance()
                        .load(new ImageLoadConfiguration.Builder(App.getInstance())
                                .defaultImageResId(R.mipmap.icon_img_load_pre)
                                .url(Constants.IMG_SERVER_PATH+imggroup)
                                .imageView(iv_goodsimg).build());

            }
        }

        tv_price.setText("￥"+listBean.getSellerprice());
        tv_goodsname.setText(listBean.getGoodsname());

        bundle.putString("sellerid",listBean.getSellerid()+"");

        tv_youfei.setText("快递费:"+listBean.getMailfee());

        tv_goodsrem.setText(listBean.getGoodsrem());

    }

    @OnClick({R.id.iv_detail_back, R.id.tv_duihuan,R.id.tv_cart,R.id.tv_goumai,R.id.iv_cart})
    public void click(View view){
        switch (view.getId()){
            case R.id.iv_detail_back:
                ActivityUtil.getInstance().closeActivity(this);
                break;
            case R.id.iv_cart:
                ActivityUtil.getInstance().openActivity(this,SpecialCartActivity.class,bundle);
                break;
            case R.id.tv_duihuan:
                ToastUtil.Info("正在开发,敬请期待");
                break;
            case R.id.tv_cart:
               addCart();
                break;
            case R.id.tv_goumai:
                bundle.putBoolean("isfromCart",false);

                ActivityUtil.getInstance().openActivity(this,SpecialConfirmDownOrderActivity.class,bundle);
                break;

        }
    }

    private void addCart() {

        Map<String, String> request = new HashMap<>();
        request.put("token", SharedPreferencedUtils.getStr(Constants.USER_TOKEN));
        request.put("usernum", SharedPreferencedUtils.getStr(Constants.USER_NUM));
        request.put("userid", SharedPreferencedUtils.getStr(Constants.USER_ID));

        request.put("type", Constants.CART_TEAM_LIVING);
        request.put("cateid", listBean.getCateid() + "");
        request.put("goodsid", listBean.getGoodsid() + "");
        request.put("goodsname", listBean.getGoodsname());
        request.put("imgurl", listBean.getLogourl());
        request.put("price", listBean.getSellerprice());
        request.put("count",  "1");
        request.put("mailfee",  listBean.getMailfee()+"");

        mPresenter.happyCartAdd(GsonUtil.obj2JSON(request));
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

        ToastUtil.Success("添加购物车成功");
        ActivityUtil.getInstance().openActivity(this,SpecialCartActivity.class);

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

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Subscribe(tag = RxBus.TAG_UPDATE, thread = EventThread.MAIN_THREAD)
    public void rxrefresh(String msg) {
        ActivityUtil.getInstance().closeActivity(this);
    }
}
