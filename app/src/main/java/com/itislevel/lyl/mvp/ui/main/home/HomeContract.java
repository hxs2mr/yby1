package com.itislevel.lyl.mvp.ui.main.home;


import com.itislevel.lyl.base.BasePresenter;
import com.itislevel.lyl.base.BaseView;
import com.itislevel.lyl.mvp.model.bean.HomeBean;
import com.itislevel.lyl.mvp.model.bean.MeiZiBean;
import com.itislevel.lyl.mvp.model.bean.PlaceBean;

import java.util.List;

/**
 ***********************
 * 功 能:必填必填必填必填必填必填
 * 创建人:itisi
 * 邮  箱:itisivip@qq.com
 * 创建时间:2017/7/7 17:56
 * 修改人:itisi
 * 修改时间: 2017/7/7 17:56
 * 修改内容:itisi
 * *********************
 */
public interface HomeContract  {
    interface View extends BaseView {
        //定义自己特有的方法
        void showContent(String msg);
        void firstPage(HomeBean message);
        void plcae(PlaceBean message);
    }
    interface Presenter extends BasePresenter<View> {
        //定义自己特有的方法
        void loadData(int num, int page);
        void firstPage(String message);
        void plcae(String message);
    }
}
