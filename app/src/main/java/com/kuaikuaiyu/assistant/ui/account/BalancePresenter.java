package com.kuaikuaiyu.assistant.ui.account;

import com.kuaikuaiyu.assistant.base.BasePresenter;
import com.kuaikuaiyu.assistant.modle.service.AccountService;

import javax.inject.Inject;

/**
 * Author:  Gavin
 * Email:   gavinking@163.com
 * Date:    2016/5/10
 * Desc:    账户余额Presenter
 */
public class BalancePresenter implements BasePresenter {

    private AccountService service;
    private BalanceView loginView;

    @Inject
    public BalancePresenter(AccountService service, BalanceView loginView) {
        this.service = service;
        this.loginView = loginView;
    }


    @Override
    public void clean() {

    }
}
