package com.itislevel.lyl.mvp.ui.main.customer;

import android.view.View;
import android.widget.TextView;

import com.itislevel.lyl.R;
import com.itislevel.lyl.base.RootActivity;
import com.itislevel.lyl.common.RxUtil;
import com.itislevel.lyl.mvp.model.bean.CFTabBean;
import com.vondear.rxtools.RxDeviceTool;
import com.vondear.rxtools.RxTool;

import java.util.List;

import butterknife.OnClick;

public class CustomerPhoneActivity extends RootActivity<CustomerPresenter> implements CustomerContract.View{


    @Override
    protected int getLayoutId() {
        return R.layout.activity_customer_phone;
    }

    @Override
    protected void initEventAndData() {
        setToolbarTvTitle("客服电话");
    }

    @Override
    public void showContent(String msg) {

    }

    @Override
    public void loadtable(List<CFTabBean> list) {

    }

    @OnClick({R.id.tv_phone1,R.id.tv_phone2})
    public void click(View view){
        TextView textView= (TextView) view;
        RxDeviceTool.callPhone(this,textView.getText().toString());
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
