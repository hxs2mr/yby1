package com.itislevel.lyl.mvp.ui.livingexpensess;

import android.os.Bundle;
import android.widget.TextView;

import com.itislevel.lyl.R;
import com.itislevel.lyl.base.RootActivity;
import com.itislevel.lyl.mvp.model.bean.BlessOrderBean;
import com.itislevel.lyl.mvp.model.bean.PropertyQueryInfo;
import com.itislevel.lyl.mvp.model.bean.PropertyQueryInfoBean;
import com.itislevel.lyl.mvp.model.bean.PropertyRecordBean;
import com.itislevel.lyl.mvp.model.bean.PropertyUpdateStatusBean;
import com.itislevel.lyl.mvp.model.bean.SetOwnerPayMonth;

import butterknife.BindView;

public class LivingPropertyDetailActivity extends RootActivity<LivingExpensesPresenter>
        implements LivingExpensesContract.View {

    Bundle bundle;

//    tv_pay_free tv_name tv_pay_ordernum
//    tv_pay_unit   tv_pay_time tv_pay_status

    @BindView(R.id.tv_pay_free)
    TextView tv_pay_free;

    @BindView(R.id.tv_name)
    TextView tv_pay_name;

    @BindView(R.id.tv_pay_ordernum)
    TextView tv_pay_ordernum;

    @BindView(R.id.tv_pay_unit)
    TextView tv_pay_unit;

    @BindView(R.id.tv_pay_time)
    TextView tv_pay_time;

    @BindView(R.id.tv_pay_status)
    TextView tv_pay_status;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_living_property_detail;
    }

    @Override
    protected void initEventAndData() {
        bundle = getIntent().getExtras();

        setToolbarTvTitle("缴费记录");

        tv_pay_ordernum.setText(bundle.getString("ordernum"));
        tv_pay_name.setText(bundle.getString("username"));
        int status = bundle.getInt("status");
        if (status == 1) {
            tv_pay_status.setText("缴费成功");
        } else {
            tv_pay_status.setText("缴费失败");
        }
        tv_pay_free.setText(bundle.getString("payfee"));
        tv_pay_unit.setText(bundle.getString("companyName"));
        tv_pay_time.setText(bundle.getString("paytime"));

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
    protected void initInject() {
        getActivityComponent().inject(this);
    }
}
