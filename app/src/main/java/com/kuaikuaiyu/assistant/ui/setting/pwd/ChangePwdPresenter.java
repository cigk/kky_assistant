package com.kuaikuaiyu.assistant.ui.setting.pwd;

import com.kuaikuaiyu.assistant.app.AppConfig;
import com.kuaikuaiyu.assistant.base.BasePresenter;
import com.kuaikuaiyu.assistant.modle.service.PassService;
import com.kuaikuaiyu.assistant.net.ReqParams;
import com.kuaikuaiyu.assistant.rx.IoTransformer;
import com.kuaikuaiyu.assistant.rx.RxSubscriber;
import com.kuaikuaiyu.assistant.utils.DigestUtil;

import javax.inject.Inject;

/**
 * Author:  Gavin
 * Email:   gavinking@163.com
 * Date:    2016/5/6
 * Desc:
 */
public class ChangePwdPresenter implements BasePresenter {

    private ChangePwdView view;
    private PassService service;

    private RxSubscriber changePwdSubscriber;

    @Inject
    public ChangePwdPresenter(ChangePwdView view, PassService service) {
        this.view = view;
        this.service = service;
    }

    @Override
    public void clean() {
        if (null != changePwdSubscriber) changePwdSubscriber.unsubscribe();
    }

    /**
     * 修改密码
     *
     * @param oldPwd
     * @param newPwd
     */
    public void changePwd(String oldPwd, String newPwd) {
        changePwdSubscriber = new RxSubscriber(view) {
            @Override
            public void onNext(Object o) {
                view.jump();
            }
        };

        ReqParams params = new ReqParams(ReqParams.POST, AppConfig.URL_CHANGE_PWD);
//        params.addParam("at", ConfigUtil.getShopMobile());
        params.addParam("oldPassword", DigestUtil.getMd5(oldPwd));
        params.addParam("newPassword", DigestUtil.getMd5(newPwd));
        service.changePwd(params.getQueryMap(), params.getFieldMap())
                .compose(new IoTransformer())
                .subscribe(changePwdSubscriber);
    }
}
