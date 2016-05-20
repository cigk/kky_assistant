package com.kuaikuaiyu.assistant.ui.income.record;

import android.os.Bundle;

import com.kuaikuaiyu.assistant.R;
import com.kuaikuaiyu.assistant.base.BaseActivity;
import com.kuaikuaiyu.assistant.base.BasePresenter;
import com.kuaikuaiyu.assistant.ui.widgets.CommonTitleBar;

import butterknife.Bind;

/**
 * Author:  Gavin
 * Email:   gavinking@163.com
 * Date:    2015/12/2
 * Desc:    for push requirement needs single task,  use AccountActivity instead of CommonActivity
 */
public class IncomeRecordActivity extends BaseActivity {


    @Bind(R.id.top_bar)
    CommonTitleBar topBar;

    @Override
    protected void initComponent() {

    }

    @Override
    protected int getRootView() {
        return R.layout.activity_common;
    }

    @Override
    protected void setListener() {
        topBar.onBackClick(v -> onBackPressed());
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        topBar.setTitle(getString(R.string.income_account_title));
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fl_container, new IncomeRecordFragment()).commit();
    }

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @Override
    protected void onDestroy() {
        startApp();
        super.onDestroy();
    }
}
