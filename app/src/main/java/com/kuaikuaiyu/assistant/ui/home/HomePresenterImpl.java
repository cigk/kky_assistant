package com.kuaikuaiyu.assistant.ui.home;

import com.igexin.sdk.PushManager;
import com.kuaikuaiyu.assistant.app.AppConfig;
import com.kuaikuaiyu.assistant.modle.domain.ShopInfo;
import com.kuaikuaiyu.assistant.modle.service.AccountService;
import com.kuaikuaiyu.assistant.modle.service.PassService;
import com.kuaikuaiyu.assistant.net.ReqParams;
import com.kuaikuaiyu.assistant.rx.IoTransformer;
import com.kuaikuaiyu.assistant.rx.RxSubscriber;
import com.kuaikuaiyu.assistant.sys.event.ShopInfoUpdated;
import com.kuaikuaiyu.assistant.utils.ConfigUtil;
import com.kuaikuaiyu.assistant.utils.UIUtil;

import org.greenrobot.eventbus.EventBus;

/**
 * Author:  Gavin
 * Email:   gavinking@163.com
 * Date:    2016/5/13
 * Desc:
 */
public class HomePresenterImpl implements HomePresenter {
    private AccountService service;
    private PassService passService;
    private HomeView homeView;

    private RxSubscriber<ShopInfo> subscriber;

    public HomePresenterImpl(AccountService service, HomeView homeView, PassService passService) {
        this.passService = passService;
        this.service = service;
        this.homeView = homeView;
    }

    @Override
    public void clean() {
        if (null != subscriber) subscriber.cancel();
    }

    @Override
    public void getShopInfo(boolean needHandleView) {
        subscriber = new RxSubscriber<ShopInfo>(null) {
            @Override
            public void onNext(ShopInfo info) {
                ConfigUtil.setShopInfo(info);
                EventBus.getDefault().post(new ShopInfoUpdated());
                if (needHandleView) homeView.loadSucceed();
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                if (needHandleView) homeView.loadFail();
            }
        };
        ReqParams params = new ReqParams(ReqParams.GET, AppConfig.URL_SHOP_INFO);
        service.getShopInfo(params.getQueryMap())
                .compose(new IoTransformer<>())
                .subscribe(subscriber);
    }

    @Override
    public void updatePushId() {
        String did = PushManager.getInstance().getClientid(UIUtil.getContext());
        ReqParams params = new ReqParams(ReqParams.POST, AppConfig.URL_UPDATE_PUSH_ID);
        params.addParam("duuid", ConfigUtil.getUuid());
        params.addParam("device_push_id", did);
        passService.updatePushId(params.getQueryMap(), params.getFieldMap()).compose(new
                IoTransformer()).subscribe(new RxSubscriber(null) {
            @Override
            public void onNext(Object o) {

            }
        });
    }
}
