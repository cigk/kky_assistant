package com.kuaikuaiyu.assistant.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Author:  Gavin
 * Email:   gavinking@163.com
 * Date:    2016/4/30
 * Desc:    SharedPreference 工具类
 */
public class SpUtil {
    private static final String SP_NAME = "config";
    private static SharedPreferences sp;
    private static SharedPreferences.Editor editor;

    static {
        sp = UIUtil.getContext().getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        editor = sp.edit();
    }

    /**
     * put value
     */
    public static void save(String key, String value) {
        if (value != null) editor.putString(key, value).apply();
    }

    public static void save(String key, boolean value) {
        editor.putBoolean(key, value).apply();
    }

    public static void save(String key, float value) {
        editor.putFloat(key, value).apply();
    }

    public static void save(String key, int value) {
        editor.putInt(key, value).apply();
    }

    public static void save(String key, long value) {
        editor.putLong(key, value).apply();
    }

    public static void save(String key, double value) {
        editor.putFloat(key, (float) value).apply();
    }


    /**
     * get value
     */
    public static String get(String key, String defValue) {
        return sp.getString(key, defValue);
    }

    public static boolean get(String key, boolean defValue) {
        return sp.getBoolean(key, defValue);
    }

    public static float get(String key, float defValue) {
        return sp.getFloat(key, defValue);
    }

    public static int get(String key, int defValue) {
        return sp.getInt(key, defValue);
    }

    public static long get(String key, long defValue) {
        return sp.getLong(key, defValue);
    }
}
