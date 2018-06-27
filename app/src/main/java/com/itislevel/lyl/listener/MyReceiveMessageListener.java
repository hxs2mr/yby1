package com.itislevel.lyl.listener;

import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Message;

/**
 * desc:功能说明必写
 * user:itisi
 * date:2017/11/30.16:53
 * path:com.itislevel.lyl.listener.MyReceiveMessageListener
 **/
public class MyReceiveMessageListener  implements RongIMClient.OnReceiveMessageListener{
    /**
     * 收到消息的处理。
     *
     * @param message 收到的消息实体。
     * @param left    剩余未拉取消息数目。
     * @return 收到消息是否处理完成，true 表示自己处理铃声和后台通知，false 走融云默认处理方式。
     */
    @Override
    public boolean onReceived(Message message, int left) {

        return false;
    }
}
