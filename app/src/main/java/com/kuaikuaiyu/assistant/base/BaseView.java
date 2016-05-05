package com.kuaikuaiyu.assistant.base;

/**
 * Author:  Gavin
 * Email:   gavinking@163.com
 * Date:    2016/4/30
 * Desc:
 */
public interface BaseView {
    /**
     * show loading message
     *
     * @param msg
     */
    void showLoading(String msg);

    /**
     * hide loading
     */
    void hideLoading();

    /**
     * show error message
     */
    void showError(String msg);


    /**
     * show net error
     */
    void showNetError();

}
