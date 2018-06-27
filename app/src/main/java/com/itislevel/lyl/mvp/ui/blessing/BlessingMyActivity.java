package com.itislevel.lyl.mvp.ui.blessing;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.itislevel.lyl.R;
import com.itislevel.lyl.adapter.BlessListAdapter;
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
import com.itislevel.lyl.utils.ActivityUtil;
import com.itislevel.lyl.utils.DateUtil;
import com.itislevel.lyl.utils.GsonUtil;
import com.itislevel.lyl.utils.SAToast;
import com.itislevel.lyl.utils.SharedPreferencedUtils;
import com.itislevel.lyl.utils.ToastUtil;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;

public class BlessingMyActivity extends RootActivity<BlessingPresenter> implements
        BlessingContract.View
        , BaseQuickAdapter.OnItemClickListener
        , BaseQuickAdapter.OnItemChildClickListener, SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {


    Bundle bundle=null;

    @BindView(R.id.refreshLayout)
    SwipeRefreshLayout refreshLayout;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    //是否已添加没有更多数据的布局
    private boolean isAddNoMoreView = false;
    private int pageIndex = 1;//分页加载时 当前页是第几页
    private int totalPage = 0;//总页数
    private boolean isRefreshing = true;//当前是刷新还是加载
    private BlessListAdapter adapter;
    private ArrayList<String> typeList;
    private int delPosition=-1;

    private int load_more =0;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_blessing_my;
    }

    @Override
    protected void initEventAndData() {

        bundle=getIntent().getExtras();
        setToolbarTvTitle("我的喜事");
        refreshLayout.setOnRefreshListener(this);
        initAdapter();
        initRefreshLayout_recyclview();
        refreshLayout.setRefreshing(true);//刷新效
        loadData();
    }

    @Override
    protected void onResume() {
        super.onResume();

        refreshLayout.setRefreshing(true);//刷新效果
        loadData();
    }
    private void initRefreshLayout_recyclview(){//设置刷新的颜色和款式及recyclevew
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
        request.put("pageIndex", pageIndex+"");
        request.put("pageSize", Constants.PAGE_NUMBER10+"");
        request.put("userid", SharedPreferencedUtils.getStr(Constants.USER_ID));


        mPresenter.happyListMy(GsonUtil.obj2JSON(request));
    }


    /**
     * 初始化adapter
     */
    private void initAdapter() {
        if (adapter == null) {
            adapter = new BlessListAdapter(R.layout.item_bless_my,this);
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



    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        BlessListBean.ListBean item = this.adapter.getItem(position);
        switch (view.getId()){
            case R.id.iv_header:
                break;
            case R.id.tv_nickname:
                break;
            case R.id.tv_title:
                //  iv_header tv_nickname
                //  tv_title ninegrid_imgs tv_comment_num tv_like_num
                bundle.putBoolean("isfromMy",true);
                bundle.putString("blessid",item.getId()+"");
                bundle.putString("userid",item.getUserid()+"");
                bundle.putString("username",item.getUsername());
                bundle.putString("userheader",item.getImgurl());

                bundle.putString("title",item.getContent());
                bundle.putString("imags",item.getImge());
                bundle.putInt("viewCount",item.getLooknum());
                bundle.putString("likenum",Integer.parseInt(item.getFabulousnumber())+"");
                bundle.putString("islike",item.getIspoint());

                bundle.putString("comments",GsonUtil.obj2JSON(item));
                bundle.putString("nicknamelist",GsonUtil.obj2JSON(item.getNmpointlist()));
                bundle.putString("createtime", DateUtil.timeSpanToDateTime(item.getCreatedtime()));

                ActivityUtil.getInstance().openActivity(BlessingMyActivity.this,BlessingDetailActivity.class,bundle);
                break;
            case R.id.tv_del:
                Map<String, String> request = new HashMap<>();
                request.put("token", SharedPreferencedUtils.getStr(Constants.USER_TOKEN));
                request.put("usernum", SharedPreferencedUtils.getStr(Constants.USER_NUM));
                request.put("id",this.adapter.getItem(position).getId()+"");
                request.put("modelename", Constants.CART_MODEL_BLESS);

                delPosition = position;
                mPresenter.happyDel(GsonUtil.obj2JSON(request));
                break;
        }
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        BlessListBean.ListBean item = this.adapter.getItem(position);

        bundle.putBoolean("isfromMy",true);
        bundle.putString("blessid",item.getId()+"");
        bundle.putString("userid",item.getUserid()+"");
        bundle.putString("username",item.getUsername());
        bundle.putString("userheader",item.getImgurl());
        bundle.putString("islike",item.getIspoint());
        bundle.putString("title",item.getContent());
        bundle.putString("imags",item.getImge());
        bundle.putInt("viewCount",item.getLooknum());
        bundle.putString("likenum",Integer.parseInt(item.getFabulousnumber())+"");

        bundle.putString("comments",GsonUtil.obj2JSON(item));
        bundle.putString("nicknamelist",GsonUtil.obj2JSON(item.getNmpointlist()));
        bundle.putString("createtime",DateUtil.timeSpanToDateTime(item.getCreatedtime()));

        ActivityUtil.getInstance().openActivity(BlessingMyActivity.this,BlessingDetailActivity.class,bundle);

    }

    @Override
    public void showContent(String msg) {

    }

    @Override
    public void happAdd(String action) {

    }

    @Override
    public void happyListMy(BlessListBean blessListBean) {
        load_more++;
        if (load_more == 1)
        {
            adapter.setOnLoadMoreListener(this,recyclerView);//设置加载更多
        }
        totalPage = blessListBean.getTotalPage();
        //返回的数据 第一次
        if (pageIndex==1&&adapter.getData().size() <= 0 && (blessListBean.getList()==null||blessListBean.getList().size() <= 0)) {
            View emptyView = View.inflate(this, R.layout.partial_empty_view, null);
            TextView viewById = emptyView.findViewById(R.id.tv_message);
            adapter.setEmptyView(emptyView);
        }

        if (isRefreshing) {
            adapter.setNewData(blessListBean.getList());
            refreshLayout.setRefreshing(false);
        } else {
            adapter.addData(blessListBean.getList());
            adapter.loadMoreComplete();
        }
    }

    @Override
    public void happyViewCount(String action) {

    }

    @Override
    public void happyDel(String action) {
        this.adapter.remove(delPosition);
        delPosition=-1;

        SAToast.makeText(BlessingMyActivity.this,"删除成功").show();
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
    public void happyBlessReceiveList(BlessReceiveYuBean blessReceiveBlessBean) {

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

        if (adapter.isLoading()){
            adapter.loadMoreEnd();
        }
        if (refreshLayout.isRefreshing()){
            refreshLayout.setRefreshing(false);
        }
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);

    }

    @Override
    public void onRefresh() {
        pageIndex = 1;
        isRefreshing = true;
        refreshLayout.setRefreshing(true);//刷新效果
        loadData();
    }

    @Override
    public void onLoadMoreRequested() {
        adapter.setEnableLoadMore(true);
        pageIndex++;
        if (pageIndex > totalPage) {
            adapter.loadMoreEnd();
            SAToast.makeText(BlessingMyActivity.this,"没有更多啦~~").show();
            return;
        } else {
            isRefreshing = false;
            loadData();
        }
    }
}
