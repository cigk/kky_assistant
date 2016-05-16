package com.kuaikuaiyu.assistant.modle.service;

import com.kuaikuaiyu.assistant.app.AppConfig;
import com.kuaikuaiyu.assistant.modle.domain.BillRecord;
import com.kuaikuaiyu.assistant.modle.domain.ShopInfo;
import com.kuaikuaiyu.assistant.net.HttpResult;

import java.util.Map;

import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * 账户模块的接口
 */
public interface AccountService {

    /**
     * 绑定银行卡
     *
     * @param queryMap
     * @param fieldMap
     * @return
     */
    @FormUrlEncoded
    @PUT(AppConfig.URL_BIND_BANK)
    Observable<HttpResult> bindBankCard(@QueryMap Map<String, String> queryMap,
            @FieldMap Map<String, String> fieldMap);


    /**
     * 绑定支付宝
     *
     * @param queryMap
     * @param fieldMap
     * @return
     */
    @FormUrlEncoded
    @PUT(AppConfig.URL_BIND_ALIPAY)
    Observable<HttpResult> bindAlipay(@QueryMap Map<String, String> queryMap,
            @FieldMap Map<String, String> fieldMap);

    /**
     * 获取商店信息
     *
     * @param queryMap
     * @return
     */
    @GET(AppConfig.URL_SHOP_INFO)
    Observable<HttpResult<ShopInfo>> getShopInfo(@QueryMap Map<String, String> queryMap);


    /**
     * 获取账单记录
     *
     * @param queryMap
     * @return
     */
    @GET(AppConfig.URL_BILL_RECORD)
    Observable<HttpResult<BillRecord>> getBillRecords(@QueryMap Map<String, String> queryMap);

    /**
     * 提现
     *
     * @param queryMap
     * @return
     */
    @FormUrlEncoded
    @POST(AppConfig.URL_WITHDRAW)
    Observable<HttpResult<ShopInfo>> withdraw(@QueryMap Map<String, String> queryMap,
            @FieldMap Map<String, String> fieldMap);
}