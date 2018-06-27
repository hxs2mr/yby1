package com.itislevel.lyl.mvp.ui.main.customer;

import com.itislevel.lyl.base.BasePresenter;
import com.itislevel.lyl.base.BaseView;
import com.itislevel.lyl.mvp.model.bean.CFTabBean;

import java.util.List;

/**
 * desc:功能说明必写
 * user:itisi
 * date:2017/11/21.10:21
 * path:com.itislevel.lyl.mvp.ui.main.customer.CustomerContract
 **/
public interface CustomerContract {
    interface View extends BaseView {
        //定义自己特有的方法
        void showContent(String msg);
        void loadtable(List<CFTabBean> list);
    }
    interface Presenter extends BasePresenter<View> {
        //定义自己特有的方法
        void loadData(int num,int page);
        void loadtable(String msg);
    }
}
