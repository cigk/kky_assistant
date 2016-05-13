package com.kuaikuaiyu.assistant.ui.account.records;

import android.content.Context;

import com.kuaikuaiyu.assistant.R;
import com.kuaikuaiyu.assistant.modle.domain.BillRecord;
import com.kuaikuaiyu.assistant.utils.MoneyFormatUtil;
import com.zhy.base.adapter.ViewHolder;
import com.zhy.base.adapter.recyclerview.CommonAdapter;

import java.util.List;

/**
 * Created by binlly
 * <p>
 * date: 2016/5/10 10:38
 * desc:
 */
public class WithdrawRecordAdapter extends CommonAdapter<BillRecord.Bill> {

    public WithdrawRecordAdapter(Context context, int layoutId, List<BillRecord.Bill> datas) {
        super(context, layoutId, datas);
    }

    @Override
    public void convert(ViewHolder holder, BillRecord.Bill data) {
        holder.setText(R.id.tv_pay_account, data.account_name);
        holder.setText(R.id.tv_pay_type, data.account_type_to_text);
        holder.setText(R.id.tv_pay_date, data.created_time);
        holder.setText(R.id.tv_withdraw_cash, "已提现 " + MoneyFormatUtil.format(data.amount));
    }
}
