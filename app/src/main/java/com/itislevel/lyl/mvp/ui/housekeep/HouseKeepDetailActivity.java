package com.itislevel.lyl.mvp.ui.housekeep;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.itislevel.lyl.R;
import com.itislevel.lyl.app.App;
import com.itislevel.lyl.app.Constants;
import com.itislevel.lyl.base.RootActivity;
import com.itislevel.lyl.mvp.model.bean.HouseKeepBean;
import com.itislevel.lyl.mvp.model.bean.MeiZiBean;
import com.itislevel.lyl.utils.SystemUtil;
import com.itislevel.lyl.utils.imageload.ImageLoadConfiguration;
import com.itislevel.lyl.utils.imageload.ImageLoadProxy;
import com.itislevel.lyl.widget.LoopViewPager;
import com.lzy.ninegrid.ImageInfo;
import com.lzy.ninegrid.NineGridView;
import com.lzy.ninegrid.preview.NineGridViewClickAdapter;
import com.orhanobut.logger.Logger;
import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

public class HouseKeepDetailActivity extends RootActivity<HouseKeepPresenter> implements HouseKeepContract.View,BaseQuickAdapter.OnItemClickListener {

    Bundle bundle = new Bundle();

    private String PROVINCE_ID = "";
    private String CITY_ID = "";
    private String COUNTY_ID = "";
    private String company_name;
    private String company_desc;
    private String company_addr;
    private String company_phone;
    private String company_img;
    private String company_contact;
    private String company_logo;

    @BindView(R.id.iv_company_img)
    ImageView iv_company_img;

    @BindView(R.id.tv_company_name)
    TextView tv_company_name;


    @BindView(R.id.tv_company_desc)
    TextView tv_company_desc;


    @BindView(R.id.tv_address)
    TextView tv_address;

    @BindView(R.id.tv_phone)
    TextView tv_phone;

    @BindView(R.id.tv_call)
    TextView tv_call;

    @BindView(R.id.tv_company_contact)
    TextView tv_company_contact;
    private int MY_PERMISSIONS_REQUEST_CALL_PHONE=100;

    @BindView(R.id.ninegrid_share)
    NineGridView ninegrid_share;

    @BindView(R.id.loopviewpager)
    LoopViewPager loopViewPager;
    private  List<ImageInfo> urlList ;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_house_keep_detail;
    }

    @Override
    protected void initEventAndData() {
        urlList = new ArrayList<>();
        bundle = this.getIntent().getExtras();
        String title = bundle.getString(Constants.COUNTY_TITLE);
        PROVINCE_ID = bundle.getString(Constants.PROVINCE_ID);
        CITY_ID = bundle.getString(Constants.CITY_ID);
        setToolbarTvTitle(title);
        company_name = bundle.getString("company_name");
        company_desc = bundle.getString("company_desc");
        company_addr = bundle.getString("company_addr");
        company_phone = bundle.getString("company_phone");
        company_img = bundle.getString("company_img");
        company_logo=bundle.getString("company_logo");
        company_contact = bundle.getString("company_contact");

        tv_company_name.setText(company_name);
        tv_company_desc.setText(company_desc);
        tv_address.setText(company_addr);
        tv_company_contact.setText(company_contact);
        tv_phone.setText(company_phone);

        if (!TextUtils.isEmpty(company_logo)) {
            Glide.with(this)
                    .load(Constants.IMG_SERVER_PATH+company_logo)
                    .asBitmap()
                    .error(R.mipmap.icon_img_load_pre)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(iv_company_img);
        } else {
            ImageLoadProxy.getInstance()
                    .load(new ImageLoadConfiguration.Builder(App.getInstance())
                            .url(R.mipmap.icon_img_load_pre)
                            .imageView(iv_company_img).build());
        }

        // 资质图片

        // 图片 ninegrid_share
        ImageInfo imageInfo;

        if (!TextUtils.isEmpty(company_img)) {
           // ninegrid_share.setVisibility(View.VISIBLE);

            String[] split = company_img.split(",");
            for (String url : split) {
                if (!TextUtils.isEmpty(url) && url != null && url != "" && !url.equals(",")) {
                    imageInfo = new ImageInfo();
                    imageInfo.setBigImageUrl(Constants.IMG_SERVER_PATH + url.trim());
                    imageInfo.setThumbnailUrl(Constants.IMG_SERVER_PATH + url.trim());
                    urlList.add(imageInfo);
                }
            }
           // ninegrid_share.setAdapter(new NineGridViewClickAdapter(App.getInstance(), urlList));
            loopViewPager.setAdapter(new MyAdapter());
            loopViewPager.setOffscreenPageLimit(3);
            loopViewPager.setPageTransformer(true, new ViewPager.PageTransformer() {
                float scale = 0.7f;
                @Override
                public void transformPage(View page, float position) {
                    if (position >= 0 && position <= 1) {
                        page.setScaleY(scale + (1 - scale) * (1 - position));
                    } else if (position > -1 && position < 0) {
                        page.setScaleY(1 + (1 - scale) * position);
                    } else {
                        page.setScaleY(scale);
                    }
                }
            });
            loopViewPager.autoLoop(true);


        } else {
            loopViewPager.setVisibility(View.GONE);
        }

        loopViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @OnClick(R.id.tv_call)
    public void click(View view) {
        switch (view.getId()) {
            case R.id.tv_call:
                // 检查是否获得了权限（Android6.0运行时权限）
                if (ContextCompat.checkSelfPermission(HouseKeepDetailActivity.this,
                        Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // 没有获得授权，申请授权
                    if (ActivityCompat.shouldShowRequestPermissionRationale(HouseKeepDetailActivity.this,
                            Manifest.permission.CALL_PHONE)) {
                        // 返回值：
//                          如果app之前请求过该权限,被用户拒绝, 这个方法就会返回true.
//                          如果用户之前拒绝权限的时候勾选了对话框中”Don’t ask again”的选项,那么这个方法会返回false.
//                          如果设备策略禁止应用拥有这条权限, 这个方法也返回false.
                        // 弹窗需要解释为何需要该权限，再次请求授权
                        Toast.makeText(HouseKeepDetailActivity.this, "请授权！", Toast.LENGTH_LONG).show();

                        // 帮跳转到该应用的设置界面，让用户手动授权
                        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                        Uri uri = Uri.fromParts("package", getPackageName(), null);
                        intent.setData(uri);
                        startActivity(intent);
                    } else {
                        // 不需要解释为何需要该权限，直接请求授权
                        ActivityCompat.requestPermissions(HouseKeepDetailActivity.this,
                                new String[]{Manifest.permission.CALL_PHONE},
                                MY_PERMISSIONS_REQUEST_CALL_PHONE);
                    }
                } else {
                    // 已经获得授权，可以打电话
                    SystemUtil.callPhone(this, company_phone);
                }

                break;
        }
    }

    /**
     * 申请权限
     */
    private void initPermission() {
        RxPermissions rxPermissions = new RxPermissions(this);
        // TODO: 2017/7/27 权限申请日志 发布需关闭
        rxPermissions.requestEach(
                Manifest.permission.CALL_PHONE)
                .subscribe(new Consumer<Permission>() {
                    @Override
                    public void accept(@NonNull Permission permission) throws Exception {
                        String name = permission.name;
                        Logger.i("HouseKeepDetailActivity 权限申请:" + name);
                    }
                });

    }

    @Override
    public void showContent(String msg) {

    }

    @Override
    public void showData(List<MeiZiBean> data) {

    }

    @Override
    public void findHouse(HouseKeepBean houseKeepBean) {

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
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

    }

    class MyAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return urlList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = View.inflate(HouseKeepDetailActivity.this, R.layout.house_viewpager, null);
            ImageView itemImage = view.findViewById(R.id.item_iv);
            Glide.with(HouseKeepDetailActivity.this).load(urlList.get(position).getThumbnailUrl()).into(itemImage);
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }

}
