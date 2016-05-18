package com.kuaikuaiyu.assistant.ui.income.alipay;

import com.kuaikuaiyu.assistant.app.AppConfig;
import com.kuaikuaiyu.assistant.modle.domain.AlipayUrl;
import com.kuaikuaiyu.assistant.modle.service.IncomeService;
import com.kuaikuaiyu.assistant.net.ReqParams;
import com.kuaikuaiyu.assistant.rx.IoTransformer;
import com.kuaikuaiyu.assistant.rx.RxSubscriber;

/**
 * Author:  Gavin
 * Email:   gavinking@163.com
 * Date:    2016/5/18
 * Desc:
 */
public class AlipayPresenterImpl implements AlipayPresenter {

    private AlipayView alipayView;
    private IncomeService incomeService;

    private RxSubscriber<AlipayUrl> subscriber;

    public AlipayPresenterImpl(AlipayView alipayView, IncomeService incomeService) {
        this.alipayView = alipayView;
        this.incomeService = incomeService;
    }

    @Override
    public void loadAlipayUrl(int money) {
        subscriber = new RxSubscriber<AlipayUrl>(alipayView) {
            @Override
            public void onNext(AlipayUrl alipayUrl) {
                alipayView.loadSucceed(alipayUrl.pay_url);
            }
        };

        ReqParams params = new ReqParams(ReqParams.POST, AppConfig.URL_INCOME_ALIPAY);
        params.addParam("total_fee", money);
        incomeService.getAlipayUrl(params.getQueryMap(), params.getFieldMap())
                .compose(new IoTransformer()).subscribe(subscriber);
    }

    @Override
    public void clean() {
        if (null != subscriber) subscriber.cancel();
    }
}
