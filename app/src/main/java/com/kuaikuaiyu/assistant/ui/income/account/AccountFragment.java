package com.kuaikuaiyu.assistant.ui.income.account;

import android.support.v4.widget.SwipeRefreshLayout;

import com.kuaikuaiyu.assistant.R;
import com.kuaikuaiyu.assistant.base.BaseFragment;
import com.kuaikuaiyu.assistant.base.BasePresenter;
import com.kuaikuaiyu.assistant.modle.domain.IncomeAccount;
import com.kuaikuaiyu.assistant.rx.SchedulersCompat;
import com.kuaikuaiyu.assistant.ui.income.CommonModule;
import com.kuaikuaiyu.assistant.utils.UIUtil;

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
    @Bind(R.id.sfl)
    SwipeRefreshLayout sfl;
    //    @Bind(R.id.rv_account)
    //    RecyclerView rv_account;

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
        sfl.setOnRefreshListener(() -> {
            UIUtil.showToast("刷新ing...");
            Observable.just(false).delay(3, TimeUnit.SECONDS).compose(SchedulersCompat
                    .applyIoSchedulers()).subscribe(new Subscriber<Boolean>() {
                @Override
                public void onCompleted() {
                }

                @Override
                public void onError(Throwable e) {
                }

                @Override
                public void onNext(Boolean aBoolean) {
                    UIUtil.showToast("ok");
                    sfl.setRefreshing(aBoolean);
                }
            });
        });
    }

    @Override
    protected void initData() throws Exception {
        mLoadingPage.setSucceed();
    }

    @Override
    protected void refresh() {
        mPresenter.getIncomeAccount();
    }

    @Override
    public void fillData(IncomeAccount incomeAccount) {
        //        if (mAdapter == null) {
        //            mAdapter = new AccountAdapter(this, R.layout.item_income_account,
        // incomeAccount,
        //                    mPresenter);
        //            rv_account.setAdapter(mAdapter);
        //        } else {
        //            mAdapter.notifyDataSetChanged();
        //        }
    }
}
