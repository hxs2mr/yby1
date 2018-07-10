package com.itislevel.lyl.mvp.ui.main.mine.fan;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.itislevel.lyl.R;
import com.itislevel.lyl.adapter.ShanListAdapter;
import com.itislevel.lyl.app.Constants;
import com.itislevel.lyl.base.RootActivity;
import com.itislevel.lyl.mvp.model.bean.FanExitBean;
import com.itislevel.lyl.mvp.model.bean.FanPayBean;
import com.itislevel.lyl.mvp.model.bean.FanRecodeBean;
import com.itislevel.lyl.mvp.model.bean.FanXianBean;
import com.itislevel.lyl.mvp.model.bean.ShanHomeBean;
import com.itislevel.lyl.utils.ActivityUtil;
import com.itislevel.lyl.utils.GsonUtil;
import com.itislevel.lyl.utils.SharedPreferencedUtils;
import com.itislevel.lyl.utils.ToastUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 作者： XS
 * 邮箱：1363826037@qq.com
 * 描述:我是商家
 * 创建时间:  2018\7\2 0002 09:06
 */
public class PersonShanActivity extends RootActivity<PersonFanPresenter>implements PersonFanContract.View, BaseQuickAdapter.RequestLoadMoreListener, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.fan_qi_monkey)
    AppCompatTextView fan_qi_monkey;//余额

    @BindView(R.id.xian_chon)
    AppCompatTextView xian_chon;//在线充值

    @BindView(R.id.fanxian_linear)
    LinearLayoutCompat fanxian_linear;//返现记录

    @BindView(R.id.chong_linear)
    LinearLayoutCompat chong_linear;//充值记录

    @BindView(R.id.refreshlayout)
    SwipeRefreshLayout refreshLayout;

    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;

    @BindView(R.id.fan_qi_number)
    AppCompatTextView fan_qi_number;
    private int pageIndex = 1;//分页加载时 当前页是第几页
    private int totalPage = 0;//总页数
    private boolean isRefreshing = true;//当前是刷新还是加载
    private ShanListAdapter adapter;

    private String monkey="";
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
    protected void initInject() {
        getActivityComponent().inject(this);
    }
    @Override
    protected int getLayoutId() {
        return R.layout.activity_shan;
    }
    @Override
    protected void initEventAndData() {
        initAdapter();
        setToolbarTvTitle("");
        setToolKON();
        setToolbarMoreTxt("查看详情");
        setToolbarMoreClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                if(!monkey.equals(""))
                {
                    bundle.putString("yu",monkey);
                }else {
                    bundle.putString("yu",0.00+"");
                }
                ActivityUtil.getInstance().openActivity(PersonShanActivity.this,PersonFanUserDetailAcivity.class,bundle);
            }
        });
        refreshLayout.setOnRefreshListener(this);
        refreshLayout.setRefreshing(true);//刷新效果
        loadData();
    }

    private void loadData() {
        Map<String,String> request = new HashMap<>();
        request.put("token", SharedPreferencedUtils.getStr(Constants.USER_TOKEN,""));
        request.put("usernum", SharedPreferencedUtils.getStr(Constants.USER_NUM,""));
        request.put("merchantid",SharedPreferencedUtils.getStr("fan_merchantid",""));
        request.put("pageIndex",pageIndex+"");
        request.put("pageSize",Constants.PAGE_NUMBER10+"");
        mPresenter.merchantmainpage(GsonUtil.obj2JSON(request));
    }

    /**
     * 初始化adapter
     */
    private void initAdapter() {
        if (adapter == null) {
            adapter = new ShanListAdapter(R.layout.item_shan);
            adapter.setOnLoadMoreListener(this,recyclerView);
            adapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN); //切换动画
            adapter.setEnableLoadMore(false);
            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(adapter);
            View emptyView = View.inflate(this, R.layout.partial_empty_view, null);
            TextView tv_message = emptyView.findViewById(R.id.tv_message);
            tv_message.setText("暂无返现明细！");
            //adapter.setEmptyView(emptyView);
        }
    }

    @OnClick({R.id.xian_chon,R.id.fanxian_linear,R.id.chong_linear})
    public  void onclick(View view)
    {
        switch (view.getId())
        {
            case R.id.xian_chon://在线充值
                ActivityUtil.getInstance().openActivity(this,PersonMonkeyActivity.class);
                break;
            case R.id.fanxian_linear://返现记录
                ActivityUtil.getInstance().openActivity(this,PersonFanjiluActivity.class);
                break;
            case R.id.chong_linear://充值记录
                ActivityUtil.getInstance().openActivity(this,PersonChonjiluActivity.class);
                break;
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
    public void onLoadMoreRequested() {
        adapter.setEnableLoadMore(true);
        pageIndex++;
        if (pageIndex > totalPage) {
            adapter.loadMoreEnd();
            return;
        } else {
            isRefreshing = false;
            loadData();
        }
    }

    @Override
    public void onRefresh() {
        refreshLayout.setRefreshing(true);//刷新效果
        pageIndex = 1;
        isRefreshing = true;
        loadData();
    }
    @Subscribe
    public  void onEvent(FanExitBean bean)
    {
        ActivityUtil.getInstance().closeActivity(this);
    }
    @Override
    public void stateError(Throwable e) {
        super.stateError(e);
        refreshLayout.setRefreshing(false);//刷新效果
    }
    @Override
    public void stateError() {
        super.stateError();
        if (adapter.getData() == null || adapter.getData().size() <= 0) {
            View emptyView = View.inflate(this, R.layout.partial_empty_view, null);
            TextView viewById = emptyView.findViewById(R.id.tv_message);
            viewById.setText("暂无返现明细！");
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
    public void merchantmainpage(ShanHomeBean bean) {
        fan_qi_number.setText("已分期"+bean.getPageBean().getList().size()+"笔");
        fan_qi_monkey.setText(bean.getBalance());
        monkey = bean.getBalance();
        totalPage = bean.getPageBean().getTotalPage();
//返回的数据 第一次
        if (pageIndex == 1 && adapter.getData().size() <= 0 && (bean.getPageBean().getList() ==
                null || bean.getPageBean().getList().size() <= 0)) {
            View emptyView = View.inflate(this, R.layout.partial_empty_view, null);
            TextView viewById = emptyView.findViewById(R.id.tv_message);
            viewById.setText("暂无返现明细！");
            adapter.setEmptyView(emptyView);
        }
        if (isRefreshing) {
            adapter.setNewData(bean.getPageBean().getList());
            refreshLayout.setRefreshing(false);
        } else {
            adapter.addData(bean.getPageBean().getList());
            adapter.loadMoreComplete();
        }
    }

    @Override
    public void rechargeRecord(FanRecodeBean bean) {

    }

    @Override
    public void cashbackist(FanXianBean bean) {

    }

    @Override
    public void onlinerecharge(String msg) {

    }

    @Subscribe
    public void OnEvent(FanPayBean bean)
    {
        ToastUtil.Info("充值成功!");
        DecimalFormat df = new DecimalFormat("0.00");//格式化小数
        float t1 = Float.parseFloat(monkey);
        float t2 = Float.parseFloat(bean.getTotal_paces());
        if(monkey.equals(""))
        {
            fan_qi_monkey.setText(df.format(t2));
        }else {
            fan_qi_monkey.setText(df.format(t1+t2));
        }
    }
}
