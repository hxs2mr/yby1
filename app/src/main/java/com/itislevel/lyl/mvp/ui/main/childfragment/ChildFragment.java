package com.itislevel.lyl.mvp.ui.main.childfragment;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.itislevel.lyl.R;
import com.itislevel.lyl.adapter.CfChildFragmentAdapter;
import com.itislevel.lyl.adapter.DynamicFindAdapter;
import com.itislevel.lyl.app.Constants;
import com.itislevel.lyl.mvp.model.bean.CFChildBean;
import com.itislevel.lyl.mvp.model.bean.CFPinBean;
import com.itislevel.lyl.mvp.ui.main.cwebfragactivity.CWebActivity;
import com.itislevel.lyl.mvp.ui.main.dynamic.childfragment.RootCancleFragment;
import com.itislevel.lyl.mvp.ui.main.home.notice.ENoticeView;
import com.itislevel.lyl.mvp.ui.main.home.notice.NoticeAdapter;
import com.itislevel.lyl.mvp.ui.user.LoginActivity;
import com.itislevel.lyl.utils.ActivityUtil;
import com.itislevel.lyl.utils.GsonUtil;
import com.itislevel.lyl.utils.SAToast;
import com.itislevel.lyl.utils.SharedPreferencedUtils;
import com.itislevel.lyl.utils.ToastUtil;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import cn.finalteam.rxgalleryfinal.imageloader.GlideImageLoader1;

import static com.itislevel.lyl.mvp.ui.main.customer.CustomerFragment.mPostion;

/**
 * Created by Administrator on 2018\5\16 0016.
 */

public class ChildFragment extends RootCancleFragment<ChildPresenter>implements ChildContract.View, BaseQuickAdapter.OnItemClickListener, SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener{

    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;

    @BindView(R.id.refreshLayout)
    SwipeRefreshLayout refreshLayout;

    private int load_more =0;
    private int pageIndex = 1;//分页加载时 当前页是第几页
    private int totalPage = 0;//总页数
    private boolean isRefreshing = true;//当前是刷新还是加载

    private CfChildFragmentAdapter adapter;
    private boolean isReady = false;
    private boolean isLoaded = false;//防止重复加载
    private  View head_view = null;
    public  Banner banner = null;
    private List<CFChildBean> list;
    private  int flage =0;
    private   List<CFChildBean.LunboBean> lunbo;
    private ENoticeView cf_eNoticeView;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = this.getArguments();
        if(bundle != null){
            flage = bundle.getInt("flage");
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.custonerchild_fragment;
    }
    @Override
    protected void initEventAndData() {
        list = new ArrayList<>();
        initAdapter();
        refreshLayout.setOnRefreshListener(this);
        refreshLayout.setRefreshing(true);//刷新效果
        isReady=true;
        lazyLoad();//去除ViewPager预加载

        //
  /*      WebFragmentImpl webFragment = WebFragmentImpl.create("")//html地址
        webFragment.*/

    }

    private void initAdapter() {
        head_view = View.inflate(getContext(),R.layout.cf_child_header,null);
        banner = head_view.findViewById(R.id.cf_banner);//头部的banner图
        cf_eNoticeView = head_view.findViewById(R.id.cf_noticeview);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        // ((SimpleItemAnimator)recyclerView.getItemAnimator()).setSupportsChangeAnimations(false);
        recyclerview.setLayoutManager(manager);
        //注册成为订阅者
        refreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright, android.R.color.holo_orange_dark, android.R.color.holo_red_dark);
        if (adapter == null){
            adapter = new CfChildFragmentAdapter(R.layout.cf_childitem, getActivity());
            adapter.setOnItemClickListener(this);
            adapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_BOTTOM); //切换动画
            if(flage==0)
            {
               adapter.addHeaderView(head_view);
            }
            adapter.setEnableLoadMore(false);
            recyclerview.setAdapter(adapter);
            View emptyView = View.inflate(getContext(), R.layout.partial_empty_view, null);
            adapter.setEmptyView(emptyView);
        }
        /*
        * 测试的数据
        * */
    }

    /*
    *      this.title = title;
        this.user = user;
        this.time = time;
        this.pin = pin;
        this.zan = zan;
        this.image = image;
    * */
    private void LoadData() {
        Map<String,String> request = new HashMap<>();
        request.put("token", SharedPreferencedUtils.getStr(Constants.USER_TOKEN));
        request.put("usernum", SharedPreferencedUtils.getStr(Constants.USER_NUM));
        request.put("cateid",flage+"");
        if(flage != 0)
        {
            request.put("cateid",flage+"");
        }else {
            request.put("cateid","-1");
        }
        request.put("pageIndex", pageIndex+"");
        request.put("pageSize", Constants.PAGE_NUMBER10 + "");
        mPresenter.fristload(GsonUtil.obj2JSON(request));
    }

    private void showBanner(Banner home_banner, ArrayList<String> bannerImages)
    {
        home_banner.setImageLoader(new GlideImageLoader1());
        home_banner.setBannerStyle(BannerConfig.NOT_INDICATOR);//设置banner样式
        home_banner.setImages(bannerImages);//设置图片集合
        home_banner.setBannerAnimation(Transformer.Stack);//设置banner动画效果
        home_banner.isAutoPlay(true);//设置自动轮播，默认为true
        home_banner.setDelayTime(3000);//设置轮播时间
        home_banner.setIndicatorGravity(BannerConfig.CENTER); //设置指示器位置（当banner模式中有指示器时）
        home_banner.start();//banner设置方法全部调用完毕时最后调用
    }
    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        Bundle bundle = new Bundle();
        bundle.putString("url",this.adapter.getData().get(position).getFlat_info_url());
        bundle.putString("id",this.adapter.getData().get(position).getId()+"");
        bundle.putString("pointnum",this.adapter.getData().get(position).getPointnum()+"");
        bundle.putString("nosense",this.adapter.getData().get(position).getNosense()+"");
        bundle.putString("looknum",this.adapter.getData().get(position).getLooknum()+"");
        bundle.putString("commentnum",this.adapter.getData().get(position).getCommentnum()+"");
        bundle.putString("descript",this.adapter.getData().get(position).getTitle()+"");
        bundle.putString("share_image",this.adapter.getData().get(position).getLogo()+"");
        bundle.putString("publisher",this.adapter.getData().get(position).getPublisher()+"");
        ActivityUtil.getInstance().openActivity(getActivity(), CWebActivity.class,bundle);
    }
    @Override
    protected void lazyLoad() {
        if (!isReady || !isVisible || isLoaded) {
            return;
        }
        isLoaded = true;
        LoadData();
    }

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }
    @Override
    public void fristload(CFChildBean bean) {
        lunbo= bean.getLunbo();
        load_more++;
        if(load_more==1)
        {
            adapter.setOnLoadMoreListener(this,recyclerview);//设置加载更多
        }
        //返回的数据 第一次
        if ( bean.getPageBean().getList() == null || bean.getPageBean().getList().size() <= 0) {
            View emptyView = View.inflate(getContext(), R.layout.partial_empty_view, null);
            TextView viewById = emptyView.findViewById(R.id.tv_message);
            adapter.setEmptyView(emptyView);
        }
        totalPage = bean.getPageBean().getTotalPage();
        if (isRefreshing) {
            adapter.getData().clear();
            adapter.setNewData(bean.getPageBean().getList());
            adapter.notifyDataSetChanged();
            refreshLayout.setRefreshing(false);
        } else {
            adapter.addData(bean.getPageBean().getList());
            adapter.loadMoreComplete();
        }

        ArrayList<String> images = new ArrayList<>();
        int size = lunbo.size();
        for (int i =0 ; i < size;i++)
        {
            images.add(Constants.IMG_SERVER_PATH+lunbo.get(i).getLogo());
        }
        showBanner(banner,images);
        onClick_notice();
    }

    @Override
    public void updatepointnum(Integer msg) {

    }

    @Override
    public void looknumFlatcount(String data) {

    }

    @Override
    public void addFlatComment(String action) {//添加评论成功

    }

    @Override
    public void cfcommentlist(CFPinBean bean) {

    }

    @Override
    public void cf_addzan(String data) {

    }

    @Override
    public void delFlatComment(String action) {

    }

    private void onClick_notice() {
        //今日头条滚动模块
        cf_eNoticeView.setAdapter(new NoticeAdapter(){
            @Override
            public int getCount() {
                return lunbo.size();
            }
            @Override
            public View getView(Context context, int position) {
                View view = View.inflate(context, R.layout.cf_notice_item, null);
                ((AppCompatTextView) view.findViewById(R.id.cf_item_notice)).setText(lunbo.get(position).getName());
                return view;
            }
        });
    /*    cf_eNoticeView.setOnItemClickListener(new ENoticeView.OnItemClickListener() {
            @Override
            public void onClick(int position) {

                Toast.makeText(mContext,"点击了"+position,Toast.LENGTH_SHORT).show();
            }
        });*/
    }

    @Override
    public void useNightMode(boolean isNight) {

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
        super.stateError();
        ToastUtil.Info(e.getMessage());
        refreshLayout.setRefreshing(false);
    }

    @Override
    public void onRefresh() {//刷新操作
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

    public  static ChildFragment newInstance(int num) {
        ChildFragment f = new ChildFragment();
        // Supply num input as an argument.
        Bundle args = new Bundle();
        args.putInt("flage", num);
        f.setArguments(args);
        return f;
    }
}
