package com.kuaikuaiyu.assistant.modle.domain;

import java.util.List;

/**
 * Created by binlly
 * <p>
 * date: 2016/5/10 10:40
 * desc:
 */
public class IncomeAccount {

    public List<Order> order_list;

    public static class Order {
        public int id;
        public long amount;
        public String status;
        public String account_type;
        public String account_type_to_text;
        public String created_time;
        public String pay_account;
    }
}
