package com.itislevel.lyl.mvp.ui.usermonkey.setting;

import com.itislevel.lyl.base.BasePresenter;
import com.itislevel.lyl.base.BaseView;
import com.itislevel.lyl.mvp.model.bean.BankCardBean;
import com.itislevel.lyl.mvp.model.bean.BlankListBean;
import com.itislevel.lyl.mvp.model.bean.BlankNameBean;

/**
 * 作者： XS
 * 邮箱：1363826037@qq.com
 * 描述:
 * 创建时间:  2018\7\10 0010 11:07
 */
public interface MonkeySettingContract {
    interface View extends BaseView{
        void queryBankNameByIdCard(BlankNameBean blankNameBean);
        void queryBankBranchList(BlankListBean blankNameBean);
        void bankCardVerification(BankCardBean data);
        void finishVerification(String data);
        void getSSMCode(String action);

    }
    interface Presenter extends BasePresenter<View>
    {
        void queryBankNameByIdCard(String msg);
        void queryBankBranchList(String msg);
        void bankCardVerification(String msg);
        void finishVerification(String data);
        void getSSMCode(String action);
    }
}
