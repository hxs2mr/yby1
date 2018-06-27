package com.itislevel.lyl.mvp.ui.property.childfragment.historicalbill;

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
import com.itislevel.lyl.adapter.PropretyBillAdapter;
import com.itislevel.lyl.adapter.PropretyHistoricalBillAdapter;
import com.itislevel.lyl.adapter.SelectDanAdapter;
import com.itislevel.lyl.app.Constants;
import com.itislevel.lyl.base.RootActivity;
import com.itislevel.lyl.mvp.model.bean.HistoricalBean;
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

import static com.itislevel.lyl.mvp.ui.main.home.HomeFragment.CITY_ID;

/**
 * Created by Administrator on 2018\6\20 0020.
 */

public class HistoricalBillActivity extends RootActivity<HistoricalPresenter>implements HistoricalbillContract.View, BaseQuickAdapter.RequestLoadMoreListener, SwipeRefreshLayout.OnRefreshListener {
    @BindView(R.id.refreshLayout)
    SwipeRefreshLayout refreshLayout;

    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;

    @BindView(R.id.p_p_back)
    LinearLayoutCompat p_p_back;

    @BindView(R.id.select_qu_linear)
    LinearLayoutCompat select_qu_linear;

    @BindView(R.id.gray_layout)
    View gray_layout;

    @BindView(R.id.select_qu_im)
    AppCompatImageView select_qu_im;

    @BindView(R.id.qu_name)
    AppCompatTextView qu_name;

    private String user_number="";
    public static String user_number_livaddress="";
    private PropretyHistoricalBillAdapter adapter;
    private int pageIndex = 1;//分页加载时 当前页是第几页
    private int totalPage = 0;//总页数
    private boolean isRefreshing = true;//当前是刷新还是加载
    private PopupWindow POPU;
    private SelectDanAdapter Qu_Adapter;
    private List<LiveAddressBean> LD_LIST;
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
        return R.layout.activity_historicalbill;
    }
    @Override
    protected void initEventAndData() {
        initAdapter();
        LD_LIST = new ArrayList<>();
        refreshLayout.setOnRefreshListener(this);
        refreshLayout.setRefreshing(true);//刷新效果
        LoadData(user_number);
        loadDanData();
    }

    private void LoadData(String number) {
        Map<String, Object> request = new HashMap<>();
        request.put("token", SharedPreferencedUtils.getStr("p_token",""));
        request.put("phone", SharedPreferencedUtils.getStr("proprety_phone",""));
        request.put("vid", SharedPreferencedUtils.getStr("vid",""));
        request.put("usernum",number);
        request.put("pageIndex", pageIndex+"");
        request.put("pageSize", Constants.PAGE_NUMBER10 +"");
        mPresenter.querybillrecord(GsonUtil.obj2JSON(request));
    }

    private void initAdapter() {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        // ((SimpleItemAnimator)recyclerView.getItemAnimator()).setSupportsChangeAnimations(false);
        recyclerView.setLayoutManager(manager);
        if (adapter == null){
            adapter = new PropretyHistoricalBillAdapter(R.layout.item_historical, this);
            adapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN); //切换动画
            adapter.setOnLoadMoreListener(this,recyclerView);
            adapter.setEnableLoadMore(false);
            recyclerView.setAdapter(adapter);
            View emptyView = View.inflate(this, R.layout.partial_empty_view, null);
            adapter.setEmptyView(emptyView);
        }
    }

    @OnClick({R.id.p_p_back,R.id.select_qu_linear})
    public void onclick(View view)
    {
        switch (view.getId())
        {
            case R.id.p_p_back:
                ActivityUtil.getInstance().closeActivity(this);
                break;
            case R.id.select_qu_linear:
                gray_layout.setVisibility(View.VISIBLE);
                POPU = showTipPopupWindow(select_qu_linear);
                RotateAnimation animation = new RotateAnimation(0, 180, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                animation.setDuration(350);
                animation.setFillAfter(true);
                select_qu_im.startAnimation(animation);
                break;
        }
    }
    @Override
    public void querybillrecord(HistoricalBean bean) {
        totalPage = bean.getTotalPage();
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
    public void findLiveaddress(List<LiveAddressBean> list) {
        LD_LIST = list;
        LiveAddressBean  bean = new LiveAddressBean();
        bean.setUsernum("");
        bean.setLiveaddress("全部");
        LD_LIST.add(bean);
    }

    public PopupWindow showTipPopupWindow(final View anchorView) {
        final View contentView = LayoutInflater.from(anchorView.getContext()).inflate(R.layout.select_dan_popu, null);
        contentView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        RecyclerView recyclerView = contentView.findViewById(R.id.select_recycle);
        initAdapter_Qu(recyclerView);
        final PopupWindow popupWindow = new PopupWindow(contentView, contentView.getMeasuredWidth(), contentView.getMeasuredHeight(), false);

        recyclerView.setOnClickListener(new View.OnClickListener() {
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
    private void initAdapter_Qu(RecyclerView select_recycle){
        LinearLayoutManager manager = new LinearLayoutManager(this);
        // ((SimpleItemAnimator)recyclerView.getItemAnimator()).setSupportsChangeAnimations(false);
        select_recycle.setLayoutManager(manager);
        Qu_Adapter = new SelectDanAdapter(R.layout.select_dan_litem,this);
        Qu_Adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                POPU.dismiss();
                user_number_livaddress =  Qu_Adapter.getData().get(position).getLiveaddress();
                if(user_number_livaddress.equals("全部"))
                {
                    user_number="";
                }else {
                    user_number = Qu_Adapter.getData().get(position).getUsernum();
                }
                refreshLayout.setRefreshing(true);
                pageIndex = 1;
                isRefreshing = true;
                LoadData(user_number);
                qu_name.setText(Qu_Adapter.getData().get(position).getLiveaddress());
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
        Qu_Adapter.setNewData(LD_LIST);
        /*
        * 测试的数据
        * */
    }
    private void loadDanData(){
        Map<String, Object> request1 = new HashMap<>();
        request1.put("token", SharedPreferencedUtils.getStr("p_token",""));
        request1.put("phone", SharedPreferencedUtils.getStr("proprety_phone",""));
        request1.put("vid", SharedPreferencedUtils.getStr("vid",""));
        mPresenter.findLiveaddress(GsonUtil.obj2JSON(request1));
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
            LoadData(user_number);
        }
    }

    @Override
    public void onRefresh() {
        refreshLayout.setRefreshing(true);
        pageIndex = 1;
        isRefreshing = true;
        LoadData(user_number);
    }
}
