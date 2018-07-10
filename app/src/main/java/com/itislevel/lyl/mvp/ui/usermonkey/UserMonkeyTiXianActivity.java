package com.itislevel.lyl.mvp.ui.usermonkey;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.itislevel.lyl.R;
import com.itislevel.lyl.adapter.RecordNewListAdapter;
import com.itislevel.lyl.base.RootActivity;
import com.itislevel.lyl.mvp.ui.property.payrecord.PayRecordDetailActivity;
import com.itislevel.lyl.mvp.ui.property.payrecord.PropertyPayRecordActivity;
import com.itislevel.lyl.utils.ActivityUtil;
import com.itislevel.lyl.utils.UtilsStyle;

import butterknife.BindView;
import butterknife.OnClick;
import dagger.BindsInstance;

/**
 * 作者： XS
 * 邮箱：1363826037@qq.com
 * 描述:
 * 创建时间:  2018\7\9 0009 13:56
 */
public class UserMonkeyTiXianActivity extends RootActivity<UserMonkeyPresenter> implements UserMonkeyContract.View{

    @BindView(R.id.back_button)
    AppCompatButton back_button;

    @BindView(R.id.bank_name)
    AppCompatTextView bank_name;

    @BindView(R.id.bank_monkey)
    AppCompatTextView bank_monkey;

    private Bundle bundle;
    private String monkey="";
    private String address="";

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
        return R.layout.activity_usertixian;
    }

    @Override
    protected void initEventAndData() {
        setToolWight("提现结果");
        bundle = getIntent().getExtras();
        monkey = bundle.getString("monkey");
        address = bundle.getString("address");
        bank_name.setText("¥"+monkey);
        bank_monkey.setText(address);
    }

    @OnClick(R.id.back_button)
    public void Onclick(View view)
    {
        switch (view.getId())
        {
            case R.id.back_button:
                ActivityUtil.getInstance().closeActivity(this);
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
