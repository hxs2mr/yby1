package com.itislevel.lyl.mvp.ui.special;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.itislevel.lyl.R;
import com.itislevel.lyl.adapter.SpecialTypeAdapter;
import com.itislevel.lyl.app.Constants;
import com.itislevel.lyl.base.RootActivity;
import com.itislevel.lyl.mvp.model.bean.BlessCartListBean;
import com.itislevel.lyl.mvp.model.bean.CartUpdateBean;
import com.itislevel.lyl.mvp.model.bean.FileUploadBean;
import com.itislevel.lyl.mvp.model.bean.SpecialGiftByIdBean;
import com.itislevel.lyl.mvp.model.bean.SpecialGiftListBean;
import com.itislevel.lyl.mvp.model.bean.SpecialOrderCompleteBean;
import com.itislevel.lyl.mvp.model.bean.SpecialOrderDetailBean;
import com.itislevel.lyl.mvp.model.bean.SpecialReceiveAddressBean;
import com.itislevel.lyl.mvp.model.bean.SpecialReturnBean;
import com.itislevel.lyl.mvp.model.bean.SpecialTuiKuanDetailBean;
import com.itislevel.lyl.mvp.model.bean.SpecialTuiKuanUpdateBean;
import com.itislevel.lyl.mvp.model.bean.SpecialTypeBean;
import com.itislevel.lyl.utils.ActivityUtil;
import com.itislevel.lyl.utils.GsonUtil;
import com.itislevel.lyl.utils.SharedPreferencedUtils;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;

public class SpecialGiftActivity extends RootActivity<SpecialPresenter> implements
        SpecialContract.View , BaseQuickAdapter.OnItemClickListener
        , BaseQuickAdapter.OnItemChildClickListener{

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
    private SpecialTypeAdapter adapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_special_gift;
    }

    @Override
    protected void initEventAndData() {
        setToolbarTvTitle("礼品区");
        setToolbarMoreTxt("我的订单");
        setToolbarMoreClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityUtil.getInstance().openActivity(SpecialGiftActivity.this,
                        SpecialOrderActivity.class);

            }
        });
        initRefreshListener();
        initAdapter();
        loadData();


    }
    private void loadData() {

        loadingDialog.show();

        Map<String, String> request = new HashMap<>();
        request.put("token", SharedPreferencedUtils.getStr(Constants.USER_TOKEN));
        request.put("usernum", SharedPreferencedUtils.getStr(Constants.USER_NUM));
        request.put("sign", "lipin");
        mPresenter.specialType(GsonUtil.obj2JSON(request));
    }

    /**
     * 初始化adapter
     */
    private void initAdapter() {
        if (adapter == null) {
            adapter = new SpecialTypeAdapter(R.layout.item_team_type);
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


    @Override
    public void showContent(String msg) {

    }

    @Override
    public void specialType(SpecialTypeBean specialTypeBean) {
        loadingDialog.dismiss();
        if (isRefreshing) {
            adapter.setNewData(specialTypeBean.getList());
            refreshLayout.finishRefresh();
        } else {
            adapter.addData(specialTypeBean.getList());
            refreshLayout.finishLoadmore();
        }
    }

    //
    @Override
    public void specialList(SpecialGiftListBean specialListBean) {

    }

    @Override
    public void specialById(SpecialGiftByIdBean specialByIdBean) {

    }

    @Override
    public void specialImmediatelyOrder(String action) {

    }

    @Override
    public void specialShopOrder(String action) {

    }

    @Override
    public void specialReceiveAddress(SpecialReceiveAddressBean addressBean) {

    }

    @Override
    public void specialOrderDetail(SpecialOrderDetailBean detailBean) {

    }

    @Override
    public void specialTuiKuanDetail(SpecialTuiKuanDetailBean tuiKuanDetailBean) {

    }

    @Override
    public void specialTuiKuan(String action) {

    }

    @Override
    public void specialTuiKuanUpdate(SpecialTuiKuanUpdateBean tuiKuanUpdateBean) {

    }

    @Override
    public void specialTuiKuanUpdate2(String action) {

    }

    @Override
    public void specialOrderComplete(SpecialOrderCompleteBean completeBean) {

    }

    @Override
    public void specialOrderGoPay(String action) {

    }

    @Override
    public void specialOrderCancel(String action) {

    }

    @Override
    public void specialShenQingTuiKuan(String action) {

    }

    @Override
    public void specialOrderConfirm(String action) {

    }

    @Override
    public void specialReturnList(SpecialReturnBean returnBean) {

    }

    @Override
    public void uploadHeader(FileUploadBean fileUploadBean) {

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
        SpecialTypeBean.ListBean item = this.adapter.getItem(position);
        bundle.putString("cateId",item.getId()+"");
                ActivityUtil.getInstance().openActivity(SpecialGiftActivity.this, SpecialGiftListActivity
                .class,bundle);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        SpecialTypeBean.ListBean item = this.adapter.getItem(position);
        bundle.putString("cateId",item.getId()+"");
        ActivityUtil.getInstance().openActivity(SpecialGiftActivity.this, SpecialGiftListActivity
                .class,bundle);
    }
}
