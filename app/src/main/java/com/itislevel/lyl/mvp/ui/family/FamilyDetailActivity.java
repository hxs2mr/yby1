package com.itislevel.lyl.mvp.ui.family;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.zhouwei.library.CustomPopWindow;
import com.itislevel.lyl.R;
import com.itislevel.lyl.adapter.FamilyCateAdapter;
import com.itislevel.lyl.adapter.FamilyDetailReceiveGiftAdapter;
import com.itislevel.lyl.adapter.TestAdapter;
import com.itislevel.lyl.app.App;
import com.itislevel.lyl.app.Constants;
import com.itislevel.lyl.base.RootActivity;
import com.itislevel.lyl.mvp.mediaplayer.FamilyMusicPlayer;
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
import com.itislevel.lyl.mvp.model.bean.JPushFete;
import com.itislevel.lyl.mvp.model.bean.LetterBean;
import com.itislevel.lyl.mvp.model.bean.PlayEvent;
import com.itislevel.lyl.mvp.model.bean.Song;
import com.itislevel.lyl.mvp.model.bean.YuBean;
import com.itislevel.lyl.utils.ActivityUtil;
import com.itislevel.lyl.utils.GsonUtil;
import com.itislevel.lyl.utils.SAToast;
import com.itislevel.lyl.utils.SharedPreferencedUtils;
import com.itislevel.lyl.utils.TextViewSpanUtl;
import com.itislevel.lyl.utils.ToastUtil;
import com.itislevel.lyl.utils.imageload.ImageLoadConfiguration;
import com.itislevel.lyl.utils.imageload.ImageLoadProxy;
import com.itislevel.lyl.utils.rxbus.annotation.UseRxBus;
import com.itislevel.lyl.widget.BlessYuListDialog;
import com.lzy.ninegrid.ImageInfo;
import com.lzy.ninegrid.preview.ImagePreviewActivity;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;
import com.vondear.rxtools.view.dialog.RxDialogLoading;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import pl.droidsonroids.gif.GifImageView;

@UseRxBus
public class FamilyDetailActivity extends RootActivity<FamilyPresenter>
        implements FamilyContract.View, MediaPlayer.OnCompletionListener{
    Bundle bundle = null;
    @BindView(R.id.iv_bianpao_left)
    GifImageView iv_bianpao_left;

    @BindView(R.id.iv_bianpao_right)
    GifImageView iv_bianpao_right;

    @BindView(R.id.ll_family_detail_single)
    LinearLayout ll_family_detail_single;
    @BindView(R.id.tv_single_name)
    TextView tv_single_name;

    @BindView(R.id.iv_single_header)
    ImageView iv_single_header;


    @BindView(R.id.iv_background)
    ImageView iv_background;

    @BindView(R.id.ll_family_detail_double)
    LinearLayout ll_family_detail_double;

    @BindView(R.id.tv_double_name1)
    TextView tv_double_name1;

    @BindView(R.id.iv_double_header1)
    ImageView iv_double_header1;

    @BindView(R.id.tv_double_name2)
    TextView tv_double_name2;

    @BindView(R.id.iv_double_header2)
    ImageView iv_double_header2;

    @BindView(R.id.iv_family_single_frame)
    ImageView iv_family_single_frame;
    @BindView(R.id.iv_family_double1_frame)
    ImageView iv_family_double1_frame;
    @BindView(R.id.iv_family_double2_frame)
    ImageView iv_family_double2_frame;

    @BindView(R.id.recycler_gift)
    RecyclerView recycler_gift;
    @BindView(R.id.tv_send_bless)
    TextView tv_send_bless;
    @BindView(R.id.tv_gift_region)
    TextView tv_gift_region;
    @BindView(R.id.recycler_type)
    RecyclerView recycler_type;

    @BindView(R.id.ll_blessyy_parent)
    LinearLayout ll_blessyy_parent;

    @BindView(R.id.recycler_blessyu)
    RecyclerView recycler_blessyu;

    @BindView(R.id.music_onclick )
    AppCompatImageView music_onclick;
    @BindView(R.id.tv_blessyu_temp)
    TextView tv_blessyu_temp;

    //收到推送模块
    @BindView(R.id.xiaoxi_linear_next)
    LinearLayoutCompat xiaoxi_linear_next;

    @BindView(R.id.xiaoxi_image)
    CircleImageView xiaoxi_image;

    @BindView(R.id.xiaoxi_message)
    AppCompatTextView xiaoxi_message;



    private boolean is_music=true;
    TestAdapter testAdapter;

    FamilyCateAdapter cateAdapter;
    FamilyDetailReceiveGiftAdapter detailReceiveGiftAdapter;
    private String feteid;
    private int looknum;
    private String deadname;
    private String fetename;
    private String imgestr;
    private String share_url;
    private String head_image;
    private String music_url="";
    private FamilyBlessListBean familyBlessList;
    private FamilyReceiveGiftBean familyReceiveGift;
    private MediaPlayer player;
    private MediaPlayer mp3_player;
    private Intent intent;
    GiftClickListener giftClickListener;
    CateClickListener cateClickListener;

    private String danren_url ="";
    private String suanren_url1 ="";
    private String suanren_url2 ="";
    private String touserid;

    private FamilyMusicPlayer Familyplayer; // 播放器

    private List<FamilySacrificeTypeBean.ListBean> cateList;
    public GiftClickListener getGiftClickListener() {
        return giftClickListener;
    }


    public void setGiftClickListener(GiftClickListener giftClickListener) {
        this.giftClickListener = giftClickListener;
    }
    public CateClickListener getCateClickListener() {
        return cateClickListener;
    }

    public void setCateClickListener(CateClickListener cateClickListener) {
        this.cateClickListener = cateClickListener;
    }

    private String username = SharedPreferencedUtils.getStr(Constants.USER_NAME);
    public  static    FamilyReceiveGiftBean.ListBean listBean_Detail;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }
    @Override
    protected int getLayoutId() {
        return R.layout.activity_family_detail;
    }
    @Override
    protected void initEventAndData() {
        Familyplayer = new FamilyMusicPlayer();
        bundle = getIntent().getExtras();
        feteid = bundle.getString("feteid");
        looknum = bundle.getInt("looknum");
        deadname = bundle.getString("deadname");
        fetename = bundle.getString("fetename");
        imgestr = bundle.getString("imgestr");
        music_url = bundle.getString("urlmp3");
        touserid = bundle.getString("touserid");
        cateClickListener = new CateClickListener();
        giftClickListener = new GiftClickListener();
        share_url =  bundle.getString("share_url");
        head_image  =  bundle.getString("head_image");
//        + "的祭祀"
        setToolbarTvTitle("祭祀详情页");
        getTvMorView();
        Drawable rightDrawable = getResources().getDrawable(R.mipmap.familydetail_add);
        rightDrawable.setBounds(0, 0, rightDrawable.getMinimumWidth(), rightDrawable
                .getMinimumHeight());
        getTvMorView().setCompoundDrawables(null, null, rightDrawable, null);
        setToolbarMoreClickListener(new View.OnClickListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public void onClick(View v) {
//                ToastUtil.Info("分享");
//                ActivityUtil.getInstance().openActivity(SpecialGiftListActivity.this,
// SpecialCartActivity.class,bundle);
                View view = View.inflate(FamilyDetailActivity.this, R.layout.item_pop_family, null);
                //创建并显示popWindow
                CustomPopWindow popWindow = new CustomPopWindow.PopupWindowBuilder
                        (FamilyDetailActivity.this)
                        .setView(view)
                        .setFocusable(true)
                        .enableBackgroundDark(true) //弹出popWindow时，背景是否变暗
                        .setBgDarkAlpha(0.7f) // 控制亮度
                        .setOutsideTouchable(true)
                        .setAnimationStyle(R.style.CustomPopWindowStyle) // 添加自定义显示和消失动画
                        .create()
                        .showAsDropDown(v, 0, 0);

                //  tv_family_add tv_family_record tv_family_added tv_receive_sacrifice
                // tv_receive_bless
                TextView share = view.findViewById(R.id.share);
                TextView tv_receive_sacrifice = view.findViewById(R.id.tv_receive_sacrifice);
                TextView tv_receive_bless = view.findViewById(R.id.tv_receive_bless);

                share.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View view, MotionEvent motionEvent) {
                        switch (motionEvent.getAction()) {
                            case MotionEvent.ACTION_DOWN:
                                share.setBackgroundResource(R.drawable.qin_qin_select);
                                share.setTextColor(Color.parseColor("#ff7a00"));
                                break;
                            case MotionEvent.ACTION_UP:
                                share.setBackgroundResource(R.drawable.qin_qin_select_false);
                                share.setTextColor(Color.parseColor("#282828"));
                                share(feteid);
                                popWindow.dissmiss();
                                break;
                            case MotionEvent.ACTION_CANCEL:
                                share.setBackgroundResource(R.drawable.qin_qin_select_false);
                                share.setTextColor(Color.parseColor("#282828"));
                                break;
                        }
                        return true;
                    }
                });

                tv_receive_sacrifice.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View view, MotionEvent motionEvent) {
                        switch (motionEvent.getAction()) {
                            case MotionEvent.ACTION_DOWN:
                                tv_receive_sacrifice.setBackgroundResource(R.drawable.qin_qin_select);
                                tv_receive_sacrifice.setTextColor(Color.parseColor("#ff7a00"));
                                break;
                            case MotionEvent.ACTION_UP:
                                tv_receive_sacrifice.setBackgroundResource(R.drawable.qin_qin_select_false);
                                tv_receive_sacrifice.setTextColor(Color.parseColor("#282828"));

                                if (familyReceiveGift == null || familyReceiveGift.getList() == null ||
                                        familyReceiveGift.getList().size() <= 0) {
                                        ToastUtil.Warning("还没有收到过礼物哦");
                                } else {
                                    bundle.putString("feteid",feteid);
                                    bundle.putString("familyReceiveGift", GsonUtil.obj2JSON(familyReceiveGift));
                                    ActivityUtil.getInstance().openActivity(FamilyDetailActivity.this, FamilyReceiveGiftActivity
                                            .class, bundle);
                                }
                                popWindow.dissmiss();
                                break;
                            case MotionEvent.ACTION_CANCEL:
                                tv_receive_sacrifice.setBackgroundResource(R.drawable.qin_qin_select_false);
                                tv_receive_sacrifice.setTextColor(Color.parseColor("#282828"));
                                break;
                        }
                        return true;
                    }
                });
                tv_receive_bless.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View view, MotionEvent motionEvent) {
                        switch (motionEvent.getAction()) {
                            case MotionEvent.ACTION_DOWN:
                                tv_receive_bless.setBackgroundResource(R.drawable.qin_qin_select);
                                tv_receive_bless.setTextColor(Color.parseColor("#ff7a00"));
                                break;
                            case MotionEvent.ACTION_UP:
                                bundle.putString("feteid",feteid) ;
                                tv_receive_bless.setBackgroundResource(R.drawable.qin_qin_select_false);
                                tv_receive_bless.setTextColor(Color.parseColor("#282828"));
                                ActivityUtil.getInstance().openActivity(FamilyDetailActivity.this,
                                        FamilyReceiveBlessActivity.class, bundle);
                                popWindow.dissmiss();
                                break;
                            case MotionEvent.ACTION_CANCEL:
                                tv_receive_bless.setBackgroundResource(R.drawable.qin_qin_select_false);
                                tv_receive_bless.setTextColor(Color.parseColor("#282828"));
                                break;
                        }
                        return true;
                    }
                });
            }
            });

        if (!deadname.contains(",")) {//单人
            tv_single_name.setText(deadname);

            String[] split = imgestr.split(",");//应该判断下

            //还有背景 相框

            ImageLoadProxy.getInstance()
                    .load(new ImageLoadConfiguration.Builder(this)
                            .url(Constants.IMG_SERVER_PATH + split[0])
                            .defaultImageResId(R.mipmap.icon_img_load_pre)
                            .imageView(iv_background).build());


            //头像
            ImageLoadProxy.getInstance()
                    .load(new ImageLoadConfiguration.Builder(this)
                            .url(Constants.IMG_SERVER_PATH + split[2])
                            .defaultImageResId(R.mipmap.icon_default_header_circle)
                            .imageView(iv_single_header).build());
            danren_url = Constants.IMG_SERVER_PATH + split[2];
            //相框
            ImageLoadProxy.getInstance()
                    .load(new ImageLoadConfiguration.Builder(this)
                            .url(Constants.IMG_SERVER_PATH + split[1])
                            .defaultImageResId(R.mipmap.icon_img_load_pre)
                            .imageView(iv_family_single_frame).build());

        } else {//双人
            ll_family_detail_single.setVisibility(View.GONE);
            ll_family_detail_double.setVisibility(View.VISIBLE);

            String[] split = deadname.split(",");
            tv_double_name1.setText(split[0]);
            tv_double_name2.setText(split[1]);

            String[] split1 = imgestr.split("@");
            String[] split2 = split1[0].split(",");
            String[] split3 = split1[1].split(",");

            //还有背景 相框
            ImageLoadProxy.getInstance()
                    .load(new ImageLoadConfiguration.Builder(this)
                            .url(Constants.IMG_SERVER_PATH + split2[0])
                            .defaultImageResId(R.mipmap.icon_img_load_pre)
                            .imageView(iv_background).build());

            if (split2.length == 3){
                //头像
                ImageLoadProxy.getInstance()
                        .load(new ImageLoadConfiguration.Builder(this)
                                .url(Constants.IMG_SERVER_PATH + split2[2])
                                .defaultImageResId(R.mipmap.icon_default_header_circle)
                                .imageView(iv_double_header1).build());
                suanren_url1 = Constants.IMG_SERVER_PATH + split2[2];
                //相框
                ImageLoadProxy.getInstance()
                        .load(new ImageLoadConfiguration.Builder(this)
                                .url(Constants.IMG_SERVER_PATH + split2[1])
                                .defaultImageResId(R.mipmap.icon_img_load_pre)
                                .imageView(iv_family_double1_frame).build());



            }

            if (split3.length == 3) {

                //头像
                ImageLoadProxy.getInstance()
                        .load(new ImageLoadConfiguration.Builder(this)
                                .url(Constants.IMG_SERVER_PATH + split3[2])
                                .defaultImageResId(R.mipmap.icon_default_header_circle)
                                .imageView(iv_double_header2).build());
                suanren_url2 = Constants.IMG_SERVER_PATH + split3[2];

                //相框
                ImageLoadProxy.getInstance()
                        .load(new ImageLoadConfiguration.Builder(this)
                                .url(Constants.IMG_SERVER_PATH + split3[1])
                                .defaultImageResId(R.mipmap.icon_img_load_pre)
                                .imageView(iv_family_double2_frame).build());

            }


        }
        initPlayer();
        initAdapter();
        loadCate();
        init_pm3(music_url);
    }
    private void init_pm3(String mp3) {
      /*  PlayEvent playEvent = new PlayEvent();
        List<Song> queue = new ArrayList<>();
        queue.add(getSong(mp3));
        playEvent.setIsplay(1);
        playEvent.setQueue(queue);
        MusicPlayer.getPlayer().setQueue(playEvent.getQueue(), 0);*/

        new Thread(new Runnable() {

            @Override
            public void run() {
                Familyplayer.playUrl(mp3);
            }
        }).start();

    }
    private Song getSong(String url) {
        Song song = new Song();
        song.setPath(url);
        return song;
    }
    //    List<View> views = new ArrayList<>();
    BlessYuListDialog blessYuListDialog;

    /**
     * 初始化界面程序
     */
    private void initView(FamilyBlessListBean blessListBean) {
//        upmarqueeview.removeAllViews();
//
//        setView(blessListBean);
//        upmarqueeview.setViews(views);
        blessYuListDialog = new BlessYuListDialog(this, null, blessListBean);
//        /**
//         * 设置item_view的监听
//         */
//        upmarqueeview.setOnItemClickListener(new UPMarqueeView.OnItemClickListener() {
//            @Override
//            public void onItemClick(int position, View view) {
////                Toast.makeText(FamilyDetailActivity.this, "items:" + position, Toast
////                        .LENGTH_SHORT).show();
//                blessYuListDialog.show();
//            }
//        });


    }

    /**
     * 初始化需要循环的View
     * 为了灵活的使用滚动的View，所以把滚动的内容让用户自定义
     * 假如滚动的是三条或者一条，或者是其他，只需要把对应的布局，和这个方法稍微改改就可以了，
     */
    private void setView(FamilyBlessListBean blessListBean) {
        for (int i = 0; i < blessListBean.getList().size(); i = i + 4) {
            final int position = i;
            //设置滚动的单个布局
//            LinearLayout moreView = (LinearLayout) LayoutInflater.from(this).inflate(R.layout
//                    .item_upmarquee_view, null);
//            //初始化布局的控件
//            TextView tv1 = (TextView) moreView.findViewById(R.id.tv1);
//            TextView tv2 = (TextView) moreView.findViewById(R.id.tv2);
//            TextView tv3 = (TextView) moreView.findViewById(R.id.tv3);
//            TextView tv4 = (TextView) moreView.findViewById(R.id.tv4);


            //进行对控件赋值
//            tv1.setText(blessListBean.getList().get(i).getWishes());
//            if (blessListBean.getList().size() > i + 1) {
//                //因为淘宝那儿是两条数据，但是当数据是奇数时就不需要赋值第二个，所以加了一个判断，还应该把第二个布局给隐藏掉
//                tv2.setText(blessListBean.getList().get(i + 1).getWishes().toString());
//            } else if (blessListBean.getList().size() > i + 2) {
//                tv3.setText(blessListBean.getList().get(i + 2).getWishes().toString());
//            } else if (blessListBean.getList().size() > i + 3) {
//                tv4.setText(blessListBean.getList().get(i + 3).getWishes().toString());
//            }  else {
////                moreView.findViewById(R.id.rl2).setVisibility(View.GONE);
//            }
//
//            FamilyBlessListBean.ListBean listBean = blessListBean.getList().get(i);//只要循环进来 肯定有
//            tv1.setText(listBean.getNickname() + ":" + listBean.getWishes());
//
//            if (blessListBean.getList().size() > (i + 1)) {
//                FamilyBlessListBean.ListBean listBean1 = blessListBean.getList().get(i + 1);
//                if (listBean1 != null) {
//                    tv2.setText(listBean1.getNickname() + ":" + listBean1.getWishes());
//                }
//            } else {
//                tv4.setVisibility(View.GONE);
//            }
//
//            if (blessListBean.getList().size() > (i + 2)) {
//                FamilyBlessListBean.ListBean listBean2 = blessListBean.getList().get(i + 2);
//                if (listBean2 != null) {
//                    tv3.setText(listBean2.getNickname() + ":" + listBean2.getWishes());
//                }
//            } else {
//                tv4.setVisibility(View.GONE);
//            }
//
//            if (blessListBean.getList().size() > (i + 3)) {
//                FamilyBlessListBean.ListBean listBean3 = blessListBean.getList().get(i + 3);
//                if (listBean3 != null) {
//                    tv4.setText(listBean3.getNickname() + ":" + listBean3.getWishes());
//                }
//            } else {
//                tv4.setVisibility(View.GONE);
//            }
//
//            //添加到循环滚动数组里面去
//            views.add(moreView);
        }
    }

    private void initPlayer() {
        player = MediaPlayer.create(this, R.raw.bianpao2);
        player.setOnCompletionListener(this);
    }

    private void loadRecevieBlessYU(){
        Map<String, String> request = new HashMap<>();
        request.put("token", SharedPreferencedUtils.getStr(Constants.USER_TOKEN));
        request.put("usernum", SharedPreferencedUtils.getStr(Constants.USER_NUM));
        request.put("feteid", feteid);
        request.put("pageIndex", "1");
        request.put("pageSize", "9999");

        mPresenter.familyBlessList(GsonUtil.obj2JSON(request));
    }

    private void addLookNum() {
        Map<String, String> request = new HashMap<>();
        request.put("token", SharedPreferencedUtils.getStr(Constants.USER_TOKEN));
        request.put("usernum", SharedPreferencedUtils.getStr(Constants.USER_NUM));
        request.put("feteid", feteid);
        request.put("looknum", looknum + "");
        mPresenter.familyViewCount(GsonUtil.obj2JSON(request));
    }

    private void initAdapter() {
        if (cateAdapter == null) {
            cateAdapter = new FamilyCateAdapter(R.layout.item_family_detail_cate);
            cateAdapter.setOnItemClickListener(cateClickListener);
            cateAdapter.setOnItemChildClickListener(cateClickListener);
            cateAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN); //切换动画
            cateAdapter.setEnableLoadMore(false);

            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//            recyclerView.addItemDecoration(new HorizontalDividerItemDecoration.Builder(this)
//                    .build());
            recycler_type.setLayoutManager(layoutManager);
            recycler_type.setAdapter(cateAdapter);
        }
        if (detailReceiveGiftAdapter == null) {
            detailReceiveGiftAdapter = new FamilyDetailReceiveGiftAdapter(R.layout.item_family_detail_receive_gift);
            detailReceiveGiftAdapter.setOnItemClickListener(giftClickListener);
            detailReceiveGiftAdapter.setOnItemChildClickListener(giftClickListener);
            detailReceiveGiftAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN); //切换动画
            detailReceiveGiftAdapter.setEnableLoadMore(false);

//            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
//            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

            GridLayoutManager layoutManager = new GridLayoutManager(this, 3, LinearLayoutManager
                    .VERTICAL, false);

//            recyclerView.addItemDecoration(new HorizontalDividerItemDecoration.Builder(this)
//                    .build());
            recycler_gift.setLayoutManager(layoutManager);
            recycler_gift.setAdapter(detailReceiveGiftAdapter);
        }

        if (testAdapter == null) {
            testAdapter = new TestAdapter(R.layout.item_te);
            testAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN); //切换动画
            testAdapter.setEnableLoadMore(false);

            testAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    blessYuListDialog.show();
                }
            });

            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);


            recycler_blessyu.addItemDecoration(new HorizontalDividerItemDecoration.Builder(this)
                    .build());
            recycler_blessyu.setLayoutManager(layoutManager);
            recycler_blessyu.setAdapter(testAdapter);

        }

    }

    private void loadCate() {
        Map<String, String> request = new HashMap<>();
        request.put("token", SharedPreferencedUtils.getStr(Constants.USER_TOKEN));
        request.put("usernum", SharedPreferencedUtils.getStr(Constants.USER_NUM));
        request.put("sign", Constants.CART_MODEL_FAMILY);
        mPresenter.familyCate(GsonUtil.obj2JSON(request));

    }

    private void loadReceiveGift() {
        loadingDialog.show();
        Map<String, String> request = new HashMap<>();
        request.put("token", SharedPreferencedUtils.getStr(Constants.USER_TOKEN));
        request.put("usernum", SharedPreferencedUtils.getStr(Constants.USER_NUM));
        request.put("moduleid", feteid);
        request.put("modelename", Constants.CART_MODEL_FAMILY);
        request.put("pageIndex", "1");
        request.put("pageSize", "99999");
        mPresenter.familyReceiveGiftById(GsonUtil.obj2JSON(request));
    }

    @OnClick({R.id.tv_send_bless, R.id.tv_gift_region, R.id.tv_jitai,R.id.music_onclick,R.id.iv_single_header,R.id.iv_double_header1,R.id.iv_double_header2})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.tv_send_bless://赠送寄语
                bundle.putString("feteid",feteid);
                bundle.getString("touserid",touserid);
                ActivityUtil.getInstance().openActivity(this, FamilyBlessYuListActivity.class,
                        bundle);
                break;
            case R.id.tv_gift_region://礼品区
                if(cateAdapter.getItem(0)!=null)
                {
                    bundle.putString("selectCateId", cateAdapter.getItem(0).getId() + "");//默认选中第一条
                    bundle.putString("feteid",feteid);
                    bundle.putString("cateList", GsonUtil.obj2JSON(cateList));
                    bundle.getString("touserid",touserid);
                    ActivityUtil.getInstance().openActivity(this, FamilyGiftListActivity.class, bundle);
                }else {
                SAToast.makeText(this,"请先登录!").show();
                }
                break;
            case R.id.tv_jitai:
//                ToastUtil.Success("祭台"); //familyReceiveGift
                bundle.putString("feteid",feteid);
                    ActivityUtil.getInstance().openActivity(this, FamilyLetterActivity
                            .class, bundle);
                break;
            case R.id.music_onclick:
                if(is_music)
                {
                    Familyplayer.pause();
                    music_onclick.setBackgroundResource(R.mipmap.music_off);
                    is_music=false;
                }else {
                    music_onclick.setBackgroundResource(R.mipmap.music_on);
                    is_music=true;
             /*       PlayEvent playEvent1 = new PlayEvent();
                    List<Song> queue = new ArrayList<>();
                    queue.add(getSong(music_url));
                    playEvent1.setIsplay(1);
                    playEvent1.setQueue(queue); */
                    Familyplayer.start();
                }
                break;
            case R.id.iv_single_header://单人头像
                List<ImageInfo> imageInfo = new ArrayList<>();
                ImageInfo info = new ImageInfo();
                info.setBigImageUrl(danren_url);
                info.setImageViewHeight(200);
                info.setImageViewWidth(150);
                info.setThumbnailUrl(danren_url);
                imageInfo.add(info);
                Intent intent = new Intent(this, ImagePreviewActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable(ImagePreviewActivity.IMAGE_INFO, (Serializable) imageInfo);
                bundle.putInt(ImagePreviewActivity.CURRENT_ITEM, 0);
                intent.putExtras(bundle);
                this.startActivity(intent);
                ((Activity) this).overridePendingTransition(0, 0);
                break;

            case R.id.iv_double_header1:
                List<ImageInfo> imageInfo1 = new ArrayList<>();
                ImageInfo info1 = new ImageInfo();
                info1.setBigImageUrl(suanren_url1);
                info1.setImageViewHeight(200);
                info1.setImageViewWidth(150);
                info1.setThumbnailUrl(suanren_url1);
                imageInfo1.add(info1);
                Intent intent1 = new Intent(this, ImagePreviewActivity.class);
                Bundle bundle1 = new Bundle();
                bundle1.putSerializable(ImagePreviewActivity.IMAGE_INFO, (Serializable) imageInfo1);
                bundle1.putInt(ImagePreviewActivity.CURRENT_ITEM, 0);
                intent1.putExtras(bundle1);
                this.startActivity(intent1);
                ((Activity) this).overridePendingTransition(0, 0);
                break;
            case R.id.iv_double_header2:
                List<ImageInfo> imageInfo2 = new ArrayList<>();
                ImageInfo info2 = new ImageInfo();
                info2.setBigImageUrl(suanren_url2);
                info2.setImageViewHeight(200);
                info2.setImageViewWidth(150);
                info2.setThumbnailUrl(suanren_url2);
                imageInfo2.add(info2);
                Intent intent2 = new Intent(this, ImagePreviewActivity.class);
                Bundle bundle2 = new Bundle();
                bundle2.putSerializable(ImagePreviewActivity.IMAGE_INFO, (Serializable) imageInfo2);
                bundle2.putInt(ImagePreviewActivity.CURRENT_ITEM, 0);
                intent2.putExtras(bundle2);
                this.startActivity(intent2);
                ((Activity) this).overridePendingTransition(0, 0);
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

    @Override
    public void stateError() {
        super.stateError();
        if (detailReceiveGiftAdapter.getData() == null || detailReceiveGiftAdapter.getData().size() <= 0) {
            View emptyView = View.inflate(this, R.layout.partial_empty_view, null);
            detailReceiveGiftAdapter.setEmptyView(emptyView);
        }
    }

    @Override
    public void showContent(String msg) {

    }

    @Override
    public void familyAdd(String message) {

    }


    @Override
    public void familyList(FamilyListBean funsharingListBeans) {

    }

    @Override
    public void familyListMy(FamilyListBean familyListBean) {

    }

    int count=0;
    int height=0;
    int new_height=0;
    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
//
//            FamilyBlessListBean.ListBean item = testAdapter.getItem(0);
//            Logger.e("size:" + testAdapter.getData().size());
//            testAdapter.remove(0);
//            testAdapter.addData(item);

            TranslateAnimation animation=new TranslateAnimation(0,0,height,height-55);
            height=height-55;
            new_height = new_height+55;
            //不停顿
            animation.setInterpolator(new LinearInterpolator());
            //重复次数
            animation.setRepeatCount(0);
            count=count+1;
            if((familyBlessList.getList().size()*3) ==count)
            {
                height = 0;
                count = 0;
            }
            animation.setFillAfter(true);
            animation.setDuration(1500);
            //开启动画
            if(ll_blessyy_parent!=null)
            {
                ll_blessyy_parent.startAnimation(animation);
            }

        }
    };


    @Override
    public void familyBlessList(FamilyBlessListBean familyBlessListBean) {
        loadReceiveGift();
        familyBlessList = familyBlessListBean;

        List<FamilyBlessListBean.ListBean> list = familyBlessListBean.getList();

        if (familyBlessListBean != null && familyBlessListBean.getList() != null && list.size() > 0) {
            initView(familyBlessListBean);

        } else {
            //隐藏 设置空布局
        }
        blessYuListDialog = new BlessYuListDialog(this, null, familyBlessListBean);//j祭事语的弹出窗口

        testAdapter.setNewData(familyBlessListBean.getList());


        ViewGroup.LayoutParams layoutParams = tv_blessyu_temp.getLayoutParams();

        if(familyBlessListBean.getList().size()>0)
        {
            tv_blessyu_temp.setVisibility(View.GONE);//
        }
        if (list.size() >3){
            Timer timer = new Timer();
            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    handler.sendEmptyMessage(0);
                }
            };
            timer.schedule(task, 2000, 2000);
        }
        if (familyBlessListBean.getList() !=null&& familyBlessListBean.getList().size()>0){
            int size = list.size();
            for (int i =0 ; i < size ; i++){
                FamilyBlessListBean.ListBean item = list.get(i);
                TextView textView=new TextView(this);
                textView.setTextSize(16);
                textView.setPadding(5,3,5,3);
                textView.setBackgroundColor(getResources().getColor(R.color.transparent));
                textView.setLayoutParams(layoutParams);
                TextView new_text = null;
                if(item.getType().equals("1"))//代表这是礼物
                {
                    textView =  TextViewSpanUtl.setFamilyWUext(this,item.getBuyusername(),"送了"+item.getWishes(),textView);//标识祝福语
                }else if(item.getType().equals("0")){
                    textView= TextViewSpanUtl.setFamilyYUext(this,item.getBuyusername(),item.getWishes(),textView);//标识祝福语
                }else {
                    textView= TextViewSpanUtl.setFamilyYUext(this,"系统","逝者也矣，生者如斯22",textView);//标识祝福语
                }
                ll_blessyy_parent.addView(textView);
              }
            }
        }


    @Override
    public void familyReceiveSacrifice(FamilyReceiveGiftBean familyReceiveGiftBean) {

    }

    @Override
    public void familyListGift(FamilyGiftListBean familyGiftListBean) {

    }

    @Override
    public void familySendGift(FamilySendGiftRecordBean familySendGiftRecordBean) {

    }

    @Override
    public void familyReceiveBless(FamilyBlessListRecevieBean familyBlessListRecevieBean) {

    }

    @Override
    public void familyViewCount(String message) {

    }

    @Override
    public void familyCate(FamilySacrificeTypeBean familySacrificeTypeBean) {
//        totalPage = funsharingListBeans.getTotalPage();
//        if (isRefreshing) {
//            adapter.setNewData(funsharingListBeans.getList());
//            refreshLayout.finishRefresh();
//        } else {
//            adapter.addData(funsharingListBeans.getList());
//            refreshLayout.finishLoadmore();
//        }
        cateList = familySacrificeTypeBean.getList();
        cateAdapter.addData(cateList);

        loadRecevieBlessYU();
    }

    @Override
    public void familyUsualLanguage(FamilyUsualLanguageBean familyUsualLanguageBean) {

    }

    @Override
    public void familyPhotoFrame(FamilyPhotoFrameBean familyPhotoFrameBean) {

    }

    @Override
    public void familyPhotoBack(FamilyPhotoFrameBean familyPhotoFrameBean) {

    }

    @Override
    public void familyBlessAdd(String message) {

    }

    @Override
    public void familySearch(FamilyListBean familyListBean) {

    }

    @Override
    public void familyReceiveGiftById(FamilyReceiveGiftBean familyReceiveGiftBean) {
        loadingDialog.dismiss();
        addLookNum();
        familyReceiveGift = familyReceiveGiftBean;

        if ( (familyReceiveGiftBean.getList() == null || familyReceiveGiftBean.getList().size() <= 0)) {
            View emptyView = View.inflate(this, R.layout.partial_empty_view, null);
            TextView viewById = emptyView.findViewById(R.id.tv_message);
            detailReceiveGiftAdapter.setEmptyView(emptyView);
        }else {
            detailReceiveGiftAdapter.setNewData(familyReceiveGiftBean.getList());
        }

    }

    @Override
    public void familySaveFPhotoFrameAndBack(String message) {

    }

    @Override
    public void familyQueryFrameAndBack(FamilyQueryFramBackBean familyQueryFramBackBean) {

    }


    @Override
    public void familyDel(String message) {

    }

    @Override
    public void uploadHeader(FileUploadBean fileUploadBean) {

    }

    @Override
    public void generatorOrder(String blessOrderBean) {

    }

    @Override
    public void immediateOrder(String blessOrderBean) {

    }

    @Override
    public void familyCartAdd(String message) {

    }

    @Override
    public void familyCartList(BlessCartListBean blessCartListBean) {

    }

    @Override
    public void familyCartUpdate(CartUpdateBean message) {

    }


    @Override
    public void familyCartDel(String message) {

    }

    @Override
    public void familySearch(BlessListBean blessListBean) {

    }

    @Override
    public void selectletter(LetterBean letterBean) {

    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        iv_bianpao_left.setBackgroundResource(R.mipmap.icon_family_bianpao_no);
        iv_bianpao_right.setBackgroundResource(R.mipmap.icon_family_bianpao_right_no);

        music_onclick.setBackgroundResource(R.mipmap.music_on);
        is_music=true;
/*        PlayEvent playEvent1 = new PlayEvent();
        List<Song> queue = new ArrayList<>();
        queue.add(getSong(music_url));
        playEvent1.setIsplay(1);
        playEvent1.setQueue(queue);*/
        Familyplayer.start();
    }


    private class CateClickListener implements BaseQuickAdapter.OnItemClickListener,
            BaseQuickAdapter
                    .OnItemChildClickListener {

        @Override
        public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

            bundle.putString("selectCateId", cateAdapter.getItem(position).getId() + "");//默认选中第一条
            bundle.putString("cateList", GsonUtil.obj2JSON(cateList));
            ActivityUtil.getInstance().openActivity(FamilyDetailActivity.this,
                    FamilyGiftListActivity.class, bundle);

        }

        @Override
        public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

            bundle.putString("selectCateId", cateAdapter.getItem(position).getId() + "");//默认选中第一条
            bundle.putString("cateList", GsonUtil.obj2JSON(cateList));
            ActivityUtil.getInstance().openActivity(FamilyDetailActivity.this,
                    FamilyGiftListActivity.class, bundle);
        }
    }

    private class GiftClickListener implements BaseQuickAdapter.OnItemClickListener,
            BaseQuickAdapter
                    .OnItemChildClickListener {

        @Override
        public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        }

        @Override
        public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
            FamilyReceiveGiftBean.ListBean item = detailReceiveGiftAdapter.getItem(position);

            RxDialogLoading dialogLoading = new RxDialogLoading(FamilyDetailActivity.this);
            View view1 = View.inflate(FamilyDetailActivity.this, R.layout
                    .partial_family_img_dialog, null);
            dialogLoading.setContentView(view1);

            TextView tvName = view1.findViewById(R.id.tv_name);
            TextView tv_nickname = view1.findViewById(R.id.tv_nickname);

            ImageView imageView = view1.findViewById(R.id.iv_url);
            tv_nickname.setText(item.getBuyusername());
            tvName.setText(item.getGoodsname());
           // tv_nickname.setText(item.getNickname());
            ImageLoadProxy.getInstance()
                    .load(new ImageLoadConfiguration.Builder(App.getInstance())
                            .url(Constants.IMG_SERVER_PATH + item.getImgurl())
                            .defaultImageResId(R.mipmap.icon_img_load_pre)
                            .imageView(imageView).build());
            dialogLoading.show();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        if (Familyplayer != null) {
            Familyplayer.stop();
            Familyplayer = null;
        }
        if (player != null) {
            player.stop();
            player.release();
            player = null;
        }
        super.onDestroy();
    }

    @org.greenrobot.eventbus.Subscribe
    public void YuEvent(YuBean bean)
    {
        ToastUtil.Success("祭语发送成功");
        ViewGroup.LayoutParams layoutParams = tv_blessyu_temp.getLayoutParams();
        TextView textView=new TextView(this);
        textView.setTextSize(16);
        textView.setPadding(5,3,5,3);
        textView.setBackgroundColor(getResources().getColor(R.color.transparent));
        textView.setLayoutParams(layoutParams);
        TextView new_text = null;
        new_text =  TextViewSpanUtl.setFamilyYUext(this,bean.getName(),bean.getContent(),textView);//标识祝福语
        ll_blessyy_parent.addView(new_text);

    }

    @org.greenrobot.eventbus.Subscribe
    public void OnEvent(FamilyReceiveGiftBean.ListBean listBean)
    {
        Familyplayer.pause();
        music_onclick.setBackgroundResource(R.mipmap.music_off);
        is_music=false;
        listBean.setDeadname(deadname);
        player.start();
        iv_bianpao_right.setBackgroundResource(R.mipmap.icon_family_bianpao_right);
        iv_bianpao_left.setBackgroundResource(R.mipmap.icon_family_bianpao);

                ViewGroup.LayoutParams layoutParams = tv_blessyu_temp.getLayoutParams();
                TextView textView=new TextView(this);
                textView.setTextSize(16);
                textView.setPadding(5,3,5,3);
                textView.setBackgroundColor(getResources().getColor(R.color.transparent));
                textView.setLayoutParams(layoutParams);
                TextView new_text = null;
                new_text =  TextViewSpanUtl.setFamilyWUext(this,listBean.getBuyusername(),"送了"+listBean.getGoodsname()+"x1",textView);//标识祝福语
                ll_blessyy_parent.addView(new_text);
        detailReceiveGiftAdapter.addData(0,listBean);
        familyReceiveGift.setList(detailReceiveGiftAdapter.getData()    );
        recycler_gift.scrollToPosition(0);
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
            web.setTitle(deadname + "的祭祀");//标题
            UMImage umImage = new UMImage(this, head_image);
            web.setThumb(umImage);  //缩略图
            web.setDescription("友邦友-祭祀分享");//描述

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

    @Subscribe
    public  void onevent(JPushFete fete)
    {
        xiaoxi_linear_next.setVisibility(View.VISIBLE);
        if(fete.getUrl().contains("wishe"))//祭语
        {
            xiaoxi_image.setBackgroundResource(R.mipmap.family_yu);
            xiaoxi_message.setText("\\@收到新的祭语");
        }else  if(fete.getUrl().contains("letter")){//祭事信
            xiaoxi_image.setBackgroundResource(R.mipmap.family_xin);
            xiaoxi_message.setText("\\@收到新的祭事信");
        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                xiaoxi_linear_next.setVisibility(View.GONE);
            }
        },1000);
    }
}
