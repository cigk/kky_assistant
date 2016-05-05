package com.kuaikuaiyu.assistant.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Pattern;

/**
 * Author:  Gavin
 * Email:   gavinking@163.com
 * Date:    2015/11/18
 * Desc:    判断字符等格式的工具类，包括是否是合理的手机号，是否全部都为中文等
 */
public class FormatUtil {

    /**
     * 判定输入汉字
     *
     * @param c
     * @return
     */

    public static boolean isChinese(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        return ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS ||
                ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS ||
                ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A ||
                ub == Character.UnicodeBlock.GENERAL_PUNCTUATION ||
                ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION ||
                ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS;
    }


    /**
     * 检测String是否全是中文
     *
     * @param name
     * @return
     */

    public static boolean checkNameChinese(String name) {
        boolean res = true;
        char[] cTemp = name.toCharArray();
        for (int i = 0; i < name.length(); i++) {
            if (!isChinese(cTemp[i])) {
                res = false;
                break;
            }
        }
        return res;
    }

    /**
     * 检测String是否包含中文
     *
     * @param name
     * @return
     */

    public static boolean checkContainChinese(String name) {
        boolean res = false;
        char[] cTemp = name.toCharArray();
        for (int i = 0; i < name.length(); i++) {
            if (isChinese(cTemp[i])) {
                res = true;
                break;
            }
        }
        return res;
    }

    /**
     * 判断是否为手机号码
     *
     * @param phoneNum
     * @return
     */
    public static boolean isMobile(String phoneNum) {
        return Pattern.matches("^((1[358][0-9])|(14[57])|(17[0678]))\\d{8}$", phoneNum);
    }

    /**
     * 判断是否为邮箱
     *
     * @param email
     * @return
     */
    public static boolean isEmail(String email) {
        return Pattern.matches("^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$",
                email);
    }

    /**
     * 日期转换成字符串
     *
     * @param date
     * @return str
     */
    public static String dateToStr(Date date) {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        String str = format.format(date);
        return str;
    }


    /**
     * 日期转换成字符串
     *
     * @param date
     * @return str
     */
    public static String dateToStr(Long date) {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        String str = format.format(date);
        return str;
    }

    /**
     * 日期转换成字符串(只保留日期)
     *
     * @param date
     * @return str
     */
    public static String dateToDateStr(Date date) {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        String str = format.format(date);
        return str;
    }

    /**
     * 日期转换成字符串(只保留日期)
     *
     * @param date
     * @return str
     */
    public static String dateToDateStr(long date) {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        String str = format.format(date);
        return str;
    }

    /**
     * 获取日
     *
     * @param date
     * @return such as 21 when date is 16/01/21
     */
    public static String getDayOfDate(Long date) {
        SimpleDateFormat format = new SimpleDateFormat("dd", Locale.getDefault());
        return format.format(date);
    }

    /**
     * 获取短日期
     *
     * @param date
     * @return such as 11-20
     */
    public static String getShortDate(Long date) {
        SimpleDateFormat format = new SimpleDateFormat("MM-dd", Locale.getDefault());
        return format.format(date);
    }

    /**
     * 获取小时和分钟
     *
     * @param date
     * @return such as 15:51
     */
    public static String getShortTimeOfDate(Long date) {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm", Locale.getDefault());
        return format.format(date);
    }

    /**
     * 字符串转换成日期
     *
     * @param str
     * @return date
     */
    public static Date strToDate(String str) {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = null;
        try {
            date = format.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 获取周几
     *
     * @param date
     * @return
     */

    //根据日期取得星期几
    public static String getWeekday(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE", Locale.getDefault());
        String weekday = sdf.format(date);
        return weekday;
    }

    /**
     * 获取周几
     *
     * @param date
     * @return
     */

    //根据日期取得星期几
    public static String getWeekday(Long date) {
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE", Locale.getDefault());
        String weekday = sdf.format(date);
        return weekday;
    }

    public static boolean isToday(Long date) {
        return getDayOfDate(date).equals(getDayOfDate(new Date().getTime()));
    }
}
