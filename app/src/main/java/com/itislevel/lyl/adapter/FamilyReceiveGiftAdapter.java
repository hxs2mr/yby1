package com.itislevel.lyl.adapter;

import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.itislevel.lyl.R;
import com.itislevel.lyl.app.App;
import com.itislevel.lyl.app.Constants;
import com.itislevel.lyl.mvp.model.bean.FamilyBlessListRecevieBean;
import com.itislevel.lyl.mvp.model.bean.FamilyReceiveGiftBean;
import com.itislevel.lyl.utils.DateUtil;
import com.itislevel.lyl.utils.imageload.ImageLoadConfiguration;
import com.itislevel.lyl.utils.imageload.ImageLoadProxy;

/**
 * desc:功能说明必写
 * user:itisi
 * date:2017/12/12.17:14
 * path:com.itislevel.lyl.adapter.FamilyListAdapter
 **/
public class FamilyReceiveGiftAdapter extends BaseQuickAdapter<FamilyReceiveGiftBean.ListBean,
        BaseViewHolder> {
    public FamilyReceiveGiftAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, FamilyReceiveGiftBean.ListBean item) {

        //  iv_goods tv_goods_name tv_time tv_name tv_receive_name


        helper.setText(R.id.tv_goods_name,item.getGoodsname()+" x"+item.getCount());
        helper.setText(R.id.tv_name,item.getNickname());
        helper.setText(R.id.tv_receive_name,item.getDeadname());
        helper.setText(R.id.tv_time,"在 "+ DateUtil.timeSpanToDate(item.getPaytime())+" 对");

        String imgurl = item.getImgurl();
        ImageLoadProxy.getInstance()
                .load(new ImageLoadConfiguration.Builder(App.getInstance())
                        .defaultImageResId(R.mipmap.icon_img_load_pre)
                        .url(Constants.IMG_SERVER_PATH+item.getImgurl())
                        .imageView((ImageView) helper.getView(R.id.iv_goods)).build());
    }


}
