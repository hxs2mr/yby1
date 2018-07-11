package com.itislevel.lyl.mvp.ui.usermonkey.setting;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.View;

import com.itislevel.lyl.R;
import com.itislevel.lyl.base.RootActivity;
import com.itislevel.lyl.mvp.ui.usermonkey.UserMonkeyContract;
import com.itislevel.lyl.mvp.ui.usermonkey.UserMonkeyPresenter;
import com.itislevel.lyl.utils.ActivityUtil;
import com.itislevel.lyl.utils.UtilsStyle;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 作者： XS
 * 邮箱：1363826037@qq.com
 * 描述:
 * 创建时间:  2018\7\10 0010 10:43
 */
public class UserMonkeySettingActivity extends RootActivity<UserMonkeyPresenter>implements UserMonkeyContract.View{

    @BindView(R.id.renzhen_linear)
    LinearLayoutCompat renzhen_linear;

    @BindView(R.id.setting_password_linear)
    LinearLayoutCompat setting_password_linear;//修改支付密码

    @BindView(R.id.settforget_linear)
    LinearLayoutCompat settforget_linear;//忘记支付密码

    @BindView(R.id.help_linear)
    LinearLayoutCompat help_linear;//帮助中心


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

    @OnClick({R.id.renzhen_linear,R.id.setting_password_linear,R.id.settforget_linear,R.id.help_linear})
    public void Onclick(View view)
    {
        switch (view.getId())
        {
            case R.id.renzhen_linear://认证中心

                Bundle bundle = new Bundle();
                bundle.putString("flage","0");
                ActivityUtil.getInstance().openActivity(this,SettingUserDataActivity.class,bundle);
                break;
            case R.id.setting_password_linear://修改密码
                ActivityUtil.getInstance().openActivity(this,SettingChangePwdActivity.class);
                break;
            case R.id.settforget_linear://忘记密码
                ActivityUtil.getInstance().openActivity(this,SettingForgeActivity.class);
                break;
            case R.id.help_linear://帮助中心

                break;
        }
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
