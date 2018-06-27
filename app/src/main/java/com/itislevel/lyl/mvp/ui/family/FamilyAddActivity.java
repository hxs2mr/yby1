package com.itislevel.lyl.mvp.ui.family;

import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.itislevel.lyl.R;
import com.itislevel.lyl.adapter.FamilyPhotoFrameAdapter;
import com.itislevel.lyl.app.App;
import com.itislevel.lyl.app.Constants;
import com.itislevel.lyl.base.RootActivity;
import com.itislevel.lyl.mvp.model.bean.BlessCartListBean;
import com.itislevel.lyl.mvp.model.bean.BlessListBean;
import com.itislevel.lyl.mvp.model.bean.BlessOrderBean;
import com.itislevel.lyl.mvp.model.bean.CartUpdateBean;
import com.itislevel.lyl.mvp.model.bean.FamilyAddBean;
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
import com.itislevel.lyl.mvp.model.bean.LetterBean;
import com.itislevel.lyl.mvp.ui.blessing.BlessReceiveBlessGiftActivity;
import com.itislevel.lyl.mvp.ui.pay.PayInfoActivity;
import com.itislevel.lyl.net.LoaderStyle;
import com.itislevel.lyl.utils.ActivityUtil;
import com.itislevel.lyl.utils.FileUtils;
import com.itislevel.lyl.utils.GsonUtil;
import com.itislevel.lyl.utils.RegexUtil;
import com.itislevel.lyl.utils.SAToast;
import com.itislevel.lyl.utils.SharedPreferencedUtils;
import com.itislevel.lyl.utils.ToastUtil;
import com.itislevel.lyl.utils.UPImageYA;
import com.itislevel.lyl.utils.imageload.ImageLoadConfiguration;
import com.itislevel.lyl.utils.imageload.ImageLoadProxy;
import com.itislevel.lyl.utils.rxbus.RxBus;
import com.itislevel.lyl.utils.rxbus.annotation.Subscribe;
import com.itislevel.lyl.utils.rxbus.annotation.UseRxBus;
import com.itislevel.lyl.utils.rxbus.event.EventThread;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import cn.finalteam.rxgalleryfinal.RxGalleryFinal;
import cn.finalteam.rxgalleryfinal.bean.ImageCropBean;
import cn.finalteam.rxgalleryfinal.imageloader.ImageLoaderType;
import cn.finalteam.rxgalleryfinal.rxbus.RxBusResultDisposable;
import cn.finalteam.rxgalleryfinal.rxbus.event.ImageRadioResultEvent;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * 新建祭祀
 */
@UseRxBus
public class  FamilyAddActivity extends RootActivity<FamilyPresenter>
        implements FamilyContract.View {

    //选择按钮
    @BindView(R.id.tv_family_radio_single)
    TextView tv_family_radio_single;

    @BindView(R.id.tv_family_radio_double)
    TextView tv_family_radio_double;

    //单人
    @BindView(R.id.ll_family_single)
    LinearLayout ll_family_single;

    @BindView(R.id.et_family_single_died_name)
    EditText et_family_single_died_name;

    @BindView(R.id.et_family_single_name)
    EditText et_family_single_name;

    @BindView(R.id.iv_family_single_header)
    ImageView iv_family_single;

    @BindView(R.id.iv_family_single_header1)
    ImageView iv_family_single1;
    //双人

    @BindView(R.id.ll_family_double)
    LinearLayout ll_family_double;

    @BindView(R.id.et_family_double_died_name1)
    EditText et_family_double_died_name1;
    @BindView(R.id.et_family_double_died_name2)
    EditText et_family_double_died_name2;

    @BindView(R.id.et_family_double_name)
    EditText et_family_double_name;

    @BindView(R.id.et_family_relation)
    EditText et_family_relation;
    @BindView(R.id.iv_family_double1_header)
    ImageView iv_family_double1;

    @BindView(R.id.iv_family_double1_header1)
    ImageView iv_family_double1_1;
    @BindView(R.id.iv_family_double2_header2)
    ImageView iv_family_double2_2;

    @BindView(R.id.iv_family_double2_header)
    ImageView iv_family_double2;


    @BindView(R.id.recycler_photo)
    RecyclerView recycler_photo;

    @BindView(R.id.recycler_back)
    RecyclerView recycler_back;

    @BindView(R.id.iv_family_single_frame)
    ImageView iv_family_single_frame;
    @BindView(R.id.iv_family_double1_frame)
    ImageView iv_family_double1_frame;
    @BindView(R.id.iv_family_double2_frame)
    ImageView iv_family_double2_frame;

    @BindView(R.id.iv_family_double3_frame)
    ImageView iv_family_double3_frame;
    //创建按钮
    @BindView(R.id.tv_create)
    TextView tv_create;

    Bundle bundle = null;
    private String PROVINCE_ID = "";
    private String CITY_ID = "";
    private String COUNTY_ID = ""; //很多情况下是空的

    private String PROVINCE_NAME = "";
    private String CITY_NAME = "";
    private String COUNTY_NAME = ""; //很多情况下是空的


    //是否已添加没有更多数据的布局
    private boolean isAddNoMoreView = false;
    private int pageIndex = 1;//分页加载时 当前页是第几页
    private int totalPage = 0;//总页数
    private boolean isRefreshing = true;//当前是刷新还是加载

    private FamilyPhotoFrameAdapter photoFrameAdapter;
    private FamilyPhotoFrameAdapter photoBackAdapter;


    boolean isSingle = true;//是单人还是双人

    private String singlePath;//单人头像路径
    private String doublePath1;//双人头像第一张
    private String doublePath2;//双人头像第二张

    private boolean isUploadSingle = false;//当前是否上传单人的
    private boolean isUploadDouble1 = false;//当前是否上传双人第一张的
    private boolean isUploadDouble2 = false;//当前是否上传双人第二张的


    private String selectPhotoFrameUrl = "";//选择的相框的地址 默认为免费的
    private String selectPhotoBackUrl = "";//选择的背景的地址 默认为免费的

    private String selectPhotoFrameId = "";//选择的相框的id 默认为免费的
    private String selectPhotoBackId = "";//选择的背景的id 默认为免费的

    PhotoFramClickListener framClickListener;
    PhotoBackClickListener backClickListener;
    private String clickGoodsId;
    private FamilyQueryFramBackBean queryFramBack;
    private String totalPrice;
    private String goodesc;
    private String goodsDetail;
    public boolean isPhotoFram = true;//默认选择的相框

    @Override
    protected int getLayoutId() {
        return R.layout.activity_family_add;
    }
    @Override
    protected void initEventAndData() {

        bundle = this.getIntent().getExtras();

        PROVINCE_ID = bundle.getString(Constants.PROVINCE_ID);
        CITY_ID = bundle.getString(Constants.CITY_ID);
        COUNTY_ID = bundle.getString(Constants.COUNTY_ID);


        PROVINCE_NAME = bundle.getString(Constants.PROVINCE_NAME);
        CITY_NAME = bundle.getString(Constants.CITY_NAME);
        COUNTY_NAME = bundle.getString(Constants.COUNTY_NAME);

        framClickListener = new PhotoFramClickListener();
        backClickListener = new PhotoBackClickListener();

        setToolbarTvTitle("新建祭祀");

        initAdapter();


        loadPhotoFrame();//加载相框
        loadPhotoBack();//加载背景

        ViewTreeObserver vto2 = iv_family_single_frame.getViewTreeObserver();
    }
    /**
     * 初始化adapter
     */
    private void initAdapter() {
        if (photoFrameAdapter == null) {
            photoFrameAdapter = new FamilyPhotoFrameAdapter(R.layout.item_family_add_photo_frame);
            photoFrameAdapter.setOnItemClickListener(framClickListener);
//            photoFrameAdapter.setOnItemChildClickListener(framClickListener);
            photoFrameAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN); //切换动画
            photoFrameAdapter.setEnableLoadMore(false);

            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
//            recycler_photo.addItemDecoration(new HorizontalDividerItemDecoration.Builder(this)
//                    .build());
            recycler_photo.setLayoutManager(layoutManager);
            recycler_photo.setAdapter(photoFrameAdapter);
        }
        if (photoBackAdapter == null) {
            photoBackAdapter = new FamilyPhotoFrameAdapter(R.layout.item_family_add_photo_frame);
            photoBackAdapter.setOnItemClickListener(backClickListener);
//            photoBackAdapter.setOnItemChildClickListener(backClickListener);
            photoBackAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN); //切换动画
            photoBackAdapter.setEnableLoadMore(false);

            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
//            recycler_photo.addItemDecoration(new HorizontalDividerItemDecoration.Builder(this)
//                    .build());
            recycler_back.setLayoutManager(layoutManager);
            recycler_back.setAdapter(photoBackAdapter);
        }

    }


    //“background”背景   “photoframe”相框
    private void loadPhotoFrame() {
        Map<String, String> request = new HashMap<>();
        request.put("token", SharedPreferencedUtils.getStr(Constants.USER_TOKEN));
        request.put("usernum", SharedPreferencedUtils.getStr(Constants.USER_NUM));
        request.put("sign", "photoframe");

        mPresenter.familyPhotoFrame(GsonUtil.obj2JSON(request));
    }

    private void loadPhotoBack() {
        Map<String, String> request = new HashMap<>();
        request.put("token", SharedPreferencedUtils.getStr(Constants.USER_TOKEN));
        request.put("usernum", SharedPreferencedUtils.getStr(Constants.USER_NUM));
        request.put("sign", "background");

        mPresenter.familyPhotoBack(GsonUtil.obj2JSON(request));
    }

    @OnClick({R.id.tv_family_radio_single, R.id.tv_family_radio_double, R.id
            .iv_family_single_header, R
            .id.iv_family_double1_header, R.id.iv_family_double2_header, R.id.tv_create})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.tv_family_radio_single://单人
                setLLGoge();
                ll_family_single.setVisibility(View.VISIBLE);
                tv_family_radio_single.setBackground(getResources().getDrawable(R.drawable
                        .add_fete_shape));
                isSingle = true;
                tv_family_radio_single.setTextColor(getResources().getColor(R.color.colorWhite));

                break;
            case R.id.tv_family_radio_double://双人
                setLLGoge();
                ll_family_double.setVisibility(View.VISIBLE);
                tv_family_radio_double.setBackground(getResources().getDrawable(R.drawable
                        .add_fete_shape));
                isSingle = false;
                tv_family_radio_double.setTextColor(getResources().getColor(R.color.colorWhite));

                break;
            case R.id.iv_family_single_header://单人头像
                RxGalleryFinal
                        .with(this)
                        .image()
                        .radio()
                        .imageLoader(ImageLoaderType.GLIDE)
                        .subscribe(new RxBusResultDisposable<ImageRadioResultEvent>() {
                            @Override
                            protected void onEvent(ImageRadioResultEvent imageRadioResultEvent)
                                    throws Exception {

                                ImageCropBean result = imageRadioResultEvent.getResult();

                                isUploadSingle = true;
                                isUploadDouble1 = false;
                                isUploadDouble2 = false;
                                uploadHeader(result.getOriginalPath());
                            }
                        })
                        .openGallery();

                break;
            case R.id.iv_family_double1_header://双人头像1
                RxGalleryFinal
                        .with(this)
                        .image()
                        .radio()
                        .imageLoader(ImageLoaderType.GLIDE)
                        .subscribe(new RxBusResultDisposable<ImageRadioResultEvent>() {
                            @Override
                            protected void onEvent(ImageRadioResultEvent imageRadioResultEvent)
                                    throws Exception {

                                ImageCropBean result = imageRadioResultEvent.getResult();
//                                doublePath1 = result.getOriginalPath();
//
//                                ImageLoadProxy.getInstance()
//                                        .load(new ImageLoadConfiguration.Builder(App
// .getInstance())
//                                                .url(doublePath1)
//                                                .imageView(iv_family_double1).build());
                                isUploadSingle = false;
                                isUploadDouble1 = true;
                                isUploadDouble2 = false;
                                uploadHeader(result.getOriginalPath());
                            }
                        })
                        .openGallery();


                break;
            case R.id.iv_family_double2_header://双人头像2
                RxGalleryFinal
                        .with(this)
                        .image()
                        .radio()
                        .imageLoader(ImageLoaderType.GLIDE)
                        .subscribe(new RxBusResultDisposable<ImageRadioResultEvent>() {
                            @Override
                            protected void onEvent(ImageRadioResultEvent imageRadioResultEvent)
                                    throws Exception {

                                ImageCropBean result = imageRadioResultEvent.getResult();
//                                doublePath2 = result.getOriginalPath();
//
//                                ImageLoadProxy.getInstance()
//                                        .load(new ImageLoadConfiguration.Builder(App
// .getInstance())
//                                                .url(doublePath2)
//                                                .imageView(iv_family_double2).build());
                                isUploadSingle = false;
                                isUploadDouble1 = false;
                                isUploadDouble2 = true;
                                uploadHeader(result.getOriginalPath());
                            }
                        })
                        .openGallery();
                break;
            case R.id.tv_create://创建按钮
                createFete();
                break;
        }
    }
    private void uploadHeader(String localPath) {
        loadingDialog.show();
        File tempPic = new File(localPath);

        final String pic_path = tempPic.getPath();
        String targetPath = tempPic.getPath();
        //调用压缩图片的方法，返回压缩后的图片path
        final String compressImage = UPImageYA.compressImage(pic_path, targetPath, 50);
        final File temp = new File(compressImage);
        RequestBody requestFile =
                RequestBody.create(MediaType.parse("multipart/form-data"), temp);
        String filename = "hxs_family" + SharedPreferencedUtils.getStr(Constants
                .USER_TOKEN) + System.currentTimeMillis();
        MultipartBody.Part body = MultipartBody.Part.createFormData(filename,
                temp.getName(), requestFile);
        mPresenter.uploadHeader(body);     //上传图片文件
    }

    private void createFete() {
        loadingDialog.show();
        Map<String, String> request = new HashMap<>();
        request.put("token", SharedPreferencedUtils.getStr(Constants.USER_TOKEN));
        request.put("usernum", SharedPreferencedUtils.getStr(Constants.USER_NUM));
        request.put("userid", SharedPreferencedUtils.getStr(Constants.USER_ID));

        request.put("provid", PROVINCE_ID);
        request.put("provincename", PROVINCE_NAME);

        request.put("cityid", CITY_ID);
        request.put("cityname", CITY_NAME);

        request.put("ptsacrifid", selectPhotoFrameId);
        request.put("bgsacrifid", selectPhotoBackId);

        if (isSingle) {
            if (TextUtils.isEmpty(singlePath)) {
                SAToast.makeText(this,"请上传头像").show();
                loadingDialog.dismiss();
                return;
            }
            String diename = et_family_single_died_name.getText().toString().trim();
            if (TextUtils.isEmpty(diename)) {
                SAToast.makeText(this,"请输入逝者姓名~~").show();
                loadingDialog.dismiss();
                return;
            }
            int length = diename.length();
            if (length<2||length>5){
                SAToast.makeText(this,"姓名长度为2-5个汉字").show();
                loadingDialog.dismiss();
                return;
            }

            request.put("deadname", diename);

            String sname = et_family_single_name.getText().toString().trim();
            if (TextUtils.isEmpty(sname)) {
                SAToast.makeText(this,"请输入祭祀人姓名").show();
                loadingDialog.dismiss();
                return;
            }

            int length0 = sname.length();
            if (length0<2||length0>5){
                SAToast.makeText(this,"姓名长度为2-5个汉字").show();
                loadingDialog.dismiss();
                return;
            }

            request.put("fetename", sname);
            request.put("type", "1");
            request.put("relation", "");
            request.put("imgestr", selectPhotoBackUrl + "," + selectPhotoFrameUrl + "," +
                    singlePath);

        } else {

            if (TextUtils.isEmpty(doublePath1) || TextUtils.isEmpty(doublePath2)) {
                SAToast.makeText(this,"请上传头像").show();
                loadingDialog.dismiss();
                return;
            }

            String ddiename1 = et_family_double_died_name1.getText().toString().trim();
            String ddiename2 = et_family_double_died_name2.getText().toString().trim();
            if (TextUtils.isEmpty(ddiename1) || TextUtils.isEmpty(ddiename2)) {
                ToastUtil.Info("请完善逝者姓名");
                loadingDialog.dismiss();
                return;
            }
            request.put("deadname", ddiename1 + "," + ddiename2);

            String sdname = et_family_double_name.getText().toString().trim();
            if (TextUtils.isEmpty(sdname)) {
                ToastUtil.Info("请输入祭祀人姓名");
                loadingDialog.dismiss();
                return;
            }

            int length0 = sdname.length();
            if (length0<2||length0>5){
                ToastUtil.Info("姓名长度为2-5个汉字");
                loadingDialog.dismiss();
                return;
            }


            int length = ddiename1.length();
            if (length<2||length>5){
                ToastUtil.Info("姓名长度为2-5个汉字");
                loadingDialog.dismiss();
                return;
            }

            int length1 = ddiename2.length();
            if (length1<2||length1>5){
                ToastUtil.Info("姓名长度为2-5个汉字");
                loadingDialog.dismiss();
                return;
            }
            request.put("fetename", sdname);
            request.put("type", "2");
            String relation = et_family_relation.getText().toString().trim();
            if (TextUtils.isEmpty(relation)) {
                ToastUtil.Info("请输入逝者关系");
                loadingDialog.dismiss();
                return;
            }
            request.put("relation", relation);

            String imgestr = selectPhotoBackUrl + "," + selectPhotoFrameUrl + "," + doublePath1;
            imgestr += "@";
            imgestr +=  selectPhotoFrameUrl +","+ selectPhotoFrameUrl+"," + doublePath2;// selectPhotoBackUrl + "," +
            request.put("imgestr", imgestr);
        }
        System.out.println("JSON数据*********__________________**********************************"+GsonUtil.obj2JSON(request));
        mPresenter.familyAdd(GsonUtil.obj2JSON(request));
    }
    /***
     *
     * @param type 相框:100 背景:200
     * @param goodsid
     * @param ordernum
     */
    private void saveFrameAndBack(int type, String goodsid, String ordernum) {
        Map<String, String> request = new HashMap<>();
        request.put("token", SharedPreferencedUtils.getStr(Constants.USER_TOKEN));
        request.put("usernum", SharedPreferencedUtils.getStr(Constants.USER_NUM));
        request.put("buyuserid", SharedPreferencedUtils.getStr(Constants.USER_ID));

        request.put("type", type + "");
        request.put("sacrifid", goodsid);//商品id
        request.put("ordernum", ordernum);//订单编号

        mPresenter.familySaveFPhotoFrameAndBack(GsonUtil.obj2JSON(request));

    }

    //加载已购买的相框或者背景
    private void loadFrameAndBack() {
        Map<String, String> request = new HashMap<>();
        request.put("token", SharedPreferencedUtils.getStr(Constants.USER_TOKEN));
        request.put("usernum", SharedPreferencedUtils.getStr(Constants.USER_NUM));
        request.put("buyuserid", SharedPreferencedUtils.getStr(Constants.USER_ID));

        mPresenter.familyQueryFrameAndBack(GsonUtil.obj2JSON(request));
    }

    private void setLLGoge() {
        ll_family_single.setVisibility(View.GONE);
        ll_family_double.setVisibility(View.GONE);
        tv_family_radio_single.setTextColor(Color.parseColor("#ff7a00"));
        tv_family_radio_double.setTextColor(Color.parseColor("#ff7a00"));
        tv_family_radio_single.setBackground(getResources().getDrawable(R.drawable
                .shape_tv_bless_normal));
        tv_family_radio_double.setBackground(getResources().getDrawable(R.drawable
                .shape_tv_bless_normal));
    }


    @Override
    public void stateError() {
        super.stateError();
        loadingDialog.dismiss();
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
    public void familyAdd(String message) {
        loadingDialog.dismiss();
        EventBus.getDefault().post(new FamilyAddBean("successs"));
        ActivityUtil.getInstance().closeActivity(this);
    }
    @Override
    public void familyList(FamilyListBean funsharingListBeans) {

    }

    @Override
    public void familyListMy(FamilyListBean familyListBean) {

    }

    @Override
    public void familyBlessList(FamilyBlessListBean familyBlessListBean) {

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

    }

    @Override
    public void familyUsualLanguage(FamilyUsualLanguageBean familyUsualLanguageBean) {

    }

    @Override
    public void familyPhotoFrame(FamilyPhotoFrameBean familyPhotoFrameBean) {

        // 看看是否有已购买的 queryFramBack
        List<Integer> buyedIds = new ArrayList<>();
//
        if (queryFramBack != null) {
            String photoframevalue = queryFramBack.getPhotoframevalue();

            if (!TextUtils.isEmpty(photoframevalue)) {
                if (photoframevalue.contains("@")) {
                    String[] split = photoframevalue.split("@");
                    for (String item : split) {
                        buyedIds.add(Integer.valueOf(item.split(",")[0]));
                    }
                } else {
                    buyedIds.add(Integer.valueOf(photoframevalue.split(",")[0]));
                }
            }
        }

        List<FamilyPhotoFrameBean.ListBean> list = familyPhotoFrameBean.getList();

        for (FamilyPhotoFrameBean.ListBean item : list) {
            if (Float.parseFloat(item.getSellprice()) == 0) {
                Glide.with(this)
                        .load(Constants.IMG_SERVER_PATH + item.getImgurl())
                        .asBitmap()
                        .error(R.mipmap.family_adderror)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(iv_family_single_frame);


                Glide.with(this)
                        .load(Constants.IMG_SERVER_PATH + item.getImgurl())
                        .asBitmap()
                        .error(R.mipmap.family_adderror)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(iv_family_double1_frame);

                Glide.with(this)
                        .load(Constants.IMG_SERVER_PATH + item.getImgurl())
                        .asBitmap()
                        .error(R.mipmap.family_adderror)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(iv_family_double2_frame);
                Glide.with(this)
                        .load(Constants.IMG_SERVER_PATH + item.getImgurl())
                        .asBitmap()
                        .error(R.mipmap.family_adderror)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(iv_family_double3_frame);
                selectPhotoFrameUrl = item.getImgurl();
                selectPhotoFrameId = item.getSacrifid() + "";
                item.setIscheck(true);
                break;
            }
            if (buyedIds.contains(item.getSacrifid())) {
                item.setSellprice("已购买");
            }

        }
        photoFrameAdapter.setNewData(list);

        // TODO: 2018-01-10  这个位置有点伤心
        loadFrameAndBack();


//        totalPage = familyPhotoFrameBean.getTotalPage();
//        if (isRefreshing) {
//            photoBackAdapter.setNewData(familyPhotoFrameBean.getList());
//            refreshLayout.finishRefresh();
//        } else {
//            adapter.addData(familyPhotoFrameBean.getList());
//            refreshLayout.finishLoadmore();
//        }
    }

    @Override
    public void familyPhotoBack(FamilyPhotoFrameBean familyPhotoFrameBean) {

        // 看看是否有已购买的 queryFramBack
        //
        List<Integer> buyedIds = new ArrayList<>();
        if (queryFramBack != null) {
            String photobackvalue = queryFramBack.getBackgroundvalue();

            if (!TextUtils.isEmpty(photobackvalue)) {
                if (photobackvalue.contains("@")) {
                    String[] split = photobackvalue.split("@");
                    for (String item : split) {
                        buyedIds.add(Integer.valueOf(item.split(",")[0]));
                    }
                } else {
                    buyedIds.add(Integer.valueOf(photobackvalue.split(",")[0]));
                }
            }
        }

        List<FamilyPhotoFrameBean.ListBean> list = familyPhotoFrameBean.getList();

        for (FamilyPhotoFrameBean.ListBean item : list) {
            if (Float.parseFloat(item.getSellprice()) == 0) {
                selectPhotoBackUrl = item.getImgurl();
                selectPhotoBackId = item.getSacrifid() + "";
                item.setIscheck(true);
                break;
            }
            if (buyedIds.contains(item.getSacrifid())) {
                item.setSellprice("已购买");
            }
        }

        photoBackAdapter.setNewData(list);

    }

    @Override
    public void familyBlessAdd(String message) {

    }

    @Override
    public void familySearch(FamilyListBean familyListBean) {

    }

    @Override
    public void familyReceiveGiftById(FamilyReceiveGiftBean familyReceiveGiftBean) {

    }

    @Override
    public void familySaveFPhotoFrameAndBack(String message) {
        Logger.e("存入相框背景:" + message);
        int a = 0;
    }

    @Override
    public void familyQueryFrameAndBack(FamilyQueryFramBackBean familyQueryFramBackBean) {

        queryFramBack = familyQueryFramBackBean;
        if (queryFramBack == null) {
            return;
        }

        List<Integer> buyedFrameIds = new ArrayList<>();
        List<Integer> buyedBackIds = new ArrayList<>();


        String photoframevalue = queryFramBack.getPhotoframevalue();

        if (!TextUtils.isEmpty(photoframevalue)) {
            if (photoframevalue.contains("@")) {
                String[] split = photoframevalue.split("@");
                for (String item : split) {
                    buyedFrameIds.add(Integer.valueOf(item.split(",")[0]));
                }
            } else {
                buyedFrameIds.add(Integer.valueOf(photoframevalue.split(",")[0]));
            }
        }


        String photobackvalue = queryFramBack.getBackgroundvalue();

        if (!TextUtils.isEmpty(photobackvalue)) {
            if (photobackvalue.contains("@")) {
                String[] split = photobackvalue.split("@");
                for (String item : split) {
                    buyedBackIds.add(Integer.valueOf(item.split(",")[0]));
                }
            } else {
                buyedBackIds.add(Integer.valueOf(photobackvalue.split(",")[0]));
            }
        }

        //相框
        List<FamilyPhotoFrameBean.ListBean> photoFrameAdapterData = photoFrameAdapter.getData();
        if (photoFrameAdapterData != null && photoFrameAdapterData.size() > 0) {
            for (FamilyPhotoFrameBean.ListBean item : photoFrameAdapterData) {
                if (buyedFrameIds.contains(item.getSacrifid())) {
                    item.setSellprice("已购买");
                }
            }
            photoFrameAdapter.notifyDataSetChanged();
        }
        //背景
        List<FamilyPhotoFrameBean.ListBean> photoBackAdapterData = photoBackAdapter.getData();
        if (photoBackAdapterData != null && photoBackAdapterData.size() > 0) {
            for (FamilyPhotoFrameBean.ListBean item : photoBackAdapterData) {
                if (buyedBackIds.contains(item.getSacrifid())) {
                    item.setSellprice("已购买");
                }
            }
            photoBackAdapter.notifyDataSetChanged();
        }


    }


    @Override
    public void familyDel(String message) {

    }

    @Override
    public void uploadHeader(FileUploadBean fileUploadBean) {
        loadingDialog.dismiss();
        if (isUploadSingle) {
            singlePath = fileUploadBean.getImglist().get(0);
            /*ImageLoadProxy.getInstance()
                    .load(new ImageLoadConfiguration.Builder(App
                            .getInstance())
                            .url(Constants.IMG_SERVER_PATH + singlePath)
                            .imageView(iv_family_single).build());

            iv_family_single.setVisibility(View.INVISIBLE);*/

 /*           ImageLoadProxy.getInstance()
                    .load(new ImageLoadConfiguration.Builder(App
                            .getInstance())
                            .url(Constants.IMG_SERVER_PATH + singlePath)
                            .imageView(iv_family_single1).build());*/

            Glide.with(App.getInstance())
                    .load(Constants.IMG_SERVER_PATH + singlePath)//
                    .asBitmap()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .error(R.mipmap.ic_launcher)//
                    .into(iv_family_single1);
        }
        if (isUploadDouble1) {
            doublePath1 = fileUploadBean.getImglist().get(0);
/*            ImageLoadProxy.getInstance()
                    .load(new ImageLoadConfiguration.Builder(App
                            .getInstance())
                            .url(Constants.IMG_SERVER_PATH + doublePath1)
                            .imageView(iv_family_double1_1).build());*/

            Glide.with(App.getInstance())
                    .load(Constants.IMG_SERVER_PATH + doublePath1)//
                    .asBitmap()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .error(R.mipmap.icon_img_load_pre)//
                    .into(iv_family_double1_1);

        }
        if (isUploadDouble2) {
            doublePath2 = fileUploadBean.getImglist().get(0);
/*            ImageLoadProxy.getInstance()
                    .load(new ImageLoadConfiguration.Builder(App
                            .getInstance())
                            .url(Constants.IMG_SERVER_PATH + doublePath2)
                            .imageView(iv_family_double2_2).build());*/
            Glide.with(App.getInstance())
                    .load(Constants.IMG_SERVER_PATH + doublePath2)//
                    .asBitmap()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .error(R.mipmap.icon_img_load_pre)//
                    .into(iv_family_double2_2);
        }
        //重置
        isUploadSingle = false;
        isUploadDouble1 = false;
        isUploadDouble2 = false;
    }

    @Override
    public void generatorOrder(String blessOrderBean) {

    }

    @Override
    public void immediateOrder(String blessOrderBean) {

        loadingDialog.dismiss();

        bundle.putString(Constants.PAY_MODULE_NAME, Constants.CART_MODEL_FAMILY);
        bundle.putInt(Constants.PAY_FROM_ACTIVITY, Constants.PAY_FROM_FETE_PHTO);//来自于相框 背景购买页面

        bundle.putString(Constants.PAY_ORDERNUM, blessOrderBean);
        bundle.putString(Constants.PAY_TOTALPRICE, totalPrice);
        bundle.putString(Constants.PAY_GOODS_DESC, goodesc);
        bundle.putString(Constants.PAY_GOODS_DETAIL, goodsDetail);


        ActivityUtil.getInstance().openActivity(FamilyAddActivity.this, PayInfoActivity
                .class, bundle);

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


    class PhotoFramClickListener implements BaseQuickAdapter.OnItemClickListener {

        @Override
        public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
            // TODO: 2018-01已购买0 免费不买

            FamilyPhotoFrameBean.ListBean item = photoFrameAdapter.getItem(position);

            clickGoodsId = item.getSacrifid() + "";
            selectPhotoFrameId = item.getSacrifid() + "";
            selectPhotoFrameUrl = item.getImgurl();

            totalPrice = item.getSellprice();
            goodesc = item.getSacrifname();
            goodsDetail = item.getSacrifname();

            setFrameDefault();
            item.setIscheck(true);

            photoFrameAdapter.notifyDataSetChanged();

         /*   if (item.getSellprice().equals("0") || item.getSellprice().equals("0.0") || item
                    .getSellprice().equals("0.00")) {
//                ToastUtil.Info("不能购买免费物品");
                // TODO: 2018-01-19 预览效果
                return;
            }
*/
            //点击已购买的 直接预览
          //  if (item.getSellprice().equals("已购买") || (RegexUtil.isDouble(item.getSellprice()) &&
            //        Float.parseFloat(item.getSellprice()) == 0)) {

            if (item.getSellprice().equals("已购买") ||item.getSellprice().equals("0")||(RegexUtil.isDouble(item.getSellprice())&&Float.parseFloat(item.getSellprice())==0))
            {
/*                ImageLoadProxy.getInstance()
                        .load(new ImageLoadConfiguration.Builder(App
                                .getInstance())
                                .url(Constants.IMG_SERVER_PATH + item.getImgurl())
                                .imageView(iv_family_single_frame).build());*/

                Glide.with(App
                        .getInstance())
                        .load(Constants.IMG_SERVER_PATH +item.getImgurl())
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(iv_family_single_frame);

                Glide.with(App
                        .getInstance())
                        .load(Constants.IMG_SERVER_PATH +item.getImgurl())
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(iv_family_double1_frame);
                /*
                ImageLoadProxy.getInstance()
                        .load(new ImageLoadConfiguration.Builder(App
                                .getInstance())
                                .url(Constants.IMG_SERVER_PATH + item.getImgurl())
                                .imageView(iv_family_double1_frame).build());*/


                Glide.with(App
                        .getInstance())
                        .load(Constants.IMG_SERVER_PATH +item.getImgurl())
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(iv_family_double2_frame);

                Glide.with(App
                        .getInstance())
                        .load(Constants.IMG_SERVER_PATH + item.getImgurl())
                        .asBitmap()
                        .error(R.mipmap.family_adderror)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(iv_family_double3_frame);
            /*    ImageLoadProxy.getInstance()
                        .load(new ImageLoadConfiguration.Builder(App
                                .getInstance())
                                .url(Constants.IMG_SERVER_PATH + item.getImgurl())
                                .imageView(iv_family_double2_frame).build());*/

                return;
            }

            //}

            isPhotoFram = true;

            Map<String, String> request = new HashMap<>();
            request.put("token", SharedPreferencedUtils.getStr(Constants.USER_TOKEN));
            request.put("usernum", SharedPreferencedUtils.getStr(Constants.USER_NUM));
            request.put("buyuserid", SharedPreferencedUtils.getStr(Constants.USER_ID));
            request.put("modelename", Constants.CART_MODEL_FAMILY);
            request.put("moduleid", "0");
            request.put("receiveuserid", SharedPreferencedUtils.getStr(Constants.USER_ID));
            request.put("goodsid", item.getSacrifid() + "");
            request.put("cateid", item.getFirstcateid() + "");
            request.put("price", item.getSellprice());
            request.put("count", "1");
            request.put("goodsname", item.getSacrifname());
            request.put("imgurl", item.getImgurl());
            mPresenter.immediateOrder(GsonUtil.obj2JSON(request));
        }
    }

    class PhotoBackClickListener implements BaseQuickAdapter.OnItemClickListener {
        @Override
        public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

            FamilyPhotoFrameBean.ListBean item = photoBackAdapter.getItem(position);


            clickGoodsId = item.getSacrifid() + "";
            selectPhotoBackId = item.getSacrifid() + "";
            selectPhotoBackUrl = item.getImgurl();


            totalPrice = item.getSellprice();
            goodesc = item.getSacrifname();
            goodsDetail = item.getSacrifname();

            setBackDefault();
            item.setIscheck(true);

            photoBackAdapter.notifyDataSetChanged();


            Logger.e("图片地址:" + item.getImgurl());

            if (item.getSellprice().equals("已购买") || (RegexUtil.isDouble(item.getSellprice()) &&
                    Float.parseFloat(item.getSellprice()) == 0)) {
                return;
            }

            if (item.getSellprice().equals("0") || item.getSellprice().equals("0.0") || item
                    .getSellprice().equals("0.00")) {
//                ToastUtil.Info("不能购买免费物品");
                // TODO: 2018-01-19 预览效果
                return;
            }

            isPhotoFram = false;


            Map<String, String> request = new HashMap<>();
            request.put("token", SharedPreferencedUtils.getStr(Constants.USER_TOKEN));
            request.put("usernum", SharedPreferencedUtils.getStr(Constants.USER_NUM));
            request.put("buyuserid", SharedPreferencedUtils.getStr(Constants.USER_ID));
            request.put("modelename", Constants.CART_MODEL_FAMILY);
            request.put("moduleid", "0");
            request.put("receiveuserid", SharedPreferencedUtils.getStr(Constants.USER_ID));
            request.put("goodsid", item.getSacrifid() + "");
            request.put("cateid", item.getFirstcateid() + "");
            request.put("price", item.getSellprice());
            request.put("count", "1");
            request.put("goodsname", item.getSacrifname());
            request.put("imgurl", item.getImgurl());

            mPresenter.immediateOrder(GsonUtil.obj2JSON(request));

        }
    }

    private void setFrameDefault() {
        List<FamilyPhotoFrameBean.ListBean> data = photoFrameAdapter.getData();
        for (FamilyPhotoFrameBean.ListBean item : data) {
            item.setIscheck(false);
        }

    }

    private void setBackDefault() {
        List<FamilyPhotoFrameBean.ListBean> data = photoBackAdapter.getData();
        for (FamilyPhotoFrameBean.ListBean item : data) {
            item.setIscheck(false);
        }

    }


    @Subscribe(tag = RxBus.TAG_UPDATE, thread = EventThread.MAIN_THREAD)
    public void rxrefresh(String ordernum) {
        //            相框:100 背景:200

        loadFrameAndBack();
        loadPhotoFrame();
        loadPhotoBack();

        if (isPhotoFram) { //保存相框 然后更新预览图
            saveFrameAndBack(100, clickGoodsId, ordernum);


            Glide.with(this)
                    .load(Constants.IMG_SERVER_PATH + selectPhotoFrameUrl)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(iv_family_single_frame);

/*
            ImageLoadProxy.getInstance()
                    .load(new ImageLoadConfiguration.Builder(App
                            .getInstance())
                            .url(Constants.IMG_SERVER_PATH + selectPhotoFrameUrl)
                            .imageView(iv_family_single_frame).build());*/

            Glide.with(this)
                    .load(Constants.IMG_SERVER_PATH + selectPhotoFrameUrl)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(iv_family_double1_frame);


           /* ImageLoadProxy.getInstance()
                    .load(new ImageLoadConfiguration.Builder(App
                            .getInstance())
                            .url(Constants.IMG_SERVER_PATH + selectPhotoFrameUrl)
                            .imageView(iv_family_double1_frame).build());*/


            Glide.with(this)
                    .load(Constants.IMG_SERVER_PATH + selectPhotoFrameUrl)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(iv_family_double2_frame);
            Glide.with(this)
                    .load(Constants.IMG_SERVER_PATH + selectPhotoFrameUrl)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(iv_family_double3_frame);
/*
            ImageLoadProxy.getInstance()
                    .load(new ImageLoadConfiguration.Builder(App
                            .getInstance())
                            .url(Constants.IMG_SERVER_PATH + selectPhotoFrameUrl)
                            .imageView(iv_family_double2_frame).build());*/

        } else {//保存背景
            saveFrameAndBack(200, clickGoodsId, ordernum);
        }
        ToastUtil.Success("购买成功");
        Logger.i("购买成功:" + ordernum);
    }


}
