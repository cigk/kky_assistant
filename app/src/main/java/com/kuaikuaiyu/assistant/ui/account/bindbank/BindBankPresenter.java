package com.kuaikuaiyu.assistant.ui.account.bindbank;

import com.kuaikuaiyu.assistant.base.BasePresenter;
import com.kuaikuaiyu.assistant.modle.service.AccountService;

import javax.inject.Inject;

/**
 * Author:  Gavin
 * Email:   gavinking@163.com
 * Date:    2016/5/10
 * Desc:    绑定银行卡
 */
public class BindBankPresenter implements BasePresenter {

    private BindBankView bindBankView;
    private AccountService service;

    @Inject
    public BindBankPresenter(BindBankView bindBankView, AccountService service) {
        this.bindBankView = bindBankView;
        this.service = service;
    }

    /**
     * 绑定银行卡
     *
     * @param bankName
     * @param cardNum
     * @param cardName
     * @param province
     * @param city
     */
    public void bindBank(String bankName, String cardNum, String cardName, String province,
                         String city) {

    }

    @Override
    public void clean() {

    }
}
