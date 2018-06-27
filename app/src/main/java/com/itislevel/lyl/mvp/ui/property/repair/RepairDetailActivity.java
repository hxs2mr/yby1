package com.itislevel.lyl.mvp.ui.property.repair;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatRatingBar;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.view.animation.Transformation;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.android.flexbox.FlexboxLayout;
import com.itislevel.lyl.R;
import com.itislevel.lyl.app.App;
import com.itislevel.lyl.app.Constants;
import com.itislevel.lyl.base.RootActivity;
import com.itislevel.lyl.mvp.model.bean.CatelistBean;
import com.itislevel.lyl.mvp.model.bean.ProperCommentList;
import com.itislevel.lyl.mvp.model.bean.ReAddComSuccBean;
import com.itislevel.lyl.mvp.model.bean.RepairCityListBean;
import com.itislevel.lyl.mvp.model.bean.RepairListBean;
import com.itislevel.lyl.mvp.model.bean.RepairTypeListBean;
import com.itislevel.lyl.mvp.model.bean.SeleBean;
import com.itislevel.lyl.utils.ActivityUtil;
import com.itislevel.lyl.utils.GsonUtil;
import com.itislevel.lyl.utils.SAToast;
import com.itislevel.lyl.utils.SharedPreferencedUtils;
import com.itislevel.lyl.utils.ToastUtil;
import com.itislevel.lyl.utils.UtilsStyle;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindInt;
import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

import static com.itislevel.lyl.mvp.ui.property.repair.PropertyRepairActivity.start_catelist;
import static com.itislevel.lyl.utils.DateUtil.timeSpanToDate;
import static io.rong.imkit.utilities.RongUtils.dip2px;

/**
 * Created by Administrator on 2018\6\6 0006.
 */

public class RepairDetailActivity extends RootActivity<RepairPresenter>implements RepairContract.View{

    @BindView(R.id.more_text)
    AppCompatTextView more_text;//查看更多的文本c_4

    @BindView(R.id.more_linear)
    View more_linear;//查看更多的布局  c_linear4


    @BindView(R.id.more_onclik)
    AppCompatTextView more_onclik;

    @BindView(R.id.more_linear_next)
    LinearLayoutCompat more_linear_next;

    @BindView(R.id.more_onclik_image)
    AppCompatImageView more_onclik_image;

    @BindView(R.id.fabu_time)
    AppCompatTextView fabu_time;
    @BindView(R.id.look_num)
    AppCompatTextView look_num;

    @BindView(R.id.head_image)
    CircleImageView head_image;
    @BindView(R.id.username)
    AppCompatTextView username_tv;

    @BindView(R.id.service_space)
    AppCompatTextView service_space;

    @BindView(R.id.address)
     AppCompatTextView address_tv;

    @BindView(R.id.fbl_parent)
    FlexboxLayout fbl_parent;


    //两条评论  暂时用这个办法
    @BindView(R.id.re_pin_linear1)
     LinearLayoutCompat re_pin_linear1;

    @BindView(R.id.re_pin_linear2)
    LinearLayoutCompat re_pin_linear2;


    @BindView(R.id.total_fen_linear)
    LinearLayoutCompat total_fen_linear;//评分的布局

    @BindView(R.id.re_pin_fen)
    AppCompatTextView re_pin_fen;//评分

     @BindView(R.id.re_pin_image1)
     CircleImageView re_pin_image1;

     @BindView(R.id.re_pin_name1)
     AppCompatTextView re_pin_name1;

    @BindView(R.id.re_pin_coment1)
    AppCompatTextView re_pin_coment1;

    @BindView(R.id.re_pin_time1)
    AppCompatTextView re_pin_time1;

    @BindView(R.id.re_pin_ratingBar1)
    AppCompatRatingBar re_pin_ratingBar1;


    @BindView(R.id.re_pin_image2)
    CircleImageView re_pin_image2;

    @BindView(R.id.re_pin_name2)
    AppCompatTextView re_pin_name2;

    @BindView(R.id.re_pin_coment2)
    AppCompatTextView re_pin_coment2;

    @BindView(R.id.re_pin_time2)
    AppCompatTextView re_pin_time2;

    @BindView(R.id.re_pin_ratingBar2)
    AppCompatRatingBar re_pin_ratingBar2;

    @BindView(R.id.pin_num)
     AppCompatTextView pin_num;

    @BindView(R.id.shou_image)
     AppCompatImageView shou_image;

    @BindView(R.id.shou_tv)
     AppCompatTextView  shou_tv;
    View more;
    boolean isExpand = true;//查看更多是否张开

    private List<CatelistBean> catelist;
    private  int max_line4= 5;
    private  String looknum="0";
    private long createtime;
    private String headurl="";
    private String username="";
    private String servicearea="";
    private String address="";
    private String workrem="";
    private Bundle bundle;
    private String rwid="";
    private String score="";
    private String noStoreUp="";//收藏  0 表示没收藏   1 表示收藏
    private String modelphone="";
    private String share_url="";
    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        UtilsStyle.setStatusBarMode(this,true); //黑色的主题图标
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_repairdetail;
    }
    @Override
    protected void initEventAndData() {
        catelist =  start_catelist ;
        bundle = getIntent().getExtras();
        looknum =  bundle.getString("looknum");
        createtime =  bundle.getLong("createtime");
        headurl =  bundle.getString("headurl");
        username =  bundle.getString("username");
        servicearea =  bundle.getString("servicearea");
        address =  bundle.getString("address");
        workrem =  bundle.getString("workrem");
        rwid = bundle.getString("rwid");
        score = bundle.getString("score");
        noStoreUp = bundle.getString("noStoreUp");
        modelphone  = bundle.getString("modelphone");
        share_url = bundle.getString("share_url");
        more_text.setText(workrem);
        fabu_time.setText("发布时间 "+timeSpanToDate(createtime));
        look_num.setText("已有"+looknum+"人浏览");

        re_pin_fen.setText(score);
        Glide.with(App.getInstance())
                .load(Constants.IMG_SERVER_PATH + headurl)
                .asBitmap()
                .error(R.mipmap.person_head)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(head_image);
        if(noStoreUp.equals("0"))
        {
            shou_image.setBackgroundResource(R.mipmap.cf_sc);
            shou_tv.setTextColor(Color.parseColor("#999999"));
        }else {
            shou_image.setBackgroundResource(R.mipmap.cf_shou_yes);
            shou_tv.setTextColor(Color.parseColor("#ff7e08"));
            shou_tv.setText("已收藏");
        }
        username_tv.setText(username);
        service_space.setText(servicearea);
        address_tv.setText(address);

        more_text.setHeight(more_text.getLineHeight() * max_line4);//设置初始高度
        more_text.post(new Runnable() {
            @Override
            public void run() {
                more_linear_next.setVisibility(more_text.getLineCount() > max_line4 ?View.VISIBLE : View.GONE);
            }
        });
        FlexboxLayout.LayoutParams params1 = new FlexboxLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params1.setWidth(dip2px(90));
        params1.setHeight(dip2px(30));
        params1.setMargins(0,dip2px(3),dip2px(6),0);
        if (catelist != null && catelist.size() > 0) {
            fbl_parent.removeAllViews();
            for (CatelistBean fab :catelist) {
                TextView textView = new TextView(App.getInstance());
                textView.setText(fab.getCatename());
                textView.setTextSize(16);
                textView.setMaxLines(1);
                textView.setMaxEms(5);
                textView.setGravity(Gravity.CENTER);
                textView.setTextColor(Color.parseColor("#666666"));
                textView.setPadding(5,2, 5, 2);
                textView.setBackgroundResource(R.drawable.repair_deal_shape);
                textView.setLayoutParams(params1);
                fbl_parent.addView(textView);
            }
        } else {
            fbl_parent.removeAllViews();
        }
        addlooknum();
        loadListComment();
    }

    private void addlooknum() {
        Map<String, Object> request = new HashMap<>();
        request.put("token", SharedPreferencedUtils.getStr("p_token",""));
        request.put("phone", SharedPreferencedUtils.getStr("proprety_phone",""));
        request.put("rwid", rwid);
        request.put("looknum", looknum);
        mPresenter.prolooknumcount(GsonUtil.obj2JSON(request));
    }

    private void loadListComment()
    {
        Map<String, Object> request = new HashMap<>();
        request.put("token", SharedPreferencedUtils.getStr("p_token",""));
        request.put("phone", SharedPreferencedUtils.getStr("proprety_phone",""));
        request.put("rwid", rwid);
        request.put("score", "");
        request.put("pageIndex",1);
        request.put("pageSize",2);
        mPresenter.commentEstatesList(GsonUtil.obj2JSON(request));
    }
    @OnClick({R.id.more_linear_next,R.id.pin_framelayout,R.id.p_p_back,R.id.cf_bottom_sc_linear,R.id.call_phone,R.id.cf_bottom_pin_linear,R.id.share_button})
    public  void onclick(View view){
        switch (view.getId())
        {
            case R.id.more_linear_next:
                show_line(more_text,more_onclik_image,isExpand,4,5);
                break;
            case R.id.pin_framelayout:
                Bundle bundle = new Bundle();
                bundle.putString("rwid",rwid);
                bundle.putString("headurl",headurl);
                ActivityUtil.getInstance().openActivity(this,RepairAddCommentActivity.class,bundle);
                break;
            case R.id.p_p_back:
                ActivityUtil.getInstance().closeActivity(this);
                break;
            case R.id.cf_bottom_sc_linear://收藏
                if(noStoreUp.equals("0"))
                {
                    Map<String, Object> request = new HashMap<>();
                    request.put("token", SharedPreferencedUtils.getStr("p_token",""));
                    request.put("phone", SharedPreferencedUtils.getStr("proprety_phone",""));
                    request.put("rwid", rwid);
                    mPresenter.addCollectMaintenance(GsonUtil.obj2JSON(request));//点击收藏
                }else {
                        SAToast.makeText(this,"收藏过了").show();
                    shou_image.startAnimation(AnimationUtils.loadAnimation(
                            this, R.anim.share));
                    shou_image.setBackgroundResource(R.mipmap.cf_shou_yes);
                    shou_tv.setTextColor(Color.parseColor("#ff7e08"));
                }
                break;
            case R.id.call_phone:
                show_call(modelphone);
                break;
            case  R.id.cf_bottom_pin_linear:
                if(pin_num.getText().toString().equals("评价"))
                {
                    Bundle bundle2 = new Bundle();
                    bundle2.putString("rwid",rwid);
                    bundle2.putString("headurl",headurl);
                    ActivityUtil.getInstance().openActivity(this,RepairAddCommentActivity.class,bundle2);
                }else {
                    Bundle bundle1  = new Bundle();
                    bundle1.putString("rwid",rwid);
                    ActivityUtil.getInstance().openActivity(this,RepairAllPinActivity.class,bundle1);
                }
                break;
            case R.id.share_button:
                share("1");
                break;
        }

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
//case_linear1_text.setHeight(case_linear1_text.getLineHeight() * max_line);
    private void show_line(final AppCompatTextView text , View image, boolean isExpand,int i,int max){
        text.clearAnimation();
        final int deltaValue;//默认高度，即前边由maxLine确定的高度
        final int startValue = text.getHeight();//起始高度
        int durationMillis = 350;//动画持续时间
        if (isExpand) {
                this.isExpand = false;
            /**
             * 折叠动画
             * 从实际高度缩回起始高度
             */
            deltaValue = text.getLineHeight() * text.getLineCount() - startValue;
            RotateAnimation animation = new RotateAnimation(0, 180, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            animation.setDuration(durationMillis);
            animation.setFillAfter(true);
            image.startAnimation(animation);
            more_onclik.setText("点击收起");

        }else
        {
            /**
             * 展开动画
             * 从起始高度增长至实际高度
             */
            this.isExpand = true;
            deltaValue = text.getLineHeight() * max - startValue;
            RotateAnimation animation = new RotateAnimation(180, 0, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            animation.setDuration(durationMillis);
            animation.setFillAfter(true);
            image.startAnimation(animation);
            more_onclik.setText("展开更多");
        }
        Animation animation = new Animation() {
            protected void applyTransformation(float interpolatedTime, Transformation t) { //根据ImageView旋转动画的百分比来显示textview高度，达到动画效果
                text.setHeight((int) (startValue + deltaValue * interpolatedTime));
            }
        };
        animation.setDuration(durationMillis);
        text.startAnimation(animation);

    }

    @Override
    public void maintenanceList(RepairListBean bean) {

    }

    @Override
    public void queryarealist(List<RepairCityListBean> bean) {

    }
    @Override
    public void queryrepairallcatelist(List<RepairCityListBean> bean) {

    }
    @Override
    public void stateError() {
        super.stateError();
        re_pin_linear1.setVisibility(View.GONE);
        re_pin_linear2.setVisibility(View.GONE);
        total_fen_linear.setVisibility(View.GONE);
    }
    @Override
    public void commentEstatesList(ProperCommentList bean) {//获取到的评论列表
        if(bean.getPageBean().getAllRow()<=0)
        {
            pin_num.setText("评价");
        }else {
            pin_num.setText(bean.getPageBean().getAllRow()+"");
        }
        if(bean.getPageBean().getList().size()<=0)
        {
            re_pin_linear1.setVisibility(View.GONE);
            re_pin_linear2.setVisibility(View.GONE);
            total_fen_linear.setVisibility(View.GONE);
        }else  if(bean.getPageBean().getList().size()==1){
            re_pin_linear1.setVisibility(View.VISIBLE);
            total_fen_linear.setVisibility(View.VISIBLE);
            re_pin_linear2.setVisibility(View.GONE);
            init_pin_one(bean);
        }else {
            re_pin_linear1.setVisibility(View.VISIBLE);
            total_fen_linear.setVisibility(View.VISIBLE);
            re_pin_linear2.setVisibility(View.VISIBLE);
            init_pin_two(bean);
        }
    }

    private void init_pin_one(ProperCommentList bean) {
        re_pin_fen.setText(bean.getPageBean().getList().get(0).getScore()+"");
        Glide.with(App.getInstance())
                .load(Constants.IMG_SERVER_PATH + bean.getPageBean().getList().get(0).getHeadurl())
                .asBitmap()
                .error(R.mipmap.person_head)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(re_pin_image1);
        re_pin_name1.setText(bean.getPageBean().getList().get(0).getObserver());
        re_pin_coment1.setText(bean.getPageBean().getList().get(0).getComment());
        re_pin_time1.setText(timeSpanToDate(bean.getPageBean().getList().get(0).getCreatedtime()));
        re_pin_ratingBar1.setRating(bean.getPageBean().getList().get(0).getScore()/2);
    }
    private void init_pin_two(ProperCommentList bean) {
        Glide.with(App.getInstance())
                .load(Constants.IMG_SERVER_PATH + bean.getPageBean().getList().get(0).getHeadurl())
                .asBitmap()
                .error(R.mipmap.person_head)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(re_pin_image1);
        re_pin_name1.setText(bean.getPageBean().getList().get(0).getObserver());
        re_pin_coment1.setText(bean.getPageBean().getList().get(0).getComment());
        re_pin_time1.setText(timeSpanToDate(bean.getPageBean().getList().get(0).getCreatedtime()));
        re_pin_ratingBar1.setRating(bean.getPageBean().getList().get(0).getScore()/2);

        Glide.with(App.getInstance())
                .load(Constants.IMG_SERVER_PATH + bean.getPageBean().getList().get(1).getHeadurl())
                .asBitmap()
                .error(R.mipmap.person_head)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(re_pin_image2);
        re_pin_name2.setText(bean.getPageBean().getList().get(1).getObserver());
        re_pin_coment2.setText(bean.getPageBean().getList().get(1).getComment());
        re_pin_time2.setText(timeSpanToDate(bean.getPageBean().getList().get(1).getCreatedtime()));
        re_pin_ratingBar2.setRating(bean.getPageBean().getList().get(1).getScore()/2);
    }
    @Override
    public void addCommentEstates(String data) {

    }

    @Override
    public void addCollectMaintenance(String action) {
        shou_image.startAnimation(AnimationUtils.loadAnimation(
                this, R.anim.share));
        shou_image.setBackgroundResource(R.mipmap.cf_shou_yes);
        shou_tv.setTextColor(Color.parseColor("#ff7e08"));
        shou_tv.setText("已收藏");
    }

    @Override
    public void seleCommentConunt(SeleBean bean) {

    }

    @Override
    public void prolooknumcount(String data) {

    }

    @Subscribe
    public void Event(ReAddComSuccBean bean)
    {
        loadListComment();
    }

    public void show_call(String phone){
        //实例化建造者
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //设置警告对话框的标题
        //设置警告显示的图片
//        builder.setIcon(android.R.drawable.ic_dialog_alert);
        //设置警告对话框的提示信息
        builder.setMessage("拨打"+phone);
        //设置”正面”按钮，及点击事件
        builder.setPositiveButton("呼叫", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Intent.ACTION_DIAL);

                Uri data = Uri.parse("tel:" + phone);
                intent.setData(data);
                startActivity(intent);
            }
        });
        //设置“中立”按钮，及点击事件
        builder.setNeutralButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        //显示对话框
        builder.show();
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
            UMWeb web = new UMWeb(share_url);
            web.setTitle("维修工人"); //标题
            UMImage umImage = new UMImage(this,Constants.IMG_SERVER_PATH+headurl);
            web.setThumb(umImage);  //缩略图
            web.setDescription("友邦友-维修工人"+workrem);//描述

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
}
