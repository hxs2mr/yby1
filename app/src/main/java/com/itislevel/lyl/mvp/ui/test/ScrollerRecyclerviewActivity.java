package com.itislevel.lyl.mvp.ui.test;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.itislevel.lyl.R;
import com.itislevel.lyl.adapter.FamilySendGiftAdapter;
import com.itislevel.lyl.adapter.TestAdapter;
import com.itislevel.lyl.app.Constants;
import com.itislevel.lyl.base.RootActivity;
import com.itislevel.lyl.mvp.model.bean.AppUpdate;
import com.itislevel.lyl.mvp.model.bean.TestBean;
import com.itislevel.lyl.mvp.model.bean.UserHeaderNickInfo;
import com.itislevel.lyl.mvp.ui.main.MainContract;
import com.itislevel.lyl.mvp.ui.main.MainPresenter;
import com.itislevel.lyl.utils.GsonUtil;
import com.itislevel.lyl.utils.SharedPreferencedUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;

public class ScrollerRecyclerviewActivity extends RootActivity<MainPresenter> implements
        MainContract.View, View
        .OnClickListener {


    @BindView(R.id.recycler)
    RecyclerView recycler;

    private TestAdapter adapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_scroller_recyclerview;
    }

    @Override
    protected void initEventAndData() {
        initAdapter();
        loadData();

    }

    /**
     * 初始化adapter
     */
    private void initAdapter() {
        if (adapter == null) {
            adapter = new TestAdapter(R.layout.item_te);
            adapter.openLoadAnimation(BaseQuickAdapter.SCALEIN); //切换动画
            adapter.setEnableLoadMore(false);

            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

            recycler.setLayoutManager(layoutManager);
            recycler.setAdapter(adapter);

        }

    }

    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
//            TestBean item = adapter.getItem(0);
//            adapter.remove(0);
//            adapter.addData(item);

        }
    };
    List<TestBean> list = new ArrayList<>();

    private void loadData() {

//            adapter.setNewData(familySendGiftRecordBean.getList());

//        for (int i = 0; i < 10; i++) {
//            list.add(new TestBean(i,"itisi:"+i));
//        }
//        adapter.setNewData(list);


        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                handler.sendEmptyMessage(0);
            }
        };
        timer.schedule(task, 0, 2000);

    }

    @Override
    public void onClick(View v) {

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
    public void testShowView(String smg) {

    }

    @Override
    public void userInfoById(List<UserHeaderNickInfo> userHeaderNickInfos) {

    }

    @Override
    public void appupdate(AppUpdate appUpdate) {

    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }
}
