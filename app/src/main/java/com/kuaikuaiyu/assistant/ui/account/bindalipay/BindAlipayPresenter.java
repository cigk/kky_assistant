package com.kuaikuaiyu.assistant.ui.account.bindalipay;

import com.kuaikuaiyu.assistant.app.AppConfig;
import com.kuaikuaiyu.assistant.base.BasePresenter;
import com.kuaikuaiyu.assistant.modle.service.AccountService;
import com.kuaikuaiyu.assistant.net.HttpResult;
import com.kuaikuaiyu.assistant.net.ReqParams;
import com.kuaikuaiyu.assistant.rx.IoTransformer;
import com.kuaikuaiyu.assistant.rx.RxSubscriber;
import com.kuaikuaiyu.assistant.utils.ConfigUtil;

import javax.inject.Inject;

/**
 * Author:  Gavin
 * Email:   gavinking@163.com
 * Date:    2016/5/10
 * Desc:    绑定银行卡
 */
public class BindAlipayPresenter implements BasePresenter {

    private BindAlipayView bindAlipayView;
    private AccountService service;
    private RxSubscriber<HttpResult> subscriber;

    @Inject
    public BindAlipayPresenter(BindAlipayView bindAlipayView, AccountService service) {
        this.bindAlipayView = bindAlipayView;
        this.service = service;
    }

    /**
     * 绑定支付宝
     */
    public void bindAlipay(String name, String account) {
        subscriber = new RxSubscriber<HttpResult>(bindAlipayView) {
            @Override
            public void onNext(HttpResult httpResult) {
                bindAlipayView.bindSucceed();
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                bindAlipayView.bindFail();
            }
        };

        ReqParams params = new ReqParams(ReqParams.PUT, AppConfig.URL_BIND_ALIPAY);
        if (null != ConfigUtil.getShopInfo().getAlipay())
            params.addParam("last_account_id", ConfigUtil.getShopInfo().getAlipay().getId());
        params.addParam("account_name", account);
        params.addParam("real_name", name);

        service.bindAlipay(params.getQueryMap(), params.getFieldMap())
                .compose(new IoTransformer())
                .subscribe(subscriber);
    }

    @Override
    public void clean() {
        if (null != subscriber) subscriber.cancel();
    }
}
