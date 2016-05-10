package com.kuaikuaiyu.assistant.ui.account.bindbank;

import com.kuaikuaiyu.assistant.modle.service.AccountService;
import com.kuaikuaiyu.assistant.modle.service.PassService;
import com.kuaikuaiyu.assistant.net.NetUtil;
import com.kuaikuaiyu.assistant.rx.PerActivity;
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
public class BindBankModule {

    private BindBankView bindBankView;

    public BindBankModule(BindBankView bindBankView) {
        this.bindBankView = bindBankView;
    }

    @PerFragment
    @Provides
    BindBankView getView() {
        return bindBankView;
    }

    @PerFragment
    @Provides
    AccountService getService() {
        return NetUtil.create(AccountService.class);
    }
}
