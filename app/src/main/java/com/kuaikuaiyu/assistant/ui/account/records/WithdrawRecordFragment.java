package com.kuaikuaiyu.assistant.ui.account.records;

import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.ListView;

import com.kuaikuaiyu.assistant.R;
import com.kuaikuaiyu.assistant.base.BaseFragment;
import com.kuaikuaiyu.assistant.base.BasePresenter;
import com.kuaikuaiyu.assistant.modle.domain.WithdrawItem;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;

public class WithdrawRecordFragment extends BaseFragment implements WithdrawRecordView {


    @Bind(R.id.lv_withdraw)
    ListView lvWithdraw;
    @Bind(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;

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

    }

    @Override
    protected void initData() throws Exception {
        mPresenter.getRecords();
    }

    @Override
    protected void refresh() {

    }

    @Override
    public void loadSucceed() {
        //TODO setAdapter

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
