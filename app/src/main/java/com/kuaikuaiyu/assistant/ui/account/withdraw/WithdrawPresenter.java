package com.kuaikuaiyu.assistant.ui.account.withdraw;

import com.kuaikuaiyu.assistant.app.AppConfig;
import com.kuaikuaiyu.assistant.base.BasePresenter;
import com.kuaikuaiyu.assistant.model.service.AccountService;
import com.kuaikuaiyu.assistant.net.ReqParams;
import com.kuaikuaiyu.assistant.rx.IoTransformer;
import com.kuaikuaiyu.assistant.rx.RxSubscriber;
import com.kuaikuaiyu.assistant.utils.DigestUtil;

import javax.inject.Inject;

/**
 * Author:  Gavin
 * Email:   gavinking@163.com
 * Date:    2016/5/10
 * Desc:    提现Presenter
 */
public class WithdrawPresenter implements BasePresenter {

    private WithdrawView withdrawView;
    private AccountService service;
    private RxSubscriber subscriber;

    @Inject
    public WithdrawPresenter(WithdrawView withdrawView, AccountService service) {
        this.withdrawView = withdrawView;
        this.service = service;
    }

    @Override
    public void clean() {
        if (null != subscriber) subscriber.cancel();
    }

    /**
     * 提现
     *
     * @param money
     */
    public void withdraw(int id, int money, String pwd) {
        subscriber = new RxSubscriber(withdrawView) {
            @Override
            public void onNext(Object o) {
                withdrawView.withdrawSucceed();
            }
        };

        ReqParams params = new ReqParams(ReqParams.POST, AppConfig.URL_WITHDRAW);
        params.addParam("amount", money);
        params.addParam("withdraw_account_id", id);
        params.addParam("password", DigestUtil.getMd5(pwd.getBytes()));

        service.withdraw(params.getQueryMap(), params.getFieldMap())
                .compose(new IoTransformer())
                .subscribe(subscriber);
    }

    /**
     * 提现前更新账户余额
     */
    public void updateAccountInfo() {

    }
}
