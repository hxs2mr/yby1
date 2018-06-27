package com.itislevel.lyl.mvp.ui.main.dynamic.childfragment.dfollow;

import com.itislevel.lyl.base.BasePresenter;
import com.itislevel.lyl.base.BaseView;
import com.itislevel.lyl.mvp.model.bean.DynamicCommnetAddBean;
import com.itislevel.lyl.mvp.model.bean.DynimacLinkBean;
import com.itislevel.lyl.mvp.model.bean.FindistBean;
import com.itislevel.lyl.mvp.model.bean.FollowListBean;
import com.itislevel.lyl.mvp.model.bean.GiftBean;

import java.util.List;

/**
 * Created by Administrator on 2018\4\27 0027.
 */

public interface DFollowContract {
    interface  View extends BaseView{
        void firstPage(FindistBean msg);
        void dynamicdianzan(DynimacLinkBean dynimacLinkBean);
        void addDynamicComment(DynamicCommnetAddBean dynamicCommnetAddBean);
        void delDynamicComment(String msg);

        void happyGiftList(List<GiftBean> blessGiftBean);
        void immediateOrder(String blessOrderBean);
    }
    interface Presenter extends BasePresenter<View>
    {
        void firstPage(String msg);
        void dynamicdianzan(String msg);
        void addDynamicComment(String action);
        void delDynamicComment(String msg);
        void happyGiftList(String action);
        void immediateOrder(String action);
    }
}
