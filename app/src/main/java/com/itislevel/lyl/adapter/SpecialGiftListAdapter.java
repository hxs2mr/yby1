package com.itislevel.lyl.adapter;

import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.itislevel.lyl.R;
import com.itislevel.lyl.app.App;
import com.itislevel.lyl.app.Constants;
import com.itislevel.lyl.mvp.model.bean.SpecialGiftListBean;
import com.itislevel.lyl.mvp.model.bean.SpecialTypeBean;
import com.itislevel.lyl.utils.imageload.ImageLoadConfiguration;
import com.itislevel.lyl.utils.imageload.ImageLoadProxy;

/**
 * desc:功能说明必写
 * user:itisi
 * date:2017/12/21.19:19
 * path:com.itislevel.lyl.adapter.TeamTypeAdapter
 **/
public class SpecialGiftListAdapter extends BaseQuickAdapter<SpecialGiftListBean.ListBean,
        BaseViewHolder> {
    public SpecialGiftListAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, SpecialGiftListBean.ListBean item) {
//        //      tv_price iv_logo tv_name

        helper.addOnClickListener(R.id.tv_name).addOnClickListener(R.id.tv_price).addOnClickListener(R.id.iv_logo);

        ImageLoadProxy.getInstance()
                .load(new ImageLoadConfiguration.Builder(App.getInstance())
                        .defaultImageResId(R.mipmap.icon_img_load_pre)
                        .url(Constants.IMG_SERVER_PATH + item.getLogourl())
                        .imageView((ImageView) helper.getView(R.id.iv_logo)).build());


        helper.setText(R.id.tv_name, item.getGoodsname());
        helper.setText(R.id.tv_price, "￥" + item.getSellerprice());

    }
}
