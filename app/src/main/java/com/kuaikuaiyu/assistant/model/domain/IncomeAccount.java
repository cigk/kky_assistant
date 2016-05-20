package com.kuaikuaiyu.assistant.model.domain;

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

    /*
    {
      "code": 0,
      "data": {
        "order_list": [
          {
            "account_type": "alipay",
            "account_type_to_text": "支付宝",
            "amount": 1,
            "created_time": "2016-05-10T17:34:37+08:00",
            "id": 2,
            "pay_account": "paincompiler@kuaikuaiyu.com",
            "status": "paid"
          },
          {
            "account_type": "alipay",
            "account_type_to_text": "支付宝",
            "amount": 900,
            "created_time": "2016-05-10T18:00:33+08:00",
            "id": 12,
            "pay_account": "paincompiler@kuaikuaiyu.com",
            "status": "paid"
          }
        ]
      }
    }
    */
}
