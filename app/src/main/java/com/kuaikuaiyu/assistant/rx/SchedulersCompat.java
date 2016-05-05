package com.kuaikuaiyu.assistant.rx;

import com.kuaikuaiyu.assistant.net.ApiException;
import com.kuaikuaiyu.assistant.net.HttpResult;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by binlly
 * <p>
 * date: 2016/4/29 15:58
 * desc: Don't break the chain: use RxJava's compose() operator
 */
public class SchedulersCompat {

    private static final Observable.Transformer computationTransformer = observable -> (
            (Observable) observable).subscribeOn(Schedulers.computation()).observeOn
            (AndroidSchedulers.mainThread());

    private static final Observable.Transformer ioTransformer = observable -> ((Observable)
            observable).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());

    private static final Observable.Transformer newTransformer = observable -> ((Observable)
            observable).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers
            .mainThread());

    private static final Observable.Transformer trampolineTransformer = observable -> (
            (Observable) observable).subscribeOn(Schedulers.trampoline()).observeOn
            (AndroidSchedulers.mainThread());


    public static <T> Observable.Transformer<T, T> applyComputationSchedulers() {
        return (Observable.Transformer<T, T>) computationTransformer;
    }

    public static <T> Observable.Transformer<T, T> applyIoSchedulers() {
        return (Observable.Transformer<T, T>) ioTransformer;
    }

    public static <T> Observable.Transformer<T, T> applyNewSchedulers() {
        return (Observable.Transformer<T, T>) newTransformer;
    }

    public static <T> Observable.Transformer<T, T> applyTrampolineSchedulers() {
        return (Observable.Transformer<T, T>) trampolineTransformer;
    }
}
