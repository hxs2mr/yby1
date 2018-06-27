package com.itislevel.lyl.adapter;

import android.graphics.Bitmap;
import android.support.annotation.LayoutRes;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.itislevel.lyl.R;
import com.itislevel.lyl.app.App;
import com.itislevel.lyl.mvp.model.bean.MeiZiBean;
import com.itislevel.lyl.mvp.model.bean.MeiZiMultipleBean;
import com.lzy.ninegrid.ImageInfo;
import com.lzy.ninegrid.NineGridView;
import com.lzy.ninegrid.preview.NineGridViewClickAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * **********************
 * 功 能:妹纸适配器
 * 创建人:itisi
 * 邮  箱:itisivip@qq.com
 * 创建时间:2017/7/13 10:50
 * 修改人:itisi
 * 修改时间: 2017/7/13 10:50
 * 修改内容:itisi
 * *********************
 */

public class MeiZiAdapter extends BaseQuickAdapter<MeiZiBean, BaseViewHolder> {


    public MeiZiAdapter(@LayoutRes int layoutResId) {
        super(layoutResId);
    }


    @Override
    protected void convert(BaseViewHolder helper, final MeiZiBean item) {
//        ImageLoadProxy.getInstance()
//                .load(new ImageLoadConfiguration.Builder(App.getInstance())
//                        .url(item.getUrl())
//                        .defaultImageResId(R.mipmap.test_menu_love_white)
//                        .imageView((ImageView) helper.getView(R.id.iv_meizi)).build());

//        final ImageView view = helper.getView(R.id.iv_test_adapter);
//        helper.addOnClickListener(R.id.iv_test_adapter);
//        //存在记录的高度时先Layout再异步加载图片
//        if (item.getHeight() > 0) {
//            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
//            layoutParams.height = item.getHeight();
//        }
//
//
//        Glide.with(mContext).load(item.getUrl()).asBitmap().diskCacheStrategy(DiskCacheStrategy.ALL)
//                .into(new SimpleTarget<Bitmap>(App.SCREEN_WIDTH / 2, App.SCREEN_WIDTH / 2) {
//                    @Override
//                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
//
//                        int width = resource.getWidth();
//                        int height = resource.getHeight();
//                        int realHeight = (App.SCREEN_WIDTH / 2) * height / width;
//                        item.setHeight(realHeight);
//                        ViewGroup.LayoutParams lp = view.getLayoutParams();
//                        lp.height = realHeight;
//                        view.setImageBitmap(resource);
//                    }
//                });

        List<ImageInfo> urlList=new ArrayList<>();
        ImageInfo imageInfo;

        Random random=new Random(1);
        int anInt = random.nextInt(50);

        for (int i = 0; i < anInt; i++) {
            imageInfo=new ImageInfo();
            imageInfo.setThumbnailUrl(item.getUrl());
            imageInfo.setBigImageUrl(item.getUrl());
            urlList.add(imageInfo);
        }

        NineGridView ngv_img= helper.getView(R.id.ninegrid_share);
        ngv_img.setAdapter(new NineGridViewClickAdapter(App.getInstance(), urlList));


    }

}
