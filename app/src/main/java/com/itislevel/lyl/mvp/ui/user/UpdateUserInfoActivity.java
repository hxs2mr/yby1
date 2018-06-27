package com.itislevel.lyl.mvp.ui.user;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.AppCompatTextView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.bigkoo.pickerview.TimePickerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.itislevel.lyl.R;
import com.itislevel.lyl.app.App;
import com.itislevel.lyl.app.Constants;
import com.itislevel.lyl.base.RootActivity;
import com.itislevel.lyl.mvp.model.bean.FileUploadBean;
import com.itislevel.lyl.mvp.model.bean.HomeBean;
import com.itislevel.lyl.mvp.model.bean.MyReceiveAddrBean;
import com.itislevel.lyl.mvp.model.bean.RegistBean;
import com.itislevel.lyl.mvp.model.bean.UpdataUserBean;
import com.itislevel.lyl.mvp.model.bean.UserInfoBean;
import com.itislevel.lyl.utils.ActivityUtil;
import com.itislevel.lyl.utils.DateUtil;
import com.itislevel.lyl.utils.GsonUtil;
import com.itislevel.lyl.utils.RegexUtil;
import com.itislevel.lyl.utils.SharedPreferencedUtils;
import com.itislevel.lyl.utils.ToastUtil;
import com.itislevel.lyl.utils.imageload.ImageLoadConfiguration;
import com.itislevel.lyl.utils.imageload.ImageLoadProxy;
import com.itislevel.lyl.utils.rxbus.RxBus;
import com.itislevel.lyl.utils.rxbus.annotation.Subscribe;
import com.itislevel.lyl.utils.rxbus.annotation.UseRxBus;
import com.itislevel.lyl.utils.rxbus.event.EventThread;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

@UseRxBus
public class UpdateUserInfoActivity extends RootActivity<UserPresenter> implements UserContract
        .View {
    @BindView(R.id.iv_header)
    CircleImageView iv_header;

    @BindView(R.id.tv_gender)
    TextView tv_gender;

    @BindView(R.id.tv_birthday)
    TextView tv_birthday;

    @BindView(R.id.et_idcard)
    EditText et_idcard;

    @BindView(R.id.et_phoen)
    AppCompatTextView et_phoen;


    @BindView(R.id.iv_update_password)
    ImageView iv_update_password;

    @BindView(R.id.rl_update_password)
    RelativeLayout rl_update_password;

    @BindView(R.id.et_name)
    EditText et_name;

    @BindView(R.id.et_nickname)
    EditText et_nickname;


    private InputMethodManager inputMethodManager;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_update_user_info;
    }

    @Override
    protected void initEventAndData() {
        setToolbarTvTitle("修改个人信息");
        setToolbarMoreTxt("编辑"); //编辑 保存

        inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);


        tv_gender.setText(SharedPreferencedUtils.getStr(Constants.USER_GENDER).equals("1") ? "男"
                : "女");
        tv_birthday.setText(SharedPreferencedUtils.getStr(Constants.USER_BIRTHDAY));
        et_idcard.setText(SharedPreferencedUtils.getStr(Constants.USER_IDCARD));
        et_name.setText(SharedPreferencedUtils.getStr(Constants.USER_REAL_NAME));
        et_nickname.setText(SharedPreferencedUtils.getStr(Constants.USER_NICK_NAME));

        et_phoen.setText(SharedPreferencedUtils.getStr(Constants.USER_PHONE));


        setAllDisableClick();

        setToolbarMoreClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                TextView tvMorView = getTvMorView();
                String tempTitle = tvMorView.getText().toString();
                int pos = et_name.getText().length();//记录光标的位置

                if (tempTitle.equals("编辑")) {
                    setToolbarMoreTxt("保存");

                    et_name.setFocusable(true);
                    et_name.setFocusableInTouchMode(true);
                    et_name.setGravity(Gravity.LEFT | Gravity.CENTER);
                    et_name.requestFocus();
//                    et_name.setBackground(getResources().getDrawable(R.drawable
// .shape_et_user_focus));

                    et_name.setSelection(pos);


                    et_idcard.setFocusable(true);
                    et_idcard.setFocusableInTouchMode(true);
                    et_idcard.setGravity(Gravity.LEFT | Gravity.CENTER);
//                    et_idcard.setBackground(getResources().getDrawable(R.drawable
// .shape_et_user_focus));


                    et_nickname.setFocusable(true);
                    et_nickname.setFocusableInTouchMode(true);
                    et_nickname.setGravity(Gravity.LEFT | Gravity.CENTER);
//                    et_nickname.setBackground(getResources().getDrawable(R.drawable
// .shape_et_user_focus));


//                    setAllEnableClick();
                    setAllEnableClick();


                } else {
                    Map<String, Object> request = new HashMap<>();
                    request.put("token", SharedPreferencedUtils.getStr(Constants.USER_TOKEN));
                    request.put("usernum", SharedPreferencedUtils.getStr(Constants.USER_NUM));
                    request.put("userid", SharedPreferencedUtils.getStr(Constants.USER_ID));
                 /*   String headerUrl = SharedPreferencedUtils.getStr(Constants.USER_HEADER);


                    if (!TextUtils.isEmpty(headerUrl)) {
                        int start = headerUrl.lastIndexOf("/");
                        int end = headerUrl.lastIndexOf("?");
                        if (start <= 1) {
                            start = 0;
                        }
                        if (end <= 1) {
                            end = headerUrl.length();
                        }
                        String temp = headerUrl.substring(start, end);
                        request.put("imgurl", temp);

                    } else {
                        request.put("imgurl", "");

                    }*/
                    String idnum = et_idcard.getText().toString().trim();
                    if (!TextUtils.isEmpty(idnum)) {
                        boolean idCard18 = RegexUtil.isIDCard18(idnum);
                        if (!idCard18) {
                            ToastUtil.Warning("身份证不合法");
                            return;
                        }
                    }

               /*     String phone = et_phoen.getText().toString().trim();
                    if (!TextUtils.isEmpty(phone)) {
                        boolean mobileExact = RegexUtil.isMobileExact(phone);
                        if (!mobileExact) {
                            ToastUtil.Warning("手机号不合法");
                            return;
                        }
                    }*/

                    String realname = et_name.getText().toString().trim();
                    if (!TextUtils.isEmpty(realname)) {
                        int length = realname.length();
                        if (length < 2 || length > 8) {
                            ToastUtil.Warning("姓名长度不合法");
                            return;
                        }
                    }

                    String nickname=et_nickname.getText().toString().trim();

                    if (!TextUtils.isEmpty(nickname)) {
                        int length = nickname.length();
                        if (length > 5) {
                            ToastUtil.Warning("昵称不能超过5个字符");
                            return;
                        }
                    }


                    request.put("realname", realname);
                    request.put("nickname", nickname);
                    request.put("gender", tv_gender.getText().toString().trim().equals("男") ? "1" :
                            "0");
                    request.put("idcard", idnum);
                    request.put("birthday", tv_birthday.getText().toString().trim());
                   // request.put("phone", phone);

                    mPresenter.userPerfectPersonal(GsonUtil.obj2JSON(request));


                    setToolbarMoreTxt("编辑");


                    et_name.setFocusable(false);
                    et_name.setFocusableInTouchMode(false);
                    et_name.setGravity(Gravity.RIGHT | Gravity.CENTER);
//                    et_name.setBackground(getResources().getDrawable(R.drawable
// .shape_et_user_normal));

                    et_idcard.setFocusable(false);
                    et_idcard.setFocusableInTouchMode(false);
                    et_idcard.setGravity(Gravity.RIGHT | Gravity.CENTER);
//                    et_idcard.setBackground(getResources().getDrawable(R.drawable
// .shape_et_user_normal));

//                    et_phoen.setBackground(getResources().getDrawable(R.drawable
// .shape_et_user_normal));

                    et_nickname.setFocusable(false);
                    et_nickname.setFocusableInTouchMode(false);
                    et_nickname.setGravity(Gravity.RIGHT | Gravity.CENTER);
//                    et_nickname.setBackground(getResources().getDrawable(R.drawable
// .shape_et_user_normal));

                    setAllDisableClick();
                }

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        String headerUrl = SharedPreferencedUtils.getStr(Constants.USER_HEADER);

        if (TextUtils.isEmpty(headerUrl)) {
            ImageLoadProxy.getInstance()
                    .load(new ImageLoadConfiguration.Builder(this)
                            .url(R.mipmap.icon_default_header_circle)
                            .imageView(iv_header).build());
        } else {
            headerUrl = Constants.IMG_SERVER_PATH + headerUrl.trim();
            ImageLoadProxy.getInstance()
                    .load(new ImageLoadConfiguration.Builder(this)
                            .defaultImageResId(R.mipmap.icon_default_header_circle)
                            .url(headerUrl)
                            .imageView(iv_header).build());
        }

//        String headerUrl = SharedPreferencedUtils.getStr(Constants.USER_HEADER);
//
//        if (TextUtils.isEmpty(headerUrl)) {
//            ImageLoadProxy.getInstance()
//                    .load(new ImageLoadConfiguration.Builder(UpdateUserInfoActivity.this)
//                            .url(R.mipmap.icon_default_header_circle)
//                            .imageView(iv_header).build());
//        } else {
//            headerUrl = Constants.IMG_SERVER_PATH + headerUrl.trim();
////            ImageLoadProxy.getInstance()
////                    .load(new ImageLoadConfiguration.Builder(UpdateUserInfoActivity.this)
////                            .defaultImageResId(R.mipmap.icon_default_header_circle)
////                            .url(headerUrl)
////                            .imageView(iv_header).build());
//
//            Glide.with(this)
//                    .load(headerUrl)
//                    .skipMemoryCache(false)
//                    .diskCacheStrategy(DiskCacheStrategy.ALL)
//                    .placeholder(R.mipmap.icon_default_header_circle)
//                    .into(iv_header);
//
//        }
    }

    private void setAllDisableClick() {
//        tv_gender.setClickable(false);
//        tv_birthday.setClickable(false);

    }

    private void setAllEnableClick() {
        tv_gender.setClickable(true);
        tv_birthday.setClickable(true);

    }


    @OnClick({R.id.iv_header, R.id.tv_gender, R.id.tv_birthday, R.id.iv_update_password,
            R.id.et_phoen, R.id.et_idcard, R.id.et_name, R.id.et_nickname,R.id.rl_update_password})
    public void click(View view) {

        InputMethodManager imm = (InputMethodManager) et_name
                .getContext().getSystemService(
                        Context.INPUT_METHOD_SERVICE);

        switch (view.getId()) {
            case R.id.et_phoen://点击的手机
                setToolbarMoreTxt("保存");
                et_phoen.setFocusable(true);
                et_phoen.setFocusableInTouchMode(true);
                et_phoen.setGravity(Gravity.LEFT | Gravity.CENTER);

                break;
            case R.id.et_idcard://点击的身份证
                setToolbarMoreTxt("保存");
                et_idcard.setFocusable(true);
                et_idcard.setFocusableInTouchMode(true);
                et_idcard.setGravity(Gravity.LEFT | Gravity.CENTER);
                break;
            case R.id.et_name://点击的真实姓名
                setToolbarMoreTxt("保存");
                et_name.setFocusable(true);
                et_name.setFocusableInTouchMode(true);
                et_name.setGravity(Gravity.LEFT | Gravity.CENTER);
                break;
            case R.id.et_nickname://点击的昵称
                setToolbarMoreTxt("保存");
                et_nickname.setFocusable(true);
                et_nickname.setFocusableInTouchMode(true);
                et_nickname.setGravity(Gravity.LEFT | Gravity.CENTER);
                break;

            case R.id.iv_header:
                ActivityUtil.getInstance().openActivity(UpdateUserInfoActivity.this,
                        UpdateHeaderActivity.class);

                break;
            case R.id.tv_gender://性别
                setToolbarMoreTxt("保存");
                tv_gender.setClickable(true);

                if (imm.isActive()) {
                    imm.hideSoftInputFromWindow(
                            et_name.getApplicationWindowToken(), 0);
                }

                List<String> genderItems = new ArrayList<>();
                genderItems.add("男");
                genderItems.add("女");

                OptionsPickerView optionsPickerView = new OptionsPickerView.Builder
                        (UpdateUserInfoActivity.this, new OptionsPickerView
                                .OnOptionsSelectListener() {
                            @Override
                            public void onOptionsSelect(int options1, int options2, int options3,
                                                        View v) {
                                tv_gender.setText(genderItems.get(options1));
                            }
                        }).build();

                optionsPickerView.setPicker(genderItems);

                optionsPickerView.show();

                break;
            case R.id.tv_birthday://点击生日
                setToolbarMoreTxt("保存");
                tv_birthday.setClickable(true);
                if (imm.isActive()) {
                    imm.hideSoftInputFromWindow(
                            et_name.getApplicationWindowToken(), 0);
                }
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
                        tv_birthday.setText(DateUtil.getCurrentDate(date));
                    }
                })
                        //年月日时分秒 的显示与否，不设置则默认全部显示
                        .setType(new boolean[]{true, true, true, false, false, false})
                        .setLabel("", "", "", "", "", "")
                        .isCenterLabel(false)
                        .setDividerColor(Color.DKGRAY)
                        .setContentSize(21)
                        .setDate(selectedDate)
                        .setRangDate(startDate, endDate)
//                .setBackgroundId(0x00FFFFFF) //设置外部遮罩颜色
                        .setDecorView(null)
                        .build();

                pvTime.show();

                break;

            case R.id.rl_update_password://更改密码 跳转界面
                ActivityUtil.getInstance().openActivity(UpdateUserInfoActivity.this,
                        UpdatePasswordActivity.class);
                break;

        }
    }


    @Override
    public void showContent(String msg) {

    }

    @Override
    public void login(UserInfoBean response) {

    }

    @Override
    public void getValidateCode(String validateCode) {

    }

    @Override
    public void getSSMCode(String smscode) {

    }

    @Override
    public void regist(RegistBean registBean) {

    }

    @Override
    public void uploadHeader(FileUploadBean fileUploadBean) {

    }

    @Override
    public void userPerfectPersonal(String message) {
        Logger.w(message);
        ToastUtil.Success("个人信息修改成功");

       // SharedPreferencedUtils.setStr(Constants.USER_PHONE, et_phoen.getText().toString().trim());
        SharedPreferencedUtils.setStr(Constants.USER_NICK_NAME, et_nickname.getText().toString()
                .trim());

        SharedPreferencedUtils.setStr(Constants.USER_GENDER, tv_gender.getText().toString().trim
                () == "男" ? "1" : "0");
        SharedPreferencedUtils.setStr(Constants.USER_BIRTHDAY, tv_birthday.getText().toString()
                .trim());
        SharedPreferencedUtils.setStr(Constants.USER_IDCARD, et_idcard.getText().toString().trim());

        SharedPreferencedUtils.setStr(Constants.USER_REAL_NAME, et_name.getText().toString().trim
                ());

        EventBus.getDefault().post(new UpdataUserBean(""));
        ActivityUtil.getInstance().closeActivity(this);
    }

    @Override
    public void userModifyPassword(String message) {

    }

    @Override
    public void userModifyNickname(String message) {

    }

    @Override
    public void userFindRecAddress(MyReceiveAddrBean address) {

    }

    @Override
    public void userUpdateRecAddress(String message) {

    }

    @Override
    public void userGiftmy(Object message) {

    }

    @Override
    public void userModifyHeader(String message) {

    }

    @Override
    public void userForgetPassword(String message) {

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


    @Subscribe(tag = RxBus.TAG_UPDATE, thread = EventThread.MAIN_THREAD)
    public void refreshHeader(String message) {
        if ("refreshheader".equals(message)) {
            Logger.i("刷新头像-用户信息更新界面");

            ToastUtil.Success("头像更新成功");

            //刷新头像
            String headerUrl = SharedPreferencedUtils.getStr(Constants.USER_HEADER);
            if (TextUtils.isEmpty(headerUrl)) {
                ImageLoadProxy.getInstance()
                        .load(new ImageLoadConfiguration.Builder(App.getInstance())
                                .defaultImageResId(R.mipmap.icon_default_header_circle)
                                .url(R.mipmap.icon_default_header_circle)
                                .imageView(iv_header).build());
            } else {
                headerUrl = Constants.IMG_SERVER_PATH + headerUrl;
                ImageLoadProxy.getInstance()
                        .load(new ImageLoadConfiguration.Builder(App.getInstance())
                                .defaultImageResId(R.mipmap.icon_default_header_circle)
                                .url(headerUrl)
                                .imageView(iv_header).build());
            }
        }
    }

}
