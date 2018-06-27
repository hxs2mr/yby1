package com.itislevel.lyl.mvp.ui.setting;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.itislevel.lyl.R;
import com.itislevel.lyl.base.RootActivity;
import com.itislevel.lyl.mvp.model.bean.FileUploadBean;

public class OperationmanualActivity extends RootActivity<SettingPresenter> implements
        SettingContract.View {



    @Override
    protected int getLayoutId() {
        return R.layout.activity_operationmanual;
    }

    @Override
    protected void initEventAndData() {
        setToolbarTvTitle("APP操作手册");

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
    protected void initInject() {
        getActivityComponent().inject(this);
    }
}
