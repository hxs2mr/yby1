package com.itislevel.lyl.mvp.ui.email.utils;

import android.content.Context;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;

import com.itislevel.lyl.mvp.ui.bus.FindEdBus;
import com.itislevel.lyl.mvp.ui.bus.FollowEdBus;
import com.itislevel.lyl.mvp.ui.bus.TonEdBus;
import com.itislevel.lyl.mvp.ui.email.adapter.EmotionGridViewAdapter;
import com.itislevel.lyl.utils.SAToast;

import org.greenrobot.eventbus.EventBus;

import static com.itislevel.lyl.mvp.ui.main.dynamic.DynamicFragment.viewpager_postion;


/**
 * Created by zejian
 * Time  16/1/8 下午5:05
 * Email shinezejian@163.com
 * Description:点击表情的全局监听管理类
 */
public class GlobalOnItemClickManagerUtils {

    private static GlobalOnItemClickManagerUtils instance;
    private EditText mEditText;//输入框
    private static Context mContext;

    public static GlobalOnItemClickManagerUtils getInstance(Context context) {
        mContext=context;
        if (instance == null) {
            synchronized (GlobalOnItemClickManagerUtils.class) {
                if(instance == null) {
                    instance = new GlobalOnItemClickManagerUtils();
                }
            }
        }
        return instance;
    }

    public void attachToEditText(EditText editText) {
        mEditText = editText;
    }

    public AdapterView.OnItemClickListener getOnItemClickListener(final int emotion_map_type) {
        return new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object itemAdapter = parent.getAdapter();
                    // 点击的是表情
                    EmotionGridViewAdapter emotionGvAdapter = (EmotionGridViewAdapter) itemAdapter;

                    if (position == emotionGvAdapter.getCount() - 1) {
                        // 如果点击了最后一个回退按钮,则调用删除键事件
                        mEditText.dispatchKeyEvent(new KeyEvent(
                                KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_DEL));
                    } else {
                        // 如果点击了表情,则添加到输入框中
                        String emotionName = emotionGvAdapter.getItem(position);

                        // 获取当前光标位置,在指定位置上添加表情图片文本
                        int curPosition = mEditText.getSelectionStart();
                        if (viewpager_postion == 0)//代表的是关注
                        {
                            EventBus.getDefault().post(new FollowEdBus(emotionName,curPosition));
                        } else if (viewpager_postion == 1)//代表的是发现
                        {
                            EventBus.getDefault().post(new FindEdBus(emotionName,curPosition));
                        }else {
                            EventBus.getDefault().post(new TonEdBus(emotionName,curPosition));
                        }
                     /*   StringBuilder sb = new StringBuilder(mEditText.getText().toString());
                        sb.insert(curPosition, emotionName);

                        // 特殊文字处理,将表情等转换一下
                        mEditText.setText(SpanStringUtils.getEmotionContent(emotion_map_type,
                                mContext, mEditText, sb.toString()));
                        // 将光标设置到新增完表情的右侧
                        mEditText.setSelection(curPosition + emotionName.length());*/
                    }

            }
        };
    }

}
