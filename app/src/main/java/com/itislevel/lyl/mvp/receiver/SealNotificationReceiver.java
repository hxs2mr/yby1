package com.itislevel.lyl.mvp.receiver;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import com.itislevel.lyl.R;
import com.itislevel.lyl.app.App;
import com.itislevel.lyl.app.Constants;
import com.itislevel.lyl.mvp.model.bean.PushBean;
import com.itislevel.lyl.mvp.ui.funsharing.FunsharingHomeActivity;
import com.itislevel.lyl.mvp.ui.myteam.MyTeamActivity;
import com.itislevel.lyl.utils.GsonUtil;
import com.itislevel.lyl.utils.SharedPreferencedUtils;
import com.orhanobut.logger.Logger;
import com.wenming.library.NotifyUtil;

import java.util.Random;

import io.rong.imkit.MainActivity;
import io.rong.push.notification.PushMessageReceiver;
import io.rong.push.notification.PushNotificationMessage;

public class SealNotificationReceiver extends PushMessageReceiver {


    @Override
    public boolean onNotificationMessageArrived(Context context, PushNotificationMessage
            pushNotificationMessage) {
        // 返回 false, 会弹出融云 SDK 默认通知; 返回 true, 融云 SDK 不会弹通知, 通知需要由您自定义。
        Logger.e("itisip" + pushNotificationMessage.getPushContent());
        Logger.e("itisip:" + GsonUtil.obj2JSON(pushNotificationMessage));

//        RxBus.getInstance().post(RxBus.getInstance().getTag(BlessingHomeActivity.class,
//                RxBus.TAG_UPDATE), "push");

//        MyTeamActivity
        Random random=new Random();
        int requestCode = random.nextInt();
//        Intent intent = new Intent(App.getInstance(), MyTeamActivity.class);
        Intent intent=null;


        String pushData1 = pushNotificationMessage.getPushData();
        PushBean pushBean = GsonUtil.toObject(pushData1, PushBean.class);
        if (pushBean!=null){
            String pushData = pushBean.getPushData();
            String[] pushStrArr=pushData.split(",");
            String type = pushStrArr[0];
            if (type.equals("adviser")){
                intent = new Intent(App.getInstance(), MyTeamActivity.class);
                // 付费咨询
                int anInt = SharedPreferencedUtils.getInt(Constants.BADGE_COUNT_TEAM, 0);
                anInt = anInt + 1;
                SharedPreferencedUtils.setInt(Constants.BADGE_COUNT_TEAM,anInt);
            }
            else if(type.equals("share")){
                intent = new Intent(App.getInstance(), FunsharingHomeActivity.class);

            }
            else if (type.equals("happy")){
//                intent = new Intent(App.getInstance(), BlessingHomeActivity.class);
                intent = new Intent(App.getInstance(), MainActivity.class);
            }
            else if (type.equals("fete")){
                // TODO: 2018-02-28 需要省市id
                intent = new Intent(App.getInstance(), MainActivity.class);
            }
            else{
                intent = new Intent(App.getInstance(), MainActivity.class);

            }

        }else{
            intent = new Intent(App.getInstance(), MainActivity.class);
        }

        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent pIntent = PendingIntent.getActivity(App.getInstance(),
                requestCode, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        int smallIcon = R.mipmap.ic_launcher;
        String ticker = "您有一条新通知";
        String title = "您有一条新通知";
        String content = pushNotificationMessage.getPushContent().substring(pushNotificationMessage.getPushContent().indexOf(":")+1,pushNotificationMessage.getPushContent().length());

        NotifyUtil notify1 = new NotifyUtil(App.getInstance(), 1);
        notify1.notify_normal_singline(pIntent, smallIcon, ticker, title, content, true, true,
                false);

        return true;

    }

    @Override
    public boolean onNotificationMessageClicked(Context context, PushNotificationMessage
            pushNotificationMessage) {
        // 返回 false, 会走融云 SDK 默认处理逻辑, 即点击该通知会打开会话列表或会话界面; 返回 true, 则由您自定义处理逻辑。
        return false;
    }
}
