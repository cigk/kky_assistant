package com.kuaikuaiyu.assistant.ui.income.alipay;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.kuaikuaiyu.assistant.R;
import com.kuaikuaiyu.assistant.base.BaseActivity;
import com.kuaikuaiyu.assistant.base.BasePresenter;
import com.kuaikuaiyu.assistant.ui.income.CommonModule;
import com.kuaikuaiyu.assistant.ui.income.qrcode.QrcodeActivity;
import com.kuaikuaiyu.assistant.ui.widgets.CommonTitleBar;
import com.kuaikuaiyu.assistant.ui.widgets.KeyboardView;
import com.kuaikuaiyu.assistant.utils.ConfigUtil;
import com.kuaikuaiyu.assistant.utils.MoneyUtil;

import java.math.BigDecimal;

import javax.inject.Inject;

import butterknife.Bind;

/**
 * Author:  Gavin
 * Email:   gavinking@163.com
 * Date:    2016/5/17
 * Desc:    支付宝支付功能
 */
public class AlipayActivity extends BaseActivity implements AlipayView, TextWatcher {

    @Bind(R.id.title)
    CommonTitleBar title;
    @Bind(R.id.keyboard)
    KeyboardView keyboard;
    @Bind(R.id.et_money)
    EditText etMoney;
    @Bind(R.id.btn_commit)
    Button btnCommit;
    @Bind(R.id.tv_money)
    TextView tvMoney;
    @Bind(R.id.tv_shop_name)
    TextView tvShopName;

    @Inject
    AlipayPresenter mPresenter;

    private int money;

    @Override
    protected void initComponent() {
        DaggerAlipayComponent.builder().commonModule(new CommonModule())
                .alipayModule(new AlipayModule(this)).build().inject(this);
    }

    @Override
    protected int getRootView() {
        return R.layout.activity_alipay;
    }

    @Override
    protected void setListener() {
        title.onBackClick(v -> onBackPressed());
        etMoney.addTextChangedListener(this);
        btnCommit.setOnClickListener(v -> {
            if (money == 0) {
                handleZero();
                return;
            }
            mPresenter.loadAlipayUrl(money);
        });
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        title.setTitle("支付宝收款");
        tvShopName.setText(ConfigUtil.getShopName());
        keyboard.setView(etMoney);
    }

    @Override
    protected BasePresenter getPresenter() {
        return mPresenter;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        if (TextUtils.isEmpty(s)) {
            tvMoney.setText("￥0.00");
            money = 0;
            return;
        }
        money = new BigDecimal(s.toString().trim()).multiply(new BigDecimal(100)).intValue();
        tvMoney.setText("￥" + MoneyUtil.format(money));
    }

    @Override
    public void loadSucceed(String url) {
        Intent intent = new Intent(this, QrcodeActivity.class);
        intent.putExtra(QrcodeActivity.PAY_TYEP, QrcodeActivity.TYEP_ALIPAY);
        intent.putExtra(QrcodeActivity.PAY_URL, url);
        goActivity(intent);
    }


    /**
     * 金额为0时处理
     */
    private void handleZero() {
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.shake_horizontal);
        tvMoney.startAnimation(anim);
//        UIUtil.showToast("金额不能为零");
    }
}
