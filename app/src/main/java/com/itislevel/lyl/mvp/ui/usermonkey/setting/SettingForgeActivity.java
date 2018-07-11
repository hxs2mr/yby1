package com.itislevel.lyl.mvp.ui.usermonkey.setting;

import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.itislevel.lyl.R;
import com.itislevel.lyl.base.RootActivity;
import com.itislevel.lyl.mvp.ui.usermonkey.UserMonkeyContract;
import com.itislevel.lyl.mvp.ui.usermonkey.UserMonkeyPresenter;
import com.itislevel.lyl.utils.ActivityUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 作者： XS
 * 邮箱：1363826037@qq.com
 * 描述:忘记密码  重新选择银行卡重新认证
 * 创建时间:  2018\7\11 0011 11:04
 */
public class SettingForgeActivity extends RootActivity<UserMonkeyPresenter> implements UserMonkeyContract.View {
    @BindView(R.id.forget_title)
    AppCompatTextView forget_title;//title表示是否有银行卡

    @BindView(R.id.forget_tv)
    AppCompatTextView forget_tv;//表示是否需要绑定银行卡
    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }
    @Override
    protected int getLayoutId() {
        return R.layout.activity_settingforget;
    }

    @Override
    protected void initEventAndData() {

    }
    @OnClick(R.id.forget_next_linear)
    public  void Onclick(View view)
    {
        Bundle bundle = new Bundle();
        bundle.putString("flage","1");
        ActivityUtil.getInstance().openActivity(this,SettingUserDataActivity.class,bundle);
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
