package com.kuaikuaiyu.assistant.ui.account.records;

import android.content.Context;

import com.kuaikuaiyu.assistant.modle.domain.WithdrawItem;
import com.kuaikuaiyu.assistant.ui.income.account.AccountPresenter;
import com.zhy.base.adapter.ViewHolder;
import com.zhy.base.adapter.recyclerview.CommonAdapter;

import java.util.List;

/**
 * Created by binlly
 * <p>
 * date: 2016/5/10 10:38
 * desc: 提现记录的Adapter
 */
public class WithdrawRecordAdapter extends CommonAdapter<WithdrawItem> {

    public WithdrawRecordAdapter(Context context, int layoutId, List<WithdrawItem> datas) {
        super(context, layoutId, datas);
    }

    @Override
    public void convert(ViewHolder holder, WithdrawItem item) {

    }
}
