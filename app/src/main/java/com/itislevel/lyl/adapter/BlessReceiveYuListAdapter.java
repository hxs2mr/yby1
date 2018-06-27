package com.itislevel.lyl.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.itislevel.lyl.R;
import com.itislevel.lyl.mvp.model.bean.BlessListBean;
import com.itislevel.lyl.mvp.model.bean.BlessReceiveYuBean;

/**
 * desc:功能说明必写
 * user:itisi
 * date:2017/12/21.19:19
 * path:com.itislevel.lyl.adapter.TeamTypeAdapter
 **/
public class BlessReceiveYuListAdapter extends BaseQuickAdapter<BlessReceiveYuBean.ListBean, BaseViewHolder> {

    public BlessReceiveYuListAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, BlessReceiveYuBean.ListBean item) {

        helper.setText(R.id.tv_name,item.getNickname());
        helper.setText(R.id.tv_content,item.getWishes());
    }
}
