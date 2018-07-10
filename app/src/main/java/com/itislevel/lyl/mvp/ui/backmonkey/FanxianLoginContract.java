package com.itislevel.lyl.mvp.ui.backmonkey;

import com.itislevel.lyl.base.BasePresenter;
import com.itislevel.lyl.base.BaseView;
import com.itislevel.lyl.mvp.model.bean.FanloginBean;

/**
 * 作者： XS
 * 邮箱：1363826037@qq.com
 * 描述:
 * 创建时间:  2018\7\3 0003 09:08
 */
public interface FanxianLoginContract {
    interface  View extends BaseView
    {
        void  merchantlogin(FanloginBean bean);
        void getSSMCode(String action);
    }
    interface Presenter extends BasePresenter<View>
    {
        void merchantlogin(String message);
        void getSSMCode(String action);
    }
}
