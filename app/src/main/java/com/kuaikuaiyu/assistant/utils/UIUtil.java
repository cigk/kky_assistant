package com.kuaikuaiyu.assistant.utils;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.kuaikuaiyu.assistant.app.AssistantApp;


public class UIUtil {

    public static Context getContext() {
        return AssistantApp.getApplication();
    }

    public static Thread getMainThread() {
        return AssistantApp.getMainThread();
    }

    public static long getMainThreadId() {
        return AssistantApp.getMainThreadId();
    }

    /**
     * dip转换px
     */
    public static int dp2px(int dip) {
        final float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (dip * scale + 0.5f);
    }

    /**
     * pxz转换dip
     */
    public static int px2dp(int px) {
        final float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (px / scale + 0.5f);
    }

    /**
     * 获取主线程的handler
     */
    public static Handler getHandler() {
        return AssistantApp.getMainThreadHandler();
    }

    /**
     * 延时在主线程执行runnable
     */
    public static boolean postDelayed(Runnable runnable, long delayMillis) {
        return getHandler().postDelayed(runnable, delayMillis);
    }

    /**
     * 在主线程执行runnable
     */
    public static boolean post(Runnable runnable) {
        return getHandler().post(runnable);
    }

    /**
     * 从主线程looper里面移除runnable
     */
    public static void removeCallbacks(Runnable runnable) {
        getHandler().removeCallbacks(runnable);
    }

    public static View inflate(int resId) {
        return LayoutInflater.from(getContext()).inflate(resId, null);
    }

    /**
     * 获取资源
     */
    public static Resources getResources() {
        return getContext().getResources();
    }

    /**
     * 获取文字
     */
    public static String getString(int resId) {
        return getResources().getString(resId);
    }

    /**
     * 获取文字数组
     */
    public static String[] getStringArray(int resId) {
        return getResources().getStringArray(resId);
    }

    /**
     * 获取dimen
     */
    public static int getDimens(int resId) {
        return getResources().getDimensionPixelSize(resId);
    }

    /**
     * 以兼容方式获取drawable
     */
    public static Drawable getDrawableCompatible(int resId) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return getContext().getDrawable(resId);
        } else {
            return getResources().getDrawable(resId);
        }
    }

    /**
     * 以兼容方式获取颜色选择
     */
    public static ColorStateList getColorStateListCompatible(int resId) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return getContext().getColorStateList(resId);
        } else {
            return getResources().getColorStateList(resId);
        }
    }

    /**
     * 以兼容方式获取颜色资源
     *
     * @param color
     * @return
     */
    public static int getColorCompatible(int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            return getContext().getColor(color);
        else
            return getContext().getResources().getColor(color);
    }

    //判断当前的线程是不是在主线程
    public static boolean isRunInMainThread() {
        return android.os.Process.myTid() == getMainThreadId();
    }

    public static void runInMainThread(Runnable runnable) {
        if (isRunInMainThread()) {
            runnable.run();
        } else {
            post(runnable);
        }
    }

    /**
     * 对toast的简易封装线程安全，可以在非UI线程调用
     */
    public static void showToast(final int resId) {
        showToast(getString(resId));
    }

    /**
     * 对toast的简易封装线程安全，可以在非UI线程调用
     */
    public static void showToast(final String str) {
        if (isRunInMainThread()) {
            showShortToast(str);
        } else {
            post(new Runnable() {
                @Override
                public void run() {
                    showShortToast(str);
                }
            });
        }
    }

    private static Toast mToast;

    private static void showShortToast(String str) {
        if (mToast == null)
            mToast = Toast.makeText(UIUtil.getContext(), str, Toast.LENGTH_SHORT);
        mToast.setText(str);
        mToast.show();
    }

    private static Toast mLongTost;

    public static void showLongToast(String str) {
        if (mLongTost == null)
            mLongTost = Toast.makeText(UIUtil.getContext(), str, Toast.LENGTH_LONG);
        mLongTost.setText(str);
        mLongTost.show();
    }

    private static WindowManager wm;

    /**
     * 获取屏幕宽度
     *
     * @return
     */
    public static int getWindowWidth() {
        return getWindowSize().x;
    }

    /**
     * 获取屏幕高度
     *
     * @return
     */
    public static int getWindowHeight() {
        return getWindowSize().y;
    }

    private static Point getWindowSize() {
        if (wm == null)
            wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);

        Display display = wm.getDefaultDisplay();
        final Point size = new Point();
        display.getSize(size);

        return size;
    }

    /**
     * 获取状态栏高度
     *
     * @return
     */
    public static int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }
}
