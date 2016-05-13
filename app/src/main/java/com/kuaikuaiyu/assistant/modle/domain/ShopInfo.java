package com.kuaikuaiyu.assistant.modle.domain;

import java.io.Serializable;

/**
 * Author:  Gavin
 * Email:   gavinking@163.com
 * Date:    2016/5/13
 * Desc: 店铺信息
 */
public class ShopInfo implements Serializable {

    /**
     * balance : 0
     * pay_url : http://service.kuaikuaiyu.com/Web/OnShopPay?shop_id=41
     * status : normal
     * total_withdraw_amount : 0
     * alipay : {"id":12,"account_name":"haha","real_name":"hello"}
     * bank : {"id":213,"account_name":"12323123","real_name":"fucker","bank_name":"深发"}
     */

    private static final long serialVersionUID = 1L;
    private int balance;
    private String pay_url;
    private String status;
    private int total_withdraw_amount;
    /**
     * id : 12
     * account_name : haha
     * real_name : hello
     */

    private AlipayBean alipay;
    /**
     * id : 213
     * account_name : 12323123
     * real_name : fucker
     * bank_name : 深发
     */

    private BankBean bank;

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public String getPay_url() {
        return pay_url;
    }

    public void setPay_url(String pay_url) {
        this.pay_url = pay_url;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTotal_withdraw_amount() {
        return total_withdraw_amount;
    }

    public void setTotal_withdraw_amount(int total_withdraw_amount) {
        this.total_withdraw_amount = total_withdraw_amount;
    }

    public AlipayBean getAlipay() {
        return alipay;
    }

    public void setAlipay(AlipayBean alipay) {
        this.alipay = alipay;
    }

    public BankBean getBank() {
        return bank;
    }

    public void setBank(BankBean bank) {
        this.bank = bank;
    }

    public static class AlipayBean {
        private int id;
        private String account_name;
        private String real_name;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getAccount_name() {
            return account_name;
        }

        public void setAccount_name(String account_name) {
            this.account_name = account_name;
        }

        public String getReal_name() {
            return real_name;
        }

        public void setReal_name(String real_name) {
            this.real_name = real_name;
        }
    }

    public static class BankBean {
        private int id;
        private String account_name;
        private String real_name;
        private String bank_name;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getAccount_name() {
            return account_name;
        }

        public void setAccount_name(String account_name) {
            this.account_name = account_name;
        }

        public String getReal_name() {
            return real_name;
        }

        public void setReal_name(String real_name) {
            this.real_name = real_name;
        }

        public String getBank_name() {
            return bank_name;
        }

        public void setBank_name(String bank_name) {
            this.bank_name = bank_name;
        }
    }
}
