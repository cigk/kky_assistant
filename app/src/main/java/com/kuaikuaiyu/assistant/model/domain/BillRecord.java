package com.kuaikuaiyu.assistant.model.domain;

import java.util.List;

/**
 * Created by binlly
 * <p>
 * date: 2016/5/13 18:44
 * desc: 账单记录
 */
public class BillRecord {

    public List<Bill> bill_list;

    public static class Bill {
        public String account_name;
        public String account_type;
        public String account_type_to_text;
        public String bill_type;
        public String bill_type_to_text;
        public int amount;
        public String created_time;
    }

    /*
    {
        "bill_list": [
            {
                "account_name": "123",
                "account_type": "alipay",
                "account_type_to_text": "支付宝",
                "bill_type": "提现",
                "amount": -20,
                "created_time": "2016-05-12T12: 45: 28+08: 00"
            },
            {
                "account_name": "123",
                "account_type": "bank",
                "account_type_to_text": "银行",
                "bill_type": "提现",
                "amount": -20,
                "created_time": "2016-05-12T12: 45: 27+08: 00"
            }
        ]
    }
     */
}
