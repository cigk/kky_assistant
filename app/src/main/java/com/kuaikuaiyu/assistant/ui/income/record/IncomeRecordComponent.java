package com.kuaikuaiyu.assistant.ui.income.record;

import com.kuaikuaiyu.assistant.rx.PerActivity;
import com.kuaikuaiyu.assistant.ui.income.CommonModule;

import dagger.Component;

/**
 * Created by binlly
 * <p>
 * date: 2016/5/10 16:51
 * desc:
 */
@PerActivity
@Component(modules = {CommonModule.class, IncomeRecordModule.class})
public interface IncomeRecordComponent {
    IncomeRecordFragment inject(IncomeRecordFragment accountFragment);
}
