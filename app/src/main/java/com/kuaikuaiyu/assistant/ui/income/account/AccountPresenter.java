package com.kuaikuaiyu.assistant.ui.income.account;

import com.kuaikuaiyu.assistant.base.BasePresenter;
import com.kuaikuaiyu.assistant.modle.domain.IncomeAccount;
import com.kuaikuaiyu.assistant.modle.service.IncomeService;
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
    private RxSubscriber<IncomeAccount> subscriber;

    @Inject
    public AccountPresenter(IncomeService service, AccountView view) {
        mService = service;
        mView = view;
    }

    public void getIncomeAccount() {
//        subscriber = new RxSubscriber<IncomeAccount>(mView) {
//            @Override
//            public void onNext(IncomeAccount incomeAccount) {
//                mView.fillData(incomeAccount);
//            }
//        };
//        mService.getIncomeAccount().compose(new IoTransformer<>()).subscribe(subscriber);
        mView.fillData(new IncomeAccount());
    }

    @Override
    public void clean() {
        if (subscriber != null) {
            subscriber.cancel();
        }
    }
}
