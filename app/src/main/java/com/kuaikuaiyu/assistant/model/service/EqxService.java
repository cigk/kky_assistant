package com.kuaikuaiyu.assistant.model.service;

import com.kuaikuaiyu.assistant.model.domain.HttpResult;
import com.kuaikuaiyu.assistant.model.domain.PageInfo;
import com.kuaikuaiyu.assistant.model.domain.StrollItem;

import java.util.List;

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
    Call<HttpResult<Object, PageInfo, List<StrollItem>>> getShowItems();
}
