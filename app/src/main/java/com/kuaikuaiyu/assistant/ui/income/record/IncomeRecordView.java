package com.kuaikuaiyu.assistant.ui.income.record;

import com.kuaikuaiyu.assistant.base.BaseView;
import com.kuaikuaiyu.assistant.modle.domain.IncomeAccount;

/**
 * Created by binlly
 * <p>
 * date: 2016/5/9 18:05
 * desc:
 */
public interface IncomeRecordView extends BaseView {
    /**
     * 数据加载成功
     */
    void loadSucceed(IncomeAccount incomeAccount);

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
