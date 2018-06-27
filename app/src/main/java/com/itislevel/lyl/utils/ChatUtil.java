package com.itislevel.lyl.utils;

import android.content.Context;

import java.util.Map;

import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;

/**
 * desc:即时通讯-工具类---融云
 * user:itisi
 * date:2017/11/30.16:09
 * path:com.itislevel.lyl.utils.ChatUtil
 **/
public class ChatUtil {
    /**
     * <p>连接服务器，在整个应用程序全局，只需要调用一次，需在 {@link # init(Context)} 之后调用。</p>
     * <p>如果调用此接口遇到连接失败，SDK 会自动启动重连机制进行最多10次重连，分别是1, 2, 4, 8, 16, 32, 64, 128, 256, 512秒后。
     * 在这之后如果仍没有连接成功，还会在当检测到设备网络状态变化时再次进行重连。</p>
     *
     * @param token    从服务端获取的用户身份令牌（Token）。
     * @param callback 连接回调。
     * @return RongIM  客户端核心类的实例。
     */
    public static void connect(String token, RongIMClient.ConnectCallback callback) {
        RongIM.connect(token, callback);
    }
    /**
     * <p>断开与融云服务器的连接。当调用此接口断开连接后，仍然可以接收 Push 消息。</p>
     * <p>若想断开连接后不接受 Push 消息，可以调用{@link # logout()}</p>
     */
    public static void disconnect(){
        RongIM.getInstance().disconnect();
    }

    /**
     * 注意：建议在您应用的生命周期里设置接受监听器，比如 Application 的 onCreate() 方法里。
     * 如果在 Activity 里进行设置，当 Activity 被释放回收后，将无法收到事件回调。
     *
     * <p>设置接收消息事件的监听器。所有接收到的消息、通知、状态都经由此处设置的监听器处理。包括私聊消息、讨论组消息、群组消息、聊天室消息以及各种状态。</p>
     * <strong>注意：</strong>如果调用此接口的Activity被释放回收，将无法收到事件回调。
     *
     * @param listener 接收消息的监听器。
     */
    public static void setOnReceiveMessageListener(final RongIMClient.OnReceiveMessageListener listener){
        RongIM.getInstance().setOnReceiveMessageListener(listener);
    }

    /**
     * <p>启动会话界面。</p>
     * <p>使用时，可以传入多种会话类型 {@link io.rong.imlib.model.Conversation.ConversationType}
     * 对应不同的会话类型，开启不同的会话界面。
     * 如果传入的是 {@link io.rong.imlib.model.Conversation.ConversationType#CHATROOM}，sdk 会默认调用
     * {@link RongIMClient#joinChatRoom(String, int, RongIMClient.OperationCallback)} 加入聊天室。
     * 如果你的逻辑是，只允许加入已存在的聊天室，请使用接口 {@link # startChatRoomChat(Context, String, boolean)} 并且第三个参数为
     * true</p>
     *
     * @param context          应用上下文。
     * @param conversationType 会话类型。
     * @param targetId         根据不同的 conversationType，可能是用户 Id、讨论组 Id、群组 Id 或聊天室 Id。
     * @param title            聊天的标题，开发者可以在聊天界面通过 intent.getData().getQueryParameter("title")
     *                         获取该值, 再手动设置为标题。
     */
    public static void startConversation(Context context, Conversation.ConversationType
            conversationType, String targetId, String title){
        RongIM.getInstance().startConversation(context,conversationType,targetId,title);
    }

    /**
     * 启动会话列表界面。
     *
     * @param context               应用上下文。
     * @param supportedConversation 定义会话列表支持显示的会话类型，及对应的会话类型是否聚合显示。
     *                              例如：supportedConversation.put(Conversation.ConversationType
     *                              .PRIVATE.getName(), false) 非聚合式显示 private 类型的会话。
     */
    public static void startConversationList(Context context, Map<String, Boolean> supportedConversation){
        RongIM.getInstance().startConversationList(context,supportedConversation);
    }

    /**
     * 启动聚合后的某类型的会话列表。<br> 例如：如果设置了单聊会话为聚合，则通过该方法可以打开包含所有的单聊会话的列表。
     *
     * @param context          应用上下文。
     * @param conversationType 会话类型。
     */
    public static void startSubConversationList(Context context, Conversation.ConversationType
            conversationType){
        RongIM.getInstance().startSubConversationList(context,conversationType);
    }
}
