package com.itislevel.lyl.mvp.ui.main.dynamic.childfragment.toncity;

import com.itislevel.lyl.base.BasePresenter;
import com.itislevel.lyl.base.BaseView;
import com.itislevel.lyl.mvp.model.bean.DynamicCommnetAddBean;
import com.itislevel.lyl.mvp.model.bean.DynimacLinkBean;
import com.itislevel.lyl.mvp.model.bean.FollowListBean;
import com.itislevel.lyl.mvp.model.bean.GiftBean;
import com.itislevel.lyl.mvp.model.bean.TonCityListBean;
import com.itislevel.lyl.mvp.ui.main.dynamic.childfragment.dfollow.DFollowContract;

import java.util.List;

/**
 * Created by Administrator on 2018\4\27 0027.
 */

public interface DTonCityContract  {
    interface  View extends BaseView{
        void firstPage(TonCityListBean msg);
        void dynamicdianzan(DynimacLinkBean dynimacLinkBean);
        void dynamicfollow(String msg);
        void addDynamicComment(DynamicCommnetAddBean dynamicCommnetAddBean);
        void delDynamicComment(String msg);
        void immediateOrder(String blessOrderBean);
        void delDynamicInfo(String msg);
        void happyGiftList(List<GiftBean> blessGiftBean);
    }
    interface Presenter extends BasePresenter<View>
    {
        void firstPage(String msg);
        void dynamicdianzan(String msg);
        void dynamicfollow(String action);
        void addDynamicComment(String action);
        void delDynamicComment(String msg);
        void immediateOrder(String action);
        void delDynamicInfo(String msg);
        void happyGiftList(String msg);
    }
}
