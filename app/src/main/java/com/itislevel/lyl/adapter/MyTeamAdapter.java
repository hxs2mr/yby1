package com.itislevel.lyl.adapter;

import android.app.Activity;
import android.support.annotation.LayoutRes;
import android.text.SpannableString;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.itislevel.lyl.R;
import com.itislevel.lyl.app.App;
import com.itislevel.lyl.mvp.model.bean.MeiZiBean;
import com.itislevel.lyl.mvp.model.bean.MyTeamBean;
import com.itislevel.lyl.mvp.ui.chatkeyboardview.EmotionUtils;
import com.itislevel.lyl.mvp.ui.chatkeyboardview.SpanStringUtils;

import q.rorbin.badgeview.Badge;
import q.rorbin.badgeview.QBadgeView;


public class MyTeamAdapter extends BaseQuickAdapter<MyTeamBean.ListBean, BaseViewHolder> {

    Activity activity;

    public MyTeamAdapter(@LayoutRes int layoutResId, Activity activity) {
        super(layoutResId);
        this.activity=activity;
    }


    @Override
    protected void convert(BaseViewHolder helper, final MyTeamBean.ListBean item) {

        // tv_title tv_problem
        helper.addOnClickListener(R.id.tv_title);
        helper.addOnClickListener(R.id.tv_problem);

        TextView textView= helper.getView(R.id.tv_title);
//        Badge badge;
//
//        badge = new QBadgeView(activity).bindTarget(textView);
//        badge.setBadgeGravity(Gravity.START | Gravity.TOP);
//        badge.setGravityOffset(5,5,true);
//        badge.setBadgeTextSize(10, true);
//        badge.setBadgePadding(3, true);
//        badge.setBadgeTextColor(activity.getResources().getColor(R.color.colorRed));
//        badge.setBadgeBackgroundColor(activity.getResources().getColor(R.color.colorRed));
//        badge.setOnDragStateChangedListener(new Badge.OnDragStateChangedListener() {
//            @Override
//            public void onDragStateChanged(int dragState, Badge badge, View targetView) {
//
//            }
//        });
        if (item.getIsreply()==0){
//            badge.setBadgeNumber(1);
            helper.setVisible(R.id.msg_tip_red,true);
        }else{
//            badge.setBadgeNumber(0);
            helper.setVisible(R.id.msg_tip_red,false);

        }
//        helper.setText(R.id.tv_title,item.getNickname()+"提问:");
        textView.setText(item.getNickname()+"提问:");
//        helper.setText(R.id.tv_problem,item.getContent());

        TextView tvConent=helper.getView(R.id.tv_problem);
        SpannableString emotionContent = SpanStringUtils.getEmotionContent(EmotionUtils
                .EMOTION_CLASSIC_TYPE, mContext, tvConent, item.getContent());
        tvConent.setText(emotionContent);


    }

}
