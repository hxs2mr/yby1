package com.itislevel.lyl.mvp.ui.funsharing;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.itislevel.lyl.R;
import com.itislevel.lyl.adapter.FunsharingMyAdapter;
import com.itislevel.lyl.app.Constants;
import com.itislevel.lyl.base.RootActivity;
import com.itislevel.lyl.mvp.model.bean.FileUploadBean;
import com.itislevel.lyl.mvp.model.bean.FunsharingAddBean;
import com.itislevel.lyl.mvp.model.bean.FunsharingCommnetAddBean;
import com.itislevel.lyl.mvp.model.bean.FunsharingLikeBean;
import com.itislevel.lyl.mvp.model.bean.FunsharingListBean;
import com.itislevel.lyl.mvp.model.bean.FunsharingMyBean;
import com.itislevel.lyl.mvp.model.bean.MeiZiBean;
import com.itislevel.lyl.mvp.model.bean.MeiZiMultipleBean;
import com.itislevel.lyl.utils.ActivityUtil;
import com.itislevel.lyl.utils.GsonUtil;
import com.itislevel.lyl.utils.SharedPreferencedUtils;
import com.itislevel.lyl.utils.ToastUtil;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

public class FunsharingMyActivity extends RootActivity<FunsharingPresenter> implements
        FunsharingContract.View
        , BaseQuickAdapter.OnItemClickListener
        , BaseQuickAdapter.OnItemChildClickListener, SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {

    @BindView(R.id.refreshLayout)
    SwipeRefreshLayout refreshLayout;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;


    //是否已添加没有更多数据的布局
    private boolean isAddNoMoreView = false;
    private int pageIndex = 1;//分页加载时 当前页是第几页
    private int totalPage = 0;//总页数
    private boolean isRefreshing = true;//当前是刷新还是加载
    private FunsharingMyAdapter adapter;
   private int load_more  =0;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_funsharing_my;
    }

    @Override
    protected void initEventAndData() {
        setToolbarTvTitle("我的分享");
        initAdapter();
        initRefreshLayout_recyclview();
        refreshLayout.setOnRefreshListener(this);
        refreshLayout.setRefreshing(true);//刷新效果
        loadData();
    }

    @Override
    protected void onResume() {
        super.onResume();
//        loadData();
    }
    private void initRefreshLayout_recyclview(){//设置刷新的颜色和款式及recyclevew
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        //注册成为订阅者
        refreshLayout.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_orange_dark,
                android.R.color.holo_red_dark);
        refreshLayout.setProgressViewOffset(true,0,200);
    }



    private void loadData() {
        Map<String, String> request = new HashMap<>();
        request.put("token", SharedPreferencedUtils.getStr(Constants.USER_TOKEN));
        request.put("usernum", SharedPreferencedUtils.getStr(Constants.USER_NUM));
        request.put("userid", SharedPreferencedUtils.getStr(Constants.USER_ID));
        request.put("pageIndex", pageIndex + "");
        request.put("pageSize", Constants.PAGE_NUMBER20 + "");

        mPresenter.shareListMy(GsonUtil.obj2JSON(request));

    }

    /**
     * 初始化adapter
     */
    private void initAdapter() {
        if (adapter == null) {
            adapter = new FunsharingMyAdapter(R.layout.item_funsharing_my);
            adapter.setOnItemClickListener(this);
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


    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
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

        if (adapter.isLoading()) {
            adapter.loadMoreComplete();
        }
        if (refreshLayout.isRefreshing()) {
            refreshLayout.setRefreshing(false);
        }
    }

    @Override
    public void showContent(String msg) {

    }

    @Override
    public void showData(List<MeiZiBean> data) {

    }

    @Override
    public void showDataMultiple(List<MeiZiMultipleBean> data) {

    }

    @Override
    public void addDynamic(String funsharingAddBean) {

    }


    @Override
    public void shareList(FunsharingListBean funsharingListBeans) {

    }

    @Override
    public void shareListMy(FunsharingMyBean funsharingMyBeans) {
        load_more++;
        if(load_more==1)
        {
            adapter.setOnLoadMoreListener(this,recyclerView);//设置加载更多
        }

        totalPage = funsharingMyBeans.getTotalPage();

//返回的数据 第一次
        if (pageIndex==1&&adapter.getData().size() <= 0 && (funsharingMyBeans.getList()==null||funsharingMyBeans.getList().size() <= 0)) {
            View emptyView = View.inflate(this, R.layout.partial_empty_view, null);
            TextView viewById = emptyView.findViewById(R.id.tv_message);
            adapter.setEmptyView(emptyView);
        }
        if (isRefreshing) {
            adapter.setNewData(funsharingMyBeans.getList());
            refreshLayout.setRefreshing(false);
        } else {
            adapter.addData(funsharingMyBeans.getList());
            adapter.loadMoreComplete();

        }
    }

    int delPosition = -1;//删除的位置

    @Override
    public void shareDel(String message) {
        this.adapter.remove(delPosition);
        delPosition = -1;
        ToastUtil.Success("删除成功");



    }

    @Override
    public void commentShareAdd(FunsharingCommnetAddBean funsharingCommnetAddBean) {

    }


    @Override
    public void commentShareDel(String message) {

    }

    @Override
    public void shareLikeOrCancel(FunsharingLikeBean funsharingLikeBean) {

    }

    @Override
    public void uploadHeader(FileUploadBean fileUploadBean) {

    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        switch (view.getId()) {
            case R.id.tv_del:
                Map<String, String> request = new HashMap<>();
                request.put("token", SharedPreferencedUtils.getStr(Constants.USER_TOKEN));
                request.put("usernum", SharedPreferencedUtils.getStr(Constants.USER_NUM));
                request.put("id", this.adapter.getItem(position).getId() + "");
                delPosition = position;
                mPresenter.shareDel(GsonUtil.obj2JSON(request));
                break;
            case R.id.tv_title:
                Bundle bunde=new Bundle();
                FunsharingMyBean.ListBean item = this.adapter.getItem(position);
                bunde.putString("item",GsonUtil.obj2JSON(item));
                ActivityUtil.getInstance().openActivity(FunsharingMyActivity.this,FunsharingDetailActivity.class,bunde);
                break;
        }
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
            ToastUtil.Info("没有更多啦~~");
            adapter.loadMoreEnd(true);//
            return;
        } else {
            isRefreshing = false;
            loadData();
        }
    }
}
