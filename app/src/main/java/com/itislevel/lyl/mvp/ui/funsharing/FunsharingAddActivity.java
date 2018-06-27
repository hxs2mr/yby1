package com.itislevel.lyl.mvp.ui.funsharing;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.flyco.dialog.listener.OnOperItemClickL;
import com.flyco.dialog.widget.ActionSheetDialog;
import com.itislevel.lyl.R;
import com.itislevel.lyl.adapter.SelectImgAdapter;
import com.itislevel.lyl.app.Constants;
import com.itislevel.lyl.base.RootActivity;
import com.itislevel.lyl.mvp.model.bean.FileUploadBean;
import com.itislevel.lyl.mvp.model.bean.FunsharingAddBean;
import com.itislevel.lyl.mvp.model.bean.FunsharingCommnetAddBean;
import com.itislevel.lyl.mvp.model.bean.FunsharingLikeBean;
import com.itislevel.lyl.mvp.model.bean.FunsharingListBean;
import com.itislevel.lyl.mvp.model.bean.FunsharingMyBean;
import com.itislevel.lyl.mvp.model.bean.MeiZiBean;
import com.itislevel.lyl.mvp.model.bean.MeiZiMultipleBean;
import com.itislevel.lyl.mvp.model.bean.SelectImgBean;
import com.itislevel.lyl.mvp.model.bean.SelectedImgBean;
import com.itislevel.lyl.mvp.ui.address.CityActivity;
import com.itislevel.lyl.mvp.ui.address.ProvinceActivity;
import com.itislevel.lyl.mvp.ui.blessing.BlessingAddActivity;
import com.itislevel.lyl.mvp.ui.bus.EventShareAdd;
import com.itislevel.lyl.mvp.ui.bus.bless_home;
import com.itislevel.lyl.mvp.ui.chatkeyboardview.EmotionMainFragment;
import com.itislevel.lyl.mvp.ui.chatkeyboardview.EmotionUtils;
import com.itislevel.lyl.mvp.ui.chatkeyboardview.SpanStringUtils;
import com.itislevel.lyl.mvp.ui.main.MainActivity;
import com.itislevel.lyl.mvp.ui.main.dynamic.childfragment.dfind.DFindFragment;
import com.itislevel.lyl.mvp.ui.troublesolution.TroubleNormalAddActivity;
import com.itislevel.lyl.utils.ActivityUtil;
import com.itislevel.lyl.utils.GsonUtil;
import com.itislevel.lyl.utils.SAToast;
import com.itislevel.lyl.utils.SharedPreferencedUtils;
import com.itislevel.lyl.utils.SimpleRxGalleryFinal;
import com.itislevel.lyl.utils.ToastUtil;
import com.itislevel.lyl.utils.rxbus.RxBus;
import com.itislevel.lyl.utils.rxbus.annotation.UseRxBus;
import com.orhanobut.logger.Logger;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.vondear.rxtools.activity.AndroidBug5497Workaround;

import org.greenrobot.eventbus.EventBus;

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
import cn.finalteam.rxgalleryfinal.utils.BitmapUtils;
import io.reactivex.annotations.NonNull;
import io.reactivex.annotations.Nullable;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

@UseRxBus
public class FunsharingAddActivity extends RootActivity<FunsharingPresenter> implements
        FunsharingContract.View, BaseQuickAdapter.OnItemChildClickListener {

    Bundle bundle = new Bundle();
    private String PROVINCE_ID = "";
    private String CITY_ID = "";
    private String COUNTY_ID = ""; //很多情况下是空的

    private String PROVINCE_TITLE = "";
    private String CITY_TITLE = "";
    private String COUNTY_TITLE = "";//很多情况下是空的

//
//    @BindView(R.id.iv_img_video)
//    ImageView iv_img_video;

    @BindView(R.id.et_content)
    EditText et_content;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    SelectImgAdapter adapter;
    private int maxPhotoCount = 9;
    private List<String> uploadedUrl = new ArrayList<>();


    // 表情输入框

    @BindView(R.id.ll_parent)
    LinearLayoutCompat mLinearLayout;

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

    private int img_w=0;
    private int img_h=0;

    private RxPermissions rxPermissions;
    private  int add_photo=0;
    private  int add_carame=0;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_funsharing_add;
    }

    @Override
    protected void initEventAndData() {
        rxPermissions = new RxPermissions(this);
        AndroidBug5497Workaround.assistActivity(this);
        bundle = this.getIntent().getExtras();
        String title = bundle.getString(Constants.CITY_TITLE);

        PROVINCE_ID = bundle.getString(Constants.PROVINCE_ID);
        CITY_ID = bundle.getString(Constants.CITY_ID);
        COUNTY_ID = bundle.getString(Constants.COUNTY_ID);

        PROVINCE_TITLE = bundle.getString(Constants.PROVINCE_NAME);
        CITY_TITLE = bundle.getString(Constants.CITY_NAME);
        COUNTY_TITLE = bundle.getString(Constants.COUNTY_NAME);


        setToolbarTvTitle(COUNTY_TITLE);

        setToolbarMoreTxt("发布");
        setToolbarMoreClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingDialog.show();
                String content = et_content.getText().toString().trim();
                if (TextUtils.isEmpty(content) && uploadedUrl.size() <= 0) {
                    ToastUtil.Info("文字,图片不能同时为空");
                    return;
                }

                Map<String, Object> request = new HashMap<>();
                request.put("token", SharedPreferencedUtils.getStr(Constants.USER_TOKEN));
                request.put("usernum", SharedPreferencedUtils.getStr(Constants.USER_NUM));
                request.put("userid", SharedPreferencedUtils.getStr(Constants.USER_ID));
               // request.put("username", SharedPreferencedUtils.getStr(Constants.USER_NAME));

                request.put("provid", PROVINCE_ID);
                request.put("provincename", PROVINCE_TITLE);

                request.put("cityid", CITY_ID);
                request.put("cityname", CITY_TITLE);

                request.put("content", content);

                String imgStr = uploadedUrl.toString().replaceAll(" ", "");
                if (!TextUtils.isEmpty(imgStr)) {
                    String substring = imgStr.substring(1, imgStr.length() - 1);
                    request.put("imge", substring);
                } else {
                    request.put("imge", "");
                }
                request.put("img_w",img_w);
                request.put("img_h",img_h);
                mPresenter.addDynamic(GsonUtil.obj2JSON(request));
            }
        });
        setPath();
        initAdapter();

        // 输入框
        mInputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        initViewListener();
        need_permissions(1);//开启权限
    }

    @OnClick({R.id.et_content,R.id.fl_pannel})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.et_content:
               // mEditText.requestFocus();
                mEditText.setFocusable(false);
                mEditText.setFocusableInTouchMode(false);
               // et_content.requestFocus();
                break;
            case R.id.fl_pannel:
                hideSoftKeyboard(fl_pannel);
                break;
        }
    }

    private void initViewListener() {
        et_content.addTextChangedListener(new EditTextContentChangeListener());
        initEmotionMainFragment();

        et_content.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){//获取焦点
                    emotionMainFragment.hideEmotionLayoutoAndExtenLayout();
                }else{//失去焦点
//                    emotionMainFragment.hideEmotionLayoutoAndExtenLayout();
                 }
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        initEmotionViewAndListener();
    }

    private void initEmotionViewAndListener() {
        if (mInputView == null) {
            mInputView = getSupportFragmentManager().findFragmentById(R.id.fl_pannel).getView();
            mBtnSend = (Button) mInputView.findViewById(R.id.bar_btn_send);
            mIvExtend = (ImageView) mInputView.findViewById(R.id.bar_iv_extend);
            mEditText = (EditText) mInputView.findViewById(R.id.bar_edit_text);
            mEditText.addTextChangedListener(new EditTextChangeListener());//文本变化监听器
            mIvExtend.setVisibility(View.GONE);
            mBtnSend.setVisibility(View.GONE);
            emotionMainFragment.setEdittextGone();

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

    /**
     * 设置 照片路径 和 裁剪路径
     * //裁剪会自动生成路径；也可以手动设置裁剪的路径；
     */
    private void setPath() {
        RxGalleryFinalApi.setImgSaveRxSDCard(Constants.PATH_GALLERY);
        RxGalleryFinalApi.setImgSaveRxCropSDCard(Constants.PATH_GALLERY_CROP);
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
        SAToast.makeText(this,"错来了").show();
        super.stateError(e);
        if(adapter.getData().size()>0)
        {
            adapter.remove(0);
        }
    }
    @Override
    public void showContent(String msg) {

    }

    @Override
    public void showData(List<MeiZiBean> data) {

    }

    @Override
    public void showDataMultiple(List<MeiZiMultipleBean> data) {

    }

    @Override
    public void addDynamic(String funsharingAddBean) {
        EventBus.getDefault().post(new EventShareAdd("123"));
        loadingDialog.dismiss();
        ActivityUtil.getInstance().closeActivity(FunsharingAddActivity.this);
    }

    private void openSelectDialog() {
        final String[] stringItems = {"相册", "拍照"};
        final ActionSheetDialog dialog = new ActionSheetDialog(FunsharingAddActivity.this,
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
                        return FunsharingAddActivity.this;
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
        adapter.addData(0, selectImgBean);//添加图片

        if (adapter.getItemCount() == 10) {//判断是否是添加的第10张  如果是 就去除已有的最后一张
            adapter.remove(9);
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

    @Override
    public void shareList(FunsharingListBean funsharingListBeans) {

    }

    @Override
    public void shareListMy(FunsharingMyBean funsharingMyBeans) {

    }

    @Override
    public void shareDel(String message) {

    }

    @Override
    public void commentShareAdd(FunsharingCommnetAddBean funsharingCommnetAddBean) {

    }


    @Override
    public void commentShareDel(String message) {

    }

    @Override
    public void shareLikeOrCancel(FunsharingLikeBean funsharingLikeBean) {

    }
    int serverSize=0;


    @Override
    public void uploadHeader(FileUploadBean fileUploadBean) {
        serverSize=serverSize+1;
        if (serverSize==adapter.getData().size()-1){
            loadingDialog.dismiss();
        }
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
                SelectImgBean item1 = this.adapter.getItem(position);
                if (item1.isSelectItem()) {//是选择图片的哪个图片
                    openSelectDialog();
                } else {
                    // TODO: 2018-01-27 放大操作
                }
                break;
        }

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
                mIvExtend.setVisibility(View.GONE);

            } else {
                mBtnSend.setVisibility(View.GONE);
                mIvExtend.setVisibility(View.GONE);
            }


            SpannableString emotionContent = SpanStringUtils.getEmotionContent(EmotionUtils
                            .EMOTION_CLASSIC_TYPE, FunsharingAddActivity.this, et_content,
                    charSequence.toString());
            String content = et_content.getText().toString();
            if (!content.equals(charSequence.toString()))
                et_content.setText(emotionContent);


            int length = mEditText.getText().toString().length();
            //mEditText.setSelection(length);
            mEditText.clearFocus();
           /// mEditText.setFocusableInTouchMode(true);
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
    class EditTextContentChangeListener implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
            SpannableString emotionContent = SpanStringUtils.getEmotionContent(EmotionUtils
                            .EMOTION_CLASSIC_TYPE, FunsharingAddActivity.this, et_content,
                    charSequence.toString());

            String content = mEditText.getText().toString();
            if (!content.equals(emotionContent))
                mEditText.setText(emotionContent);


            int length = mEditText.getText().toString().length();
            mEditText.setSelection(length);
            mEditText.clearFocus();
//            emotionMainFragment.showEmotionLayoutoAndExtenLayout();
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }


    public static void hideSoftKeyboard(View view) {
        Context context = view.getContext();
        InputMethodManager imm = (InputMethodManager) context
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_DEL&&mEditText.isFocused()) {

            mEditText.dispatchKeyEvent(new KeyEvent(
                    KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_DEL));
        }
        return super.onKeyDown(keyCode, event);
    }
}
