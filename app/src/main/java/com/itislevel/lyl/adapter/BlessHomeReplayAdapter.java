package com.itislevel.lyl.adapter;

import android.text.SpannableString;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.itislevel.lyl.R;
import com.itislevel.lyl.app.Constants;
import com.itislevel.lyl.mvp.model.bean.BlessListBean;
import com.itislevel.lyl.mvp.ui.chatkeyboardview.EmotionUtils;
import com.itislevel.lyl.mvp.ui.chatkeyboardview.SpanStringUtils;
import com.itislevel.lyl.utils.SharedPreferencedUtils;

/**
 * desc:功能说明必写
 * user:itisi
 * date:2017/12/21.19:19
 * path:com.itislevel.lyl.adapter.TeamTypeAdapter
 **/
public class BlessHomeReplayAdapter extends BaseQuickAdapter<BlessListBean.ListBean.CommentsBean.Comments1Bean, BaseViewHolder> {
    private String fabuzheid;//发布者id

    public BlessHomeReplayAdapter(int layoutResId, String fabuzheid) {
        super(layoutResId);
        this.fabuzheid=fabuzheid;
    }

    @Override
    protected void convert(BaseViewHolder helper, BlessListBean.ListBean.CommentsBean.Comments1Bean item) {
        //    tv_from_username tv_to_username tv_replay
        item.setFabuzheid(fabuzheid);

        helper.addOnClickListener(R.id.tv_from_username);
        helper.addOnClickListener(R.id.tv_to_username);
        helper.addOnClickListener(R.id.tv_replay);

        LinearLayout bless_home_ad_linear = helper.getView(R.id.bless_home_ad_linear);
        String user_name =  SharedPreferencedUtils.getStr(Constants.USER_NAME);
        if(user_name.equals(item.getObserver())||user_name.equals(item.getAnswerer())) {

            helper.setText(R.id.tv_from_username, item.getObserver() == null ? "匿名" : item.getObserver());
            helper.setText(R.id.tv_to_username, item.getAnswerer());
//        helper.setText(R.id.tv_replay,item.getComment());

            TextView tvConent = helper.getView(R.id.tv_replay);
            SpannableString emotionContent = SpanStringUtils.getEmotionContent(EmotionUtils
                    .EMOTION_CLASSIC_TYPE, mContext, tvConent, item.getComment());
            tvConent.setText(emotionContent);
        }else {
            bless_home_ad_linear.setVisibility(View.GONE);
        }
    }
}
