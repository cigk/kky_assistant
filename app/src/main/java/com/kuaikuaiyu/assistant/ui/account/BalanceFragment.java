package com.kuaikuaiyu.assistant.ui.account;

import android.widget.TextView;

import com.kuaikuaiyu.assistant.R;
import com.kuaikuaiyu.assistant.base.BaseFragment;
import com.kuaikuaiyu.assistant.base.BasePresenter;
import com.kuaikuaiyu.assistant.modle.domain.ShopInfo;
import com.kuaikuaiyu.assistant.ui.account.withdraw.WithdrawActivity;
import com.kuaikuaiyu.assistant.ui.common.CommonActivity;
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

    @Bind(R.id.tv_balance)
    TextView tvBalance;
    @Bind(R.id.tv_withdraw)
    TextView tvWithdraw;
    @Bind(R.id.tv_bank_card)
    TextView tvBankCard;
    @Bind(R.id.tv_alipay)
    TextView tvAlipay;

    @Inject
    BalancePresenter mPresenter;
    private ShopInfo shopInfo;

    @Override
    protected void initComponent() {
        DaggerBalanceComponent.builder().balanceModule(new BalanceModule(this)).build().inject(this);
        shopInfo = ConfigUtil.getShopInfo();
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
        if (null == shopInfo.getAlipay() && null == shopInfo.getBank())
            UIUtil.showToast("还没有绑定银行卡或者支付宝，请先去绑定账号");
        else
            goActivity(WithdrawActivity.class);
    }

    /**
     * 绑定支付宝/修改支付宝绑定账号
     */
    @OnClick(R.id.tv_alipay)
    public void alipay() {
        CommonActivity.start(mActivity, CommonActivity.DISPLAY_BIND_ALIPAY, "绑定支付宝", null);
    }

    /**
     * 绑定银行卡/修改银行卡绑定账号
     */
    @OnClick(R.id.tv_bank_card)
    public void bankCard() {
        CommonActivity.start(mActivity, CommonActivity.DISPLAY_BIND_BANK, "绑定银行卡", null);
    }
}
