package com.itislevel.lyl.adapter;

import android.text.SpannableString;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.itislevel.lyl.R;
import com.itislevel.lyl.app.App;
import com.itislevel.lyl.mvp.model.bean.ListCommentItemBean;
import com.itislevel.lyl.mvp.ui.chatkeyboardview.EmotionUtils;
import com.itislevel.lyl.mvp.ui.chatkeyboardview.SpanStringUtils;
import com.itislevel.lyl.utils.TextViewSpanUtl;

/**
 * desc:功能说明必写
 * user:itisi
 * date:2017/12/14.9:52
 * path:com.itislevel.lyl.adapter.FunsharingCommentAdapter
 **/
public class DYPersonReplayAdapter extends BaseQuickAdapter<ListCommentItemBean,
        BaseViewHolder> {
    private int fabuzheid;//发布者id

    public DYPersonReplayAdapter(int layoutResId, int fabuzheid) {
        super(layoutResId);
        this.fabuzheid=fabuzheid;
    }

    @Override
    protected void convert(BaseViewHolder helper, ListCommentItemBean item) {
        item.setId(fabuzheid);
        helper.setText(R.id.tv_from_username,item.getObserver());

        if (item.getAnswerer()==null){
            helper.setText(R.id.tv_to_username,"空昵称");
        }else{
            helper.setText(R.id.tv_to_username,item.getAnswerer()+"");
        }

        TextView tvConent=helper.getView(R.id.tv_replay);

        SpannableString emotionContent;

        if(item.getComment().contains("[数量1]"))
        {
            emotionContent = SpanStringUtils.getEmotionContent_Dy(EmotionUtils
                    .EMOTION_CLASSIC_TYPE, mContext, tvConent, item.getComment(),0);
        }else {
            emotionContent = SpanStringUtils.getEmotionContent_Dy(EmotionUtils
                    .EMOTION_CLASSIC_TYPE, mContext, tvConent, item.getComment(),1);
        }

        tvConent.setText(emotionContent);


        TextView tv_add_comment = helper.getView(R.id.tv_add_comment);
        TextViewSpanUtl.setCommentReplayText(App.getInstance(),item.getObserver(),item.getAnswerer()+"",item.getComment(),tv_add_comment);
        helper.addOnClickListener(R.id.tv_from_username);
        helper.addOnClickListener(R.id.tv_to_username);
        helper.addOnClickListener(R.id.tv_replay);
        helper.addOnClickListener(R.id.tv_add_comment);
    }
}
