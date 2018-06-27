package com.itislevel.lyl.mvp.ui.family;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.itislevel.lyl.R;
import com.itislevel.lyl.adapter.FamilyReceiveGiftAdapter;
import com.itislevel.lyl.app.Constants;
import com.itislevel.lyl.base.RootActivity;
import com.itislevel.lyl.mvp.model.bean.BlessCartListBean;
import com.itislevel.lyl.mvp.model.bean.BlessListBean;
import com.itislevel.lyl.mvp.model.bean.BlessOrderBean;
import com.itislevel.lyl.mvp.model.bean.CartUpdateBean;
import com.itislevel.lyl.mvp.model.bean.FamilyBlessListBean;
import com.itislevel.lyl.mvp.model.bean.FamilyBlessListRecevieBean;
import com.itislevel.lyl.mvp.model.bean.FamilyGiftListBean;
import com.itislevel.lyl.mvp.model.bean.FamilyListBean;
import com.itislevel.lyl.mvp.model.bean.FamilyPhotoFrameBean;
import com.itislevel.lyl.mvp.model.bean.FamilyQueryFramBackBean;
import com.itislevel.lyl.mvp.model.bean.FamilyReceiveGiftBean;
import com.itislevel.lyl.mvp.model.bean.FamilySacrificeTypeBean;
import com.itislevel.lyl.mvp.model.bean.FamilySendGiftRecordBean;
import com.itislevel.lyl.mvp.model.bean.FamilyUsualLanguageBean;
import com.itislevel.lyl.mvp.model.bean.FileUploadBean;
import com.itislevel.lyl.mvp.model.bean.LetterBean;
import com.itislevel.lyl.utils.GsonUtil;
import com.itislevel.lyl.utils.SharedPreferencedUtils;
import com.itislevel.lyl.utils.ToastUtil;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;

public class FamilyReceiveSacrificeActivity extends RootActivity<FamilyPresenter>
        implements FamilyContract.View, BaseQuickAdapter.OnItemClickListener
        , BaseQuickAdapter.OnItemChildClickListener, SwipeRefreshLayout.OnRefreshListener ,BaseQuickAdapter.RequestLoadMoreListener{


    @BindView(R.id.refreshLayout)
    SwipeRefreshLayout refreshLayout;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;


    //是否已添加没有更多数据的布局
    private boolean isAddNoMoreView = false;
    private int pageIndex = 1;//分页加载时 当前页是第几页
    private int totalPage = 0;//总页数
    private boolean isRefreshing = true;//当前是刷新还是加载
    private FamilyReceiveGiftAdapter adapter;

    private int load_more = 0;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_family_receive_sacrifice;
    }

    @Override
    protected void initEventAndData() {
        setToolbarTvTitle("收到的祭品");
        initAdapter();
        initRefreshLayout_recyclview();
        refreshLayout.setOnRefreshListener(this);
        refreshLayout.setRefreshing(true);//刷新效果
        loadData();
    }
    private void initRefreshLayout_recyclview(){//设置刷新的颜色和款式及recyclevew
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        //注册成为订阅者
        refreshLayout.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_orange_dark,
                android.R.color.holo_red_dark);
        refreshLayout.setProgressViewOffset(true,10,200);
    }
    /**
     * 初始化adapter
     */
    private void initAdapter() {
        if (adapter == null) {
            adapter = new FamilyReceiveGiftAdapter(R.layout.item_family_gift_receive);
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

    private void loadData() {
        Map<String, String> request = new HashMap<>();
        request.put("token", SharedPreferencedUtils.getStr(Constants.USER_TOKEN));
        request.put("usernum", SharedPreferencedUtils.getStr(Constants.USER_NUM));
        request.put("userid", SharedPreferencedUtils.getStr(Constants.USER_ID));
        request.put("pageIndex", pageIndex + "");
        request.put("pageSize", Constants.PAGE_NUMBER10 + "");
        request.put("modelename", Constants.CART_MODEL_FAMILY);

        mPresenter.familyReceiveGift(GsonUtil.obj2JSON(request));

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
    public void showContent(String msg) {

    }

    @Override
    public void familyAdd(String message) {

    }


    @Override
    public void familyList(FamilyListBean funsharingListBeans) {

    }

    @Override
    public void familyListMy(FamilyListBean familyListBean) {

    }

    @Override
    public void familyBlessList(FamilyBlessListBean familyBlessListBean) {

    }

    @Override
    public void familyReceiveSacrifice(FamilyReceiveGiftBean familyReceiveGiftBean) {
        totalPage = familyReceiveGiftBean.getTotalPage();
        load_more++;
        if(load_more==1)
        {
            adapter.setOnLoadMoreListener(this,recyclerView);//设置加载更多
        }
        //返回的数据 第一次
        if (pageIndex == 1 && adapter.getData().size() <= 0 && (familyReceiveGiftBean.getList() ==
                null || familyReceiveGiftBean.getList().size() <= 0)) {
            View emptyView = View.inflate(this, R.layout.partial_empty_view, null);

            TextView viewById = emptyView.findViewById(R.id.tv_message);
            adapter.setEmptyView(emptyView);
        }
        if (isRefreshing) {
            adapter.setNewData(familyReceiveGiftBean.getList());
            refreshLayout.setRefreshing(false);
        } else {
            adapter.addData(familyReceiveGiftBean.getList());
            adapter.loadMoreComplete();
        }
    }

    @Override
    public void familyListGift(FamilyGiftListBean familyGiftListBean) {

    }

    @Override
    public void familySendGift(FamilySendGiftRecordBean familySendGiftRecordBean) {

    }

    @Override
    public void familyReceiveBless(FamilyBlessListRecevieBean familyBlessListRecevieBean) {

    }

    @Override
    public void familyViewCount(String message) {

    }

    @Override
    public void familyCate(FamilySacrificeTypeBean familySacrificeTypeBean) {

    }

    @Override
    public void familyUsualLanguage(FamilyUsualLanguageBean familyUsualLanguageBean) {

    }

    @Override
    public void familyPhotoFrame(FamilyPhotoFrameBean familyPhotoFrameBean) {

    }

    @Override
    public void familyPhotoBack(FamilyPhotoFrameBean familyPhotoFrameBean) {

    }

    @Override
    public void familyBlessAdd(String message) {

    }

    @Override
    public void familySearch(FamilyListBean familyListBean) {

    }

    @Override
    public void familyReceiveGiftById(FamilyReceiveGiftBean familyReceiveGiftBean) {

    }

    @Override
    public void familySaveFPhotoFrameAndBack(String message) {

    }

    @Override
    public void familyQueryFrameAndBack(FamilyQueryFramBackBean familyQueryFramBackBean) {

    }


    @Override
    public void familyDel(String message) {

    }

    @Override
    public void uploadHeader(FileUploadBean fileUploadBean) {

    }

    @Override
    public void generatorOrder(String blessOrderBean) {

    }

    @Override
    public void immediateOrder(String blessOrderBean) {

    }

    @Override
    public void familyCartAdd(String message) {

    }

    @Override
    public void familyCartList(BlessCartListBean blessCartListBean) {

    }

    @Override
    public void familyCartUpdate(CartUpdateBean message) {

    }



    @Override
    public void familyCartDel(String message) {

    }

    @Override
    public void familySearch(BlessListBean blessListBean) {

    }

    @Override
    public void selectletter(LetterBean letterBean) {

    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);

    }

    @Override
    public void onRefresh() {
        refreshLayout.setRefreshing(true);//刷新效果
        pageIndex = 1;
        isRefreshing = true;
        loadData();
    }

    @Override
    public void onLoadMoreRequested() {
        adapter.setEnableLoadMore(true);
        pageIndex++;
        if (pageIndex > totalPage) {
            ToastUtil.Info("没有更多啦~~");
            adapter.loadMoreEnd();
            return;
        } else {
            isRefreshing = false;
            loadData();
        }
    }
}
