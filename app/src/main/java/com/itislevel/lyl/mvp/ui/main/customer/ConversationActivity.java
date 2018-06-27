package com.itislevel.lyl.mvp.ui.main.customer;

import com.itislevel.lyl.R;
import com.itislevel.lyl.base.RootActivity;
import com.itislevel.lyl.mvp.model.bean.CFTabBean;
import com.itislevel.lyl.utils.ChatUtil;

import java.util.List;

import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;

public class ConversationActivity extends RootActivity<CustomerPresenter> implements
        CustomerContract.View{


    @Override
    protected int getLayoutId() {
        return R.layout.activity_conversation;
    }

    @Override
    protected void initEventAndData() {
//        ChatUtil.startConversation(this, Conversation.ConversationType.PRIVATE,"itisi2","走一个");
        setToolbarTvTitle("客服");
        String currentUserId = RongIM.getInstance().getCurrentUserId();
        int a=0;

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
