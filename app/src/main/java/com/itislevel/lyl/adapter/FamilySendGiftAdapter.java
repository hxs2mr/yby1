package com.itislevel.lyl.adapter;

import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.itislevel.lyl.R;
import com.itislevel.lyl.app.App;
import com.itislevel.lyl.app.Constants;
import com.itislevel.lyl.mvp.model.bean.FamilyReceiveGiftBean;
import com.itislevel.lyl.mvp.model.bean.FamilySendGiftRecordBean;
import com.itislevel.lyl.utils.DateUtil;
import com.itislevel.lyl.utils.imageload.ImageLoadConfiguration;
import com.itislevel.lyl.utils.imageload.ImageLoadProxy;

/**
 * desc:功能说明必写
 * user:itisi
 * date:2017/12/12.17:14
 * path:com.itislevel.lyl.adapter.FamilyListAdapter
 **/
public class FamilySendGiftAdapter extends BaseQuickAdapter<FamilySendGiftRecordBean.ListBean,
        BaseViewHolder> {
    public FamilySendGiftAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, FamilySendGiftRecordBean.ListBean item) {

        //  tv_name 不用  tv_time tv_receive_name  tv_goods_name iv_goods

        helper.setText(R.id.tv_receive_name,item.getDeadname());
        helper.setText(R.id.tv_time,"在"+ DateUtil.timeSpanToDate(item.getPaytime())+"对");
        helper.setText(R.id.tv_goods_name,item.getGoodsname()+" x"+item.getCount());

                ImageLoadProxy.getInstance()
                .load(new ImageLoadConfiguration.Builder(App.getInstance())
                        .defaultImageResId(R.mipmap.icon_img_load_pre)
                        .url(Constants.IMG_SERVER_PATH+item.getImgurl())
                        .imageView((ImageView) helper.getView(R.id.iv_goods)).build());

    }


}
