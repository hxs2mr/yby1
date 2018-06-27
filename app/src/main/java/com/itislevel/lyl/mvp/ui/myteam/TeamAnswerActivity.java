package com.itislevel.lyl.mvp.ui.myteam;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.itislevel.lyl.R;
import com.itislevel.lyl.app.App;
import com.itislevel.lyl.app.Constants;
import com.itislevel.lyl.base.RootActivity;
import com.itislevel.lyl.mvp.model.bean.MeiZiBean;
import com.itislevel.lyl.mvp.model.bean.MyTeamBean;
import com.itislevel.lyl.mvp.ui.chatkeyboardview.EmotionMainFragment;
import com.itislevel.lyl.mvp.ui.chatkeyboardview.EmotionUtils;
import com.itislevel.lyl.mvp.ui.chatkeyboardview.SpanStringUtils;
import com.itislevel.lyl.utils.ActivityUtil;
import com.itislevel.lyl.utils.GsonUtil;
import com.itislevel.lyl.utils.SharedPreferencedUtils;
import com.itislevel.lyl.utils.ToastUtil;
import com.itislevel.lyl.utils.rxbus.RxBus;
import com.itislevel.lyl.utils.rxbus.annotation.UseRxBus;
import com.lzy.ninegrid.ImageInfo;
import com.lzy.ninegrid.NineGridView;
import com.lzy.ninegrid.preview.NineGridViewClickAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

@UseRxBus
public class TeamAnswerActivity extends RootActivity<MyTeamPresenter> implements
        MyTeamContract.View, BaseQuickAdapter.OnItemClickListener, BaseQuickAdapter
        .OnItemChildClickListener {

    // et_content btn_save  tv_problem tv_answer tv_problem_username


//    @BindView(R.id.ll_replay_parent)
//    LinearLayout ll_replay_parent;
// TODO: 2018-02-06

    @BindView(R.id.tv_problem_username)
    TextView tv_problem_username;

    @BindView(R.id.tv_problem)
    TextView tv_problem;

    @BindView(R.id.tv_answer)
    TextView tv_answer;

    @BindView(R.id.et_content)
    EditText et_content;

    @BindView(R.id.tv_answer_temp)
    TextView tv_answer_temp;

    @BindView(R.id.tv_problem_time)
    TextView tv_problem_time;


    @BindView(R.id.ninegrid_share)
    NineGridView ninegrid_imgs;


    Bundle bundle = null;
    private String proble_userid;
    private String querstion_id;

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


    private InputMethodManager inputMethodManager;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_team_answer;
    }

    @Override
    protected void initEventAndData() {
        bundle = getIntent().getExtras();

        setToolbarTvTitle("顾问咨询详情");
//        MyTeamBean.ListBean item = this.adapter.getItem(position);
//
//        //提问者信息
//        bundle.putString("proble_username",item.getNickname());
//        bundle.putString("proble_userid",item.getUserid()+"");
//        bundle.putString("proble_content",item.getContent());
//
//        //回答信息 可能为空
//        List<MyTeamBean.ListBean.CommentsBean> comments = item.getComments();
//        if (comments!=null&&comments.size()>0){
//            bundle.putString("replay_content",comments.get(0).getComment());
//        }

        proble_userid = bundle.getString("proble_userid");
        querstion_id = bundle.getString("proble_querstion_id");

        tv_problem_username.setText(bundle.getString("proble_username")+"的提问");
        tv_problem_time.setText(bundle.getString("tv_problem_time"));

//        tv_problem.setText(bundle.getString("proble_content"));

        //            TextView tvConent=helper.getView(R.id.tv_problem);
        SpannableString emotionContent1 = SpanStringUtils.getEmotionContent(EmotionUtils
                .EMOTION_CLASSIC_TYPE, this, tv_problem, bundle.getString("proble_content"));
        tv_problem.setText(emotionContent1);


        // 图片 ninegrid_share
        List<ImageInfo> urlList = new ArrayList<>();
        ImageInfo imageInfo;
        //包含图片
        String imge = bundle.getString("img");


        if (!TextUtils.isEmpty(imge)) {
            String[] split = imge.split(",");
            for (String url : split) {
                if (!TextUtils.isEmpty(url) && url != null && url != "" && !url.equals(",")) {
                    imageInfo = new ImageInfo();
                    imageInfo.setBigImageUrl(Constants.IMG_SERVER_PATH + url.trim());
                    imageInfo.setThumbnailUrl(Constants.IMG_SERVER_PATH + url.trim());
                    urlList.add(imageInfo);
                }
            }
            ninegrid_imgs.setAdapter(new NineGridViewClickAdapter(App.getInstance(), urlList));

        }
        // 输入框
        mInputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        initViewListener();
        initEmotionMainFragment();


    }

    private void initViewListener() {


        et_content.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {//获取焦点
                    emotionMainFragment.hideEmotionLayoutoAndExtenLayout();
                } else {//失去焦点
                    emotionMainFragment.hideEmotionLayoutoAndExtenLayout();
                }
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        initEmotionViewAndListener();

    }

    @Override
    protected void onResume() {
        super.onResume();
        String replay_content = bundle.getString("replay_content");
        if (TextUtils.isEmpty(replay_content)) {
            tv_answer.setVisibility(View.GONE);
            tv_answer_temp.setVisibility(View.GONE);
//            ll_replay_parent.setVisibility(View.VISIBLE);
            emotionMainFragment.setRootViewIsVisiable(true);

        } else {
//            tv_answer.setText(replay_content);

//            TextView tvConent=helper.getView(R.id.tv_problem);
            SpannableString emotionContent = SpanStringUtils.getEmotionContent(EmotionUtils
                    .EMOTION_CLASSIC_TYPE, this, tv_answer, replay_content);
            tv_answer.setText(emotionContent);


//            ll_replay_parent.setVisibility(View.GONE);
            emotionMainFragment.setRootViewIsVisiable(false);

        }
    }

    private void initEmotionViewAndListener() {
        if (mInputView == null) {
            mInputView = getSupportFragmentManager().findFragmentById(R.id.fl_pannel).getView();
            mBtnSend = (Button) mInputView.findViewById(R.id.bar_btn_send);
            mIvExtend = (ImageView) mInputView.findViewById(R.id.bar_iv_extend);
            mEditText = (EditText) mInputView.findViewById(R.id.bar_edit_text);
            mEditText.addTextChangedListener(new EditTextChangeListener());//文本变化监听器

            mIvExtend.setVisibility(View.GONE);
//            mBtnSend.setVisibility(View.GONE);
//            emotionMainFragment.setEdittextGone();
//            mEditText.setVisibility(View.GONE);

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

                    String trim = et_content.getText().toString().trim();
                    if (TextUtils.isEmpty(trim)) {
                        ToastUtil.Info("请输入回复内容");
                        return;
                    }

                    Map<String, String> request = new HashMap<>();
                    request.put("token", SharedPreferencedUtils.getStr(Constants.USER_TOKEN));
                    request.put("usernum", SharedPreferencedUtils.getStr(Constants.USER_NUM));
                    request.put("adviserid", SharedPreferencedUtils.getStr(Constants.USER_ID));
                    request.put("userid", proble_userid);
                    request.put("comment", trim);
                    request.put("parentid", querstion_id);
                    request.put("questionid", querstion_id);
                    request.put("fabulous", "0");


                    mPresenter.teamReplay(GsonUtil.obj2JSON(request));

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

    @OnClick({R.id.btn_save, R.id.et_content})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.btn_save:
                String trim = et_content.getText().toString().trim();
                if (TextUtils.isEmpty(trim)) {
                    ToastUtil.Info("请输入回复内容");
                    return;
                }

                Map<String, String> request = new HashMap<>();
                request.put("token", SharedPreferencedUtils.getStr(Constants.USER_TOKEN));
                request.put("usernum", SharedPreferencedUtils.getStr(Constants.USER_NUM));
                request.put("adviserid", SharedPreferencedUtils.getStr(Constants.USER_ID));
                request.put("userid", proble_userid);
                request.put("comment", trim);
                request.put("parentid", querstion_id);
                request.put("questionid", querstion_id);
                request.put("fabulous", "0");


                mPresenter.teamReplay(GsonUtil.obj2JSON(request));
                break;
            case R.id.et_content:
                mEditText.requestFocus();
                break;
        }
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

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
    public void showContent(String msg) {

    }

    @Override
    public void loadData(List<MeiZiBean> meiZiBeans) {

    }

    @Override
    public void teamProblemList(MyTeamBean myTeamBean) {

    }

    @Override
    public void teamReplay(String message) {
        int anInt = SharedPreferencedUtils.getInt(Constants.BADGE_COUNT_TEAM, 0);

        anInt = anInt - 1;
        if (anInt <= 0) anInt = 0;
        SharedPreferencedUtils.setInt(Constants.BADGE_COUNT_TEAM, anInt);

        RxBus.getInstance().post(RxBus.getInstance().getTag(MyTeamActivity.class,
                RxBus.TAG_UPDATE), "refresh");
        ActivityUtil.getInstance().closeActivity(this);


    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
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
//                mIvExtend.setVisibility(View.GONE);

            } else {
                mBtnSend.setVisibility(View.VISIBLE);
//                mIvExtend.setVisibility(View.GONE);
            }

            et_content.setText(charSequence);
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
