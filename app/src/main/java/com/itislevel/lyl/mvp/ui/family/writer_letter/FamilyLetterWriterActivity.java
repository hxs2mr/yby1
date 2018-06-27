package com.itislevel.lyl.mvp.ui.family.writer_letter;

import android.app.ActivityManager;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.View;
import android.widget.Switch;

import com.bigkoo.pickerview.TimePickerView;
import com.itislevel.lyl.R;
import com.itislevel.lyl.app.Constants;
import com.itislevel.lyl.base.RootActivity;
import com.itislevel.lyl.mvp.model.bean.EventLetter;
import com.itislevel.lyl.utils.ActivityUtil;
import com.itislevel.lyl.utils.DateUtil;
import com.itislevel.lyl.utils.GsonUtil;
import com.itislevel.lyl.utils.SAToast;
import com.itislevel.lyl.utils.SharedPreferencedUtils;
import com.itislevel.lyl.widget.LineEditText;

import org.greenrobot.eventbus.EventBus;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindInt;
import butterknife.BindView;

/**
 * Created by Administrator on 2018\5\14 0014.
 */

public class FamilyLetterWriterActivity extends RootActivity<LetterWriterPresenter>implements LetterWriterContract.View, View.OnClickListener {

    @BindView(R.id.title)
    AppCompatEditText title;

    @BindView(R.id.title_chenhu)
    AppCompatEditText title_chenhu;

    @BindView(R.id.et_comment)
    LineEditText et_comment;

    @BindView(R.id.letter_name)
    AppCompatEditText letter_name;

    @BindView(R.id.letter_date)
    AppCompatTextView letter_date;

    @BindView(R.id.linear_show)
    LinearLayoutCompat linear_show;

    @BindView(R.id.linear_writer)
    LinearLayoutCompat linear_writer;

    @BindView(R.id.et_comment1)
    AppCompatEditText et_comment1;
    private  Bundle bundle;
    private String feteid;
    private String type="";

    private String createtime="";
    private String title_start="";
    private String resfu="";
    private String comment="";
    private String fetename="";
    private String looknum="";
    private String letterid="";
    private int frist = 0 ;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_letter_writer;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void initEventAndData() {
        bundle=  getIntent().getExtras();
        feteid = bundle.getString("feteid");
        letter_date.setOnClickListener(this);
        setToolbarTvTitle("祭祀信");

        type =bundle.getString("type");
        if(type.equals("0"))
        {
            et_comment1.setVisibility(View.VISIBLE);//没有横线的
            et_comment.setVisibility(View.GONE);//有横线的

            setToolbarMoreTxt("发布");
            setToolbarMoreClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(checkForm())
                    {
                        loadingDialog.show();
                        uploaddata();
                    }
                }
            });

            et_comment1.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if (hasFocus) {
                        if(frist==0)
                        {
                            frist =1;
                            et_comment1.setText("\u3000\u3000");
                        }
                    } else {
                        // 此处为失去焦点时的处理内容
                    }
                }
            });
        }else {
            et_comment1.setVisibility(View.GONE);//没有横线的
            et_comment.setVisibility(View.VISIBLE);//有横线的
            createtime=bundle.getString("createdtime");
            title_start=bundle.getString("title");
            resfu=bundle.getString("respectfully");
            comment=bundle.getString("comment");
            fetename = bundle.getString("fetename");
            looknum = bundle.getString("looknum");
            letterid = bundle.getString("letterid");
            title.setText("【"+title_start+"】");
            title.setCursorVisible(false);
            title.setFocusable(false);
            title.setFocusableInTouchMode(false);

            title_chenhu.setText(resfu);
            title_chenhu.setCursorVisible(false);
            title_chenhu.setFocusable(false);
            title_chenhu.setFocusableInTouchMode(false);

            et_comment.setText("\u3000\u3000"+comment);
            et_comment.setCursorVisible(false);
            et_comment.setFocusable(false);
            et_comment.setFocusableInTouchMode(false);

            letter_name.setText(fetename);
            letter_name.setCursorVisible(false);
            letter_name.setFocusable(false);
            letter_name.setFocusableInTouchMode(false);

            letter_date.setText(createtime);
            letter_date.setFocusable(false);
            letter_date.setFocusableInTouchMode(false);

            addlooknum();//添加浏览量的接口
        }
    }

    private void addlooknum() {//新增浏览量
        Map<String, String> request = new HashMap<>();
        request.put("token", SharedPreferencedUtils.getStr(Constants.USER_TOKEN));
        request.put("usernum", SharedPreferencedUtils.getStr(Constants.USER_NUM));
        request.put("letterid", letterid);
        request.put("looknum",looknum);
        mPresenter.looknumLetter(GsonUtil.obj2JSON(request));
    }

    private void uploaddata(){
        Map<String, String> request = new HashMap<>();
        request.put("token", SharedPreferencedUtils.getStr(Constants.USER_TOKEN));
        request.put("usernum", SharedPreferencedUtils.getStr(Constants.USER_NUM));
        request.put("userid", SharedPreferencedUtils.getStr(Constants.USER_ID));
        request.put("feteid", feteid);
        request.put("title", title.getText().toString().trim());
        request.put("respectfully", title_chenhu.getText().toString().trim());
        request.put("comment", et_comment1.getText().toString().trim());
        request.put("fetename", letter_name.getText().toString().trim());
        request.put("createdtime", letter_date.getText().toString().trim());
        mPresenter.save(GsonUtil.obj2JSON(request));
    }

    private boolean checkForm()
    {
        String title_s = title.getText().toString();
        String title_chenhu_s = title_chenhu.getText().toString();
        String et_comment_s;
        if(type.equals("0"))
        {
            et_comment_s = et_comment1.getText().toString();
        }else {
            et_comment_s = et_comment.getText().toString();
        }
        String letter_name_s = letter_name.getText().toString();
        String letter_date_s = letter_date.getText().toString();

        //检查输入的文本是否正确
        boolean isPass = true;
        if(title_s.isEmpty())
        {
            title.setError("添加标题");
            isPass = false;
        }else {
            title.setError(null);
        }
        if(title_chenhu_s.isEmpty())
        {
            title_chenhu.setError("添加称呼");
            isPass = false;
        }else {
            title_chenhu.setError(null);
        }

        if(et_comment_s.isEmpty())
        {
            et_comment.setError("添加内容");
            isPass = false;
        }else {
            et_comment.setError(null);
        }

        if(letter_name_s.isEmpty())
        {
            letter_name.setError("添加姓名");
            isPass = false;
        }else {
            letter_name.setError(null);
        }
        if(letter_date_s.isEmpty())
        {
            letter_date.setError("选择日期");
            isPass = false;
        }else {
            letter_date.setError(null);
        }

        return isPass;
    }


    @Override
    public void stateError(Throwable e) {
        super.stateError(e);
        SAToast.makeText(this,"服务器异常").show();
        loadingDialog.dismiss();
    }

    @Override
    public void save(String msg) {  //保存成功
        loadingDialog.dismiss();
        SAToast.makeText(this,"写信成功").show();
        EventBus.getDefault().post(new EventLetter("success"));
        ActivityUtil.getInstance().closeActivity(this);
    }

    @Override
    public void looknumLetter(String msg) {

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
        if(type.equals("0"))
        {
            TimePickerView pvTime;

            Calendar selectedDate = Calendar.getInstance();

            Calendar startDate = Calendar.getInstance();
            startDate.set(1968, 5, 5);

            Calendar endDate = Calendar.getInstance();
            endDate.set(DateUtil.getCurrentYear(), DateUtil.getCurrentMonth(), DateUtil
                    .getCurrentDay());

            //时间选择器
            pvTime = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener
                    () {
                @Override
                public void onTimeSelect(Date date, View v) {//选中事件回调
                    // 这里回调过来的v,就是show()方法里面所添加的 View 参数，如果show的时候没有添加参数，v则为null
                    letter_date.setText(DateUtil.getCurrentDate(date));
                }
            })
                    //年月日时分秒 的显示与否，不设置则默认全部显示
                    .setType(new boolean[]{true, true, true, false, false, false})
                    .setLabel("", "", "", "", "", "")
                    .isCenterLabel(false)
                    .setDividerColor(Color.DKGRAY)
                    .setContentSize(21)
                    .setCancelColor(Color.parseColor("#282828"))
                    .setSubmitColor(Color.parseColor("#282828"))
                    .setDate(selectedDate)
                    .setRangDate(startDate, endDate)
//                .setBackgroundId(0x00FFFFFF) //设置外部遮罩颜色
                    .setDecorView(null)
                    .build();

            pvTime.show();
        }
    }
}
