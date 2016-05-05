package com.kuaikuaiyu.assistant.modle.service;

import com.kuaikuaiyu.assistant.app.AppConfig;
import com.kuaikuaiyu.assistant.modle.domain.LoginResp;
import com.kuaikuaiyu.assistant.modle.domain.Uuid;
import com.kuaikuaiyu.assistant.net.HttpResult;

import java.util.Map;

import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Author:  Gavin
 * Email:   gavinking@163.com
 * Date:    2016/4/30
 * Desc:    账户模块的接口,访问PassServer，(注册、登录等功能)
 */
public interface PassService {

    /**
     * 测试接口
     *
     * @return
     */
    @GET("/v1/ping")
    Observable<HttpResult<Uuid>> apiTest();

    /**
     * 更新设备信息
     *
     * @return
     */
    @FormUrlEncoded
    @POST(AppConfig.URL_INFO)
    Observable<HttpResult<Uuid>> updateDeviceInfo(@QueryMap Map<String, String> queryMap,
                                                  @FieldMap Map<String, String> deviceInfo);

    /**
     * 登录
     *
     * @return
     */
    @FormUrlEncoded
    @POST(AppConfig.URL_LOGIN)
    Observable<HttpResult<LoginResp>> login(@QueryMap Map<String, String> queryMap,
                                            @FieldMap Map<String, String> deviceInfo);

}


//    RequestBody fileBody = RequestBody.create(MediaType.parse("image/jpeg"), imageFile);
//    MultipartBuilder multipartBuilder = new MultipartBuilder();
//    multipartBuilder.addFormDataPart("photo", imageFile.getName(), fileBody);
//    RequestBody fileRequestBody = multipartBuilder.build();
//
//    mRestClient.getRetrofitService().uploadProfilePhoto(fileRequestBody, sessionId);