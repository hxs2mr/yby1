package com.itislevel.lyl.adapter;

import android.app.Activity;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.itislevel.lyl.R;
import com.itislevel.lyl.mvp.model.bean.HistoricalBean;
import com.itislevel.lyl.mvp.model.bean.PropertyBillBean;
import com.itislevel.lyl.utils.SharedPreferencedUtils;

import static com.itislevel.lyl.mvp.ui.property.bill.PropertyBillActivity.HOME_LOAATION;
import static com.itislevel.lyl.mvp.ui.property.childfragment.historicalbill.HistoricalBillActivity.user_number_livaddress;

/**
 * desc:功能说明必写
 * user:itisi
 * date:2017/12/5.14:29
 * path:com.itislevel.lyl.adapter.ProvinceAdapter
 **/
public class PropretyHistoricalBillAdapter extends BaseQuickAdapter<HistoricalBean.ListBean,
        BaseViewHolder> {
    Activity mActivity;

    public PropretyHistoricalBillAdapter(int layoutResId, Activity activity) {
        super(layoutResId);
        this.mActivity = activity;
    }
    @Override
    protected void convert(BaseViewHolder helper,HistoricalBean.ListBean item) {
        if(item.getType()==1)
        {
            helper.setText(R.id.type_name,"物业费");
            helper.setBackgroundRes(R.id.type_image,R.mipmap.proper_bill_w);
        }else {
            helper.setText(R.id.type_name,"车位费");
            helper.setBackgroundRes(R.id.type_image,R.mipmap.proper_bill_c);
        }
        helper.setText(R.id.bill_danwei,"贵州友邦友网络科技有限公司");
        helper.setText(R.id.bill_name,item.getUsername());
        helper.setText(R.id.bill_phone,item.getPhone());
        helper.setText(R.id.bill_address,SharedPreferencedUtils.getStr("proprety_cityname","")+SharedPreferencedUtils.getStr("villagename","")+user_number_livaddress);
        helper.setText(R.id.bill_monkey,item.getPayfee());
        helper.setText(R.id.bill_monkey_type,item.getStatus()==1?"也缴费":"未缴费");
        helper.setText(R.id.bill_start_date,item.getPayfeebegintime());
        helper.setText(R.id.bill_end_date,item.getPayfeefinishtime());
        helper.setText(R.id.bill_select_time,item.getPaytype()+"个月付");
    }

}
