package com.itislevel.lyl.mvp.ui.chatkeyboardview;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;


import com.itislevel.lyl.R;

import java.util.List;

/**
 * **********************
 * 功 能:必填必填必填必填必填必填
 * 创建人:itisi
 * 邮  箱:itisivip@qq.com
 * 创建时间:2017/6/23 16:31
 * 修改人:itisi
 * 修改时间: 2017/6/23 16:31
 * 修改内容:itisi
 * *********************
 */

public class EmotionGridViewAdapter extends BaseAdapter {
    private Context mContext;
    private List<String> emotionNames;
    private int itemWidth;
    private int emotion_map_type;

    public EmotionGridViewAdapter(Context context, List<String> emotionNames, int itemWidth, int emotion_map_type) {
        mContext = context;
        this.emotionNames = emotionNames;
        this.itemWidth = itemWidth;
        this.emotion_map_type = emotion_map_type;
    }

    @Override
    public int getCount() {
        // +1 最后一个为删除按钮
        return emotionNames.size()+1;
    }

    @Override
    public String getItem(int i) {
        return emotionNames.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        ImageView iv_emotion=new ImageView(mContext);
        // 设置内边距
        iv_emotion.setPadding(itemWidth/8, itemWidth/8, itemWidth/8, itemWidth/8);
        AbsListView.LayoutParams params = new AbsListView.LayoutParams(itemWidth, itemWidth);
        iv_emotion.setLayoutParams(params);
        //判断是否为最后一个item
        if(position == getCount() - 1) {
            iv_emotion.setImageResource(R.drawable.compose_emotion_delete);
        } else {
            String emotionName = emotionNames.get(position);
            iv_emotion.setImageResource(EmotionUtils.getImgByName(emotion_map_type,emotionName));
        }
        return iv_emotion;
    }
}
