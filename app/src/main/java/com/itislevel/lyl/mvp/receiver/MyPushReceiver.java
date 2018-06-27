package com.itislevel.lyl.mvp.receiver;

import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.util.Log;

import com.itislevel.lyl.app.App;
import com.itislevel.lyl.app.Constants;
import com.itislevel.lyl.mvp.model.bean.JPDynamicSendBean;
import com.itislevel.lyl.mvp.model.bean.JPFetSendBean;
import com.itislevel.lyl.mvp.model.bean.JPSuccess;
import com.itislevel.lyl.mvp.model.bean.JPushBean;
import com.itislevel.lyl.mvp.model.bean.JPushFete;
import com.itislevel.lyl.mvp.ui.main.MainActivity;
import com.itislevel.lyl.mvp.ui.myteam.MyTeamActivity;
import com.itislevel.lyl.mvp.ui.user.LoginActivity;
import com.itislevel.lyl.utils.GsonUtil;
import com.itislevel.lyl.utils.SharedPreferencedUtils;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;
import java.util.List;

import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.data.JPushLocalNotification;
import me.leolin.shortcutbadger.ShortcutBadgeException;
import me.leolin.shortcutbadger.ShortcutBadger;

import static com.itislevel.lyl.mvp.ui.main.MainActivity.jiaobiao_number;

public class MyPushReceiver extends BroadcastReceiver {

    private static final String TAG = "JIGUANG-Example";
    @Override
    public void onReceive(Context context, Intent intent) {
        try {
            Bundle bundle = intent.getExtras();
            String string1 = bundle.getString(JPushInterface.EXTRA_EXTRA);
            if(isApplicationBroughtToBackground(context))//判断APP是否进入后台
            {
                if(string1!=null)
                {
                    JPushInterface.addLocalNotification(context,new JPushLocalNotification());
                    jiaobiao_number = jiaobiao_number+1;
                    ShortcutBadger.applyCount(context,jiaobiao_number); //for 1.1.4+
                    try {
                        ShortcutBadger.applyCountOrThrow(context,jiaobiao_number); //for 1.1.3
                    } catch (ShortcutBadgeException e) {
                        e.printStackTrace();
                    }
                }
            }else {
                jiaobiao_number = 0;
                JPushInterface.clearAllNotifications(context);//清楚极光推送的所有提示框消息
            }
            System.out.println("推送的内容1****************************"+string1);

            JPushBean jPushBean1 = GsonUtil.toObject(string1, JPushBean.class);
            String type1 = jPushBean1.getType();
            String[] split1 = type1.split(",");
            String s1 = split1[0];
            String i_or_t = "";//=split1[1];

            if(split1.length>=2)
            {
                i_or_t=split1[1];
            }
            if (s1.equals("adviser")){                                   // 付费咨询
                int anInt = SharedPreferencedUtils.getInt(Constants.BADGE_COUNT_TEAM, 0);
                anInt = anInt + 1;
                SharedPreferencedUtils.setInt(Constants.BADGE_COUNT_TEAM, anInt);
            } else if(s1.equals("share")) {//乐趣分享
                int anInt = SharedPreferencedUtils.getInt(Constants.BADGE_COUNT_SHARE, 0);
                anInt = anInt + 1;
                SharedPreferencedUtils.setInt(Constants.BADGE_COUNT_SHARE, anInt);

            }else if(s1.equals("happy")) {//喜事分享
                int anInt = SharedPreferencedUtils.getInt(Constants.BADGE_COUNT_BLESS, 0);
                anInt = anInt + 1;
                SharedPreferencedUtils.setInt(Constants.BADGE_COUNT_BLESS, anInt);

            }else if(s1.equals("fete")) {//亲情祭祀
                int anInt = SharedPreferencedUtils.getInt(Constants.BADGE_COUNT_FEGE, 0);
                anInt = anInt + 1;
                SharedPreferencedUtils.setInt(Constants.BADGE_COUNT_FEGE, anInt);
            }

            System.out.println("推送的内容====================="+ printBundle(bundle));
            if (JPushInterface.ACTION_REGISTRATION_ID.equals(intent.getAction())) {
                String regId = bundle.getString(JPushInterface.EXTRA_REGISTRATION_ID);
                Logger.d(TAG, "[MyReceiver] 接收Registration Id : " + regId);
                //send the Registration Id to your server...

            } else if (JPushInterface.ACTION_MESSAGE_RECEIVED.equals(intent.getAction())) {

                System.out.println("[MyReceiver] 接收到推送下来的自定义消息****************************"+ bundle.getString(JPushInterface
                        .EXTRA_MESSAGE));
                if (JPushInterface.EXTRA_MESSAGE.equals("login")) {
                    Intent intent1 = new Intent(App.getInstance(), LoginActivity.class);
                    if (bundle != null) {
                        intent1.putExtras(bundle);
                    }
                    App.getInstance().startActivity(intent);

                } else {
                    SharedPreferencedUtils.setStr(Constants.LYL_DETAIL, bundle.getString
                            (JPushInterface.EXTRA_MESSAGE));
                }
            } else if (JPushInterface.ACTION_NOTIFICATION_RECEIVED.equals(intent.getAction())) {
                Logger.d(TAG, "[MyReceiver] 接收到推送下来的通知");
                int notifactionId = bundle.getInt(JPushInterface.EXTRA_NOTIFICATION_ID);
                Logger.d(TAG, "[MyReceiver] 接收到推送下来的通知的ID: " + notifactionId);
                //

                //fete_letter
                if(s1.equals("fete_wishes")||s1.equals("fete_letter"))//送祭语
                {
                    EventBus.getDefault().post(new JPushFete(i_or_t));
                }else if(s1.equals("fete_issue"))//发布祭事
                {
                    EventBus.getDefault().post(new JPFetSendBean("1"));
                }else if(s1.equals("dynamic_point")||s1.equals("dynamic_cmt")||s1.equals("dynamic_gift")){//动态的发布和点赞
                    EventBus.getDefault().post(new JPSuccess(i_or_t));
                }else if(s1.equals("dynamic_issue"))//发布动态
                {
                    EventBus.getDefault().post(new JPDynamicSendBean(i_or_t));
                }
            } else if (JPushInterface.ACTION_NOTIFICATION_OPENED.equals(intent.getAction())) {
                Logger.d(TAG, "[MyReceiver] 用户点击打开了通知");
                //     * type : adviser,10

                String string = bundle.getString(JPushInterface.EXTRA_EXTRA);
                JPushBean jPushBean = GsonUtil.toObject(string, JPushBean.class);
                String type = jPushBean.getType();
                String[] split = type.split(",");
                String s = split[0];
//
//                if (s1.equals("adviser")) {// 付费咨询
//                    int anInt = SharedPreferencedUtils.getInt(Constants.BADGE_COUNT_TEAM, 0);
//                    anInt = anInt - 1;
//                    SharedPreferencedUtils.setInt(Constants.BADGE_COUNT_TEAM, anInt);
//                } else if(s1.equals("share")) {//乐趣分享
//                    int anInt = SharedPreferencedUtils.getInt(Constants.BADGE_COUNT_SHARE, 0);
//                    anInt = anInt - 1;
//                    SharedPreferencedUtils.setInt(Constants.BADGE_COUNT_SHARE, anInt);
//
//                }else if(s1.equals("happy")) {//喜事分享
//                    int anInt = SharedPreferencedUtils.getInt(Constants.BADGE_COUNT_BLESS, 0);
//                    anInt = anInt - 1;
//                    SharedPreferencedUtils.setInt(Constants.BADGE_COUNT_BLESS, anInt);
//
//                }else if(s1.equals("fete")) {//亲情祭祀
//                    int anInt = SharedPreferencedUtils.getInt(Constants.BADGE_COUNT_FEGE, 0);
//                    anInt = anInt - 1;
//                    SharedPreferencedUtils.setInt(Constants.BADGE_COUNT_FEGE, anInt);
//
//                }

                Intent i = null;
                //打开的界面
                if (s.equals("adviser")) {
                    i = new Intent(context, MyTeamActivity.class);
                    // 付费咨询
//                    int anInt = SharedPreferencedUtils.getInt(Constants.BADGE_COUNT_TEAM, 0);
//                    anInt = anInt + 1;
//                    SharedPreferencedUtils.setInt(Constants.BADGE_COUNT_TEAM, anInt);
                } else {
                    i = new Intent(context, MainActivity.class);
                }
                i.putExtras(bundle);
                //i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                context.startActivity(i);

            } else if (JPushInterface.ACTION_RICHPUSH_CALLBACK.equals(intent.getAction())) {
                Logger.d(TAG, "[MyReceiver] 用户收到到RICH PUSH CALLBACK: " + bundle.getString
                        (JPushInterface.EXTRA_EXTRA));
                //在这里根据 JPushInterface.EXTRA_EXTRA 的内容处理代码，比如打开新的Activity， 打开一个网页等..

            } else if (JPushInterface.ACTION_CONNECTION_CHANGE.equals(intent.getAction())) {
                boolean connected = intent.getBooleanExtra(JPushInterface
                        .EXTRA_CONNECTION_CHANGE, false);
                Logger.w(TAG, "[MyReceiver]" + intent.getAction() + " connected state change to"
                        + connected);
            } else {
                Logger.d(TAG, "[MyReceiver] Unhandled intent - " + intent.getAction());
            }
             processCustomMessage(context, bundle);
        } catch (Exception e) {

        }

    }

    // 打印所有的 intent extra 数据
    private static String printBundle(Bundle bundle) {
        StringBuilder sb = new StringBuilder();
        for (String key : bundle.keySet()) {
            if (key.equals(JPushInterface.EXTRA_NOTIFICATION_ID)) {
                sb.append("\nkey:" + key + ", value:" + bundle.getInt(key));
            } else if (key.equals(JPushInterface.EXTRA_CONNECTION_CHANGE)) {
                sb.append("\nkey:" + key + ", value:" + bundle.getBoolean(key));
            } else if (key.equals(JPushInterface.EXTRA_EXTRA)) {
                if (TextUtils.isEmpty(bundle.getString(JPushInterface.EXTRA_EXTRA))) {
//                    Logger.i(TAG, "This message has no Extra data");
                    continue;
                }
                try {
                    JSONObject json = new JSONObject(bundle.getString(JPushInterface.EXTRA_EXTRA));
                    Iterator<String> it = json.keys();

                    while (it.hasNext()) {
                        String myKey = it.next();
                        sb.append("\nkey:" + key + ", value: [" +
                                myKey + " - " + json.optString(myKey) + "]");
                    }
                } catch (JSONException e) {
//                    Logger.e(TAG, "Get message extra JSON error!");
                }

            } else {
                sb.append("\nkey:" + key + ", value:" + bundle.getString(key));
            }
        }
        return sb.toString();
    }

    //send msg to MainActivity
    private void processCustomMessage(Context context, Bundle bundle) {
//        if (MainActivity.isForeground) {
//            String message = bundle.getString(JPushInterface.EXTRA_MESSAGE);
//            String extras = bundle.getString(JPushInterface.EXTRA_EXTRA);
//            Intent msgIntent = new Intent(MainActivity.MESSAGE_RECEIVED_ACTION);
//            msgIntent.putExtra(MainActivity.KEY_MESSAGE, message);
//            if (!ExampleUtil.isEmpty(extras)) {
//                try {
//                    JSONObject extraJson = new JSONObject(extras);
//                    if (extraJson.length() > 0) {
//                        msgIntent.putExtra(MainActivity.KEY_EXTRAS, extras);
//                    }
//                } catch (JSONException e) {
//
//                }
//
//            }
//            LocalBroadcastManager.getInstance(context).sendBroadcast(msgIntent);
//
//        }
            Intent msgIntent = new Intent("myaction");
            msgIntent.putExtras(bundle);
            LocalBroadcastManager.getInstance(context).sendBroadcast(msgIntent);
    }
    public static boolean isApplicationBroughtToBackground(final Context context) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> tasks = am.getRunningTasks(1);
        if (!tasks.isEmpty()) {
            ComponentName topActivity = tasks.get(0).topActivity;
            if (!topActivity.getPackageName().equals(context.getPackageName())) {
                Log.i("后台", "aaaaaaaaaaa");
                return true;
            }
        }
        Log.i("前台","bbbbbbbbbbbbbbb");
        return false;
    }
}
