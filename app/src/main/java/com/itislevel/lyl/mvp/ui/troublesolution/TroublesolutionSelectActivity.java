package com.itislevel.lyl.mvp.ui.troublesolution;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.zhouwei.library.CustomPopWindow;
import com.itislevel.lyl.R;
import com.itislevel.lyl.app.Constants;
import com.itislevel.lyl.base.RootActivity;
import com.itislevel.lyl.mvp.model.bean.BlessOrderBean;
import com.itislevel.lyl.mvp.model.bean.FileUploadBean;
import com.itislevel.lyl.mvp.model.bean.MeiZiBean;
import com.itislevel.lyl.mvp.model.bean.TroubleAddBean;
import com.itislevel.lyl.mvp.model.bean.TroubleAdviserMyBean;
import com.itislevel.lyl.mvp.model.bean.TroubleCommentAdd;
import com.itislevel.lyl.mvp.model.bean.TroubleListBean;
import com.itislevel.lyl.mvp.model.bean.TroubleTypeBean;
import com.itislevel.lyl.mvp.ui.team.TeamListActivity;
import com.itislevel.lyl.utils.ActivityUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class TroublesolutionSelectActivity extends RootActivity<TroublesolutionPresenter> implements
        TroublesolutionContract.View {
    Bundle bundle = new Bundle();
    private String PROVINCE_ID="";
    private String CITY_ID="";
    private String COUNTY_ID=""; //很多情况下是空的



    @BindView(R.id.btn_normal)
    Button btn_normal;//普通解答

    @BindView(R.id.btn_adviser)
    Button btn_adviser; //顾问团队

    @Override
    protected int getLayoutId() {
        return R.layout.activity_troublesolution_select;
    }

    @Override
    protected void initEventAndData() {
        bundle = this.getIntent().getExtras();
        String title = bundle.getString(Constants.TROUBLE_TITLE);

        PROVINCE_ID=bundle.getString(Constants.PROVINCE_ID);
        CITY_ID=bundle.getString(Constants.CITY_ID);
        COUNTY_ID=bundle.getString(Constants.COUNTY_ID);


        setToolbarTvTitle(title);

        setToolbarMoreTxt("我的咨询");

        String circleTitle=bundle.getString(Constants.TROUBLE_TEAM_TYPE_NAME)+"圈";

        TextView tvMorView = getTvMorView();

        setToolbarMoreClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View popView = View.inflate(TroublesolutionSelectActivity.this, R.layout
                        .item_pop_trouble, null);
                final CustomPopWindow popWindow = new CustomPopWindow.PopupWindowBuilder
                        (TroublesolutionSelectActivity.this)
                        .enableBackgroundDark(true) //弹出popWindow时，背景是否变暗
                        .setBgDarkAlpha(0.7f) // 控制亮度
                        .setOutsideTouchable(true)//是否PopupWindow 以外触摸dissmiss
                        .setView(popView)
                        .create();
                popWindow.showAsDropDown(tvMorView, -70, 40);

                TextView tv_advise=popView.findViewById(R.id.tv_advise);
                TextView tv_normal=popView.findViewById(R.id.tv_normal);
                TextView tv_circle=popView.findViewById(R.id.tv_circle);

                tv_circle.setText(circleTitle);

                tv_advise.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ActivityUtil.getInstance().openActivity(TroublesolutionSelectActivity.this,TroubleAdviserMyActivity.class,bundle);

                        popWindow.dissmiss();
                    }
                });
                tv_normal.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ActivityUtil.getInstance().openActivity(TroublesolutionSelectActivity.this,TroubleNormalMyActivity.class,bundle);
                        popWindow.dissmiss();

                    }
                });
                tv_circle.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ActivityUtil.getInstance().openActivity(TroublesolutionSelectActivity.this,TroublesolutionListActivity.class,bundle);
                        popWindow.dissmiss();

                    }
                });

            }
        });


    }
    @OnClick({R.id.btn_normal,R.id.btn_adviser})
    public void click(View v){
        switch (v.getId()){
            case R.id.btn_normal: //普通解答
                ActivityUtil.getInstance().openActivity(TroublesolutionSelectActivity.this,TroubleNormalAddActivity.class,bundle);
                break;
            case R.id.btn_adviser://顾问团队

//                ActivityUtil.getInstance().openActivity(TroublesolutionSelectActivity.this,TroubleAdviserActivity_back.class,bundle);
                ActivityUtil.getInstance().openActivity(TroublesolutionSelectActivity.this,TeamListActivity.class,bundle);
                break;
        }
    }

    @Override
    public void showContent(String msg) {

    }

    @Override
    public void showDataList(List<MeiZiBean> meiZiBeans) {

    }

    @Override
    public void troubleAdd(TroubleAddBean troubleAdd) {

    }

    @Override
    public void troubleList(TroubleListBean troubleListBean) {

    }

    @Override
    public void troubleListMy(TroubleListBean troubleListBean) {

    }

    @Override
    public void troubleDel(String action) {

    }

    @Override
    public void troubleCommentReplay(TroubleCommentAdd troubleCommentAdd) {

    }

    @Override
    public void troubleCommentDel(String action) {

    }

    @Override
    public void troubleType(TroubleTypeBean troubleTypeBean) {

    }

    @Override
    public void teamProblemAdd(String message) {

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
    public void teamViewCount(String message) {

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
