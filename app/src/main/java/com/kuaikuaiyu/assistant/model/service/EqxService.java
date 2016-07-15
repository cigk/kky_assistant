package com.kuaikuaiyu.assistant.model.service;

import com.google.gson.JsonElement;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Author:  Gavin
 * Email:   gavinking@163.com
 * Date:    2016/7/15
 * Desc:
 */
public interface EqxService {

    @GET("/app/scene/tag/tpl?pageSize=20&pageNo=1")
    Call<JSONObject> getShowItems();
}
