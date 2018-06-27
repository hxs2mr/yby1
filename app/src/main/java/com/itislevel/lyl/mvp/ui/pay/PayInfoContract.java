package com.itislevel.lyl.mvp.ui.pay;


import com.itislevel.lyl.base.BasePresenter;
import com.itislevel.lyl.base.BaseView;
import com.itislevel.lyl.mvp.model.bean.AliPayBean;
import com.itislevel.lyl.mvp.model.bean.BlessOrderBean;
import com.itislevel.lyl.mvp.model.bean.PropertyUpdateStatusBean;
import com.itislevel.lyl.mvp.model.bean.WeiXinPayTestBean;

/**
 ***********************
 * 功 能:必填必填必填必填必填必填
 * 创建人:itisi
 * 邮  箱:itisivip@qq.com
 * 创建时间:2017/7/7 17:56
 * 修改人:itisi
 * 修改时间: 2017/7/7 17:56
 * 修改内容:itisi
 * *********************
 */
public interface PayInfoContract {
    interface View extends BaseView {
        //定义自己特有的方法
        void showContent(String msg);


        void weixinPayTest(WeiXinPayTestBean blessOrderBean);
        void alipayList(BlessOrderBean blessOrderBean);


    }
    interface Presenter extends BasePresenter<View> {


        void weixinPayTest(String action);

        void alipayList(String action);

    }
}
