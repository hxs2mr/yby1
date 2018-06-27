package com.itislevel.lyl.mvp.ui.team;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

import com.flyco.animation.BaseAnimatorSet;
import com.flyco.animation.SlideEnter.SlideBottomEnter;
import com.flyco.animation.SlideExit.SlideBottomExit;
import com.flyco.dialog.listener.OnBtnClickL;
import com.flyco.dialog.widget.MaterialDialog;
import com.flyco.dialog.widget.NormalDialog;
import com.itislevel.lyl.R;
import com.itislevel.lyl.base.RootActivity;
import com.itislevel.lyl.mvp.model.bean.BlessOrderBean;
import com.itislevel.lyl.mvp.model.bean.FileUploadBean;
import com.itislevel.lyl.mvp.model.bean.TeamListBean;
import com.itislevel.lyl.mvp.model.bean.TeamStatusBean;
import com.itislevel.lyl.mvp.model.bean.TeamTypeBean;
import com.itislevel.lyl.mvp.model.bean.TroubleAdviserMyBean;
import com.itislevel.lyl.utils.ActivityUtil;
import com.itislevel.lyl.utils.ToastUtil;

import butterknife.BindView;
import butterknife.OnClick;

public class TeamAgreementActivity extends RootActivity<TeamPresenter> implements TeamContract.View {


    Bundle bundle = new Bundle();


    @BindView(R.id.rb_agree)
    RadioButton rb_agree;

    @BindView(R.id.tv_agreement)
    TextView tv_agreement;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_team_agreement;
    }



    @Override
    protected void initEventAndData() {

        bundle = this.getIntent().getExtras();

        setToolbarTvTitle("阅读协议");
        setToolbarMoreTxt("下一步");
        setToolbarMoreClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rb_agree.isChecked()){
                    ActivityUtil.getInstance().openActivity(TeamAgreementActivity.this, TeamSupplementActivity
                            .class,bundle);
                }else{
                    ToastUtil.Warning("请阅读协议,并点击已阅读");
                }

            }
        });
    }
    BaseAnimatorSet mBasIn = new SlideBottomEnter();
    BaseAnimatorSet mBasOut = new SlideBottomExit();

    @OnClick({R.id.tv_agreement})
    public void click(View view){
//        switch (view.getId()){
//            case R.id.tv_agreement:
////                String content="xieyi";
//                String content = getResources().getString(R.string.team_regist_argrement);
//                final MaterialDialog dialog = new MaterialDialog(this);
//                dialog.content(content)//
//                        .title("顾问团队注册协议")
//                        .btnNum(1)
//                        .btnText("确认")// 取消, "确定"
//                        .showAnim(mBasIn)//
//                        .dismissAnim(mBasOut)//
//                        .show();
//
//                dialog.getCreateView().setVerticalScrollBarEnabled(true);
//
//
//                dialog.setOnBtnClickL(
//                        new OnBtnClickL() {//left btn click listener
//                            @Override
//                            public void onBtnClick() {
//                                dialog.dismiss();
//                            }
//                        }
//
//                );
//                break;

            ActivityUtil.getInstance().openActivity(TeamAgreementActivity.this,TeamUseAgreementActivity.class);
//        }
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
