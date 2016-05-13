package com.kuaikuaiyu.assistant.ui.account.records;

import android.support.v7.widget.LinearLayoutManager;

import com.kuaikuaiyu.assistant.R;
import com.kuaikuaiyu.assistant.base.BaseFragment;
import com.kuaikuaiyu.assistant.base.BasePresenter;
import com.kuaikuaiyu.assistant.modle.domain.WithdrawItem;
import com.kuaikuaiyu.assistant.ui.account.withdraw.WithdrawAdapter;
import com.kuaikuaiyu.assistant.ui.widgets.MaterialPtrFramelayout;
import com.kuaikuaiyu.assistant.ui.widgets.PtrRecyclerView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import in.srain.cube.views.ptr.PtrFrameLayout;

public class WithdrawRecordFragment extends BaseFragment implements WithdrawRecordView {


    @Bind(R.id.rv_withdraw)
    PtrRecyclerView rvWithdraw;
    @Bind(R.id.mptr)
    MaterialPtrFramelayout mptr;

    private WithdrawAdapter mAdapter;

    @Inject
    WithdrawRecordPresenter mPresenter;

    private List<WithdrawItem> mWithdrawItem = new ArrayList<>();

    @Override
    protected void initComponent() {
        DaggerWithdrawRecordComponent.builder()
                .withdrawRecordModule(new WithdrawRecordModule(this)).build().inject(this);
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
        mptr.setMaterialPtrHandler(new MaterialPtrFramelayout.PtrMaterialHandler() {
            @Override
            public void onBegin(PtrFrameLayout frame) {
                refresh();
            }
        });
    }

    @Override
    protected void initData() {
        mPresenter.getRecords();
    }

    @Override
    protected void refresh() {
        mPresenter.getRecords();
    }

    @Override
    public void loadSucceed(List<WithdrawItem> data) {
        //TODO setAdapter
        if (null == mAdapter) {
            mAdapter = new WithdrawAdapter(mActivity, R.layout.list_item_withdraw_record, data);
            rvWithdraw.setAdapter(mAdapter);
            rvWithdraw.setLayoutManager(new LinearLayoutManager(mActivity));
        }
        mAdapter.notifyDataSetChanged();
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
}
