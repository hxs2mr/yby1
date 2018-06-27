package com.itislevel.lyl.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.util.MultiTypeDelegate;
import com.itislevel.lyl.R;
import com.itislevel.lyl.app.App;
import com.itislevel.lyl.mvp.model.bean.MeiZiBean;
import com.itislevel.lyl.mvp.model.bean.MeiZiMultipleBean;
import com.itislevel.lyl.utils.imageload.ImageLoadConfiguration;
import com.itislevel.lyl.utils.imageload.ImageLoadProxy;
import com.lzy.ninegrid.ImageInfo;
import com.lzy.ninegrid.NineGridView;
import com.lzy.ninegrid.preview.NineGridViewClickAdapter;

import java.util.ArrayList;
import java.util.List;


/**
 * **********************
 * 功 能:妹纸适配器-多item
 * 创建人:itisi
 * 邮  箱:itisivip@qq.com
 * 创建时间:2017/7/13 10:50
 * 修改人:itisi
 * 修改时间: 2017/7/13 10:50
 * 修改内容:itisi
 * *********************
 */

public class MeiZiMultipleAdapter extends BaseMultiItemQuickAdapter<MeiZiMultipleBean, BaseViewHolder> {

    List<ImageInfo> urlList=new ArrayList<>();
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public MeiZiMultipleAdapter(List<MeiZiMultipleBean> data) {
        super(data);

        ImageInfo imageInfo;
        for (MeiZiMultipleBean item:data){
            imageInfo=new ImageInfo();
            imageInfo.setThumbnailUrl(item.getUrl());
            imageInfo.setBigImageUrl(item.getUrl());

            urlList.add(imageInfo);
        }

        int i=0;
        for (MeiZiMultipleBean bean:data){
            i++;
            if (i%2==0){
                bean.setItemType(0);
                addItemType(0,R.layout.test_adapter_img);

            }else{
                bean.setItemType(1);
                addItemType(1,R.layout.test_adapter_txt);
            }
        }


    }

    @Override
    protected void convert(BaseViewHolder helper, MeiZiMultipleBean item) {
        switch (item.getItemType()){
            case 0:
//        ImageLoadProxy.getInstance()
//                .load(new ImageLoadConfiguration.Builder(App.getInstance())
//                        .url(item.getUrl())
//                        .defaultImageResId(R.mipmap.icon_img_load_pre)
//                        .imageView((ImageView) helper.getView(R.id.iv_test_adapter)).build());

                NineGridView ngv_img= helper.getView(R.id.ngv_img);
                ngv_img.setAdapter(new NineGridViewClickAdapter(App.getInstance(), urlList));
                break;
            case 1:
                    helper.setText(R.id.tv_test_txt,item.getCreatedAt());
                break;
        }
    }
}
