package com.itislevel.lyl.mvp.ui.troublesolution;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.itislevel.lyl.R;
import com.itislevel.lyl.adapter.TroubleTypeAdapter;
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
import com.itislevel.lyl.mvp.ui.address.ProvinceActivity;
import com.itislevel.lyl.utils.ActivityUtil;
import com.itislevel.lyl.utils.GsonUtil;
import com.itislevel.lyl.utils.SharedPreferencedUtils;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

public class TroublesolutionActivity extends RootActivity<TroublesolutionPresenter> implements
        TroublesolutionContract.View, BaseQuickAdapter.OnItemClickListener
        , BaseQuickAdapter.OnItemChildClickListener {


    Bundle bundle = new Bundle();


    @BindView(R.id.refreshLayout)
    RefreshLayout refreshLayout;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    //是否已添加没有更多数据的布局
    private boolean isAddNoMoreView = false;
    private int pageIndex = 1;//分页加载时 当前页是第几页
    private int totalPage = 0;//总页数
    private boolean isRefreshing = true;//当前是刷新还是加载
    private TroubleTypeAdapter adapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_troublesolution;
    }

    @Override
    protected void initEventAndData() {
//        bundle = this.getIntent().getExtras();
//        String title = bundle.getString(Constants.CITY_TITLE);
//        PROVINCE_ID = bundle.getString(Constants.PROVINCE_ID);
//        CITY_ID = bundle.getString(Constants.CITY_ID);

        bundle=new Bundle();
        setToolbarTvTitle("烦恼解答");


        initRefreshListener();
        initAdapter();
        loadData();


    }

    private void loadData() {

        Map<String, String> request = new HashMap<>();
        request.put("token", SharedPreferencedUtils.getStr(Constants.USER_TOKEN));
        request.put("usernum", SharedPreferencedUtils.getStr(Constants.USER_NUM));
        request.put("sign", "fannao");
        mPresenter.troubleType(GsonUtil.obj2JSON(request));
    }

    /**
     * 初始化adapter
     */
    private void initAdapter() {
        if (adapter == null) {
            adapter = new TroubleTypeAdapter(R.layout.item_team_type);
            adapter.setOnItemClickListener(this);
            adapter.setOnItemChildClickListener(this);
            adapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN); //切换动画
            adapter.setEnableLoadMore(false);
//            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            GridLayoutManager layoutManager = new GridLayoutManager(this, 2, LinearLayoutManager
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


//    @OnClick({R.id.iv_trouble_lawsuit, R.id.iv_trouble_renovation, R.id.iv_trouble_emotion,
//            R.id.iv_trouble_education, R.id.iv_trouble_tax, R.id.iv_trouble_sickness, R.id
//            .iv_trouble_marriage,})
//    public void click(View v) {
//
//        Bundle bundle = new Bundle();
//        bundle.putInt(Constants.ACTIVITY_TARGET, Constants.ACTIVITY_TROUBLE);
//        String title = "";
//        switch (v.getId()) {
//            case R.id.iv_trouble_lawsuit://官司咨询
//                title = "官司咨询";
//                bundle.putBoolean(Constants.IS_SHOW_COUNTY, false);
//                bundle.putInt(Constants.TROUBLE_TYPE, Constants.TROUBLE_LAWSUIT);
//
//                break;
//            case R.id.iv_trouble_renovation://装修咨询
//                title = "装修咨询";
//                bundle.putBoolean(Constants.IS_SHOW_COUNTY, false);
//                bundle.putInt(Constants.TROUBLE_TYPE, Constants.TROUBLE_RENOVATION);
//                break;
//            case R.id.iv_trouble_emotion://情感咨询
//                title = "情感咨询";
//                bundle.putBoolean(Constants.IS_SHOW_COUNTY, false);
//                bundle.putInt(Constants.TROUBLE_TYPE, Constants.TROUBLE_EMOTION);
//                break;
//            case R.id.iv_trouble_education://教育咨询
//                title = "教育咨询";
//                bundle.putBoolean(Constants.IS_SHOW_COUNTY, false);
//                bundle.putInt(Constants.TROUBLE_TYPE, Constants.TROUBLE_EDUCATION);
//                break;
//            case R.id.iv_trouble_marriage://婚姻咨询
//                title = "婚姻咨询";
//                bundle.putBoolean(Constants.IS_SHOW_COUNTY, false);
//                bundle.putInt(Constants.TROUBLE_TYPE, Constants.TROUBLE_MARRIAGE);
//                break;
//            case R.id.iv_trouble_tax://税务咨询
//                title = "税务咨询";
//                bundle.putBoolean(Constants.IS_SHOW_COUNTY, false);
//                bundle.putInt(Constants.TROUBLE_TYPE, Constants.TROUBLE_TAX);
//                break;
//            case R.id.iv_trouble_sickness://病痛咨询
//                title = "病痛咨询";
//                bundle.putBoolean(Constants.IS_SHOW_COUNTY, false);
//                bundle.putInt(Constants.TROUBLE_TYPE, Constants.TROUBLE_SICKNESS);
//                break;
//        }
//
//        bundle.putString(Constants.PROVINCE_TITLE, title);
//        bundle.putString(Constants.CITY_TITLE, title);
//        bundle.putString(Constants.COUNTY_TITLE, title);
//        bundle.putString(Constants.TROUBLE_TITLE, title);
//        ActivityUtil.getInstance().openActivity(TroublesolutionActivity.this, ProvinceActivity
//                .class, bundle);
//
//    }

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
        //        totalPage = myGiftBean.getTotalPage();
        if (isRefreshing) {
            adapter.setNewData(troubleTypeBean.getList());
            refreshLayout.finishRefresh();
        } else {
            adapter.addData(troubleTypeBean.getList());
            refreshLayout.finishLoadmore();
        }

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

    }

    @Override
    public void teamViewCount(String message) {

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

        TroubleTypeBean.ListBean item = this.adapter.getItem(position);
        bundle.putInt(Constants.TROUBLE_TEAM_TYPE_FIRST_ID, item.getFirstid());
        bundle.putInt(Constants.TROUBLE_TEAM_TYPE_SECOND_ID, item.getSecondid());
        bundle.putString(Constants.TROUBLE_TEAM_TYPE_NAME, item.getCatename());
        bundle.putBoolean(Constants.IS_SHOW_COUNTY, false);
        bundle.putInt(Constants.ACTIVITY_TARGET, Constants.ACTIVITY_TROUBLE);

        bundle.putString(Constants.PROVINCE_TITLE, item.getCatename());
        bundle.putString(Constants.CITY_TITLE, item.getCatename());
        bundle.putString(Constants.COUNTY_TITLE, item.getCatename());
        bundle.putString(Constants.TROUBLE_TITLE, item.getCatename());
        bundle.putString("add_title",item.getCatename());


        ActivityUtil.getInstance().openActivity(TroublesolutionActivity.this, ProvinceActivity
                .class, bundle);

    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        TroubleTypeBean.ListBean item = this.adapter.getItem(position);
        bundle.putInt(Constants.TROUBLE_TEAM_TYPE_FIRST_ID, item.getFirstid());
        bundle.putInt(Constants.TROUBLE_TEAM_TYPE_SECOND_ID, item.getSecondid());
        bundle.putString(Constants.TROUBLE_TEAM_TYPE_NAME, item.getCatename());
        bundle.putBoolean(Constants.IS_SHOW_COUNTY, false);
        bundle.putInt(Constants.ACTIVITY_TARGET, Constants.ACTIVITY_TROUBLE);

        bundle.putString(Constants.PROVINCE_TITLE, item.getCatename());
        bundle.putString(Constants.CITY_TITLE, item.getCatename());
        bundle.putString(Constants.COUNTY_TITLE, item.getCatename());
        bundle.putString(Constants.TROUBLE_TITLE, item.getCatename());
        bundle.putString("add_title",item.getCatename());


        ActivityUtil.getInstance().openActivity(TroublesolutionActivity.this, ProvinceActivity
                .class, bundle);

    }
}
