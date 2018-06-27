package com.itislevel.lyl.mvp.ui.property.childfragment.historicalbill;

import com.itislevel.lyl.adapter.LiveAddressBean;
import com.itislevel.lyl.base.BasePresenter;
import com.itislevel.lyl.base.BaseView;
import com.itislevel.lyl.mvp.model.bean.HistoricalBean;

import java.util.List;

/**
 * Created by Administrator on 2018\6\20 0020.
 */

public interface HistoricalbillContract {
    interface View extends BaseView{
    void querybillrecord(HistoricalBean bean);
    void findLiveaddress(List<LiveAddressBean> list);//小区下的单元列表
    }
    interface Presenter extends BasePresenter<View>
    {
        void querybillrecord(String bean);
        void findLiveaddress(String list);//小区下的单元列表
    }
}
