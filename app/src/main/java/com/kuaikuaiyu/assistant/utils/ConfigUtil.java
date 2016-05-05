package com.kuaikuaiyu.assistant.utils;

import android.text.TextUtils;

/**
 * Author:  Gavin
 * Email:   gavinking@163.com
 * Date:    2016/5/4
 * Desc:    应用配置相关的工具类
 */
public class ConfigUtil {

    public static final String UUID = "uuid";
    public static final String AUTH_TOKEN = "auth_token";
    public static final String LOGIN_PHONE = "login_phone";

    public static String uuid;
    public static String authToken;

    /**
     * 获取Device Uuid
     *
     * @return
     */
    public static String getUuid() {
        if (TextUtils.isEmpty(uuid)) {
            uuid = SpUtil.get(UUID, "");
        }
        return uuid;
    }

    /**
     * 保存Device Uuid
     *
     * @param deviceUuid
     */
    public static void setUuid(String deviceUuid) {
        uuid = deviceUuid;
        SpUtil.save(UUID, uuid);
    }

    /**
     * 获取Auth Token
     *
     * @return
     */
    public static String getAuthToken() {
        if (TextUtils.isEmpty(authToken)) {
            authToken = SpUtil.get(AUTH_TOKEN, "");
        }
        return authToken;
    }

    /**
     * 保存Auth Token
     *
     * @param token
     */
    public static void setAuthToken(String token) {
        authToken = token;
        SpUtil.save(AUTH_TOKEN, authToken);
    }

    /**
     * 获取登录的账号用于自动填充
     *
     * @return
     */
    public static String getLoginPhone() {
        return SpUtil.get(LOGIN_PHONE, "");
    }

    /**
     * 保存用于自动填充的登录账户
     *
     * @param phone
     * @return
     */
    public static void setLoginPhone(String phone) {
        SpUtil.save(LOGIN_PHONE, phone);
    }
}
