package com.kuaikuaiyu.assistant.ui.account.withdraw;

import com.kuaikuaiyu.assistant.model.service.AccountService;
import com.kuaikuaiyu.assistant.net.NetUtil;
import com.kuaikuaiyu.assistant.rx.PerActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Author:  Gavin
 * Email:   gavinking@163.com
 * Date:    2016/4/30
 * Desc:    提现module
 */
@Module
public class WithdrawModule {

    private WithdrawView withdrawView;

    public WithdrawModule(WithdrawView withdrawView) {
        this.withdrawView = withdrawView;
    }

    @PerActivity
    @Provides
    WithdrawView getView() {
        return withdrawView;
    }

    @PerActivity
    @Provides
    AccountService getService() {
        return NetUtil.create(AccountService.class);
    }
}
