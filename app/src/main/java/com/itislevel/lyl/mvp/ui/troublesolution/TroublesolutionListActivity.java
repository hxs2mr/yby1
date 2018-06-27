package com.itislevel.lyl.mvp.ui.troublesolution;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
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
import com.itislevel.lyl.adapter.TroubleCommentAdapter;
import com.itislevel.lyl.adapter.TroubleCommentReplayAdapter;
import com.itislevel.lyl.adapter.TroubleListAdapter;
import com.itislevel.lyl.app.Constants;
import com.itislevel.lyl.base.RootActivity;
import com.itislevel.lyl.mvp.model.bean.BlessOrderBean;
import com.itislevel.lyl.mvp.model.bean.FileUploadBean;
import com.itislevel.lyl.mvp.model.bean.MeiZiBean;
import com.itislevel.lyl.mvp.model.bean.TroubleAddBean;
import com.itislevel.lyl.mvp.model.bean.TroubleAdviserMyBean;
import com.itislevel.lyl.mvp.model.bean.TroubleCommentAdd;
import com.itislevel.lyl.mvp.model.bean.TroubleListBean;
import com.itislevel.lyl.mvp.model.bean.TroubleTypeBean;
import com.itislevel.lyl.mvp.ui.chatkeyboardview.EmotionMainFragment;
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

public class TroublesolutionListActivity extends RootActivity<TroublesolutionPresenter> implements
        TroublesolutionContract.View
        , BaseQuickAdapter.OnItemClickListener
        , BaseQuickAdapter.OnItemChildClickListener, View.OnClickListener {

    Bundle bundle = new Bundle();
    private String PROVINCE_ID = "";
    private String CITY_ID = "";
    private String COUNTY_ID = ""; //很多情况下是空的

    private String PROVINCE_TITLE = "";
    private String CITY_TITLE = "";
    private String COUNTY_TITLE = "";//很多情况下是空的


    @BindView(R.id.refreshLayout)
    RefreshLayout refreshLayout;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
//
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


    //是否已添加没有更多数据的布局
    private boolean isAddNoMoreView = false;
    private int pageIndex = 1;//分页加载时 当前页是第几页
    private int totalPage = 0;//总页数
    private boolean isRefreshing = true;//当前是刷新还是加载
    private TroubleListAdapter adapter;

    private int isComment = 0;//评论传0，回复传1

    private TextView operatorView = null;
    private int operatorPosition = -1;

    private InputMethodManager inputMethodManager;

    private String touserid = "";//回复时  回复对象的用户id
    private int parentId = -1; //回复时的父id
    TroubleCommentAdapter commentAdapter;//评论的adapter
    TroubleCommentReplayAdapter commentReplayAdapter;//回复的adapter
    private int whoAdapter = 0; // 哪一个adapter 0分享-1评论-2回复

    @Override
    protected int getLayoutId() {
        return R.layout.activity_troublesolution_list;
    }

    @Override
    protected void initEventAndData() {

        inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);


        bundle = this.getIntent().getExtras();
        String title = bundle.getString(Constants.TROUBLE_TITLE);

        PROVINCE_ID = bundle.getString(Constants.PROVINCE_ID);
        CITY_ID = bundle.getString(Constants.CITY_ID);
        COUNTY_ID = bundle.getString(Constants.COUNTY_ID);

        PROVINCE_TITLE = bundle.getString(Constants.PROVINCE_TITLE);
        CITY_TITLE = bundle.getString(Constants.CITY_TITLE);
        COUNTY_TITLE = bundle.getString(Constants.COUNTY_TITLE);

        setToolbarTvTitle(title+"圈");

        initRefreshListener();

        // 输入框
        mInputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        initViewListener();
        initAdapter();

        refreshLayout.autoRefresh();//刷新效果
//        loadData();

    }


    private void initViewListener() {

//        iv_comment_img.setOnClickListener(this);
//        iv_comment_face.setOnClickListener(this);
//        et_comment.setOnClickListener(this);
//        et_comment.setOnEditorActionListener(new TextView.OnEditorActionListener() {
//            @Override
//            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
//                 /*判断是否是“GO”键*/
//                if (actionId == EditorInfo.IME_ACTION_SEND) {
//                    /*隐藏软键盘 并隐藏评论输入框*/
//                    InputMethodManager imm = (InputMethodManager) v
//                            .getContext().getSystemService(
//                                    Context.INPUT_METHOD_SERVICE);
//                    ll_commint.setVisibility(View.GONE);
//
//                    if (imm.isActive()) {
//                        imm.hideSoftInputFromWindow(
//                                v.getApplicationWindowToken(), 0);
//                    }
//                    Editable content = et_comment.getText();
//                    if (TextUtils.isEmpty(content)) {
//                        ToastUtil.Info("请输入评论内容");
//                    } else {
//
//                        Map<String, String> request = new HashMap<>();
//                        request.put("token", SharedPreferencedUtils.getStr(Constants.USER_TOKEN));
//                        request.put("usernum", SharedPreferencedUtils.getStr(Constants.USER_NUM));
//                        request.put("userid", SharedPreferencedUtils.getStr(Constants.USER_ID));
//
//                        int annotabceid = -1;
//                        switch (whoAdapter) {
//                            case 0:
//                                annotabceid = adapter.getItem(operatorPosition).getId();
//                                break;
//                            case 1:
//                                annotabceid = commentAdapter.getItem(operatorPosition)
//                                        .getAnnotabceid();
//                                break;
//                            case 2:
//                                annotabceid = commentReplayAdapter.getItem(operatorPosition)
//                                        .getAnnotabceid();
//                                break;
//                        }
//
//                        request.put("annotabceid", annotabceid + "");
//                        request.put("comment", content.toString());
//                        request.put("parentid", parentId + "");
////                        request.put("parentname", "");
//                        request.put("fabulous", isComment + "");
//                        request.put("touserid", touserid);
//
//                        Logger.w(GsonUtil.obj2JSON(request));
//
//                        mPresenter.troubleCommentReplay(GsonUtil.obj2JSON(request));
//                    }
//                    return true;
//                }
//                return false;
//            }
//        });


        commentItemListener = new CommentItemListener();
        commentReplayListener = new CommentReplayListener();

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                closeCommentInput();
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
                        ToastUtil.Info("请输入评论内容");
                    } else {

                        Map<String, String> request = new HashMap<>();
                        request.put("token", SharedPreferencedUtils.getStr(Constants.USER_TOKEN));
                        request.put("usernum", SharedPreferencedUtils.getStr(Constants.USER_NUM));
                        request.put("userid", SharedPreferencedUtils.getStr(Constants.USER_ID));

                        int annotabceid = -1;
                        switch (whoAdapter) {
                            case 0:
                                annotabceid = adapter.getItem(operatorPosition).getId();
                                break;
                            case 1:
                                annotabceid = commentAdapter.getItem(operatorPosition)
                                        .getAnnotabceid();
                                break;
                            case 2:
                                annotabceid = commentReplayAdapter.getItem(operatorPosition)
                                        .getAnnotabceid();
                                break;
                        }

                        request.put("annotabceid", annotabceid + "");
                        request.put("comment", content.toString());
                        request.put("parentid", parentId + "");
//                        request.put("parentname", "");
                        request.put("fabulous", isComment + "");
                        request.put("touserid", touserid);
                        request.put("reluserid", reluserid);

                        Logger.w(GsonUtil.obj2JSON(request));

                        mPresenter.troubleCommentReplay(GsonUtil.obj2JSON(request));
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
        request.put("userid", SharedPreferencedUtils.getStr(Constants.USER_ID));
        request.put("pageIndex", pageIndex + "");
        request.put("pageSize", Constants.PAGE_NUMBER10 + "");
        request.put("secondcateid", bundle.getInt(Constants.TROUBLE_TEAM_TYPE_SECOND_ID) + "");

        mPresenter.troubleList(GsonUtil.obj2JSON(request));
    }

    /**
     * 初始化adapter
     */
    private void initAdapter() {
        if (adapter == null) {
            adapter = new TroubleListAdapter(R.layout.item_trouble, this);
            adapter.setOnItemClickListener(this);
            adapter.setOnItemChildClickListener(this);
            adapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN); //切换动画
            adapter.setEnableLoadMore(false);
            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(adapter);
        }
    }

    private void initRefreshListener() {
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
    }


    @Override
    public void showContent(String msg) {

    }

    @Override
    public void showDataList(List<MeiZiBean> meiZiBeans) {

    }

    @Override
    public void troubleAdd(TroubleAddBean troubleAdd) {

    }

    @Override
    public void troubleList(TroubleListBean troubleListBean) {
        totalPage = troubleListBean.getTotalPage();

        //返回的数据 第一次
        if (adapter.getData().size() <= 0 && troubleListBean.getList().size() <= 0) {
            View emptyView = View.inflate(this, R.layout.partial_empty_view, null);
            TextView viewById = emptyView.findViewById(R.id.tv_message);
            adapter.setEmptyView(emptyView);
        }
        if (isRefreshing) {
            adapter.setNewData(troubleListBean.getList());
            refreshLayout.finishRefresh();
        } else {
            adapter.addData(troubleListBean.getList());
            refreshLayout.finishLoadmore();
        }
    }

    @Override
    public void troubleListMy(TroubleListBean troubleListBean) {

    }

    @Override
    public void troubleDel(String action) {

    }

    @Override
    public void troubleCommentReplay(TroubleCommentAdd troubleCommentAdd) {
        int fabulous = troubleCommentAdd.getFabulous();
        if (fabulous == 0) {//评论
            TroubleListBean.ListBean item = this.adapter.getItem(operatorPosition);
            TroubleListBean.ListBean.CommentsBean commentItemBean = new TroubleListBean.ListBean
                    .CommentsBean();

            commentItemBean.setId(troubleCommentAdd.getId());
            commentItemBean.setComment(troubleCommentAdd.getComment());
            commentItemBean.setCreatedtime(troubleCommentAdd.getCreatedtime());
            commentItemBean.setFabulous(troubleCommentAdd.getFabulous() + "");
            commentItemBean.setNickname(troubleCommentAdd.getNickname());
            commentItemBean.setParentid(troubleCommentAdd.getParentid());
            commentItemBean.setAnnotabceid(troubleCommentAdd.getAnnotabceid());
            commentItemBean.setStatus(troubleCommentAdd.getStatus());
            commentItemBean.setUserid(troubleCommentAdd.getUserid());
            commentItemBean.setTouserid(troubleCommentAdd.getTouserid());

            item.getComments().add(commentItemBean);
            adapter.notifyDataSetChanged();

        } else if (fabulous == 1) {//回复
            if (whoAdapter == 1) {
                TroubleListBean.ListBean.CommentsBean item = commentAdapter.getItem
                        (operatorPosition);
                List<TroubleListBean.ListBean.CommentsBean.Comments1Bean> comments1 = item
                        .getComments1();

                TroubleListBean.ListBean.CommentsBean.Comments1Bean replayItemBean = new
                        TroubleListBean.ListBean.CommentsBean.Comments1Bean();

                replayItemBean.setId(troubleCommentAdd.getId());
                replayItemBean.setComment(troubleCommentAdd.getComment());
                replayItemBean.setCreatedtime(troubleCommentAdd.getCreatedtime());
                replayItemBean.setFabulous(troubleCommentAdd.getFabulous() + "");
                replayItemBean.setNickname(troubleCommentAdd.getNickname());
                replayItemBean.setParentid(troubleCommentAdd.getParentid());
                replayItemBean.setAnnotabceid(troubleCommentAdd.getAnnotabceid());
//                replayItemBean.setTonickname(troubleCommentAdd.getTonickname());
                replayItemBean.setTonickname(troubleCommentAdd.getTonickname() == null ?
                        tonickname : troubleCommentAdd.getTonickname());
                replayItemBean.setUserid(troubleCommentAdd.getUserid());
                replayItemBean.setStatus(troubleCommentAdd.getStatus());

                comments1.add(replayItemBean);

                commentAdapter.notifyDataSetChanged();
            } else if (whoAdapter == 2) {
                TroubleListBean.ListBean.CommentsBean.Comments1Bean replayItemBean = new
                        TroubleListBean.ListBean.CommentsBean.Comments1Bean();

                replayItemBean.setId(troubleCommentAdd.getId());
                replayItemBean.setComment(troubleCommentAdd.getComment());
                replayItemBean.setCreatedtime(troubleCommentAdd.getCreatedtime());
                replayItemBean.setFabulous(troubleCommentAdd.getFabulous() + "");
                replayItemBean.setNickname(troubleCommentAdd.getNickname());
                replayItemBean.setParentid(troubleCommentAdd.getParentid());
                replayItemBean.setAnnotabceid(troubleCommentAdd.getAnnotabceid());
                // replayItemBean.setTonickname(troubleCommentAdd.getTonickname());
                replayItemBean.setTonickname(troubleCommentAdd.getTonickname() == null ?
                        tonickname : troubleCommentAdd.getTonickname());
                replayItemBean.setUserid(troubleCommentAdd.getUserid());
                replayItemBean.setStatus(troubleCommentAdd.getStatus());

                commentReplayAdapter.addData(replayItemBean);
            }
        }

        //重置
        touserid = "";
        parentId = -1;
        operatorPosition = -1;
        reluserid = "";

        mEditText.setText("");

        closeCommentInput();
        //显示结果
    }

    @Override
    public void troubleCommentDel(String message) {
        Logger.i("msg:" + message);
        //刷新
    }

    @Override
    public void troubleType(TroubleTypeBean troubleTypeBean) {

    }

    @Override
    public void teamProblemAdd(String blessOrderBean) {

    }


    @Override
    public void teamProblemList(Object object) {

    }

    @Override
    public void teamReplay(Object object) {

    }

    @Override
    public void teamMyProblemList(TroubleAdviserMyBean troubleAdviserMyBean) {

    }

    @Override
    public void teamViewCount(String message) {

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
            TextView viewById = emptyView.findViewById(R.id.tv_message);
            viewById.setText("网络访问错误");

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
    public void stateSuccess() {

    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        switch (view.getId()) {
            case R.id.tv_title:
                //                ToastUtil.Info("item 文字");
                break;
            case R.id.iv_header:
                ToastUtil.Info("头像");
                break;
            case R.id.tv_comment_input:
                mEditText.setHint("评论");
                openCommentInput(adapter, 0, position, 0, this.adapter.getItem(position).getId());
                break;
            case R.id.tv_del_share:
                Map<String, Object> request = new HashMap<>();
                request.put("token", SharedPreferencedUtils.getStr(Constants
                        .USER_TOKEN));
                request.put("usernum", SharedPreferencedUtils.getStr(Constants
                        .USER_NUM));

                TroubleListBean.ListBean item = this.adapter.getItem(position);
                request.put("id", item.getId());

                mPresenter.troubleDel(GsonUtil.obj2JSON(request));
                this.adapter.remove(position);

                break;
        }
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onClick(View v) {

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
                this.adapter = (TroubleListAdapter) adapter;
                break;
            case 1://评论的item
                Logger.w("评论的item:" + position);
                commentAdapter = (TroubleCommentAdapter) adapter;
                break;
            case 2://回复的item
                Logger.w("回复的item:" + position);
                commentReplayAdapter = (TroubleCommentReplayAdapter) adapter;
                break;
        }
        this.parentId = parentId;
        operatorPosition = position;
        this.isComment = isComment;


        fl_pannel.setVisibility(View.VISIBLE);
        emotionMainFragment.hideEmotionLayoutoAndExtenLayout();//隐藏表情面板

        mEditText.requestFocus();
        inputMethodManager.showSoftInput(mEditText, 0);


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

            commentAdapter = (TroubleCommentAdapter) adapter;

            TroubleListBean.ListBean.CommentsBean item = commentAdapter.getItem(position);
            tonickname = item.getNickname();
            int local_userid = Integer.parseInt(SharedPreferencedUtils.getStr(Constants.USER_ID));
            if (local_userid == item.getUserid()) {//点击的是自己回复的
                switch (view.getId()) {
                    case R.id.tv_nickname:
                        break;
                    case R.id.tv_comment_content:
                    case R.id.tv_add_comment:
                        if (item.getNickname().equals(SharedPreferencedUtils.getStr(Constants
                                .USER_NICK_NAME))) {//删除
                            View popView = View.inflate(TroublesolutionListActivity.this, R.layout
                                    .item_pop_del, null);
                            final CustomPopWindow popWindow = new CustomPopWindow.PopupWindowBuilder
                                    (TroublesolutionListActivity.this)
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

                                    mPresenter.troubleCommentDel(GsonUtil.obj2JSON(request));
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
                            reluserid = item.getFabuzheid();
                            mEditText.setHint("回复:" + item.getNickname());
                            touserid = item.getUserid() + "";
                            parentId = Integer.parseInt(item.getParentid());
                            openCommentInput(adapter, 2, position, 1, Integer.parseInt(item
                                    .getParentid()));
                        }
                        break;
                }

            } else {//点击的是别人回复的
                reluserid = item.getFabuzheid();

                mEditText.setHint("回复:" + item.getNickname());
                touserid = item.getUserid() + "";
                parentId = item.getId();
                openCommentInput(adapter, 1, position, 1, parentId);
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
            commentReplayAdapter = (TroubleCommentReplayAdapter) adapter;

            TroubleListBean.ListBean.CommentsBean.Comments1Bean item = commentReplayAdapter
                    .getItem(position);
            tonickname = item.getNickname();

            int local_userid = Integer.parseInt(SharedPreferencedUtils.getStr(Constants.USER_ID));
            if (local_userid == item.getUserid()) {//点击的是自己回复的
                switch (view.getId()) {
                    case R.id.tv_from_username:
                        break;
                    case R.id.tv_to_username:
                        break;
                    case R.id.tv_replay:
                    case R.id.tv_add_comment:
                        if (item.getNickname().equals(SharedPreferencedUtils.getStr(Constants
                                .USER_NICK_NAME))) {//删除
                            View popView = View.inflate(TroublesolutionListActivity.this, R.layout
                                    .item_pop_del, null);
                            final CustomPopWindow popWindow = new CustomPopWindow.PopupWindowBuilder
                                    (TroublesolutionListActivity.this)
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

                                    mPresenter.troubleCommentDel(GsonUtil.obj2JSON(request));

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
                            reluserid = item.getFabuzheid();
                            mEditText.setHint("回复:" + item.getNickname());
                            touserid = item.getUserid() + "";
                            parentId = Integer.parseInt(item.getParentid());
                            openCommentInput(adapter, 2, position, 1, Integer.parseInt(item
                                    .getParentid()));
                        }
                        break;
                }

            } else {//点击的是别人回复的
                reluserid = item.getFabuzheid();

                mEditText.setHint("回复:" + item.getNickname());
                touserid = item.getUserid() + "";
                parentId = Integer.parseInt(item.getParentid());
                openCommentInput(adapter, 2, position, 1, Integer.parseInt(item.getParentid()));
            }
        }

        @Override
        public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

        }
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

}
