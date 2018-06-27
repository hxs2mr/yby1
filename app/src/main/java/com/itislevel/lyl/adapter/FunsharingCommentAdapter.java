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
import com.itislevel.lyl.mvp.model.bean.FunsharingCommnetAddBean;
import com.itislevel.lyl.mvp.model.bean.FunsharingListBean;
import com.itislevel.lyl.mvp.model.bean.FunshingCommentItemBean;
import com.itislevel.lyl.mvp.model.bean.FunshingReplayItemBean;
import com.itislevel.lyl.mvp.ui.chatkeyboardview.EmotionUtils;
import com.itislevel.lyl.mvp.ui.chatkeyboardview.SpanStringUtils;
import com.itislevel.lyl.mvp.ui.funsharing.FunsharingHomeActivity;
import com.itislevel.lyl.utils.GsonUtil;
import com.itislevel.lyl.utils.TextViewSpanUtl;
import com.itislevel.lyl.utils.ToastUtil;
import com.itislevel.lyl.utils.rxbus.RxBus;
import com.itislevel.lyl.utils.rxbus.annotation.Subscribe;
import com.itislevel.lyl.utils.rxbus.annotation.UseRxBus;
import com.itislevel.lyl.utils.rxbus.event.EventThread;
import com.orhanobut.logger.Logger;

import java.util.List;

/**
 * desc:功能说明必写
 * user:itisi
 * date:2017/12/14.9:52
 * path:com.itislevel.lyl.adapter.FunsharingCommentAdapter
 **/
@UseRxBus
public class FunsharingCommentAdapter extends BaseQuickAdapter<FunshingCommentItemBean,
        BaseViewHolder> {

    FunsharingHomeActivity funsharingHomeActivity;
    private String fabuzheid;//发布者id


    public FunsharingCommentAdapter(int layoutResId,FunsharingHomeActivity activity,String fabuzheid) {
        super(layoutResId);
        funsharingHomeActivity=activity;
        this.fabuzheid=fabuzheid;
    }

    @Override
    protected void convert(BaseViewHolder helper, FunshingCommentItemBean item) {
        RecyclerView recyclerViewReplay= null;
        FunsharingCommentReplayAdapter adapter = null;
        TextView tv_add_comment = helper.getView(R.id.tv_add_comment);
        if(recyclerViewReplay==null)
         {
             recyclerViewReplay =   helper.getView(R.id.recyclerview_replay);
             LinearLayoutManager layoutManager = new LinearLayoutManager(App.getInstance());
             List<FunshingReplayItemBean> comments1 = item.getComments1();
             layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
             recyclerViewReplay.setLayoutManager(layoutManager);
              adapter=new FunsharingCommentReplayAdapter(R.layout.item_comment_replay,fabuzheid);
             adapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN); //切换动画
             adapter.setOnItemChildClickListener(funsharingHomeActivity.getCommentReplayListener());
             adapter.setOnItemClickListener(funsharingHomeActivity.getCommentReplayListener());
             adapter.setEnableLoadMore(false);
             recyclerViewReplay.setAdapter(adapter);
         }

        if(item.getFabulous().equals("0"))
        {
            tv_add_comment.setVisibility(View.VISIBLE);
        item.setFabuzheid(fabuzheid);

        helper.setText(R.id.tv_comment_nickname,item.getObserver());

//        helper.setText(R.id.tv_comment_content,item.getComment());

        TextView tvConent=helper.getView(R.id.tv_comment_content);
        SpannableString emotionContent = SpanStringUtils.getEmotionContent(EmotionUtils
                .EMOTION_CLASSIC_TYPE, mContext, tvConent, item.getComment());
        tvConent.setText(emotionContent);

        TextViewSpanUtl.setCommentText(funsharingHomeActivity,item.getObserver(),item.getComment(),tv_add_comment);

        helper.addOnClickListener(R.id.tv_comment_nickname);
        helper.addOnClickListener(R.id.tv_comment_content);
        helper.addOnClickListener(R.id.tv_add_comment);
        }else {
//        //回复列表
            tv_add_comment.setVisibility(View.GONE);
            FunshingReplayItemBean replayItemBean = new FunshingReplayItemBean(item.getId(),item.getShareid(),item.getUserid(),
                    item.getTouserid(),item.getObserver(),item.getAnswerer()+"",
                    item.getParentid(),item.getComment(),
                    item.getFabulous(),item.getCreatedtime());
            adapter.addData(replayItemBean);

        }

    }


}
