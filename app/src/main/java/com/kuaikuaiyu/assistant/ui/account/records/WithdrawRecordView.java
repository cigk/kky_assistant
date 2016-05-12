package com.kuaikuaiyu.assistant.ui.account.records;

import com.kuaikuaiyu.assistant.base.BaseView;
import com.kuaikuaiyu.assistant.modle.domain.WithdrawItem;

import java.util.List;

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
    void loadSucceed(List<WithdrawItem> data);

    /**
     * 加载数据为空
     */
    void loadEmpty();

    /**
     * 加载数据失败
     */
    void loadError();

}
