package com.itislevel.lyl.mvp.ui.user;

import android.graphics.Color;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatTextView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.flyco.animation.FadeEnter.FadeEnter;
import com.flyco.animation.FadeExit.FadeExit;
import com.flyco.dialog.widget.NormalDialog;
import com.itislevel.lyl.R;
import com.itislevel.lyl.app.Constants;
import com.itislevel.lyl.base.RootActivity;
import com.itislevel.lyl.mvp.model.bean.FileUploadBean;
import com.itislevel.lyl.mvp.model.bean.HomeBean;
import com.itislevel.lyl.mvp.model.bean.MyReceiveAddrBean;
import com.itislevel.lyl.mvp.model.bean.RegistBean;
import com.itislevel.lyl.mvp.model.bean.UserInfoBean;
import com.itislevel.lyl.mvp.model.http.request.SMSValidateRequest;
import com.itislevel.lyl.utils.ActivityUtil;
import com.itislevel.lyl.utils.GsonUtil;
import com.itislevel.lyl.utils.SharedPreferencedUtils;
import com.itislevel.lyl.utils.ToastUtil;
import com.itislevel.lyl.widget.DownTimer;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import qiu.niorgai.StatusBarCompat;

import static com.itislevel.lyl.utils.FormatUtil.isMobileNO;

public class ResetPasswordActivity extends RootActivity<UserPresenter> implements UserContract.View
        , View.OnClickListener {


    @BindView(R.id.forget_ok)
    AppCompatButton btn_save;

    @BindView(R.id.forget_miao)
    AppCompatTextView btn_getsmscode;

    @BindView(R.id.forget_password)
    TextInputEditText et_password;

    @BindView(R.id.forget_password_agin)
    TextInputEditText et_password_conf;


    @BindView(R.id.forget_phone)
    TextInputEditText et_phone;

    @BindView(R.id.forget_yzm)
    AppCompatEditText et_validate;
    private String ssmCode;


    @Override
    protected int getLayoutId() {
        return R.layout.forgetpassword_fragment;
    }

    @Override
    protected void initEventAndData() {
        setToolbarTvTitle("重置密码");//toolbar中返回是默认开启的
        StatusBarCompat.translucentStatusBar(this, true);
        btn_save.setOnClickListener(this);
        btn_getsmscode.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.forget_miao:
                if(isMobileNO( et_phone.getText().toString()))
                {
                    getValidate();
                }else {
                    et_phone.setError("手机格式不合法！");
                }
                break;

            case R.id.forget_ok:
                String password = et_password.getText().toString().trim();
                String passwordConf = et_password_conf.getText().toString().trim();
                String phone = et_phone.getText().toString().trim();
                String validate = et_validate.getText().toString().trim();

                if (TextUtils.isEmpty(password)) {
                    ToastUtil.Info("密码不能为空");
                    return;
                }
                if (TextUtils.isEmpty(passwordConf)) {
                    ToastUtil.Info("确认密码不能为空");
                    return;
                }
                if (TextUtils.isEmpty(phone)) {
                    ToastUtil.Info("手机号不能为空");
                    return;
                }
                if (TextUtils.isEmpty(validate)) {
                    ToastUtil.Info("验证码不能为空");
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    ToastUtil.Info("密码不能为空");
                    return;
                }

                if (!password.equals(passwordConf)) {
                    ToastUtil.Info("两次密码不一样");
                    return;
                }

                if (TextUtils.isEmpty(ssmCode)) {
                    ToastUtil.Info("请获取验证码");
                    return;
                }

                if (!validate.equals(ssmCode)) {
                    ToastUtil.Info("验证码错误");
                    return;
                }
                Map<String, Object> request = new HashMap<>();

                request.put("token", SharedPreferencedUtils.getStr(Constants.USER_TOKEN));
                request.put("usernum", SharedPreferencedUtils.getStr(Constants.USER_NUM));
                request.put("userid", SharedPreferencedUtils.getStr(Constants.USER_ID));
                request.put("password", password);
                request.put("phone", phone);

                mPresenter.userForgetPassword(GsonUtil.obj2JSON(request));

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
    public void login(UserInfoBean response) {

    }

    @Override
    public void getValidateCode(String validateCode) {

    }

    @Override
    public void getSSMCode(String action) {
        ToastUtil.Success("获取验证码成功");
        String[] split = action.split("\\+");
        ssmCode = split[1];

    }

    @Override
    public void regist(RegistBean registBean) {

    }

    @Override
    public void uploadHeader(FileUploadBean fileUploadBean) {

    }

    @Override
    public void userPerfectPersonal(String message) {

    }

    @Override
    public void userModifyPassword(String message) {

    }

    @Override
    public void userModifyNickname(String message) {

    }

    @Override
    public void userFindRecAddress(MyReceiveAddrBean address) {

    }

    @Override
    public void userUpdateRecAddress(String message) {

    }

    @Override
    public void userGiftmy(Object message) {

    }

    @Override
    public void userModifyHeader(String message) {

    }

    @Override
    public void userForgetPassword(String message) {
        ToastUtil.Success("密码重置成功");
        ActivityUtil.getInstance().closeActivity(this);
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
