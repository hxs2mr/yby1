package com.itislevel.lyl.adapter;

import android.text.SpannableString;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.itislevel.lyl.R;
import com.itislevel.lyl.app.App;
import com.itislevel.lyl.app.Constants;
import com.itislevel.lyl.mvp.model.bean.AddressBean;
import com.itislevel.lyl.mvp.model.bean.FunsharingMyBean;
import com.itislevel.lyl.mvp.ui.chatkeyboardview.EmotionUtils;
import com.itislevel.lyl.mvp.ui.chatkeyboardview.SpanStringUtils;
import com.itislevel.lyl.utils.DateUtil;
import com.itislevel.lyl.utils.imageload.ImageLoadConfiguration;
import com.itislevel.lyl.utils.imageload.ImageLoadProxy;

import java.util.Date;

/**
 * desc:功能说明必写
 * user:itisi
 * date:2017/12/5.14:29
 * path:com.itislevel.lyl.adapter.ProvinceAdapter
 **/
public class FunsharingMyAdapter extends BaseQuickAdapter<FunsharingMyBean.ListBean,
        BaseViewHolder> {

    public FunsharingMyAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, FunsharingMyBean.ListBean item) {
        helper.addOnClickListener(R.id.tv_del);
        helper.addOnClickListener(R.id.tv_title);

        if (TextUtils.isEmpty(item.getContent())){
            helper.setText(R.id.tv_title, "图片");

        }else{
            TextView tvConent = helper.getView(R.id.tv_title);
            SpannableString emotionContent = SpanStringUtils.getEmotionContent(EmotionUtils
                    .EMOTION_CLASSIC_TYPE, mContext, tvConent, item.getContent());
            tvConent.setText(emotionContent);

//            helper.setText(R.id.tv_title, item.getContent());
        }
        helper.setText(R.id.tv_time, DateUtil.timeStrToDateTime(new Date(item.getCreatedtime())));


    }
}
