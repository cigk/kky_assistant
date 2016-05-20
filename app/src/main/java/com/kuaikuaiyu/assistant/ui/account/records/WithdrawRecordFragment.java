package com.kuaikuaiyu.assistant.ui.account.records;

import android.support.v7.widget.LinearLayoutManager;

import com.kuaikuaiyu.assistant.R;
import com.kuaikuaiyu.assistant.base.BaseFragment;
import com.kuaikuaiyu.assistant.base.BasePresenter;
import com.kuaikuaiyu.assistant.model.domain.BillRecord;
import com.kuaikuaiyu.assistant.ui.widgets.MaterialPtrFramelayout;
import com.kuaikuaiyu.assistant.ui.widgets.PtrRecyclerView;
import com.zhy.base.adapter.recyclerview.DividerItemDecoration;

import javax.inject.Inject;

import butterknife.Bind;

public class WithdrawRecordFragment extends BaseFragment implements WithdrawRecordView {

    @Bind(R.id.rv_withdraw)
    PtrRecyclerView rvWithdraw;
    @Bind(R.id.mpf)
    MaterialPtrFramelayout mpf;

    private WithdrawRecordAdapter mAdapter;

    @Inject
    WithdrawRecordPresenter mPresenter;

    @Override
    protected void initComponent() {
        DaggerWithdrawRecordComponent.builder().withdrawRecordModule(new WithdrawRecordModule
                (this)).build().inject(this);
    }

    @Override
    protected BasePresenter getPresenter() {
        return mPresenter;
    }

    @Override
    protected int getRootViewId() {
        return R.layout.fragment_withdraw_record;
    }

    @Override
    protected void setListener() {
        mpf.setRefreshingListener(rvWithdraw);
        mpf.setMaterialPtrHandler(frame -> mPresenter.getBillRecords());
    }

    @Override
    protected void initData() {
        mPresenter.getBillRecords();
    }

    @Override
    protected void refresh() {
        mPresenter.getBillRecords();
    }

    @Override
    public void loadSucceed(BillRecord bill) {
        if (null == mAdapter) {
            mAdapter = new WithdrawRecordAdapter(mActivity, R.layout.list_item_withdraw_record,
                    bill.bill_list);
            rvWithdraw.setAdapter(mAdapter);
            rvWithdraw.setLayoutManager(new LinearLayoutManager(mActivity));
            rvWithdraw.addItemDecoration(new DividerItemDecoration(mActivity,
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
