package com.itislevel.lyl.utils.imageload.manager;

import com.bumptech.glide.DrawableTypeRequest;
import com.bumptech.glide.Glide;
import com.itislevel.lyl.utils.imageload.ImageLoadConfiguration;

import jp.wasabeef.glide.transformations.CropCircleTransformation;
import jp.wasabeef.glide.transformations.GrayscaleTransformation;

/**
 * author: itisi---
 * created by Administrator on 2017/3/22.
 * desc:
 */

public class GlideManager {
    public static void load(ImageLoadConfiguration configuration){
        DrawableTypeRequest dtRequest= Glide.with(configuration.mContext).load(configuration.mUrl);
        if (configuration.defaultImageResId!=0){
            dtRequest.placeholder(configuration.defaultImageResId);
        }
        if (configuration.isCircle){
            dtRequest.bitmapTransform(new CropCircleTransformation(configuration.mContext));
        }
        if (configuration.isGray){
            dtRequest.bitmapTransform(new GrayscaleTransformation(configuration.mContext));
        }
        if (configuration.imageWidth!=0&&configuration.imageHeight!=0){
            dtRequest.override(configuration.imageWidth,configuration.imageHeight);
        }
        if (configuration.isGray){
            dtRequest.asGif();
        }
        //模糊 后期可扩展
        //dtRequest.bitmapTransform(new BlurTransformation(configuration.mContext,5));

        //遮罩层??? 相当于取图片相交部分
       // dtRequest.bitmapTransform(new MaskTransformation(configuration.mContext, R.mipmap.ic_launcher));
        dtRequest.into(configuration.mImageView);

    }
}
