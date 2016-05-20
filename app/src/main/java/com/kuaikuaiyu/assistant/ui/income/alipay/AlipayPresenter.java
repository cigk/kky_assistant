package com.kuaikuaiyu.assistant.ui.income.alipay;

import com.kuaikuaiyu.assistant.base.BasePresenter;

/**
 * Author:  Gavin
 * Email:   gavinking@163.com
 * Date:    2016/5/18
 * Desc:
 */
public interface AlipayPresenter extends BasePresenter {

    /**
     * 获取生成支付码的url
     *
     * @param money
     */
    void loadAlipayUrl(int money);
}
