package com.kuaikuaiyu.assistant.ui.account.records;

import com.kuaikuaiyu.assistant.model.service.AccountService;
import com.kuaikuaiyu.assistant.net.NetUtil;
import com.kuaikuaiyu.assistant.rx.PerFragment;

import dagger.Module;
import dagger.Provides;

/**
 * Author:  Gavin
 * Email:   gavinking@163.com
 * Date:    2016/4/30
 * Desc:    绑定银行卡Module
 */
@Module
public class WithdrawRecordModule {

    private WithdrawRecordView withdrawRecordView;

    public WithdrawRecordModule(WithdrawRecordView withdrawRecordView) {
        this.withdrawRecordView = withdrawRecordView;
    }

    @PerFragment
    @Provides
    WithdrawRecordView getView() {
        return withdrawRecordView;
    }

    @PerFragment
    @Provides
    AccountService getService() {
        return NetUtil.create(AccountService.class);
    }
}
