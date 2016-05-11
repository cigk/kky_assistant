package com.kuaikuaiyu.assistant.utils;

import java.util.Locale;

public class MoneyFormatUtil {

    public static String format(int money) {
        if (money >= 0)
            return String.format("%d.%02d", (money / 100), (money % 100), Locale.getDefault());
        else
            return "-" + String.format("%d.%02d", (money * (-1) / 100), (money * (-1) % 100), Locale.getDefault());
    }

    public static String format(long money) {
        if (money >= 0)
            return String.format("%d.%02d", (money / 100), (money % 100), Locale.getDefault());
        else
            return "-" + String.format("%d.%02d", (money * (-1) / 100), (money * (-1) % 100), Locale.getDefault());
    }

    public static int formatToSend(String money) {
        try {
            return Math.round(Float.parseFloat(money) * 100);
        } catch (Exception e) {
            return 0;
        }
    }
}
