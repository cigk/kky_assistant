package com.kuaikuaiyu.assistant.utils;

/**
 * Created by Gavin on 2015/6/23.
 * 一些未分类的工具
 * 1. 判断是否为快速多次点击
 *
 */
public class CommonUtil {
    private static long lastClickTime;

    /**
     * 判断是否为快速重复点击
     * 阀值可以根据需求自己定义
     *
     * @return
     */
    public static boolean isFastDoubleClick() {
        long time = System.currentTimeMillis();
        long timeD = time - lastClickTime;
        if (0 < timeD && timeD < 500) {
            return true;
        }
        lastClickTime = time;
        return false;
    }

}
