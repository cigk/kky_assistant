package com.kuaikuaiyu.assistant.ui.income.account;

import android.os.Bundle;

import com.kuaikuaiyu.assistant.R;
import com.kuaikuaiyu.assistant.base.BaseActivity;
import com.kuaikuaiyu.assistant.base.BasePresenter;
import com.kuaikuaiyu.assistant.modle.domain.IncomeAccount;
import com.kuaikuaiyu.assistant.ui.income.DaggerIncomeComponent;

import javax.inject.Inject;

/**
 * Created by binlly
 * <p>
 * date: 2016/5/9 18:03
 * desc:
 */
public class AccountActivity extends BaseActivity implements AccountView {


    @Inject
    AccountPresenter mPresenter;

    private AccountAdapter mAdapter;


    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected BasePresenter getPresenter() {
        return mPresenter;
    }

}
