package com.kuaikuaiyu.assistant.ui.income.qrcode;

import android.os.Bundle;

import com.kuaikuaiyu.assistant.R;
import com.kuaikuaiyu.assistant.base.BaseActivity;
import com.kuaikuaiyu.assistant.base.BasePresenter;

import javax.inject.Inject;

/**
 * Created by binlly
 * <p>
 * date: 2016/5/10 14:49
 * desc:
 */
public class QrcodeActivity extends BaseActivity{
    @Inject
    QrcodePresenter mPresenter;

    @Override
    protected void initComponent() {

    }

    @Override
    protected int getRootView() {
        return R.layout.activity_qrcode;
    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected BasePresenter getPresenter() {
        return mPresenter;
    }
}
