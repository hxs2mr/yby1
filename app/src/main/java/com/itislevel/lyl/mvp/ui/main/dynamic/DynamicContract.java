package com.itislevel.lyl.mvp.ui.main.dynamic;

import com.itislevel.lyl.base.BasePresenter;
import com.itislevel.lyl.base.BaseView;

/**
 * Created by Administrator on 2018\4\27 0027.
 */

public interface DynamicContract {
    interface  View extends BaseView{//视图层展示的方法
        void firstPage(String msg);
    }
    interface  Presenter  extends BasePresenter<View>//控制层展示的方法
    {
        void firstPage(String msg);
    }
}
