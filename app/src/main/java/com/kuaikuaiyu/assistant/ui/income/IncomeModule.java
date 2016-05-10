package com.kuaikuaiyu.assistant.ui.income;

import com.kuaikuaiyu.assistant.modle.service.IncomeService;
import com.kuaikuaiyu.assistant.net.NetUtil;
import com.kuaikuaiyu.assistant.rx.PerActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by binlly
 * <p>
 * date: 2016/5/9 14:20
 * desc:
 */
@Module
public class IncomeModule {
    private IncomeView mView;

    public IncomeModule(IncomeView incomeView) {
        this.mView = incomeView;
    }

    @PerActivity
    @Provides
    IncomeView getIncomeView() {
        return mView;
    }
}
