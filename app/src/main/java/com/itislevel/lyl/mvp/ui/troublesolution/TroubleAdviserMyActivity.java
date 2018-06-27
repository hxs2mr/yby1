package com.itislevel.lyl.mvp.ui.troublesolution;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.itislevel.lyl.R;
import com.itislevel.lyl.adapter.TroubleAdviserMyAdapter;
import com.itislevel.lyl.app.Constants;
import com.itislevel.lyl.base.RootActivity;
import com.itislevel.lyl.mvp.model.bean.BlessOrderBean;
import com.itislevel.lyl.mvp.model.bean.FileUploadBean;
import com.itislevel.lyl.mvp.model.bean.MeiZiBean;
import com.itislevel.lyl.mvp.model.bean.TroubleAddBean;
import com.itislevel.lyl.mvp.model.bean.TroubleAdviserMyBean;
import com.itislevel.lyl.mvp.model.bean.TroubleCommentAdd;
import com.itislevel.lyl.mvp.model.bean.TroubleListBean;
import com.itislevel.lyl.mvp.model.bean.TroubleTypeBean;
import com.itislevel.lyl.utils.GsonUtil;
import com.itislevel.lyl.utils.SharedPreferencedUtils;
import com.itislevel.lyl.utils.ToastUtil;
import com.itislevel.lyl.utils.rxbus.RxBus;
import com.itislevel.lyl.utils.rxbus.annotation.Subscribe;
import com.itislevel.lyl.utils.rxbus.annotation.UseRxBus;
import com.itislevel.lyl.utils.rxbus.event.EventThread;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

@UseRxBus
public class TroubleAdviserMyActivity extends RootActivity<TroublesolutionPresenter> implements
        TroublesolutionContract.View, BaseQuickAdapter.OnItemClickListener
        , BaseQuickAdapter.OnItemChildClickListener {
    Bundle bundle = new Bundle();
    private String PROVINCE_ID = "";
    private String CITY_ID = "";
    private String COUNTY_ID = ""; //很多情况下是空的

    private String PROVINCE_TITLE = "";
    private String CITY_TITLE = "";
    private String COUNTY_TITLE = "";//很多情况下是空的


    @BindView(R.id.refreshLayout)
    RefreshLayout refreshLayout;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    //   tv_problem tv_answer

    //是否已添加没有更多数据的布局
    private boolean isAddNoMoreView = false;
    private int pageIndex = 1;//分页加载时 当前页是第几页
    private int totalPage = 0;//总页数
    private boolean isRefreshing = true;//当前是刷新还是加载
    private TroubleAdviserMyAdapter adapter;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_trouble_adviser_my;
    }

    @Override
    protected void initEventAndData() {

        bundle = this.getIntent().getExtras();
        String title = bundle.getString(Constants.TROUBLE_TITLE);

        PROVINCE_ID = bundle.getString(Constants.PROVINCE_ID);
        CITY_ID = bundle.getString(Constants.CITY_ID);
        COUNTY_ID = bundle.getString(Constants.COUNTY_ID);

        PROVINCE_TITLE = bundle.getString(Constants.PROVINCE_TITLE);
        CITY_TITLE = bundle.getString(Constants.CITY_TITLE);
        COUNTY_TITLE = bundle.getString(Constants.COUNTY_TITLE);

        setToolbarTvTitle("顾问咨询");

        initRefreshListener();
        initAdapter();

        refreshLayout.autoRefresh();//刷新效果
//        loadData();

    }


    private void loadData() {

        Map<String, String> request = new HashMap<>();
        request.put("token", SharedPreferencedUtils.getStr(Constants.USER_TOKEN));
        request.put("usernum", SharedPreferencedUtils.getStr(Constants.USER_NUM));
        request.put("userid", SharedPreferencedUtils.getStr(Constants.USER_ID));
        request.put("pageIndex", pageIndex + "");
        request.put("pageSize", Constants.PAGE_NUMBER10 + "");
        int anInt = bundle.getInt(Constants.TROUBLE_TEAM_TYPE_FIRST_ID);
        if (anInt == 0) {
            request.put("firstcateid", "");
        } else {
            request.put("firstcateid", anInt + "");
        }
        mPresenter.teamMyProblemList(GsonUtil.obj2JSON(request));
    }

    /**
     * 初始化adapter
     */
    private void initAdapter() {
        if (adapter == null) {
            adapter = new TroubleAdviserMyAdapter(R.layout.item_trouble_adviser_my);
            adapter.setOnItemClickListener(this);
            adapter.setOnItemChildClickListener(this);
            adapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN); //切换动画
            adapter.setEnableLoadMore(false);
            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(adapter);
        }
    }

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
        if (adapter.getData()==null||adapter.getData().size()<=0){
            View emptyView = View.inflate(this, R.layout.partial_empty_view, null);
            TextView viewById = emptyView.findViewById(R.id.tv_message);
            viewById.setText("网络访问错误");
            adapter.setEmptyView(emptyView);
        }

        if (refreshLayout.isLoading()){
            refreshLayout.finishLoadmore();
        }
        if (refreshLayout.isRefreshing()){
            refreshLayout.finishRefresh();
        }
    }

    @Override
    public void showContent(String msg) {

    }

    @Override
    public void showDataList(List<MeiZiBean> meiZiBeans) {

    }

    @Override
    public void troubleAdd(TroubleAddBean troubleAdd) {

    }

    @Override
    public void troubleList(TroubleListBean troubleListBean) {

    }

    @Override
    public void troubleListMy(TroubleListBean troubleListBean) {

    }

    @Override
    public void troubleDel(String action) {

    }

    @Override
    public void troubleCommentReplay(TroubleCommentAdd troubleCommentAdd) {

    }

    @Override
    public void troubleCommentDel(String action) {

    }

    @Override
    public void troubleType(TroubleTypeBean troubleTypeBean) {

    }

    @Override
    public void teamProblemAdd(String message) {

    }

    @Override
    public void teamProblemList(Object object) {

    }

    @Override
    public void teamReplay(Object object) {

    }

    @Override
    public void teamMyProblemList(TroubleAdviserMyBean troubleAdviserMyBean) {
        totalPage = troubleAdviserMyBean.getTotalPage();

//返回的数据 第一次
        if (adapter.getData().size() <= 0 && troubleAdviserMyBean.getList().size() <= 0) {
            View emptyView = View.inflate(this, R.layout.partial_empty_view, null);
            TextView viewById = emptyView.findViewById(R.id.tv_message);
            adapter.setEmptyView(emptyView);
        }

        if (isRefreshing) {
            adapter.setNewData(troubleAdviserMyBean.getList());
            refreshLayout.finishRefresh();
        } else {

            adapter.addData(troubleAdviserMyBean.getList());
            refreshLayout.finishLoadmore();
        }
    }

    @Override
    public void teamViewCount(String message) {

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

    }

    @Subscribe(tag = RxBus.TAG_UPDATE, thread = EventThread.MAIN_THREAD)
    public void rxrefresh(String msg) {
        ToastUtil.Success("提问成功");
        refreshLayout.autoRefresh();//第一次进入触发自动刷新，演示效果--然后在重新加载数据
    }

}
