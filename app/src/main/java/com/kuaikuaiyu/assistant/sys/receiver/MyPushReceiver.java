package com.kuaikuaiyu.assistant.sys.receiver;

import android.app.ActivityManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;

import java.util.List;
import java.util.Random;


public class MyPushReceiver extends BroadcastReceiver {
//    private PushItem pushItem;

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle mBundle = intent.getExtras();

//        switch (mBundle.getInt(PushConsts.CMD_ACTION)) {
//            case PushConsts.GET_MSG_DATA:
//                byte[] payload = mBundle.getByteArray("payload");
//                if (payload != null) {
//                    String data = new String(payload);
//                    pushItem = GsonUitl.parse(data, PushItem.class);
//                    if (pushItem != null && pushItem.getType() != null) {
//                        if ("new_order".equals(pushItem.getType())) {
//                            if (ConfigUtil.getAuthToken().isEmpty()) return;
//                            if (isForground(context) && isHomeActivityFront(UIUtil.getContext())) {
//                                EventBus.getDefault().post(new NewOrderEvent(pushItem.getData()));
//                            } else {
//                                sendNotification("快快鱼", "来新订单了",
//                                        "您有一笔快快鱼新订单，请点击查看！",
//                                        HomeActivity.class, null);
//                                MediaPlayerUtil.play(R.raw.new_order);
//                            }
//                        } else if ("preorder".equals(pushItem.getType())) {
//                            Bundle bundle = new Bundle();
//                            bundle.putString(OrderDetailActivity.ORDER_ID, pushItem.getData());
//                            sendNotification("快快鱼", "预约订单提醒",
//                                    "您有一笔快快鱼预约订单距预约送达时间还有15分钟，请点击查看！",
//                                    OrderDetailActivity.class, bundle);
//                        }
//                    }
//                }
//                break;
//
//            default:
//                break;
//        }
    }

    /**
     * 发送Notification
     */
    private void sendNotification(String ticker, String title, String content,
                                  Class clazz, Bundle extra) {
//        NotificationManager manager = (NotificationManager) UIUtil.getContext()
//                .getSystemService(Context.NOTIFICATION_SERVICE);
//        Intent intent = new Intent(UIUtil.getContext(), clazz);
//        intent.putExtra("notifyFlag", true);
//        if (extra != null)
//            intent.putExtra(OrderDetailActivity.EXTRA, extra);
//        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        intent.addFlags(Intent.FILL_IN_DATA);
//        Random rd = new Random();
//        int code = rd.nextInt(1000);
//        PendingIntent pendingIntent = PendingIntent.getActivity(UIUtil.getContext(), code,
//                intent, PendingIntent.FLAG_UPDATE_CURRENT);
//        NotificationCompat.Builder nBuilder = new NotificationCompat.Builder(UIUtil.getContext())
//                .setSmallIcon(R.mipmap.ic_launcher)
//                .setTicker(ticker)
//                .setContentTitle(title)
//                .setContentText(content)
//                .setContentIntent(pendingIntent)
//                .setAutoCancel(true);
//        manager.notify(0, nBuilder.build());
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