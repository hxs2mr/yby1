package com.itislevel.lyl.adapter;

import android.app.Activity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.itislevel.lyl.R;
import com.itislevel.lyl.app.Constants;
import com.itislevel.lyl.mvp.model.bean.PropertyBillBean;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.itislevel.lyl.mvp.ui.property.bill.PropertyBillActivity.HOME_LOAATION;
import static com.itislevel.lyl.mvp.ui.property.homelist.PropertyHomeListActivity.p_home_image_url;

/**
 * desc:功能说明必写
 * user:itisi
 * date:2017/12/5.14:29
 * path:com.itislevel.lyl.adapter.ProvinceAdapter
 **/
public class PropretyBillAdapter extends BaseQuickAdapter<PropertyBillBean,
        BaseViewHolder> {
    Activity mActivity;

    public PropretyBillAdapter(int layoutResId, Activity activity) {
        super(layoutResId);
        this.mActivity = activity;

    }
    @Override
    protected void convert(BaseViewHolder helper,PropertyBillBean item) {
        helper.addOnClickListener(R.id.checkbox);
        helper.addOnClickListener(R.id.bill_select_time);
        if(item.getType()==1)
        {
            helper.setText(R.id.type_name,"物业费");
            helper.setBackgroundRes(R.id.type_image,R.mipmap.proper_bill_w);
        }else {
            helper.setText(R.id.type_name,"车位费");
            helper.setBackgroundRes(R.id.type_image,R.mipmap.proper_bill_c);
        }
        helper.setText(R.id.bill_name,item.getUsername());
        helper.setText(R.id.bill_phone,item.getPhone());
        helper.setText(R.id.bill_address,HOME_LOAATION);
        helper.setText(R.id.bill_monkey,item.getPayfee());
        helper.setText(R.id.bill_date,item.getPayfeefinishtime());
        helper.setText(R.id.bill_select_time,item.getPaytype()+"个月付");
    }

}
