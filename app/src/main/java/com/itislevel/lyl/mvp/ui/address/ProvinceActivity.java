package com.itislevel.lyl.mvp.ui.address;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.itislevel.lyl.R;
import com.itislevel.lyl.adapter.AddressAdapter;
import com.itislevel.lyl.app.Constants;
import com.itislevel.lyl.base.RootActivity;
import com.itislevel.lyl.mvp.model.bean.AddressBean;
import com.itislevel.lyl.utils.ActivityUtil;
import com.itislevel.lyl.utils.GsonUtil;
import com.itislevel.lyl.utils.SharedPreferencedUtils;
import com.itislevel.lyl.utils.ToastUtil;
import com.itislevel.lyl.utils.rxbus.RxBus;
import com.itislevel.lyl.utils.rxbus.annotation.Subscribe;
import com.itislevel.lyl.utils.rxbus.annotation.UseRxBus;
import com.itislevel.lyl.utils.rxbus.event.EventThread;
import com.itislevel.lyl.widget.DividerGridItemDecoration;
import com.orhanobut.logger.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
@UseRxBus
public class ProvinceActivity extends RootActivity<AddressPresenter> implements AddressContract
        .View , BaseQuickAdapter.OnItemClickListener {

    Bundle bundle = new Bundle();
    private String PROVINCE_ID="";
    private String PROVINCE_NAME="";
    private String PROVINCE_TITLE="";



    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private AddressAdapter adapter;
    private GridLayoutManager layoutManager;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_province;
    }

    @Override
    protected void initEventAndData() {
        bundle = this.getIntent().getExtras();
        String title = bundle.getString(Constants.PROVINCE_TITLE);
        setToolbarTvTitle(title);

        initAdapter();
        loadData();

    }

    private void initAdapter() {
        layoutManager = new GridLayoutManager(this,4);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new DividerGridItemDecoration(this, 4));

    }

    private void loadData() {

        Map<String,String> request=new HashMap<>();
        request.put("token",SharedPreferencedUtils.getStr(Constants.USER_TOKEN));
        request.put("usernum",SharedPreferencedUtils.getStr(Constants.USER_NUM));
        mPresenter.province(GsonUtil.obj2JSON(request));//省份地区的网络请求
    }


    @Override
    public void showContent(String msg) {

    }

    @Override
    public void province(final List<AddressBean> addressBeans) {//拿到省份地区
        if (addressBeans.get(0).getName().equals("中国")){
            addressBeans.remove(0);
        }
        adapter=new AddressAdapter(addressBeans,this);
        adapter.setClickListener(new AddressAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                View parentLayout;
                TextView viewById;
                for (int i=0;i<addressBeans.size();i++){
                    parentLayout= layoutManager.findViewByPosition(i);
                    if (parentLayout!=null){
                        viewById= (TextView) parentLayout.findViewById(R.id.tv_name);
                        viewById.setTextColor(getResources().getColor(R.color.colorNav));
                    }

                }
                parentLayout= layoutManager.findViewByPosition(position);
                viewById= (TextView) parentLayout.findViewById(R.id.tv_name);
                viewById.setTextColor(getResources().getColor(R.color.colorBtn));


                PROVINCE_ID=addressBeans.get(position).getId()+"";
                PROVINCE_NAME=addressBeans.get(position).getName();

                bundle.putString(Constants.PROVINCE_ID,PROVINCE_ID);
                bundle.putString(Constants.PROVINCE_NAME,PROVINCE_NAME); //省地区的名称和ID代号

                ActivityUtil.getInstance().openActivity(ProvinceActivity.this,CityActivity.class,bundle);


            }
            @Override
            public void onItemLongClick(View view, int position) {
            }
        });
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void city(List<AddressBean> addressBeans) {

    }

    @Override
    public void county(List<AddressBean> addressBeans) {

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
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Subscribe(tag = RxBus.TAG_UPDATE, thread = EventThread.MAIN_THREAD)
    public void rxfinish(String msg){
        Logger.i(msg);
         if (msg.equals("finish")){
            ActivityUtil.getInstance().closeActivity(this);
        }
    }

}
