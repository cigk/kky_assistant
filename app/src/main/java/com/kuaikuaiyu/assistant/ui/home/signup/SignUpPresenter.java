package com.kuaikuaiyu.assistant.ui.home.signup;

import com.kuaikuaiyu.assistant.app.AppConfig;
import com.kuaikuaiyu.assistant.base.BasePresenter;
import com.kuaikuaiyu.assistant.modle.service.PassService;
import com.kuaikuaiyu.assistant.net.ReqParams;
import com.kuaikuaiyu.assistant.rx.IoTransformer;
import com.kuaikuaiyu.assistant.rx.RxSubscriber;

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
        signUpSubscriber = new RxSubscriber(signUpView) {
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
                .compose(new IoTransformer<>())
                .subscribe(signUpSubscriber);

    }

    /**
     * 注册
     *
     * @param phone
     * @param pwd
     */
    public void signUp(String phone, String pwd) {

    }

    @Override
    public void clean() {
        if (null != signUpSubscriber) signUpSubscriber.cancel();
        if (null != verifyCodeSubscriber) verifyCodeSubscriber.cancel();
    }
}
