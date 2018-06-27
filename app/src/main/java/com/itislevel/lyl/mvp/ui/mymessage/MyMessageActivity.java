package com.itislevel.lyl.mvp.ui.mymessage;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter.RequestLoadMoreListener;
import com.itislevel.lyl.R;
import com.itislevel.lyl.adapter.FamilyListAdapter;
import com.itislevel.lyl.adapter.MyMessageAdapter;
import com.itislevel.lyl.app.Constants;
import com.itislevel.lyl.base.RootActivity;
import com.itislevel.lyl.mvp.model.bean.MeiZiBean;
import com.itislevel.lyl.utils.ToastUtil;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import java.util.List;

import butterknife.BindView;

public class MyMessageActivity extends RootActivity<MyMessagePresenter> implements
        MyMessageContract.View, BaseQuickAdapter.OnItemClickListener,SwipeRefreshLayout.OnRefreshListener,RequestLoadMoreListener {


    @BindView(R.id.refreshLayout)
    SwipeRefreshLayout refreshLayout;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;


    //是否已添加没有更多数据的布局
    private boolean isAddNoMoreView = false;
    private int pageIndex = 1;//分页加载时 当前页是第几页
    private int totalPage = 0;//总页数
    private boolean isRefreshing = true;//当前是刷新还是加载
    private MyMessageAdapter adapter;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_message;
    }

    @Override
    protected void initEventAndData() {
        setToolbarTvTitle("新消息提醒");
        initRefreshLayout_recyclview();
      //  initRefreshListener();
        initAdapter();
        refreshLayout.setRefreshing(true);//刷新效果
        loadData();
    }

    private void initRefreshLayout_recyclview(){//设置刷新的颜色和款式及recyclevew
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        //注册成为订阅者
        refreshLayout.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_orange_dark,
                android.R.color.holo_red_dark);
    }
    private void loadData() {

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

//        new HorizontalDividerItemDecoration.Builder(this)
//                .color(Color.RED)
//                .sizeResId(R.dimen.divider)
//                .marginResId(R.dimen.leftmargin, R.dimen.rightmargin)
//                .build()
//
        recyclerView.addItemDecoration( new HorizontalDividerItemDecoration.Builder(this)
                .color(R.color.colorGray)
                .build());
        recyclerView.setAdapter(adapter);

        mPresenter.loadData(Constants.PAGE_NUMBER10, pageIndex);
    }

    /**
     * 初始化adapter
     */
    private void initAdapter() {

        if (adapter == null) {
            adapter = new MyMessageAdapter(R.layout.item_my_message);
            adapter.setOnItemClickListener(this);
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
/*
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

    }*/
    @Override
    public void showContent(String msg) {

    }

    @Override
    public void loadData(List<MeiZiBean> meiZiBeans) {
//        totalPage = funsharingListBeans.getTotalPage();
////返回的数据 第一次
//        if (pageIndex == 1 && adapter.getData().size() <= 0 && (funsharingListBeans.getList() ==
//                null || funsharingListBeans.getList().size() <= 0)) {
//            View emptyView = View.inflate(this, R.layout.partial_empty_view, null);
//            TextView viewById = emptyView.findViewById(R.id.tv_message);
//            adapter.setEmptyView(emptyView);
//        }
        if (adapter.getData()==null||adapter.getData().size()<=0){
            View emptyView = View.inflate(this, R.layout.partial_empty_view, null);
            adapter.setEmptyView(emptyView);
            refreshLayout.setRefreshing(false);
        }
        if (isRefreshing) {
//            adapter.setNewData(meiZiBeans.getList());
            refreshLayout.setRefreshing(false);
        } else {
//            adapter.addData(funsharingListBeans.getList());
            adapter.loadMoreEnd();
            refreshLayout.setRefreshing(false);
        }
    }

    @Override
    public void stateEmpty() {

    }

    @Override
    public void stateLoading() {

    }


    @Override
    public void stateError(Throwable e) {
        if (adapter.getData()==null||adapter.getData().size()<=0){
            View emptyView = View.inflate(this, R.layout.partial_empty_view, null);
            adapter.setEmptyView(emptyView);
        }

        if (adapter.isLoading()){
            adapter.loadMoreEnd();
        }
        if (refreshLayout.isRefreshing()){
            refreshLayout.setRefreshing(false);
        }

    }

    @Override
    public void stateSuccess() {

    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onRefresh() {
        pageIndex = 1;
        isRefreshing = true;
        loadData();
    }

    @Override
    public void onLoadMoreRequested() {
        pageIndex++;
        if (pageIndex > totalPage) {
            if (!isAddNoMoreView) {
                isAddNoMoreView = true;
                View view = getLayoutInflater().inflate(R.layout.partial_no_more_data,
                        null, false);
                adapter.addFooterView(view);
            }
            ToastUtil.Info("没有更多啦~~");
            adapter.setOnLoadMoreListener(this,recyclerView);
            return;
        } else {
            isRefreshing = false;
            loadData();
        }
    }
}
