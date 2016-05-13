package com.kuaikuaiyu.assistant.ui.pass.splash;

import com.kuaikuaiyu.assistant.rx.PerActivity;

import dagger.Component;

/**
 * Author:  Gavin
 * Email:   gavinking@163.com
 * Date:    2016/4/30
 * Desc:
 */
@PerActivity
@Component(modules = SplashModule.class)
public interface SplashComponent {
    SplashActivity inject(SplashActivity splashActivity);
}
