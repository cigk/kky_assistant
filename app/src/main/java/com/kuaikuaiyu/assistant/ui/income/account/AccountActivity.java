package com.kuaikuaiyu.assistant.ui.income.account;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.kuaikuaiyu.assistant.R;
import com.kuaikuaiyu.assistant.base.BaseActivity;
import com.kuaikuaiyu.assistant.base.BasePresenter;
import com.kuaikuaiyu.assistant.modle.domain.IncomeAccount;
import com.kuaikuaiyu.assistant.ui.income.DaggerIncomeComponent;
import com.kuaikuaiyu.assistant.ui.widgets.CommonTitleBar;

import javax.inject.Inject;

import butterknife.Bind;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;

/**
 * Created by binlly
 * <p>
 * date: 2016/5/9 18:03
 * desc:
 */
public class AccountActivity extends BaseActivity implements AccountView {
    @Bind(R.id.top_bar)
    CommonTitleBar top_bar;
    @Bind(R.id.ptr)
    PtrClassicFrameLayout ptr;
    @Bind(R.id.lv_account)
    ListView lv_account;

    @Inject
    AccountPresenter mPresenter;

    private AccountAdapter mAdapter;

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
        top_bar.onBackClick(v -> onBackPressed());

        ptr.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {

            }
        });
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected BasePresenter getPresenter() {
        return mPresenter;
    }

    @Override
    public void setAdapter(IncomeAccount incomeAccount) {
        if (mAdapter == null) {
            mAdapter = new AccountAdapter(incomeAccount);
            lv_account.setAdapter(mAdapter);
        }else {
            mAdapter.setData(incomeAccount);
        }
    }
}
