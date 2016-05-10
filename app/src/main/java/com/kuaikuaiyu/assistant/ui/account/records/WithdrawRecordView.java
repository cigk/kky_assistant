package com.kuaikuaiyu.assistant.ui.account.records;

import com.kuaikuaiyu.assistant.base.BaseView;

/**
 * Author:  Gavin
 * Email:   gavinking@163.com
 * Date:    2016/5/10
 * Desc:    提现记录
 */
public interface WithdrawRecordView extends BaseView {

    /**
     * 数据加载成功
     */
    void loadSucceed();

    /**
     * 加载数据为空
     */
    void loadEmpty();

    /**
     * 加载数据失败
     */
    void loadError();

}
