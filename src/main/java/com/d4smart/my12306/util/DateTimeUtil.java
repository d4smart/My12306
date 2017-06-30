package com.d4smart.my12306.util;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.Date;

/**
 * Created by d4smart on 2017/6/30 18:11
 */
public class DateTimeUtil {

    public static final String STANDARD_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static Date strToDate(String time, String format) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern(format);
        DateTime dateTime = dateTimeFormatter.parseDateTime(time);
        return dateTime.toDate();
    }

    public static Date strToDate(String time) {
        return strToDate(time, STANDARD_FORMAT);
    }

    public static String dateToStr(Date date, String format) {
        if(date == null) {
            return StringUtils.EMPTY;
        }
        DateTime dateTime = new DateTime(date);
        return dateTime.toString(format);
    }

    public static String dateToStr(Date date) {
        return dateToStr(date, STANDARD_FORMAT);
    }
}
