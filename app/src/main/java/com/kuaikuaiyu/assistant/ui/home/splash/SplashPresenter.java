package com.kuaikuaiyu.assistant.ui.home.splash;

import com.kuaikuaiyu.assistant.app.AppConfig;
import com.kuaikuaiyu.assistant.base.BasePresenter;
import com.kuaikuaiyu.assistant.modle.domain.Uuid;
import com.kuaikuaiyu.assistant.modle.service.PassService;
import com.kuaikuaiyu.assistant.net.ReqParams;
import com.kuaikuaiyu.assistant.rx.IoTransformer;
import com.kuaikuaiyu.assistant.rx.RxSubscriber;
import com.kuaikuaiyu.assistant.utils.ConfigUtil;
import com.kuaikuaiyu.assistant.utils.DeviceUtil;

import javax.inject.Inject;

import timber.log.Timber;

/**
 * Author:  Gavin
 * Email:   gavinking@163.com
 * Date:    2016/4/30
 * Desc:    启动页Presenter
 */
public class SplashPresenter implements BasePresenter {

    private PassService service;
    private SplashView splashView;
    private RxSubscriber<Uuid> uuidSubscriber;


    @Inject
    public SplashPresenter(PassService service, SplashView splashView) {
        this.service = service;
        this.splashView = splashView;
    }

    /**
     * 获取用户的Uuid
     */
    public void getUuid() {
        uuidSubscriber = new RxSubscriber<Uuid>(null) {
            @Override
            public void onNext(Uuid uuid) {
                Timber.d("Uuid = %s", uuid);
                ConfigUtil.setUuid(uuid.uuid);
                splashView.jump();
            }
        };
        subscribe();
    }

    /**
     * 执行获取Uuid的订阅
     */
    private void subscribe() {
        ReqParams params = new ReqParams(ReqParams.POST, AppConfig.URL_INFO);
        params.addParam("deviceHashId", DeviceUtil.getDeviceId());
        params.addParam("screenSize", DeviceUtil.getScreenSize());
        params.addParam("os", DeviceUtil.getOs());
        params.addParam("osVersion", DeviceUtil.getOsVersion());
//        params.addParam("appName", DeviceUtil.getAppName());
        params.addParam("appName", "ioshop");
        params.addParam("appVersion", DeviceUtil.getAppVersion());
        params.addParam("deviceModel", DeviceUtil.getDeviceModle());
        service.updateDeviceInfo(params.getQueryMap(), params.getFieldMap())
                .compose(new IoTransformer<>())
                .subscribe(uuidSubscriber);
    }

    @Override
    public void clean() {
        if (null != uuidSubscriber) uuidSubscriber.cancel();
    }
}
