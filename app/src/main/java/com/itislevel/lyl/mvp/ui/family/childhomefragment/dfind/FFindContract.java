package com.itislevel.lyl.mvp.ui.family.childhomefragment.dfind;

import com.itislevel.lyl.base.BasePresenter;
import com.itislevel.lyl.base.BaseView;
import com.itislevel.lyl.mvp.model.bean.FamilyListBean;
import com.itislevel.lyl.mvp.model.bean.FamilyPersonListBean;

/**
 * Created by Administrator on 2018\4\27 0027.
 */

public interface FFindContract {
    interface  View extends BaseView{
        void firstPage(FamilyListBean lylResponse);
        void findmylist(FamilyPersonListBean familyPersonListBean);
        void delFete(String action);
    }
    interface  Presenter extends BasePresenter<View>
    {
        void firstPage(String msg);
        void findmylist(String msg);
        void delFete(String msg);
    }
}
