package com.itislevel.lyl.adapter;

import android.app.Activity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.itislevel.lyl.R;
import com.itislevel.lyl.app.Constants;
import com.itislevel.lyl.mvp.model.bean.AddressBean;
import com.itislevel.lyl.mvp.model.bean.PropertLoginBean;
import com.itislevel.lyl.mvp.model.bean.VillageNameBean;

/**
 * desc:功能说明必写
 * user:itisi
 * date:2017/12/5.14:29
 * path:com.itislevel.lyl.adapter.ProvinceAdapter
 **/
public class SelectQuAdapter extends BaseQuickAdapter<VillageNameBean, BaseViewHolder> {
    private  Activity mActivity;
    public SelectQuAdapter(int layoutResId, Activity activity) {
        super(layoutResId);
        openLoadAnimation(BaseQuickAdapter.ALPHAIN);
        this.mActivity = activity;
    }
    @Override
    protected void convert(BaseViewHolder helper, VillageNameBean item) {
        Glide.with(mActivity)
                .load(Constants.IMG_SERVER_PATH+item.getHeadurl())
                .asBitmap()
                .error(R.mipmap.person_head)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into((ImageView) helper.getView(R.id.qu_image));
        helper.setText(R.id.qu_name,item.getVillagename());
    }
}
