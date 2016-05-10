package com.kuaikuaiyu.assistant.ui.income;

import com.kuaikuaiyu.assistant.base.BasePresenter;
import com.kuaikuaiyu.assistant.modle.service.IncomeService;

import javax.inject.Inject;

/**
 * Created by binlly
 * <p>
 * date: 2016/5/9 14:17
 * desc:
 */
public class IncomePresenter implements BasePresenter {
    private IncomeService service;
    private IncomeView mView;

    @Inject
    public IncomePresenter(IncomeService service, IncomeView view) {
        this.service = service;
        mView = view;
    }

    @Override
    public void clean() {

    }
}
