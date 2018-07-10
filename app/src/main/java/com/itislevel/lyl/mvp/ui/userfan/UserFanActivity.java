package com.itislevel.lyl.mvp.ui.userfan;

import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.itislevel.lyl.R;
import com.itislevel.lyl.adapter.FanxianListAdapter;
import com.itislevel.lyl.adapter.UserFanListAdapter;
import com.itislevel.lyl.app.Constants;
import com.itislevel.lyl.base.RootActivity;
import com.itislevel.lyl.mvp.model.bean.UserFanBean;
import com.itislevel.lyl.mvp.model.bean.UserHistoryBean;
import com.itislevel.lyl.mvp.model.bean.UserPlanBean;
import com.itislevel.lyl.mvp.model.bean.UserPlanDetailBean;
import com.itislevel.lyl.utils.ActivityUtil;
import com.itislevel.lyl.utils.GsonUtil;
import com.itislevel.lyl.utils.SharedPreferencedUtils;
import com.itislevel.lyl.utils.ToastUtil;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 作者： XS
 * 邮箱：1363826037@qq.com
 * 描述:
 * 创建时间:  2018\7\5 0005 14:22
 */
public class UserFanActivity extends RootActivity<UserFanPresenter>implements UserFanContract.View, SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.OnItemChildClickListener, BaseQuickAdapter.RequestLoadMoreListener
{

    @BindView(R.id.refreshlayout)
    SwipeRefreshLayout refreshLayout;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;
    private View head_view = null;
    private int pageIndex = 1;//分页加载时 当前页是第几页
    private int totalPage = 0;//总页数
    private boolean isRefreshing = true;//当前是刷新还是加载
    private UserFanListAdapter adapter;
    private int adapter_postion;
    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_userfan;
    }

    @Override
    protected void initEventAndData() {
        setToolbarTvTitle("");
        setToolKON();
        refreshLayout.setOnRefreshListener(this);
        refreshLayout.setRefreshing(true);//刷新效果
        head_view = View.inflate(this, R.layout.header_fanxian, null);
        initAdapter();
        loadData();
    }

    private void loadData() {
        Map<String,String> request = new HashMap<>();
        request.put("token", SharedPreferencedUtils.getStr(Constants.USER_TOKEN,""));
        request.put("usernum", SharedPreferencedUtils.getStr(Constants.USER_NUM,""));
        request.put("tradersphone",SharedPreferencedUtils.getStr(Constants.USER_PHONE,""));
        request.put("pageIndex",pageIndex+"");
        request.put("pageSize",Constants.PAGE_NUMBER10+"");
        mPresenter.cashbackPage(GsonUtil.obj2JSON(request));
    }

    private void initAdapter() {
        refreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright, android.R.color.holo_orange_dark, android.R.color.holo_red_dark);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        if (adapter == null) {
            adapter = new UserFanListAdapter(R.layout.item_userfan);
            adapter.setOnItemChildClickListener(this);
            adapter.setOnLoadMoreListener(this, recyclerView);
            adapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN); //切换动画
            adapter.setEnableLoadMore(false);
            adapter.addHeaderView(head_view);//添加头部
            recyclerView.setAdapter(adapter);
            View emptyView = View.inflate(this, R.layout.partial_empty_view, null);
            TextView viewById = emptyView.findViewById(R.id.tv_message);
            viewById.setText("暂无分期记录！");
            adapter.setEmptyView(emptyView);

        }
    }

    @OnClick({R.id.user_fen_jihua, R.id.user_fan_lishi})
    public void Onclick(View view) {
        switch (view.getId())
        {
            case R.id.user_fen_jihua://分期计划
                 ActivityUtil.getInstance().openActivity(this,UserPlanActivity.class);
                break;
            case R.id.user_fan_lishi://返现历史
                ActivityUtil.getInstance().openActivity(this,UserFanHistoryActivity.class);
                break;
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
    public void onRefresh() {
        refreshLayout.setRefreshing(true);//刷新效果
        pageIndex = 1;
        isRefreshing = true;
        loadData();
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        adapter_postion = position;
        switch (view.getId())
        {
            case R.id.user_lin_button:
                loadingDialog.show();
                String monkey = this.adapter.getData().get(position).getPerperiodlimit();
                int moid = this.adapter.getData().get(position).getMerchantid();
                int proid = this.adapter.getData().get(position).getPeriodid();
                Map<String,String> request = new HashMap<>();
                request.put("token", SharedPreferencedUtils.getStr(Constants.USER_TOKEN,""));
                request.put("usernum", SharedPreferencedUtils.getStr(Constants.USER_NUM,""));
                request.put("userid",SharedPreferencedUtils.getStr(Constants.USER_ID,""));
                request.put("periodid",proid+"");
                request.put("perperiodlimit",monkey);
                request.put("merchantid",moid+"");
                mPresenter.clickreceive(GsonUtil.obj2JSON(request));
                break;
        }
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
    @Override
    public void stateError() {
        super.stateError();
        if (adapter.getData() == null || adapter.getData().size() <= 0) {
            View emptyView = View.inflate(this, R.layout.partial_empty_view, null);
            TextView viewById = emptyView.findViewById(R.id.tv_message);
            viewById.setText("暂无分期记录！");
            adapter.setEmptyView(emptyView);
        }
        if (refreshLayout.isRefreshing()) {
            refreshLayout.setRefreshing(false);
        }
        if (adapter.isLoadMoreEnable()) {
            adapter.loadMoreFail();
        }
    }
    @Override
    public void cashbackPage(UserFanBean bean) {
        totalPage = bean.getTotalPage();
        //返回的数据 第一次
        if (pageIndex == 1 && adapter.getData().size() <= 0 && (bean.getList() ==
                null || bean.getList().size() <= 0)) {
            View emptyView = View.inflate(this, R.layout.partial_empty_view, null);
            TextView viewById = emptyView.findViewById(R.id.tv_message);
            viewById.setText("暂无分期记录！");
            adapter.setEmptyView(emptyView);
        }
        if (isRefreshing) {
            adapter.setNewData(bean.getList());
            refreshLayout.setRefreshing(false);
        } else {
            adapter.addData(bean.getList());
            adapter.loadMoreComplete();
        }
    }

    @Override
    public void cashbackstages(UserPlanBean bean) {

    }

    @Override
    public void cashbackstagesDetails(UserPlanDetailBean bean) {

    }

    @Override
    public void cashbackRecord(UserHistoryBean bean) {

    }

    @Override
    public void stateError(Throwable e) {
        super.stateError(e);
        loadingDialog.dismiss();
    }

    @Override
    public void stateError(Exception e) {
        super.stateError(e);
        loadingDialog.dismiss();
    }

    @Override
    public void clickreceive(String data) {//点击领取之后
        ToastUtil.Info("领取成功!");
        this.adapter.remove(adapter_postion);
        loadingDialog.dismiss();
    }
}
