package com.kuaikuaiyu.assistant.modle.service;

import com.kuaikuaiyu.assistant.modle.domain.Uuid;
import com.kuaikuaiyu.assistant.net.HttpResult;

import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.http.FieldMap;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * 账户模块的接口
 */
public interface AccountService {

    /**
     * 更新设备信息
     *
     * @return
     */
    @GET("/v1/ping")
    Observable<HttpResult<Uuid>> updateDeviceInfo();

    @Multipart
    @POST("/v1/info")
    Observable<HttpResult<Uuid>> updateDeviceInfo1(@QueryMap Map<String, String> queryMap,
                                                   @FieldMap Map<String, String> deviceInfo,
                                                   @Part RequestBody image);


    @GET("/v1/info")
    Observable<HttpResult<Uuid>> lallala(@QueryMap Map<String, String> opt);

}


//    RequestBody fileBody = RequestBody.create(MediaType.parse("image/jpeg"), imageFile);
//    MultipartBuilder multipartBuilder = new MultipartBuilder();
//    multipartBuilder.addFormDataPart("photo", imageFile.getName(), fileBody);
//    RequestBody fileRequestBody = multipartBuilder.build();
//
//    mRestClient.getRetrofitService().uploadProfilePhoto(fileRequestBody, sessionId);