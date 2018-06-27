package com.itislevel.lyl.adapter;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.itislevel.lyl.R;
import com.itislevel.lyl.app.App;
import com.itislevel.lyl.app.Constants;
import com.itislevel.lyl.mvp.model.bean.BlessDetailGiftListBean;
import com.itislevel.lyl.mvp.model.bean.ListFollowItemBean;
import com.itislevel.lyl.mvp.model.bean.ListItemBean;
import com.itislevel.lyl.utils.imageload.ImageLoadConfiguration;
import com.itislevel.lyl.utils.imageload.ImageLoadProxy;

/**
 * desc:功能说明必写
 * user:itisi
 * date:2017/12/21.19:19
 * path:com.itislevel.lyl.adapter.TeamTypeAdapter
 **/
public class DynamicGiftAdapter extends BaseQuickAdapter<ListFollowItemBean.GiftListBean, BaseViewHolder> {

    public DynamicGiftAdapter(int layoutResId) {
        super(layoutResId);
    }
    @Override
    protected void convert(BaseViewHolder helper, ListFollowItemBean.GiftListBean item) {
        helper.setIsRecyclable(true);
       // iv_gift tv_name  tv_count
        helper.addOnClickListener(R.id.iv_gift).addOnClickListener(R.id.tv_name).addOnClickListener(R.id.tv_count);

        String imgurl = item.getImgurlX();

        if (TextUtils.isEmpty(imgurl)){

        ImageLoadProxy.getInstance()
                .load(new ImageLoadConfiguration.Builder(App.getInstance())
                        .defaultImageResId(R.mipmap.icon_img_load_pre)
                        .url(R.mipmap.icon_img_load_pre)
                        .imageView((ImageView) helper.getView(R.id.iv_gift)).build());
        }else{

        ImageLoadProxy.getInstance()
                .load(new ImageLoadConfiguration.Builder(App.getInstance())
                        .defaultImageResId(R.mipmap.icon_img_load_pre)
                        .url(Constants.IMG_SERVER_PATH + item.getImgurlX())
                        .imageView((ImageView) helper.getView(R.id.iv_gift)).build());
        }

        helper.setText(R.id.tv_name, item.getGoodsname());
       // helper.setText(R.id.tv_count, "x"+item.getCount()+"个");
        helper.setText(R.id.tv_send_name,item.getNicknameX()+"");
        TextView  tv_send_name = helper.getView(R.id.tv_send_name);
        tv_send_name.setVisibility(View.GONE);
    }
}
