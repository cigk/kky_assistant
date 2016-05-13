package com.kuaikuaiyu.assistant.ui.income.account;

import android.support.v7.widget.LinearLayoutManager;

import com.kuaikuaiyu.assistant.R;
import com.kuaikuaiyu.assistant.base.BaseFragment;
import com.kuaikuaiyu.assistant.base.BasePresenter;
import com.kuaikuaiyu.assistant.modle.domain.IncomeAccount;
import com.kuaikuaiyu.assistant.ui.income.CommonModule;
import com.kuaikuaiyu.assistant.ui.widgets.LoadingPage;
import com.kuaikuaiyu.assistant.ui.widgets.MaterialPtrFramelayout;
import com.kuaikuaiyu.assistant.ui.widgets.PtrRecyclerView;

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
    PtrRecyclerView rv_account;

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
        mpf.setRefreshingListener(rv_account);
        mpf.setMaterialPtrHandler(frame -> {
            mPresenter.getIncomeAccount();
        });
    }

    @Override
    protected void initData() throws Exception {
        mPresenter.getIncomeAccount();
    }

    @Override
    protected void refresh() {
        mPresenter.getIncomeAccount();
    }

    @Override
    public void fillData(IncomeAccount incomeAccount) {
        if (mAdapter == null) {
            mAdapter = new AccountAdapter(mActivity, R.layout.item_income_account, incomeAccount
                    .order_list);
            rv_account.setLayoutManager(new LinearLayoutManager(mActivity));
            rv_account.setAdapter(mAdapter);
        } else {
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void refreshComplete() {
        if (mpf != null) {
            mpf.complete();
        }
    }

    @Override
    public LoadingPage getLoadingPage() {
        return mLoadingPage;
    }
}
