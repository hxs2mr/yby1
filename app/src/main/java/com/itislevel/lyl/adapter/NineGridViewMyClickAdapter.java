package com.itislevel.lyl.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.lzy.ninegrid.ImageInfo;
import com.lzy.ninegrid.NineGridView;
import com.lzy.ninegrid.NineGridViewAdapter;
import com.lzy.ninegrid.preview.ImagePreviewActivity;

import java.io.Serializable;
import java.util.List;

/**
 * desc:功能说明必写
 * user:itisi
 * date:2017/12/23.15:21
 * path:com.itislevel.lyl.adapter.NineGridViewMyClickAdapter
 **/
public class NineGridViewMyClickAdapter extends NineGridViewAdapter {
    public static int TYPE_ADD_BLESS=1000;
    public static int TYPE_ADD_FUNSHAR=2000;

    private int statusHeight;

    private int currentType=TYPE_ADD_BLESS;

    public NineGridViewMyClickAdapter(Context context, List<ImageInfo> imageInfo,int type) {
        super(context, imageInfo);
        this.currentType=type;
        statusHeight = getStatusHeight(context);
    }

    @Override
    protected void onImageItemClick(Context context, NineGridView nineGridView, int index,
                                    List<ImageInfo> imageInfo) {
        if (index==imageInfo.size()-1){
            System.out.println("是最后一个");

        }else{
            System.out.println("不是最后一个");
        }
//
//        for (int i = 0; i < imageInfo.size(); i++) {
//            ImageInfo info = imageInfo.get(i);
//            View imageView;
//            if (i < nineGridView.getMaxSize()) {
//                imageView = nineGridView.getChildAt(i);
//            } else {
//                //如果图片的数量大于显示的数量，则超过部分的返回动画统一退回到最后一个图片的位置
//                imageView = nineGridView.getChildAt(nineGridView.getMaxSize() - 1);
//            }
//            info.imageViewWidth = imageView.getWidth();
//            info.imageViewHeight = imageView.getHeight();
//            int[] points = new int[2];
//            imageView.getLocationInWindow(points);
//            info.imageViewX = points[0];
//            info.imageViewY = points[1] - statusHeight;
//        }
//
//        Intent intent = new Intent(context, ImagePreviewActivity.class);
//        Bundle bundle = new Bundle();
//        bundle.putSerializable(ImagePreviewActivity.IMAGE_INFO, (Serializable) imageInfo);
//        bundle.putInt(ImagePreviewActivity.CURRENT_ITEM, index);
//        intent.putExtras(bundle);
//        context.startActivity(intent);
//        ((Activity) context).overridePendingTransition(0, 0);
    }

    /**
     * 获得状态栏的高度
     */
    public int getStatusHeight(Context context) {
        int statusHeight = -1;
        try {
            Class<?> clazz = Class.forName("com.android.internal.R$dimen");
            Object object = clazz.newInstance();
            int height = Integer.parseInt(clazz.getField("status_bar_height").get(object)
                    .toString());
            statusHeight = context.getResources().getDimensionPixelSize(height);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return statusHeight;
    }

}
