package com.itislevel.lyl.mvp.ui.family;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.itislevel.lyl.R;
import com.itislevel.lyl.adapter.DynamicViewPagerAdapter;
import com.itislevel.lyl.adapter.FamilyGiftListAdapter;
import com.itislevel.lyl.adapter.GiftListViewPagerAdapter;
import com.itislevel.lyl.app.Constants;
import com.itislevel.lyl.base.RootActivity;
import com.itislevel.lyl.mvp.model.bean.BlessCartListBean;
import com.itislevel.lyl.mvp.model.bean.BlessListBean;
import com.itislevel.lyl.mvp.model.bean.BlessOrderBean;
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
import com.itislevel.lyl.mvp.ui.family.giftchildfragment.GiftChildFragment;
import com.itislevel.lyl.mvp.ui.main.childfragment.ChildFragment;
import com.itislevel.lyl.mvp.ui.main.dynamic.childfragment.dfind.DFindFragment;
import com.itislevel.lyl.mvp.ui.main.dynamic.childfragment.dfollow.DFollowFragment;
import com.itislevel.lyl.mvp.ui.main.dynamic.childfragment.toncity.DTonCityFragment;
import com.itislevel.lyl.mvp.ui.pay.PayInfoActivity;
import com.itislevel.lyl.utils.ActivityUtil;
import com.itislevel.lyl.utils.DecimalUtils;
import com.itislevel.lyl.utils.GsonUtil;
import com.itislevel.lyl.utils.SAToast;
import com.itislevel.lyl.utils.SharedPreferencedUtils;
import com.itislevel.lyl.utils.ToastUtil;
import com.itislevel.lyl.utils.rxbus.RxBus;
import com.itislevel.lyl.utils.rxbus.annotation.Subscribe;
import com.itislevel.lyl.utils.rxbus.annotation.UseRxBus;
import com.itislevel.lyl.utils.rxbus.event.EventThread;
import com.itislevel.lyl.widget.BlessGiftDialog;
import com.itislevel.lyl.widget.FamilyCartDialog;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.vondear.rxtools.activity.AndroidBug5497Workaround;

import org.greenrobot.eventbus.EventBus;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

import static com.itislevel.lyl.mvp.ui.family.FamilyDetailActivity.listBean_Detail;
import static com.itislevel.lyl.widget.UIUtil.dip2px;

@UseRxBus
public class FamilyGiftListActivity extends RootActivity<FamilyPresenter>
        implements FamilyContract.View{

    @BindView(R.id.tabLayout)
    TabLayout tableLayout;

    @BindView(R.id.viewpager)
    ViewPager viewPager;

    Bundle bundle = null;
//
//    @BindView(R.id.tablayout)
//    TabLayout tablayout;


    private String selectCateId;
    public static String feteid;
    public static String touserid;
    private List<FamilySacrificeTypeBean.ListBean> cateList;

    private String son_end_name="";
    private String blessid="";
    //是否已添加没有更多数据的布局
    private boolean isAddNoMoreView = false;
    private int pageIndex = 1;//分页加载时 当前页是第几页
    private int totalPage = 0;//总页数
    private boolean isRefreshing = true;//当前是刷新还是加载
    FamilyGiftListAdapter adapter;


    private BlessGiftDialog blessGiftDialog;
    List<BlessCartListBean.ShopcartlistBean> shopcartlistBeanList; //购物车数据

    private double totalPrice;
    private String goodsDetail;
    private int load_more = 0;

    private View view_end;
    private String gif_name="";
    private  String gif_imgurl="";
    private String nick_name="";
    private List<Fragment> fragments = new ArrayList<>();
    private GiftListViewPagerAdapter mViewPagerGiftAdapter;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_family_gift_list;
    }
    @Override
    protected void initEventAndData() {
        AndroidBug5497Workaround.assistActivity(this);
        bundle = getIntent().getExtras();
        setToolbarTvTitle("购买祭品");
        //默认选中第一条
        selectCateId = bundle.getString("selectCateId");
        feteid = bundle.getString("feteid");
        touserid = bundle.getString("touserid");
        String cateListStr = bundle.getString("cateList");
        cateList = GsonUtil.toList(cateListStr, FamilySacrificeTypeBean.ListBean.class);
        int_title_tablayout();
    }

    private void int_title_tablayout() {

        for(int i =0 ; i < cateList.size();i++)
        {
            fragments.add(new GiftChildFragment().newInstance(cateList.get(i).getId()));
        }

        if(tableLayout.getTabCount()>0)
        {
            tableLayout.removeAllTabs();
        }
        for(int i = 0 ; i < cateList.size() ;i++)
        {
            tableLayout.addTab(tableLayout.newTab().setText(cateList.get(i).getCatename()));
        }
        //设置顶部标签指示条的颜色和选中页面时标签字体颜色
        tableLayout.setSelectedTabIndicatorColor(Color.parseColor("#ff7a00"));
        tableLayout.setTabTextColors(Color.parseColor("#282828"), Color.parseColor("#ff7a00"));
        //这里注意的是，因为我是在fragment中创建MyFragmentStatePagerAdapter，所以要传getChildFragmentManager()
        mViewPagerGiftAdapter = new GiftListViewPagerAdapter(getSupportFragmentManager(), cateList,fragments);
        viewPager.setAdapter(mViewPagerGiftAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tableLayout));

        tableLayout.post(new Runnable() {
            @Override
            public void run() {
                setTabIndicatorWidth(FamilyGiftListActivity.this,tableLayout,10,10);
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
                mViewPagerGiftAdapter.getItem(position);
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

    public void dialogDelCart(BlessCartListBean.ShopcartlistBean item) {

        Map<String, String> request = new HashMap<>();
        request.put("token", SharedPreferencedUtils.getStr(Constants.USER_TOKEN));
        request.put("usernum", SharedPreferencedUtils.getStr(Constants.USER_NUM));
        request.put("userid", SharedPreferencedUtils.getStr(Constants.USER_ID));

        request.put("type", Constants.CART_TYPE_FAMILY);
        request.put("goodsid", item.getGoodsid() + "");

        mPresenter.familyCartDel(GsonUtil.obj2JSON(request));
    }

    public void dialogAddCart(BlessCartListBean.ShopcartlistBean item) {

        Map<String, String> request = new HashMap<>();
        request.put("token", SharedPreferencedUtils.getStr(Constants.USER_TOKEN));
        request.put("usernum", SharedPreferencedUtils.getStr(Constants.USER_NUM));
        request.put("userid", SharedPreferencedUtils.getStr(Constants.USER_ID));

        request.put("type", Constants.CART_TYPE_FAMILY);
        request.put("cateid", item.getCateid() + "");
        request.put("goodsid", item.getGoodsid() + "");
        request.put("goodsname", item.getGoodsname() + "");
        request.put("imgurl", item.getImgurl() + "");
        request.put("price", item.getPrice() + "");
        request.put("count", item.getCount() + "");

        mPresenter.familyCartAdd(GsonUtil.obj2JSON(request));
    }


    public void dialogUpdateCart(BlessCartListBean.ShopcartlistBean item) {

        Map<String, String> request = new HashMap<>();
        request.put("token", SharedPreferencedUtils.getStr(Constants.USER_TOKEN));
        request.put("usernum", SharedPreferencedUtils.getStr(Constants.USER_NUM));
        request.put("userid", SharedPreferencedUtils.getStr(Constants.USER_ID));

        request.put("type", Constants.CART_TYPE_FAMILY);
        request.put("goodsid", item.getGoodsid() + "");
        request.put("count", item.getCount() + "");

        mPresenter.familyCartUpdate(GsonUtil.obj2JSON(request));
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
    public void familyDel(String action) {

    }

    @Override
    public void uploadHeader(FileUploadBean fileUploadBean) {

    }

    @Override
    public void generatorOrder(String blessOrderBean) {

        loadingDialog.dismiss();

        bundle.putString(Constants.PAY_MODULE_NAME, Constants.CART_MODEL_FAMILY);
        bundle.putInt(Constants.PAY_FROM_ACTIVITY, Constants.PAY_FROM_FETE_GIFT);
        bundle.putString(Constants.PAY_ORDERNUM, blessOrderBean);
        bundle.putString(Constants.PAY_TOTALPRICE, totalPrice + "");
        bundle.putString(Constants.PAY_GOODS_DESC, "礼品费用");
        bundle.putString(Constants.PAY_GOODS_DETAIL, goodsDetail);

        ActivityUtil.getInstance().openActivity(FamilyGiftListActivity.this, PayInfoActivity
                .class, bundle);
    }

    @Override
    public void immediateOrder(String blessOrderBean) {

        listBean_Detail = new FamilyReceiveGiftBean.ListBean(son_end_name,gif_name,gif_imgurl,System.currentTimeMillis());
        loadingDialog.dismiss();
        SAToast.makeText(this,"送礼成功").show();
        EventBus.getDefault().post(listBean_Detail);
        finish();
    }

    @Override
    public void familyCartAdd(String message) {

    }
//    private BadgeView badgeView;
    @Override
    public void familyCartList(BlessCartListBean blessCartListBean) {
        shopcartlistBeanList = blessCartListBean.getShopcartlist();
        if (shopcartlistBeanList != null && shopcartlistBeanList.size() > 0) {
            double res = 0;
            int count2=0;
            for (BlessCartListBean.ShopcartlistBean item : shopcartlistBeanList) {
                res += Double.parseDouble(item.getPrice()) * item.getCount();
                count2+=item.getCount();
            }
        } else {
        }

        // TODO: 2018-02-24  购物车 图标  待定

//        badgeView = BadgeFactory.create(this)
//                .setTextColor(getResources().getColor(R.color.colorWhite))
//                .setWidthAndHeight(18, 18 )
//                .setBadgeBackground(getResources().getColor(R.color.red))
//                .setTextSize(10)
//                .setBadgeGravity(Gravity.LEFT | Gravity.TOP)
//
//                .setShape(BadgeView.SHAPE_CIRCLE)
//                .setSpace(10, 10)
//                .bind(tv_temp_cart);
//
//
//        badgeView.setBadgeCount(99);
//        badgeView.setVisibility(View.GONE);

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
//
//            if (item.getId()==Integer.parseInt(selectCateId)){
//        textView.setBackground(getResources().getDrawable(R.drawable.shape_tv_bless_tab_select));
//
//    }else{
//        textView.setBackground(getResources().getDrawable(R.drawable.shape_tv_bless_tab_normal));
//    }

    public void setTotalPrice(String tp) {
    }
    public void setCount(String count) {
    }
    @Subscribe(tag = RxBus.TAG_UPDATE, thread = EventThread.MAIN_THREAD)
    public void rxrefresh(String msg) {
        ActivityUtil.getInstance().closeActivity(this);
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

}
