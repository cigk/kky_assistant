package com.kuaikuaiyu.assistant.ui.income.account;

import com.kuaikuaiyu.assistant.app.AppConfig;
import com.kuaikuaiyu.assistant.base.BasePresenter;
import com.kuaikuaiyu.assistant.modle.domain.IncomeAccount;
import com.kuaikuaiyu.assistant.modle.service.IncomeService;
import com.kuaikuaiyu.assistant.net.ReqParams;
import com.kuaikuaiyu.assistant.rx.IoTransformer;
import com.kuaikuaiyu.assistant.rx.RxSubscriber;

import javax.inject.Inject;

/**
 * Created by binlly
 * <p>
 * date: 2016/5/9 18:04
 * desc:
 */
public class AccountPresenter implements BasePresenter {
    private AccountView mView;
    private IncomeService mService;
    private RxSubscriber<IncomeAccount> mSubscriber;

    @Inject
    public AccountPresenter(IncomeService service, AccountView view) {
        mService = service;
        mView = view;
    }

    public void getIncomeAccount() {
        mSubscriber = new RxSubscriber<IncomeAccount>(null) {
            @Override
            public void onNext(IncomeAccount incomeAccount) {
                if (incomeAccount != null && incomeAccount.order_list != null) {
                    mView.fillData(incomeAccount);
                    mView.getLoadingPage().setSucceed();
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                mView.getLoadingPage().setError();
            }

            @Override
            public void onCompleted() {
                super.onCompleted();
                mView.refreshComplete();
            }
        };
        ReqParams params = new ReqParams(ReqParams.GET, AppConfig.URL_INCOME_ACCOUNT);
        params.addQuery("offset", 0);
        params.addQuery("limit", 10000);
        mService.getIncomeAccount(params.getQueryMap()).compose(new IoTransformer<>()).subscribe
                (mSubscriber);
    }

    @Override
    public void clean() {
        if (mSubscriber != null) {
            mSubscriber.cancel();
        }
    }
}
