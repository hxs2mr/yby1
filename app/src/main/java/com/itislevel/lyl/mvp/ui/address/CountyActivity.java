package com.itislevel.lyl.mvp.ui.address;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.itislevel.lyl.R;
import com.itislevel.lyl.adapter.AddressAdapter;
import com.itislevel.lyl.app.Constants;
import com.itislevel.lyl.base.RootActivity;
import com.itislevel.lyl.mvp.model.bean.AddressBean;
import com.itislevel.lyl.mvp.ui.blessing.BlessingHomeActivity;
import com.itislevel.lyl.mvp.ui.family.FamilyHomeActivity;
import com.itislevel.lyl.mvp.ui.funsharing.FunsharingAddActivity;
import com.itislevel.lyl.mvp.ui.housekeep.HouseKeepActivity;
import com.itislevel.lyl.mvp.ui.livingexpensess.LivingPropertyQeruyActivity;
import com.itislevel.lyl.mvp.ui.livingexpensess.LivingWaterQueryActivity;
import com.itislevel.lyl.mvp.ui.special.SpecialHomeActivity;
import com.itislevel.lyl.mvp.ui.team.TeamHomeActivity;
import com.itislevel.lyl.mvp.ui.troublesolution.TroublesolutionSelectActivity;
import com.itislevel.lyl.utils.ActivityUtil;
import com.itislevel.lyl.utils.GsonUtil;
import com.itislevel.lyl.utils.SharedPreferencedUtils;
import com.itislevel.lyl.utils.rxbus.RxBus;
import com.itislevel.lyl.utils.rxbus.annotation.Subscribe;
import com.itislevel.lyl.utils.rxbus.annotation.UseRxBus;
import com.itislevel.lyl.utils.rxbus.event.EventThread;
import com.itislevel.lyl.widget.DividerGridItemDecoration;
import com.orhanobut.logger.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

@UseRxBus
public class CountyActivity extends RootActivity<AddressPresenter> implements AddressContract.View {

    Bundle bundle = new Bundle();

    private String PROVINCE_ID = "";
    private String CITY_ID = "";
    private String COUNTY_ID = "";
    private String COUNTY_TITLE = "";//很多情况下是空的

    private String COUNTY_NAME="";


    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private AddressAdapter adapter;
    private GridLayoutManager layoutManager;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_county;
    }

    @Override
    protected void initEventAndData() {
        bundle = this.getIntent().getExtras();
        String title = bundle.getString(Constants.CITY_TITLE);
        PROVINCE_ID = bundle.getString(Constants.PROVINCE_ID);
        CITY_ID = bundle.getString(Constants.CITY_ID);

        setToolbarTvTitle(title);
//        setToolbarMoreTxt("下一步");

        layoutManager = new GridLayoutManager(this, 4);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new DividerGridItemDecoration(this, 4));


//        setToolbarMoreClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                next();
//
//            }
//        });

        loadData();
    }

    private void next() {
        int anInt = bundle.getInt(Constants.ACTIVITY_TARGET);
        switch (anInt) {
            case Constants.ACTIVITY_FUNSHARING:
                ActivityUtil.getInstance().openActivity(CountyActivity.this,
                        FunsharingAddActivity
                                .class, bundle);
                break;
            case Constants.ACTIVITY_TROUBLE:
                ActivityUtil.getInstance().openActivity(CountyActivity.this,
                        TroublesolutionSelectActivity
                                .class, bundle);
                break;
            case Constants.ACTIVITY_LIVING://不用
                break;
            case Constants.ACTIVITY_HOUSE://家政服务
                ActivityUtil.getInstance().openActivity(CountyActivity.this,
                        HouseKeepActivity
                                .class, bundle);
                break;
            case Constants.ACTIVITY_TEAM://顾问团队
                ActivityUtil.getInstance().openActivity(CountyActivity.this,
                        TeamHomeActivity
                                .class, bundle);
                break;
            case Constants.ACTIVITY_SPECIAL://特产礼品
                ActivityUtil.getInstance().openActivity(CountyActivity.this,
                        SpecialHomeActivity
                                .class, bundle);
                break;
            case Constants.ACTIVITY_FAMILY://亲情祭祀
                ActivityUtil.getInstance().openActivity(CountyActivity.this,
                        FamilyHomeActivity
                                .class, bundle);
                break;
            case Constants.ACTIVITY_WEDDING://喜事祝福
                ActivityUtil.getInstance().openActivity(CountyActivity.this,
                        BlessingHomeActivity
                                .class, bundle);
                break;
            case Constants.ACTIVITY_LIVING_WATER://生活交费-水费 电费-燃气费-闭路电视费
                ActivityUtil.getInstance().openActivity(CountyActivity.this,
                        LivingWaterQueryActivity
                                .class, bundle);
                break;
            case Constants.ACTIVITY_LIVING_PROPERTY://物业费
                ActivityUtil.getInstance().openActivity(CountyActivity.this,
                        LivingPropertyQeruyActivity
                                .class, bundle);
                break;
        }
    }

    private void loadData() {

        Map<String, String> request = new HashMap<>();
        request.put("token", SharedPreferencedUtils.getStr(Constants.USER_TOKEN));
        request.put("usernum", SharedPreferencedUtils.getStr(Constants.USER_NUM));

        request.put("id", CITY_ID);

        //省的id
        mPresenter.county(GsonUtil.obj2JSON(request));

    }

    @Override
    public void showContent(String msg) {

    }

    @Override
    public void province(List<AddressBean> addressBeans) {

    }

    @Override
    public void city(List<AddressBean> addressBeans) {

    }

    @Override
    public void county(final List<AddressBean> addressBeans) {

        if (addressBeans.size() == 0) {
            next();
            ActivityUtil.getInstance().closeActivity(this);
        } else {
            adapter = new AddressAdapter(addressBeans, this);
            adapter.setClickListener(new AddressAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
//                ToastUtil.Info(addressBeans.get(position).getName());

                    View parentLayout;
                    TextView viewById;
                    for (int i = 0; i < addressBeans.size(); i++) {
                        parentLayout = layoutManager.findViewByPosition(i);
                        if (parentLayout != null) {
                            viewById = (TextView) parentLayout.findViewById(R.id.tv_name);
                            viewById.setTextColor(getResources().getColor(R.color.colorNav));
                        }
                    }
                    parentLayout = layoutManager.findViewByPosition(position);
                    viewById = (TextView) parentLayout.findViewById(R.id.tv_name);
                    viewById.setTextColor(getResources().getColor(R.color.colorBtn));

                    COUNTY_ID = addressBeans.get(position).getId() + "";
                    COUNTY_TITLE = addressBeans.get(position).getName();

                    bundle.putString(Constants.COUNTY_ID, COUNTY_ID);
//                    bundle.putString(Constants.COUNTY_TITLE, COUNTY_TITLE);
                    next();
                }

                @Override
                public void onItemLongClick(View view, int position) {
                }
            });
            recyclerView.setAdapter(adapter);
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

    @Subscribe(tag = RxBus.TAG_UPDATE, thread = EventThread.MAIN_THREAD)
    public void rxfinish(String msg) {
        Logger.i(msg);

        if (msg.equals("finish")) {
            ActivityUtil.getInstance().closeActivity(this);
        }
    }
}
