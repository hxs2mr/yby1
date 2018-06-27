package com.itislevel.lyl.mvp.ui.main.dynamic.childfragment.message;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.itislevel.lyl.R;
import com.itislevel.lyl.adapter.MessageAdapter;
import com.itislevel.lyl.adapter.RepairListAdapter;
import com.itislevel.lyl.app.Constants;
import com.itislevel.lyl.base.RootActivity;
import com.itislevel.lyl.mvp.model.bean.MessageBean;
import com.itislevel.lyl.utils.GsonUtil;
import com.itislevel.lyl.utils.SharedPreferencedUtils;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;

/**
 * Created by Administrator on 2018\6\15 0015.
 */

public class MessageshouActivity extends RootActivity<MessagePresenter>implements MessageContract.View, SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {
    @BindView(R.id.refreshLayout)
    SwipeRefreshLayout refreshLayout;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;


    private int load_more =0;
    private int pageIndex = 1;//分页加载时 当前页是第几页
    private int totalPage = 0;//总页数
    private boolean isRefreshing = true;//当前是刷新还是加载

    private MessageAdapter adapter;
    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }
    @Override
    protected int getLayoutId() {
        return R.layout.activity_message;
    }
    @Override
    protected void initEventAndData() {
        setToolbarTvTitle("消息");
        initAdapter();
        refreshLayout.setOnRefreshListener(this);
        refreshLayout.setRefreshing(true);//刷新效果
        LoadData();
    }
    private void LoadData() {
        Map<String, String> request = new HashMap<>();
        request.put("token", SharedPreferencedUtils.getStr(Constants.USER_TOKEN));
        request.put("usernum", SharedPreferencedUtils.getStr(Constants.USER_NUM));
        request.put("userid",  SharedPreferencedUtils.getStr(Constants.USER_ID));
        request.put("pageIndex", pageIndex+"");
        request.put("pageSize", Constants.PAGE_NUMBER10+"");
        System.out.println("JSON消息***********************************"+GsonUtil.obj2JSON(request));
        mPresenter.myDyPushList(GsonUtil.obj2JSON(request));
    }
    /**
     * 初始化adapter
     */
    private void initAdapter() {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        ((SimpleItemAnimator)recyclerView.getItemAnimator()).setSupportsChangeAnimations(false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setHasFixedSize(true);
        refreshLayout.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_orange_dark,
                android.R.color.holo_red_dark);
        if (adapter == null){
            adapter = new MessageAdapter(R.layout.item_message, this);
            adapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_BOTTOM); //切换动画
            adapter.setEnableLoadMore(false);
            adapter.setOnLoadMoreListener(this,recyclerView);
            //adapter.setOnItemChildClickListener(this);
            //adapter.setOnItemClickListener(this);
            //   adapter.isFirstOnly(false);//动画默认只执行一次
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
    public void myDyPushList(MessageBean bean) {
        if ( bean.getList() == null || bean
                .getList().size() <= 0) {
            View emptyView = View.inflate(this, R.layout.partial_empty_view, null);
            TextView viewById = emptyView.findViewById(R.id.tv_message);
            adapter.setEmptyView(emptyView);
        }
        totalPage = bean.getTotalPage();
      if (isRefreshing) {
            adapter.getData().clear();
            adapter.setNewData(bean.getList());
            adapter.notifyDataSetChanged();
            refreshLayout.setRefreshing(false);
        } else {
            adapter.addData(bean.getList());
            adapter.loadMoreComplete();
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Map<String, String> request = new HashMap<>();
        request.put("token", SharedPreferencedUtils.getStr(Constants.USER_TOKEN));
        request.put("usernum", SharedPreferencedUtils.getStr(Constants.USER_NUM));
        request.put("userid",  SharedPreferencedUtils.getStr(Constants.USER_ID));
        mPresenter.clearMyDyPushList(GsonUtil.obj2JSON(request));
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Map<String, String> request = new HashMap<>();
        request.put("token", SharedPreferencedUtils.getStr(Constants.USER_TOKEN));
        request.put("usernum", SharedPreferencedUtils.getStr(Constants.USER_NUM));
        request.put("userid",  SharedPreferencedUtils.getStr(Constants.USER_ID));
        mPresenter.clearMyDyPushList(GsonUtil.obj2JSON(request));
    }

    @Override
    public void clearMyDyPushList(String action) {

    }

    @Override
    public void onRefresh() {
        refreshLayout.setRefreshing(true);
        pageIndex = 1;
        isRefreshing = true;
        LoadData();
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
            LoadData();
        }
    }
}
