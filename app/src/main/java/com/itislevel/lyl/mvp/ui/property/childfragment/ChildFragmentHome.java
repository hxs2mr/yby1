package com.itislevel.lyl.mvp.ui.property.childfragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.itislevel.lyl.R;
import com.itislevel.lyl.adapter.DynamicFindAdapter;
import com.itislevel.lyl.adapter.LiveAddressBean;
import com.itislevel.lyl.adapter.PropretyHomeAdapter;
import com.itislevel.lyl.adapter.recyclew.ItemType;
import com.itislevel.lyl.adapter.recyclew.MultipleItemEntity;
import com.itislevel.lyl.app.Constants;
import com.itislevel.lyl.base.RootFragment;
import com.itislevel.lyl.mvp.model.bean.PropertLoginBean;
import com.itislevel.lyl.mvp.model.bean.PropretyLiveBean;
import com.itislevel.lyl.mvp.model.bean.PropretyLoginSuccessBean;
import com.itislevel.lyl.mvp.model.bean.PropretyNoticeBean;
import com.itislevel.lyl.mvp.model.bean.VillageNameBean;
import com.itislevel.lyl.mvp.ui.blessing.BlessingHomeActivity;
import com.itislevel.lyl.mvp.ui.main.home.HomeModel.HomeFields;
import com.itislevel.lyl.mvp.ui.property.PropertLoginActivity;
import com.itislevel.lyl.mvp.ui.property.PropertyAllTonActivity;
import com.itislevel.lyl.mvp.ui.property.PropertyContract;
import com.itislevel.lyl.mvp.ui.property.PropertyPresenter;
import com.itislevel.lyl.mvp.ui.property.complaint.PropertyComplatintActivity;
import com.itislevel.lyl.mvp.ui.property.houselist.PropertyHouseListActivity;
import com.itislevel.lyl.mvp.ui.property.repair.PropertyRepairActivity;
import com.itislevel.lyl.mvp.ui.user.LoginActivity;
import com.itislevel.lyl.net.LatteLoader;
import com.itislevel.lyl.net.LoaderStyle;
import com.itislevel.lyl.utils.ActivityUtil;
import com.itislevel.lyl.utils.GsonUtil;
import com.itislevel.lyl.utils.SharedPreferencedUtils;
import com.itislevel.lyl.utils.ToastUtil;
import com.itislevel.lyl.utils.UtilsStyle;
import com.vondear.rxtools.model.Gps;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import cn.finalteam.rxgalleryfinal.imageloader.GlideImageLoader1;
import de.hdodenhof.circleimageview.CircleImageView;

import static com.itislevel.lyl.mvp.ui.main.home.HomeFragment.CITY_ID;
import static com.itislevel.lyl.utils.DateUtil.timeSpanToDate;

/**
 * Created by Administrator on 2018\5\22 0022.
 */

public class ChildFragmentHome extends RootFragment<PropertyPresenter>implements PropertyContract.View, View.OnClickListener, BaseQuickAdapter.OnItemChildClickListener, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;

    @BindView(R.id.refreshLayout)
    SwipeRefreshLayout refreshLayout;

    @BindView(R.id.p_home_back)
    LinearLayoutCompat p_home_back;

    private View Head_View;
    Banner banner;
    AppCompatTextView p_jiaofei;//缴费

    AppCompatTextView p_weixiu;//维修

    AppCompatTextView p_tou;//投诉


    private int load_more =0;
    private int pageIndex = 1;//分页加载时 当前页是第几页
    private int totalPage = 0;//总页数
    private boolean isRefreshing = true;//当前是刷新还是加载
    String vid ="";
    String  city_id="";
    private PropretyHomeAdapter adapter;
    private     List<MultipleItemEntity> home_data_null = null;
    boolean islogin;
    private PopupWindow DetailPopu;
    private PropretyNoticeBean No_Bean;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UtilsStyle.setStatusBarMode(getActivity(),true); //黑色的主题图标
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.childhome_fragment;
    }

    @Override
    protected void initEventAndData() {
        Head_View = View.inflate(getContext(),R.layout.proprety_item_head,null);
        initAdapter();
        LoadData();//获取广告及小区通知的数据
        p_jiaofei.setOnClickListener(this);
        p_weixiu.setOnClickListener(this);
        p_tou.setOnClickListener(this);
        refreshLayout.setOnRefreshListener(this);
        refreshLayout.setRefreshing(true);//刷新效果
    }
    /**
     * 初始化adapter
     */
    private void initAdapter() {
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(manager);
        //注册成为订阅者
        refreshLayout.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_orange_dark,
                android.R.color.holo_red_dark);
        if (adapter == null) {
            home_data_null = new ArrayList<>();
            banner = Head_View.findViewById(R.id.banner);
            p_jiaofei = Head_View.findViewById(R.id.p_jiaofei);
            p_weixiu = Head_View.findViewById(R.id.p_weixiu);
            p_tou= Head_View.findViewById(R.id.p_tou);
            adapter = PropretyHomeAdapter.create(home_data_null,getContext());
            adapter.setOnItemChildClickListener(this);
            adapter.addHeaderView(Head_View);
            recyclerView.setAdapter(adapter);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        banner.stopAutoPlay();
    }
    private void LoadData() {
        Map<String, Object> request = new HashMap<>();
        request.put("token", SharedPreferencedUtils.getStr("p_token",""));
        request.put("phone", SharedPreferencedUtils.getStr("proprety_phone",""));
        mPresenter.propretyLive(GsonUtil.obj2JSON(request));//获取广告

        Map<String, Object> request1 = new HashMap<>();
        request.put("token", SharedPreferencedUtils.getStr("p_token",""));
        request.put("phone", SharedPreferencedUtils.getStr("proprety_phone",""));
        request1.put("cityid",SharedPreferencedUtils.getStr("proprety_cityid",CITY_ID+""));
        request1.put("vid",SharedPreferencedUtils.getStr("vid",""));
        request1.put("pageIndex", pageIndex+"");
        request1.put("pageSize", Constants.PAGE_NUMBER10 +"");
        System.out.println("JSON数据****************************"+GsonUtil.obj2JSON(request1));
        mPresenter.noticeEstates(GsonUtil.obj2JSON(request1));//获取通知
    }

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }
    private void showBanner(Banner banner, ArrayList<String> bannerImages)
    {
        banner.setImageLoader(new GlideImageLoader1());
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);//设置banner样式
        banner.setImages(bannerImages);//设置图片集合
        banner.setBannerAnimation(Transformer.DepthPage);//设置banner动画效果
        banner.isAutoPlay(true);//设置自动轮播，默认为true
        banner.setDelayTime(3000);//设置轮播时间
        banner.setIndicatorGravity(BannerConfig.RIGHT); //设置指示器位置（当banner模式中有指示器时）
        banner.start();//banner设置方法全部调用完毕时最后调用
    }

    @OnClick({R.id.p_home_back})
    public void click(View view) {
        switch (view.getId())
        {
            case R.id.p_home_back:
                ActivityUtil.getInstance().closeActivity(getActivity());
                break;
        }
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
    public void getSSMCode(String action) {

    }

    @Override
    public void loginEstates(PropertLoginBean bean) {

    }

    @Override
    public void noticeEstates(PropretyNoticeBean bean) {//物业通知
        No_Bean = bean;
        home_data_null = new ArrayList<>();
        MultipleItemEntity entity = MultipleItemEntity.builder()
                .setItemType(ItemType.PROPRETY_ITEM)
                .setField(HomeFields.PROPRE_LIST,bean.getList())
                .build();
        home_data_null.add(entity);
        adapter.setNewData(home_data_null);
        refreshLayout.setRefreshing(false);
    }

    @Override
    public void propretyLive(PropretyLiveBean list) {//物业的广告
        int size = list.getAdvertlist().size();
        ArrayList<String> images = new ArrayList<>();
        for (int i = 0 ; i < size ; i++)
        {
            images.add(Constants.IMG_SERVER_PATH+list.getAdvertlist().get(i).getLogo());
        }
        showBanner(banner,images);
    }

    @Override
    public void findVillagename(List<VillageNameBean> list) {

    }

    @Override
    public void findLiveaddress(List<LiveAddressBean> list) {

    }

    @Override
    public void stateError(Throwable e) {
        super.stateError(e);
        ToastUtil.Error(e.getMessage());
        home_data_null.clear();
        home_data_null = new ArrayList<>();
        MultipleItemEntity entity = MultipleItemEntity.builder()
                .setItemType(ItemType.PROPRETY_NOITEM)
                .build();
        home_data_null.add(entity);
        adapter.setNewData(home_data_null);
        refreshLayout.setRefreshing(false);
    }

    @Subscribe
    public void Event(PropretyLoginSuccessBean bean)
    {
            LoadData();//登录成功后回传的事件
    }


    @Override
    public void onResume() {
        super.onResume();
        banner.start();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.p_jiaofei://在线缴费
                islogin = SharedPreferencedUtils.getBool("isproprety", false);
                if (!islogin) {
                    banner.stopAutoPlay();
                    ActivityUtil.getInstance().openActivity(getActivity(), PropertLoginActivity.class);
                    return;
                }else {
                    ActivityUtil.getInstance().openActivity(getActivity(), PropertyHouseListActivity.class);
                }
                break;
            case R.id.p_tou://投诉建议
                islogin = SharedPreferencedUtils.getBool("isproprety", false);
                if (!islogin) {
                    banner.stopAutoPlay();
                    ActivityUtil.getInstance().openActivity(getActivity(), PropertLoginActivity.class);
                    return;
                }else {
                    ActivityUtil.getInstance().openActivity(getActivity(), PropertyComplatintActivity.class);
                }
                break;
            case R.id.p_weixiu://维修
                islogin = SharedPreferencedUtils.getBool("isproprety", false);
                if (!islogin) {
                    banner.stopAutoPlay();
                    ActivityUtil.getInstance().openActivity(getActivity(), PropertLoginActivity.class);
                    return;
                }else {
                    ActivityUtil.getInstance().openActivity(getActivity(), PropertyRepairActivity.class);
                }

                break;
        }
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        switch (view.getId())
        {
            case R.id.p_all_ton://跟多通知
          /*      islogin = SharedPreferencedUtils.getBool("isproprety", false);
                if (!islogin) {
                    ActivityUtil.getInstance().openActivity(getActivity(), PropertLoginActivity.class);
                    return;
                }*/
                ActivityUtil.getInstance().openActivity(getActivity(), PropertyAllTonActivity.class);
                break;
            case R.id.p_ton_linear1:
                showPopuWindow_headimage(No_Bean.getList().get(0).getTitile(),No_Bean.getList().get(0).getContent(),
                        No_Bean.getList().get(0).getVillagename(),No_Bean.getList().get(0).getCreatedtime());
                break;
            case R.id.p_ton_linear2:
                showPopuWindow_headimage(No_Bean.getList().get(1).getTitile(),No_Bean.getList().get(1).getContent(),
                        No_Bean.getList().get(1).getVillagename(),No_Bean.getList().get(1).getCreatedtime());
                break;
        }
    }
    private void showPopuWindow_headimage(String ctitle,String ccomment,String cqu,long ctime) {
        final View view = LayoutInflater.from(getContext()).inflate(R.layout.tonz_detail_popu, null);
        AppCompatTextView title  = view.findViewById(R.id.pd_title);//标题
        AppCompatTextView comment =  view.findViewById(R.id.pd_content);//内 容
        AppCompatTextView qu = view.findViewById(R.id.pd_qu);//小区名
        AppCompatTextView time = view.findViewById(R.id.pd_time);//时间

        title.setText(ctitle);
        comment.setText(ccomment);
        qu.setText(cqu);
        time.setText(timeSpanToDate(ctime));

        LinearLayoutCompat p_tondetal_back = view.findViewById(R.id.p_tondetal_back);//取消
        DetailPopu = new PopupWindow(view);
        DetailPopu.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        DetailPopu.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);

        DetailPopu.setAnimationStyle(R.style.PDeatailDopuStyle);//设置进入和出场动画
        DetailPopu.setBackgroundDrawable(this.getResources().getDrawable(R.color.white));
        DetailPopu.setOutsideTouchable(true);
        DetailPopu.setFocusable(true);
        DetailPopu.showAtLocation(view, Gravity.BOTTOM, 0, 0);

        p_tondetal_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DetailPopu.dismiss();
            }
        });

    }
    @Override
    public void onRefresh() {
        refreshLayout.setRefreshing(true);
        pageIndex = 1;
        isRefreshing = true;
        LoadData();
    }
}
