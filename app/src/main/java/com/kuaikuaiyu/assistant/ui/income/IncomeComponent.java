package com.kuaikuaiyu.assistant.ui.income;

import com.kuaikuaiyu.assistant.rx.PerActivity;
import com.kuaikuaiyu.assistant.ui.income.account.AccountActivity;
import com.kuaikuaiyu.assistant.ui.income.account.AccountModule;

import dagger.Component;

/**
 * Author:  Gavin
 * Email:   gavinking@163.com
 * Date:    2016/4/30
 * Desc:
 */
@PerActivity
@Component(modules = {IncomeModule.class, AccountModule.class})
public interface IncomeComponent {
    IncomeActivity inject(IncomeActivity incomeActivity);

    AccountActivity inject(AccountActivity accountActivity);
}
