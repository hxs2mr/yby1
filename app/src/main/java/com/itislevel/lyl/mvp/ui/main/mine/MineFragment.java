package com.itislevel.lyl.mvp.ui.main.mine;


import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.itislevel.lyl.R;
import com.itislevel.lyl.app.App;
import com.itislevel.lyl.app.Constants;
import com.itislevel.lyl.base.RootFragment;
import com.itislevel.lyl.mvp.model.bean.FanloginBean;
import com.itislevel.lyl.mvp.model.bean.JPOnclick;
import com.itislevel.lyl.mvp.model.bean.RefreshHeadBean;
import com.itislevel.lyl.mvp.model.bean.UpdataUserBean;
import com.itislevel.lyl.mvp.ui.about.AboutActivity;
import com.itislevel.lyl.mvp.ui.backmonkey.FanxianLoginActivity;
import com.itislevel.lyl.mvp.ui.main.mine.fan.PersonFanActivity;
import com.itislevel.lyl.mvp.ui.main.mine.fan.PersonShanActivity;
import com.itislevel.lyl.mvp.ui.mygift.MyGiftActivity;
import com.itislevel.lyl.mvp.ui.myaddress.MyAddressActivity;
import com.itislevel.lyl.mvp.ui.mymessage.MyMessageActivity;
import com.itislevel.lyl.mvp.ui.myteam.MyTeamActivity;
import com.itislevel.lyl.mvp.ui.setting.SettingActivity;
import com.itislevel.lyl.mvp.ui.user.LoginActivity;
import com.itislevel.lyl.mvp.ui.user.QRCodeActivity;
import com.itislevel.lyl.mvp.ui.user.UpdateUserInfoActivity;
import com.itislevel.lyl.mvp.ui.userfan.UserFanActivity;
import com.itislevel.lyl.mvp.ui.usermonkey.UserMonkeyQActivity;
import com.itislevel.lyl.utils.ActivityUtil;
import com.itislevel.lyl.utils.ChatUtil;
import com.itislevel.lyl.utils.GsonUtil;
import com.itislevel.lyl.utils.SharedPreferencedUtils;
import com.itislevel.lyl.utils.ToastUtil;
import com.itislevel.lyl.utils.imageload.ImageLoadConfiguration;
import com.itislevel.lyl.utils.imageload.ImageLoadProxy;
import com.itislevel.lyl.utils.rxbus.annotation.UseRxBus;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import io.rong.imlib.RongIMClient;
import q.rorbin.badgeview.Badge;
import q.rorbin.badgeview.QBadgeView;

import static com.itislevel.lyl.base.NoMVPActivity.ISLIUHAN;
import static com.itislevel.lyl.base.NoMVPActivity.ISLIUHANNUMBER;

/**
 * A simple {@link Fragment} subclass.
 */
@UseRxBus
public class MineFragment extends RootFragment<MinePresenter> implements MineContract.View {

//   iv_header  tv_nickname  tv_regist_num iv_right_arrow iv_qr_code
//tv_gift tv_mymessage tv_address  tv_about  tv_setting
    @BindView(R.id.home_tb)
    Toolbar home_tb ;
    @BindView(R.id.iv_header)
    CircleImageView iv_header;

    @BindView(R.id.tv_nickname)
    AppCompatTextView tv_nickname;

    @BindView(R.id.tv_regist_num)
    AppCompatTextView tv_regist_num;

    @BindView(R.id.iv_qr_code)
    AppCompatImageView iv_qr_code;

    @BindView(R.id.iv_team)
    AppCompatImageView iv_team;

    @BindView(R.id.person_qian_linear)
    LinearLayoutCompat person_qian_linear;

    @BindView(R.id.person_fan_linear)
    LinearLayoutCompat person_fan_linear;

    @BindView(R.id.person_shan_linear)
    LinearLayoutCompat person_shan_linear;

    Badge badge;

    public MineFragment() {

    }
    @BindView(R.id.linear_gu)
    LinearLayoutCompat ll_parent_team;

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.personfragment;
    }
    @Override
    protected void initEventAndData() {
        if(ISLIUHAN)//刘海屏适配
        {
            home_tb.setPadding(0, ISLIUHANNUMBER-50,0,0);
        }
        setUserInfo();
    }
    @Override
    public void onResume() {
        super.onResume();
        int anInt = SharedPreferencedUtils.getInt(Constants.BADGE_COUNT_TEAM, 0);
        if (anInt > 0) {
            badge.setBadgeText(anInt + "");
        }
    }
    @Subscribe
    public  void onenvent(RefreshHeadBean bean)
    {
        //刷新头像
         String  headerUrl = SharedPreferencedUtils.getStr(Constants.USER_HEADER);
         headerUrl = Constants.IMG_SERVER_PATH + headerUrl;
        Glide.with(getContext())
                .load(headerUrl)
                .asBitmap()
                .error(R.mipmap.person_head)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(iv_header);
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String headerUrl = SharedPreferencedUtils.getStr(Constants.USER_HEADER);

        if (TextUtils.isEmpty(headerUrl)) {
            ImageLoadProxy.getInstance()
                    .load(new ImageLoadConfiguration.Builder(getActivity())
                            .url(R.mipmap.icon_default_header_circle)
                            .imageView(iv_header).build());
        } else {
            headerUrl = Constants.IMG_SERVER_PATH + headerUrl.trim();
            ImageLoadProxy.getInstance()
                    .load(new ImageLoadConfiguration.Builder(getActivity())
//                            .defaultImageResId(R.mipmap.icon_default_header_circle)
                            .url(headerUrl)
                            .imageView(iv_header).build());
        }

        int anInt = SharedPreferencedUtils.getInt(Constants.USER_IS_ADVISER, 0);
      /*  if (anInt == 1) {
            ll_parent_team.setVisibility(View.VISIBLE);
        } else {
            ll_parent_team.setVisibility(View.GONE);
        }*/
        refreshBadgeViewCount();
    }

    public ImageView getUserHeaderView() {
        return iv_header;
    }

    private void setUserInfo() {
        String nickname = SharedPreferencedUtils.getStr(Constants.USER_NICK_NAME);
        tv_nickname.setText(TextUtils.isEmpty(nickname) ? "请登录" : "" + nickname);
        String usernum = SharedPreferencedUtils.getStr(Constants.USER_PHONE);
        tv_regist_num.setText(TextUtils.isEmpty(usernum) ? "" : usernum);
    }

    //   iv_header  tv_nickname  tv_regist_num iv_right_arrow iv_qr_code
//   tv_gift tv_mymessage tv_address  tv_about tv_setting

    @OnClick({R.id.iv_header, R.id.iv_right_arrow, R.id.iv_qr_code, R.id.linear_lipin,
            R.id.linear_xiaoxi, R.id.linear_address, R.id.linear_guan, R.id.linear_setting,
            R.id.linear_gu, R.id.tv_nickname, R.id.tv_regist_num,R.id.person_qian_linear,R.id.person_fan_linear,R.id.person_shan_linear})
    public void click(View v) {
        boolean islogin = SharedPreferencedUtils.getBool("islogin", false);
       if (!islogin) {
            ActivityUtil.getInstance().openActivity(getActivity(), LoginActivity.class);
            return;
        }
        switch (v.getId()) {
            case R.id.iv_header:
            case R.id.tv_nickname:
            case R.id.tv_regist_num:
            case R.id.iv_right_arrow:
                ActivityUtil.getInstance().openActivity(getActivity(), UpdateUserInfoActivity
                        .class);
                break;
            case R.id.iv_qr_code:
                ActivityUtil.getInstance().openActivity(getActivity(), QRCodeActivity
                        .class);
                break;
            case R.id.linear_lipin:
                ActivityUtil.getInstance().openActivity(getActivity(), MyGiftActivity.class);
                break;
            case R.id.linear_xiaoxi:
                ActivityUtil.getInstance().openActivity(getActivity(), MyMessageActivity.class);
                break;
            case R.id.linear_gu:
//                int anInt = SharedPreferencedUtils.getInt(Constants.BADGE_COUNT_TEAM, 0);
//                anInt=0;
//                SharedPreferencedUtils.setInt(Constants.BADGE_COUNT_TEAM,0);
                ActivityUtil.getInstance().openActivity(getActivity(), MyTeamActivity.class);
                break;
            case R.id.linear_address:
                ActivityUtil.getInstance().openActivity(getActivity(), MyAddressActivity.class);
                break;
            case R.id.linear_guan:
                ActivityUtil.getInstance().openActivity(getActivity(), AboutActivity.class);
                break;
            case R.id.linear_setting:
                ActivityUtil.getInstance().openActivity(getActivity(), SettingActivity.class);
                break;
            case R.id.person_fan_linear://我的返现
                ActivityUtil.getInstance().openActivity(getActivity(), UserFanActivity.class);
                break;
            case R.id.person_qian_linear://我的钱包
                ActivityUtil.getInstance().openActivity(getActivity(), UserMonkeyQActivity.class);
                break;
            case R.id.person_shan_linear://我是商家
                boolean fan_login = SharedPreferencedUtils.getBool("fan_login", false);
                if(fan_login)
                {
                    ActivityUtil.getInstance().openActivity(getActivity(),PersonShanActivity.class);
                }else {
                    login();
                }
                break;
        }
    }
    private void login() {
        loadingDialog.show();
        Map<String,String> request = new HashMap<>();
        request.put("token", SharedPreferencedUtils.getStr(Constants.USER_TOKEN,""));
        request.put("usernum", SharedPreferencedUtils.getStr(Constants.USER_NUM,""));
        request.put("phone",  SharedPreferencedUtils.getStr(Constants.USER_PHONE,""));
        request.put("randcode", "");
        mPresenter.merchantlogin(GsonUtil.obj2JSON(request));
    }
    @Override
    public void useNightMode(boolean isNight) {

    }

    @Override
    public void stateEmpty() {

    }

    @Override
    public void stateLoading() {

    }

    @Override
    public void stateError() {
    }

    @Override
    public void stateError(Throwable e) {
        loadingDialog.dismiss();
        ToastUtil.Info("当前APP账号不是商家账号!");
        ActivityUtil.getInstance().openActivity(getActivity(),FanxianLoginActivity.class);
    }

    @Override
    public void stateSuccess() {

    }

    @Override
    public void showContent(String msg) {

    }

    @Override
    public void merchantlogin(FanloginBean bean) {
        loadingDialog.dismiss();
        SharedPreferencedUtils.setBool("fan_login", true);
        SharedPreferencedUtils.setStr("fan_merchantid", bean.getMerchantid()+"");
        SharedPreferencedUtils.setStr("fan_provname", bean.getProvname()+"");
        SharedPreferencedUtils.setStr("fan_cityname", bean.getCityname()+"");
        SharedPreferencedUtils.setStr("fan_companyname", bean.getCompanyname()+"");
        SharedPreferencedUtils.setStr("fan_cuntryname", bean.getCuntryname()+"");
        SharedPreferencedUtils.setLong("fan_createdtime", bean.getCreatedtime());
        SharedPreferencedUtils.setStr("fan_linkman", bean.getLinkman()+"");
        SharedPreferencedUtils.setStr("fan_linkaddress", bean.getLinkaddress()+"");
        SharedPreferencedUtils.setStr("fan_linkphone", bean.getLinkphone()+"");
        ActivityUtil.getInstance().openActivity(getActivity(),PersonShanActivity.class);
    }

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    public void refreshHeader() {

        int anInt = SharedPreferencedUtils.getInt(Constants.USER_IS_ADVISER, 0);
  /*      if (anInt == 1) {
            if (ll_parent_team != null)
                ll_parent_team.setVisibility(View.VISIBLE);
        } else {
            if (ll_parent_team != null)
                ll_parent_team.setVisibility(View.GONE);
        }*/

        String nickname = SharedPreferencedUtils.getStr(Constants.USER_NICK_NAME);
        tv_nickname.setText(TextUtils.isEmpty(nickname) ? "昵称:友邦友" : "昵称:" + nickname);
        String usernum = SharedPreferencedUtils.getStr(Constants.USER_PHONE);
        tv_regist_num.setText(TextUtils.isEmpty(usernum) ? "000000" : usernum);

        //刷新头像
        String headerUrl = SharedPreferencedUtils.getStr(Constants.USER_HEADER);
        headerUrl = Constants.IMG_SERVER_PATH + headerUrl;
        ImageLoadProxy.getInstance()
                .load(new ImageLoadConfiguration.Builder(App.getInstance())
//                        .defaultImageResId(R.mipmap.icon_header_default)
                        .url(headerUrl)
                        .imageView(iv_header).build());

        String rongcloudtoken = SharedPreferencedUtils.getStr(Constants.RONGCLOUD_TOKEN);
//        String rongcloudtoken=response.getRytoken();

        ChatUtil.connect(rongcloudtoken, new RongIMClient.ConnectCallback() {
            @Override
            public void onTokenIncorrect() {
                com.orhanobut.logger.Logger.w("incorrect");
            }

            @Override
            public void onSuccess(String s) {
                com.orhanobut.logger.Logger.w("success:" + s);

            }

            @Override
            public void onError(RongIMClient.ErrorCode errorCode) {
                com.orhanobut.logger.Logger.w("error:" + errorCode);

            }
        });

    }

    public void refreshBadgeViewCount() {

        if (iv_team != null) {
            if (badge == null) {
                badge = new QBadgeView(getActivity()).bindTarget(iv_team);
                badge.setBadgeGravity(Gravity.END | Gravity.TOP);
//        badge.setGravityOffset(-5,0,true);
                badge.setBadgeTextSize(10, true);
                badge.setBadgePadding(3, true);
            }

            int anInt = SharedPreferencedUtils.getInt(Constants.BADGE_COUNT_TEAM, 0);
            if (anInt > 0) {
                badge.setBadgeText(anInt + "");
            } else {
                badge.hide(false);
            }
        }
    }
    @Subscribe
    public void onevent(UpdataUserBean bean)//用户更新
    {
        setUserInfo();
    }
}
