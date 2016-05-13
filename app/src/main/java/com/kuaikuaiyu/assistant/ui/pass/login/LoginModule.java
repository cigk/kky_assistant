package com.kuaikuaiyu.assistant.ui.pass.login;

import com.kuaikuaiyu.assistant.modle.service.PassService;
import com.kuaikuaiyu.assistant.net.NetUtil;
import com.kuaikuaiyu.assistant.rx.PerActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Author:  Gavin
 * Email:   gavinking@163.com
 * Date:    2016/4/30
 * Desc:    启动页Module
 */
@Module
public class LoginModule {

    private LoginView loginView;

    public LoginModule(LoginView loginView) {
        this.loginView = loginView;
    }

    @PerActivity
    @Provides
    LoginView getView() {
        return loginView;
    }

    @PerActivity
    @Provides
    PassService getService() {
        return NetUtil.createForPass(PassService.class);
    }
}
