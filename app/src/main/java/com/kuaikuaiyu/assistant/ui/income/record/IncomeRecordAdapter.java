package com.kuaikuaiyu.assistant.ui.income.record;

import android.content.Context;

import com.kuaikuaiyu.assistant.R;
import com.kuaikuaiyu.assistant.model.domain.IncomeAccount;
import com.kuaikuaiyu.assistant.utils.DateUtil;
import com.kuaikuaiyu.assistant.utils.MoneyUtil;
import com.zhy.base.adapter.ViewHolder;
import com.zhy.base.adapter.recyclerview.CommonAdapter;

import java.util.List;

/**
 * Created by binlly
 * <p>
 * date: 2016/5/10 10:38
 * desc:
 */
public class IncomeRecordAdapter extends CommonAdapter<IncomeAccount.Order> {

    public IncomeRecordAdapter(Context context, int layoutId, List<IncomeAccount.Order> list) {
        super(context, layoutId, list);
    }

    @Override
    public void convert(ViewHolder holder, IncomeAccount.Order data) {
        holder.setText(R.id.tv_pay_account, data.pay_account);
        holder.setText(R.id.tv_pay_type, data.account_type_to_text);
        String payTime = DateUtil.utc2Local(data.created_time, "yyyy-MM-dd'T'HH:mm:ssZ",
                "yyyy-MM-dd HH:mm:ss");
        holder.setText(R.id.tv_pay_date, payTime);
        holder.setText(R.id.tv_income_money, MoneyUtil.format(data.amount));
    }
}
