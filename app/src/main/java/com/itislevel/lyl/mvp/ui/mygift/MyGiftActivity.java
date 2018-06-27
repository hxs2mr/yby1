package com.itislevel.lyl.mvp.ui.mygift;

import android.view.View;

import com.itislevel.lyl.R;
import com.itislevel.lyl.base.RootActivity;
import com.itislevel.lyl.mvp.model.bean.MyGiftBean;
import com.itislevel.lyl.utils.ActivityUtil;

import butterknife.OnClick;

public class MyGiftActivity extends RootActivity<MyGiftPresenter> implements MyGiftContract.View {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_gift_my;
    }

    @Override
    protected void initEventAndData() {
        setToolbarTvTitle("礼品卷");

    }

    @OnClick({R.id.tv_voucher})
    public void click(View view){
        switch (view.getId()){
            case R.id.tv_voucher:
                ActivityUtil.getInstance().openActivity(MyGiftActivity.this,MyGiftListActivity.class);
                break;

        }
    }

    @Override
    public void showContent(String msg) {

    }

    @Override
    public void userGiftList(MyGiftBean myGiftBean) {

    }

    @Override
    public void stateEmpty() {

    }

    @Override
    public void stateLoading() {

    }



    @Override
    public void stateSuccess() {

    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }
}
