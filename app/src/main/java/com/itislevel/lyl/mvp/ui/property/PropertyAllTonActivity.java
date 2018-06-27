package com.itislevel.lyl.mvp.ui.property;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.itislevel.lyl.R;
import com.itislevel.lyl.adapter.CFAllPinAdapter;
import com.itislevel.lyl.adapter.LiveAddressBean;
import com.itislevel.lyl.adapter.PropretyAllTonAdapter;
import com.itislevel.lyl.app.Constants;
import com.itislevel.lyl.base.RootActivity;
import com.itislevel.lyl.mvp.model.bean.PropertLoginBean;
import com.itislevel.lyl.mvp.model.bean.PropretyLiveBean;
import com.itislevel.lyl.mvp.model.bean.PropretyNoticeBean;
import com.itislevel.lyl.mvp.model.bean.VillageNameBean;
import com.itislevel.lyl.utils.GsonUtil;
import com.itislevel.lyl.utils.SharedPreferencedUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

import static com.itislevel.lyl.mvp.ui.main.home.HomeFragment.CITY_ID;
import static com.itislevel.lyl.utils.DateUtil.timeSpanToDate;

/**
 * Created by Administrator on 2018\5\22 0022.
 */

public class PropertyAllTonActivity extends RootActivity<PropertyPresenter>implements PropertyContract.View, BaseQuickAdapter.OnItemClickListener, SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener{
    @BindView(R.id.refreshLayout)
    SwipeRefreshLayout refreshLayout;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private int load_more =0;
    private int pageIndex = 1;//分页加载时 当前页是第几页
    private int totalPage = 0;//总页数
    private boolean isRefreshing = true;//当前是刷新还是加载

     private  PropretyAllTonAdapter adapter;
    private PopupWindow DetailPopu;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_propertyallton;
    }
    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }
    @Override
    protected void initEventAndData() {
        setToolbarTvTitle("物业通知");
         initAdapter();
        refreshLayout.setOnRefreshListener(this);
        refreshLayout.setRefreshing(true);//刷新效果
        LoadData();
    }

    private void LoadData() {
        Map<String, Object> request1 = new HashMap<>();
        request1.put("token", SharedPreferencedUtils.getStr(Constants.USER_TOKEN,""));
        request1.put("usernum", SharedPreferencedUtils.getStr(Constants.USER_NUM,""));
        request1.put("cityid",SharedPreferencedUtils.getStr("proprety_cityid",CITY_ID+""));
        request1.put("vid",SharedPreferencedUtils.getStr("vid",""));
        request1.put("pageIndex", pageIndex+"");
        request1.put("pageSize", Constants.PAGE_NUMBER10 +"");
        mPresenter.noticeEstates(GsonUtil.obj2JSON(request1));//获取通知
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
            adapter = new PropretyAllTonAdapter(R.layout.properallton_item, this);
            adapter.setOnItemClickListener(this);
            adapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_BOTTOM); //切换动画
            adapter.setEnableLoadMore(false);
          /*  recyclerView.addItemDecoration(new HorizontalDividerItemDecoration.Builder(this)
                    .build());*/
            recyclerView.setAdapter(adapter);
            View emptyView = View.inflate(this, R.layout.partial_empty_view, null);
            adapter.setEmptyView(emptyView);
        }
    }
    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        showPopuWindow_headimage(this.adapter.getData().get(position).getTitile(),this.adapter.getData().get(position).getContent(),
                this.adapter.getData().get(position).getVillagename(),this.adapter.getData().get(position).getCreatedtime());
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
        pageIndex = 1;
        isRefreshing = true;
        LoadData();
    }

    @Override
    public void getSSMCode(String action) {

    }

    @Override
    public void loginEstates(PropertLoginBean bean) {

    }

    @Override
    public void noticeEstates(PropretyNoticeBean bean) {
        totalPage = bean.getTotalPage();
        load_more++;
        if(load_more==1)
        {
            adapter.setOnLoadMoreListener(this,recyclerView);//设置加载更多
        }
//返回的数据 第一次
        if (pageIndex == 1 && adapter.getData().size() <= 0 && (bean.getList() ==
                null || bean.getList().size() <= 0)) {
            View emptyView = View.inflate(this, R.layout.partial_empty_view, null);
            TextView viewById = emptyView.findViewById(R.id.tv_message);
            adapter.setEmptyView(emptyView);
        }
        if (isRefreshing) {
            adapter.setNewData(bean.getList());
            refreshLayout.setRefreshing(false);
        } else {
            adapter.addData(bean.getList());
            adapter.loadMoreComplete();
        }
    }

    @Override
    public void propretyLive(PropretyLiveBean list) {

    }

    @Override
    public void findVillagename(List<VillageNameBean> list) {

    }

    @Override
    public void findLiveaddress(List<LiveAddressBean> list) {

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
            LoadData();
        }
    }

    private void showPopuWindow_headimage(String ctitle,String ccomment,String cqu,long ctime) {
        final View view = LayoutInflater.from(this).inflate(R.layout.tonz_detail_popu, null);
        AppCompatTextView title  = view.findViewById(R.id.pd_title);//标题
        AppCompatTextView comment =  view.findViewById(R.id.pd_content);//内 容
        AppCompatTextView qu = view.findViewById(R.id.pd_qu);//小区名
        AppCompatTextView time = view.findViewById(R.id.pd_time);//时间

        title.setText(ctitle);
        comment.setText(ccomment);
        qu.setText(cqu);
        time.setText(timeSpanToDate(ctime));

        LinearLayoutCompat p_tondetal_back = view.findViewById(R.id.p_tondetal_back);//取消
        DetailPopu = new PopupWindow(view);
        DetailPopu.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        DetailPopu.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);

        DetailPopu.setAnimationStyle(R.style.PDeatailDopuStyle);//设置进入和出场动画
        DetailPopu.setBackgroundDrawable(this.getResources().getDrawable(R.color.white));
        DetailPopu.setOutsideTouchable(true);
        DetailPopu.setFocusable(true);
        DetailPopu.showAtLocation(view, Gravity.BOTTOM, 0, 0);

        p_tondetal_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DetailPopu.dismiss();
            }
        });

    }
}
