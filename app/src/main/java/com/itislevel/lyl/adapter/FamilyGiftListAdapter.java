package com.itislevel.lyl.adapter;

import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.itislevel.lyl.R;
import com.itislevel.lyl.app.App;
import com.itislevel.lyl.app.Constants;
import com.itislevel.lyl.mvp.model.bean.FamilyBlessListRecevieBean;
import com.itislevel.lyl.mvp.model.bean.FamilyGiftListBean;
import com.itislevel.lyl.utils.imageload.ImageLoadConfiguration;
import com.itislevel.lyl.utils.imageload.ImageLoadProxy;

/**
 * desc:功能说明必写
 * user:itisi
 * date:2017/12/12.17:14
 * path:com.itislevel.lyl.adapter.FamilyListAdapter
 **/
public class FamilyGiftListAdapter extends BaseQuickAdapter<FamilyGiftListBean.ListBean,
        BaseViewHolder> {
    public FamilyGiftListAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, FamilyGiftListBean.ListBean item) {
        //  iv_gift tv_name tv_price
        helper.setText(R.id.tv_name,item.getSacrifname());
        helper.setText(R.id.tv_price,"￥ "+item.getSellprice());
                ImageLoadProxy.getInstance()
                .load(new ImageLoadConfiguration.Builder(App.getInstance())
                        .defaultImageResId(R.mipmap.icon_img_load_pre)
                        .url(Constants.IMG_SERVER_PATH+item.getImgurl())

                        .imageView((ImageView) helper.getView(R.id.iv_gift)).build());

    }


}
