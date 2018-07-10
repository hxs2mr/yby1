package com.itislevel.lyl.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.itislevel.lyl.R;
import com.itislevel.lyl.mvp.model.bean.UserFanBean;

/**
 * desc:返现列表的adapter
 * user:itisi
 * date:2017/12/12.17:14
 * path:com.itislevel.lyl.adapter.FamilyListAdapter
 **/
public class UserFanListAdapter extends BaseQuickAdapter<UserFanBean.ListBean,
        BaseViewHolder> {
    public UserFanListAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper,UserFanBean.ListBean item) {

        helper.addOnClickListener(R.id.user_lin_button);

        helper.setText(R.id.shop_name, item.getGoodsname());
        helper.setText(R.id.shop_jiaoyi, "交易号:"+item.getTradenum() );
        helper.setText(R.id.shop_monkey, "¥"+item.getPerperiodlimit());

        String time_data=  item.getCashbacklastdate().substring(0,7);
        helper.setText(R.id.shop_time, "第"+time_data+"期" );

    }


}
