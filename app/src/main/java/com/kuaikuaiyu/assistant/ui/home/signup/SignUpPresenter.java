package com.kuaikuaiyu.assistant.ui.home.signup;

import com.kuaikuaiyu.assistant.app.AppConfig;
import com.kuaikuaiyu.assistant.base.BasePresenter;
import com.kuaikuaiyu.assistant.modle.service.PassService;
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
 * Desc:    注册Presenter
 */
public class SignUpPresenter implements BasePresenter {

    private PassService service;
    private SignUpView signUpView;
    private RxSubscriber signUpSubscriber;
    private RxSubscriber verifyCodeSubscriber;

    @Inject
    public SignUpPresenter(PassService service, SignUpView signUpView) {
        this.service = service;
        this.signUpView = signUpView;
    }

    /**
     * 获取验证码
     *
     * @param phone
     */
    public void getVerifyCode(String phone) {
        verifyCodeSubscriber = new RxSubscriber(signUpView) {
            @Override
            public void onNext(Object o) {
                signUpView.codeSent();
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                signUpView.codeSendFail();
            }
        };

        ReqParams params = new ReqParams(ReqParams.POST, AppConfig.URL_SIGN_UP_SMS);
        params.addParam("mobile", phone);

        service.getSignUpSms(params.getQueryMap(), params.getFieldMap())
                .compose(new IoTransformer())
                .subscribe(verifyCodeSubscriber);

    }

    /**
     * 注册
     *
     * @param mobile
     * @param pwd
     */
    public void signUp(String mobile, String pwd, String code) {
        signUpSubscriber = new RxSubscriber(signUpView) {
            @Override
            public void onNext(Object o) {
                ConfigUtil.setLoginPhone(mobile);
                signUpView.jump();
            }
        };

        ReqParams params = new ReqParams(ReqParams.POST, AppConfig.URL_SIGN_UP);
        params.addParam("mobile", mobile);
        params.addParam("code", code);
        params.addParam("password", DigestUtil.getMd5(pwd));
        params.addParam("duuid", ConfigUtil.getUuid());
        service.signUp(params.getQueryMap(), params.getFieldMap())
                .compose(new IoTransformer())
                .subscribe(signUpSubscriber);

    }

    @Override
    public void clean() {
        if (null != signUpSubscriber) signUpSubscriber.cancel();
        if (null != verifyCodeSubscriber) verifyCodeSubscriber.cancel();
    }
}
