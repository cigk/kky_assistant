package com.kuaikuaiyu.assistant.ui.account.withdraw;

import com.kuaikuaiyu.assistant.rx.PerActivity;

import dagger.Component;

/**
 * Author:  Gavin
 * Email:   gavinking@163.com
 * Date:    2016/4/30
 * Desc:
 */
@PerActivity
@Component(modules = WithdrawModule.class)
public interface WithdrawComponent {
    WithdrawActivity inject(WithdrawActivity activity);
}
