package com.itislevel.lyl.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.itislevel.lyl.R;
import com.itislevel.lyl.app.App;
import com.itislevel.lyl.app.Constants;
import com.itislevel.lyl.mvp.model.bean.FamilyListBean;
import com.itislevel.lyl.mvp.model.bean.MyGiftBean;
import com.itislevel.lyl.utils.imageload.ImageLoadConfiguration;
import com.itislevel.lyl.utils.imageload.ImageLoadProxy;

import java.util.List;

/**
 * desc:功能说明必写
 * user:itisi
 * date:2017/12/21.14:33
 * path:com.itislevel.lyl.adapter.MyGiftListAdapter
 **/
public class MyGiftListAdapter extends BaseQuickAdapter<MyGiftBean.ListputBean,
        BaseViewHolder> {
    public MyGiftListAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, MyGiftBean.ListputBean item) {

                ImageLoadProxy.getInstance()
                .load(new ImageLoadConfiguration.Builder(App.getInstance())
                        .defaultImageResId(R.mipmap.icon_img_load_pre)
                        .url(Constants.IMG_SERVER_PATH+item.getIcon())
                        .imageView((ImageView) helper.getView(R.id.iv_icon)).build());

                helper.setText(R.id.tv_count,"X"+item.getCount());

    }
}
