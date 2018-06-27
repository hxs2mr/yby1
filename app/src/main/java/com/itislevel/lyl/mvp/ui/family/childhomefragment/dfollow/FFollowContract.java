package com.itislevel.lyl.mvp.ui.family.childhomefragment.dfollow;

import com.itislevel.lyl.base.BasePresenter;
import com.itislevel.lyl.base.BaseView;
import com.itislevel.lyl.mvp.model.bean.FamilyFollowListBean;

/**
 * Created by Administrator on 2018\4\27 0027.
 */

public interface FFollowContract {
    interface  View extends BaseView{
        void firstPage(FamilyFollowListBean lylResponse);
    }
    interface Presenter extends BasePresenter<View>
    {
        void firstPage(String msg);
    }
}
