package com.itislevel.lyl.mvp.ui.property.bill;

import com.itislevel.lyl.base.BasePresenter;
import com.itislevel.lyl.base.BaseView;
import com.itislevel.lyl.mvp.model.bean.BillsBean;
import com.itislevel.lyl.mvp.model.bean.PropertyBillBean;

import java.util.List;

/**
 * Created by Administrator on 2018\5\28 0028.
 */

public interface PropertyBillContract {
    interface View extends BaseView{
        void propertyBill(List<PropertyBillBean> billBeans);
        void propertyadd(String data);
        void  findBillsMonth(String data);

    }
    interface Presenter extends BasePresenter<View>
    {
        void propertyBill(String action);
        void propertyadd(String action);
        void  findBillsMonth(String action);
    }
}
