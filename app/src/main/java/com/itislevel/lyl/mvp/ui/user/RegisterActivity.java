package com.itislevel.lyl.mvp.ui.user;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.text.InputType;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.flyco.animation.FadeEnter.FadeEnter;
import com.flyco.animation.FadeExit.FadeExit;
import com.flyco.dialog.widget.NormalDialog;
import com.itislevel.lyl.R;
import com.itislevel.lyl.app.App;
import com.itislevel.lyl.app.Constants;
import com.itislevel.lyl.base.RootActivity;
import com.itislevel.lyl.mvp.model.bean.FileUploadBean;
import com.itislevel.lyl.mvp.model.bean.HomeBean;
import com.itislevel.lyl.mvp.model.bean.MyReceiveAddrBean;
import com.itislevel.lyl.mvp.model.bean.RegistBean;
import com.itislevel.lyl.mvp.model.bean.UserInfoBean;
import com.itislevel.lyl.mvp.model.http.request.RegistRequest;
import com.itislevel.lyl.mvp.model.http.request.SMSValidateRequest;
import com.itislevel.lyl.utils.ActivityUtil;
import com.itislevel.lyl.utils.GsonUtil;
import com.itislevel.lyl.utils.RegexUtil;
import com.itislevel.lyl.utils.SharedPreferencedUtils;
import com.itislevel.lyl.utils.ToastUtil;
import com.itislevel.lyl.utils.imageload.ImageLoadConfiguration;
import com.itislevel.lyl.utils.imageload.ImageLoadProxy;
import com.itislevel.lyl.utils.rxbus.RxBus;
import com.itislevel.lyl.utils.rxbus.annotation.UseRxBus;
import com.itislevel.lyl.widget.DownTimer;
import com.orhanobut.logger.Logger;
import com.vondear.rxtools.RxActivityTool;
import com.vondear.rxtools.RxConstants;
import com.vondear.rxtools.RxSPTool;
import com.vondear.rxtools.activity.ActivityScanerCode;


import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import qiu.niorgai.StatusBarCompat;

@UseRxBus
public class RegisterActivity extends RootActivity<UserPresenter> implements
        UserContract
        .View, RadioGroup.OnCheckedChangeListener {

    private boolean isCheckRecommendPhone = true; //是否选择了推荐号码
    @BindView(R.id.register_ok)
    AppCompatButton btn_register;
    @BindView(R.id.iv_kaiyan_biyan)
    AppCompatImageView iv_kaiyan_biyan;

    @BindView(R.id.register_username)
    TextInputEditText et_name;

    @BindView(R.id.register_password)
    TextInputEditText et_password;

    @BindView(R.id.register_password_agin)
    TextInputEditText et_repassword;


    @BindView(R.id.register_phone)
    TextInputEditText et_phone;

    @BindView(R.id.register_yzm)
    AppCompatEditText et_validate;

    @BindView(R.id.rg_recommend)
    RadioGroup rg_recommend;

    @BindView(R.id.rb_yes)
    RadioButton rb_yes;
    @BindView(R.id.rb_no)
    RadioButton rb_no;

    @BindView(R.id.et_recomment_phone)
    EditText et_recomment_phone;


    @BindView(R.id.rb_agree)
    RadioButton rb_agree;

    @BindView(R.id.iv_scan_qrcode)
    ImageView iv_scan_qrcode;

    @BindView(R.id.tv_agreement)
    TextView tv_agreement;

    @BindView(R.id.forget_miao)
    AppCompatTextView btn_getsmscode;


    @Override
    protected int getLayoutId() {
        return R.layout.register_fragment;
    }

    @Override
    protected void initEventAndData() {
        StatusBarCompat.translucentStatusBar(this, true);
        rg_recommend.setOnCheckedChangeListener(this);
        setToolbarTvTitle("注册");
    }
    /**
     * 是否同意协议
     */
    private boolean isAgree = false;

    @OnClick({R.id.register_ok, R.id.iv_kaiyan_biyan, R.id.tv_agreement, R.id.rb_agree,R.id.forget_miao,R.id.iv_scan_qrcode})
    public void click(View v) {
        switch (v.getId()) {
            case R.id.register_ok://注册
                regist();
                break;
            case R.id.forget_miao://获取验证码
                getValidate();
                break;
            case R.id.tv_agreement://跳转 注册协议
                readAgreement();
                break;

            case R.id.rb_agree://同意
                isAgree = !isAgree;
                rb_agree.setChecked(isAgree);
                break;
            case R.id.iv_kaiyan_biyan://密码切换
                int pos = et_password.getSelectionStart();//记录光标的位置
                if (et_password.getInputType() != (InputType.TYPE_CLASS_TEXT | InputType
                        .TYPE_TEXT_VARIATION_PASSWORD)) {
                    et_password.setInputType(InputType.TYPE_CLASS_TEXT | InputType
                            .TYPE_TEXT_VARIATION_PASSWORD);//隐藏密码
                    ImageLoadProxy.getInstance()
                            .load(new ImageLoadConfiguration.Builder(App.getInstance())
                                    .url(R.mipmap.icon_biyan)
                                    .imageView(iv_kaiyan_biyan).build());
                } else {
                    et_password.setInputType(InputType.TYPE_CLASS_TEXT | InputType
                            .TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    ImageLoadProxy.getInstance()
                            .load(new ImageLoadConfiguration.Builder(App.getInstance())
                                    .url(R.mipmap.icon_kaiyan)
                                    .imageView(iv_kaiyan_biyan).build());
                }
                et_password.setSelection(pos);
                break;
            case R.id.iv_scan_qrcode: //扫描
                scanQRCode();
                break;

        }
    }

    /**
     * 注册
     */
    private void regist() {
        String name = et_name.getText().toString().trim();
        String password = et_password.getText().toString().trim();
        String reassword = et_repassword.getText().toString().trim();
        String phone = et_phone.getText().toString().trim();
        String smscode = et_validate.getText().toString().trim();

        if (TextUtils.isEmpty(name)) {//用户名还应该有一定的规则
            ToastUtil.Info("用户名不能为空");
            return;
        }
        if (TextUtils.isEmpty(password)) {//密码
            ToastUtil.Info("密码不能为空");
            return;
        }
        if (TextUtils.isEmpty(reassword)) {//重复密码
            ToastUtil.Info("确认密码不能为空");
            return;
        }
        if (password.length()<6||password.length()>16){
            ToastUtil.Info("密码长度为6-16位");
            return;
        }
        //两次密码一致性判断
        if (!password.equals(reassword)){
            ToastUtil.Info("两次密码不一致");
            return;
        }

        if (TextUtils.isEmpty(phone)) {//电话号码 ---正则校验
            ToastUtil.Info("电话不能为空");
            return;
        }
        if (!RegexUtil.isMobileExact(phone)){
            ToastUtil.Warning("电话号码格式不正确");
            return;
        }
        if (TextUtils.isEmpty(smscode)) {//验证码-短信
            ToastUtil.Info("验证码不能为空");
            return;
        }

        if (!isAgree){
            ToastUtil.Info("请阅读并同意注册协议");
            return;
        }

/*        Map<String, Object> request = new HashMap<>();
        request.put("token", SharedPreferencedUtils.getStr(Constants.USER_TOKEN,""));
        request.put("usernum", SharedPreferencedUtils.getStr(Constants.USER_NUM,""));
        request.put("username", name);
        request.put("phone", phone);
        request.put("password", password);
        request.put("randcode", smscode);
        request.put("recommendphone",et_recomment_phone.getText().toString().trim());*/

            RegistRequest request = new RegistRequest();

            request.setUsername(name);
            request.setPassword(password);
            request.setPhone(phone);
            request.setRandcode(smscode);

        // isCheckRecommendPhone 是否有推荐号码
            String recommendPhone = et_recomment_phone.getText().toString().trim()+"";
            request.setRecommendphone(recommendPhone);

        mPresenter.regist(GsonUtil.obj2JSON(request));

    }

    /**
     * 获取验证码
     */
    private void getValidate() {
        String phone = et_phone.getText().toString().trim();
        if (TextUtils.isEmpty(phone)) {
            ToastUtil.Info("号码不能为空");
            return;
        }
        //判断号码的合法性

        Map<String, Object> request = new HashMap<>();

        request.put("token", "");
        request.put("usernum", "");
        request.put("phone", phone);

        mPresenter.getSSMCode(GsonUtil.obj2JSON(request));

        DownTimer timer = new DownTimer();
        int totalTime = 60 * 1000;
        timer.setTotalTime(totalTime);
        timer.setIntervalTime(1000);
        timer.setTimerLiener(new DownTimer.TimeListener() {
            @Override
            public void onFinish() {
                if (btn_getsmscode != null) {
                    btn_getsmscode.setClickable(true);
                    btn_getsmscode.setText("获取验证码");
                    btn_getsmscode.setBackground(getResources().getDrawable(R.drawable
                            .shape_btn_getvalidatecode_normal));
                }
            }

            @Override
            public void onInterval(long remainTime) {
                if (btn_getsmscode != null) {
                    btn_getsmscode.setText("还有" + (remainTime / 1000) + "秒");
                    if (btn_getsmscode.isClickable()) {
                        btn_getsmscode.setBackground(getResources().getDrawable(R.drawable
                                .shape_btn_getvalidatecode_disable));
                        btn_getsmscode.setClickable(false);
                    }
                }

            }
        });
        timer.start();

    }

    /**
     * 阅读注册协议
     */
    private void readAgreement() {
        ActivityUtil.getInstance().openActivity(this,UseAppAgreementActivity.class);
    }

    /**
     * 扫描二维码
     */
    private void scanQRCode() {
        RxActivityTool.skipActivity(RegisterActivity.this, ActivityScanerCode.class);
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==Constants.RESULT_CODE_QR_SCAN){
            if (data!=null){
                Bundle bundle = data.getExtras();
                if (bundle!=null){
                    String result = bundle.getString("result");
                    if (!TextUtils.isEmpty(result)){
                        et_recomment_phone.setText(result);
                    }else{
                        Logger.w("result为空");
                    }

                }else{
                    Logger.w("bundle为空");
                }
            }else{
                Logger.w("data为空");
            }

        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        et_recomment_phone.setText( RxSPTool.getContent(this, RxConstants.SP_SCAN_CODE));
        RxSPTool.putContent(this, RxConstants.SP_SCAN_CODE, "");
    }

    /**
     * 单纯的是为了备份代码
     *
     * @param v
     */
    public void back(View v) {
        switch (v.getId()) {
            case R.id.btn_register:
//                int mWindowWidth, mWindowHeight;
//                Dialog dialog = new Dialog(this);
//                View view = LayoutInflater.from(this).inflate(R.layout.dialog_register, null);
//                DisplayMetrics displayMetrics = this.getResources().getDisplayMetrics();
//                mWindowWidth = displayMetrics.widthPixels;
//                mWindowHeight = displayMetrics.heightPixels;
//                dialog.setContentView(view, new ViewGroup.MarginLayoutParams(mWindowWidth,
//                        ViewGroup.MarginLayoutParams.MATCH_PARENT));
//                dialog.show();

                final NormalDialog dialog = new NormalDialog(RegisterActivity.this);
                dialog.isTitleShow(false)//
                        .btnNum(1)
                        .bgColor(Color.parseColor("#ffffff"))//
                        .cornerRadius(5)//
                        .content("赠送10元代金卷已到你的零钱")//
                        .contentGravity(Gravity.CENTER)//
                        .contentTextColor(Color.parseColor("#000000"))//
                        .dividerColor(Color.parseColor("#222222"))//
                        .btnTextSize(15.5f, 15.5f)//
                        .btnTextColor(Color.parseColor("#000000"), Color.parseColor("#000000"))//
                        .btnText("确定")
                        .btnPressColor(Color.parseColor("#2B2B2B"))//
                        .widthScale(0.85f)//
                        .showAnim(new FadeEnter())//
                        .dismissAnim(new FadeExit())//
                        .show();


                break;
            case R.id.iv_kaiyan_biyan:
                ToastUtil.Success("suc");
                int pos = et_password.getSelectionStart();//记录光标的位置
                if (et_password.getInputType() != (InputType.TYPE_CLASS_TEXT | InputType
                        .TYPE_TEXT_VARIATION_PASSWORD)) {
                    et_password.setInputType(InputType.TYPE_CLASS_TEXT | InputType
                            .TYPE_TEXT_VARIATION_PASSWORD);//隐藏面膜
                } else {
                    et_password.setInputType(InputType.TYPE_CLASS_TEXT | InputType
                            .TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                }
//                TransformationMethod transformationMethod = et_password.getTransformationMethod();
//                et_password.setTransformationMethod(HideReturnsTransformationMethod.getInstance
// ());//明文
//                et_password.setTransformationMethod(PasswordTransformationMethod.getInstance())
// ;//密文
                et_password.setSelection(pos);
                break;

        }

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
//        et_validate.setText(smscode);
//        btn_getsmscode.setText(smscode);

//        String[] split = action.split("\\+");
//        ssmCode = split[1];

        ToastUtil.Success("获取验证码成功");
    }

    @Override
    public void regist(RegistBean registBean) {
        ToastUtil.Info("注册成功");

        SharedPreferencedUtils.setStr(Constants.USER_NAME,registBean.getUsername());
        SharedPreferencedUtils.setStr(Constants.USER_PHONE,registBean.getPhone());
        SharedPreferencedUtils.setStr(Constants.USER_NUM,registBean.getUsernum());
        SharedPreferencedUtils.setStr(Constants.USER_RECOMMEND_PHONE,registBean.getRecommendphone());

        ActivityUtil.getInstance().closeActivity(this);
        notivyLoginActivity();

    }

    @Override
    public void uploadHeader(FileUploadBean fileUploadBean) {

    }

    @Override
    public void userPerfectPersonal(String message) {

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



    /**
     * 通知 login 页面 更新数据
     * registSuccess
     */
    private void notivyLoginActivity() {
        RxBus.getInstance().post(RxBus.getInstance().getTag(LoginActivity.class,RxBus.TAG_CREATE),
               "registSuccess");
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (group.getId()) {
            case R.id.rg_recommend:
                if (checkedId == R.id.rb_yes) {
                    isCheckRecommendPhone = true;
                   // et_recomment_phone.setVisibility(View.VISIBLE);


                } else if (checkedId == R.id.rb_no) {
                    isCheckRecommendPhone = false;
                   // et_recomment_phone.setVisibility(View.GONE);
                }
                break;
        }
    }
}
