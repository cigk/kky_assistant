package com.kuaikuaiyu.assistant.ui.pass.splash;

import android.os.Bundle;
import android.text.TextUtils;

import com.igexin.sdk.PushManager;
import com.kuaikuaiyu.assistant.R;
import com.kuaikuaiyu.assistant.base.BaseActivity;
import com.kuaikuaiyu.assistant.base.BasePresenter;
import com.kuaikuaiyu.assistant.ui.home.HomeActivity;
import com.kuaikuaiyu.assistant.ui.pass.login.LoginActivity;
import com.kuaikuaiyu.assistant.utils.ConfigUtil;
import com.kuaikuaiyu.assistant.utils.UIUtil;

import javax.inject.Inject;

public class SplashActivity extends BaseActivity implements SplashView {

    @Inject
    SplashPresenter mPresenter;

    @Override
    protected int getRootView() {
        return R.layout.activity_splash;
    }

    @Override
    protected void setListener() {
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
//        if (TextUtils.isEmpty(ConfigUtil.getUuid())) {
//            mPresenter.getUuid();
//        } else {
            jump();
//        }
    }

    @Override
    protected void initComponent() {
        PushManager.getInstance().initialize(UIUtil.getContext());
        DaggerSplashComponent.builder().splashModule(new SplashModule(this)).build().inject(this);
    }

    @Override
    protected BasePresenter getPresenter() {
        return this.mPresenter;
    }

    @Override
    public void jump() {
        UIUtil.postDelayed(() -> {
//            if (TextUtils.isEmpty(ConfigUtil.getAuthToken())) {
//                toLogin();
//            } else {
                toHome();
//            }
        }, 1000);
    }

    /**
     * 跳转到主页面
     */
    private void toHome() {
        goActivityAndFinishTransition(HomeActivity.class);
    }

    /**
     * 跳转到登录页面
     */
    private void toLogin() {
        goActivityAndFinishTransition(LoginActivity.class);
    }
}
