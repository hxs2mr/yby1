package com.itislevel.lyl.mvp.ui.team;


import com.itislevel.lyl.base.BasePresenter;
import com.itislevel.lyl.base.BaseView;
import com.itislevel.lyl.mvp.model.bean.BlessOrderBean;
import com.itislevel.lyl.mvp.model.bean.FileUploadBean;
import com.itislevel.lyl.mvp.model.bean.TeamAddProblemBean;
import com.itislevel.lyl.mvp.model.bean.TeamListBean;
import com.itislevel.lyl.mvp.model.bean.TeamStatusBean;
import com.itislevel.lyl.mvp.model.bean.TeamTypeBean;
import com.itislevel.lyl.mvp.model.bean.TroubleAdviserMyBean;

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
public interface TeamContract {
    interface View extends BaseView {
        //定义自己特有的方法
        void showContent(String msg);

        void teamRegister(String message);
        void teamStatus(TeamStatusBean teamStatusBean);
        void teamList(TeamListBean teamListBean );
        void teamViewCount(String message);
        void teamProblemAdd(BlessOrderBean message );
        void teamProblemList(Object object );
        void teamReplay(Object object );
        void teamMyProblemList(TroubleAdviserMyBean troubleAdviserMyBean );
        void teamType(TeamTypeBean teamTypeBean );

        void uploadHeader(FileUploadBean fileUploadBean );


    }
    interface Presenter extends BasePresenter<View> {
        //定义自己特有的方法
        void loadData(int num, int page);

        void teamRegister(String action);
        void teamStatus(String action);
        void teamList(String action);
        void teamViewCount(String action);
        void teamProblemAdd(String action);
        void teamProblemList(String action);
        void teamReplay(String action);
        void teamMyProblemList(String action);
        void teamType(String action);

        void uploadHeader( @Part MultipartBody.Part file);




    }
}
