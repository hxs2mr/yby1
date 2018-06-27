package com.itislevel.lyl.mvp.ui.blessing;

import android.Manifest;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.itislevel.lyl.R;
import com.itislevel.lyl.adapter.BlessListAdapter;
import com.itislevel.lyl.app.Constants;
import com.itislevel.lyl.base.RootActivity;
import com.itislevel.lyl.mvp.model.bean.AliPayBean;
import com.itislevel.lyl.mvp.model.bean.BlessAddBean;
import com.itislevel.lyl.mvp.model.bean.BlessAddLikeBean;
import com.itislevel.lyl.mvp.model.bean.BlessCartListBean;
import com.itislevel.lyl.mvp.model.bean.BlessCommentBean;
import com.itislevel.lyl.mvp.model.bean.BlessDetailGiftListBean;
import com.itislevel.lyl.mvp.model.bean.BlessGiftBean;
import com.itislevel.lyl.mvp.model.bean.BlessListBean;
import com.itislevel.lyl.mvp.model.bean.BlessOrderBean;
import com.itislevel.lyl.mvp.model.bean.BlessReceiveGiftBean;
import com.itislevel.lyl.mvp.model.bean.BlessReceiveYuBean;
import com.itislevel.lyl.mvp.model.bean.BlessSendGiftBean;
import com.itislevel.lyl.mvp.model.bean.CartUpdateBean;
import com.itislevel.lyl.mvp.model.bean.FileUploadBean;
import com.itislevel.lyl.mvp.model.bean.FunshingCommentItemBean;
import com.itislevel.lyl.mvp.model.bean.HappyBlessUsualLanguageBean;
import com.itislevel.lyl.mvp.model.bus.HomeAdapaterSucc;
import com.itislevel.lyl.mvp.model.http.api.LYLApi;
import com.itislevel.lyl.mvp.ui.bus.bless_home;
import com.itislevel.lyl.mvp.ui.funsharing.FunsharingHomeActivity;
import com.itislevel.lyl.mvp.ui.location.Location_CityPickerActivity;
import com.itislevel.lyl.mvp.ui.user.LoginActivity;
import com.itislevel.lyl.net.LoaderStyle;
import com.itislevel.lyl.net.RestClent;
import com.itislevel.lyl.net.callback.IFailure;
import com.itislevel.lyl.net.callback.ISuccess;
import com.itislevel.lyl.utils.ActivityUtil;
import com.itislevel.lyl.utils.DateUtil;
import com.itislevel.lyl.utils.GsonUtil;
import com.itislevel.lyl.utils.SAToast;
import com.itislevel.lyl.utils.SharedPreferencedUtils;
import com.itislevel.lyl.utils.ToastUtil;
import com.itislevel.lyl.utils.rxbus.RxBus;
import com.itislevel.lyl.utils.rxbus.annotation.Subscribe;
import com.itislevel.lyl.utils.rxbus.annotation.UseRxBus;
import com.itislevel.lyl.utils.rxbus.event.EventThread;
import com.orhanobut.logger.Logger;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;
import com.vondear.rxtools.interfaces.OnRequestListener;
import com.vondear.rxtools.module.alipay.AliPayTools;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

@UseRxBus
public class BlessingHomeActivity extends RootActivity<BlessingPresenter> implements
        BlessingContract.View
        , BaseQuickAdapter.OnItemClickListener
        , BaseQuickAdapter.OnItemChildClickListener, View.OnClickListener,SwipeRefreshLayout.OnRefreshListener,BaseQuickAdapter.RequestLoadMoreListener {

    Bundle bundle = null;

    @BindView(R.id.refreshLayout)
    SwipeRefreshLayout refreshLayout;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @BindView(R.id.ll_header_temp)
    LinearLayout ll_header_temp;


    @BindView(R.id.bless_back_tv)
    AppCompatImageView bless_back_tv;//返回

    @BindView(R.id.belss_location_linear)
    LinearLayoutCompat belss_location_linear;//定位

    @BindView(R.id.bless_xishi_tv)
    AppCompatTextView bless_xishi_tv;//我的喜事

    @BindView(R.id.bless_location_tv)
    AppCompatTextView bless_location_tv ;

    @BindView(R.id.fa_tv)
    AppCompatTextView fa_tv;

    @BindView(R.id.liu_tv)
    AppCompatTextView liu_tv;

    @BindView(R.id.shou_tv)
    AppCompatTextView shou_tv;

    @BindView(R.id.bless_nulldata_linear)
    LinearLayoutCompat bless_nulldata_linear;

    //是否已添加没有更多数据的布局
    private boolean isAddNoMoreView = false;
    private int pageIndex = 1;//分页加载时 当前页是第几页
    private int totalPage = 0;//总页数
    private boolean isRefreshing = true;//当前是刷新还是加载
    private BlessListAdapter adapter;
    private ArrayList<String> typeList;

    private Dialog mDialog = null;

    String date = "";
    String looknum = "";
    String happynum = "";
    private boolean issearch;
    private LinearLayoutManager layoutManager;
    private TextView tv_search_header;
    private TextView tv_order_by_time_header;
    private TextView tv_order_by_view_count_header;

    private AppCompatTextView fa_tv1;
    private AppCompatTextView liu_tv1;
    private AppCompatTextView shou_tv1;
    private TextView tv_order_by_recevie_gift_header;
    private EditText et_search_header;

   private  int loade_more = 0;
    private int operatorPosition = -1;
    private TextView operatorView = null;
    private TextView tv_zan_count = null;
    private String start_city_id;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_blessing_home;
    }
    @Override
    protected void initEventAndData() {
        bless_back_tv.setOnClickListener(this);
        bless_xishi_tv.setOnClickListener(this);
        belss_location_linear.setOnClickListener(this);
        bundle = getIntent().getExtras();

        bless_location_tv.setText(bundle.getString(Constants.CITY_NAME));

        setToolbarTvTitle("喜事祝福");

        setToolbarMoreTxt("我的喜事");

        initAdapter();

        date = DateUtil.getCurrentDate();
        initRefreshLayout_recyclview();
        refreshLayout.setOnRefreshListener(this);
        refreshLayout.setRefreshing(true);
        isRefreshing = true;
        start_city_id =  bundle.getString(Constants.CITY_ID);
        loadData(start_city_id);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                int scollYDistance = getScollYDistance();
                Logger.e("scollYDistance:" + scollYDistance);

                if (dy >= 1||scollYDistance<=5) {//往上滚动 隐藏
                    if (ll_header_temp.isShown()) {
                        ll_header_temp.setVisibility(View.GONE);
                    }
                } else if (dy<1){ //往下滚动

                    if (scollYDistance > 185) {
                        if (!ll_header_temp.isShown()) {
                            ll_header_temp.setVisibility(View.VISIBLE);
//                            startTranslateAnimation();
                        }
                    }
                }

            }
        });
           et_search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH){
                    search(true);
                    return true;
                }
                return false;
            }

        });
    }
    private void initRefreshLayout_recyclview(){//设置刷新的颜色和款式及recyclevew
        //注册成为订阅者
        refreshLayout.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_orange_dark,
                android.R.color.holo_red_dark);
        refreshLayout.setProgressViewOffset(true,10,200);
    }
    public int getScollYDistance() {
//        LinearLayoutManager layoutManager = (LinearLayoutManager) this.getLayoutManager();
        int position = layoutManager.findFirstVisibleItemPosition();
        View firstVisiableChildView = layoutManager.findViewByPosition(position);
        int itemHeight = firstVisiableChildView.getHeight();
        return (position) * itemHeight - firstVisiableChildView.getTop();
    }

    @BindView(R.id.et_search)
    AppCompatEditText et_search;

    private void search(boolean isheader) {
        String searchTxt = "";
        if (isheader){
            searchTxt= et_search.getText().toString().trim();
        }else{
            searchTxt= tv_search_header.getText().toString().trim();
        }

        if (TextUtils.isEmpty(searchTxt)) {
            SAToast.makeText(this,"手机号修改成功", Toast.LENGTH_SHORT).show();
            return;
        }
        Map<String, String> request = new HashMap<>();
        request.put("token", SharedPreferencedUtils.getStr(Constants.USER_TOKEN));
        request.put("usernum", SharedPreferencedUtils.getStr(Constants.USER_NUM));
        request.put("pageIndex", pageIndex + "");
        request.put("pageSize", Constants.PAGE_NUMBER10 + "");
        request.put("nickname", searchTxt);
        mPresenter.happySearch(GsonUtil.obj2JSON(request));
    }

    private void getDialog() {
        //设置全屏将会覆盖状态栏
        mDialog = new Dialog(BlessingHomeActivity.this, R.style.Dialog_Fullscreen);
        //mDialog = new Dialog(MainActivity.this);
        Window mWindow = mDialog.getWindow();
        WindowManager.LayoutParams mParams = mWindow.getAttributes();
        mParams.alpha = 1f;
        mParams.gravity = Gravity.TOP;
        mParams.width = WindowManager.LayoutParams.MATCH_PARENT;
//        mParams.height = getResources().getDisplayMetrics().heightPixels/3; // 2
        mParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
//        mParams.y = -200;
        mParams.y = -150;
        mParams.x = 0;
        mWindow.setAttributes(mParams);
        mWindow.getDecorView().setBackgroundColor(Color.WHITE);
        mWindow.getDecorView().setPadding(0, 0, 0, 0);
        mWindow.getDecorView().setMinimumWidth(getResources().getDisplayMetrics().widthPixels);

//        mWindow.setType(WindowManager.LayoutParams.TYPE_APPLICATION_PANEL);
//        mWindow.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams
// .FLAG_FULLSCREEN);


        View view = LayoutInflater.from(this).inflate(R.layout.dialog_fullscreen_blessing, null);

        //   tv_bless_add  tv_send_record tv_bless_my

        ImageView iv_close = view.findViewById(R.id.iv_close);

        TextView tv_bless_add = view.findViewById(R.id.tv_bless_add);
        TextView tv_send_record = view.findViewById(R.id.tv_send_record);
        TextView tv_bless_my = view.findViewById(R.id.tv_bless_my);

        iv_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.dismiss();
            }
        });

        tv_bless_add.setOnClickListener(new View.OnClickListener() { //发布喜事
            @Override
            public void onClick(View v) {
                mDialog.dismiss();
                boolean islogin = SharedPreferencedUtils.getBool("islogin", false);
                if (!islogin) {
                    ActivityUtil.getInstance().openActivity(BlessingHomeActivity.this, LoginActivity.class);
                    return;
                }
                ActivityUtil.getInstance().openActivity(BlessingHomeActivity.this,
                        BlessingAddActivity.class, bundle);

//                Map<String, String> request = new HashMap<>();
//
//                mPresenter.alipayTest(GsonUtil.obj2JSON(request));
            }
        });
        tv_send_record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.dismiss();
                boolean islogin = SharedPreferencedUtils.getBool("islogin", false);
                if (!islogin) {
                    ActivityUtil.getInstance().openActivity(BlessingHomeActivity.this, LoginActivity.class);
                    return;
                }
                ActivityUtil.getInstance().openActivity(BlessingHomeActivity.this,
                        BlessingSendRecordActivity.class, bundle);
            }
        });

        tv_bless_my.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.dismiss();
                boolean islogin = SharedPreferencedUtils.getBool("islogin", false);
                if (!islogin) {
                    ActivityUtil.getInstance().openActivity(BlessingHomeActivity.this, LoginActivity.class);
                    return;
                }

                ActivityUtil.getInstance().openActivity(BlessingHomeActivity.this,
                        BlessingMyActivity.class, bundle);
            }
        });


        mDialog.setContentView(view);

        mDialog.setCancelable(true);
        mDialog.setCanceledOnTouchOutside(true);
//        mDialog.setTitle("this is title");
        mDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                Window mWindow = getWindow();
                WindowManager.LayoutParams mParams = mWindow.getAttributes();
                mParams.alpha = 1.0f;
                mWindow.setAttributes(mParams);
            }
        });
        mDialog.show();

    }

    private void loadData(String city_id) {
        issearch = false;
        Map<String, String> request = new HashMap<>();
        request.put("token", SharedPreferencedUtils.getStr(Constants.USER_TOKEN));
        request.put("usernum", SharedPreferencedUtils.getStr(Constants.USER_NUM));
        request.put("pageIndex", pageIndex + "");
        request.put("pageSize", Constants.PAGE_NUMBER10 + "");
        request.put("userid", SharedPreferencedUtils.getStr(Constants.USER_ID));

        request.put("createdtime", date);
        request.put("looknum", looknum);
        request.put("happynum", happynum);
        request.put("cityid",city_id);//需要的城市id
        mPresenter.happyList(GsonUtil.obj2JSON(request));
    }
    /**
     * 初始化adapter
     */
    private void initAdapter() {

        if (adapter == null) {
            adapter = new BlessListAdapter(R.layout.item_bless_home,this);
            adapter.setOnItemClickListener(this);
            adapter.setOnItemChildClickListener(this);
            adapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN); //切换动画
           // adapter.setEnableLoadMore(false);
            layoutManager = new LinearLayoutManager(this);
//            GridLayoutManager layoutManager = new GridLayoutManager(this, 3, LinearLayoutManager
//                    .VERTICAL, false);
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            recyclerView.setLayoutManager(layoutManager);
            View header = LayoutInflater.from(this).inflate(R.layout.header_bless_home, null,
                    false);
            adapter.addHeaderView(header);
            recyclerView.setAdapter(adapter);

            tv_search_header = header.findViewById(R.id.tv_search_header);
            et_search_header = header.findViewById(R.id.et_search_header);
            tv_order_by_time_header = header.findViewById(R.id.tv_order_by_time_header);
            tv_order_by_view_count_header = header.findViewById(R.id.tv_order_by_view_count_header);
            tv_order_by_recevie_gift_header = header.findViewById(R.id.tv_order_by_recevie_gift_header);
            fa_tv1 = header.findViewById(R.id.fa_tv1);
            shou_tv1 = header.findViewById(R.id.shou_tv1);
            liu_tv1 = header.findViewById(R.id.liu_tv1);

            tv_search_header.setOnClickListener(BlessingHomeActivity.this);
            tv_order_by_time_header.setOnClickListener(BlessingHomeActivity.this);
            tv_order_by_view_count_header.setOnClickListener(BlessingHomeActivity.this);
            tv_order_by_recevie_gift_header.setOnClickListener(BlessingHomeActivity.this);

        }
    }



    @Override
    public void showContent(String msg) {

    }

    @Override
    public void happAdd(String action) {
        refreshLayout.setRefreshing(false);
    }

    @Override
    public void happyListMy(BlessListBean blessListBean) {

    }

    @Override
    public void happyViewCount(String action) {

    }

    @Override
    public void happyDel(String action) {

    }

    @Override
    public void happyList(BlessListBean blessListBean) {
        totalPage = blessListBean.getTotalPage();
        loade_more++;
        if (loade_more == 1)
        {
            adapter.setOnLoadMoreListener(this,recyclerView);//设置加载更多
        }
        //返回的数据 第一次
        if (adapter.getData().size() <= 0 && blessListBean.getList() != null && blessListBean
                .getList().size() <= 0) {
            View emptyView = View.inflate(this, R.layout.partial_empty_view, null);
            TextView viewById = emptyView.findViewById(R.id.tv_message);
            adapter.setEmptyView(emptyView);
        }
        if (isRefreshing) {
            adapter.setNewData(blessListBean.getList());
            refreshLayout.setRefreshing(false);
        } else {
            adapter.addData(blessListBean.getList());
            adapter.loadMoreComplete();
        }


    }

    @Override
    public void happyComment(BlessCommentBean blessCommentBean) {

    }


    @Override
    public void happyCommentDel(String action) {

    }

    @Override
    public void happyLike(BlessAddLikeBean blessAddLikeBean) {
           BlessListBean.ListBean item = this.adapter.getItem(operatorPosition);
           // EventBus.getDefault().post(new bless_home());
           int oldNum = Integer.parseInt(operatorView.getText().toString());
            int newNUm = blessAddLikeBean.getSl().size();

        if (oldNum > newNUm) {//取消
                item.setIspoint("0");
                Drawable rightDrawable = getResources().getDrawable(R.mipmap.icon_like_gray);
                rightDrawable.setBounds(0, 0, rightDrawable.getMinimumWidth(), rightDrawable.getMinimumHeight());
                operatorView.setCompoundDrawables(rightDrawable, null, null, null);
                SAToast.makeText(this,"来了1").show();
            } else {//点赞
            item.setIspoint("1");
               // item.setFabulousnumber((end_num++)+"");
                Drawable rightDrawable = getResources().getDrawable(R.mipmap.icon_like);
                rightDrawable.setBounds(0, 0, rightDrawable.getMinimumWidth(), rightDrawable
                        .getMinimumHeight());
                operatorView.setCompoundDrawables(rightDrawable, null, null, null);
                SAToast.makeText(this,"来了2").show();
            }
           item.setFabulousnumber(newNUm+"");
            List<String> s2 = blessAddLikeBean.getS2();
            item.setNmpointlist(s2);
        operatorView = null;
    }

    @Override
    public void happyBlessAdd(BlessAddBean blessAddBean) {

    }

    @Override
    public void happyBlessReceiveList(BlessReceiveYuBean blessReceiveYuBean) {

    }

    @Override
    public void happyUsualLanguage(HappyBlessUsualLanguageBean blessUsualLanguageBeanb) {

    }


    @Override
    public void happyGiftList(BlessGiftBean blessGiftBean) {

    }

    @Override
    public void happyGiftReceiveListMy(BlessReceiveGiftBean blessGiftReceivedBean) {

    }

    @Override
    public void happyGiftSendListMy(BlessSendGiftBean blessSendGiftBean) {

    }

    @Override
    public void happyOrderAdd(String blessOrderBean) {

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

    @Override
    public void happySearch(BlessListBean blessListBean) {

        issearch = true;
        //返回的数据 第一次
        if (pageIndex == 1 && (blessListBean.getList() == null || blessListBean.getList().size()
                <= 0)) {
            View emptyView = View.inflate(this, R.layout.partial_empty_view, null);
            TextView viewById = emptyView.findViewById(R.id.tv_message);
            adapter.setEmptyView(emptyView);
            SAToast.makeText(this,"没有搜索到内容").show();
        }
        totalPage = blessListBean.getTotalPage();
        if (isRefreshing) {
            adapter.setNewData(blessListBean.getList());
            refreshLayout.setRefreshing(false);
        } else {
            adapter.addData(blessListBean.getList());
            adapter.loadMoreComplete();
        }
    }

    @Override
    public void happyDetailsGiftList(BlessDetailGiftListBean detailGiftListBean) {

    }


    @Override
    public void uploadHeader(FileUploadBean fileUploadBean) {

    }

    @Override
    public void alipay(AliPayBean aliPayBean) {

        Logger.w("ali:" + aliPayBean.getOrder());

//        AliPayTools.aliPay(BlessingHomeActivity.this,aliPayBean.getOrder(), new
//                OnRequestListener() {
//
//
//
//                    @Override
//                    public void onSuccess(String message) {
//
//                    }
//
//                    @Override
//                    public void onError(String s) {
//                        Logger.w("error:" + s);
//
//                    }
//                });


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
        if (adapter.getData() == null || adapter.getData().size() <= 0||e.getMessage().contains("失败")) {
            bless_nulldata_linear.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        }
        if (adapter.isLoadMoreEnable()) {
            adapter.loadMoreComplete();
        }
        if (refreshLayout.isRefreshing()) {
            refreshLayout.setRefreshing(false);
        }
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        BlessListBean.ListBean item = this.adapter.getItem(position);
        operatorPosition = position;
        switch (view.getId()) {
            case R.id.iv_header:
                break;
            case R.id.tv_nickname:
                break;
            case R.id.tv_title:
                boolean islogin3 = SharedPreferencedUtils.getBool("islogin", false);
                if (!islogin3) {
                    ActivityUtil.getInstance().openActivity(BlessingHomeActivity.this, LoginActivity.class);
                    return;
                }
                //  iv_header tv_nickname
                //  tv_title ninegrid_imgs tv_comment_num tv_like_num
                bundle.putBoolean("isfromMy", false);
                bundle.putString("blessid", item.getId() + "");
                bundle.putString("userid", item.getUserid() + "");
                bundle.putString("username", item.getNickname());
                bundle.putString("userheader", item.getImgurl());

                bundle.putString("title", item.getContent());
                bundle.putString("imags", item.getImge());
                bundle.putInt("viewCount", item.getLooknum());
                bundle.putString("islike", item.getIspoint());
                bundle.putString("likenum", Integer.parseInt(item.getFabulousnumber()) + "");

                bundle.putString("comments", GsonUtil.obj2JSON(item));
                bundle.putString("addr", item.getProvincename() + "." + item.getCityname());
                bundle.putString("createtime", DateUtil.timeSpanToDateTime(item.getCreatedtime()));
                bundle.putString("nicknamelist", GsonUtil.obj2JSON(item.getNmpointlist()));
                bundle.putString("fabuzheid", item.getUserid() + "");

                ActivityUtil.getInstance().openActivity(BlessingHomeActivity.this,
                        BlessingDetailActivity.class, bundle);
                break;
            case R.id.tv_like_num:
                boolean islogin = SharedPreferencedUtils.getBool("islogin", false);
                if (!islogin) {
                    ActivityUtil.getInstance().openActivity(BlessingHomeActivity.this, LoginActivity.class);
                    return;
                }
                operatorView = (TextView) view;
                Map<String, String> request = new HashMap<>();
                request.put("token", SharedPreferencedUtils.getStr(Constants.USER_TOKEN));
                request.put("usernum", SharedPreferencedUtils.getStr(Constants.USER_NUM));
                request.put("userid", SharedPreferencedUtils.getStr(Constants.USER_ID));
                request.put("id",this.adapter.getItem(position).getId() + "");
                request.put("reluserid", this.adapter.getItem(position).getUserid() + "");
                mPresenter.happyLike(GsonUtil.obj2JSON(request));
                break;
            case R.id.tv_zan_count:
                break;
            case R.id.tv_share:
                boolean islogin2 = SharedPreferencedUtils.getBool("islogin", false);
                if (!islogin2) {
                    ActivityUtil.getInstance().openActivity(BlessingHomeActivity.this, LoginActivity.class);
                    return;
                }
                share(this.adapter.getItem(position).getId()+"",this.adapter.getItem(position).getNickname());
                //SAToast.makeText(this,"分先").show();
                break;
            case R.id.share_guan_linear:
                LinearLayoutCompat share_guan_linear = view.findViewById(R.id.share_guan_linear);
                AppCompatImageView add_im = view.findViewById(R.id.guan_zhu_image);
                AppCompatTextView add_tv = view.findViewById(R.id.guan_zhu_tv);
                SAToast.makeText(this,"关注触发").show();
                if(add_tv.getText().toString().equals("关注"))
                {
                    add_tv.setText("已关注");
                    add_im.setBackgroundResource(R.mipmap.share_yeguan);
                    add_tv.setTextColor(Color.parseColor("#666666"));
                    share_guan_linear.setBackground(getResources().getDrawable(R.drawable.share_item_yeguan));
                    init_guan(this.adapter.getItem(position).getUserid()+"");//关注的网络请求
                }else {
                    SAToast.makeText(this,"不能取消关注").show();
                   /* add_tv.setText("关注");
                    add_im.setBackgroundResource(R.mipmap.share_guan_add);
                    add_tv.setTextColor(Color.parseColor("#ff7a00"));
                    share_guan_linear.setBackground(getResources().getDrawable(R.drawable.share_item_shape));
                    init_guan(this.adapter.getItem(position).getUserid()+"");//关注的网络请求*/
                }
                break;
        }
    }
    private void addLikeNum() {
    }
    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        BlessListBean.ListBean item = this.adapter.getItem(position);

        bundle.putBoolean("isfromMy", false);
        bundle.putString("blessid", item.getId() + "");
        bundle.putString("userid", item.getUserid() + "");
        bundle.putString("username", item.getNickname());
        bundle.putString("userheader", item.getImgurl());

        bundle.putString("title", item.getContent());
        bundle.putString("imags", item.getImge());
        bundle.putInt("viewCount", item.getLooknum());
        bundle.putString("islike", item.getIspoint());
        bundle.putString("likenum", Integer.parseInt(item.getFabulousnumber()) + "");

        bundle.putString("comments", GsonUtil.obj2JSON(item));
        bundle.putString("addr", item.getProvincename() + "." + item.getCityname());
        bundle.putString("createtime", DateUtil.timeSpanToDateTime(item.getCreatedtime()));
        bundle.putString("nicknamelist", GsonUtil.obj2JSON(item.getNmpointlist()));
        bundle.putString("fabuzheid", item.getUserid() + "");

        ActivityUtil.getInstance().openActivity(BlessingHomeActivity.this, BlessingDetailActivity
                .class, bundle);
    }

    @org.greenrobot.eventbus.Subscribe
    public void Event(bless_home messageEvent)
    {
        refreshLayout.setRefreshing(true);
        pageIndex = 1;
        isRefreshing = true;
        start_city_id = messageEvent.getCity_id();
        bless_location_tv.setText(messageEvent.getCity_name());
        loadData(start_city_id);
    }
    @Subscribe(tag = RxBus.TAG_UPDATE, thread = EventThread.MAIN_THREAD)
    public void rxrefresh(String msg) {
        if (msg.equals("refresh")) {
            Logger.i("开始刷新");

            SAToast.makeText(this,"发布成功").show();
            refreshLayout.setRefreshing(true);//第一次进入触发自动刷新，演示效果--然后在重新加载数据
        } else if ("close".equals(msg)) {
//            refreshLayout.autoRefresh();//第一次进入触发自动刷新，演示效果--然后在重新加载数据
        }
    }

/*    @Override
    public void onBackPressed() {
        if (issearch) {
            refreshLayout.setRefreshing(true);
            et_search.setText("");
        } else {
            super.onBackPressed();

        }

    }*/


    @BindView(R.id.tv_order_by_time)
    TextView tv_order_by_time;


    @BindView(R.id.tv_order_by_view_count)
    TextView tv_order_by_view_count;


    @BindView(R.id.tv_order_by_recevie_gift)
    TextView tv_order_by_recevie_gift;

    //  tv_search tv_order_by_time tv_order_by_view_count tv_order_by_recevie_gift
    @OnClick({R.id.tv_search, R.id.tv_order_by_time, R.id.tv_order_by_view_count, R.id
            .tv_order_by_recevie_gift})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.tv_search://搜索
                search(true);
                break;
            case R.id.tv_order_by_time://按收时间排序 DateUtil.getCurrentDate()
                setDefault();
                init_bootom();
                fa_tv.setVisibility(View.VISIBLE);
                fa_tv1.setVisibility(View.VISIBLE);
                tv_order_by_time.setTextColor(Color.parseColor("#ff7a00"));
                tv_order_by_time_header.setTextColor(Color.parseColor("#ff7a00"));

                date = DateUtil.getCurrentDate();
                pageIndex = 1;
                looknum = "";
                happynum = "";
                isRefreshing = true;
                refreshLayout.setRefreshing(true);
                loadData(start_city_id);
                break;
            case R.id.tv_order_by_view_count://按浏览量排序
                setDefault();
                init_bootom();
                liu_tv1.setVisibility(View.VISIBLE);
                liu_tv.setVisibility(View.VISIBLE);

                tv_order_by_view_count.setTextColor(Color.parseColor("#ff7a00"));
                tv_order_by_view_count_header.setTextColor(Color.parseColor("#ff7a00"));
                date = DateUtil.getCurrentDate();
                pageIndex = 1;
                looknum = "1";
                happynum = "";
                isRefreshing = true;
                refreshLayout.setRefreshing(true);
                loadData(start_city_id);
                break;
            case R.id.tv_order_by_recevie_gift://按收到的礼物排序
                setDefault();
                init_bootom();
                shou_tv.setVisibility(View.VISIBLE);
                shou_tv1.setVisibility(View.VISIBLE);

                tv_order_by_recevie_gift.setTextColor(Color.parseColor("#ff7a00"));
                tv_order_by_recevie_gift_header.setTextColor(Color.parseColor("#ff7a00"));
                date = DateUtil.getCurrentDate();
                pageIndex = 1;
                looknum = "";
                happynum = "1";
                isRefreshing = true;
                refreshLayout.setRefreshing(true);
                loadData(start_city_id);
                break;

        }

    }

    private void setDefault() {
        tv_order_by_time.setTextColor(Color.parseColor("#666666"));
        tv_order_by_view_count.setTextColor(Color.parseColor("#666666"));
        tv_order_by_recevie_gift.setTextColor(Color.parseColor("#666666"));


        tv_order_by_time_header.setTextColor(Color.parseColor("#666666"));
        tv_order_by_view_count_header.setTextColor(Color.parseColor("#666666"));
        tv_order_by_recevie_gift_header.setTextColor(Color.parseColor("#666666"));
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.belss_location_linear://定位选择
                Bundle bundle1 = new Bundle();
                bundle1.putString(Constants.START_FLAGE,"bless");//代表品的事喜事祝福
                bundle1.putString(Constants.CITY_NAME, bundle.getString(Constants.CITY_NAME));
                bundle1.putString(Constants.CITY_ID, bundle.getString(Constants.CITY_ID));
                ActivityUtil.getInstance().openActivity(this, Location_CityPickerActivity.class,bundle1);
                break;
            case R.id.bless_back_tv:
                ActivityUtil.getInstance().closeActivity(this);
                break;
            case R.id.bless_xishi_tv:
                getDialog();
                break;
            case R.id.tv_search_header://搜索
                search(false);
                break;
            case R.id.tv_order_by_time_header://按收时间排序 DateUtil.getCurrentDate()
                setDefault();
                init_bootom();
                fa_tv.setVisibility(View.VISIBLE);
                fa_tv1.setVisibility(View.VISIBLE);
                tv_order_by_time.setTextColor(Color.parseColor("#ff7a00"));
                tv_order_by_time_header.setTextColor(Color.parseColor("#ff7a00"));
                date = DateUtil.getCurrentDate();
                pageIndex = 1;
                looknum = "";
                happynum = "";
                isRefreshing = true;
                refreshLayout.setRefreshing(true);
                loadData(start_city_id);
                break;
            case R.id.tv_order_by_view_count_header://按浏览量排序
              //
                setDefault();
                init_bootom();
                liu_tv1.setVisibility(View.VISIBLE);
                liu_tv.setVisibility(View.VISIBLE);

                tv_order_by_view_count.setTextColor(Color.parseColor("#ff7a00"));
                tv_order_by_view_count_header.setTextColor(Color.parseColor("#ff7a00"));
                date = DateUtil.getCurrentDate();
                pageIndex = 1;
                looknum = "1";
                happynum = "";
                isRefreshing = true;
                refreshLayout.setRefreshing(true);
                loadData(start_city_id);
                break;
            case R.id.tv_order_by_recevie_gift_header://按收到的礼物排序
               // setDefault();
               //
                setDefault();
                init_bootom();
                shou_tv.setVisibility(View.VISIBLE);
                shou_tv1.setVisibility(View.VISIBLE);

                tv_order_by_recevie_gift.setTextColor(Color.parseColor("#ff7a00"));
                tv_order_by_recevie_gift_header.setTextColor(Color.parseColor("#ff7a00"));
                date = DateUtil.getCurrentDate();
                pageIndex = 1;
                looknum = "";
                happynum = "1";
                isRefreshing = true;
                refreshLayout.setRefreshing(true);
                loadData(start_city_id);
                break;
        }
    }


    private void init_bootom(){
        fa_tv.setVisibility(View.GONE);
        liu_tv.setVisibility(View.GONE);
        shou_tv.setVisibility(View.GONE);

        fa_tv1.setVisibility(View.GONE);
        liu_tv1.setVisibility(View.GONE);
        shou_tv1.setVisibility(View.GONE);
    }

    @Override
    public void onRefresh() {
        refreshLayout.setRefreshing(true);
        pageIndex = 1;
        isRefreshing = true;
        loadData(start_city_id);

    }

    @Override
    public void onLoadMoreRequested() {
        adapter.setEnableLoadMore(true);
        pageIndex++;
        if (pageIndex > totalPage) {
            adapter.loadMoreEnd();
            SAToast.makeText(BlessingHomeActivity.this,"没有更多啦~~").show();
            return;
        } else {
            isRefreshing = false;
            loadData(start_city_id);
        }
    }
    private void share(String id,String name) {
        if (Build.VERSION.SDK_INT >= 23) {
            String[] mPermissionList = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.CALL_PHONE,
                    Manifest.permission.READ_LOGS, Manifest.permission.READ_PHONE_STATE, Manifest
                    .permission.READ_EXTERNAL_STORAGE, Manifest.permission.SET_DEBUG_APP,
                    Manifest.permission.SYSTEM_ALERT_WINDOW, Manifest.permission.GET_ACCOUNTS,
                    Manifest.permission.WRITE_APN_SETTINGS};
            ActivityCompat.requestPermissions(this, mPermissionList, 666);

//            new ShareAction(this)
//                    .withText("hello")
//                    .setDisplayList(SHARE_MEDIA.QQ,SHARE_MEDIA.WEIXIN)
//                    .setCallback(shareListener)
//                    .open();

//            UMWeb web = new UMWeb("https://juejin.im/post/5a692377518825734e3e71ab");
            UMWeb web = new UMWeb(LYLApi.HOST_SHARE+"share/index.html#/xishi_detail?xishiId="+id);

            web.setTitle(name+"的喜事");//标题
            UMImage umImage = new UMImage(this, R.mipmap.ic_launcher);
            web.setThumb(umImage);  //缩略图
            web.setDescription("友邦友-喜事分享");//描述
            new ShareAction(this)
                    .withMedia(web)
                    .setDisplayList(SHARE_MEDIA.QQ, SHARE_MEDIA.QZONE,SHARE_MEDIA.WEIXIN,SHARE_MEDIA.WEIXIN_CIRCLE)
                    .setCallback(shareListener)
                    .open();
//                    .share();
        }
    }

    private UMShareListener shareListener = new UMShareListener() {
        /**
         * @descrption 分享开始的回调
         * @param platform 平台类型
         */
        @Override
        public void onStart(SHARE_MEDIA platform) {

        }

        /**
         * @descrption 分享成功的回调
         * @param platform 平台类型
         */
        @Override
        public void onResult(SHARE_MEDIA platform) {
//            Toast.makeText(FunsharingHomeActivity.this,"成功了",Toast.LENGTH_LONG).show();
            // ToastUtil.Success("分享成功");

            SAToast.makeText(BlessingHomeActivity.this,"分享成功", Toast.LENGTH_SHORT).show();
        }
        /**
         * @descrption 分享失败的回调
         * @param platform 平台类型
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
//            Toast.makeText(FunsharingHomeActivity.this,"失败"+t.getMessage(),Toast.LENGTH_LONG)
// .show();
            SAToast.makeText(BlessingHomeActivity.this,"分享失败", Toast.LENGTH_SHORT).show();
        }
        /**
         * @descrption 分享取消的回调
         * @param platform 平台类型
         */
        @Override
        public void onCancel(SHARE_MEDIA platform) {
//            Toast.makeText(FunsharingHomeActivity.this,"取消了",Toast.LENGTH_LONG).show();
//            ToastUtil.Success("分享已取消");
        }
    };
    private void init_guan(String touserid)
    {
        String user_id = SharedPreferencedUtils.getStr(Constants.USER_NUM);
        Map<String, String> request = new HashMap<>();
        request.put("token", SharedPreferencedUtils.getStr(Constants.USER_TOKEN));
        request.put("usernum", SharedPreferencedUtils.getStr(Constants.USER_NUM));
        request.put("userid",  SharedPreferencedUtils.getStr(Constants.USER_ID));
        request.put("touserid", touserid);
        request.put("modename", "happy");
        String  jsonObject = JSON.toJSONString(request);

        RestClent.builder()
                .url("follow")
                .params("action",jsonObject)
                .loader(this, LoaderStyle.BallClipRotateIndicator)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        System.out.println("关注返回的数据******************"+response);
                        SAToast.makeText(BlessingHomeActivity.this,"关注成功！").show();
                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onIFailure() {

                    }
                })
                .build()
                .post();
    }
}
