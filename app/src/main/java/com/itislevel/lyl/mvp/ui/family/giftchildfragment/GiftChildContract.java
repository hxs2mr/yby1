package com.itislevel.lyl.mvp.ui.family.giftchildfragment;

import com.itislevel.lyl.base.BasePresenter;
import com.itislevel.lyl.base.BaseView;
import com.itislevel.lyl.mvp.model.bean.BlessCartListBean;
import com.itislevel.lyl.mvp.model.bean.FamilyGiftListBean;

/**
 * Created by Administrator on 2018\5\21 0021.
 */

public class GiftChildContract {
    interface  View extends BaseView{
        void familyListGift(FamilyGiftListBean familyGiftListBean);
        void familyCartList(BlessCartListBean blessCartListBean);
        void immediateOrder(String blessOrderBean);
    }
    interface  Presenter extends BasePresenter<View> {
        void familyListGift(String action);//礼物列表
        void familyCartList(String action);
        void immediateOrder(String blessOrderBean);
    }
}
