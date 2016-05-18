package com.kuaikuaiyu.assistant.ui.income;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.kuaikuaiyu.assistant.R;
import com.kuaikuaiyu.assistant.base.BaseActivity;
import com.kuaikuaiyu.assistant.base.BasePresenter;
import com.kuaikuaiyu.assistant.ui.common.WebViewActivity;
import com.kuaikuaiyu.assistant.ui.income.alipay.AlipayActivity;
import com.kuaikuaiyu.assistant.ui.income.qrcode.QrcodeActivity;
import com.kuaikuaiyu.assistant.ui.income.record.IncomeRecordActivity;
import com.kuaikuaiyu.assistant.ui.widgets.CommonTitleBar;
import com.kuaikuaiyu.assistant.utils.ConfigUtil;

import javax.inject.Inject;

import butterknife.Bind;

/**
 * Created by binlly
 * <p>
 * date: 2016/5/9 14:11
 * desc:
 */
public class IncomeActivity extends BaseActivity implements IncomeView {

    public static final String URL_CASH_DESK_INSTRUCTION =
            "file:///android_asset/cash_desk_instruction.html";

    @Bind(R.id.top_bar)
    CommonTitleBar topBar;
    @Bind(R.id.ll_income_qrcode)
    LinearLayout llIncomeQrcode;
    @Bind(R.id.ll_income_account)
    LinearLayout llIncomeAccount;
    @Bind(R.id.ll_alipay)
    LinearLayout llAlipay;

    @Inject
    IncomePresenter mPresenter;

    @Override
    protected void initComponent() {
        DaggerIncomeComponent.builder().commonModule(new CommonModule()).incomeModule(new
                IncomeModule(this)).build().inject(this);
    }

    @Override
    protected int getRootView() {
        return R.layout.activity_income;
    }

    @Override
    protected void setListener() {
        topBar.onBackClick(v -> onBackPressed());

        topBar.onRightImageClick(v -> WebViewActivity.start(this, URL_CASH_DESK_INSTRUCTION, "说明"));
        llIncomeQrcode.setOnClickListener(v -> {
            Intent intent = new Intent(this, QrcodeActivity.class);
            intent.putExtra(QrcodeActivity.PAY_TYEP, QrcodeActivity.TYPE_WECHAT);
            intent.putExtra(QrcodeActivity.PAY_URL, ConfigUtil.getShopInfo().getPay_url());
            goActivity(intent);
        });
        llIncomeAccount.setOnClickListener(v -> goActivity(IncomeRecordActivity.class));
        llAlipay.setOnClickListener(v -> goActivity(AlipayActivity.class));
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        topBar.setRightImgVisibility(View.VISIBLE);
        topBar.setRightImage(R.mipmap.ic_notification);
    }

    @Override
    protected BasePresenter getPresenter() {
        return mPresenter;
    }
}
