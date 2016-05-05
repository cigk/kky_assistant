package com.kuaikuaiyu.assistant.ui.home;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.kuaikuaiyu.assistant.R;
import com.kuaikuaiyu.assistant.base.BaseActivity;
import com.kuaikuaiyu.assistant.base.BasePresenter;
import com.kuaikuaiyu.assistant.utils.CommonUtil;

import butterknife.Bind;

/**
 * Author:  Gavin
 * Email:   gavinking@163.com
 * Date:    2016/5/3
 * Desc:
 */
public class HomeActivity extends BaseActivity implements View.OnClickListener {

    @Bind(R.id.ll_cash_desk)
    LinearLayout llCashDesk;
    @Bind(R.id.ll_balance)
    LinearLayout llBalance;
    @Bind(R.id.ll_assistant)
    LinearLayout llAssistant;
    @Bind(R.id.ll_school)
    LinearLayout llSchool;
    @Bind(R.id.iv_setting)
    ImageView ivSetting;

    @Override
    protected void setupActivityComponent() {

    }

    @Override
    protected int getRootView() {
        return R.layout.activity_home;
    }

    @Override
    protected void setListener() {
        llCashDesk.setOnClickListener(this);
        llBalance.setOnClickListener(this);
        llAssistant.setOnClickListener(this);
        llSchool.setOnClickListener(this);
        ivSetting.setOnClickListener(this);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
    }

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @Override
    public void onClick(View v) {
        if (CommonUtil.isFastDoubleClick()) return;
        switch (v.getId()) {
            case R.id.ll_cash_desk:

                break;

            case R.id.ll_balance:

                break;

            case R.id.ll_assistant:

                break;

            case R.id.ll_school:

                break;

            default:
                break;
        }
    }
}
