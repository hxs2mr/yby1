package com.itislevel.lyl.listener;

import android.content.Context;

import com.xiaomi.mipush.sdk.MiPushMessage;
import com.xiaomi.mipush.sdk.PushMessageReceiver;

/**
 * desc:功能说明必写
 * user:itisi
 * date:2018-02-08.15:34
 * path:com.itislevel.lyl.listener.SealNotificationReceiver
 **/
public class SealNotificationReceiver extends PushMessageReceiver {
    /* push 通知到达事件*/
    // 返回 false, 会弹出融云 SDK 默认通知; 返回 true, 融云 SDK 不会弹通知, 通知需要由您自定义。
    @Override
    public void onNotificationMessageArrived(Context context, MiPushMessage miPushMessage) {
        super.onNotificationMessageArrived(context, miPushMessage);
    }

    /* push 通知点击事件 */
    // 返回 false, 会走融云 SDK 默认处理逻辑, 即点击该通知会打开会话列表或会话界面; 返回 true, 则由您自定义处理逻辑。
    @Override
    public void onNotificationMessageClicked(Context context, MiPushMessage miPushMessage) {
        super.onNotificationMessageClicked(context, miPushMessage);

    }
}
