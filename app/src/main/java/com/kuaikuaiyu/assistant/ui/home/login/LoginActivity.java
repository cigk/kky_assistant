package com.kuaikuaiyu.assistant.ui.home.login;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.kuaikuaiyu.assistant.R;
import com.kuaikuaiyu.assistant.base.BaseActivity;
import com.kuaikuaiyu.assistant.base.BasePresenter;
import com.kuaikuaiyu.assistant.ui.home.HomeActivity;
import com.kuaikuaiyu.assistant.ui.home.signup.SignUpActivity;
import com.kuaikuaiyu.assistant.utils.ConfigUtil;
import com.kuaikuaiyu.assistant.utils.DigestUtil;
import com.kuaikuaiyu.assistant.utils.FormatUtil;
import com.kuaikuaiyu.assistant.utils.UIUtil;

import javax.inject.Inject;

import butterknife.Bind;

/**
 * 登录页面
 */
public class LoginActivity extends BaseActivity implements LoginView, View.OnClickListener {

    @Bind(R.id.et_mobile)
    EditText etMobile;
    @Bind(R.id.et_pwd)
    EditText etPwd;
    @Bind(R.id.btn_login)
    Button btnLogin;
    @Bind(R.id.btn_signup)
    Button btnSignup;
    @Bind(R.id.tv_forget_pwd)
    TextView tvForgetPwd;

    @Inject
    LoginPresenter loginPresenter;

    @Override
    protected void setupActivityComponent() {
        DaggerLoginComponent.builder().loginModule(new LoginModule(this)).build().inject(this);
    }

    @Override
    protected int getRootView() {
        return R.layout.activity_login;
    }

    @Override
    protected void setListener() {
        btnLogin.setOnClickListener(this);
        tvForgetPwd.setOnClickListener(this);
        btnSignup.setOnClickListener(this);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
    }

    @Override
    protected BasePresenter getPresenter() {
        return loginPresenter;
    }

    @Override
    protected void onResume() {
        super.onResume();
        etMobile.setText(ConfigUtil.getLoginPhone());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                login();
                break;

            case R.id.tv_forget_pwd:
//                goActivity(ForgetPassActivity.class);
                break;

            case R.id.btn_signup:
                goActivity(SignUpActivity.class);
                break;

            default:
                break;
        }
    }

    @Override
    public void jump() {
        UIUtil.showToast("登录成功");
        goActivityAndFinish(HomeActivity.class);
    }

    /**
     * 登录
     */
    private void login() {
        final String mobile = etMobile.getText().toString();
        final String password = etPwd.getText().toString();

        if (!FormatUtil.isMobile(mobile)) {
            UIUtil.showToast(R.string.err_no_username);
            return;
        }

        if (TextUtils.isEmpty(password)) {
            UIUtil.showToast(R.string.err_no_pwd);
            return;
        }

        if (password.length() < 6 || password.length() > 16) {
            UIUtil.showToast(R.string.err_length_pwd);
            return;
        }

        loginPresenter.login(mobile, DigestUtil.getMd5(password));
    }
}
