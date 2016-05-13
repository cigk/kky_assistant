package com.kuaikuaiyu.assistant.ui.account.withdraw;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.kuaikuaiyu.assistant.R;
import com.kuaikuaiyu.assistant.base.BaseActivity;
import com.kuaikuaiyu.assistant.base.BasePresenter;
import com.kuaikuaiyu.assistant.modle.domain.ShopInfo;
import com.kuaikuaiyu.assistant.ui.widgets.MoneyEditText;
import com.kuaikuaiyu.assistant.utils.ConfigUtil;
import com.kuaikuaiyu.assistant.utils.UIUtil;

import javax.inject.Inject;

import butterknife.Bind;

public class WithdrawActivity extends BaseActivity implements WithdrawView, View.OnClickListener {

    @Bind(R.id.tv_alipay)
    TextView tv_alipay;
    @Bind(R.id.tv_bank_card)
    TextView tv_bank_card;
    @Bind(R.id.et_money)
    MoneyEditText et_money;
    @Bind(R.id.btn_submit)
    Button btn_submit;
    @Bind(R.id.ll_alipay)
    LinearLayout ll_alipay;
    @Bind(R.id.rb_alipay)
    RadioButton rb_alipay;
    @Bind(R.id.rb_bank)
    RadioButton rb_bank;
    @Bind(R.id.ll_bank)
    LinearLayout ll_bank;
    @Bind(R.id.ib_back)
    ImageButton ib_back;
    @Bind(R.id.tv_title)
    TextView tv_title;
    @Bind(R.id.tv_withdraw_management)
    TextView tv_withdraw_management;
    @Bind(R.id.tv_right)
    TextView tv_right;

    @Inject
    WithdrawPresenter mPresenter;

    private boolean withdrawFlag = false;
    private String method = "alipay";
    private ShopInfo shopInfo;

    @Override
    protected void initComponent() {
        DaggerWithdrawComponent.builder().withdrawModule(new WithdrawModule(this))
                .build().inject(this);
        shopInfo = ConfigUtil.getShopInfo();
    }

    @Override
    protected int getRootView() {
        return R.layout.activity_withdraw;
    }

    @Override
    protected void setListener() {
        ib_back.setOnClickListener(this);
        btn_submit.setOnClickListener(this);
        ll_bank.setOnClickListener(this);
        ll_alipay.setOnClickListener(this);
        tv_withdraw_management.setOnClickListener(this);
        tv_right.setOnClickListener(this);

    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        mPresenter.updateAccountInfo();
    }

    @Override
    protected BasePresenter getPresenter() {
        return mPresenter;
    }

    /**
     * 提现时需要更新账户信息
     */
    private void initView() {
//        if (mAccount.min_withdraw_money == 0)
//            et_money.setHint("请输入提现金额");
//        else
//            et_money.setHint("不少于" + String.valueOf(mAccount.min_withdraw_money / 100));
//        tv_alipay.setText(mAccount.alipay);
//        tv_bank_card.setText(mAccount.bank.card_no);
        tv_title.setText("提现");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ib_back:
                finish();
                break;

            case R.id.ll_alipay:
                method = "alipay";
                rb_alipay.setChecked(true);
                rb_bank.setChecked(false);
                break;

            case R.id.ll_bank:
                method = "bank";
                rb_bank.setChecked(true);
                rb_alipay.setChecked(false);
                break;

            case R.id.btn_submit:
                if (TextUtils.isEmpty(et_money.getText())) {
                    UIUtil.showToast(R.string.err_withdraw_num);
                    return;
                }

                if (method.equals("alipay") && null == shopInfo.getAlipay()) {
                    UIUtil.showToast(R.string.err_no_alipay);
                    return;
                }

                if (method.equals("bank") && null == shopInfo.getBank()) {
                    UIUtil.showToast(R.string.err_no_bankcard);
                    return;
                }
                submitWithdraw(method, et_money.getMoney());
                break;

            case R.id.tv_withdraw_management:
                break;

            default:
                break;
        }
    }

    /**
     * 提交提现请求
     *
     * @param method
     * @param money
     */
    private void submitWithdraw(final String method, final int money) {
//        if (money > mAccount.balance) {
//            UIUtil.showToast(R.string.err_over_balance);
//            return;
//        }
//        if (money <= 0) {
//            UIUtil.showToast(R.string.err_withdraw_num);
//            return;
//        }
//        if (money < mAccount.min_withdraw_money / 100) {
//            UIUtil.showToast("提现金额不少于"
//                    + String.valueOf(mAccount.min_withdraw_money / 100));
//            return;
//        }

        mPresenter.withdraw(method, money);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        if (withdrawFlag) EventBus.getDefault().post(new SetupAccountInfoEvent());
    }

    @Override
    public void withdrawSucceed() {

    }
}
