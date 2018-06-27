package com.itislevel.lyl.mvp.ui.main.home.homeright;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.itislevel.lyl.R;
import com.itislevel.lyl.adapter.CFAllPinAdapter;
import com.itislevel.lyl.adapter.HomeRightAdapter;
import com.itislevel.lyl.base.RootActivity;

import butterknife.BindView;

/**
 * Created by Administrator on 2018\6\14 0014.
 */

public class HomRightActivity extends RootActivity<HomeRightPresenter>implements HomeRightContract.View, BaseQuickAdapter.RequestLoadMoreListener, SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.OnItemClickListener {
    @BindView(R.id.refreshLayout)
    SwipeRefreshLayout refreshLayout;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;

    private int load_more =0;
    private int pageIndex = 1;//分页加载时 当前页是第几页
    private int totalPage = 0;//总页数
    private boolean isRefreshing = true;//当前是刷新还是加载

    private HomeRightAdapter adapter;
    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }
    @Override
    protected int getLayoutId() {
        return R.layout.activity_homeright;
    }

    @Override
    protected void initEventAndData() {
        setToolbarTvTitle("消息");
        initAdapter();
        refreshLayout.setOnRefreshListener(this);
        refreshLayout.setRefreshing(true);//刷新效果

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                refreshLayout.setRefreshing(false);
            }
        },1500);
    }

     private void initAdapter() {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        // ((SimpleItemAnimator)recyclerView.getItemAnimator()).setSupportsChangeAnimations(false);
        recyclerView.setLayoutManager(manager);
        //注册成为订阅者
        refreshLayout.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_orange_dark,
                android.R.color.holo_red_dark);
        if (adapter == null){
            adapter = new HomeRightAdapter(R.layout.allpin_item, this);
            adapter.setOnItemClickListener(this);
            adapter.setOnLoadMoreListener(this,recyclerView);
            adapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_BOTTOM); //切换动画
            adapter.setEnableLoadMore(false);
          /*  recyclerView.addItemDecoration(new HorizontalDividerItemDecoration.Builder(this)
                    .build());*/
            recyclerView.setAdapter(adapter);
            View emptyView = View.inflate(this, R.layout.partial_empty_view, null);
            adapter.setEmptyView(emptyView);
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
    public void onLoadMoreRequested() {

    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

    }
}
