package com.kuaikuaiyu.assistant.ui.income.alipay;

import com.kuaikuaiyu.assistant.model.service.IncomeService;
import com.kuaikuaiyu.assistant.rx.PerActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by binlly
 * <p>
 * date: 2016/5/10 16:50
 * desc:
 */
@Module
public class AlipayModule {
    private AlipayView alipayView;

    public AlipayModule(AlipayView alipayView) {
        this.alipayView = alipayView;
    }

    @PerActivity
    @Provides
    AlipayView getView() {
        return alipayView;
    }

    @PerActivity
    @Provides
    AlipayPresenter getPresenter(AlipayView alipayView, IncomeService incomeService) {
        return new AlipayPresenterImpl(alipayView, incomeService);
    }
}
