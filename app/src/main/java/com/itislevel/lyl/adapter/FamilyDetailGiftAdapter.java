package com.itislevel.lyl.adapter;

import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.itislevel.lyl.R;
import com.itislevel.lyl.app.App;
import com.itislevel.lyl.app.Constants;
import com.itislevel.lyl.mvp.model.bean.FamilyReceiveGiftBean;
import com.itislevel.lyl.utils.TextViewSpanUtl;
import com.itislevel.lyl.utils.imageload.ImageLoadConfiguration;
import com.itislevel.lyl.utils.imageload.ImageLoadProxy;

import static com.itislevel.lyl.utils.DateUtil.timeSpanToDate;

/**
 * desc:功能说明必写
 * user:itisi
 * date:2017/12/12.17:14
 * path:com.itislevel.lyl.adapter.FamilyListAdapter
 **/
public class FamilyDetailGiftAdapter extends BaseQuickAdapter<FamilyReceiveGiftBean.ListBean,
        BaseViewHolder> {
    public FamilyDetailGiftAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, FamilyReceiveGiftBean.ListBean item) {
        //     iv_icon

        TextView  textView = helper.getView(R.id.tv_name);
        TextViewSpanUtl.setFamilyReceive(item.getBuyusername(),item.getDeadname()+"的祭祀",textView);//标识祝福语
        helper.setText(R.id.receive_time, timeSpanToDate(item.getCreatedtime()));
        helper.setText(R.id.tv_goodsname,item.getGoodsname()+"x1");
        ImageLoadProxy.getInstance()
                .load(new ImageLoadConfiguration.Builder(App.getInstance())
                        .defaultImageResId(R.mipmap.icon_img_load_pre)
                        .url(Constants.IMG_SERVER_PATH+item.getImgurl())
                        .imageView((ImageView) helper.getView(R.id.iv_icon)).build());
    }

}
