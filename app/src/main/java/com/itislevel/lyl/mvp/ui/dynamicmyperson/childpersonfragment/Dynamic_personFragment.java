package com.itislevel.lyl.mvp.ui.dynamicmyperson.childpersonfragment;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.zhouwei.library.CustomPopWindow;
import com.google.android.flexbox.FlexboxLayout;
import com.itislevel.lyl.R;
import com.itislevel.lyl.adapter.DYPersonCommentAdapter;
import com.itislevel.lyl.adapter.DynamicGiftPersonAdapter;
import com.itislevel.lyl.adapter.DynamicPersonAdapter;
import com.itislevel.lyl.app.App;
import com.itislevel.lyl.app.Constants;
import com.itislevel.lyl.mvp.model.bean.BlessCartListBean;
import com.itislevel.lyl.mvp.model.bean.BlessGiftBean;
import com.itislevel.lyl.mvp.model.bean.DynamicCommnetAddBean;
import com.itislevel.lyl.mvp.model.bean.DynamicPersonBean;
import com.itislevel.lyl.mvp.model.bean.DynimacLinkBean;
import com.itislevel.lyl.mvp.model.bean.FollowGson;
import com.itislevel.lyl.mvp.model.bean.FollowListBean;
import com.itislevel.lyl.mvp.model.bean.FunshingReplayItemBean;
import com.itislevel.lyl.mvp.model.bean.GiftBean;
import com.itislevel.lyl.mvp.model.bean.ListPersonBean;
import com.itislevel.lyl.mvp.model.http.api.LYLApi;
import com.itislevel.lyl.mvp.ui.chatkeyboardview.EmotionMainFragment;
import com.itislevel.lyl.mvp.ui.main.dynamic.childfragment.DynamicReceiveGiftActivity;
import com.itislevel.lyl.mvp.ui.main.dynamic.childfragment.RootCancleFragment;
import com.itislevel.lyl.mvp.ui.main.dynamic.childfragment.dfind.DFindContract;
import com.itislevel.lyl.mvp.ui.main.dynamic.childfragment.dfind.DFindPresenter;
import com.itislevel.lyl.mvp.ui.user.LoginActivity;
import com.itislevel.lyl.utils.ActivityUtil;
import com.itislevel.lyl.utils.GsonUtil;
import com.itislevel.lyl.utils.SAToast;
import com.itislevel.lyl.utils.SharedPreferencedUtils;
import com.itislevel.lyl.utils.ToastUtil;
import com.itislevel.lyl.widget.DyNamicPersonGiftDialog;
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

import static com.itislevel.lyl.mvp.ui.dynamicmyperson.Dynamic_MypersonActivity.PERSON_USER_ID;

/**
 * Created by Administrator on 2018\5\7 0007.
 */

public class Dynamic_personFragment  extends RootCancleFragment<DFindPresenter> implements DFindContract.View,SwipeRefreshLayout.OnRefreshListener,
        BaseQuickAdapter.RequestLoadMoreListener, BaseQuickAdapter.OnItemClickListener,
        BaseQuickAdapter.OnItemChildClickListener{

    @BindView(R.id.refreshLayout)
    SwipeRefreshLayout refreshLayout;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @BindView(R.id.fl_pannel)
    FrameLayout fl_pannel ;

    @BindView(R.id.ll_parent)
    LinearLayout mLinearLayout;

    @BindView(R.id.person_total_num)
    AppCompatTextView person_total_num;

    private DynamicPersonAdapter adapter ;
    private int load_more =0;
    private int pageIndex = 1;//分页加载时 当前页是第几页
    private int totalPage = 0;//总页数
    private boolean isRefreshing = true;//当前是刷新还是加载

    private EmotionMainFragment emotionMainFragment;//表情面板
    private InputMethodManager inputMethodManager;

    // 评论与回复的点击事件 start
    public static CommentItemListener DPersonommentItemListener;

    public static  ReceiveGiftClickListener receivePersonGiftClickListener;

    private DYPersonCommentAdapter commentAdapter ;
    private int  comment_postion=0;
    private int  commentReplay_postion=0;
    String tonickname = "";
    private String reluserid;
    private EditText mEditText;//内容输入框
    private Button mBtnSend;//发送按钮
    private ImageView mIvEmotion;//表情按钮
    private ImageView mIvExtend;//扩展菜单按钮

    private String touserid = "";//回复时  回复对象的用户id
    private String parentId = ""; //回复时的父id
    private int whoAdapter = 0; // 哪一个adapter 0分享-1评论-2回复
    private int operatorPosition = -1;//点击的是哪一个
    private int isComment = 0;//评论传0，回复传1
    private View mInputView;
    private Fragment fragment;
    private FollowGson mNearDynamic  ;
    private boolean isfrist = true;
    private DyNamicPersonGiftDialog dynamincGiftDialog;
    //点赞
    private TextView operatorView = null;
    //点赞的列表
    FlexboxLayout fbl_parent = null;
    public static BlessGiftBean giftBean;
    private BlessCartListBean cartListBean = new BlessCartListBean();

    //礼品
    private String totalPrice;
    private String goodsDetail;
    private String blessid="";
    private String userid="";
    Bundle bundle = null;

    private  int del_postion=0;//记录删除的是哪条动态

    //个人中心
    private String headerUrl= Constants.IMG_SERVER_PATH;
    public static List<ListPersonBean.GiftListBean> person_giftBeans;
    ListPersonBean.GiftListBean giftListBean;
    private LinearLayoutCompat  gift_linear;
    private RecyclerView recyclerview_comment;
    private AppCompatTextView shou_gift_tv ;

    private  TextView tv_comment_input;
    private View comment_view;
    private View commentRe_view;
     private int click_p=0;
    private RecyclerView recycler_gift;
    private boolean isReady = false;
    private boolean isLoaded = false;//防止重复加载
    String share_name = "";//回复人的姓名

    String observer_name="";
    @Override
    protected int getLayoutId() {
        return R.layout.dy_psersonfragment;
    }
    @Override
    protected void initEventAndData() {
        giftBean = new BlessGiftBean();
        person_giftBeans = new ArrayList<>();//点击的礼物列表里的数据
        inputMethodManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        initAdapter();
        initViewListener();
        refreshLayout.setOnRefreshListener(this);

        isReady=true;
        refreshLayout.setRefreshing(true);//刷新效果
        lazyLoad();
            //获取礼品list
    }
    /**
     * 初始化adapter
     */
    private void initAdapter() {
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        ((SimpleItemAnimator)recyclerView.getItemAnimator()).setSupportsChangeAnimations(false);
        recyclerView.setLayoutManager(manager);
        //注册成为订阅者
        refreshLayout.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_orange_dark,
                android.R.color.holo_red_dark);
        if (adapter == null){
            adapter = new DynamicPersonAdapter(R.layout.item_funsharing, getActivity());
            adapter.setOnItemClickListener(this);
            adapter.setOnItemChildClickListener(this);
//            adapter.openLoadAnimation();
            adapter.openLoadAnimation(BaseQuickAdapter.SCALEIN); //切换动画
            adapter.setEnableLoadMore(false);

//        adapter.isFirstOnly(false);//动画默认只执行一次
//        adapter.setNotDoAnimationCount(count);//设置不显示动画数量
            //setNotDoAnimationCount 可以设置第一屏不展示动画
            //打开或关闭加载 adapter.setEnableLoadMore(boolean);
            //预加载  当列表滑动到倒数第N个Item的时候(默认是1)回调onLoadMoreRequested方法  adapter.setPreLoadNumber(int);
            //设置自定义加载布局 adapter.setLoadMoreView(new CustomLoadMoreView());
            // 滑动最后一个Item的时候回调onLoadMoreRequested方法
            //默认第一次加载会进入回调，如果不需要可以配置： adapter.disableLoadMoreIfNotFullPage();
//            layoutManager.setStackFromEnd(true);
            recyclerView.setAdapter(adapter);
            View emptyView = View.inflate(getContext(), R.layout.partial_empty_view, null);
            adapter.setEmptyView(emptyView);
        }
    }
    private void loadData() {
        Map<String, String> request = new HashMap<>();
        request.put("token", SharedPreferencedUtils.getStr(Constants.USER_TOKEN));
        request.put("usernum", SharedPreferencedUtils.getStr(Constants.USER_NUM));
        request.put("userid", PERSON_USER_ID);
        request.put("pageIndex", pageIndex+"");
        request.put("pageSize", Constants.PAGE_NUMBER5 + "");

        mPresenter.dynamicList(GsonUtil.obj2JSON(request));
    }

    private void initViewListener() {
        DPersonommentItemListener = new CommentItemListener();
        receivePersonGiftClickListener = new ReceiveGiftClickListener();
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

    private void closeCommentInput() {
        inputMethodManager.hideSoftInputFromWindow(mEditText.getWindowToken(), 0);
        if (fl_pannel != null) {
            fl_pannel.setVisibility(View.GONE);
        }
        if(emotionMainFragment!=null){
            emotionMainFragment.hideEmotionLayoutoOrExtenLayout();//隐藏表情面板
        }
    }
    @Override
    public void onStart() {
        super.onStart();
        initEmotionViewAndListener();
    }


    private void initEmotionViewAndListener(){
        if (mInputView == null){
            fragment =  getChildFragmentManager().findFragmentById(R.id.fl_pannel);
            mInputView  = fragment.getView();
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
                    closeCommentInput();
                    Editable content = mEditText.getText();
                    if (TextUtils.isEmpty(content)) {
                        ToastUtil.Warning("内容不能为空");
                    } else {
                        Map<String, String> request = new HashMap<>();
                        request.put("token", SharedPreferencedUtils.getStr(Constants.USER_TOKEN));
                        request.put("usernum", SharedPreferencedUtils.getStr(Constants.USER_NUM));
                        request.put("userid", SharedPreferencedUtils.getStr(Constants.USER_ID));

                        String shareid = "-1";
                        switch (whoAdapter) {
                            case 0:
                                shareid = adapter.getItem(operatorPosition).getId()+"";
                                observer_name =  SharedPreferencedUtils.getStr(Constants.USER_NICK_NAME);
                                break;
                            case 1:
                                shareid = commentAdapter.getItem(operatorPosition).getDynamicid()+"";
                                break;
                        }

                        // TODO: 2017/12/16 此处可能有坑
                        request.put("comment", content.toString());
                        request.put("dynamicid", shareid);
                        request.put("observer", observer_name);
                        request.put("parentid", parentId);
                        request.put("answerer", share_name);
                        request.put("fabulous", isComment + "");
                        request.put("touserid", touserid);
                        request.put("reluserid", reluserid);
                        request.put("relnickname", SharedPreferencedUtils.getStr(Constants.USER_NICK_NAME));
                        request.put("relheadimg",request.put("relheadimg", SharedPreferencedUtils.getStr(Constants.USER_HEADER,"").equals("")?"hxs.jpg":SharedPreferencedUtils.getStr(Constants.USER_HEADER)));
                        String imge="";
                        if (adapter.getItem(operatorPosition) != null)
                        {
                            imge = adapter.getItem(operatorPosition).getImge();
                            if (!TextUtils.isEmpty(imge)) {
                                String[] split = imge.split(",");
                                request.put("dyimgorct", "1@"+split[0]);
                            }else {
                                request.put("dyimgorct", "0@"+adapter.getItem(operatorPosition).getContent());
                            }

                        }else {
                            request.put("dyimgorct", "0@"+SharedPreferencedUtils.getStr(Constants.USER_HEADER,""));
                        }
                        mPresenter.addDynamicComment(GsonUtil.obj2JSON(request));
                    }
                }
            });
        }
    }
    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        ListPersonBean item = this.adapter.getItem(position);
        operatorPosition = position;
        fbl_parent = (FlexboxLayout) adapter.getViewByPosition(position,R.id.fbl_parent);
        gift_linear = (LinearLayoutCompat) adapter.getViewByPosition(position,R.id.gift_linear);
        recyclerview_comment = (RecyclerView) adapter.getViewByPosition(position,R.id.recyclerview_comment);
        shou_gift_tv = (AppCompatTextView) adapter.getViewByPosition(position,R.id.shou_gift_tv);
        tv_comment_input = (TextView) adapter.getViewByPosition(position,R.id.tv_comment_input);
        recycler_gift = (RecyclerView) adapter.getViewByPosition(position,R.id.recycler_gift);
        boolean islogin = SharedPreferencedUtils.getBool("islogin", false);
        if (!islogin) {
            ActivityUtil.getInstance().openActivity(getActivity(), LoginActivity.class);
            return;
        }
        switch (view.getId()) {
            case R.id.share_guan_linear://关注
                break;
            //分享item的
            case R.id.tv_like_num://点赞
                closeCommentInput();
                operatorView = (TextView) view;
                Map<String, String> request = new HashMap<>();
                request.put("token", SharedPreferencedUtils.getStr(Constants.USER_TOKEN));
                request.put("usernum", SharedPreferencedUtils.getStr(Constants.USER_NUM));
                request.put("userid", SharedPreferencedUtils.getStr(Constants.USER_ID));
                request.put("id", this.adapter.getItem(position).getId() + "");
                request.put("reluserid", this.adapter.getItem(position).getUserid() + "");
                request.put("dynamicid", this.adapter.getItem(position).getId()+"");
                request.put("relnickname", SharedPreferencedUtils.getStr(Constants.USER_NICK_NAME));
                request.put("relheadimg",request.put("relheadimg", SharedPreferencedUtils.getStr(Constants.USER_HEADER,"").equals("")?"hxs.jpg":SharedPreferencedUtils.getStr(Constants.USER_HEADER)));
                String imge = this.adapter.getItem(position).getImge();
                if (!TextUtils.isEmpty(imge)) {
                    String[] split = imge.split(",");
                    request.put("dyimgorct", "1@"+split[0]);
                }else {
                    request.put("dyimgorct", "0@"+this.adapter.getItem(position).getContent());
                }
                mPresenter.dynamicdianzan(GsonUtil.obj2JSON(request));

                break;
            case R.id.tv_comment_num://评论
                click_p=1;
                mEditText.setHint("评论");
                reluserid = this.adapter.getItem(position).getUserid() + "";
                openCommentInput(adapter, 0, position, 0, Integer.parseInt(reluserid)) ;
                break;
            case R.id.tv_comment_input://评论
                click_p =0;
                mEditText.setHint("评论");
                reluserid =  this.adapter.getItem(position).getUserid() + "";
                openCommentInput(adapter, 0, position, 0, Integer.parseInt(reluserid)) ;
                break;
            case R.id.iv_header:
//                closeCommentInput();
                break;

            case R.id.tv_title:
//                closeCommentInput();
                break;
            case R.id.tv_share:
//                closeCommentInput();
                // share(this.adapter.getItem(position).getId());
                share(this.adapter.getItem(position).getId());
                break;
            case R.id.song2_iv:

                blessid =item.getId()+"";
                userid = item.getUserid()+"";
                int size = giftBean.getList().size();
                for(int i = 0 ; i < size ; i++ ){
                    giftBean.getList().get(i).setOnclick(true);
                }
                openSendGiftDialog();
                break;
            case R.id.tv_delete:
                del_postion =position;
                show_clear_cart(item.getId());
                break;
            case R.id.song2_iv_zhu:
                blessid =item.getId()+"";
                userid = item.getUserid()+"";
                int size1 = giftBean.getList().size();
                for(int i = 0 ; i < size1 ; i++ ){
                    giftBean.getList().get(i).setOnclick(true);
                }
                openSendGiftDialog();
                break;
            case R.id.genduo_linear:
                Bundle bundle = new Bundle();
                bundle.putString("model","person");
                person_giftBeans = this.adapter.getData().get(position).getGiftList();
                ActivityUtil.getInstance().openActivity(getActivity(),DynamicReceiveGiftActivity.class,bundle);

                break;
        }

    }

    private void openSendGiftDialog() {
        dynamincGiftDialog = new DyNamicPersonGiftDialog(this, getContext(),null, giftBean, cartListBean);
        dynamincGiftDialog.show();
    }
        /*
    * 送礼区
    * */

    private void loadGiftList() {
        Map<String, String> request = new HashMap<>();
        request.put("token", SharedPreferencedUtils.getStr(Constants.USER_TOKEN));
        request.put("usernum", SharedPreferencedUtils.getStr(Constants.USER_NUM));

        mPresenter.happyGiftList(GsonUtil.obj2JSON(request));
    }

    public void dialogGenerateOrder(int location){//创建订单

        loadingDialog.show();
        loadingDialog.setLabel(Constants.CART_GENERATE_ORDER_TXT);

        totalPrice = giftBean.getList().get(location).getSellprice()+"";
        goodsDetail = giftBean.getList().get(location).getGiftname()+"";
        int bus_id = Integer.parseInt( SharedPreferencedUtils.getStr(Constants.USER_ID));
        Map<String, String> request = new HashMap<>();

      /*  request.put("token", SharedPreferencedUtils.getStr(Constants.USER_TOKEN));
        request.put("usernum", SharedPreferencedUtils.getStr(Constants.USER_NUM));
        request.put("buyuserid", SharedPreferencedUtils.getStr(Constants.USER_ID));
        request.put("moduleid", blessid);
        request.put("modelename", "xishi");
        request.put("receiveuserid", userid);
        request.put("shopcartstr", carstr);
        request.put("type", Constants.CART_TEAM_BLESS);
*/      String nick_name =SharedPreferencedUtils.getStr(Constants.USER_NICK_NAME);
        request.put("token", SharedPreferencedUtils.getStr(Constants.USER_TOKEN));
        request.put("usernum", SharedPreferencedUtils.getStr(Constants.USER_NUM));
        request.put("buyuserid", bus_id+"");
        request.put("modelename", Constants.CART_MODEL_DYNAMIC);
        request.put("moduleid", blessid);
        request.put("receiveuserid", this.adapter.getData().get(operatorPosition).getUserid()+"");
        request.put("goodsid", giftBean.getList().get(location).getGiftid() + "");
        request.put("cateid", giftBean.getList().get(location).getFirstcateid() + "");
        request.put("price", giftBean.getList().get(location).getSellprice() );
        request.put("count", "1");
        request.put("goodsname", giftBean.getList().get(location).getGiftname() );
        request.put("imgurl", giftBean.getList().get(location).getImgurl() );
        request.put("buyusername",nick_name);
        giftListBean = new ListPersonBean.GiftListBean(giftBean.getList().get(location).getGiftname(),bus_id
                ,1,giftBean.getList().get(location).getGiftid(), giftBean.getList().get(location).getGiftname(),giftBean.getList().get(location).getImgurl());
        mPresenter.immediateOrder(GsonUtil.obj2JSON(request));
    }

    public void show_clear_cart(int id){
        //实例化建造者
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        //设置警告对话框的标题
        builder.setTitle("删除动态");
        //设置警告显示的图片
//        builder.setIcon(android.R.drawable.ic_dialog_alert);
        //设置警告对话框的提示信息
        builder.setMessage("确认要删除这条动态吗？");
        //设置”正面”按钮，及点击事件
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Map<String, String> request = new HashMap<>();
                request.put("token", SharedPreferencedUtils.getStr(Constants.USER_TOKEN));
                request.put("usernum", SharedPreferencedUtils.getStr(Constants.USER_NUM));
                request.put("id",id+"");
                mPresenter.delDynamicInfo(GsonUtil.obj2JSON(request));
            }
        });
        //设置“反面”按钮，及点击事件
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        //设置“中立”按钮，及点击事件
        builder.setNeutralButton("等等看吧", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        //显示对话框
        builder.show();
    }
    @Override
    public void firstPage(FollowListBean lylResponse) {

    }

    @Override
    public void dynamicdianzan(DynimacLinkBean dynimacLinkBean) {
        ListPersonBean item = this.adapter.getItem(operatorPosition);
        String name = SharedPreferencedUtils.getStr(Constants.USER_NICK_NAME);
        if (operatorView != null) {
//            operatorView.setText(funsharingLikeBean.getSl().size() + "");
            int oldNum = Integer.parseInt(operatorView.getText().toString());
            int newNUm = dynimacLinkBean.getSl().size();
            if (item.getIsdianzan()){//也点赞
                int size = fbl_parent.getFlexItemCount();//获取当前布局里面的全部个数
                int location_name = -1 ;
                for (int  i =0 ; i < size ; i++)
                {
                    TextView textView = (TextView) fbl_parent.getFlexItemAt(i);
                    if(textView.getText().toString().contains(name))//计算出那个名称相等于这个名字  点赞取消ppostion
                    {
                        location_name =  i ;
                    }
                }
                if(item.getNmpointlist()==null||item.getNmpointlist().size()==1||item.getNmpointlist().size()==0)
                {
                    fbl_parent.removeAllViews();
                }else {
                    if(location_name!=-1)
                    {
                        if (item.getNmpointlist() != null && item.getNmpointlist().size() > 0)
                        {
                            fbl_parent.removeViewAt(location_name);
                        }
                    }
                }
                this.adapter.getData().get(operatorPosition).setIspoint("0");
                Drawable rightDrawable = getResources().getDrawable(R.mipmap.icon_like_gray);
                rightDrawable.setBounds(0, 0, rightDrawable.getMinimumWidth(), rightDrawable
                        .getMinimumHeight());
                operatorView.setCompoundDrawables(rightDrawable, null, null, null);
                this.adapter.getData().get(operatorPosition).setIsdianzan(false);
            } else {//点赞
                this.adapter.getData().get(operatorPosition).setIspoint("1");
                if( fbl_parent.getFlexItemCount()==0)
                {
                    TextView likeICONTxt = new TextView(App.getInstance());
                    Drawable rightDrawable = mActivity.getResources().getDrawable(R.mipmap
                            .icon_like_tip);
                    rightDrawable.setBounds(0, 0, 40, 40);
                    likeICONTxt.setTextSize(14);
                    likeICONTxt.setCompoundDrawables(rightDrawable, null, null, null);
                    likeICONTxt.setText("");
                    likeICONTxt.setCompoundDrawablePadding(10);
                    likeICONTxt.setTextColor(Color.parseColor("#034b71"));
                    fbl_parent.addView(likeICONTxt);
                }
                Drawable rightDrawable = getResources().getDrawable(R.mipmap.icon_like);
                rightDrawable.setBounds(0, 0, rightDrawable.getMinimumWidth(), rightDrawable
                        .getMinimumHeight());
                operatorView.setCompoundDrawables(rightDrawable, null, null, null);
                TextView textView = new TextView(App.getInstance());
                textView.setText(name+"、");
                textView.setTextSize(14);
                textView.setTextColor(Color.parseColor("#034b71"));
                textView.setPadding(5, 5, 5, 5);
                fbl_parent.addView(textView);
                this.adapter.getData().get(operatorPosition).setIsdianzan(true);
            }
            this.adapter.getData().get(operatorPosition).setFabulousnumber(newNUm);
            operatorView.setText(newNUm+"");
            List<String> s2 = dynimacLinkBean.getS2();
            this.adapter.getData().get(operatorPosition).setNmpointlist(s2);
        }
        operatorView = null;
    }

    @Override
    public void dynamicfollow(String msg) {
        SAToast.makeText(getContext(),"关注成功").show();
    }
    @Override
    public void addDynamicComment(DynamicCommnetAddBean dynamicCommnetAddBean) {
        int fabulous = dynamicCommnetAddBean.getFabulous();
        //重置
        touserid = "";
        parentId = "";
        reluserid = "";
        mEditText.setText("");
        if(fabulous == 0)
        {
            SAToast.makeText(getContext(),""+operatorPosition).show();
            ListPersonBean.ListCommentItemBean commentItemBean = new ListPersonBean.ListCommentItemBean(dynamicCommnetAddBean.getId(),dynamicCommnetAddBean.getCreatedtime(),
                    dynamicCommnetAddBean.getUserid(),dynamicCommnetAddBean.getShareid(),dynamicCommnetAddBean.getTouserid(),
                    dynamicCommnetAddBean.getAnswerer(),dynamicCommnetAddBean.getParentid(),dynamicCommnetAddBean.getComment(),dynamicCommnetAddBean.getObserver(),
                    dynamicCommnetAddBean.getFabulous());
            DYPersonCommentAdapter   adapter = (DYPersonCommentAdapter) recyclerview_comment.getAdapter();
            adapter.addData(commentItemBean);
        }else if (fabulous == 1){
            if (whoAdapter == 1){
                ListPersonBean.ListCommentItemBean commentItemBean = new ListPersonBean.ListCommentItemBean(dynamicCommnetAddBean.getId(),dynamicCommnetAddBean.getCreatedtime(),
                        dynamicCommnetAddBean.getUserid(),dynamicCommnetAddBean.getShareid(),dynamicCommnetAddBean.getTouserid(),
                        dynamicCommnetAddBean.getAnswerer(),dynamicCommnetAddBean.getParentid(),dynamicCommnetAddBean.getComment(),dynamicCommnetAddBean.getObserver(),
                        dynamicCommnetAddBean.getFabulous());
                commentAdapter.addData(comment_postion+1,commentItemBean);
            }
        }
        operatorPosition = -1;
        //显示结果
    }

    @Override
    public void delDynamicComment(String msg) {

    }

    @Override
    public void happyOrderAdd(String blessOrderBean) {

    }

    @Override
    public void happyCartAdd(String message) {

    }

    @Override
    public void happyGiftList(List<GiftBean> blessGiftBean) {
        giftBean.setList(blessGiftBean);
    }

    @Override
    public void immediateOrder(String blessOrderBean) {
        if(shou_gift_tv.getVisibility()==View.GONE)
        {
            shou_gift_tv.setVisibility(View.VISIBLE);
            gift_linear.setVisibility(View.VISIBLE);
        }
        SAToast.makeText(getContext(),"送礼成功").show();
        DynamicGiftPersonAdapter detailGiftAdapter = (DynamicGiftPersonAdapter) recycler_gift.getAdapter();
        loadingDialog.dismiss();
        dynamincGiftDialog.dismiss();
        this.adapter.getData().get(operatorPosition).getGiftList().add(giftListBean);
        if(detailGiftAdapter.getData().size()>0)
        {
            detailGiftAdapter.addData(0,giftListBean);
            recycler_gift.scrollToPosition(0);//移动到第一个
        }else {
            detailGiftAdapter.addData(giftListBean);
        }
    }

    @Override
    public void delDynamicInfo(String msg) {
        this.adapter.remove(del_postion);
        this.adapter.notifyDataSetChanged();
    }

    @Override
    public void dynamicList(DynamicPersonBean bean) {//获取动态列表返回的数据
        load_more++;
        if(load_more==1)
        {
            adapter.setOnLoadMoreListener(this,recyclerView);//设置加载更多
        }
        //返回的数据 第一次
        if ( bean.getList() == null || bean
                .getList().size() <= 0) {
            View emptyView = View.inflate(getContext(), R.layout.partial_empty_view, null);
            TextView viewById = emptyView.findViewById(R.id.tv_message);
            adapter.setEmptyView(emptyView);
        }
        person_total_num.setText("全部动态("+bean.getAllRow()+")");
        int size = bean.getList().size();
        List<FunshingReplayItemBean> comments1 = new ArrayList<>();
        int end_j = 0;
        totalPage = bean.getTotalPage();
        if (isRefreshing) {
            adapter.getData().clear();
            adapter.setNewData(bean.getList());
            adapter.notifyDataSetChanged();
            refreshLayout.setRefreshing(false);
        } else {
            adapter.addData(bean.getList());
            adapter.loadMoreComplete();
        }
    }
    /**
     * 评论item的点击事件
     */
    class CommentItemListener implements BaseQuickAdapter.OnItemClickListener
            , BaseQuickAdapter.OnItemChildClickListener{
        @Override
        public void onItemChildClick(BaseQuickAdapter adapter, View view, final int position) {
            comment_view  = view;
            SAToast.makeText(getContext(),"点击了"+position).show();
            commentAdapter = (DYPersonCommentAdapter) adapter;
            comment_postion = position;
            final ListPersonBean.ListCommentItemBean item = commentAdapter.getItem(position);

            tonickname = item.getObserver();
            int local_userid=0;
            if(SharedPreferencedUtils.getStr(Constants.USER_ID)!=null)
            {
                local_userid = Integer.parseInt(SharedPreferencedUtils.getStr(Constants.USER_ID));
            }
            if (local_userid == item.getUserid()){//点击的是自己回复的
                switch (view.getId()) {
                    case R.id.tv_comment_content:
                    case R.id.tv_add_comment:
                        if(item.getAnswerer().equals("")||(item.getAnswerer()==null)){
                            if (item.getObserver().equals(SharedPreferencedUtils.getStr(Constants
                                    .USER_NICK_NAME)))
                            {
                                View popView = View.inflate(getContext(), R.layout
                                        .item_pop_del, null);
                                final CustomPopWindow popWindow = new CustomPopWindow.PopupWindowBuilder
                                        (getContext())
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
                                        request.put("id", item.getIds());
                                        request.put("fabulous", item.getFabulous());
                                        request.put("userid", item.getUserid());

                                        mPresenter.delDynamicComment(GsonUtil.obj2JSON(request));
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
                            }
                        }else if (item.getAnswerer().equals(SharedPreferencedUtils.getStr(Constants
                                .USER_NICK_NAME))) {//删除
//                            closeCommentInput();
                            View popView = View.inflate(getContext(), R.layout
                                    .item_pop_del, null);
                            final CustomPopWindow popWindow = new CustomPopWindow.PopupWindowBuilder
                                    (getContext())
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
                                    request.put("id", item.getIds());
                                    request.put("fabulous", item.getFabulous());
                                    request.put("userid", item.getUserid());

                                    mPresenter.delDynamicComment(GsonUtil.obj2JSON(request));
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
                        }else {
                            reluserid=item.getTouserid()+"";
                            mEditText.setHint("回复:"+item.getAnswerer());
                            observer_name =item.getAnswerer();
                            share_name = SharedPreferencedUtils.getStr(Constants.USER_NICK_NAME);
                            touserid = item.getUserid() + "";
                            parentId = item.getIds() + "";
                            openCommentInput(adapter, 1, position, 1,item.getIds()+"" == "" ?
                                    -18 : (int) item.getIds());
                        }
                        break;
                }
            } else {//点击的是别人回复的
                if (item.getAnswerer().equals(SharedPreferencedUtils.getStr(Constants.USER_NICK_NAME)))
                {
                    View popView = View.inflate(getContext(), R.layout
                            .item_pop_del, null);
                    final CustomPopWindow popWindow = new CustomPopWindow.PopupWindowBuilder
                            (getContext())
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
                            request.put("id", item.getIds());
                            request.put("fabulous", item.getFabulous());
                            request.put("userid", item.getUserid());

                            mPresenter.delDynamicComment(GsonUtil.obj2JSON(request));
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
                }else{
                    share_name = SharedPreferencedUtils.getStr(Constants.USER_NICK_NAME);
                    if( item.getAnswerer().equals("")||item.getAnswerer()==null)
                    {
                        mEditText.setHint("回复:" + item.getObserver());
                        observer_name =item.getObserver();
                        reluserid=item.getUserid()+"";
                        touserid = item.getUserid() + "";
                    }else {
                        mEditText.setHint("回复:" + item.getAnswerer());
                        observer_name =item.getAnswerer();
                        reluserid=item.getUserid()+"";
                        touserid = item.getTouserid() + "";
                    }
                    parentId = item.getIds() + "";
                    openCommentInput(adapter, 1, position, 1, (int) item.getId());
                }
            }
        }
        @Override
        public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//            closeCommentInput();
            SAToast.makeText(getContext(),"点击了11").show();
        }
    }
    /**
     * 初始化表情面板
     */
    public void initEmotionMainFragment(){
        //构建传递参数
        Bundle bundle = new Bundle();
        //绑定主内容编辑框
        bundle.putBoolean(EmotionMainFragment.BIND_TO_EDITTEXT, true);
        //隐藏控件
        bundle.putBoolean(EmotionMainFragment.HIDE_BAR_EDITTEXT_AND_BTN, false);
        //替换fragment   //创建修改实例
        emotionMainFragment = EmotionMainFragment.newInstance(EmotionMainFragment.class, bundle);
        emotionMainFragment.bindToContentView(mLinearLayout);
        FragmentTransaction transaction =getChildFragmentManager().beginTransaction();
        transaction.replace(R.id.fl_pannel, emotionMainFragment);
        transaction.addToBackStack(null);
        //提交修改
        transaction.commit();
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
        fl_pannel.setVisibility(View.VISIBLE);
        mEditText.requestFocus();
        inputMethodManager.showSoftInput(mEditText, 0);
        if(emotionMainFragment!=null)
        {
            emotionMainFragment.hideEmotionLayoutoAndExtenLayout();//隐藏表情面板
        }
        this.whoAdapter = whoAdapter;
        switch (whoAdapter) {
            case 0://分享的tiem
                Logger.w("分享的tiem:" + position);
                this.adapter = (DynamicPersonAdapter) adapter;
                int[] location2 = new int[2];
                tv_comment_input.getLocationOnScreen(location2);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        int[] location_et1 = new int[2];
                            mEditText.getLocationOnScreen(location_et1);
                            recyclerView.smoothScrollBy(0,location2[1]-location_et1[1]);
                    }
                },500);
                break;
            case 1://评论的item
                Logger.w("评论的item:" + position);
                commentAdapter = (DYPersonCommentAdapter) adapter;
                int[] location1= new int[2];
                comment_view.getLocationOnScreen(location1);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        int[] location_et1 = new int[2];
                        mEditText.getLocationOnScreen(location_et1);
                        recyclerView.smoothScrollBy(0,location1[1]+(comment_view.getHeight())*2-location_et1[1]);
                    }
                },500);
                break;
        }
        operatorPosition = position;
        this.isComment = isComment;

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
    @Override
    protected void lazyLoad() {
        if (!isReady || !isVisible || isLoaded) {
            return;
        }
        isLoaded = true;
        loadData();
        loadGiftList();
    }

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        operatorPosition = position;
    }

    @Override
    public void useNightMode(boolean isNight) {

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
            View emptyView = View.inflate(getContext(), R.layout.partial_empty_view, null);
            adapter.setEmptyView(emptyView);
        }

        if (refreshLayout.isRefreshing()) {
            refreshLayout.setRefreshing(false);
        }
        if (adapter.isLoadMoreEnable()) {
            adapter.loadMoreFail();
        }
    }

    public class ReceiveGiftClickListener implements   BaseQuickAdapter.OnItemClickListener
            , BaseQuickAdapter.OnItemChildClickListener{

        @Override
        public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
            Bundle bundle = new Bundle();
            bundle.putString("model","person");
            person_giftBeans = adapter.getData();
            ActivityUtil.getInstance().openActivity(getActivity(),DynamicReceiveGiftActivity.class,bundle);

        }

        @Override
        public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
            Bundle bundle = new Bundle();
            bundle.putString("model","person");
            person_giftBeans = adapter.getData();
            ActivityUtil.getInstance().openActivity(getActivity(),DynamicReceiveGiftActivity.class,bundle);
        }
    }
    private void share(int id) {
        if (Build.VERSION.SDK_INT >= 23) {
            String[] mPermissionList = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.CALL_PHONE,
                    Manifest.permission.READ_LOGS, Manifest.permission.READ_PHONE_STATE, Manifest
                    .permission.READ_EXTERNAL_STORAGE, Manifest.permission.SET_DEBUG_APP,
                    Manifest.permission.SYSTEM_ALERT_WINDOW, Manifest.permission.GET_ACCOUNTS,
                    Manifest.permission.WRITE_APN_SETTINGS};
            ActivityCompat.requestPermissions(getActivity(), mPermissionList, 666);

//            new ShareAction(this)
//                    .withText("hello")
//                    .setDisplayList(SHARE_MEDIA.QQ,SHARE_MEDIA.WEIXIN)
//                    .setCallback(shareListener)
//                    .open();

//            UMWeb web = new UMWeb("https://juejin.im/post/5a692377518825734e3e71ab");
            UMWeb web = new UMWeb(LYLApi.HOST_SHARE+"share/index.html#/lequ?shareId="+id);

            web.setTitle("乐趣分享");//标题
            UMImage umImage = new UMImage(getContext(), R.mipmap.ic_launcher);
            web.setThumb(umImage);  //缩略图
            web.setDescription("友邦友-乐趣分享");//描述

            new ShareAction(getActivity())
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
            adapter.loadMoreEnd();
            return;
        } else {
            isRefreshing = false;
            loadData();
        }
    }

}
