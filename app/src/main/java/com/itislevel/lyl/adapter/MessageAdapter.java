package com.itislevel.lyl.adapter;

import android.app.Activity;
import android.graphics.Color;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutCompat;
import android.text.SpannableString;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.itislevel.lyl.R;
import com.itislevel.lyl.app.App;
import com.itislevel.lyl.app.Constants;
import com.itislevel.lyl.mvp.model.bean.CFPinBean;
import com.itislevel.lyl.mvp.model.bean.MessageBean;
import com.itislevel.lyl.mvp.ui.chatkeyboardview.EmotionUtils;
import com.itislevel.lyl.mvp.ui.chatkeyboardview.SpanStringUtils;
import com.itislevel.lyl.utils.SharedPreferencedUtils;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * desc:功能说明必写
 * user:itisi
 * date:2017/12/5.14:29
 * path:com.itislevel.lyl.adapter.ProvinceAdapter
 **/
public class MessageAdapter extends BaseQuickAdapter<MessageBean.ListBean,
        BaseViewHolder> {
    Activity mActivity;

    public MessageAdapter(int layoutResId, Activity activity) {
        super(layoutResId);
        this.mActivity = activity;

    }
    @Override
    protected void convert(BaseViewHolder helper, MessageBean.ListBean item) {

        helper.addOnClickListener(R.id.cf_dianzan_linear1);
        helper.addOnClickListener(R.id.delete);

        helper.setText(R.id.repair_username,item.getRelnickname());
        helper.setText(R.id.time,item.getTime());


        Glide.with(mActivity)
                .load(Constants.IMG_SERVER_PATH + item.getRelheadimg())
                .asBitmap()
                .error(R.mipmap.person_head)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into((ImageView) helper.getView(R.id.iv_photo));
        AppCompatTextView text = helper.getView(R.id.text);
        AppCompatImageView image=helper.getView(R.id.image);
        AppCompatImageView dianzan_image = helper.getView(R.id.dianzan_image);
        AppCompatTextView location_name= helper.getView(R.id.location_name);
        AppCompatImageView gif_image= helper.getView(R.id.gif_image);
        AppCompatTextView gif_name =  helper.getView(R.id.gif_name);
        LinearLayoutCompat gif_image_linear =  helper.getView(R.id.gif_image_linear);
        location_name.setText(item.getComment());
        if(item.getIscmtorpnt().equals("1"))
        {
            dianzan_image.setVisibility(View.VISIBLE);
            location_name.setVisibility(View.GONE);
            gif_image_linear.setVisibility(View.GONE);
        }else   if(item.getIscmtorpnt().equals("0")){
            dianzan_image.setVisibility(View.GONE);
            location_name.setVisibility(View.VISIBLE);
            gif_image_linear.setVisibility(View.GONE);
        }else if(item.getIscmtorpnt().equals("2")){
            dianzan_image.setVisibility(View.GONE);
            location_name.setVisibility(View.GONE);
            gif_image_linear.setVisibility(View.VISIBLE);
            gif_name.setText(item.getTitleinfo());

            Glide.with(mActivity)
                    .load(Constants.IMG_SERVER_PATH +item.getComment())
                    .asBitmap()
                    .error(R.mipmap.person_head)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(gif_image);
        }

        String t_i =item.getDyimgorct().substring(0,2);
        System.out.println("数据的方式*****************"+t_i);
        if(t_i.equals("0@"))
        {
            String data = item.getDyimgorct().substring(2,item.getDyimgorct().length());
            SpannableString emotionContent;
                emotionContent = SpanStringUtils.getEmotionContent_Dy(EmotionUtils
                        .EMOTION_CLASSIC_TYPE, mContext, text,data,1);
            text.setVisibility(View.VISIBLE);
            image.setVisibility(View.GONE);
            text.setText(emotionContent);
        }else {
            text.setVisibility(View.GONE);
            image.setVisibility(View.VISIBLE);
            String url = item.getDyimgorct().substring(2,item.getDyimgorct().length());
            Glide.with(mActivity)
                    .load(Constants.IMG_SERVER_PATH +url)
                    .asBitmap()
                    .error(R.mipmap.person_head)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(image);

        }
    }

}
