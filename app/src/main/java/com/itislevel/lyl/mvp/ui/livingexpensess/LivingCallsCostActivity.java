package com.itislevel.lyl.mvp.ui.livingexpensess;

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
import com.itislevel.lyl.utils.ToastUtil;

import butterknife.OnClick;

public class LivingCallsCostActivity extends RootActivity<LivingExpensesPresenter> implements
        LivingExpensesContract.View {
    Bundle bundle = new Bundle();

    private String PROVINCE_ID = "";
    private String CITY_ID = "";
    private String COUNTY_ID = "";

    @Override
    protected int getLayoutId() {
        return R.layout.activity_living_calls_cost;
    }

    @Override
    protected void initEventAndData() {
        bundle = this.getIntent().getExtras();
        String title = bundle.getString(Constants.CITY_TITLE);
        PROVINCE_ID = bundle.getString(Constants.PROVINCE_ID);
        CITY_ID = bundle.getString(Constants.CITY_ID);

        setToolbarTvTitle(title);
    }

    @OnClick({R.id.tv_call30, R.id.tv_call50, R.id.tv_call100,
            R.id.tv_call200, R.id.tv_call300, R.id.tv_call500,})
    public void click(View v) {
        switch (v.getId()) {
            case R.id.tv_call30:
                ToastUtil.Info("订单确认页面:30");
                break;
            case R.id.tv_call50:
                ToastUtil.Info("订单确认页面:50");
                break;
            case R.id.tv_call100:
                ToastUtil.Info("订单确认页面:100");
                break;
            case R.id.tv_call200:
                ToastUtil.Info("订单确认页面:200");
                break;
            case R.id.tv_call300:
                ToastUtil.Info("订单确认页面:300");
                break;
            case R.id.tv_call500:
                ToastUtil.Info("订单确认页面:500");
                break;
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
