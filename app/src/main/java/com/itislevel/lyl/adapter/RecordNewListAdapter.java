package com.itislevel.lyl.adapter;

import android.app.Activity;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.itislevel.lyl.R;
import com.itislevel.lyl.mvp.model.bean.PayLuBean;
import com.itislevel.lyl.utils.SharedPreferencedUtils;

import static com.itislevel.lyl.utils.DateUtil.monthToDate;

/**
 * desc:功能说明必写
 * user:itisi
 * date:2017/12/12.17:14
 * path:com.itislevel.lyl.adapter.FamilyListAdapter
 **/
public class RecordNewListAdapter extends BaseQuickAdapter<PayLuBean.ListBean, BaseViewHolder> {
    public RecordNewListAdapter(int layoutResId, Activity activity) {
        super(layoutResId);
    }
    @Override
    protected void convert(BaseViewHolder helper,PayLuBean.ListBean item) {
            helper.addOnClickListener(R.id.onclick_lienar);
            AppCompatImageView record_image = helper.getView(R.id.record_image);
            String name =  SharedPreferencedUtils.getStr("villagename","");
            helper.setText(R.id.record_title,name+""+item.getLiveaddress());
                if(item.getTypes().equals("1"))
                {
                    helper.setText(R.id.record_type,"[物业缴费]");
                    record_image.setBackgroundResource(R.mipmap.fan_wu);
                }else if(item.getTypes().equals("2"))
                {
                    helper.setText(R.id.record_type,"[车位费]");
                    record_image.setBackgroundResource(R.mipmap.ce_wei);
                }else {
                    helper.setText(R.id.record_type,"[物业缴费]&[车位费]");
                    record_image.setBackgroundResource(R.mipmap.all_fei);
                }
                helper.setText(R.id.record_time,monthToDate(item.getPayfeetime()));
                helper.setText(R.id.record_monkey,"-"+item.getPaytotalfee());

        //   iv_photo tv_name tv_look_count tv_add_username
//        tv_receive_gitcount tv_time
    }
}
