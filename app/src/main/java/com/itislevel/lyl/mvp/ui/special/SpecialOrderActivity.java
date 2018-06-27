package com.itislevel.lyl.mvp.ui.special;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.itislevel.lyl.R;
import com.itislevel.lyl.adapter.SpecialOrderPagerAdapter;
import com.itislevel.lyl.base.RootActivity;
import com.itislevel.lyl.mvp.model.bean.BlessCartListBean;
import com.itislevel.lyl.mvp.model.bean.CartUpdateBean;
import com.itislevel.lyl.mvp.model.bean.FileUploadBean;
import com.itislevel.lyl.mvp.model.bean.SpecialGiftByIdBean;
import com.itislevel.lyl.mvp.model.bean.SpecialGiftListBean;
import com.itislevel.lyl.mvp.model.bean.SpecialOrderCompleteBean;
import com.itislevel.lyl.mvp.model.bean.SpecialOrderDetailBean;
import com.itislevel.lyl.mvp.model.bean.SpecialReceiveAddressBean;
import com.itislevel.lyl.mvp.model.bean.SpecialReturnBean;
import com.itislevel.lyl.mvp.model.bean.SpecialTuiKuanDetailBean;
import com.itislevel.lyl.mvp.model.bean.SpecialTuiKuanUpdateBean;
import com.itislevel.lyl.mvp.model.bean.SpecialTypeBean;
import com.itislevel.lyl.utils.ActivityUtil;
import com.itislevel.lyl.utils.rxbus.RxBus;
import com.itislevel.lyl.utils.rxbus.annotation.Subscribe;
import com.itislevel.lyl.utils.rxbus.annotation.UseRxBus;
import com.itislevel.lyl.utils.rxbus.event.EventThread;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

@UseRxBus
public class SpecialOrderActivity extends RootActivity<SpecialPresenter>
        implements SpecialContract.View {

    @BindView(R.id.viewpager)
    ViewPager viewpager;

    @BindView(R.id.tablayout)
    TabLayout tablayout;

    List<Fragment> fragmentList = new ArrayList<>();
    SpecialOrderPagerAdapter orderPagerAdapter;
    String[] titles = new String[]{"全部", "待付款", "待发货", "待收货", "已完成", "退款/退货"};

    @Override
    protected int getLayoutId() {
        return R.layout.activity_special_order;
    }

    @Override
    protected void initEventAndData() {

        setToolbarTvTitle("我的订单");

        initTitles();
        initFragment();
        orderPagerAdapter = new SpecialOrderPagerAdapter(getSupportFragmentManager(),
                fragmentList, titles);

        viewpager.setAdapter(orderPagerAdapter);
        viewpager.setOffscreenPageLimit(6);
        tablayout.setupWithViewPager(viewpager);


    }

    private void initTitles() {
    }

    private void initFragment() {

        fragmentList.add(new OrderAllFragment());
        fragmentList.add(new OrderNoPayFragment());
        fragmentList.add(new OrderNoSendFragment());
        fragmentList.add(new OrderWaitingReceiveFragment());
        fragmentList.add(new OrderCompleteFragment());
        fragmentList.add(new OrderRefundFragment());


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

    }

    @Override
    public void showContent(String msg) {

    }

    @Override
    public void specialType(SpecialTypeBean specialTypeBean) {

    }

    @Override
    public void specialList(SpecialGiftListBean specialListBean) {

    }

    @Override
    public void specialById(SpecialGiftByIdBean specialByIdBean) {

    }

    @Override
    public void specialImmediatelyOrder(String action) {

    }

    @Override
    public void specialShopOrder(String action) {

    }

    @Override
    public void specialReceiveAddress(SpecialReceiveAddressBean addressBean) {

    }

    @Override
    public void specialOrderDetail(SpecialOrderDetailBean detailBean) {

    }

    @Override
    public void specialTuiKuanDetail(SpecialTuiKuanDetailBean tuiKuanDetailBean) {

    }

    @Override
    public void specialTuiKuan(String action) {

    }

    @Override
    public void specialTuiKuanUpdate(SpecialTuiKuanUpdateBean tuiKuanUpdateBean) {

    }

    @Override
    public void specialTuiKuanUpdate2(String action) {

    }

    @Override
    public void specialOrderComplete(SpecialOrderCompleteBean completeBean) {

    }

    @Override
    public void specialOrderGoPay(String action) {

    }

    @Override
    public void specialOrderCancel(String action) {

    }

    @Override
    public void specialShenQingTuiKuan(String action) {

    }

    @Override
    public void specialOrderConfirm(String action) {

    }

    @Override
    public void specialReturnList(SpecialReturnBean returnBean) {

    }

    @Override
    public void uploadHeader(FileUploadBean fileUploadBean) {

    }

    @Override
    public void happyCartAdd(String message) {

    }

    @Override
    public void happyCartList(BlessCartListBean blessCartListBean) {

    }

    @Override
    public void happyCartUpdate(CartUpdateBean message) {

    }

    @Override
    public void happyCartDel(String message) {

    }

    @Subscribe(tag = RxBus.TAG_UPDATE, thread = EventThread.MAIN_THREAD)
    public void rxrefresh(String msg) {
        viewpager.setCurrentItem(2);
        Logger.w("开始刷新");

    }
}
