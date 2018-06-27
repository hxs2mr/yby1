package com.itislevel.lyl.mvp.ui.special;

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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.flyco.animation.SlideEnter.SlideBottomEnter;
import com.flyco.dialog.listener.OnOperItemClickL;
import com.flyco.dialog.widget.ActionSheetDialog;
import com.itislevel.lyl.R;
import com.itislevel.lyl.adapter.SelectImgAdapter;
import com.itislevel.lyl.app.App;
import com.itislevel.lyl.app.Constants;
import com.itislevel.lyl.base.RootActivity;
import com.itislevel.lyl.common.RxUtil;
import com.itislevel.lyl.mvp.model.bean.BlessCartListBean;
import com.itislevel.lyl.mvp.model.bean.CartUpdateBean;
import com.itislevel.lyl.mvp.model.bean.FileUploadBean;
import com.itislevel.lyl.mvp.model.bean.SelectImgBean;
import com.itislevel.lyl.mvp.model.bean.SelectedImgBean;
import com.itislevel.lyl.mvp.model.bean.SpecialGiftByIdBean;
import com.itislevel.lyl.mvp.model.bean.SpecialGiftListBean;
import com.itislevel.lyl.mvp.model.bean.SpecialOrderCompleteBean;
import com.itislevel.lyl.mvp.model.bean.SpecialOrderDetailBean;
import com.itislevel.lyl.mvp.model.bean.SpecialReceiveAddressBean;
import com.itislevel.lyl.mvp.model.bean.SpecialReturnBean;
import com.itislevel.lyl.mvp.model.bean.SpecialTuiKuanDetailBean;
import com.itislevel.lyl.mvp.model.bean.SpecialTuiKuanUpdateBean;
import com.itislevel.lyl.mvp.model.bean.SpecialTypeBean;
import com.itislevel.lyl.mvp.ui.setting.FacebackActivity;
import com.itislevel.lyl.utils.ActivityUtil;
import com.itislevel.lyl.utils.GsonUtil;
import com.itislevel.lyl.utils.SharedPreferencedUtils;
import com.itislevel.lyl.utils.SimpleRxGalleryFinal;
import com.itislevel.lyl.utils.ToastUtil;
import com.itislevel.lyl.utils.imageload.ImageLoadConfiguration;
import com.itislevel.lyl.utils.imageload.ImageLoadProxy;
import com.itislevel.lyl.utils.rxbus.annotation.UseRxBus;
import com.itislevel.lyl.widget.ActionSheetBottomDoalog;
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

/**
 * 退货退款 申请页面
 */
@UseRxBus
public class OrderRefundShenQingActivity extends RootActivity<SpecialPresenter>
        implements SpecialContract.View , BaseQuickAdapter.OnItemChildClickListener{

    Bundle bundle;

    @BindView(R.id.tv_status)
    TextView tv_status;

    @BindView(R.id.tv_reason)
    TextView tv_reason;

    @BindView(R.id.et_price)
    EditText et_price;

    @BindView(R.id.et_desc)
    EditText et_desc;

    @BindView(R.id.tv_goodsname)
    TextView tv_goodsname;


    @BindView(R.id.iv_logo)
    ImageView iv_logo;

    private boolean isFirstApply = true;//初次申请

    //上传图片用的
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    SelectImgAdapter adapter;
    private int maxPhotoCount = 3;
    private List<String> uploadedUrl = new ArrayList<>();

    private SpecialOrderDetailBean.ListBean listBean;
    private SpecialTuiKuanUpdateBean.ListBean listBean1;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_order_refund_shen_qing;
    }

    @Override
    protected void initEventAndData() {

        setToolbarTvTitle("申请退款");
        bundle = getIntent().getExtras();
        initDialog();
        initAdapter();
        setPath();
        // SpecialOrderDetailBean.ListBean
        isFirstApply = bundle.getBoolean("isFirstApply");

        if (isFirstApply){
            String goodsitem = bundle.getString("goodsitem");
            listBean = GsonUtil.toObject(goodsitem,
                    SpecialOrderDetailBean.ListBean.class);

            tv_goodsname.setText(listBean.getGoodsname());
            et_price.setText(Double.parseDouble(listBean.getPrice())*listBean.getCount()+"");
            ImageLoadProxy.getInstance()
                    .load(new ImageLoadConfiguration.Builder(App.getInstance())
                            .defaultImageResId(R.mipmap.icon_img_load_pre)
                            .url(Constants.IMG_SERVER_PATH + listBean.getImgurl())
                            .imageView(iv_logo).build());

        }else{
            loadData();
        }

    }

    private void loadData() {
        //  ordernum goodsid

        loadingDialog.show();

        Map<String, Object> request = new HashMap<>();
        request.put("token", SharedPreferencedUtils.getStr(Constants.USER_TOKEN));
        request.put("usernum", SharedPreferencedUtils.getStr(Constants.USER_NUM));
        request.put("ordernum", bundle.getString("ordernum"));
        request.put("goodsid", bundle.getString("goodsid"));

        mPresenter.specialTuiKuanUpdate(GsonUtil.obj2JSON(request));

    }


    SlideBottomEnter mBasIn = new SlideBottomEnter();
    List<String> stateList = new ArrayList<>();
    List<String> reasonList = new ArrayList<>();

    ActionSheetBottomDoalog stateDialog;
    ActionSheetBottomDoalog reasonDialog;

    private void initDialog() {
        stateList.add("未收到货");
        stateList.add("已收到货");

        reasonList.add("7天无理由退换货");
        reasonList.add("大小/尺寸与商品描述不符");
        reasonList.add("颜色/款式/图案与商品描述不符");
        reasonList.add("材质面料与商品描述不符");

        stateDialog = new ActionSheetBottomDoalog(this, null, stateList, new
                ActionSheetBottomDoalog.OnOKClickListener() {
                    @Override
                    public void onOKClick(String msg) {

                        tv_status.setText(msg);
                    }
                }, "物品状态");

        reasonDialog = new ActionSheetBottomDoalog(this, null, reasonList, new
                ActionSheetBottomDoalog.OnOKClickListener() {
                    @Override
                    public void onOKClick(String msg) {
                        tv_reason.setText(msg);
                    }
                }, "退款原因");


    }

    @OnClick({R.id.rl_state, R.id.rl_reason,R.id.tv_save})
    public void click(View view) {

        switch (view.getId()) {
            case R.id.rl_state:
                stateDialog.showAnim(mBasIn).show();
                break;
            case R.id.rl_reason:
                reasonDialog.showAnim(mBasIn).show();
                break;
            case R.id.tv_save:

                Map<String, Object> request = new HashMap<>();
                request.put("token", SharedPreferencedUtils.getStr(Constants.USER_TOKEN));
                request.put("usernum", SharedPreferencedUtils.getStr(Constants.USER_NUM));
                request.put("buyuserid", SharedPreferencedUtils.getStr(Constants.USER_ID));

                if (isFirstApply){
                    request.put("ordernum", listBean.getOrdernum());
                    request.put("goodsid", listBean.getGoodsid());
                    request.put("price", listBean.getPrice());
                    request.put("goodsname", listBean.getGoodsname());
                    request.put("count", listBean.getCount());
                    request.put("imgurl", listBean.getImgurl());
                    request.put("goodsid", listBean.getGoodsid());
                    request.put("orderid", listBean.getOrderid());
                    request.put("count", listBean.getCount());

                }else{
                    request.put("ordernum", listBean1.getOrdernum());
                    request.put("goodsid", listBean1.getGoodsid());
                    request.put("price", listBean1.getRefundfee());
                    request.put("goodsname", listBean1.getGoodsname());
                    request.put("count", "1");
                    request.put("imgurl", listBean1.getLogourl());
                    request.put("goodsid", listBean1.getGoodsid());
                    request.put("orderid", listBean1.getOrderid());
                    request.put("count", bundle.getString("count"));

                }
                String reason = tv_reason.getText().toString().trim();
                String desc = et_desc.getText().toString().trim();
                String price = et_price.getText().toString().trim();

                request.put("refundreason",reason);
                request.put("refundfee", price);
                request.put("refundexplain", desc);

                request.put("reftype", bundle.getString("reftype"));


                String imgStr = uploadedUrl.toString();
                String substring = imgStr.substring(1, imgStr.length() - 1);

                request.put("imge", substring);


                if (isFirstApply){
                    mPresenter.specialShenQingTuiKuan(GsonUtil.obj2JSON(request));

                }else{
                    mPresenter.specialTuiKuanUpdate2(GsonUtil.obj2JSON(request));
                }

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
        final ActionSheetDialog dialog = new ActionSheetDialog(OrderRefundShenQingActivity.this,
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
                        return OrderRefundShenQingActivity.this;
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
        if (adapter.getItemCount() == 4) {
            adapter.remove(3);
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
    public void showContent(String msg) {

    }

    @Override
    public void specialType(SpecialTypeBean specialTypeBean) {

    }

    @Override
    public void specialList(SpecialGiftListBean specialListBean) {

    }

    @Override
    public void specialById(SpecialGiftByIdBean specialByIdBean) {

    }

    @Override
    public void specialImmediatelyOrder(String action) {

    }

    @Override
    public void specialShopOrder(String action) {

    }

    @Override
    public void specialReceiveAddress(SpecialReceiveAddressBean addressBean) {

    }

    @Override
    public void specialOrderDetail(SpecialOrderDetailBean detailBean) {

    }

    @Override
    public void specialTuiKuanDetail(SpecialTuiKuanDetailBean tuiKuanDetailBean) {

    }

    @Override
    public void specialTuiKuan(String action) {

    }

    @Override
    public void specialTuiKuanUpdate(SpecialTuiKuanUpdateBean tuiKuanUpdateBean) {

        loadingDialog.dismiss();

        listBean1 = tuiKuanUpdateBean.getList().get(0);
        tv_goodsname.setText(listBean1.getGoodsname());
        et_price.setText(listBean1.getRefundfee());
        ImageLoadProxy.getInstance()
                .load(new ImageLoadConfiguration.Builder(App.getInstance())
                        .defaultImageResId(R.mipmap.icon_img_load_pre)
                        .url(Constants.IMG_SERVER_PATH + listBean1.getLogourl())
                        .imageView(iv_logo).build());

        tv_reason.setText(listBean1.getRefundreason());
        et_desc.setText(listBean1.getRefundexplain());

        adapter.remove(0);
        String imge = listBean1.getImge();
        if (!TextUtils.isEmpty(imge)){
            String[] split = imge.split(",");
           for (String item:split){
               uploadedUrl.add(item);

               SelectImgBean selectImgBean = new SelectImgBean();
               selectImgBean.setImgUrl(Constants.IMG_SERVER_PATH+item);
               adapter.addData(selectImgBean);

           }
        }
        if (adapter.getData().size()<=3){
            SelectImgBean selectImgBean = new SelectImgBean();
            selectImgBean.setImgUrl(R.mipmap.icon_img_add_temp);
            adapter.addData(selectImgBean);
        }

    }

    @Override
    public void specialTuiKuanUpdate2(String action) {

        ToastUtil.Success("申请成功,等待商家审核");
        ActivityUtil.getInstance().closeActivity(this);
    }

    @Override
    public void specialOrderComplete(SpecialOrderCompleteBean completeBean) {

    }

    @Override
    public void specialOrderGoPay(String action) {

    }

    @Override
    public void specialOrderCancel(String action) {

    }

    @Override
    public void specialShenQingTuiKuan(String action) {

        ToastUtil.Success("申请退款成功,等待商家审核");
        ActivityUtil.getInstance().closeActivity(this);



    }

    @Override
    public void specialOrderConfirm(String action) {

    }

    @Override
    public void specialReturnList(SpecialReturnBean returnBean) {

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
    public void happyCartAdd(String message) {

    }

    @Override
    public void happyCartList(BlessCartListBean blessCartListBean) {

    }

    @Override
    public void happyCartUpdate(CartUpdateBean message) {

    }

    @Override
    public void happyCartDel(String message) {

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
                uploadedUrl.remove(position);
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
