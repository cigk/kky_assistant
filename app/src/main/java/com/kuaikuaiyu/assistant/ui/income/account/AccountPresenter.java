package com.kuaikuaiyu.assistant.ui.income.account;

import com.kuaikuaiyu.assistant.base.BasePresenter;

import javax.inject.Inject;

/**
 * Created by binlly
 * <p>
 * date: 2016/5/9 18:04
 * desc:
 */
public class AccountPresenter implements BasePresenter {
    private AccountView mView;

    @Inject
    public AccountPresenter(AccountView view) {
        mView = view;
    }

    @Override
    public void clean() {

    }
}
