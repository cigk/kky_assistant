package com.kuaikuaiyu.assistant.ui.home.signup;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.kuaikuaiyu.assistant.R;
import com.kuaikuaiyu.assistant.base.BaseActivity;
import com.kuaikuaiyu.assistant.base.BasePresenter;
import com.kuaikuaiyu.assistant.utils.FormatUtil;
import com.kuaikuaiyu.assistant.utils.UIUtil;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Author:  Gavin
 * Email:   gavinking@163.com
 * Date:    2016/5/4
 * Desc:
 */
public class SignUpActivity extends BaseActivity implements SignUpView {
    @Bind(R.id.ib_back)
    ImageButton ibBack;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.et_mobile)
    EditText etMobile;
    @Bind(R.id.et_verify_code)
    EditText etVerifyCode;
    @Bind(R.id.et_pwd)
    EditText etPwd;
    @Bind(R.id.et_pwd_confirm)
    EditText etPwdConfirm;

    @Inject
    SignUpPresenter signUpPresenter;

    @Override
    protected void setupActivityComponent() {
        DaggerSignUpComponent.builder().signUpModule(new SignUpModule(this)).build().inject(this);
    }

    @Override
    protected int getRootView() {
        return R.layout.activity_signup;
    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void jump() {

    }

    @Override
    public void codeSent() {
        UIUtil.showToast("验证码发送成功");
    }

    @Override
    public void codeSendFail() {

    }


    /**
     * 获取验证码
     */
    @OnClick(R.id.btn_verify_code)
    public void getVerifyCode() {
        String phone = etMobile.getText().toString().trim();
        if (TextUtils.isEmpty(phone)) {
            UIUtil.showToast("电话号码不能为空");
            return;
        }

        if (!FormatUtil.isMobile(phone)) {
            UIUtil.showToast("电话号码不正确，请重新输入");
            return;
        }
        signUpPresenter.getVerifyCode(phone);
    }

    /**
     * 注册
     */
    @OnClick(R.id.btn_signup)
    public void signUp() {

    }
}
