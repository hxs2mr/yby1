package com.itislevel.lyl.adapter;

import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.itislevel.lyl.R;
import com.itislevel.lyl.app.App;
import com.itislevel.lyl.app.Constants;
import com.itislevel.lyl.mvp.model.bean.FamilyPhotoFrameBean;
import com.itislevel.lyl.utils.RegexUtil;
import com.itislevel.lyl.utils.imageload.ImageLoadConfiguration;
import com.itislevel.lyl.utils.imageload.ImageLoadProxy;

/**
 * desc:功能说明必写
 * user:itisi
 * date:2017/12/12.17:14
 * path:com.itislevel.lyl.adapter.FamilyListAdapter
 **/
public class FamilyPhotoFrameAdapter extends BaseQuickAdapter<FamilyPhotoFrameBean.ListBean,
        BaseViewHolder> {
    public FamilyPhotoFrameAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, FamilyPhotoFrameBean.ListBean item) {
        //  iv_add tv_price
//        if (item.getSellprice().equals("-1")){
//            helper.setText(R.id.tv_price, "已购买");
//        }

        if (item.getSellprice().equals("0")||(RegexUtil.isDouble(item.getSellprice())&&Float.parseFloat(item.getSellprice())==0)) {
            helper.setText(R.id.tv_price, "");

        } else if(RegexUtil.isZhCN(item.getSellprice())){
            helper.setText(R.id.tv_price, item.getSellprice());
        }
        else {
            helper.setText(R.id.tv_price, "￥"+item.getSellprice());
        }

        if (item.isIscheck()){
            helper.setBackgroundRes(R.id.ll_checked,R.drawable.shape_border_family_select);
        }else{
            helper.setBackgroundRes(R.id.ll_checked,R.drawable.shape_border_family_normal);
        }

        ImageLoadProxy.getInstance()
                .load(new ImageLoadConfiguration.Builder(App.getInstance())
                        .defaultImageResId(R.mipmap.icon_img_load_pre)
                        .url(Constants.IMG_SERVER_PATH+item.getImgurl())
                        .imageView((ImageView) helper.getView(R.id.iv_add)).build());
    }
}
