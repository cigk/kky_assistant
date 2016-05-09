package com.kuaikuaiyu.assistant.net;

import android.text.TextUtils;

import com.kuaikuaiyu.assistant.app.AppConfig;
import com.kuaikuaiyu.assistant.utils.ConfigUtil;
import com.kuaikuaiyu.assistant.utils.JniUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import timber.log.Timber;

/**
 * Created by Gavin on 2015/7/14.
 * 网络请求参数
 * 封装了SessionId和AccessToken,version
 * 封装请求签名
 */
public class ReqParams {

    public static final String GET = "GET";
    public static final String POST = "POST";
    public static final String PUT = "PUT";
    public static final String DELETE = "DELETE";

    private Map<String, String> queryMap;
    private Map<String, String> fieldMap;
    private Map<String, String> signMap;
    private String method;
    private String url;


    public ReqParams(String method, String url) {
        this.method = method;
        this.url = url;

        queryMap = new HashMap<>();
        signMap = new HashMap<>();
        addQuery("at", ConfigUtil.getAuthToken());
        addQuery("t", String.valueOf((int) (System.currentTimeMillis() / 1000)));

        fieldMap = new HashMap<>();
    }

    /**
     * 添加参数到 bodyParam  若请求不存在body 则添加到query中
     *
     * @param key
     * @param value
     */

    public void addParam(String key, Number value) {
        if (value != null) {
            addParam(key, value.toString());
        }
    }

    public void addParam(String key, String value) {
        if (null != value) {
            value = value.trim();
            fieldMap.put(key, value);
            signMap.put(key, value);
        }
    }

    /**
     * 添加参数到 query
     *
     * @param key
     * @param value
     */
    public void addQuery(String key, String value) {
        if (!TextUtils.isEmpty(value)) {
            value = value.trim();
            queryMap.put(key, value);
            signMap.put(key, value);
        }
    }

    /**
     * 获取请求参数
     *
     * @return
     */
    public Map<String, String> getFieldMap() {
        return this.fieldMap;
    }

    public Map<String, String> getQueryMap() {
        if (queryMap.containsKey("s")) {
            queryMap.remove("s");
        }
        queryMap.put("s", getSignature());
        return queryMap;
    }


    /**
     * 生成签名
     */
    private String getSignature() {
        String mSignature = null;
        List<Map.Entry<String, String>> infoIds = new ArrayList<>(signMap.entrySet());
        StringBuilder signature = new StringBuilder();
        //排序
        Collections.sort(infoIds, new Comparator<Map.Entry<String, String>>() {
            public int compare(Map.Entry<String, String> o1, Map.Entry<String, String> o2) {
                return (o1.getKey()).compareTo(o2.getKey());
            }
        });
        StringBuilder sb = new StringBuilder();
        sb.append(method).append("&").append(url);
        for (int i = 0; i < infoIds.size(); i++) {
            sb.append("&").append(infoIds.get(i).getKey())
                    .append("=").append(infoIds.get(i).getValue());
        }

        Timber.d("sig = %s", sb.toString());
        mSignature = JniUtil.sign(sb.toString());
        //加密  _sig
//        try {
//            SecretKeySpec keySpec = new SecretKeySpec(JniUtil.getSignKey().getBytes(),
//                    "HmacSHA256");
//            Mac mac = Mac.getInstance("HmacSHA256");
//            mac.init(keySpec);
//            byte[] result = mac.doFinal(sb.toString().getBytes("UTF-8"));
//            for (byte b : result) {
//                signature.append(byteToHexString(b));
//            }
//            mSignature = signature.toString();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        return mSignature;
    }

    private String byteToHexString(byte ib) {
        char[] Digit = {
                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'
        };
        char[] ob = new char[2];
        ob[0] = Digit[(ib >>> 4) & 0X0f];
        ob[1] = Digit[ib & 0X0F];
        return new String(ob);
    }
}
