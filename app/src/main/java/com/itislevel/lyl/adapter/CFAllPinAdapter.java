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
import com.itislevel.lyl.app.App;
import com.itislevel.lyl.app.Constants;
import com.itislevel.lyl.mvp.model.bean.CFChildBean;
import com.itislevel.lyl.mvp.model.bean.CFPinBean;
import com.itislevel.lyl.mvp.ui.chatkeyboardview.EmotionUtils;
import com.itislevel.lyl.mvp.ui.chatkeyboardview.SpanStringUtils;
import com.itislevel.lyl.utils.SharedPreferencedUtils;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.itislevel.lyl.utils.DateUtil.timeSpanToDateTime1;

/**
 * desc:功能说明必写
 * user:itisi
 * date:2017/12/5.14:29
 * path:com.itislevel.lyl.adapter.ProvinceAdapter
 **/
public class CFAllPinAdapter extends BaseQuickAdapter<CFPinBean.ListBean,
        BaseViewHolder> {
    Activity mActivity;

    public CFAllPinAdapter(int layoutResId, Activity activity) {
        super(layoutResId);
        this.mActivity = activity;

    }
    @Override
    protected void convert(BaseViewHolder helper, CFPinBean.ListBean item) {

        helper.addOnClickListener(R.id.cf_dianzan_linear1);
        helper.addOnClickListener(R.id.delete);

        helper.setText(R.id.cf_pin_name1,item.getObserver());
        helper.setText(R.id.cf_time_name1,item.getWritingDate());

        AppCompatTextView cf_coment1 = helper.getView(R.id.cf_coment1);
        SpannableString emotionContent = SpanStringUtils.getEmotionContent_Cf(EmotionUtils
                .EMOTION_CLASSIC_TYPE, mActivity, cf_coment1,item.getComment());
        cf_coment1.setText(emotionContent);

        AppCompatImageView cf_dianzan_image1= helper.getView(R.id.cf_dianzan_image1);
        AppCompatTextView cf_dianzan_text1 = helper.getView(R.id.cf_dianzan_text1);
        AppCompatTextView delete = helper.getView(R.id.delete);
        String userid = item.getUserid()+"";
        if(userid.equals(SharedPreferencedUtils.getStr(Constants.USER_ID)))
        {
            delete.setVisibility(View.VISIBLE);
        }
        if(item.getIspoint().equals("0"))
        {
            cf_dianzan_image1.setBackgroundResource(R.mipmap.cf_nodian);
            cf_dianzan_text1.setText(item.getPointnum());
            cf_dianzan_text1.setTextColor(Color.parseColor("#999999"));
        }else {
            cf_dianzan_image1.setBackgroundResource(R.mipmap.cf_dian);
            cf_dianzan_text1.setText(item.getPointnum());
            cf_dianzan_text1.setTextColor(Color.parseColor("#ff7b00"));
        }

        CircleImageView imageView = helper.getView(R.id.cf_pin_image1);

        Glide.with(mActivity)
                .load(Constants.IMG_SERVER_PATH + item.getHeadimg())
                .asBitmap()
                .error(R.mipmap.person_head)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView);
    }

}
