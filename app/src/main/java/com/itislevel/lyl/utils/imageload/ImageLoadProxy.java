package com.itislevel.lyl.utils.imageload;


import com.itislevel.lyl.utils.imageload.load.GlideImageLoad;
import com.itislevel.lyl.utils.imageload.load.ImageLoad;

/**
 * author: itisi---
 * created by Administrator on 2017/3/22.
 * desc: 图片代理类 单例
 */
public class ImageLoadProxy {
    private static ImageLoadProxy instance;
    private ImageLoad mImageLoad;

    private ImageLoadProxy(){
        mImageLoad=new GlideImageLoad();
    }
    public static ImageLoadProxy getInstance(){
        if (instance==null){
            synchronized (ImageLoadProxy.class){
                if (instance==null){
                    instance=new ImageLoadProxy();
                }
            }
        }
        return instance;
    }

    /**
     *
     * @param imageLoadCfg
     */
    public void load(ImageLoadConfiguration imageLoadCfg){
        mImageLoad.load(imageLoadCfg);
    }
}
