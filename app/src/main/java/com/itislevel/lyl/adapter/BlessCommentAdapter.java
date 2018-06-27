package com.itislevel.lyl.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.itislevel.lyl.R;
import com.itislevel.lyl.app.App;
import com.itislevel.lyl.app.Constants;
import com.itislevel.lyl.mvp.model.bean.BlessListBean;
import com.itislevel.lyl.mvp.ui.blessing.BlessingDetailActivity;
import com.itislevel.lyl.mvp.ui.chatkeyboardview.EmotionUtils;
import com.itislevel.lyl.mvp.ui.chatkeyboardview.SpanStringUtils;
import com.itislevel.lyl.utils.DateUtil;
import com.itislevel.lyl.utils.imageload.ImageLoadConfiguration;
import com.itislevel.lyl.utils.imageload.ImageLoadProxy;
import com.lzy.ninegrid.ImageInfo;
import com.lzy.ninegrid.NineGridView;
import com.lzy.ninegrid.preview.NineGridViewClickAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * desc:功能说明必写
 * user:itisi
 * date:2017/12/21.19:19
 * path:com.itislevel.lyl.adapter.TeamTypeAdapter
 **/
public class BlessCommentAdapter extends BaseQuickAdapter<BlessListBean.ListBean.CommentsBean, BaseViewHolder> {

    BlessingDetailActivity blessingDetailActivity;
    private String fabuzheid;//发布者id

    public BlessCommentAdapter(int layoutResId,BlessingDetailActivity blessingDetailActivity,String fabuzheid) {
        super(layoutResId);
        this.blessingDetailActivity=blessingDetailActivity;
        this.fabuzheid=fabuzheid;
    }

    @Override
    protected void convert(BaseViewHolder helper, BlessListBean.ListBean.CommentsBean item) {
        //   tv_comment_nickname  tv_comment_content
        RecyclerView recyclerView = null;// = helper.getView(R.id.recyclerview_replay);
        BlessCommentReplayAdapter commentReplayAdapter = null;
        LinearLayout bless_item_shuo_linear = helper.getView(R.id.bless_item_shuo_linear);
        if(recyclerView==null)
        {
            recyclerView = helper.getView(R.id.recyclerview_replay);
            commentReplayAdapter = new BlessCommentReplayAdapter(R.layout.item_comment_replay_bless,fabuzheid);
            commentReplayAdapter.setOnItemClickListener(blessingDetailActivity.getCommentReplayListener());
            commentReplayAdapter.setOnItemChildClickListener(blessingDetailActivity.getCommentReplayListener());
            commentReplayAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN); //切换动画
            commentReplayAdapter.setEnableLoadMore(false);
            LinearLayoutManager layoutManager = new LinearLayoutManager(blessingDetailActivity);
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(commentReplayAdapter);
        }

        if(item.getFabulous().equals("0"))
        {
            bless_item_shuo_linear.setVisibility(View.VISIBLE);
            helper.addOnClickListener(R.id.tv_comment_nickname);
            helper.addOnClickListener(R.id.tv_comment_content);

            helper.setText(R.id.tv_comment_nickname,item.getObserver());
//        helper.setText(R.id.tv_comment_content,item.getComment());

            item.setFabuzheid(fabuzheid);

            TextView tvConent=helper.getView(R.id.tv_comment_content);
            SpannableString emotionContent = SpanStringUtils.getEmotionContent(EmotionUtils
                .EMOTION_CLASSIC_TYPE, mContext, tvConent, item.getComment());
            tvConent.setText(emotionContent);
        }else {
            /*
            *
            * this.id = id;
                    this.happyid = happyid;
                    this.userid = userid;
                    this.touserid = touserid;
                    this.observer = observer;
                    this.answerer = answerer;
                    this.parentid = parentid;
                    this.comment = comment;
                    this.fabulous = fabulous;
                    this.createdtime = createdtime;
            * */
            bless_item_shuo_linear.setVisibility(View.GONE);
            BlessListBean.ListBean.CommentsBean.Comments1Bean  bean  = new BlessListBean.ListBean.CommentsBean.Comments1Bean(item.getId(),item.getHappyid(),item.getUserid(),
                    item.getTouserid(),item.getObserver(),item.getAnswerer()+""
                    ,item.getParentid(),item.getComment(),item.getFabulous(),item.getCreatedtime());
            commentReplayAdapter.addData(bean);
        }




    }
}
