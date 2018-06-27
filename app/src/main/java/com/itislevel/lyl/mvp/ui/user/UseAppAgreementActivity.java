package com.itislevel.lyl.mvp.ui.user;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

import com.itislevel.lyl.R;
import com.itislevel.lyl.base.RootActivity;
import com.itislevel.lyl.mvp.model.bean.FileUploadBean;
import com.itislevel.lyl.mvp.model.bean.HomeBean;
import com.itislevel.lyl.mvp.model.bean.MyReceiveAddrBean;
import com.itislevel.lyl.mvp.model.bean.RegistBean;
import com.itislevel.lyl.mvp.model.bean.UserInfoBean;

import butterknife.BindView;

public class UseAppAgreementActivity extends RootActivity<UserPresenter> implements UserContract.View {

    @BindView(R.id.webView)
    WebView webView;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_use_app_agreement;
    }

    @Override
    protected void initEventAndData() {
        setToolbarTvTitle("用户注册协议");

        webView.loadUrl("file:///android_asset/user_use_agreement.html");

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
    protected void initInject() {

    }
}
