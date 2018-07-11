package com.itislevel.lyl.mvp.ui.usermonkey.setting;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.itislevel.lyl.R;
import com.itislevel.lyl.base.RootActivity;
import com.itislevel.lyl.mvp.model.bean.BankCardBean;
import com.itislevel.lyl.mvp.model.bean.BlankListBean;
import com.itislevel.lyl.mvp.model.bean.BlankNameBean;
import com.itislevel.lyl.utils.UtilsStyle;

/**
 * 作者： XS
 * 邮箱：1363826037@qq.com
 * 描述:
 * 创建时间:  2018\7\11 0011 10:55
 */
public class SettingChangePwdActivity extends RootActivity<MonkeySettingPresenter> implements MonkeySettingContract.View{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UtilsStyle.setStatusBarMode(this, true); //黑色的主题图标
    }

    @Override
    protected void initInject() {
            getActivityComponent().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_changemonkeypwd;
    }

    @Override
    protected void initEventAndData() {
        setToolWight("修改支付密码");
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
    public void queryBankNameByIdCard(BlankNameBean blankNameBean) {

    }

    @Override
    public void queryBankBranchList(BlankListBean blankNameBean) {

    }

    @Override
    public void bankCardVerification(BankCardBean data) {

    }

    @Override
    public void finishVerification(String data) {

    }

    @Override
    public void getSSMCode(String action) {

    }
}
