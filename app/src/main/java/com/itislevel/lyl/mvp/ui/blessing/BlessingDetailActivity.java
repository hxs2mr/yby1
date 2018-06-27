package com.itislevel.lyl.mvp.ui.blessing;

import android.Manifest;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.Editable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.zhouwei.library.CustomPopWindow;
import com.google.android.flexbox.FlexboxLayout;
import com.itislevel.lyl.R;
import com.itislevel.lyl.adapter.BlessCommentAdapter;
import com.itislevel.lyl.adapter.BlessCommentReplayAdapter;
import com.itislevel.lyl.adapter.BlessDetailGiftAdapter;
import com.itislevel.lyl.app.App;
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
import com.itislevel.lyl.mvp.model.bean.FunshingItemBean;
import com.itislevel.lyl.mvp.model.bean.HappyBlessUsualLanguageBean;
import com.itislevel.lyl.mvp.model.http.api.LYLApi;
import com.itislevel.lyl.mvp.ui.chatkeyboardview.EmotionMainFragment;
import com.itislevel.lyl.mvp.ui.chatkeyboardview.EmotionUtils;
import com.itislevel.lyl.mvp.ui.chatkeyboardview.SpanStringUtils;
import com.itislevel.lyl.mvp.ui.pay.PayInfoActivity;
import com.itislevel.lyl.utils.ActivityUtil;
import com.itislevel.lyl.utils.GsonUtil;
import com.itislevel.lyl.utils.SAToast;
import com.itislevel.lyl.utils.SharedPreferencedUtils;
import com.itislevel.lyl.utils.ToastUtil;
import com.itislevel.lyl.utils.imageload.ImageLoadConfiguration;
import com.itislevel.lyl.utils.imageload.ImageLoadProxy;
import com.itislevel.lyl.utils.rxbus.RxBus;
import com.itislevel.lyl.utils.rxbus.annotation.Subscribe;
import com.itislevel.lyl.utils.rxbus.annotation.UseRxBus;
import com.itislevel.lyl.utils.rxbus.event.EventThread;
import com.itislevel.lyl.widget.BlessGiftDialog;
import com.itislevel.lyl.widget.BlessYUDialog;
import com.lzy.ninegrid.ImageInfo;
import com.lzy.ninegrid.NineGridView;
import com.lzy.ninegrid.preview.NineGridViewClickAdapter;
import com.orhanobut.logger.Logger;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

@UseRxBus
public class BlessingDetailActivity extends RootActivity<BlessingPresenter> implements
        BlessingContract.View
        , BaseQuickAdapter.OnItemClickListener
        , BaseQuickAdapter.OnItemChildClickListener, View.OnClickListener {


    //  iv_header tv_nickname
    //  tv_title ninegrid_imgs tv_comment_num tv_like_num


    @BindView(R.id.iv_header)
    ImageView iv_header;

    @BindView(R.id.tv_nickname)
    TextView tv_nickname;

    @BindView(R.id.tv_title)
    TextView tv_title;


    @BindView(R.id.tv_time)
    TextView tv_time;

    @BindView(R.id.tv_comment_num)
    TextView tv_comment_num;

    @BindView(R.id.tv_like_num)
    TextView tv_like_num;

    @BindView(R.id.ninegrid_imgs)
    NineGridView ninegrid_imgs;

    @BindView(R.id.ns_scroll)
    ScrollView ns_scroll;


    @BindView(R.id.ll_my_parent)
    LinearLayout ll_my_parent;


    Bundle bundle = null;

    private boolean isfromMy = false; //是否来自于我的

    private String userid;
    private String blessid;
    private String imagsUrls;
    private String likenum;
    private int viewCount;
    private BlessYUDialog blessYuDialog;
    private BlessGiftDialog blessGiftDialog;
    private String comments;
    private BlessListBean.ListBean listBean;


    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private InputMethodManager inputMethodManager;

    @BindView(R.id.tv_send_gift)
    TextView tv_send_gift;

    @BindView(R.id.tv_go_bless)
    TextView tv_go_bless;

//    //评论框
//    @BindView(R.id.ll_comment)
//    LinearLayout ll_commint;
//    @BindView(R.id.iv_comment_img)
//    ImageView iv_comment_img;
//    @BindView(R.id.iv_comment_face)
//    ImageView iv_comment_face;
//    @BindView(R.id.et_comment)
//    EditText et_comment;

    // 表情输入框

    @BindView(R.id.ll_parent)
    LinearLayout mLinearLayout;

    @BindView(R.id.fl_pannel)
    FrameLayout fl_pannel;
    private EmotionMainFragment emotionMainFragment;//表情面板
    private Button mBtnSend;//发送按钮
    private ImageView mIvEmotion;//表情按钮
    private ImageView mIvExtend;//扩展菜单按钮
    private EditText mEditText;//内容输入框
    private View mInputView;
    private InputMethodManager mInputMethodManager;//软键盘管理


    //    private TextView operatorView = null;
    private int operatorPosition = -1;

    private String touserid = "";//回复时  回复对象的用户id
    private String parentId = ""; //回复时的父id
    private String observer="";
    private String answever="";
    BlessCommentAdapter commentAdapter;//评论的adapter
    BlessCommentReplayAdapter commentReplayAdapter;//回复的adapter
    private int whoAdapter = 0; // 哪一个adapter 0分享-1评论-2回复
    private int isComment = 0;//评论传0，回复传1

    private boolean isCommenting = false;//当前状态是评论还是祝福 false 是祝福,默认是祝福 true 是评论
    private HappyBlessUsualLanguageBean usualLanguageBean;
    private BlessGiftBean giftBean;
    private BlessCartListBean cartListBean;

    // 礼品展示的
    @BindView(R.id.recycler_gift)
    RecyclerView recycler_gift;

    BlessDetailGiftAdapter detailGiftAdapter;
    private double totalPrice;
    private String goodsDetail;

    @BindView(R.id.fbl_parent)
    FlexboxLayout fbl_parent;

    @BindView(R.id.son_tv)
    AppCompatTextView son_tv;

    @BindView(R.id.song2_iv)
    AppCompatImageView song2_iv;

    @BindView(R.id.tv_look_count)
    TextView tv_look_count;
    private String username;
    private List<BlessDetailGiftListBean.ListBean> receiveGiftList;
    private BlessDetailGiftListBean detailGiftListBean1;
    String tonickname = "";
    @Override
    protected int getLayoutId() {
        return R.layout.activity_blessing_detail;
    }
    @Override
    protected void initEventAndData() {
        bundle = getIntent().getExtras();
        son_tv.setOnClickListener(this);
        song2_iv.setOnClickListener(this);
        inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

        setToolbarTvTitle("喜事祝福");
        String fabuzheid = bundle.getString("fabuzheid");
        String localUserid = SharedPreferencedUtils.getStr(Constants.USER_ID);


        isfromMy = bundle.getBoolean("isfromMy");

        if (isfromMy||fabuzheid.equals(localUserid)) {
            tv_go_bless.setText("收到的祝福");
            tv_send_gift.setText("收到的礼品");
        }
        else {
            tv_go_bless.setText("我要祝福");
            tv_send_gift.setText("我要送礼");
        }

        userid = bundle.getString("userid");
        observer=  SharedPreferencedUtils.getStr(Constants.USER_NICK_NAME);
        SAToast.makeText(this,"Observer"+observer).show();
        blessid = bundle.getString("blessid");
        String header = bundle.getString("userheader");
        username = bundle.getString("username");
        String addr = bundle.getString("addr");
        String title = bundle.getString("title");
        String nicknamelist = bundle.getString("nicknamelist");
        tv_look_count.setText(bundle.getString("viewCount"));
        if (!TextUtils.isEmpty(nicknamelist)) {
            List<String> nicknameList = GsonUtil.toList(nicknamelist, String.class);
            //***
            if (nicknameList != null && nicknameList.size() > 0) {

                fbl_parent.removeAllViews();

                TextView likeICONTxt = new TextView(App.getInstance());
                Drawable rightDrawable = getResources().getDrawable(R.mipmap
                        .icon_like_tip);
                rightDrawable.setBounds(0, 0, rightDrawable.getMinimumWidth(), rightDrawable
                        .getMinimumHeight());
                likeICONTxt.setTextSize(14);
                likeICONTxt.setCompoundDrawables(rightDrawable, null, null, null);
                likeICONTxt.setText("、");
                likeICONTxt.setCompoundDrawablePadding(10);
                likeICONTxt.setTextColor(Color.parseColor("#034b71"));
                fbl_parent.addView(likeICONTxt);

                int index = 0;
//                    index = index + 1;
//                    if (index < blessAddLikeBean.getS2().size()) {
//                        textView.setText(fab + "、");
//                    } else {
//                        textView.setText(fab);
//                    }

                for (String fab : nicknameList) {
                    TextView textView = new TextView(App.getInstance());
                    index = index + 1;
                    if (index < nicknameList.size()) {
                        textView.setText(fab + "、");
                    } else {
                        textView.setText(fab);
                    }
                    textView.setTextSize(15);
                    textView.setTextColor(Color.parseColor("#034b71"));
                    textView.setPadding(5, 5, 5, 5);
                    fbl_parent.addView(textView);
                }

            } else {
                fbl_parent.removeAllViews();
            }

            //***

        }
        comments = bundle.getString("comments");

        if (!TextUtils.isEmpty(comments)) {
            listBean = GsonUtil.toObject(comments, BlessListBean
                    .ListBean.class);

        }

        listBean.getImgurl();

        imagsUrls = bundle.getString("imags");
        likenum = bundle.getString("likenum");
        viewCount = bundle.getInt("viewCount");


        if (TextUtils.isEmpty(header)) {
            ImageLoadProxy.getInstance()
                    .load(new ImageLoadConfiguration.Builder(App.getInstance())
                            .url(R.mipmap.icon_default_header_circle)
                            .defaultImageResId(R.mipmap.icon_default_header_circle)
                            .imageView(iv_header).build());
        } else {
            ImageLoadProxy.getInstance()
                    .load(new ImageLoadConfiguration.Builder(App.getInstance())
                            .url(Constants.IMG_SERVER_PATH + header.trim())
                            .defaultImageResId(R.mipmap.icon_default_header_circle)
                            .imageView(iv_header).build());
        }

        tv_nickname.setText(username);

//        tv_time.setText(bundle.getString("addr"));
        tv_time.setText(bundle.getString("createtime"));

//        tv_title.setText(title);
//        TextView tvConent=helper.getView(R.id.tv_title);
        SpannableString emotionContent = SpanStringUtils.getEmotionContent(EmotionUtils
                .EMOTION_CLASSIC_TYPE, this, tv_title, title);
        tv_title.setText(emotionContent);

        if (bundle.getString("islike").equals("1")) {
            Drawable rightDrawable = getResources().getDrawable(R.mipmap.icon_like);
            rightDrawable.setBounds(0, 0, rightDrawable.getMinimumWidth(), rightDrawable
                    .getMinimumHeight());
            tv_like_num.setCompoundDrawables(rightDrawable, null, null, null);
        } else {
            Drawable rightDrawable = getResources().getDrawable(R.mipmap.icon_like_gray);
            rightDrawable.setBounds(0, 0, rightDrawable.getMinimumWidth(), rightDrawable
                    .getMinimumHeight());
            tv_like_num.setCompoundDrawables(rightDrawable, null, null, null);
        }

        tv_like_num.setText(likenum);
        //tv_like_num.setText("");

        List<ImageInfo> urlList = new ArrayList<>();
        ImageInfo imageInfo = null;
        //包含图片
        if (!TextUtils.isEmpty(imagsUrls)) {
            String[] split = imagsUrls.split(",");
            ninegrid_imgs.setVisibility(View.VISIBLE);
            for (String url : split) {
                if (url != null && url != "" && !url.equals(",")) {
                    imageInfo = new ImageInfo();
                    imageInfo.setBigImageUrl(Constants.IMG_SERVER_PATH + url.trim());
                    imageInfo.setThumbnailUrl(Constants.IMG_SERVER_PATH + url.trim());
                    urlList.add(imageInfo);
                }
            }
            ninegrid_imgs.setAdapter(new NineGridViewClickAdapter(App.getInstance(), urlList));
        } else {
            ninegrid_imgs.setVisibility(View.GONE);

        }
        commentItemListener = new CommentItemListener();
        commentReplayListener = new CommentReplayListener();


//        ns_scroll.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver
//                .OnScrollChangedListener() {
//
//            @Override
//            public void onScrollChanged() {
//
//                if (!isCommenting) {
//                    closeCommentInput();
//                }
//            }
//        });

        // 输入框
        mInputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        initViewListener();

        initAdapter();

        //新增浏览量
        addlooknum();
        //获取常用语
        loadUsualLanguage();
        //获取礼品list
        loadGiftList();
        //我的购物车
        loadMyCart();
        //收到的礼品展示
        loadRecevieGift();

    }

    private void loadRecevieGift() {
        Map<String, String> request = new HashMap<>();
        request.put("token", SharedPreferencedUtils.getStr(Constants.USER_TOKEN));
        request.put("usernum", SharedPreferencedUtils.getStr(Constants.USER_NUM));
        request.put("userid", SharedPreferencedUtils.getStr(Constants.USER_ID));
        request.put("moduleid", blessid);
        request.put("modelename", Constants.CART_MODEL_BLESS);

        request.put("pageIndex", "1");
        request.put("pageSize", "10000");

        mPresenter.happyDetailsGiftList(GsonUtil.obj2JSON(request));

    }


    private void loadMyCart() {
        Map<String, String> request = new HashMap<>();
        request.put("token", SharedPreferencedUtils.getStr(Constants.USER_TOKEN));
        request.put("usernum", SharedPreferencedUtils.getStr(Constants.USER_NUM));
        request.put("userid", SharedPreferencedUtils.getStr(Constants.USER_ID));
        request.put("type", Constants.CART_TEAM_BLESS);

        mPresenter.happyCartList(GsonUtil.obj2JSON(request));
    }

    private void loadGiftList() {
        Map<String, String> request = new HashMap<>();
        request.put("token", SharedPreferencedUtils.getStr(Constants.USER_TOKEN));
        request.put("usernum", SharedPreferencedUtils.getStr(Constants.USER_NUM));

        mPresenter.happyGiftList(GsonUtil.obj2JSON(request));
    }

    private void loadUsualLanguage() {
        Map<String, String> request = new HashMap<>();
        request.put("token", SharedPreferencedUtils.getStr(Constants.USER_TOKEN));
        request.put("usernum", SharedPreferencedUtils.getStr(Constants.USER_NUM));


        mPresenter.happyUsualLanguage(GsonUtil.obj2JSON(request));
    }

    public void dialogAddCart(BlessCartListBean.ShopcartlistBean item) {

        Map<String, String> request = new HashMap<>();
        request.put("token", SharedPreferencedUtils.getStr(Constants.USER_TOKEN));
        request.put("usernum", SharedPreferencedUtils.getStr(Constants.USER_NUM));
        request.put("userid", SharedPreferencedUtils.getStr(Constants.USER_ID));

        request.put("type", Constants.CART_TEAM_BLESS);
        request.put("cateid", item.getCateid() + "");
        request.put("goodsid", item.getGoodsid() + "");
        request.put("goodsname", item.getGoodsname() + "");
        request.put("imgurl", item.getImgurl() + "");
        request.put("price", item.getPrice() + "");
        request.put("count", item.getCount() + "");

        mPresenter.happyCartAdd(GsonUtil.obj2JSON(request));
    }

    public void dialogUpdateCart(BlessCartListBean.ShopcartlistBean item) {

        Map<String, String> request = new HashMap<>();
        request.put("token", SharedPreferencedUtils.getStr(Constants.USER_TOKEN));
        request.put("usernum", SharedPreferencedUtils.getStr(Constants.USER_NUM));
        request.put("userid", SharedPreferencedUtils.getStr(Constants.USER_ID));

        request.put("type", Constants.CART_TEAM_BLESS);
        request.put("goodsid", item.getGoodsid() + "");
        request.put("count", item.getCount() + "");

        mPresenter.happyCartUpdate(GsonUtil.obj2JSON(request));
    }


    public void dialogDelCart(BlessCartListBean.ShopcartlistBean item) {

        Map<String, String> request = new HashMap<>();
        request.put("token", SharedPreferencedUtils.getStr(Constants.USER_TOKEN));
        request.put("usernum", SharedPreferencedUtils.getStr(Constants.USER_NUM));
        request.put("userid", SharedPreferencedUtils.getStr(Constants.USER_ID));

        request.put("type", Constants.CART_TEAM_BLESS);
        request.put("goodsid", item.getGoodsid() + "");

        mPresenter.happyCartDel(GsonUtil.obj2JSON(request));
    }


    public void dialogGenerateOrder(String carstr, double tp, String detail) {

        totalPrice = tp;
        goodsDetail = detail;

        loadingDialog.show();
        loadingDialog.setLabel(Constants.CART_GENERATE_ORDER_TXT);

        Map<String, String> request = new HashMap<>();

        request.put("token", SharedPreferencedUtils.getStr(Constants.USER_TOKEN));
        request.put("usernum", SharedPreferencedUtils.getStr(Constants.USER_NUM));
        request.put("buyuserid", SharedPreferencedUtils.getStr(Constants.USER_ID));
        request.put("moduleid", blessid);
        request.put("modelename", "xishi");
        request.put("receiveuserid", userid);
        request.put("shopcartstr", carstr);
        request.put("type", Constants.CART_TEAM_BLESS);

        mPresenter.happyOrderAdd(GsonUtil.obj2JSON(request));
    }


    private void initViewListener() {


        commentItemListener = new CommentItemListener();
        commentReplayListener = new CommentReplayListener();

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
//                closeCommentInput();
            }

        });
        initEmotionMainFragment();

    }

    @Override
    protected void onStart() {
        super.onStart();
        initEmotionViewAndListener();

    }
    private String reluserid;

    private void initEmotionViewAndListener() {
        if (mInputView == null) {
            mInputView = getSupportFragmentManager().findFragmentById(R.id.fl_pannel).getView();
            mBtnSend = (Button) mInputView.findViewById(R.id.bar_btn_send);
            mIvExtend = (ImageView) mInputView.findViewById(R.id.bar_iv_extend);
            mEditText = (EditText) mInputView.findViewById(R.id.bar_edit_text);
            mEditText.addTextChangedListener(new EditTextChangeListener());//文本变化监听器

            mIvExtend.setVisibility(View.GONE);

            //只为获取高度 软键盘
//            mEditText.postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    mEditText.setFocusable(true);
//                    mEditText.setFocusableInTouchMode(true);
//                    mEditText.requestFocus();
//                    mInputMethodManager.showSoftInput(mEditText,InputMethodManager.SHOW_FORCED);
//
//                    mInputMethodManager.hideSoftInputFromWindow(mEditText.getWindowToken(), 0);
// 强制隐藏键盘
//                }
//            },200);
            mEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    //暂时不需要 因为QQ 也没有在输入框获取焦点的时候 进行滚动
//                    if (hasFocus) {
//                       recyclerSmoothScrollToBottom();
//                    }
                }
            });
            mBtnSend.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Editable content = mEditText.getText();
                    if (TextUtils.isEmpty(content)) {
                        SAToast.makeText(BlessingDetailActivity.this,"请输入评论内容", Toast.LENGTH_SHORT).show();
                    } else {

                        Map<String, String> request = new HashMap<>();
                        request.put("token", SharedPreferencedUtils.getStr(Constants.USER_TOKEN));
                        request.put("usernum", SharedPreferencedUtils.getStr(Constants.USER_NUM));
                        request.put("userid", SharedPreferencedUtils.getStr(Constants.USER_ID));

                        String happyid;
//                        switch (whoAdapter) {
//                            case 0:
////                                happyid=adapter.getItem(operatorPosition).getId();
//                                break;
//                            case 1:
////                                happyid = commentAdapter.getItem(operatorPosition).getHappyid();
//                                break;
//                            case 2:
//                                happyid = commentReplayAdapter.getItem(operatorPosition)
//                                        .getHappyid();
//                                break;
//                        }

                        happyid = blessid;
                        System.out.println("observer**************"+observer);
                        request.put("happyid", happyid + "");
                        request.put("comment", content.toString());
                        request.put("parentid", parentId);
                        request.put("fabulous", isComment + "");
                        request.put("touserid", touserid);
                        request.put("observer",observer);
                        request.put("answerer",answever);
                        request.put("reluserid", userid);

                        Logger.w(GsonUtil.obj2JSON(request));
                        mPresenter.happyComment(GsonUtil.obj2JSON(request));

                    }

                }
            });

            TextView tv_extend_picture = (TextView) mInputView.findViewById(R.id
                    .tv_extend_picture);//照片
            TextView tv_extend_camera = (TextView) mInputView.findViewById(R.id.tv_extend_camera)
                    ;//拍照

            tv_extend_picture.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    openAlbum();

                    SAToast.makeText(BlessingDetailActivity.this,"相册", Toast.LENGTH_SHORT).show();

                }
            });

            tv_extend_camera.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SAToast.makeText(BlessingDetailActivity.this,"照相", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    /**
     * 初始化adapter
     */
    private void initAdapter() {
        if (commentAdapter == null) {
            commentAdapter = new BlessCommentAdapter(R.layout.item_comment_bless, this,userid);
            commentAdapter.setOnItemClickListener(commentItemListener);
            commentAdapter.setOnItemChildClickListener(commentItemListener);
            commentAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN); //切换动画
            commentAdapter.setEnableLoadMore(false);
            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(commentAdapter);
        }

        List<BlessListBean.ListBean.CommentsBean> comments = listBean.getShmlist();
        if (comments != null && comments.size() > 0) {
            commentAdapter.addData(comments);
        }

        if (detailGiftAdapter == null) {
            detailGiftAdapter = new BlessDetailGiftAdapter(R.layout.item_bless_detail_gift);
            detailGiftAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN); //切换动画
            detailGiftAdapter.setEnableLoadMore(false);
            ReceiveGiftClickListener listener=new ReceiveGiftClickListener();
            detailGiftAdapter.setOnItemClickListener(listener);
            detailGiftAdapter.setOnItemChildClickListener(listener);

//            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
//            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.HORIZONTAL);
            recycler_gift.setLayoutManager(layoutManager);
            recycler_gift.setAdapter(detailGiftAdapter);
        }
    }

    private void closeCommentInput() {

        if (fl_pannel != null) {
            fl_pannel.setVisibility(View.GONE);
        }
        ll_my_parent.setVisibility(View.VISIBLE);
        emotionMainFragment.hideEmotionLayoutoOrExtenLayout();//隐藏表情面板
        inputMethodManager.hideSoftInputFromWindow(mEditText.getWindowToken(), 0);
    }

    /**
     * @param adapter    操作的adapter
     * @param whoAdapter 哪一个adapter 0分享-1评论-2回复
     * @param position   操作的位置
     * @param isComment  是否是评论 0评论 1回复
     */
    private void openCommentInput(BaseQuickAdapter adapter, int whoAdapter, int position, int
            isComment) {

        ll_my_parent.setVisibility(View.GONE);
        this.whoAdapter = whoAdapter;
        switch (whoAdapter) {
            case 0://分享的tiem
                Logger.w("分享的tiem:" + position);
//                this.adapter = (FunsharingListAdapter) adapter;
//                share();
                break;
            case 1://评论的item
                Logger.w("评论的item:" + position);
                commentAdapter = (BlessCommentAdapter) adapter;
                BlessListBean.ListBean.CommentsBean item = commentAdapter.getItem(position);
                parentId = item.getId() + "";
                touserid = item.getUserid() + "";

                observer =  SharedPreferencedUtils.getStr(Constants.USER_NICK_NAME);
                answever = item.getAnswerer()+"";
                break;
            case 2://回复的item
                Logger.w("回复的item:" + position);
                commentReplayAdapter = (BlessCommentReplayAdapter) adapter;
                BlessListBean.ListBean.CommentsBean.Comments1Bean item1 = commentReplayAdapter
                        .getItem(position);
                parentId = item1.getParentid() + "";
                touserid = item1.getUserid() + "";

                observer = item1.getObserver();
                answever = item1.getAnswerer()+"";
                break;
        }
        operatorPosition = position;
        this.isComment = isComment;

        fl_pannel.setVisibility(View.VISIBLE);
        emotionMainFragment.hideEmotionLayoutoAndExtenLayout();//隐藏表情面板

        mEditText.requestFocus();
        inputMethodManager.showSoftInput(mEditText, 0);
    }
    /**
     * 新增浏览量
     */
    private void addlooknum() {
        Map<String, String> request = new HashMap<>();
        request.put("token", SharedPreferencedUtils.getStr(Constants.USER_TOKEN));
        request.put("usernum", SharedPreferencedUtils.getStr(Constants.USER_NUM));
        request.put("id", blessid);
        request.put("looknum", viewCount + "");

        mPresenter.happyViewCount(GsonUtil.obj2JSON(request));
    }


    @OnClick({R.id.iv_header, R.id.tv_comment_num, R.id.tv_like_num, R.id.tv_send_gift, R.id
            .tv_go_bless, R.id.tv_comment_input,R.id.tv_share})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.iv_header://头像
                break;
            case R.id.tv_share:
                share(blessid);
                break;
            case R.id.tv_comment_num://评论
            case R.id.tv_comment_input:
                isCommenting = false;
                openAddBlessDialog();
                observer= SharedPreferencedUtils.getStr(Constants.USER_NICK_NAME);
                parentId =   bundle.getString("userid");
//                isCommenting = true;
//                openCommentInput(commentAdapter, 0, -1, 0);
                break;
            case R.id.tv_like_num://点赞
                addLikeNum();
                break;
            case R.id.tv_send_gift://我要送礼 收到的礼品
                if (tv_send_gift.getText().toString().equals("我要送礼")) {
                openSendGiftDialog();
                } else {
                    bundle.putString("blessid", blessid);
                    ActivityUtil.getInstance().openActivity(BlessingDetailActivity.this,
                            BlessReceiveBlessGiftActivity.class, bundle);
                }
                break;
            case R.id.tv_go_bless://我要祝福 收到的祝福

                if (tv_go_bless.getText().toString().equals("我要祝福")) {
                    isCommenting = false;
                    openAddBlessDialog();
                } else {
                    bundle.putString("blessid", blessid);
                    ActivityUtil.getInstance().openActivity(BlessingDetailActivity.this,
                            BlessReceiveBlessYuActivity.class, bundle);

                }

                break;
        }
    }


    private void openAddBlessDialog() {

        blessYuDialog = new BlessYUDialog(this, null, usualLanguageBean);
        blessYuDialog.show();
    }

    private void openSendGiftDialog() {
        blessGiftDialog = new BlessGiftDialog(this, null, giftBean, cartListBean);
        blessGiftDialog.show();
    }

    /**
     * 点赞
     */
    private void addLikeNum() {
        Map<String, String> request = new HashMap<>();
        request.put("token", SharedPreferencedUtils.getStr(Constants.USER_TOKEN));
        request.put("usernum", SharedPreferencedUtils.getStr(Constants.USER_NUM));
        request.put("userid", SharedPreferencedUtils.getStr(Constants.USER_ID));
        request.put("id", blessid);

        request.put("reluserid", userid);

        mPresenter.happyLike(GsonUtil.obj2JSON(request));
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void showContent(String msg) {

    }

    @Override
    public void happAdd(String action) {

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
    public void happyList(BlessListBean blessListBeanect) {

    }

    @Override
    public void happyComment(BlessCommentBean blessCommentBean) {

        int fabulous = Integer.parseInt(blessCommentBean.getFabulous());

        if (fabulous == 0) {//评论
            if (!isbless) {
                SAToast.makeText(BlessingDetailActivity.this,"评论成功", Toast.LENGTH_SHORT).show();
            }
            SAToast.makeText(BlessingDetailActivity.this,""+blessCommentBean.getFabulous()).show();
            BlessListBean.ListBean.CommentsBean commentItemBean = new BlessListBean.ListBean
                    .CommentsBean();

            commentItemBean.setId(blessCommentBean.getId());
            commentItemBean.setComment(blessCommentBean.getComment());
            commentItemBean.setCreatedtime(blessCommentBean.getCreatedtime());
            commentItemBean.setFabulous(blessCommentBean.getFabulous() + "");
            commentItemBean.setObserver(blessCommentBean.getObserver());
            commentItemBean.setParentid(blessCommentBean.getParentid());
            commentItemBean.setHappyid(blessCommentBean.getHappyid());
            commentItemBean.setAnswerer(blessCommentBean.getAnswerer());
            commentItemBean.setUserid(blessCommentBean.getUserid());
            commentItemBean.setStatus(blessCommentBean.getStatus());

            commentAdapter.addData(commentItemBean);

        } else if (fabulous == 1) {//回复
            if (whoAdapter == 1) {//一级回复

                BlessListBean.ListBean.CommentsBean item = commentAdapter
                        .getItem(operatorPosition);
                BlessListBean.ListBean.CommentsBean commentsBean = new BlessListBean.ListBean.CommentsBean();
                commentsBean.setId(blessCommentBean.getId());
                commentsBean.setComment(blessCommentBean.getComment());
                commentsBean.setCreatedtime(blessCommentBean.getCreatedtime());
                commentsBean.setFabulous(blessCommentBean.getFabulous() + "");
                commentsBean.setObserver(blessCommentBean.getObserver());
                commentsBean.setParentid(blessCommentBean.getParentid());
                commentsBean.setHappyid(blessCommentBean.getHappyid());
                commentsBean.setAnswerer(blessCommentBean.getAnswerer() == null ?
                        tonickname : blessCommentBean.getAnswerer());
                commentsBean.setUserid(blessCommentBean.getUserid());
                commentsBean.setStatus(blessCommentBean.getStatus());

                commentAdapter.addData(operatorPosition+1,commentsBean);
            } else if (whoAdapter == 2) { //二级回复
                BlessListBean.ListBean.CommentsBean.Comments1Bean comments1Bean = new
                        BlessListBean.ListBean.CommentsBean.Comments1Bean();

                comments1Bean.setId(blessCommentBean.getId());
                comments1Bean.setComment(blessCommentBean.getComment());
                comments1Bean.setCreatedtime(blessCommentBean.getCreatedtime());
                comments1Bean.setFabulous(blessCommentBean.getFabulous() + "");
                comments1Bean.setObserver(blessCommentBean.getObserver());
                comments1Bean.setParentid(blessCommentBean.getParentid());
                comments1Bean.setHappyid(blessCommentBean.getHappyid());
                comments1Bean.setAnswerer(blessCommentBean.getAnswerer() == null ?
                        tonickname : blessCommentBean.getAnswerer());
                comments1Bean.setUserid(blessCommentBean.getUserid());
                comments1Bean.setStatus(blessCommentBean.getStatus());

                commentReplayAdapter.addData(comments1Bean);
            }
        }
        //重置
        touserid = "";
        parentId = "";
        operatorPosition = -1;
        reluserid = "";
        mEditText.setText("");

        closeCommentInput();
        //显示结果
    }

    @Override
    public void happyCommentDel(String action) {

    }

    @Override
    public void happyLike(BlessAddLikeBean blessAddLikeBean) {

        int oldNum = Integer.parseInt(tv_like_num.getText().toString());
        int newNUm = blessAddLikeBean.getSl().size();

        if (oldNum > newNUm) {//取消
            Drawable rightDrawable = getResources().getDrawable(R.mipmap.icon_like_gray);
            rightDrawable.setBounds(0, 0, rightDrawable.getMinimumWidth(), rightDrawable
                    .getMinimumHeight());
            tv_like_num.setCompoundDrawables(rightDrawable, null, null, null);

        } else {//点赞
            Drawable rightDrawable = getResources().getDrawable(R.mipmap.icon_like);
            rightDrawable.setBounds(0, 0, rightDrawable.getMinimumWidth(), rightDrawable
                    .getMinimumHeight());
            tv_like_num.setCompoundDrawables(rightDrawable, null, null, null);
        }
        tv_like_num.setText(newNUm + "");
        //tv_like_num.setText("");
        if (blessAddLikeBean.getS2() != null && blessAddLikeBean.getS2().size() > 0) {

            fbl_parent.removeAllViews();

            TextView likeICONTxt = new TextView(App.getInstance());
            Drawable rightDrawable = getResources().getDrawable(R.mipmap.bless_home_zan);
            rightDrawable.setBounds(0, 0, rightDrawable.getMinimumWidth(), rightDrawable
                    .getMinimumHeight());
            likeICONTxt.setTextSize(14);
            likeICONTxt.setCompoundDrawables(rightDrawable, null, null, null);
            likeICONTxt.setText("、");
            likeICONTxt.setCompoundDrawablePadding(10);
            likeICONTxt.setTextColor(App.getInstance().getResources().getColor(R.color.colorNav));
            fbl_parent.addView(likeICONTxt);
            int index = 0;
            for (String fab : blessAddLikeBean.getS2()) {
                TextView textView = new TextView(App.getInstance());
                index = index + 1;
                if (index < blessAddLikeBean.getS2().size()) {
                    textView.setText(fab + "、");
                } else {
                    textView.setText(fab);
                }


                textView.setTextSize(14);
                textView.setTextColor(App.getInstance().getResources().getColor(R.color.colorNav));
                textView.setPadding(5, 5, 5, 5);
                fbl_parent.addView(textView);
            }

        } else {
            fbl_parent.removeAllViews();
        }
    }

    @Override
    public void happyBlessAdd(BlessAddBean blessAddBean) {
        if (isbless) {
            SAToast.makeText(BlessingDetailActivity.this,"祝福成功", Toast.LENGTH_SHORT).show();
        }
        isbless = false;

//        BlessListBean.ListBean.CommentsBean commentsBean = new BlessListBean.ListBean
//                .CommentsBean();
//        BlessAddBean.ListBean item = blessAddBean.getList().get(0);
//        commentsBean.setComment(item.getWishes());
//        commentsBean.setCreatedtime(item.getWishtime());
//        commentsBean.setHappyid(item.getHappyid());
//        commentsBean.setId(item.getWishid());
//        commentsBean.setNickname(SharedPreferencedUtils.getStr(Constants.USER_NICK_NAME));
//        commentsBean.setParentid(item.getHappyid() + "");
//        commentsBean.setParentname("");
//        commentsBean.setUserid(item.getUserid());
//
//        commentAdapter.addData(commentsBean);
        blessYuDialog.dismiss();

    }

    @Override
    public void happyBlessReceiveList(BlessReceiveYuBean blessReceiveYuBean) {

    }

    @Override
    public void happyUsualLanguage(HappyBlessUsualLanguageBean blessUsualLanguageBeanb) {

        usualLanguageBean = blessUsualLanguageBeanb;
    }


    @Override
    public void happyGiftList(BlessGiftBean blessGiftBean) {

        giftBean = blessGiftBean;
    }

    @Override
    public void happyGiftReceiveListMy(BlessReceiveGiftBean blessGiftReceivedBean) {

    }

    @Override
    public void happyGiftSendListMy(BlessSendGiftBean blessSendGiftBean) {

    }

    @Override
    public void happyOrderAdd(String blessOrderBean) {
        loadingDialog.dismiss();

        bundle.putString(Constants.PAY_MODULE_NAME, Constants.CART_MODEL_BLESS);
        bundle.putInt(Constants.PAY_FROM_ACTIVITY, Constants.PAY_FROM_HAPPY_GIFT);
        bundle.putString(Constants.PAY_ORDERNUM, blessOrderBean);
        bundle.putString(Constants.PAY_TOTALPRICE, totalPrice + "");
        bundle.putString(Constants.PAY_GOODS_DESC, "支付礼品费用");
        bundle.putString(Constants.PAY_GOODS_DETAIL, goodsDetail);

        ActivityUtil.getInstance().openActivity(BlessingDetailActivity.this, PayInfoActivity
                .class, bundle);

    }

    @Override
    public void happyCartAdd(String message) {
        SAToast.makeText(BlessingDetailActivity.this,"添加购物车成功", Toast.LENGTH_SHORT).show();
    }


    @Override
    public void happyCartList(BlessCartListBean blessCartListBean) {
        cartListBean = blessCartListBean;
    }

    @Override
    public void happyCartUpdate(CartUpdateBean message) {
        Logger.e("更新成功购物车");
    }


    @Override
    public void happyCartDel(String message) {

    }

    @Override
    public void happySearch(BlessListBean blessListBean) {

    }

    @Override
    public void happyDetailsGiftList(BlessDetailGiftListBean detailGiftListBean) {
        detailGiftListBean1 = detailGiftListBean;
        detailGiftAdapter.addData(detailGiftListBean.getList());
    }

    @Override
    public void uploadHeader(FileUploadBean fileUploadBean) {

    }

    @Override
    public void alipay(AliPayBean action) {

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

    boolean isbless = false;

    @Subscribe(tag = RxBus.TAG_UPDATE, thread = EventThread.MAIN_THREAD)
    public void rxrefresh(String msg) {

        if (msg.equals("refresh")) {//购买买礼物成功
            detailGiftAdapter.getData().clear();
            //收到的礼品展示
            loadRecevieGift();

            blessGiftDialog.dismiss();

        } else if (!TextUtils.isEmpty(msg)) {
            //添加祝福语
            isbless = true;
            blessYuDialog.dismiss();
            Map<String, String> request = new HashMap<>();
            request.put("token", SharedPreferencedUtils.getStr(Constants.USER_TOKEN));
            request.put("usernum", SharedPreferencedUtils.getStr(Constants.USER_NUM));
            request.put("userid", SharedPreferencedUtils.getStr(Constants.USER_ID));
            request.put("observer",observer);
            request.put("answerer",answever);
            request.put("happyid", blessid);
            request.put("receiveuserid", userid);

            request.put("reluserid", userid);

            request.put("wishes", msg);

            Logger.w(GsonUtil.obj2JSON(request));
            mPresenter.happyBlessAdd(GsonUtil.obj2JSON(request));


            //********************
            //添加评论
         /*   Map<String, String> request1 = new HashMap<>();
            request1.put("token", SharedPreferencedUtils.getStr(Constants.USER_TOKEN));
            request1.put("usernum", SharedPreferencedUtils.getStr(Constants.USER_NUM));
            request1.put("userid", SharedPreferencedUtils.getStr(Constants.USER_ID));

            String happyid;

            happyid = blessid;
            request1.put("happyid", happyid + "");
            request1.put("comment", msg);
            request1.put("parentid", happyid);//添加评论
            request.put("observer",observer);
            request.put("answerer",answever);
            request1.put("fabulous", isComment + "");
            request1.put("touserid", touserid);
            request1.put("reluserid", userid);

            Logger.w(GsonUtil.obj2JSON(request1));
            mPresenter.happyComment(GsonUtil.obj2JSON(request1));*/

            dialogCommentAdd(msg);
        }
    }

    public void dialogCommentAdd(String msg) {

        Map<String, String> request = new HashMap<>();
        request.put("token", SharedPreferencedUtils.getStr(Constants.USER_TOKEN));
        request.put("usernum", SharedPreferencedUtils.getStr(Constants.USER_NUM));
        request.put("userid", SharedPreferencedUtils.getStr(Constants.USER_ID));

        request.put("happyid", blessid + "");
        request.put("comment", msg);
        request.put("parentid", blessid);
        request.put("observer",observer);
        request.put("answerer",answever);
        request.put("fabulous", "0");
        request.put("reluserid", userid);
        request.put("touserid", touserid);

        mPresenter.happyComment(GsonUtil.obj2JSON(request));

    }

    // 评论与回复的点击事件 start
    private CommentItemListener commentItemListener;
    private CommentReplayListener commentReplayListener;

    public CommentItemListener getCommentItemListener() {
        return commentItemListener;
    }

    public void setCommentItemListener(CommentItemListener commentItemListener) {
        this.commentItemListener = commentItemListener;
    }

    public CommentReplayListener getCommentReplayListener() {
        return commentReplayListener;
    }

    public void setCommentReplayListener(CommentReplayListener commentReplayListener) {
        this.commentReplayListener = commentReplayListener;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.son_tv:
                    openSendGiftDialog();
                break;
            case R.id.song2_iv:
                    openSendGiftDialog();
                break;
        }
    }
    // 评论与回复的点击事件 end


    /**
     * 评论item的点击事件
     */
    public class CommentItemListener implements BaseQuickAdapter.OnItemClickListener
            , BaseQuickAdapter.OnItemChildClickListener {

        @Override
        public void onItemChildClick(BaseQuickAdapter adapter, View view, final int position) {

            commentAdapter = (BlessCommentAdapter) adapter;
            final BlessListBean.ListBean.CommentsBean item = commentAdapter.getItem(position);
            tonickname = item.getObserver();
            int local_userid = Integer.parseInt(SharedPreferencedUtils.getStr(Constants.USER_ID));
            if (local_userid == item.getUserid()) {//点击的是自己回复的
                switch (view.getId()) {
                    case R.id.tv_nickname:
                        break;
                    case R.id.tv_comment_content:
                        View popView = View.inflate(BlessingDetailActivity.this, R.layout
                                .item_pop_del, null);
                        final CustomPopWindow popWindow = new CustomPopWindow.PopupWindowBuilder
                                (BlessingDetailActivity.this)
                                .enableBackgroundDark(true) //弹出popWindow时，背景是否变暗
                                .setBgDarkAlpha(0.7f) // 控制亮度
                                .setOutsideTouchable(true)//是否PopupWindow 以外触摸dissmiss
                                .setView(popView)
                                .create();
                        popWindow.showAsDropDown(view, 0, -(view.getHeight() + popWindow
                                .getHeight()));

                        TextView tv_del = popView.findViewById(R.id.tv_del);
                        TextView tv_cancel = popView.findViewById(R.id.tv_cancel);

                        tv_del.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Map<String, Object> request = new HashMap<>();
                                request.put("token", SharedPreferencedUtils.getStr(Constants
                                        .USER_TOKEN));
                                request.put("usernum", SharedPreferencedUtils.getStr(Constants
                                        .USER_NUM));
                                request.put("id", item.getId());
                                request.put("fabulous", item.getFabulous());

                                mPresenter.happyCommentDel(GsonUtil.obj2JSON(request));
                                commentAdapter.remove(position);
                                popWindow.dissmiss();
                            }
                        });

                        tv_cancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                popWindow.dissmiss();
                            }
                        });
                        break;
                }

            } else {//点击的是别人回复的--一级回复
                isCommenting = true;
                mEditText.setHint("回复:" + item.getObserver());
                touserid = item.getUserid() + "";
                parentId = item.getId() + "";
                openCommentInput(commentAdapter, 1, position, 1);


            }

        }

        @Override
        public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        }
    }


    /**
     * 回复item 的点击事件
     */
    public class CommentReplayListener implements BaseQuickAdapter.OnItemClickListener
            , BaseQuickAdapter.OnItemChildClickListener {

        @Override
        public void onItemChildClick(BaseQuickAdapter adapter, View view, final int position) {


            commentReplayAdapter = (BlessCommentReplayAdapter) adapter;
            final BlessListBean.ListBean.CommentsBean.Comments1Bean item = commentReplayAdapter
                    .getItem(position);
            tonickname = item.getObserver();
            int local_userid = Integer.parseInt(SharedPreferencedUtils.getStr(Constants.USER_ID));
            if (local_userid == item.getUserid()) {//点击的是自己回复的
                switch (view.getId()) {
                    case R.id.tv_from_username:
                        break;
                    case R.id.tv_to_username:
                        break;
                    case R.id.tv_replay:
                        View popView = View.inflate(BlessingDetailActivity.this, R.layout
                                .item_pop_del, null);
                        final CustomPopWindow popWindow = new CustomPopWindow.PopupWindowBuilder
                                (BlessingDetailActivity.this)
                                .enableBackgroundDark(true) //弹出popWindow时，背景是否变暗
                                .setBgDarkAlpha(0.7f) // 控制亮度
                                .setOutsideTouchable(true)//是否PopupWindow 以外触摸dissmiss
                                .setView(popView)
                                .create();
                        popWindow.showAsDropDown(view, 0, -(view.getHeight() + popWindow
                                .getHeight()));

                        TextView tv_del = popView.findViewById(R.id.tv_del);
                        TextView tv_cancel = popView.findViewById(R.id.tv_cancel);

                        tv_del.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Map<String, Object> request = new HashMap<>();
                                request.put("token", SharedPreferencedUtils.getStr(Constants
                                        .USER_TOKEN));
                                request.put("usernum", SharedPreferencedUtils.getStr(Constants
                                        .USER_NUM));
                                request.put("id", item.getId());
                                request.put("fabulous", item.getFabulous());

                                mPresenter.happyCommentDel(GsonUtil.obj2JSON(request));

                                commentReplayAdapter.remove(position);
                                popWindow.dissmiss();
                            }
                        });

                        tv_cancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                popWindow.dissmiss();
                            }
                        });
                        break;
                }

            } else {//点击的是别人回复的  二级回复
                isCommenting = true;
                mEditText.setHint("回复:" + item.getObserver());
                touserid = item.getUserid() + "";
                parentId = item.getParentid() + "";
                openCommentInput(commentReplayAdapter, 2, position, 1);

            }
        }

        @Override
        public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        RxBus.getInstance().post(RxBus.getInstance().getTag(BlessingHomeActivity.class,
                RxBus.TAG_UPDATE), "close");
    }

    /**
     * 关`闭软键盘
     */
    private void closeSoftInput() {

        if (fl_pannel != null) {
            fl_pannel.setVisibility(View.GONE);
        }

        inputMethodManager.hideSoftInputFromWindow(mEditText.getWindowToken(), 0);

//        mEditText.clearFocus();
//        mInputMethodManager.hideSoftInputFromWindow(mEditText.getWindowToken(), 0);

    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
          /* 判断是否拦截返回键操作
                */
        if (!emotionMainFragment.isInterceptBackPress()) {
            super.onBackPressed();
        }
    }

    /**
     * 初始化表情面板
     */
    public void initEmotionMainFragment() {
        //构建传递参数
        Bundle bundle = new Bundle();
        //绑定主内容编辑框
        bundle.putBoolean(EmotionMainFragment.BIND_TO_EDITTEXT, true);
        //隐藏控件
        bundle.putBoolean(EmotionMainFragment.HIDE_BAR_EDITTEXT_AND_BTN, false);
        //替换fragment   //创建修改实例
        emotionMainFragment = EmotionMainFragment.newInstance(EmotionMainFragment.class, bundle);
        emotionMainFragment.bindToContentView(mLinearLayout);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fl_pannel, emotionMainFragment);
        transaction.addToBackStack(null);
        //提交修改
        transaction.commit();

    }

    /**
     * 输入框内容变化监听器
     */
    class EditTextChangeListener implements TextWatcher {
        /**
         * 编辑框内容发生改变之前的会回调
         *
         * @param s
         * @param start
         * @param count
         * @param after
         */
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        /**
         * 编辑框的内容正在发生改变时的回调方法 >>用户正在输入
         * 我们可以在这里实时地 通过搜索匹配用户的输入
         *
         * @param charSequence
         * @param start
         * @param before
         * @param count
         */
        @Override
        public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
            if (TextUtils.isEmpty(charSequence.toString())) {
                mBtnSend.setVisibility(View.GONE);
                // TODO: 2018-02-02 隐藏扩展菜单
                mIvExtend.setVisibility(View.GONE);

            } else {
                mBtnSend.setVisibility(View.VISIBLE);
                mIvExtend.setVisibility(View.GONE);

            }
        }

        /**
         * 编辑框的内容改变以后,用户没有继续输入时 的回调方法
         *
         * @param s
         */
        @Override
        public void afterTextChanged(Editable s) {
        }


    }

    private void share(String id) {
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

            web.setTitle(username+"的喜事");//标题
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

            SAToast.makeText(BlessingDetailActivity.this,"分享成功", Toast.LENGTH_SHORT).show();
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
            SAToast.makeText(BlessingDetailActivity.this,"分享失败", Toast.LENGTH_SHORT).show();
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

    class ReceiveGiftClickListener implements   BaseQuickAdapter.OnItemClickListener
            , BaseQuickAdapter.OnItemChildClickListener{

        @Override
        public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
            bundle.putString("receiveGift",GsonUtil.obj2JSON(detailGiftListBean1));
            ActivityUtil.getInstance().openActivity(BlessingDetailActivity.this,BlessReceiveGiftActivity.class,bundle);
        }

        @Override
        public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
            bundle.putString("receiveGift",GsonUtil.obj2JSON(detailGiftListBean1));
            ActivityUtil.getInstance().openActivity(BlessingDetailActivity.this,BlessReceiveGiftActivity.class,bundle);        }
    }
}
