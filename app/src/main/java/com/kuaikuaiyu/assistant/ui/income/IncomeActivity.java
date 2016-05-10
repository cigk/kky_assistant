package com.kuaikuaiyu.assistant.ui.income;

import android.os.Bundle;
import android.widget.LinearLayout;

import com.kuaikuaiyu.assistant.R;
import com.kuaikuaiyu.assistant.base.BaseActivity;
import com.kuaikuaiyu.assistant.base.BasePresenter;
import com.kuaikuaiyu.assistant.ui.income.account.AccountActivity;
import com.kuaikuaiyu.assistant.ui.widgets.CommonTitleBar;

import javax.inject.Inject;

import butterknife.Bind;

/**
 * Created by binlly
 * <p>
 * date: 2016/5/9 14:11
 * desc:
 */
public class IncomeActivity extends BaseActivity implements IncomeView {
    @Bind(R.id.top_bar)
    CommonTitleBar top_bar;
    @Bind(R.id.ll_income_qrcode)
    LinearLayout ll_income_qrcode;
    @Bind(R.id.ll_income_account)
    LinearLayout ll_income_account;

    @Inject
    IncomePresenter mPresenter;

    @Override
    protected void initComponent() {
        DaggerIncomeComponent.builder().incomeModule(new IncomeModule(this)).build().inject(this);
    }

    @Override
    protected int getRootView() {
        return R.layout.activity_income;
    }

    @Override
    protected void setListener() {
        top_bar.onBackClick(v -> onBackPressed());

        top_bar.onRightClick(v -> {
            // TODO: 2016/5/9           
        });

        ll_income_qrcode.setOnClickListener(v -> {
            // TODO: 2016/5/9  
        });

        ll_income_account.setOnClickListener(v -> goActivity(AccountActivity.class));
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected BasePresenter getPresenter() {
        return mPresenter;
    }
}
