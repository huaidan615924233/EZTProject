package com.ezt.eztproject.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import android.text.TextUtils;

/**
 * @describe 时间操作工具类
 * @author Zhaop
 */
public class TimeUtils {

    /**
     * 获得当前日期时间格式
     * @return yyyy-MM-dd HH:mm:ss
     */
    public static String getDateFormat() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        return simpleDateFormat.format(date);
    }

    /**
     * 获得当前日期时间格式
     * @return HH:mm
     */
    public static String getTimeCur() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
        Date date = new Date();
        return simpleDateFormat.format(date);
    }

    /**
     * 获得时间戳日期时间格式
     * @return HH:mm
     */
    public static String getTime(String timeStr) {
        long mill = 0;
        try {
            mill = Long.parseLong(timeStr);
        } catch(Exception e) {
            e.printStackTrace();
            return "-1";
        }
        Date date = new Date(mill * 1000L);
        String strs = "";
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm", Locale.getDefault());
            strs = sdf.format(date);
        } catch(Exception e) {
            e.printStackTrace();
        }
        return strs;
    }

    /**
     * 获得当前日期时间格式
     * @return yyyyMMddHHmmss
     */
    public static String getDateMyFormat() {
        String str1 = getDateFormat();
        String str2 = str1.split(" ")[0];// yyyy-MM-dd
        String str3 = str1.split(" ")[1];// HH:mm:ss
        String year = str2.split("-")[0];
        String month = str2.split("-")[1];
        String day = str2.split("-")[2];
        String hour = str3.split(":")[0];
        String min = str3.split(":")[1];
        String sec = str3.split(":")[2];
        return year + month + day + hour + min + sec;
    }

    /**
     * 获得输入时间日期时间格式
     * @param timeMillis yyyy-MM-dd HH:mm:ss
     * @return yyyyMMddHHmmss
     */
    public static String getDateMyFormat(String timeMillis) {
        String str2 = timeMillis.split(" ")[0];// yyyy-MM-dd
        String str3 = timeMillis.split(" ")[1];// HH:mm:ss
        String year = str2.split("-")[0];
        String month = str2.split("-")[1];
        String day = str2.split("-")[2];
        String hour = str3.split(":")[0];
        String min = str3.split(":")[1];
        String sec = str3.split(":")[2];
        return year + month + day + hour + min + sec;
    }

    /**
     * 获得当前日期时间格式
     * @param date yyyy-M-d
     * @return yyyyMMdd
     */
    public static String getDateFormat(String date) {

        String year = date.split("-")[0];
        String month = date.split("-")[1];
        String day = date.split("-")[2];
        if(month.length() == 1) {
            month = "0" + month;
        }
        if(day.length() == 1) {
            day = "0" + day;
        }
        return year + month + day;

    }

    /**
     * 获得当前日期时间格式
     * @param date YYYYMMDD
     * @return YYYY-MM-DD
     */
    public static String getDateService(String date) {
        String year = date.substring(0, 4);
        String month = date.substring(4, 6);
        String day = date.substring(6, 8);
        return year + "-" + month + "-" + day;
    }

    /**
     * 获得当前日期时间格式
     * @param date YYYYMMDDHHMMSS
     * @return YYYY-MM-DD HH:MM:SS
     */
    public static String getDateSpecific(String date) {
        if(TextUtils.isEmpty(date) || date.length() < 14) {
            // 返回当前时间,如果date位数不够的话
            return getDateFormat();
        }
        String year = date.substring(0, 4);
        String month = date.substring(4, 6);
        String day = date.substring(6, 8);
        String hour = date.substring(8, 10);
        String min = date.substring(10, 12);
        String sec = date.substring(12, 14);
        return year + "-" + month + "-" + day + " " + hour + ":" + min + ":" + sec;
    }

    /**
     * 获得当前日期时间格式
     * @return HHmm
     */
    public static String getTimeFormat(String time) {
        String hour = time.split(":")[0];
        String min = time.split(":")[1];
        if(hour.length() == 1) {
            hour = "0" + hour;
        }
        if(min.length() == 1) {
            min = "0" + min;
        }
        return hour + min;
    }

    /**
     * 获得当前日期时间格式
     * @param time HHmm
     * @return HH:mm
     */
    public static String getCurrentTime(String time) {
        String hour = "00";
        String min = "00";
        if(time.length() == 4) {
            hour = time.substring(0, 2);
            min = time.substring(2, 4);
        }
        return hour + ":" + min;
    }

    /**
     * @param day 输入日期格式 yyyy-MM-dd
     * @leaveDay 差距的天数 -1 or +1
     * @return 【几天前或几天后】日期
     */
    public static String getLeaveDayFormat(String day, int leaveDay) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        Date date = null;
        try {
            date = simpleDateFormat.parse(day);
        } catch(ParseException e) {
            e.printStackTrace();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        calendar.add(Calendar.DATE, leaveDay); // 得到前一天
        date = calendar.getTime();
        return simpleDateFormat.format(date);
    }

    /**
     * @param day 输入日期格式 yyyy-MM-dd
     * @leaveMonth 差距的月数 -1 or +1
     * @return 【几天前或几天后】日期
     */
    public static String getLeaveMonthFormat(String day, int leaveMonth) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = null;
        try {
            date = simpleDateFormat.parse(day);
        } catch(ParseException e) {
            e.printStackTrace();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        calendar.add(Calendar.DATE, leaveMonth); // 得到前（后）月份数据
        date = calendar.getTime();
        return simpleDateFormat.format(date);
    }

    /**
     * @param day 输入日期格式 yyyy-MM-dd
     * @return 前一天的日期
     */
    public static String getBeforeDayFormat(String day) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        Date date = null;
        try {
            date = simpleDateFormat.parse(day);
        } catch(ParseException e) {
            e.printStackTrace();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        calendar.add(Calendar.DATE, - 1); // 得到前一天
        date = calendar.getTime();
        return simpleDateFormat.format(date);
    }

    /**
     * @param day 输入日期格式 yyyy-MM-dd
     * @return 下一天的日期
     */
    public static String getNextDayFormat(String day) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        Date date = null;
        try {
            date = simpleDateFormat.parse(day);
        } catch(ParseException e) {
            e.printStackTrace();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        calendar.add(Calendar.DATE, + 1); // 得到后一天
        date = calendar.getTime();
        return simpleDateFormat.format(date);
    }

    /**
     * 将时间戳直接转换为时间
     * @param mill System.currentTimeMillis()
     * @return yyyyMMddHHmmss
     */
    public static String getDateTimeForTimeMillis(long mill) {
        return getDateMyFormat(convertForTimeMillis(mill));

    }

    /**
     * 获得输入日期的星期
     * @param inputDate 需要转换的日期 yyyy-MM-dd
     * @return 星期×
     */
    public static String getWeekDay(String inputDate) {
        String weekStrArr1[] = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        Date date = null;
        try {
            date = simpleDateFormat.parse(inputDate);
        } catch(ParseException e) {
            e.printStackTrace();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int outWeek = calendar.get(Calendar.DAY_OF_WEEK);// 返回的是1-7的整数，1为周日，2为周一，以此类推。
        return weekStrArr1[outWeek - 1];
    }

    /*
     * String weekStrArr1[] = {"周日","周一","周二","周三","周四","周五","周六"}; public String[] getWeekA(int y,int c, int m, int d){ String[]
     * weekArr = new String[7]; for(int i = 0; i < weekArr.length; i++){ weekArr[i] = ""; } for(int i = 0; i < weekArr.length; i++){
     * weekArr[i] = weekStrArr1[getWeekB(y, c, m, d + i)]; } return weekArr; } /** 根据日期获得星期
     * @param y 年 比如10年
     * @param c 世纪比如20世纪
     * @param m 月
     * @param d 日
     * @return
     */
    /*
     * private int getWeekB(int y, int c, int m, int d) { if(m == 1){ m = 13; y = y-1; }else if(m == 2){ m = 14; y = y-1; } int
     * tempDate = (y+(y/4)+(c/4)-2*c+(26*(m+1)/10)+d-1)%7; if(tempDate < 0){ return 7+tempDate; } return tempDate; }
     */
    /**
     * 检测时间是否在某个时间段内
     * @param timeSlot 时间段 00：00--24：00
     * @param time 需要检测的时间 00：23
     * @return
     */
    public static boolean isInsideTime(String timeSlot, String time) {
        String startTime = timeSlot.split("--")[0];
        String endTime = timeSlot.split("--")[1];
        boolean isGreaterStart = isCompareTime(time, startTime);
        boolean isLessEnd = isCompareTime(endTime, time);
        if(isGreaterStart && isLessEnd) {
            return true;
        }
        return false;
    }

    /**
     * 比较两个时间的大小
     * @param time1 00：23
     * @param time2 00：25
     * @return time1大于等于time2 为 true,time1小于time2 为 false
     */
    public static boolean isCompareTime(String time1, String time2) {
        if(time1.equals("24:00") || time2.equals("00:00") || time1.equals("24：00") || time2.equals("00：00")) {
            return true;
        }
        if(time2.equals("24:00") || time1.equals("00:00") || time2.equals("24：00") || time1.equals("00：00")) {
            return false;
        }
        DateFormat df = new SimpleDateFormat("HH:mm", Locale.getDefault());
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        try {
            c1.setTime(df.parse(time1));
            c2.setTime(df.parse(time2));
        } catch(ParseException e) {
            System.err.println("格式不正确");
        }
        int result = c1.compareTo(c2);

        if(result < 0) {
            return false;
        } else if(result >= 0) {
            return true;
        }
        return true;
    }

    /**
     * 比较两个时间的大小
     * @param date1 2012-5-11
     * @param date2 2012-5-11
     * @return date1大于等于date2 为 true,date1小于date2 为 false
     */
    public static boolean isCompareDate(String date1, String date2) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        try {
            c1.setTime(df.parse(date1));
            c2.setTime(df.parse(date2));
        } catch(ParseException e) {
            System.err.println("格式不正确");
        }
        int result = c1.compareTo(c2);

        if(result < 0) {
            return false;
        } else if(result >= 0) {
            return true;
        }
        return true;
    }

    /**
     * 将时间戳转换为时间
     * @param time 单位 毫秒
     * @return yyyy-MM-dd HH:mm:ss
     */
    public static String convertForTimeMillis(long time) {
        long beforeTime = time;
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(beforeTime);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        String dateString = formatter.format(calendar.getTime());

        return dateString;
    }

    /**
     * 将时间戳转换为时间
     * @param time 单位 毫秒
     * @return yyyy-MM-dd HH:mm:ss
     */
    public static String convertDataForTimeMillis(long time) {
        long beforeTime = time;
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(beforeTime);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        String dateString = formatter.format(calendar.getTime());

        return dateString;
    }

    /**
     * 将时间戳转换为时间
     * @param time 单位 毫秒
     * @return yyyy-MM-dd HH:mm:ss
     */
    public static String convertDataForTimeMillis(String format,long time) {
        long beforeTime = time;
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(beforeTime);
        SimpleDateFormat formatter = new SimpleDateFormat(format, Locale.getDefault());
        String dateString = formatter.format(calendar.getTime());

        return dateString;
    }
    /**
     * 将时间戳转化为文字显示【今天 昨天 前天】
     * @param time 单位 秒
     * @return
     */
    public static String getTime(long time) {
        long currentTime = System.currentTimeMillis() / 1000;
        String string = "";
        long distance = 0;
        distance = currentTime - time;
        if(distance < 86400) {// 60 * 60 * 24
            string = "今天";
        } else if(distance < 27800) {// 60*60*24*2
            string = "昨天";
        } else if(distance < 259200) {// 60*60*24*3
            string = "前天";
        } else {
            string = convertForTimeMillis(time);
        }
        return string;
    }

    /**
     * 将时间戳转化为文字显示【 xxx前】
     * @param time 单位 秒
     * @return
     */
    public static String getTime2(long time) {
        long currentTime = System.currentTimeMillis() / 1000;
        String string = "";
        long distance = 0;
        distance = currentTime - time;
        if(distance < 1) {// avoid 0 seconds
            string = "刚刚";
        } else if(distance < 60) {
            string = distance + "秒前";
        } else if(distance < 3600) {// 60 * 60
            string = distance / 60 + "分钟前";
        } else if(distance < 86400) {// 60 * 60 * 24
            string = distance / 3600 + "小时前";
        } else if(distance < 604800) {// 60 * 60 * 24 * 7
            string = distance / 86400 + "天前";
        } else if(distance < 2419200) {// 60 * 60 * 24 * 7 * 4
            string = distance / 604800 + "周前";
        } else {
            string = convertForTimeMillis(time);
        }
        return string;
    }

    /**
     * 将时间戳转化为计时器00:00:00的格式显示 最多只能到99小时，如需要更大小时数需要改改方法
     * @param time
     * @author Luoxiankui
     * @return
     */
    public static String getTimeCount(long time) {
        if(time >= 360000000) {
            return "00:00:00";
        }
        String timeCount = "";
        long hourc = time / 3600000;
        String hour = "0" + hourc;
        hour = hour.substring(hour.length() - 2, hour.length());

        long minuec = (time - hourc * 3600000) / (60000);
        String minue = "0" + minuec;
        minue = minue.substring(minue.length() - 2, minue.length());

        long secc = (time - hourc * 3600000 - minuec * 60000) / 1000;
        String sec = "0" + secc;
        sec = sec.substring(sec.length() - 2, sec.length());
        timeCount = hour + ":" + minue + ":" + sec;
        return timeCount;
    }

    /**
     * @author Zhaop
     * @Description 返回带 月 日 时 分的日期格式
     * @param
     */
    public static String getMonthDate(String date) {
        String dateString = "";
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("MM月dd日 H:m");
            Date dateTemp = new Date(date);
            dateString = sdf.format(dateTemp);
        } catch(Exception e) {
            // TODO: handle exception
        }
        return dateString;
    }
    
    public static final int SECONDS_IN_DAY = 60 * 60 * 24;
    public static final long MILLIS_IN_DAY = 1000L * SECONDS_IN_DAY;
 
    public static boolean isSameDayOfMillis( long ms1,  long ms2) {
        final long interval = ms1 - ms2;
        return interval < MILLIS_IN_DAY
                && interval > -1L * MILLIS_IN_DAY
                && toDay(ms1) == toDay(ms2);
    }
 
    private static long toDay(long millis) {
        return (millis + TimeZone.getDefault().getOffset(millis)) / MILLIS_IN_DAY;
    }
}
