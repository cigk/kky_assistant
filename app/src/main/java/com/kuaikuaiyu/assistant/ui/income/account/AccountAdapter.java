package com.kuaikuaiyu.assistant.ui.income.account;

import android.content.Context;

import com.kuaikuaiyu.assistant.R;
import com.kuaikuaiyu.assistant.modle.domain.IncomeAccount;
import com.zhy.base.adapter.ViewHolder;
import com.zhy.base.adapter.recyclerview.CommonAdapter;

import java.util.List;

/**
 * Created by binlly
 * <p>
 * date: 2016/5/10 10:38
 * desc:
 */
public class AccountAdapter extends CommonAdapter<String> {
    private AccountPresenter mPresenter;

    public AccountAdapter(Context context, int layoutId, List<String> datas,
            AccountPresenter presenter) {
        super(context, layoutId, datas);
        mPresenter = presenter;
    }

    @Override
    public void convert(ViewHolder holder, String datas) {
        holder.setText(R.id.tv_pay_account, datas);
        holder.setText(R.id.tv_pay_type, "11234");
        holder.setText(R.id.tv_pay_date, "12:34");
        holder.setText(R.id.tv_income_money, "123.99");

        //加载到底部继续加载数据
//        if (getPosition(holder) == getItemCount() - 1) {
//            // TODO: 2016/5/10 判断能否加载更多
//            mPresenter.getIncomeAccount();
//        }
    }
}
