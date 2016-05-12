package com.kuaikuaiyu.assistant.ui.account.withdraw;

import android.content.Context;

import com.kuaikuaiyu.assistant.R;
import com.kuaikuaiyu.assistant.modle.domain.WithdrawItem;
import com.zhy.base.adapter.ViewHolder;
import com.zhy.base.adapter.recyclerview.CommonAdapter;

import java.util.List;

/**
 * Created by binlly
 * <p>
 * date: 2016/5/10 10:38
 * desc:
 */
public class WithdrawAdapter extends CommonAdapter<WithdrawItem> {

    public WithdrawAdapter(Context context, int layoutId, List<WithdrawItem> datas) {
        super(context, layoutId, datas);
    }

    @Override
    public void convert(ViewHolder holder, WithdrawItem data) {
        holder.setText(R.id.tv_pay_account, data.status_text);
        holder.setText(R.id.tv_pay_type, "11234");
        holder.setText(R.id.tv_pay_date, "12:34");
        holder.setText(R.id.tv_income_money, "123.99");

    }
}
