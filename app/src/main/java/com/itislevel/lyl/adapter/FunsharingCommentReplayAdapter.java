package com.itislevel.lyl.adapter;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.itislevel.lyl.R;
import com.itislevel.lyl.app.App;
import com.itislevel.lyl.mvp.model.bean.FunsharingListBean;
import com.itislevel.lyl.mvp.model.bean.FunshingReplayItemBean;
import com.itislevel.lyl.mvp.ui.chatkeyboardview.EmotionUtils;
import com.itislevel.lyl.mvp.ui.chatkeyboardview.SpanStringUtils;
import com.itislevel.lyl.utils.TextViewSpanUtl;

import java.util.List;

/**
 * desc:功能说明必写
 * user:itisi
 * date:2017/12/14.9:52
 * path:com.itislevel.lyl.adapter.FunsharingCommentAdapter
 **/
public class FunsharingCommentReplayAdapter extends BaseQuickAdapter<FunshingReplayItemBean,
        BaseViewHolder> {
    private String fabuzheid;//发布者id

    public FunsharingCommentReplayAdapter(int layoutResId,String fabuzheid) {
        super(layoutResId);
        this.fabuzheid=fabuzheid;
    }

    @Override
    protected void convert(BaseViewHolder helper, FunshingReplayItemBean item) {

        item.setFabuzheid(fabuzheid);
        helper.setText(R.id.tv_from_username,item.getObserver());

        if (item.getAnswerer()==null){
            helper.setText(R.id.tv_to_username,"空昵称");
        }else{
            helper.setText(R.id.tv_to_username,item.getAnswerer()+"");
        }

//        helper.setText(R.id.tv_replay,item.getComment());

        TextView tvConent=helper.getView(R.id.tv_replay);
        SpannableString emotionContent = SpanStringUtils.getEmotionContent(EmotionUtils
                .EMOTION_CLASSIC_TYPE, mContext, tvConent, item.getComment());
        tvConent.setText(emotionContent);

        TextView tv_add_comment = helper.getView(R.id.tv_add_comment);
        TextViewSpanUtl.setCommentReplayText(App.getInstance(),item.getObserver(),item.getAnswerer()+"",item.getComment(),tv_add_comment);
        helper.addOnClickListener(R.id.tv_from_username);
        helper.addOnClickListener(R.id.tv_to_username);
        helper.addOnClickListener(R.id.tv_replay);
        helper.addOnClickListener(R.id.tv_add_comment);
    }
}
