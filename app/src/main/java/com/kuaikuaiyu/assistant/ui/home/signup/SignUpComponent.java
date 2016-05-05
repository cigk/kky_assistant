package com.kuaikuaiyu.assistant.ui.home.signup;

import com.kuaikuaiyu.assistant.rx.PerActivity;

import dagger.Component;

/**
 * Author:  Gavin
 * Email:   gavinking@163.com
 * Date:    2016/4/30
 * Desc:    注册
 */
@PerActivity
@Component(modules = SignUpModule.class)
public interface SignUpComponent {
    SignUpActivity inject(SignUpActivity LoginActivity);
}
