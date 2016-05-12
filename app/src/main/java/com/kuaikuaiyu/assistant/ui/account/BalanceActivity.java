package com.kuaikuaiyu.assistant.ui.account;

import android.os.Bundle;
import android.view.View;

import com.kuaikuaiyu.assistant.R;
import com.kuaikuaiyu.assistant.base.BaseActivity;
import com.kuaikuaiyu.assistant.base.BaseFragment;
import com.kuaikuaiyu.assistant.base.BasePresenter;
import com.kuaikuaiyu.assistant.ui.common.CommonActivity;
import com.kuaikuaiyu.assistant.ui.widgets.CommonTitleBar;

import butterknife.Bind;

/**
 * Author:  Gavin
 * Email:   gavinking@163.com
 * Date:    2016/5/10
 * Desc:
 */
public class BalanceActivity extends BaseActivity {

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
        topBar.onRightClick(v -> records());
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        topBar.setTitle("余额");
        topBar.setRightText("账单记录");
        topBar.setRightTextVisibility(View.VISIBLE);
        BaseFragment fragment = new BalanceFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_container, fragment).commit();
    }

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    /**
     * 提现记录页面
     */
    public void records() {
        CommonActivity.start(this, CommonActivity.DISPLAY_WITHDRAW_RECORDS, "提现记录", null);
    }
}
