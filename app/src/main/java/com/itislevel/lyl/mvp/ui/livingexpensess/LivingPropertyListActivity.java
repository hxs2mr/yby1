package com.itislevel.lyl.mvp.ui.livingexpensess;


import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.flyco.dialog.widget.NormalDialog;
import com.itislevel.lyl.R;
import com.itislevel.lyl.adapter.PropertyQueryAdapter;
import com.itislevel.lyl.app.App;
import com.itislevel.lyl.app.Constants;
import com.itislevel.lyl.base.RootActivity;
import com.itislevel.lyl.mvp.model.bean.BlessOrderBean;
import com.itislevel.lyl.mvp.model.bean.PropertyQueryInfo;
import com.itislevel.lyl.mvp.model.bean.PropertyQueryInfoBean;
import com.itislevel.lyl.mvp.model.bean.PropertyRecordBean;
import com.itislevel.lyl.mvp.model.bean.PropertyUpdateStatusBean;
import com.itislevel.lyl.mvp.model.bean.SetOwnerPayMonth;
import com.itislevel.lyl.utils.ActivityUtil;
import com.itislevel.lyl.utils.DateUtil;
import com.itislevel.lyl.utils.GsonUtil;
import com.itislevel.lyl.utils.SharedPreferencedUtils;
import com.itislevel.lyl.utils.ToastUtil;
import com.itislevel.lyl.utils.imageload.ImageLoadConfiguration;
import com.itislevel.lyl.utils.imageload.ImageLoadProxy;
import com.itislevel.lyl.utils.rxbus.RxBus;
import com.itislevel.lyl.utils.rxbus.annotation.Subscribe;
import com.itislevel.lyl.utils.rxbus.annotation.UseRxBus;
import com.itislevel.lyl.utils.rxbus.event.EventThread;
import com.itislevel.lyl.widget.PropertyDialog;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

@UseRxBus
public class LivingPropertyListActivity extends RootActivity<LivingExpensesPresenter> implements
        LivingExpensesContract.View, BaseQuickAdapter.OnItemClickListener, BaseQuickAdapter
        .OnItemChildClickListener {


    Bundle bundle = new Bundle();

    private String PROVINCE_ID = "";
    private String CITY_ID = "";
    private String COUNTY_ID = "";

    private String name;
    private String phone;
    private String validate;

    @BindView(R.id.ll_parent)
    LinearLayout ll_parent;


    @BindView(R.id.iv_arrow)
    ImageView iv_arrow;

    //头部
//    iv_cover tv_village tv_name tv_phone   tv_house_price tv_car_price

    @BindView(R.id.iv_cover)
    ImageView iv_cover;

    @BindView(R.id.tv_village)
    TextView tv_village;

    @BindView(R.id.tv_name)
    TextView tv_name;

    @BindView(R.id.tv_phone)
    TextView tv_phone;

    @BindView(R.id.tv_house_price)
    TextView tv_house_price;
    @BindView(R.id.tv_car_price)
    TextView tv_car_price;

    @BindView(R.id.tv_company)
    TextView tv_company;


    //    tv_house_one tv_house_three tv_house_six tv_house_twelve
//    tv_car_one tv_car_three  tv_car_six  tv_car_twelve

    @BindView(R.id.tv_house_one)
    TextView tv_house_one;
    @BindView(R.id.tv_house_three)
    TextView tv_house_three;
    @BindView(R.id.tv_house_six)
    TextView tv_house_six;
    @BindView(R.id.tv_house_twelve)
    TextView tv_house_twelve;


    @BindView(R.id.tv_car_one)
    TextView tv_car_one;
    @BindView(R.id.tv_car_three)
    TextView tv_car_three;
    @BindView(R.id.tv_car_six)
    TextView tv_car_six;
    @BindView(R.id.tv_car_twelve)
    TextView tv_car_twelve;


    @BindView(R.id.btn_immediate)
    Button btn_immediate;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private PropertyQueryAdapter adapter;
    private String userid;
    private int selectPos = -1;
    private String companyName;
    private String villageName;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_living_property_list;
    }

    @Override
    protected void initEventAndData() {
        bundle = this.getIntent().getExtras();
        String title = bundle.getString(Constants.CITY_TITLE);
        PROVINCE_ID = bundle.getString(Constants.PROVINCE_ID);
        CITY_ID = bundle.getString(Constants.CITY_ID);

        name = bundle.getString("name");
        phone = bundle.getString("phone");
        validate = bundle.getString("validate");

        setToolbarTvTitle("物业缴费");

        setToolbarMoreTxt("缴费记录");
        setToolbarMoreClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityUtil.getInstance().openActivity(LivingPropertyListActivity.this,
                        LivingExpensesRecordActivity.class, bundle);
            }
        });

        initAdapter();
        //查询账单信息
        queryInfo();
    }

    /**
     * 初始化adapter
     */
    private void initAdapter() {
        if (adapter == null) {
            adapter = new PropertyQueryAdapter(R.layout.item_property);
            adapter.setOnItemClickListener(this);
            adapter.setOnItemChildClickListener(this);
            adapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN); //切换动画
            adapter.setEnableLoadMore(false);

            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
//            GridLayoutManager layoutManager = new GridLayoutManager(this, 4, LinearLayoutManager
//                    .HORIZONTAL, false);
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//            recyclerView.addItemDecoration(new HorizontalDividerItemDecoration.Builder(this)
//                    .build());
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(adapter);

        }

    }

    private void queryInfo() {

        Map<String, String> request = new HashMap<>();
        request.put("token", SharedPreferencedUtils.getStr(Constants.USER_TOKEN));
        request.put("usernum", SharedPreferencedUtils.getStr(Constants.USER_NUM));
//        request.put("username", name);
        request.put("userid", bundle.getString("userid"));

        mPresenter.propertyQueryByUserid1(GsonUtil.obj2JSON(request));

    }

    //    tv_house_one tv_house_three tv_house_six tv_house_twelve
//    tv_car_one tv_car_three  tv_car_six  tv_car_twelve

    boolean isArrowUp = true;

    @OnClick({R.id.tv_house_one, R.id.tv_house_three, R.id.tv_house_six, R.id.tv_house_twelve,
            R.id.tv_car_one, R.id.tv_car_three, R.id.tv_car_six, R.id.tv_car_twelve,
            R.id.ll_arrow, R.id.btn_immediate})
    public void click(View v) {
        switch (v.getId()) {
            case R.id.tv_house_one:
                setHouseTxtNormal();
                tv_house_one.setBackground(getResources().getDrawable(R.drawable
                        .selector_tv_property_select));
                updatePayType(1, 1);
                break;

            case R.id.tv_house_three:
                setHouseTxtNormal();
                tv_house_three.setBackground(getResources().getDrawable(R.drawable
                        .selector_tv_property_select));
                updatePayType(1, 3);

                break;

            case R.id.tv_house_six:
                setHouseTxtNormal();
                tv_house_six.setBackground(getResources().getDrawable(R.drawable
                        .selector_tv_property_select));
                updatePayType(1, 6);

                break;


            case R.id.tv_house_twelve:

                setHouseTxtNormal();
                tv_house_twelve.setBackground(getResources().getDrawable(R.drawable
                        .selector_tv_property_select));
                updatePayType(1, 12);


                break;


            case R.id.tv_car_one:
                setCareTxtNormal();
                tv_car_one.setBackground(getResources().getDrawable(R.drawable
                        .selector_tv_property_select));
                updatePayType(2, 1);

                break;

            case R.id.tv_car_three:
                setCareTxtNormal();
                tv_car_three.setBackground(getResources().getDrawable(R.drawable
                        .selector_tv_property_select));
                updatePayType(2, 3);

                break;

            case R.id.tv_car_six:
                setCareTxtNormal();
                tv_car_six.setBackground(getResources().getDrawable(R.drawable
                        .selector_tv_property_select));
                updatePayType(2, 6);

                break;

            case R.id.tv_car_twelve:
                setCareTxtNormal();
                tv_car_twelve.setBackground(getResources().getDrawable(R.drawable
                        .selector_tv_property_select));
                updatePayType(2, 12);

                break;


            case R.id.ll_arrow:
                if (isArrowUp) {
                    isArrowUp = false;
                    ImageLoadProxy.getInstance()
                            .load(new ImageLoadConfiguration.Builder(App.getInstance())
                                    .url(R.mipmap.icon_arrow_down)
                                    .defaultImageResId(R.mipmap.icon_arrow_down)
                                    .imageView(iv_arrow).build());
                    ll_parent.setVisibility(View.VISIBLE);

                } else {
                    isArrowUp = true;
                    ImageLoadProxy.getInstance()
                            .load(new ImageLoadConfiguration.Builder(App.getInstance())
                                    .url(R.mipmap.icon_arrow_up)
                                    .defaultImageResId(R.mipmap.icon_arrow_up)
                                    .imageView(iv_arrow).build());
                    ll_parent.setVisibility(View.GONE);

                }
                break;

            case R.id.btn_immediate:
                if (selectPos==-1){
                    ToastUtil.Info("请选择账单");
                    return;
                }

                PropertyQueryInfoBean.EstateinfoBean.PayBilllistBean item = adapter.getItem
                        (selectPos);


                bundle.putInt("type", item.getType());
                bundle.putString("userid", item.getUserid() + "");
                bundle.putString("ordernum", item.getOrdernum());
                bundle.putString("usernum", item.getUsernum());
                bundle.putString("username", item.getUsername());
                bundle.putString("phone", item.getPhone());
                bundle.putString("status", item.getStatus() + "");
//                bundle.putString("type", item.getType() + "");
                bundle.putInt("paytype", item.getPaytype());
                bundle.putString("payfee", item.getPayfee());
                bundle.putString("estatearea", villageName);

                bundle.putString("payunit", companyName);
                bundle.putString("payfeebegintime", DateUtil.timeStrSpanToDate(item
                        .getPayfeebegintime()));
                bundle.putString("payfeefinishtime", DateUtil.timeStrSpanToDate(item
                        .getPayfeefinishtime()));


                ActivityUtil.getInstance().openActivity(LivingPropertyListActivity.this,
                        LivingExpensesPayActivity.class, bundle);

                break;


        }


    }

    private void updatePayType(int type, int month) {
        Map<String, String> request = new HashMap<>();
        request.put("token", SharedPreferencedUtils.getStr(Constants.USER_TOKEN));
        request.put("usernum", SharedPreferencedUtils.getStr(Constants.USER_NUM));
        request.put("userid", userid);
        request.put("type", type + "");
        request.put("paytype", month + "");

        mPresenter.propertyUpdatePayType(GsonUtil.obj2JSON(request));
    }

    private void setHouseTxtNormal() {

        tv_house_one.setBackground(getResources().getDrawable(R.drawable
                .selector_tv_property_normal));
        tv_house_three.setBackground(getResources().getDrawable(R.drawable
                .selector_tv_property_normal));
        tv_house_six.setBackground(getResources().getDrawable(R.drawable
                .selector_tv_property_normal));
        tv_house_twelve.setBackground(getResources().getDrawable(R.drawable
                .selector_tv_property_normal));

    }

    private void setCareTxtNormal() {
        tv_car_one.setBackground(getResources().getDrawable(R.drawable
                .selector_tv_property_normal));
        tv_car_three.setBackground(getResources().getDrawable(R.drawable
                .selector_tv_property_normal));
        tv_car_six.setBackground(getResources().getDrawable(R.drawable
                .selector_tv_property_normal));
        tv_car_twelve.setBackground(getResources().getDrawable(R.drawable
                .selector_tv_property_normal));
    }

    @Override
    public void showContent(String msg) {

    }

    @Override
    public void propertyQuery(PropertyQueryInfoBean propertyQueryInfoBean) {


    }

    private void setHouseSelect(int type) {
        switch (type) {
            case 1:
                tv_house_one.setBackground(getResources().getDrawable(R.drawable
                        .selector_tv_property_select));
                break;
            case 3:
                tv_house_three.setBackground(getResources().getDrawable(R.drawable
                        .selector_tv_property_select));
                break;
            case 6:
                tv_house_six.setBackground(getResources().getDrawable(R.drawable
                        .selector_tv_property_select));
                break;
            case 12:
                tv_house_twelve.setBackground(getResources().getDrawable(R.drawable
                        .selector_tv_property_select));
                break;
        }
    }

    private void setCarSelect(int type) {
        switch (type) {
            case 1:
                tv_car_one.setBackground(getResources().getDrawable(R.drawable
                        .selector_tv_property_select));
                break;
            case 3:
                tv_car_three.setBackground(getResources().getDrawable(R.drawable
                        .selector_tv_property_select));
                break;
            case 6:
                tv_car_six.setBackground(getResources().getDrawable(R.drawable
                        .selector_tv_property_select));
                break;
            case 12:
                tv_car_twelve.setBackground(getResources().getDrawable(R.drawable
                        .selector_tv_property_select));
                break;
        }
    }

    @Override
    public void propertyQueryOrder(PropertyRecordBean propertyRecordBean) {

    }

    @Override
    public void propertyUpdatePayType(PropertyUpdateStatusBean statusBean) {
        ToastUtil.Success("设置支付类型成功");
    }

    @Override
    public void propertyUpdateOrderState(PropertyUpdateStatusBean statusBean) {

    }

    @Override
    public void propertyGenerateOrder(String blessOrderBean) {

    }

    @Override
    public void getSSMCode(String smscode) {

    }

    @Override
    public void propertyQueryByUserid(PropertyQueryInfo queryInfo) {

    }

    @Override
    public void propertyQueryList(PropertyQueryInfo queryInfo) {

    }

    @Override
    public void propertyQueryByUserid1(PropertyQueryInfoBean propertyQueryInfoBean) {
        PropertyQueryInfoBean.EstateinfoBean estateinfo = propertyQueryInfoBean.getEstateinfo();

        bundle.putString("village", estateinfo.getLiveaddress());//居住地址 支付详情页面要用

        if (!TextUtils.isEmpty(estateinfo.getHeadurl())) {
            ImageLoadProxy.getInstance()
                    .load(new ImageLoadConfiguration.Builder(App.getInstance())
                            .defaultImageResId(R.mipmap.icon_img_load_pre)
                            .url(Constants.IMG_SERVER_PATH + estateinfo.getHeadurl())
                            .imageView(iv_cover).build());
        }

        companyName = estateinfo.getEstatecompany();
        villageName = estateinfo.getEstatearea() + estateinfo.getLiveaddress();

        userid = propertyQueryInfoBean.getEstateinfo().getUserid() + "";
        bundle.putString("userid", propertyQueryInfoBean.getEstateinfo().getUserid() + "");
        bundle.putString("companyName", companyName);
        tv_company.setText("物业公司:" + companyName);
        tv_village.setText("小区名:" + villageName);
        tv_name.setText("户名:" + estateinfo.getUsername());
        tv_phone.setText("电话：" + estateinfo.getPhone());
        tv_house_price.setText("￥ " + estateinfo.getHousetotalfee());
        tv_car_price.setText("￥" + estateinfo.getCarfee());

        setHouseSelect(estateinfo.getHousepaytype());
        setCarSelect(estateinfo.getCarpaytype());

        if (estateinfo.getPayBilllist() != null && estateinfo.getPayBilllist().size() > 0) {
//            estateinfo.getPayBilllist().get(0).setSelected(true);
            btn_immediate.setVisibility(View.VISIBLE);
            adapter.setNewData(estateinfo.getPayBilllist());
        } else {
            btn_immediate.setVisibility(View.GONE);
            View emptyView = View.inflate(this, R.layout.partial_empty_view, null);
            adapter.setEmptyView(emptyView);
            ToastUtil.Info("没有账单信息");
        }

    }

    @Override
    public void propertySetOwnerPayMonth(SetOwnerPayMonth setOwnerPayMonth) {
        PropertyQueryInfoBean.EstateinfoBean.PayBilllistBean item = adapter.getItem(operationPos);
        item.setPaytype(selectMonth);

        ToastUtil.Success(setOwnerPayMonth.getResult());
        queryInfo();
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
        if (adapter.getData() == null || adapter.getData().size() <= 0) {
            View emptyView = View.inflate(this, R.layout.partial_empty_view, null);
            adapter.setEmptyView(emptyView);
        }

//        if (refreshLayout.isLoading()) {
//            refreshLayout.finishLoadmore();
//        }
//        if (refreshLayout.isRefreshing()) {
//            refreshLayout.finishRefresh();
//        }
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);

    }

    int operationPos=-1;

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        operationPos=position;
        PropertyQueryInfoBean.EstateinfoBean.PayBilllistBean item = this.adapter.getItem(position);
        selectPos = position;
        List<PropertyQueryInfoBean.EstateinfoBean.PayBilllistBean> data = this.adapter.getData();
        for (PropertyQueryInfoBean.EstateinfoBean.PayBilllistBean item1 : data) {
            if (item.getOrdernum().equals(item1.getOrdernum())) {
                continue;
            }
            item1.setSelected(false);
        }
        item.setSelected(!item.isSelected());
        this.adapter.notifyDataSetChanged();
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

        operationPos=position;

        PropertyQueryInfoBean.EstateinfoBean.PayBilllistBean item = this.adapter.getItem(position);
        selectPos = position;

        if (view.getId() == R.id.iv_setting) {

            PropertyDialog normalDialog=new PropertyDialog(LivingPropertyListActivity.this,item.getPaytype());
            normalDialog.show();

        } else {
            List<PropertyQueryInfoBean.EstateinfoBean.PayBilllistBean> data = this.adapter
                    .getData();
            for (PropertyQueryInfoBean.EstateinfoBean.PayBilllistBean item1 : data) {
                if (item.getOrdernum().equals(item1.getOrdernum())) {
                    continue;
                }
                item1.setSelected(false);
            }
            item.setSelected(!item.isSelected());
            this.adapter.notifyDataSetChanged();
        }

    }


    @Subscribe(tag = RxBus.TAG_UPDATE, thread = EventThread.MAIN_THREAD)
    public void rxrefresh(String order) {
        queryInfo();
        ToastUtil.Success("支付成功");
    }

    int selectMonth=1;

    public void setMonth(int month){
        selectMonth=month;
        Map<String, String> request = new HashMap<>();
        request.put("token", SharedPreferencedUtils.getStr(Constants.USER_TOKEN));
        request.put("usernum", SharedPreferencedUtils.getStr(Constants.USER_NUM));
        request.put("ordernum", adapter.getItem(selectPos).getOrdernum());
        request.put("moths", month + "");

        mPresenter.propertySetOwnerPayMonth(GsonUtil.obj2JSON(request));

    }

}
