package com.itislevel.lyl.adapter;

import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.itislevel.lyl.R;
import com.itislevel.lyl.app.App;
import com.itislevel.lyl.app.Constants;
import com.itislevel.lyl.mvp.model.bean.TeamListBean;
import com.itislevel.lyl.mvp.model.bean.TeamTypeBean;
import com.itislevel.lyl.utils.imageload.ImageLoadConfiguration;
import com.itislevel.lyl.utils.imageload.ImageLoadProxy;

/**
 * desc:功能说明必写
 * user:itisi
 * date:2017/12/21.19:19
 * path:com.itislevel.lyl.adapter.TeamTypeAdapter
 **/
public class TeamListAdapter extends BaseQuickAdapter<TeamListBean.ListBean, BaseViewHolder> {
    public TeamListAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, TeamListBean.ListBean item) {
        //      tv_name tv_desc tv_view_count tv_go_advice

        helper.addOnClickListener(R.id.tv_desc);
        helper.addOnClickListener(R.id.tv_go_advice);
        helper.addOnClickListener(R.id.iv_header);


        ImageLoadProxy.getInstance()
                .load(new ImageLoadConfiguration.Builder(App.getInstance())
                        .defaultImageResId(R.mipmap.icon_img_load_pre)
                        .url(Constants.IMG_SERVER_PATH + item.getPhoto())
                        .imageView((ImageView) helper.getView(R.id.iv_header)).build());

        helper.setText(R.id.tv_name, item.getName());
        helper.setText(R.id.tv_view_count, "已咨询人数:" + item.getSeeknum());

    }
}
