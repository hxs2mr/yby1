package com.itislevel.lyl.mvp.ui.mygift;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.itislevel.lyl.R;
import com.itislevel.lyl.adapter.MyGiftListAdapter;
import com.itislevel.lyl.app.Constants;
import com.itislevel.lyl.base.RootActivity;
import com.itislevel.lyl.mvp.model.bean.MyGiftBean;
import com.itislevel.lyl.utils.GsonUtil;
import com.itislevel.lyl.utils.SharedPreferencedUtils;
import com.itislevel.lyl.utils.ToastUtil;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;

public class MyGiftListActivity extends RootActivity<MyGiftPresenter>
        implements MyGiftContract.View
        , BaseQuickAdapter.OnItemClickListener
        , BaseQuickAdapter.OnItemChildClickListener , SwipeRefreshLayout.OnRefreshListener,BaseQuickAdapter.RequestLoadMoreListener{

    @BindView(R.id.refreshLayout)
    SwipeRefreshLayout refreshLayout;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    //是否已添加没有更多数据的布局
    private boolean isAddNoMoreView = false;
    private int pageIndex = 1;//分页加载时 当前页是第几页
    private int totalPage = 0;//总页数
    private boolean isRefreshing = true;//当前是刷新还是加载
    private MyGiftListAdapter adapter;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_gift_list;
    }

    @Override
    protected void initEventAndData() {
        setToolbarTvTitle("礼品卷详情");
        initRefreshLayout_recyclview();

        initAdapter();
        refreshLayout.setRefreshing(true);//刷新效果

        loadData();
//loadData();
    }

    private void initRefreshLayout_recyclview(){//设置刷新的颜色和款式及recyclevew
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        //注册成为订阅者
        refreshLayout.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_orange_dark,
                android.R.color.holo_red_dark);
        refreshLayout.setProgressViewOffset(true,50,300);
    }

    private void loadData() {

        Map<String, String> request = new HashMap<>();
        request.put("token", SharedPreferencedUtils.getStr(Constants.USER_TOKEN));
        request.put("usernum", SharedPreferencedUtils.getStr(Constants.USER_NUM));
        request.put("userid", SharedPreferencedUtils.getStr(Constants.USER_ID));

        mPresenter.userGiftList(GsonUtil.obj2JSON(request));
    }

    /**
     * 初始化adapter
     */
    private void initAdapter() {
        if (adapter == null) {
            adapter = new MyGiftListAdapter(R.layout.item_gift);
            adapter.setOnItemClickListener(this);
//            adapter.setOnItemChildClickListener(this);
            adapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN); //切换动画
            adapter.setEnableLoadMore(false);

            adapter.setEmptyView(R.layout.item_refresh_loading_view, (ViewGroup) recyclerView.getParent());

            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(adapter);
        }
    }


    @Override
    public void showContent(String msg) {

    }

    @Override
    public void userGiftList(MyGiftBean myGiftBean) {
//        totalPage = myGiftBean.getTotalPage();

        //返回的数据 第一次
        if (pageIndex == 1 && adapter.getData().size() <= 0 && (myGiftBean.getListput() ==
                null || myGiftBean.getListput().size() <= 0)) {
            View emptyView = View.inflate(this, R.layout.partial_empty_view, null);
            TextView viewById = emptyView.findViewById(R.id.tv_message);
            adapter.setEmptyView(emptyView);
            refreshLayout.setRefreshing(false);
        }
        if (isRefreshing) {
            adapter.setNewData(myGiftBean.getListput());
            refreshLayout.setRefreshing(false);
        } else {
            adapter.addData(myGiftBean.getListput());
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
        super.stateError(e);

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
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        ToastUtil.Success(position+":pos");
    }

    @Override
    public void onRefresh() {//
        pageIndex = 1;
        isRefreshing = true;
        loadData();
    }

    @Override
    public void onLoadMoreRequested() {
        pageIndex++;
        if (pageIndex > totalPage) {
//                    if (!isAddNoMoreView) {
//                        isAddNoMoreView = true;
//                        View view = getLayoutInflater().inflate(R.layout.partial_no_more_data,
//                                null, false);
//                        adapter.addFooterView(view);
//                    }
            ToastUtil.Info("没有更多啦~~");
            adapter.setOnLoadMoreListener(this,recyclerView);
            return;
        } else {
            isRefreshing = false;
            loadData();
        }
    }
}
