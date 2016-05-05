package com.kuaikuaiyu.assistant.rx;

import com.kuaikuaiyu.assistant.base.BaseView;
import com.kuaikuaiyu.assistant.net.NetUtil;
import com.kuaikuaiyu.assistant.utils.UIUtil;

import rx.Subscriber;

/**
 * Author:  Gavin
 * Email:   gavinking@163.com
 * Date:    2016/5/3
 * Desc:    对Subscriber进行包装，简化开发代码
 */
public abstract class RxSubscriber<T> extends Subscriber<T> {

    private BaseView baseView;

    public RxSubscriber(BaseView baseView) {
        this.baseView = baseView;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (baseView != null)
            baseView.showLoading("加载中…");
        if (!NetUtil.isNetAvailable(UIUtil.getContext())) {
            this.cancel();
            UIUtil.showToast("网络不给力，请联网后重试");
        }
    }

    @Override
    public void onCompleted() {
        if (baseView != null)
            baseView.hideLoading();
    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        UIUtil.showToast(e.getMessage());
        // RxJava中的Subscriber执行onError后并不会执行onComplete,所以这里要调用一下
        onCompleted();
    }

    /**
     * 取消订阅
     */
    public void cancel() {
        this.unsubscribe();
        this.onCompleted();
    }
}
