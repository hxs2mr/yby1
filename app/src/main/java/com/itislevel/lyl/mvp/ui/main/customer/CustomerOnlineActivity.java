package com.itislevel.lyl.mvp.ui.main.customer;

import android.view.View;

import com.itislevel.lyl.R;
import com.itislevel.lyl.base.RootActivity;
import com.itislevel.lyl.mvp.model.bean.CFTabBean;
import com.itislevel.lyl.utils.ChatUtil;

import java.util.List;

import io.rong.imlib.model.Conversation;
import qiu.niorgai.StatusBarCompat;

public class CustomerOnlineActivity extends RootActivity<CustomerPresenter> implements
        CustomerContract.View{


    @Override
    protected int getLayoutId() {
        return R.layout.activity_customer_online;
    }

    @Override
    protected void initEventAndData() {
        StatusBarCompat.translucentStatusBar(this, false);
        setToolbarTvTitle("客服");
        setToolbarTvClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ActivityUtil.getInstance().openActivity(CustomerOnlineActivity.this,ConversationActivity.class);
//                ChatUtil.startConversation(CustomerOnlineActivity.this, Conversation.ConversationType.PRIVATE,"itisi2","走一个");
            }
        });

    }



    @Override
    public void showContent(String msg) {

    }

    @Override
    public void loadtable(List<CFTabBean> list) {

    }


    @Override
    public void stateEmpty() {

    }

    @Override
    public void stateLoading() {

    }

    @Override
    public void stateError() {

    }

    @Override
    public void stateError(Throwable e) {

    }

    @Override
    public void stateSuccess() {

    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }
}
