package com.itislevel.lyl.mvp.ui.location;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.AppCompatImageView;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.TextView;

import com.itislevel.lyl.R;
import com.itislevel.lyl.mvp.model.bean.Location_Bean;

import static com.itislevel.lyl.mvp.ui.location.Location_CityPickerActivity.loaction_p_id;
import static com.itislevel.lyl.mvp.ui.location.Location_CityPickerActivity.location_p_name;


/**
 * Created by hbh on 2017/4/20.
 * 父布局ViewHolder TimeTeleconMode
 */

public class TrunkHolder extends BaseTelecomHolder {

    private Context mContext;
    private View view;
    public TrunkHolder(Context context, View itemView) {
        super(itemView);
        this.mContext = context;
        this.view = itemView;
    }

    public void bindView(final Location_Bean rdb, final int position, final ItemClickListener listener) {
        ((TextView) view.findViewById(R.id.trunk_1)).setText(rdb.getS_name());
        ((TextView) view.findViewById(R.id.trunk_2)).setText(rdb.getId());
        TextView  trunk_1 = view.findViewById(R.id.trunk_1);
        TextView  trunk_2 = view.findViewById(R.id.trunk_2);
        AppCompatImageView  down_top = view.findViewById(R.id.location_down_top);
        //父布局OnClick监听
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                        loaction_p_id = trunk_1.getText().toString();
                        location_p_name = trunk_2.getText().toString();
                if (listener != null) {
                    if (rdb.isExpand()) {//当前是否是展开
                        RotateAnimation animation = new RotateAnimation(180, 0, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                        animation.setDuration(350);
                        animation.setFillAfter(true);
                        down_top.startAnimation(animation);
                        listener.onHideChildren(rdb);
                        rdb.setExpand(false);
                        trunk_1.setTextColor(Color.parseColor("#282828"));
                    } else {//当前是否是隐藏
                        RotateAnimation animation = new RotateAnimation(0, 180, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                        animation.setDuration(350);
                        animation.setFillAfter(true);
                        down_top.startAnimation(animation);

                        listener.onExpandChildren(rdb);
                        rdb.setExpand(true);
                        trunk_1.setTextColor(Color.parseColor("#57839a"));
                    }
                }
            }
        });
    }



}
