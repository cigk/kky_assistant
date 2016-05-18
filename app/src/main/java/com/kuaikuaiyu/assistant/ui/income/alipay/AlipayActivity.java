package com.kuaikuaiyu.assistant.ui.income.alipay;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.kuaikuaiyu.assistant.R;
import com.kuaikuaiyu.assistant.base.BaseActivity;
import com.kuaikuaiyu.assistant.base.BasePresenter;
import com.kuaikuaiyu.assistant.ui.widgets.CommonTitleBar;
import com.kuaikuaiyu.assistant.ui.widgets.KeyboardView;
import com.kuaikuaiyu.assistant.utils.MoneyUtil;

import java.math.BigDecimal;

import butterknife.Bind;

/**
 * Author:  Gavin
 * Email:   gavinking@163.com
 * Date:    2016/5/17
 * Desc:    支付宝支付功能
 */
public class AlipayActivity extends BaseActivity implements TextWatcher {

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

    @Override
    protected void initComponent() {

    }

    @Override
    protected int getRootView() {
        return R.layout.activity_alipay;
    }

    @Override
    protected void setListener() {
        title.onBackClick(v -> onBackPressed());
        etMoney.addTextChangedListener(this);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        title.setTitle("支付宝收款");
        keyboard.setView(etMoney);
    }

    @Override
    protected BasePresenter getPresenter() {
        return null;
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
            return;
        }
        String str = s.toString().trim();
        int posDot = str.indexOf(".");
        if (posDot == 0) {
            str = new StringBuilder("0").append(str).toString();
        }

        String money = MoneyUtil.format(new BigDecimal(str).multiply(new BigDecimal
                (100)).intValue());
        tvMoney.setText("￥" + money);
    }
}
