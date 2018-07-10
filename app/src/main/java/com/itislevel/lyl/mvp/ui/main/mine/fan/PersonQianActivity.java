package com.itislevel.lyl.mvp.ui.main.mine.fan;

import com.itislevel.lyl.base.RootActivity;
import com.itislevel.lyl.mvp.model.bean.FanRecodeBean;
import com.itislevel.lyl.mvp.model.bean.FanXianBean;
import com.itislevel.lyl.mvp.model.bean.ShanHomeBean;

/**
 * 作者： XS
 * 邮箱：1363826037@qq.com
 * 描述:我的钱包
 * 创建时间:  2018\7\2 0002 09:05
 */
public class PersonQianActivity extends RootActivity<PersonFanPresenter>implements PersonFanContract.View{
    @Override
    protected void initInject() {

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

    @Override
    public void merchantmainpage(ShanHomeBean bean) {

    }

    @Override
    public void rechargeRecord(FanRecodeBean bean) {

    }

    @Override
    public void cashbackist(FanXianBean bean) {

    }

    @Override
    public void onlinerecharge(String msg) {

    }
}
