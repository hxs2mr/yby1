package com.itislevel.lyl.adapter;

import android.support.annotation.LayoutRes;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.itislevel.lyl.R;
import com.itislevel.lyl.app.App;
import com.itislevel.lyl.mvp.model.bean.MeiZiBean;
import com.lzy.ninegrid.ImageInfo;
import com.lzy.ninegrid.NineGridView;
import com.lzy.ninegrid.preview.NineGridViewClickAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class MyMessageAdapter extends BaseQuickAdapter<MeiZiBean, BaseViewHolder> {


    public MyMessageAdapter(@LayoutRes int layoutResId) {
        super(layoutResId);
    }


    @Override
    protected void convert(BaseViewHolder helper, final MeiZiBean item) {

    }

}
