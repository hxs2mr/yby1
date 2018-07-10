package com.itislevel.lyl.mvp.ui.usermonkey;

import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.View;

import com.itislevel.lyl.R;
import com.itislevel.lyl.base.RootActivity;
import com.itislevel.lyl.mvp.ui.usermonkey.putrecord.PutRecordActivity;
import com.itislevel.lyl.mvp.ui.usermonkey.setting.UserMonkeySettingActivity;
import com.itislevel.lyl.utils.ActivityUtil;
import com.itislevel.lyl.utils.ToastUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 作者： XS
 * 邮箱：1363826037@qq.com
 * 描述:
 * 创建时间:  2018\7\7 0007 10:49
 */
public class UserMonkeyQActivity extends RootActivity<UserMonkeyPresenter>implements UserMonkeyContract.View{
    @BindView(R.id.alltixian_linear)
    LinearLayoutCompat alltixian_linear;//全部体现

    @BindView(R.id.tixian_linear)
    LinearLayoutCompat tixian_linear;//体现账单

    @BindView(R.id.user_monkey)
    AppCompatTextView user_monkey;
    @Override
    protected void initInject() {
    getActivityComponent().inject(this);
    }
    @Override
    protected int getLayoutId() {
        return R.layout.activity_usermonkey;
    }
    @Override
    protected void initEventAndData() {
        setToolbarMoreTxt("收支明细");
        setToolbarTvTitle("余额");
        setToolbarMoreClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityUtil.getInstance().openActivity(UserMonkeyQActivity.this,UserMonkeyShouActivity.class);
            }
        });
    }

    @OnClick({R.id.alltixian_linear,R.id.tixian_linear,R.id.setting_linear})
    public void Onclick(View view)
    {
            switch (view.getId())
            {
                case R.id.alltixian_linear://全部提现
                    String monkey=user_monkey.getText().toString();
                    if(!monkey.equals(""))
                    {
                        Bundle bundle = new Bundle();
                        bundle.putString("monkey",monkey);
                        ActivityUtil.getInstance().openActivity(this,UserAllMonkeyActivity.class,bundle);
                    }else {
                        ToastUtil.Info("没有可提现余额!");
                    }
                    break;
                case R.id.tixian_linear://体现账单
                        ActivityUtil.getInstance().openActivity(this, PutRecordActivity.class);
                    break;
                case R.id.setting_linear:
                        ActivityUtil.getInstance().openActivity(this, UserMonkeySettingActivity.class);
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
