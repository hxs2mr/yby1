package com.itislevel.lyl.mvp.ui.setting;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.itislevel.lyl.R;
import com.itislevel.lyl.app.Constants;
import com.itislevel.lyl.base.RootActivity;
import com.itislevel.lyl.mvp.model.bean.FileUploadBean;
import com.itislevel.lyl.mvp.ui.main.MainActivity;
import com.itislevel.lyl.mvp.ui.user.LoginActivity;
import com.itislevel.lyl.utils.ActivityUtil;
import com.itislevel.lyl.utils.DataCacheManager;
import com.itislevel.lyl.utils.SharedPreferencedUtils;
import com.itislevel.lyl.utils.rxbus.annotation.UseRxBus;

import butterknife.BindView;
import butterknife.OnClick;

import static com.itislevel.lyl.utils.DataCacheManager.clearAllCache;
import static com.itislevel.lyl.utils.DataCacheManager.getTotalCacheSize;

@UseRxBus
public class SettingActivity extends RootActivity<SettingPresenter> implements
        SettingContract.View {

    @BindView(R.id.app_cache)
    AppCompatTextView app_cache ;
    private   String cache_size="";
    @Override
    protected int getLayoutId() {
        return R.layout.activity_setting;
    }

    @Override
    protected void initEventAndData(){
        setToolbarTvTitle("设置");
        init_cache();
        app_cache.setText(cache_size);
    }

    private void init_cache() {
        try {
            cache_size = getTotalCacheSize(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @OnClick({R.id.btn_logout, R.id.tv_account_safe_linear, R.id.tv_healp_linear,R.id.tv_clear_linear})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.tv_account_safe_linear:
                ActivityUtil.getInstance().openActivity(SettingActivity.this, AccountSafePreActivity.class);
                break;
            case R.id.tv_healp_linear:
                ActivityUtil.getInstance().openActivity(SettingActivity.this, HelpFeedbackActivity.class);
                break;

            case R.id.tv_clear_linear://清楚缓存
                show_call(cache_size);
                break;
            case R.id.btn_logout:
                //用户信息 情况

                SharedPreferencedUtils.setBool("islogin",false);

                SharedPreferencedUtils.setStr(Constants.USER_TOKEN, "");
                SharedPreferencedUtils.setStr(Constants.USER_ID,  "");
//                SharedPreferencedUtils.setStr(Constants.USER_NUM, "");
                SharedPreferencedUtils.setStr(Constants.USER_PHONE, "");
                SharedPreferencedUtils.setStr(Constants.USER_NAME, "");
                SharedPreferencedUtils.setStr(Constants.USER_NICK_NAME, "");

                SharedPreferencedUtils.setStr(Constants.USER_GENDER, "");
                SharedPreferencedUtils.setStr(Constants.USER_BIRTHDAY, "");
                SharedPreferencedUtils.setStr(Constants.USER_IDCARD, "");

                SharedPreferencedUtils.setStr(Constants.USER_REAL_NAME, "");
//                SharedPreferencedUtils.setStr(Constants.USER_HEADER,"");
                ActivityUtil.getInstance().openActivity(SettingActivity.this, LoginActivity.class);//不应该直接跳转
                break;
        }
    }


    public void show_call(String huancun){
        //实例化建造者
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //设置警告对话框的标题
        //设置警告显示的图片
//        builder.setIcon(android.R.drawable.ic_dialog_alert);
        //设置警告对话框的提示信息
        builder.setTitle("缓存");
        builder.setMessage("是否清除该APP缓存"+huancun);
        //设置”正面”按钮，及点击事件
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                clearAllCache(SettingActivity.this);
                dialog.cancel();
                app_cache.setText("0.0Byte");
            }
        });
        //设置“中立”按钮，及点击事件
        builder.setNeutralButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        //显示对话框
        builder.show();
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
