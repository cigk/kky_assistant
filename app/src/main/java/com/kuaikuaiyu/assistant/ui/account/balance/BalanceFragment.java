package com.kuaikuaiyu.assistant.ui.account.balance;

import com.kuaikuaiyu.assistant.R;
import com.kuaikuaiyu.assistant.base.BaseFragment;
import com.kuaikuaiyu.assistant.base.BasePresenter;

import javax.inject.Inject;

/**
 * Author:  Gavin
 * Email:   gavinking@163.com
 * Date:    2016/5/10
 * Desc:
 */
public class BalanceFragment extends BaseFragment implements BalanceView {

    @Inject
    BalancePresenter mPresenter;

    @Override
    protected void initComponent() {
        DaggerBalanceComponent.builder().balanceModule(new BalanceModule(this)).build().inject(this);
    }

    @Override
    protected BasePresenter getPresenter() {
        return mPresenter;
    }

    @Override
    protected int getRootViewId() {
        return R.layout.fragment_balance;
    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void initData() throws Exception {

    }

    @Override
    protected void refresh() {

    }
}
