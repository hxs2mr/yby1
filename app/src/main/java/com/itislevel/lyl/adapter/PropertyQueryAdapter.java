package com.itislevel.lyl.adapter;

import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.itislevel.lyl.R;
import com.itislevel.lyl.app.App;
import com.itislevel.lyl.app.Constants;
import com.itislevel.lyl.mvp.model.bean.BlessCartListBean;
import com.itislevel.lyl.mvp.model.bean.PropertyQueryInfoBean;
import com.itislevel.lyl.utils.DateUtil;
import com.itislevel.lyl.utils.imageload.ImageLoadConfiguration;
import com.itislevel.lyl.utils.imageload.ImageLoadProxy;

import java.util.Date;

/**
 * desc:功能说明必写
 * user:itisi
 * date:2017/12/21.19:19
 * path:com.itislevel.lyl.adapter.TeamTypeAdapter
 **/
public class PropertyQueryAdapter extends BaseQuickAdapter<PropertyQueryInfoBean.EstateinfoBean
        .PayBilllistBean,
        BaseViewHolder> {

    public PropertyQueryAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, PropertyQueryInfoBean.EstateinfoBean
            .PayBilllistBean item) {
        //    tv_price tv_time  tv_type iv_select

        helper.addOnClickListener(R.id.tv_price);
        helper.addOnClickListener(R.id.tv_time);
        helper.addOnClickListener(R.id.tv_type);
        helper.addOnClickListener(R.id.iv_select);
        helper.addOnClickListener(R.id.iv_setting);


        helper.setText(R.id.tv_price, "￥" + item.getPayfee());
        helper.setText(R.id.tv_time, DateUtil.timeSpanToDateYearMonthDAY(item.getPayfeefinishtime()));

        // helper.setText(R.id.tv_type, item.getCount()+"");
        switch (item.getPaytype()) {
            case 1:
                helper.setText(R.id.tv_type, "1个月");
                break;
            case 2:
                helper.setText(R.id.tv_type, "2个月");
                break;
            case 3:
                helper.setText(R.id.tv_type, "3个月");
                break;
            case 4:
                helper.setText(R.id.tv_type, "4个月");
                break;
            case 5:
                helper.setText(R.id.tv_type, "5个月");
                break;
            case 6:
                helper.setText(R.id.tv_type, "6个月");
                break;
            case 7:
                helper.setText(R.id.tv_type, "7个月");
                break;
            case 8:
                helper.setText(R.id.tv_type, "8个月");
                break;
            case 9:
                helper.setText(R.id.tv_type, "9个月");
                break;
            case 10:
                helper.setText(R.id.tv_type, "10个月");
                break;
            case 11:
                helper.setText(R.id.tv_type, "11个月");
                break;
            case 12:
                helper.setText(R.id.tv_type, "12个月");
                break;
        }

        if (item.getType()==1){
            helper.setText(R.id.tv_pay_type, "物业费");

        }else{
            helper.setText(R.id.tv_pay_type, "停车费");
        }


        if (item.isSelected()) {
            ImageLoadProxy.getInstance()
                    .load(new ImageLoadConfiguration.Builder(App.getInstance())
                            .url(R.mipmap.icon_radio_select)
                            .imageView((ImageView) helper.getView(R.id.iv_select)).build());

        } else {
            ImageLoadProxy.getInstance()
                    .load(new ImageLoadConfiguration.Builder(App.getInstance())
                            .url(R.mipmap.icon_radio_normal)
                            .imageView((ImageView) helper.getView(R.id.iv_select)).build());
        }


    }
}
