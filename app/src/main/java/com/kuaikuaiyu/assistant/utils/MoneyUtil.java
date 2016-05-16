package com.kuaikuaiyu.assistant.utils;

import java.math.BigDecimal;
import java.util.Locale;

public class MoneyUtil {

    /**
     * 分转换为元, 并且保留两位小数点
     * 用于显示余额之类
     *
     * @param money
     * @return
     */
    public static String format(int money) {
        if (money >= 0) {
            return String.format("%d.%02d", (money / 100), (money % 100), Locale.getDefault());
        } else {
            return "-" + String.format("%d.%02d", (money * (-1) / 100), (money * (-1) % 100), Locale.getDefault());
        }

    }

    public static String format(long money) {
        if (money >= 0) {
            return String.format("%d.%02d", (money / 100), (money % 100), Locale.getDefault());
        } else {
            return "-" + String.format("%d.%02d", (money * (-1) / 100), (money * (-1) % 100), Locale.getDefault());
        }
    }

    /**
     * 分转换为元
     *
     * @param money
     * @return
     */
    public static String cent2Buck(int money) {
        return String.valueOf(money / 100.0f);
    }

    /**
     * 元转换成分
     *
     * @param money
     * @return
     */
    public static int buck2Cent(String money) {
        return new BigDecimal(money).multiply(new BigDecimal(100)).intValue();
    }
}
