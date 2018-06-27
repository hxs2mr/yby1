package com.itislevel.lyl.mvp.ui.property.houselist;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.itislevel.lyl.R;
import com.itislevel.lyl.adapter.LiveAddressBean;
import com.itislevel.lyl.adapter.PropretyHomeListAdapter;
import com.itislevel.lyl.adapter.PropretyHouseListAdapter;
import com.itislevel.lyl.adapter.SelectQuAdapter;
import com.itislevel.lyl.app.Constants;
import com.itislevel.lyl.base.RootActivity;
import com.itislevel.lyl.mvp.model.bean.HomeDetailBean;
import com.itislevel.lyl.mvp.model.bean.VillageNameBean;
import com.itislevel.lyl.mvp.ui.property.bill.PropertyBillActivity;
import com.itislevel.lyl.mvp.ui.property.homelist.PropertyHomeContract;
import com.itislevel.lyl.mvp.ui.property.homelist.PropertyHomePresenter;
import com.itislevel.lyl.utils.ActivityUtil;
import com.itislevel.lyl.utils.GsonUtil;
import com.itislevel.lyl.utils.SharedPreferencedUtils;
import com.itislevel.lyl.utils.UtilsStyle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import cn.finalteam.rxgalleryfinal.ui.widget.HorizontalDividerItemDecoration;

/**
 * Created by Administrator on 2018\5\25 0025. 在线缴费的房屋列表
 */

public class PropertyHouseListActivity extends RootActivity<PropertyHomePresenter> implements PropertyHomeContract.View, SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.OnItemClickListener{
    @BindView(R.id.refreshLayout)
    SwipeRefreshLayout refreshLayout;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;

    @BindView(R.id.select_qu_linear)
    LinearLayoutCompat select_qu_linear;

    @BindView(R.id.qu_name)
    AppCompatTextView qu_name;

    @BindView(R.id.select_qu_im)
    AppCompatImageView select_qu_im;

    @BindView(R.id.gray_layout)
    View gray_layout;
    private int load_more =0;
    private int pageIndex = 1;//分页加载时 当前页是第几页
    private int totalPage = 0;//总页数
    private boolean isRefreshing = true;//当前是刷新还是加载

    private PropretyHouseListAdapter adapter;
    private SelectQuAdapter Qu_Adapter;
    private List<VillageNameBean> QU_LIST;
    private PopupWindow POPU;
    private String vid;
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
        return R.layout.activity_houselist;
    }
    @Override
    protected void initEventAndData() {
        QU_LIST = new ArrayList<>();
        initAdapter();
        refreshLayout.setOnRefreshListener(this);
        String qu_name_t =  SharedPreferencedUtils.getStr("villagename","请登录");
        qu_name.setText(qu_name_t);
        LoadQUData();
        vid = SharedPreferencedUtils.getStr("vid","");
        LoadHomeData(vid);
    }
    private void LoadQUData() {
        Map<String, Object> request = new HashMap<>();
        request.put("token", SharedPreferencedUtils.getStr("p_token",""));
        request.put("phone", SharedPreferencedUtils.getStr("proprety_phone",""));
        mPresenter.findVillagename(GsonUtil.obj2JSON(request));//获取小区的列表数据
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
            adapter = new PropretyHouseListAdapter(R.layout.houselist_item, this);
            adapter.setOnItemClickListener(this);
            adapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_BOTTOM); //切换动画
            adapter.setEnableLoadMore(false);
            recyclerView.setAdapter(adapter);
            View emptyView = View.inflate(this, R.layout.partial_empty_view, null);
            adapter.setEmptyView(emptyView);
        }
    }
    @OnClick({R.id.p_p_back,R.id.select_qu_linear})
    public  void  onclick(View view)
    {
        switch (view.getId())
        {
            case R.id.p_p_back:
                ActivityUtil.getInstance().closeActivity(this);
                break;
            case R.id.select_qu_linear:
                gray_layout.setVisibility(View.VISIBLE);
                POPU = showTipPopupWindow(select_qu_linear);
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
    public void stateError(Throwable e) {
        super.stateError(e);
        refreshLayout.setRefreshing(false);
    }
    @Override
    public void onRefresh() {
            refreshLayout.setRefreshing(true);
            isRefreshing = true;
            LoadHomeData(vid);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        Bundle bundle = new Bundle();
        bundle.putString("homenumber",this.adapter.getData().get(position).getUsernum());
       String  homelocation = this.adapter.getData().get(position).getProvname()+this.adapter.getData().get(position).getCityname()+this.adapter.getData().get(position).getVillagename()+this.adapter.getData().get(position).getLiveaddress();
        bundle.putString("homelocation",homelocation);
        bundle.putString("managerid",this.adapter.getData().get(position).getManagerid()+"");
        bundle.putString("userid",this.adapter.getData().get(position).getUserid()+"");
        bundle.putString("vid",this.adapter.getData().get(position).getVid()+"");
        bundle.putString("provinceid",this.adapter.getData().get(position).getProvinceid()+"");
        bundle.putString("cityid",this.adapter.getData().get(position).getCityid()+"");
        bundle.putString("countyid",this.adapter.getData().get(position).getCountyid()+"");
        bundle.putString("username",this.adapter.getData().get(position).getUsername()+"");
        ActivityUtil.getInstance().openActivity(this, PropertyBillActivity.class,bundle);
    }
    public PopupWindow showTipPopupWindow(final View anchorView) {

        final View contentView = LayoutInflater.from(anchorView.getContext()).inflate(R.layout.select_qu_popu, null);
        contentView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        RecyclerView recyclerView1 = contentView.findViewById(R.id.select_recycle);
        initAdapter_Qu(recyclerView1);
        final PopupWindow popupWindow = new PopupWindow(contentView, contentView.getMeasuredWidth(), contentView.getMeasuredHeight(), false);

        recyclerView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.dismiss();
            }
        });
        contentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });

        contentView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                // 自动调整箭头的位置
                // autoAdjustArrowPos(popupWindow, contentView, anchorView);
                contentView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
            }
        });
        popupWindow.setBackgroundDrawable(this.getResources().getDrawable(R.color.transparent));
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                RotateAnimation animation = new RotateAnimation(180, 0, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                animation.setDuration(350);
                animation.setFillAfter(true);
                select_qu_im.startAnimation(animation);
                gray_layout.setVisibility(View.GONE);
            }
        });
        // 如果希望showAsDropDown方法能够在下面空间不足时自动在anchorView的上面弹出
        // 必须在创建PopupWindow的时候指定高度，不能用wrap_content
        popupWindow.showAsDropDown(anchorView);
        return popupWindow;
    }
    private void initAdapter_Qu(RecyclerView select_recycle ) {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        // ((SimpleItemAnimator)recyclerView.getItemAnimator()).setSupportsChangeAnimations(false);
        select_recycle.setLayoutManager(manager);
        Qu_Adapter = new SelectQuAdapter(R.layout.select_qu_item_two,this);
        Qu_Adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                POPU.dismiss();
                vid = Qu_Adapter.getData().get(position).getVid();
                LoadHomeData(vid);
                qu_name.setText(Qu_Adapter.getData().get(position).getVillagename());
                RotateAnimation animation = new RotateAnimation(180, 0, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                animation.setDuration(350);
                animation.setFillAfter(true);
                select_qu_im.startAnimation(animation);
                gray_layout.setVisibility(View.GONE);

                //在加载房屋列表界面的数据
            }
        });
        Qu_Adapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_BOTTOM); //切换动画
        Qu_Adapter.setEnableLoadMore(false);
        select_recycle.setAdapter(Qu_Adapter);
        Qu_Adapter.setNewData(QU_LIST);
        /*
        * 测试的数据
        * */
    }

    @Override
    public void findVillagename(List<VillageNameBean> list) {
        QU_LIST =list;
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
