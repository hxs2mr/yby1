package com.itislevel.lyl.mvp.ui.property.homelist;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.PopupWindow;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.itislevel.lyl.R;
import com.itislevel.lyl.adapter.LiveAddressBean;
import com.itislevel.lyl.adapter.SelectDanAdapter;
import com.itislevel.lyl.app.Constants;
import com.itislevel.lyl.base.RootActivity;
import com.itislevel.lyl.mvp.model.bean.HomeDetailBean;
import com.itislevel.lyl.mvp.model.bean.VillageNameBean;
import com.itislevel.lyl.mvp.ui.property.PropertLoginActivity;
import com.itislevel.lyl.utils.ActivityUtil;
import com.itislevel.lyl.utils.GsonUtil;
import com.itislevel.lyl.utils.SAToast;
import com.itislevel.lyl.utils.SharedPreferencedUtils;
import com.itislevel.lyl.utils.UtilsStyle;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import cn.finalteam.rxgalleryfinal.ui.widget.HorizontalDividerItemDecoration;
import retrofit2.http.PUT;

/**
 * Created by Administrator on 2018\5\28 0028.
 */

public class PropertyHomeListDetailActivity extends RootActivity<PropertyHomePresenter> implements PropertyHomeContract.View, BaseQuickAdapter.OnItemClickListener {

    @BindView(R.id.p_p_back)
    LinearLayoutCompat p_p_back;

    @BindView(R.id.d_huhao)
    AppCompatTextView d_huhao;//户号

    @BindView(R.id.d_name)
    AppCompatTextView d_name;//户名

    @BindView(R.id.d_address)
    AppCompatTextView d_address;//归属地

    @BindView(R.id.d_qu_name)
    AppCompatTextView d_qu_name;//小区名

    @BindView(R.id.d_phone)
    AppCompatTextView d_phone;//号码

    @BindView(R.id.d_code)
    AppCompatTextView d_code;//身份证

    @BindView(R.id.d_jiaofei_nei)
    AppCompatTextView d_jiaofei_nei;//物业缴费类型

    @BindView(R.id.d_mianji)
    AppCompatTextView d_mianji;//房屋面积

    @BindView(R.id.d_danjia)
    AppCompatTextView d_danjia;//房屋单价

    @BindView(R.id.d_zone)
    AppCompatTextView d_zone;//房屋总额

    @BindView(R.id.d_jiaofeie)
    AppCompatTextView d_jiaofeie;//缴费额

    @BindView(R.id.d_c)
    AppCompatTextView d_c;//有无车位

    @BindView(R.id.d_c_fei)
    AppCompatTextView d_c_fei;//车位费

    @BindView(R.id.d_c_feiyear)
    AppCompatTextView d_c_feiyear;//车位缴费额

    @BindView(R.id.d_c_lei)
    AppCompatTextView d_c_lei;//车位缴费类型

    @BindView(R.id.d_dan_don)
    AppCompatTextView dan_name;

    @BindView(R.id.no_c_linear)
    LinearLayoutCompat no_c_linear;//当没有车位的时候   gone

    @BindView(R.id.d_guan_phone)
    AppCompatTextView d_guan_phone;

    @BindView(R.id.select_dan_linear)
    LinearLayoutCompat select_dan_linear;

    @BindView(R.id.dan_name)
    AppCompatTextView dan_name_top;

    @BindView(R.id.gray_layout)
    View gray_layout;

    @BindView(R.id.select_dan_im)
    AppCompatImageView select_dan_im;
     private Bundle bundle ;
     private String address;
     private String wu_number;
     private String username;
    private String paddress;
    private String villagename;
    private String phone;
    private PopupWindow POPU;
    private SelectDanAdapter adapter;
    private List<LiveAddressBean> LD_LIST;
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
        return R.layout.activity_hl_detail;
    }

    @Override
    protected void initEventAndData() {
        bundle = getIntent().getExtras();
        address=bundle.getString("address");
        wu_number = bundle.getString("number");
        username = bundle.getString("username");
        paddress = bundle.getString("paddress");
        villagename = bundle.getString("villagename");
        phone = bundle.getString("phone");
        d_huhao.setText(wu_number);
        dan_name.setText(address);
        dan_name_top.setText(address);
        d_name.setText(username);
        d_address.setText(paddress);
        d_qu_name.setText(villagename);
        d_phone.setText(phone);
        loadData(address);
        loadDanData();
    }

    private void loadDanData() {
        Map<String, Object> request1 = new HashMap<>();
        request1.put("token", SharedPreferencedUtils.getStr("p_token",""));
        request1.put("phone", SharedPreferencedUtils.getStr("proprety_phone",""));
        request1.put("vid", SharedPreferencedUtils.getStr("vid",""));
        mPresenter.findLiveaddress(GsonUtil.obj2JSON(request1));
    }

    private void loadData(String liveaddress) {
        loadingDialog.show();
        Map<String, Object> request = new HashMap<>();
        request.put("token", SharedPreferencedUtils.getStr("p_token",""));
        request.put("phone", SharedPreferencedUtils.getStr("proprety_phone",""));
        request.put("vid", SharedPreferencedUtils.getStr("vid",""));
        request.put("liveaddress",liveaddress);

        System.out.println("JSON*(**************************"+GsonUtil.obj2JSON(request));
        mPresenter.personalNews(GsonUtil.obj2JSON(request));

    }

    @OnClick({R.id.p_p_back,R.id.select_dan_linear})
    public  void onclivk(View view){
        switch (view.getId())
        {
            case R.id.p_p_back:
                ActivityUtil.getInstance().closeActivity(this);
                break;
            case R.id.select_dan_linear:
                Boolean    islogin = SharedPreferencedUtils.getBool("isproprety", false);
                if(islogin)
                {
                    gray_layout.setVisibility(View.VISIBLE);

                    POPU = showTipPopupWindow(select_dan_linear);
                    RotateAnimation animation = new RotateAnimation(0, 180, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                    animation.setDuration(350);
                    animation.setFillAfter(true);
                    select_dan_im.startAnimation(animation);
                }else {
                    ActivityUtil.getInstance().openActivity(this, PropertLoginActivity.class);
                }
                break;
        }
    }
    public PopupWindow showTipPopupWindow(final View anchorView) {

        final View contentView = LayoutInflater.from(anchorView.getContext()).inflate(R.layout.select_dan_popu, null);
        contentView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        RecyclerView recyclerView = contentView.findViewById(R.id.select_recycle);
        initAdapter(recyclerView);
        final PopupWindow popupWindow = new PopupWindow(contentView, contentView.getMeasuredWidth(), contentView.getMeasuredHeight(), false);

        recyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.dismiss();
            }
        });
        contentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });

        contentView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                // 自动调整箭头的位置
                // autoAdjustArrowPos(popupWindow, contentView, anchorView);
                contentView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
            }
        });
        popupWindow.setBackgroundDrawable(this.getResources().getDrawable(R.color.transparent));
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                RotateAnimation animation = new RotateAnimation(180, 0, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                animation.setDuration(350);
                animation.setFillAfter(true);
                select_dan_im.startAnimation(animation);
                gray_layout.setVisibility(View.GONE);
            }
        });
        // 如果希望showAsDropDown方法能够在下面空间不足时自动在anchorView的上面弹出
        // 必须在创建PopupWindow的时候指定高度，不能用wrap_content
        popupWindow.showAsDropDown(anchorView);
        return popupWindow;
    }

    private void initAdapter(RecyclerView select_recycle ) {

        LinearLayoutManager manager = new LinearLayoutManager(this);
        // ((SimpleItemAnimator)recyclerView.getItemAnimator()).setSupportsChangeAnimations(false);
        select_recycle.setLayoutManager(manager);
        adapter = new SelectDanAdapter(R.layout.select_dan_litem,this);
        adapter.setOnItemClickListener(this);
        adapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_BOTTOM); //切换动画
        adapter.setEnableLoadMore(false);
        select_recycle.addItemDecoration(new HorizontalDividerItemDecoration.Builder(this)
                .build());
        select_recycle.setAdapter(adapter);
        adapter.setNewData(LD_LIST);
        /*
        * 测试的数据
        * */
    }
    @Override
    public void stateError(Throwable e) {
        super.stateError(e);
        loadingDialog.dismiss();
    }

    @Override
    public void findVillagename(List<VillageNameBean> list) {

    }

    @Override
    public void findLiveaddress(List<LiveAddressBean> list) {
        LD_LIST = list;
    }

    @Override
    public void personalNews(List<HomeDetailBean> bean) {
        d_code.setText(bean.get(0).getIdcard());
        d_jiaofei_nei.setText(bean.get(0).getHousepaytype()+"个月缴");
        d_mianji.setText(bean.get(0).getHouseareas()+"");
        d_danjia.setText(bean.get(0).getHouseunitfee()+"");
        d_zone.setText(bean.get(0).getHousetotalfee()+"");
        d_jiaofeie.setText(bean.get(0).getHousepaylimit());

        if(bean.get(0).getIscar()==1)
        {
            no_c_linear.setVisibility(View.VISIBLE);
            d_c_fei.setText(bean.get(0).getCarfee());
            d_c_feiyear.setText(bean.get(0).getCarpaylimit());
            d_c_lei.setText(bean.get(0).getCarpaytype()+"个月缴");

        }else {
            no_c_linear.setVisibility(View.GONE);
            d_c.setText("无");
        }
        loadingDialog.dismiss();
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
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        POPU.dismiss();
        dan_name_top.setText(this.adapter.getData().get(position).getLiveaddress());
        RotateAnimation animation = new RotateAnimation(180, 0, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        animation.setDuration(350);
        animation.setFillAfter(true);
        select_dan_im.startAnimation(animation);
        gray_layout.setVisibility(View.GONE);
        loadData(this.adapter.getData().get(position).getLiveaddress());
    }
}
