package com.itislevel.lyl.mvp.ui.usermonkey.setting;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.itislevel.lyl.R;
import com.itislevel.lyl.base.RootActivity;
import com.itislevel.lyl.mvp.ui.usermonkey.UserMonkeyContract;
import com.itislevel.lyl.mvp.ui.usermonkey.UserMonkeyPresenter;
import com.itislevel.lyl.utils.UtilsStyle;

/**
 * 作者： XS
 * 邮箱：1363826037@qq.com
 * 描述:
 * 创建时间:  2018\7\10 0010 10:43
 */
public class UserMonkeySettingActivity extends RootActivity<UserMonkeyPresenter>implements UserMonkeyContract.View{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UtilsStyle.setStatusBarMode(this,true); //黑色的主题图标
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_usermonkeysetting;
    }

    @Override
    protected void initEventAndData() {
            setToolWight("支付管理");
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
}
