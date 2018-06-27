package com.itislevel.lyl.adapter;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.itislevel.lyl.R;
import com.itislevel.lyl.app.App;
import com.itislevel.lyl.mvp.model.bean.FunshingCommentItemBean;
import com.itislevel.lyl.mvp.model.bean.FunshingReplayItemBean;
import com.itislevel.lyl.mvp.ui.chatkeyboardview.EmotionUtils;
import com.itislevel.lyl.mvp.ui.chatkeyboardview.SpanStringUtils;
import com.itislevel.lyl.mvp.ui.funsharing.FunsharingDetailActivity;
import com.itislevel.lyl.mvp.ui.funsharing.FunsharingHomeActivity;
import com.itislevel.lyl.utils.TextViewSpanUtl;
import com.itislevel.lyl.utils.rxbus.annotation.UseRxBus;

import java.util.List;

/**
 * desc:功能说明必写
 * user:itisi
 * date:2017/12/14.9:52
 * path:com.itislevel.lyl.adapter.FunsharingCommentAdapter
 **/
@UseRxBus
public class FunsharingDetailCommentAdapter extends BaseQuickAdapter<FunshingCommentItemBean,
        BaseViewHolder> {

    FunsharingDetailActivity funsharingDetailActivity;
    String fabuzheid;

    public FunsharingDetailCommentAdapter(int layoutResId, FunsharingDetailActivity activity,String fabuzheid) {
        super(layoutResId);
        funsharingDetailActivity=activity;
        this.fabuzheid=fabuzheid;
    }

    @Override
    protected void convert(BaseViewHolder helper, FunshingCommentItemBean item) {

        item.setFabuzheid(fabuzheid);

        helper.addOnClickListener(R.id.tv_comment_nickname);
        helper.addOnClickListener(R.id.tv_comment_content);
        helper.addOnClickListener(R.id.tv_add_comment);

        helper.setText(R.id.tv_comment_nickname,item.getObserver());

//        helper.setText(R.id.tv_comment_content,item.getComment());
        TextView tvConent=helper.getView(R.id.tv_comment_content);
        SpannableString emotionContent = SpanStringUtils.getEmotionContent(EmotionUtils
                .EMOTION_CLASSIC_TYPE, mContext, tvConent, item.getComment());
        tvConent.setText(emotionContent);


        TextView tv_add_comment = helper.getView(R.id.tv_add_comment);
        TextViewSpanUtl.setCommentText(funsharingDetailActivity,item.getObserver(),item.getComment(),tv_add_comment);


//        //回复列表
        RecyclerView recyclerViewReplay= helper.getView(R.id.recyclerview_replay);
        List<FunshingReplayItemBean> comments1 = item.getComments1();
        LinearLayoutManager layoutManager = new LinearLayoutManager(App.getInstance());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewReplay.setLayoutManager(layoutManager);

        if (comments1!=null&&comments1.size()>0){
            FunsharingCommentReplayAdapter adapter=new FunsharingCommentReplayAdapter(R.layout.item_comment_replay,fabuzheid);
            adapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN); //切换动画

            adapter.setOnItemChildClickListener(funsharingDetailActivity.getCommentReplayListener());
            adapter.setOnItemClickListener(funsharingDetailActivity.getCommentReplayListener());

            adapter.setEnableLoadMore(false);
            adapter.setNewData(comments1);
            recyclerViewReplay.setAdapter(adapter);
        }else{
            FunsharingCommentReplayAdapter adapter=new FunsharingCommentReplayAdapter(R.layout.item_comment_replay,fabuzheid);
            recyclerViewReplay.setAdapter(adapter);
        }


    }


}
