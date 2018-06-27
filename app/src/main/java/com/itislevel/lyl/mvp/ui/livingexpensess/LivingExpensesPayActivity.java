package com.itislevel.lyl.mvp.ui.livingexpensess;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import com.itislevel.lyl.R;
import com.itislevel.lyl.app.Constants;
import com.itislevel.lyl.base.RootActivity;
import com.itislevel.lyl.mvp.model.bean.BlessOrderBean;
import com.itislevel.lyl.mvp.model.bean.PropertyQueryInfo;
import com.itislevel.lyl.mvp.model.bean.PropertyQueryInfoBean;
import com.itislevel.lyl.mvp.model.bean.PropertyRecordBean;
import com.itislevel.lyl.mvp.model.bean.PropertyUpdateStatusBean;
import com.itislevel.lyl.mvp.model.bean.SetOwnerPayMonth;
import com.itislevel.lyl.mvp.ui.pay.PayInfoActivity;
import com.itislevel.lyl.utils.ActivityUtil;
import com.itislevel.lyl.utils.GsonUtil;
import com.itislevel.lyl.utils.SharedPreferencedUtils;
import com.itislevel.lyl.utils.ToastUtil;
import com.itislevel.lyl.utils.rxbus.RxBus;
import com.itislevel.lyl.utils.rxbus.annotation.Subscribe;
import com.itislevel.lyl.utils.rxbus.annotation.UseRxBus;
import com.itislevel.lyl.utils.rxbus.event.EventThread;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

@UseRxBus
public class LivingExpensesPayActivity extends RootActivity<LivingExpensesPresenter> implements
        LivingExpensesContract.View {

    Bundle bundle = new Bundle();

    private String PROVINCE_ID = "";
    private String CITY_ID = "";
    private String COUNTY_ID = "";

//    tv_pay_type tv_price tv_danwei  tv_danhao tv_name
//    tv_village tv_time rb_agree btn_pay tv_agreement


    @BindView(R.id.tv_pay_type)
    TextView tv_pay_type;

    @BindView(R.id.tv_price)
    TextView tv_price;
    @BindView(R.id.tv_danwei)
    TextView tv_danwei;
    @BindView(R.id.tv_danhao)
    TextView tv_danhao;
    @BindView(R.id.tv_name)
    TextView tv_name;
    @BindView(R.id.tv_village)
    TextView tv_village;

    @BindView(R.id.tv_time)
    TextView tv_time;
    @BindView(R.id.tv_agreement)
    TextView tv_agreement;

    @BindView(R.id.rb_agree)
    RadioButton rb_agree;
    @BindView(R.id.btn_pay)
    Button btn_pay;

    private int type;
    private String userid;
    private String ordernum;
    private String usernum;
    private String username;
    private String phone;
    private String status;
    private String type1;
    private int paytype;
    private String payfee;
    private String estatearea;
    private String payunit;
    private String payfeebegintime;
    private String payfeefinishtime;
    private String village;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_living_expenses_pay;
    }

    @Override
    protected void initEventAndData() {
        bundle = this.getIntent().getExtras();


        String title = bundle.getString(Constants.CITY_TITLE);
        setToolbarTvTitle(title);

        PROVINCE_ID = bundle.getString(Constants.PROVINCE_ID);
        CITY_ID = bundle.getString(Constants.CITY_ID);

        type = bundle.getInt("type");
        userid = bundle.getString("userid");
        ordernum = bundle.getString("ordernum");
        usernum = bundle.getString("usernum");
        username = bundle.getString("username");
        phone = bundle.getString("phone");
        status = bundle.getString("status");
        type1 = bundle.getString("type");
        paytype = bundle.getInt("paytype");
        payfee = bundle.getString("payfee");
        estatearea = bundle.getString("estatearea");
        village = bundle.getString("village");
        payunit = bundle.getString("payunit");
        payfeebegintime = bundle.getString("payfeebegintime");
        payfeefinishtime = bundle.getString("payfeefinishtime");

        if (type == 1) {
            tv_pay_type.setText("物业费");

        } else {
            tv_pay_type.setText("停车费");
        }
        tv_price.setText(payfee);
        tv_danwei.setText(payunit);
        tv_danhao.setText(ordernum);
        tv_name.setText(username);
        tv_village.setText(village);
        tv_time.setText(payfeefinishtime);


    }

    @OnClick({R.id.btn_pay, R.id.tv_agreement})
    public void click(View v) {
        switch (v.getId()) {
            case R.id.btn_pay:
                if (!rb_agree.isChecked()) {
                    ToastUtil.Info("请阅读并同意服务协议");
                    return;
                }
                bundle.putString(Constants.PAY_ORDERNUM, ordernum);
                bundle.putString(Constants.PAY_MODULE_NAME, Constants.CART_MODEL_PROPERTY);
                bundle.putInt(Constants.PAY_FROM_ACTIVITY, Constants.PAY_FROM_LIVE_PROPERTY);

                bundle.putString(Constants.PAY_TOTALPRICE, payfee);
//                payfeefinishtime +
//                payfeefinishtime +
                bundle.putString(Constants.PAY_GOODS_DETAIL, type == 1 ? "物业费" : "停车费");
                bundle.putString(Constants.PAY_GOODS_DESC, type == 1 ? "物业费" : "停车费");

                ActivityUtil.getInstance().openActivity(LivingExpensesPayActivity.this,
                        PayInfoActivity.class, bundle);
                break;
            case R.id.tv_agreement:
                ActivityUtil.getInstance().openActivity(LivingExpensesPayActivity.this,
                        LivingArgeementActivity.class, bundle);


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

    @Subscribe(tag = RxBus.TAG_UPDATE, thread = EventThread.MAIN_THREAD)
    public void rxrefresh(String msg) {
        ActivityUtil.getInstance().closeActivity(this);
    }

}
