package com.itislevel.lyl.mvp.ui.troublesolution;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
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
import com.itislevel.lyl.mvp.model.bean.BlessOrderBean;
import com.itislevel.lyl.mvp.model.bean.FileUploadBean;
import com.itislevel.lyl.mvp.model.bean.MeiZiBean;
import com.itislevel.lyl.mvp.model.bean.SelectImgBean;
import com.itislevel.lyl.mvp.model.bean.SelectedImgBean;
import com.itislevel.lyl.mvp.model.bean.TroubleAddBean;
import com.itislevel.lyl.mvp.model.bean.TroubleAdviserMyBean;
import com.itislevel.lyl.mvp.model.bean.TroubleCommentAdd;
import com.itislevel.lyl.mvp.model.bean.TroubleListBean;
import com.itislevel.lyl.mvp.model.bean.TroubleTypeBean;
import com.itislevel.lyl.mvp.ui.blessing.BlessingAddActivity;
import com.itislevel.lyl.mvp.ui.chatkeyboardview.EmotionMainFragment;
import com.itislevel.lyl.mvp.ui.chatkeyboardview.EmotionUtils;
import com.itislevel.lyl.mvp.ui.chatkeyboardview.SpanStringUtils;
import com.itislevel.lyl.mvp.ui.funsharing.FunsharingAddActivity;
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

public class TroubleNormalAddActivity extends RootActivity<TroublesolutionPresenter> implements
        TroublesolutionContract.View, BaseQuickAdapter.OnItemChildClickListener {

    Bundle bundle = new Bundle();
    private String PROVINCE_ID = "";
    private String CITY_ID = "";
    private String COUNTY_ID = ""; //很多情况下是空的

    private String PROVINCE_TITLE = "";
    private String CITY_TITLE = "";
    private String COUNTY_TITLE = "";//很多情况下是空的


    @BindView(R.id.et_content)
    EditText et_content;

    @BindView(R.id.btn_save)
    Button btn_save;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    SelectImgAdapter adapter;
    private int maxPhotoCount = 9;
    private List<String> uploadedUrl = new ArrayList<>();

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

    private int img_w = 0;
    private int img_h = 0;
    private ActionSheetDialog dialog;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_trouble_normal;
    }

    @Override
    protected void initEventAndData() {
        bundle = this.getIntent().getExtras();
        String title = bundle.getString(Constants.TROUBLE_TITLE);

        PROVINCE_ID = bundle.getString(Constants.PROVINCE_ID);
        CITY_ID = bundle.getString(Constants.CITY_ID);
        COUNTY_ID = bundle.getString(Constants.COUNTY_ID);

        PROVINCE_TITLE = bundle.getString(Constants.PROVINCE_NAME);
        CITY_TITLE = bundle.getString(Constants.CITY_NAME);
        COUNTY_TITLE = bundle.getString(Constants.COUNTY_NAME);

        setToolbarTvTitle(title);


        setPath();
        initAdapter();

        // 输入框
        mInputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        initViewListener();


    }


    private void initViewListener() {

        initEmotionMainFragment();
        et_content.addTextChangedListener(new EditTextContentChangeListener());

        et_content.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {//获取焦点
                    emotionMainFragment.hideEmotionLayoutoAndExtenLayout();
                } else {//失去焦点
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
//                    sendTxtMessage();
//                    Editable content = mEditText.getText();
//                    if (TextUtils.isEmpty(content)) {
//                        ToastUtil.Warning("内容不能为空");
//                    } else {
//                        Map<String, String> request = new HashMap<>();
//                        request.put("token", SharedPreferencedUtils.getStr(Constants.USER_TOKEN));
//                        request.put("usernum", SharedPreferencedUtils.getStr(Constants.USER_NUM));
//                        request.put("userid", SharedPreferencedUtils.getStr(Constants.USER_ID));
//
//                        int shareid = -1;
//                        switch (whoAdapter) {
//                            case 0:
//                                shareid = adapter.getItem(operatorPosition).getId();
//                                break;
//                            case 1:
//                                shareid = commentAdapter.getItem(operatorPosition).getShareid();
//                                break;
//                            case 2:
//                                shareid = commentReplayAdapter.getItem(operatorPosition)
//                                        .getShareid();
//                                break;
//                        }
//                        // TODO: 2017/12/16 此处可能有坑
//                        request.put("shareid", shareid + "");
//                        request.put("comment", content.toString());
//                        request.put("parentid", parentId);
//                        request.put("parentname", "");
//                        request.put("fabulous", isComment + "");
//                        request.put("touserid", touserid);
//                        mPresenter.commentShareAdd(GsonUtil.obj2JSON(request));
//                    }

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

    private void openSelectDialog() {
        final String[] stringItems = {"相册", "拍照"};
        dialog = new ActionSheetDialog(TroubleNormalAddActivity.this,
                stringItems, null).isTitleShow(false);
        dialog.show();

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
                        return TroubleNormalAddActivity.this;
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

                        if (img_w == 0) {
                            Bitmap bitmap = BitmapFactory.decodeFile(imageMultipleResultEvent
                                    .getResult().get(0)
                                    .getThumbnailSmallPath());
                            img_w = bitmap.getWidth();
                            img_h = bitmap.getHeight();
                        }

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
                                    .getThumbnailSmallPath());
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


    @OnClick({R.id.btn_save, R.id.et_content})
    public void click(View v) {
        switch (v.getId()) {
            case R.id.btn_save:
                String content = et_content.getText().toString().trim();

                if (TextUtils.isEmpty(content)) {
                    ToastUtil.Info("内容不能为空");
                    return;
                }

                loadingDialog.show();
                Map<String, Object> request = new HashMap<>();
                request.put("token", SharedPreferencedUtils.getStr(Constants.USER_TOKEN));
                request.put("usernum", SharedPreferencedUtils.getStr(Constants.USER_NUM));
                request.put("userid", SharedPreferencedUtils.getStr(Constants.USER_ID));
                request.put("content", content);
                request.put("imge", "");

                request.put("provid", PROVINCE_ID);
                request.put("provincename", PROVINCE_TITLE);

                request.put("cityid", CITY_ID);
                request.put("cityname", CITY_TITLE);

                request.put("secondcateid", bundle.getInt(Constants.TROUBLE_TEAM_TYPE_SECOND_ID));

                String imgStr = uploadedUrl.toString().replaceAll(" ", "");
                if (!TextUtils.isEmpty(imgStr)) {
                    String substring = imgStr.substring(1, imgStr.length() - 1);
                    request.put("imge", substring);
                } else {
                    request.put("imge", "");

                }
                request.put("img_w", img_w);
                request.put("img_h", img_h);

                mPresenter.troubleAdd(GsonUtil.obj2JSON(request));

                break;
            case R.id.et_content:
                mEditText.requestFocus();
                break;
        }
    }

    @Override
    public void showContent(String msg) {

    }

    @Override
    public void showDataList(List<MeiZiBean> meiZiBeans) {

    }

    @Override
    public void troubleAdd(TroubleAddBean troubleAdd) {
        loadingDialog.dismiss();
        ActivityUtil.getInstance().openActivity(TroubleNormalAddActivity.this,
                TroublesolutionListActivity.class, bundle);

        ActivityUtil.getInstance().closeActivity(this);


    }

    @Override
    public void troubleList(TroubleListBean troubleListBean) {

    }

    @Override
    public void troubleListMy(TroubleListBean troubleListBean) {

    }

    @Override
    public void troubleDel(String action) {

    }

    @Override
    public void troubleCommentReplay(TroubleCommentAdd troubleCommentAdd) {

    }

    @Override
    public void troubleCommentDel(String action) {

    }

    @Override
    public void troubleType(TroubleTypeBean troubleTypeBean) {

    }

    @Override
    public void teamProblemAdd(String message) {

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
    public void teamViewCount(String message) {

    }

    @Override
    public void uploadHeader(FileUploadBean fileUploadBean) {
        loadingDialog.dismiss();
        List<String> imglist = fileUploadBean.getImglist();
        for (String item : imglist) {
            uploadedUrl.add(item.trim());

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        SimpleRxGalleryFinal.get().onActivityResult(requestCode, resultCode, data);

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
                mIvExtend.setVisibility(View.GONE);

            } else {
                mBtnSend.setVisibility(View.GONE);
                mIvExtend.setVisibility(View.GONE);
            }

            SpannableString emotionContent = SpanStringUtils.getEmotionContent(EmotionUtils
                            .EMOTION_CLASSIC_TYPE, TroubleNormalAddActivity.this, et_content,
                    charSequence.toString());
            String content = et_content.getText().toString();
            if (!content.equals(charSequence.toString()))
                et_content.setText(emotionContent);


            int length = mEditText.getText().toString().length();
            mEditText.setSelection(length);
            mEditText.requestFocus();
            mEditText.setFocusableInTouchMode(true);

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
                            .EMOTION_CLASSIC_TYPE, TroubleNormalAddActivity.this, et_content,
                    charSequence.toString());

            String content = mEditText.getText().toString();
            if (!content.equals(emotionContent))
                mEditText.setText(emotionContent);


            int length = mEditText.getText().toString().length();
            mEditText.setSelection(length);
            mEditText.requestFocus();
            mEditText.setFocusableInTouchMode(true);
//            emotionMainFragment.showEmotionLayoutoAndExtenLayout();
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_DEL && mEditText.isFocused()) {

            mEditText.dispatchKeyEvent(new KeyEvent(
                    KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_DEL));
        }
        return super.onKeyDown(keyCode, event);
    }
}
