package com.itislevel.lyl.mvp.ui.user;

import android.graphics.Bitmap;
import android.support.v7.widget.AppCompatImageView;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.itislevel.lyl.R;
import com.itislevel.lyl.app.App;
import com.itislevel.lyl.app.Constants;
import com.itislevel.lyl.base.RootActivity;
import com.itislevel.lyl.mvp.model.bean.FileUploadBean;
import com.itislevel.lyl.mvp.model.bean.HomeBean;
import com.itislevel.lyl.mvp.model.bean.MyReceiveAddrBean;
import com.itislevel.lyl.mvp.model.bean.RegistBean;
import com.itislevel.lyl.mvp.model.bean.UserInfoBean;
import com.itislevel.lyl.utils.SharedPreferencedUtils;
import com.itislevel.lyl.utils.imageload.ImageLoadConfiguration;
import com.itislevel.lyl.utils.imageload.ImageLoadProxy;
import com.vondear.rxtools.view.RxQRCode;

import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;

public class QRCodeActivity extends RootActivity<UserPresenter> implements
        UserContract.View {

    // iv_header tv_nickname tv_regist_num iv_qr_code

    @BindView(R.id.iv_header)
    AppCompatImageView iv_header;


    @BindView(R.id.iv_qr_code)
    ImageView iv_qr_code;

    @BindView(R.id.tv_nickname)
    TextView tv_nickname;

    @BindView(R.id.tv_regist_num)
    TextView tv_regist_num;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_qrcode;
    }

    @Override
    protected void initEventAndData() {
        setToolbarTvTitle("二维码名片");

        tv_nickname.setText(SharedPreferencedUtils.getStr(Constants.USER_NICK_NAME));
        tv_regist_num.setText(SharedPreferencedUtils.getStr(Constants.USER_NUM));

        String header = SharedPreferencedUtils.getStr(Constants.USER_HEADER);
        if (TextUtils.isEmpty(header)){
            ImageLoadProxy.getInstance()
                    .load(new ImageLoadConfiguration.Builder(App.getInstance())
                            .defaultImageResId(R.mipmap.icon_default_header_circle)
                            .imageView(iv_header).build());
        }else{
//            ImageLoadProxy.getInstance()
//                    .load(new ImageLoadConfiguration.Builder(App.getInstance())
//                            .url(Constants.IMG_SERVER_PATH+header)
//                            .defaultImageResId(R.mipmap.icon_default_header_circle)
//                            .imageView(iv_header).build());


            Glide.with(this)
                    .load(Constants.IMG_SERVER_PATH+header)
                    .placeholder(R.mipmap.icon_default_header_circle)
                    .into(iv_header);

        }


    }

    @Override
    protected void onResume() {
        super.onResume();
        // TODO: 2018-01-15 生成二维码
        String phone = SharedPreferencedUtils.getStr(Constants.USER_PHONE);
       RxQRCode.createQRCode(phone,200,200,iv_qr_code);
//        iv_qr_code.setImageBitmap(bitmap);
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
}
