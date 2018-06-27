package com.itislevel.lyl.mvp.ui.family.childhomefragment.toncity;

import com.itislevel.lyl.base.BasePresenter;
import com.itislevel.lyl.base.BaseView;
import com.itislevel.lyl.mvp.model.bean.FamilyTonListBean;

/**
 * Created by Administrator on 2018\4\27 0027.
 */

public interface FTonCityContract {
    interface  View extends BaseView{
        void firstPage(FamilyTonListBean lylResponse);
    }
    interface Presenter extends BasePresenter<View>
    {
        void firstPage(String msg);
    }
}
