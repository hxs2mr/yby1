package com.itislevel.lyl.adapter;

import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.itislevel.lyl.R;
import com.itislevel.lyl.app.Constants;
import com.itislevel.lyl.mvp.model.bean.BlessListBean;
import com.itislevel.lyl.mvp.ui.blessing.BlessingDetailActivity;
import com.itislevel.lyl.mvp.ui.chatkeyboardview.EmotionUtils;
import com.itislevel.lyl.mvp.ui.chatkeyboardview.SpanStringUtils;
import com.itislevel.lyl.utils.SharedPreferencedUtils;

import java.util.List;

/**
 * desc:功能说明必写
 * user:itisi
 * date:2017/12/21.19:19
 * path:com.itislevel.lyl.adapter.TeamTypeAdapter
 **/
public class BlessHomeAdapter extends BaseQuickAdapter<BlessListBean.ListBean.CommentsBean, BaseViewHolder> {

    Activity blessingDetailActivity;
    private String fabuzheid;//发布者id

    public BlessHomeAdapter(int layoutResId, Activity blessingDetailActivity, String fabuzheid) {
        super(layoutResId);
        this.blessingDetailActivity=blessingDetailActivity;
        this.fabuzheid=fabuzheid;
    }

    @Override
    protected void convert(BaseViewHolder helper, BlessListBean.ListBean.CommentsBean item) {
        //   tv_comment_nickname  tv_comment_content

        helper.addOnClickListener(R.id.tv_comment_nickname);
        helper.addOnClickListener(R.id.tv_comment_content);
        LinearLayout bless_item_shuo_linear = helper.getView(R.id.bless_item_shuo_linear);
       String user_name =  SharedPreferencedUtils.getStr(Constants.USER_NAME);
        if(user_name.equals(item.getObserver())) {
            helper.setText(R.id.tv_comment_nickname, item.getObserver());
//        helper.setText(R.id.tv_comment_content,item.getComment());

            item.setFabuzheid(fabuzheid);

            TextView tvConent = helper.getView(R.id.tv_comment_content);
            SpannableString emotionContent = SpanStringUtils.getEmotionContent(EmotionUtils
                    .EMOTION_CLASSIC_TYPE, mContext, tvConent, item.getComment());
            tvConent.setText(emotionContent);
        }else {
            bless_item_shuo_linear.setVisibility(View.GONE);
        }
            List<BlessListBean.ListBean.CommentsBean.Comments1Bean> comments1 = item.getComments1();
            RecyclerView recyclerView = helper.getView(R.id.recyclerview_replay);
            BlessHomeReplayAdapter commentReplayAdapter;
            if (comments1 != null && comments1.size() > 0) {
                commentReplayAdapter = new BlessHomeReplayAdapter(R.layout.item_comment_replay_bless, fabuzheid);
                //  commentReplayAdapter.setOnItemClickListener(blessingDetailActivity.getCommentReplayListener());
                // commentReplayAdapter.setOnItemChildClickListener(blessingDetailActivity.getCommentReplayListener());
                commentReplayAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN); //切换动画
                commentReplayAdapter.setEnableLoadMore(false);

                commentReplayAdapter.addData(comments1);

                LinearLayoutManager layoutManager = new LinearLayoutManager(blessingDetailActivity);
                layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(commentReplayAdapter);
            }
    }
}
