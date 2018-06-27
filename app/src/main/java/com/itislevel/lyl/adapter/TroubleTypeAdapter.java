package com.itislevel.lyl.adapter;

import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.itislevel.lyl.R;
import com.itislevel.lyl.app.App;
import com.itislevel.lyl.app.Constants;
import com.itislevel.lyl.mvp.model.bean.TeamTypeBean;
import com.itislevel.lyl.mvp.model.bean.TroubleTypeBean;
import com.itislevel.lyl.utils.imageload.ImageLoadConfiguration;
import com.itislevel.lyl.utils.imageload.ImageLoadProxy;

/**
 * desc:功能说明必写
 * user:itisi
 * date:2017/12/21.19:19
 * path:com.itislevel.lyl.adapter.TeamTypeAdapter
 **/
public class TroubleTypeAdapter extends BaseQuickAdapter<TroubleTypeBean.ListBean, BaseViewHolder> {
    public TroubleTypeAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, TroubleTypeBean.ListBean item) {
//        //     iv_icon tv_title
                ImageLoadProxy.getInstance()
                .load(new ImageLoadConfiguration.Builder(App.getInstance())
                        .defaultImageResId(R.mipmap.icon_img_load_pre)
                        .url(Constants.IMG_SERVER_PATH+item.getIcon())
                        .imageView((ImageView) helper.getView(R.id.iv_icon)).build());


        helper.setText(R.id.tv_title,item.getCatename());

    }
}
