package com.itislevel.lyl.mvp.ui.usermonkey;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.itislevel.lyl.R;
import com.itislevel.lyl.adapter.UserFanHistoryAdapter;
import com.itislevel.lyl.adapter.UserShouAdapter;
import com.itislevel.lyl.base.RootActivity;
import com.itislevel.lyl.utils.UtilsStyle;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 作者： XS
 * 邮箱：1363826037@qq.com
 * 描述:
 * 创建时间:  2018\7\7 0007 15:11
 */
public class UserMonkeyShouActivity extends RootActivity<UserMonkeyPresenter> implements UserMonkeyContract.View, BaseQuickAdapter.RequestLoadMoreListener {
    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;

    private int pageIndex = 1;//分页加载时 当前页是第几页
    private int totalPage = 0;//总页数
    private boolean isRefreshing = true;//当前是刷新还是加载
    private UserShouAdapter adapter;
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
        return R.layout.activity_usershou;
    }

    @Override
    protected void initEventAndData() {
        setToolWight("收支明细");
        initAdapter();
    }

    /**
     * 初始化adapter
     */
    private void initAdapter() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        if (adapter == null) {
            adapter = new UserShouAdapter(R.layout.item_usershou);
            adapter.setOnLoadMoreListener(this,recyclerView);
            adapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN); //切换动画
            adapter.setEnableLoadMore(false);
            recyclerView.addItemDecoration(new HorizontalDividerItemDecoration.Builder(this)
                    .color(Color.parseColor("#e8e8e8"))
                    .margin(35,0)
                    .build());
            recyclerView.setAdapter(adapter);
            View emptyView = View.inflate(this, R.layout.partial_empty_view, null);
            TextView viewById = emptyView.findViewById(R.id.tv_message);
            viewById.setText("暂无收支明细！");
            adapter.setEmptyView(emptyView);

            List<String> list = new ArrayList<>();
            list.add("");
            list.add("");
            list.add("");
            list.add("");
            list.add("");
            adapter.setNewData(list);
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
    public void stateError() {
        super.stateError();
        if (adapter.getData() == null || adapter.getData().size() <= 0) {
            View emptyView = View.inflate(this, R.layout.partial_empty_view, null);
            TextView viewById = emptyView.findViewById(R.id.tv_message);
            viewById.setText("暂无收支明细！");
            adapter.setEmptyView(emptyView);
        }
        loadingDialog.dismiss();
        if (adapter.isLoadMoreEnable()) {
            adapter.loadMoreFail();
        }
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
           // loadData();
        }
    }
}
