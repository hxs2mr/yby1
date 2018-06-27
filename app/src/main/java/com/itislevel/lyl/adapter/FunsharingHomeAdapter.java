package com.itislevel.lyl.adapter;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.itislevel.lyl.R;
import com.itislevel.lyl.app.App;
import com.itislevel.lyl.mvp.model.bean.FunshingCommentItemBean;
import com.itislevel.lyl.mvp.model.bean.FunshingItemBean;
import com.itislevel.lyl.mvp.model.bean.FunshingReplayItemBean;
import com.itislevel.lyl.utils.DateUtil;
import com.itislevel.lyl.utils.imageload.ImageLoadConfiguration;
import com.itislevel.lyl.utils.imageload.ImageLoadProxy;

import java.util.Date;
import java.util.List;

/**
 * desc:功能说明必写
 * user:itisi
 * date:2017/12/15.15:24
 * path:com.itislevel.lyl.adapter.FunsharingHomeAdapter
 **/
public class FunsharingHomeAdapter extends BaseMultiItemQuickAdapter<MultiItemEntity,
        BaseViewHolder> {
    public static final int TYPE_SHARE_ITEM = 0;//分享
    public static final int TYPE_COMMENT_ITEM = 1;//评论
    public static final int TYPE_REPLAY_ITEM = 2;//回复

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public FunsharingHomeAdapter(List<MultiItemEntity> data) {
        super(data);
        addItemType(TYPE_SHARE_ITEM, R.layout.item_funsharing);
        addItemType(TYPE_COMMENT_ITEM, R.layout.item_comment);
        addItemType(TYPE_REPLAY_ITEM, R.layout.item_comment_replay);
    }

    @Override
    protected void convert(BaseViewHolder helper, MultiItemEntity item) {
        switch (helper.getItemViewType()) {
            case TYPE_SHARE_ITEM://分享item
                helper.addOnClickListener(R.id.tv_like_num);
                helper.addOnClickListener(R.id.tv_comment_num);
                helper.addOnClickListener(R.id.iv_header);
                helper.addOnClickListener(R.id.tv_comment_input);
                helper.addOnClickListener(R.id.tv_title);


                FunshingItemBean listBean= (FunshingItemBean) item;

                helper.setText(R.id.tv_nickname,listBean.getNickname());
                helper.setText(R.id.tv_time, DateUtil.getFriendlytime(new Date(listBean.getCreatedtime())));
                helper.setText(R.id.tv_title, listBean.getContent());
                if (TextUtils.isEmpty(listBean.getNickname())) {
                    helper.setText(R.id.tv_nickname, listBean.getUsername());
                } else {
                    helper.setText(R.id.tv_nickname, listBean.getNickname());
                }
                //评论不显示数量
                helper.setText(R.id.tv_like_num, listBean.getFabulousnumber() + "");

                if (listBean.getImge() != null) {
                    ImageLoadProxy.getInstance()
                            .load(new ImageLoadConfiguration.Builder(App.getInstance())
                                    .url(listBean.getImge())
                                    .defaultImageResId(R.mipmap.icon_default_header_circle)
                                    .imageView((ImageView) helper.getView(R.id.iv_header)).build());
                } else {
                    ImageLoadProxy.getInstance()
                            .load(new ImageLoadConfiguration.Builder(App.getInstance())
                                    .url(R.mipmap.icon_default_header_circle)
                                    .imageView((ImageView) helper.getView(R.id.iv_header)).build());
                }

//                View commentView=View.inflate(App.getInstance(),R.layout.item_pop,null);
//                this.addFooterView(commentView);

//                LinearLayout linearLayout= helper.getView(R.id.ll_comment_parent);//评论的父布局
//                TextView textView=(TextView) View.inflate(App.getInstance(), R.layout.view_comment_list_item, null);
//                textView.setText("fdassfdasf");
//                linearLayout.addView(textView);


                break;
            case TYPE_COMMENT_ITEM://评论item

//                helper.addOnClickListener(R.id.tv_comment_nickname);
//                helper.addOnClickListener(R.id.tv_comment_content);
//
//                FunshingCommentItemBean itemBean= (FunshingCommentItemBean) item;
//
//                helper.setText(R.id.tv_comment_nickname,itemBean.getNickname());
//                helper.setText(R.id.tv_comment_content,itemBean.getComment());


                break;
            case TYPE_REPLAY_ITEM://回复item
//
//                helper.addOnClickListener(R.id.tv_from_username);
//                helper.addOnClickListener(R.id.tv_to_username);
//                helper.addOnClickListener(R.id.tv_replay);
//
//                FunshingReplayItemBean replayItemBean= (FunshingReplayItemBean) item;
//                helper.setText(R.id.tv_from_username,replayItemBean.getNickname());
//                helper.setText(R.id.tv_to_username,replayItemBean.getTonickname());
//                helper.setText(R.id.tv_replay,replayItemBean.getComment());

                break;
        }

    }
}
