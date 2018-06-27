package com.itislevel.lyl.adapter;

import android.app.Activity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.itislevel.lyl.R;
import com.itislevel.lyl.app.Constants;
import com.itislevel.lyl.mvp.model.bean.VillageNameBean;

/**
 * desc:功能说明必写
 * user:itisi
 * date:2017/12/5.14:29
 * path:com.itislevel.lyl.adapter.ProvinceAdapter
 **/
public class SelectDanAdapter extends BaseQuickAdapter<LiveAddressBean, BaseViewHolder> {
    private  Activity mActivity;
    public SelectDanAdapter(int layoutResId, Activity activity) {
        super(layoutResId);
        openLoadAnimation(BaseQuickAdapter.ALPHAIN);
        this.mActivity = activity;
    }
    @Override
    protected void convert(BaseViewHolder helper, LiveAddressBean item) {
        helper.setText(R.id.tv_content,item.getLiveaddress());
    }
}
