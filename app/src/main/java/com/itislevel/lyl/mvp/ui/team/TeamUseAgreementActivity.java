package com.itislevel.lyl.mvp.ui.team;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

import com.itislevel.lyl.R;
import com.itislevel.lyl.base.RootActivity;
import com.itislevel.lyl.mvp.model.bean.BlessOrderBean;
import com.itislevel.lyl.mvp.model.bean.FileUploadBean;
import com.itislevel.lyl.mvp.model.bean.TeamListBean;
import com.itislevel.lyl.mvp.model.bean.TeamStatusBean;
import com.itislevel.lyl.mvp.model.bean.TeamTypeBean;
import com.itislevel.lyl.mvp.model.bean.TroubleAdviserMyBean;

import butterknife.BindView;

public class TeamUseAgreementActivity extends RootActivity<TeamPresenter> implements TeamContract.View {

    @BindView(R.id.webView)
    WebView webView;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_team_use_agreement;
    }

    @Override
    protected void initEventAndData() {
        setToolbarTvTitle("顾问注册协议");
        webView.loadUrl("file:///android_asset/guwen.html");

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
    public void teamRegister(String message) {

    }

    @Override
    public void teamStatus(TeamStatusBean teamStatusBean) {

    }

    @Override
    public void teamList(TeamListBean teamListBean) {

    }

    @Override
    public void teamViewCount(String message) {

    }

    @Override
    public void teamProblemAdd(BlessOrderBean message) {

    }

    @Override
    public void teamProblemList(Object object) {

    }

    @Override
    public void teamReplay(Object object) {

    }

    @Override
    public void teamMyProblemList(TroubleAdviserMyBean troubleAdviserMyBean) {

    }

    @Override
    public void teamType(TeamTypeBean teamTypeBean) {

    }

    @Override
    public void uploadHeader(FileUploadBean fileUploadBean) {

    }

    @Override
    protected void initInject() {

    }
}
