package com.itislevel.lyl.mvp.ui.main.childfragment;

import com.itislevel.lyl.base.BasePresenter;
import com.itislevel.lyl.base.BaseView;
import com.itislevel.lyl.mvp.model.bean.CFChildBean;
import com.itislevel.lyl.mvp.model.bean.CFPinBean;

/**
 * Created by Administrator on 2018\5\16 0016.
 */

public interface ChildContract {
    interface View extends BaseView{
        //加载列表
        void fristload(CFChildBean cfChildBean);

        //点赞
        void updatepointnum(Integer msg);

        //浏览量
        void looknumFlatcount(String data);

        //添加评论
        void addFlatComment(String action);

        //评论列表
        void cfcommentlist(CFPinBean bean);

        //点赞
        void cf_addzan(String data);

        //删除
        void delFlatComment(String action);
    }
    interface Presenter extends BasePresenter<View>
    {
        void fristload(String msg);
        void updatepointnum(String msg);
        //浏览量
        void looknumFlatcount(String data);

        void addFlatComment(String action);

        void cfcommentlist(String bean);

        //点赞
        void cf_addzan(String data);
        //删除
        void delFlatComment(String action);
    }
}
