package com.itislevel.lyl.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.itislevel.lyl.R;
import com.itislevel.lyl.mvp.model.bean.AddressBean;
import com.itislevel.lyl.mvp.model.bean.ComSearchBean;

import static com.itislevel.lyl.utils.DateUtil.timeSpanToDate;

/**
 * desc:功能说明必写
 * user:itisi
 * date:2017/12/5.14:29
 * path:com.itislevel.lyl.adapter.ProvinceAdapter
 **/
public class ComplinHorstAdapter extends BaseQuickAdapter<ComSearchBean.ListBean, BaseViewHolder> {

    public ComplinHorstAdapter(int layoutResId) {
        super(layoutResId);
    }
    @Override
    protected void convert(BaseViewHolder helper, ComSearchBean.ListBean item) {
        helper.setText(R.id.toushu_name,item.getContent());
        helper.setText(R.id.toushu_time,timeSpanToDate(item.getCreatedtime()));
    }
}
