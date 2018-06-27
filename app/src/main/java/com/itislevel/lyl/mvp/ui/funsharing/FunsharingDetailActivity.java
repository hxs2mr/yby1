package com.itislevel.lyl.mvp.ui.funsharing;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
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

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.zhouwei.library.CustomPopWindow;
import com.itislevel.lyl.R;
import com.itislevel.lyl.adapter.FunsharingCommentAdapter;
import com.itislevel.lyl.adapter.FunsharingCommentReplayAdapter;
import com.itislevel.lyl.adapter.FunsharingDetailAdapter;
import com.itislevel.lyl.adapter.FunsharingDetailCommentAdapter;
import com.itislevel.lyl.adapter.FunsharingListAdapter;
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
import com.itislevel.lyl.mvp.ui.address.ProvinceActivity;
import com.itislevel.lyl.mvp.ui.chatkeyboardview.EmotionMainFragment;
import com.itislevel.lyl.mvp.ui.family.FamilyContract;
import com.itislevel.lyl.mvp.ui.family.FamilyPresenter;
import com.itislevel.lyl.utils.ActivityUtil;
import com.itislevel.lyl.utils.GsonUtil;
import com.itislevel.lyl.utils.SharedPreferencedUtils;
import com.itislevel.lyl.utils.ToastUtil;
import com.orhanobut.logger.Logger;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

public class FunsharingDetailActivity extends RootActivity<FunsharingPresenter> implements
        FunsharingContract.View
        , View.OnClickListener
        , BaseQuickAdapter.OnItemClickListener
        , BaseQuickAdapter.OnItemChildClickListener {

    @BindView(R.id.refreshLayout)
    RefreshLayout refreshLayout;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    //评论框
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


    //是否已添加没有更多数据的布局
    private boolean isAddNoMoreView = false;
    private int pageIndex = 1;//分页加载时 当前页是第几页
    private int totalPage = 0;//总页数
    private boolean isRefreshing = true;//当前是刷新还是加载
    private FunsharingDetailAdapter adapter;

    private int isComment = 0;//评论传0，回复传1

    private TextView operatorView = null;
    private int operatorPosition = -1;

    private Dialog mDialog = null;
    private InputMethodManager inputMethodManager;

    private String touserid = "";//回复时  回复对象的用户id
    private String parentId = ""; //回复时的父id
    FunsharingDetailCommentAdapter commentAdapter;//评论的adapter
    FunsharingCommentReplayAdapter commentReplayAdapter;//回复的adapter
    private int whoAdapter = 0; // 哪一个adapter 0分享-1评论-2回复

    Bundle bundle;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_funsharing_detail;
    }

    @Override
    protected void initEventAndData() {

        bundle = getIntent().getExtras();

        bundle.getString("item");

        inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

        setToolbarTvTitle("详情");


        initRefreshListener();
        initViewListener();


        initAdapter();
        refreshLayout.autoRefresh();//刷新效果
//        loadData();
    }

    private void getDialog() {
        //设置全屏将会覆盖状态栏
        mDialog = new Dialog(FunsharingDetailActivity.this, R.style.Dialog_Fullscreen);
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
                mDialog.dismiss();

                Bundle bundle = new Bundle();
                bundle.putString(Constants.PROVINCE_TITLE, "乐趣分享");
                bundle.putString(Constants.CITY_TITLE, "乐趣分享");
                bundle.putString(Constants.COUNTY_TITLE, "乐趣分享");
                bundle.putBoolean(Constants.IS_SHOW_COUNTY, false);

                bundle.putInt(Constants.ACTIVITY_TARGET, Constants.ACTIVITY_FUNSHARING);

                ActivityUtil.getInstance().openActivity(FunsharingDetailActivity.this,
                        ProvinceActivity.class, bundle);


            }
        });
        tv_share_my.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.dismiss();
                ActivityUtil.getInstance().openActivity(FunsharingDetailActivity.this,
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

    private void initViewListener() {

        commentItemListener = new CommentItemListener();
        commentReplayListener = new CommentReplayListener();

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
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
//                    sendTxtMessage();
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
                                break;
                            case 1:
                                shareid = commentAdapter.getItem(operatorPosition).getShareid();
                                break;
                            case 2:
                                shareid = commentReplayAdapter.getItem(operatorPosition)
                                        .getShareid();
                                break;
                        }
                        // TODO: 2017/12/16 此处可能有坑
                        request.put("shareid", shareid + "");
                        request.put("comment", content.toString());
                        request.put("observer", SharedPreferencedUtils.getStr(Constants.USER_NICK_NAME) );
                        request.put("parentid", parentId);
                        request.put("answerer", "");
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
//        Map<String, String> request = new HashMap<>();
//        request.put("token", SharedPreferencedUtils.getStr(Constants.USER_TOKEN));
//        request.put("usernum", SharedPreferencedUtils.getStr(Constants.USER_NUM));
//        request.put("userid", SharedPreferencedUtils.getStr(Constants.USER_ID));
//        request.put("pageIndex", pageIndex + "");
//        request.put("pageSize", Constants.PAGE_NUMBER10 + "");
//
//        mPresenter.shareList(GsonUtil.obj2JSON(request));

    }

    /**
     * 初始化adapter
     */
    private void initAdapter() {
        if (adapter == null) {
            adapter = new FunsharingDetailAdapter(R.layout.item_funsharing, this);
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
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(adapter);
        }

        String item = bundle.getString("item");
        FunshingItemBean funshingItemBean = GsonUtil.toObject(item, FunshingItemBean
                .class);

        adapter.addData(funshingItemBean);

    }

    private void initRefreshListener() {
//        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
//            @Override
//            public void onRefresh(RefreshLayout refreshlayout) {
//                pageIndex = 1;
//                isRefreshing = true;
//                loadData();
//            }
//        });
//
//        refreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
//            @Override
//            public void onLoadmore(RefreshLayout refreshlayout) {
//                pageIndex++;
//                if (pageIndex > totalPage) {
//                    if (!isAddNoMoreView) {
//                        isAddNoMoreView = true;
//                        View view = getLayoutInflater().inflate(R.layout.partial_no_more_data,
//                                null, false);
//                        adapter.addFooterView(view);
//                    }
//                    ToastUtil.Info("没有更多啦~~");
//                    refreshLayout.finishLoadmore(true);//
//                    return;
//                } else {
//                    isRefreshing = false;
//                    loadData();
//                }
//            }
//        });

        refreshLayout.setEnableRefresh(false);
        refreshLayout.setEnableLoadmore(false);
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        operatorPosition = position;
        switch (view.getId()) {
            //分享item的
            case R.id.tv_like_num://点赞
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
//                ToastUtil.Info("头像");
                break;

            case R.id.tv_title:
//                ToastUtil.Info("item 文字");
                break;
//
//            //评论item的
//            case R.id.tv_comment_nickname:
//                ToastUtil.Info("评论 的人");
//                break;
//            case R.id.tv_comment_content:
//                ToastUtil.Info("评论 文字");
//                break;
//
//
//            //回复item的
//            case R.id.tv_from_username:
//                ToastUtil.Info("回复者");
//                break;
//            case R.id.tv_to_username:
//                ToastUtil.Info("被回复的人");
//                break;
//            case R.id.tv_replay:
//                ToastUtil.Info("回复 文字");
//                break;

        }
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        operatorPosition = position;
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
        totalPage = funsharingListBeans.getTotalPage();
        if (isRefreshing) {
            adapter.setNewData(funsharingListBeans.getList());
            refreshLayout.finishRefresh();
        } else {
            adapter.addData(funsharingListBeans.getList());
            refreshLayout.finishLoadmore();
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

        } else if (fabulous == 1) {//回复
            if (whoAdapter == 1) {
                FunshingCommentItemBean commentItemBean = commentAdapter.getItem(operatorPosition);
                List<FunshingReplayItemBean> comments1 = commentItemBean.getComments1();
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

                comments1.add(replayItemBean);

                commentAdapter.notifyDataSetChanged();
            } else if (whoAdapter == 2) {
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

            if (item.getNmpointlist() != null)
                item.getNmpointlist().clear();
            item.setNmpointlist(s2);

            adapter.notifyItemChanged(operatorPosition);
        }
        operatorView = null;
    }

    @Override
    public void stateError(Throwable e) {
        super.stateError(e);
        if (adapter.getData() == null || adapter.getData().size() <= 0) {
            View emptyView = View.inflate(this, R.layout.partial_empty_view, null);
            adapter.setEmptyView(emptyView);
        }

        if (refreshLayout.isLoading()) {
            refreshLayout.finishLoadmore();
        }
        if (refreshLayout.isRefreshing()) {
            refreshLayout.finishRefresh();
        }
    }

    @Override
    public void uploadHeader(FileUploadBean fileUploadBean) {

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
                this.adapter = (FunsharingDetailAdapter) adapter;
                break;
            case 1://评论的item
                Logger.w("评论的item:" + position);
                commentAdapter = (FunsharingDetailCommentAdapter) adapter;
                break;
            case 2://回复的item
                Logger.w("回复的item:" + position);
                commentReplayAdapter = (FunsharingCommentReplayAdapter) adapter;
                break;
        }
        operatorPosition = position;
        this.isComment = isComment;

        fl_pannel.setVisibility(View.VISIBLE);

        mEditText.requestFocus();
        inputMethodManager.showSoftInput(mEditText, 0);
        emotionMainFragment.hideEmotionLayoutoAndExtenLayout();//隐藏表情面板


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

    /**
     * 评论item的点击事件
     */
    public class CommentItemListener implements BaseQuickAdapter.OnItemClickListener
            , BaseQuickAdapter.OnItemChildClickListener {

        @Override
        public void onItemChildClick(BaseQuickAdapter adapter, View view, final int position) {

            commentAdapter = (FunsharingDetailCommentAdapter) adapter;

            final FunshingCommentItemBean item = commentAdapter.getItem(position);
            tonickname = item.getObserver();

            int local_userid = Integer.parseInt(SharedPreferencedUtils.getStr(Constants.USER_ID));
            if (local_userid == item.getUserid()) {//点击的是自己回复的
                switch (view.getId()) {
                    case R.id.tv_nickname:
                        break;
                    case R.id.tv_comment_content:
                    case R.id.tv_add_comment:
                        if (item.getObserver().equals(SharedPreferencedUtils.getStr(Constants
                                .USER_NICK_NAME))) {//删除
//                            closeCommentInput();
                            View popView = View.inflate(FunsharingDetailActivity.this, R.layout
                                    .item_pop_del, null);
                            final CustomPopWindow popWindow = new CustomPopWindow.PopupWindowBuilder
                                    (FunsharingDetailActivity.this)
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
                        break;
                    case R.id.tv_to_username:
                        break;
                    case R.id.tv_replay:
                    case R.id.tv_add_comment:
                        if (item.getObserver().equals(SharedPreferencedUtils.getStr(Constants
                                .USER_NICK_NAME))) {//删除
//                            closeCommentInput();
                            View popView = View.inflate(FunsharingDetailActivity.this, R.layout
                                    .item_pop_del, null);
                            final CustomPopWindow popWindow = new CustomPopWindow.PopupWindowBuilder
                                    (FunsharingDetailActivity.this)
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
        }
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
}
