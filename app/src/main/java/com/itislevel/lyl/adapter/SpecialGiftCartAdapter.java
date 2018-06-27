package com.itislevel.lyl.adapter;

import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.itislevel.lyl.R;
import com.itislevel.lyl.app.App;
import com.itislevel.lyl.app.Constants;
import com.itislevel.lyl.mvp.model.bean.BlessCartListBean;
import com.itislevel.lyl.mvp.model.bean.FunsharingMyBean;
import com.itislevel.lyl.utils.DateUtil;
import com.itislevel.lyl.utils.imageload.ImageLoadConfiguration;
import com.itislevel.lyl.utils.imageload.ImageLoadProxy;

/**
 * desc:功能说明必写
 * user:itisi
 * date:2017/12/5.14:29
 * path:com.itislevel.lyl.adapter.ProvinceAdapter
 **/
public class SpecialGiftCartAdapter extends BaseQuickAdapter<BlessCartListBean.ShopcartlistBean,
        BaseViewHolder> {

    public SpecialGiftCartAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, BlessCartListBean.ShopcartlistBean item) {

//        tv_del iv_select
//        iv_jian tv_count iv_add

        helper.addOnClickListener(R.id.tv_del);
        helper.addOnClickListener(R.id.iv_select);
        helper.addOnClickListener(R.id.iv_logo);
        helper.addOnClickListener(R.id.tv_goodsname);
        helper.addOnClickListener(R.id.iv_jian);
        helper.addOnClickListener(R.id.iv_add);
        helper.addOnClickListener(R.id.tv_count);
        helper.addOnClickListener(R.id.tv_price);


        helper.setText(R.id.tv_goodsname, item.getGoodsname());
        helper.setText(R.id.tv_price,"￥"+ item.getPrice());
        helper.setText(R.id.tv_count, item.getCount()+"");

        ImageLoadProxy.getInstance()
                .load(new ImageLoadConfiguration.Builder(App.getInstance())
                        .defaultImageResId(R.mipmap.icon_img_load_pre)
                        .url(Constants.IMG_SERVER_PATH+item.getImgurl())
                        .imageView((ImageView) helper.getView(R.id.iv_logo)).build());


        if (item.isSelect()) {
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
