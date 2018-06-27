package com.itislevel.lyl.utils.imageload.load;


import com.itislevel.lyl.utils.imageload.ImageLoadConfiguration;
import com.itislevel.lyl.utils.imageload.manager.GlideManager;

/**
 * author: itisi---
 * created by Administrator on 2017/3/22.
 * desc:
 */
public class GlideImageLoad implements ImageLoad {
    @Override
    public void load(ImageLoadConfiguration imageLoadConfiguration) {
        GlideManager.load(imageLoadConfiguration);
    }
}
