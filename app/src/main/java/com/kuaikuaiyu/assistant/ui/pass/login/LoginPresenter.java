package com.kuaikuaiyu.assistant.ui.pass.login;

import com.kuaikuaiyu.assistant.app.AppConfig;
import com.kuaikuaiyu.assistant.base.BasePresenter;
import com.kuaikuaiyu.assistant.model.domain.LoginResp;
import com.kuaikuaiyu.assistant.model.service.PassService;
import com.kuaikuaiyu.assistant.net.ReqParams;
import com.kuaikuaiyu.assistant.rx.IoTransformer;
import com.kuaikuaiyu.assistant.rx.RxSubscriber;
import com.kuaikuaiyu.assistant.utils.ConfigUtil;
import com.kuaikuaiyu.assistant.utils.DigestUtil;

import javax.inject.Inject;

/**
 * Author:  Gavin
 * Email:   gavinking@163.com
 * Date:    2016/5/4
 * Desc:
 */
public class LoginPresenter implements BasePresenter {
    private PassService service;
    private LoginView loginView;
    private RxSubscriber<LoginResp> loginSubscriber;

    @Inject
    public LoginPresenter(PassService service, LoginView loginView) {
        this.service = service;
        this.loginView = loginView;
    }

    /**
     * 登录
     *
     * @param mobile
     * @param pwd
     */
    public void login(String mobile, String pwd) {
        loginSubscriber = new RxSubscriber<LoginResp>(loginView) {
            @Override
            public void onNext(LoginResp loginResp) {
                ConfigUtil.setAuthToken(loginResp.at);
                ConfigUtil.setShopName(loginResp.nickname);
                ConfigUtil.setShopMobile(mobile);
                ConfigUtil.setLoginPhone(mobile);
                loginView.jump();
            }
        };
        subscribe(mobile, pwd);
    }

    /**
     * 执行订阅
     *
     * @param mobile
     * @param pwd
     */
    private void subscribe(String mobile, String pwd) {
        ReqParams params = new ReqParams(ReqParams.POST, AppConfig.URL_LOGIN);
        params.addParam("mobile", mobile);
        params.addParam("password", DigestUtil.getMd5(pwd));
        service.login(params.getQueryMap(), params.getFieldMap())
                .compose(new IoTransformer<>())
                .subscribe(loginSubscriber);
    }

    @Override
    public void clean() {
        if (null != loginSubscriber) loginSubscriber.cancel();
    }
}
