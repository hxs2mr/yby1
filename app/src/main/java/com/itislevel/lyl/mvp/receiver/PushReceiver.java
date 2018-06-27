package com.itislevel.lyl.mvp.receiver;

import android.content.Context;

import cn.jpush.android.api.JPushMessage;
import cn.jpush.android.service.JPushMessageReceiver;
import io.rong.imkit.model.Event;

/**
 * desc:功能说明必写
 * user:itisi
 * date:2018-01-06.10:06
 * path:com.itislevel.lyl.mvp.receiver.PushReceiver
 **/
public class PushReceiver extends JPushMessageReceiver {
    @Override
    public void onTagOperatorResult(Context context, JPushMessage jPushMessage) {
        TagAliasOperatorHelper.getInstance().onTagOperatorResult(context,jPushMessage);
        super.onTagOperatorResult(context, jPushMessage);
    }
    @Override
    public void onCheckTagOperatorResult(Context context,JPushMessage jPushMessage){
        TagAliasOperatorHelper.getInstance().onCheckTagOperatorResult(context,jPushMessage);
        super.onCheckTagOperatorResult(context, jPushMessage);
    }
    @Override
    public void onAliasOperatorResult(Context context, JPushMessage jPushMessage) {
        TagAliasOperatorHelper.getInstance().onAliasOperatorResult(context,jPushMessage);
        super.onAliasOperatorResult(context, jPushMessage);
    }

}
