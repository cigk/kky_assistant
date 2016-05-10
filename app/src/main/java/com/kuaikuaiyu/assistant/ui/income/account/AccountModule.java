package com.kuaikuaiyu.assistant.ui.income.account;

import com.kuaikuaiyu.assistant.modle.service.IncomeService;
import com.kuaikuaiyu.assistant.net.NetUtil;
import com.kuaikuaiyu.assistant.rx.PerActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by binlly
 * <p>
 * date: 2016/5/9 18:05
 * desc:
 */
@Module
public class AccountModule {
    private AccountView mView;

    public AccountModule(AccountView accountView) {
        this.mView = accountView;
    }

    @PerActivity
    @Provides
    AccountView getAccountView() {
        return mView;
    }
}
