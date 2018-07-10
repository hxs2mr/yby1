package com.itislevel.lyl.mvp.ui.setting;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.itislevel.lyl.R;
import com.itislevel.lyl.base.RootActivity;
import com.itislevel.lyl.mvp.model.bean.FileUploadBean;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;

public class OperationmanualActivity extends RootActivity<SettingPresenter> implements
        SettingContract.View {

    @BindView(R.id.webView)
    WebView webView;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_operationmanual;
    }

    @Override
    protected void initEventAndData() {

//支持javascript
        webView.getSettings().setJavaScriptEnabled(true);
// 设置可以支持缩放
        webView.getSettings().setSupportZoom(true);
// 设置出现缩放工具
        webView.getSettings().setBuiltInZoomControls(true);
//扩大比例的缩放
        webView.getSettings().setUseWideViewPort(true);
        webView.setInitialScale(25);
//自适应屏幕
        webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        setToolbarTvTitle("APP操作手册");
        webView.loadUrl("file:///android_asset/caozuo.html");
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
    public void showContent(String msg) {

    }

    @Override
    public void getSSMCode(String smscode) {

    }

    @Override
    public void updatePhone(String msg) {

    }

    @Override
    public void userAddFeedback(String msg) {

    }

    @Override
    public void uploadHeader(FileUploadBean fileUploadBean) {

    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (webView != null) {
            webView.removeAllViews();
            webView.destroy();
        }
    }
    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }
}
