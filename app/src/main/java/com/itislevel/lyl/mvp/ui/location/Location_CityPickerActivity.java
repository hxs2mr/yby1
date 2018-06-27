package com.itislevel.lyl.mvp.ui.location;

import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.itislevel.lyl.R;
import com.itislevel.lyl.adapter.recyclew.MultipleItemEntity;
import com.itislevel.lyl.app.Constants;
import com.itislevel.lyl.base.RootActivity;
import com.itislevel.lyl.mvp.model.bean.HouseBean;
import com.itislevel.lyl.mvp.model.bean.Location_Bean;
import com.itislevel.lyl.mvp.model.bus.HomeAdapaterSucc;
import com.itislevel.lyl.mvp.ui.bus.bless_home;
import com.itislevel.lyl.mvp.ui.funsharing.FunsharingContract;
import com.itislevel.lyl.mvp.ui.funsharing.FunsharingPresenter;
import com.itislevel.lyl.utils.SAToast;

import org.greenrobot.eventbus.EventBus;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import static com.itislevel.lyl.mvp.ui.main.home.HomeFragment.CITY_NAME;

/**
 * Created by Administrator on 2018\4\12 0012.
 */

public class Location_CityPickerActivity extends RootActivity<Location_CityPickerPresenter> implements
        Location_CityPickerContract.View, View.OnClickListener {
    Bundle bundle = null;
    @BindView(R.id.tree_recycle)
    RecyclerView tree_recycle ;

    @BindView(R.id.location_look)
    AppCompatTextView location_look;

    @BindView(R.id.location_city_tv)
    AppCompatTextView location_city_tv;

    @BindView(R.id.location_xinyi)
    AppCompatTextView location_xinyi;

    @BindView(R.id.location_guiyan)
    AppCompatTextView location_guiyan;

    @BindView(R.id.location_zunyi)
    AppCompatTextView location_zunyi;

    @BindView(R.id.location_ansun)
    AppCompatTextView location_ansun;

    @BindView(R.id.location_tonren)
    AppCompatTextView location_tonren;

    @BindView(R.id.location_bijie)
    AppCompatTextView location_bijie;

    @BindView(R.id.location_qiannan)
    AppCompatTextView location_qiannan;

    @BindView(R.id.location_qiandn)
    AppCompatTextView location_qiandn;

    @BindView(R.id.location_lbs)
    AppCompatTextView location_lbs;


    @BindView(R.id.location_cancle)
    LinearLayoutCompat location_cancle;

    TreeAdapter treeAdapter;//recycleview适配器
    List<TreeBean> list;//一二级列表的list 实体类

    private List<MultipleItemEntity> location_data = null;
    List<Location_Bean> list_data ;


    public static  String start_flage ="";

    public static  String loaction_p_id="0";
    public static  String location_p_name="";
    @Override
    protected int getLayoutId() {
        return R.layout.locationactivity;
    }

    @Override
    protected void initEventAndData() {
        setToolbarTvTitle("选择城市");
        bundle = getIntent().getExtras();
        /*
        * home 标识的主界面
        * bless 标识的是喜事祝福
        * */
        start_flage = bundle.getString(Constants.START_FLAGE);//获取到标志位

        location_look.setText("您正在看的城市："+bundle.getString(Constants.CITY_NAME));
        location_city_tv.setText(bundle.getString(Constants.CITY_NAME));
        tree_recycle.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<>();
        list_data = new ArrayList<>();
        read_json();
        list_data=location_data.get(0).getField(LocationCityFields.P_C_LIST);
        treeAdapter = new TreeAdapter(this, list_data,Location_CityPickerActivity.this);
        tree_recycle.setAdapter(treeAdapter);

        location_xinyi.setOnClickListener(this);
        location_guiyan.setOnClickListener(this);
        location_zunyi.setOnClickListener(this);
        location_ansun.setOnClickListener(this);
        location_tonren.setOnClickListener(this);
        location_bijie.setOnClickListener(this);
        location_cancle.setOnClickListener(this);

        location_qiannan.setOnClickListener(this);
        location_qiandn.setOnClickListener(this);
        location_lbs.setOnClickListener(this);

    }
    private void read_json() {
      String json =   getJson ("resultvalue.json");//读取本地的json数据
      System.out.println("************:"+json);
      location_data =new LocationDataConvter().setJsonData(json).CONVERT();//解析获取到的json数据
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

    }

    private String getJson(String fileName) {//读取本地json数据

                StringBuilder stringBuilder = new StringBuilder();
               try {
                     BufferedReader bf = new BufferedReader(new InputStreamReader(
                                   getAssets().open(fileName)));
                     String line;
                   while ((line = bf.readLine()) != null) {
                               stringBuilder.append(line);
                         }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                 return stringBuilder.toString();
            }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.location_xinyi:
                if(start_flage.equals("home"))
                {
                    EventBus.getDefault().post(new HomeAdapaterSucc("522300","黔西南","520000","贵州"));
                }else if(start_flage.equals("toncity")){
                    EventBus.getDefault().post(new bless_home("522300","黔西南","520000","贵州"));
                }else {
                    EventBus.getDefault().post(new HouseBean("522300","黔西南","520000","贵州"));
                }
                finish();
                break;
            case R.id.location_guiyan:
                if(start_flage.equals("home"))
                {
                    EventBus.getDefault().post(new HomeAdapaterSucc("520100","贵阳","520000","贵州"));
                }else if(start_flage.equals("toncity")){
                    EventBus.getDefault().post(new bless_home("520100","贵阳","520000","贵州"));
                }else {
                    EventBus.getDefault().post(new HouseBean("520100","贵阳","520000","贵州"));
                }
                finish();
                break;
            case R.id.location_zunyi:
                if(start_flage.equals("home"))
                {
                    EventBus.getDefault().post(new HomeAdapaterSucc("520300","遵义","520000","贵州"));
                }else if(start_flage.equals("toncity")){
                    EventBus.getDefault().post(new bless_home("520300","遵义","520000","贵州"));
                }else {
                    EventBus.getDefault().post(new HouseBean("520300","遵义","520000","贵州"));
                }
                finish();
                break;
            case R.id.location_ansun:
                if(start_flage.equals("home"))
                {
                    EventBus.getDefault().post(new HomeAdapaterSucc("520400","安顺","520000","贵州"));
                }else if(start_flage.equals("toncity")){
                    EventBus.getDefault().post(new bless_home("520400","安顺","520000","贵州"));
                }else {
                    EventBus.getDefault().post(new HouseBean("520400","安顺","520000","贵州"));
                }
                finish();
                break;
            case R.id.location_tonren:
                if(start_flage.equals("home"))
                {
                    EventBus.getDefault().post(new HomeAdapaterSucc("522200","铜仁","520000","贵州"));
                }else if(start_flage.equals("toncity")){
                    EventBus.getDefault().post(new bless_home("522200","铜仁","520000","贵州"));
                }else {
                    EventBus.getDefault().post(new HouseBean("522200","铜仁","520000","贵州"));
                }
                finish();
                break;
            case R.id.location_bijie:
                if(start_flage.equals("home"))
                {
                    EventBus.getDefault().post(new HomeAdapaterSucc("522400","毕节","520000","贵州"));
                }else if(start_flage.equals("toncity")){
                    EventBus.getDefault().post(new bless_home("522400","毕节","520000","贵州"));
                }else {
                    EventBus.getDefault().post(new HouseBean("522400","毕节","520000","贵州"));
                }
                finish();
                break;
            case R.id.location_qiannan:
                if(start_flage.equals("home"))
                {
                    EventBus.getDefault().post(new HomeAdapaterSucc("522700","黔南","520000","贵州"));
                }else if(start_flage.equals("toncity")){
                    EventBus.getDefault().post(new bless_home("522700","黔南","520000","贵州"));
                }else {
                    EventBus.getDefault().post(new HouseBean("522700","黔南","520000","贵州"));
                }
                finish();
                break;
            case R.id.location_qiandn:
                if(start_flage.equals("home"))
                {
                    EventBus.getDefault().post(new HomeAdapaterSucc("522600","黔东南","520000","贵州"));
                }else if(start_flage.equals("toncity")){
                    EventBus.getDefault().post(new bless_home("522600","黔东南","520000","贵州"));
                }else {
                    EventBus.getDefault().post(new HouseBean("522600","黔东南","520000","贵州"));
                }
                finish();
                break;
            case R.id.location_lbs:
                if(start_flage.equals("home"))
                {
                    EventBus.getDefault().post(new HomeAdapaterSucc("520200","六盘水","520000","贵州"));
                }else if(start_flage.equals("toncity")){
                    EventBus.getDefault().post(new bless_home("520200","六盘水","520000","贵州"));
                }else {
                    EventBus.getDefault().post(new HouseBean("520200","六盘水","520000","贵州"));
                }
                finish();
                break;
            case R.id.location_cancle:
                finish();
                break;

        }
    }
}
