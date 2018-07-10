package com.itislevel.lyl.mvp.ui.backmonkey;

import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutCompat;
import android.text.TextUtils;
import android.view.View;

import com.google.gson.Gson;
import com.itislevel.lyl.R;
import com.itislevel.lyl.app.Constants;
import com.itislevel.lyl.base.RootActivity;
import com.itislevel.lyl.mvp.model.bean.FanloginBean;
import com.itislevel.lyl.mvp.ui.main.mine.fan.PersonShanActivity;
import com.itislevel.lyl.utils.ActivityUtil;
import com.itislevel.lyl.utils.GsonUtil;
import com.itislevel.lyl.utils.SharedPreferencedUtils;
import com.itislevel.lyl.utils.ToastUtil;
import com.itislevel.lyl.widget.DownTimer;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

import static com.itislevel.lyl.utils.FormatUtil.isMobileNO;

/**
 * 作者： XS
 * 邮箱：1363826037@qq.com
 * 描述: 我是商家登录
 * 创建时间:  2018\7\3 0003 09:11
 */
public class FanxianLoginActivity extends RootActivity<FanxianLoginPresenter>implements FanxianLoginContract.View {

    @BindView(R.id.back_linear)
    LinearLayoutCompat back_linear;

    @BindView(R.id.user_phone)
    TextInputEditText user_phone;

    @BindView(R.id.user_code)
    TextInputEditText user_code;//验证码

    @BindView(R.id.login_button)
    AppCompatButton login_button;

    @BindView(R.id.tv_validate)
    AppCompatTextView tv_validate;//获取验证码

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_fanxianlogin;
    }

    @Override
    protected void initEventAndData() {

    }

    private boolean checkForm()
    {
        String title_s = user_phone.getText().toString();
        String title_chenhu_s = user_code.getText().toString();

        //检查输入的文本是否正确
        boolean isPass = true;
        if(title_s.isEmpty())
        {
            user_phone.setError("输入手机号");
            isPass = false;
        }else {
            if(!isMobileNO(title_s))
            {
                user_phone.setError("手机格式不合法！");
            }else {
                user_phone.setError(null);
            }
        }
        if(title_chenhu_s.isEmpty())
        {
            user_code.setError("输入验证码");
            isPass = false;
        }else {
            user_code.setError(null);
        }

        return isPass;
    }

    @OnClick({R.id.back_linear,R.id.login_button,R.id.tv_validate})
    public  void Onclick(View view){
        switch (view.getId())
        {
            case R.id.back_linear:
                ActivityUtil.getInstance().closeActivity(this);
                break;
            case R.id.login_button:
                if(checkForm())
                {
                    login();//登录接口
                }
                break;
            case R.id.tv_validate:
                if(isMobileNO( user_phone.getText().toString()))
                {
                    getValidate();
                }else {
                    user_phone.setError("手机格式不合法！");
                }

                break;
        }
    }
    private void getValidate() {//获取验证码
        String phone = user_phone.getText().toString().trim();
        if (TextUtils.isEmpty(phone)) {
            ToastUtil.Info("号码不能为空");
            return;
        }
        //判断号码的合法性

        Map<String, Object> request = new HashMap<>();

        request.put("token","");
        request.put("usernum", "");
        request.put("phone", phone);

        mPresenter.getSSMCode(GsonUtil.obj2JSON(request));
    }

    private void login() {
        loadingDialog.show();
        Map<String,String> request = new HashMap<>();
        request.put("token", SharedPreferencedUtils.getStr(Constants.USER_TOKEN,""));
        request.put("usernum", SharedPreferencedUtils.getStr(Constants.USER_NUM,""));
        request.put("phone", user_phone.getText().toString().trim());
        request.put("randcode", user_code.getText().toString().trim());
        mPresenter.merchantlogin(GsonUtil.obj2JSON(request));
    }

    @Override
    public void stateError(Throwable e) {
        super.stateError(e);
        ToastUtil.Info("当前账号不是商家账号!");
        loadingDialog.dismiss();
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
    public void merchantlogin(FanloginBean bean) {//登录成功
        loadingDialog.dismiss();
        //测试的地方
        SharedPreferencedUtils.setBool("fan_login", true);
        SharedPreferencedUtils.setStr("fan_merchantid", bean.getMerchantid()+"");
        SharedPreferencedUtils.setStr("fan_provname", bean.getProvname()+"");
        SharedPreferencedUtils.setStr("fan_cityname", bean.getCityname()+"");
        SharedPreferencedUtils.setStr("fan_companyname", bean.getCompanyname()+"");
        SharedPreferencedUtils.setStr("fan_cuntryname", bean.getCuntryname()+"");
        SharedPreferencedUtils.setLong("fan_createdtime", bean.getCreatedtime());
        SharedPreferencedUtils.setStr("fan_linkman", bean.getLinkman()+"");
        SharedPreferencedUtils.setStr("fan_linkaddress", bean.getLinkaddress()+"");
        SharedPreferencedUtils.setStr("fan_linkphone", bean.getLinkphone()+"");
        ActivityUtil.getInstance().openActivity(this,PersonShanActivity.class);
        ActivityUtil.getInstance().closeActivity(this);
    }

    @Override
    public void getSSMCode(String action) {
        DownTimer timer = new DownTimer();
        int totalTime = 60 * 1000;
        timer.setTotalTime(totalTime);
        timer.setIntervalTime(1000);
        timer.setTimerLiener(new DownTimer.TimeListener() {
            @Override
            public void onFinish() {
                if (tv_validate != null) {
                    tv_validate.setClickable(true);
                    tv_validate.setText("获取验证码");
                    tv_validate.setBackground(getResources().getDrawable(R.drawable
                            .pro_yan_shape));
                }
            }

            @Override
            public void onInterval(long remainTime) {
                if (tv_validate != null) {
                    tv_validate.setText("还有" + (remainTime / 1000) + "秒");
                    if (tv_validate.isClickable()) {
                        tv_validate.setBackground(getResources().getDrawable(R.drawable
                                .shape_btn_getvalidatecode_disable));
                        tv_validate.setClickable(false);
                    }
                }

            }
        });
        timer.start();
    }
}
