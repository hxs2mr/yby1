package com.itislevel.lyl.adapter;

import android.text.SpannableString;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.itislevel.lyl.R;
import com.itislevel.lyl.app.App;
import com.itislevel.lyl.mvp.model.bean.FunshingReplayItemBean;
import com.itislevel.lyl.mvp.model.bean.TroubleListBean;
import com.itislevel.lyl.mvp.ui.chatkeyboardview.EmotionUtils;
import com.itislevel.lyl.mvp.ui.chatkeyboardview.SpanStringUtils;
import com.itislevel.lyl.utils.TextViewSpanUtl;

/**
 * desc:功能说明必写
 * user:itisi
 * date:2017/12/14.9:52
 * path:com.itislevel.lyl.adapter.FunsharingCommentAdapter
 **/
public class TroubleCommentReplayAdapter extends BaseQuickAdapter<TroubleListBean.ListBean.CommentsBean.Comments1Bean,
        BaseViewHolder> {

    public TroubleCommentReplayAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, TroubleListBean.ListBean.CommentsBean.Comments1Bean item) {


        helper.setText(R.id.tv_from_username,item.getNickname());

        if (item.getTonickname()==null){
            helper.setText(R.id.tv_to_username,"昵称");
        }else{
            helper.setText(R.id.tv_to_username,item.getTonickname()+"");
        }
//        helper.setText(R.id.tv_replay,item.getComment());
        TextView tvConent=helper.getView(R.id.tv_replay);
        SpannableString emotionContent = SpanStringUtils.getEmotionContent(EmotionUtils
                .EMOTION_CLASSIC_TYPE, mContext, tvConent, item.getComment());
        tvConent.setText(emotionContent);


        TextView tv_add_comment = helper.getView(R.id.tv_add_comment);
        TextViewSpanUtl.setCommentReplayText(App.getInstance(),item.getNickname(),item.getTonickname(),item.getComment(),tv_add_comment);

        helper.addOnClickListener(R.id.tv_from_username);
        helper.addOnClickListener(R.id.tv_to_username);
        helper.addOnClickListener(R.id.tv_replay);
        helper.addOnClickListener(R.id.tv_add_comment);


    }
}
