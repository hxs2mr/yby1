package com.itislevel.lyl.adapter;

import android.app.Activity;
import android.graphics.Color;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.text.SpannableString;
import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.itislevel.lyl.R;
import com.itislevel.lyl.app.Constants;
import com.itislevel.lyl.mvp.model.bean.CFPinBean;
import com.itislevel.lyl.mvp.model.bean.PropretyNoticeBean;
import com.itislevel.lyl.mvp.ui.chatkeyboardview.EmotionUtils;
import com.itislevel.lyl.mvp.ui.chatkeyboardview.SpanStringUtils;
import com.itislevel.lyl.utils.SharedPreferencedUtils;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.itislevel.lyl.utils.DateUtil.timeSpanToDate;

/**
 * desc:功能说明必写
 * user:itisi
 * date:2017/12/5.14:29
 * path:com.itislevel.lyl.adapter.ProvinceAdapter
 **/
public class PropretyAllTonAdapter extends BaseQuickAdapter<PropretyNoticeBean.ListBean,
        BaseViewHolder> {
    Activity mActivity;

    public PropretyAllTonAdapter(int layoutResId, Activity activity) {
        super(layoutResId);
        this.mActivity = activity;

    }
    @Override
    protected void convert(BaseViewHolder helper,PropretyNoticeBean.ListBean item) {
        helper.setText(R.id.p_title1,item.getTitile());
        helper.setText(R.id.p_comment1,item.getContent());
        helper.setText(R.id.p_time1,timeSpanToDate(item.getCreatedtime()));
        CircleImageView imageView = helper.getView(R.id.p_image1);
        Glide.with(mActivity)
                .load(Constants.IMG_SERVER_PATH + item.getHeadurl())
                .asBitmap()
                .error(R.mipmap.person_head)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView);
    }

}
