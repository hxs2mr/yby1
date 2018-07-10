package com.itislevel.lyl.mvp.ui.userfan;

import com.itislevel.lyl.base.BasePresenter;
import com.itislevel.lyl.base.BaseView;
import com.itislevel.lyl.mvp.model.bean.UserFanBean;
import com.itislevel.lyl.mvp.model.bean.UserHistoryBean;
import com.itislevel.lyl.mvp.model.bean.UserPlanBean;
import com.itislevel.lyl.mvp.model.bean.UserPlanDetailBean;

import java.util.List;

/**
 * 作者： XS
 * 邮箱：1363826037@qq.com
 * 描述:
 * 创建时间:  2018\7\5 0005 14:19
 */
public interface UserFanContract {
    interface View extends BaseView
    {
        void cashbackPage(UserFanBean beans);
        void cashbackstages(UserPlanBean bean);
        void cashbackstagesDetails(UserPlanDetailBean bean);
        void cashbackRecord(UserHistoryBean bean);
        void  clickreceive(String data);
    }
    interface Presenter extends BasePresenter<View>
    {
        void cashbackPage(String msg);
        void cashbackstages(String msg);
        void cashbackstagesDetails(String msg);
        void cashbackRecord(String msg);
        void  clickreceive(String msg);
    }
}
