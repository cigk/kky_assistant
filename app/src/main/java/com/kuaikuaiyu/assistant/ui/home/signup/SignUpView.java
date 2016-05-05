package com.kuaikuaiyu.assistant.ui.home.signup;

import com.kuaikuaiyu.assistant.base.BaseView;

/**
 * Author:  Gavin
 * Email:   gavinking@163.com
 * Date:    2016/5/4
 * Desc:
 */
public interface SignUpView extends BaseView {
    /**
     * 跳转
     */
    void jump();

    /**
     * 验证码发送成功
     */
    void codeSent();

    /**
     * 验证码发送失败
     */
    void codeSendFail();
}
