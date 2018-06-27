package com.itislevel.lyl.mvp.ui.blessing;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.itislevel.lyl.R;
import com.itislevel.lyl.adapter.BlessReceiveYuListAdapter;
import com.itislevel.lyl.app.Constants;
import com.itislevel.lyl.base.RootActivity;
import com.itislevel.lyl.mvp.model.bean.AliPayBean;
import com.itislevel.lyl.mvp.model.bean.BlessAddBean;
import com.itislevel.lyl.mvp.model.bean.BlessAddLikeBean;
import com.itislevel.lyl.mvp.model.bean.BlessCartListBean;
import com.itislevel.lyl.mvp.model.bean.BlessCommentBean;
import com.itislevel.lyl.mvp.model.bean.BlessDetailGiftListBean;
import com.itislevel.lyl.mvp.model.bean.BlessGiftBean;
import com.itislevel.lyl.mvp.model.bean.BlessListBean;
import com.itislevel.lyl.mvp.model.bean.BlessOrderBean;
import com.itislevel.lyl.mvp.model.bean.BlessReceiveGiftBean;
import com.itislevel.lyl.mvp.model.bean.BlessReceiveYuBean;
import com.itislevel.lyl.mvp.model.bean.BlessSendGiftBean;
import com.itislevel.lyl.mvp.model.bean.CartUpdateBean;
import com.itislevel.lyl.mvp.model.bean.FileUploadBean;
import com.itislevel.lyl.mvp.model.bean.HappyBlessUsualLanguageBean;
import com.itislevel.lyl.utils.GsonUtil;
import com.itislevel.lyl.utils.SAToast;
import com.itislevel.lyl.utils.SharedPreferencedUtils;
import com.itislevel.lyl.utils.ToastUtil;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;

public class BlessReceiveBlessYuActivity extends RootActivity<BlessingPresenter> implements
        BlessingContract.View
        , BaseQuickAdapter.OnItemClickListener
        , BaseQuickAdapter.OnItemChildClickListener {


    @BindView(R.id.refreshLayout)
    RefreshLayout refreshLayout;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    //是否已添加没有更多数据的布局
    private boolean isAddNoMoreView = false;
    private int pageIndex = 1;//分页加载时 当前页是第几页
    private int totalPage = 0;//总页数
    private boolean isRefreshing = true;//当前是刷新还是加载
    private BlessReceiveYuListAdapter adapter;

    Bundle bundle;
    String happyid="";

    @Override
    protected int getLayoutId() {
        return R.layout.activity_bless_receive_bless_yu;
    }

    @Override
    protected void initEventAndData() {
        bundle=getIntent().getExtras();
        setToolbarTvTitle("收到的祝福");

        happyid=bundle.getString("blessid");

        initRefreshListener();
        initAdapter();
        loadData();

    }

    private void loadData() {

        Map<String, String> request = new HashMap<>();
        request.put("token", SharedPreferencedUtils.getStr(Constants.USER_TOKEN));
        request.put("usernum", SharedPreferencedUtils.getStr(Constants.USER_NUM));
        request.put("pageIndex", pageIndex+"");
        request.put("pageSize", Constants.PAGE_NUMBER10+"");
        request.put("receiveuserid", SharedPreferencedUtils.getStr(Constants.USER_ID));
        request.put("happyid", happyid);

        mPresenter.happyBlessReceiveList(GsonUtil.obj2JSON(request));
    }


    /**
     * 初始化adapter
     */
    private void initAdapter() {
        if (adapter == null) {
            adapter = new BlessReceiveYuListAdapter(R.layout.item_bless_yu_receive);
            adapter.setOnItemClickListener(this);
            adapter.setOnItemChildClickListener(this);
            adapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN); //切换动画
            adapter.setEnableLoadMore(false);
            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
//            GridLayoutManager layoutManager = new GridLayoutManager(this, 3, LinearLayoutManager
//                    .VERTICAL, false);
            recyclerView.addItemDecoration(new HorizontalDividerItemDecoration.Builder(this)
                    .color(R.color.colorGray)
                    .build());
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
                    SAToast.makeText(BlessReceiveBlessYuActivity.this,"没有更多啦~~").show();
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
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

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
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    public void showContent(String msg) {

    }

    @Override
    public void happAdd(String action) {

    }

    @Override
    public void happyListMy(BlessListBean blessListBean) {

    }

    @Override
    public void happyViewCount(String action) {

    }

    @Override
    public void happyDel(String action) {

    }

    @Override
    public void happyList(BlessListBean blessListBean) {

    }

    @Override
    public void happyComment(BlessCommentBean blessCommentBean) {

    }

    @Override
    public void happyCommentDel(String action) {

    }

    @Override
    public void happyLike(BlessAddLikeBean blessAddLikeBean) {

    }

    @Override
    public void happyBlessAdd(BlessAddBean blessAddBean) {

    }

    @Override
    public void happyBlessReceiveList(BlessReceiveYuBean blessReceiveYuBean) {
         totalPage = blessReceiveYuBean.getTotalPage();
        if (isRefreshing) {
            adapter.setNewData(blessReceiveYuBean.getList());
            refreshLayout.finishRefresh();
        } else {
            adapter.addData(blessReceiveYuBean.getList());
            refreshLayout.finishLoadmore();
        }
    }

    @Override
    public void happyUsualLanguage(HappyBlessUsualLanguageBean blessUsualLanguageBeanb) {

    }

    @Override
    public void happyGiftList(BlessGiftBean blessGiftBean) {

    }

    @Override
    public void happyGiftReceiveListMy(BlessReceiveGiftBean blessGiftReceivedBean) {

    }

    @Override
    public void happyGiftSendListMy(BlessSendGiftBean blessSendGiftBean) {

    }

    @Override
    public void happyOrderAdd(String blessOrderBean) {

    }

    @Override
    public void happyCartAdd(String message) {

    }

    @Override
    public void happyCartList(BlessCartListBean blessCartListBean) {

    }

    @Override
    public void happyCartUpdate(CartUpdateBean message) {

    }



    @Override
    public void happyCartDel(String message) {

    }

    @Override
    public void happySearch(BlessListBean blessListBean) {

    }

    @Override
    public void happyDetailsGiftList(BlessDetailGiftListBean detailGiftListBean) {

    }

    @Override
    public void uploadHeader(FileUploadBean fileUploadBean) {

    }

    @Override
    public void alipay(AliPayBean action) {

    }
}
