package com.itislevel.lyl.mvp.ui.dynamicmyperson.childpersonfragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.itislevel.lyl.R;
import com.itislevel.lyl.adapter.FamilyPersonListAdapter;
import com.itislevel.lyl.app.Constants;
import com.itislevel.lyl.mvp.model.bean.FamilyListBean;
import com.itislevel.lyl.mvp.model.bean.FamilyPersonListBean;
import com.itislevel.lyl.mvp.ui.family.FamilyDetailActivity;
import com.itislevel.lyl.mvp.ui.family.childhomefragment.dfind.FFindContract;
import com.itislevel.lyl.mvp.ui.family.childhomefragment.dfind.FFindPresenter;
import com.itislevel.lyl.mvp.ui.main.dynamic.childfragment.RootCancleFragment;
import com.itislevel.lyl.utils.ActivityUtil;
import com.itislevel.lyl.utils.GsonUtil;
import com.itislevel.lyl.utils.SharedPreferencedUtils;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;

import static com.itislevel.lyl.mvp.ui.dynamicmyperson.Dynamic_MypersonActivity.PERSON_USER_ID;

/**
 * Created by Administrator on 2018\5\10 0010.
 */

public class Family_personFragment extends RootCancleFragment<FFindPresenter> implements FFindContract.View,SwipeRefreshLayout.OnRefreshListener,
        BaseQuickAdapter.RequestLoadMoreListener,BaseQuickAdapter.OnItemClickListener, BaseQuickAdapter.OnItemChildClickListener {
    @BindView(R.id.refreshLayout)
    SwipeRefreshLayout refreshLayout;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private int load_more =0;
    private int pageIndex = 1;//分页加载时 当前页是第几页
    private int totalPage = 0;//总页数
    private boolean isRefreshing = true;//当前是刷新还是加载
    private FamilyPersonListAdapter adapter;
    private boolean isReady = false;
    private boolean isLoaded = false;//防止重复加载
    private int POSTION=0;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_family_home;
    }

    @Override
    protected void initEventAndData() {
        initAdapter();
        refreshLayout.setOnRefreshListener(this);
        refreshLayout.setRefreshing(true);//刷新效果
        isReady=true;
        lazyLoad();
    }
    /**
     * 初始化adapter
     */
    private void initAdapter() {
        if (adapter == null) {
            adapter = new FamilyPersonListAdapter(R.layout.item_family);
            adapter.setOnItemClickListener(this);
            adapter.setOnItemChildClickListener(this);
            adapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN); //切换动画
            adapter.setEnableLoadMore(false);
            LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(adapter);
        }
    }

    private void loadNormalData() {
        Map<String, String> request = new HashMap<>();
        request.put("token", SharedPreferencedUtils.getStr(Constants.USER_TOKEN));
        request.put("usernum", SharedPreferencedUtils.getStr(Constants.USER_NUM));
        request.put("userid", PERSON_USER_ID);
        request.put("pageIndex", pageIndex + "");
        request.put("pageSize", Constants.PAGE_NUMBER10 + "");
        mPresenter.findmylist(GsonUtil.obj2JSON(request));
    }

    @Override
    public void useNightMode(boolean isNight) {

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
    public void firstPage(FamilyListBean lylResponse) {

    }

    @Override
    public void findmylist(FamilyPersonListBean familyPersonListBean) {
        totalPage = familyPersonListBean.getTotalPage();
        load_more++;
        if(load_more==1)
        {
            adapter.setOnLoadMoreListener(this,recyclerView);//设置加载更多
        }
//返回的数据 第一次
        if (pageIndex == 1 && adapter.getData().size() <= 0 && (familyPersonListBean.getList() ==
                null || familyPersonListBean.getList().size() <= 0)) {
            View emptyView = View.inflate(getContext(), R.layout.partial_empty_view, null);
            TextView viewById = emptyView.findViewById(R.id.tv_message);
            adapter.setEmptyView(emptyView);
        }
        if (isRefreshing) {
            adapter.setNewData(familyPersonListBean.getList());
            refreshLayout.setRefreshing(false);
        } else {
            adapter.addData(familyPersonListBean.getList());
            adapter.loadMoreComplete();
        }
    }

    @Override
    public void delFete(String action) {
        this.adapter.remove(POSTION);
    }

    @Override
    public void stateError(Exception e) {
        super.stateError(e);
        if (adapter.getData() == null || adapter.getData().size() <= 0) {
            View emptyView = View.inflate(getContext(), R.layout.partial_empty_view, null);
            adapter.setEmptyView(emptyView);
        }
        if (adapter.isLoadMoreEnable()) {
            adapter.loadMoreComplete();
        }
        if (refreshLayout.isRefreshing()) {
            refreshLayout.setRefreshing(false);
        }
    }


    @Override
    protected void lazyLoad() {
        if (!isReady || !isVisible || isLoaded) {
            return;
        }
        isLoaded = true;
        loadNormalData();
    }

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    public void onRefresh() {
        refreshLayout.setRefreshing(true);//刷新效果
        pageIndex = 1;
        isRefreshing = true;
        loadNormalData();
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
            loadNormalData();
        }
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        FamilyPersonListBean.ListBean item = this.adapter.getItem(position);
        Bundle bundle = new Bundle();
        bundle.putString("feteid", item.getFeteid() + "");
        bundle.putInt("looknum", item.getLooknum());
        bundle.putString("deadname", item.getDeadname());
        bundle.putString("fetename", item.getFetename());
        bundle.putString("imgestr", item.getImgestr());
        bundle.putString("touserid", item.getUserid() + "");
        bundle.putString("urlmp3", item.getMusicname() + "");
        ActivityUtil.getInstance().openActivity(getActivity(), FamilyDetailActivity.class, bundle);
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        POSTION = position;
        switch (view.getId())
        {
            case R.id.tv_delete:
                show_clear_cart(this.adapter.getData().get(position).getFeteid());
                break;
        }
    }

    public void show_clear_cart(int fiteid){
        //实例化建造者
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        //设置警告对话框的标题
        builder.setTitle("祭事");
        //设置警告显示的图片
//        builder.setIcon(android.R.drawable.ic_dialog_alert);
        //设置警告对话框的提示信息
        builder.setMessage("确认要删除这条祭事吗？");
        //设置”正面”按钮，及点击事件
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Map<String, String> request = new HashMap<>();
                request.put("token", SharedPreferencedUtils.getStr(Constants.USER_TOKEN));
                request.put("usernum", SharedPreferencedUtils.getStr(Constants.USER_NUM));
                request.put("feteid", fiteid+"");
                mPresenter.delFete(GsonUtil.obj2JSON(request));
            }
        });
        //设置“反面”按钮，及点击事件
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        //设置“中立”按钮，及点击事件
        builder.setNeutralButton("在等等", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        //显示对话框
        builder.show();
    }
}
