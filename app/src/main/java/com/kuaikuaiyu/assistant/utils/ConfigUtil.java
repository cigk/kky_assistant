package com.kuaikuaiyu.assistant.utils;

import android.text.TextUtils;
import android.util.Base64;

import com.kuaikuaiyu.assistant.model.domain.ShopInfo;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

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
    public static final String SHOP_NAME = "shop_name";
    public static final String SHOP_MOBILE = "shop_moble";
    public static final String SHOP_INFO = "shop_info";

    public static String uuid;
    public static String authToken;
    public static String shopName;
    public static String shopMobile;
    public static ShopInfo shopInfo;

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

    /**
     * 保存店铺名
     *
     * @param name
     * @return
     */
    public static void setShopName(String name) {
        shopName = name;
        SpUtil.save(SHOP_NAME, shopName);
    }

    /**
     * 获取店铺名称
     *
     * @return
     */
    public static String getShopName() {
        if (TextUtils.isEmpty(shopName)) {
            shopName = SpUtil.get(SHOP_NAME, "");
        }
        return shopName;
    }

    /**
     * 保存店铺手机号码
     *
     * @param mobile
     */
    public static void setShopMobile(String mobile) {
        shopMobile = mobile;
        SpUtil.save(SHOP_MOBILE, shopMobile);
    }

    /**
     * 获取店铺手机号
     *
     * @return
     */
    public static String getShopMobile() {
        if (TextUtils.isEmpty(shopMobile)) {
            shopMobile = SpUtil.get(SHOP_MOBILE, "");
        }
        return shopMobile;
    }

    /**
     * 获取ShopInfo
     *
     * @return
     */
    public static ShopInfo getShopInfo() {
        if (null == shopInfo) {
            shopInfo = getBeanFromSp(SHOP_INFO);
        }
        return shopInfo;
    }

    /**
     * 保存ShopInfo
     *
     * @param info
     */
    public static void setShopInfo(ShopInfo info) {
        shopInfo = info;
        saveBeanToSp(shopInfo, SHOP_INFO);
    }

    /**
     * 保存对象到SharedPreference
     *
     * @param obj
     */
    public static void saveBeanToSp(Object obj, String key) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos;
        try {
            oos = new ObjectOutputStream(baos);
            oos.writeObject(obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 将Product对象转换成byte数组，并将其进行base64编码
        String objStr = Base64.encodeToString(baos.toByteArray(), Base64.URL_SAFE);
        SpUtil.save(key, objStr);
    }

    /**
     * 从SharedPreference取出对象
     *
     * @return
     */
    public static <T> T getBeanFromSp(String key) {
        String str = SpUtil.get(key, null);
        if (null == str) return null;
        T obj = null;
        // 对Base64格式的字符串进行解码
        byte[] base64Bytes = Base64.decode(str.getBytes(), Base64.URL_SAFE);
        ByteArrayInputStream bais = new ByteArrayInputStream(base64Bytes);
        ObjectInputStream ois;
        try {
            ois = new ObjectInputStream(bais);
            obj = (T) ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 从ObjectInputStream中读取Product对象
        return obj;
    }
}
