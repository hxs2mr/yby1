package com.itislevel.lyl.mvp.ui.family.childhomefragment.dfind;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.itislevel.lyl.R;
import com.itislevel.lyl.adapter.FamilyListAdapter;
import com.itislevel.lyl.app.Constants;
import com.itislevel.lyl.mvp.model.bean.FamilyAddBean;
import com.itislevel.lyl.mvp.model.bean.FamilyListBean;
import com.itislevel.lyl.mvp.model.bean.FamilyPersonListBean;
import com.itislevel.lyl.mvp.ui.dynamicmyperson.Dynamic_MypersonActivity;
import com.itislevel.lyl.mvp.ui.family.FamilyDetailActivity;
import com.itislevel.lyl.mvp.ui.main.dynamic.childfragment.RootCancleFragment;
import com.itislevel.lyl.mvp.ui.user.LoginActivity;
import com.itislevel.lyl.utils.ActivityUtil;
import com.itislevel.lyl.utils.GsonUtil;
import com.itislevel.lyl.utils.SharedPreferencedUtils;
import com.itislevel.lyl.utils.rxbus.annotation.UseRxBus;
import com.kaopiz.kprogresshud.KProgressHUD;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;

import static com.itislevel.lyl.mvp.ui.main.home.HomeFragment.CITY_ID;

/**
 * Created by Administrator on 2018\4\27 0027.
 */
@UseRxBus
public class FFindFragment extends RootCancleFragment<FFindPresenter> implements FFindContract.View,SwipeRefreshLayout.OnRefreshListener,
        BaseQuickAdapter.RequestLoadMoreListener,
        BaseQuickAdapter.OnItemChildClickListener, BaseQuickAdapter.OnItemClickListener {

    @BindView(R.id.refreshLayout)
    SwipeRefreshLayout refreshLayout;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private int load_more =0;
    private int pageIndex = 1;//分页加载时 当前页是第几页
    private int totalPage = 0;//总页数
    private boolean isRefreshing = true;//当前是刷新还是加载
    private FamilyListAdapter adapter;
    private KProgressHUD loadingDialog;
    private boolean isSearch;
    private boolean isReady = false;
    private boolean isLoaded = false;//防止重复加载
    @Override
    protected int getLayoutId() {
        return R.layout.activity_family_home;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }
    @Override
    protected void initEventAndData() {
        initAdapter();
        refreshLayout.setOnRefreshListener(this);
        refreshLayout.setRefreshing(true);//刷新效果
        isReady=true;
        lazyLoad();
    }
    private void loadNormalData(){
        Map<String, String> request = new HashMap<>();
        request.put("token", SharedPreferencedUtils.getStr(Constants.USER_TOKEN));
        request.put("usernum", SharedPreferencedUtils.getStr(Constants.USER_NUM));
        request.put("userid", SharedPreferencedUtils.getStr(Constants.USER_ID));
        request.put("pageIndex", pageIndex + "");
        request.put("pageSize", Constants.PAGE_NUMBER10 + "");
        request.put("cityid",CITY_ID);
        request.put("type","1003");

        System.out.println("json数据***************"+GsonUtil.obj2JSON(request));
        mPresenter.firstPage(GsonUtil.obj2JSON(request));
    }
    @Subscribe
    public void OnEvent(FamilyAddBean messageEvent)
    {
        refreshLayout.setRefreshing(true);//刷新效果
        pageIndex = 1;
        isRefreshing = true;
        isSearch=false;
        loadNormalData();
    }
    /**
     * 初始化adapter
     */
    private void initAdapter() {
        if (adapter == null) {
            adapter = new FamilyListAdapter(R.layout.item_family);
            adapter.setOnItemChildClickListener(this);
            adapter.setOnItemClickListener(this);
            adapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN); //切换动画
            adapter.setEnableLoadMore(false);
            LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(adapter);
            View emptyView = View.inflate(getContext(), R.layout.partial_empty_view, null);
            adapter.setEmptyView(emptyView);
        }
    }

    @Override
    public void firstPage(FamilyListBean funsharingListBeans) {
        totalPage = funsharingListBeans.getTotalPage();
        load_more++;
        if(load_more==1)
        {
            adapter.setOnLoadMoreListener(this,recyclerView);//设置加载更多
        }
//返回的数据 第一次
        if (pageIndex == 1 && adapter.getData().size() <= 0 && (funsharingListBeans.getList() ==
                null || funsharingListBeans.getList().size() <= 0)) {
            View emptyView = View.inflate(getContext(), R.layout.partial_empty_view, null);
            TextView viewById = emptyView.findViewById(R.id.tv_message);
            adapter.setEmptyView(emptyView);
        }
        if (isRefreshing) {
            adapter.setNewData(funsharingListBeans.getList());
            refreshLayout.setRefreshing(false);
        } else {
            adapter.addData(funsharingListBeans.getList());
            adapter.loadMoreComplete();
        }
    }

    @Override
    public void findmylist(FamilyPersonListBean familyPersonListBean) {

    }

    @Override
    public void delFete(String action) {

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
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        FamilyListBean.ListBean item = this.adapter.getItem(position);
        if(view.getId()!=R.id.linear_person)
        {
            Bundle bundle = new Bundle();
            bundle.putString("feteid", item.getFeteid() + "");
            bundle.putInt("looknum", item.getLooknum());
            bundle.putString("deadname", item.getDeadname());
            bundle.putString("fetename", item.getFetename());
            bundle.putString("imgestr", item.getImgestr());
            bundle.putString("touserid", item.getUserid() + "");
            bundle.putString("urlmp3", item.getMusicname() + "");
            bundle.putString("share_url",item.getFete_share_url());
            bundle.putString("head_image",Constants.IMG_SERVER_PATH+item.getImgurl());
            ActivityUtil.getInstance().openActivity(getActivity(), FamilyDetailActivity.class, bundle);
        }
    }
    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        FamilyListBean.ListBean item = this.adapter.getItem(position);
        if(view.getId()==R.id.linear_person)
        {
            boolean islogin = SharedPreferencedUtils.getBool("islogin", false);
            if (!islogin) {
                ActivityUtil.getInstance().openActivity(getActivity(), LoginActivity.class);
                return;
            }
            String name = SharedPreferencedUtils.getStr(Constants.USER_NICK_NAME);
            Bundle bundle1  = new Bundle();
            bundle1.putString("FLAGE","WO");
            bundle1.putString("head_image",Constants.IMG_SERVER_PATH+item.getImgurl());
            bundle1.putString("name", TextUtils.isEmpty(name) ? "请登录" : "" + item.getNickname());
            bundle1.putString("userid",item.getUserid()+"");

            ActivityUtil.getInstance().openActivity(getActivity(), Dynamic_MypersonActivity.class,bundle1);
        }
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
    public void stateError() {
        super.stateError();
        if (adapter.getData() == null || adapter.getData().size() <= 0) {
            View emptyView = View.inflate(getContext(), R.layout.partial_empty_view, null);
            adapter.setEmptyView(emptyView);
        }
        if (refreshLayout.isRefreshing()) {
            refreshLayout.setRefreshing(false);
        }
        if (adapter.isLoadMoreEnable()) {
            adapter.loadMoreFail();
        }
    }
    @Override
    public void onRefresh() {
        refreshLayout.setRefreshing(true);//刷新效果
        pageIndex = 1;
        isRefreshing = true;
        isSearch=false;
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

}
