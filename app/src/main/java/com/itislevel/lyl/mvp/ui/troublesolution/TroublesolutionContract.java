package com.itislevel.lyl.mvp.ui.troublesolution;


import com.itislevel.lyl.base.BasePresenter;
import com.itislevel.lyl.base.BaseView;
import com.itislevel.lyl.mvp.model.bean.BlessOrderBean;
import com.itislevel.lyl.mvp.model.bean.FileUploadBean;
import com.itislevel.lyl.mvp.model.bean.MeiZiBean;
import com.itislevel.lyl.mvp.model.bean.TeamAddProblemBean;
import com.itislevel.lyl.mvp.model.bean.TeamListBean;
import com.itislevel.lyl.mvp.model.bean.TeamTypeBean;
import com.itislevel.lyl.mvp.model.bean.TroubleAddBean;
import com.itislevel.lyl.mvp.model.bean.TroubleAdviserMyBean;
import com.itislevel.lyl.mvp.model.bean.TroubleCommentAdd;
import com.itislevel.lyl.mvp.model.bean.TroubleListBean;
import com.itislevel.lyl.mvp.model.bean.TroubleMyListBean;
import com.itislevel.lyl.mvp.model.bean.TroubleTypeBean;

import java.util.List;

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
public interface TroublesolutionContract {
    interface View extends BaseView {
        //定义自己特有的方法
        void showContent(String msg);
        void showDataList(List<MeiZiBean> meiZiBeans);

        void troubleAdd(TroubleAddBean troubleAdd );

        void troubleList(TroubleListBean troubleListBean);

        void troubleListMy(TroubleListBean troubleListBean);

        void troubleDel(String action);

        void troubleCommentReplay(TroubleCommentAdd troubleCommentAdd );

        void troubleCommentDel(String action);
        void troubleType(TroubleTypeBean troubleTypeBean  );


        void teamProblemAdd(String blessOrderBean );
        void teamProblemList(Object object );
        void teamReplay(Object object );
        void teamMyProblemList(TroubleAdviserMyBean troubleAdviserMyBean );
        void teamViewCount(String message);

        void uploadHeader(FileUploadBean fileUploadBean );


    }
    interface Presenter extends BasePresenter<View> {
        //定义自己特有的方法
        void loadData(int num, int page);

        void troubleAdd(String action);

        void troubleList(String action);

        void troubleListMy(String action);

        void troubleDel(String action);

        void troubleCommentReplay(String action);

        void troubleCommentDel(String action);
        void troubleType(String action);





        void teamProblemAdd(String action);
        void teamProblemList(String action);
        void teamReplay(String action);
        void teamMyProblemList(String action);

        void teamViewCount(String action);

        void uploadHeader( @Part MultipartBody.Part file);


    }
}
