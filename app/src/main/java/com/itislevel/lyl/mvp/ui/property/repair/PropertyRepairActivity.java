package com.itislevel.lyl.mvp.ui.property.repair;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.itislevel.lyl.R;
import com.itislevel.lyl.adapter.DynamicFindAdapter;
import com.itislevel.lyl.adapter.RepairListAdapter;
import com.itislevel.lyl.adapter.RepairPopuAdapter;
import com.itislevel.lyl.adapter.SelectQuAdapter;
import com.itislevel.lyl.app.Constants;
import com.itislevel.lyl.base.RootActivity;
import com.itislevel.lyl.mvp.model.bean.CatelistBean;
import com.itislevel.lyl.mvp.model.bean.ProperCommentList;
import com.itislevel.lyl.mvp.model.bean.RepairCityListBean;
import com.itislevel.lyl.mvp.model.bean.RepairListBean;
import com.itislevel.lyl.mvp.model.bean.RepairTypeListBean;
import com.itislevel.lyl.mvp.model.bean.SeleBean;
import com.itislevel.lyl.net.LoaderStyle;
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

import static com.itislevel.lyl.mvp.ui.property.PropertyHomeActvity.location_city_id;
import static com.itislevel.lyl.mvp.ui.property.PropertyHomeActvity.location_city_name;

/**
 * Created by Administrator on 2018\6\1 0001.
 */

public class PropertyRepairActivity extends RootActivity<RepairPresenter>implements RepairContract.View, BaseQuickAdapter.OnItemChildClickListener, BaseQuickAdapter.OnItemClickListener, SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {

    @BindView(R.id.repair_location)
    LinearLayoutCompat repair_location;

    @BindView(R.id.loaction_name)
    AppCompatTextView loaction_name;

    @BindView(R.id.location_image)
    AppCompatImageView location_image;

    @BindView(R.id.lei_linear)
    LinearLayoutCompat lei_linear;

    @BindView(R.id.lei_name)
    AppCompatTextView lei_name;

    @BindView(R.id.lei_image)
    AppCompatImageView lei_image;


    @BindView(R.id.refreshLayout)
    SwipeRefreshLayout refreshLayout;

    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;

    @BindView(R.id.gray_layout)
    View  gray_layout;

    private RepairListAdapter adapter;
    private PopupWindow POPU;
    private RepairPopuAdapter Popu_Adapter;
    private List<RepairCityListBean> list_city;
    private List<RepairCityListBean> list_type;
    private String city_id;
    private int pageIndex = 1;//分页加载时 当前页是第几页
    private int totalPage = 0;//总页数
    private boolean isRefreshing = true;//当前是刷新还是加载
    public static int select_type=0;
    public static  String loaction_name_s="";
    public static  String lei_name_s="";
    private String s_cityid="";
    private String s_leiid="";
    public static List<CatelistBean> start_catelist;
    //    @Override
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
        return R.layout.activity_repair;
    }

    @Override
    protected void initEventAndData() {
        list_city = new ArrayList<>();
        list_type = new ArrayList<>();
        loaction_name.setText("全"+location_city_name);
        city_id = location_city_id;
        initAdapter();
        refreshLayout.setOnRefreshListener(this);
        refreshLayout.setRefreshing(true);//刷新效果
        loadDatalist("","");//获取维修工人的列表
        loadDataCITY();
        loadDataType();
    }

    private void loadDataCITY() {
        Map<String, Object> request = new HashMap<>();
        request.put("token", SharedPreferencedUtils.getStr("p_token",""));
        request.put("phone", SharedPreferencedUtils.getStr("proprety_phone",""));
        request.put("cityid",city_id);
        mPresenter.queryarealist(GsonUtil.obj2JSON(request));
    }

    private void loadDataType() {
        Map<String, Object> request = new HashMap<>();
        request.put("token", SharedPreferencedUtils.getStr("p_token",""));
        request.put("phone", SharedPreferencedUtils.getStr("proprety_phone",""));
        mPresenter.queryrepairallcatelist(GsonUtil.obj2JSON(request));
    }

    private void loadDatalist(String countyid,String recateids) {
        Map<String, Object> request = new HashMap<>();
        request.put("token", SharedPreferencedUtils.getStr("p_token",""));
        request.put("phone", SharedPreferencedUtils.getStr("proprety_phone",""));
        request.put("cityid",city_id);
        request.put("countyid",countyid);
        request.put("recateids",recateids);
        request.put("pageIndex", pageIndex + "");
        request.put("pageSize", Constants.PAGE_NUMBER10 + "");
        System.out.println("JSON*(***********"+GsonUtil.obj2JSON(request));
        mPresenter.maintenanceList(GsonUtil.obj2JSON(request));
    }

    /**
     * 初始化adapter
     */
    private void initAdapter() {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        ((SimpleItemAnimator)recyclerView.getItemAnimator()).setSupportsChangeAnimations(false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setHasFixedSize(true);
        //注册成为订阅者
        refreshLayout.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_orange_dark,
                android.R.color.holo_red_dark);
        if (adapter == null){
            adapter = new RepairListAdapter(R.layout.item_repair, this);
            adapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_BOTTOM); //切换动画
            adapter.setOnLoadMoreListener(this,recyclerView);//设置加载更多
            adapter.setEnableLoadMore(false);
            adapter.setOnItemChildClickListener(this);
            adapter.setOnItemClickListener(this);
         //   adapter.isFirstOnly(false);//动画默认只执行一次
            recyclerView.setAdapter(adapter);
            View emptyView = View.inflate(this, R.layout.partial_empty_view, null);
            adapter.setEmptyView(emptyView);
        }
    }

    @OnClick({R.id.p_p_back,R.id.repair_location,R.id.lei_linear})
    public void  Onclick(View view){
        switch (view.getId())
        {
            case R.id.p_p_back:
                ActivityUtil.getInstance().closeActivity(this);
                break;
            case R.id.repair_location://选择城市
                select_type= 0;
                gray_layout.setVisibility(View.VISIBLE);
                POPU = showTipPopupWindow(repair_location,1);
                RotateAnimation animation = new RotateAnimation(0, 180, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                animation.setDuration(350);
                animation.setFillAfter(true);
                location_image.startAnimation(animation);
                location_image.setBackgroundResource(R.mipmap.location_xia);
                loaction_name.setTextColor(Color.parseColor("#ff7a00"));
                if(list_city.size()<=0)
                {
                    loadDataCITY();
                }else {
                    Popu_Adapter.getData().clear();
                    Popu_Adapter.setNewData(list_city);
                }
                break;
            case R.id.lei_linear://选择类别
                select_type= 1;
                gray_layout.setVisibility(View.VISIBLE);
                POPU = showTipPopupWindow(lei_linear,2);
                RotateAnimation animation1 = new RotateAnimation(0, 180, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                animation1.setDuration(350);
                animation1.setFillAfter(true);
                lei_image.startAnimation(animation1);
                lei_image.setBackgroundResource(R.mipmap.location_xia);
                lei_name.setTextColor(Color.parseColor("#ff7a00"));
                if(list_type.size()<=0)
                {
                    loadDataType();
                }else {
                    Popu_Adapter.getData().clear();
                    Popu_Adapter.setNewData(list_type);
                }
                break;
        }

    }

    public PopupWindow showTipPopupWindow(final View anchorView,int flage) {

        final View contentView = LayoutInflater.from(anchorView.getContext()).inflate(R.layout.repaot_popu, null);
        contentView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        RecyclerView recyclerView = contentView.findViewById(R.id.popu_recycleview);
        initAdapter_popu(recyclerView);
        final PopupWindow popupWindow = new PopupWindow(contentView);
        popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
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
        popupWindow.setAnimationStyle(R.style.CustomPopWindowStyle);
        popupWindow.setBackgroundDrawable(this.getResources().getDrawable(R.color.transparent));
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                RotateAnimation animation = new RotateAnimation(180, 0, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                animation.setDuration(350);
                animation.setFillAfter(true);
                if(flage==1)
                {
                    location_image.startAnimation(animation);
                    location_image.setBackgroundResource(R.mipmap.location_xia_no);
                    loaction_name.setTextColor(Color.parseColor("#282828"));
                }else {
                    lei_image.startAnimation(animation);
                    lei_image.setBackgroundResource(R.mipmap.location_xia_no);
                    lei_name.setTextColor(Color.parseColor("#282828"));
                }
                gray_layout.setVisibility(View.GONE);
            }
        });
        // 如果希望showAsDropDown方法能够在下面空间不足时自动在anchorView的上面弹出
        // 必须在创建PopupWindow的时候指定高度，不能用wrap_content
        popupWindow.showAsDropDown(anchorView);
        return popupWindow;
    }
    private void initAdapter_popu(RecyclerView select_recycle ) {

        LinearLayoutManager manager = new LinearLayoutManager(this);
        // ((SimpleItemAnimator)recyclerView.getItemAnimator()).setSupportsChangeAnimations(false);
        select_recycle.setLayoutManager(manager);
        Popu_Adapter = new RepairPopuAdapter(R.layout.item_city_type,this);
        Popu_Adapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_BOTTOM); //切换动画
        Popu_Adapter.setEnableLoadMore(false);
        Popu_Adapter.setOnItemClickListener(new PopuOnclickItemlisenter());
        select_recycle.setAdapter(Popu_Adapter);
        /*
        * 测试的数据
        * */
    }

    @Override
    public void stateError(Throwable e) {
            super.stateError(e);
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
        //ActivityUtil.getInstance().openActivity(this,RepairDetailActivity.class);
        if(view.getId()==R.id.call_phone){
            show_call(this.adapter.getData().get(position).getMobilephone());
        }
    }
    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        Bundle bundle = new Bundle();
        if(view.getId()!=R.id.call_phone)
        {
            start_catelist = this.adapter.getData().get(position).getCatelist();
            bundle.putLong("createtime",this.adapter.getData().get(position).getCreatedtime());
            bundle.putString("looknum",this.adapter.getData().get(position).getLooknum()+"");
            bundle.putString("headurl",this.adapter.getData().get(position).getHeadurl());
            bundle.putString("username",this.adapter.getData().get(position).getRealname());
            bundle.putString("modelphone",this.adapter.getData().get(position).getMobilephone());
            bundle.putString("servicearea",this.adapter.getData().get(position).getServicearea());
            bundle.putString("catelist",GsonUtil.obj2JSON(this.adapter.getData().get(position).getCatelist()));

            bundle.putString("address",this.adapter.getData().get(position).getAddress()+this.adapter.getData().get(position).getCuntyname());
            bundle.putString("workrem",this.adapter.getData().get(position).getWorkrem());
            bundle.putString("rwid",this.adapter.getData().get(position).getRwid()+"");
            bundle.putString("score",this.adapter.getData().get(position).getScoreAverage()+"");
            bundle.putString("noStoreUp",this.adapter.getData().get(position).getNoStoreUp()+"");
            bundle.putString("share_url",this.adapter.getData().get(position).getRepair_share_url()+"");
            ActivityUtil.getInstance().openActivity(this,RepairDetailActivity.class,bundle);
        }
    }

    @Override
    public void maintenanceList(RepairListBean bean) {
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
    public void queryarealist(List<RepairCityListBean> bean) {//获取到的城市列表
        RepairCityListBean bean1  = new RepairCityListBean();
        bean1.setId(Integer.parseInt(city_id));
        bean1.setS_name("全"+location_city_name);
        list_city = bean;
        list_city.add(0,bean1);
        Popu_Adapter.getData().clear();
        Popu_Adapter.setNewData(bean);
    }

    @Override
    public void queryrepairallcatelist(List<RepairCityListBean> bean) {//获取到的列表列表
            list_type = bean;
        Popu_Adapter.getData().clear();
        Popu_Adapter.setNewData(bean);
    }

    @Override
    public void commentEstatesList(ProperCommentList bean) {

    }

    @Override
    public void addCommentEstates(String data) {

    }

    @Override
    public void addCollectMaintenance(String action) {

    }

    @Override
    public void seleCommentConunt(SeleBean bean) {

    }

    @Override
    public void prolooknumcount(String data) {

    }

    class PopuOnclickItemlisenter implements BaseQuickAdapter.OnItemClickListener{
        @Override
        public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if(select_type==0)
                {
                    s_cityid = Popu_Adapter.getData().get(position).getId()+"";
                    loaction_name.setText(""+Popu_Adapter.getData().get(position).getS_name());
                    loaction_name_s =Popu_Adapter.getData().get(position).getS_name();

                    if(loaction_name_s.contains("全"))
                    {
                        s_cityid="";
                    }
                }else {
                    s_leiid = Popu_Adapter.getData().get(position).getId()+"";
                    lei_name.setText(Popu_Adapter.getData().get(position).getCatename());
                    lei_name_s =Popu_Adapter.getData().get(position).getCatename();
                }
            RotateAnimation animation = new RotateAnimation(180, 0, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            animation.setDuration(350);
            animation.setFillAfter(true);
            if(select_type==0)
            {
                location_image.startAnimation(animation);
                location_image.setBackgroundResource(R.mipmap.location_xia_no);
                loaction_name.setTextColor(Color.parseColor("#282828"));
            }else {
                lei_image.startAnimation(animation);
                lei_image.setBackgroundResource(R.mipmap.location_xia_no);
                lei_name.setTextColor(Color.parseColor("#282828"));
            }
            gray_layout.setVisibility(View.GONE);
            POPU.dismiss();
            pageIndex =1;
            refreshLayout.setRefreshing(true);//刷新效果
            isRefreshing = true;
            loadDatalist(s_cityid,s_leiid);
        }

    }
    @Override
    public void onRefresh() {
        refreshLayout.setRefreshing(true);//刷新效果
        pageIndex = 1;
        isRefreshing = true;
        loadDatalist("","");
    }

    public void show_call(String phone){
        //实例化建造者
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //设置警告对话框的标题
        //设置警告显示的图片
//        builder.setIcon(android.R.drawable.ic_dialog_alert);
        //设置警告对话框的提示信息
        builder.setMessage("拨打"+phone);
        //设置”正面”按钮，及点击事件
        builder.setPositiveButton("呼叫", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                Uri data = Uri.parse("tel:" +phone);
                intent.setData(data);
                startActivity(intent);
            }
        });
        //设置“中立”按钮，及点击事件
        builder.setNeutralButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        //显示对话框
        builder.show();
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
            loadDatalist("","");
        }
    }
}
