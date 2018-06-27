package com.itislevel.lyl.adapter;

import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.itislevel.lyl.R;
import com.itislevel.lyl.app.App;
import com.itislevel.lyl.app.Constants;
import com.itislevel.lyl.mvp.model.bean.SpecialOrderDetailBean;
import com.itislevel.lyl.mvp.model.bean.SpecialReturnBean;
import com.itislevel.lyl.utils.imageload.ImageLoadConfiguration;
import com.itislevel.lyl.utils.imageload.ImageLoadProxy;

/**
 * desc:功能说明必写
 * user:itisi
 * date:2017/12/5.14:29
 * path:com.itislevel.lyl.adapter.ProvinceAdapter
 **/
public class SpecialOrderReturnAdapter extends BaseQuickAdapter<SpecialReturnBean.ListBean,
        BaseViewHolder> {

    public SpecialOrderReturnAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, SpecialReturnBean.ListBean item) {


        View tv_contcat = helper.getView(R.id.tv_contcat);
        if (tv_contcat != null) {
            helper.addOnClickListener(R.id.tv_contcat);
        }

        View tv_refund = helper.getView(R.id.tv_refund);
        if (tv_refund != null) {
            helper.addOnClickListener(R.id.tv_refund);
        }

        View tv_confirm = helper.getView(R.id.tv_confirm);
        if (tv_confirm != null) {
            helper.addOnClickListener(R.id.tv_confirm);
        }

        View tv_cancel = helper.getView(R.id.tv_cancel);
        if (tv_cancel != null) {
            helper.addOnClickListener(R.id.tv_cancel);
        }

        View tv_go_pay = helper.getView(R.id.tv_go_pay);
        if (tv_go_pay != null) {
            helper.addOnClickListener(R.id.tv_go_pay);
        }

        View tv_look_detail = helper.getView(R.id.tv_look_detail);
        if (tv_look_detail != null) {
            helper.addOnClickListener(R.id.tv_look_detail);
        }
        View tv_service = helper.getView(R.id.tv_service);
        if (tv_service != null) {
            helper.addOnClickListener(R.id.tv_service);
        }

        helper.setText(R.id.tv_goodsname, item.getGoodsname());
        helper.setText(R.id.tv_price, "￥" + item.getPrice());
        helper.setText(R.id.tv_count, "x" + item.getCount());
        helper.setText(R.id.tv_goods_desc, "共" + item.getCount() + "件商品 合计" + (item.getCount() *
                Double.parseDouble(item.getPrice())));

        ImageLoadProxy.getInstance()
                .load(new ImageLoadConfiguration.Builder(App.getInstance())
                        .defaultImageResId(R.mipmap.icon_img_load_pre)
                        .url(Constants.IMG_SERVER_PATH + item.getLogourl())
                        .imageView((ImageView) helper.getView(R.id.iv_logo)).build());

    }
}
