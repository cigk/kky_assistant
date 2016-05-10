package com.kuaikuaiyu.assistant.ui.account.records;

import com.kuaikuaiyu.assistant.base.BasePresenter;
import com.kuaikuaiyu.assistant.modle.service.AccountService;

import javax.inject.Inject;

/**
 * Author:  Gavin
 * Email:   gavinking@163.com
 * Date:    2016/5/10
 * Desc:    提现记录Presenter
 */
public class WithdrawRecordPresenter implements BasePresenter {

    private WithdrawRecordView withdrawRecordView;
    private AccountService service;

    @Inject
    public WithdrawRecordPresenter(WithdrawRecordView withdrawRecordView, AccountService service) {
        this.withdrawRecordView = withdrawRecordView;
        this.service = service;
    }

    @Override
    public void clean() {

    }

    /**
     * 获取提现记录
     */
    public void getRecords() {

    }
}
