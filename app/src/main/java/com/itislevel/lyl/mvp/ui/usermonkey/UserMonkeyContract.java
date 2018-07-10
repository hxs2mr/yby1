package com.itislevel.lyl.mvp.ui.usermonkey;

import com.itislevel.lyl.base.BasePresenter;
import com.itislevel.lyl.base.BaseView;

/**
 * 作者： XS
 * 邮箱：1363826037@qq.com
 * 描述:
 * 创建时间:  2018\7\7 0007 10:47
 */
public  interface  UserMonkeyContract {
    interface  View extends BaseView{

    }
    interface  Presenter extends BasePresenter<View>
    {

    }
}
