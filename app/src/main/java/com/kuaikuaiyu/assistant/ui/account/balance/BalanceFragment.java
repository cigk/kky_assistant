package com.kuaikuaiyu.assistant.ui.account.balance;

import android.text.TextUtils;
import android.widget.TextView;

import com.kuaikuaiyu.assistant.R;
import com.kuaikuaiyu.assistant.base.BaseFragment;
import com.kuaikuaiyu.assistant.base.BasePresenter;
import com.kuaikuaiyu.assistant.modle.domain.Account;
import com.kuaikuaiyu.assistant.ui.account.withdraw.WithdrawActivity;
import com.kuaikuaiyu.assistant.utils.ConfigUtil;
import com.kuaikuaiyu.assistant.utils.UIUtil;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Author:  Gavin
 * Email:   gavinking@163.com
 * Date:    2016/5/10
 * Desc:
 */
public class BalanceFragment extends BaseFragment implements BalanceView {

    @Inject
    BalancePresenter mPresenter;
    @Bind(R.id.tv_balance)
    TextView tvBalance;
    @Bind(R.id.tv_withdraw)
    TextView tvWithdraw;

    private Account mAccount;

    @Override
    protected void initComponent() {
        DaggerBalanceComponent.builder().balanceModule(new BalanceModule(this)).build().inject(this);
        mAccount = ConfigUtil.getAccountInfo();
    }

    @Override
    protected BasePresenter getPresenter() {
        return mPresenter;
    }

    @Override
    protected int getRootViewId() {
        return R.layout.fragment_balance;
    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void initData() throws Exception {
        mLoadingPage.setSucceed();
    }

    @Override
    protected void refresh() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    /**
     * 提现
     */
    @OnClick(R.id.tv_withdraw)
    public void withdraw() {
        if (TextUtils.isEmpty(mAccount.alipay) && TextUtils.isEmpty(mAccount.bank.card_no))
            UIUtil.showToast("还没有绑定银行卡或者支付宝，请先去绑定账号");
        else
            goActivity(WithdrawActivity.class);
    }
}
