package com.kuaikuaiyu.assistant.ui.income.account;

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

    public AccountModule(AccountView incomeView) {
        this.mView = incomeView;
    }

    @PerActivity
    @Provides
    AccountView getAccountView() {
        return mView;
    }
}
