package com.kuaikuaiyu.assistant.ui.home.signup;

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
public class SignUpModule {

    private SignUpView signUpView;

    public SignUpModule(SignUpView signUpView) {
        this.signUpView = signUpView;
    }

    @PerActivity
    @Provides
    SignUpView getSignUpView() {
        return signUpView;
    }

    @PerActivity
    @Provides
    PassService getPassService() {
        return NetUtil.createForPass(PassService.class);
    }
}
