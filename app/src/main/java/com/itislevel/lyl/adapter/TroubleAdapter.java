package com.itislevel.lyl.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.itislevel.lyl.R;
import com.itislevel.lyl.mvp.model.bean.AddressBean;

/**
 * desc:功能说明必写
 * user:itisi
 * date:2017/12/5.14:29
 * path:com.itislevel.lyl.adapter.ProvinceAdapter
 **/
public class TroubleAdapter extends BaseQuickAdapter<AddressBean, BaseViewHolder> {

    public TroubleAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, AddressBean item) {
        helper.setText(R.id.tv_name,item.getName());
    }
}
