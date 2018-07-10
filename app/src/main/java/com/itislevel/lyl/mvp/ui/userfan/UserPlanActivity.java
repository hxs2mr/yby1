package com.itislevel.lyl.mvp.ui.userfan;

import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.itislevel.lyl.R;
import com.itislevel.lyl.adapter.PlanListAdapter;
import com.itislevel.lyl.adapter.ShanListAdapter;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 * 作者： XS
 * 邮箱：1363826037@qq.com
 * 描述: 分期计划
 * 创建时间:  2018\7\5 0005 16:51
 */
public class UserPlanActivity   extends RootActivity<UserFanPresenter> implements UserFanContract.View, BaseQuickAdapter.RequestLoadMoreListener, BaseQuickAdapter.OnItemChildClickListener {
    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;

    @BindView(R.id.total_bi)
    AppCompatTextView total_bi;
    private int pageIndex = 1;//分页加载时 当前页是第几页
    private int totalPage = 0;//总页数
    private boolean isRefreshing = true;//当前是刷新还是加载
    private PlanListAdapter adapter;
    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }
    @Override
    protected int getLayoutId() {
        return R.layout.activity_plan;
    }
    @Override
    protected void initEventAndData() {
        setToolbarTvTitle("分期计划");
        initAdapter();
        loadingDialog.show();
        loadData();
   }
        /**
     * 初始化adapter
     */
    private void initAdapter() {
        if (adapter == null) {
            adapter = new PlanListAdapter(R.layout.item_plan);
            adapter.setOnLoadMoreListener(this,recyclerView);
            adapter.setOnItemChildClickListener(this);
            adapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN); //切换动画
            adapter.setEnableLoadMore(false);
            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(adapter);
            View emptyView = View.inflate(this, R.layout.partial_empty_view, null);
            TextView tv_message = emptyView.findViewById(R.id.tv_message);
            tv_message.setText("暂无分期计划！");
            adapter.setEmptyView(emptyView);
        }
    }
    private void loadData() {
        Map<String,String> request = new HashMap<>();
        request.put("token", SharedPreferencedUtils.getStr(Constants.USER_TOKEN,""));
        request.put("usernum", SharedPreferencedUtils.getStr(Constants.USER_NUM,""));
        request.put("tradersphone",SharedPreferencedUtils.getStr(Constants.USER_PHONE,""));
        request.put("pageIndex",pageIndex+"");
        request.put("pageSize",Constants.PAGE_NUMBER10+"");
        mPresenter.cashbackstages(GsonUtil.obj2JSON(request));
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
            loadData();
        }
    }
    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

        Bundle bundle = new Bundle();
        bundle.putString("tradenum",this.adapter.getData().get(position).getTradenum());
        ActivityUtil.getInstance().openActivity(this,UserFanDetailActivity.class,bundle);
    }

    @Override
    public void cashbackPage(UserFanBean beans) {

    }
    @Override
    public void stateError() {
        super.stateError();
        if (adapter.getData() == null || adapter.getData().size() <= 0) {
            View emptyView = View.inflate(this, R.layout.partial_empty_view, null);
            TextView viewById = emptyView.findViewById(R.id.tv_message);
            viewById.setText("暂无分期计划！");
            adapter.setEmptyView(emptyView);
        }
            loadingDialog.dismiss();
        if (adapter.isLoadMoreEnable()) {
            adapter.loadMoreFail();
        }
    }
    @Override
    public void cashbackstages(UserPlanBean bean) {
        total_bi.setText("共"+bean.getAllRow()+"笔");
        totalPage = bean.getTotalPage();
        //返回的数据 第一次
        if (pageIndex == 1 && adapter.getData().size() <= 0 && (bean.getList() ==
                null || bean.getList().size() <= 0)) {
            View emptyView = View.inflate(this, R.layout.partial_empty_view, null);
            TextView viewById = emptyView.findViewById(R.id.tv_message);
            viewById.setText("暂无分期计划！");
            adapter.setEmptyView(emptyView);
        }
        if (isRefreshing) {
            adapter.setNewData(bean.getList());
            loadingDialog.dismiss();
        } else {
            adapter.addData(bean.getList());
            adapter.loadMoreComplete();
        }
    }
    @Override
    public void cashbackstagesDetails(UserPlanDetailBean bean) {

    }

    @Override
    public void cashbackRecord(UserHistoryBean bean) {

    }

    @Override
    public void clickreceive(String data) {

    }
}
