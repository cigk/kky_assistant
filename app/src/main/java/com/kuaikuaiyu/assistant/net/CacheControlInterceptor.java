package com.kuaikuaiyu.assistant.net;

import com.kuaikuaiyu.assistant.utils.UIUtil;
import com.kuaikuaiyu.assistant.utils.logger.Logger;

import java.io.IOException;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Author:  Gavin
 * Email:   gavinking@163.com
 * Date:    2016/5/10
 * Desc:    缓存处理的拦截器
 */
public class CacheControlInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {

        //TODO need checked with GET rquests
        Request request = chain.request();
        if (!NetUtil.isNetAvailable(UIUtil.getContext()) && "GET".equals(request.method())) {
            Logger.d("cacheControl = %s", CacheControl.FORCE_CACHE);
            request = request.newBuilder()
                    .cacheControl(CacheControl.FORCE_CACHE)
                    .build();
        }
        Response originalResponse = chain.proceed(request);
        if (NetUtil.isNetAvailable(UIUtil.getContext())) {
            String cacheControl;
            if ("GET".equals(request.method())) {
                //GET请求缓存5秒
                cacheControl = "max-age=10";
            } else {
                //读接口上的@Headers里的配置
                cacheControl = request.cacheControl().toString();
            }
            Logger.d("cacheControl = %s", cacheControl);
            return originalResponse.newBuilder()
                    .header("Cache-Control", cacheControl)
                    .removeHeader("Pragma")
                    .build();
        } else {
            return originalResponse.newBuilder()
                    .header("Cache-Control", "public, only-if-cached, max-stale=2419200")
                    .removeHeader("Pragma")
                    .build();
        }
    }
}
