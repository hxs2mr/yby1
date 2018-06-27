package com.itislevel.lyl.mvp.ui.team;

import com.itislevel.lyl.R;
import com.itislevel.lyl.base.RootActivity;
import com.itislevel.lyl.mvp.model.bean.BlessOrderBean;
import com.itislevel.lyl.mvp.model.bean.FileUploadBean;
import com.itislevel.lyl.mvp.model.bean.TeamListBean;
import com.itislevel.lyl.mvp.model.bean.TeamStatusBean;
import com.itislevel.lyl.mvp.model.bean.TeamTypeBean;
import com.itislevel.lyl.mvp.model.bean.TroubleAdviserMyBean;

public class TeamListMyActivity extends RootActivity<TeamPresenter> implements TeamContract.View {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_team_list_my;
    }

    @Override
    protected void initEventAndData() {

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
