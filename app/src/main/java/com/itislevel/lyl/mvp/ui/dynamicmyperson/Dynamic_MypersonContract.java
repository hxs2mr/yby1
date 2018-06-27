package com.itislevel.lyl.mvp.ui.dynamicmyperson;

import com.itislevel.lyl.base.BasePresenter;
import com.itislevel.lyl.base.BaseView;
import com.itislevel.lyl.mvp.model.bean.PersonalCommunBean;

/**
 * Created by Administrator on 2018\5\3 0003.
 */

public interface Dynamic_MypersonContract {
    interface  View extends BaseView{
        void frist_loader(String msg);
        void dynamicfollow(String msg);
        void personalCommun(PersonalCommunBean bean);
        void delefollow(String msg);

    }
    interface Presenter extends BasePresenter<View>
    {
        void dynamicfollow(String action);
        void frist_loader(String msg);
        void personalCommun(String msg);
        void delefollow(String msg);
    }
}
