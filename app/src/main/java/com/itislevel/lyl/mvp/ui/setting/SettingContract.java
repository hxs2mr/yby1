package com.itislevel.lyl.mvp.ui.setting;


import com.itislevel.lyl.base.BasePresenter;
import com.itislevel.lyl.base.BaseView;
import com.itislevel.lyl.mvp.model.bean.FileUploadBean;

import okhttp3.MultipartBody;
import retrofit2.http.Part;

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
public interface SettingContract {
    interface View extends BaseView {
        //定义自己特有的方法
        void showContent(String msg);

        void getSSMCode(String smscode);
        void updatePhone(String msg);
        void userAddFeedback(String msg);
        void uploadHeader(FileUploadBean fileUploadBean );




    }
    interface Presenter extends BasePresenter<View> {
        //定义自己特有的方法
        void loadData(int num, int page);
        void getSSMCode(String action);
        void updatePhone(String action);
        void userAddFeedback(String action);
        void uploadHeader( @Part MultipartBody.Part file);


    }
}
