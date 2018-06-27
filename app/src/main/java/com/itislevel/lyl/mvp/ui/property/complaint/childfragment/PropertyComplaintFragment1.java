package com.itislevel.lyl.mvp.ui.property.complaint.childfragment;

import android.Manifest;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.AdapterView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.flyco.dialog.listener.OnOperItemClickL;
import com.flyco.dialog.widget.ActionSheetDialog;
import com.itislevel.lyl.R;
import com.itislevel.lyl.adapter.ComplintTouAdapter;
import com.itislevel.lyl.adapter.DynamicFindAdapter;
import com.itislevel.lyl.adapter.SelectImgAdapter;
import com.itislevel.lyl.app.Constants;
import com.itislevel.lyl.base.RootFragment;
import com.itislevel.lyl.mvp.model.bean.AddressBean;
import com.itislevel.lyl.mvp.model.bean.ComSearchBean;
import com.itislevel.lyl.mvp.model.bean.ComplaintTypeBean;
import com.itislevel.lyl.mvp.model.bean.FileUploadBean;
import com.itislevel.lyl.mvp.model.bean.PropertyDetailBean;
import com.itislevel.lyl.mvp.model.bean.SelectImgBean;
import com.itislevel.lyl.mvp.model.bean.SelectedImgBean;
import com.itislevel.lyl.mvp.ui.funsharing.FunsharingAddActivity;
import com.itislevel.lyl.mvp.ui.main.dynamic.childfragment.RootCancleFragment;
import com.itislevel.lyl.mvp.ui.main.dynamic.childfragment.dfind.DFindFragment;
import com.itislevel.lyl.mvp.ui.property.complaint.ComplaintContract;
import com.itislevel.lyl.mvp.ui.property.complaint.ComplaintPresenter;
import com.itislevel.lyl.utils.ActivityUtil;
import com.itislevel.lyl.utils.GsonUtil;
import com.itislevel.lyl.utils.SAToast;
import com.itislevel.lyl.utils.SharedPreferencedUtils;
import com.itislevel.lyl.utils.SimpleRxGalleryFinal;
import com.itislevel.lyl.utils.ToastUtil;
import com.orhanobut.logger.Logger;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindInt;
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

/**
 * Created by Administrator on 2018\5\29 0029.
 */

public class PropertyComplaintFragment1 extends RootCancleFragment<ComplaintPresenter> implements ComplaintContract.View, BaseQuickAdapter.OnItemClickListener, BaseQuickAdapter.OnItemChildClickListener {

    @BindView(R.id.toushu_flage_Recyclview)
    RecyclerView toushu_flage_Recyclview;//偷书类型·

    @BindView(R.id.complint_qu)
    AppCompatTextView complint_qu;//投诉的小区

    @BindView(R.id.complint_neixin)
    AppCompatTextView complint_neixin;//投诉的类型

    @BindView(R.id.complint_xia_image)
    AppCompatImageView complint_xia_image;//投诉的的旋转图标

    @BindView(R.id.complint_content)
    TextInputEditText complint_content;//投诉的的内容

    @BindView(R.id.complint_name)
    TextInputEditText complint_name;//投诉的的姓名

    @BindView(R.id.complint_phone)
    TextInputEditText complint_phone;//投诉的的手机号


    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;//投诉的图片

    @BindView(R.id.complint_tijiao)
    AppCompatButton complint_tijiao;//提交投诉

    @BindView(R.id.neixin_linear)
    LinearLayoutCompat neixin_linear;

    private ComplintTouAdapter adapter;
    private int mHiddenViewMeasuredHeight;
    private     SelectImgAdapter Image_Adapter;
    private RxPermissions rxPermissions;
    private     int serverSize=0;
    private int maxPhotoCount = 9;

    private int img_w=0;
    private int img_h=0;
    private List<String> uploadedUrl = new ArrayList<>();
    private  int create_id=0;
    @Override
    protected int getLayoutId() {
        return R.layout.property_complintfragment1;
    }

    @Override
    protected void initEventAndData() {
        rxPermissions = new RxPermissions(getActivity());
        initAdapter();
        init_data();
        setPath();
        initAdapter_Image();
        loadweixiuData();
    }

    private boolean checkForm(){//检查输入的文本是否正确
        final  String content = complint_content.getText().toString().trim();
        final  String name = complint_name.getText().toString().trim();
        final  String phone = complint_phone.getText().toString().trim();
        final  String qu = complint_qu.getText().toString().trim();
        boolean isPass = true;
        if(content.isEmpty()){
            complint_content.setError("请输入内容");
            isPass = false;
        }
        if(name.isEmpty()){
            complint_name.setError("请输入姓名");
            isPass = false;
        }
        if(phone.isEmpty()){
            complint_phone.setError("请输入手机号");
            isPass = false;
        }
        if(qu.isEmpty()){
            complint_qu.setError("输入小区");
            isPass = false;
        }
        return isPass;
    }
    private void loadweixiuData() {
        Map<String, Object> request = new HashMap<>();
        request.put("token", SharedPreferencedUtils.getStr("p_token",""));
        request.put("phone", SharedPreferencedUtils.getStr("proprety_phone",""));
        request.put("sign","1");
        System.out.println("JSON********************"+GsonUtil.obj2JSON(request));
        mPresenter.complaintType(GsonUtil.obj2JSON(request));//获取广告
    }

    private void init_data() {
        complint_qu.setText(SharedPreferencedUtils.getStr("villagename",""));
        complint_name.setText(SharedPreferencedUtils.getStr("p_user_n",""));
        complint_phone.setText(SharedPreferencedUtils.getStr("proprety_phone",""));
    }

    private void initAdapter_Image() {
        if (Image_Adapter == null) {
            Image_Adapter = new SelectImgAdapter(R.layout.item_select_img);
//            adapter.setOnItemClickListener(this);
            Image_Adapter.setOnItemChildClickListener(this);
            Image_Adapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN); //切换动画
            Image_Adapter.setEnableLoadMore(false);
//            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 3, LinearLayoutManager.VERTICAL, false);
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(Image_Adapter);

            addSelectImg();
        }
    }

    private void initAdapter() {
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        toushu_flage_Recyclview.setLayoutManager(manager);
        if (adapter == null){
            adapter = new ComplintTouAdapter(R.layout.item_toushu);
            adapter.setOnItemClickListener(this);
            adapter.openLoadAnimation();
            toushu_flage_Recyclview.setAdapter(adapter);
        }
    }
    /**
     * 设置 照片路径 和 裁剪路径
     * //裁剪会自动生成路径；也可以手动设置裁剪的路径；
     */
    private void setPath() {
        RxGalleryFinalApi.setImgSaveRxSDCard(Constants.PATH_GALLERY);
        RxGalleryFinalApi.setImgSaveRxCropSDCard(Constants.PATH_GALLERY_CROP);
    }
    /**
     * 添加选择图片的默认图片
     */
    private void addSelectImg() {
        SelectImgBean selectImgBean = new SelectImgBean();
        selectImgBean.setSelectItem(true);
        selectImgBean.setImgUrl(R.mipmap.icon_img_add_temp);
        Image_Adapter.addData(selectImgBean);
    }
    @Override
    public void stateError(Throwable e) {
        super.stateError(e);
        if(adapter.getData().size()>0)
        {
            adapter.remove(0);
        }
    }

    @OnClick({R.id.neixin_linear,R.id.complint_tijiao})
    public void Onclick(View view)
    {
        switch (view.getId())
        {
            case R.id.neixin_linear:
                toushu_flage_Recyclview.measure(0,0);
//获取组件的高度
                int height=toushu_flage_Recyclview.getMeasuredHeight();//获取组件的高度
                // 获取布局的高度
                mHiddenViewMeasuredHeight =height;
                int durationMillis = 350;//动画持续时间
                if (toushu_flage_Recyclview.getVisibility()==View.VISIBLE) {
                    animateClose(toushu_flage_Recyclview);//关闭动画
                    RotateAnimation animation = new RotateAnimation(180, 0, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                    animation.setDuration(durationMillis);
                    animation.setFillAfter(true);
                    complint_xia_image.startAnimation(animation);
                } else {
                    animateOpen(toushu_flage_Recyclview);//打开动画
                    RotateAnimation animation = new RotateAnimation(0, 180, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                    animation.setDuration(durationMillis);
                    animation.setFillAfter(true);
                    complint_xia_image.startAnimation(animation);
                }
                break;
            case R.id.complint_tijiao://提交
                if(checkForm())
                {
                    add_Comlaint();//提交投诉
                }
                break;
        }
    }

    private void openSelectDialog() {
        final String[] stringItems = {"相册", "拍照"};
        final ActionSheetDialog dialog = new ActionSheetDialog(getContext(),
                stringItems, null);
        dialog.isTitleShow(false).show();

        dialog.setOnOperItemClickL(new OnOperItemClickL() {
            @Override
            public void onOperItemClick(AdapterView<?> parent, View view, int position,
                                        long id) {
                dialog.dismiss();
                if (position == 0) {
                    need_permissions(0);
                    photo();//相册
                } else if (position == 1) {
                    need_permissions(1);
                    camera();
                }
            }
        });
    }
    private void need_permissions(int flage) {//需要的权限
        rxPermissions
                .requestEach(Manifest.permission.CAMERA,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE)
                .subscribe(permission -> {
                    if (permission.granted) {
                        //权限获取成功
                    } else {
                        ToastUtil.Error("请打开相机及存储权限！");
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
                        return getActivity();
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
                        if (img_w==0){
                            Bitmap bitmap = BitmapFactory.decodeFile(imageMultipleResultEvent
                                    .getResult().get(0)
                                    .getThumbnailSmallPath());
                            img_w=bitmap.getWidth();
                            img_h=bitmap.getHeight();
                        }

                        int size = imageMultipleResultEvent.getResult().size();
                        maxPhotoCount = maxPhotoCount - size;
                        SelectedImgBean temp;
                        for (int i = 0; i < size; i++) {
                            temp = new SelectedImgBean();
                            temp.setOriginalPath(imageMultipleResultEvent.getResult().get(i)
                                    .getThumbnailSmallPath());
                            temp.setThumbPath(imageMultipleResultEvent.getResult().get(i)
                                    .getThumbnailSmallPath());
                            Logger.i("original:" + temp.getOriginalPath() + "==thumb:" + temp
                                    .getThumbPath());
//                            tempList.add(temp);
                            uploadHeader(temp.getOriginalPath());//上传图标及验证token
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
        loadingDialog.show();
        SelectImgBean selectImgBean = new SelectImgBean();
        selectImgBean.setImgUrl(localPath);
        Image_Adapter.addData(0, selectImgBean);//添加图片

        if (Image_Adapter.getItemCount() == 10) {//判断是否是添加的第10张  如果是 就去除已有的最后一张
            Image_Adapter.remove(9);
        }

        File temp = new File(localPath);
        RequestBody requestFile =
                RequestBody.create(MediaType.parse("multipart/form-data"), temp);
        String filename = "hxs" + SharedPreferencedUtils.getStr(Constants
                .USER_TOKEN) + System.currentTimeMillis();
        MultipartBody.Part body = MultipartBody.Part.createFormData(filename,
                temp.getName(), requestFile);
        mPresenter.uploadHeader(body);     //上传图片文件
    }

    private void animateClose(final View view) {
        int origHeight = view.getHeight();
        ValueAnimator animator = createDropAnimator(view, origHeight, 0);
        animator.addListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animation) {
                // 动画结束时隐藏view
                view.setVisibility(View.GONE);
            }
        });
        animator.start();
    }

    @SuppressLint("NewApi")
    private void animateOpen(final View view) {
        view.setVisibility(View.VISIBLE);
        ValueAnimator animator = createDropAnimator(view, 0,
                mHiddenViewMeasuredHeight);
        animator.start();
    }

    private ValueAnimator createDropAnimator(final View view, int start, int end) {
        // 创建一个数值发生器
        ValueAnimator animator = ValueAnimator.ofInt(start, end);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int value = (Integer) valueAnimator.getAnimatedValue();
                ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
                layoutParams.height = value;
                view.setLayoutParams(layoutParams);
            }
        });
        return animator;
    }
    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
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
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        animateClose(toushu_flage_Recyclview);//关闭动画
        RotateAnimation animation = new RotateAnimation(180, 0, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        animation.setDuration(350);
        animation.setFillAfter(true);
        complint_xia_image.startAnimation(animation);
        create_id = this.adapter.getData().get(position).getId();
        complint_neixin.setText(this.adapter.getData().get(position).getCatename());
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

        switch (view.getId())
        {
            case R.id.iv_selected://点击的是最后一个
                SelectImgBean item1 = this.Image_Adapter.getItem(position);
                if (item1.isSelectItem()) {//是选择图片的哪个图片
                    openSelectDialog();
                } else {
                    // TODO: 2018-01-27 放大操作
                }
                break;
            case R.id.iv_close:
                if (uploadedUrl.size() >= position) {
                    uploadedUrl.remove(position);
                }
                adapter.remove(position);
                maxPhotoCount = maxPhotoCount + 1;
                SelectImgBean item = this.Image_Adapter.getItem(adapter.getData().size() - 1);
                if (!item.isSelectItem()) {
                    addSelectImg();
                }
                break;
        }
    }

    @Override
    public void uploadHeader(FileUploadBean fileUploadBean) {
        serverSize=serverSize+1;
        if (serverSize==Image_Adapter.getData().size()-1){
            loadingDialog.dismiss();
        }
        List<String> imglist = fileUploadBean.getImglist();
        for (String item : imglist) {

            uploadedUrl.add(item.trim());

        }
    }

    @Override
    public void complaintType(List<ComplaintTypeBean> data) {
        create_id=data.get(0).getId();
        complint_neixin.setText(data.get(0).getCatename());
        adapter.setNewData(data);
        toushu_flage_Recyclview.setVisibility(View.GONE);
    }
    private void add_Comlaint()
    {
        loadingDialog.show();
        Map<String, Object> request = new HashMap<>();
        request.put("token", SharedPreferencedUtils.getStr("p_token",""));
        request.put("phone", SharedPreferencedUtils.getStr("proprety_phone",""));
        request.put("managerid", SharedPreferencedUtils.getStr(Constants.USER_NUM,""));
        request.put("vid", SharedPreferencedUtils.getStr("vid",""));
        request.put("villagename", complint_qu.getText().toString());
        request.put("cateid", create_id+"");
        request.put("catename",complint_neixin.getText().toString());
        request.put("realname",complint_name.getText().toString());
        request.put("phone", complint_phone.getText().toString());
        request.put("content", complint_content.getText().toString());
        String imgStr = uploadedUrl.toString().replaceAll(" ", "");
        if (!TextUtils.isEmpty(imgStr)) {
            String substring = imgStr.substring(1, imgStr.length() - 1);
            request.put("imgs", substring);
        } else {
            request.put("imgs", "");
        }
        request.put("villageaddress",SharedPreferencedUtils.getStr("address_vill",complint_qu.getText().toString()));
        mPresenter.addComplaint(GsonUtil.obj2JSON(request));
    }

    @Override
    public void stateError() {
        super.stateError();
        loadingDialog.dismiss();
    }

    @Override
    public void addComplaint(String data) {//投诉成功
        loadingDialog.dismiss();
        ActivityUtil.getInstance().closeActivity(getActivity());
        SAToast.makeText(getContext(),"投诉成功").show();
    }

    @Override
    public void findComplaintList(ComSearchBean bean) {

    }

    @Override
    public void detaComplaintList(List<PropertyDetailBean> bean) {

    }

    @Override
    protected void lazyLoad() {

    }

    public  static PropertyComplaintFragment1 newInstance(int num) {
        PropertyComplaintFragment1 f = new PropertyComplaintFragment1();
        // Supply num input as an argument.
        Bundle args = new Bundle();
        args.putInt("flage", num);
        f.setArguments(args);
        return f;
    }
}
