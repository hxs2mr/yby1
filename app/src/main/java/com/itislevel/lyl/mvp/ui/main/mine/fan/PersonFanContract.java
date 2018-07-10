package com.itislevel.lyl.mvp.ui.main.mine.fan;

import com.itislevel.lyl.base.BasePresenter;
import com.itislevel.lyl.base.BaseView;
import com.itislevel.lyl.mvp.model.bean.FanRecodeBean;
import com.itislevel.lyl.mvp.model.bean.FanXianBean;
import com.itislevel.lyl.mvp.model.bean.ShanHomeBean;

/**
 * 作者： XS
 * 邮箱：1363826037@qq.com
 * 描述:
 * 创建时间:  2018\7\2 0002 09:02
 */
public interface PersonFanContract {
    interface View extends BaseView
    {
       void merchantmainpage(ShanHomeBean bean);
       void rechargeRecord(FanRecodeBean bean);//充值记录
        void  cashbackist(FanXianBean bean);//返现记录
        void  onlinerecharge(String msg);//在线充值的订单号
    }
    interface Presenter extends BasePresenter<View>
    {
        void merchantmainpage(String  msg);
        void rechargeRecord(String msg);
        void  cashbackist(String msg);//返现记录
        void  onlinerecharge(String msg);
    }
}
