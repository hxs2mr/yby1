package com.itislevel.lyl.mvp.ui.housekeep;


import com.itislevel.lyl.base.BasePresenter;
import com.itislevel.lyl.base.BaseView;
import com.itislevel.lyl.mvp.model.bean.HouseKeepBean;
import com.itislevel.lyl.mvp.model.bean.MeiZiBean;

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
public interface HouseKeepContract {
    interface View extends BaseView {
        //定义自己特有的方法
        void showContent(String msg);
        void showData(List<MeiZiBean> data);

        void findHouse(HouseKeepBean houseKeepBean);


    }
    interface Presenter extends BasePresenter<View> {
        //定义自己特有的方法
        void loadData(int num, int page);

        void findHouse(String action);
    }
}
