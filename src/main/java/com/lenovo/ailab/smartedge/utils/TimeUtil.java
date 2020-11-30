package com.lenovo.ailab.smartedge.utils;

import org.springframework.util.StringUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author duanxr2
 * @Email duanxr2@lenovo.com
 * @Date：2020/3/5
 * @About: 时间转公共方法
 */
public class TimeUtil {
    private static ThreadLocal<DateFormat> threadLocal = new ThreadLocal<DateFormat>() {
        @Override
        protected DateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
    };

    /**
     * 字符串转date
     */
    public static Date dateFormat(String time) {
        if (!StringUtils.hasText(time)) {
            return new Date();
        }
        try {
            return threadLocal.get().parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new Date();
    }

    /**
     * 获取两个时间间隔秒
     *
     * @param startTime
     * @param endTime
     * @return
     */
    public static int timeInterval(Date startTime, Date endTime) {
        return (int) (Math.abs(endTime.getTime() - startTime.getTime()) / 1000);
    }

    /**
     * 时间戳转换成日期格式字符串
     * @return
     */
    public static String getDateToString(long longValue) {
        Date d = new Date(longValue);
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sf.format(d);
    }

}
