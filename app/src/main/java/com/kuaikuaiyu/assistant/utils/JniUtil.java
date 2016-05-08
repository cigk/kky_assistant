package com.kuaikuaiyu.assistant.utils;

/**
 * Author:  Gavin
 * Email:   gavinking@163.com
 * Date:    2016/5/6
 * Desc:    JNI相关的工具类
 */
public class JniUtil {

    static {
        System.loadLibrary("kkylib");
    }

    /**
     * 获取签名秘钥
     *
     * @return
     */
    public static native String getSignKey();
}
