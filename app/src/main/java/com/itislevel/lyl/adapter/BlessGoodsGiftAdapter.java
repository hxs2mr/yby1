package com.itislevel.lyl.adapter;

import android.text.TextUtils;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.itislevel.lyl.R;
import com.itislevel.lyl.app.App;
import com.itislevel.lyl.app.Constants;
import com.itislevel.lyl.mvp.model.bean.BlessGiftBean;
import com.itislevel.lyl.mvp.model.bean.BlessListBean;
import com.itislevel.lyl.mvp.model.bean.GiftBean;
import com.itislevel.lyl.mvp.model.bean.GoodsBlessGiftBean;
import com.itislevel.lyl.utils.DateUtil;
import com.itislevel.lyl.utils.imageload.ImageLoadConfiguration;
import com.itislevel.lyl.utils.imageload.ImageLoadProxy;
import com.lzy.ninegrid.ImageInfo;
import com.lzy.ninegrid.NineGridView;
import com.lzy.ninegrid.preview.NineGridViewClickAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * desc:功能说明必写
 * user:itisi
 * date:2017/12/21.19:19
 * path:com.itislevel.lyl.adapter.TeamTypeAdapter
 **/
public class BlessGoodsGiftAdapter extends BaseQuickAdapter<GiftBean,
        BaseViewHolder> {
    private List<Integer> checkboxUserIdList = new ArrayList<>();
    public BlessGoodsGiftAdapter(int layoutResId) {
        super(layoutResId);
        openLoadAnimation(BaseQuickAdapter.SCALEIN);
        //多次执行动画
        isFirstOnly(false);
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    protected void convert(BaseViewHolder helper, GiftBean item) {
        //  iv_gift tv_name tv_price

        helper.setText(R.id.tv_name, item.getGiftname());
        helper.setText(R.id.tv_price, "￥" + item.getSellprice());

        ImageLoadProxy.getInstance()
                .load(new ImageLoadConfiguration.Builder(App.getInstance())
                        .defaultImageResId(R.mipmap.icon_img_load_pre)
                        .url(Constants.IMG_SERVER_PATH+item.getImgurl())
                        .imageView((ImageView) helper.getView(R.id.iv_gift)).build());

    }
}
