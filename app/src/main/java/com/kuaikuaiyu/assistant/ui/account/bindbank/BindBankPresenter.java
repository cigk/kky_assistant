package com.kuaikuaiyu.assistant.ui.account.bindbank;

import com.kuaikuaiyu.assistant.app.AppConfig;
import com.kuaikuaiyu.assistant.base.BasePresenter;
import com.kuaikuaiyu.assistant.modle.service.AccountService;
import com.kuaikuaiyu.assistant.net.HttpResult;
import com.kuaikuaiyu.assistant.net.ReqParams;
import com.kuaikuaiyu.assistant.rx.RxSubscriber;
import com.kuaikuaiyu.assistant.utils.ConfigUtil;
import com.kuaikuaiyu.assistant.utils.UIUtil;

import javax.inject.Inject;

/**
 * Author:  Gavin
 * Email:   gavinking@163.com
 * Date:    2016/5/10
 * Desc:    绑定银行卡
 */
public class BindBankPresenter implements BasePresenter {

    private BindBankView bindBankView;
    private AccountService service;
    private RxSubscriber<HttpResult> subscriber;

    @Inject
    public BindBankPresenter(BindBankView bindBankView, AccountService service) {
        this.bindBankView = bindBankView;
        this.service = service;
    }

    /**
     * 绑定银行卡
     *
     * @param bankName
     * @param cardNum
     * @param name
     * @param province
     * @param city
     */
    public void bindBank(String bankName, String cardNum, String name, String province,
                         String city) {
        subscriber = new RxSubscriber<HttpResult>(bindBankView) {
            @Override
            public void onNext(HttpResult httpResult) {
                UIUtil.showToast("绑定审核已经提交，请耐心等待…");
            }
        };

        ReqParams params = new ReqParams(ReqParams.PUT, AppConfig.URL_BIND_BANK);
        params.addParam("last_account_id", ConfigUtil.getShopInfo().getBank().getId());
        params.addParam("bank_name", bankName);
        params.addParam("bank_no", cardNum);
        params.addParam("real_name", name);
    }

    @Override
    public void clean() {

    }
}
