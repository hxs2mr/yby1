package com.itislevel.lyl.adapter;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.itislevel.lyl.R;
import com.itislevel.lyl.app.App;
import com.itislevel.lyl.mvp.model.bean.ListFollowItemBean;
import com.itislevel.lyl.utils.TextViewSpanUtl;
import com.itislevel.lyl.utils.rxbus.annotation.UseRxBus;

/**
 * desc:功能说明必写
 * user:itisi
 * date:2017/12/14.9:52
 * path:com.itislevel.lyl.adapter.FunsharingCommentAdapter
 **/
@UseRxBus
public class DFindCommentAdapter extends BaseQuickAdapter<ListFollowItemBean.ListCommentItemBean,
        BaseViewHolder> {

    Activity funsharingHomeActivity;
    private int fabuzheid;//发布者id


    public DFindCommentAdapter(int layoutResId, Activity activity, int fabuzheid) {
        super(layoutResId);
        funsharingHomeActivity=activity;
        this.fabuzheid=fabuzheid;
    }

    @Override
    protected void convert(BaseViewHolder helper, ListFollowItemBean.ListCommentItemBean item) {
        TextView tv_add_comment = helper.getView(R.id.tv_add_comment);
        helper.addOnClickListener(R.id.tv_add_comment);
        if(item.getFabulous()==0||item.getFabulous()==1)
        {
            tv_add_comment.setVisibility(View.VISIBLE);
              item.setId(fabuzheid);

              if(item.getFabulous()==0)//表示是评论
              {
                  TextViewSpanUtl.setCommentText(funsharingHomeActivity,item.getObserver(),item.getComment().trim(),tv_add_comment);
              }else {//表示是回复
                  TextViewSpanUtl.setCommentReplayText(App.getInstance(),item.getObserver(),item.getAnswerer()+"",item.getComment(),tv_add_comment);
              }
        }
    }

}
