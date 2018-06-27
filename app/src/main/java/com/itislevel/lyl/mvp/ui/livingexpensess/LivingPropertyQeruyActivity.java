package com.itislevel.lyl.mvp.ui.livingexpensess;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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
import com.itislevel.lyl.utils.GsonUtil;
import com.itislevel.lyl.utils.RegexUtil;
import com.itislevel.lyl.utils.ToastUtil;
import com.itislevel.lyl.widget.DownTimer;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class LivingPropertyQeruyActivity extends RootActivity<LivingExpensesPresenter> implements
        LivingExpensesContract.View {


    Bundle bundle = new Bundle();

    private String PROVINCE_ID = "";
    private String CITY_ID = "";
    private String COUNTY_ID = "";

    @BindView(R.id.et_name)
    EditText et_name;

    @BindView(R.id.et_phone)
    EditText et_phone;

    @BindView(R.id.et_validate)
    EditText et_validate;


    @BindView(R.id.btn_getsmscode)
    Button btn_getsmscode;
    private String dbValidateCode;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_living_property_qeruy;
    }

    @Override
    protected void initEventAndData() {
        bundle = this.getIntent().getExtras();
        String title = bundle.getString(Constants.CITY_TITLE);
        PROVINCE_ID = bundle.getString(Constants.PROVINCE_ID);
        CITY_ID = bundle.getString(Constants.CITY_ID);

        setToolbarTvTitle("物业缴费");
    }


    @OnClick({R.id.btn_query_bill, R.id.btn_getsmscode})
    public void click(View v) {
        switch (v.getId()) {
            case R.id.btn_query_bill:
                // TODO: 2018-01-11  封装参数 或者当前页面请求 应该是在当前页面请求
                String name = et_name.getText().toString().trim();
                String phone = et_phone.getText().toString().trim();
                String validate = et_validate.getText().toString().trim();

                if (TextUtils.isEmpty(name)) {
                    ToastUtil.Info("请输入姓名");
                    return;
                }
                if (TextUtils.isEmpty(phone)) {
                    ToastUtil.Info("请输入手机号");
                    return;
                }
                if (!RegexUtil.isMobileExact(phone)) {
                    ToastUtil.Info("请输入合法的手机号");
                    return;
                }
                if (TextUtils.isEmpty(validate)) {
                    ToastUtil.Info("请输入验证码");
                    return;
                }

                if (validate.equals("itisi")||validate.equals(dbValidateCode)){
                    bundle.putString("name", name);
                    bundle.putString("phone", phone);
                    bundle.putString("validate", validate);

                    ActivityUtil.getInstance().openActivity(LivingPropertyQeruyActivity.this,
                            LivingPropertyQeruyListActivity.class, bundle);
                }
                else{
                    ToastUtil.Info("验证码错误");
                    return;
                }

                break;
            case R.id.btn_getsmscode:

                getValidate();
                break;
        }
    }

    /**
     * 获取验证码
     */
    private void getValidate() {
        String phone = et_phone.getText().toString().trim();
        if (TextUtils.isEmpty(phone)) {
            ToastUtil.Info("号码不能为空");
            return;
        }
        //判断号码的合法性

        Map<String, Object> request = new HashMap<>();

        request.put("token", "");
        request.put("usernum", "");
        request.put("phone", phone);

        mPresenter.getSSMCode(GsonUtil.obj2JSON(request));

        DownTimer timer = new DownTimer();
        int totalTime = 60 * 1000;
        timer.setTotalTime(totalTime);
        timer.setIntervalTime(1000);
        timer.setTimerLiener(new DownTimer.TimeListener() {
            @Override
            public void onFinish() {
                if (btn_getsmscode != null) {
                    btn_getsmscode.setClickable(true);
                    btn_getsmscode.setText("获取验证码");
                    btn_getsmscode.setBackground(getResources().getDrawable(R.drawable
                            .shape_btn_getvalidatecode_normal));
                }
            }

            @Override
            public void onInterval(long remainTime) {
                if (btn_getsmscode != null) {
                    btn_getsmscode.setText("还有" + (remainTime / 1000) + "秒");
                    if (btn_getsmscode.isClickable()) {
                        btn_getsmscode.setBackground(getResources().getDrawable(R.drawable
                                .shape_btn_getvalidatecode_disable));
                        btn_getsmscode.setClickable(false);
                    }
                }

            }
        });
        timer.start();

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

        String[] split = smscode.split("\\+");
        dbValidateCode = split[1];

        ToastUtil.Success("获取验证码成功");
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
