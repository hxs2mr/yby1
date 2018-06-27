package com.itislevel.lyl.adapter;

import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.itislevel.lyl.R;
import com.itislevel.lyl.app.App;
import com.itislevel.lyl.app.Constants;
import com.itislevel.lyl.mvp.model.bean.MeiZiMultipleBean;
import com.itislevel.lyl.mvp.model.bean.SpecialOrderDetailBean;
import com.itislevel.lyl.utils.imageload.ImageLoadConfiguration;
import com.itislevel.lyl.utils.imageload.ImageLoadProxy;

import java.util.List;

/**
 * desc:功能说明必写
 * user:itisi
 * date:2017/12/5.14:29
 * path:com.itislevel.lyl.adapter.ProvinceAdapter
 **/
public class SpecialOrderAllAdapter extends BaseMultiItemQuickAdapter<SpecialOrderDetailBean
        .ListBean, BaseViewHolder> {

    /**
     * //订单状态
     * public static final int SPECIAL_ORDER_ALL = -1;//所有订单
     * public static final int SPECIAL_ORDER_WAITNG_PAY = 101;//待付款
     *
     * public static final int SPECIAL_ORDER_PAYED = 201;//已付款
     *
     * public static final int SPECIAL_ORDER_SENDED = 202;//已发货-待收货
     *
     * public static final int SPECIAL_ORDER_RECEIVED = 203;//已收货
     * public static final int SPECIAL_ORDER_COMPLETE = 204;//已完成
     *
     * public static final int SPECIAL_ORDER_REFUNDED = 301;//已退款
     * <p>
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public SpecialOrderAllAdapter(List<SpecialOrderDetailBean.ListBean> data) {
        super(data);
        for (SpecialOrderDetailBean.ListBean item : data) {
            int status = Integer.parseInt(item.getStatus());
            item.setItemType(status);
            switch (status) {
                case Constants.SPECIAL_ORDER_WAITNG_PAY: //未付款 101
                    addItemType(status, R.layout.item_order_nopay);
                    break;
                case Constants.SPECIAL_ORDER_PAYED://已付款 未发货 201
                    addItemType(status, R.layout.item_order_nosend);
                    break;
                case Constants.SPECIAL_ORDER_SENDED://已发货 代收款 202
                    addItemType(status, R.layout.item_order_watingreceive);
                    break;
                case Constants.SPECIAL_ORDER_RECEIVED://已收获 已完成 203
                case Constants.SPECIAL_ORDER_COMPLETE:// 已完成 204
                    addItemType(status, R.layout.item_order_complete);
                    break;
                case Constants.SPECIAL_ORDER_REFUNDED://退货退款 301
                    addItemType(status, R.layout.item_order_refund);
                    break;

            }
        }

    }

    @Override
    protected void convert(BaseViewHolder helper, SpecialOrderDetailBean.ListBean item) {

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



        int status = Integer.parseInt(item.getStatus());

        helper.setText(R.id.tv_goodsname, item.getGoodsname());
        helper.setText(R.id.tv_price, "￥" + item.getPrice());
        helper.setText(R.id.tv_count, "x" + item.getCount());
        helper.setText(R.id.tv_goods_desc, "共" + item.getCount() + "件商品 合计" + (item.getCount() *
                Double.parseDouble(item.getPrice())));

        ImageLoadProxy.getInstance()
                .load(new ImageLoadConfiguration.Builder(App.getInstance())
                        .defaultImageResId(R.mipmap.icon_img_load_pre)
                        .url(Constants.IMG_SERVER_PATH + item.getImgurl())
                        .imageView((ImageView) helper.getView(R.id.iv_logo)).build());

    }
}
