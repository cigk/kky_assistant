package com.kuaikuaiyu.assistant.ui.income.account;

import android.support.v7.widget.LinearLayoutManager;

import com.kuaikuaiyu.assistant.R;
import com.kuaikuaiyu.assistant.base.BaseFragment;
import com.kuaikuaiyu.assistant.base.BasePresenter;
import com.kuaikuaiyu.assistant.modle.domain.IncomeAccount;
import com.kuaikuaiyu.assistant.rx.SchedulersCompat;
import com.kuaikuaiyu.assistant.ui.income.CommonModule;
import com.kuaikuaiyu.assistant.ui.widgets.MaterialPtrFramelayout;
import com.kuaikuaiyu.assistant.ui.widgets.PtrRecyclerView;
import com.kuaikuaiyu.assistant.utils.UIUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.Bind;
import rx.Observable;
import rx.Subscriber;

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
            UIUtil.showToast("刷新ing...");

            mPresenter.getIncomeAccount();

            Observable.just(false).delay(3, TimeUnit.SECONDS).compose(SchedulersCompat
                    .applyIoSchedulers()).subscribe(new Subscriber<Boolean>() {
                @Override
                public void onCompleted() {
                    if (mpf != null) {
                        mpf.complete();
                    }
                }

                @Override
                public void onError(Throwable e) {
                    if (mpf != null) {
                        mpf.complete();
                    }
                }

                @Override
                public void onNext(Boolean aBoolean) {
                    UIUtil.showToast("ok");
                }
            });
        });
    }

    @Override
    protected void initData() {
        mLoadingPage.setSucceed();
        mPresenter.getIncomeAccount();
    }

    @Override
    protected void refresh() {
        mPresenter.getIncomeAccount();
    }

    private List<String> mList = new ArrayList<>();

    @Override
    public void fillData(IncomeAccount incomeAccount) {
        for (int i = 0; i < 5; i++) {
            mList.add(String.valueOf(i));
        }
        if (mAdapter == null) {
            mAdapter = new AccountAdapter(mActivity, R.layout.item_income_account, mList,
                    mPresenter);
            rv_account.setLayoutManager(new LinearLayoutManager(mActivity));
            rv_account.setAdapter(mAdapter);
        } else {
            mAdapter.notifyDataSetChanged();
        }
    }
}
