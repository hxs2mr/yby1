package com.itislevel.lyl.mvp.ui.main;


import com.itislevel.lyl.base.BasePresenter;
import com.itislevel.lyl.base.BaseView;
import com.itislevel.lyl.mvp.model.bean.AddressBean;
import com.itislevel.lyl.mvp.model.bean.AppUpdate;
import com.itislevel.lyl.mvp.model.bean.UserHeaderNickInfo;
import com.itislevel.lyl.mvp.model.http.response.LYLResponse;

import java.util.List;

import retrofit2.http.Field;

/**
 * **********************
 * 功 能:主页锲约类
 * 创建人:itisi
 * 邮  箱:itisivip@qq.com
 * 创建时间:2017/7/6 10:20
 * 修改人:itisi
 * 修改时间: 2017/7/6 10:20
 * 修改内容:itisi
 * *********************
 */

public interface MainContract {
    interface View extends BaseView {
        void testShowView(String smg);

        void userInfoById(List<UserHeaderNickInfo> userHeaderNickInfos);

        void appupdate(AppUpdate appUpdate);

    }

    interface Presenter extends BasePresenter<View> {
        void testShowPresenter(boolean isShow);

        void userInfoById(String action);
        void appupdate(String action);


    }
}
