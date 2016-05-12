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
    @GET("v1/ping")
    Observable<HttpResult<Uuid>> apiTest();

    /**
     * 更新设备信息
     *
     * @return
     */
    @FormUrlEncoded
    @POST(AppConfig.URL_INFO)
    Observable<HttpResult<Uuid>> updateDeviceInfo(@QueryMap Map<String, String> queryMap,
                                                  @FieldMap Map<String, String> fieldMap);

    /**
     * 注册验证码
     *
     * @param queryMap
     * @param fieldMap
     * @return
     */
    @FormUrlEncoded
    @POST(AppConfig.URL_SIGN_UP_SMS)
    Observable<HttpResult> getSignUpSms(@QueryMap Map<String, String> queryMap,
                                        @FieldMap Map<String, String> fieldMap);


    /**
     * 重置密码验证码
     *
     * @param queryMap
     * @param fieldMap
     * @return
     */
    @FormUrlEncoded
    @POST(AppConfig.URL_RESET_PWD_SMS)
    Observable<HttpResult> getResetPwdSms(@QueryMap Map<String, String> queryMap,
                                          @FieldMap Map<String, String> fieldMap);

    /**
     * 册
     *
     * @param queryMap
     * @param fieldMap
     * @return
     */
    @FormUrlEncoded
    @POST(AppConfig.URL_SIGN_UP)
    Observable<HttpResult> signUp(@QueryMap Map<String, String> queryMap,
                                  @FieldMap Map<String, String> fieldMap);

    /**
     * 重置密码
     *
     * @param queryMap
     * @param fieldMap
     * @return
     */
    @FormUrlEncoded
    @POST(AppConfig.URL_RESET_PWD)
    Observable<HttpResult> resetPwd(@QueryMap Map<String, String> queryMap,
                                    @FieldMap Map<String, String> fieldMap);


    /**
     * 登录
     *
     * @return
     */
//    @Headers("Cache-Control: max-age=10")
    @FormUrlEncoded
    @POST(AppConfig.URL_LOGIN)
    Observable<HttpResult<LoginResp>> login(@QueryMap Map<String, String> queryMap,
                                            @FieldMap Map<String, String> fieldMap);


    /**
     * 更新推送id
     *
     * @return
     */
    @FormUrlEncoded
    @POST(AppConfig.URL_UPDATE_PUSH_ID)
    Observable<HttpResult> updatePushId(@QueryMap Map<String, String> queryMap,
                                        @FieldMap Map<String, String> fieldMap);

    /**
     * 修改密码
     *
     * @return
     */
    @FormUrlEncoded
    @POST(AppConfig.URL_CHANGE_PWD)
    Observable<HttpResult> changePwd(@QueryMap Map<String, String> queryMap,
                                     @FieldMap Map<String, String> fieldMap);

    /**
     * 测试重定向
     *
     * @return
     */
    @GET("data")
    Observable<Object> testRedirect(@QueryMap Map<String, String> queryMap);
}


//    RequestBody fileBody = RequestBody.create(MediaType.parse("image/jpeg"), imageFile);
//    MultipartBuilder multipartBuilder = new MultipartBuilder();
//    multipartBuilder.addFormDataPart("photo", imageFile.getName(), fileBody);
//    RequestBody fileRequestBody = multipartBuilder.build();
//
//    mRestClient.getRetrofitService().uploadProfilePhoto(fileRequestBody, sessionId);