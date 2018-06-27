package com.itislevel.lyl.mvp.ui.team;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.itislevel.lyl.R;
import com.itislevel.lyl.adapter.TeamTypeAdapter;
import com.itislevel.lyl.app.Constants;
import com.itislevel.lyl.base.RootActivity;
import com.itislevel.lyl.mvp.model.bean.BlessOrderBean;
import com.itislevel.lyl.mvp.model.bean.FileUploadBean;
import com.itislevel.lyl.mvp.model.bean.TeamListBean;
import com.itislevel.lyl.mvp.model.bean.TeamStatusBean;
import com.itislevel.lyl.mvp.model.bean.TeamTypeBean;
import com.itislevel.lyl.mvp.model.bean.TroubleAdviserMyBean;
import com.itislevel.lyl.mvp.ui.troublesolution.TroubleAdviserMyActivity;
import com.itislevel.lyl.utils.ActivityUtil;
import com.itislevel.lyl.utils.GsonUtil;
import com.itislevel.lyl.utils.SharedPreferencedUtils;
import com.itislevel.lyl.utils.ToastUtil;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class TeamHomeActivity extends RootActivity<TeamPresenter> implements TeamContract.View
        , BaseQuickAdapter.OnItemClickListener
        , BaseQuickAdapter.OnItemChildClickListener {


    Bundle bundle = new Bundle();

    private String PROVINCE_ID = "";
    private String CITY_ID = "";
    private String COUNTY_ID = "";

    @BindView(R.id.refreshLayout)
    RefreshLayout refreshLayout;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @BindView(R.id.btn_team_reg)
    Button btn_team_reg;


    //是否已添加没有更多数据的布局
    private boolean isAddNoMoreView = false;
    private int pageIndex = 1;//分页加载时 当前页是第几页
    private int totalPage = 0;//总页数
    private boolean isRefreshing = true;//当前是刷新还是加载
    private TeamTypeAdapter adapter;
    private ArrayList<String> typeNameList;
    private ArrayList<String> typeIdList;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_team_home;
    }

    @Override
    protected void initEventAndData() {
        bundle = this.getIntent().getExtras();
        String title = bundle.getString(Constants.COUNTY_TITLE);
        PROVINCE_ID = bundle.getString(Constants.PROVINCE_ID);
        CITY_ID = bundle.getString(Constants.CITY_ID);

        setToolbarTvTitle(title);

        initRefreshListener();
        initAdapter();
        loadData();
        loadDataStatus();

    }

    private void loadDataStatus() {
        Map<String, String> request = new HashMap<>();
        request.put("token",SharedPreferencedUtils.getStr(Constants.USER_TOKEN));
        request.put("usernum",SharedPreferencedUtils.getStr(Constants.USER_NUM));
        request.put("adviserid",SharedPreferencedUtils.getStr(Constants.USER_ID));

        mPresenter.teamStatus(GsonUtil.obj2JSON(request));

    }

    private void loadData() {

        Map<String, String> request = new HashMap<>();
        request.put("token", SharedPreferencedUtils.getStr(Constants.USER_TOKEN));
        request.put("usernum", SharedPreferencedUtils.getStr(Constants.USER_NUM));
        request.put("sign", "guwen");
        mPresenter.teamType(GsonUtil.obj2JSON(request));
    }

    /**
     * 初始化adapter
     */
    private void initAdapter() {
        if (adapter == null) {
            adapter = new TeamTypeAdapter(R.layout.item_team_type);
            adapter.setOnItemClickListener(this);
//            adapter.setOnItemChildClickListener(this);
            adapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN); //切换动画
            adapter.setEnableLoadMore(false);
//            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            GridLayoutManager layoutManager = new GridLayoutManager(this, 3, LinearLayoutManager
                    .VERTICAL, false);
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
        refreshLayout.setEnableLoadmore(false);
        refreshLayout.setEnableRefresh(false);
//        refreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
//            @Override
//            public void onLoadmore(RefreshLayout refreshlayout) {
//                pageIndex++;
//                if (pageIndex > totalPage) {
////                    if (!isAddNoMoreView) {
////                        isAddNoMoreView = true;
////                        View view = getLayoutInflater().inflate(R.layout.partial_no_more_data,
////                                null, false);
////                        adapter.addFooterView(view);
////                    }
//                    ToastUtil.Info("没有更多啦~~");
//                    refreshLayout.finishLoadmore(true);//
//                    return;
//                } else {
//                    isRefreshing = false;
//                    loadData();
//                }
//            }
//        });
    }


    @OnClick({R.id.btn_team_reg,R.id.btn_my_consult})
    public void click(View view){
        switch (view.getId()){
            case R.id.btn_team_reg:
                if (btn_team_reg.getText().equals("顾问在线注册")){
                    ActivityUtil.getInstance().openActivity(TeamHomeActivity.this,TeamAgreementActivity.class,bundle);
                }else {
                    ActivityUtil.getInstance().openActivity(TeamHomeActivity.this,TeamWaitingForVerifyActivity.class,bundle);

                }
                break;

            case R.id.btn_my_consult://我的咨询
                ActivityUtil.getInstance().openActivity(TeamHomeActivity.this,TroubleAdviserMyActivity.class,bundle);

                break;
        }
    }

    @Override
    public void showContent(String msg) {

    }

    @Override
    public void teamRegister(String message) {

    }

    @Override
    public void teamStatus(TeamStatusBean teamStatusBean) {
        // (-1 显示在线注册按钮，0 审核中（查看审核进度），1审核通过（隐藏注册按钮）)
        if (teamStatusBean.getIscheck().equals("-1")){
            btn_team_reg.setVisibility(View.VISIBLE);
            btn_team_reg.setText("顾问在线注册");

        }else if(teamStatusBean.getIscheck().equals("0")){
            btn_team_reg.setVisibility(View.VISIBLE);
            btn_team_reg.setText("审核中");

        }else if(teamStatusBean.getIscheck().equals("1")){
            btn_team_reg.setVisibility(View.VISIBLE);
            btn_team_reg.setText("您已经是"+teamStatusBean.getCatename()+"顾问");


        }
    }

    @Override
    public void teamList(TeamListBean teamListBean) {

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

        //        totalPage = myGiftBean.getTotalPage();
        if (isRefreshing) {
            adapter.setNewData(teamTypeBean.getList());
            refreshLayout.finishRefresh();
        } else {
            adapter.addData(teamTypeBean.getList());
            refreshLayout.finishLoadmore();
        }
        typeNameList = new ArrayList<>();
        typeIdList = new ArrayList<>();

        for (TeamTypeBean.ListBean item:teamTypeBean.getList()){
            typeNameList.add(item.getCatename());
            typeIdList.add(item.getId()+"");
        }
        bundle.putStringArrayList(Constants.TROUBLE_TEAM_TYPE_NAME_LIST, typeNameList);
        bundle.putStringArrayList(Constants.TROUBLE_TEAM_TYPE_ID_LIST, typeIdList);

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
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        ToastUtil.Info("child");
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        TeamTypeBean.ListBean item = this.adapter.getItem(position);

        bundle.putInt(Constants.TROUBLE_TEAM_TYPE_FIRST_ID,item.getId());
        bundle.putInt(Constants.TROUBLE_TEAM_TYPE_NAME,item.getId());

        bundle.putString("add_title",item.getCatename());


        ActivityUtil.getInstance().openActivity(TeamHomeActivity.this, TeamListActivity
                        .class, bundle);


    }
}
