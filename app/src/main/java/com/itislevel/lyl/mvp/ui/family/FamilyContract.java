package com.itislevel.lyl.mvp.ui.family;


import com.itislevel.lyl.base.BasePresenter;
import com.itislevel.lyl.base.BaseView;
import com.itislevel.lyl.mvp.model.bean.BlessCartListBean;
import com.itislevel.lyl.mvp.model.bean.BlessListBean;
import com.itislevel.lyl.mvp.model.bean.BlessOrderBean;
import com.itislevel.lyl.mvp.model.bean.CartUpdateBean;
import com.itislevel.lyl.mvp.model.bean.FamilyAddBean;
import com.itislevel.lyl.mvp.model.bean.FamilyBlessListBean;
import com.itislevel.lyl.mvp.model.bean.FamilyBlessListRecevieBean;
import com.itislevel.lyl.mvp.model.bean.FamilyGiftListBean;
import com.itislevel.lyl.mvp.model.bean.FamilyListBean;
import com.itislevel.lyl.mvp.model.bean.FamilyMyListBean;
import com.itislevel.lyl.mvp.model.bean.FamilyPhotoFrameBean;
import com.itislevel.lyl.mvp.model.bean.FamilyQueryFramBackBean;
import com.itislevel.lyl.mvp.model.bean.FamilyReceiveGiftBean;
import com.itislevel.lyl.mvp.model.bean.FamilySacrificeTypeBean;
import com.itislevel.lyl.mvp.model.bean.FamilySendGiftRecordBean;
import com.itislevel.lyl.mvp.model.bean.FamilyUsualLanguageBean;
import com.itislevel.lyl.mvp.model.bean.FileUploadBean;
import com.itislevel.lyl.mvp.model.bean.FunsharingAddBean;
import com.itislevel.lyl.mvp.model.bean.FunsharingListBean;
import com.itislevel.lyl.mvp.model.bean.FunsharingMyBean;
import com.itislevel.lyl.mvp.model.bean.LetterBean;

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
public interface FamilyContract {
    interface View extends BaseView {
        //定义自己特有的方法
        void showContent(String msg);

        void familyAdd(String message);
        void familyList(FamilyListBean familyListBean);
        void familyListMy(FamilyListBean familyListBean);

        void familyBlessList(FamilyBlessListBean familyBlessListBean);//祝福语列表-祭语-针对祭祀的
        void familyReceiveSacrifice(FamilyReceiveGiftBean familyReceiveGiftBean);
        void familyListGift(FamilyGiftListBean familyGiftListBean);
        void familySendGift(FamilySendGiftRecordBean familySendGiftRecordBean);
        void familyReceiveBless(FamilyBlessListRecevieBean familyBlessListRecevieBean);
        void familyViewCount(String message);
        void familyCate(FamilySacrificeTypeBean familySacrificeTypeBean);
        void familyUsualLanguage(FamilyUsualLanguageBean familyUsualLanguageBean);
        void familyPhotoFrame(FamilyPhotoFrameBean familyPhotoFrameBean);
        void familyPhotoBack(FamilyPhotoFrameBean familyPhotoFrameBean);

        void familyBlessAdd(String message);
        void familySearch(FamilyListBean familyListBean);
        void familyReceiveGiftById(FamilyReceiveGiftBean familyReceiveGiftBean);

        void familySaveFPhotoFrameAndBack(String message);//
        void familyQueryFrameAndBack(FamilyQueryFramBackBean familyQueryFramBackBean);//


        void familyDel(String action);

        void uploadHeader(FileUploadBean fileUploadBean );

        void generatorOrder(String blessOrderBean);
        void immediateOrder(String blessOrderBean);

        void familyCartAdd(String message);
        void familyCartList(BlessCartListBean blessCartListBean);
        void familyCartUpdate(CartUpdateBean message);
        void familyCartDel(String message);
        void familySearch(BlessListBean blessListBean );
        void selectletter(LetterBean letterBean );

    }
    interface Presenter extends BasePresenter<View> {
        //定义自己特有的方法
        void loadData(int num, int page);

        void familyAdd(String action);//新建祭祀
        void familyList(String action);//祭祀首页
        void familyListMy(String action);//我的祭祀
        void familyBlessList(String action);//祝福语列表-祭语

        void familyReceiveGift(String action);//收到的礼物
        void familyListGift(String action);//礼物列表
        void familySendGift(String action);//送礼记录
        void familyReceiveBless(String action);//收到的祭祀语
        void familyViewCount(String action);
        void familyCate(String action);//礼品分类?
        void familyUsualLanguage(String action);//常用语
        void familyPhotoFrame(String action);//相框-背景
        void familyPhotoBack(String action);//相框-背景
        void familyBlessAdd(String action);//添加祝福 祭语
        void familySearch(String action);//搜索
        void familyReceiveGiftById(String action);//房间里面收到的礼物

        void familySaveFPhotoFrameAndBack(String action);//
        void familyQueryFrameAndBack(String action);//


        void familyDel(String action);


        void uploadHeader( @Part MultipartBody.Part file);

        void generatorOrder(String action);
        void immediateOrder(String action);

        void familyCartAdd(String action);
        void familyCartList(String action);
        void familyCartUpdate(String action);
        void familyCartDel(String action);

        void selectletter(String action );
    }
}
