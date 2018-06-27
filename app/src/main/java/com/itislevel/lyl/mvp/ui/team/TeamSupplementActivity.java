package com.itislevel.lyl.mvp.ui.team;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.flyco.dialog.listener.OnOperItemClickL;
import com.flyco.dialog.widget.ActionSheetDialog;
import com.itislevel.lyl.R;
import com.itislevel.lyl.adapter.SelectImgAdapter;
import com.itislevel.lyl.app.App;
import com.itislevel.lyl.app.Constants;
import com.itislevel.lyl.base.RootActivity;
import com.itislevel.lyl.mvp.model.bean.BlessOrderBean;
import com.itislevel.lyl.mvp.model.bean.FileUploadBean;
import com.itislevel.lyl.mvp.model.bean.SelectImgBean;
import com.itislevel.lyl.mvp.model.bean.SelectedImgBean;
import com.itislevel.lyl.mvp.model.bean.TeamListBean;
import com.itislevel.lyl.mvp.model.bean.TeamStatusBean;
import com.itislevel.lyl.mvp.model.bean.TeamTypeBean;
import com.itislevel.lyl.mvp.model.bean.TroubleAdviserMyBean;
import com.itislevel.lyl.mvp.ui.main.MainActivity;
import com.itislevel.lyl.mvp.ui.setting.FacebackActivity;
import com.itislevel.lyl.utils.ActivityUtil;
import com.itislevel.lyl.utils.GsonUtil;
import com.itislevel.lyl.utils.RegexUtil;
import com.itislevel.lyl.utils.SharedPreferencedUtils;
import com.itislevel.lyl.utils.SimpleRxGalleryFinal;
import com.itislevel.lyl.utils.ToastUtil;
import com.itislevel.lyl.utils.imageload.ImageLoadConfiguration;
import com.itislevel.lyl.utils.imageload.ImageLoadProxy;
import com.itislevel.lyl.utils.rxbus.RxBus;
import com.itislevel.lyl.utils.rxbus.annotation.UseRxBus;
import com.orhanobut.logger.Logger;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import cn.finalteam.rxgalleryfinal.RxGalleryFinal;
import cn.finalteam.rxgalleryfinal.RxGalleryFinalApi;
import cn.finalteam.rxgalleryfinal.imageloader.ImageLoaderType;
import cn.finalteam.rxgalleryfinal.rxbus.RxBusResultDisposable;
import cn.finalteam.rxgalleryfinal.rxbus.event.ImageMultipleResultEvent;
import cn.finalteam.rxgalleryfinal.ui.base.IRadioImageCheckedListener;
import io.reactivex.annotations.NonNull;
import io.reactivex.annotations.Nullable;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

@UseRxBus
public class TeamSupplementActivity extends RootActivity<TeamPresenter> implements TeamContract
        .View, BaseQuickAdapter.OnItemChildClickListener {

    Bundle bundle = new Bundle();
    private String PROVINCE_ID = "";
    private String CITY_ID = "";

    private String PROVINCE_NAME = "";
    private String CITY_NAME = "";


    @BindView(R.id.sp_type)
    Spinner sp_type;

    private List<String> dataNmaeList;
    private List<String> dataIdList;
    private ArrayAdapter<String> arr_adapter;

    @BindView(R.id.et_name)
    EditText et_name;
    @BindView(R.id.et_phone)
    EditText et_phone;
    @BindView(R.id.et_idnumber)
    EditText et_idnumber;

    @BindView(R.id.et_desc)
    EditText et_desc;

    @BindView(R.id.iv_photo)
    ImageView iv_photo;

//    @BindView(R.id.iv_aptitude)
//    ImageView iv_aptitude;

    private boolean IS_PHOTO = true;//点击的是头像 还是证书

    private String phtoPaht = "";//头像地址 上传后的
    private String aptitudePath = "";//资质地址 上传后的

    //上传图片用的
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    SelectImgAdapter adapter;
    private int maxPhotoCount = 9;
    private List<String> uploadedUrl = new ArrayList<>();


    @Override
    protected int getLayoutId() {
        return R.layout.activity_team_supplement;
    }

    @Override
    protected void initEventAndData() {

        bundle = this.getIntent().getExtras();

        PROVINCE_ID = bundle.getString(Constants.PROVINCE_ID);
        CITY_ID = bundle.getString(Constants.CITY_ID);

        PROVINCE_NAME = bundle.getString(Constants.PROVINCE_NAME);
        CITY_NAME = bundle.getString(Constants.CITY_NAME);

        setPath();
        initAdapter();


        setToolbarTvTitle("填写资料");
        setToolbarMoreTxt("下一步");
        setToolbarMoreClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ToastUtil.Info(sp_type.getSelectedItemPosition() + "");
//                ActivityUtil.getInstance().openActivity(TeamSupplementActivity.this,
// TeamWaitingForVerifyActivity
//                        .class);

                Map<String, String> request = new HashMap<>();
                request.put("token", SharedPreferencedUtils.getStr(Constants.USER_TOKEN));
                request.put("usernum", SharedPreferencedUtils.getStr(Constants.USER_NUM));
                String name = et_name.getText().toString().trim();
                if (name.length() < 2 || name.length() > 8) {
                    ToastUtil.Warning("姓名长度为2-8位");
                    return;
                }

                String phone = et_phone.getText().toString().trim();
                if (!RegexUtil.isMobileExact(phone)) {
                    ToastUtil.Warning("电话号码格式不正确");
                    return;
                }

                String idcard = et_idnumber.getText().toString().trim();
                if (!RegexUtil.isIDCard18(idcard)) {
                    ToastUtil.Warning("身份证号码格式不正确");
                    return;
                }

                if (TextUtils.isEmpty(phtoPaht)){
                    ToastUtil.Warning("头像不能为空");
                    return;
                }

                request.put("name", name);
                request.put("personalcv", et_desc.getText().toString().trim());
                request.put("phone", phone);
                request.put("idcard", idcard);

                request.put("photo", phtoPaht);//头像
//                request.put("certificate", aptitudePath);//资质

                if (uploadedUrl.size() <= 0) {
                    ToastUtil.Warning("请至少上传一张资质图片");
                    return;
                }
                String imgStr = uploadedUrl.toString().replaceAll(" ", "");
                String substring = imgStr.substring(1, imgStr.length() - 1);
                request.put("certificate", substring);//资质

                request.put("provid", PROVINCE_ID);
                request.put("provname", PROVINCE_NAME);

                request.put("cityid", CITY_ID);
                request.put("cityname", CITY_NAME);

                String typeid = dataIdList.get(sp_type.getSelectedItemPosition());
                request.put("firstcateid", typeid);
                request.put("sex", "1");

                request.put("adviserid", SharedPreferencedUtils.getStr(Constants.USER_ID));

                Logger.w("action:" + GsonUtil.obj2JSON(request));

                mPresenter.teamRegister(GsonUtil.obj2JSON(request));


            }
        });

        dataNmaeList = bundle.getStringArrayList(Constants
                .TROUBLE_TEAM_TYPE_NAME_LIST);

        dataIdList = bundle.getStringArrayList(Constants
                .TROUBLE_TEAM_TYPE_ID_LIST);
        //数据
//        dataNmaeList = new ArrayList<String>();
//        dataNmaeList.add("纠纷律师");
//        dataNmaeList.add("情感顾问");
//        dataNmaeList.add("税务会计");
//        dataNmaeList.add("装修工程");
//        dataNmaeList.add("育儿教师");
//        dataNmaeList.add("病痛医生");
//        dataNmaeList.add("企业管理");
//        dataNmaeList.add("药师");
        //适配器
        arr_adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,
                dataNmaeList);
        //设置样式
        arr_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //加载适配器
        sp_type.setAdapter(arr_adapter);
    }

    /**
     * 初始化adapter
     */
    private void initAdapter() {
        if (adapter == null) {
            adapter = new SelectImgAdapter(R.layout.item_select_img);
//            adapter.setOnItemClickListener(this);
            adapter.setOnItemChildClickListener(this);
            adapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN); //切换动画
            adapter.setEnableLoadMore(false);
//            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            GridLayoutManager layoutManager = new GridLayoutManager(this, 3, LinearLayoutManager
                    .VERTICAL, false);
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(adapter);

            addSelectImg();


        }
    }

    /**
     * 添加选择图片的默认图片
     */
    private void addSelectImg() {
        SelectImgBean selectImgBean = new SelectImgBean();
        selectImgBean.setSelectItem(true);
        selectImgBean.setImgUrl(R.mipmap.icon_img_add_temp);
        adapter.addData(selectImgBean);
    }


    private void openSelectDialog() {
        final String[] stringItems = {"相册", "拍照"};
        final ActionSheetDialog dialog = new ActionSheetDialog(TeamSupplementActivity.this,
                stringItems, null);
        dialog.isTitleShow(false).show();

        dialog.setOnOperItemClickL(new OnOperItemClickL() {
            @Override
            public void onOperItemClick(AdapterView<?> parent, View view, int position,
                                        long id) {
                if (position == 0) {
                    photo();
                } else if (position == 1) {
                    camera();
                }
                dialog.dismiss();
            }
        });
    }


    /**
     * 拍照
     */
    private void camera() {
        // 直接裁剪  or  拍照并裁剪( 查看 onActivityResult())
        SimpleRxGalleryFinal.get().init(
                new SimpleRxGalleryFinal.RxGalleryFinalCropListener() {
                    @NonNull
                    @Override
                    public Activity getSimpleActivity() {
                        return TeamSupplementActivity.this;
                    }

                    @Override
                    public void onCropCancel() {
                        Logger.i("onCropCancel");
                    }

                    @Override
                    public void onCropSuccess(@Nullable Uri uri) {
//                        Toast.makeText(getSimpleActivity(), "裁剪成功：" + uri, Toast.LENGTH_SHORT)
//                                .show();
                        Logger.i(String.format("拍照成功,图片存储路径:" + uri));
                        maxPhotoCount = maxPhotoCount - 1;
                        String encodedPath = uri.getEncodedPath();
                        uploadHeader(encodedPath);

                    }

                    @Override
                    public void onCropError(@NonNull String errorMessage) {
//                        Toast.makeText(getSimpleActivity(), errorMessage, Toast.LENGTH_SHORT)
//                                .show();
                        Logger.i(errorMessage);
                    }
                }
        )
                .openCamera();

    }


    /**
     * 从相册选择
     */
    private void photo() {
        RxGalleryFinal
                .with(mActivity)
                .image()
                .multiple()
                .maxSize(maxPhotoCount)
                .imageLoader(ImageLoaderType.GLIDE)
                .subscribe(new RxBusResultDisposable<ImageMultipleResultEvent>() {
                    @Override
                    protected void onEvent(ImageMultipleResultEvent imageMultipleResultEvent)
                            throws Exception {
                        int size = imageMultipleResultEvent.getResult().size();
                        maxPhotoCount = maxPhotoCount - size;
//                        if (maxPhotoCount == 0) {
//                            mAdapter.remove(0);//删除添加按钮那个图标
//                        }
//                        List<SelectedImgBean> tempList = new ArrayList<>();
                        SelectedImgBean temp;
                        for (int i = 0; i < size; i++) {
                            temp = new SelectedImgBean();
                            temp.setOriginalPath(imageMultipleResultEvent.getResult().get(i)
                                    .getOriginalPath());
                            temp.setThumbPath(imageMultipleResultEvent.getResult().get(i)
                                    .getThumbnailSmallPath());
                            Logger.i("original:" + temp.getOriginalPath() + "==thumb:" + temp
                                    .getThumbPath());

//                            tempList.add(temp);

                            uploadHeader(temp.getOriginalPath());
                        }
                    }

                    @Override
                    public void onComplete() {
                        super.onComplete();
                    }

                })
                .openGallery();
    }


    private void uploadHeader(String localPath) {

        SelectImgBean selectImgBean = new SelectImgBean();
        selectImgBean.setImgUrl(localPath);
        adapter.addData(0, selectImgBean);

        if (adapter.getItemCount() == 10) {
            adapter.remove(9);
        }

        loadingDialog.show();
        File temp = new File(localPath);
        RequestBody requestFile =
                RequestBody.create(MediaType.parse("multipart/form-data"), temp);
        String filename = "itisi" + SharedPreferencedUtils.getStr(Constants
                .USER_TOKEN) + System.currentTimeMillis();
        MultipartBody.Part body = MultipartBody.Part.createFormData(filename,
                temp.getName(), requestFile);

        mPresenter.uploadHeader(body);
    }


    @OnClick({R.id.iv_photo})
    public void click(View view) {

        final String[] stringItems = {"相册", "拍照"};
        final ActionSheetDialog dialog = new ActionSheetDialog(TeamSupplementActivity.this,
                stringItems, null);

        dialog.setOnOperItemClickL(new OnOperItemClickL() {
            @Override
            public void onOperItemClick(AdapterView<?> parent, View view, int position,
                                        long id) {
                if (position == 0) {
                    photo1();
                } else if (position == 1) {
                    camera1();
                }
                dialog.dismiss();
            }
        });

        switch (view.getId()) {

            case R.id.iv_photo:
                IS_PHOTO = true;
                dialog.isTitleShow(false).show();
                break;
        }
    }

    /**
     * 拍照
     */
    private void camera1() {
        // 直接裁剪  or  拍照并裁剪( 查看 onActivityResult())
        SimpleRxGalleryFinal.get().init(
                new SimpleRxGalleryFinal.RxGalleryFinalCropListener() {
                    @NonNull
                    @Override
                    public Activity getSimpleActivity() {
                        return TeamSupplementActivity.this;
                    }

                    @Override
                    public void onCropCancel() {
                        Logger.i("onCropCancel");
                    }

                    @Override
                    public void onCropSuccess(@Nullable Uri uri) {
//                        Toast.makeText(getSimpleActivity(), "裁剪成功：" + uri, Toast.LENGTH_SHORT)
//                                .show();
                        Logger.i(String.format("拍照成功,图片存储路径:" + uri));
                        String encodedPath = uri.getEncodedPath();
                        uploadHeader1(encodedPath);
                    }

                    @Override
                    public void onCropError(@NonNull String errorMessage) {
//                        Toast.makeText(getSimpleActivity(), errorMessage, Toast.LENGTH_SHORT)
//                                .show();
                        Logger.i(errorMessage);
                    }
                }
        ).openCamera();


    }

    /**
     * 从相册选择
     */
    private void photo1() {
        RxGalleryFinalApi.getInstance(this)
                .onCrop(false)//是否裁剪
                .openGalleryRadioImgDefault(new RxBusResultDisposable() {
                    @Override
                    protected void onEvent(Object o) throws Exception {
                        Logger.i("只要选择图片就会触发");
                    }
                })
                .onCropImageResult(new IRadioImageCheckedListener() {
                    @Override
                    public void cropAfter(Object t) {
                        Logger.i("裁剪完成:" + t);
                        uploadHeader1(t.toString());
                    }

                    @Override
                    public boolean isActivityFinish() {
                        Logger.i("返回false不关闭，返回true则为关闭");
                        return true;
                    }
                });
    }

    private void uploadHeader1(String localPath) {

        if (IS_PHOTO) {
            ImageLoadProxy.getInstance()
                    .load(new ImageLoadConfiguration.Builder(App.getInstance())
                            .url(localPath)
                            .imageView(iv_photo).build());
        } else {
//            ImageLoadProxy.getInstance()
//                    .load(new ImageLoadConfiguration.Builder(App.getInstance())
//                            .url(localPath)
//                            .imageView(iv_aptitude).build());
        }

        File temp = new File(localPath);
        RequestBody requestFile =
                RequestBody.create(MediaType.parse("multipart/form-data"), temp);
        String filename = "itisi" + SharedPreferencedUtils.getStr(Constants
                .USER_TOKEN) + System.currentTimeMillis();
        MultipartBody.Part body = MultipartBody.Part.createFormData(filename,
                temp.getName(), requestFile);

        mPresenter.uploadHeader(body);
    }

    /**
     * 设置 照片路径 和 裁剪路径
     * //裁剪会自动生成路径；也可以手动设置裁剪的路径；
     */
    private void setPath() {
        RxGalleryFinalApi.setImgSaveRxSDCard(Constants.PATH_GALLERY);
        RxGalleryFinalApi.setImgSaveRxCropSDCard(Constants.PATH_GALLERY_CROP);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        SimpleRxGalleryFinal.get().onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void showContent(String msg) {

    }

    @Override
    public void teamRegister(String message) {
        SharedPreferencedUtils.setInt(Constants.USER_IS_ADVISER,1);
        RxBus.getInstance().post(RxBus.getInstance().getTag(MainActivity.class,
                RxBus.TAG_UPDATE), "refreshheader");
        ActivityUtil.getInstance().openActivity(this, TeamWaitingForVerifyActivity.class, bundle);
        ActivityUtil.getInstance().closeActivity(this);

    }

    @Override
    public void teamStatus(TeamStatusBean teamStatusBean) {

    }

    @Override
    public void teamList(TeamListBean teamListBean) {

    }

    @Override
    public void teamViewCount(String message) {

    }

    @Override
    public void teamProblemAdd(BlessOrderBean message) {

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
    public void teamType(TeamTypeBean teamTypeBean) {

    }

    @Override
    public void uploadHeader(FileUploadBean fileUploadBean) {

        loadingDialog.dismiss();

        if (IS_PHOTO) {
            ToastUtil.Success("头像上传成功");
            phtoPaht = fileUploadBean.getImglist().get(0);
        } else {
            ToastUtil.Success("资质上传成功");
//            aptitudePath = fileUploadBean.getImglist().get(0);

            loadingDialog.dismiss();
            List<String> imglist = fileUploadBean.getImglist();
            for (String item : imglist) {
//            String imgurl = Constants.IMG_SERVER_PATH + item.trim()+"?random="+System
// .currentTimeMillis();
//            Logger.i(imgurl);
//            ImageLoadProxy.getInstance()
//                    .load(new ImageLoadConfiguration.Builder(App.getInstance())
//                            .url(imgurl)
//                            .defaultImageResId(R.mipmap.ic_launcher)
//                            .imageView(iv_header).build());
//
//            SharedPreferencedUtils.setStr(Constants.USER_PHONE, imgurl);

                uploadedUrl.add(item.trim());


            }
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
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

        switch (view.getId()) {
            case R.id.iv_close:
                if (uploadedUrl.size() >= position) {
                    uploadedUrl.remove(position);
                }
                adapter.remove(position);
                maxPhotoCount = maxPhotoCount + 1;
                SelectImgBean item = this.adapter.getItem(adapter.getData().size() - 1);
                if (!item.isSelectItem()) {
                    addSelectImg();
                }
                break;
            case R.id.iv_selected://点击的是最后一个
                IS_PHOTO = false;
                SelectImgBean item1 = this.adapter.getItem(position);
                if (item1.isSelectItem()) {//是选择图片的哪个图片
                    openSelectDialog();

                } else {
                    // TODO: 2018-01-27 放大操作
                }
                break;
        }
    }
}
