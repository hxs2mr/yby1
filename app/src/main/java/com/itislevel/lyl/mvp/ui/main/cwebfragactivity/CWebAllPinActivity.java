package com.itislevel.lyl.mvp.ui.main.cwebfragactivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.itislevel.lyl.R;
import com.itislevel.lyl.adapter.CFAllPinAdapter;
import com.itislevel.lyl.adapter.CfChildFragmentAdapter;
import com.itislevel.lyl.app.Constants;
import com.itislevel.lyl.base.RootActivity;
import com.itislevel.lyl.mvp.model.bean.CFChildBean;
import com.itislevel.lyl.mvp.model.bean.CFPinBean;
import com.itislevel.lyl.mvp.model.bean.CWPinBean;
import com.itislevel.lyl.mvp.ui.main.childfragment.ChildContract;
import com.itislevel.lyl.mvp.ui.main.childfragment.ChildPresenter;
import com.itislevel.lyl.utils.GsonUtil;
import com.itislevel.lyl.utils.SharedPreferencedUtils;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;

/**
 * Created by Administrator on 2018\5\17 0017.查看跟多评论
 */

public class CWebAllPinActivity extends RootActivity<ChildPresenter> implements ChildContract.View, BaseQuickAdapter.OnItemClickListener, BaseQuickAdapter.OnItemChildClickListener, SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener{
    @BindView(R.id.refreshLayout)
    SwipeRefreshLayout refreshLayout;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private int load_more =0;
    private int pageIndex = 1;//分页加载时 当前页是第几页
    private int totalPage = 0;//总页数
    private boolean isRefreshing = true;//当前是刷新还是加载


     private  CFAllPinAdapter adapter;
    private Bundle bundle;
    private String id;

    private AppCompatImageView cf_dianzan_image1;
    private AppCompatTextView cf_dianzan_text1;
    private LinearLayoutCompat cf_dianzan_linear1;//点赞
    private  int  mPostion=0;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_allpin;
    }
    @Override
    protected void initEventAndData() {
        bundle = getIntent().getExtras();
        id = bundle.getString("id");
        setToolbarTvTitle("评论列表");
        initAdapter();
        refreshLayout.setOnRefreshListener(this);
        refreshLayout.setRefreshing(true);//刷新效果
        LoadData();
    }

    private void LoadData() {
        Map<String,String> request = new HashMap<>();
        request.put("token", SharedPreferencedUtils.getStr(Constants.USER_TOKEN));
        request.put("usernum", SharedPreferencedUtils.getStr(Constants.USER_NUM));
        request.put("id",id);
        request.put("userid", SharedPreferencedUtils.getStr(Constants.USER_ID));
        request.put("pageIndex", pageIndex+"");
        request.put("pageSize", Constants.PAGE_NUMBER10+"");
        mPresenter.cfcommentlist(GsonUtil.obj2JSON(request));
    }

    private void initAdapter() {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        // ((SimpleItemAnimator)recyclerView.getItemAnimator()).setSupportsChangeAnimations(false);
        recyclerView.setLayoutManager(manager);
        refreshLayout.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_orange_dark,
                android.R.color.holo_red_dark);
        if (adapter == null){
            adapter = new CFAllPinAdapter(R.layout.allpin_item, this);
            adapter.setOnItemChildClickListener(this);
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
    protected void initInject() {
        getActivityComponent().inject(this);
    }
    @Override
    public void fristload(CFChildBean cfChildBean) {

    }

    @Override
    public void updatepointnum(Integer msg) {

    }

    @Override
    public void looknumFlatcount(String data) {

    }

    @Override
    public void addFlatComment(String action) {

    }

    @Override
    public void cfcommentlist(CFPinBean bean) {
        if(load_more==0)
        {  load_more=1;
            adapter.setOnLoadMoreListener(CWebAllPinActivity.this,recyclerView);//设置加载更多
        }
        //返回的数据 第一次
        if ( bean.getList() == null || bean
                .getList().size() <= 0) {
            View emptyView = View.inflate(this, R.layout.partial_empty_view, null);
            TextView viewById = emptyView.findViewById(R.id.tv_message);
            adapter.setEmptyView(emptyView);
        }
        totalPage = bean.getTotalPage();
        if (isRefreshing) {
            adapter.getData().clear();
            adapter.setNewData(bean.getList());
            adapter.notifyDataSetChanged();
            refreshLayout.setRefreshing(false);
        } else {
            adapter.addData(bean.getList());
            adapter.loadMoreComplete();
        }

    }

    @Override
    public void cf_addzan(String data) {
        if(data.equals("0"))
        {
                if(this.adapter.getData().get(mPostion).getIspoint().equals("0"))
                {
                    cf_dianzan_image1.setBackgroundResource(R.mipmap.cf_dian);
                    cf_dianzan_text1.setTextColor(Color.parseColor("#ff7b00"));
                    this.adapter.getData().get(mPostion).setIspoint("1");
                    int size = Integer.parseInt(cf_dianzan_text1.getText().toString())+1;
                    cf_dianzan_text1.setText(size+"");
                }else {
                    cf_dianzan_image1.setBackgroundResource(R.mipmap.cf_nodian);
                    cf_dianzan_text1.setTextColor(Color.parseColor("#999999"));
                    this.adapter.getData().get(mPostion).setIspoint("0");
                    int size = Integer.parseInt(cf_dianzan_text1.getText().toString())-1;
                    cf_dianzan_text1.setText(size+"");
                }
        }
    }

    @Override
    public void delFlatComment(String action) {
        this.adapter.remove(mPostion);
        if(mPostion==0)
        {
            EventBus.getDefault().post(new CWPinBean("1"));
        }else {
            EventBus.getDefault().post(new CWPinBean("1"));
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
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        //d
        mPostion = position;
        cf_dianzan_image1 = (AppCompatImageView) adapter.getViewByPosition(position, R.id.cf_dianzan_image1);
        cf_dianzan_text1 = (AppCompatTextView) adapter.getViewByPosition(position, R.id.cf_dianzan_text1);
        cf_dianzan_linear1 = (LinearLayoutCompat) adapter.getViewByPosition(position, R.id.cf_dianzan_linear1);

            switch (view.getId())
            {

                case R.id.cf_dianzan_linear1:
                    cf_dianzan_image1.startAnimation(AnimationUtils.loadAnimation(
                            this, R.anim.zan_anim));
                    Map<String,String> request0 = new HashMap<>();
                    request0.put("token", SharedPreferencedUtils.getStr(Constants.USER_TOKEN));
                    request0.put("usernum", SharedPreferencedUtils.getStr(Constants.USER_NUM));
                    request0.put("userid",SharedPreferencedUtils.getStr(Constants.USER_ID));
                    request0.put("id",this.adapter.getData().get(position).getId()+"");
                    //request0.put("reluserid",HEAD_BEAN.getList().get(0).getUserid()+"");
                    mPresenter.cf_addzan(GsonUtil.obj2JSON(request0));
                    break;
                case R.id.delete:
                    show_clear_cart(this.adapter.getData().get(position).getId());
                    break;

            }
    }

    public void show_clear_cart(int id){
        //实例化建造者
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //设置警告对话框的标题
        builder.setTitle("删除动态");
        //设置警告显示的图片
//        builder.setIcon(android.R.drawable.ic_dialog_alert);
        //设置警告对话框的提示信息
        builder.setMessage("确认要删除这条评论吗？");
        //设置”正面”按钮，及点击事件
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Map<String, String> request = new HashMap<>();
                request.put("token", SharedPreferencedUtils.getStr(Constants.USER_TOKEN));
                request.put("usernum", SharedPreferencedUtils.getStr(Constants.USER_NUM));
                request.put("userid", SharedPreferencedUtils.getStr(Constants.USER_ID));
                request.put("fabulous","0");
                request.put("id",id+"");
                mPresenter.delFlatComment(GsonUtil.obj2JSON(request));
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
        builder.setNeutralButton("等等看吧", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        //显示对话框
        builder.show();
    }
    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onRefresh() {
        refreshLayout.setRefreshing(true);
        pageIndex = 1;
        isRefreshing = true;
        LoadData();
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
}
