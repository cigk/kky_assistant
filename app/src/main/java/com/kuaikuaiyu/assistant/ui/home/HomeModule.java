package com.kuaikuaiyu.assistant.ui.home;

import com.kuaikuaiyu.assistant.modle.service.AccountService;
import com.kuaikuaiyu.assistant.modle.service.PassService;
import com.kuaikuaiyu.assistant.net.NetUtil;
import com.kuaikuaiyu.assistant.rx.PerFragment;
import com.kuaikuaiyu.assistant.ui.home.HomeView;

import dagger.Module;
import dagger.Provides;

/**
 * Author:  Gavin
 * Email:   gavinking@163.com
 * Date:    2016/4/30
 * Desc:
 */
@Module
public class HomeModule {

    private HomeView homeView;

    public HomeModule(HomeView homeView) {
        this.homeView = homeView;
    }

    @PerFragment
    @Provides
    HomeView getView() {
        return homeView;
    }

    @PerFragment
    @Provides
    AccountService getService() {
        return NetUtil.create(AccountService.class);
    }

    @PerFragment
    @Provides
    PassService getPassService() {
        return NetUtil.createForPass(PassService.class);
    }
}
