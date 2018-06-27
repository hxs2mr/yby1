package com.itislevel.lyl.mvp.ui.setting;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.flyco.dialog.listener.OnOperItemClickL;
import com.flyco.dialog.widget.ActionSheetDialog;
import com.itislevel.lyl.R;
import com.itislevel.lyl.adapter.SelectImgAdapter;
import com.itislevel.lyl.app.Constants;
import com.itislevel.lyl.base.RootActivity;
import com.itislevel.lyl.mvp.model.bean.FileUploadBean;
import com.itislevel.lyl.mvp.model.bean.SelectImgBean;
import com.itislevel.lyl.mvp.model.bean.SelectedImgBean;
import com.itislevel.lyl.mvp.ui.blessing.BlessingAddActivity;
import com.itislevel.lyl.utils.ActivityUtil;
import com.itislevel.lyl.utils.GsonUtil;
import com.itislevel.lyl.utils.SharedPreferencedUtils;
import com.itislevel.lyl.utils.SimpleRxGalleryFinal;
import com.itislevel.lyl.utils.ToastUtil;
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
import io.reactivex.annotations.NonNull;
import io.reactivex.annotations.Nullable;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class FacebackActivity extends RootActivity<SettingPresenter> implements
        SettingContract.View, BaseQuickAdapter.OnItemChildClickListener {

    @BindView(R.id.et_content)
    EditText et_content;

    @BindView(R.id.et_phone)
    EditText et_phone;

    @BindView(R.id.btn_submit)
    Button btn_submit;

    //上传图片用的
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    SelectImgAdapter adapter;
    private int maxPhotoCount = 4;
    private List<String> uploadedUrl = new ArrayList<>();


    @Override
    protected int getLayoutId() {
        return R.layout.activity_faceback;
    }

    @Override
    protected void initEventAndData() {

        setToolbarTvTitle("问题反馈");
        setPath();
        initAdapter();

    }

    @OnClick({R.id.btn_submit})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.btn_submit:
                String content = et_content.getText().toString().trim();
                if (TextUtils.isEmpty(content)) {
                    ToastUtil.Info("内容不能为空");
                    return;
                }

                String phone = et_phone.getText().toString().trim();

                Map<String, Object> request = new HashMap<>();
                request.put("token", SharedPreferencedUtils.getStr(Constants.USER_TOKEN));
                request.put("usernum", SharedPreferencedUtils.getStr(Constants.USER_NUM));
                request.put("userid", SharedPreferencedUtils.getStr(Constants.USER_ID));
                request.put("content", content);
                request.put("phone", phone);

                String imgStr = uploadedUrl.toString();
                String substring = imgStr.substring(1, imgStr.length() - 1);

                request.put("imgurl", substring);

                mPresenter.userAddFeedback(GsonUtil.obj2JSON(request));

                break;
        }
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
        final ActionSheetDialog dialog = new ActionSheetDialog(FacebackActivity.this,
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
                        return FacebackActivity.this;
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

        // TODO: 2017/12/25 不足 待调整
        if (adapter.getItemCount() == 5) {
            adapter.remove(4);
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
    public void getSSMCode(String smscode) {

    }

    @Override
    public void updatePhone(String msg) {

    }

    @Override
    public void userAddFeedback(String msg) {

          ToastUtil.Success("反馈成功");
        ActivityUtil.getInstance().closeActivity(this);
    }

    @Override
    public void uploadHeader(FileUploadBean fileUploadBean) {
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        SimpleRxGalleryFinal.get().onActivityResult(requestCode, resultCode, data);

    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

        switch (view.getId()) {
            case R.id.iv_close:
                if (uploadedUrl.size()>=position){
                    uploadedUrl.remove(position);
                }
                adapter.remove(position);
                maxPhotoCount=maxPhotoCount+1;
                SelectImgBean item = this.adapter.getItem(adapter.getData().size() - 1);
                if (!item.isSelectItem()){
                    addSelectImg();
                }
                break;
            case R.id.iv_selected://点击的是最后一个
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
