package com.kuaikuaiyu.assistant.ui.setting.pwd;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.kuaikuaiyu.assistant.R;
import com.kuaikuaiyu.assistant.base.BaseActivity;
import com.kuaikuaiyu.assistant.base.BasePresenter;
import com.kuaikuaiyu.assistant.utils.CommonUtil;
import com.kuaikuaiyu.assistant.utils.UIUtil;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Author:  Gavin
 * Email:   gavinking@163.com
 * Date:    2016/5/6
 * Desc:
 */
public class ChangePwdActivity extends BaseActivity implements ChangePwdView {

    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.et_pwd_old)
    EditText etPwdOld;
    @Bind(R.id.et_pwd_new)
    EditText etPwdNew;
    @Bind(R.id.et_pwd_new_confirm)
    EditText etPwdNewConfirm;

    @Inject
    ChangePwdPresenter mPresenter;


    @Override
    protected void initComponent() {
        DaggerChangePwdComponent.builder()
                .changePwdModule(new ChangePwdModule(this))
                .build().inject(this);
    }

    @Override
    protected int getRootView() {
        return R.layout.activity_change_pwd;
    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        tvTitle.setText(UIUtil.getString(R.string.modify_pwd));
    }

    @Override
    protected BasePresenter getPresenter() {
        return mPresenter;
    }

    @Override
    public void jump() {
        UIUtil.showToast("密码修改成功，再登录请使用新密码");
        onBackPressed();
    }

    @OnClick(R.id.ib_back)
    public void back() {
        onBackPressed();
    }

    @OnClick(R.id.btn_confirm)
    public void changePwd() {
        String oldPwd = etPwdOld.getText().toString().trim();
        String newPwd = etPwdNew.getText().toString().trim();
        String newPwdConfirm = etPwdNewConfirm.getText().toString().trim();

        if (CommonUtil.checkEmpty(oldPwd, "旧密码不能为空")) return;
        if (CommonUtil.checkEmpty(newPwd, "新密码不能为空")) return;
        if (CommonUtil.checkEmpty(newPwdConfirm, "密码确认不能为空")) return;

        if (oldPwd.length() < 6 || oldPwd.length() > 16 ||
                newPwd.length() < 6 || newPwd.length() > 16 ||
                newPwdConfirm.length() < 6 || newPwdConfirm.length() > 16) {
            UIUtil.showToast("密码长度为6~16位");
            return;
        }

        if (!newPwd.equals(newPwdConfirm)) {
            UIUtil.showToast("两次输入的密码不一致");
            return;
        }

        mPresenter.changePwd(oldPwd, newPwd);
    }
}
