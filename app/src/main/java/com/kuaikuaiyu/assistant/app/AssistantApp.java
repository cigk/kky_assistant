package com.kuaikuaiyu.assistant.app;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.kuaikuaiyu.assistant.BuildConfig;
import com.kuaikuaiyu.assistant.utils.ImageUtil;
import com.kuaikuaiyu.assistant.utils.logger.Logger;
import com.kuaikuaiyu.assistant.utils.logger.Settings;
import com.squareup.leakcanary.LeakCanary;

import timber.log.Timber;

/**
 * Author:  Gavin
 * Email:   gavinking@163.com
 * Date:    2016/4/29
 * Desc:    Application
 */
public class AssistantApp extends Application {
    // 获取到主线程的上下文
    private static AssistantApp mContext = null;
    // 获取到主线程的handler
    private static Handler mMainThreadHandler = null;
    // 获取到主线程的looper
    private static Looper mMainThreadLooper = null;
    // 获取到主线程
    private static Thread mMainThead = null;
    // 获取到主线程的id
    private static int mMainTheadId;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        mMainThreadHandler = new Handler();
        mMainThreadLooper = getMainLooper();
        mMainThead = Thread.currentThread();
        mMainTheadId = android.os.Process.myTid();
        initTools();
    }

    /**
     * tools init
     */
    private void initTools() {
        // 初始化ImageLoader
        ImageUtil.initImageLoader(getApplicationContext());

        //初始化CrashHandler
        if (!BuildConfig.DEBUG_MODE)
            CrashHandler.getInstance().init(this);

        //初始化Timber
        if (BuildConfig.DEBUG_MODE)
            Timber.plant(new Timber.DebugTree());

        //初始化LeakCanary
        LeakCanary.install(this);

        //初始化CrashWoodpecker
        //        CrashWoodpecker.init(this);

        initLogger();
    }


    public static AssistantApp getApplication() {
        return mContext;
    }

    public static Handler getMainThreadHandler() {
        return mMainThreadHandler;
    }

    public static Looper getMainThreadLooper() {
        return mMainThreadLooper;
    }

    public static Thread getMainThread() {
        return mMainThead;
    }

    public static int getMainThreadId() {
        return mMainTheadId;
    }

    /**
     * 初始化日志工具
     */
    private void initLogger() {
        Logger.initialize(
                new Settings().setLogPriority(BuildConfig.DEBUG_MODE ? Log.VERBOSE : Log.ASSERT)
        );
        Logger.d("Logger init");
    }
}
