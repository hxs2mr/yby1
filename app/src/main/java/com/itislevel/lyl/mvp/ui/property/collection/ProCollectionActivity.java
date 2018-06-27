package com.itislevel.lyl.mvp.ui.property.collection;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.itislevel.lyl.R;
import com.itislevel.lyl.adapter.CollectionListAdapter;
import com.itislevel.lyl.adapter.PropretyHouseListAdapter;
import com.itislevel.lyl.app.Constants;
import com.itislevel.lyl.base.RootActivity;
import com.itislevel.lyl.mvp.model.bean.CatelistBean;
import com.itislevel.lyl.mvp.model.bean.CollectionListBean;
import com.itislevel.lyl.mvp.ui.property.repair.RepairDetailActivity;
import com.itislevel.lyl.utils.ActivityUtil;
import com.itislevel.lyl.utils.GsonUtil;
import com.itislevel.lyl.utils.SAToast;
import com.itislevel.lyl.utils.SharedPreferencedUtils;
import com.itislevel.lyl.utils.UtilsStyle;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018\6\12 0012.我的收藏列表
 */

public class ProCollectionActivity extends RootActivity<CollectionPresenter>implements CollectionContract.View, BaseQuickAdapter.OnItemClickListener, SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {

    @BindView(R.id.refreshLayout)
    SwipeRefreshLayout refreshLayout;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;

    @BindView(R.id.botoom_del_linear)
    LinearLayoutCompat botoom_del_linear;

    @BindView(R.id.qu_name)
    AppCompatTextView qu_name;

    @BindView(R.id.checkbox_all)
    AppCompatCheckBox checkbox_all;

    private boolean is_onclick = true;
    private int load_more =0;
    private int pageIndex = 1;//分页加载时 当前页是第几页
    private int totalPage = 0;//总页数
    private boolean isRefreshing = true;//当前是刷新还是加载
    private CollectionListAdapter adapter;
    public static List<CatelistBean> start_catelist;
    private int delete_all=0;
    private String loaction_delete="";
    private  String dan_id ="";
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
        return R.layout.activity_collection;
    }

    @Override
    protected void initEventAndData() {
        refreshLayout.setOnRefreshListener(this);
        refreshLayout.setRefreshing(true);//刷新效果
        initAdapter();
        LoadData();

        checkbox_all.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                        if(b)
                        {
                            init_del_true(true);
                        }else {
                            init_del_true(false);
                        }
            }
        });
    }

    private void init_del_true(boolean is_onclick) {
        int size = this.adapter.getData().size();
        if(is_onclick)
        {
            for(int i = 0 ; i < size; i++)
            {
                AppCompatCheckBox box = (AppCompatCheckBox) this.adapter.getViewByPosition(i,R.id.checkbox);
                box.setChecked(true);
            }
        }else {
            for(int i = 0 ; i < size; i++)
            {
                AppCompatCheckBox box = (AppCompatCheckBox) this.adapter.getViewByPosition(i,R.id.checkbox);
                box.setChecked(false);
            }
        }
        this.adapter.notifyDataSetChanged();
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
            adapter = new CollectionListAdapter(R.layout.collection_item, this);
            adapter.setOnItemClickListener(this);
            adapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_BOTTOM); //切换动画
            adapter.setEnableLoadMore(false);
            recyclerView.setAdapter(adapter);
            adapter.setOnLoadMoreListener(this,recyclerView);//设置加载更多
            View emptyView = View.inflate(this, R.layout.partial_empty_view, null);
            adapter.setEmptyView(emptyView);
        }
    }

    private void LoadData()
    {
        refreshLayout.setRefreshing(true);//刷新效果
        Map<String, Object> request = new HashMap<>();
        request.put("token", SharedPreferencedUtils.getStr("p_token",""));
        request.put("phone", SharedPreferencedUtils.getStr("proprety_phone",""));
        request.put("pageIndex", pageIndex + "");
        request.put("pageSize", Constants.PAGE_NUMBER10 + "");
        System.out.println("JSON数据***********************"+GsonUtil.obj2JSON(request));
        mPresenter.collectMaintenanceList(GsonUtil.obj2JSON(request));

    }

    @OnClick({R.id.p_p_back,R.id.select_qu_linear,R.id.del_tv})
    public  void  onclick(View view)
    {
        switch (view.getId())
        {
            case R.id.p_p_back:
                ActivityUtil.getInstance().closeActivity(this);
                break;
            case R.id.select_qu_linear://设置编辑到完成的状态
                if(!qu_name.getText().equals("完成"))
                {
                    is_onclick = false;
                    qu_name.setText("完成");
                    botoom_del_linear.setVisibility(View.VISIBLE);
                    int size = this.adapter.getData().size();
                    for(int i = 0 ; i < size; i++)
                    {
                        AppCompatCheckBox box = (AppCompatCheckBox) this.adapter.getViewByPosition(recyclerView,i,R.id.checkbox);
                        box.setVisibility(View.VISIBLE);
                    }

                }else {
                    qu_name.setText("管理");
                    botoom_del_linear.setVisibility(View.GONE);
                    int size = this.adapter.getData().size();
                    for(int i = 0 ; i < size; i++)
                    {
                        AppCompatCheckBox box = (AppCompatCheckBox) this.adapter.getViewByPosition(recyclerView,i,R.id.checkbox);
                        box.setVisibility(View.GONE);
                    }
                    is_onclick = true;
                }
                break;
            case R.id.del_tv://删除按钮
                if(checkbox_all.isChecked())//判断是否也全选删除
                {
                    show_call();
                }else{//在判断是否有单选的操作
                    int size = this.adapter.getData().size();
                    for(int i = 0 ; i < size; i++)
                    {
                        AppCompatCheckBox box = (AppCompatCheckBox) this.adapter.getViewByPosition(recyclerView,i,R.id.checkbox);
                        if(box.isChecked())//判断是否选中
                        {
                            if(!dan_id.equals(""))
                            {
                                dan_id = dan_id+","+this.adapter.getData().get(i).getId();
                            }else{
                                dan_id =this.adapter.getData().get(i).getId()+"";
                            }
                        }
                    }
                    delete_list(dan_id);
                }
                break;
        }
    }
    @Override
    public void collectMaintenanceList(CollectionListBean bean) {
        totalPage = bean.getTotalPage();
//返回的数据 第一次
        if (pageIndex == 1 && adapter.getData().size() <= 0 && (bean.getList() ==
                null || bean.getList().size() <= 0)) {
            adapter.getData().clear();
            adapter.notifyDataSetChanged();
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
    public void deleMaintenanceList(String data) {//删除收藏
        loadingDialog.dismiss();
        loaction_delete="";
        dan_id="";
            pageIndex = 1;
            isRefreshing = true;
            LoadData();
        qu_name.setText("管理");
        botoom_del_linear.setVisibility(View.GONE);
        int size = this.adapter.getData().size();
        for(int i = 0 ; i < size; i++)
        {
            AppCompatCheckBox box = (AppCompatCheckBox) this.adapter.getViewByPosition(recyclerView,i,R.id.checkbox);
            box.setVisibility(View.GONE);
        }
        is_onclick = true;
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
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
            if(is_onclick)
            {
                if(view.getId()!=R.id.checkbox) {
                    Bundle bundle = new Bundle();
                    start_catelist = this.adapter.getData().get(position).getCatelist();
                    bundle.putLong("createtime", this.adapter.getData().get(position).getCreatedtime());
                    bundle.putString("looknum", this.adapter.getData().get(position).getLooknum() + "");
                    bundle.putString("headurl", this.adapter.getData().get(position).getHeadurl());
                    bundle.putString("username", this.adapter.getData().get(position).getRealname());
                    bundle.putString("modelphone", this.adapter.getData().get(position).getMobilephone());
                    bundle.putString("servicearea", this.adapter.getData().get(position).getServicearea());
                    bundle.putString("catelist", GsonUtil.obj2JSON(this.adapter.getData().get(position).getCatelist()));

                    bundle.putString("address", this.adapter.getData().get(position).getAddress() + this.adapter.getData().get(position).getCuntyname());
                    bundle.putString("workrem", this.adapter.getData().get(position).getWorkrem());
                    bundle.putString("rwid", this.adapter.getData().get(position).getRwid() + "");
                    bundle.putString("score", this.adapter.getData().get(position).getScore() + "");
                    bundle.putString("noStoreUp", "1");
                    ActivityUtil.getInstance().openActivity(this, RepairDetailActivity.class, bundle);
                }
            }else {
                AppCompatCheckBox box = (AppCompatCheckBox) this.adapter.getViewByPosition(position,R.id.checkbox);
                if(box.isChecked())
                {
                    box.setChecked(false);
                    checkbox_all.setChecked(false);
                }else {
                    box.setChecked(true);
                }
            }
    }

    @Override
    public void stateError(Throwable e) {
        super.stateError(e);
        dan_id="";
        loadingDialog.dismiss();
        loaction_delete="";
        loadingDialog.dismiss();
        adapter.getData().clear();
        adapter.notifyDataSetChanged();
        View emptyView = View.inflate(this, R.layout.partial_empty_view, null);
        adapter.setEmptyView(emptyView);
        if (refreshLayout.isRefreshing()) {
            refreshLayout.setRefreshing(false);
        }
        if (adapter.isLoadMoreEnable()) {
            adapter.loadMoreComplete();
        }
        qu_name.setText("管理");
        botoom_del_linear.setVisibility(View.GONE);
        int size = this.adapter.getData().size();
        for(int i = 0 ; i < size; i++)
        {
            AppCompatCheckBox box = (AppCompatCheckBox) this.adapter.getViewByPosition(recyclerView,i,R.id.checkbox);
            box.setVisibility(View.GONE);
        }
        is_onclick = true;
    }

    public void show_call(){
        //实例化建造者
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //设置警告对话框的标题
        //设置警告显示的图片
//        builder.setIcon(android.R.drawable.ic_dialog_alert);
        //设置警告对话框的提示信息
        builder.setTitle("删除收藏");
        builder.setMessage("是否清空所有的收藏?");
        //设置”正面”按钮，及点击事件
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                int size = adapter.getData().size();
                String[] id=  new String[size];
                loadingDialog.show();
                for(int i=0;i<size;i++)
                {
                    if(!loaction_delete.equals(""))
                    {
                        loaction_delete = loaction_delete+","+adapter.getData().get(i).getId();
                    }else{
                        loaction_delete = adapter.getData().get(i).getId()+"";
                    }
                }
                delete_list(loaction_delete);
            }
        });
        //设置“中立”按钮，及点击事件
        builder.setNeutralButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which){
                dialog.cancel();
            }
        });
        //显示对话框
        builder.show();
    }

    private void delete_list(String id) {
        Map<String, Object> request = new HashMap<>();
        request.put("token", SharedPreferencedUtils.getStr("p_token",""));
        request.put("phone", SharedPreferencedUtils.getStr("proprety_phone",""));
        request.put("id",id+"");
        mPresenter.deleMaintenanceList(GsonUtil.obj2JSON(request));
    }

    @Override
    public void onRefresh() {
        refreshLayout.setRefreshing(true);//刷新效果
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
