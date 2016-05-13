package com.kuaikuaiyu.assistant.ui.setting.pwd;

import com.kuaikuaiyu.assistant.rx.PerActivity;
import com.kuaikuaiyu.assistant.ui.pass.login.LoginActivity;
import com.kuaikuaiyu.assistant.ui.pass.login.LoginModule;

import dagger.Component;

/**
 * Author:  Gavin
 * Email:   gavinking@163.com
 * Date:    2016/4/30
 * Desc:
 */
@PerActivity
@Component(modules = ChangePwdModule.class)
public interface ChangePwdComponent {
    ChangePwdActivity inject(ChangePwdActivity LoginActivity);
}
