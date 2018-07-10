package com.itislevel.lyl.adapter;

import android.support.v7.widget.AppCompatTextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.itislevel.lyl.R;
import com.itislevel.lyl.mvp.model.bean.FanXianBean;

/**
 * desc:返现列表的adapter
 * user:itisi
 * date:2017/12/12.17:14
 * path:com.itislevel.lyl.adapter.FamilyListAdapter
 **/
public class FanxianJiluAdapter extends BaseQuickAdapter<FanXianBean.ListBean,
        BaseViewHolder> {
    public FanxianJiluAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper,FanXianBean.ListBean item) {

        helper.setText(R.id.shan_jiaoyi_number, "交易号:"+item.getTradenum());
        helper.setText(R.id.shan_jiaoyi_time, item.getCashbackdate());
        helper.setText(R.id.shan_jiaoyi_monkey, "- "+item.getPerperiodlimit());
        AppCompatTextView shan_jiaoyi_type = helper.getView(R.id.shan_jiaoyi_type);
        if(item.getStatus().equals("0"))
        {
            shan_jiaoyi_type.setText("待返现");
        }else      if(item.getStatus().equals("1")){
            shan_jiaoyi_type.setText("待领取");
        }else      if(item.getStatus().equals("2")){
            shan_jiaoyi_type.setText("未领取");
        }else      if(item.getStatus().equals("3")){
            shan_jiaoyi_type.setText("已领取");
        }
    }


}
