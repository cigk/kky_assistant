package com.kuaikuaiyu.assistant.ui.income.account;

import android.os.Bundle;
import android.view.View;

import com.kuaikuaiyu.assistant.R;
import com.kuaikuaiyu.assistant.base.BaseActivity;
import com.kuaikuaiyu.assistant.base.BasePresenter;
import com.kuaikuaiyu.assistant.ui.income.DaggerIncomeComponent;
import com.kuaikuaiyu.assistant.ui.widgets.CommonTitleBar;

import javax.inject.Inject;

import butterknife.Bind;

/**
 * Created by binlly
 * <p>
 * date: 2016/5/9 18:03
 * desc:
 */
public class AccountActivity extends BaseActivity implements AccountView {
    @Bind(R.id.top_bar)
    CommonTitleBar top_bar;

    @Inject
    AccountPresenter mPresenter;

    @Override
    protected void initComponent() {
        DaggerIncomeComponent.builder().accountModule(new AccountModule(this)).build().inject(this);
    }

    @Override
    protected int getRootView() {
        return R.layout.activity_income_account;
    }

    @Override
    protected void setListener() {
        top_bar.onBackClick(v -> {
            onBackPressed();
        });
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected BasePresenter getPresenter() {
        return mPresenter;
    }
}
