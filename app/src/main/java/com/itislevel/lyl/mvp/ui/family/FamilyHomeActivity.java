package com.itislevel.lyl.mvp.ui.family;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.itislevel.lyl.R;
import com.itislevel.lyl.adapter.FamilyViewPagerAdapter;
import com.itislevel.lyl.app.Constants;
import com.itislevel.lyl.base.RootActivity;
import com.itislevel.lyl.mvp.model.bean.BlessCartListBean;
import com.itislevel.lyl.mvp.model.bean.BlessListBean;
import com.itislevel.lyl.mvp.model.bean.CartUpdateBean;
import com.itislevel.lyl.mvp.model.bean.FamilyBlessListBean;
import com.itislevel.lyl.mvp.model.bean.FamilyBlessListRecevieBean;
import com.itislevel.lyl.mvp.model.bean.FamilyGiftListBean;
import com.itislevel.lyl.mvp.model.bean.FamilyListBean;
import com.itislevel.lyl.mvp.model.bean.FamilyPhotoFrameBean;
import com.itislevel.lyl.mvp.model.bean.FamilyQueryFramBackBean;
import com.itislevel.lyl.mvp.model.bean.FamilyReceiveGiftBean;
import com.itislevel.lyl.mvp.model.bean.FamilySacrificeTypeBean;
import com.itislevel.lyl.mvp.model.bean.FamilySendGiftRecordBean;
import com.itislevel.lyl.mvp.model.bean.FamilyUsualLanguageBean;
import com.itislevel.lyl.mvp.model.bean.FileUploadBean;
import com.itislevel.lyl.mvp.model.bean.LetterBean;
import com.itislevel.lyl.mvp.ui.dynamicmyperson.Dynamic_MypersonActivity;
import com.itislevel.lyl.mvp.ui.family.childhomefragment.dfind.FFindFragment;
import com.itislevel.lyl.mvp.ui.family.childhomefragment.dfollow.FFollowFragment;
import com.itislevel.lyl.mvp.ui.family.childhomefragment.toncity.FTonCityFragment;
import com.itislevel.lyl.mvp.ui.location.Location_CityPickerActivity;
import com.itislevel.lyl.mvp.ui.user.LoginActivity;
import com.itislevel.lyl.utils.ActivityUtil;
import com.itislevel.lyl.utils.SAToast;
import com.itislevel.lyl.utils.SharedPreferencedUtils;
import com.itislevel.lyl.utils.rxbus.annotation.UseRxBus;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;

import static com.itislevel.lyl.widget.UIUtil.dip2px;

@UseRxBus
public class FamilyHomeActivity extends RootActivity<FamilyPresenter>
        implements FamilyContract.View, BaseQuickAdapter.OnItemClickListener
        , BaseQuickAdapter.OnItemChildClickListener,View.OnClickListener{

    Bundle bundle = null;
    private String PROVINCE_ID = "";
    private String CITY_ID = "";
    private String COUNTY_ID = ""; //很多情况下是空的
    @BindView(R.id.tabLayout)
    TabLayout tableLayout;

    @BindView(R.id.viewpager)
    ViewPager viewPager;

    @BindView(R.id.dynamic_hear_image)
    CircleImageView dynamic_hear_image;
    @BindView(R.id.fadon_frame)
    FrameLayout fadon_frame;
    private List<Fragment> fragments = new ArrayList<>();
    private String[] tabTitle = new String[]{"关注","发现","同城"};

    public static int totalTabNum = 4;//假如有四个Fragment页面
    public static int openTabNum = 0;//当前已打开的页面数量
    private FamilyViewPagerAdapter dynamicViewPagerAdapter;
    private String headerUrl;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
    @Override
    protected int getLayoutId() {
        return R.layout.family_home_activity;
    }
    @Override
    protected void initEventAndData() {
        bundle = this.getIntent().getExtras();
        String title = bundle.getString(Constants.CITY_TITLE);

        PROVINCE_ID = bundle.getString(Constants.PROVINCE_ID);
        CITY_ID = bundle.getString(Constants.CITY_ID);
        COUNTY_ID = bundle.getString(Constants.COUNTY_ID);
        setToolbarTvTitle("亲情祭祀");
        setToolbarMoreTxt("   ");
        int_title_tablayout();
      /*  setToolbarMoreClickListener(new View.OnClickListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public void onClick(View v) {
//                FamilyPopup popup=new FamilyPopup(FamilyHomeActivity.this);
//                popup.show();

                View view = View.inflate(FamilyHomeActivity.this, R.layout.item_pop_family, null);

                //创建并显示popWindow
                CustomPopWindow popWindow = new CustomPopWindow.PopupWindowBuilder
                        (FamilyHomeActivity.this)
                        .setView(view)
                        .setFocusable(true)
                        .enableBackgroundDark(true) //弹出popWindow时，背景是否变暗
                        .setBgDarkAlpha(0.7f) // 控制亮度
                        .setOutsideTouchable(true)
                        .setAnimationStyle(R.style.CustomPopWindowStyle) // 添加自定义显示和消失动画
                        .create()
                        .showAsDropDown(v, 0, 0);

                //  tv_family_add tv_family_record tv_family_added tv_receive_sacrifice
                // tv_receive_bless

                TextView tv_family_add = view.findViewById(R.id.tv_family_add);
                TextView tv_family_record = view.findViewById(R.id.tv_family_record);
                TextView tv_family_added = view.findViewById(R.id.tv_family_added);
                TextView tv_receive_sacrifice = view.findViewById(R.id.tv_receive_sacrifice);
                TextView tv_receive_bless = view.findViewById(R.id.tv_receive_bless);

                tv_family_add.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View view, MotionEvent motionEvent) {
                        switch (motionEvent.getAction())
                        {
                            case MotionEvent.ACTION_DOWN:
                                tv_family_add.setBackgroundResource(R.drawable.qin_qin_select);
                                tv_family_add.setTextColor(Color.parseColor("#ff7a00"));
                                break;
                            case MotionEvent.ACTION_UP:
                                tv_family_add.setBackgroundResource(R.drawable.qin_qin_select_false);
                                tv_family_add.setTextColor(Color.parseColor("#282828"));
                                ActivityUtil.getInstance().openActivity(FamilyHomeActivity.this,
                                        FamilyAddActivity.class, bundle);
                                popWindow.dissmiss();
                                break;
                            case MotionEvent.ACTION_CANCEL:
                                tv_family_add.setBackgroundResource(R.drawable.qin_qin_select_false);
                                tv_family_add.setTextColor(Color.parseColor("#282828"));
                                break;
                        }
                        return true;
                    }
                });

                tv_family_record.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View view, MotionEvent motionEvent) {
                        switch (motionEvent.getAction())
                        {
                            case MotionEvent.ACTION_DOWN:
                                tv_family_record.setBackgroundResource(R.drawable.qin_qin_select);
                                tv_family_record.setTextColor(Color.parseColor("#ff7a00"));
                                break;
                            case MotionEvent.ACTION_UP:
                                tv_family_record.setBackgroundResource(R.drawable.qin_qin_select_false);
                                tv_family_record.setTextColor(Color.parseColor("#282828"));
                                ActivityUtil.getInstance().openActivity(FamilyHomeActivity.this,
                                        FamilyMySendGiftRecordActivity.class, bundle);
                                popWindow.dissmiss();
                                break;
                            case MotionEvent.ACTION_CANCEL:
                                tv_family_record.setBackgroundResource(R.drawable.qin_qin_select_false);
                                tv_family_record.setTextColor(Color.parseColor("#282828"));
                                break;
                        }
                        return true;
                    }
                });

                tv_family_added.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View view, MotionEvent motionEvent) {
                        switch (motionEvent.getAction())
                        {
                            case MotionEvent.ACTION_DOWN:
                                tv_family_added.setBackgroundResource(R.drawable.qin_qin_select);
                                tv_family_added.setTextColor(Color.parseColor("#ff7a00"));
                                break;
                            case MotionEvent.ACTION_UP:
                                tv_family_added.setBackgroundResource(R.drawable.qin_qin_select_false);
                                tv_family_added.setTextColor(Color.parseColor("#282828"));
                                ActivityUtil.getInstance().openActivity(FamilyHomeActivity.this,
                                        FamilyAddedActivity.class, bundle);
                                popWindow.dissmiss();
                                break;
                            case MotionEvent.ACTION_CANCEL:
                                tv_family_added.setBackgroundResource(R.drawable.qin_qin_select_false);
                                tv_family_added.setTextColor(Color.parseColor("#282828"));
                                break;
                        }
                        return true;
                    }
                });

                tv_receive_sacrifice.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View view, MotionEvent motionEvent) {
                        switch (motionEvent.getAction())
                        {
                            case MotionEvent.ACTION_DOWN:
                                tv_receive_sacrifice.setBackgroundResource(R.drawable.qin_qin_select);
                                tv_receive_sacrifice.setTextColor(Color.parseColor("#ff7a00"));
                                break;
                            case MotionEvent.ACTION_UP:
                                tv_receive_sacrifice.setBackgroundResource(R.drawable.qin_qin_select_false);
                                tv_receive_sacrifice.setTextColor(Color.parseColor("#282828"));
                                ActivityUtil.getInstance().openActivity(FamilyHomeActivity.this,
                                        FamilyReceiveSacrificeActivity.class, bundle);
                                popWindow.dissmiss();
                                break;
                            case MotionEvent.ACTION_CANCEL:
                                tv_receive_sacrifice.setBackgroundResource(R.drawable.qin_qin_select_false);
                                tv_receive_sacrifice.setTextColor(Color.parseColor("#282828"));
                                break;
                        }
                        return true;
                    }
                });
                tv_receive_bless.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View view, MotionEvent motionEvent) {
                        switch (motionEvent.getAction())
                        {
                            case MotionEvent.ACTION_DOWN:
                                tv_receive_bless.setBackgroundResource(R.drawable.qin_qin_select);
                                tv_receive_bless.setTextColor(Color.parseColor("#ff7a00"));
                                break;
                            case MotionEvent.ACTION_UP:
                                tv_receive_bless.setBackgroundResource(R.drawable.qin_qin_select_false);
                                tv_receive_bless.setTextColor(Color.parseColor("#282828"));
                                ActivityUtil.getInstance().openActivity(FamilyHomeActivity.this,
                                        FamilyReceiveBlessActivity.class, bundle);
                                popWindow.dissmiss();
                                break;
                            case MotionEvent.ACTION_CANCEL:
                                tv_receive_bless.setBackgroundResource(R.drawable.qin_qin_select_false);
                                tv_receive_bless.setTextColor(Color.parseColor("#282828"));
                                break;
                        }
                        return true;
                    }
                });

            }
        });*/
    }
    private void int_title_tablayout() {
        fadon_frame.setOnClickListener(this);
        dynamic_hear_image.setOnClickListener(this);
        fragments.add(new FFollowFragment());
        fragments.add(new FFindFragment());
        fragments.add(new FTonCityFragment());
        viewPager.setFocusable(true);
        viewPager.setFocusableInTouchMode(true);
        viewPager.requestFocus();
        //刷新头像
        headerUrl = SharedPreferencedUtils.getStr(Constants.USER_HEADER);
        headerUrl = Constants.IMG_SERVER_PATH + headerUrl;
        Glide.with(this)
                .load(headerUrl)
                .asBitmap()
                .error(R.mipmap.person_head)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(dynamic_hear_image);
        if(tableLayout.getTabCount()>0)
        {
            tableLayout.removeAllTabs();
        }
        for(int i = 0 ; i < tabTitle.length ;i++)
        {
            tableLayout.addTab(tableLayout.newTab().setText(tabTitle[i]));
        }
        //设置顶部标签指示条的颜色和选中页面时标签字体颜色
        tableLayout.setSelectedTabIndicatorColor(Color.parseColor("#ff7a00"));
        tableLayout.setTabTextColors(Color.parseColor("#282828"), Color.parseColor("#ff7a00"));
        //这里注意的是，因为我是在fragment中创建MyFragmentStatePagerAdapter，所以要传getChildFragmentManager()
        dynamicViewPagerAdapter = new FamilyViewPagerAdapter(getSupportFragmentManager(), tabTitle,fragments);

        viewPager.setAdapter(dynamicViewPagerAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tableLayout));

        tableLayout.post(new Runnable() {
            @Override
            public void run() {
                setTabIndicatorWidth(FamilyHomeActivity.this,tableLayout,10,10);
            }
        });
        viewPager.setCurrentItem(1);
        viewPager.setOffscreenPageLimit(3);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                //滑动监听加载数据，一次只加载一个标签页
                dynamicViewPagerAdapter.getItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        tableLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //在选中的顶部标签时，为viewpager设置currentitem
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    public static void setTabIndicatorWidth(Context context, TabLayout tabLayout, int marginLeft, int marginRight) {
        try {
            //拿到tabLayout的mTabStrip属性
            LinearLayout mTabStrip = (LinearLayout) tabLayout.getChildAt(0);

            for (int i = 0; i < mTabStrip.getChildCount(); i++) {
                View tabView = mTabStrip.getChildAt(i);

                //拿到tabView的mTextView属性  tab的字数不固定一定用反射取mTextView
                Field mTextViewField = tabView.getClass().getDeclaredField("mTextView");
                mTextViewField.setAccessible(true);

                TextView mTextView = (TextView) mTextViewField.get(tabView);

                tabView.setPadding(0, 0, 0, 0);

                //字多宽线就多宽，所以测量mTextView的宽度
                int width = 0;
                width = mTextView.getWidth();
                if (width == 0) {
                    mTextView.measure(0, 0);
                    width = mTextView.getMeasuredWidth();
                }

                //设置tab左右间距 注意这里不能使用Padding 因为源码中线的宽度是根据 tabView的宽度来设置的
                LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) tabView.getLayoutParams();
                params.width = width ;
                //如果想设置第一个距左边距的距离，那么就设置i=0时leftMargin的值，同理，最后一个距右边距的距离，可以设置i=mTabStrip.getChildCount()-1的值
                params.leftMargin = dip2px(context, marginLeft);
                params.rightMargin = dip2px(context, marginRight);
                tabView.setLayoutParams(params);

                tabView.invalidate();
            }
        } catch (NoSuchFieldException e) {
        } catch (IllegalAccessException e) {
        }
    }
/*
    private void start_search() {
        String search = et_search.getText().toString().trim();
        if (TextUtils.isEmpty(search)) {
            ToastUtil.Info("没有搜索内容哦");
            return;
        }
        isSearch = true;
        pageIndex = 1;
        Map<String, String> request = new HashMap<>();
        request.put("token", SharedPreferencedUtils.getStr(Constants.USER_TOKEN));
        request.put("usernum", SharedPreferencedUtils.getStr(Constants.USER_NUM));
        request.put("deadname", search);
        request.put("pageIndex", pageIndex + "");
        request.put("pageSize", Constants.PAGE_NUMBER10 + "");

        mPresenter.familySearch(GsonUtil.obj2JSON(request));
    }*/


    @Override
    public void showContent(String msg) {

    }

    @Override
    public void familyAdd(String message) {

    }

    @Override
    public void familyList(FamilyListBean familyListBean) {

    }


    @Override
    public void familyListMy(FamilyListBean familyListBean) {

    }

    @Override
    public void familyBlessList(FamilyBlessListBean familyBlessListBean) {

    }

    @Override
    public void familyReceiveSacrifice(FamilyReceiveGiftBean familyReceiveGiftBean) {

    }

    @Override
    public void familyListGift(FamilyGiftListBean familyGiftListBean) {

    }

    @Override
    public void familySendGift(FamilySendGiftRecordBean familySendGiftRecordBean) {

    }

    @Override
    public void familyReceiveBless(FamilyBlessListRecevieBean familyBlessListRecevieBean) {

    }

    @Override
    public void familyViewCount(String message) {

    }

    @Override
    public void familyCate(FamilySacrificeTypeBean familySacrificeTypeBean) {

    }

    @Override
    public void familyUsualLanguage(FamilyUsualLanguageBean familyUsualLanguageBean) {

    }

    @Override
    public void familyPhotoFrame(FamilyPhotoFrameBean familyPhotoFrameBean) {

    }

    @Override
    public void familyPhotoBack(FamilyPhotoFrameBean familyPhotoFrameBean) {

    }

    @Override
    public void familyBlessAdd(String message) {

    }

    @Override
    public void familySearch(FamilyListBean familyListBean) {

     /*   totalPage = familyListBean.getTotalPage();
        if (familyListBean.getList().size() <= 0) {
            ToastUtil.Info("没有搜索结果");
            View emptyView = View.inflate(this, R.layout.partial_empty_view, null);
            adapter.setEmptyView(emptyView);
        }
        if (isRefreshing) {
            adapter.setNewData(familyListBean.getList());
            refreshLayout.setRefreshing(false);
        } else {
            adapter.addData(familyListBean.getList());
            adapter.loadMoreComplete();
        }*/
    }

    @Override
    public void familyReceiveGiftById(FamilyReceiveGiftBean familyReceiveGiftBean) {

    }

    @Override
    public void familySaveFPhotoFrameAndBack(String message) {

    }

    @Override
    public void familyQueryFrameAndBack(FamilyQueryFramBackBean familyQueryFramBackBean) {

    }


    @Override
    public void familyDel(String message) {

    }

    @Override
    public void uploadHeader(FileUploadBean fileUploadBean) {

    }

    @Override
    public void generatorOrder(String blessOrderBean) {

    }

    @Override
    public void immediateOrder(String blessOrderBean) {

    }

    @Override
    public void familyCartAdd(String message) {

    }

    @Override
    public void familyCartList(BlessCartListBean blessCartListBean) {

    }

    @Override
    public void familyCartUpdate(CartUpdateBean message) {

    }



    @Override
    public void familyCartDel(String message) {

    }

    @Override
    public void familySearch(BlessListBean blessListBean) {

    }

    @Override
    public void selectletter(LetterBean letterBean) {

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
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.dynamic_hear_image://个人中心
                boolean islogin = SharedPreferencedUtils.getBool("islogin", false);
                if (!islogin) {
                    ActivityUtil.getInstance().openActivity(this, LoginActivity.class);
                    return;
                }
                String name = SharedPreferencedUtils.getStr(Constants.USER_NICK_NAME);
                Bundle bundle1  = new Bundle();
                bundle1.putString("FLAGE","WO");
                bundle1.putString("head_image",headerUrl);
                bundle1.putString("name", TextUtils.isEmpty(name) ? "请登录" : "" + name);
                bundle1.putString("userid",  SharedPreferencedUtils.getStr(Constants.USER_ID)+"");
                ActivityUtil.getInstance().openActivity(this, Dynamic_MypersonActivity
                        .class,bundle1);
                break;
            case R.id.fadon_frame://发布祭事
                if(!CITY_ID.equals("0"))
                {
                    ActivityUtil.getInstance().openActivity(FamilyHomeActivity.this,
                            FamilyAddActivity.class, bundle);
                }else {
                    SAToast.makeText(this,"请先选择所属地区").show();
                    Bundle bundle = new Bundle();
                    String title = "";
                    bundle.putString(Constants.START_FLAGE, "home");
                    bundle.putString(Constants.CITY_NAME, "定位失败");
                    bundle.putString(Constants.CITY_ID, "0");
                    ActivityUtil.getInstance().openActivity(this, Location_CityPickerActivity.class,bundle);
                }
                break;
        }
    }
}
