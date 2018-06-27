package com.itislevel.lyl.mvp.ui.main.dynamic;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.itislevel.lyl.adapter.recyclew.MultipleItemEntity;

import java.util.List;

/**
 * Created by Administrator on 2018\5\23 0023.
 *
 * 定义一个共用的基Adapter
 */

public abstract  class BaseDynamicAdapter<T,K extends BaseViewHolder> extends BaseQuickAdapter<T,K>{
    protected boolean scroll;
    public BaseDynamicAdapter(int layoutResId, @Nullable List<T> data) {
        super(layoutResId, data);
    }

    public BaseDynamicAdapter(@Nullable List<T> data) {
        super(data);
    }

    public BaseDynamicAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(K helper, T item) {

    }
    public void setScrolling(boolean scroll){
        this.scroll = scroll;
    }

    public boolean getScrolling(){
        return scroll;
    }

    }
