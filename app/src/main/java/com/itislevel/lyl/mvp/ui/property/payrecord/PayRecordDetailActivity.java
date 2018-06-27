package com.itislevel.lyl.mvp.ui.property.payrecord;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageButton;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.itislevel.lyl.R;
import com.itislevel.lyl.adapter.LiveAddressBean;
import com.itislevel.lyl.base.RootActivity;
import com.itislevel.lyl.mvp.model.bean.PayLuBean;
import com.itislevel.lyl.utils.ActivityUtil;
import com.itislevel.lyl.utils.SharedPreferencedUtils;
import com.itislevel.lyl.utils.UtilsStyle;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static android.content.DialogInterface.BUTTON_NEUTRAL;
import static android.content.DialogInterface.BUTTON_POSITIVE;
import static com.itislevel.lyl.utils.DateUtil.timeSpanToDateTime1;

/**
 * Created by Administrator on 2018\6\1 0001.
 */

public class PayRecordDetailActivity  extends RootActivity<PayRecordPresenter> implements PayRecordContract.View{

    @BindView(R.id.total_monkey)
    AppCompatTextView total_monkey ;

    @BindView(R.id.double_total_monkey)
    AppCompatTextView double_total_monkey ;

    @BindView(R.id.record_fanshi)
    AppCompatTextView record_fanshi ;

    @BindView(R.id.record_address)
    AppCompatTextView record_address ;

    @BindView(R.id.record_userphone)
    AppCompatTextView record_userphone ;

    @BindView(R.id.record_time)
    AppCompatTextView record_time ;

    @BindView(R.id.record_types)
    AppCompatTextView record_types ;

    @BindView(R.id.record_number)
    AppCompatTextView record_number ;

    @BindView(R.id.fei_imag)
    AppCompatImageView fei_imag;

    private Bundle bundle;
    private String ordernum = "";
    private String liveaddress = "";
    private String paytotalfee = "";
    private String types = "";
    private long creattime = 0;
    private String paymethod ="";
    private String username ="";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UtilsStyle.setStatusBarMode(this,true); //黑色的主题图标
    }
    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_recorddetail;
    }

    @Override
    protected void initEventAndData() {
        bundle = getIntent().getExtras();
        ordernum = bundle.getString("ordernum");
        liveaddress = bundle.getString("liveaddress");
        paytotalfee = bundle.getString("paytotalfee");
        types = bundle.getString("types");
        creattime = bundle.getLong("creattime");
        paymethod = bundle.getString("paymethod");
        username = bundle.getString("username");
        total_monkey.setText("-"+paytotalfee);
        String name =  SharedPreferencedUtils.getStr("villagename","");
        record_address.setText(name+liveaddress);
        record_number.setText(ordernum);
        if(types.equals("1"))
        {
            record_types.setText("物业缴费");
            fei_imag.setBackgroundResource(R.mipmap.fan_wu);
        }else   if(types.equals("2"))
        {
            record_types.setText("车位费");
            fei_imag.setBackgroundResource(R.mipmap.ce_wei);
        }else {
            fei_imag.setBackgroundResource(R.mipmap.all_fei);
            record_types.setText("物业缴费 & 车位费");
            double_total_monkey.setVisibility(View.VISIBLE);
            double_total_monkey.setText("物业缴费 + 车位费="+paytotalfee);
        }
        record_userphone.setText( SharedPreferencedUtils.getStr("proprety_phone","")+"-"+username);
        record_time.setText(timeSpanToDateTime1(creattime));

        if(paymethod.equals("1"))
        {
            record_fanshi.setText("支付宝");
        }else {
            record_fanshi.setText("微信");
        }
    }
    @OnClick({R.id.p_p_back,R.id.call_button_linear})
    public  void  onclick(View view){
        switch (view.getId())
        {
            case R.id.p_p_back:
                ActivityUtil.getInstance().closeActivity(this);
                break;
            case R.id.call_button_linear://打电话
                show_call();
                break;
        }

    }

    public void show_call() {
        //实例化建造者
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //设置警告对话框的标题
        //设置警告显示的图片
//        builder.setIcon(android.R.drawable.ic_dialog_alert);
        //设置警告对话框的提示信息
        builder.setMessage("拨打18385655626");
        //设置”正面”按钮，及点击事件
        builder.setPositiveButton("呼叫", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Intent.ACTION_DIAL);

                Uri data = Uri.parse("tel:" + "18385655626");
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
    public void estatesPayList(List<PayLuBean> bean) {

    }

    @Override
    public void findLiveaddress(List<LiveAddressBean> list) {

    }
}
