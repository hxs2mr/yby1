package com.itislevel.lyl.mvp.ui.property;

import com.itislevel.lyl.adapter.LiveAddressBean;
import com.itislevel.lyl.base.BasePresenter;
import com.itislevel.lyl.base.BaseView;
import com.itislevel.lyl.mvp.model.bean.PropertLoginBean;
import com.itislevel.lyl.mvp.model.bean.PropretyLiveBean;
import com.itislevel.lyl.mvp.model.bean.PropretyNoticeBean;
import com.itislevel.lyl.mvp.model.bean.VillageNameBean;

import java.util.List;

/**
 * Created by Administrator on 2018\5\22 0022.
 */

public interface PropertyContract {
    interface View extends BaseView{

        void getSSMCode(String action);
        void loginEstates(PropertLoginBean bean);
        void noticeEstates(PropretyNoticeBean bean);//物业通知
        void propretyLive(PropretyLiveBean list);//物业广告
        void findVillagename(List<VillageNameBean> list);//用户的小区列表
        void findLiveaddress(List<LiveAddressBean> list);//用户的单元列表
    }
    interface  Presenter extends BasePresenter<View>
    {
        void getSSMCode(String action);
        void loginEstates(String action);
        void noticeEstates(String action);//物业通知
        void propretyLive(String action);//物业广告
        void findVillagename(String action);//用户的小区列表
        void findLiveaddress(String action);//用户的单元列表
    }
}
