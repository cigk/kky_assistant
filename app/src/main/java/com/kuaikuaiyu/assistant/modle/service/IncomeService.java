package com.kuaikuaiyu.assistant.modle.service;

import com.kuaikuaiyu.assistant.app.AppConfig;
import com.kuaikuaiyu.assistant.modle.domain.IncomeAccount;
import com.kuaikuaiyu.assistant.net.HttpResult;

import java.util.Map;

import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Created by binlly
 * <p>
 * date: 2016/5/9 14:42
 * desc:
 */
public interface IncomeService {

    //    @GET("/REST/QRCodeOrder")
    //    Observable<HttpResult<QRCode>> getQRCodeData(@QueryMap Map<String, Integer> queryMap);

    @GET(AppConfig.URL_INCOME_ACCOUNT)
    Observable<HttpResult<IncomeAccount>> getIncomeAccount(@QueryMap Map<String, String> queryMap);
}
