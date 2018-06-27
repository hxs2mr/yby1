package com.itislevel.lyl.mvp.ui.funsharing;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.itislevel.lyl.R;
import com.itislevel.lyl.adapter.FunsharingHomeAdapter;
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
import com.itislevel.lyl.mvp.ui.address.ProvinceActivity;
import com.itislevel.lyl.utils.ActivityUtil;
import com.itislevel.lyl.utils.GsonUtil;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

@UseRxBus
public class FunsharingListActivity extends RootActivity<FunsharingPresenter> implements
        FunsharingContract.View
        , View.OnClickListener
        , BaseQuickAdapter.OnItemClickListener
        , BaseQuickAdapter.OnItemChildClickListener {

    @BindView(R.id.refreshLayout)
    RefreshLayout refreshLayout;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    //是否已添加没有更多数据的布局
    private boolean isAddNoMoreView = false;
    private int pageIndex = 1;//分页加载时 当前页是第几页
    private int totalPage = 0;//总页数
    private boolean isRefreshing = true;//当前是刷新还是加载
    private FunsharingHomeAdapter adapter;

    private int isComment = 0;//评论传0，回复传1

    private TextView operatorView = null;
    private int operatorPosition = -1;

    private Dialog mDialog = null;
    private InputMethodManager inputMethodManager;

    private String touserid = "";//回复时  回复对象的用户id
    private String parentId = ""; //回复时的父id

    //评论框
    @BindView(R.id.ll_comment)
    LinearLayout ll_commint;
    @BindView(R.id.iv_comment_img)
    ImageView iv_comment_img;
    @BindView(R.id.iv_comment_face)
    ImageView iv_comment_face;
    @BindView(R.id.et_comment)
    EditText et_comment;

    ArrayList<MultiItemEntity> list;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_funsharing_list;
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

        initRefreshListener();
        initViewListener();
        loadData();
        if (list == null) {
            list = new ArrayList<>();
        }
        initAdapter(list);
    }


    private void getDialog() {
        //设置全屏将会覆盖状态栏
        mDialog = new Dialog(FunsharingListActivity.this, R.style.Dialog_Fullscreen);
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

                ActivityUtil.getInstance().openActivity(FunsharingListActivity.this,
                        ProvinceActivity.class, bundle);


            }
        });
        tv_share_my.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.dismiss();
                ActivityUtil.getInstance().openActivity(FunsharingListActivity.this,
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

        iv_comment_img.setOnClickListener(this);
        iv_comment_face.setOnClickListener(this);
        et_comment.setOnClickListener(this);
        et_comment.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                 /*判断是否是“GO”键*/
                if (actionId == EditorInfo.IME_ACTION_SEND) {
                    /*隐藏软键盘 并隐藏评论输入框*/
                    InputMethodManager imm = (InputMethodManager) v
                            .getContext().getSystemService(
                                    Context.INPUT_METHOD_SERVICE);
                    ll_commint.setVisibility(View.GONE);

                    if (imm.isActive()) {
                        imm.hideSoftInputFromWindow(
                                v.getApplicationWindowToken(), 0);
                    }
                    Editable content = et_comment.getText();
                    if (TextUtils.isEmpty(content)) {
                        ToastUtil.Info("请输入评论内容");
                    } else {

                        Map<String, String> request = new HashMap<>();
                        request.put("token", SharedPreferencedUtils.getStr(Constants.USER_TOKEN));
                        request.put("usernum", SharedPreferencedUtils.getStr(Constants.USER_NUM));
                        request.put("userid", SharedPreferencedUtils.getStr(Constants.USER_ID));

                        // TODO: 2017/12/15  临时注释
                        MultiItemEntity multiItemEntity = adapter.getItem(operatorPosition);
                        switch (multiItemEntity.getItemType()) {
                            case 0:
                                FunshingItemBean shareItem = (FunshingItemBean) multiItemEntity;
                                request.put("shareid", shareItem.getId() + "");
                                request.put("comment", content.toString());
                                request.put("parentid", parentId);
                                request.put("parentname", "");
                                request.put("fabulous", isComment + "");
                                request.put("touserid", touserid);

                                break;
                            case 1:
                                FunshingCommentItemBean commentItem = (FunshingCommentItemBean) multiItemEntity;


                                break;
                            case 2:
                                FunshingReplayItemBean replayItemBean = (FunshingReplayItemBean) multiItemEntity;

                                break;


                        }

                        mPresenter.commentShareAdd(GsonUtil.obj2JSON(request));

                    }
                    return true;
                }
                return false;
            }
        });


        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                closeCommentInput();
            }
        });
    }

    private void loadData() {

        Map<String, String> request = new HashMap<>();
        request.put("token", SharedPreferencedUtils.getStr(Constants.USER_TOKEN));
        request.put("usernum", SharedPreferencedUtils.getStr(Constants.USER_NUM));
        request.put("userid", SharedPreferencedUtils.getStr(Constants.USER_ID));
        request.put("pageIndex", pageIndex + "");
        request.put("pageSize", Constants.PAGE_NUMBER10 + "");
        mPresenter.shareList(GsonUtil.obj2JSON(request));
    }

    /**
     * 初始化adapter
     */
    private void initAdapter(List<MultiItemEntity> listBean) {

        if (adapter == null) {
            adapter = new FunsharingHomeAdapter(listBean);
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

            adapter.expandAll();
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
    public void onClick(View v) {

    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        MultiItemEntity item = this.adapter.getItem(position);

        switch (item.getItemType()) {
            case 0:
                FunshingItemBean shareItem = (FunshingItemBean) item;
                switch (view.getId()) {

                    case R.id.tv_like_num://点赞图标
                        Map<String, String> request = new HashMap<>();
                        request.put("token", SharedPreferencedUtils.getStr(Constants.USER_TOKEN));
                        request.put("usernum", SharedPreferencedUtils.getStr(Constants.USER_NUM));
                        request.put("userid", SharedPreferencedUtils.getStr(Constants.USER_ID));
                        request.put("id", shareItem.getId() + "");
                        mPresenter.shareLikeOrCancel(GsonUtil.obj2JSON(request));
                        break;
                    case R.id.tv_comment_num://评论图标
                    case R.id.tv_comment_input://评论匡
                        openCommentInput(position, 0, shareItem.getNickname());
                        break;
                    case R.id.iv_header://分享者头像
                        ToastUtil.Info("头像");
                        break;
                    case R.id.tv_title://评论的文字内容
                        ToastUtil.Info("评论内容");
                        break;
                }
                break;
            case 1:
                FunshingCommentItemBean commentItem = (FunshingCommentItemBean) item;
                switch (view.getId()) {
                    case R.id.tv_comment_nickname://评论者昵称
                        break;
                    case R.id.tv_comment_content://评论的内容
                        break;
                }
                break;
            case 2:
                FunshingReplayItemBean replayItem = (FunshingReplayItemBean) item;
                switch (view.getId()) {
                    case R.id.tv_from_username://回复者昵称
                        break;
                    case R.id.tv_to_username://被回复者昵称
                        break;
                    case R.id.tv_replay://回复的内容
                        break;
                }
                break;
        }
        ToastUtil.Info(item.getItemType() + "");
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

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

        ArrayList<MultiItemEntity> result = new ArrayList<>();

        List<FunshingItemBean> shareList = funsharingListBeans.getList();//分享的list

        for (FunshingItemBean shareItem : shareList) {//循环遍历分享list

            List<FunshingCommentItemBean> comments = shareItem.getShmlist();

            if (comments != null && comments.size() > 0) {
                for (FunshingCommentItemBean commentsBean : comments) {//循环遍历评论信息

                    shareItem.addSubItem(commentsBean);//-------------------------------添加评论

                    List<FunshingReplayItemBean> comments1 = commentsBean.getComments1();

                    if (comments1 != null && comments1.size() > 0) {

                        for (FunshingReplayItemBean commentsReplayBean : comments1) {//循环遍历回复信息

                            commentsBean.addSubItem(commentsReplayBean);//--------------添加回复
                        }
                    }

                }
            }
            result.add(shareItem);
        }

        totalPage = funsharingListBeans.getTotalPage();

        if (isRefreshing) {
            adapter.setNewData(result);
            refreshLayout.finishRefresh();
        } else {
            adapter.addData(result);
            refreshLayout.finishLoadmore();
        }
        adapter.expandAll();
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
        MultiItemEntity item = adapter.getItem(operatorPosition);
        int itemType = item.getItemType();
        switch (itemType) {
            case 0://点击的是分享类型 不会走这个
                FunshingItemBean funshingItemBeanBean = (FunshingItemBean) item;

                break;
            case 1://
                FunshingCommentItemBean commentItemBean = (FunshingCommentItemBean) item;
                break;
            case 2:
                FunshingReplayItemBean replayItemBean = (FunshingReplayItemBean) item;
                break;
        }
        if (fabulous == 0) {//评论
//            FunsharingListBean.ListBean item = adapter.getItem(operatorPosition);
//            List<FunsharingListBean.ListBean.CommentsBean> comments = item.getComments();
//
//            FunsharingListBean.ListBean.CommentsBean bean = new FunsharingListBean.ListBean
//                    .CommentsBean();
//
//            bean.setComment(funsharingCommnetAddBean.getComment());
//            bean.setShareid(funsharingCommnetAddBean.getShareid());
//            bean.setId(funsharingCommnetAddBean.getId());
//            bean.setUserid(funsharingCommnetAddBean.getUserid());
//            bean.setUsername(funsharingCommnetAddBean.getNickname());
//            bean.setNickname(funsharingCommnetAddBean.getNickname());
//
//            bean.setTouserid(funsharingCommnetAddBean.getTouserid());
//
//            bean.setStatus(funsharingCommnetAddBean.getStatus());
//            bean.setCreatedtime(funsharingCommnetAddBean.getCreatedtime());
//
//            bean.setParentid(funsharingCommnetAddBean.getParentid());

//            comments.add(bean);

            // TODO: 2017/12/14 这个刷新 暂时 用着 估计会有一点问题
            adapter.notifyDataSetChanged();
//            adapter.notifyItemInserted(po);
        } else if (fabulous == 1) {//回复
            switch (1) {
                case 0://分享的adapter //不动
                    break;
                case 1://点击的是评论的adapter
                case 2://点击的是回复的adapter
//
//                    FunsharingListBean.ListBean.CommentsBean bean = new FunsharingListBean
//                            .ListBean.CommentsBean();
//                    bean.setUserid(funsharingCommnetAddBean.getUserid());
//                    bean.setNickname(funsharingCommnetAddBean.getNickname());
//                    bean.setId(funsharingCommnetAddBean.getId());
//                    bean.setTonickname(funsharingCommnetAddBean.getTonickname());
//                    bean.setTouserid(funsharingCommnetAddBean.getTouserid());
//                    bean.setShareid(funsharingCommnetAddBean.getShareid());
//                    bean.setStatus(funsharingCommnetAddBean.getStatus());
//                    bean.setParentid(funsharingCommnetAddBean.getParentid());
//                    bean.setCreatedtime(funsharingCommnetAddBean.getCreatedtime());
//                    bean.setFabulous(String.valueOf(funsharingCommnetAddBean.getFabulous()));
//                    bean.setComment(funsharingCommnetAddBean.getComment());

//                    commentReplayAdapter.addData(bean);
//                    commentReplayAdapter.notifyDataSetChanged();
//                    adapter.notifyDataSetChanged();
                    break;
            }
        }


        //重置
        touserid = "";
        parentId = "";
        operatorPosition = -1;
        et_comment.setText("");
        closeCommentInput();
        //显示结果
    }

    @Override
    public void commentShareDel(String message) {

    }

    @Override
    public void shareLikeOrCancel(FunsharingLikeBean funsharingLikeBean) {
        if (operatorView != null) {
            operatorView.setText(funsharingLikeBean.getSl().size() + "");
        }
        operatorView = null;
    }

    @Override
    public void uploadHeader(FileUploadBean fileUploadBean) {

    }

    private void closeCommentInput() {
        ll_commint.setVisibility(View.GONE);
        inputMethodManager.hideSoftInputFromWindow(et_comment.getWindowToken(), 0);
    }

    /**
     * @param position  操作的位置
     * @param isComment 是否是评论 0评论 1回复
     * @param hintTxt   edittext 回复
     */
    private void openCommentInput(int position, int isComment, String hintTxt) {

        ToastUtil.Info(position + hintTxt);

        et_comment.setHint(hintTxt);
        operatorPosition = position;
        this.isComment = isComment;
        ll_commint.setVisibility(View.VISIBLE);
        et_comment.requestFocus();
        inputMethodManager.showSoftInput(et_comment, 0);

    }

    @Subscribe(tag = RxBus.TAG_UPDATE, thread = EventThread.MAIN_THREAD)
    public void rxrefresh(String msg) {
        if (msg.equals("refresh")) {
            Logger.i("开始刷新");
            ToastUtil.Success("分享成功");
            refreshLayout.autoRefresh();//第一次进入触发自动刷新，演示效果--然后在重新加载数据
        }
    }
}
