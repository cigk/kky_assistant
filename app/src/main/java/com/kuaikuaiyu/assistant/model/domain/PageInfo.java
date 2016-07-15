package com.kuaikuaiyu.assistant.model.domain;

/**
 * Author:  Gavin
 * Email:   gavinking@163.com
 * Date:    2016/7/14
 * Desc:
 */
public class PageInfo {
    public int count;
    public int pageNo;
    public int pageSize;

    @Override
    public String toString() {
        return "PageInfo{" +
                "count=" + count +
                ", pageNo=" + pageNo +
                ", pageSize=" + pageSize +
                '}';
    }
}
