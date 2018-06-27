package com.itislevel.lyl.mvp.ui.housekeep;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.itislevel.lyl.R;
import com.itislevel.lyl.adapter.HouseKeepAdapter;
import com.itislevel.lyl.app.Constants;
import com.itislevel.lyl.base.RootActivity;
import com.itislevel.lyl.mvp.model.bean.HouseBean;
import com.itislevel.lyl.mvp.model.bean.HouseKeepBean;
import com.itislevel.lyl.mvp.model.bean.MeiZiBean;
import com.itislevel.lyl.mvp.ui.location.Location_CityPickerActivity;
import com.itislevel.lyl.utils.ActivityUtil;
import com.itislevel.lyl.utils.GsonUtil;
import com.itislevel.lyl.utils.SAToast;
import com.itislevel.lyl.utils.SharedPreferencedUtils;
import com.itislevel.lyl.utils.ToastUtil;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

import static com.itislevel.lyl.mvp.ui.main.home.HomeFragment.CITY_ID;
import static com.itislevel.lyl.mvp.ui.main.home.HomeFragment.CITY_NAME;
import static com.itislevel.lyl.mvp.ui.main.home.HomeFragment.P_ID;
import static com.itislevel.lyl.mvp.ui.main.home.HomeFragment.P_NAME;

public class  HouseKeepActivity extends RootActivity<HouseKeepPresenter> implements
        HouseKeepContract.View
        , BaseQuickAdapter.OnItemClickListener
        , SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {

    Bundle bundle = new Bundle();

    private String PROVINCE_ID = "";
    private String CITY_ID = "";
    private String COUNTY_ID = "";
    private String CITY_NAME = "";
    @BindView(R.id.refreshLayout)
    SwipeRefreshLayout refreshLayout;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @BindView(R.id.house_loaction)
    TextView house_loaction;
    @BindView(R.id.house_loaction_linear)
    LinearLayoutCompat house_loaction_linear;
    //是否已添加没有更多数据的布局
    private boolean isAddNoMoreView = false;
    private int pageIndex = 1;//分页加载时 当前页是第几页
    private int totalPage = 0;//总页数
    private boolean isRefreshing = true;//当前是刷新还是加载

    private HouseKeepAdapter adapter;
    private  int loade_more = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
    @Override
    protected int getLayoutId() {
        return R.layout.activity_house_keep;
    }
    @Subscribe
    public void onEvent(HouseBean bean)
    {
        String city_id = bean.getCity_id();
        String city_name = bean.getCity_name();
        CITY_NAME = city_name;
        CITY_ID = city_id;
        house_loaction.setText(CITY_NAME);
        refreshLayout.setRefreshing(true);
        isRefreshing = true;
        pageIndex= 1;
        loadData(city_id);
    }
    @Override
    protected void initEventAndData() {
        bundle = this.getIntent().getExtras();
        String title = bundle.getString(Constants.COUNTY_TITLE);
        PROVINCE_ID = bundle.getString(Constants.PROVINCE_ID);
        CITY_ID = bundle.getString(Constants.CITY_ID);
        COUNTY_ID = bundle.getString(Constants.COUNTY_ID);
        CITY_NAME = bundle.getString(Constants.CITY_NAME);
        house_loaction.setText(TextUtils.isEmpty(CITY_NAME)?"无定位":CITY_NAME);
//
//        if (TextUtils.isEmpty(COUNTY_ID)){
//            COUNTY_ID=CITY_ID;
//        }
        setToolbarTvTitle(title);
        // tv_title
        initAdapter();
        refreshLayout.setOnRefreshListener(this);
        refreshLayout.setRefreshing(true);
        isRefreshing = true;
        loadData(CITY_ID);
    }

    /**
     * 初始化adapter
     */
    private void initAdapter() {
        if (adapter == null) {
            adapter = new HouseKeepAdapter(R.layout.item_housekeep);
            adapter.setOnItemClickListener(this);
            adapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN); //切换动画
            adapter.setEnableLoadMore(false);
            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(adapter);
        }
    }

    @OnClick({R.id.house_loaction_linear})
    public  void onclick(View view){
        switch (view.getId())
        {
            case R.id.house_loaction_linear:
                Bundle bundle = new Bundle();
                String title = "";
                bundle.putString(Constants.START_FLAGE, "house");
                bundle.putString(Constants.CITY_NAME, CITY_NAME);
                bundle.putString(Constants.CITY_ID, CITY_ID);
                ActivityUtil.getInstance().openActivity(this, Location_CityPickerActivity.class,bundle);
                break;
        }

    }
    private void loadData(String city_id) {
        Map<String, String> request = new HashMap<>();
        request.put("token", SharedPreferencedUtils.getStr(Constants.USER_TOKEN));
        request.put("usernum", SharedPreferencedUtils.getStr(Constants.USER_NUM));
        request.put("countid", TextUtils.isEmpty(COUNTY_ID)?"100":COUNTY_ID);
        request.put("cityid", city_id);
        request.put("pageIndex", pageIndex + "");
        request.put("pageSize", Constants.PAGE_NUMBER10 + "");
            System.out.print("JSON*******家政*****&&&&&&&&&&&&&&***************************"+GsonUtil.obj2JSON(request));
        mPresenter.findHouse(GsonUtil.obj2JSON(request));
    }


    @Override
    public void showContent(String msg) {

    }

    @Override
    public void showData(List<MeiZiBean> data) {

    }

    @Override
    public void findHouse(HouseKeepBean houseKeepBean) {
        totalPage = houseKeepBean.getTotalPage();
        loade_more++;
        if (loade_more == 1)
        {
            adapter.setOnLoadMoreListener(this,recyclerView);//设置加载更多
        }
//返回的数据 第一次
        if (adapter.getData().size() <= 0 && houseKeepBean.getList().size() <= 0) {
            View emptyView = View.inflate(this, R.layout.partial_empty_view, null);
            TextView viewById = emptyView.findViewById(R.id.tv_message);
            adapter.setEmptyView(emptyView);
        }
        if (isRefreshing) {
            adapter.setNewData(houseKeepBean.getList());
            refreshLayout.setRefreshing(false);
        } else {
            adapter.addData(houseKeepBean.getList());
            adapter.loadMoreComplete();
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
    public void stateError(Throwable e) {
        super.stateError(e);
        if (adapter.getData() == null || adapter.getData().size() <= 0) {
            View emptyView = View.inflate(this, R.layout.partial_empty_view, null);
            TextView viewById = emptyView.findViewById(R.id.tv_message);
            viewById.setText("空空如也");
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
    protected void initInject() {
        getActivityComponent().inject(this);
    }
    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        HouseKeepBean.ListBean item = this.adapter.getItem(position);
        bundle.putString("company_name", item.getCompanyname());
        bundle.putString("company_desc", item.getCompanyrem());
        bundle.putString("company_addr", item.getAddress());
        bundle.putString("company_phone", item.getContactphone());
        bundle.putString("company_img", item.getCompanyimge());
        bundle.putString("company_contact", item.getContactname());
        bundle.putString("company_logo", item.getCompanylogo());
        ActivityUtil.getInstance().openActivity(HouseKeepActivity.this, HouseKeepDetailActivity
                .class, bundle);
    }
    @Override
    public void onRefresh() {
        refreshLayout.setRefreshing(true);
        pageIndex = 1;
        isRefreshing = true;
        loadData(CITY_ID);
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
            loadData(CITY_ID);
        }
    }
}
