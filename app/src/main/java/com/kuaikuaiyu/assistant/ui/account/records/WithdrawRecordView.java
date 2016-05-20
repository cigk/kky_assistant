package com.kuaikuaiyu.assistant.ui.account.records;

import com.kuaikuaiyu.assistant.base.BaseView;
import com.kuaikuaiyu.assistant.model.domain.BillRecord;

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
    void loadSucceed(BillRecord bill);

    /**
     * 加载数据为空
     */
    void loadEmpty();

    /**
     * 加载数据失败
     */
    void loadError();

    /**
     * 刷新完成(有可能是失败 有可能是成功)
     */
    void refreshComplete();

}
