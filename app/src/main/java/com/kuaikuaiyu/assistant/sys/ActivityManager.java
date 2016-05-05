package com.kuaikuaiyu.assistant.sys;

import android.content.Context;

import com.kuaikuaiyu.assistant.base.BaseActivity;

import java.util.Stack;

/**
 * Author:  Gavin
 * Email:   gavinking@163.com
 * Date:    15-7-20
 * Desc:
 */
public class ActivityManager {
    private static Stack<BaseActivity> activityStack = new Stack<>();

    /**
     * 添加Activity到堆栈
     */
    public static void addActivity(BaseActivity activity) {
        activityStack.add(activity);
    }

    /**
     * 移除Activity出堆栈
     */
    public static void removeActivity(BaseActivity activity) {
        activityStack.remove(activity);
    }

    /**
     * 获取当前Activity（堆栈中最后一个压入的）
     */
    public static BaseActivity currentActivity() {
        if (activityStack.size() == 0)
            return null;
        else
            return activityStack.lastElement();
    }

    /**
     * 结束当前Activity（堆栈中最后一个压入的）
     */
    public static void finishActivity() {
        BaseActivity activity = activityStack.lastElement();
        finishActivity(activity);
    }

    /**
     * 结束指定的Activity
     *
     * @param index 若<0 则为反向序列号
     */
    public static void finishActivity(int index) {
        if (index < activityStack.size() && index + activityStack.size() > 0) {
            finishActivity((activityStack.size() + index) % activityStack.size());
        }
    }

    /**
     * 结束指定的Activity
     */
    public static void finishActivity(BaseActivity activity) {
        if (activity != null && !activity.isFinishing()) {
            activity.finish();
        }
    }

    /**
     * 结束指定类名的Activity
     */
    public static void finishActivity(Class<?> cls) {
        for (int i = 0; i < activityStack.size(); i++) {
            if (activityStack.get(i).getClass().equals(cls)) {
                finishActivity(activityStack.get(i));
                break;
            }
        }
    }


    /**
     * 结束指定类名的Activity以外的Activity
     */
    public static void finishOtherActivities(Class<?> cls) {
        for (int i = 0; i < activityStack.size(); i++) {
            if (!activityStack.get(i).getClass().equals(cls)) {
                finishActivity(activityStack.get(i));
            }
        }
    }

    /**
     * 结束所有Activity
     */
    public static void finishAllActivity() {
        for (int i = 0; i < activityStack.size(); i++) {
            finishActivity(activityStack.get(i));
        }
        activityStack.clear();
    }

    /**
     * 获取指定的Activity
     */
    public static BaseActivity getActivity(Class<?> cls) {
        for (BaseActivity activity : activityStack) {
            if (activity.getClass().equals(cls)) {
                return activity;
            }
        }
        return null;
    }

    /**
     * 判断activity是否启动
     *
     * @param clazz
     * @return
     */
    public static boolean isActivityExist(Class<?> clazz) {
        for (BaseActivity activity : activityStack) {
            if (activity.getClass().equals(clazz)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 退出应用程序
     */
    public static void AppExit(Context context) {
        try {
            finishAllActivity();
            // 杀死该应用进程
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(0);
        } catch (Exception ignored) {
        }
    }

}
