package com.itislevel.lyl.adapter;

import android.app.Activity;
import android.support.v7.widget.AppCompatImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.itislevel.lyl.R;
import com.itislevel.lyl.app.Constants;
import com.itislevel.lyl.mvp.model.bean.CFChildBean;

import static com.itislevel.lyl.utils.DateUtil.timeSpanToDateTime1;

/**
 * desc:功能说明必写
 * user:itisi
 * date:2017/12/5.14:29
 * path:com.itislevel.lyl.adapter.ProvinceAdapter
 **/
public class CfChildFragmentAdapter extends BaseQuickAdapter<CFChildBean.PageBeanBean.ListBean,
        BaseViewHolder> {
    Activity mActivity;

    public CfChildFragmentAdapter(int layoutResId, Activity activity) {
        super(layoutResId);
        this.mActivity = activity;

    }
    @Override
    protected void convert(BaseViewHolder helper, CFChildBean.PageBeanBean.ListBean item) {
        helper.setText(R.id.cf_title, item.getTitle());
        helper.setText(R.id.cf_user_time, item.getPublisher() + "  " + timeSpanToDateTime1(item.getCreatedtime()));
        helper.setText(R.id.cf_pin, item.getCommentnum() + "");
        int size;
        if (item.getNosense() != null)
        {
            size = item.getPointnum()+ item.getNosense().toString().split(",").length;
        }else {
            size = item.getPointnum();
        }
        helper.setText(R.id.cf_zan,size+"");

        AppCompatImageView imageView = helper.getView(R.id.cf_image);

        Glide.with(mActivity)
                .load(Constants.IMG_SERVER_PATH +item.getLogo())
                .asBitmap()
                .error(R.mipmap.person_head)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView);
    }

}
