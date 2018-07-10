package com.itislevel.lyl.adapter;

import android.graphics.Color;
import android.support.v7.widget.AppCompatTextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.itislevel.lyl.R;
import com.itislevel.lyl.app.Constants;
import com.itislevel.lyl.mvp.model.bean.ShanHomeBean;
import com.itislevel.lyl.mvp.model.bean.UserPlanBean;
import com.itislevel.lyl.utils.SharedPreferencedUtils;

/**
 * desc:功能说明必写
 * user:itisi
 * date:2017/12/12.17:14
 * path:com.itislevel.lyl.adapter.FamilyListAdapter
 **/
public class PlanListAdapter extends BaseQuickAdapter<UserPlanBean.ListBean,
        BaseViewHolder> {
    public PlanListAdapter(int layoutResId) {
        super(layoutResId);
    }
    @Override
    protected void convert(BaseViewHolder helper,UserPlanBean.ListBean item) {
        helper.addOnClickListener(R.id.look_button);
        helper.setText(R.id.shan_name, item.getShopname());
        helper.setText(R.id.shan_number, item.getTradenum());
        helper.setText(R.id.shan_qi_number, item.getPeriods() + "");
        helper.setText(R.id.shan_total_monkey, item.getPeriodlimit() + "");
        helper.setText(R.id.shan_qi_monkey, item.getPerperiodlimit() + "");
        helper.setText(R.id.shan_qi_time, item.getPeriodstarttime() + "至" + item.getPeriodendtime());

        AppCompatTextView shan_type = helper.getView(R.id.shan_type);   //分期的状态
        AppCompatTextView shan_shengyu = helper.getView(R.id.shan_shengyu); //剩余多少期未领取
         if(item.getStatus().equals("0"))
         {
             shan_type.setText("返现中");
         }else {
             shan_type.setText("也结束");
             shan_type.setTextColor(Color.parseColor("#cccccc"));
         }
    }
}
