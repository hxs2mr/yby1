package com.itislevel.lyl.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.itislevel.lyl.R;
import com.itislevel.lyl.mvp.model.bean.BlessCartListBean;

/**
 * desc:功能说明必写
 * user:itisi
 * date:2017/12/21.19:19
 * path:com.itislevel.lyl.adapter.TeamTypeAdapter
 **/
public class ActionSheetReasonAdapter extends BaseQuickAdapter<String,
        BaseViewHolder> {

    public ActionSheetReasonAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
//
        helper.addOnClickListener(R.id.tv_reason);
//
        helper.setText(R.id.tv_reason, item);

//
//        ImageLoadProxy.getInstance()
//                .load(new ImageLoadConfiguration.Builder(App.getInstance())
//                        .url(Constants.IMG_SERVER_PATH+item.getImgurl())
//                        .imageView((ImageView) helper.getView(R.id.iv_gift)).build());

    }
}
