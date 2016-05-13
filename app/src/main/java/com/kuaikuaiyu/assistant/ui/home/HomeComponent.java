package com.kuaikuaiyu.assistant.ui.home;

import com.kuaikuaiyu.assistant.rx.PerFragment;

import dagger.Component;

/**
 * Author:  Gavin
 * Email:   gavinking@163.com
 * Date:    2016/4/30
 * Desc:
 */
@PerFragment
@Component(modules = HomeModule.class)
public interface HomeComponent {
    HomeFragment inject(HomeFragment homeFragment);
}
