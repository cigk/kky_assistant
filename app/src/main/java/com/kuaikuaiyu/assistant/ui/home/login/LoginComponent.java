package com.kuaikuaiyu.assistant.ui.home.login;

import com.kuaikuaiyu.assistant.rx.PerActivity;

import dagger.Component;

/**
 * Author:  Gavin
 * Email:   gavinking@163.com
 * Date:    2016/4/30
 * Desc:
 */
@PerActivity
@Component(modules = LoginModule.class)
public interface LoginComponent {
    LoginActivity inject(LoginActivity LoginActivity);
}
