package com.kuaikuaiyu.assistant.modle.domain;

/**
 * Author:  Gavin
 * Email:   gavinking@163.com
 * Date:    2016/5/10
 * Desc:    账户相关信息封装
 */
public class Account {
    public String _id;
    public String name;
    public String realname;
    public long balance;
    public Bank bank = new Bank();
    public String alipay;
    public String shop_id;
    public String status;
    public String alipay_name;
    public int min_withdraw_money;
}
