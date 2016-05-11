package com.kuaikuaiyu.assistant.ui.common;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.kuaikuaiyu.assistant.R;
import com.kuaikuaiyu.assistant.base.BaseActivity;
import com.kuaikuaiyu.assistant.base.BaseFragment;
import com.kuaikuaiyu.assistant.base.BasePresenter;
import com.kuaikuaiyu.assistant.ui.account.bindalipay.BindAlipayFragment;
import com.kuaikuaiyu.assistant.ui.account.bindbank.BindBankFragment;
import com.kuaikuaiyu.assistant.ui.account.records.WithdrawRecordFragment;
import com.kuaikuaiyu.assistant.ui.income.account.AccountFragment;
import com.kuaikuaiyu.assistant.ui.widgets.CommonTitleBar;

import butterknife.Bind;

/**
 * Author:  Gavin
 * Email:   gavinking@163.com
 * Date:    2015/12/2
 * Desc:    通用Activity， 用来承载不同的Fragment
 */
public class CommonActivity extends BaseActivity {

    public static final String DISPLAY_TYPE = "DISPLAY_TYPE";
    public static final String TITLE = "TITLE";
    public static final String BUNDLE = "BUNDlE";

    public static final int DISPLAY_BIND_BANK = 1;
    public static final int DISPLAY_BIND_ALIPAY = 2;
    public static final int DISPLAY_INCOME_ACCOUNT = 3;
    public static final int DISPLAY_WITHDRAW_RECORDS = 5;


    @Bind(R.id.top_bar)
    CommonTitleBar top_bar;

    private BaseFragment mFragment;
    private Bundle bundle;
    private String title;
    private int displayType;

    @Override
    protected void initComponent() {
        displayType = getIntent().getIntExtra(DISPLAY_TYPE, 0);
        title = getIntent().getStringExtra(TITLE);
        bundle = getIntent().getBundleExtra(BUNDLE);
    }

    @Override
    protected int getRootView() {
        return R.layout.activity_common;
    }

    @Override
    protected void setListener() {
        top_bar.onBackClick(v -> onBackPressed());
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        top_bar.setTitle(title);
        createFragment();
        if (mFragment == null) {
            throw new RuntimeException("Fragment can not be null");
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_container, mFragment)
                .commit();
        //        mFragment.show();
    }

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    /**
     * 创建Fragment
     */
    private void createFragment() {
        switch (displayType) {
            case DISPLAY_BIND_BANK:
                mFragment = new BindBankFragment();
                break;

            case DISPLAY_BIND_ALIPAY:
                mFragment = new BindAlipayFragment();
                break;

            case DISPLAY_INCOME_ACCOUNT:
                mFragment = new AccountFragment();
                break;

            case DISPLAY_WITHDRAW_RECORDS:
                mFragment = new WithdrawRecordFragment();
                break;

            default:
                break;
        }
        if (bundle != null)
            mFragment.setArguments(bundle);
    }

    /**
     * 启动Activity
     *
     * @param context
     * @param bundle
     * @param title
     */
    public static void start(@NonNull Context context, @NonNull int displayType,
                             @NonNull String title, Bundle bundle) {
        Intent intent = new Intent(context, CommonActivity.class);
        intent.putExtra(DISPLAY_TYPE, displayType);
        intent.putExtra(TITLE, title);
        intent.putExtra(BUNDLE, bundle);
        context.startActivity(intent);
    }

    /**
     * 启动ActivityForResult
     *
     * @param context
     * @param bundle
     * @param title
     */
    public static void startForResult(@NonNull Activity context, @NonNull int displayType,
                                      @NonNull String title, @NonNull int requestCode, Bundle bundle) {
        Intent intent = new Intent(context, CommonActivity.class);
        intent.putExtra(DISPLAY_TYPE, displayType);
        intent.putExtra(TITLE, title);
        intent.putExtra(BUNDLE, bundle);
        context.startActivityForResult(intent, requestCode);
    }
}
