package com.itislevel.lyl.mvp.ui.main.mine.fan;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.itislevel.lyl.R;
import com.itislevel.lyl.adapter.ChongJiluAdapter;
import com.itislevel.lyl.adapter.FanxianJiluAdapter;
import com.itislevel.lyl.app.Constants;
import com.itislevel.lyl.base.RootActivity;
import com.itislevel.lyl.mvp.model.bean.FanRecodeBean;
import com.itislevel.lyl.mvp.model.bean.FanXianBean;
import com.itislevel.lyl.mvp.model.bean.ShanHomeBean;
import com.itislevel.lyl.utils.GsonUtil;
import com.itislevel.lyl.utils.SharedPreferencedUtils;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 * 作者： XS
 * 邮箱：1363826037@qq.com
 * 描述: 充值记录
 * 创建时间:  2018\7\3 0003 11:47
 */
public class PersonChonjiluActivity extends RootActivity<PersonFanPresenter>implements PersonFanContract.View, BaseQuickAdapter.RequestLoadMoreListener {

    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;
    private int pageIndex = 1;//分页加载时 当前页是第几页
    private int totalPage = 0;//总页数
    private boolean isRefreshing = true;//当前是刷新还是加载
    private ChongJiluAdapter adapter;
    @Override
    protected void initInject() {
     getActivityComponent().inject(this);
    }
    @Override
    protected int getLayoutId() {
        return R.layout.activity_jilu;
    }
    @Override
    protected void initEventAndData() {
        setToolbarTvTitle("充值记录");
        initAdapter();
        loadData();
    }

    private void loadData() {
        loadingDialog.show();
        Map<String,String> request = new HashMap<>();
        request.put("token", SharedPreferencedUtils.getStr(Constants.USER_TOKEN,""));
        request.put("usernum", SharedPreferencedUtils.getStr(Constants.USER_NUM,""));
        request.put("merchantid",SharedPreferencedUtils.getStr("fan_merchantid",""));
        request.put("pageIndex",pageIndex+"");
        request.put("pageSize",Constants.PAGE_NUMBER10+"");
        mPresenter.rechargeRecord(GsonUtil.obj2JSON(request));
    }

    /**
     * 初始化adapter
     */
    private void initAdapter() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        if (adapter == null) {
            adapter = new ChongJiluAdapter(R.layout.item_chonjilu);
            adapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN); //切换动画
            adapter.setOnLoadMoreListener(this,recyclerView);
            adapter.setEnableLoadMore(false);
            recyclerView.addItemDecoration(new HorizontalDividerItemDecoration.Builder(this)
                    .color(Color.parseColor("#e8e8e8"))
                    .margin(35,0)
                    .build());
            recyclerView.setAdapter(adapter);
            View emptyView = View.inflate(this, R.layout.partial_empty_view, null);
            TextView tv_message = emptyView.findViewById(R.id.tv_message);
            tv_message.setText("暂无充值记录！");
            adapter.setEmptyView(emptyView);
        }
    }

    @Override
    public void stateEmpty() {

    }
    @Override
    public void stateError() {
        super.stateError();
        if (adapter.getData() == null || adapter.getData().size() <= 0) {
            View emptyView = View.inflate(this, R.layout.partial_empty_view, null);
            TextView viewById = emptyView.findViewById(R.id.tv_message);
            viewById.setText("暂无充值记录！");
            adapter.setEmptyView(emptyView);
        }
        if (loadingDialog.isShowing()) {
            loadingDialog.dismiss();
        }
        if (adapter.isLoadMoreEnable()) {
            adapter.loadMoreFail();
        }
    }
    @Override
    public void stateLoading() {

    }

    @Override
    public void stateSuccess() {

    }

    @Override
    public void merchantmainpage(ShanHomeBean bean) {

    }

    @Override
    public void rechargeRecord(FanRecodeBean bean) {
        totalPage = bean.getTotalPage();
//返回的数据 第一次
        if (pageIndex == 1 && adapter.getData().size() <= 0 && (bean.getList() ==
                null || bean.getList().size() <= 0)) {
            View emptyView = View.inflate(this, R.layout.partial_empty_view, null);
            TextView viewById = emptyView.findViewById(R.id.tv_message);
            viewById.setText("暂无充值记录！");
            adapter.setEmptyView(emptyView);
        }
        if (isRefreshing) {
                 loadingDialog.dismiss();
                adapter.setNewData(bean.getList());
        } else {
            adapter.addData(bean.getList());
            adapter.loadMoreComplete();
        }
    }

    @Override
    public void cashbackist(FanXianBean bean) {

    }

    @Override
    public void onlinerecharge(String msg) {

    }

    @Override
    public void onLoadMoreRequested() {
        adapter.setEnableLoadMore(true);
        pageIndex++;
        if (pageIndex > totalPage) {
            adapter.loadMoreEnd();
            return;
        } else {
            isRefreshing = false;
            loadData();
        }
    }
}
