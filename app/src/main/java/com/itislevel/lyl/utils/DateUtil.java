package com.itislevel.lyl.utils;

import android.support.annotation.Nullable;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static java.lang.System.currentTimeMillis;
import static java.lang.System.load;

/**
 * Created by codeest on 16/8/13.
 */

public class DateUtil {

    /**
     * 获取当前日期
     * @return
     */
    public static String getCurrentDate() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return df.format(new Date());
    }

    public static String getCurrentDate(Date date) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return df.format(date);
    }

    public static String getCurrentDateMM(Date date) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM");
        return df.format(date);
    }

    public static String getCurrentYYYYMM(Date date) {
        SimpleDateFormat df = new SimpleDateFormat("yyyyMM");
        return df.format(date);
    }

    /**
     * 获取当前日期
     * @return
     */
    public static String getTomorrowDate() {
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
        return String.valueOf(Integer.valueOf(df.format(new Date())) + 1);
    }

    /**
     * 获取当前日期字符串
     * @return
     */
    public static String getCurrentDateString() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy年MM月dd日");
        return df.format(new Date());
    }

    /**
     * 获取当前年
     * @return
     */
    public static int getCurrentYear() {
        Calendar cal= Calendar.getInstance();
        return cal.get(Calendar.YEAR);
    }

    /**
     * 获取当前月
     * @return
     */
    public static int getCurrentMonth() {
        Calendar cal= Calendar.getInstance();
        return cal.get(Calendar.MONTH);
    }

    /**
     * 获取当前日
     * @return
     */
    public static int getCurrentDay() {
        Calendar cal= Calendar.getInstance();
        return cal.get(Calendar.DATE);
    }

    /**
     * 切割标准时间
     * @param time
     * @return
     */
    @Nullable
    public static String subStandardTime(String time) {
        int idx = time.indexOf(".");
        if (idx > 0) {
            return time.substring(0, idx).replace("T"," ");
        }
        return null;
    }

    /**
     * 将时间戳转化为字符串
     * @param showTime
     * @return
     */
    public static String formatTime2String(long showTime) {
        return formatTime2String(showTime,false);
    }

    public static String formatTime2String(long showTime , boolean haveYear) {
        String str = "";
        long distance = currentTimeMillis()/1000 - showTime;
        if(distance < 300){
            str = "刚刚";
        }else if(distance >= 300 && distance < 600){
            str = "5分钟前";
        }else if(distance >= 600 && distance < 1200){
            str = "10分钟前";
        }else if(distance >= 1200 && distance < 1800){
            str = "20分钟前";
        }else if(distance >= 1800 && distance < 2700){
            str = "半小时前";
        }else if(distance >= 2700){
            Date date = new Date(showTime * 1000);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            str = formatDateTime(sdf.format(date) , haveYear);
        }
        return str;
    }

    public static String formatDate2String(String time) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if(time == null){
            return "未知";
        }
        try {
            long createTime = format.parse(time).getTime() / 1000;
            long currentTime = System.currentTimeMillis() / 1000;
            if (currentTime - createTime - 24 * 3600 > 0) { //超出一天
                return (currentTime - createTime) / (24 * 3600) + "天前";
            } else {
                return (currentTime - createTime) / 3600 + "小时前";
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "未知";
    }

    public static String formatDate3String(long starttimespan,long endttimespan) {
        String res="还剩";
        long l = endttimespan - starttimespan;
        long day=24*60*60*1000;
        long hour=60*60*1000;
        long minute=60*1000;

        long l1 = l / day;//多少天
        res+=l1+"天";

        long l2= l % day;// 余数多少s

        long l3=l2/hour;//多少个小时
        res+=l3+"小时";

        long l5 = l2 % hour;

        long l4 = l5/minute;
        res+=l4+"分钟";

        return res;

    }


    public static String getFriendlytime(Date d){
        long delta = (new Date().getTime()-d.getTime())/1000;
        if(delta<=0)return d.toLocaleString();
        if(delta/(60*60*24*365) > 0) return delta/(60*60*24*365) +"年前";
        if(delta/(60*60*24*30) > 0) return delta/(60*60*24*30) +"个月前";
        if(delta/(60*60*24*7) > 0)return delta/(60*60*24*7) +"周前";
        if(delta/(60*60*24) > 0) return delta/(60*60*24) +"天前";
        if(delta/(60*60) > 0)return delta/(60*60) +"小时前";
        if(delta/(60) > 0)return delta/(60) +"分钟前";
        return "刚刚";
    }

    public static String formatDateTime(String time , boolean haveYear) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if(time == null){
            return "";
        }
        Date date;
        try {
            date = format.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
            return "";
        }

        Calendar current = Calendar.getInstance();
        Calendar today = Calendar.getInstance();
        today.set(Calendar.YEAR, current.get(Calendar.YEAR));
        today.set(Calendar.MONTH, current.get(Calendar.MONTH));
        today.set(Calendar.DAY_OF_MONTH, current.get(Calendar.DAY_OF_MONTH));
        today.set(Calendar.HOUR_OF_DAY, 0);
        today.set(Calendar.MINUTE, 0);
        today.set(Calendar.SECOND, 0);
        Calendar yesterday = Calendar.getInstance();
        yesterday.set(Calendar.YEAR, current.get(Calendar.YEAR));
        yesterday.set(Calendar.MONTH, current.get(Calendar.MONTH));
        yesterday.set(Calendar.DAY_OF_MONTH, current.get(Calendar.DAY_OF_MONTH)-1);
        yesterday.set(Calendar.HOUR_OF_DAY, 0);
        yesterday.set(Calendar.MINUTE, 0);
        yesterday.set(Calendar.SECOND, 0);

        current.setTime(date);
        if(current.after(today)){
            return "今天 "+time.split(" ")[1];
        }else if(current.before(today) && current.after(yesterday)){
            return "昨天 "+time.split(" ")[1];
        }else{
            if(haveYear) {
                int index = time.indexOf(" ");
                return time.substring(0,index);
            }else {
                int yearIndex = time.indexOf("-")+1;
                int index = time.indexOf(" ");
                return time.substring(yearIndex,time.length()).substring(0,index);
            }
        }
    }

    /*
   * 将时间戳转换为时间
   */
    public static String timeSpanToDateTime(long s){
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long lt = new Long(s);
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        return res;
    }

    public static String timeSpanToDateTime1(long s){
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        long lt = new Long(s);
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        return res;
    }


    public static String timeSpanToDate(long s){
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        long lt = new Long(s);
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        return res;
    }
    public static String timeSpanToDate_han(long s){
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日");
        long lt = new Long(s);
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        return res;
    }

    public static String monthToDate(long s){
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd HH:mm");
        long lt = new Long(s);
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        return res;
    }
    public static String timeSpanToDateYearMonth(long s){
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月");
        long lt = new Long(s);
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        return res;
    }

    public static String timeSpanToDateYearMonth(Date date){
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月");
        res = simpleDateFormat.format(date);
        return res;
    }

    public static String timeSpanToDateYearMonthDAY(Date date){
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日");
        res = simpleDateFormat.format(date);
        return res;
    }


    public static String timeSpanToDateYearMonthGang(Date date){
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM");
        res = simpleDateFormat.format(date);
        return res;
    }

    public static String timeStrSpanToDate(Date date){
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        res = simpleDateFormat.format(date);
        return res;
    }

    public static String timeSpanToDateTiem(Date date){
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd HH:mm");
        res = simpleDateFormat.format(date);
        return res;
    }
    public static String timeStrToDateTime(Date date){
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        res = simpleDateFormat.format(date);
        return res;
    }

    public static String timeStrToDateTime1(Date date){
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd HH:mm");
        res = simpleDateFormat.format(date);
        return res;
    }



    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /*
   * 将时间转换为时间戳
   */
    public static String dateToTimespan(String s) throws ParseException{
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = simpleDateFormat.parse(s);
        long ts = date.getTime();
        res = String.valueOf(ts);
        return res;
    }
    public static Date stringToDate(String str) {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            // Fri Feb 24 00:00:00 CST 2012
            date = format.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        // 2012-02-24
        date = java.sql.Date.valueOf(str);

        return date;
    }

}
