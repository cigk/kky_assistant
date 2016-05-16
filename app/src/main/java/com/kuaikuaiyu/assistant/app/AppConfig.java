package com.kuaikuaiyu.assistant.app;

/**
 * Author:  Gavin
 * Email:   gavinking@163.com
 * Date:    2016/4/29
 * Desc:
 */
public class AppConfig {

    public static final String PROTOCAL_HTTP = "http://";
    public static final String PROTOCAL_HTTPS = "https://";

    public static final String URL_ASSISTANT = "http://kuaikuaiyu.com/";
    public static final String URL_SCHOOL = "http://kuaikuaiyu.com/";

    /*---------- Pass Url Begin-------- */
    public static final String URL_INFO = "/v1/info";
    public static final String URL_SIGN_UP_SMS = "/v1/signup_sms";
    public static final String URL_SIGN_UP = "/v1/signup";
    public static final String URL_LOGIN = "/v1/login";
    public static final String URL_CHANGE_PWD = "/v1/change_password";
    public static final String URL_RESET_PWD_SMS = "/v1/reset_password_sms";
    public static final String URL_RESET_PWD = "/v1/reset_password";
    public static final String URL_UPDATE_PUSH_ID = "/v1/lazy_update";
    /*---------- Pass Url End---------- */


    /*---------- Balance Url Begin-------- */
    public static final String URL_BIND_ALIPAY = "/REST/WithdrawBindAlipay";
    public static final String URL_BIND_BANK = "/REST/WithdrawBindBank";
    public static final String URL_SHOP_INFO = "/REST/ShopInfo";
    public static final String URL_WITHDRAW = "/REST/DoWithdraw";
    public static final String URL_BILL_RECORD = "/REST/Bill";
    /*---------- Balance Url End---------- */


    /*---------- Income Url Begin-------- */
    public static final String URL_INCOME_ACCOUNT = "/REST/QRCodeOrder";
    /*---------- Income Url End---------- */


    /**
     * ********************** RequestCode *****************************
     */
    public static final int REQUEST_CODE_BASE = 10000;
    public static final int REQUEST_CODE_CHANGE_PASSWORD = REQUEST_CODE_BASE + 1;
    public static final int REQUEST_CODE_CHANGE_BANK_INFO = REQUEST_CODE_BASE + 2;
    public static final int REQUEST_CODE_CHANGE_ALIPAY_INFO = REQUEST_CODE_BASE + 3;
    public static final int REQUEST_CODE_TAKE_PHOTO = REQUEST_CODE_BASE + 4;
    public static final int REQUEST_CODE_PICK_PIC = REQUEST_CODE_BASE + 5;
    public static final int REQUEST_CODE_CHOOSE_AREA = REQUEST_CODE_BASE + 6;
    public static final int REQUEST_CODE_DELIVERY_AREA = REQUEST_CODE_BASE + 7;
    public static final int REQUEST_CODE_CHOOSE_CITY = REQUEST_CODE_BASE + 8;
    public static final int REQUEST_CODE_ENABLE_BT = REQUEST_CODE_BASE + 9;
    public static final int REQUEST_CODE_OPEN_TIME = REQUEST_CODE_BASE + 10;
    public static final int REQUEST_CODE_SCAN_QRCODE = REQUEST_CODE_BASE + 11;
    public static final int REQUEST_CODE_CAMERA = REQUEST_CODE_BASE + 12;
    public static final int REQUEST_CODE_SHOP_INFO = REQUEST_CODE_BASE + 13;
    public static final int REQUEST_CODE_LUCKY_MONEY = REQUEST_CODE_BASE + 15;
    public static final int REQUEST_CODE_USER_NOTICE = REQUEST_CODE_BASE + 16;
    public static final int REQUEST_CODE_SETTING_CAMERA = REQUEST_CODE_BASE + 17;
    public static final int REQUEST_CODE_SETTING_STORAGE = REQUEST_CODE_BASE + 18;

    //Permission request code
    public static final int PERMISSIONS_REQUEST_CAMERA = 201;
    public static final int PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 202;
    public static final int PERMISSIONS_REQUEST_PRINTER = 203;
    public static final int PERMISSIONS_REQUEST_PHONE_STATE = 205;
    public static final int PERMISSIONS_REQUEST_LOCATION = 206;

    /**
     * ********************** Verify Status ***************************
     */
    public static final String VERIFYSTATUS_VERIFIED = "verified";
    public static final String VERIFYSTATUS_VERIFING = "verifying";
    public static final String VERIFYSTATUS_FAILED = "verify_failed";
}
