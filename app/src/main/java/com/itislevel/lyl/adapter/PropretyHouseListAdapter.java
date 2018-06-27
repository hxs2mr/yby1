package com.itislevel.lyl.adapter;

import android.app.Activity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.itislevel.lyl.R;
import com.itislevel.lyl.app.Constants;
import com.itislevel.lyl.mvp.model.bean.PropretyNoticeBean;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.itislevel.lyl.utils.DateUtil.timeSpanToDate;

/**
 * desc:功能说明必写
 * user:itisi
 * date:2017/12/5.14:29
 * path:com.itislevel.lyl.adapter.ProvinceAdapter
 **/
public class PropretyHouseListAdapter extends BaseQuickAdapter<LiveAddressBean,
        BaseViewHolder> {
    Activity mActivity;

    public PropretyHouseListAdapter(int layoutResId, Activity activity) {
        super(layoutResId);
        this.mActivity = activity;

    }
    @Override
    protected void convert(BaseViewHolder helper,LiveAddressBean item) {
        helper.setText(R.id.p_house_phone,item.getPhone());
        helper.setText(R.id.p_house_user,item.getUsername());
        helper.setText(R.id.p_house_addrss,item.getLiveaddress());
    }

}
