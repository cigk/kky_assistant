package com.kuaikuaiyu.assistant.ui.account.balance;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.kuaikuaiyu.assistant.R;
import com.kuaikuaiyu.assistant.base.BaseActivity;
import com.kuaikuaiyu.assistant.base.BaseFragment;
import com.kuaikuaiyu.assistant.base.BasePresenter;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Author:  Gavin
 * Email:   gavinking@163.com
 * Date:    2016/5/10
 * Desc:
 */
public class BalanceActivity extends BaseActivity {

    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tv_right)
    TextView tvRight;

    @Override
    protected void initComponent() {

    }

    @Override
    protected int getRootView() {
        return R.layout.activity_common;
    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        tvTitle.setText("余额");
        tvRight.setVisibility(View.VISIBLE);
        tvRight.setText("账单记录");
        BaseFragment fragment = new BalanceFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_container, fragment).commit();
    }

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @OnClick(R.id.ib_back)
    public void back() {
        finish();
    }

    @OnClick(R.id.tv_right)
    public void records() {

    }
}
