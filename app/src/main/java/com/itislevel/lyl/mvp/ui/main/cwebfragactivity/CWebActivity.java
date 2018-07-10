package com.itislevel.lyl.mvp.ui.main.cwebfragactivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutCompat;
import android.text.Editable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.itislevel.lyl.R;
import com.itislevel.lyl.adapter.DFindCommentAdapter;
import com.itislevel.lyl.adapter.DFindCommentReplayAdapter;
import com.itislevel.lyl.adapter.DynamicFindAdapter;
import com.itislevel.lyl.app.Constants;
import com.itislevel.lyl.base.RootActivity;
import com.itislevel.lyl.base.RootFragment;
import com.itislevel.lyl.mvp.model.bean.CFChildBean;
import com.itislevel.lyl.mvp.model.bean.CFPinBean;
import com.itislevel.lyl.mvp.model.bean.CWPinBean;
import com.itislevel.lyl.mvp.model.bean.DynamicBean;
import com.itislevel.lyl.mvp.model.http.api.LYLApi;
import com.itislevel.lyl.mvp.ui.chatkeyboardview.EmotionMainFragment;
import com.itislevel.lyl.mvp.ui.chatkeyboardview.EmotionUtils;
import com.itislevel.lyl.mvp.ui.chatkeyboardview.SpanStringUtils;
import com.itislevel.lyl.mvp.ui.main.MainActivity;
import com.itislevel.lyl.mvp.ui.main.childfragment.ChildContract;
import com.itislevel.lyl.mvp.ui.main.childfragment.ChildPresenter;
import com.itislevel.lyl.mvp.ui.main.dynamic.childfragment.dfind.DFindFragment;
import com.itislevel.lyl.mvp.ui.user.LoginActivity;
import com.itislevel.lyl.mvp.web.WebViewInitializer;
import com.itislevel.lyl.utils.ActivityUtil;
import com.itislevel.lyl.utils.GsonUtil;
import com.itislevel.lyl.utils.SAToast;
import com.itislevel.lyl.utils.SharedPreferencedUtils;
import com.itislevel.lyl.utils.ToastUtil;
import com.orhanobut.logger.Logger;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;
import com.vondear.rxtools.activity.AndroidBug5497Workaround;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

import static com.itislevel.lyl.utils.DateUtil.formatTime2String;
import static java.lang.System.currentTimeMillis;

/**
 * Created by Administrator on 2018\5\16 0016.加载webview
 */

public class CWebActivity extends RootActivity<ChildPresenter> implements ChildContract.View{
    //初始化webview
    @BindView(R.id.cf_webview)
    WebView webView;

    @BindView(R.id.cf_kaopu_lienar)
    LinearLayoutCompat cf_kaopu_lienar;//靠谱


    @BindView(R.id.cf_wugan_linear)
    LinearLayoutCompat cf_wugan_linear;//无感

    @BindView(R.id.cf_kaopu_num)
    AppCompatTextView cf_kaopu_num;

    @BindView(R.id.cf_wugan_num)
    AppCompatTextView cf_wugan_num;

    @BindView(R.id.cf_pin_linear1)
    LinearLayoutCompat cf_pin_linear1;//第一条评论

    @BindView(R.id.cf_pin_linear2)
    LinearLayoutCompat cf_pin_linear2;//第二条评论

    @BindView(R.id.cf_pin_image1)
    CircleImageView  cf_pin_image1;//头像

    @BindView(R.id.cf_pin_image2)
    CircleImageView  cf_pin_image2;//头像

    @BindView(R.id.cf_pin_name1)
    AppCompatTextView cf_pin_name1;//姓名
    @BindView(R.id.cf_pin_name2)
    AppCompatTextView cf_pin_name2;//第二条评论

    @BindView(R.id.cf_time_name1)
    AppCompatTextView cf_time_name1;//时间
    @BindView(R.id.cf_time_name2)
    AppCompatTextView cf_time_name2;//时间

    @BindView(R.id.cf_coment1)
    AppCompatTextView cf_coment1;//内容

    @BindView(R.id.cf_coment2)
    AppCompatTextView cf_coment2;//内容

    @BindView(R.id.cf_dianzan_linear1)
    LinearLayoutCompat cf_dianzan_linear1;//点赞

    @BindView(R.id.cf_dianzan_linear2)
    LinearLayoutCompat cf_dianzan_linear2;//点赞


    @BindView(R.id.cf_dianzan_text1)
    AppCompatTextView cf_dianzan_text1;//点赞的文本

    @BindView(R.id.cf_dianzan_text2)
    AppCompatTextView cf_dianzan_text2;//点赞的文本


    @BindView(R.id.cf_dianzan_image1)
    AppCompatImageView cf_dianzan_image1;//点赞的文本

    @BindView(R.id.cf_dianzan_image2)
    AppCompatImageView cf_dianzan_image2;//点赞的文本
    @BindView(R.id.onfish_linear)
    LinearLayoutCompat onfish_linear;

    @BindView(R.id.pb)
    ProgressBar pb;

    @BindView(R.id.fl_pannel)
    FrameLayout fl_pannel ;

    @BindView(R.id.scrollView)
    NestedScrollView scrollView;

    @BindView(R.id.ll_whish_parent)
    LinearLayoutCompat ll_whish_parent;

    @BindView(R.id.cf_all_pin)
    AppCompatTextView cf_all_pin;

    @BindView(R.id.wugan_image)
    AppCompatImageView wugan_image;

    @BindView(R.id.kaopu_image)
    AppCompatImageView kaopu_image;

    @BindView(R.id.shou_image)
    AppCompatImageView shou_image;
    @BindView(R.id.share_image)
    AppCompatImageView share_image;

    @BindView(R.id.pin_num)
    AppCompatTextView  pin_num;

    @BindView(R.id.delete1)
    AppCompatTextView delete1;


    @BindView(R.id.delete2)
    AppCompatTextView delete2;

    private EmotionMainFragment emotionMainFragment;//表情面板
    private InputMethodManager inputMethodManager;
    private EditText mEditText;//内容输入框
    private Button mBtnSend;//发送按钮
    private ImageView mIvEmotion;//表情按钮
    private ImageView mIvExtend;//扩展菜单按钮
    private View mInputView;
    private Fragment fragment;

    private Bundle bundle;
    private String URL;
    private String id;
    private String zan_number;
    private String no_zan_number;
    private String no_zan_length;
    private String[] zan;
    private int zan_flage=0;
    private String looknum;
    private InputMethodManager mInputMethodManager;//软键盘管理

    private int pageIndex = 1;//分页加载时 当前页是第几页
    private int totalPage = 0;//总页数
    private int cf_item_zan = 1 ;
    private String commentnum;
    private CFPinBean HEAD_BEAN;
    private int wuhan_flage = 0 ;
    private int you_flage = 0;
    private int del_flage = 1;

    private String descript;
    private String share_image_url;
    private String publisher;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.cwebfragment_laytout;
    }
    @Override
    protected void initEventAndData() {
        AndroidBug5497Workaround.assistActivity(this);
        setToolbarTvTitle("");
        getTvMorView();
        Drawable rightDrawable = getResources().getDrawable(R.mipmap.cf_more);
        rightDrawable.setBounds(0, 0, rightDrawable.getMinimumWidth(), rightDrawable.getMinimumHeight());
        getTvMorView().setCompoundDrawables(null, null, rightDrawable, null);
        setToolbarMoreClickListener(new View.OnClickListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public void onClick(View v) {
                     share(id);
                  }
        });
        pb.setMax(100);
        bundle = getIntent().getExtras();
        URL = bundle.getString("url");
        id = bundle.getString("id");
        looknum =  bundle.getString("looknum");
        zan_number = bundle.getString("pointnum");
        no_zan_number= bundle.getString("nosense");
        commentnum = bundle.getString("commentnum");
        descript =  bundle.getString("descript");
        publisher =   bundle.getString("publisher");
        share_image_url =  bundle.getString("share_image");

        pin_num.setText(commentnum);
        zan = no_zan_number.split(",");
        no_zan_length = zan.length+"";
        cf_kaopu_num.setText(zan_number+"人");


        cf_wugan_num.setText(zan.length+"人");

        //初始化webview
        webView.setWebChromeClient(new WebCClient() );
        webView.setWebViewClient(new WebViewClient());
        webView = initWebView(webView);
        loadUrl();
        // 输入框
        inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        mInputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
         initViewListener();
    }

    private void load_cfpinlist(){
        Map<String,String> request = new HashMap<>();
        request.put("token", SharedPreferencedUtils.getStr(Constants.USER_TOKEN));
        request.put("usernum", SharedPreferencedUtils.getStr(Constants.USER_NUM));
        request.put("id",id);
        request.put("userid", SharedPreferencedUtils.getStr(Constants.USER_ID));
        request.put("pageIndex", pageIndex+"");
        request.put("pageSize", 2+"");
        mPresenter.cfcommentlist(GsonUtil.obj2JSON(request));
    }

    private void addlooknum() {
        Map<String,String> request = new HashMap<>();
        request.put("token", SharedPreferencedUtils.getStr(Constants.USER_TOKEN));
        request.put("usernum", SharedPreferencedUtils.getStr(Constants.USER_NUM));
        request.put("id",id);
        request.put("looknum",looknum);
        mPresenter.looknumFlatcount(GsonUtil.obj2JSON(request));
    }

    private void loadUrl() {
        webView.loadUrl(URL);
    }
    public WebView initWebView(WebView webView) {
            return new WebViewInitializer().createWebView(webView);
        }
    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        if (webView != null) {
            webView.removeAllViews();
            webView.destroy();
        }
    }
    private class WebCClient extends WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            if (newProgress == 100) {
                load_cfpinlist();//获取评论的列表
                if (pb != null) {
                    pb.setVisibility(View.GONE);
                }
                if (onfish_linear != null)
                {
                    onfish_linear.setVisibility(View.VISIBLE);
                }
            } else {
                if(pb!=null)
                {
                    pb.setVisibility(View.VISIBLE);
                    pb.setProgress(newProgress);
                }
            }
            super.onProgressChanged(view, newProgress);
        }
    }


    @OnClick({R.id.delete1,R.id.delete2,R.id.cf_kaopu_lienar,R.id.cf_wugan_linear,R.id.cf_all_pin,R.id.pin_framelayout,R.id.cf_bottom_pin_linear,R.id.cf_dianzan_linear1,R.id.cf_dianzan_linear2,R.id.cf_bottom_share_linear,R.id.cf_bottom_sc_linear})
    public void click(View view) {
        switch (view.getId())
        {
            case R.id.delete1:
                del_flage = 1;
                show_clear_cart(HEAD_BEAN.getList().get(0).getId());
                break;
            case R.id.delete2:
                del_flage=2 ;
                show_clear_cart(HEAD_BEAN.getList().get(1).getId());
                break;
            case R.id.cf_dianzan_linear1://点赞
                cf_item_zan = 1;
                cf_dianzan_image1.startAnimation(AnimationUtils.loadAnimation(
                        this, R.anim.zan_anim));
                Map<String,String> request0 = new HashMap<>();
                request0.put("token", SharedPreferencedUtils.getStr(Constants.USER_TOKEN));
                request0.put("usernum", SharedPreferencedUtils.getStr(Constants.USER_NUM));
                request0.put("userid",SharedPreferencedUtils.getStr(Constants.USER_ID));
                request0.put("id",HEAD_BEAN.getList().get(0).getId()+"");
                //request0.put("reluserid",HEAD_BEAN.getList().get(0).getUserid()+"");
                mPresenter.cf_addzan(GsonUtil.obj2JSON(request0));

                break;
            case R.id.cf_dianzan_linear2://点赞
                cf_item_zan = 2;
                cf_dianzan_image2.startAnimation(AnimationUtils.loadAnimation(
                        this, R.anim.zan_anim));
                Map<String,String> request2 = new HashMap<>();
                request2.put("token", SharedPreferencedUtils.getStr(Constants.USER_TOKEN));
                request2.put("usernum", SharedPreferencedUtils.getStr(Constants.USER_NUM));
                request2.put("userid",SharedPreferencedUtils.getStr(Constants.USER_ID));
                request2.put("id",HEAD_BEAN.getList().get(1).getId()+"");
                //request2.put("reluserid",HEAD_BEAN.getList().get(1).getUserid()+"");
                mPresenter.cf_addzan(GsonUtil.obj2JSON(request2));
                break;
            case R.id.pin_framelayout://评论
                openCommentInput();
                break;
            case R.id.cf_kaopu_lienar:
                zan_flage= 0 ;

                kaopu_image.startAnimation(AnimationUtils.loadAnimation(
                        this, R.anim.zan_anim));
                Map<String,String> request = new HashMap<>();
                request.put("token", SharedPreferencedUtils.getStr(Constants.USER_TOKEN));
                request.put("usernum", SharedPreferencedUtils.getStr(Constants.USER_NUM));
                request.put("userid",SharedPreferencedUtils.getStr(Constants.USER_ID));
                request.put("id",id);
                request.put("type","1001");
                mPresenter.updatepointnum(GsonUtil.obj2JSON(request));

                break;
            case R.id.cf_wugan_linear:
                zan_flage = 1;

                wugan_image.startAnimation(AnimationUtils.loadAnimation(this, R.anim.zan_anim));
                Map<String,String> request1 = new HashMap<>();
                request1.put("token", SharedPreferencedUtils.getStr(Constants.USER_TOKEN));
                request1.put("usernum", SharedPreferencedUtils.getStr(Constants.USER_NUM));
                request1.put("userid",SharedPreferencedUtils.getStr(Constants.USER_ID));
                request1.put("id",id);
                request1.put("type","1002");
                mPresenter.updatepointnum(GsonUtil.obj2JSON(request1));
                break;
            case R.id.cf_all_pin://查看更多评论
                Bundle bundle = new Bundle();
                bundle.putString("id",id);
                ActivityUtil.getInstance().openActivity(this, CWebAllPinActivity.class,bundle);
                break;
            case R.id.cf_bottom_pin_linear:
                Bundle bundle1 = new Bundle();
                bundle1.putString("id",id);
                ActivityUtil.getInstance().openActivity(this, CWebAllPinActivity.class,bundle1);
                break;
            case R.id.cf_bottom_share_linear://分享
                share_image.startAnimation(AnimationUtils.loadAnimation(
                    this, R.anim.share));
                share(id);
                break;

            case R.id.cf_bottom_sc_linear://收藏
                shou_image.startAnimation(AnimationUtils.loadAnimation(
                        this, R.anim.share));

                shou_image.setBackgroundResource(R.mipmap.cf_shou_yes);
                break;
        }
    }
    private class WebViewClient extends android.webkit.WebViewClient {
        @Override
        public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
            super.onReceivedError(view, request, error);
        }
    }
    @Override
    public void updatepointnum(Integer msg) {//点赞成功之后返回的结果
        if(msg==1)
        {
            SAToast.makeText(this,"您已经点过赞了!").show();
          /*  if(zan_flage==0)
            {
                if(you_flage == 0)
                {
                    you_flage=1;
                    int you = Integer.parseInt(zan_number)-1;
                    if(you<=0)
                    {
                        cf_kaopu_num.setText(0+"人");
                    }else {
                        cf_kaopu_num.setText(you+"人");
                    }
                }
            }else {
                if(wuhan_flage == 0)
                {
                    int wu = Integer.parseInt(no_zan_length)-1;
                    wuhan_flage =1;
                    if(wu<=0)
                    {
                        cf_wugan_num.setText(0+"人");
                    }else {
                        cf_wugan_num.setText(wu+"人");
                    }
                }
            }*/
        }else if(msg==0){
            if(zan_flage==0)//表示靠谱
            {
                int you = Integer.parseInt(zan_number)+1;
                zan_number= you+"";
                cf_kaopu_num.setText(you+"人");
            }else {
                no_zan_length= zan.length+1+"";
                cf_wugan_num.setText(no_zan_length+"人");
            }
        }else
        {
            ActivityUtil.getInstance().openActivity(this, LoginActivity.class);
        }
    }

    @Override
    public void looknumFlatcount(String data) {

    }

    @Override
    public void addFlatComment(String action) {//评论成功之后
        load_cfpinlist();//添加评论
      /*  if(cf_pin_linear1.getVisibility()==View.GONE)
        {
            cf_pin_name1.setText(SharedPreferencedUtils.getStr(Constants.USER_NICK_NAME));
            cf_coment1.setText( mEditText.getText().toString());
            cf_pin_linear1.setVisibility(View.VISIBLE);
            cf_time_name1.setText(formatTime2String( currentTimeMillis()/1000,true));

            String head_image = SharedPreferencedUtils.getStr(Constants.USER_HEADER);
            head_image = Constants.IMG_SERVER_PATH + head_image;
            Glide.with(this)
                    .load(head_image)
                    .asBitmap()
                    .error(R.mipmap.person_head)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(cf_pin_image1);

            cf_dianzan_linear1.setBackgroundResource(R.drawable.cf_normal_shape);
            cf_dianzan_image1.setBackgroundResource(R.mipmap.cf_nodian);
            cf_dianzan_text1.setTextColor(Color.parseColor("#999999"));
            cf_dianzan_text1.setText("点赞");

        }else if(cf_pin_linear2.getVisibility()==View.GONE)
        {

            cf_pin_name2.setText(SharedPreferencedUtils.getStr(Constants.USER_NICK_NAME));
            cf_coment2.setText( mEditText.getText().toString());
            cf_pin_linear2.setVisibility(View.VISIBLE);
            cf_time_name2.setText(formatTime2String( currentTimeMillis()/1000,true));
            String head_image = SharedPreferencedUtils.getStr(Constants.USER_HEADER);
            head_image = Constants.IMG_SERVER_PATH + head_image;
            Glide.with(this)
                    .load(head_image)
                    .asBitmap()
                    .error(R.mipmap.person_head)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(cf_pin_image2);
            cf_dianzan_linear2.setBackgroundResource(R.drawable.cf_normal_shape);
            cf_dianzan_image2.setBackgroundResource(R.mipmap.cf_nodian);
            cf_dianzan_text2.setTextColor(Color.parseColor("#999999"));
            cf_dianzan_text2.setText("点赞");
        }   */
        mEditText.setText("");
    }

    @Override
    public void cfcommentlist(CFPinBean bean) {//获取的评论列表
        addlooknum();//添加浏览量
        HEAD_BEAN = bean;
    if(bean.getList().size()==0||bean.getList()==null) {
        cf_pin_linear1.setVisibility(View.GONE);
        cf_pin_linear2.setVisibility(View.GONE);
        cf_all_pin.setVisibility(View.GONE);
    }else if(bean.getList().size()==1)
    {   cf_pin_linear1.setVisibility(View.VISIBLE);
        cf_pin_linear2.setVisibility(View.GONE);
        cf_all_pin.setVisibility(View.GONE);
        init_pin_data_one(bean);
    }else{
        cf_pin_linear1.setVisibility(View.VISIBLE);
        cf_pin_linear2.setVisibility(View.VISIBLE);
        cf_all_pin.setVisibility(View.VISIBLE);
        init_pin_data(bean);//初始化顶部的评论
    }
        cf_all_pin.setText("查看全部评论("+bean.getAllRow()+")");
        pin_num.setText(bean.getAllRow()+"");
    }

    private void init_pin_data_one(CFPinBean bean) {
        Glide.with(this)
                .load(Constants.IMG_SERVER_PATH + bean.getList().get(0).getHeadimg())
                .asBitmap()
                .error(R.mipmap.person_head)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(cf_pin_image1);
        SpannableString emotionContent = SpanStringUtils.getEmotionContent_Cf(EmotionUtils
                .EMOTION_CLASSIC_TYPE, this, cf_coment1,bean.getList().get(0).getComment());
        cf_coment1.setText(emotionContent);
        cf_pin_name1.setText(bean.getList().get(0).getObserver());
        cf_time_name1.setText(bean.getList().get(0).getWritingDate());
        String userid = bean.getList().get(0).getUserid()+"";
        if(userid.equals(SharedPreferencedUtils.getStr(Constants.USER_ID)))
        {
            delete1.setVisibility(View.VISIBLE);
        }

        if(bean.getList().get(0).getIspoint().equals("0"))
        {
            cf_dianzan_linear1.setBackgroundResource(R.drawable.cf_normal_shape);
            cf_dianzan_image1.setBackgroundResource(R.mipmap.cf_nodian);
            cf_dianzan_text1.setTextColor(Color.parseColor("#999999"));
            cf_dianzan_text1.setText(bean.getList().get(0).getPointnum());
        }else {
            cf_dianzan_linear1.setBackgroundResource(R.drawable.cf_like_shape);
            cf_dianzan_image1.setBackgroundResource(R.mipmap.cf_dian);
            cf_dianzan_text1.setTextColor(Color.parseColor("#ff7b00"));
            cf_dianzan_text1.setText(bean.getList().get(0).getPointnum()+"");
        }
    }


    @Override
    public void cf_addzan(String data) {//点赞
        if(data.equals("0"))
        {
            if(cf_item_zan == 1)
            {
                if(HEAD_BEAN.getList().get(0).getIspoint().equals("0"))
                {
                    cf_dianzan_linear1.setBackgroundResource(R.drawable.cf_like_shape);
                    cf_dianzan_image1.setBackgroundResource(R.mipmap.cf_dian);
                    cf_dianzan_text1.setTextColor(Color.parseColor("#ff7b00"));
                    HEAD_BEAN.getList().get(0).setIspoint("1");
                    int size = Integer.parseInt(cf_dianzan_text1.getText().toString())+1;
                    cf_dianzan_text1.setText(size+"");
                }else {
                    cf_dianzan_linear1.setBackgroundResource(R.drawable.cf_normal_shape);
                    cf_dianzan_image1.setBackgroundResource(R.mipmap.cf_nodian);
                    cf_dianzan_text1.setTextColor(Color.parseColor("#999999"));
                    HEAD_BEAN.getList().get(0).setIspoint("0");
                    int size = Integer.parseInt(cf_dianzan_text1.getText().toString())-1;
                    cf_dianzan_text1.setText(size+"");
                }
            }else {
                if(HEAD_BEAN.getList().get(1).getIspoint().equals("0"))
                {
                    cf_dianzan_linear2.setBackgroundResource(R.drawable.cf_like_shape);
                    cf_dianzan_image2.setBackgroundResource(R.mipmap.cf_dian);
                    cf_dianzan_text2.setTextColor(Color.parseColor("#ff7b00"));
                    HEAD_BEAN.getList().get(1).setIspoint("1");
                    int size = Integer.parseInt(cf_dianzan_text2.getText().toString())+1;
                    cf_dianzan_text2.setText(size+"");
                }else {
                    cf_dianzan_linear2.setBackgroundResource(R.drawable.cf_normal_shape);
                    cf_dianzan_image2.setBackgroundResource(R.mipmap.cf_nodian);
                    cf_dianzan_text2.setTextColor(Color.parseColor("#999999"));
                    HEAD_BEAN.getList().get(1).setIspoint("0");
                    int size = Integer.parseInt(cf_dianzan_text2.getText().toString())-1;
                    cf_dianzan_text2.setText(size+"");
                }
            }
        }
    }

    @Override
    public void delFlatComment(String action) {
        if(del_flage ==1)
        {
            cf_pin_linear1.setVisibility(View.GONE);
        }else {
            cf_pin_linear2.setVisibility(View.GONE);
        }
    }

    private void init_pin_data(CFPinBean bean) {
        Glide.with(this)
                .load(Constants.IMG_SERVER_PATH + bean.getList().get(0).getHeadimg())
                .asBitmap()
                .error(R.mipmap.person_head)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(cf_pin_image1);

        Glide.with(this)
                .load(Constants.IMG_SERVER_PATH + bean.getList().get(1).getHeadimg())
                .asBitmap()
                .error(R.mipmap.person_head)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(cf_pin_image2);

        SpannableString emotionContent = SpanStringUtils.getEmotionContent_Cf(EmotionUtils
                .EMOTION_CLASSIC_TYPE, this, cf_coment1,bean.getList().get(0).getComment());
        cf_coment1.setText(emotionContent);


        SpannableString emotionContent1 = SpanStringUtils.getEmotionContent_Cf(EmotionUtils
                .EMOTION_CLASSIC_TYPE, this, cf_coment1,bean.getList().get(1).getComment());
        cf_coment2.setText(emotionContent1);

        cf_pin_name1.setText(bean.getList().get(0).getObserver());
        cf_pin_name2.setText(bean.getList().get(1).getObserver());

        cf_time_name1.setText(bean.getList().get(0).getWritingDate());
        cf_time_name2.setText(bean.getList().get(1).getWritingDate());
        String userid1 = bean.getList().get(0).getUserid()+"";
        if(userid1.equals(SharedPreferencedUtils.getStr(Constants.USER_ID)))
        {
            delete1.setVisibility(View.VISIBLE);
        }
        String userid2 = bean.getList().get(1).getUserid()+"";
        if(userid2.equals(SharedPreferencedUtils.getStr(Constants.USER_ID)))
        {
            delete2.setVisibility(View.VISIBLE);
        }

        if(bean.getList().get(0).getIspoint().equals("0"))
        {
            cf_dianzan_linear1.setBackgroundResource(R.drawable.cf_normal_shape);
            cf_dianzan_image1.setBackgroundResource(R.mipmap.cf_nodian);
            cf_dianzan_text1.setTextColor(Color.parseColor("#999999"));
            cf_dianzan_text1.setText(bean.getList().get(0).getPointnum()+"");
        }else {
            cf_dianzan_linear1.setBackgroundResource(R.drawable.cf_like_shape);
            cf_dianzan_image1.setBackgroundResource(R.mipmap.cf_dian);
            cf_dianzan_text1.setTextColor(Color.parseColor("#ff7b00"));
            cf_dianzan_text1.setText(bean.getList().get(0).getPointnum()+"");
        }
        if(bean.getList().get(1).getIspoint().equals("0"))
        {
            cf_dianzan_linear2.setBackgroundResource(R.drawable.cf_normal_shape);
            cf_dianzan_image2.setBackgroundResource(R.mipmap.cf_nodian);
            cf_dianzan_text2.setTextColor(Color.parseColor("#999999"));
            cf_dianzan_text2.setText(bean.getList().get(1).getPointnum()+"");
        }else {
            cf_dianzan_linear2.setBackgroundResource(R.drawable.cf_like_shape);
            cf_dianzan_image2.setBackgroundResource(R.mipmap.cf_dian);
            cf_dianzan_text2.setTextColor(Color.parseColor("#ff7b00"));
            cf_dianzan_text2.setText(bean.getList().get(1).getPointnum()+"");
        }

    }

    @Override
    public void stateError(Throwable e) {
        super.stateError(e);
    }

    @Override
    public void stateError() {
        super.stateError();
    }

    @Override
    public void fristload(CFChildBean cfChildBean) {

    }


    @Override
    public void useNightMode(boolean isNight) {

    }

    @Override
    public void onStart() {
        super.onStart();
        initEmotionViewAndListener();
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

    @Subscribe
    public   void OnEvent(CWPinBean bean)
    {
        if(bean.getPin().equals("1"))
        {
            cf_pin_linear1.setVisibility(View.GONE);
        }else {
            cf_pin_linear2.setVisibility(View.GONE);
        }

    }

    @Override
    protected void initInject()  {
        getActivityComponent().inject(this);
    }
    private void openCommentInput() {
        if(fl_pannel!=null)
        {
            fl_pannel.setVisibility(View.VISIBLE);
        }
        emotionMainFragment.hideEmotionLayoutoAndExtenLayout();//隐藏表情面板
        mEditText.requestFocus();
        inputMethodManager.showSoftInput(mEditText, 0);
        ll_whish_parent.setVisibility(View.GONE);
    }
    private void initEmotionViewAndListener(){
        if (mInputView == null){
            mInputView =  getSupportFragmentManager().findFragmentById(R.id.fl_pannel).getView();
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
                    closeCommentInput();
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
                        // TODO: 2017/12/16 此处可能有坑
                        request.put("comment", content.toString());
                        request.put("flatid", id);
                        request.put("observer", SharedPreferencedUtils.getStr(Constants.USER_NICK_NAME));
                        request.put("parentid", "0");
                        request.put("answerer","");
                        request.put("fabulous", "0");
                        request.put("touserid", "");
                        request.put("reluserid",SharedPreferencedUtils.getStr(Constants.USER_ID));
                        mPresenter.addFlatComment(GsonUtil.obj2JSON(request));
                    }
                }
            });
        }
    }
    private void initViewListener() {
        scrollView.setOnTouchListener(new View.OnTouchListener() {
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
        emotionMainFragment.bindToContentView(onfish_linear);
        FragmentTransaction transaction =getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fl_pannel, emotionMainFragment);
        transaction.addToBackStack(null);
        //提交修改
        transaction.commit();
    }
    private void closeCommentInput() {
        if(emotionMainFragment!=null)
        {
            emotionMainFragment.hideEmotionLayoutoOrExtenLayout();//隐藏表情面板
        }
        inputMethodManager.hideSoftInputFromWindow(mEditText.getWindowToken(), 0);
        if (fl_pannel != null) {
            fl_pannel.setVisibility(View.GONE);
        }
        ll_whish_parent.setVisibility(View.VISIBLE);
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
            UMWeb web = new UMWeb(URL);
            web.setTitle(publisher+"的资讯");//标题
            UMImage umImage = new UMImage(this,Constants.IMG_SERVER_PATH+share_image_url);
            web.setThumb(umImage);  //缩略图
            web.setDescription(descript);//描述

            new ShareAction(this)
                    .withMedia(web)
                    .setDisplayList(SHARE_MEDIA.QQ, SHARE_MEDIA.QZONE, SHARE_MEDIA.WEIXIN,
                            SHARE_MEDIA.WEIXIN_CIRCLE)
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
            ToastUtil.Success("分享已取消");
        }
    };

    public void show_clear_cart(int id){
        //实例化建造者
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //设置警告对话框的标题
        builder.setTitle("删除动态");
        //设置警告显示的图片
//        builder.setIcon(android.R.drawable.ic_dialog_alert);
        //设置警告对话框的提示信息
        builder.setMessage("确认要删除这条评论吗？");
        //设置”正面”按钮，及点击事件
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Map<String, String> request = new HashMap<>();
                request.put("token", SharedPreferencedUtils.getStr(Constants.USER_TOKEN));
                request.put("usernum", SharedPreferencedUtils.getStr(Constants.USER_NUM));
                request.put("userid", SharedPreferencedUtils.getStr(Constants.USER_ID));
                request.put("fabulous","0");
                request.put("id",id+"");
                mPresenter.delFlatComment(GsonUtil.obj2JSON(request));
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
}
