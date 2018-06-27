package com.itislevel.lyl.adapter;

import android.text.TextUtils;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.itislevel.lyl.R;
import com.itislevel.lyl.app.App;
import com.itislevel.lyl.app.Constants;
import com.itislevel.lyl.mvp.model.bean.BlessReceiveYuBean;
import com.itislevel.lyl.mvp.model.bean.BlessSendGiftBean;
import com.itislevel.lyl.utils.DateUtil;
import com.itislevel.lyl.utils.imageload.ImageLoadConfiguration;
import com.itislevel.lyl.utils.imageload.ImageLoadProxy;

/**
 * desc:功能说明必写
 * user:itisi
 * date:2017/12/21.19:19
 * path:com.itislevel.lyl.adapter.TeamTypeAdapter
 **/
public class BlessSendGiftRecordAdapter extends BaseQuickAdapter<BlessSendGiftBean.ListBean, BaseViewHolder> {

    public BlessSendGiftRecordAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, BlessSendGiftBean.ListBean item) {

        //  tv_tiem  tv_name   tv_goods_name iv_goods

        helper.setText(R.id.tv_name,item.getNickname());
        helper.setText(R.id.tv_goods_name,item.getGoodsname()+"x"+item.getCount());
        String time= DateUtil.timeSpanToDate(item.getCreatedtime());
        if (TextUtils.isEmpty(time)){
            helper.setText(R.id.tv_time,"时间错误");

        }else{
            helper.setText(R.id.tv_time,time);

        }

        if (!TextUtils.isEmpty(item.getImgurl())){

            ImageLoadProxy.getInstance()
                    .load(new ImageLoadConfiguration.Builder(App.getInstance())
                            .defaultImageResId(R.mipmap.icon_img_load_pre)
                            .url(Constants.IMG_SERVER_PATH+item.getImgurl())
                            .imageView((ImageView) helper.getView(R.id.iv_goods)).build());
        }
    }
}
