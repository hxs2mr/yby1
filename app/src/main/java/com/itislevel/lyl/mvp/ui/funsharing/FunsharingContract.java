package com.itislevel.lyl.mvp.ui.funsharing;


import com.itislevel.lyl.base.BasePresenter;
import com.itislevel.lyl.base.BaseView;
import com.itislevel.lyl.mvp.model.bean.FileUploadBean;
import com.itislevel.lyl.mvp.model.bean.FunsharingAddBean;
import com.itislevel.lyl.mvp.model.bean.FunsharingCommnetAddBean;
import com.itislevel.lyl.mvp.model.bean.FunsharingLikeBean;
import com.itislevel.lyl.mvp.model.bean.FunsharingListBean;
import com.itislevel.lyl.mvp.model.bean.FunsharingMyBean;
import com.itislevel.lyl.mvp.model.bean.MeiZiBean;
import com.itislevel.lyl.mvp.model.bean.MeiZiMultipleBean;

import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.http.Part;
import retrofit2.http.Query;

/**
 * **********************
 * 功 能:必填必填必填必填必填必填
 * 创建人:itisi
 * 邮  箱:itisivip@qq.com
 * 创建时间:2017/7/7 17:56
 * 修改人:itisi
 * 修改时间: 2017/7/7 17:56
 * 修改内容:itisi
 * *********************
 */
public interface FunsharingContract {
    interface View extends BaseView {
        //定义自己特有的方法
        void showContent(String msg);

        void showData(List<MeiZiBean> data);

        void showDataMultiple(List<MeiZiMultipleBean> data);

        void addDynamic(String funsharingAddBean);

        void shareList(FunsharingListBean funsharingListBeans);

        void shareListMy(FunsharingMyBean funsharingMyBeans);

        void shareDel(String message);

        void commentShareAdd(FunsharingCommnetAddBean funsharingCommnetAddBean );

        void commentShareDel(String message);

        void shareLikeOrCancel(FunsharingLikeBean funsharingLikeBean );

        void uploadHeader(FileUploadBean fileUploadBean );

    }

    interface Presenter extends BasePresenter<View> {
        //定义自己特有的方法
        void loadData(int num, int page);

        void loadDataMul(int num, int page);

        void addDynamic(String action);

        void shareList(String action);

        void shareListMy(String action);

        void shareDel(String action);

        void commentShareAdd(String action);

        void commentShareDel(String action);

        void shareLikeOrCancel(String action);
        void uploadHeader( @Part MultipartBody.Part file);

    }
}
