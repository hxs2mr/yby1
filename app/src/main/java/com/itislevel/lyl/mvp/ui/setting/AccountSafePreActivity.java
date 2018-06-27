package com.itislevel.lyl.mvp.ui.setting;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.itislevel.lyl.R;
import com.itislevel.lyl.app.Constants;
import com.itislevel.lyl.base.RootActivity;
import com.itislevel.lyl.mvp.model.bean.FileUploadBean;
import com.itislevel.lyl.utils.ActivityUtil;
import com.itislevel.lyl.utils.SharedPreferencedUtils;

import butterknife.BindView;
import butterknife.OnClick;

public class AccountSafePreActivity extends RootActivity<SettingPresenter> implements
        SettingContract.View {


    @BindView(R.id.tv_phone)
    TextView tv_phone;

    @BindView(R.id.tv_usernum)
    TextView tv_usernum;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_account_safe_pre;
    }

    @Override
    protected void initEventAndData() {
        setToolbarTvTitle("账户与安全");

        tv_usernum.setText(SharedPreferencedUtils.getStr(Constants.USER_NUM));
        tv_phone.setText(SharedPreferencedUtils.getStr(Constants.USER_PHONE));

    }

    @OnClick(R.id.tv_phone_linear)
    public void click(View view){
        switch (view.getId()){
            case R.id.tv_phone_linear:
                ActivityUtil.getInstance().openActivity(AccountSafePreActivity.this, AccountSafeActivity.class);
                break;
        }
    }

    @Override
    public void showContent(String msg) {

    }

    @Override
    public void getSSMCode(String smscode) {

    }

    @Override
    public void updatePhone(String msg) {

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
