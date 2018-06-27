package com.itislevel.lyl.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.itislevel.lyl.R;
import com.itislevel.lyl.mvp.model.bean.AddressBean;
import com.itislevel.lyl.mvp.model.bean.HouseKeepBean;

/**
 * desc:功能说明必写
 * user:itisi
 * date:2017/12/5.14:29
 * path:com.itislevel.lyl.adapter.ProvinceAdapter
 **/
public class HouseKeepAdapter extends BaseQuickAdapter<HouseKeepBean.ListBean, BaseViewHolder> {

    public HouseKeepAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, HouseKeepBean.ListBean item) {
        helper.setText(R.id.tv_title,item.getCompanyname());
    }
}
