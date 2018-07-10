package com.itislevel.lyl.adapter;

import android.graphics.Color;
import android.support.v7.widget.AppCompatTextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.itislevel.lyl.R;
import com.itislevel.lyl.mvp.model.bean.FanRecodeBean;

import static com.itislevel.lyl.utils.DateUtil.timeSpanToDate;

/**
 * desc:返现列表的adapter
 * user:itisi
 * date:2017/12/12.17:14
 * path:com.itislevel.lyl.adapter.FamilyListAdapter
 **/
public class ChongJiluAdapter extends BaseQuickAdapter<FanRecodeBean.ListBean,
        BaseViewHolder> {
    public ChongJiluAdapter(int layoutResId) {
        super(layoutResId);
    }
    @Override
    protected void convert(BaseViewHolder helper,FanRecodeBean.ListBean item) {
        AppCompatTextView shan_chon = helper.getView(R.id.shan_chon);
        AppCompatTextView shan_chon_monkey = helper.getView(R.id.shan_chon_monkey);

        helper.setText(R.id.shan_chon_time, timeSpanToDate(item.getPayfeetime()));
        helper.setText(R.id.shan_chon_monkey, "+ "+item.getPayfee());
        if(item.getTrademode().equals("1"))
        {
            shan_chon.setTextColor(Color.parseColor("#ff9100"));
            shan_chon_monkey.setTextColor(Color.parseColor("#ff9100"));
        }else {
            shan_chon.setText("线上");
        }
    }


}
