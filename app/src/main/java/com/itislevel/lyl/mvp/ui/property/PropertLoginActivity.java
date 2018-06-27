package com.itislevel.lyl.mvp.ui.property;

import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.AnimationUtils;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.itislevel.lyl.R;
import com.itislevel.lyl.adapter.LiveAddressBean;
import com.itislevel.lyl.adapter.SelectQuAdapter;
import com.itislevel.lyl.app.Constants;
import com.itislevel.lyl.base.RootActivity;
import com.itislevel.lyl.mvp.model.bean.PropertLoginBean;
import com.itislevel.lyl.mvp.model.bean.PropretyLiveBean;
import com.itislevel.lyl.mvp.model.bean.PropretyLoginSuccessBean;
import com.itislevel.lyl.mvp.model.bean.PropretyNoticeBean;
import com.itislevel.lyl.mvp.model.bean.VillageNameBean;
import com.itislevel.lyl.utils.ActivityUtil;
import com.itislevel.lyl.utils.GsonUtil;
import com.itislevel.lyl.utils.SAToast;
import com.itislevel.lyl.utils.SharedPreferencedUtils;
import com.itislevel.lyl.utils.ToastUtil;
import com.itislevel.lyl.widget.DownTimer;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import cn.finalteam.rxgalleryfinal.ui.widget.HorizontalDividerItemDecoration;


/**
 * Created by Administrator on 2018\5\22 0022.
 */

public class PropertLoginActivity extends RootActivity<PropertyPresenter>implements PropertyContract.View, BaseQuickAdapter.OnItemClickListener{
    @BindView(R.id.p_login_ok)
    AppCompatTextView p_login_ok;

    @BindView(R.id.p_yzm_ed)
    TextInputEditText p_yzm_ed;//验证码

    @BindView(R.id.p_huoqu)
    AppCompatTextView forget_miao;//获取验证码按钮

    @BindView(R.id.p_login_phone)
    TextInputEditText p_login_phone;//手机号

    @BindView(R.id.p_login_back)
    AppCompatImageView p_login_back;

    @BindView(R.id.linear_login)
    LinearLayoutCompat linear_login;//

    @BindView(R.id.lienar_select_qu)
    LinearLayoutCompat lienar_select_qu;

    @BindView(R.id.select_recycle)
    RecyclerView select_recycle;//小区的列表

    private SelectQuAdapter adapter;
    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_propert_login;
    }

    @Override
    protected void initEventAndData() {
        initAdapter();
    }
    private void initAdapter() {

        LinearLayoutManager manager = new LinearLayoutManager(this);
        // ((SimpleItemAnimator)recyclerView.getItemAnimator()).setSupportsChangeAnimations(false);
        select_recycle.setLayoutManager(manager);
        if (adapter == null){
            adapter = new SelectQuAdapter(R.layout.select_qu_item,this);
            adapter.setOnItemClickListener(this);
            adapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_BOTTOM); //切换动画
            adapter.setEnableLoadMore(false);
            select_recycle.addItemDecoration(new HorizontalDividerItemDecoration.Builder(this)
                    .build());
            select_recycle.setAdapter(adapter);
        }
        /*
        * 测试的数据
        * */
    }
    @OnClick({R.id.p_login_back,R.id.p_login_ok,R.id.p_huoqu})
    public void clck(View view)
    {
        switch (view.getId())
        {
            case R.id.p_login_ok:
                String yzm = p_yzm_ed.getText().toString().trim();
                if(TextUtils.isEmpty(yzm))
                {
                    ToastUtil.Info("请输入验证码");
                    return;
                }
                String  phone = p_login_phone.getText().toString();
                if(TextUtils.isEmpty(phone))
                {
                    ToastUtil.Info("请输入手机号");
                    return;
                }
                login();
                break;
            case R.id.p_login_back:
                ActivityUtil.getInstance().closeActivity(this);
                break;
            case R.id.p_huoqu:
                getValidate();
                break;
        }
    }

    private void login() {
        loadingDialog.show();
        Map<String, Object> request = new HashMap<>();
        request.put("phone", p_login_phone.getText().toString().trim());
        request.put("randcode", p_yzm_ed.getText().toString().trim());
        mPresenter.loginEstates(GsonUtil.obj2JSON(request));
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

    private void getValidate() {
        String phone = p_login_phone.getText().toString().trim();
        if (TextUtils.isEmpty(phone)) {
            ToastUtil.Info("号码不能为空");
            return;
        }
        //判断号码的合法性

        Map<String, Object> request = new HashMap<>();

        request.put("token","");
        request.put("usernum", "");
        request.put("phone", phone);

        mPresenter.getSSMCode(GsonUtil.obj2JSON(request));
    }

    @Override
    public void stateError(Throwable e) {//登录失败返回的信息
        super.stateError(e);
        ToastUtil.Info(e.getMessage());
    }

    @Override
    public void getSSMCode(String action) {
        DownTimer timer = new DownTimer();
        int totalTime = 60 * 1000;
        timer.setTotalTime(totalTime);
        timer.setIntervalTime(1000);
        timer.setTimerLiener(new DownTimer.TimeListener() {
            @Override
            public void onFinish() {
                if (forget_miao != null) {
                    forget_miao.setClickable(true);
                    forget_miao.setText("获取验证码");
                    forget_miao.setBackground(getResources().getDrawable(R.drawable
                            .pro_yan_shape));
                }
            }

            @Override
            public void onInterval(long remainTime) {
                if (forget_miao != null) {
                    forget_miao.setText("还有" + (remainTime / 1000) + "秒");
                    if (forget_miao.isClickable()) {
                        forget_miao.setBackground(getResources().getDrawable(R.drawable
                                .shape_btn_getvalidatecode_disable));
                        forget_miao.setClickable(false);
                    }
                }

            }
        });
        timer.start();
    }

    @Override
    public void loginEstates(PropertLoginBean bean) {//登录成功
        /*
        *         this.vid = vid;
        this.city_id = city_id;
        this.user_id = user_id;
        this.villagename = villagename;
        * */
        loadingDialog.dismiss();
        linear_login.startAnimation(AnimationUtils.loadAnimation(
                this, R.anim.fade_out));
        linear_login.setVisibility(View.GONE);
        SharedPreferencedUtils.setStr("proprety_phone",bean.getList().get(0).getPhone());
        SharedPreferencedUtils.setStr("p_user_n",bean.getList().get(0).getUsername());
        SharedPreferencedUtils.setStr("p_user_id",bean.getList().get(0).getUserid()+"");
        SharedPreferencedUtils.setStr("p_token",bean.getToken());

        Map<String, Object> request = new HashMap<>();
        request.put("token",bean.getToken());
        request.put("phone", p_login_phone.getText().toString().trim());
        mPresenter.findVillagename(GsonUtil.obj2JSON(request));

        lienar_select_qu.setVisibility(View.VISIBLE);
        lienar_select_qu.startAnimation(AnimationUtils.loadAnimation(this, R.anim.right_in));
    }

    @Override
    public void noticeEstates(PropretyNoticeBean bean) {

    }

    @Override
    public void propretyLive(PropretyLiveBean list) {

    }

    @Override
    public void findVillagename(List<VillageNameBean> list) {//获取当前用户下的小区列表
        adapter.setNewData(list);
    }

    @Override
    public void findLiveaddress(List<LiveAddressBean> list) {

    }


    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        String vid = this.adapter.getData().get(position).getVid();
        String cityid = this.adapter.getData().get(position).getCityid()+"";
        String Userid = this.adapter.getData().get(position).getUserid()+"";
        String cityname = this.adapter.getData().get(position).getCityname()+"";
        String Villagename = this.adapter.getData().get(position).getVillagename()+"";
        String address = this.adapter.getData().get(position).getCityname()+Villagename;
        String head_url = this.adapter.getData().get(position).getHeadurl()+"";
        EventBus.getDefault().post(new PropretyLoginSuccessBean(vid,cityid+"",Userid+"",Villagename));

        SharedPreferencedUtils.setBool("isproprety",true);
        SharedPreferencedUtils.setStr("vid",vid);
        SharedPreferencedUtils.setStr("villagename",Villagename);
        SharedPreferencedUtils.setStr("address_vill",address);
        SharedPreferencedUtils.setStr("proprety_cityid",cityid);
        SharedPreferencedUtils.setStr("proprety_cityname",cityname);
        SharedPreferencedUtils.setStr("p_head_url",head_url);
        ActivityUtil.getInstance().closeActivity(this);
    }
}
