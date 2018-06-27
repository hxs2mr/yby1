package com.itislevel.lyl.mvp.ui.special;


import com.itislevel.lyl.base.BasePresenter;
import com.itislevel.lyl.base.BaseView;
import com.itislevel.lyl.mvp.model.bean.BlessCartListBean;
import com.itislevel.lyl.mvp.model.bean.CartUpdateBean;
import com.itislevel.lyl.mvp.model.bean.FileUploadBean;
import com.itislevel.lyl.mvp.model.bean.SpecialGiftByIdBean;
import com.itislevel.lyl.mvp.model.bean.SpecialGiftListBean;
import com.itislevel.lyl.mvp.model.bean.SpecialOrderCompleteBean;
import com.itislevel.lyl.mvp.model.bean.SpecialOrderDetailBean;
import com.itislevel.lyl.mvp.model.bean.SpecialReceiveAddressBean;
import com.itislevel.lyl.mvp.model.bean.SpecialReturnBean;
import com.itislevel.lyl.mvp.model.bean.SpecialTuiKuanDetailBean;
import com.itislevel.lyl.mvp.model.bean.SpecialTuiKuanUpdateBean;
import com.itislevel.lyl.mvp.model.bean.SpecialTypeBean;
import com.itislevel.lyl.mvp.model.http.response.LYLResponse;

import okhttp3.MultipartBody;
import retrofit2.http.Field;
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
public interface SpecialContract {
    interface View extends BaseView {
        //定义自己特有的方法
        void showContent(String msg);

        void specialType(SpecialTypeBean specialTypeBean);
        void specialList(SpecialGiftListBean specialListBean);
        void specialById(SpecialGiftByIdBean specialByIdBean);
        void specialImmediatelyOrder(String action);
        void specialShopOrder(String action);
        void specialReceiveAddress(SpecialReceiveAddressBean addressBean);
        void specialOrderDetail(SpecialOrderDetailBean detailBean);
        void specialTuiKuanDetail(SpecialTuiKuanDetailBean tuiKuanDetailBean);
        void specialTuiKuan(String action);
        void specialTuiKuanUpdate(SpecialTuiKuanUpdateBean tuiKuanUpdateBean);
        void specialTuiKuanUpdate2(String action);
        void specialOrderComplete(SpecialOrderCompleteBean completeBean);
        void specialOrderGoPay(String action);
        void specialOrderCancel(String action);
        void specialShenQingTuiKuan(String action);
        void specialOrderConfirm(String action);
        void specialReturnList(SpecialReturnBean returnBean);


        void uploadHeader(FileUploadBean fileUploadBean );

        void happyCartAdd(String message);
        void happyCartList(BlessCartListBean blessCartListBean);
        void happyCartUpdate(CartUpdateBean message);
        void happyCartDel(String message);

    }
    interface Presenter extends BasePresenter<View> {
        //定义自己特有的方法
        void loadData(int num, int page);

        void specialType(String action);
        void specialList(String action);
        void specialById(String action);
        void specialImmediatelyOrder(String action);
        void specialShopOrder(String action);
        void specialReceiveAddress(String action);
        void specialOrderDetail(String action);
        void specialTuiKuanDetail(String action);
        void specialTuiKuan(String action);
        void specialTuiKuanUpdate(String action);
        void specialTuiKuanUpdate2(String action);
        void specialOrderComplete(String action);
        void specialOrderGoPay(String action);
        void specialOrderCancel(String action);
        void specialShenQingTuiKuan(String action);
        void specialOrderConfirm(String action);
        void specialReturnList(String action);





        void uploadHeader( @Part MultipartBody.Part file);

        void happyCartAdd(String action);
        void happyCartList(String action);
        void happyCartUpdate(String action);
        void happyCartDel(String action);


    }
}
