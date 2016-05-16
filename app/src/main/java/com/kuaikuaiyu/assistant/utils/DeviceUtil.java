package com.kuaikuaiyu.assistant.utils;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.content.ContextCompat;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;

import com.kuaikuaiyu.assistant.BuildConfig;
import com.kuaikuaiyu.assistant.sys.ActivityManager;

import java.util.Random;

/**
 * Author:  Gavin
 * Email:   gavinking@163.com
 * Date:    2016/5/4
 * Desc:    设备工具类， 提供设备相关的信息
 */
public class DeviceUtil {

    private static final String OS = "android";
    private static final String DEVICE_ID = "device_id";
    private static String sDeviceId;

    /**
     * 获取设备的DeviceId
     *
     * @return
     */
    public static String getDeviceId() {
        if (!TextUtils.isEmpty(sDeviceId)) return sDeviceId;
        sDeviceId = SpUtil.get(DEVICE_ID, "");
        if (!TextUtils.isEmpty(sDeviceId)) return sDeviceId;

        if (ContextCompat.checkSelfPermission(ActivityManager.currentActivity(), Manifest.permission
                .READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED) {
            TelephonyManager tm = (TelephonyManager) UIUtil.getContext()
                    .getSystemService(Context.TELEPHONY_SERVICE);
            sDeviceId = tm.getDeviceId();//865931025340712
        }
        if (TextUtils.isEmpty(sDeviceId)) {
            WifiManager wifi = (WifiManager) UIUtil.getContext().getSystemService(Context.WIFI_SERVICE);
            String mac = wifi.getConnectionInfo().getMacAddress();
            sDeviceId = mac;//02:00:00:00:00:00
        }
        if (TextUtils.isEmpty(sDeviceId)) {
            sDeviceId = Settings.Secure.getString(UIUtil.getContext()
                    .getContentResolver(), Settings.Secure.ANDROID_ID);//462f762ed9935a27
        }
        //fake device id (16位)
        if (TextUtils.isEmpty(sDeviceId)) {
            sDeviceId = getRandomString(16);
        }
        SpUtil.save(DEVICE_ID, sDeviceId);
        return sDeviceId;
    }

    /**
     * 获取屏幕宽高
     *
     * @return
     */
    public static String getScreenSize() {
        DisplayMetrics metrics = UIUtil.getContext().getResources().getDisplayMetrics();
        return metrics.heightPixels + "x" + metrics.widthPixels;

    }

    /**
     * 获取系统版本
     *
     * @return
     */
    public static String getOsVersion() {
        return Build.VERSION.RELEASE;

    }

    /**
     * 获取应用名
     *
     * @return
     */
    public static String getAppName() {
        return BuildConfig.AppName;
    }

    /**
     * 获取应用版本号
     *
     * @return
     */
    public static String getAppVersion() {
        return BuildConfig.VERSION_NAME;
    }

    /**
     * 获取手机型号
     *
     * @return
     */
    public static String getDeviceModle() {
        return Build.MODEL;
    }

    /**
     * 获取系统类型
     *
     * @return
     */
    public static String getOs() {
        return OS;
    }

    /**
     * 生成随机字符串
     *
     * @param length
     * @return
     */
    public static String getRandomString(int length) { //length表示生成字符串的长度
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

}
