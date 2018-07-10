package com.itislevel.lyl.mvp.ui.usermonkey.putrecord;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.itislevel.lyl.R;
import com.itislevel.lyl.base.RootActivity;
import com.itislevel.lyl.utils.UtilsStyle;

/**
 * 作者： XS
 * 邮箱：1363826037@qq.com
 * 描述:
 * 创建时间:  2018\7\9 0009 16:30
 */
public class PutRecordDetailActivity extends RootActivity<PutRecordPresenter> implements PutRecordContract.View{
    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }
    @Override
    protected int getLayoutId() {
        return R.layout.activity_putrecorddetail;
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UtilsStyle.setStatusBarMode(this,true); //黑色的主题图标
    }
    @Override
    protected void initEventAndData() {
        setToolWight("账单详情");
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
