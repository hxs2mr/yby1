package com.itislevel.lyl.utils.imageload;

import android.content.Context;
import android.widget.ImageView;

/**
 * author: itisi---
 * created by Administrator on 2017/3/22.
 * desc:加载图片 配置类 建造者模式
 */
public class ImageLoadConfiguration {
    /**
     * 上下文
     */
    public Context mContext;
    /**
     * 承载的ImageView
     */
    public ImageView mImageView;
    /**
     * 图片地址 不只是的地址 也可以是 缓存文件 也可是资源id 所以该Object 类型
     */
    public Object mUrl;
    /**
     * 占位图片 默认图片地址
     */
    public int defaultImageResId;
    /**
     * 是否显示圆形
     */
    public boolean isCircle;
    /**
     * 是否显示灰度图片
     */
    public boolean isGray;
    /**
     * 手动指定图片宽
     */
    public int imageWidth;
    /**
     * 手动指定图片高
     */
    public int imageHeight;

    private boolean isGif;


    /**
     * 构造函数
     * @param builder 参数构建类
     */
    public ImageLoadConfiguration(Builder builder){
        mContext=builder.mContext;
        mImageView=builder.mImageView;
        mUrl=builder.mUrl;
        defaultImageResId=builder.defaultImageResId;
        isCircle=builder.isCircle;
        isGray=builder.isGray;
        imageWidth=builder.imageWidth;
        imageHeight=builder.imageHeight;
        isGif=builder.isGif;
    }

    /**
     * 参数构建类
     */
    public static class Builder{
        private Context mContext;
        private ImageView mImageView;
        private Object mUrl;
        private int defaultImageResId;
        private boolean isCircle;
        private boolean isGray;
        private int imageWidth;
        private int imageHeight;
        private boolean isGif;


        public Builder(Context context) {
            mContext = context;
        }

        public Builder isGif(boolean isGif) {
            this.isGif = isGif;
            return this;
        }

        /**
         * 设置显示图片的地址
         * @param url 图片地址
         * @return
         */
        public Builder url(Object url) {
            mUrl = url;
            return this;
        }

//        public Builder url(int resId) {
//            mUrl = url;
//            return this;
//        }


        /**
         * 设置占位图片 默认显示的图片
         * @param defaultImageResId
         * @return
         */
        public Builder defaultImageResId(int defaultImageResId) {
            this.defaultImageResId = defaultImageResId;
            return this;
        }

        public Builder isCircle( boolean isCircle) {
            this.isCircle = isCircle;
            return this;
        }

        public Builder isGray(boolean isGray) {
            this.isGray = isGray;
            return this;
        }

        public Builder imageWidth(int imageWidth) {
            this.imageWidth = imageWidth;
            return this;
        }

        public Builder imageHeight(int imageHeight) {
            this.imageHeight = imageHeight;
            return this;
        }

        public Builder imageView(ImageView imageView) {
            mImageView = imageView;
            return this;
        }

        public ImageLoadConfiguration build(){
            return new ImageLoadConfiguration(this);
        }
    }
}
