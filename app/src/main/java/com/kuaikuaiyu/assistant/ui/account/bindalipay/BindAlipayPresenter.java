package com.kuaikuaiyu.assistant.ui.account.bindalipay;

import com.kuaikuaiyu.assistant.base.BasePresenter;
import com.kuaikuaiyu.assistant.modle.service.AccountService;

import javax.inject.Inject;

/**
 * Author:  Gavin
 * Email:   gavinking@163.com
 * Date:    2016/5/10
 * Desc:    绑定银行卡
 */
public class BindAlipayPresenter implements BasePresenter {

    private BindAlipayView bindAlipayView;
    private AccountService service;

    @Inject
    public BindAlipayPresenter(BindAlipayView bindAlipayView, AccountService service) {
        this.bindAlipayView = bindAlipayView;
        this.service = service;
    }

    /**
     * 绑定支付宝
     */
    public void bindAlipay(String name, String account) {

    }

    @Override
    public void clean() {

    }
}
