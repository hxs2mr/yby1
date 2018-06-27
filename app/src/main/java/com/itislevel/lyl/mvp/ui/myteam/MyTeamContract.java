package com.itislevel.lyl.mvp.ui.myteam;


import com.itislevel.lyl.base.BasePresenter;
import com.itislevel.lyl.base.BaseView;
import com.itislevel.lyl.mvp.model.bean.MeiZiBean;
import com.itislevel.lyl.mvp.model.bean.MyTeamBean;

import java.util.List;

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
public interface MyTeamContract {
    interface View extends BaseView {
        //定义自己特有的方法
        void showContent(String msg);
        void loadData(List<MeiZiBean> meiZiBeans);

        void teamProblemList(MyTeamBean myTeamBean);
        void teamReplay(String message);

    }
    interface Presenter extends BasePresenter<View> {
        //定义自己特有的方法
        void loadData(int num, int page);

        void teamProblemList(String action);
        void teamReplay(String action);

    }
}
