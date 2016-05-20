package com.kuaikuaiyu.assistant.ui.income;

import com.kuaikuaiyu.assistant.model.service.IncomeService;
import com.kuaikuaiyu.assistant.net.NetUtil;
import com.kuaikuaiyu.assistant.rx.PerActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by binlly
 * <p>
 * date: 2016/5/10 16:26
 * desc:
 */
@Module
public class CommonModule {
    @PerActivity
    @Provides
    IncomeService getIncomeService() {
        return NetUtil.create(IncomeService.class);
    }
}
