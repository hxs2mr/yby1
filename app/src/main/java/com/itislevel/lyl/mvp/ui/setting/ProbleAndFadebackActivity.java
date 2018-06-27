package com.itislevel.lyl.mvp.ui.setting;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.itislevel.lyl.R;
import com.itislevel.lyl.base.RootActivity;
import com.itislevel.lyl.mvp.model.bean.FileUploadBean;
import com.itislevel.lyl.mvp.ui.main.customer.CustomerOnlineActivity;
import com.itislevel.lyl.mvp.ui.main.customer.CustomerPhoneActivity;
import com.itislevel.lyl.utils.ActivityUtil;

import butterknife.OnClick;

public class ProbleAndFadebackActivity extends RootActivity<SettingPresenter> implements
        SettingContract.View {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_proble_and_fadeback;
    }

    @Override
    protected void initEventAndData() {
        setToolbarTvTitle("问题反馈");
    }

    @OnClick({R.id.tv_online,R.id.tv_fadeback})
    public void click(View view){
        switch (view.getId()){
            case R.id.tv_online:
                ActivityUtil.getInstance().openActivity(ProbleAndFadebackActivity.this,CustomerPhoneActivity.class);

                break;
            case R.id.tv_fadeback:
                ActivityUtil.getInstance().openActivity(ProbleAndFadebackActivity.this,FacebackActivity.class);
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
