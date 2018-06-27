package com.itislevel.lyl.mvp.ui.property;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatEditText;
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
import com.itislevel.lyl.adapter.SelectQuAdapter;
import com.itislevel.lyl.app.Constants;
import com.itislevel.lyl.base.RootActivity;
import com.itislevel.lyl.mvp.model.bean.PropertLoginBean;
import com.itislevel.lyl.mvp.model.bean.PropretyLiveBean;
import com.itislevel.lyl.mvp.model.bean.PropretyNoticeBean;
import com.itislevel.lyl.mvp.model.bean.VillageNameBean;
import com.itislevel.lyl.utils.ActivityUtil;
import com.itislevel.lyl.utils.GsonUtil;
import com.itislevel.lyl.utils.SharedPreferencedUtils;
import com.itislevel.lyl.utils.UtilsStyle;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import cn.finalteam.rxgalleryfinal.ui.widget.HorizontalDividerItemDecoration;
import io.rong.imageloader.utils.L;

/**
 * Created by Administrator on 2018\5\22 0022.
 */

public class ProperttPersonActivity extends RootActivity<PropertyPresenter> implements PropertyContract.View, BaseQuickAdapter.OnItemClickListener{

    @BindView(R.id.user_name)
    AppCompatTextView user_name;

    @BindView(R.id.user_phone)
    AppCompatTextView user_phone;

    @BindView(R.id.user_code)
    AppCompatTextView user_code;

    @BindView(R.id.user_location)
    AppCompatTextView user_location;

    @BindView(R.id.user_update)
    AppCompatTextView user_update;//保存

    @BindView(R.id.select_dan_im)
    AppCompatImageView select_dan_im;

    @BindView(R.id.dan_name)
    AppCompatTextView dan_name;

    @BindView(R.id.gray_layout)
    View gray_layout;

    @BindView(R.id.select_dan_linear)
    LinearLayoutCompat select_dan_linear;

    @BindView(R.id.p_p_back)
    LinearLayoutCompat p_p_back;
    private SelectDanAdapter adapter;
    private  PopupWindow POPU;
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
        return R.layout.activity_pp;
    }
    @Override
    protected void initEventAndData() {
        setToolbarTvTitle("个人信息");
        Boolean  islogin = SharedPreferencedUtils.getBool("isproprety", false);
        if(islogin)
        {
            Map<String, Object> request = new HashMap<>();
            request.put("token", SharedPreferencedUtils.getStr(Constants.USER_TOKEN,""));
            request.put("usernum", SharedPreferencedUtils.getStr(Constants.USER_NUM,""));
            request.put("phone", SharedPreferencedUtils.getStr("proprety_phone",""));
            request.put("vid", SharedPreferencedUtils.getStr("vid",""));
            mPresenter.findLiveaddress(GsonUtil.obj2JSON(request));
        }else {
            dan_name.setText("请登录");
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
    public void getSSMCode(String action) {

    }

    @Override
    public void loginEstates(PropertLoginBean bean) {

    }

    @Override
    public void noticeEstates(PropretyNoticeBean bean) {

    }

    @Override
    public void propretyLive(PropretyLiveBean list) {

    }

    @Override
    public void findVillagename(List<VillageNameBean> list) {

    }

    @Override
    public void findLiveaddress(List<LiveAddressBean> list) {
        dan_name.setText(list.get(0).getLiveaddress());//默认填写第一个小区的名称
        LD_LIST = list;
    }


    @OnClick({R.id.select_dan_linear,R.id.p_p_back})
    public void onclick(View view)
    {
        switch (view.getId())
        {
            case R.id.select_dan_linear:
                Boolean    islogin = SharedPreferencedUtils.getBool("isproprety", false);
                if(islogin)
                {
                    gray_layout.setVisibility(View.VISIBLE);
                    POPU = showTipPopupWindow(select_dan_im);
                    RotateAnimation animation = new RotateAnimation(0, 180, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                    animation.setDuration(350);
                    animation.setFillAfter(true);
                    select_dan_im.startAnimation(animation);
                }else {
                    ActivityUtil.getInstance().openActivity(this, PropertLoginActivity.class);
                }
                break;
            case R.id.p_p_back:
                ActivityUtil.getInstance().closeActivity(this);
                break;

        }
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
    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        POPU.dismiss();
        dan_name.setText(this.adapter.getData().get(position).getLiveaddress());
        RotateAnimation animation = new RotateAnimation(180, 0, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        animation.setDuration(350);
        animation.setFillAfter(true);
        select_dan_im.startAnimation(animation);
        gray_layout.setVisibility(View.GONE);
    }
}
