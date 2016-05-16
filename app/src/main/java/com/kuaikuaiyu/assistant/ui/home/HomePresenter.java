package com.kuaikuaiyu.assistant.ui.home;

import com.kuaikuaiyu.assistant.base.BasePresenter;

/**
 * Author:  Gavin
 * Email:   gavinking@163.com
 * Date:    2016/5/15
 * Desc:
 */
public interface HomePresenter extends BasePresenter {

    /**
     * 获取店铺信息
     *
     * @param needHandleView true 回调View的 loadSucceed/loadFail, false不回调，只更新数据并发送事件
     */
    void getShopInfo(boolean needHandleView);

    /**
     * 更新个推id
     */
    void updatePushId();
}
