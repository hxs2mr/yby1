package com.itislevel.lyl.mvp.ui.userfan;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.itislevel.lyl.R;
import com.itislevel.lyl.adapter.UserFanDetailAdapter;
import com.itislevel.lyl.adapter.UserFanHistoryAdapter;
import com.itislevel.lyl.app.Constants;
import com.itislevel.lyl.base.RootActivity;
import com.itislevel.lyl.mvp.model.bean.UserFanBean;
import com.itislevel.lyl.mvp.model.bean.UserHistoryBean;
import com.itislevel.lyl.mvp.model.bean.UserPlanBean;
import com.itislevel.lyl.mvp.model.bean.UserPlanDetailBean;
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
 * 描述:  分期计划的查看详情
 * 创建时间:  2018\7\6 0006 09:06
 */
public class UserFanDetailActivity extends RootActivity<UserFanPresenter>implements UserFanContract.View, BaseQuickAdapter.RequestLoadMoreListener {
    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;

    @BindView(R.id.total_qi)
    AppCompatTextView total_qi;

    @BindView(R.id.fan_qi)
    AppCompatTextView fan_qi;

    @BindView(R.id.sheng_qi)
    AppCompatTextView sheng_qi;

    private int pageIndex = 1;//分页加载时 当前页是第几页
    private int totalPage = 0;//总页数
    private boolean isRefreshing = true;//当前是刷新还是加载
    private UserFanDetailAdapter adapter;
    private Bundle bundle;
    private String tradenum="";
    @Override
    protected void initInject() {
     getActivityComponent().inject(this);
    }
    @Override
    protected int getLayoutId() {
        return R.layout.activity_userdetail;
    }

    @Override
    protected void initEventAndData() {
        bundle = getIntent().getExtras();
        tradenum = bundle.getString("tradenum");
        setToolbarTvTitle("详情页");
        initAdapter();
        loadingDialog.show();
        loadData();
    }

    private void loadData() {
        Map<String,String> request = new HashMap<>();
        request.put("token", SharedPreferencedUtils.getStr(Constants.USER_TOKEN,""));
        request.put("usernum", SharedPreferencedUtils.getStr(Constants.USER_NUM,""));
        request.put("tradenum", tradenum);
        mPresenter.cashbackstagesDetails(GsonUtil.obj2JSON(request));
    }
    /**
     * 初始化adapter
     */
    private void initAdapter() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        if (adapter == null) {
            adapter = new UserFanDetailAdapter(R.layout.item_fandetail);
            adapter.setOnLoadMoreListener(this,recyclerView);
            adapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN); //切换动画
            adapter.setEnableLoadMore(false);
            recyclerView.addItemDecoration(new HorizontalDividerItemDecoration.Builder(this)
                    .color(Color.parseColor("#e8e8e8"))
                    .margin(35,0)
                    .build());
            recyclerView.setAdapter(adapter);
            View emptyView = View.inflate(this, R.layout.partial_empty_view, null);
            TextView viewById = emptyView.findViewById(R.id.tv_message);
            viewById.setText("暂无返现详情！");
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
        adapter.setEnableLoadMore(true);
        pageIndex++;
        if (pageIndex > totalPage) {
            adapter.loadMoreEnd();
            return;
        } else {
            isRefreshing = false;
            //  loadData();
        }
    }

    @Override
    public void cashbackPage(UserFanBean beans) {

    }

    @Override
    public void stateError() {
        super.stateError();
        loadingDialog.dismiss();
    }

    @Override
    public void cashbackstages(UserPlanBean bean) {

    }

    @Override
    public void cashbackstagesDetails(UserPlanDetailBean bean) {//分区详情
        total_qi.setText(bean.getList().size()+"");
        fan_qi.setText(bean.getCount()+"");
        sheng_qi.setText((bean.getList().size() - bean.getCount())+"");

        adapter.setNewData(bean.getList());
        loadingDialog.dismiss();
    }

    @Override
    public void cashbackRecord(UserHistoryBean bean) {

    }

    @Override
    public void clickreceive(String data) {

    }
}
