package com.kuaikuaiyu.assistant.model.service;

import com.kuaikuaiyu.assistant.app.AppConfig;
import com.kuaikuaiyu.assistant.model.domain.AlipayUrl;
import com.kuaikuaiyu.assistant.model.domain.IncomeAccount;
import com.kuaikuaiyu.assistant.net.HttpResult;

import java.util.Map;

import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Created by binlly
 * <p>
 * date: 2016/5/9 14:42
 * desc:
 */
public interface IncomeService {

    /**
     * 请求收款流水记录
     *
     * @param queryMap
     * @return
     */
    @GET(AppConfig.URL_INCOME_ACCOUNT)
    Observable<HttpResult<IncomeAccount>> getIncomeAccount(@QueryMap Map<String, String> queryMap);


    /**
     * 支付宝付款码
     *
     * @param queryMap
     * @param fieldMap
     * @return
     */
    @FormUrlEncoded
    @POST(AppConfig.URL_INCOME_ALIPAY)
    Observable<HttpResult<AlipayUrl>> getAlipayUrl(@QueryMap Map<String, String> queryMap,
                                                   @FieldMap Map<String, String> fieldMap);
}
