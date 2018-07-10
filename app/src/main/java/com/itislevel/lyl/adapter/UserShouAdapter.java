package com.itislevel.lyl.adapter;

import android.graphics.Color;
import android.support.v7.widget.AppCompatTextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.itislevel.lyl.R;
import com.itislevel.lyl.mvp.model.bean.UserHistoryBean;

/**
 * desc:返现列表的adapter
 * user:itisi
 * date:2017/12/12.17:14
 * path:com.itislevel.lyl.adapter.FamilyListAdapter
 **/
public class UserShouAdapter extends BaseQuickAdapter<String,
        BaseViewHolder> {
    public UserShouAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper,String item) {
/*
        helper.setText(R.id.shan_jiaoyi_time, item.getCashbacklastdate());
        helper.setText(R.id.shan_jiaoyi_number, "交易号:"+item.getTradenum());
        helper.setText(R.id.shan_jiaoyi_monkey, "+ "+item.getPerperiodlimit());
        AppCompatTextView shan_jiaoyi_type = helper.getView(R.id.shan_jiaoyi_type);
        if(item.getStatus().equals("2"))
        {
            shan_jiaoyi_type.setText("已领取");
        }else  {
            shan_jiaoyi_type.setText("未领取");
            shan_jiaoyi_type.setTextColor(Color.parseColor("#68c5b0"));
        }*/
    }


}
