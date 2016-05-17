package com.kuaikuaiyu.assistant.ui.income.alipay;

import android.os.Bundle;
import android.text.InputType;
import android.widget.Button;
import android.widget.EditText;

import com.kuaikuaiyu.assistant.R;
import com.kuaikuaiyu.assistant.base.BaseActivity;
import com.kuaikuaiyu.assistant.base.BasePresenter;
import com.kuaikuaiyu.assistant.ui.widgets.KeyboardView;

import java.math.BigDecimal;

import butterknife.Bind;
import timber.log.Timber;

/**
 * Author:  Gavin
 * Email:   gavinking@163.com
 * Date:    2016/5/17
 * Desc:    支付宝支付功能
 */
public class AlipayActivity extends BaseActivity {

    @Bind(R.id.keyboard)
    KeyboardView keyboard;
    @Bind(R.id.et_money)
    EditText etMoney;
    @Bind(R.id.btn_commit)
    Button btnCommit;

    @Override
    protected void initComponent() {

    }

    @Override
    protected int getRootView() {
        return R.layout.activity_alipay;
    }

    @Override
    protected void setListener() {
        btnCommit.setOnClickListener(v->{
            BigDecimal bd = new BigDecimal(etMoney.getText().toString().trim()).multiply(new
                    BigDecimal(100));
            Timber.d("Num = %s ", String.valueOf(bd.intValue()));
        });
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        keyboard.setView(etMoney);
        etMoney.setInputType(InputType.TYPE_NULL);
    }

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }
}
