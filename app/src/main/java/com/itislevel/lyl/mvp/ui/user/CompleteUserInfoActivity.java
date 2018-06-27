package com.itislevel.lyl.mvp.ui.user;

import com.itislevel.lyl.R;
import com.itislevel.lyl.base.RootActivity;
import com.itislevel.lyl.mvp.model.bean.FileUploadBean;
import com.itislevel.lyl.mvp.model.bean.HomeBean;
import com.itislevel.lyl.mvp.model.bean.MyReceiveAddrBean;
import com.itislevel.lyl.mvp.model.bean.RegistBean;
import com.itislevel.lyl.mvp.model.bean.UserInfoBean;

public class CompleteUserInfoActivity extends RootActivity<UserPresenter> implements
        UserContract.View {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_complete_user_info;
    }

    @Override
    protected void initEventAndData() {

        setToolbarTvTitle("完善个人资料");
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
