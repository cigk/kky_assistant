package com.kuaikuaiyu.assistant.rx;

import com.kuaikuaiyu.assistant.net.ApiException;
import com.kuaikuaiyu.assistant.net.HttpResult;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Author:  Gavin
 * Email:   gavinking@163.com
 * Date:    2016/5/2
 * Desc:    将HttpResult<T>的observable转化为<T>的observable,并且在io shceduler这行耗时操作，
 * 在UI线程执行更新UI操作
 *
 * @param <T>
 */
public class IoTransformer<T> implements Observable.Transformer<HttpResult<T>, T> {
    @Override
    public Observable<T> call(Observable<HttpResult<T>> httpResultObservable) {
        return httpResultObservable.map(new Func1<HttpResult<T>, T>() {
            @Override
            public T call(HttpResult<T> httpResult) {
                if (httpResult.code != 0) {
                    throw new ApiException(httpResult.code, httpResult.message);
                }
                return httpResult.data;

            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }
}