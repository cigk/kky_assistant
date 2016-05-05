package com.kuaikuaiyu.assistant.ui.home.signup;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.kuaikuaiyu.assistant.R;
import com.kuaikuaiyu.assistant.base.BaseActivity;
import com.kuaikuaiyu.assistant.base.BasePresenter;
import com.kuaikuaiyu.assistant.ui.home.login.LoginActivity;
import com.kuaikuaiyu.assistant.utils.CommonUtil;
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
    @Bind(R.id.btn_verify_code)
    Button btnVerfyCode;

    private static final int TIME_OUT = 30;

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
        return signUpPresenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void jump() {
        UIUtil.showToast("注册成功，马上登陆吧");
        goActivityAndFinish(LoginActivity.class);
    }

    @Override
    public void codeSent() {
        UIUtil.showToast("验证码发送成功");
        UIUtil.post(countDown);
    }

    @Override
    public void codeSendFail() {
        UIUtil.removeCallbacks(countDown);
        btnVerfyCode.setText("验证手机");
        btnVerfyCode.setEnabled(true);
    }


    /**
     * 获取验证码
     */
    @OnClick(R.id.btn_verify_code)
    public void getVerifyCode() {
        String mobile = etMobile.getText().toString().trim();
        if (CommonUtil.checkEmpty(mobile, "电话号码不能为空")) return;

        if (!FormatUtil.isMobile(mobile)) {
            UIUtil.showToast("电话号码不正确，请重新输入");
            return;
        }
        signUpPresenter.getVerifyCode(mobile);
    }

    /**
     * 注册
     */
    @OnClick(R.id.btn_signup)
    public void signUp() {
        String mobile = etMobile.getText().toString().trim();
        String pwd = etPwd.getText().toString().trim();
        String pwdConfirm = etPwdConfirm.getText().toString().trim();
        String verifyCode = etVerifyCode.getText().toString().trim();

        if (CommonUtil.checkEmpty(mobile, "电话号码不能为空")) return;
        if (CommonUtil.checkEmpty(pwd, "密码不能为空")) return;
        if (CommonUtil.checkEmpty(pwdConfirm, "密码确认不能为空")) return;
        if (CommonUtil.checkEmpty(verifyCode, "验证码不能为空")) return;

        if (!FormatUtil.isMobile(mobile)) {
            UIUtil.showToast("电话号码不正确，请重新输入");
            return;
        }

        if (pwd.length() < 6 || pwd.length() > 16 ||
                pwdConfirm.length() < 6 || pwdConfirm.length() > 16) {
            UIUtil.showToast("密码长度为6~16位");
            return;
        }

        signUpPresenter.signUp(mobile, pwd, verifyCode);

    }

    /**
     * 获取验证码倒计时
     */
    private Runnable countDown = new Runnable() {
        private int time = TIME_OUT;

        public void run() {
            if (time > 0) {
                btnVerfyCode.setEnabled(false);
                btnVerfyCode.setText(time + "秒后重试");
                time--;
                UIUtil.postDelayed(this, 1000);
            } else {
                time = TIME_OUT;
                btnVerfyCode.setEnabled(true);
                btnVerfyCode.setText("验证手机");
            }
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        UIUtil.removeCallbacks(countDown);
    }
}
