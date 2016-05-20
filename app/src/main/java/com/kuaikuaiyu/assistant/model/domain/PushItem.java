package com.kuaikuaiyu.assistant.model.domain;

/**
 * 推送的item
 * <p/>
 * {
 * "type": "new_order",
 * "data": "order_id"
 * }
 * <p/>
 * Created by Gavin on 2015/8/7.
 */
public class PushItem {
    String type;
    String data;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}

