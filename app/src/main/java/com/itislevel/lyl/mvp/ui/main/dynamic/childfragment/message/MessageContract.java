package com.itislevel.lyl.mvp.ui.main.dynamic.childfragment.message;

import com.itislevel.lyl.base.BasePresenter;
import com.itislevel.lyl.base.BaseView;
import com.itislevel.lyl.mvp.model.bean.MessageBean;

/**
 * Created by Administrator on 2018\6\15 0015.
 */

public interface MessageContract {
    interface View extends BaseView
    {
        void myDyPushList(MessageBean action);
        void clearMyDyPushList(String action);
    }
    interface Presenter extends BasePresenter<View>
    {
        void myDyPushList(String data);
        void clearMyDyPushList(String data);
    }
}
