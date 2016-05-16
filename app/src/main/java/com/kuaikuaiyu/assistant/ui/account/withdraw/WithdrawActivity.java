package com.kuaikuaiyu.assistant.ui.account.withdraw;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.kuaikuaiyu.assistant.R;
import com.kuaikuaiyu.assistant.base.BaseActivity;
import com.kuaikuaiyu.assistant.base.BasePresenter;
import com.kuaikuaiyu.assistant.modle.domain.ShopInfo;
import com.kuaikuaiyu.assistant.sys.event.UpdateShopInfo;
import com.kuaikuaiyu.assistant.ui.widgets.MoneyEditText;
import com.kuaikuaiyu.assistant.utils.CommonUtil;
import com.kuaikuaiyu.assistant.utils.ConfigUtil;
import com.kuaikuaiyu.assistant.utils.UIUtil;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

import butterknife.Bind;

public class WithdrawActivity extends BaseActivity implements WithdrawView, View.OnClickListener {

    private static final String METHOD_ALIPAY = "alipay";
    private static final String METHOD_BANK = "bank";


    @Bind(R.id.tv_alipay)
    TextView tvAlipay;
    @Bind(R.id.tv_bank_card)
    TextView tvBankCard;
    @Bind(R.id.et_money)
    MoneyEditText etMoney;
    @Bind(R.id.btn_submit)
    Button btnSubmit;
    @Bind(R.id.ll_alipay)
    LinearLayout llAlipay;
    @Bind(R.id.rb_alipay)
    RadioButton rbAlipay;
    @Bind(R.id.rb_bank)
    RadioButton rbBank;
    @Bind(R.id.ll_bank)
    LinearLayout llBank;
    @Bind(R.id.ib_back)
    ImageButton ibBack;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.et_pwd)
    EditText etPwd;

    @Inject
    WithdrawPresenter mPresenter;

    private String method = METHOD_ALIPAY;
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
        ibBack.setOnClickListener(this);
        btnSubmit.setOnClickListener(this);
        llBank.setOnClickListener(this);
        llAlipay.setOnClickListener(this);

    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        tvTitle.setText("提现");
        mPresenter.updateAccountInfo();
    }

    @Override
    protected BasePresenter getPresenter() {
        return mPresenter;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ib_back:
                onBackPressed();
                break;

            case R.id.ll_alipay:
                method = METHOD_ALIPAY;
                rbAlipay.setChecked(true);
                rbBank.setChecked(false);
                break;

            case R.id.ll_bank:
                method = METHOD_BANK;
                rbBank.setChecked(true);
                rbAlipay.setChecked(false);
                break;

            case R.id.btn_submit:
                if (method.equals("alipay") && null == shopInfo.getAlipay()) {
                    UIUtil.showToast(R.string.err_no_alipay);
                    return;
                }

                if (method.equals("bank") && null == shopInfo.getBank()) {
                    UIUtil.showToast(R.string.err_no_bankcard);
                    return;
                }
                submit();
                break;

            default:
                break;
        }
    }

    /**
     * 提交提现请求
     */
    private void submit() {
        String moneyStr = etMoney.getText().toString().trim();
        String pwd = etPwd.getText().toString().trim();
        if (CommonUtil.checkEmpty(moneyStr, R.string.err_withdraw_num)) return;
        if (CommonUtil.checkEmpty(pwd, "密码不能为空")) return;
        int money = etMoney.getMoney();
        if (money > shopInfo.getBalance()) {
            UIUtil.showToast(R.string.err_over_balance);
            return;
        }
        if (money <= 0) {
            UIUtil.showToast(R.string.err_withdraw_num);
            return;
        }

        int id = METHOD_ALIPAY.equals(method) ?
                shopInfo.getAlipay().getId() : shopInfo.getBank().getId();
//        if (money < mAccount.min_withdraw_money / 100) {
//            UIUtil.showToast("提现金额不少于"
//                    + String.valueOf(mAccount.min_withdraw_money / 100));
//            return;
//        }

        mPresenter.withdraw(id, money, pwd);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        if (withdrawFlag) EventBus.getDefault().post(new SetupAccountInfoEvent());
    }

    @Override
    public void withdrawSucceed() {
        UIUtil.showToast("提现申请提交成功");
        UIUtil.postDelayed(() -> onBackPressed(), 1000);
        EventBus.getDefault().post(new UpdateShopInfo());
    }
}