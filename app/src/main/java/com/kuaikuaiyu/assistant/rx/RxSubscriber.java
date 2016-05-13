package com.kuaikuaiyu.assistant.rx;


import android.content.Intent;

import com.kuaikuaiyu.assistant.base.BaseActivity;
import com.kuaikuaiyu.assistant.base.BaseView;
import com.kuaikuaiyu.assistant.net.ApiException;
import com.kuaikuaiyu.assistant.net.NetUtil;
import com.kuaikuaiyu.assistant.sys.ActivityManager;
import com.kuaikuaiyu.assistant.ui.pass.login.LoginActivity;
import com.kuaikuaiyu.assistant.utils.ConfigUtil;
import com.kuaikuaiyu.assistant.utils.UIUtil;

import retrofit2.adapter.rxjava.HttpException;
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
//            this.cancel();
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
        //有网的情况下需要提示用户出错了，没网的时候提示网络不给力
//        if (NetUtil.isNetAvailable(UIUtil.getContext())) {
//            UIUtil.showToast(e.getMessage());
//        } else {
//            UIUtil.showToast("网络不给力，请联网后重试");
//        }

        if (e instanceof HttpException) {
            HttpException exception = (HttpException) e;
            handleHttpException(exception);
        } else if (e instanceof ApiException) {
            ApiException exception = (ApiException) e;
            handleApiException(exception);
        }
        // Java中的Subscriber执行onError后并不会执行onComplete,所以这里要调用一下
        onCompleted();
    }

    /**
     * 根据不同的Api异常做相应的处理并提示用户
     *
     * @param e
     */
    private void handleApiException(ApiException e) {
        if (e.code() == ApiException.ERROR_CODE_INVALID_AUTHENTICATION_TOKEN) {
            handleAuthFail();
        } else {
            UIUtil.showToast(e.getMessage());
        }
    }

    /**
     * 根据不同的Http异常做出相应的处理并给用户相应的提示
     *
     * @param e
     */
    private void handleHttpException(HttpException e) {
        switch (e.code()) {
            case 400:
                UIUtil.showToast("错误请求");
                break;

            case 401://授权失败
                handleAuthFail();
                break;

            case 408:
                UIUtil.showToast("请求超时，请检查网络后重试…");
                break;

            case 500:
                UIUtil.showToast("服务器开小差了，请稍后重试…");
                break;

            default:
                break;

        }
    }

    /**
     * 处理授权失败
     */
    private void handleAuthFail() {
        UIUtil.showToast("授权超时，请重新登录");
        UIUtil.postDelayed(() -> {
                    ConfigUtil.setAuthToken("");
                    BaseActivity activity = ActivityManager.currentActivity();
                    Intent intent = new Intent(activity, LoginActivity.class);
                    activity.startActivity(intent);
                    ActivityManager.finishOtherActivities(LoginActivity.class);
                }, 1000
        );
    }

    /**
     * 取消订阅
     */
    public void cancel() {
        this.unsubscribe();
        this.onCompleted();
    }
}
