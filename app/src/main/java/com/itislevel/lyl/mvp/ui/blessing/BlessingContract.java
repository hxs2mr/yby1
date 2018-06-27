package com.itislevel.lyl.mvp.ui.blessing;


import com.itislevel.lyl.base.BasePresenter;
import com.itislevel.lyl.base.BaseView;
import com.itislevel.lyl.mvp.model.bean.AliPayBean;
import com.itislevel.lyl.mvp.model.bean.BlessAddBean;
import com.itislevel.lyl.mvp.model.bean.BlessAddLikeBean;
import com.itislevel.lyl.mvp.model.bean.BlessCartListBean;
import com.itislevel.lyl.mvp.model.bean.BlessCommentBean;
import com.itislevel.lyl.mvp.model.bean.BlessDetailGiftListBean;
import com.itislevel.lyl.mvp.model.bean.BlessGiftBean;
import com.itislevel.lyl.mvp.model.bean.BlessListBean;
import com.itislevel.lyl.mvp.model.bean.BlessOrderBean;
import com.itislevel.lyl.mvp.model.bean.BlessReceiveGiftBean;
import com.itislevel.lyl.mvp.model.bean.BlessReceiveYuBean;
import com.itislevel.lyl.mvp.model.bean.BlessSendGiftBean;
import com.itislevel.lyl.mvp.model.bean.CartUpdateBean;
import com.itislevel.lyl.mvp.model.bean.FileUploadBean;
import com.itislevel.lyl.mvp.model.bean.HappyBlessUsualLanguageBean;

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
public interface BlessingContract {
    interface View extends BaseView {
        //定义自己特有的方法
        void showContent(String msg);

        void happAdd(String action);
        void happyListMy(BlessListBean blessListBean);
        void happyViewCount(String action);
        void happyDel(String action);
        void happyList(BlessListBean blessListBean);
        void happyComment(BlessCommentBean blessCommentBean);
        void happyCommentDel(String action);
        void happyLike(BlessAddLikeBean blessAddLikeBean);
        void happyBlessAdd(BlessAddBean blessAddBean);
        void happyBlessReceiveList(BlessReceiveYuBean blessReceiveYuBean);
        void happyUsualLanguage(HappyBlessUsualLanguageBean blessUsualLanguageBeanb);
        void happyGiftList(BlessGiftBean blessGiftBean);
        void happyGiftReceiveListMy(BlessReceiveGiftBean blessReceiveGiftBean);
        void happyGiftSendListMy(BlessSendGiftBean blessSendGiftBean);


        void happyOrderAdd(String blessOrderBean);
        void happyCartAdd(String message);
        void happyCartList(BlessCartListBean blessCartListBean);
        void happyCartUpdate(CartUpdateBean message);
        void happyCartDel(String message);
        void happySearch(BlessListBean blessListBean );

        void happyDetailsGiftList(BlessDetailGiftListBean detailGiftListBean );



        void uploadHeader(FileUploadBean fileUploadBean );

        void alipay(AliPayBean action);


    }
    interface Presenter extends BasePresenter<View> {
        //定义自己特有的方法
        void loadData(int num, int page);

        void happAdd(String action);
        void happyListMy(String action);
        void happyViewCount(String action);
        void happyDel(String action);
        void happyList(String action);
        void happyComment(String action);
        void happyCommentDel(String action);
        void happyLike(String action);
        void happyBlessAdd(String action);
        void happyBlessReceiveList(String action);
        void happyUsualLanguage(String action);
        void happyGiftList(String action);
        void happyGiftReceiveListMy(String action);
        void happyGiftSendListMy(String action);

        void happyOrderAdd(String action);
        void happyCartAdd(String action);
        void happyCartList(String action);
        void happyCartUpdate(String action);
        void happyCartDel(String action);

        void happySearch(String action);
        void happyDetailsGiftList(String action);





        void uploadHeader( @Part MultipartBody.Part file);

        void alipay(String action);


    }
}
