package com.itislevel.lyl.mvp.ui.property.complaint;

import com.itislevel.lyl.base.BasePresenter;
import com.itislevel.lyl.base.BaseView;
import com.itislevel.lyl.mvp.model.bean.ComSearchBean;
import com.itislevel.lyl.mvp.model.bean.ComplaintTypeBean;
import com.itislevel.lyl.mvp.model.bean.FileUploadBean;
import com.itislevel.lyl.mvp.model.bean.PropertyDetailBean;

import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.http.Part;

/**
 * Created by Administrator on 2018\5\29 0029.
 */

public interface ComplaintContract {
    interface View extends BaseView
    {
        void uploadHeader(FileUploadBean fileUploadBean );
        void complaintType(List<ComplaintTypeBean> data);//投诉类型
        void addComplaint(String data);//添加投诉
        void findComplaintList(ComSearchBean bean);
        void detaComplaintList(List<PropertyDetailBean> bean);
    }
    interface Presenter extends BasePresenter<View>
    {
        void uploadHeader( @Part MultipartBody.Part file);
        void complaintType(String action);//投诉类型
        void addComplaint(String data);//添加投诉
        void findComplaintList(String data);
        void detaComplaintList(String action);
    }
}
