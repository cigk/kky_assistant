package com.kuaikuaiyu.assistant.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Author:  Gavin
 * Email:   gavinking@163.com
 * Date:    2016/5/16
 * Desc:    日期/时间相关工具类
 */
public class DateUtil {
    /**
     * 日期转换成字符串
     *
     * @param date
     * @return str
     */
    public static String date2Str(Date date) {

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
    public static String date2Str(Long date) {

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
    public static String date2DateStr(Date date) {

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
    public static String date2DateStr(long date) {

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
    public static Date str2Date(String str) {

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


    /**
     * UTC事件转化为本地事件
     *
     * @param utcTime
     * @param localTimePatten
     * @return
     */
    public static String utc2Local(String utcTime, String utcTimePatten,
                                   String localTimePatten) {
        SimpleDateFormat utcFormater = new SimpleDateFormat(utcTimePatten);
        utcFormater.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date gpsUTCDate = null;
        try {
            gpsUTCDate = utcFormater.parse(utcTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat localFormater = new SimpleDateFormat(localTimePatten);
        localFormater.setTimeZone(TimeZone.getDefault());
        String localTime = localFormater.format(gpsUTCDate.getTime());
        return localTime;
    }
}
