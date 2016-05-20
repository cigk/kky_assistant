package com.kuaikuaiyu.assistant.ui.account.bindbank;

import com.kuaikuaiyu.assistant.app.AppConfig;
import com.kuaikuaiyu.assistant.base.BasePresenter;
import com.kuaikuaiyu.assistant.model.service.AccountService;
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
public class BindBankPresenter implements BasePresenter {

    private BindBankView bindBankView;
    private AccountService service;
    private RxSubscriber subscriber;

    @Inject
    public BindBankPresenter(BindBankView bindBankView, AccountService service) {
        this.bindBankView = bindBankView;
        this.service = service;
    }

    /**
     * 绑定银行卡
     *
     * @param bankName
     * @param cardNum
     * @param name
     */
    public void bindBank(String bankName, String cardNum, String name) {
        subscriber = new RxSubscriber(bindBankView) {
            @Override
            public void onNext(Object object) {
                bindBankView.bindSucceed();
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                bindBankView.bindFail();
            }
        };

        ReqParams params = new ReqParams(ReqParams.PUT, AppConfig.URL_BIND_BANK);
        if (null != ConfigUtil.getShopInfo().getBank())
            params.addParam("last_account_id", ConfigUtil.getShopInfo().getBank().getId());
        params.addParam("bank_name", bankName);
        params.addParam("bank_no", cardNum);
        params.addParam("real_name", name);

        service.bindBankCard(params.getQueryMap(), params.getFieldMap())
                .compose(new IoTransformer())
                .subscribe(subscriber);
    }

    @Override
    public void clean() {
        if (null != subscriber) subscriber.cancel();
    }
}
