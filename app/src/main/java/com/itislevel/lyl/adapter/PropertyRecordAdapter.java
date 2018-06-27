package com.itislevel.lyl.adapter;

import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.itislevel.lyl.R;
import com.itislevel.lyl.app.App;
import com.itislevel.lyl.app.Constants;
import com.itislevel.lyl.mvp.model.bean.PropertyQueryInfoBean;
import com.itislevel.lyl.mvp.model.bean.PropertyRecordBean;
import com.itislevel.lyl.utils.DateUtil;
import com.itislevel.lyl.utils.StringUtil;
import com.itislevel.lyl.utils.imageload.ImageLoadConfiguration;
import com.itislevel.lyl.utils.imageload.ImageLoadProxy;

/**
 * desc:功能说明必写
 * user:itisi
 * date:2017/12/21.19:19
 * path:com.itislevel.lyl.adapter.TeamTypeAdapter
 **/
public class PropertyRecordAdapter extends BaseQuickAdapter<PropertyRecordBean.PageBeanBean.ListBean,
        BaseViewHolder> {

    public PropertyRecordAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, PropertyRecordBean.PageBeanBean.ListBean item) {
        //    iv_type tv_name_and_phone tv_time   tv_price tv_date

        helper.addOnClickListener(R.id.iv_type);
        helper.addOnClickListener(R.id.tv_price);
        helper.addOnClickListener(R.id.tv_date);
        helper.addOnClickListener(R.id.tv_name_and_phone);
        helper.addOnClickListener(R.id.tv_time);

        if (item.getType() == 1) {
            helper.setText(R.id.tv_name_and_phone, "物业费 " + StringUtil.maskPhone(item.getPhone()) +"  "+StringUtil.maskZhCN(item.getUsername()));
            ImageLoadProxy.getInstance()
                    .load(new ImageLoadConfiguration.Builder(App.getInstance())
                            .url(R.mipmap.icon_property_rcord)
                            .imageView((ImageView) helper.getView(R.id.iv_type)).build());
        } else {
            helper.setText(R.id.tv_name_and_phone, "停车费 " + StringUtil.maskPhone(item.getPhone()) +"  "+StringUtil.maskZhCN(item.getUsername()));
            ImageLoadProxy.getInstance()
                    .load(new ImageLoadConfiguration.Builder(App.getInstance())
                            .url(R.mipmap.icon_property_rcord)
                            .imageView((ImageView) helper.getView(R.id.iv_type)).build());
        }
// timeSpanToDateYearMonthGang
        helper.setText(R.id.tv_price, "￥"+item.getPayfee());
        helper.setText(R.id.tv_date,  DateUtil.timeSpanToDateYearMonthGang(item.getPayfeefinishtime()));
        helper.setText(R.id.tv_time, DateUtil.timeSpanToDateTiem(item.getPayfeefinishtime()));


    }
}
