package com.kuaikuaiyu.assistant.ui.income.record;

import com.kuaikuaiyu.assistant.rx.PerActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by binlly
 * <p>
 * date: 2016/5/9 18:05
 * desc:
 */
@Module
public class IncomeRecordModule {
    private IncomeRecordView mView;

    public IncomeRecordModule(IncomeRecordView accountView) {
        this.mView = accountView;
    }

    @PerActivity
    @Provides
    IncomeRecordView getAccountView() {
        return mView;
    }
}
