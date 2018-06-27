package com.itislevel.lyl.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.itislevel.lyl.R;
import com.itislevel.lyl.mvp.model.bean.AddressBean;
import com.itislevel.lyl.mvp.model.bean.TroubleAdviserBean;

/**
 * desc:功能说明必写
 * user:itisi
 * date:2017/12/19.14:30
 * path:com.itislevel.lyl.adapter.TroubleAdviserAdapter
 **/
public class TroubleAdviserAdapter extends BaseQuickAdapter<TroubleAdviserBean, BaseViewHolder> {
    public TroubleAdviserAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, TroubleAdviserBean item) {
        // tv_desc tv_go_advice

        helper.addOnClickListener(R.id.tv_desc);
        helper.addOnClickListener(R.id.tv_go_advice);

    }
}
