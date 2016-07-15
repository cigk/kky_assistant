package com.kuaikuaiyu.assistant.model.domain;

/**
 * Author:  Gavin
 * Email:   gavinking@163.com
 * Date:    2016/7/14
 * Desc:
 */
public class HttpResult<EObj, EMap, EList> {
    public boolean success;
    public int code;
    public String msg;
    public EObj obj;
    public EMap map;
    public EList list;
}