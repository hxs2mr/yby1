package com.itislevel.lyl.mvp.ui.about;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.itislevel.lyl.R;
import com.itislevel.lyl.app.Constants;
import com.itislevel.lyl.base.RootActivity;
import com.itislevel.lyl.mvp.ui.main.customer.CustomerOnlineActivity;
import com.itislevel.lyl.mvp.ui.mygift.MyGiftActivity;
import com.itislevel.lyl.mvp.ui.mygift.MyGiftListActivity;
import com.itislevel.lyl.mvp.ui.user.LoginActivity;
import com.itislevel.lyl.utils.ActivityUtil;
import com.itislevel.lyl.utils.ChatUtil;
import com.itislevel.lyl.utils.SharedPreferencedUtils;
import com.itislevel.lyl.utils.ToastUtil;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import io.rong.imlib.model.Conversation;
import qiu.niorgai.StatusBarCompat;

import static com.itislevel.lyl.mvp.ui.main.home.HomeFragment.customer_service_phone;

public class AboutActivity extends RootActivity<AboutPresenter> implements AboutContract.View, View.OnClickListener {
    @BindView(R.id.about_kehu_phone)
    AppCompatTextView about_kehu_phone;
    @BindView(R.id.about_kehu_xian)
    AppCompatTextView about_kehu_xian;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_about;
    }

    @Override
    protected void initEventAndData() {
        setToolbarTvTitle("关于友帮友");
        about_kehu_phone.setOnClickListener(this);
        about_kehu_xian.setOnClickListener(this);
    }

    @Override
    public void showContent(String msg) {

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

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.about_kehu_phone://客户电话
                show_call();
                break;
            case R.id.about_kehu_xian://在线客户
                ToastUtil.Info("暂未开通该功能!");
              //  login_kehu();
                break;
        }
    }
    public void show_call(){
        //实例化建造者
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //设置警告对话框的标题
        //设置警告显示的图片
//        builder.setIcon(android.R.drawable.ic_dialog_alert);
        //设置警告对话框的提示信息
        builder.setMessage("拨打"+customer_service_phone);
        //设置”正面”按钮，及点击事件
        builder.setPositiveButton("呼叫", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                Uri data = Uri.parse("tel:" +customer_service_phone);
                intent.setData(data);
                startActivity(intent);
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
    private void login_kehu() {
        boolean islogin = SharedPreferencedUtils.getBool("islogin", false);
        if (!islogin) {
            ActivityUtil.getInstance().openActivity(this, LoginActivity.class);
            return;
        }
        int anInt = SharedPreferencedUtils.getInt(Constants.USER_IS_CUSTOMER, 0);

        if (anInt == 0) {//用户
            ChatUtil.startConversation(this, Conversation.ConversationType
                    .PRIVATE, "12", "");

        } else {//客服
            Map<String, Boolean> supportedConversation = new HashMap<>();
            supportedConversation.put(Conversation.ConversationType.PRIVATE.getName(),
                    false);
            ChatUtil.startConversationList(this, supportedConversation);
        }
        ActivityUtil.getInstance().openActivity(this,CustomerOnlineActivity.class);
    }

}
