package com.kuaikuaiyu.assistant.modle.service;

import com.kuaikuaiyu.assistant.modle.domain.IncomeAccount;
import com.kuaikuaiyu.assistant.net.HttpResult;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by binlly
 * <p>
 * date: 2016/5/9 14:42
 * desc:
 */
public interface IncomeService {
    @GET()
    Observable<HttpResult<IncomeAccount>> getIncomeAccount();
}
