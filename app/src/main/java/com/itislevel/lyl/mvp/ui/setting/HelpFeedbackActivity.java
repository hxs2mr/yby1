package com.itislevel.lyl.mvp.ui.setting;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.itislevel.lyl.R;
import com.itislevel.lyl.base.RootActivity;
import com.itislevel.lyl.mvp.model.bean.FileUploadBean;
import com.itislevel.lyl.utils.ActivityUtil;
import com.itislevel.lyl.utils.ToastUtil;

import butterknife.BindView;
import butterknife.OnClick;

public class HelpFeedbackActivity extends RootActivity<SettingPresenter> implements
        SettingContract.View {


    //    tv_fadeback tv_problem tv_operate

    @BindView(R.id.tv_fadeback)
    TextView tv_fadeback;

    @BindView(R.id.tv_problem)
    TextView tv_problem;

    @BindView(R.id.tv_operate)
    TextView tv_operate;



    @Override
    protected int getLayoutId() {
        return R.layout.activity_help_feedback;
    }


    @Override
    protected void initEventAndData() {
        setToolbarTvTitle("帮助与反馈");
    }


    @OnClick({R.id.tv_fadeback,R.id.tv_problem,R.id.tv_operate})
    public void click(View view){
        switch (view.getId()){
            case R.id.tv_fadeback:
                ActivityUtil.getInstance().openActivity(HelpFeedbackActivity.this,FacebackActivity.class);
                break;
            case R.id.tv_problem:
                ActivityUtil.getInstance().openActivity(HelpFeedbackActivity.this,CommonproblemActivity.class);
                break;
            case R.id.tv_operate:
                ActivityUtil.getInstance().openActivity(HelpFeedbackActivity.this,OperationmanualActivity.class);
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
