package com.itislevel.lyl.mvp.ui.team;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.itislevel.lyl.R;
import com.itislevel.lyl.app.Constants;
import com.itislevel.lyl.base.RootActivity;
import com.itislevel.lyl.mvp.model.bean.BlessOrderBean;
import com.itislevel.lyl.mvp.model.bean.FileUploadBean;
import com.itislevel.lyl.mvp.model.bean.TeamListBean;
import com.itislevel.lyl.mvp.model.bean.TeamStatusBean;
import com.itislevel.lyl.mvp.model.bean.TeamTypeBean;
import com.itislevel.lyl.mvp.model.bean.TroubleAdviserMyBean;
import com.itislevel.lyl.utils.GsonUtil;
import com.itislevel.lyl.utils.SharedPreferencedUtils;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;

public class TeamWaitingForVerifyActivity extends RootActivity<TeamPresenter> implements
        TeamContract.View {

    @BindView(R.id.btn_waiting_verify)
    Button btn_waiting_verify;

    @BindView(R.id.tv_success)
    TextView tv_success;

    @BindView(R.id.tv_error)
    TextView tv_error;



    @Override
    protected int getLayoutId() {
        return R.layout.activity_team_waiting_for_verify;
    }

    @Override
    protected void initEventAndData() {
        setToolbarTvTitle("顾问注册");
        loadData();
    }

    private void loadData() {
        Map<String, String> request = new HashMap<>();
        request.put("token",SharedPreferencedUtils.getStr(Constants.USER_TOKEN));
        request.put("usernum",SharedPreferencedUtils.getStr(Constants.USER_NUM));
        request.put("adviserid",SharedPreferencedUtils.getStr(Constants.USER_ID));

        mPresenter.teamStatus(GsonUtil.obj2JSON(request));
    }

    @Override
    public void showContent(String msg) {

    }

    @Override
    public void teamRegister(String message) {

    }

    @Override
    public void teamStatus(TeamStatusBean teamStatusBean) {
        // (-1 显示在线注册按钮，0 审核中（查看审核进度），1审核通过（隐藏注册按钮）)
        //tv_error tv_success btn_waiting_verify

        if (teamStatusBean.getIscheck().equals("0")){
            tv_error.setVisibility(View.GONE);
            tv_success.setVisibility(View.GONE);
            btn_waiting_verify.setVisibility(View.VISIBLE);

        }else if(teamStatusBean.getIscheck().equals("1")){
            tv_error.setVisibility(View.GONE);
            tv_success.setVisibility(View.VISIBLE);
            btn_waiting_verify.setVisibility(View.GONE);

        }
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
