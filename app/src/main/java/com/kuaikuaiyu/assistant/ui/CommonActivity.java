package com.kuaikuaiyu.assistant.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.kuaikuaiyu.assistant.R;
import com.kuaikuaiyu.assistant.base.BaseActivity;
import com.kuaikuaiyu.assistant.base.BaseFragment;
import com.kuaikuaiyu.assistant.base.BasePresenter;

import butterknife.Bind;

/**
 * Author:  Gavin
 * Email:   gavinking@163.com
 * Date:    2015/12/2
 * Desc:    通用Activity， 用来承载不同的Fragment
 */
public class CommonActivity extends BaseActivity implements View.OnClickListener {

    public static final String DISPLAY_TYPE = "DISPLAY_TYPE";
    public static final String TITLE = "TITLE";
    public static final String BUNDLE = "BUNDlE";

    public static final int DISPLAY_MODIFY_GOODS = 1;
    public static final int DISPLAY_ORDER_DETAIL = 2;
    public static final int DISPLAY_DELIVERY_METHOD = 3;
    public static final int DISPLAY_BIND_BANK = 4;
    public static final int DISPLAY_BIND_ALIPAY = 5;
    public static final int DISPLAY_RESERVE_SETTING = 6;
    public static final int DISPLAY_PROCESSED_ORDER = 7;
    public static final int DISPLAY_GOODS_DETAIL = 8;
    public static final int DISPLAY_ADD_GOODS = 9;
    public static final int DISPLAY_GOODS_INFO = 10;
    public static final int DISPLAY_CASH_ON_DELIVERY_SETTING = 11;
    public static final int DISPLAY_SHOP_INFO = 12;
    public static final int DISPLAY_PRINTER_INFO = 13;
    public static final int DISPLAY_MORE = 15;
    public static final int DISPLAY_BILL = 16;
    public static final int DISPLAY_WITHDRAW = 17;
    public static final int DISPLAY_ABOUT = 18;
    public static final int DISPLAY_LUCKY_MONEY = 19;
    public static final int DISPLAY_SHARE_LUCKY_MONEY = 20;

    @Bind(R.id.ib_back)
    ImageButton ib_back;
    @Bind(R.id.tv_title)
    TextView tv_title;

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
        ib_back.setOnClickListener(this);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        tv_title.setText(title);
        createFragment();
        if (mFragment == null) {
            throw new RuntimeException("Fragment can not be null");
        }
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fl_container, mFragment).commit();
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
            case DISPLAY_MODIFY_GOODS:
//                mFragment = new ModifyGoodsFragment();
                break;


            default:
                break;
        }
        if (bundle != null) mFragment.setArguments(bundle);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ib_back:
                finish();
                break;

            default:
                break;
        }
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
                                      @NonNull String title, @NonNull int requestCode,
                                      Bundle bundle) {
        Intent intent = new Intent(context, CommonActivity.class);
        intent.putExtra(DISPLAY_TYPE, displayType);
        intent.putExtra(TITLE, title);
        intent.putExtra(BUNDLE, bundle);
        context.startActivityForResult(intent, requestCode);
    }
}
