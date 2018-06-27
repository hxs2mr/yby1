package com.itislevel.lyl.mvp.ui.special;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.flyco.animation.BaseAnimatorSet;
import com.flyco.animation.SlideEnter.SlideBottomEnter;
import com.itislevel.lyl.R;
import com.itislevel.lyl.adapter.SpecialOrderOtherAdapter;
import com.itislevel.lyl.app.Constants;
import com.itislevel.lyl.base.RootFragment;
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
import com.itislevel.lyl.mvp.ui.pay.PayInfoActivity;
import com.itislevel.lyl.utils.ActivityUtil;
import com.itislevel.lyl.utils.GsonUtil;
import com.itislevel.lyl.utils.SharedPreferencedUtils;
import com.itislevel.lyl.utils.ToastUtil;
import com.itislevel.lyl.widget.OrderCancelReasonBottomDoalog;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class OrderNoPayFragment extends RootFragment<SpecialPresenter>
        implements SpecialContract.View, BaseQuickAdapter.OnItemClickListener
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
    private SpecialOrderOtherAdapter adapter;

    public OrderNoPayFragment() {
        // Required empty public constructor
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_order_no_pay;
    }

    @Override
    protected void initEventAndData() {
        initRefreshListener();
        initAdapter();
        refreshLayout.autoRefresh();//刷新效果
//        loadData();
    }

    private void loadData() {
        Map<String, String> request = new HashMap<>();
        request.put("token", SharedPreferencedUtils.getStr(Constants.USER_TOKEN));
        request.put("usernum", SharedPreferencedUtils.getStr(Constants.USER_NUM));
        request.put("buyuserid", SharedPreferencedUtils.getStr(Constants.USER_ID));
        request.put("pageIndex", pageIndex + "");
        request.put("pageSize", Constants.PAGE_NUMBER20 + "");
        request.put("status", Constants.SPECIAL_ORDER_WAITNG_PAY + "");

        mPresenter.specialOrderDetail(GsonUtil.obj2JSON(request));

    }

    /**
     * 初始化adapter
     */
    private void initAdapter() {
        if (adapter == null) {
            adapter = new SpecialOrderOtherAdapter(R.layout.item_order_nopay);
            adapter.setOnItemClickListener(this);
            adapter.setOnItemChildClickListener(this);
            adapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN); //切换动画
            adapter.setEnableLoadMore(false);

            LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//            recyclerView.addItemDecoration(new HorizontalDividerItemDecoration.Builder(this)
//                    .build());
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
    public void useNightMode(boolean isNight) {

    }

    @Override
    public void stateEmpty() {

    }

    @Override
    public void stateLoading() {

    }

    @Override
    public void stateError(Throwable e) {
        super.stateError(e);
        if (adapter.getData() == null || adapter.getData().size() <= 0) {
            View emptyView = View.inflate(getActivity(), R.layout.partial_empty_view, null);
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
//        totalPage = detailBean.getTotalPage();


        if (isRefreshing) {
            if (detailBean.getList() != null) {
                adapter.setNewData(detailBean.getList());
            } else {
                adapter.setNewData(new ArrayList<>());
            }
            refreshLayout.finishRefresh();
        } else {
            if (detailBean.getList() != null)
                adapter.addData(detailBean.getList());
            refreshLayout.finishLoadmore();

        }
        //返回的数据 第一次
        if (pageIndex == 1 && adapter.getData().size() <= 0 && (detailBean.getList() == null ||
                detailBean.getList().size() <= 0)) {
            View emptyView = View.inflate(getActivity(), R.layout.partial_empty_view, null);
            TextView viewById = emptyView.findViewById(R.id.tv_message);
            adapter.setEmptyView(emptyView);
        }
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

        ToastUtil.Success("订单取消成功");
        adapter.remove(cancelPos);

        if (adapter.getData().size() <= 0) {
            View emptyView = View.inflate(getActivity(), R.layout.partial_empty_view, null);
            TextView viewById = emptyView.findViewById(R.id.tv_message);
            adapter.setEmptyView(emptyView);
        }
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
        getFragmentComponent().inject(this);
    }

    Bundle bundle=new Bundle();

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        SpecialOrderDetailBean.ListBean item = this.adapter.getItem(position);
        bundle.putString("goodsitem", GsonUtil.obj2JSON(item));


        switch (view.getId()) {
            case R.id.tv_contcat://联系买家
                ActivityUtil.getInstance().openActivity(getActivity(), CustomerPhoneActivity.class);
                break;
            case R.id.tv_refund://申请退款

                ActivityUtil.getInstance().openActivity(getActivity(), OrderRefundShenQingActivity
                        .class, bundle);
                break;
            case R.id.tv_confirm://确认
                break;
            case R.id.tv_cancel://取消
                cancelOrder(position);
                break;
            case R.id.tv_go_pay://去付款

                bundle.putString(Constants.PAY_MODULE_NAME, Constants.CART_MODEL_GIFT);
                bundle.putInt(Constants.PAY_FROM_ACTIVITY, Constants.PAY_FROM_SPECIAL_ORDER);


                bundle.putString(Constants.PAY_ORDERNUM, item.getOrdernum());
                bundle.putString(Constants.PAY_TOTALPRICE, item.getPrice() + "");
                bundle.putString(Constants.PAY_GOODS_DESC, item.getGoodsname());
                bundle.putString(Constants.PAY_GOODS_DETAIL, item.getGoodsname());

                ActivityUtil.getInstance().openActivity(getActivity(), PayInfoActivity
                        .class, bundle);

                break;
            case R.id.tv_look_detail://查看详情
                ActivityUtil.getInstance().openActivity(getActivity(), OrderDetailActivity
                        .class, bundle);

                break;
            case R.id.tv_service:
                ActivityUtil.getInstance().openActivity(getActivity(), OrderServiceActivity
                        .class, bundle);

                break;

        }
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        SpecialOrderDetailBean.ListBean item = this.adapter.getItem(position);

        bundle.putString("goodsitem", GsonUtil.obj2JSON(item));
        ActivityUtil.getInstance().openActivity(getActivity(), OrderDetailActivity
                .class, bundle);




    }

    private BaseAnimatorSet mBasIn;
    private int cancelPos = 0;

    private void cancelOrder(int position) {
        cancelPos = position;

        mBasIn = new SlideBottomEnter();
        OrderCancelReasonBottomDoalog cancelReasonBottomDoalog = new
                OrderCancelReasonBottomDoalog(getActivity(), null, this);

        cancelReasonBottomDoalog.showAnim(mBasIn)
                .show();

    }

    public void addCancel(String msg) {
        SpecialOrderDetailBean.ListBean item = adapter.getItem(cancelPos);

        Map<String, String> request = new HashMap<>();
        request.put("token", SharedPreferencedUtils.getStr(Constants.USER_TOKEN));
        request.put("usernum", SharedPreferencedUtils.getStr(Constants.USER_NUM));
        request.put("ordernum", item.getOrdernum());
        request.put("cancelOrder", msg);

        mPresenter.specialOrderCancel(GsonUtil.obj2JSON(request));

    }

}
