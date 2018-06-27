package com.itislevel.lyl.mvp.ui.user;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.itislevel.lyl.R;
import com.itislevel.lyl.app.Constants;
import com.itislevel.lyl.base.RootActivity;
import com.itislevel.lyl.mvp.model.bean.FileUploadBean;
import com.itislevel.lyl.mvp.model.bean.HomeBean;
import com.itislevel.lyl.mvp.model.bean.MyReceiveAddrBean;
import com.itislevel.lyl.mvp.model.bean.RegistBean;
import com.itislevel.lyl.mvp.model.bean.UserInfoBean;
import com.itislevel.lyl.utils.ActivityUtil;
import com.itislevel.lyl.utils.GsonUtil;
import com.itislevel.lyl.utils.SharedPreferencedUtils;
import com.itislevel.lyl.utils.ToastUtil;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class UpdatePasswordActivity extends RootActivity<UserPresenter> implements UserContract
        .View {

//    et_current_password  et_new_passwrod  et_confirm_password btn_save

    @BindView(R.id.et_current_password)
    EditText et_current_password;

    @BindView(R.id.et_new_passwrod)
    EditText et_new_passwrod;

    @BindView(R.id.et_confirm_password)
    EditText et_confirm_password;



    @Override
    protected int getLayoutId() {
        return R.layout.activity_update_password;
    }

    @Override
    protected void initEventAndData() {

    }

    @OnClick({R.id.btn_save})
    public void click(View view){
        switch (view.getId()){
            case R.id.btn_save:

                String currentPwd = et_current_password.getText().toString().trim();
                String newPwd = et_new_passwrod.getText().toString().trim();
                String confPwd = et_confirm_password.getText().toString().trim();

                if (TextUtils.isEmpty(currentPwd)){
                    ToastUtil.Info("请输入旧密码");
                    return;
                }
                if (TextUtils.isEmpty(newPwd)){
                    ToastUtil.Info("请输入新密码");
                    return;
                }
                if (TextUtils.isEmpty(confPwd)){
                    ToastUtil.Info("请输入确认密码");
                    return;
                }

                if (newPwd.length()<6||newPwd.length()>16){
                    ToastUtil.Info("密码长度为6-16位");
                    return;
                }

                if (TextUtils.isEmpty(confPwd)){
                    ToastUtil.Info("请输入确认密码");
                    return;
                }
                if (!confPwd.equals(newPwd)){
                    ToastUtil.Info("两次密码不一致");
                    return;
                }
                Map<String, Object> request = new HashMap<>();

                request.put("token", SharedPreferencedUtils.getStr(Constants.USER_TOKEN));
                request.put("usernum", SharedPreferencedUtils.getStr(Constants.USER_NUM));
                request.put("userid", SharedPreferencedUtils.getStr(Constants.USER_ID));
                request.put("password", currentPwd);
                request.put("password1", confPwd);

                mPresenter.userModifyPassword(GsonUtil.obj2JSON(request));
                break;
        }
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
    public void getSSMCode(String smscode) {

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

        ToastUtil.Success("密码修改成功");
        //应该跳转登录界面
        ActivityUtil.getInstance().openActivity(this,LoginActivity.class);
        ActivityUtil.getInstance().closeActivity(this);

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
