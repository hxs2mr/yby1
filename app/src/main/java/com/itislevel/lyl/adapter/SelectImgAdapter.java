package com.itislevel.lyl.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.itislevel.lyl.R;
import com.itislevel.lyl.app.App;
import com.itislevel.lyl.mvp.model.bean.SelectImgBean;
import com.itislevel.lyl.mvp.model.bean.TeamListBean;
import com.itislevel.lyl.utils.imageload.ImageLoadConfiguration;
import com.itislevel.lyl.utils.imageload.ImageLoadProxy;

import java.util.List;

/**
 * desc:功能说明必写
 * user:itisi
 * date:2017/12/25.11:08
 * path:com.itislevel.lyl.adapter.SelectImgAdapter
 **/
public class SelectImgAdapter extends BaseQuickAdapter<SelectImgBean, BaseViewHolder> {
    public SelectImgAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, SelectImgBean item) {

        helper.addOnClickListener(R.id.iv_selected)
            .addOnClickListener(R.id.iv_close);

        ImageLoadProxy.getInstance()
                .load(new ImageLoadConfiguration.Builder(App.getInstance())
                        .defaultImageResId(R.mipmap.icon_img_load_pre)
                        .url(item.getImgUrl())
                        .imageView((ImageView) helper.getView(R.id.iv_selected)).build());

        if (item.isSelectItem()){
            helper.setVisible(R.id.iv_close,false);

        }else{
            helper.setVisible(R.id.iv_close,true);
        }
    }
}
