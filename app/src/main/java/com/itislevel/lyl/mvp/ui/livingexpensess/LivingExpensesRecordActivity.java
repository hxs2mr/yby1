package com.itislevel.lyl.mvp.ui.livingexpensess;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.itislevel.lyl.R;
import com.itislevel.lyl.adapter.PropertyRecordAdapter;
import com.itislevel.lyl.app.Constants;
import com.itislevel.lyl.base.RootActivity;
import com.itislevel.lyl.mvp.model.bean.BlessOrderBean;
import com.itislevel.lyl.mvp.model.bean.PropertyQueryInfo;
import com.itislevel.lyl.mvp.model.bean.PropertyQueryInfoBean;
import com.itislevel.lyl.mvp.model.bean.PropertyRecordBean;
import com.itislevel.lyl.mvp.model.bean.PropertyUpdateStatusBean;
import com.itislevel.lyl.mvp.model.bean.SetOwnerPayMonth;
import com.itislevel.lyl.utils.ActivityUtil;
import com.itislevel.lyl.utils.DateUtil;
import com.itislevel.lyl.utils.GsonUtil;
import com.itislevel.lyl.utils.SharedPreferencedUtils;
import com.itislevel.lyl.utils.ToastUtil;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;

public class LivingExpensesRecordActivity extends RootActivity<LivingExpensesPresenter>
        implements LivingExpensesContract.View, BaseQuickAdapter.OnItemClickListener,
        BaseQuickAdapter.OnItemChildClickListener {


    @BindView(R.id.refreshLayout)
    RefreshLayout refreshLayout;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;


    //是否已添加没有更多数据的布局
    private boolean isAddNoMoreView = false;
    private int pageIndex = 1;//分页加载时 当前页是第几页
    private int totalPage = 0;//总页数
    private boolean isRefreshing = true;//当前是刷新还是加载
    PropertyRecordAdapter adapter;

    Bundle bundle = null;
    String userid;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_living_expenses_record;
    }

    @Override
    protected void initEventAndData() {

        bundle = getIntent().getExtras();
        userid = bundle.getString("userid");

        setToolbarTvTitle("缴费记录");

        initRefreshListener();
        initAdapter();
        refreshLayout.autoRefresh();//刷新效果
//        loadData();

    }


    /**
     * 初始化adapter
     */
    private void initAdapter() {
        if (adapter == null) {
            adapter = new PropertyRecordAdapter(R.layout.item_property_record);
            adapter.setOnItemClickListener(this);
            adapter.setOnItemChildClickListener(this);
            adapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN); //切换动画
            adapter.setEnableLoadMore(false);

            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
//            GridLayoutManager layoutManager = new GridLayoutManager(this, 4, LinearLayoutManager
//                    .HORIZONTAL, false);
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

    private void loadData() {
        Map<String, String> request = new HashMap<>();
        request.put("token", SharedPreferencedUtils.getStr(Constants.USER_TOKEN));
        request.put("usernum", SharedPreferencedUtils.getStr(Constants.USER_NUM));
        request.put("userid", userid);//这个userid 是查询出来的
        request.put("page", pageIndex + "");
        request.put("pagesize", Constants.PAGE_NUMBER10 + "");

        mPresenter.propertyQueryOrder(GsonUtil.obj2JSON(request));

    }

    @Override
    public void showContent(String msg) {

    }

    @Override
    public void propertyQuery(PropertyQueryInfoBean propertyQueryInfoBean) {

    }

    @Override
    public void propertyQueryOrder(PropertyRecordBean propertyRecordBean) {
        totalPage = propertyRecordBean.getPageBean().getTotalPage();

//返回的数据 第一次
        if (pageIndex == 1 && adapter.getData().size() <= 0 && (propertyRecordBean.getPageBean()
                .getList() == null || propertyRecordBean.getPageBean().getList().size() <= 0)) {
            View emptyView = View.inflate(this, R.layout.partial_empty_view, null);
            TextView viewById = emptyView.findViewById(R.id.tv_message);
            adapter.setEmptyView(emptyView);
        }

        // TODO: 2018-01-11  差一个字段
        if (isRefreshing) {
            adapter.setNewData(propertyRecordBean.getPageBean().getList());
            refreshLayout.finishRefresh();
        } else {
            adapter.addData(propertyRecordBean.getPageBean().getList());
            refreshLayout.finishLoadmore();
        }
    }

    @Override
    public void propertyUpdatePayType(PropertyUpdateStatusBean statusBean) {

    }

    @Override
    public void propertyUpdateOrderState(PropertyUpdateStatusBean statusBean) {

    }

    @Override
    public void propertyGenerateOrder(String blessOrderBean) {

    }

    @Override
    public void getSSMCode(String smscode) {

    }

    @Override
    public void propertyQueryByUserid(PropertyQueryInfo queryInfo) {

    }

    @Override
    public void propertyQueryList(PropertyQueryInfo queryInfo) {

    }

    @Override
    public void propertyQueryByUserid1(PropertyQueryInfoBean queryInfoBean) {

    }

    @Override
    public void propertySetOwnerPayMonth(SetOwnerPayMonth setOwnerPayMonth) {

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
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

        PropertyRecordBean.PageBeanBean.ListBean item = this.adapter.getItem(position);

        bundle.putString("ordernum", item.getUsernum());
        bundle.putString("username", item.getUsername());
        bundle.putInt("status", item.getStatus());
        bundle.putString("payfee", item.getPayfee());
        bundle.putString("payunit", item.getPayunit());
        bundle.putString("paytime", item.getPayfeetime());

        ActivityUtil.getInstance().openActivity(LivingExpensesRecordActivity.this,
                LivingPropertyDetailActivity.class, bundle);
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        PropertyRecordBean.PageBeanBean.ListBean item = this.adapter.getItem(position);

        bundle.putString("ordernum", item.getUsernum());
        bundle.putString("username", item.getUsername());
        bundle.putInt("status", item.getStatus());
        bundle.putString("payfee", item.getPayfee());
        bundle.putString("payunit", item.getPayunit());
        bundle.putString("paytime", item.getPayfeetime());

        ActivityUtil.getInstance().openActivity(LivingExpensesRecordActivity.this,
                LivingPropertyDetailActivity.class, bundle);
    }
}
