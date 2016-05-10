package com.kuaikuaiyu.assistant.ui.income.account;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kuaikuaiyu.assistant.R;
import com.kuaikuaiyu.assistant.base.BaseAdapter;
import com.kuaikuaiyu.assistant.modle.domain.IncomeAccount;
import com.kuaikuaiyu.assistant.utils.UIUtil;

import java.util.List;

/**
 * Created by binlly
 * <p>
 * date: 2016/5/10 10:38
 * desc:
 */
public class AccountAdapter extends BaseAdapter<IncomeAccount> {
    private AccountPresenter mPresenter;

    public AccountAdapter(List<IncomeAccount> mDatas, AccountPresenter presenter) {
        super(mDatas);
        mPresenter = presenter;
    }

    @Override
    public View getEachView(int position, View convertView, ViewGroup parent) {
        Holder holder;
        if (convertView == null) {
            holder = new Holder();
            convertView = UIUtil.inflate(R.layout.item_income_account);
            holder.tv_pay_account = (TextView) convertView.findViewById(R.id.tv_pay_account);
            holder.tv_pay_type = (TextView) convertView.findViewById(R.id.tv_pay_type);
            holder.tv_pay_date = (TextView) convertView.findViewById(R.id.tv_pay_date);
            holder.tv_income_money = (TextView) convertView.findViewById(R.id.tv_income_money);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }

        getData();

        if (position == getData().size() - 1){
            mPresenter.getIncomeAccount();
        }
    }

    private class Holder {
        TextView tv_pay_account;
        TextView tv_pay_type;
        TextView tv_pay_date;
        TextView tv_income_money;
    }
}
