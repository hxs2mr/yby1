package com.itislevel.lyl.mvp.ui.special;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.itislevel.lyl.R;
import com.itislevel.lyl.adapter.SpecialGiftCartAdapter;
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
import com.itislevel.lyl.mvp.ui.blessing.BlessingDetailActivity;
import com.itislevel.lyl.mvp.ui.myaddress.MyAddressActivity;
import com.itislevel.lyl.mvp.ui.pay.PayInfoActivity;
import com.itislevel.lyl.utils.ActivityUtil;
import com.itislevel.lyl.utils.DecimalUtils;
import com.itislevel.lyl.utils.GsonUtil;
import com.itislevel.lyl.utils.SharedPreferencedUtils;
import com.itislevel.lyl.utils.ToastUtil;
import com.itislevel.lyl.utils.rxbus.RxBus;
import com.itislevel.lyl.utils.rxbus.annotation.Subscribe;
import com.itislevel.lyl.utils.rxbus.annotation.UseRxBus;
import com.itislevel.lyl.utils.rxbus.event.EventThread;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

@UseRxBus
public class SpecialConfirmDownOrderActivity extends RootActivity<SpecialPresenter>
        implements SpecialContract.View, BaseQuickAdapter.OnItemClickListener
        , BaseQuickAdapter.OnItemChildClickListener {

//    tv_select_addr ll_addr
//    tv_name tv_phone tv_addr_detail
//    tv_price tv_order


    @BindView(R.id.tv_select_addr)
    TextView tv_select_addr;

    @BindView(R.id.ll_addr)
    LinearLayout ll_addr;

    @BindView(R.id.tv_name)
    TextView tv_name;
    @BindView(R.id.tv_phone)
    TextView tv_phone;
    @BindView(R.id.tv_addr_detail)
    TextView tv_addr_detail;


    @BindView(R.id.tv_price)
    TextView tv_price;

    @BindView(R.id.tv_order)
    TextView tv_order;


    @BindView(R.id.refreshLayout)
    RefreshLayout refreshLayout;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;


    //是否已添加没有更多数据的布局
    private boolean isAddNoMoreView = false;
    private int pageIndex = 1;//分页加载时 当前页是第几页
    private int totalPage = 0;//总页数
    private boolean isRefreshing = true;//当前是刷新还是加载
    private SpecialGiftCartAdapter adapter;

    private int opearatorPos;

    private boolean isfromCart = false; //立即购买 还是下单购买

    Bundle bundle;
    private SpecialReceiveAddressBean.ListBean receiveAddr;
    private BlessCartListBean.ShopcartlistBean shopcartlistBean;
    private List<BlessCartListBean.ShopcartlistBean> shopcartlistBeans;
    private SpecialGiftListBean.ListBean goodsSingleBean;
    private String goodsDetail;
    private String totalPrice;




    @Override
    protected int getLayoutId() {
        return R.layout.activity_special_confirm_down_order;
    }

    @Override
    protected void initEventAndData() {
        bundle = getIntent().getExtras();
        setToolbarTvTitle("确认订单");
        initRefreshListener();
        initAdapter();
//        refreshLayout.autoRefresh();//刷新效果
//        loadData();

        isfromCart = bundle.getBoolean("isfromCart");
        if (isfromCart) {

            String carStr = bundle.getString("carStr");
            shopcartlistBeans = GsonUtil.toList(carStr,
                    BlessCartListBean.ShopcartlistBean.class);
            adapter.addData(shopcartlistBeans);

            tv_price.setText(bundle.getString("price"));

        } else {
            String gooditem = bundle.getString("gooditem");
            goodsSingleBean = GsonUtil.toObject(gooditem, SpecialGiftListBean
                    .ListBean.class);
            shopcartlistBean = new BlessCartListBean
                    .ShopcartlistBean();

            shopcartlistBean.setCount(1);
            shopcartlistBean.setGoodsname(goodsSingleBean.getGoodsname());
            shopcartlistBean.setCateid(goodsSingleBean.getCateid());
            shopcartlistBean.setPrice(goodsSingleBean.getSellerprice());
            shopcartlistBean.setGoodsid(goodsSingleBean.getGoodsid());
            shopcartlistBean.setImgurl(goodsSingleBean.getLogourl());
            shopcartlistBean.setGoodsrem(goodsSingleBean.getGoodsrem());

            adapter.addData(shopcartlistBean);

            tv_price.setText(DecimalUtils.format2(Double.parseDouble(goodsSingleBean
                    .getSellerprice())));

        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        loadReceiveAddr();

    }

    @OnClick({R.id.tv_select_addr, R.id.tv_addr_detail, R.id.tv_order})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.tv_select_addr:
            case R.id.tv_addr_detail:
                ActivityUtil.getInstance().openActivity(this, MyAddressActivity.class, bundle);
                break;
            case R.id.tv_order:
                generateOrder();
                break;
        }
    }

    private void loadReceiveAddr() {

        Map<String, String> request = new HashMap<>();
        request.put("token", SharedPreferencedUtils.getStr(Constants.USER_TOKEN));
        request.put("usernum", SharedPreferencedUtils.getStr(Constants.USER_NUM));
        request.put("buyuserid", SharedPreferencedUtils.getStr(Constants.USER_ID));
        mPresenter.specialReceiveAddress(GsonUtil.obj2JSON(request));


    }


    /**
     * 初始化adapter
     */
    private void initAdapter() {
        if (adapter == null) {
            adapter = new SpecialGiftCartAdapter(R.layout.item_special_order_confirm);
            adapter.setOnItemClickListener(this);
            adapter.setOnItemChildClickListener(this);
            adapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN); //切换动画
            adapter.setEnableLoadMore(false);
            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            recyclerView.addItemDecoration(new HorizontalDividerItemDecoration.Builder(this)
                    .build());
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(adapter);

        }

    }

    private void initRefreshListener() {
//        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
//            @Override
//            public void onRefresh(RefreshLayout refreshlayout) {
//                pageIndex = 1;
//                isRefreshing = true;
//                loadData();
//            }
//        });
//
//        refreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
//            @Override
//            public void onLoadmore(RefreshLayout refreshlayout) {
//                pageIndex++;
//                if (pageIndex > totalPage) {
//                    if (!isAddNoMoreView) {
//                        isAddNoMoreView = true;
//                        View view = getLayoutInflater().inflate(R.layout.partial_no_more_data,
//                                null, false);
//                        adapter.addFooterView(view);
//                    }
//                    ToastUtil.Info("没有更多啦~~");
//                    refreshLayout.finishLoadmore(true);//
//                    return;
//                } else {
//                    isRefreshing = false;
//                    loadData();
//                }
//            }
//        });

        refreshLayout.setEnableLoadmore(false);
        refreshLayout.setEnableRefresh(false);

    }


    private void generateOrder() {
        if (receiveAddr == null) {
            ToastUtil.Info("请选择收货地址");
            return;
        }
        if (isfromCart) {
            gouwuchexiadan();

        } else {
            lijixiadan();

        }

    }

    private void gouwuchexiadan() {
        loadingDialog.show();
        loadingDialog.setLabel(Constants.CART_GENERATE_ORDER_TXT);


        Map<String, String> request = new HashMap<>();
        request.put("token", SharedPreferencedUtils.getStr(Constants.USER_TOKEN));
        request.put("usernum", SharedPreferencedUtils.getStr(Constants.USER_NUM));
        request.put("buyuserid", SharedPreferencedUtils.getStr(Constants.USER_ID));

        request.put("sellerid", TextUtils.isEmpty(bundle.getString("sellerid"))?"1":bundle.getString("sellerid"));


        request.put("realname", receiveAddr.getUsername());
        request.put("type", Constants.CART_TEAM_LIVING);
        request.put("phone", receiveAddr.getPhone());
        request.put("receiveaddress", receiveAddr.getProvname() + receiveAddr.getCityname() +
                (TextUtils
                        .isEmpty(receiveAddr.getCountname()) ? "" : receiveAddr.getCountname()) +
                receiveAddr
                        .getDetailaddress());

        String str="";
        double price=0;
        String desc="";

        for (BlessCartListBean.ShopcartlistBean item:shopcartlistBeans){
            price+=Double.parseDouble(item.getPrice())*item.getCount();
            desc+=item.getGoodsname()+"+";
            str+=item.getGoodsid()+","+item.getPrice()+","+item.getCount()+","+item.getGoodsname()+","+item.getImgurl()+","+item.getMailfee();
            str+="@";
        }
        if (str.endsWith("@")){
            str = str.substring(0, str.length() - 1);
        }
        if (desc.endsWith("+")){
            desc = desc.substring(0, desc.length() - 1);
        }

        request.put("shopcartstr", str);

        totalPrice=DecimalUtils.format2(price)+"";
        goodsDetail=desc;

        mPresenter.specialShopOrder(GsonUtil.obj2JSON(request));

    }

    private void lijixiadan() {

        totalPrice = tv_price.getText().toString().trim();
        goodsDetail = goodsSingleBean.getGoodsname();

        loadingDialog.show();
        loadingDialog.setLabel(Constants.CART_GENERATE_ORDER_TXT);


        Map<String, String> request = new HashMap<>();
        request.put("token", SharedPreferencedUtils.getStr(Constants.USER_TOKEN));
        request.put("usernum", SharedPreferencedUtils.getStr(Constants.USER_NUM));
        request.put("buyuserid", SharedPreferencedUtils.getStr(Constants.USER_ID));

        request.put("sellerid", goodsSingleBean.getSellerid() + "");
        request.put("goodsid", goodsSingleBean.getGoodsid() + "");
        request.put("goodsname", goodsSingleBean.getGoodsname());
        request.put("count", shopcartlistBean.getCount() + "");
        request.put("imgurl", goodsSingleBean.getLogourl());
        request.put("price", goodsSingleBean.getSellerprice());

        request.put("realname", receiveAddr.getUsername());
        request.put("phone", receiveAddr.getPhone());
        request.put("mailfee", goodsSingleBean.getMailfee()+"");
        request.put("receiveaddress", receiveAddr.getProvname() + receiveAddr.getCityname() +
                (TextUtils
                .isEmpty(receiveAddr.getCountname()) ? "" : receiveAddr.getCountname()) +
                receiveAddr
                .getDetailaddress());


        mPresenter.specialImmediatelyOrder(GsonUtil.obj2JSON(request));

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
    public void stateError(Throwable e) {
        super.stateError(e);
        if (adapter.getData() == null || adapter.getData().size() <= 0) {
            View emptyView = View.inflate(this, R.layout.partial_empty_view, null);
            adapter.setEmptyView(emptyView);
        }

        if (refreshLayout.isLoading()) {
            refreshLayout.finishLoadmore();
        }
        if (refreshLayout.isRefreshing()) {
            refreshLayout.finishRefresh();
        }
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
    public void specialImmediatelyOrder(String blessOrderBean) {

        loadingDialog.dismiss();

        bundle.putString(Constants.PAY_MODULE_NAME, Constants.CART_MODEL_GIFT);
        bundle.putInt(Constants.PAY_FROM_ACTIVITY, Constants.PAY_FROM_SPECIAL_GIFT);

        bundle.putString(Constants.PAY_ORDERNUM, blessOrderBean);
        bundle.putString(Constants.PAY_TOTALPRICE, totalPrice + "");
        bundle.putString(Constants.PAY_GOODS_DESC, goodsDetail);
        bundle.putString(Constants.PAY_GOODS_DETAIL, goodsDetail);

        ActivityUtil.getInstance().openActivity(this, PayInfoActivity
                .class, bundle);


    }

    @Override
    public void specialShopOrder(String blessOrderBean) {

        loadingDialog.dismiss();

        bundle.putString(Constants.PAY_MODULE_NAME, Constants.CART_MODEL_GIFT);
        bundle.putInt(Constants.PAY_FROM_ACTIVITY, Constants.PAY_FROM_SPECIAL_GIFT);

        bundle.putString(Constants.PAY_ORDERNUM, blessOrderBean);
        bundle.putString(Constants.PAY_TOTALPRICE, totalPrice + "");
        bundle.putString(Constants.PAY_GOODS_DESC, goodsDetail);
        bundle.putString(Constants.PAY_GOODS_DETAIL, goodsDetail);

        ActivityUtil.getInstance().openActivity(this, PayInfoActivity
                .class, bundle);
    }

    @Override
    public void specialReceiveAddress(SpecialReceiveAddressBean addressBean) {

        if (addressBean.getList() != null && addressBean.getList().size() > 0) {
            tv_select_addr.setVisibility(View.GONE);
            ll_addr.setVisibility(View.VISIBLE);
            receiveAddr = addressBean.getList().get(0);

            tv_name.setText(receiveAddr.getUsername());
            tv_phone.setText(receiveAddr.getPhone());
            tv_addr_detail.setText(receiveAddr.getProvname() + receiveAddr.getCityname() +
                    (TextUtils
                    .isEmpty(receiveAddr.getCountname()) ? "" : receiveAddr.getCountname()) +
                    receiveAddr
                    .getDetailaddress());
        }else{
            tv_select_addr.setVisibility(View.VISIBLE);
            ll_addr.setVisibility(View.GONE);
        }

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

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

        BlessCartListBean.ShopcartlistBean item = this.adapter.getItem(position);
        opearatorPos = position;

        switch (view.getId()) {
            case R.id.iv_jian:
                int oldCount = item.getCount();
                oldCount = oldCount - 1;
                if (oldCount <= 0) {
                    adapter.remove(position);
                } else {
                    item.setCount(oldCount);
                }
                break;
            case R.id.iv_add:
                int oldCount1 = item.getCount();
                oldCount1 = oldCount1 + 1;
                item.setCount(oldCount1);
                break;
        }
        adapter.notifyDataSetChanged();
        calcTotalPrice();

    }

    private void calcTotalPrice() {
        List<BlessCartListBean.ShopcartlistBean> data = adapter.getData();
        double price = 0;
        for (BlessCartListBean.ShopcartlistBean item : data) {
            price += Double.parseDouble(item.getPrice()) * item.getCount();
        }
        tv_price.setText(DecimalUtils.format2(price));

    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Subscribe(tag = RxBus.TAG_UPDATE, thread = EventThread.MAIN_THREAD)
    public void rxrefresh(String msg) {
        ActivityUtil.getInstance().closeActivity(this);
    }
}
