package com.itislevel.lyl.mvp.ui.property.homelist;

import com.itislevel.lyl.adapter.LiveAddressBean;
import com.itislevel.lyl.base.BasePresenter;
import com.itislevel.lyl.base.BaseView;
import com.itislevel.lyl.mvp.model.bean.HomeDetailBean;
import com.itislevel.lyl.mvp.model.bean.VillageNameBean;

import java.util.List;

/**
 * Created by Administrator on 2018\5\25 0025.
 */

public interface PropertyHomeContract {
    interface View extends BaseView{
        void findVillagename(List<VillageNameBean> list);//用户的小区列表
        void findLiveaddress(List<LiveAddressBean> list);//小区下的单元列表
        void personalNews(List<HomeDetailBean> bean);
    }
    interface Presenter extends BasePresenter<View>
    {
        void findVillagename(String action);//用户的小区列表
        void findLiveaddress(String action);//小区下的单元列表
        void personalNews(String action);
    }
}
