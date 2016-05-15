package com.kuaikuaiyu.assistant.ui.account.records;

import com.kuaikuaiyu.assistant.app.AppConfig;
import com.kuaikuaiyu.assistant.base.BasePresenter;
import com.kuaikuaiyu.assistant.modle.domain.BillRecord;
import com.kuaikuaiyu.assistant.modle.service.AccountService;
import com.kuaikuaiyu.assistant.net.ReqParams;
import com.kuaikuaiyu.assistant.rx.IoTransformer;
import com.kuaikuaiyu.assistant.rx.RxSubscriber;

import javax.inject.Inject;

/**
 * Author:  Gavin
 * Email:   gavinking@163.com
 * Date:    2016/5/10
 * Desc:    提现记录Presenter
 */
public class WithdrawRecordPresenter implements BasePresenter {

    private WithdrawRecordView mView;
    private AccountService mService;
    private RxSubscriber<BillRecord> mSubscriber;

    @Inject
    public WithdrawRecordPresenter(WithdrawRecordView withdrawRecordView, AccountService service) {
        this.mView = withdrawRecordView;
        this.mService = service;
    }

    /**
     * 获取提现记录
     */
    public void getBillRecords() {
        mSubscriber = new RxSubscriber<BillRecord>(null) {
            @Override
            public void onNext(BillRecord billRecord) {
                if (billRecord != null && billRecord.bill_list != null) {
                    if (billRecord.bill_list.size() == 0) {
                        mView.loadEmpty();
                    } else {
                        mView.loadSucceed(billRecord);
                    }
                } else {
                    mView.loadError();
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                mView.loadError();
            }

            @Override
            public void onCompleted() {
                super.onCompleted();
                mView.refreshComplete();
            }
        };

        ReqParams params = new ReqParams(ReqParams.GET, AppConfig.URL_INCOME_ACCOUNT);
        params.addQuery("offset", 0);
        // TODO: 2016/5/13 limit 加载更多
        params.addQuery("limit", 10000);
        mService.getBillRecords(params.getQueryMap()).compose(new IoTransformer<>()).subscribe
                (mSubscriber);
    }

    @Override
    public void clean() {
        if (mSubscriber != null) {
            mSubscriber.cancel();
        }
    }
}
