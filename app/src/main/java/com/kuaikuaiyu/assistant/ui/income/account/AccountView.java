package com.kuaikuaiyu.assistant.ui.income.account;

import com.kuaikuaiyu.assistant.base.BaseView;
import com.kuaikuaiyu.assistant.modle.domain.IncomeAccount;
import com.kuaikuaiyu.assistant.ui.widgets.LoadingPage;

/**
 * Created by binlly
 * <p>
 * date: 2016/5/9 18:05
 * desc:
 */
public interface AccountView extends BaseView {
    void fillData(IncomeAccount incomeAccount);

    void refreshComplete();

    LoadingPage getLoadingPage();
}
