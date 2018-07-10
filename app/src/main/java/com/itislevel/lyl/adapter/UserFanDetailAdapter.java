package com.itislevel.lyl.adapter;

import android.support.v7.widget.AppCompatTextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.itislevel.lyl.R;
import com.itislevel.lyl.mvp.model.bean.UserPlanDetailBean;

/**
 * desc:返现列表的adapter
 * user:itisi
 * date:2017/12/12.17:14
 * path:com.itislevel.lyl.adapter.FamilyListAdapter
 **/
public class UserFanDetailAdapter extends BaseQuickAdapter<UserPlanDetailBean.ListBean,
        BaseViewHolder> {
    public UserFanDetailAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper,UserPlanDetailBean.ListBean item) {

       helper.setText(R.id.qi_monkey, "¥"+item.getPerperiodlimit());
        AppCompatTextView qi_time = helper.getView(R.id.qi_time);
        AppCompatTextView qi_type = helper.getView(R.id.qi_type);
        qi_time.setText(item.getCashbacklastdate().substring(0,7));
        if(item.getStatus().equals("0"))
        {
            qi_type.setText("待返现");
        }else  if(item.getStatus().equals("1")){
            qi_type.setText("待领取");
        }else  if(item.getStatus().equals("2")){
            qi_type.setText("已领取");
        }else  if(item.getStatus().equals("3")){
            qi_type.setText("超期未领取");
        }
    }


}
