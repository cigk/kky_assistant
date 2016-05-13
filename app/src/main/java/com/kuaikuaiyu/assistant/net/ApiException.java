package com.kuaikuaiyu.assistant.net;

/**
 * {
 * "code": 0,
 * "data": {},
 * "message": ""
 * }
 * <p>
 * Http Status Code 为 200 并且返回内容的code不为0时 抛出APIException
 */
public class ApiException extends RuntimeException {

    /**
     * ********************** Error Code *****************************
     */
    public static final int ERROR_CODE_INTERNAL_SERVER_ERROR = -2;//internal server error
    public static final int ERROR_CODE_DATABASE_ERROR = -1;//database error
    public static final int ERROR_CODE_INVALID_ARGUEMENT = 100;//invalid arguments
    public static final int ERROR_CODE_INVALID_SIGNATURE = 110;//invalid signature
    public static final int ERROR_CODE_USER_ALREADY_EXISTS = 120;//user already exists
    public static final int ERROR_CODE_INVALID_DEVICE_UUID = 130;//invalid device uuid
    public static final int ERROR_CODE_INVALID_SIGNUP_CODE = 140;//invalid signup code
    public static final int ERROR_CODE_INVALID_RESET_CODE = 141;//invalid reset code
    public static final int ERROR_CODE_USER_NOT_EXISTS = 150;//user does not exists
    public static final int ERROR_CODE_INVALID_PASSWORD = 160;//invalid password
    public static final int ERROR_CODE_OLD_PASSWORD_EQUAL_NEW_PASSWORD = 161;//new password could not equal the old one
    public static final int ERROR_CODE_INVALID_AUTHENTICATION_TOKEN = 170;//invalid authentication

    private int code;

    public ApiException(int code, String message) {
        this(getApiExceptionMessage(code, message));
        this.code = code;
    }

    public ApiException(String detailMessage) {
        super(detailMessage);
    }

    /**
     * 获取异常代码
     *
     * @return
     */
    public int code() {
        return this.code;
    }

    /**
     * 由于服务器传递过来的错误信息直接给用户看的话，用户未必能够理解
     * 需要根据错误码对错误信息进行一个转换，在显示给用户
     *
     * @param code
     * @return
     */
    private static String getApiExceptionMessage(int code, String message) {
//        if (null == message) {
        switch (code) {
            case ERROR_CODE_INTERNAL_SERVER_ERROR:
                message = "服务器内部错误";
                break;

            case ERROR_CODE_DATABASE_ERROR:
                message = "数据库错误";
                break;

            case ERROR_CODE_INVALID_ARGUEMENT:
                message = "参数错误";
                break;

            case ERROR_CODE_INVALID_SIGNATURE:
                message = "无效签名";
                break;

            case ERROR_CODE_USER_ALREADY_EXISTS:
                message = "用户已存在";
                break;

            case ERROR_CODE_INVALID_DEVICE_UUID:
                message = "UUID无效";
                break;

            case ERROR_CODE_INVALID_SIGNUP_CODE:
                message = "验证码不正确";
                break;

            case ERROR_CODE_INVALID_RESET_CODE:
                message = "验证码不正确";
                break;

            case ERROR_CODE_USER_NOT_EXISTS:
                message = "用户不存在";
                break;

            case ERROR_CODE_INVALID_PASSWORD:
                message = "密码错误";
                break;

            case ERROR_CODE_OLD_PASSWORD_EQUAL_NEW_PASSWORD:
                message = "新密码不能与旧密码相同";
                break;

            case ERROR_CODE_INVALID_AUTHENTICATION_TOKEN:
                message = "授权失败";
                break;

            default:
                if (null == message) {
                    StringBuilder builder = new StringBuilder().append("出错了，请稍后重试")
                            .append(" (").append(code).append(")");
                    message = builder.toString();
                }
                break;
        }
        return message;
    }
}