package com.kuaikuaiyu.assistant.ui.pass.splash;

import com.kuaikuaiyu.assistant.model.service.PassService;
import com.kuaikuaiyu.assistant.rx.PerActivity;
import com.kuaikuaiyu.assistant.net.NetUtil;

import dagger.Module;
import dagger.Provides;

/**
 * Author:  Gavin
 * Email:   gavinking@163.com
 * Date:    2016/4/30
 * Desc:    启动页Module
 */
@Module
public class SplashModule {

    private SplashView splashView;

    public SplashModule(SplashView splashView) {
        this.splashView = splashView;
    }

    @PerActivity
    @Provides
    SplashView getSplashView() {
        return  splashView;
    }

    @PerActivity
    @Provides
    PassService getPassService() {
        return NetUtil.createForPass(PassService.class);
    }
}
