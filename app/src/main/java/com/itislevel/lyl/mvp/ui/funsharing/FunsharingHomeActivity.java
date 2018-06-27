package com.itislevel.lyl.mvp.ui.funsharing;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.zhouwei.library.CustomPopWindow;
import com.itislevel.lyl.R;
import com.itislevel.lyl.adapter.FunsharingCommentAdapter;
import com.itislevel.lyl.adapter.FunsharingCommentReplayAdapter;
import com.itislevel.lyl.adapter.FunsharingListAdapter;
import com.itislevel.lyl.app.Constants;
import com.itislevel.lyl.base.RootActivity;
import com.itislevel.lyl.mvp.model.bean.FileUploadBean;
import com.itislevel.lyl.mvp.model.bean.FunsharingAddBean;
import com.itislevel.lyl.mvp.model.bean.FunsharingCommnetAddBean;
import com.itislevel.lyl.mvp.model.bean.FunsharingLikeBean;
import com.itislevel.lyl.mvp.model.bean.FunsharingListBean;
import com.itislevel.lyl.mvp.model.bean.FunsharingMyBean;
import com.itislevel.lyl.mvp.model.bean.FunshingCommentItemBean;
import com.itislevel.lyl.mvp.model.bean.FunshingItemBean;
import com.itislevel.lyl.mvp.model.bean.FunshingReplayItemBean;
import com.itislevel.lyl.mvp.model.bean.MeiZiBean;
import com.itislevel.lyl.mvp.model.bean.MeiZiMultipleBean;
import com.itislevel.lyl.mvp.model.bus.HomeAdapaterSucc;
import com.itislevel.lyl.mvp.model.http.api.LYLApi;
import com.itislevel.lyl.mvp.ui.address.ProvinceActivity;
import com.itislevel.lyl.mvp.ui.chatkeyboardview.EmotionMainFragment;
import com.itislevel.lyl.mvp.ui.location.Location_CityPickerActivity;
import com.itislevel.lyl.mvp.ui.user.LoginActivity;
import com.itislevel.lyl.net.LoaderStyle;
import com.itislevel.lyl.net.RestClent;
import com.itislevel.lyl.net.callback.IFailure;
import com.itislevel.lyl.net.callback.ISuccess;
import com.itislevel.lyl.utils.ActivityUtil;
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
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

import static com.itislevel.lyl.mvp.ui.main.home.HomeFragment.CITY_ID;
import static com.itislevel.lyl.mvp.ui.main.home.HomeFragment.CITY_NAME;
import static com.itislevel.lyl.mvp.ui.main.home.HomeFragment.P_ID;
import static com.itislevel.lyl.mvp.ui.main.home.HomeFragment.P_NAME;

@UseRxBus
public class FunsharingHomeActivity extends RootActivity<FunsharingPresenter> implements
        FunsharingContract.View
        , View.OnClickListener
        , BaseQuickAdapter.OnItemClickListener
        , BaseQuickAdapter.OnItemChildClickListener,SwipeRefreshLayout.OnRefreshListener,BaseQuickAdapter.RequestLoadMoreListener {

    @BindView(R.id.refreshLayout)
    SwipeRefreshLayout refreshLayout;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    //是否已添加没有更多数据的布局
    private boolean isAddNoMoreView = false;
    private int pageIndex = 1;//分页加载时 当前页是第几页
    private int totalPage = 0;//总页数
    private boolean isRefreshing = true;//当前是刷新还是加载
    private FunsharingListAdapter adapter;


    private int isComment = 0;//评论传0，回复传1

    private TextView operatorView = null;
    private int operatorPosition = -1;

    private Dialog mDialog = null;
    private InputMethodManager inputMethodManager;

    private String touserid = "";//回复时  回复对象的用户id
    private String parentId = ""; //回复时的父id

    FunsharingCommentAdapter commentAdapter;//评论的adapter
    FunsharingCommentReplayAdapter commentReplayAdapter;//回复的adapter

    private int whoAdapter = 0; // 哪一个adapter 0分享-1评论-2回复

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

    private String huifu_share_id ="0";
    private int load_more =0;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_funsharing_home;
    }

    @Override
    protected void initEventAndData() {
        inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        setToolbarTvTitle("乐趣分享");
        setToolbarMoreTxt("我的分享");
        setToolbarMoreClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog();
            }
        });
        initRefreshLayout_recyclview();
        initAdapter();
        refreshLayout.setOnRefreshListener(this);
        refreshLayout.setRefreshing(true);//刷新效果
        // 输入框
        mInputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        initViewListener();
        loadData();
//        initGalleryListener();
    }
    private void initRefreshLayout_recyclview(){//设置刷新的颜色和款式及recyclevew
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        //注册成为订阅者
        refreshLayout.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_orange_dark,
                android.R.color.holo_red_dark);
        refreshLayout.setProgressViewOffset(true,0,200);
    }

    private void getDialog() {
        //设置全屏将会覆盖状态栏
        mDialog = new Dialog(FunsharingHomeActivity.this, R.style.Dialog_Fullscreen);
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

        View view = LayoutInflater.from(this).inflate(R.layout.dialog_fullscreen_funshare, null);

        //        iv_close tv_share tv_share_my
        ImageView iv_close = view.findViewById(R.id.iv_close);
        TextView tv_share = view.findViewById(R.id.tv_share);
        TextView tv_share_my = view.findViewById(R.id.tv_share_my);

        iv_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.dismiss();
            }
        });

        tv_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!CITY_ID.equals("0"))
                {
                mDialog.dismiss();
                Bundle bundle = new Bundle();
                bundle.putString(Constants.PROVINCE_TITLE, "乐趣分享");
                bundle.putString(Constants.CITY_TITLE, "乐趣分享");
                bundle.putString(Constants.COUNTY_TITLE, "乐趣分享");
                bundle.putBoolean(Constants.IS_SHOW_COUNTY, false);

                bundle.putString(Constants.PROVINCE_ID, P_ID);
                bundle.putString(Constants.PROVINCE_NAME, P_NAME);
                bundle.putString(Constants.CITY_ID, CITY_ID);
                bundle.putString(Constants.CITY_NAME, CITY_NAME);

                bundle.putInt(Constants.ACTIVITY_TARGET, Constants.ACTIVITY_FUNSHARING);

                ActivityUtil.getInstance().openActivity(FunsharingHomeActivity.this,
                        FunsharingAddActivity.class, bundle);

                }else {
                    SAToast.makeText(FunsharingHomeActivity.this,"请先选择所属地区").show();
                    Bundle bundle = new Bundle();
                    String title = "";
                    bundle.putString(Constants.START_FLAGE, "home");
                    bundle.putString(Constants.CITY_NAME, "定位失败");
                    bundle.putString(Constants.CITY_ID, "0");
                    ActivityUtil.getInstance().openActivity(FunsharingHomeActivity.this, Location_CityPickerActivity.class,bundle);

                }
            }
        });
        tv_share_my.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.dismiss();
                ActivityUtil.getInstance().openActivity(FunsharingHomeActivity.this,
                        FunsharingMyActivity.class);
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

    @SuppressLint("ClickableViewAccessibility")
    private void initViewListener() {
        commentItemListener = new CommentItemListener();
        commentReplayListener = new CommentReplayListener();

//        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
//                super.onScrollStateChanged(recyclerView, newState);
//                closeCommentInput();
//            }
//        });

        recyclerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (fl_pannel != null && fl_pannel.isShown()) {
                    closeCommentInput();
                    return true;

                }
                return false;

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

    private void initEmotionViewAndListener(){
        if (mInputView == null) {
            mInputView = getSupportFragmentManager().findFragmentById(R.id.fl_pannel).getView();
            mBtnSend = (Button) mInputView.findViewById(R.id.bar_btn_send);
            mIvExtend = (ImageView) mInputView.findViewById(R.id.bar_iv_extend);
            mEditText = (EditText) mInputView.findViewById(R.id.bar_edit_text);
            mEditText.addTextChangedListener(new EditTextChangeListener());//文本变化监听器

            mIvExtend.setVisibility(View.GONE);

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
//                    sendTxtMessage();
                    String name="";
                    Editable content = mEditText.getText();
                    if (TextUtils.isEmpty(content)) {
                        ToastUtil.Warning("内容不能为空");
                    } else {
                        Map<String, String> request = new HashMap<>();
                        request.put("token", SharedPreferencedUtils.getStr(Constants.USER_TOKEN));
                        request.put("usernum", SharedPreferencedUtils.getStr(Constants.USER_NUM));
                        request.put("userid", SharedPreferencedUtils.getStr(Constants.USER_ID));

                        int shareid = -1;
                        switch (whoAdapter) {
                            case 0:
                                shareid = adapter.getItem(operatorPosition).getId();
                                name =  SharedPreferencedUtils.getStr(Constants.USER_NICK_NAME);
                                break;
                            case 1:
                                shareid = commentAdapter.getItem(operatorPosition).getShareid();
                                name =  commentAdapter.getItem(operatorPosition).getObserver();
                                break;
                            case 2:
                                ///SAToast.makeText(FunsharingHomeActivity.this,"回复").show();
                                shareid = commentReplayAdapter.getItem(operatorPosition).getShareid();
                                name =  commentReplayAdapter.getItem(operatorPosition).getObserver();
                                break;
                        }
                        SAToast.makeText(FunsharingHomeActivity.this,"回复"+shareid).show();


                        // TODO: 2017/12/16 此处可能有坑
                        request.put("shareid", shareid + "");
                        request.put("comment", content.toString());
                        request.put("observer", name);
                        request.put("parentid", parentId);
                        request.put("answerer", SharedPreferencedUtils.getStr(Constants.USER_NICK_NAME));
                        request.put("fabulous", isComment + "");
                        request.put("touserid", touserid);
                        request.put("reluserid", reluserid);
                        mPresenter.commentShareAdd(GsonUtil.obj2JSON(request));
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
                    ToastUtil.Info("相册");
                }
            });

            tv_extend_camera.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ToastUtil.Info("照相");
                }
            });
        }
    }

    private void loadData() {
        Map<String, String> request = new HashMap<>();
        request.put("token", SharedPreferencedUtils.getStr(Constants.USER_TOKEN));
        request.put("usernum", SharedPreferencedUtils.getStr(Constants.USER_NUM));
        request.put("userid",  SharedPreferencedUtils.getStr(Constants.USER_ID));
        request.put("pageIndex", pageIndex + "");
        request.put("pageSize", Constants.PAGE_NUMBER10 + "");

        mPresenter.shareList(GsonUtil.obj2JSON(request));
    }

    /**
     * 初始化adapter
     */
    private void initAdapter() {
        if (adapter == null) {
            adapter = new FunsharingListAdapter(R.layout.item_funsharing, this);
            adapter.setOnItemClickListener(this);
            adapter.setOnItemChildClickListener(this);
//            adapter.openLoadAnimation();
            adapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN); //切换动画
            adapter.setEnableLoadMore(false);
//        adapter.isFirstOnly(false);//动画默认只执行一次
//        adapter.setNotDoAnimationCount(count);//设置不显示动画数量
            //setNotDoAnimationCount 可以设置第一屏不展示动画
            //打开或关闭加载 adapter.setEnableLoadMore(boolean);
            //预加载  当列表滑动到倒数第N个Item的时候(默认是1)回调onLoadMoreRequested方法  adapter.setPreLoadNumber(int);
            //设置自定义加载布局 adapter.setLoadMoreView(new CustomLoadMoreView());
            // 滑动最后一个Item的时候回调onLoadMoreRequested方法
            //默认第一次加载会进入回调，如果不需要可以配置： adapter.disableLoadMoreIfNotFullPage();
            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//            layoutManager.setStackFromEnd(true);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(adapter);
        }
    }

  /*  private void initRefreshListener() {
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                pageIndex = 1;
                isRefreshing = true;
                loadData();
            }
        });
        refreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                pageIndex++;
                if (pageIndex > totalPage) {
                    if (!isAddNoMoreView) {
                        isAddNoMoreView = true;
                        View view = getLayoutInflater().inflate(R.layout.partial_no_more_data,
                                null, false);
                        adapter.addFooterView(view);
                    }
                    ToastUtil.Info("没有更多啦~~");
                    refreshLayout.finishLoadmore(true);//
                    return;
                } else {
                    isRefreshing = false;
                    loadData();
                }
            }
        });
    }*/
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_comment_img://输入框的图片按钮
                ToastUtil.Info("选择图片");
                break;
            case R.id.iv_comment_face://输入框的表情按钮
                ToastUtil.Info("选择表情");
                break;
            case R.id.et_comment://输入框 不过点击事件应该没有

                break;
        }
    }

    @Override
    public void showContent(String msg) {

    }

    @Override
    public void showData(List<MeiZiBean> data) {
    }

    @Override
    public void showDataMultiple(List<MeiZiMultipleBean> data) {

    }

    @Override
    public void addDynamic(String funsharingAddBean) {

    }

    @Override
    public void shareList(FunsharingListBean funsharingListBeans) {
        load_more++;
        if(load_more==1)
        {
            adapter.setOnLoadMoreListener(this,recyclerView);//设置加载更多
        }
        //返回的数据 第一次
        if ( funsharingListBeans.getList() == null || funsharingListBeans
                .getList().size() <= 0) {
            View emptyView = View.inflate(this, R.layout.partial_empty_view, null);
            TextView viewById = emptyView.findViewById(R.id.tv_message);
            adapter.setEmptyView(emptyView);
        }

        int size = funsharingListBeans.getList().size();
         List<FunshingReplayItemBean> comments1 = new ArrayList<>();
         int end_j = 0 ;

        totalPage = funsharingListBeans.getTotalPage();
        if (isRefreshing) {
            adapter.setNewData(funsharingListBeans.getList());
            refreshLayout.setRefreshing(false);
        } else {
            adapter.addData(funsharingListBeans.getList());
            adapter.loadMoreComplete();
        }

    }

    @Override
    public void shareListMy(FunsharingMyBean funsharingMyBeans) {

    }

    @Override
    public void shareDel(String message) {

    }

    @Override
    public void commentShareAdd(FunsharingCommnetAddBean funsharingCommnetAddBean) {

        int fabulous = funsharingCommnetAddBean.getFabulous();
        SAToast.makeText(this,""+fabulous).show();
        if (fabulous == 0) {//评论
            List<FunshingItemBean> data = this.adapter.getData();
            FunshingItemBean funshingItemBean = data.get(operatorPosition);
            FunshingCommentItemBean commentItemBean = new FunshingCommentItemBean();

            commentItemBean.setId(funsharingCommnetAddBean.getId());
            commentItemBean.setComment(funsharingCommnetAddBean.getComment());
            commentItemBean.setCreatedtime(funsharingCommnetAddBean.getCreatedtime());
            commentItemBean.setFabulous(funsharingCommnetAddBean.getFabulous() + "");
            commentItemBean.setObserver(funsharingCommnetAddBean.getObserver());
            commentItemBean.setParentid(funsharingCommnetAddBean.getParentid());
            commentItemBean.setShareid(funsharingCommnetAddBean.getShareid());
            commentItemBean.setAnswerer(funsharingCommnetAddBean.getAnswerer());
            commentItemBean.setUserid(funsharingCommnetAddBean.getUserid());
            commentItemBean.setStatus(funsharingCommnetAddBean.getStatus());

            funshingItemBean.getShmlist().add(commentItemBean);

//            this.adapter.addData(funshingItemBean);
            adapter.notifyDataSetChanged();
//            commentAdapter.addData(commentItemBean);

        } else if (fabulous == 1){//回复
            if (whoAdapter == 1) {
                SAToast.makeText(FunsharingHomeActivity.this,"回复1").show();

                FunshingCommentItemBean commentItemBean = new FunshingCommentItemBean();
                commentItemBean.setId(funsharingCommnetAddBean.getId());
                commentItemBean.setComment(funsharingCommnetAddBean.getComment());
                commentItemBean.setCreatedtime(funsharingCommnetAddBean.getCreatedtime());
                commentItemBean.setFabulous(funsharingCommnetAddBean.getFabulous() + "");
                commentItemBean.setObserver(funsharingCommnetAddBean.getObserver());
                commentItemBean.setParentid(funsharingCommnetAddBean.getParentid());
                commentItemBean.setShareid(funsharingCommnetAddBean.getShareid());
                commentItemBean.setAnswerer(funsharingCommnetAddBean.getAnswerer() == null ?
                        tonickname : funsharingCommnetAddBean.getAnswerer());
                commentItemBean.setUserid(funsharingCommnetAddBean.getUserid());
                commentItemBean.setStatus(funsharingCommnetAddBean.getStatus());

                commentAdapter.addData(operatorPosition+1,commentItemBean);

            } else if (whoAdapter == 2) {
                SAToast.makeText(FunsharingHomeActivity.this,"回复2").show();
                FunshingReplayItemBean replayItemBean = new FunshingReplayItemBean();
                replayItemBean.setId(funsharingCommnetAddBean.getId());
                replayItemBean.setComment(funsharingCommnetAddBean.getComment());
                replayItemBean.setCreatedtime(funsharingCommnetAddBean.getCreatedtime());
                replayItemBean.setFabulous(funsharingCommnetAddBean.getFabulous() + "");
                replayItemBean.setObserver(funsharingCommnetAddBean.getObserver());
                replayItemBean.setParentid(funsharingCommnetAddBean.getParentid());
                replayItemBean.setShareid(funsharingCommnetAddBean.getShareid());
                replayItemBean.setAnswerer(funsharingCommnetAddBean.getAnswerer() == null ?
                        tonickname : funsharingCommnetAddBean.getAnswerer());
                replayItemBean.setUserid(funsharingCommnetAddBean.getUserid());
                replayItemBean.setStatus(funsharingCommnetAddBean.getStatus());
                commentReplayAdapter.addData(replayItemBean);
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
    public void commentShareDel(String message) {
        Logger.i("msg:" + message);
        //刷新
    }

    @Override
    public void shareLikeOrCancel(FunsharingLikeBean funsharingLikeBean) {
//        Logger.w(funsharingLikeBean.getSl().toString());
        FunshingItemBean item = adapter.getItem(operatorPosition);
        if (operatorView != null) {
//            operatorView.setText(funsharingLikeBean.getSl().size() + "");
            int oldNum = Integer.parseInt(operatorView.getText().toString());
            int newNUm = funsharingLikeBean.getSl().size();

            if (oldNum > newNUm) {//取消
                item.setIspoint("0");
                Drawable rightDrawable = getResources().getDrawable(R.mipmap.icon_like_gray);
                rightDrawable.setBounds(0, 0, rightDrawable.getMinimumWidth(), rightDrawable
                        .getMinimumHeight());
                operatorView.setCompoundDrawables(rightDrawable, null, null, null);

            } else {//点赞
                item.setIspoint("1");
                Drawable rightDrawable = getResources().getDrawable(R.mipmap.icon_like);
                rightDrawable.setBounds(0, 0, rightDrawable.getMinimumWidth(), rightDrawable
                        .getMinimumHeight());
                operatorView.setCompoundDrawables(rightDrawable, null, null, null);

            }
//            operatorView.setText(newNUm + "");
            item.setFabulousnumber(newNUm);

//            List<String> s2 = funsharingLikeBean.getS2();
//            if (s2!=null&&s2.size()>0){
//
//            }
            List<String> s2 = funsharingLikeBean.getS2();

//            item.getNicknameFabulous().clear();
            item.setNmpointlist(s2);

            adapter.notifyItemChanged(operatorPosition);
        }
        operatorView = null;

    }

    @Override
    public void uploadHeader(FileUploadBean fileUploadBean) {

    }

    @Override
    public void stateEmpty() {

    }

    @Override
    public void stateLoading() {

    }

    @Override
    public void stateError(Throwable e) {
        super.stateError(e);
        if (adapter.getData() == null || adapter.getData().size() <= 0) {
            View emptyView = View.inflate(this, R.layout.partial_empty_view, null);
            adapter.setEmptyView(emptyView);
        }

        if (refreshLayout.isRefreshing()) {
            refreshLayout.setRefreshing(false);
        }
        if (adapter.isLoadMoreEnable()) {
            adapter.loadMoreFail();
        }
    }

    @Override
    public void stateSuccess() {

    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }


    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//        closeCommentInput();
    }

    private void closeCommentInput() {

        if (fl_pannel != null) {
            fl_pannel.setVisibility(View.GONE);
        }
        emotionMainFragment.hideEmotionLayoutoOrExtenLayout();//隐藏表情面板
        inputMethodManager.hideSoftInputFromWindow(mEditText.getWindowToken(), 0);
    }

    /**
     * @param adapter    操作的adapter
     * @param whoAdapter 哪一个adapter 0分享-1评论-2回复
     * @param position   操作的位置
     * @param isComment  是否是评论 0评论 1回复
     * @param parentId   当前评论的id 回复的父id
     */
    private void openCommentInput(BaseQuickAdapter adapter, int whoAdapter, int position, int
            isComment, int parentId) {

        this.whoAdapter = whoAdapter;
        switch (whoAdapter) {
            case 0://分享的tiem
                Logger.w("分享的tiem:" + position);
                this.adapter = (FunsharingListAdapter) adapter;
                break;
            case 1://评论的item
                Logger.w("评论的item:" + position);
                commentAdapter = (FunsharingCommentAdapter) adapter;
                break;
            case 2://回复的item
                Logger.w("回复的item:" + position);
                commentReplayAdapter = (FunsharingCommentReplayAdapter) adapter;
                break;
        }
        operatorPosition = position;
        this.isComment = isComment;

        fl_pannel.setVisibility(View.VISIBLE);
        emotionMainFragment.hideEmotionLayoutoAndExtenLayout();//隐藏表情面板

        mEditText.requestFocus();
        inputMethodManager.showSoftInput(mEditText, 0);
    }

    @Subscribe(tag = RxBus.TAG_UPDATE, thread = EventThread.MAIN_THREAD)
    public void rxrefresh(String msg) {
        if (msg.equals("refresh")) {
            Logger.i("开始刷新");
            ToastUtil.Success("分享成功");
            refreshLayout.setRefreshing(true);//第一次进入触发自动刷新，演示效果--然后在重新加载数据
            isRefreshing = true;
            loadData();
        }
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

        operatorPosition = position;
        boolean islogin = SharedPreferencedUtils.getBool("islogin", false);
        if (!islogin) {
            ActivityUtil.getInstance().openActivity(this, LoginActivity.class);
            return;
        }
        switch (view.getId()) {
            //分享item的
            case R.id.tv_like_num://点赞
//                closeCommentInput();

                operatorView = (TextView) view;
                Map<String, String> request = new HashMap<>();
                request.put("token", SharedPreferencedUtils.getStr(Constants.USER_TOKEN));
                request.put("usernum", SharedPreferencedUtils.getStr(Constants.USER_NUM));
                request.put("userid", SharedPreferencedUtils.getStr(Constants.USER_ID));
                request.put("id", this.adapter.getItem(position).getId() + "");
                request.put("reluserid", this.adapter.getItem(position).getUserid() + "");
                mPresenter.shareLikeOrCancel(GsonUtil.obj2JSON(request));

                break;
            case R.id.tv_comment_num://评论
            case R.id.tv_comment_input://评论
                mEditText.setHint("评论");
                reluserid = this.adapter.getItem(position).getUserid() + "";
                openCommentInput(adapter, 0, position, 0, this.adapter.getItem(position).getId());

                break;
            case R.id.iv_header:
//                closeCommentInput();
                break;

            case R.id.tv_title:
//                closeCommentInput();
                break;
            case R.id.tv_share:
//                closeCommentInput();
                share(this.adapter.getItem(position).getId());
                break;
            case R.id.share_guan_linear://点击关注按钮

                LinearLayoutCompat share_guan_linear = view.findViewById(R.id.share_guan_linear);
                AppCompatImageView add_im = view.findViewById(R.id.guan_zhu_image);
                AppCompatTextView add_tv = view.findViewById(R.id.guan_zhu_tv);
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
//            //评论item的
//            case R.id.tv_comment_nickname:
//                break;
//            case R.id.tv_comment_content:
//                break;
//            //回复item的
//            case R.id.tv_from_username:
//                break;
//            case R.id.tv_to_username:
//                break;
//            case R.id.tv_replay:
//                break;

        }

    }

    private void init_guan(String touserid)
    {
        String user_id = SharedPreferencedUtils.getStr(Constants.USER_NUM);
        Map<String, String> request = new HashMap<>();
        request.put("token", SharedPreferencedUtils.getStr(Constants.USER_TOKEN));
        request.put("usernum", SharedPreferencedUtils.getStr(Constants.USER_NUM));
        request.put("userid",  SharedPreferencedUtils.getStr(Constants.USER_ID));
        request.put("touserid", touserid);
        request.put("modename", "share");
        String  jsonObject = JSON.toJSONString(request);

        RestClent.builder()
                .url("follow")
                .params("action",jsonObject)
                .loader(this,LoaderStyle.BallClipRotateIndicator)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        System.out.println("关注返回的数据******************"+response);
                        SAToast.makeText(FunsharingHomeActivity.this,"关注成功！").show();
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
    private void share(int id) {
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
            UMWeb web = new UMWeb(LYLApi.HOST_SHARE+"share/index.html#/lequ?shareId="+id);

            web.setTitle("乐趣分享");//标题
            UMImage umImage = new UMImage(this, R.mipmap.ic_launcher);
            web.setThumb(umImage);  //缩略图
            web.setDescription("友邦友-乐趣分享");//描述

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
            ToastUtil.Success("分享成功");
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
            ToastUtil.Success("分享失败");

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

    //权限认证
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {

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
    // 评论与回复的点击事件 end

    String tonickname = "";

    @Override
    public void onRefresh() {
        refreshLayout.setRefreshing(true);
        pageIndex = 1;
        isRefreshing = true;
        loadData();
    }

    @Override
    public void onLoadMoreRequested() {
        adapter.setEnableLoadMore(true);
        pageIndex++;
        if (pageIndex > totalPage) {
            ToastUtil.Info("没有更多啦~~");
            adapter.loadMoreEnd();
            return;
        } else {
            isRefreshing = false;
            loadData();
        }
    }

    /**
     * 评论item的点击事件
     */
    public class CommentItemListener implements BaseQuickAdapter.OnItemClickListener
            , BaseQuickAdapter.OnItemChildClickListener {

        @Override
        public void onItemChildClick(BaseQuickAdapter adapter, View view, final int position) {

            commentAdapter = (FunsharingCommentAdapter) adapter;

            final FunshingCommentItemBean item = commentAdapter.getItem(position);
            tonickname = item.getObserver();
            int local_userid = Integer.parseInt(SharedPreferencedUtils.getStr(Constants.USER_ID));
            if (local_userid == item.getUserid()) {//点击的是自己回复的
                switch (view.getId()) {
                    case R.id.tv_nickname:
//                        closeCommentInput();
                        break;
                    case R.id.tv_comment_content:
                    case R.id.tv_add_comment:
                        if (item.getObserver().equals(SharedPreferencedUtils.getStr(Constants
                                .USER_NICK_NAME))) {//删除
//                            closeCommentInput();
                            View popView = View.inflate(FunsharingHomeActivity.this, R.layout
                                    .item_pop_del, null);
                            final CustomPopWindow popWindow = new CustomPopWindow.PopupWindowBuilder
                                    (FunsharingHomeActivity.this)
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

                                    mPresenter.commentShareDel(GsonUtil.obj2JSON(request));
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
                        } else {
                            reluserid=item.getFabuzheid();
                            mEditText.setHint("回复:" + item.getObserver());
                            touserid = item.getUserid() + "";
                            parentId = item.getId() + "";
                            openCommentInput(adapter, 1, position, 1, item.getParentid() == "" ?
                                    -18 :
                                    Integer.parseInt(item.getParentid()));
                        }

                        break;
                }

            } else {//点击的是别人回复的
                SAToast.makeText(FunsharingHomeActivity.this,""+position).show();
                reluserid=item.getFabuzheid();
                mEditText.setHint("回复:" + item.getObserver());
                touserid = item.getUserid() + "";
                parentId = item.getId() + "";
                openCommentInput(adapter, 1, position, 1, item.getParentid() == "" ? -18 :
                        Integer.parseInt(item.getParentid()));
            }

        }

        @Override
        public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//            closeCommentInput();

        }
    }


    /**
     * 回复item 的点击事件
     */
    public class CommentReplayListener implements BaseQuickAdapter.OnItemClickListener
            , BaseQuickAdapter.OnItemChildClickListener {

        @Override
        public void onItemChildClick(BaseQuickAdapter adapter, View view, final int position) {

            commentReplayAdapter = (FunsharingCommentReplayAdapter) adapter;
            final FunshingReplayItemBean item = commentReplayAdapter.getItem(position);
            tonickname = item.getObserver();
            int local_userid = Integer.parseInt(SharedPreferencedUtils.getStr(Constants.USER_ID));
            if (local_userid == item.getUserid()) {//点击的是自己回复的
                switch (view.getId()) {
                    case R.id.tv_from_username:
//                        closeCommentInput();
                        break;
                    case R.id.tv_to_username:
//                        closeCommentInput();
                        break;
                    case R.id.tv_replay:
                    case R.id.tv_add_comment:
                        if (item.getObserver().equals(SharedPreferencedUtils.getStr(Constants
                                .USER_NICK_NAME))) {//删除
//                            closeCommentInput();
                            View popView = View.inflate(FunsharingHomeActivity.this, R.layout
                                    .item_pop_del, null);
                            final CustomPopWindow popWindow = new CustomPopWindow.PopupWindowBuilder
                                    (FunsharingHomeActivity.this)
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

                                    mPresenter.commentShareDel(GsonUtil.obj2JSON(request));

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
                        } else {
                            reluserid=item.getFabuzheid();
                            mEditText.setHint("回复:" + item.getObserver());
                            touserid = item.getUserid() + "";
                            parentId = item.getParentid() + "";
                            openCommentInput(adapter, 2, position, 1, Integer.parseInt(item
                                    .getParentid()));
                        }

                        break;

                }
            } else {//点击的是别人回复的
                reluserid=item.getFabuzheid();
                mEditText.setHint("回复:" + item.getObserver());
                touserid = item.getUserid() + "";
                parentId = item.getParentid() + "";
                openCommentInput(adapter, 2, position, 1, Integer.parseInt(item.getParentid()));
            }
        }

        @Override
        public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//            closeCommentInput();

        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
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
    @org.greenrobot.eventbus.Subscribe
    public void Event(HomeAdapaterSucc messageEvent)
    {
        String city_id = messageEvent.getId();
        String city_name = messageEvent.getName();
        CITY_NAME=city_name;
        CITY_ID = city_id;
        P_ID= messageEvent.getP_id();
        P_NAME= messageEvent.getP_name();
        // SAToast.makeText(getContext(),"事件来了11111111111").show();
    }

}
