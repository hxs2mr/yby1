package com.itislevel.lyl.mvp.ui.property.payrecord;

import com.itislevel.lyl.adapter.LiveAddressBean;
import com.itislevel.lyl.base.BasePresenter;
import com.itislevel.lyl.base.BaseView;
import com.itislevel.lyl.mvp.model.bean.PayLuBean;

import java.util.List;

/**
 * Created by Administrator on 2018\5\31 0031.
 */

public interface PayRecordContract {
    interface View extends BaseView{
        void estatesPayList(List<PayLuBean> bean);
        void findLiveaddress(List<LiveAddressBean> list);//小区下的单元列表
    }
    interface Presenter extends BasePresenter<View>
    {
        void estatesPayList(String action);
        void findLiveaddress(String list);//小区下的单元列表
    }
}
