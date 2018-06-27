package com.itislevel.lyl.mvp.ui.test;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.text.Html;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.itislevel.lyl.R;
import com.itislevel.lyl.base.RootActivity;
import com.itislevel.lyl.mvp.model.bean.AppUpdate;
import com.itislevel.lyl.mvp.model.bean.UserHeaderNickInfo;
import com.itislevel.lyl.mvp.ui.main.MainActivity;
import com.itislevel.lyl.mvp.ui.main.MainContract;
import com.itislevel.lyl.mvp.ui.main.MainPresenter;
import com.itislevel.lyl.utils.ActivityUtil;
import com.itislevel.lyl.utils.QRCodeUtil;
import com.itislevel.lyl.utils.ToastUtil;
import com.itislevel.lyl.utils.rxbus.RxBus;

import java.util.Date;
import java.util.List;

import butterknife.BindView;

public class TestActivity extends RootActivity<MainPresenter> implements MainContract.View, View
        .OnClickListener {

    @BindView(R.id.rxtest)
    Button rxtest;

    @BindView(R.id.tv_test_partial_click)
    TextView tv_test_partial_click;

    @BindView(R.id.test_video)
    Button test_video;
    @BindView(R.id.btn_qrcode_gene)
    Button btn_qrcode_gene;
    @BindView(R.id.iv_qrcode_gene_result)
    ImageView iv_qrcode_gene_result;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_test;
    }

    @Override
    protected void initEventAndData() {
        rxtest.setOnClickListener(this);


        testTextViewClick();
        test_video.setOnClickListener(this);
        btn_qrcode_gene.setOnClickListener(this);
    }

    /**
     * textview 部分可点击
     * tv_test_partial_click
     */
    private void testTextViewClick() {

        String strClick = "这里可以点击";
        tv_test_partial_click.setText(Html.fromHtml("<font color=\"#ffoooo\">红色</font>text文本"));
        SpannableString spString = new SpannableString(strClick);
        spString.setSpan(new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                ToastUtil.Success("success");
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(Color.BLUE);//设置颜色
            }
        }, 0, strClick.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        tv_test_partial_click.append(spString);
        tv_test_partial_click.setMovementMethod(LinkMovementMethod.getInstance());//开始相应点击事件

    }

    @Override
    public void testShowView(String smg) {

    }

    @Override
    public void userInfoById(List<UserHeaderNickInfo> userHeaderNickInfos) {

    }

    @Override
    public void appupdate(AppUpdate appUpdate) {

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
    public void onClick(View v) {
//        if (v.getId()==R.id.rxtest){
//            RxBus.getInstance().post(RxBus.getInstance().getTag(MainActivity.class,RxBus
// .TAG_UPDATE),
//                    new Date().toString());
//        }
        switch (v.getId()) {
            case R.id.rxtest:
                RxBus.getInstance().post(RxBus.getInstance().getTag(MainActivity.class, RxBus
                                .TAG_UPDATE),
                        new Date().toString());
                break;
            case R.id.test_video:
                ActivityUtil.getInstance().openActivity(this, TestPlayerActivity.class);
                break;
            case R.id.btn_qrcode_gene:
                qrcodeGene();
                break;
        }
    }

    private void qrcodeGene() {
        Bitmap bitmap = encodeAsBitmap("1553333333");
        Bitmap bitmap_logo = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        bitmap = QRCodeUtil.addLogo(bitmap, bitmap_logo);
        if (bitmap != null) {
            iv_qrcode_gene_result.setImageBitmap(bitmap);
        }
    }

    private Bitmap encodeAsBitmap(String str) {
//        String uri = resultTextView.getText().toString();
//      Bitmap bitmap = BitmapUtil.create2DCoderBitmap(uri, mScreenWidth/2, mScreenWidth/2);
//        Bitmap bitmap;
//        try {
//            bitmap = QRCodeUtil.createQRCode(str, 400);
//
//            if(bitmap != null){
//                iv_qrcode_gene_result.setImageBitmap(bitmap);
//            }
//            return  bitmap;
//
//        } catch (WriterException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
        return null;
    }
}
