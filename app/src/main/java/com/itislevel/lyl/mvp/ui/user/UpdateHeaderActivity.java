package com.itislevel.lyl.mvp.ui.user;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Toast;

import com.flyco.dialog.listener.OnOperItemClickL;
import com.flyco.dialog.widget.ActionSheetDialog;
import com.itislevel.lyl.R;
import com.itislevel.lyl.app.App;
import com.itislevel.lyl.app.Constants;
import com.itislevel.lyl.base.RootActivity;
import com.itislevel.lyl.mvp.model.bean.FileUploadBean;
import com.itislevel.lyl.mvp.model.bean.HomeBean;
import com.itislevel.lyl.mvp.model.bean.MyReceiveAddrBean;
import com.itislevel.lyl.mvp.model.bean.RefreshHeadBean;
import com.itislevel.lyl.mvp.model.bean.RegistBean;
import com.itislevel.lyl.mvp.model.bean.UserInfoBean;
import com.itislevel.lyl.mvp.ui.main.MainActivity;
import com.itislevel.lyl.mvp.ui.main.mine.MineFragment;
import com.itislevel.lyl.utils.ActivityUtil;
import com.itislevel.lyl.utils.GsonUtil;
import com.itislevel.lyl.utils.SharedPreferencedUtils;
import com.itislevel.lyl.utils.SimpleRxGalleryFinal;
import com.itislevel.lyl.utils.imageload.ImageLoadConfiguration;
import com.itislevel.lyl.utils.imageload.ImageLoadProxy;
import com.itislevel.lyl.utils.rxbus.RxBus;
import com.itislevel.lyl.utils.rxbus.annotation.UseRxBus;
import com.orhanobut.logger.Logger;
import com.vondear.rxtools.RxFileTool;
import com.vondear.rxtools.RxImageTool;
import com.yalantis.ucrop.model.AspectRatio;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import cn.finalteam.rxgalleryfinal.RxGalleryFinal;
import cn.finalteam.rxgalleryfinal.RxGalleryFinalApi;
import cn.finalteam.rxgalleryfinal.imageloader.ImageLoaderType;
import cn.finalteam.rxgalleryfinal.rxbus.RxBusResultDisposable;
import cn.finalteam.rxgalleryfinal.rxbus.event.BaseResultEvent;
import cn.finalteam.rxgalleryfinal.rxbus.event.ImageRadioResultEvent;
import cn.finalteam.rxgalleryfinal.ui.base.IRadioImageCheckedListener;
import cn.finalteam.rxgalleryfinal.utils.MediaScanner;
import io.reactivex.annotations.NonNull;
import io.reactivex.annotations.Nullable;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

@UseRxBus
public class UpdateHeaderActivity extends RootActivity<UserPresenter> implements UserContract.View {


    @BindView(R.id.iv_header)
    ImageView iv_header;
    private String imgURL;
    private ActionSheetDialog dialog;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_update_header;
    }

    @Override
    protected void initEventAndData() {

        String headerUrl = SharedPreferencedUtils.getStr(Constants.USER_HEADER);

        if (TextUtils.isEmpty(headerUrl)) {

            ImageLoadProxy.getInstance()
                    .load(new ImageLoadConfiguration.Builder(UpdateHeaderActivity.this)
                            .url(R.mipmap.icon_default_header_circle)
                            .imageView(iv_header).build());
        } else {
            headerUrl = Constants.IMG_SERVER_PATH + headerUrl.trim();
            ImageLoadProxy.getInstance()
                    .load(new ImageLoadConfiguration.Builder(UpdateHeaderActivity.this)
                            .defaultImageResId(R.mipmap.icon_default_header_circle)
                            .url(headerUrl)
                            .imageView(iv_header).build());
        }


        setPath();
        setToolbarTvTitle("个人头像");
        setToolbarMoreTxt("更换");
        setToolbarMoreClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                final String[] stringItems = {"相册", "拍照"};
                dialog = new ActionSheetDialog(UpdateHeaderActivity.this,
                        stringItems, null);
                dialog.isTitleShow(false).show();

                dialog.setOnOperItemClickL(new OnOperItemClickL() {
                    @Override
                    public void onOperItemClick(AdapterView<?> parent, View view, int position,
                                                long id) {
                        dialog.dismiss();
                        if (position == 0) {
                            photo();
                        } else if (position == 1) {
                            camera();
                        }

                    }
                });
            }
        });
    }
    @Override
    protected void onDestroy() {
        if (dialog != null && dialog.isShowing())
            dialog.dismiss();
        super.onDestroy();

    }

    @Override
    protected void onPause() {
        if (dialog != null && dialog.isShowing())
            dialog.dismiss();
        super.onPause();

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
                        return UpdateHeaderActivity.this;
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
                        uploadHeader(encodedPath);

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
     * .cropAspectRatioOptions(0, new AspectRatio("1:1",30, 10))
     */
    private void photo() {
//        AspectRatio aspectRatio = new AspectRatio("title","3:3",30, 10);
        //                .cropWithAspectRatio(300, 300)


//        RxGalleryFinal.with(this)
//                .cropAspectRatioOptions(0, new AspectRatio("头像裁剪",200, 200))
//                .cropOvalDimmedLayer(true)
//                .cropFreeStyleCropEnabled(true)
//                .crop(true)
//                .imageLoader(ImageLoaderType.GLIDE)
//                .image()
//                .radio()
//                .subscribe(new RxBusResultDisposable<ImageRadioResultEvent>() {
//                    @Override
//                    protected void onEvent(ImageRadioResultEvent imageRadioResultEvent) throws
// Exception {
//                        Logger.i("只要选择图片就会触发:"+imageRadioResultEvent.getResult()
// .getOriginalPath());
//
//                    }
//
//                    @Override
//                    public void onNext(ImageRadioResultEvent imageRadioResultEvent) {
//                        super.onNext(imageRadioResultEvent);
//                        Logger.i("只要选择图片就会触发2:"+imageRadioResultEvent.getResult()
// .getOriginalPath());
//
//                    }
//                }).openGallery();

//        RxGalleryFinalApi.getInstance(this)
//                .onCrop(true)//是否裁剪
//                .openGalleryRadioImgDefault(new RxBusResultDisposable<ImageRadioResultEvent>() {
//                    @Override
//                    protected void onEvent(ImageRadioResultEvent imageRadioResultEvent) throws
// Exception {
//
//                    }
//                })
//                .onCropImageResult(new IRadioImageCheckedListener() {
//                    @Override
//                    public void cropAfter(Object t) {
//                        Logger.i("裁剪完成");
//                    }
//
//
//                    @Override
//                    public boolean isActivityFinish() {
//                        Logger.i("返回false不关闭，返回true则为关闭");
//                        return true;
//                    }
//                });


//        RxGalleryFinalApi.openZKCamera(this);

//        RxGalleryFinal
//                .with(this)
//
//                .image()
//                .radio()
//                .cropAspectRatioOptions(0, new AspectRatio("头像裁剪", 100, 100))
//                .crop()
//                .imageLoader(ImageLoaderType.GLIDE)
//                .subscribe(new RxBusResultDisposable<ImageRadioResultEvent>() {
//                    @Override
//                    protected void onEvent(ImageRadioResultEvent imageRadioResultEvent) throws Exception {
//                        Toast.makeText(getBaseContext(), "选中了图片路径：" + imageRadioResultEvent.getResult().getOriginalPath(), Toast.LENGTH_SHORT).show();
//                    }
//                })
//
//                .openGallery();


//        RxGalleryFinal
//                .with(this)
//                .image()            //选择图片
//                .radio()             //单选
//                .cropAspectRatioOptions(0, new AspectRatio("头像剪裁", 200, 200))
//                //单选能剪裁，AspectRatio设置标题和剪裁宽长
//                .crop()              //设置剪裁
//                .imageLoader(ImageLoaderType.GLIDE)     //调用glide图片加载器
//                .subscribe(new RxBusResultDisposable<ImageRadioResultEvent>() {
//                    @Override
//                    protected void onEvent(ImageRadioResultEvent imageRadioResultEvent) throws
//                            Exception {
//                        Logger.i("只要选择图片就会触发:" + imageRadioResultEvent.getResult()
//                                .getOriginalPath());
//                    }
//                })
//                .openGallery();



        RxGalleryFinalApi.getInstance(this)
                .onCrop(true)//是否裁剪

                .openGalleryRadioImgDefault2(new RxBusResultDisposable() {
                    @Override
                    protected void onEvent(Object o) throws Exception {
                        Logger.i("只要选择图片就会触发");
                    }
                })
                .onCropImageResult(new IRadioImageCheckedListener() {
                    @Override
                    public void cropAfter(Object t) {
                        Logger.i("裁剪完成:" + t);
                        uploadHeader(t.toString());
                    }

                    @Override
                    public boolean isActivityFinish() {
                        Logger.i("返回false不关闭，返回true则为关闭");
                        return true;
                    }
                });
    }

    private String localPathPath;

    private void uploadHeader(String localPath) {
        loadingDialog.show();
        localPathPath=localPath;

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
    public void showContent(String msg) {

    }

    @Override
    public void login(UserInfoBean response) {

    }

    @Override
    public void getValidateCode(String validateCode) {

    }

    @Override
    public void getSSMCode(String smscode) {

    }

    @Override
    public void regist(RegistBean registBean) {

    }

    @Override
    public void uploadHeader(FileUploadBean fileUploadBean) {

        RxFileTool.deleteFile(localPathPath);

        loadingDialog.dismiss();

        List<String> imglist = fileUploadBean.getImglist();
        for (String item : imglist) {
            imgURL = item.trim();
            String imgurl = Constants.IMG_SERVER_PATH + imgURL + "?random=" + System
                    .currentTimeMillis();
            Logger.i(imgurl);
            ImageLoadProxy.getInstance()
                    .load(new ImageLoadConfiguration.Builder(App.getInstance())
                            .url(imgurl)
                            .defaultImageResId(R.mipmap.icon_default_header_circle)
                            .imageView(iv_header).build());

            SharedPreferencedUtils.setStr(Constants.USER_HEADER, imgURL);


            Map<String, Object> request = new HashMap<>();

            request.put("token", SharedPreferencedUtils.getStr(Constants.USER_TOKEN));
            request.put("usernum", SharedPreferencedUtils.getStr(Constants.USER_NUM));
            request.put("userid", SharedPreferencedUtils.getStr(Constants.USER_ID));
            request.put("imgurl", imgURL);
            mPresenter.userModifyHeader(GsonUtil.obj2JSON(request));
        }
    }

    @Override
    public void userPerfectPersonal(String message) {

    }

    @Override
    public void userModifyPassword(String message) {

    }

    @Override
    public void userModifyNickname(String message) {

    }

    @Override
    public void userFindRecAddress(MyReceiveAddrBean address) {

    }

    @Override
    public void userUpdateRecAddress(String message) {

    }

    @Override
    public void userGiftmy(Object message) {

    }

    @Override
    public void userModifyHeader(String message) {

        RxBus.getInstance().post(RxBus.getInstance().getTag(UpdateUserInfoActivity.class,
                RxBus.TAG_UPDATE), "refreshheader");

        RxBus.getInstance().post(RxBus.getInstance().getTag(MainActivity.class,
                RxBus.TAG_UPDATE), "refreshheader");

        EventBus.getDefault().post(new RefreshHeadBean(""));
        ActivityUtil.getInstance().closeActivity(this);
    }
    @Override
    public void userForgetPassword(String message) {

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

    // data.getExtras().getString("OutputUri")

    //Bundle[{com.yalantis.ucrop.ImageWidth=780, com.yalantis.ucrop.ImageHeight=1040, com
    // .yalantis.ucrop.CropAspectRatio=0.75, com.yalantis.ucrop
    // .OutputUri=file:///storage/emulated/0/Android/data/com.itislevel
    // .lyl/cache/1513742503846.jpg}]
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        SimpleRxGalleryFinal.get().onActivityResult(requestCode, resultCode, data);
//        if (requestCode == RxGalleryFinalApi.TAKE_IMAGE_REQUEST_CODE && resultCode == Activity
//                .RESULT_OK) {
//            Logger.i("拍照OK，图片路径:" + RxGalleryFinalApi.fileImagePath.getPath());
//            //刷新相册数据库
//
//            RxGalleryFinalApi.openZKCameraForResult(UpdateHeaderActivity.this, new MediaScanner
//                    .ScanCallback() {
//                @Override
//                public void onScanCompleted(String[] strings) {
//                    Logger.i(String.format("拍照成功,图片存储路径:%s", strings[0]));
//                    Logger.d("演示拍照后进行图片裁剪，根据实际开发需求可去掉上面的判断");
//                    RxGalleryFinalApi.cropScannerForResult(UpdateHeaderActivity.this,
//                            RxGalleryFinalApi.getModelPath(), strings[0]);//调用裁剪
//                    // .RxGalleryFinalApi.getModelPath()为默认的输出路径
//                }
//            });
//        } else {
//            Logger.i("失敗");
//        }
    }

}
