package com.itislevel.lyl.mvp.ui.main.mine.fan;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.itislevel.lyl.R;
import com.itislevel.lyl.adapter.FamilyListAdapter;
import com.itislevel.lyl.adapter.FanxianListAdapter;
import com.itislevel.lyl.base.RootActivity;
import com.itislevel.lyl.mvp.model.bean.FanRecodeBean;
import com.itislevel.lyl.mvp.model.bean.FanXianBean;
import com.itislevel.lyl.mvp.model.bean.ShanHomeBean;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 作者： XS
 * 邮箱：1363826037@qq.com
 * 描述:我的返现
 * 创建时间:  2018\7\2 0002 09:07
 */
public class PersonFanActivity extends RootActivity<PersonFanPresenter>implements PersonFanContract.View, BaseQuickAdapter.OnItemClickListener, SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {

    @BindView(R.id.refreshLayout)
    SwipeRefreshLayout refreshLayout;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private View head_view = null;
    private int pageIndex = 1;//分页加载时 当前页是第几页
    private int totalPage = 0;//总页数
    private boolean isRefreshing = true;//当前是刷新还是加载
    private FanxianListAdapter adapter;
    private Bundle bundle;
    private String FLAGE="";
    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }
    @Override
    protected int getLayoutId() {
        return R.layout.activity_fanxian;
    }
    @Override
    protected void initEventAndData() {
        bundle = getIntent().getExtras();
        FLAGE = bundle.getString("flage");
        setToolbarVisible();
        head_view = View.inflate(this, R.layout.header_fanxian, null);
        refreshLayout.setOnRefreshListener(this);
       // refreshLayout.setRefreshing(true);//刷新效果
        initAdapter();
        setSearchOnclick(new View.OnClickListener() {//搜索框的点击事件
            @Override
            public void onClick(View view) {

            }
        });
    }
    /**
     * 初始化adapter
     */
    private void initAdapter() {
        refreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright, android.R.color.holo_orange_dark, android.R.color.holo_red_dark);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        if (adapter == null) {
            adapter = new FanxianListAdapter(R.layout.item_fanxian);
            adapter.setOnItemClickListener(this);
            adapter.setOnLoadMoreListener(this,recyclerView);
            adapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN); //切换动画
            adapter.setEnableLoadMore(false);
            adapter.addHeaderView(head_view);//添加头部
            recyclerView.addItemDecoration(new HorizontalDividerItemDecoration.Builder(this)
            .color(Color.parseColor("#e8e8e8"))
            .margin(35,0)
            .build()
            );
            recyclerView.setAdapter(adapter);
           /* View emptyView = View.inflate(this, R.layout.partial_empty_view, null);
            adapter.setEmptyView(emptyView);*/

           //测试的数据
            List<String> list = new ArrayList<>();
            list.add("");
            list.add("");
            list.add("");
            list.add("");
            list.add("");
            adapter.setNewData(list);
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
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onLoadMoreRequested() {

    }

    @Override
    public void merchantmainpage(ShanHomeBean bean) {

    }

    @Override
    public void rechargeRecord(FanRecodeBean bean) {

    }

    @Override
    public void cashbackist(FanXianBean bean) {

    }

    @Override
    public void onlinerecharge(String msg) {

    }
}
