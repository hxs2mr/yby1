package com.itislevel.lyl.mvp.ui.findmonkey;

import com.itislevel.lyl.base.RootActivity;

/**
 * 作者： XS
 * 邮箱：1363826037@qq.com
 * 描述:
 * 创建时间:  2018\6\29 0029 09:07
 */
public class FindMonkeyActivity extends RootActivity<FindMonkeyPresenter>implements FindMonkeyContract.View {
    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }
    @Override
    protected int getLayoutId() {
        return 0;
    }

    @Override
    protected void initEventAndData() {

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
