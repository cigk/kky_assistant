package com.kuaikuaiyu.assistant.modle.domain;

/**
 * Created by kky on 15-7-29.
 */
public class VerifyData {
    public String status;
    public Data verify_data;
    public class Data {
        public Bank bank;
        public String alipay;
        public String delivery_method;
        public String alipay_name;
    }
}


/**
 *
 *   type: bank
 "verify_data": {
 "bank": {
 "name": "",
 "card_no": "*************6666",
 "owner": "",
 "province": "",
 "city": ""
 }
 },
 "status":



 type: alipay
 {
 "verify_data": {
 "alipay": "nihao"
 },
 "status": "verifying"



 type: delivery_method
 "verify_data": {
 "delivery_method": "self"
 },
 "status": "verifying"



 */