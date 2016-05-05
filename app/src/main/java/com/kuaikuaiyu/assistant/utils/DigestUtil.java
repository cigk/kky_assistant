package com.kuaikuaiyu.assistant.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;

/**
 * Author:  Gavin
 * Email:   gavinking@163.com
 * Date:    2016/5/4
 * Desc:    摘要工具类
 */
public class DigestUtil {

    /**
     * 计算字符串MD5值
     *
     * @param str 需要计算的字符串
     * @return String
     */
    public static String getMd5(String str) {
        try {
            return getMd5(str.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getMd5(byte[] bytes) {
        try {
            byte[] hash = MessageDigest.getInstance("MD5").digest(bytes);
            StringBuilder hex = new StringBuilder(hash.length * 2);
            for (byte b : hash) {
                if ((b & 0xFF) < 0x10)
                    hex.append("0");
                hex.append(Integer.toHexString(b & 0xFF));
            }
            return hex.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
