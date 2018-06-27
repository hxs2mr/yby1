package com.itislevel.lyl.mvp.ui.myteam;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.itislevel.lyl.R;
import com.itislevel.lyl.adapter.MyTeamAdapter;
import com.itislevel.lyl.app.Constants;
import com.itislevel.lyl.base.RootActivity;
import com.itislevel.lyl.mvp.model.bean.MeiZiBean;
import com.itislevel.lyl.mvp.model.bean.MyTeamBean;
import com.itislevel.lyl.mvp.ui.main.MainActivity;
import com.itislevel.lyl.utils.ActivityUtil;
import com.itislevel.lyl.utils.DateUtil;
import com.itislevel.lyl.utils.GsonUtil;
import com.itislevel.lyl.utils.SharedPreferencedUtils;
import com.itislevel.lyl.utils.ToastUtil;
import com.itislevel.lyl.utils.rxbus.RxBus;
import com.itislevel.lyl.utils.rxbus.annotation.Subscribe;
import com.itislevel.lyl.utils.rxbus.annotation.UseRxBus;
import com.itislevel.lyl.utils.rxbus.event.EventThread;
import com.orhanobut.logger.Logger;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

@UseRxBus
public class MyTeamActivity extends RootActivity<MyTeamPresenter> implements
        MyTeamContract.View, BaseQuickAdapter.OnItemClickListener, BaseQuickAdapter
        .OnItemChildClickListener ,SwipeRefreshLayout.OnRefreshListener ,BaseQuickAdapter.RequestLoadMoreListener{


    @BindView(R.id.refreshLayout)
    SwipeRefreshLayout refreshLayout;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private int load_more = 0;

    //是否已添加没有更多数据的布局
    private boolean isAddNoMoreView = false;
    private int pageIndex = 1;//分页加载时 当前页是第几页
    private int totalPage = 0;//总页数
    private boolean isRefreshing = true;//当前是刷新还是加载
    private MyTeamAdapter adapter;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_team;

    }

    @Override
    protected void initEventAndData() {
        setToolbarTvTitle("咨询列表");
        initAdapter();

        RxBus.getInstance().post(RxBus.getInstance().getTag(MainActivity.class,
                RxBus.TAG_UPDATE), "badgecount");
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
    }

    private void loadData() {
        Map<String, String> request = new HashMap<>();
        request.put("token", SharedPreferencedUtils.getStr(Constants.USER_TOKEN));
        request.put("usernum", SharedPreferencedUtils.getStr(Constants.USER_NUM));
        request.put("adviserid", SharedPreferencedUtils.getStr(Constants.USER_ID));

//        request.put("firstcateid", "");
        request.put("pageSize", Constants.PAGE_NUMBER10 + "");
        request.put("pageIndex", pageIndex + "");

        mPresenter.teamProblemList(GsonUtil.obj2JSON(request));
    }

    /**
     * 初始化adapter
     */
    private void initAdapter() {
        if (adapter == null) {
            adapter = new MyTeamAdapter(R.layout.item_my_message, this);
            adapter.setOnItemClickListener(this);
            adapter.setOnItemChildClickListener(this);
            adapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN); //切换动画
            adapter.setEnableLoadMore(false);

//            adapter.setEmptyView(R.layout.item_refresh_loading_view, (ViewGroup) recyclerView
// .getParent());

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
    public void showContent(String msg) {

    }

    @Override
    public void loadData(List<MeiZiBean> meiZiBeans) {

    }


    @Override
    public void teamProblemList(MyTeamBean myTeamBean) {

        int count=0;
        load_more++;
        if(load_more==1)
        {
            adapter.setOnLoadMoreListener(this,recyclerView);//设置加载更多
        }
        for (MyTeamBean.ListBean item:myTeamBean.getList()){
            if (item.getIsreply()==0){
                count=count+1;
            }
        }

        totalPage = myTeamBean.getTotalPage();
        //返回的数据 第一次
        if (pageIndex == 1 && adapter.getData().size() <= 0 && (myTeamBean.getList() ==
                null || myTeamBean.getList().size() <= 0)) {
            View emptyView = View.inflate(this, R.layout.partial_empty_view, null);
            TextView viewById = emptyView.findViewById(R.id.tv_message);
            adapter.setEmptyView(emptyView);
        }

        if (isRefreshing) {
            adapter.setNewData(myTeamBean.getList());
            refreshLayout.setRefreshing(false);
        } else {
            adapter.addData(myTeamBean.getList());
            adapter.loadMoreComplete();
        }
//        int anInt = SharedPreferencedUtils.getInt(Constants.BADGE_COUNT_TEAM, 0);
//        anInt = anInt + 1;
        SharedPreferencedUtils.setInt(Constants.BADGE_COUNT_TEAM,count);
    }

    @Override
    public void teamReplay(String message) {

    }

    @Override
    public void stateEmpty() {

    }

    @Override
    public void stateLoading() {

    }


    @Override
    public void stateError(Throwable e) {
        if (adapter.getData() == null || adapter.getData().size() <= 0) {
            View emptyView = View.inflate(this, R.layout.partial_empty_view, null);
            adapter.setEmptyView(emptyView);
        }

        if (refreshLayout.isRefreshing()) {
            refreshLayout.setRefreshing(false);
        }
        if (adapter.isLoadMoreEnable()) {
            adapter.loadMoreComplete();
        }
    }

    @Override
    public void stateSuccess() {

    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    Bundle bundle = new Bundle();

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        MyTeamBean.ListBean item = this.adapter.getItem(position);

        //提问者信息
        bundle.putString("proble_username", item.getNickname());
        bundle.putString("proble_userid", item.getUserid() + "");
        bundle.putString("img", item.getImge());
        bundle.putString("proble_content", item.getContent());
        bundle.putString("proble_querstion_id", item.getQuestionid() + "");
        bundle.putString("tv_problem_time", DateUtil.timeSpanToDateTime(item.getCreatedtime()));

        //回答信息 可能为空
        List<MyTeamBean.ListBean.CommentsBean> comments = item.getComments();
        if (comments != null && comments.size() > 0) {
            bundle.putString("replay_content", comments.get(0).getComment());
        } else {
            bundle.putString("replay_content", "");
        }

        ActivityUtil.getInstance().openActivity(MyTeamActivity.this, TeamAnswerActivity
                .class, bundle);

    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        MyTeamBean.ListBean item = this.adapter.getItem(position);

        //提问者信息
        bundle.putString("proble_username", item.getNickname());
        bundle.putString("proble_userid", item.getUserid() + "");
        bundle.putString("img", item.getImge());
        bundle.putString("proble_content", item.getContent());
        bundle.putString("proble_querstion_id", item.getQuestionid() + "");
        bundle.putString("tv_problem_time", DateUtil.timeSpanToDateTime(item.getCreatedtime()));
        //回答信息 可能为空
        List<MyTeamBean.ListBean.CommentsBean> comments = item.getComments();
        if (comments != null && comments.size() > 0) {
            bundle.putString("replay_content", comments.get(0).getComment());
        } else {
            bundle.putString("replay_content", "");
        }

        ActivityUtil.getInstance().openActivity(MyTeamActivity.this, TeamAnswerActivity
                .class, bundle);

    }

    @Subscribe(tag = RxBus.TAG_UPDATE, thread = EventThread.MAIN_THREAD)
    public void rxrefresh(String msg) {
        if (msg.equals("refresh")) {
            ToastUtil.Success("回复成功");
            Logger.i("开始刷新");
            refreshLayout.setRefreshing(true);// ;//第一次进入触发自动刷新，演示效果--然后在重新加载数据
            pageIndex = 1;
            isRefreshing = true;
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
