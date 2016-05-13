package com.kuaikuaiyu.assistant.ui.account.bindbank;

import com.kuaikuaiyu.assistant.base.BaseView;

/**
 * Author:  Gavin
 * Email:   gavinking@163.com
 * Date:    2016/5/10
 * Desc:    绑定银行卡
 */
public interface BindBankView extends BaseView {
    /**
     * 绑定成功
     */
    void bindSucceed();

    /**
     * 绑定失败
     */
    void bindFail();
}
