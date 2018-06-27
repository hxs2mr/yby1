package com.itislevel.lyl.mvp.ui.main.dynamic.childfragment.dfind;

import com.itislevel.lyl.base.BasePresenter;
import com.itislevel.lyl.base.BaseView;
import com.itislevel.lyl.mvp.model.bean.BlessGiftBean;
import com.itislevel.lyl.mvp.model.bean.DynamicCommnetAddBean;
import com.itislevel.lyl.mvp.model.bean.DynamicPersonBean;
import com.itislevel.lyl.mvp.model.bean.DynimacLinkBean;
import com.itislevel.lyl.mvp.model.bean.FollowListBean;
import com.itislevel.lyl.mvp.model.bean.GiftBean;
import com.itislevel.lyl.mvp.model.http.response.LYLResponse;

import java.util.List;

/**
 * Created by Administrator on 2018\4\27 0027.
 */

public interface DFindContract {
    interface  View extends BaseView{
        void firstPage(FollowListBean lylResponse);
        void dynamicdianzan(DynimacLinkBean dynimacLinkBean);
        void dynamicfollow(String msg);
        void addDynamicComment(DynamicCommnetAddBean  dynamicCommnetAddBean);
        void delDynamicComment(String msg);

        void happyOrderAdd(String blessOrderBean);
        void happyCartAdd(String message);
        void happyGiftList(List<GiftBean> blessGiftBean);
        void immediateOrder(String blessOrderBean);
        void delDynamicInfo(String msg);
        void dynamicList(DynamicPersonBean dynamicPersonBean);

    }
    interface  Presenter extends BasePresenter<View>
    {
        void firstPage(String msg);
        void dynamicdianzan(String msg);
        void dynamicfollow(String action);
        void addDynamicComment(String action);
        void delDynamicComment(String msg);
        void happyOrderAdd(String action);
        void happyCartAdd(String action);
        void happyGiftList(String action);
        void immediateOrder(String action);
        void delDynamicInfo(String msg);
        void dynamicList(String action);
    }
}
