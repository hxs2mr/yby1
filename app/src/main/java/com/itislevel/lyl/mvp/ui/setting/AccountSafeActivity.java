package com.itislevel.lyl.mvp.ui.setting;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.itislevel.lyl.R;
import com.itislevel.lyl.app.Constants;
import com.itislevel.lyl.base.RootActivity;
import com.itislevel.lyl.mvp.model.bean.FileUploadBean;
import com.itislevel.lyl.mvp.ui.user.LoginActivity;
import com.itislevel.lyl.utils.ActivityUtil;
import com.itislevel.lyl.utils.GsonUtil;
import com.itislevel.lyl.utils.RegexUtil;
import com.itislevel.lyl.utils.SAToast;
import com.itislevel.lyl.utils.SharedPreferencedUtils;
import com.itislevel.lyl.utils.ToastUtil;
import com.itislevel.lyl.widget.DownTimer;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class AccountSafeActivity extends RootActivity<SettingPresenter> implements
        SettingContract.View {

//    btn_save et_new_phone tv_new_phone
//    btn_getsmscode et_validate et_old_phone

    @BindView(R.id.btn_save)
    Button btn_save;

    @BindView(R.id.btn_getsmscode)
    Button btn_getsmscode;

    @BindView(R.id.et_new_phone)
    EditText et_new_phone;

    @BindView(R.id.et_validate)
    EditText et_validate;

    @BindView(R.id.et_old_phone)
    EditText et_old_phone;

    String serverValidate = "";

    @Override
    protected int getLayoutId() {
        return R.layout.activity_account_safe;
    }

    @Override
    protected void initEventAndData() {

        setToolbarTvTitle("账户与安全");

    }

    @OnClick({R.id.btn_save, R.id.btn_getsmscode})
    public void click(View view) {

        switch (view.getId()) {

            case R.id.btn_getsmscode://获取验证码

                String oldPhone = et_old_phone.getText().toString().trim();
                if (TextUtils.isEmpty(oldPhone)) {

                    SAToast.makeText(this,"新手机号不能为空",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!RegexUtil.isMobileExact(oldPhone)) {
                    SAToast.makeText(this,"手机号不合法",Toast.LENGTH_SHORT).show();
                    return;
                }
                getValidate();

                break;
            case R.id.btn_save://保存
                String oldPhone1 = et_old_phone.getText().toString().trim();
                if (TextUtils.isEmpty(oldPhone1)) {
                    SAToast.makeText(this,"新手机号不能为空",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!RegexUtil.isMobileExact(oldPhone1)) {
                    SAToast.makeText(this,"新手机号不合法",Toast.LENGTH_SHORT).show();
                    return;
                }

                String newPhone = et_new_phone.getText().toString().trim();
                if (TextUtils.isEmpty(newPhone)) {
                    SAToast.makeText(this,"旧手机号不能为空",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!RegexUtil.isMobileExact(newPhone)) {
                    SAToast.makeText(this,"旧手机号不合法",Toast.LENGTH_SHORT).show();
                    return;
                }

                String validate = et_validate.getText().toString().trim();
                if (TextUtils.isEmpty(validate)) {
                    SAToast.makeText(this,"验证码不能为空",Toast.LENGTH_SHORT).show();
                    return;
                }

                // 597 446
//                if (!validate.equals(serverValidate)) {
//                    ToastUtil.Info("验证码错误");
//                    return;
//                }


                Map<String, Object> request = new HashMap<>();
                request.put("token", SharedPreferencedUtils.getStr(Constants.USER_TOKEN));
                request.put("usernum", SharedPreferencedUtils.getStr(Constants.USER_NUM));
                request.put("userid", SharedPreferencedUtils.getStr(Constants.USER_ID));
                request.put("Phoneold", newPhone);
                request.put("phone", oldPhone1);

                mPresenter.updatePhone(GsonUtil.obj2JSON(request));


                break;
        }


    }

    @Override
    public void showContent(String msg) {

    }

    @Override
    public void getSSMCode(String smscode) {
        String[] split = smscode.split("\\+");
        serverValidate = split[1];
        SAToast.makeText(this,"获取验证码成功",Toast.LENGTH_SHORT).show();
    }

    /**
     * 获取验证码
     */
    private void getValidate() {
        String phone = et_old_phone.getText().toString().trim();

        Map<String, Object> request = new HashMap<>();
        request.put("token", "");
        request.put("usernum", "");
        request.put("phone", phone);

        mPresenter.getSSMCode(GsonUtil.obj2JSON(request));

        DownTimer timer = new DownTimer();
        int totalTime = 30 * 1000;
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
                    btn_getsmscode.setText( (remainTime / 1000) + "秒后重新发送");
                    if (btn_getsmscode.isClickable()) {
                        btn_getsmscode.setBackground(getResources().getDrawable(R.drawable
                                .shape_btn_getvalidatecode_normal));
                        btn_getsmscode.setClickable(false);
                    }
                }

            }
        });
        timer.start();

    }

    @Override
    public void updatePhone(String msg) {
        SAToast.makeText(this,"手机号修改成功",Toast.LENGTH_SHORT).show();
        ActivityUtil.getInstance().openActivity(AccountSafeActivity.this, LoginActivity.class);
        finish();

    }

    @Override
    public void userAddFeedback(String msg) {

    }

    @Override
    public void uploadHeader(FileUploadBean fileUploadBean) {

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
