package com.kuaikuaiyu.assistant.ui.setting.pwd;

import com.kuaikuaiyu.assistant.modle.service.PassService;
import com.kuaikuaiyu.assistant.net.NetUtil;
import com.kuaikuaiyu.assistant.rx.PerActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Author:  Gavin
 * Email:   gavinking@163.com
 * Date:    2016/4/30
 * Desc:    修改密码Module
 */
@Module
public class ChangePwdModule {

    private ChangePwdView changePwdView;

    public ChangePwdModule(ChangePwdView changePwdView) {
        this.changePwdView = changePwdView;
    }

    @PerActivity
    @Provides
    ChangePwdView getView() {
        return changePwdView;
    }

    @PerActivity
    @Provides
    PassService getService() {
        return NetUtil.createForPass(PassService.class);
    }
}
