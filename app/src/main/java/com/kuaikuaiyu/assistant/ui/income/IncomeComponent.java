package com.kuaikuaiyu.assistant.ui.income;

import com.kuaikuaiyu.assistant.rx.PerActivity;

import dagger.Component;

/**
 * Author:  Gavin
 * Email:   gavinking@163.com
 * Date:    2016/4/30
 * Desc:
 */
@PerActivity
@Component(modules = {CommonModule.class, IncomeModule.class})
public interface IncomeComponent {
    IncomeActivity inject(IncomeActivity incomeActivity);
}
