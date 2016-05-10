package com.kuaikuaiyu.assistant.ui.income.account;

import com.kuaikuaiyu.assistant.rx.PerActivity;
import com.kuaikuaiyu.assistant.ui.income.CommonModule;
import com.kuaikuaiyu.assistant.ui.income.IncomeActivity;
import com.kuaikuaiyu.assistant.ui.income.IncomeModule;

import dagger.Component;

/**
 * Created by binlly
 * <p>
 * date: 2016/5/10 16:51
 * desc:
 */
@PerActivity
@Component(modules = {CommonModule.class, AccountModule.class})
public interface AccountComponent {
    AccountFragment inject(AccountFragment accountFragment);
}
