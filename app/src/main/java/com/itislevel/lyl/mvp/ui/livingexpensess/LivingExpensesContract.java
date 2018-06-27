package com.itislevel.lyl.mvp.ui.livingexpensess;


import com.itislevel.lyl.base.BasePresenter;
import com.itislevel.lyl.base.BaseView;
import com.itislevel.lyl.mvp.model.bean.BlessOrderBean;
import com.itislevel.lyl.mvp.model.bean.PropertyQueryInfo;
import com.itislevel.lyl.mvp.model.bean.PropertyQueryInfoBean;
import com.itislevel.lyl.mvp.model.bean.PropertyRecordBean;
import com.itislevel.lyl.mvp.model.bean.PropertyUpdateStatusBean;
import com.itislevel.lyl.mvp.model.bean.SetOwnerPayMonth;

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
public interface LivingExpensesContract {
    interface View extends BaseView {
        //定义自己特有的方法
        void showContent(String msg);

        void propertyQuery(PropertyQueryInfoBean propertyQueryInfoBean);
        void propertyQueryOrder(PropertyRecordBean propertyRecordBean);
        void propertyUpdatePayType(PropertyUpdateStatusBean statusBean);
        void propertyUpdateOrderState(PropertyUpdateStatusBean statusBean);
        void propertyGenerateOrder(String blessOrderBean);
        void getSSMCode(String smscode);

        void propertyQueryByUserid(PropertyQueryInfo queryInfo);

        void propertyQueryList(PropertyQueryInfo queryInfo);
        void propertyQueryByUserid1(PropertyQueryInfoBean queryInfoBean);

        void propertySetOwnerPayMonth(SetOwnerPayMonth setOwnerPayMonth);


    }
    interface Presenter extends BasePresenter<View> {
        //定义自己特有的方法
        void loadData(int num, int page);


        void propertyQuery(String action);
        void propertyQueryOrder(String action);
        void propertyUpdatePayType(String action);
        void propertyUpdateOrderState(String action);
        void propertyGenerateOrder(String action);

        void getSSMCode(String action);

        void propertyQueryByUserid(String action);

        void propertyQueryList(String action);
        void propertyQueryByUserid1(String action);

        void propertySetOwnerPayMonth(String action);

    }
}
