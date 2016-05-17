package com.kuaikuaiyu.assistant.sys.receiver;

import android.app.ActivityManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;

import com.igexin.sdk.PushConsts;
import com.kuaikuaiyu.assistant.R;
import com.kuaikuaiyu.assistant.modle.domain.PushItem;
import com.kuaikuaiyu.assistant.sys.event.UpdateShopInfo;
import com.kuaikuaiyu.assistant.utils.GsonUitl;
import com.kuaikuaiyu.assistant.utils.UIUtil;

import org.greenrobot.eventbus.EventBus;

import java.util.List;
import java.util.Random;


public class MyPushReceiver extends BroadcastReceiver {

    public static final String NOTIFICATION_FLAG = "notification_flag";
    public static final String NOTIFICATION_EXTRA = "notification_extra";

    private PushItem pushItem;

    @Override
    public void onReceive(Context context, Intent intent) {
        EventBus.getDefault().post(new UpdateShopInfo());
        Bundle mBundle = intent.getExtras();

        switch (mBundle.getInt(PushConsts.CMD_ACTION)) {
            case PushConsts.GET_MSG_DATA:
                byte[] payload = mBundle.getByteArray("payload");
                if (payload != null) {
                    String data = new String(payload);
                    pushItem = GsonUitl.parse(data, PushItem.class);
                    if (pushItem != null && pushItem.getType() != null) {

                    }
                }
                break;

            default:
                break;
        }
    }

    /**
     * 发送Notification
     */
    private void sendNotification(String ticker, String title, String content,
                                  Class clazz, Bundle extra) {
        NotificationManager manager = (NotificationManager) UIUtil.getContext()
                .getSystemService(Context.NOTIFICATION_SERVICE);
        Intent intent = new Intent(UIUtil.getContext(), clazz);
        intent.putExtra(NOTIFICATION_FLAG, true);
        if (extra != null)
            intent.putExtra(NOTIFICATION_EXTRA, extra);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FILL_IN_DATA);
        Random rd = new Random();
        int code = rd.nextInt(1000);
        PendingIntent pendingIntent = PendingIntent.getActivity(UIUtil.getContext(), code,
                intent, PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(UIUtil.getContext());
        builder.setSmallIcon(R.mipmap.ic_launcher)
                .setTicker(ticker)
                .setContentTitle(title)
                .setContentText(content)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);
        manager.notify(0, builder.build());
    }

    private boolean isHomeActivityFront(Context context) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        ComponentName cn = am.getRunningTasks(1).get(0).topActivity;
        return cn.getClassName() != null
                && "com.kuaikuaiyu.merchant.ui.activity.HomeActivity".equals(cn.getClassName());
    }

    public static boolean isForground(Context context) {
        ActivityManager activityManager = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> appProcesses = activityManager
                .getRunningAppProcesses();
        for (ActivityManager.RunningAppProcessInfo appProcess : appProcesses) {
            if (appProcess.processName.equals(context.getPackageName())) {
                return (appProcess.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND);
            }
        }
        return false;
    }
}