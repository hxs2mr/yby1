package com.itislevel.lyl.mvp.ui.user;

import android.graphics.Rect;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutCompat;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.itislevel.lyl.R;
import com.itislevel.lyl.app.Constants;
import com.itislevel.lyl.base.RootActivity;
import com.itislevel.lyl.mvp.model.bean.FileUploadBean;
import com.itislevel.lyl.mvp.model.bean.HomeBean;
import com.itislevel.lyl.mvp.model.bean.MyReceiveAddrBean;
import com.itislevel.lyl.mvp.model.bean.RefreshHeadBean;
import com.itislevel.lyl.mvp.model.bean.RegistBean;
import com.itislevel.lyl.mvp.model.bean.UserInfoBean;
import com.itislevel.lyl.mvp.model.http.request.ImgValidateRequest;
import com.itislevel.lyl.mvp.model.http.request.LoginRequest;
import com.itislevel.lyl.mvp.ui.main.MainActivity;
import com.itislevel.lyl.utils.ActivityUtil;
import com.itislevel.lyl.utils.ChatUtil;
import com.itislevel.lyl.utils.GsonUtil;
import com.itislevel.lyl.utils.SharedPreferencedUtils;
import com.itislevel.lyl.utils.ToastUtil;
import com.itislevel.lyl.utils.rxbus.RxBus;
import com.itislevel.lyl.utils.rxbus.annotation.Subscribe;
import com.itislevel.lyl.utils.rxbus.annotation.UseRxBus;
import com.itislevel.lyl.utils.rxbus.event.EventThread;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.OnClick;
import cn.jpush.android.api.JPushInterface;
import io.rong.imlib.RongIMClient;
import qiu.niorgai.StatusBarCompat;

@UseRxBus
public class LoginActivity extends RootActivity<UserPresenter> implements UserContract.View {

    @BindView(R.id.button_login)
    AppCompatButton btn_login;
    // et_name et_password et_validate  btn_login tv_regist tv_forget_password  tv_validate
    @BindView(R.id.tv_validate)
    AppCompatTextView tv_validate;

    @BindView(R.id.text_forget)
    AppCompatTextView tv_forget_password;

    @BindView(R.id.text_register)
    AppCompatTextView tv_regist;


    @BindView(R.id.login_username)
    TextInputEditText et_name;

    @BindView(R.id.login_userpassword)
    TextInputEditText et_password;

    @BindView(R.id.login_yzm)
    AppCompatEditText et_validate;

    @BindView(R.id.bottom_end)
    LinearLayoutCompat bottom_end;
    @BindView(R.id.login_start)
    LinearLayoutCompat login_start;
    @Override
    protected int getLayoutId() {
        return R.layout.login_fragment;
    }
    @Override
    protected void initEventAndData() {
     //   StatusBarCompat.translucentStatusBar(this, true);
        et_name.setText(SharedPreferencedUtils.getStr(Constants.USER_NAME));
        setToolbarTvTitle("登录");//toolbar中返回是默认开启的
        addLayoutListener(login_start,bottom_end);
    }
    //  et_name et_password et_validate  btn_login tv_regist tv_forget_password
    @OnClick({R.id.button_login, R.id.tv_validate, R.id.text_register, R.id.text_forget})
    public void click(View v) {
        switch (v.getId()) {
            case R.id.button_login:
                login();
                break;
            case R.id.tv_validate:
                getValidate();
                break;
            case R.id.text_register:
                ActivityUtil.getInstance().openActivity(LoginActivity.this, RegisterActivity.class);
                break;
            case R.id.text_forget:
                ActivityUtil.getInstance().openActivity(LoginActivity.this, ResetPasswordActivity
                        .class);

                break;
        }
    }

//    @Override
//    protected boolean setIsNavigationIconShow() {
//        return false;
//    }


    /**
     * 禁止侧滑
     *
     * @return
     */
//    @Override
//    protected boolean setSwipeEnabled() {
//        return false;
//    }

        /**
     * 获取验证码
     */
    private void getValidate() {
        String name = et_name.getText().toString().trim();
        if (TextUtils.isEmpty(name)) {
            ToastUtil.Info("请输入用户名");
            return;
        }
        loadingDialog.show();
        //获取用户名 判断
        ImgValidateRequest request = new ImgValidateRequest();
        request.setName(name);

        mPresenter.getValidateCode(GsonUtil.obj2JSON(request));
    }

    /**
     * 18823852027
     * 123456
     */
    private void login() {

        LoginRequest request = new LoginRequest();
        String name = et_name.getText().toString().trim();
        String password = et_password.getText().toString().trim();
        String validate = et_validate.getText().toString().trim();

        if (TextUtils.isEmpty(name)) {
            ToastUtil.Info("请输入用户名");
            return;
        }

        if (TextUtils.isEmpty(password)) {
            ToastUtil.Info("请输入密码");
            return;
        }

        if (TextUtils.isEmpty(validate)) {
            ToastUtil.Info("请输入用验证码");
            return;
        }

        loadingDialog.show();
        request.setName(name);
        request.setPassword(password);
        request.setRandcode(validate);

        mPresenter.login(GsonUtil.obj2JSON(request));

//                final NormalDialog dialog = new NormalDialog(LoginActivity.this);
//                dialog.isTitleShow(false)//
//                        .btnNum(1)
//                        .bgColor(Color.parseColor("#ffffff"))//
//                        .cornerRadius(5)//
//                        .content("账号或密码错误，请重新登陆")//
//                        .contentGravity(Gravity.CENTER)//
//                        .contentTextColor(Color.parseColor("#000000"))//
//                        .dividerColor(Color.parseColor("#222222"))//
//                        .btnTextSize(15.5f, 15.5f)//
//                        .btnTextColor(Color.parseColor("#000000"), Color.parseColor("#000000"))
//                        .btnText("确定")
//                        .btnPressColor(Color.parseColor("#2B2B2B"))//
//                        .widthScale(0.85f)//
//                        .showAnim(new FadeEnter())//
//                        .dismissAnim(new FadeExit())//
//                        .show();

//
//                dialog.content("你今天的抢购名额已用完~")//
//                        .btnNum(1)
//                        .btnText("继续逛逛")//
//                        .showAnim(new FadeEnter())//
//                        .dismissAnim(new FadeExit())//
//                        .show();

    }


    @Override
    public void showContent(String msg) {

    }

    /**
     * 登录
     *
     * @param response
     */
    @Override
    public void login(UserInfoBean response) {

        loadingDialog.dismiss();
        ToastUtil.Info("登录成功!");

        //用户信息 存入本地

        SharedPreferencedUtils.setBool("islogin",true);

        SharedPreferencedUtils.setStr(Constants.USER_TOKEN, response.getToken());
        SharedPreferencedUtils.setStr(Constants.RONGCLOUD_TOKEN, response.getRytoken());


        SharedPreferencedUtils.setStr(Constants.USER_ID, response.getUserid() + "");
        SharedPreferencedUtils.setStr(Constants.USER_NUM, response.getUsernum());

        SharedPreferencedUtils.setStr(Constants.USER_PHONE, response.getPhone());
        SharedPreferencedUtils.setStr(Constants.USER_NAME, response.getUsername());
        SharedPreferencedUtils.setStr(Constants.USER_NICK_NAME, response.getNickname());

        SharedPreferencedUtils.setStr(Constants.USER_GENDER,TextUtils.isEmpty(response.getGender())?"1":response.getGender());
        SharedPreferencedUtils.setStr(Constants.USER_BIRTHDAY, TextUtils.isEmpty(response.getBirthday())?"":response.getBirthday());
        SharedPreferencedUtils.setStr(Constants.USER_IDCARD, TextUtils.isEmpty(response.getIdcard())?"":response.getIdcard());

        SharedPreferencedUtils.setStr(Constants.USER_REAL_NAME, TextUtils.isEmpty(response.getRealname())?"":response.getRealname());

        SharedPreferencedUtils.setInt(Constants.USER_IS_CUSTOMER,response.getIscustom());
        SharedPreferencedUtils.setInt(Constants.USER_IS_ADVISER,response.getIsadviser());

        // 180 960 40043
        if (!TextUtils.isEmpty(response.getImgurl())){
            String str =response.getImgurl().trim();
            //SharedPreferencedUtils.setStr(Constants.USER_HEADER, str+"?random="+System.currentTimeMillis());
            SharedPreferencedUtils.setStr(Constants.USER_HEADER, str);
        }else{
            SharedPreferencedUtils.setStr(Constants.USER_HEADER, "");

        }

        JPushInterface.setAlias(getApplicationContext(),
                response.getUserid(),response.getUserid()+"");

//        String rongcloudtoken=SharedPreferencedUtils.getStr(Constants.RONGCLOUD_TOKEN);
        String rongcloudtoken=response.getRytoken();

        ChatUtil.connect(rongcloudtoken, new RongIMClient.ConnectCallback() {
            @Override
            public void onTokenIncorrect() {
                com.orhanobut.logger.Logger.w("incorrect 错误");
            }

            @Override
            public void onSuccess(String s) {
                com.orhanobut.logger.Logger.w("success:"+s);

            }

            @Override
            public void onError(RongIMClient.ErrorCode errorCode) {
                com.orhanobut.logger.Logger.w("error:"+errorCode);

            }
        });
        RxBus.getInstance().post(RxBus.getInstance().getTag(MainActivity.class,
                RxBus.TAG_UPDATE), "refreshheader");

        EventBus.getDefault().post(new RefreshHeadBean("refresh"));
        //ActivityUtil.getInstance().openActivityTop(this, MainActivity.class);
        ActivityUtil.getInstance().closeActivity(this);
       // ActivityUtil.getInstance().closeActivity(this);
    }
    /**
     * 获取验证码
     *
     * @param validateCode
     */
    @Override
    public void getValidateCode(String validateCode) {
        loadingDialog.dismiss();
        tv_validate.setText(validateCode);
        // TODO: 2017/11/28  自动填入验证码 到时候可能需要去掉
        et_validate.setText(validateCode);

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
    public void stateError(Throwable e) {
        super.stateError(e);
        et_password.setText("");
        et_validate.setText("");
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Subscribe(tag = RxBus.TAG_CREATE, thread = EventThread.MAIN_THREAD)
    public void testRx(String message) {
        if ("registSuccess".equals(message)){
            et_name.setText(SharedPreferencedUtils.getStr(Constants.USER_NAME));
            et_password.setText("");
            et_password.requestFocus();
            // TODO: 2017/11/29  密码框获取焦点
        }
    }

    public void addLayoutListener(final View main, final View scroll) {
        main.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Rect rect = new Rect();
                main.getWindowVisibleDisplayFrame(rect);
                int mainInvisibleHeight = main.getRootView().getHeight() - rect.bottom;
                if (mainInvisibleHeight > 200) {
                    int[] location = new int[2];
                    scroll.getLocationInWindow(location);
                    int srollHeight = (location[1] + scroll.getHeight()) - rect.bottom;
                    main.scrollTo(0, srollHeight);
                } else {
                    main.scrollTo(0, 0);
                }
            }
        });
    }
}
