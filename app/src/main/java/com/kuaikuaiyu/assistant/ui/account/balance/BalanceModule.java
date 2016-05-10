package com.kuaikuaiyu.assistant.ui.account.balance;

import com.kuaikuaiyu.assistant.modle.service.AccountService;
import com.kuaikuaiyu.assistant.net.NetUtil;
import com.kuaikuaiyu.assistant.rx.PerActivity;
import com.kuaikuaiyu.assistant.rx.PerFragment;

import dagger.Module;
import dagger.Provides;

/**
 * Author:  Gavin
 * Email:   gavinking@163.com
 * Date:    2016/4/30
 * Desc:    账户余额Module
 */
@Module
public class BalanceModule {

    private BalanceView balanceView;

    public BalanceModule(BalanceView balanceView) {
        this.balanceView = balanceView;
    }

    @PerFragment
    @Provides
    BalanceView getView() {
        return balanceView;
    }

    @PerFragment
    @Provides
    AccountService getService() {
        return NetUtil.create(AccountService.class);
    }
}
