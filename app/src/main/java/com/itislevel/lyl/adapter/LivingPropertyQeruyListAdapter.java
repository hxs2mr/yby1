package com.itislevel.lyl.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.itislevel.lyl.R;
import com.itislevel.lyl.mvp.model.bean.FunsharingMyBean;
import com.itislevel.lyl.mvp.model.bean.PropertyQueryInfo;
import com.itislevel.lyl.utils.DateUtil;

/**
 * desc:功能说明必写
 * user:itisi
 * date:2018-02-03.11:07
 * path:com.itislevel.lyl.adapter.LivingPropertyQeruyListAdapter
 **/
public class LivingPropertyQeruyListAdapter extends BaseQuickAdapter<PropertyQueryInfo.OwnerplacelistBean,
        BaseViewHolder> {

    public LivingPropertyQeruyListAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, PropertyQueryInfo.OwnerplacelistBean item) {
        helper.addOnClickListener(R.id.tv_title);

        helper.setText(R.id.tv_title, item.getLiveaddress());

    }
}
