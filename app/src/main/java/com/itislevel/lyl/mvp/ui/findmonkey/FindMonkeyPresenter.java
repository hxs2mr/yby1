package com.itislevel.lyl.mvp.ui.findmonkey;

import com.itislevel.lyl.base.RxPresenter;
import com.itislevel.lyl.mvp.model.DataManager;

import javax.inject.Inject;

/**
 * 作者： XS
 * 邮箱：1363826037@qq.com
 * 描述:
 * 创建时间:  2018\6\29 0029 09:06
 */
public class FindMonkeyPresenter extends RxPresenter<FindMonkeyContract.View>implements FindMonkeyContract.Presenter {
    private DataManager mDataManager;
    @Inject
    public FindMonkeyPresenter(DataManager dataManager)
    {
        this.mDataManager = dataManager;
    }
}
