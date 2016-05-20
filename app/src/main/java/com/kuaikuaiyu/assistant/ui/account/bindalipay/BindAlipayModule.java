package com.kuaikuaiyu.assistant.ui.account.bindalipay;

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
public class BindAlipayModule {

    private BindAlipayView bindAlipayView;

    public BindAlipayModule(BindAlipayView bindAlipayView) {
        this.bindAlipayView = bindAlipayView;
    }

    @PerFragment
    @Provides
    BindAlipayView getView() {
        return bindAlipayView;
    }

    @PerFragment
    @Provides
    AccountService getService() {
        return NetUtil.create(AccountService.class);
    }
}
