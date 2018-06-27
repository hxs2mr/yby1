package com.itislevel.lyl.mvp.ui.property.repair;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatRatingBar;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.itislevel.lyl.R;
import com.itislevel.lyl.adapter.CFAllPinAdapter;
import com.itislevel.lyl.adapter.ReAllPinAdapter;
import com.itislevel.lyl.app.Constants;
import com.itislevel.lyl.base.RootActivity;
import com.itislevel.lyl.mvp.model.bean.ProperCommentList;
import com.itislevel.lyl.mvp.model.bean.RepairCityListBean;
import com.itislevel.lyl.mvp.model.bean.RepairListBean;
import com.itislevel.lyl.mvp.model.bean.SeleBean;
import com.itislevel.lyl.mvp.ui.main.cwebfragactivity.CWebAllPinActivity;
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
 * Created by Administrator on 2018\6\8 0008.
 */

public class RepairAllPinActivity extends RootActivity<RepairPresenter>implements RepairContract.View, BaseQuickAdapter.RequestLoadMoreListener {

    @BindView(R.id.total_fen)
    AppCompatTextView  total_fen;
    @BindView(R.id.ratingBar)
    AppCompatRatingBar ratingBar;

    @BindView(R.id.pin_all)
    AppCompatTextView  pin_all;

    @BindView(R.id.pin_z)
    AppCompatTextView  pin_z;

    @BindView(R.id.pin_h)
    AppCompatTextView  pin_h;

    @BindView(R.id.pin_zhon)
    AppCompatTextView  pin_zhon;

    @BindView(R.id.pin_zun)
    AppCompatTextView  pin_zun;

    @BindView(R.id.pin_c)
    AppCompatTextView  pin_c;

    @BindView(R.id.cha_lienar)
    LinearLayoutCompat cha_lienar;

    @BindView(R.id.pin_xia_linear)
    LinearLayoutCompat pin_xia_linear;

    @BindView(R.id.pin_xia_im)
    AppCompatImageView pin_xia_im;

    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;

    private int load_more =0;
    private int pageIndex = 1;//分页加载时 当前页是第几页
    private int totalPage = 0;//总页数
    private boolean isRefreshing = true;//当前是刷新还是加载

    private ReAllPinAdapter adapter;
    private String rwid="";
    private Bundle bundle;

    private String score="";
    private int mHiddenViewMeasuredHeight;
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
        return R.layout.activity_reallpin;
    }

    @Override
    protected void initEventAndData() {
        bundle=  getIntent().getExtras();
        rwid = bundle.getString("rwid");
        initAdapter();
        loadDataSele();//获取标题个数
        loadData(score);//获取全部
    }

    private void initAdapter()
    {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        // ((SimpleItemAnimator)recyclerView.getItemAnimator()).setSupportsChangeAnimations(false);
        recyclerview.setLayoutManager(manager);
        if (adapter == null){
            adapter = new ReAllPinAdapter(R.layout.item_reallpin, this);
            adapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_BOTTOM); //切换动画
            adapter.setEnableLoadMore(false);
            adapter.setOnLoadMoreListener(this,recyclerview);//设置加载更多
            recyclerview.setAdapter(adapter);
            View emptyView = View.inflate(this, R.layout.partial_empty_view, null);
            adapter.setEmptyView(emptyView);
        }
    }

    private void loadDataSele()
    {
        Map<String, Object> request = new HashMap<>();
        request.put("token", SharedPreferencedUtils.getStr("p_token",""));
        request.put("phone", SharedPreferencedUtils.getStr("proprety_phone",""));
        request.put("rwid", rwid);
        mPresenter.seleCommentConunt(GsonUtil.obj2JSON(request));
    }
    private void loadData(String score)
    {
        Map<String, Object> request = new HashMap<>();
        request.put("token", SharedPreferencedUtils.getStr("p_token",""));
        request.put("phone", SharedPreferencedUtils.getStr("proprety_phone",""));
        request.put("rwid", rwid);
        request.put("score", score);
        request.put("pageIndex", pageIndex+"");
        request.put("pageSize", Constants.PAGE_NUMBER10+"");
        mPresenter.commentEstatesList(GsonUtil.obj2JSON(request));
    }

    @OnClick({R.id.pin_all,R.id.pin_z,R.id.pin_h,R.id.pin_zhon,R.id.pin_zun,R.id.pin_c,R.id.p_p_back,R.id.pin_xia_linear})
    public void onclick(View view)
    {   pageIndex = 1;
        switch (view.getId())
        {
            case R.id.pin_all://全部
                isRefreshing= true;
                initback();
                score ="";
                pin_all.setBackgroundResource(R.drawable.reallpin_shape_yes);
                pin_all.setTextColor(Color.parseColor("#ffffff"));
                loadData(score);
                break;
            case R.id.pin_z://专业
                isRefreshing= true;
                initback();
                score ="10";
                pin_z.setBackgroundResource(R.drawable.reallpin_shape_yes);
                pin_z.setTextColor(Color.parseColor("#ffffff"));
                loadData(score);
                break;
            case R.id.pin_h://好评
                isRefreshing= true;
                initback();
                score ="8";
                pin_h.setBackgroundResource(R.drawable.reallpin_shape_yes);
                pin_h.setTextColor(Color.parseColor("#ffffff"));
                loadData(score);
                break;
            case R.id.pin_zhon://中评
                isRefreshing= true;
                initback();
                score ="6";
                pin_zhon.setBackgroundResource(R.drawable.reallpin_shape_yes);
                pin_zhon.setTextColor(Color.parseColor("#ffffff"));
                loadData(score);
                break;
            case R.id.pin_zun:
                isRefreshing= true;
                initback();
                score ="4";
                pin_zun.setBackgroundResource(R.drawable.reallpin_shape_yes);
                pin_zun.setTextColor(Color.parseColor("#ffffff"));
                loadData(score);
                break;
            case R.id.pin_c://差评
                isRefreshing= true;
                initback();
                score ="2";
                pin_c.setBackgroundResource(R.drawable.reallpin_shape_yes);
                pin_c.setTextColor(Color.parseColor("#ffffff"));
                loadData(score);
                break;
            case R.id.p_p_back:
                ActivityUtil.getInstance().closeActivity(this);
                break;
            case R.id.pin_xia_linear:
                cha_lienar.measure(0,0);
                int height=cha_lienar.getMeasuredHeight();//获取组件的高度
                mHiddenViewMeasuredHeight =height;
                int durationMillis = 350;//动画持续时间
                if (cha_lienar.getVisibility()==View.VISIBLE) {
                    animateClose(cha_lienar);//关闭动画
                    RotateAnimation animation = new RotateAnimation(180, 0, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                    animation.setDuration(durationMillis);
                    animation.setFillAfter(true);
                    pin_xia_im.startAnimation(animation);
                } else {
                    animateOpen(cha_lienar);//打开动画
                    RotateAnimation animation = new RotateAnimation(0, 180, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                    animation.setDuration(durationMillis);
                    animation.setFillAfter(true);
                    pin_xia_im.startAnimation(animation);
                }
                break;
        }
    }
    private void initback()
    {
        pin_all.setBackgroundResource(R.drawable.reallpin_shape);
        pin_all.setTextColor(Color.parseColor("#333333"));

        pin_z.setBackgroundResource(R.drawable.reallpin_shape);
        pin_z.setTextColor(Color.parseColor("#333333"));

        pin_h.setBackgroundResource(R.drawable.reallpin_shape);
        pin_h.setTextColor(Color.parseColor("#333333"));

        pin_zhon.setBackgroundResource(R.drawable.reallpin_shape);
        pin_zhon.setTextColor(Color.parseColor("#333333"));

        pin_c.setBackgroundResource(R.drawable.reallpin_shape);
        pin_c.setTextColor(Color.parseColor("#333333"));

        pin_zun.setBackgroundResource(R.drawable.reallpin_shape);
        pin_zun.setTextColor(Color.parseColor("#333333"));
    }
    private void animateClose(final View view) {
        int origHeight = view.getHeight();
        ValueAnimator animator = createDropAnimator(view, origHeight, 0);
        animator.addListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animation) {
                // 动画结束时隐藏view
                view.setVisibility(View.GONE);
            }
        });
        animator.start();
    }

    @Override
    public void stateError() {
        super.stateError();
        View emptyView = View.inflate(this, R.layout.partial_empty_view, null);
        TextView viewById = emptyView.findViewById(R.id.tv_message);
        adapter.setEmptyView(emptyView);
    }

    @Override
    public void stateError(Exception e) {
        super.stateError(e);
        View emptyView = View.inflate(this, R.layout.partial_empty_view, null);
        TextView viewById = emptyView.findViewById(R.id.tv_message);
        adapter.setEmptyView(emptyView);
    }

    @Override
    public void stateError(Throwable e) {
        super.stateError(e);
        adapter.getData().clear();
        adapter.notifyDataSetChanged();
        View emptyView = View.inflate(this, R.layout.partial_empty_view, null);
        TextView viewById = emptyView.findViewById(R.id.tv_message);
        adapter.setEmptyView(emptyView);
    }

    @SuppressLint("NewApi")
    private void animateOpen(final View view) {
        view.setVisibility(View.VISIBLE);
        ValueAnimator animator = createDropAnimator(view, 0,
                mHiddenViewMeasuredHeight);
        animator.start();
    }

    private ValueAnimator createDropAnimator(final View view, int start, int end) {
        // 创建一个数值发生器
        ValueAnimator animator = ValueAnimator.ofInt(start, end);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int value = (Integer) valueAnimator.getAnimatedValue();
                ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
                layoutParams.height = value;
                view.setLayoutParams(layoutParams);
            }
        });
        return animator;
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
    public void maintenanceList(RepairListBean bean) {

    }

    @Override
    public void queryarealist(List<RepairCityListBean> bean) {

    }

    @Override
    public void queryrepairallcatelist(List<RepairCityListBean> bean) {

    }

    @Override
    public void commentEstatesList(ProperCommentList bean) {
        //返回的数据 第一次
        if ( bean.getPageBean() == null || bean.getPageBean().getList() == null || bean
                .getPageBean().getList().size() <= 0) {
            adapter.getData().clear();
            adapter.notifyDataSetChanged();
            View emptyView = View.inflate(this, R.layout.partial_empty_view, null);
            TextView viewById = emptyView.findViewById(R.id.tv_message);
            adapter.setEmptyView(emptyView);
            return;
        }
        totalPage = bean.getPageBean().getTotalPage();
        if (isRefreshing) {
            adapter.getData().clear();
            adapter.notifyDataSetChanged();
            adapter.setNewData(bean.getPageBean().getList());
        } else {
            adapter.addData(bean.getPageBean().getList());
            adapter.loadMoreComplete();
        }
    }

    @Override
    public void addCommentEstates(String data) {

    }

    @Override
    public void addCollectMaintenance(String action) {

    }

    @Override
    public void seleCommentConunt(SeleBean bean) {
            pin_all.setText("全部("+bean.getConunt()+")");
            pin_z.setText("专业("+bean.getConuntZY()+")");
            pin_h.setText("好评("+bean.getConuntHP()+")");
            pin_zhon.setText("中评("+bean.getConuntZP()+")");
            pin_zun.setText("准时("+bean.getConuntZS()+")");
            pin_c.setText("差评("+bean.getConuntCP()+")");
    }

    @Override
    public void prolooknumcount(String data) {

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
            loadData(score);
        }
    }
}
