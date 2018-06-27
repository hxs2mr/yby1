package com.itislevel.lyl.mvp.ui.main.home.refresh;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.itislevel.lyl.R;
import com.itislevel.lyl.adapter.recyclew.DataConVerter;
import com.itislevel.lyl.adapter.recyclew.MultipleItemEntity;
import com.itislevel.lyl.adapter.refresh.PageBean;
import com.itislevel.lyl.app.Constants;
import com.itislevel.lyl.mvp.model.bus.HomeAdapaterSucc;
import com.itislevel.lyl.mvp.ui.main.home.HomeAdapter.MultipleRecyclerAdapter;
import com.itislevel.lyl.mvp.ui.main.home.HomeModel.HomeDataConVerter;
import com.itislevel.lyl.net.RestClent;
import com.itislevel.lyl.net.callback.IFailure;
import com.itislevel.lyl.net.callback.ISuccess;
import com.itislevel.lyl.utils.SAToast;
import com.itislevel.lyl.utils.SharedPreferencedUtils;
import com.vondear.rxtools.model.Gps;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.finalteam.rxgalleryfinal.imageloader.GlideImageLoader1;
import io.rong.eventbus.EventBus;

import static com.itislevel.lyl.mvp.ui.main.home.HomeFragment.HEAD_IMAGE_URL;
import static com.itislevel.lyl.mvp.ui.main.home.HomeFragment.HOME_BANN;
import static com.itislevel.lyl.mvp.ui.main.home.HomeFragment.HOME_MODULE;
import static com.itislevel.lyl.mvp.ui.main.home.HomeFragment.group;
import static com.itislevel.lyl.mvp.ui.main.home.HomeFragment.head_one_iv_1;
import static com.itislevel.lyl.mvp.ui.main.home.HomeFragment.head_one_iv_2;
import static com.itislevel.lyl.mvp.ui.main.home.HomeFragment.head_one_iv_3;
import static com.itislevel.lyl.mvp.ui.main.home.HomeFragment.head_one_iv_4;
import static com.itislevel.lyl.mvp.ui.main.home.HomeFragment.head_one_iv_5;
import static com.itislevel.lyl.mvp.ui.main.home.HomeFragment.head_one_iv_6;
import static com.itislevel.lyl.mvp.ui.main.home.HomeFragment.head_one_iv_7;
import static com.itislevel.lyl.mvp.ui.main.home.HomeFragment.head_one_iv_8;
import static com.itislevel.lyl.mvp.ui.main.home.HomeFragment.head_one_linear_5;
import static com.itislevel.lyl.mvp.ui.main.home.HomeFragment.head_one_tv_1;
import static com.itislevel.lyl.mvp.ui.main.home.HomeFragment.head_one_tv_2;
import static com.itislevel.lyl.mvp.ui.main.home.HomeFragment.head_one_tv_3;
import static com.itislevel.lyl.mvp.ui.main.home.HomeFragment.head_one_tv_4;
import static com.itislevel.lyl.mvp.ui.main.home.HomeFragment.head_one_tv_5;
import static com.itislevel.lyl.mvp.ui.main.home.HomeFragment.head_one_tv_6;
import static com.itislevel.lyl.mvp.ui.main.home.HomeFragment.head_one_tv_7;
import static com.itislevel.lyl.mvp.ui.main.home.HomeFragment.head_one_tv_8;
import static com.itislevel.lyl.mvp.ui.main.home.HomeFragment.home_one_start_linear2;
import static com.itislevel.lyl.mvp.ui.main.home.HomeFragment.home_vewpage;
import static com.vondear.rxtools.RxImageTool.dip2px;

/**
 * Created by microtech on 2017/11/17.
 */

public class Home_RefreshHandler implements SwipeRefreshLayout.OnRefreshListener,BaseQuickAdapter.RequestLoadMoreListener{
    public  static int patient_postion =-1;
    private final SwipeRefreshLayout REFRESH_LAYOUT;
    private final PageBean BEAN;
    private final RecyclerView RECYCLEVIEW;
    public static MultipleRecyclerAdapter patient_mAdapter=null;
    private final DataConVerter CONVERTER;
    private Context mContext;
    private View view = null;
    private String  City_id = "0";
    private Banner banner=null;
    private String next_start ="";
    private List<MultipleItemEntity> home_data = null;
    private List<MultipleItemEntity> home_data_null = null;
    public Home_RefreshHandler(SwipeRefreshLayout REFRESH_LAYOUT, RecyclerView recycleview, DataConVerter conVerter, PageBean bean) {
        this.REFRESH_LAYOUT = REFRESH_LAYOUT;
        REFRESH_LAYOUT.setOnRefreshListener(this);
        this.RECYCLEVIEW = recycleview;
        this.CONVERTER = conVerter;
        this.BEAN = bean;
    }

    public static Home_RefreshHandler create(SwipeRefreshLayout swipeRefreshLayout, RecyclerView recycleview, DataConVerter conVerter){
        return new Home_RefreshHandler(swipeRefreshLayout,recycleview,conVerter,new PageBean());
    }
    private void refresh(){
        REFRESH_LAYOUT.setRefreshing(true);
        Map<String, String> request = new HashMap<>();
        request.put("token", SharedPreferencedUtils.getStr(Constants.USER_TOKEN));
        request.put("usernum", SharedPreferencedUtils.getStr(Constants.USER_NUM));
        request.put("cityid", City_id);
        String  jsonObject = JSON.toJSONString(request);

        //  mPresenter.firstPage(GsonUtil.obj2JSON(request));坑比  大坑比
        RestClent.builder()
                .url("firstPage")
                .params("action",jsonObject)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        int status = JSON.parseObject(response).getInteger("status");
                        System.out.println("返回的主界面数据********"+response);
                        if(status == 0)
                        {
                            home_data =CONVERTER.setJsonData(response).CONVERT();
                            patient_mAdapter.refresh(home_data);
                            //测试banner图
                            ArrayList<String> image_arr = new ArrayList<>();
                            int size = HOME_BANN.size();
                            for(int i  =0 ; i <size ; i++)
                            {
                                System.out.println("主界面banner图片地址*********"+HEAD_IMAGE_URL+HOME_BANN.get(i).getLogo());
                                image_arr.add(HEAD_IMAGE_URL+HOME_BANN.get(i).getLogo());
                            }
                            showBanner(banner,image_arr);
                            init_start_iv_tv();
                        }else {
                            Toast.makeText(mContext,"服务器异常！",Toast.LENGTH_SHORT).show();
                        }
                        REFRESH_LAYOUT.setRefreshing(false);
                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onIFailure() {
                        System.out.println("主界面数据请求失败********");
                        SAToast.makeText(mContext,"服务器异常！").show();
                        REFRESH_LAYOUT.setRefreshing(false);
                    }
                })
                .build()
                .post();
    }

    public void loation_refresh(String city_id){
        REFRESH_LAYOUT.setRefreshing(true);
        Map<String, String> request = new HashMap<>();
        request.put("token", SharedPreferencedUtils.getStr(Constants.USER_TOKEN));
        request.put("usernum", SharedPreferencedUtils.getStr(Constants.USER_NUM));
        request.put("cityid", city_id);
        String  jsonObject = JSON.toJSONString(request);

        //  mPresenter.firstPage(GsonUtil.obj2JSON(request));坑比  大坑比
        RestClent.builder()
                .url("firstPage")
                .params("action",jsonObject)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        int status = JSON.parseObject(response).getInteger("status");
                        if(status == 0) {
                            home_data = CONVERTER.setJsonData(response).CONVERT();
                            if (home_data != null)
                            {
                                patient_mAdapter.refresh(home_data);
                            }
                            //测试banner图
                            ArrayList<String> image_arr = new ArrayList<>();
                            int size = HOME_BANN.size();
                            for(int i  =0 ; i <size ; i++)
                            {
                                image_arr.add(HEAD_IMAGE_URL+HOME_BANN.get(i).getLogo());
                            }
                            showBanner(banner,image_arr);
                        }else {
                            Toast.makeText(mContext,"服务器异常！",Toast.LENGTH_SHORT).show();
                        }
                        REFRESH_LAYOUT.setRefreshing(false);
                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onIFailure() {
                        System.out.println("主界面数据请求失败********");
                        SAToast.makeText(mContext,"服务器异常！").show();
                        REFRESH_LAYOUT.setRefreshing(false);
                    }
                })
                .build()
                .post();
    }

    private void init_add_new(String response){//比较刷新的数据
    }

    public void refresh_add(Context context, final String r, final String g, final String d){
        REFRESH_LAYOUT.setRefreshing(true);
    }

    private void init_add_head(String response ) {
    }

    public void firstPage(String url, Activity activity, Context context, final View view, String City_id, Banner banner)
    {
        this.view=view;
        this.mContext= context;
        this.banner=banner;
        this.City_id = City_id;
        REFRESH_LAYOUT.setRefreshing(true);
        BEAN.setDelayed(1000);
        home_data_null= new ArrayList<>();
        Map<String, String> request = new HashMap<>();
        request.put("token", SharedPreferencedUtils.getStr(Constants.USER_TOKEN));
        request.put("usernum", SharedPreferencedUtils.getStr(Constants.USER_NUM));
        request.put("cityid", City_id);
        String  jsonObject = JSON.toJSONString(request);
        patient_mAdapter= MultipleRecyclerAdapter.create(activity,mContext,home_data_null);
        View emptyView = View.inflate(mContext, R.layout.partial_empty_view, null);
        TextView viewById = emptyView.findViewById(R.id.tv_message);
        patient_mAdapter.setEmptyView(emptyView);
        patient_mAdapter.addHeaderView(view);
        patient_mAdapter.isFirstOnly(true);
        RECYCLEVIEW.setAdapter(patient_mAdapter);
        //  mPresenter.firstPage(GsonUtil.obj2JSON(request));坑比  大坑比
        RestClent.builder()
                .url("firstPage")
                .params("action",jsonObject)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        int status = JSON.parseObject(response).getInteger("status");
                        if(status == 0)
                        {
                            home_data =CONVERTER.setJsonData(response).CONVERT();
                            patient_mAdapter.setNewData(home_data);
                            //测试banner图
                            ArrayList<String> image_arr = new ArrayList<>();
                            int size = HOME_BANN.size();
                            for(int i  =0 ; i <size ; i++)
                            {
                                image_arr.add(HEAD_IMAGE_URL+HOME_BANN.get(i).getLogo());
                            }
                            showBanner(banner,image_arr);

                            init_start_iv_tv();//加载功能图标
                        }else {
                            SAToast.makeText(mContext,"服务器异常！").show();
                        }
                        REFRESH_LAYOUT.setRefreshing(false);
                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onIFailure() {
                        SAToast.makeText(mContext,"服务器异常！").show();
                        REFRESH_LAYOUT.setRefreshing(false);
                    }
                })
                .build()
                .post();
    }

    private void init_start_iv_tv() {
       int size =  HOME_MODULE.size();
       if(size<8)
       {
           group.setVisibility(View.GONE);
       }
       if (size<=5)
       {
           LinearLayoutCompat.LayoutParams linearParams = (LinearLayoutCompat.LayoutParams) home_vewpage.getLayoutParams();
           linearParams.height =dip2px(87);
           home_vewpage.setLayoutParams(linearParams);
       }else {
           LinearLayoutCompat.LayoutParams linearParams = (LinearLayoutCompat.LayoutParams) home_vewpage.getLayoutParams();
           linearParams.height =dip2px(174);
           home_vewpage.setLayoutParams(linearParams);
       }
       if(size!=0)
       {
           if(size ==8||size>8)
           {
               init_load_Image(HEAD_IMAGE_URL+HOME_MODULE.get(0).getIcon(),head_one_iv_1);
               head_one_tv_1.setText(HOME_MODULE.get(0).getCatename());

               init_load_Image(HEAD_IMAGE_URL+HOME_MODULE.get(1).getIcon(),head_one_iv_2);
               head_one_tv_2.setText(HOME_MODULE.get(1).getCatename());

               init_load_Image(HEAD_IMAGE_URL+HOME_MODULE.get(2).getIcon(),head_one_iv_3);
               head_one_tv_3.setText(HOME_MODULE.get(2).getCatename());
               head_one_tv_4.setText(HOME_MODULE.get(3).getCatename());
               head_one_tv_5.setText(HOME_MODULE.get(4).getCatename());
               head_one_tv_6.setText(HOME_MODULE.get(5).getCatename());
               head_one_tv_7.setText(HOME_MODULE.get(6).getCatename());
               head_one_tv_8.setText(HOME_MODULE.get(7).getCatename());
               init_load_Image(HEAD_IMAGE_URL+HOME_MODULE.get(3).getIcon(),head_one_iv_4);

               init_load_Image(HEAD_IMAGE_URL+HOME_MODULE.get(4).getIcon(),head_one_iv_5);
               init_load_Image(HEAD_IMAGE_URL+HOME_MODULE.get(5).getIcon(),head_one_iv_6);
               init_load_Image(HEAD_IMAGE_URL+HOME_MODULE.get(6).getIcon(),head_one_iv_7);
               init_load_Image(HEAD_IMAGE_URL+HOME_MODULE.get(7).getIcon(),head_one_iv_8);
           }else if(size == 7)
           {
               init_load_Image(HEAD_IMAGE_URL+HOME_MODULE.get(0).getIcon(),head_one_iv_1);
               head_one_tv_1.setText(HOME_MODULE.get(0).getCatename());

               init_load_Image(HEAD_IMAGE_URL+HOME_MODULE.get(1).getIcon(),head_one_iv_2);
               head_one_tv_2.setText(HOME_MODULE.get(1).getCatename());

               init_load_Image(HEAD_IMAGE_URL+HOME_MODULE.get(2).getIcon(),head_one_iv_3);
               head_one_tv_3.setText(HOME_MODULE.get(2).getCatename());
               head_one_tv_4.setText(HOME_MODULE.get(3).getCatename());
               head_one_tv_5.setText(HOME_MODULE.get(4).getCatename());
               head_one_tv_6.setText(HOME_MODULE.get(5).getCatename());
               head_one_tv_7.setText(HOME_MODULE.get(6).getCatename());
               init_load_Image(HEAD_IMAGE_URL+HOME_MODULE.get(3).getIcon(),head_one_iv_4);

               init_load_Image(HEAD_IMAGE_URL+HOME_MODULE.get(4).getIcon(),head_one_iv_5);
               init_load_Image(HEAD_IMAGE_URL+HOME_MODULE.get(5).getIcon(),head_one_iv_6);
               init_load_Image(HEAD_IMAGE_URL+HOME_MODULE.get(6).getIcon(),head_one_iv_7);
           }else if(size == 6)
           {
               init_load_Image(HEAD_IMAGE_URL+HOME_MODULE.get(0).getIcon(),head_one_iv_1);
               head_one_tv_1.setText(HOME_MODULE.get(0).getCatename());
               init_load_Image(HEAD_IMAGE_URL+HOME_MODULE.get(1).getIcon(),head_one_iv_2);
               head_one_tv_2.setText(HOME_MODULE.get(1).getCatename());
               init_load_Image(HEAD_IMAGE_URL+HOME_MODULE.get(2).getIcon(),head_one_iv_3);
               head_one_tv_3.setText(HOME_MODULE.get(2).getCatename());
               head_one_tv_4.setText(HOME_MODULE.get(3).getCatename());
               head_one_tv_5.setText(HOME_MODULE.get(4).getCatename());
               head_one_tv_6.setText(HOME_MODULE.get(5).getCatename());
               init_load_Image(HEAD_IMAGE_URL+HOME_MODULE.get(3).getIcon(),head_one_iv_4);

               init_load_Image(HEAD_IMAGE_URL+HOME_MODULE.get(4).getIcon(),head_one_iv_5);
               init_load_Image(HEAD_IMAGE_URL+HOME_MODULE.get(5).getIcon(),head_one_iv_6);
           }else if(size == 5)
           {
               init_load_Image(HEAD_IMAGE_URL+HOME_MODULE.get(0).getIcon(),head_one_iv_1);
               head_one_tv_1.setText(HOME_MODULE.get(0).getCatename());
               init_load_Image(HEAD_IMAGE_URL+HOME_MODULE.get(1).getIcon(),head_one_iv_2);
               head_one_tv_2.setText(HOME_MODULE.get(1).getCatename());
               init_load_Image(HEAD_IMAGE_URL+HOME_MODULE.get(2).getIcon(),head_one_iv_3);
               head_one_tv_3.setText(HOME_MODULE.get(2).getCatename());
               head_one_tv_4.setText(HOME_MODULE.get(3).getCatename());
               head_one_tv_5.setText(HOME_MODULE.get(4).getCatename());
               init_load_Image(HEAD_IMAGE_URL+HOME_MODULE.get(3).getIcon(),head_one_iv_4);

               init_load_Image(HEAD_IMAGE_URL+HOME_MODULE.get(4).getIcon(),head_one_iv_5);
           }else if(size == 4)
           {
               head_one_linear_5.setVisibility(View.GONE);
               home_one_start_linear2.setVisibility(View.GONE);
               init_load_Image(HEAD_IMAGE_URL+HOME_MODULE.get(0).getIcon(),head_one_iv_1);
               head_one_tv_1.setText(HOME_MODULE.get(0).getCatename());
               init_load_Image(HEAD_IMAGE_URL+HOME_MODULE.get(1).getIcon(),head_one_iv_2);
               head_one_tv_2.setText(HOME_MODULE.get(1).getCatename());
               init_load_Image(HEAD_IMAGE_URL+HOME_MODULE.get(2).getIcon(),head_one_iv_3);
               head_one_tv_3.setText(HOME_MODULE.get(2).getCatename());
               head_one_tv_4.setText(HOME_MODULE.get(3).getCatename());
               init_load_Image(HEAD_IMAGE_URL+HOME_MODULE.get(3).getIcon(),head_one_iv_4);

           }
       }
    }

    private void init_load_Image( String image_url, AppCompatImageView image) {
        Glide.with(mContext)
                .load(image_url)
                .asBitmap()
                .error(R.mipmap.test)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(image);
    }

    @Override
    public void onRefresh() {
        refresh();
    }

    public MultipleRecyclerAdapter return_adapter(){
        return patient_mAdapter;
    }

    @Override
    public void onLoadMoreRequested() {//加载更多
        loadmore();
    }

    private void loadmore() {

    }
    private void showBanner(Banner home_banner, ArrayList<String> bannerImages)
    {
        home_banner.setImageLoader(new GlideImageLoader1());
        home_banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);//设置banner样式
        home_banner.setImages(bannerImages);//设置图片集合
        home_banner.setBannerAnimation(Transformer.Accordion);//设置banner动画效果
        home_banner.isAutoPlay(true);//设置自动轮播，默认为true
        home_banner.setDelayTime(3000);//设置轮播时间
        home_banner.setIndicatorGravity(BannerConfig.RIGHT); //设置指示器位置（当banner模式中有指示器时）
        home_banner.start();//banner设置方法全部调用完毕时最后调用
    }
}
