package com.itislevel.lyl.mvp.ui.main.mine.fan;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.itislevel.lyl.R;
import com.itislevel.lyl.base.RootActivity;
import com.itislevel.lyl.mvp.model.bean.FanExitBean;
import com.itislevel.lyl.mvp.model.bean.FanRecodeBean;
import com.itislevel.lyl.mvp.model.bean.FanXianBean;
import com.itislevel.lyl.mvp.model.bean.ShanHomeBean;
import com.itislevel.lyl.mvp.ui.property.PropertLoginActivity;
import com.itislevel.lyl.utils.ActivityUtil;
import com.itislevel.lyl.utils.SharedPreferencedUtils;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.OnClick;

import static com.itislevel.lyl.utils.DateUtil.timeSpanToDate;

/**
 * 作者： XS
 * 邮箱：1363826037@qq.com
 * 描述: 用户的详情
 * 创建时间:  2018\7\2 0002 11:07
 */
public class PersonFanUserDetailAcivity extends RootActivity<PersonFanPresenter>implements PersonFanContract.View {

    @BindView(R.id.company_name)
    AppCompatTextView company_name;

    @BindView(R.id.guisu_address)
    AppCompatTextView guisu_address;

    @BindView(R.id.user_name)
    AppCompatTextView user_name;

    @BindView(R.id.user_phone)
    AppCompatTextView user_phone;

    @BindView(R.id.user_yu)
    AppCompatTextView user_yu;

    @BindView(R.id.phone_address)
    AppCompatTextView phone_address;

    @BindView(R.id.careate_time)
    AppCompatTextView careate_time;

    @BindView(R.id.exit_button)
    AppCompatButton  exit_button;
    private Bundle bundle;
    private String yu="";
    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }
    @Override
    protected int getLayoutId() {
        return R.layout.activity_fanxianuser;
    }
    @Override
    protected void initEventAndData() {
        bundle = getIntent().getExtras();
        yu = bundle.getString("yu");
        setToolbarTvTitle("查看详情");
        initData();
    }

    private void initData() {
        company_name.setText(SharedPreferencedUtils.getStr("fan_companyname",""));

        String guishu = SharedPreferencedUtils.getStr("fan_provname","")+SharedPreferencedUtils.getStr("fan_cityname","")+SharedPreferencedUtils.getStr("fan_cuntryname","");
        guisu_address.setText(guishu);
        user_name.setText(SharedPreferencedUtils.getStr("fan_linkman",""));
        user_phone.setText(SharedPreferencedUtils.getStr("fan_linkphone",""));
        phone_address.setText(SharedPreferencedUtils.getStr("fan_linkaddress",""));
        user_yu.setText(yu);
        long time = SharedPreferencedUtils.getLong("fan_createdtime",0l);
        careate_time.setText(timeSpanToDate(time));
    }

    @Override
    public void stateEmpty() {

    }
    @OnClick(R.id.exit_button)
    public void onclick(View viev)
    {
        show_clear_cart();
    }

    public void show_clear_cart(){
        //实例化建造者
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //设置警告对话框的标题
        builder.setTitle("商家登录");
        //设置警告显示的图片
//        builder.setIcon(android.R.drawable.ic_dialog_alert);
        //设置警告对话框的提示信息
        builder.setMessage("确认要退出商家登录？");
            //设置”正面”按钮，及点击事件
            builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //测试的地方
                SharedPreferencedUtils.setBool("fan_login", false);
                EventBus.getDefault().post(new FanExitBean(""));
                ActivityUtil.getInstance().closeActivity(PersonFanUserDetailAcivity.this);
            }
        });
        //设置“反面”按钮，及点击事件
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        //设置“中立”按钮，及点击事件
        builder.setNeutralButton("等等看吧", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        //显示对话框
        builder.show();
    }

    @Override
    public void stateLoading() {

    }

    @Override
    public void stateSuccess() {

    }

    @Override
    public void merchantmainpage(ShanHomeBean bean) {

    }

    @Override
    public void rechargeRecord(FanRecodeBean bean) {

    }

    @Override
    public void cashbackist(FanXianBean bean) {

    }

    @Override
    public void onlinerecharge(String msg) {

    }
}
