package com.kuaikuaiyu.assistant.ui.account.withdraw;

import com.kuaikuaiyu.assistant.base.BasePresenter;
import com.kuaikuaiyu.assistant.modle.service.AccountService;

import javax.inject.Inject;

/**
 * Author:  Gavin
 * Email:   gavinking@163.com
 * Date:    2016/5/10
 * Desc:    提现Presenter
 */
public class WithdrawPresenter implements BasePresenter {

    private WithdrawView withdrawView;
    private AccountService service;

    @Inject
    public WithdrawPresenter(WithdrawView withdrawView, AccountService service) {
        this.withdrawView = withdrawView;
        this.service = service;
    }

    @Override
    public void clean() {

    }

    /**
     * 提现
     *
     * @param method
     * @param money
     */
    public void withdraw(String method, int money) {

    }

    /**
     * 提现前更新账户余额
     */
    public void updateAccountInfo() {

    }
}
