package com.kuaikuaiyu.assistant.ui.home;

import android.os.Bundle;

import com.kuaikuaiyu.assistant.R;
import com.kuaikuaiyu.assistant.base.BaseActivity;
import com.kuaikuaiyu.assistant.base.BasePresenter;
import com.kuaikuaiyu.assistant.utils.UIUtil;
import com.umeng.update.UmengUpdateAgent;

/**
 * Author:  Gavin
 * Email:   gavinking@163.com
 * Date:    2016/5/3
 * Desc:    主页面
 */
public class HomeActivity extends BaseActivity {


    @Override
    protected void initComponent() {
    }

    @Override
    protected int getRootView() {
        return R.layout.activity_home;
    }

    @Override
    protected void setListener() {
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_container, new
                HomeFragment()).commit();
        UIUtil.postDelayed(() -> UmengUpdateAgent.update(HomeActivity.this), 500);
    }

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }
}
