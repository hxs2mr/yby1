package com.itislevel.lyl.mvp.ui.user;


import com.itislevel.lyl.base.BasePresenter;
import com.itislevel.lyl.base.BaseView;
import com.itislevel.lyl.mvp.model.bean.FileUploadBean;
import com.itislevel.lyl.mvp.model.bean.HomeBean;
import com.itislevel.lyl.mvp.model.bean.MyReceiveAddrBean;
import com.itislevel.lyl.mvp.model.bean.RegistBean;
import com.itislevel.lyl.mvp.model.bean.UserInfoBean;

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
public interface UserContract {
    interface View extends BaseView {
        //定义自己特有的方法
        void showContent(String msg);
        //登陆
        void login(UserInfoBean response);
        void getValidateCode(String  validateCode );
        //注册
        void getSSMCode(String smscode);
        void regist(RegistBean registBean);
        void uploadHeader(FileUploadBean fileUploadBean );


        void userPerfectPersonal(String message);
        void userModifyPassword(String message);
        void userModifyNickname(String message);
        void userFindRecAddress(MyReceiveAddrBean address);
        void userUpdateRecAddress(String message);
        void userGiftmy(Object message);

        void userModifyHeader(String message);
        void userForgetPassword(String message);


    }
    interface Presenter extends BasePresenter<View> {
        //定义自己特有的方法
        void loadData(int num, int page);
        //登陆
        void getValidateCode(String action);
        //注册
        void getSSMCode(String action);
        void regist(String action);
        void uploadHeader( @Part MultipartBody.Part file);



        void userPerfectPersonal(String action);
        void userModifyPassword(String action);
        void userModifyNickname(String action);
        void userFindRecAddress(String action);
        void userUpdateRecAddress(String action);
        void userGiftmyAdd(String action);
        void userModifyHeader(String action);

        void userForgetPassword(String action);
        void login(String msg);
    }
}
