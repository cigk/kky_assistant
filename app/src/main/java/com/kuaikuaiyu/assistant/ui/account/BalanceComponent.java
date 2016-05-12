package com.kuaikuaiyu.assistant.ui.account;

import com.kuaikuaiyu.assistant.rx.PerFragment;

import dagger.Component;

/**
 * Author:  Gavin
 * Email:   gavinking@163.com
 * Date:    2016/4/30
 * Desc:
 */
@PerFragment
@Component(modules = BalanceModule.class)
public interface BalanceComponent {
    BalanceFragment inject(BalanceFragment balanceFragment);
}
