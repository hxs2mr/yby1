package com.itislevel.lyl.mvp.ui.livingexpensess;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.itislevel.lyl.R;
import com.itislevel.lyl.adapter.FunsharingMyAdapter;
import com.itislevel.lyl.adapter.LivingPropertyQeruyListAdapter;
import com.itislevel.lyl.app.Constants;
import com.itislevel.lyl.base.RootActivity;
import com.itislevel.lyl.mvp.model.bean.PropertyQueryInfo;
import com.itislevel.lyl.mvp.model.bean.PropertyQueryInfoBean;
import com.itislevel.lyl.mvp.model.bean.PropertyRecordBean;
import com.itislevel.lyl.mvp.model.bean.PropertyUpdateStatusBean;
import com.itislevel.lyl.mvp.model.bean.SetOwnerPayMonth;
import com.itislevel.lyl.utils.ActivityUtil;
import com.itislevel.lyl.utils.GsonUtil;
import com.itislevel.lyl.utils.SharedPreferencedUtils;
import com.itislevel.lyl.utils.ToastUtil;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;

public class LivingPropertyQeruyListActivity extends RootActivity<LivingExpensesPresenter>
        implements
        LivingExpensesContract.View, BaseQuickAdapter.OnItemChildClickListener {

    @BindView(R.id.refreshLayout)
    RefreshLayout refreshLayout;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    Bundle bundle;

    private String name;
    private String phone;
    private String validate;


    //是否已添加没有更多数据的布局
    private boolean isAddNoMoreView = false;
    private int pageIndex = 1;//分页加载时 当前页是第几页
    private int totalPage = 0;//总页数
    private boolean isRefreshing = true;//当前是刷新还是加载
    private LivingPropertyQeruyListAdapter adapter;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_living_property_qeruy_list;
    }

    @Override
    protected void initEventAndData() {

        bundle = this.getIntent().getExtras();

        name = bundle.getString("name");
        phone = bundle.getString("phone");
        validate = bundle.getString("validate");


        setToolbarTvTitle("房屋列表");
        initRefreshListener();
        initAdapter();
        refreshLayout.autoRefresh();//刷新效果
//        loadData();
    }

    private void loadData() {
//        Map<String, String> request = new HashMap<>();
//        request.put("token", SharedPreferencedUtils.getStr(Constants.USER_TOKEN));
//        request.put("usernum", SharedPreferencedUtils.getStr(Constants.USER_NUM));
//        request.put("userid", SharedPreferencedUtils.getStr(Constants.USER_ID));
//        request.put("pageIndex", pageIndex + "");
//        request.put("pageSize", Constants.PAGE_NUMBER20 + "");
//
//        mPresenter.propertyQueryByUserid(GsonUtil.obj2JSON(request));

        Map<String, String> request = new HashMap<>();
        request.put("token", SharedPreferencedUtils.getStr(Constants.USER_TOKEN));
        request.put("usernum", SharedPreferencedUtils.getStr(Constants.USER_NUM));
        request.put("username", name);
        request.put("phone", phone);

        mPresenter.propertyQueryList(GsonUtil.obj2JSON(request));


    }

    /**
     * 初始化adapter
     */
    private void initAdapter() {
        if (adapter == null) {
            adapter = new LivingPropertyQeruyListAdapter(R.layout.item_property_query);
//            adapter.setOnItemClickListener(this);
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
    public void propertyQuery(PropertyQueryInfoBean propertyQueryInfoBean) {


    }

    @Override
    public void propertyQueryOrder(PropertyRecordBean propertyRecordBean) {

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
//        totalPage = queryInfo.getTotalPage();

//返回的数据 第一次
        if (pageIndex == 1 && adapter.getData().size() <= 0 && (queryInfo.getOwnerplacelist() ==
                null || queryInfo.getOwnerplacelist().size() <= 0)) {
            View emptyView = View.inflate(this, R.layout.partial_empty_view, null);
            TextView viewById = emptyView.findViewById(R.id.tv_message);
            adapter.setEmptyView(emptyView);
        }
        if (isRefreshing) {
            adapter.setNewData(queryInfo.getOwnerplacelist());
            refreshLayout.finishRefresh();
        } else {
            adapter.addData(queryInfo.getOwnerplacelist());
            refreshLayout.finishLoadmore();

        }
    }

    @Override
    public void propertyQueryByUserid1(PropertyQueryInfoBean queryInfoBean) {

    }

    @Override
    public void propertySetOwnerPayMonth(SetOwnerPayMonth setOwnerPayMonth) {

    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        bundle.putString("userid",this.adapter.getItem(position).getUserid()+"");
        ActivityUtil.getInstance().openActivity(this,
                LivingPropertyListActivity.class, bundle);
    }
}
