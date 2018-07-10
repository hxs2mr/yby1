package com.itislevel.lyl.adapter;

import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.itislevel.lyl.R;
import com.itislevel.lyl.app.App;
import com.itislevel.lyl.app.Constants;
import com.itislevel.lyl.mvp.model.bean.FamilyBlessListRecevieBean;
import com.itislevel.lyl.mvp.model.bean.FamilySacrificeTypeBean;
import com.itislevel.lyl.utils.imageload.ImageLoadConfiguration;
import com.itislevel.lyl.utils.imageload.ImageLoadProxy;

/**
 * desc:功能说明必写
 * user:itisi
 * date:2017/12/12.17:14
 * path:com.itislevel.lyl.adapter.FamilyListAdapter
 **/
public class FamilyCateAdapter extends BaseQuickAdapter<FamilySacrificeTypeBean.ListBean,
        BaseViewHolder> {
    public FamilyCateAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, FamilySacrificeTypeBean.ListBean item) {
        //          iv_icon tv_name

        helper.addOnClickListener(R.id.tv_name).addOnClickListener(R.id.iv_icon);

        helper.setText(R.id.tv_name,item.getGoodsname());
        ImageLoadProxy.getInstance()
                .load(new ImageLoadConfiguration.Builder(App.getInstance())
                        .defaultImageResId(R.mipmap.icon_img_load_pre)
                        .url(Constants.IMG_SERVER_PATH+item.getIcon())
                        .imageView((ImageView) helper.getView(R.id.iv_icon)).build());

    }


}
