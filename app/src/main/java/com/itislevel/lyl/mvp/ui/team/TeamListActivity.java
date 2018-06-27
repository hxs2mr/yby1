package com.itislevel.lyl.mvp.ui.team;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.itislevel.lyl.R;
import com.itislevel.lyl.adapter.TeamListAdapter;
import com.itislevel.lyl.app.Constants;
import com.itislevel.lyl.base.RootActivity;
import com.itislevel.lyl.mvp.model.bean.BlessOrderBean;
import com.itislevel.lyl.mvp.model.bean.FileUploadBean;
import com.itislevel.lyl.mvp.model.bean.TeamListBean;
import com.itislevel.lyl.mvp.model.bean.TeamStatusBean;
import com.itislevel.lyl.mvp.model.bean.TeamTypeBean;
import com.itislevel.lyl.mvp.model.bean.TroubleAdviserMyBean;
import com.itislevel.lyl.mvp.ui.troublesolution.TroubleAdviserAddActivity;
import com.itislevel.lyl.mvp.ui.troublesolution.TroubleAdviserDescActivity;
import com.itislevel.lyl.utils.ActivityUtil;
import com.itislevel.lyl.utils.GsonUtil;
import com.itislevel.lyl.utils.SharedPreferencedUtils;
import com.itislevel.lyl.utils.ToastUtil;
import com.itislevel.lyl.utils.rxbus.RxBus;
import com.itislevel.lyl.utils.rxbus.annotation.Subscribe;
import com.itislevel.lyl.utils.rxbus.annotation.UseRxBus;
import com.itislevel.lyl.utils.rxbus.event.EventThread;
import com.orhanobut.logger.Logger;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;

@UseRxBus
public class TeamListActivity extends RootActivity<TeamPresenter> implements TeamContract.View
        , BaseQuickAdapter.OnItemClickListener
        , BaseQuickAdapter.OnItemChildClickListener {

//      bundle.putInt("teamtype",item.getId());
//        bundle.putInt("teamname",item.getId());
//        bundle.putStringArrayList("typeList",typeList);
//

    Bundle bundle = new Bundle();

    private String PROVINCE_ID = "";
    private String CITY_ID = "";
    private String COUNTY_ID = "";

    private int teamtype = 1; //选择的顾问类型


    @BindView(R.id.refreshLayout)
    RefreshLayout refreshLayout;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    //是否已添加没有更多数据的布局
    private boolean isAddNoMoreView = false;
    private int pageIndex = 1;//分页加载时 当前页是第几页
    private int totalPage = 0;//总页数
    private boolean isRefreshing = true;//当前是刷新还是加载
    private TeamListAdapter adapter;
    private ArrayList<String> typeList;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_team_list;
    }

    @Override
    protected void initEventAndData() {
        bundle = this.getIntent().getExtras();
        String title = bundle.getString(Constants.COUNTY_TITLE);
        PROVINCE_ID = bundle.getString(Constants.PROVINCE_ID);
        CITY_ID = bundle.getString(Constants.CITY_ID);

        teamtype = bundle.getInt(Constants.TROUBLE_TEAM_TYPE_FIRST_ID);

        setToolbarTvTitle(title);

        initRefreshListener();
        initAdapter();

        refreshLayout.autoRefresh();//刷新效果
//        loadData();


    }

    private void loadData() {

        Map<String, String> request = new HashMap<>();
        request.put("token", SharedPreferencedUtils.getStr(Constants.USER_TOKEN));
        request.put("usernum", SharedPreferencedUtils.getStr(Constants.USER_NUM));
        request.put("firstcateid", teamtype + "");
        request.put("pageSize", Constants.PAGE_NUMBER10 + "");
        request.put("pageIndex", pageIndex + "");
        request.put("cityid", CITY_ID + "");


        mPresenter.teamList(GsonUtil.obj2JSON(request));
    }

    /**
     * 初始化adapter
     */
    private void initAdapter() {
        if (adapter == null) {
            adapter = new TeamListAdapter(R.layout.item_team);
            adapter.setOnItemClickListener(this);
            adapter.setOnItemChildClickListener(this);
            adapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN); //切换动画
            adapter.setEnableLoadMore(false);
            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
//            GridLayoutManager layoutManager = new GridLayoutManager(this, 3, LinearLayoutManager
//                    .VERTICAL, false);
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
//        refreshLayout.setEnableLoadmore(false);
//        refreshLayout.setEnableRefresh(false);
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
    public void showContent(String msg) {

    }

    @Override
    public void teamRegister(String message) {

    }

    @Override
    public void teamStatus(TeamStatusBean teamStatusBean) {

    }

    @Override
    public void teamList(TeamListBean teamListBean) {
        totalPage = teamListBean.getTotalPage();
        //返回的数据 第一次
        if (adapter.getData().size() <= 0 && teamListBean.getList().size() <= 0) {
            View emptyView = View.inflate(this, R.layout.partial_empty_view, null);
            TextView viewById = emptyView.findViewById(R.id.tv_message);
            adapter.setEmptyView(emptyView);
        }

        if (isRefreshing) {
            adapter.setNewData(teamListBean.getList());
            refreshLayout.finishRefresh();
        } else {
            adapter.addData(teamListBean.getList());
            refreshLayout.finishLoadmore();
        }
    }

    @Override
    public void teamViewCount(String message) {

    }

    @Override
    public void teamProblemAdd(BlessOrderBean message) {

    }


    @Override
    public void teamProblemList(Object object) {

    }

    @Override
    public void teamReplay(Object object) {

    }

    @Override
    public void teamMyProblemList(TroubleAdviserMyBean troubleAdviserMyBean) {

    }

    @Override
    public void teamType(TeamTypeBean teamTypeBean) {

    }

    @Override
    public void uploadHeader(FileUploadBean fileUploadBean) {

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
            TextView viewById = emptyView.findViewById(R.id.tv_message);
            viewById.setText("网络访问错误");
            adapter.setEmptyView(emptyView);
        }

        if (refreshLayout.isLoading()) {
            refreshLayout.finishLoadmore();
        }
        if (refreshLayout.isRefreshing()) {
            refreshLayout.finishRefresh();
        }
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        switch (view.getId()) {
            case R.id.tv_desc:

                TeamListBean.ListBean item = this.adapter.getItem(position);
                bundle.putString("trouble_desc_photo", item.getPhoto());
                bundle.putString("trouble_desc_title", item.getName());
                bundle.putString("trouble_desc_desc", item.getPersonalcv());

                ActivityUtil.getInstance().openActivity(TeamListActivity.this,
                        TroubleAdviserDescActivity
                        .class, bundle);
                break;

            case R.id.iv_header:

                ActivityUtil.getInstance().openActivity(TeamListActivity.this,
                        TroubleAdviserDescActivity
                        .class, bundle);
                break;
            case R.id.tv_go_advice:
                bundle.putString("adviserid", this.adapter.getItem(position).getAdviserid() + "");
                ActivityUtil.getInstance().openActivity(TeamListActivity.this,
                        TroubleAdviserAddActivity
                        .class, bundle);
                break;

        }
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
    }


    @Subscribe(tag = RxBus.TAG_UPDATE, thread = EventThread.MAIN_THREAD)
    public void rxrefresh(String msg) {
        if (msg.equals("refresh")) {
            Logger.i("开始刷新");
            refreshLayout.autoRefresh();//第一次进入触发自动刷新，演示效果--然后在重新加载数据
        }else if(msg.equals("close")){
            ActivityUtil.getInstance().closeActivity(this);
        }
    }

}
