package com.kuaikuaiyu.assistant.ui.account.bindbank;

import com.kuaikuaiyu.assistant.rx.PerFragment;

import dagger.Component;

/**
 * Author:  Gavin
 * Email:   gavinking@163.com
 * Date:    2016/4/30
 * Desc:
 */
@PerFragment
@Component(modules = BindBankModule.class)
public interface BindBankComponent {
    BindBankFragment inject(BindBankFragment bindBankFragment);
}
