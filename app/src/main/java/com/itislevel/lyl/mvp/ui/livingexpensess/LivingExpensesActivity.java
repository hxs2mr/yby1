package com.itislevel.lyl.mvp.ui.livingexpensess;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.itislevel.lyl.R;
import com.itislevel.lyl.app.Constants;
import com.itislevel.lyl.base.RootActivity;
import com.itislevel.lyl.mvp.model.bean.BlessOrderBean;
import com.itislevel.lyl.mvp.model.bean.PropertyQueryInfo;
import com.itislevel.lyl.mvp.model.bean.PropertyQueryInfoBean;
import com.itislevel.lyl.mvp.model.bean.PropertyRecordBean;
import com.itislevel.lyl.mvp.model.bean.PropertyUpdateStatusBean;
import com.itislevel.lyl.mvp.model.bean.SetOwnerPayMonth;
import com.itislevel.lyl.utils.ActivityUtil;

import butterknife.OnClick;

public class LivingExpensesActivity extends RootActivity<LivingExpensesPresenter> implements
        LivingExpensesContract.View {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_living_expenses;
    }

    @Override
    protected void initEventAndData() {
        setToolbarTvTitle("物业缴费");
        setToolbarBackground(Color.parseColor("#000000"));
    }

    //iv_living_water iv_living_electric
//iv_living_gas iv_living_closed_tv
//iv_living_property iv_living_phone_credit
    @OnClick({            R.id.iv_living_property})
    public void click(View v) {
        Bundle bundle=new Bundle();
        String title="";
        boolean isCallsCost=false;//是否是手机充值
        switch (v.getId()) {
            case R.id.iv_living_property://物业费
                title="物业费";
                bundle.putBoolean(Constants.IS_SHOW_COUNTY,true);
                bundle.putInt(Constants.ACTIVITY_TARGET,Constants.ACTIVITY_LIVING_PROPERTY);
                break;

        }
        bundle.putString(Constants.PROVINCE_TITLE,title);
        bundle.putString(Constants.CITY_TITLE,title);
        bundle.putString(Constants.COUNTY_TITLE,title);
        if (isCallsCost){
            ActivityUtil.getInstance().openActivity(LivingExpensesActivity.this, LivingCallsCostActivity.class,bundle);

        }else{
//            ActivityUtil.getInstance().openActivity(LivingExpensesActivity.this, ProvinceActivity.class,bundle);
            ActivityUtil.getInstance().openActivity(LivingExpensesActivity.this, LivingPropertyQeruyActivity.class,bundle);

        }

    }

    @Override
    public void showContent(String msg) {

    }

    @Override
    public void propertyQuery(PropertyQueryInfoBean propertyQueryInfoBean) {

    }

    @Override
    public void propertyQueryOrder(PropertyRecordBean propertyRecordBean) {

    }

    @Override
    public void propertyUpdatePayType(PropertyUpdateStatusBean statusBean) {

    }

    @Override
    public void propertyUpdateOrderState(PropertyUpdateStatusBean statusBean) {

    }

    @Override
    public void propertyGenerateOrder(String blessOrderBean) {

    }

    @Override
    public void getSSMCode(String smscode) {

    }

    @Override
    public void propertyQueryByUserid(PropertyQueryInfo queryInfo) {

    }

    @Override
    public void propertyQueryList(PropertyQueryInfo queryInfo) {

    }

    @Override
    public void propertyQueryByUserid1(PropertyQueryInfoBean queryInfoBean) {

    }

    @Override
    public void propertySetOwnerPayMonth(SetOwnerPayMonth setOwnerPayMonth) {

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
}
