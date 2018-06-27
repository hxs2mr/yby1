package com.itislevel.lyl.mvp.ui.property.homelist;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.itislevel.lyl.R;
import com.itislevel.lyl.adapter.LiveAddressBean;
import com.itislevel.lyl.adapter.PropretyAllTonAdapter;
import com.itislevel.lyl.adapter.PropretyHomeListAdapter;
import com.itislevel.lyl.app.Constants;
import com.itislevel.lyl.base.RootActivity;
import com.itislevel.lyl.mvp.model.bean.HomeDetailBean;
import com.itislevel.lyl.mvp.model.bean.PropertLoginBean;
import com.itislevel.lyl.mvp.model.bean.PropretyLiveBean;
import com.itislevel.lyl.mvp.model.bean.PropretyNoticeBean;
import com.itislevel.lyl.mvp.model.bean.VillageNameBean;
import com.itislevel.lyl.mvp.ui.property.PropertyContract;
import com.itislevel.lyl.mvp.ui.property.PropertyPresenter;
import com.itislevel.lyl.utils.ActivityUtil;
import com.itislevel.lyl.utils.GsonUtil;
import com.itislevel.lyl.utils.SharedPreferencedUtils;
import com.itislevel.lyl.utils.UtilsStyle;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import cn.finalteam.rxgalleryfinal.utils.BitmapUtils;
import dagger.BindsInstance;
import retrofit2.http.PUT;

/**
 * Created by Administrator on 2018\5\25 0025.
 */

public class PropertyHomeListActivity extends RootActivity<PropertyHomePresenter>implements PropertyHomeContract.View, SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.OnItemClickListener {

    @BindView(R.id.refreshLayout)
    SwipeRefreshLayout refreshLayout;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;

    private int load_more =0;
    private int pageIndex = 1;//分页加载时 当前页是第几页
    private int totalPage = 0;//总页数
    private boolean isRefreshing = true;//当前是刷新还是加载

    private PropretyHomeListAdapter adapter;
    private String vid;
    public static String p_home_image_url;
    private Bundle bundle;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UtilsStyle.setStatusBarMode(this,true); //黑色的主题图标
    }
    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }
    @Override
    protected int getLayoutId() {
        return R.layout.activity_homelist_layout;
    }

    @OnClick({R.id.p_p_back})
    public  void  onclick(View view)
    {
        switch (view.getId())
        {
            case R.id.p_p_back:
                ActivityUtil.getInstance().closeActivity(this);
                break;
        }
    }
    @Override
    protected void initEventAndData() {
        bundle = getIntent().getExtras();
        vid = bundle.getString("vid");
        p_home_image_url = bundle.getString("image");
        setToolbarTvTitle("物业通知");
        initAdapter();
        refreshLayout.setOnRefreshListener(this);
        refreshLayout.setRefreshing(true);//刷新效果
        LoadHomeData(vid);
    }
    private void LoadHomeData(String vid)
    {
        refreshLayout.setRefreshing(true);//刷新效果
        Map<String, Object> request = new HashMap<>();
        request.put("token", SharedPreferencedUtils.getStr("p_token",""));
        request.put("phone", SharedPreferencedUtils.getStr("proprety_phone",""));
        request.put("vid",vid);
        mPresenter.findLiveaddress(GsonUtil.obj2JSON(request));
    }

    private void initAdapter() {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        // ((SimpleItemAnimator)recyclerView.getItemAnimator()).setSupportsChangeAnimations(false);
        recyclerView.setLayoutManager(manager);
        //注册成为订阅者
        refreshLayout.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_orange_dark,
                android.R.color.holo_red_dark);
        if (adapter == null){
            adapter = new PropretyHomeListAdapter(R.layout.homelist_item, this);
            adapter.setOnItemClickListener(this);
            adapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_BOTTOM); //切换动画
            adapter.setEnableLoadMore(false);
            recyclerView.setAdapter(adapter);
            View emptyView = View.inflate(this, R.layout.partial_empty_view, null);
            adapter.setEmptyView(emptyView);
        }
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
    public void onRefresh() {
            refreshLayout.setRefreshing(true);
            isRefreshing = true;
            LoadHomeData(vid);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
            Bundle bundle  = new Bundle();
            bundle.putString("address",this.adapter.getData().get(position).getLiveaddress());
            bundle.putString("number",this.adapter.getData().get(position).getUsernum());
            bundle.putString("username",this.adapter.getData().get(position).getUsername());
            bundle.putString("paddress",this.adapter.getData().get(position).getProvname()+this.adapter.getData().get(position).getCityname());
            bundle.putString("villagename",this.adapter.getData().get(position).getVillagename());
             bundle.putString("phone",this.adapter.getData().get(position).getPhone());
            ActivityUtil.getInstance().openActivity(this,PropertyHomeListDetailActivity.class,bundle);
    }

    @Override
    public void findVillagename(List<VillageNameBean> list) {

    }

    @Override
    public void findLiveaddress(List<LiveAddressBean> list) {
//返回的数据 第一次
        if (adapter.getData().size() <= 0 && (list ==
                null ||list.size() <= 0)) {
            View emptyView = View.inflate(this, R.layout.partial_empty_view, null);
            TextView viewById = emptyView.findViewById(R.id.tv_message);
            adapter.setEmptyView(emptyView);
        }
        if (isRefreshing) {
            adapter.setNewData(list);
            refreshLayout.setRefreshing(false);
        }
    }

    @Override
    public void personalNews(List<HomeDetailBean> bean) {

    }
}
