package com.itislevel.lyl.mvp.ui.special;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.itislevel.lyl.R;
import com.itislevel.lyl.adapter.FunsharingMyAdapter;
import com.itislevel.lyl.adapter.SpecialGiftCartAdapter;
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
import com.itislevel.lyl.utils.DecimalUtils;
import com.itislevel.lyl.utils.GsonUtil;
import com.itislevel.lyl.utils.SharedPreferencedUtils;
import com.itislevel.lyl.utils.ToastUtil;
import com.itislevel.lyl.utils.imageload.ImageLoadConfiguration;
import com.itislevel.lyl.utils.imageload.ImageLoadProxy;
import com.itislevel.lyl.utils.rxbus.RxBus;
import com.itislevel.lyl.utils.rxbus.annotation.Subscribe;
import com.itislevel.lyl.utils.rxbus.annotation.UseRxBus;
import com.itislevel.lyl.utils.rxbus.event.EventThread;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

@UseRxBus
public class SpecialCartActivity extends RootActivity<SpecialPresenter>
        implements SpecialContract.View
        , BaseQuickAdapter.OnItemClickListener
        , BaseQuickAdapter.OnItemChildClickListener {

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

    // tv_checkall tv_totalprice tv_jiesuan

    @BindView(R.id.tv_checkall)
    TextView tv_checkall;

    @BindView(R.id.iv_checkall)
    ImageView iv_checkall;

    @BindView(R.id.tv_totalprice)
    TextView tv_totalprice;

    @BindView(R.id.tv_jiesuan)
    TextView tv_jiesuan;

    private int opearatorPos;

    Bundle bundle;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_special_cart;
    }

    @Override
    protected void initEventAndData() {
        bundle = getIntent().getExtras();
//        setToolbarTvTitle("购物车(0)");
        setToolbarTvTitle("购物车");
        initRefreshListener();
        initAdapter();
        refreshLayout.autoRefresh();//刷新效果
//        loadData();
    }

    private boolean ischeckAll = false;


    @OnClick({R.id.tv_checkall, R.id.iv_checkall, R.id.tv_jiesuan})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.tv_checkall:
            case R.id.iv_checkall:
                ischeckAll = !ischeckAll;

                if (ischeckAll) {
                    ImageLoadProxy.getInstance()
                            .load(new ImageLoadConfiguration.Builder(App.getInstance())
                                    .url(R.mipmap.icon_radio_select)
                                    .imageView(iv_checkall).build());
                } else {
                    ImageLoadProxy.getInstance()
                            .load(new ImageLoadConfiguration.Builder(App.getInstance())
                                    .url(R.mipmap.icon_radio_normal)
                                    .imageView(iv_checkall).build());

                }

                checkAll(ischeckAll);
                break;
            case R.id.tv_jiesuan:

                List<BlessCartListBean.ShopcartlistBean> temp = new ArrayList<>();
                List<BlessCartListBean.ShopcartlistBean> data = adapter.getData();
                double price = 0;
                for (BlessCartListBean.ShopcartlistBean item : data) {
                    if (item.isSelect()) {
                        temp.add(item);
                        price += Double.parseDouble(item.getPrice()) * item.getCount();
                    }
                }
                if (temp.size() <= 0) {
                    ToastUtil.Info("请选择需要购买的商品");
                    return;
                }
                if (bundle == null) {
                    bundle = new Bundle();
                }

                bundle.putBoolean("isfromCart", true);
                bundle.putString("price", DecimalUtils.format2(price));
                bundle.putString("carStr", GsonUtil.obj2JSON(temp));
                ActivityUtil.getInstance().openActivity(this, SpecialConfirmDownOrderActivity
                        .class, bundle);

                break;

        }
    }

    private void checkAll(boolean ischeckAll) {
        List<BlessCartListBean.ShopcartlistBean> data = adapter.getData();
        for (BlessCartListBean.ShopcartlistBean item : data) {
            item.setSelect(ischeckAll);
        }
        adapter.notifyDataSetChanged();

        calcTotalPrice();
    }

    private void loadData() {
        Map<String, String> request = new HashMap<>();
        request.put("token", SharedPreferencedUtils.getStr(Constants.USER_TOKEN));
        request.put("usernum", SharedPreferencedUtils.getStr(Constants.USER_NUM));
        request.put("userid", SharedPreferencedUtils.getStr(Constants.USER_ID));
        request.put("type", Constants.CART_TEAM_LIVING);

        mPresenter.happyCartList(GsonUtil.obj2JSON(request));

    }

    private void updateCart(BlessCartListBean.ShopcartlistBean item) {

        Map<String, String> request = new HashMap<>();
        request.put("token", SharedPreferencedUtils.getStr(Constants.USER_TOKEN));
        request.put("usernum", SharedPreferencedUtils.getStr(Constants.USER_NUM));
        request.put("userid", SharedPreferencedUtils.getStr(Constants.USER_ID));

        request.put("type", Constants.CART_TEAM_LIVING);
        request.put("goodsid", item.getGoodsid() + "");
        request.put("count", item.getCount() + "");

        mPresenter.happyCartUpdate(GsonUtil.obj2JSON(request));

    }

    private void delCart(BlessCartListBean.ShopcartlistBean item) {
        Map<String, String> request = new HashMap<>();
        request.put("token", SharedPreferencedUtils.getStr(Constants.USER_TOKEN));
        request.put("usernum", SharedPreferencedUtils.getStr(Constants.USER_NUM));
        request.put("userid", SharedPreferencedUtils.getStr(Constants.USER_ID));

        request.put("type", Constants.CART_TEAM_LIVING);
        request.put("goodsid", item.getGoodsid() + "");

        mPresenter.happyCartDel(GsonUtil.obj2JSON(request));

    }


    /**
     * 初始化adapter
     */
    private void initAdapter() {
        if (adapter == null) {
            adapter = new SpecialGiftCartAdapter(R.layout.item_special_cart);
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
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                pageIndex = 1;
                isRefreshing = true;
                loadData();
            }
        });

        refreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                pageIndex++;
                if (pageIndex > totalPage) {
                    if (!isAddNoMoreView) {
                        isAddNoMoreView = true;
                        View view = getLayoutInflater().inflate(R.layout.partial_no_more_data,
                                null, false);
                        adapter.addFooterView(view);
                    }
                    ToastUtil.Info("没有更多啦~~");
                    refreshLayout.finishLoadmore(true);//
                    return;
                } else {
                    isRefreshing = false;
                    loadData();
                }
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
//        totalPage = blessCartListBean.getTotalPage();

//        setToolbarTvTitle("购物车(" + blessCartListBean.getShopcartlist().size() + ")");
//返回的数据 第一次
        if (pageIndex == 1 && adapter.getData().size() <= 0 && (blessCartListBean.getShopcartlist
                () == null || blessCartListBean.getShopcartlist().size() <= 0)) {
            View emptyView = View.inflate(this, R.layout.partial_empty_view, null);
            TextView viewById = emptyView.findViewById(R.id.tv_message);
            adapter.setEmptyView(emptyView);
        }
        if (isRefreshing) {
            adapter.setNewData(blessCartListBean.getShopcartlist());
            refreshLayout.finishRefresh();
        } else {
            adapter.addData(blessCartListBean.getShopcartlist());
            refreshLayout.finishLoadmore();

        }
    }

    @Override
    public void happyCartUpdate(CartUpdateBean message) {

    }

    @Override
    public void happyCartDel(String message) {

        adapter.remove(opearatorPos);
        adapter.notifyItemChanged(opearatorPos);
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
//        tv_del iv_select iv_logo   tv_goodsname
//        iv_jian tv_count iv_add tv_price

        BlessCartListBean.ShopcartlistBean item = this.adapter.getItem(position);
        opearatorPos = position;

        switch (view.getId()) {
            case R.id.tv_del:
                delCart(item);
                break;
            case R.id.iv_select:
            case R.id.iv_logo:
            case R.id.tv_goodsname:
            case R.id.tv_price:

                item.setSelect(!item.isSelect());

                break;
            case R.id.iv_jian:
                int oldCount = item.getCount();
                oldCount = oldCount - 1;
                if (oldCount <= 0) {
                    delCart(item);
                } else {
                    item.setCount(oldCount);
                    updateCart(item);

                }
                break;
            case R.id.iv_add:
                int oldCount1 = item.getCount();
                oldCount1 = oldCount1 + 1;
                item.setCount(oldCount1);
                updateCart(item);
                break;
        }
        adapter.notifyDataSetChanged();
        calcTotalPrice();
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

        BlessCartListBean.ShopcartlistBean item = this.adapter.getItem(position);
        opearatorPos = position;
        item.setSelect(!item.isSelect());
        adapter.notifyDataSetChanged();
        calcTotalPrice();

    }


    private void calcTotalPrice() {
        List<BlessCartListBean.ShopcartlistBean> data = adapter.getData();
        double price = 0;
        int count = 0;
        for (BlessCartListBean.ShopcartlistBean item : data) {
            if (item.isSelect()) {
                price += Double.parseDouble(item.getPrice()) * item.getCount();
                count += item.getCount();
            }
        }
        tv_totalprice.setText(DecimalUtils.format2(price));
        tv_jiesuan.setText("结算(" + count + ")");

    }


    @Subscribe(tag = RxBus.TAG_UPDATE, thread = EventThread.MAIN_THREAD)
    public void rxrefresh(String msg) {
        ActivityUtil.getInstance().closeActivity(this);
    }

}
