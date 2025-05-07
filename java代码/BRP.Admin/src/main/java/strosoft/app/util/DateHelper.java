package strosoft.app.util;

import java.io.StringReader;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import com.google.gson.JsonObject;
import net.sf.json.JSONObject;

public class DateHelper {
    /*
     * 格式化日期
     */
    public static String format(Date date, String formatString) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(formatString);
        return sdf.format(date);
    }

    /*
     * 格式化日期
     */
    public static String format(Timestamp ts, String formatString) {
        if (ts == null) {
            return null;
        }
        Date date = toDate(ts);
        SimpleDateFormat sdf = new SimpleDateFormat(formatString);
        return sdf.format(date);
    }

    /**
     * 获取现在时间
     *
     * @return返回字符串格式 yyyy-MM-dd HH:mm:ss
     */
    public static String getDateTimeString() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    /**
     * 获取现在时间
     *
     * @return 返回短时间字符串格式yyyy-MM-dd
     */
    public static String getDateString() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    /**
     * 获取时间 小时:分;秒 HH:mm:ss
     *
     * @return
     */
    public static String getTimeString() {
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        Date currentTime = new Date();
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    /**
     * 将长时间格式时间转换为字符串 yyyy-MM-dd HH:mm:ss
     *
     * @param dateDate
     * @return
     */
    public static String toDateTimeString(Date dateDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(dateDate);
        return dateString;
    }

    /**
     * 将日期转换为字符串 yyyy-MM-dd
     *
     * @param dateDate
     * @param k
     * @return
     */
    public static String toDateString(Date dateDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(dateDate);
        return dateString;
    }

    /*
     * 将时间转换为字符串
     */
    public static String toTimeString(Date dateDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        String dateString = formatter.format(dateDate);
        return dateString;
    }

    /**
     * 将长时间格式字符串转换为时间 yyyy-MM-dd HH:mm:ss
     *
     * @param strDate
     * @return
     */
    public static Date toDateTime(String strDateTime) {
        if (StringHelper.isNullOrEmpty(strDateTime)) {
            return null;
        }
        if (strDateTime.substring(0, 1).equals("{")) {
            JSONObject jDate = JSONObject.fromObject(strDateTime);
            long time = jDate.getLong("time");
            Date date = new Date(time);
            return date;
        } else {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            ParsePosition pos = new ParsePosition(0);
            Date strtodate = formatter.parse(strDateTime, pos);
            if (null == strtodate) {
                strtodate = toDate(strDateTime);
            }
            return strtodate;
        }
    }

    /**
     * 将短时间格式字符串转换为时间 yyyy-MM-dd
     *
     * @param strDate
     * @return
     */
    public static Date toDate(String strDate) {
        return toDate(strDate, "yyyy-MM-dd");
    }

    /**
     * 将短时间格式字符串转换为时间 yyyy-MM-dd
     *
     * @param strDate
     * @return
     */
    public static Date toDate(String strDate, String dateFormat) {
        SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
        ParsePosition pos = new ParsePosition(0);
        Date strtodate = formatter.parse(strDate, pos);
        return strtodate;
    }

    /**
     * JSONObject转日期
     *
     * @param jDate
     * @return
     */
    public static Date toDate(JSONObject jDate) {
        long time = jDate.getLong("time");
        Date date = new Date(time);
        return date;
    }

    /**
     * 日期转JSONObject
     *
     * @param date
     * @return
     */
    public static JSONObject toJSONObject(Date date) {
        JSONObject jDate = JSONObject.fromObject(date);
        return jDate;
    }

    /**
     * 将时间格式字符串转换为时间 yyyy-MM-dd
     *
     * @param strDate
     * @return
     */
    public static Date toTime(String strTime) {
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        ParsePosition pos = new ParsePosition(0);
        Date strtodate = formatter.parse(strTime, pos);
        return strtodate;
    }

    /**
     * 将长时间格式字符串转换为时间 yyyy-MM-dd HH:mm:ss
     *
     * @param strDate
     * @return
     */
    public static Timestamp toTimestamp(String strDateTime) {
        if (StringHelper.isNullOrEmpty(strDateTime)) {
            return null;
        }
        if (strDateTime.equals("null")) {
            return null;
        }
        if (strDateTime.contains("-")) {
            if (strDateTime.toString().substring(0, 1).equals("{")) {
                JSONObject jDate = JSONObject.fromObject(strDateTime);
                long time = jDate.getLong("time");
                Timestamp ts = new Timestamp(time);
                return ts;
            } else {
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                ParsePosition pos = new ParsePosition(0);
                Date newDate = formatter.parse(strDateTime, pos);
                if (null == newDate) {
                    newDate = toDate(strDateTime);
                }
                Timestamp newTimestamp = toTimestamp(newDate);
                return newTimestamp;
            }
        } else if (strDateTime.toString().substring(0, 1).equals("{")) {
            JSONObject jDateTime = JSONObject.fromObject(strDateTime);
            // 提取年、月、日、时、分、秒等信息
            int year = jDateTime.getInt("year");
            int month = JsonHelper.getInt(jDateTime, "monthValue", "month");
            int day = JsonHelper.getInt(jDateTime, "dayOfMonth", "date");
            int hour = JsonHelper.getInt(jDateTime, "hour", "hours");
            int minute = JsonHelper.getInt(jDateTime, "minute", "minutes");
            int second = JsonHelper.getInt(jDateTime, "second", "seconds");
            // 构造 LocalDateTime 对象
            LocalDateTime dateTime = LocalDateTime.of(year, month, day, hour, minute, second);
            // 转换为 Timestamp 对象
            Timestamp timestamp = Timestamp.valueOf(dateTime);
            return timestamp;
        } else {
            return new Timestamp(Long.valueOf(strDateTime));
        }
    }

    /**
     * 时间戳转字符串
     *
     * @param ts
     * @param fmt
     * @return
     */
    public static String timestampToString(Timestamp ts, String fmt) {
        String f = fmt == null ? "yyyy-MM-dd HH:mm:ss" : fmt;
        DateFormat sdf = new SimpleDateFormat(fmt);
        return sdf.format(ts);
    }

    /**
     * 得到现在时间
     *
     * @return
     */
    public static Date getNow() {
        Date currentTime = new Date();
        return currentTime;
    }

    /**
     * 提取一个月中的最后一天
     *
     * @param day
     * @return
     */
    public static Date getLastDayOfMonth(Date date) {
        // 获取Calendar
        Calendar calendar = Calendar.getInstance();
        // 设置时间,当前时间不用设置
        calendar.setTime(date);
        // 设置日期为本月最大日期
        calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE));
        Date lastDayOfMonth = calendar.getTime();
        return lastDayOfMonth;
    }

    /**
     * 得到现在小时
     */
    public static String getHours() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("HH");
        String hoursString = formatter.format(currentTime);
        return hoursString;
    }

    /**
     * 得到现在分钟
     *
     * @return
     */
    public static String getMinutes() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("mm");
        String minituesString = formatter.format(currentTime);
        return minituesString;
    }

    /**
     * 加减年
     *
     * @param day
     * @return
     */
    public static Date addYears(Date date, int years) {
        // 获取Calendar
        Calendar calendar = Calendar.getInstance();
        // 设置时间,当前时间不用设置
        calendar.setTime(date);
        calendar.add(Calendar.YEAR, years);
        Date newDate = calendar.getTime();
        return newDate;
    }

    /**
     * 加减月
     *
     * @param day
     * @return
     */
    public static Date addMonths(Date date, int months) {
        // 获取Calendar
        Calendar calendar = Calendar.getInstance();
        // 设置时间,当前时间不用设置
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, months);
        Date newDate = calendar.getTime();
        return newDate;
    }

    /**
     * 加减天数
     */
    public static Date addDays(Date date, int days) {
        long time = date.getTime() + 1000l * 60 * 60 * 24 * days;
        Date newDate = new Date(time);
        return newDate;
    }

    /**
     * 加减小时
     */
    public static Date addHours(Date date, int hours) {
        Date newDate = new Date(date.getTime() + 1000l * 60 * 60 * hours);
        return newDate;
    }

    /**
     * 加减分钟
     */
    public static Date addMinutes(Date date, int minitues) {
        Date newDate = new Date(date.getTime() + 1000l * 60 * minitues);
        return newDate;
    }

    /**
     * 加减秒
     */
    public static Date addSeconds(Date date, int seconds) {
        Date newDate = new Date(date.getTime() + 1000l * seconds);
        return newDate;
    }

    /**
     * 加减秒
     */
    public static Date addMilliseconds(Date date, int milliseconds) {
        Date newDate = new Date(date.getTime() + milliseconds);
        return newDate;
    }

    /**
     * 判断是否润年
     */
    public static boolean isLeapYear(Date date) {
        GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
        gc.setTime(date);
        int year = gc.get(Calendar.YEAR);
        if ((year % 400) == 0)
            return true;
        if ((year % 4) == 0 && (year % 100) != 0) {
            return true;
        }
        return false;
    }

    /**
     * 判断二个时间是否在同一个周
     *
     * @param date1
     * @param date2
     * @return
     */
    public static boolean isSameWeekDates(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(date1);
        cal2.setTime(date2);
        int subYear = cal1.get(Calendar.YEAR) - cal2.get(Calendar.YEAR);
        if (0 == subYear) {
            if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2.get(Calendar.WEEK_OF_YEAR))
                return true;
        } else if (1 == subYear && 11 == cal2.get(Calendar.MONTH)) {
            // 如果12月的最后一周横跨来年第一周的话则最后一周即算做来年的第一周
            if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2.get(Calendar.WEEK_OF_YEAR))
                return true;
        } else if (-1 == subYear && 11 == cal1.get(Calendar.MONTH)) {
            if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2.get(Calendar.WEEK_OF_YEAR))
                return true;
        }
        return false;
    }

    /*
     * 取得星期几字符串
     */
    public static String getDayOfWeekString(Date date) {
        String str = "";
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        if (dayOfWeek == 1) {
            str = "星期日";
        } else if (dayOfWeek == 2) {
            str = "星期一";
        } else if (dayOfWeek == 3) {
            str = "星期二";
        } else if (dayOfWeek == 4) {
            str = "星期三";
        } else if (dayOfWeek == 5) {
            str = "星期四";
        } else if (dayOfWeek == 6) {
            str = "星期五";
        } else if (dayOfWeek == 7) {
            str = "星期六";
        }
        return str;
    }

    /**
     * 取得随机数字
     *
     * @param max
     * @return
     */
    public static int GetRandomNumber(int max) {
        return GetRandomNumber(max, 1);
    }

    /**
     * 取得随机数字
     *
     * @param max
     * @param min
     * @return
     */
    public static int GetRandomNumber(int max, int min) {
        long randomNum = System.currentTimeMillis();
        int randomNumber = (int) (randomNum % (max - min) + min);
        return randomNumber;
    }

    /**
     * Timestamp转Date
     *
     * @param ts
     * @param fmt
     * @return
     */
    public static Date timestampToDate(Timestamp ts) {
        Date date = new Date(ts.getTime());
        return date;
    }

    /**
     * Timestamp转Date
     *
     * @param ts
     * @param fmt
     * @return
     */
    public static Date toDate(Timestamp ts) {
        Date date = new Date(ts.getTime());
        return date;
    }

    /**
     * Date转Timestamp
     *
     * @param date
     * @return
     */
    public static Timestamp dateToTimestamp(Date date) {
        Timestamp tsMinCn = new Timestamp(70, 0, 1, 8, 0, 0, 0);
        Timestamp ts = new Timestamp(date.getTime());
        if (ts.before(tsMinCn)) {
            ts = tsMinCn;
        }
        return ts;
    }

    /**
     * Date转Timestamp
     *
     * @param date
     * @return
     */
    public static Timestamp toTimestamp(Date date) {
        if (date == null) {
            return null;
        }
        Timestamp tsMinCn = new Timestamp(0);
        Timestamp ts = new Timestamp(date.getTime());
        if (ts.before(tsMinCn)) {
            ts = tsMinCn;
        }
        return ts;
    }

    /**
     * 取得指定日期所在周的最后一天
     *
     * @param date
     * @return Last day of this day's week
     */
    public static Date getLastDayOfWeek(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int currentDay = cal.get(Calendar.DAY_OF_WEEK);
        int leftDays = Calendar.SATURDAY - currentDay;
        cal.add(Calendar.DATE, leftDays);
        return cal.getTime();
    }

    /**
     * 取得指定日期所在周的第一天
     *
     * @param date
     * @return First day of this day's week
     */
    public static Date getFirstDayOfWeek(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int currentDay = cal.get(Calendar.DAY_OF_WEEK);
        int pastDays = Calendar.SUNDAY - currentDay;
        cal.add(Calendar.DATE, pastDays);
        return cal.getTime();
    }

    /**
     * 取得当前时间
     *
     * @return
     */
    public static Timestamp getCurrentTimestamp() {
        Timestamp currentTimestamp = new Timestamp(new Date().getTime());
        return currentTimestamp;
    }

    /**
     * 取得昨天
     *
     * @return
     */
    public static Date getYesterday() {
        long time = new Date().getTime() - 1000l * 60 * 60 * 24;
        Date newDate = new Date(time);
        return newDate;
    }

    /**
     * 新编号
     *
     * @param dateDate
     * @return
     */
    public static String newNo() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String dateString = formatter.format(new Date());
        return dateString;
    }

    /**
     * 添加天数
     * @param args
     */
    public static Timestamp addDays(Timestamp timestamp, int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timestamp.getTime());
        calendar.add(Calendar.DAY_OF_MONTH, days);
        return new Timestamp(calendar.getTimeInMillis());
    }



    public static void main(String[] args) {
       /* JSONObject jData = new JSONObject();
        jData.put("ts", "2022-02-03 16:00:00");
        Timestamp ts = JsonHelper.getTimestampValue(jData, "ts");*/

        System.out.println(getDateString());
        System.out.println(toDateString(addDays(new Date(), 1)));
    }
}
