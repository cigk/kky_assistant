package com.kuaikuaiyu.assistant.net;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Author:  Gavin
 * Email:   gavinking@163.com
 * Date:    2016/7/15
 * Desc:
 */
public class HeaderInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        request = request.newBuilder()
                .addHeader("header1", "header1")
                .build();
        return chain.proceed(request);
    }
}
