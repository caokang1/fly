package com.cignacmb.smart.base.utils.basic;

import com.cignacmb.smart.base.config.Commons;
import com.cignacmb.smart.base.utils.excel.ExcelConst;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.lang.management.ManagementFactory;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * 日期工具类
 * @author r9wuxx
 */
public class DateUtils extends org.apache.commons.lang3.time.DateUtils {

    public static String YYYY = "yyyy";

    public static String YYYY_MM = "yyyy-MM";

    public static String YYYY_MM_DD = "yyyy-MM-dd";

    public static String YYYYMMDD = "yyyyMMdd";

    public static String YYYYMMDD1 = "yyyy/MM/dd";

    public static String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";

    public static String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

    public static String HH_MM_SS = "HH:mm:ss";

    public static String CST_US = "EEE MMM dd HH:mm:ss Z yyyy";

    public static String CST_CHINA = "yyyy'年' MM'月' dd'日' EEE HH:mm:ss Z";

    private static String[] parsePatterns = {
            "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM",
            "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy/MM",
            "yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm", "yyyy.MM"};

    /**
     * 获取当前Date型日期
     *
     * @return Date() 当前日期
     */
    public static Date parseNowDate()
    {
        return new Date();
    }

    /**
     * 日期型字符串转化为日期 格式
     */
    public static Date parseDate(Object str)
    {
        if (str == null)
        {
            return null;
        }
        try
        {
            return parseDate(str.toString(), parsePatterns);
        }
        catch (ParseException e)
        {
            return parseCSTDate(str);
        }
    }

    /**
     * 转换CST格式的日期
     * @param str
     * @return
     */
    public static Date parseCSTDate(Object str) {

        if(str == null || "".equals(str.toString())) {
            return null;
        }

        Date date = null;

        SimpleDateFormat sdf1 = new SimpleDateFormat(CST_US, Locale.US);

        try {

            date = sdf1.parse(str.toString());

        }catch(Exception e) {

            try {

                sdf1 = new SimpleDateFormat(CST_CHINA, Locale.CHINA);
                date = sdf1.parse(str.toString());

            }catch(Exception e1) {

                return null;

            }

        }

        return date;

    }

    public static final Date parseDateTime(final String format, final Object ts)
    {
        if(ts == null || "".equals(ts.toString())) {
            return null;
        }

        try
        {
            return new SimpleDateFormat(format).parse(ts.toString());
        }
        catch (ParseException e)
        {
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取当前日期并转换为数据库日期，格式为yyyy-mm-dd
     * @return
     */
    public static Date parseDBNowDate() {
        return parseDateTime(YYYY_MM_DD,getNowDateStr());
    }

    /**
     * 转换成数据库日期，格式为yyyy-mm-dd
     * @param date
     * @return
     */
    public static Date parseDBDate(Date date) {
        return parseDateTime(YYYY_MM_DD,getDateToStr(YYYY_MM_DD,date));
    }

    /**
     * 获取服务器启动时间
     */
    public static Date parseServerStartDate()
    {
        long time = ManagementFactory.getRuntimeMXBean().getStartTime();
        return new Date(time);
    }

    /**
     * 获取当前日期, 默认格式为yyyy-MM-dd
     *
     * @return String
     */
    public static String getNowDateStr()
    {
        return getNowDateTimeStr(YYYY_MM_DD);
    }

    /**
     * 获取当前日期时间，格式yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static final String getNowTimeStr1()
    {
        return getNowDateTimeStr(YYYY_MM_DD_HH_MM_SS);
    }

    /**
     * 获取当前日期时间，格式yyyyMMddHHmmss
     * @return
     */
    public static final String getNowTimeStr()
    {
        return getNowDateTimeStr(YYYYMMDDHHMMSS);
    }

    /**
     * 根据指定的格式获取当前的日期时间
     * @param format
     * @return
     */
    public static final String getNowDateTimeStr(String format)
    {
        return getDateToStr(format, new Date());
    }

    /**
     * 获取当前日期字符串，格式为yyyy-MM-dd
     * @param date
     * @return
     */
    public static final String getDateStr(Date date){
        if(date == null) {
            return "";
        }

        return getDateToStr(YYYY_MM_DD, date);
    }

    /**
     * 根据指定格式返回日期字符串
     * @param format
     * @param date
     * @return
     */
    public static final String getDateToStr(String format, Date date){
        if(date == null) {
            return "";
        }

        if(format == null || "".equals(format)) {
            return new SimpleDateFormat(YYYY_MM_DD).format(date);
        }

        return new SimpleDateFormat(format).format(date);
    }

    /**
     * 日期路径 如20180808
     */
    public static String getDatePath()
    {
        Date now = new Date();
        return DateFormatUtils.format(now, YYYYMMDD);
    }

    /**
     * 获取当前时分秒，格式为hh:mm:ss
     * @return
     */
    public static String getNowTime() {
        return getTimeStr(parseNowDate());
    }

    /**
     * 获取时分秒，格式为hh:mm:ss
     * @return
     */
    public static String getTimeStr(Date date) {
        return getDateToStr(HH_MM_SS, date);
    }

    /**
     * 计算两个时间差
     */
    public static String getDatePoor(Date endDate, Date nowDate)
    {
        long nd = 1000 * 24 * 60 * 60;
        long nh = 1000 * 60 * 60;
        long nm = 1000 * 60;
        // long ns = 1000;
        // 获得两个时间的毫秒时间差异
        long diff = endDate.getTime() - nowDate.getTime();
        // 计算差多少天
        long day = diff / nd;
        // 计算差多少小时
        long hour = diff % nd / nh;
        // 计算差多少分钟
        long min = diff % nd % nh / nm;
        // 计算差多少秒//输出结果
        // long sec = diff % nd % nh % nm / ns;
        return day + "天" + hour + "小时" + min + "分钟";
    }
    // 获取年
    public static int getYear(long time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);
        int year = calendar.get(Calendar.YEAR);
        return year;
    }

    // 获取指定格式时间的年
    public static int getYear(String time, DateFormat format) {
        int year = 0;
        try {
            Date date = format.parse(time);
            long t = date.getTime();
            year = getYear(t);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return year;
    }

    // 获取月
    public static int getMonth(long time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);
        int month = calendar.get(Calendar.MONTH);
        return month+1;
    }

    // 获取日
    public static int getDay(long time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        return day;
    }

    // 获取时
    public static int getHour(long time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);
        // 24制时间
        int hour = calendar.get(Calendar.HOUR_OF_DAY);

        // 12制时间
        // int hour = calendar.get(Calendar.HOUR);
        return hour;
    }

    // 获取分
    public static int getMinuth(long time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);
        int minuth = calendar.get(Calendar.MINUTE);
        return minuth;
    }

    // 把一种格式的时间转成另外一种格式
    public static String timeTransfer(String time, DateFormat sourceFormat,
                                      DateFormat targetFormat) {
        long t = getTime(time, sourceFormat);
        time = getTime(t, targetFormat);
        return time;

    }

    // 把String类型的时间转换成long型时间
    public static long getTime(String time, DateFormat format) {
        long t = 0;
        try {
            Date date = format.parse(time);
            t = date.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return t;
    }

    // 把long类型的时间转换成String型时间
    public static String getTime(long time, DateFormat format) {
        String t = format.format(time);
        return t;
    }


    // 获取某年某个月的天数
    public static int getDayofMouth(long time) {
        Calendar calendar = Calendar.getInstance();
        int days = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        return days;
    }

    // 获取某年某个月的天数
    public static int getDayofMouth(int year, int mouth) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH,mouth-1);
        int days = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        return days;
    }

    /**
     * 判断是否是SMART上传功能中认可的日期格式
     * @param dateStr
     * @return
     */
    public static boolean isSmartDate(String dateStr) {
        if(!isValidDateA(dateStr) && !isValidDateB(dateStr) && !isValidDateC(dateStr) ){
            return false;
        }
        return true;
    }

    /**
     * 将符合SMART格式的日期格式为YYYY-MM-DD并返回
     * @param dateStr
     * @return
     */
    public static String getSmartDateStr(String dateStr) {

        if("".equals(dateStr) || dateStr == null){
            return "";
        }
        String date = "";

        try {
            if(dateStr.contains("-")){
                //先转换成date再转换成string
                date = getDateStr(parseDateTime(YYYY_MM_DD, dateStr));

            }else if(dateStr.contains("/")){

                date = getDateStr(parseDateTime(YYYYMMDD1, dateStr));

            }else if(dateStr.length() <= 8){

                date = getDateStr(parseDateTime(YYYYMMDD, dateStr));

            }
        } catch (Exception e) {
            e.printStackTrace();
            date = ExcelConst.VALID_DATE_FALSE.getValue();
        }

        return date;

    }

    /**
     * 日期加减
     * @param date			日期
     * @param time			加减数字
     * @param field			加减类型，参考Calendar
     * @return
     */
    public static Date parseCalDate(Date date, int time, int field) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        calendar.add(field, time);

        return calendar.getTime();

    }

    /**
     *	  追加日期天数
     * @param date 日期
     * @param num  追加的天数
     * @return YYYY-MM-DD
     */
    public static String getAddDateStr(Date date,int num) {
        SimpleDateFormat sj = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, num);
        return sj.format(calendar.getTime());
    }

    private static boolean isValidDateA(String str){
        boolean flag= true;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try{
            format.setLenient(false);
            format.parse(str);
        }catch(ParseException e){
            flag = false;
        }
        return flag;
    }

    private static boolean isValidDateB(String str){
        boolean flag= true;
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        try{
            format.setLenient(false);
            format.parse(str);
        }catch(ParseException e){
            flag = false;
        }
        return flag;
    }

    private static boolean isValidDateC(String str){
        boolean flag= true;
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        try{
            format.setLenient(false);
            format.parse(str);
        }catch(ParseException e){
            flag = false;
        }
        return flag;
    }

    public static void main(String[] args) {
        String str = "Fri Sep 09 00:00:00 CST 1994";
        System.out.println(DateUtils.getDateStr(DateUtils.parseDate(str)));
    }

}
