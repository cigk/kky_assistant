package com.kuaikuaiyu.assistant.ui.income.account;

import android.support.v7.widget.LinearLayoutManager;

import com.kuaikuaiyu.assistant.R;
import com.kuaikuaiyu.assistant.base.BaseFragment;
import com.kuaikuaiyu.assistant.base.BasePresenter;
import com.kuaikuaiyu.assistant.modle.domain.IncomeAccount;
import com.kuaikuaiyu.assistant.ui.income.CommonModule;
import com.kuaikuaiyu.assistant.ui.widgets.MaterialPtrFramelayout;
import com.kuaikuaiyu.assistant.ui.widgets.PtrRecyclerView;
import com.zhy.base.adapter.recyclerview.DividerItemDecoration;

import javax.inject.Inject;

import butterknife.Bind;

/**
 * Created by binlly
 * <p>
 * date: 2016/5/10 16:04
 * desc:
 */
public class AccountFragment extends BaseFragment implements AccountView {
    @Bind(R.id.mpf)
    MaterialPtrFramelayout mpf;
    @Bind(R.id.rv_account)
    PtrRecyclerView rvAccount;

    @Inject
    AccountPresenter mPresenter;

    private AccountAdapter mAdapter;

    @Override
    protected void initComponent() {
        DaggerAccountComponent.builder().commonModule(new CommonModule()).accountModule(new
                AccountModule(this)).build().inject(this);
    }

    @Override
    protected BasePresenter getPresenter() {
        return mPresenter;
    }

    @Override
    protected int getRootViewId() {
        return R.layout.fragment_income_account;
    }

    @Override
    protected void setListener() {
        mpf.setRefreshingListener(rvAccount);
        mpf.setMaterialPtrHandler(frame -> mPresenter.getIncomeAccount());
    }

    @Override
    protected void initData() {
        mPresenter.getIncomeAccount();
    }

    @Override
    protected void refresh() {
        mPresenter.getIncomeAccount();
    }

    @Override
    public void loadSucceed(IncomeAccount incomeAccount) {
        if (mAdapter == null) {
            mAdapter = new AccountAdapter(mActivity, R.layout.item_income_account, incomeAccount
                    .order_list);
            rvAccount.setLayoutManager(new LinearLayoutManager(mActivity));
            rvAccount.setAdapter(mAdapter);
            rvAccount.addItemDecoration(new DividerItemDecoration(mActivity,
                    DividerItemDecoration.VERTICAL_LIST));
        } else {
            mAdapter.notifyDataSetChanged();
        }
        mLoadingPage.setSucceed();
    }

    @Override
    public void loadEmpty() {
        mLoadingPage.setEmpty();
    }

    @Override
    public void loadError() {
        mLoadingPage.setError();
    }

    @Override
    public void refreshComplete() {
        if (mpf != null) {
            mpf.complete();
        }
    }
}
