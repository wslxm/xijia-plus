package io.github.wslxm.springbootplus2.core.utils;

import lombok.extern.slf4j.Slf4j;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.TemporalUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * 时间处理工具类
 *
 * @author ws
 * @mail 1720696548@qq.com
 * @date 2020/4/24 0024 9:54
 * @return
 */
@Slf4j
public class LocalDateTimeUtil {

    /**
     * 时间格式
     */
    private static final String YYYY_MM_DD_HH_MM_SS_SSS = "yyyy-MM-dd HH:mm:ss.SSS";
    private static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    private static final String YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";
    private static final String YYYY_MM_DD_HH = "yyyy-MM-dd HH";
    private static final String YYYY_MM_DD = "yyyy-MM-dd";
    private static final String YYYY_MM = "yyyy-MM";
    private static final String YYYY = "yyyy";

    /**
     * 获取时间类型
     * 1-包含开始和结束时间(默认)
     * 2-包含结束-不包含开始时间   // 开始时间+1天
     * 3-包含开始-不包含结束时间   // 结束时间-1天
     * 4-不包含开始和结束时间 // 开始时间+1天  or 结束时间-1天
     */
    private static final int BETWEEN_TYPE_ONE = 1;
    private static final int BETWEEN_TYPE_TWO = 2;
    private static final int BETWEEN_TYPE_THREE = 3;
    private static final int BETWEEN_TYPE_FOUR = 4;

    private static Random random;

    static {
        try {
            random = SecureRandom.getInstanceStrong();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    /**
     * 判断时间 小于
     * <P>   t1 < t2 = true （如：2019-10-13 11:11:00 < 2020-11-13 13:13:00 = true）  </P>
     *
     * @author wangsong
     */
    public static boolean isBefore(LocalDateTime t1, LocalDateTime t2) {
        return t1.isBefore(t2);
    }

    /**
     * 判断时间 大于
     * <P>   t1 > t2 = true  </P>
     *
     * @author wangsong
     */
    public static boolean isAfter(LocalDateTime t1, LocalDateTime t2) {
        return t1.isAfter(t2);
    }


    /**
     * 自构建 LocalDateTime ==> 年，月，日，时，分
     *
     * @author wangsong
     */
    public static LocalDateTime of(int year, int month, int dayOfMonth, int hour, int minute) {
        return LocalDateTime.of(year, month, dayOfMonth, hour, minute);
    }

    /**
     * 自构建 LocalDateTime ==> 年，月，日，时，分，秒，毫秒（精确到9位数）
     *
     * @author wangsong
     */
    public static LocalDateTime of(int year, int month, int dayOfMonth, int hour, int minute, int second, int nanoOfSecond) {
        return LocalDateTime.of(year, month, dayOfMonth, hour, minute, second, nanoOfSecond);
    }


    //========================================================================================================
    //========================================================================================================
    //========================================================================================================
    //============================================== 时间获取 =================================================
    //========================================================================================================
    //========================================================================================================


    /**
     * 获取当前时间
     *
     * @author wangsong
     */
    public static LocalDateTime now() {
        return LocalDateTime.now();
    }

    /**
     * 获取指定时间是周几  1到7
     *
     * @author wangsong
     */
    public static int week(LocalDateTime time) {
        return time.getDayOfWeek().getValue();
    }


    /**
     * 获取加或减N月的第一天 00:00:00
     *
     * @author wangsong
     */
    public static LocalDateTime monthFirst(int num) {
        LocalDateTime newTime = plus(LocalDateTime.now(), num, ChronoUnit.MONTHS);
        newTime = newTime.with(TemporalAdjusters.firstDayOfMonth());
        return getDayStart(newTime);
    }

    /**
     * 获取加或减N月的最后一天 23:59:59:99999999
     *
     * @author wangsong
     */
    public static LocalDateTime monthLast(int num) {
        LocalDateTime newTime = plus(LocalDateTime.now(), num, ChronoUnit.MONTHS);
        newTime = newTime.with(TemporalAdjusters.lastDayOfMonth());
        return getDayEnd(newTime);
    }


    /**
     * 获取指定时间月第一天
     *
     * @author wangsong
     */
    public static LocalDateTime getMonthOfFirst(LocalDateTime time) {
        LocalDateTime firstday = time.with(TemporalAdjusters.firstDayOfMonth());
        return LocalDateTime.of(firstday.toLocalDate(), LocalTime.MIN);
    }

    /**
     * 获取指定时间月最后一天
     *
     * @author wangsong
     */
    public static LocalDateTime getMonthOfLast(LocalDateTime time) {
        LocalDateTime lastDay = time.with(TemporalAdjusters.lastDayOfMonth());
        return LocalDateTime.of(lastDay.toLocalDate(), LocalTime.MAX);
    }


    /**
     * 获取指定月 加或减N月的第一天 00:00:00
     *
     * @author wangsong
     */
    public static LocalDateTime getMonthOfFirst(LocalDateTime time, int num) {
        LocalDateTime newTime = plus(time, num, ChronoUnit.MONTHS);
        newTime = newTime.with(TemporalAdjusters.firstDayOfMonth());
        return getDayStart(newTime);
    }


    /**
     * 获取指定月 加或减N月的最后一天  23:59:59:99999999
     *
     * @author wangsong
     */
    public static LocalDateTime getMonthOfLast(LocalDateTime time, int num) {
        LocalDateTime newTime = plus(time, num, ChronoUnit.MONTHS);
        newTime = newTime.with(TemporalAdjusters.lastDayOfMonth());
        return getDayEnd(newTime);
    }


    /**
     * 获取加或减N周的第一天 00:00:00
     *
     * @author wangsong
     */
    public static LocalDateTime weekFirst(int num) {
        int week = week(LocalDateTime.now());
        LocalDateTime newTime = subtract(LocalDateTime.now(), week - 1L, ChronoUnit.DAYS);
        newTime = plus(newTime, num * 7L, ChronoUnit.DAYS);
        return getDayStart(newTime);
    }

    /**
     * 获取加或减N周的最后一天  23:59:59:99999999
     *
     * @author wangsong
     */
    public static LocalDateTime weekLast(int num) {
        int week = week(LocalDateTime.now());
        LocalDateTime newTime = plus(LocalDateTime.now(), 7L - week, ChronoUnit.DAYS);
        newTime = plus(newTime, num * 7L, ChronoUnit.DAYS);
        return getDayEnd(newTime);
    }


    /**
     * 获取指定年的第一天 00:00:00
     *
     * @author wangsong
     */
    public static LocalDateTime yearFirst(LocalDateTime time) {
        int year = time.getYear();
        // 年 月  天 时 分 秒 毫秒（这里精确到9位数）
        return LocalDateTime.of(1, 1, 0, 0, 0, 0);
    }

    /**
     * 获取指定年的最后一天  23:59:59:99999999
     *
     * @author wangsong
     */
    public static LocalDateTime yearLast(LocalDateTime time) {
        int year = time.getYear();
        // 年 月  天 时 分 秒 毫秒（这里精确到6位数）
        return LocalDateTime.of(
                year,
                12,
                31,
                23,
                59,
                59,
                999999);
    }


    /**
     * 获取指定时间之后的日期
     * <P>  根据field不同加不同值 , field为ChronoUnit.*
     * 秒   ChronoUnit.SECONDS
     * 分   ChronoUnit.MINUTES
     * 时   ChronoUnit.HOURS
     * 半天  ChronoUnit.HALF_DAYS
     * 天    ChronoUnit.DAYS
     * 月    ChronoUnit.MONTHS
     * 年    ChronoUnit.YEARS
     * </P>
     *
     * @author wangsong
     */
    public static LocalDateTime plus(LocalDateTime time, long number, TemporalUnit field) {
        return time.plus(number, field);
    }


    /**
     * 获取指定时间之前的日期
     *
     * @author wangsong
     * <P> 根据field不同减不同值 , field为ChronoUnit.*
     * 秒   ChronoUnit.SECONDS
     * 分   ChronoUnit.MINUTES
     * 时   ChronoUnit.HOURS
     * 半天  ChronoUnit.HALF_DAYS
     * 天    ChronoUnit.DAYS
     * 月    ChronoUnit.MONTHS
     * 年    ChronoUnit.YEARS
     * </P>
     * @version 1.0.1
     */
    public static LocalDateTime subtract(LocalDateTime time, long number, TemporalUnit field) {
        return time.minus(number, field);
    }


    /**
     * 获取两个日期的差
     *
     * @param startTime 开始时间
     * @param endTime   计算时间
     * @param field     根据field不同减不同值 , field为ChronoUnit.*
     * @return startTime小 endTime大 返回正数，则反之
     * @author wangsong
     * <p>
     * 秒   ChronoUnit.SECONDS
     * 分   ChronoUnit.MINUTES
     * 时   ChronoUnit.HOURS
     * 半天  ChronoUnit.HALF_DAYS
     * 天    ChronoUnit.DAYS
     * 月    ChronoUnit.MONTHS
     * 年    ChronoUnit.YEARS
     * </P>
     */
    public static long betweenTwoTime(LocalDateTime startTime, LocalDateTime endTime, ChronoUnit field) {
        Period period = Period.between(LocalDate.from(startTime), LocalDate.from(endTime));
        if (field == ChronoUnit.YEARS) {
            return period.getYears();
        }
        if (field == ChronoUnit.MONTHS) {
            return period.getYears() * 12L + period.getMonths();
        }
        return field.between(startTime, endTime);
    }


    /**
     * 获取指定某一天的开始时间 00:00:00
     *
     * @param time
     * @return java.time.LocalDateTime
     * @author wangsong
     * @date 2020/12/24 0024 15:10
     * @version 1.0.1
     */
    public static LocalDateTime getDayStart(LocalDateTime time) {
        return time.withHour(0)
                .withMinute(0)
                .withSecond(0)
                .withNano(0);
    }


    /**
     * 获取指定某一天的结束时间  23:59:59.999
     *
     * @author wangsong
     */
    public static LocalDateTime getDayEnd(LocalDateTime time) {
        // 年 月  天 时 分 秒 毫秒（这里精确到6位数）
        return time
                // .withDayOfMonth(1)
                // .withDayOfYear(2)
                .withHour(23)
                .withMinute(59)
                .withSecond(59)
                .withNano(999999);
    }


    /**
     * 获取指定时间的周一
     *
     * @author wangsong
     */
    public static LocalDateTime getWeekOfFirst(LocalDateTime time) {
        return time.with(TemporalAdjusters.previous(DayOfWeek.SUNDAY)).
                plusDays(1).withHour(0).withMinute(0).withSecond(0);
    }


    /**
     * 获取指定时间的周日
     *
     * @author wangsong
     */
    public static LocalDateTime getWeekOfLast(LocalDateTime time) {
        return time.with(TemporalAdjusters.next(DayOfWeek.MONDAY)).
                minusDays(1).withHour(23).withMinute(59).withSecond(59);
    }


    /**
     * 获取17位时间戳字符串+3位随机数
     * <p>  这里增加了线程锁和延时一毫秒，单体项目100%不会重复，可用于生成订单号  </p>
     * 20200101125959999  2020-01-01 12:59:59:999
     *
     * @return
     * @author wangsong
     */
    public static synchronized String getTimeStr20() {
        String timeStamp = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
        timeStamp += (random.nextInt(10) + "") + (random.nextInt(10) + "") + (random.nextInt(10) + "");
        return timeStamp;
    }


    /**
     * 获取整点--  把指定时间的 分+秒设置为0
     *
     * @param time time
     * @return java.time.LocalDateTime
     * @author wangsong
     * @date 2020/12/24 0024 15:10
     * @version 1.0.1
     */
    public static LocalDateTime getTheHour(LocalDateTime time) {
        // 分   // 秒   // 毫秒（这里精确到9位数）
        return time.withMinute(0)
                .withSecond(0)
                .withNano(0);
    }


    /**
     * 获取整分--  把指定时间的 秒设置为0
     * <p>
     * 如：
     * 2020-01-01 12:10  ===>  等于 2020-01-01 12:20
     * 2020-01-01 12:11  ===>  等于 2020-01-01 12:20
     * 2020-01-01 12:19  ===>  等于 2020-01-01 12:20
     * </P>
     *
     * @param time
     * @return java.time.LocalDateTime
     * @author wangsong
     * @date 2020/12/24 0024 15:21
     * @version 1.0.1
     */
    public static LocalDateTime getTheMinute(LocalDateTime time) {
        // 秒    // 毫秒（这里精确到9位数）
        return time.withSecond(0).withNano(0);
    }


    //========================================================================================================
    //========================================================================================================
    //========================================================================================================
    //============================================== 转换相关 =================================================
    //========================================================================================================
    //========================================================================================================

    /**
     * LocalDateTime 转为 天 的字符串，如1号返回 01
     *
     * @author wangsong
     */
    public static Integer parseDayInt() {
        return Integer.parseInt(parse(LocalDateTime.now(), "dd"));
    }


    /**
     * Date 转 LocalDateTime
     *
     * @author wangsong
     */
    public static LocalDateTime parseLdt(Date date) {
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }

    /**
     * LocalDateTime 转 Date
     *
     * @author wangsong
     */
    public static Date parseDate(LocalDateTime time) {
        return Date.from(time.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * LocalDateTime 转 毫秒
     *
     * @author wangsong
     */
    public static Long parseMillisecond(LocalDateTime time) {
        return time.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    /**
     * LocalDateTime 转 秒
     *
     * @author wangsong
     */
    public static Long parseSecond(LocalDateTime time) {
        return time.atZone(ZoneId.systemDefault()).toInstant().getEpochSecond();
    }


    /**
     * String 类型转成 LocalDateTime ,必须为完整时间,如：2020-01-20 00:00:00
     *
     * @param timeStr 时间字符串
     * @return java.time.LocalDateTime
     */
    public static LocalDateTime parse(String timeStr) {
        return parse(timeStr, YYYY_MM_DD_HH_MM_SS);
    }

    /**
     * String (2020-01-20 00:00:00)类型转成 LocalDateTime
     *
     * @param timeStr timeStr
     * @param pattern pattern
     * @return java.time.LocalDateTime
     */
    public static LocalDateTime parse(String timeStr, String pattern) {
        if (pattern.equals(YYYY)) {
            timeStr += "-01-01 00:00:00";
        } else if (pattern.equals(YYYY_MM)) {
            timeStr += "-01 00:00:00";
        } else if (pattern.equals(YYYY_MM_DD)) {
            timeStr += " 00:00:00";
        } else if (pattern.equals(YYYY_MM_DD_HH)) {
            timeStr += ":00:00";
        } else if (pattern.equals(YYYY_MM_DD_HH_MM)) {
            timeStr += ":00";
        }
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(YYYY_MM_DD_HH_MM_SS);
        return LocalDateTime.parse(timeStr, dtf);
    }


    /**
     * LocalDateTime 转完整 String 类型的时间 如：2020-01-20 00:00:00
     *
     * @param time time
     * @return java.lang.String
     */
    public static String parse(LocalDateTime time) {
        return parse(time, YYYY_MM_DD_HH_MM_SS);
    }


    /**
     * LocalDateTime 转指定类型的字符串
     *
     * @return java.lang.String
     * @author wangsong
     */
    public static String parse(LocalDateTime time, String pattern) {
        if (time == null) {
            return null;
        }
        DateTimeFormatter df = DateTimeFormatter.ofPattern(pattern);
        return df.format(time);
    }


    /**
     * Date 转指定格式的字符串
     *
     * @param time
     * @author wangsong
     */
    public static String parse(Date time, String pattern) {
        if (time == null) {
            return null;
        }
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        return format.format(time);
    }


    /**
     * 将时间戳转为当前时间
     *
     * @param timestamp
     * @return java.lang.String
     * @author wangsong
     * @date 2021/5/12 0012 17:13
     * @version 1.0.1
     */
    public static LocalDateTime parseTimestamp(Long timestamp) {
        return LocalDateTime.ofEpochSecond(timestamp / 1000, 0, ZoneOffset.ofHours(8));
    }

    /**
     * 将时间转为时间戳
     *
     * @return java.lang.String
     * @author wangsong
     * @date 2021/5/12 0012 17:13
     * @version 1.0.1
     */
    public static Long parseTimestamp(LocalDateTime time) {
        return time.toEpochSecond(ZoneOffset.ofHours(8));
    }


    /**
     * 获取指定天的24小时  |  yyyy-MM-dd HH 格式
     *
     * @param t 开始月
     * @return
     */
    public static List<String> getDay24Hour(LocalDateTime t) {
        if (t == null) {
            return new ArrayList<>();
        }
        List<String> times = new ArrayList<>();
        String time = parse(t, YYYY_MM_DD);
        int hourNum = 24;
        for (int i = 0; i < hourNum; i++) {
            if (i < 10) {
                times.add(time + " 0" + i);
            } else {
                times.add(time + " " + i);
            }
        }
        return times;
    }


    /**
     * 获取 n月前的第一天 到 n月后的最后一天的所有时间
     * <P>  一天一条数据 List<DateDays>  </P>
     *
     * @param startNum 前n月，当前月开始为0
     * @param entNum   后n月，当前月就是为0
     * @return java.util.List<com.lplb.common.utils.LocalDateTimeUtil.DateDays>
     * @author wangsong
     */
    public static List<DateDays> getDateDaysUpList(Integer startNum, Integer entNum) {
        // 本月第一天  00:00:00
        LocalDateTime startTime = monthFirst(startNum);
        // n月后的最后一天 23:59:59.999
        LocalDateTime endTime = monthLast(entNum);
        // 3个月的数据
        List<DateDays> everyDays = new ArrayList<>();
        // 第一天数据
        everyDays.add(new DateDays(startTime, week(startTime)));
        while (true) {
            // 获取一天后时间
            LocalDateTime nextDay = plus(everyDays.get(everyDays.size() - 1).dayTime, 1, ChronoUnit.DAYS);
            //大于两月后最后一天-跳出循环
            if (isAfter(nextDay, endTime)) {
                break;
            }
            everyDays.add(new DateDays(nextDay, week(nextDay)));
        }
        return everyDays;
    }


    /**
     * 获取月( 包含开始时间和结束时间的月，返回每一个月的字串， yyyy-MM 格式)
     * <p> 包含开始月，不包含结束月 </>
     *
     * @param startTime 开始月
     * @param endTime   结束月
     * @return
     */
    public static List<String> getMonths(LocalDateTime startTime, LocalDateTime endTime) {
        List<String> times = new ArrayList<>();
        if (startTime != null && endTime != null) {
            endTime = getMonthOfFirst(endTime);
            while (isBefore(startTime, endTime)) {
                LocalDateTime time = plus(startTime, 1, ChronoUnit.MONTHS);
                if (time != null) {
                    startTime = time;
                    times.add(parse(startTime, YYYY_MM));
                } else {
                    break;
                }
            }
        }
        return times;
    }


    /**
     * 获取指定开始时间到指定结束时间的每一天, 包括开始时间，不包括结束时间，如：2020-5-16到2020-5-18 获得时间为：[2020-5-16,2020-5-17]
     *
     * @param startTime
     * @param endTime
     * @param type      1-包含开始和结束时间  2-包含结束-不包含开始时间  3-包含开始-不包含结束时间  4-不包含开始和结束时间
     * @return java.util.List<java.time.LocalDateTime>
     * @author wangsong
     * @date 2020/12/24 0024 15:16
     * @version 1.0.1
     */
    public static List<LocalDateTime> getBetweenList(LocalDateTime startTime, LocalDateTime endTime, Integer type) {
        // 指定开始时间  00:00:00  // 指定结束时间  00:00:00
        LocalDateTime oldStartTime = getDayStart(startTime);
        LocalDateTime oldEndTime = getDayStart(endTime);
        // 1-包含开始和结束时间(默认) BetweenType
        // 2-包含结束-不包含开始时间   // 开始时间+1天
        // 3-包含开始-不包含结束时间   // 结束时间-1天
        // 4-不包含开始和结束时间 // 开始时间+1天  or 结束时间-1天
        if (type == BETWEEN_TYPE_TWO) {
            oldStartTime = plus(oldStartTime, 1, ChronoUnit.DAYS);
        } else if (type == BETWEEN_TYPE_THREE) {
            oldEndTime = subtract(endTime, 1, ChronoUnit.DAYS);
        } else if (type == BETWEEN_TYPE_FOUR) {
            oldStartTime = plus(oldStartTime, 1, ChronoUnit.DAYS);
            oldEndTime = subtract(endTime, 1, ChronoUnit.DAYS);
        }
        // 3个月的数据
        List<LocalDateTime> everyDays = new ArrayList<>();
        // 第一天数据
        everyDays.add(oldStartTime);
        while (true) {
            // 获取之后的一天后时间
            LocalDateTime nextDay = plus(everyDays.get(everyDays.size() - 1), 1, ChronoUnit.DAYS);
            // 大于两月后最后一天-跳出循环
            if (isAfter(nextDay, oldEndTime)) {
                break;
            }
            everyDays.add(nextDay);
        }
        return everyDays;
    }


    /**
     * 获取日期端的数据保存对象
     *
     * @author ws
     * @mail 1720696548@qq.com
     * @date 2020/5/7 0007 9:41
     */
    public static class DateDays {
        //当天时间- 年月日/00:00:00
        private LocalDateTime dayTime;
        //当天是周几
        private int week;

        public DateDays(LocalDateTime dayTime, int week) {
            this.dayTime = dayTime;
            this.week = week;
        }

        public LocalDateTime getDayTime() {
            return dayTime;
        }

        public void setDayTime(LocalDateTime dayTime) {
            this.dayTime = dayTime;
        }

        public int getWeek() {
            return week;
        }

        public void setWeek(int week) {
            this.week = week;
        }
    }

    /**
     * 测试方法
     *
     * @param args
     * @return void
     * @author ws
     * @mail 1720696548@qq.com
     * @date 2020/4/24 0024 15:54
     */
    public static void main(String[] args) {
        test();
        test1();
        test2();
        test3();
    }

    private static void test() {
        log.debug("当前时间 ==> " + LocalDateTime.now());
        log.debug("当前时间秒数 ==> " + parseSecond(LocalDateTime.now()));
        log.debug("当前时间毫秒数 ==> " + parseMillisecond(LocalDateTime.now()));
        log.debug("今天是几号：" + parseDayInt());
        log.debug("===========================================================");
        log.debug("今天开始时间 ==> " + getDayStart(LocalDateTime.now()));
        log.debug("今天结束时间 ==> " + getDayEnd(LocalDateTime.now()));
        log.debug("构建自定义时间 ==> " + of(2020, 1, 1, 12, 00, 00, 999999999));
        log.debug("指定格式 ==>  " + parse(LocalDateTime.now(), "yyyy-MM-dd HH:mm:ss:SSS"));


    }

    private static void test1() {
        log.debug("=========================" + LocalDateTime.now() + " 之前 ==================================");
        log.debug("10秒前 ==> " + subtract(LocalDateTime.now(), 10, ChronoUnit.SECONDS));
        log.debug("10分前 ==> " + subtract(LocalDateTime.now(), 10, ChronoUnit.MINUTES));
        log.debug("一小时前 ==> " + subtract(LocalDateTime.now(), 1, ChronoUnit.HOURS));
        log.debug("半天前 ==> " + subtract(LocalDateTime.now(), 1, ChronoUnit.HALF_DAYS));
        log.debug("一天前 ==> " + subtract(LocalDateTime.now(), 1, ChronoUnit.DAYS));
        log.debug("一月前 ==> " + subtract(LocalDateTime.now(), 1, ChronoUnit.MONTHS));
        log.debug("一年前 ==> " + subtract(LocalDateTime.now(), 1, ChronoUnit.YEARS));
        log.debug("==========================" + LocalDateTime.now() + " 之后 ===================================");
        log.debug("10秒后 ==> " + plus(LocalDateTime.now(), 10, ChronoUnit.SECONDS));
        log.debug("10分后 ==> " + plus(LocalDateTime.now(), 10, ChronoUnit.MINUTES));
        log.debug("一小时后 ==> " + plus(LocalDateTime.now(), 1, ChronoUnit.HOURS));
        log.debug("半天后 ==> " + plus(LocalDateTime.now(), 1, ChronoUnit.HALF_DAYS));
        log.debug("一天后 ==> " + plus(LocalDateTime.now(), 1, ChronoUnit.DAYS));
        log.debug("一月后 ==> " + plus(LocalDateTime.now(), 1, ChronoUnit.MONTHS));
        log.debug("一年后 ==> " + plus(LocalDateTime.now(), 1, ChronoUnit.YEARS));
        log.debug("================================= 时间差 =====================================");
        //  输出 ====>
        LocalDateTime start = LocalDateTime.of(2019, 10, 13, 11, 11);
        LocalDateTime end = LocalDateTime.of(2020, 11, 13, 13, 13);
        log.debug("输出:" + parse(start, "yyyy-MM-dd HH:mm:ss") + " -到- " + parse(end, "yyyy-MM-dd HH:mm:ss") + "的时间差");
        log.debug("年:" + betweenTwoTime(start, end, ChronoUnit.YEARS));
        log.debug("月:" + betweenTwoTime(start, end, ChronoUnit.MONTHS));
        log.debug("日:" + betweenTwoTime(start, end, ChronoUnit.DAYS));
        log.debug("半日:" + betweenTwoTime(start, end, ChronoUnit.HALF_DAYS));
        log.debug("小时:" + betweenTwoTime(start, end, ChronoUnit.HOURS));
        log.debug("分钟:" + betweenTwoTime(start, end, ChronoUnit.MINUTES));
        log.debug("秒:" + betweenTwoTime(start, end, ChronoUnit.SECONDS));
        log.debug("毫秒:" + betweenTwoTime(start, end, ChronoUnit.MILLIS));
        log.debug("================================== 时间大小 ===================================");
        //t1 < t2 = true
        log.debug("2019-10-13 11:11:00 < 2020-11-13 13:13:00 = " + isBefore(start, end));
        //t1 > t2 = true
        log.debug("2019-10-13 11:11:00 > 2020-11-13 13:13:00 = " + isAfter(start, end));
    }

    private static void test2() {
        log.debug("================================== 周几 ===================================");
        LocalDateTime of1 = LocalDateTime.of(2020, 4, 20, 0, 0);
        LocalDateTime of2 = LocalDateTime.of(2020, 4, 21, 0, 0);
        LocalDateTime of3 = LocalDateTime.of(2020, 4, 22, 0, 0);
        LocalDateTime of4 = LocalDateTime.of(2020, 4, 23, 0, 0);
        LocalDateTime of5 = LocalDateTime.of(2020, 4, 24, 0, 0);
        LocalDateTime of6 = LocalDateTime.of(2020, 4, 25, 0, 0);
        LocalDateTime of7 = LocalDateTime.of(2020, 4, 26, 0, 0);
        log.debug(parse(of1, "yyyy-MM-dd") + " 是周 " + week(of1));
        log.debug(parse(of2, "yyyy-MM-dd") + " 是周 " + week(of2));
        log.debug(parse(of3, "yyyy-MM-dd") + " 是周 " + week(of3));
        log.debug(parse(of4, "yyyy-MM-dd") + " 是周 " + week(of4));
        log.debug(parse(of5, "yyyy-MM-dd") + " 是周 " + week(of5));
        log.debug(parse(of6, "yyyy-MM-dd") + " 是周 " + week(of6));
        log.debug(parse(of7, "yyyy-MM-dd") + " 是周 " + week(of7));
        log.debug("=========================== 周开头和结束 ===============================");
        log.debug(" 上周的周一是 " + weekFirst(-1));
        log.debug(" 上周的周末是 " + weekLast(-1));
        log.debug(" 本周的周一是 " + weekFirst(0));
        log.debug(" 本周的周末是 " + weekLast(0));
        log.debug(" 下周的周一是 " + weekFirst(1));
        log.debug(" 下周的周末是 " + weekLast(1));
        log.debug("=========================== 月开头和结束 ===============================");
        log.debug(" 上月的月初是 " + monthFirst(-1));
        log.debug(" 上月的月位是 " + monthLast(-1));
        log.debug(" 本月的月初是 " + monthFirst(0));
        log.debug(" 本月的月位是 " + monthLast(0));
        log.debug(" 下月的月初是 " + monthFirst(1));
        log.debug(" 下月的月位是 " + monthLast(1));
        log.debug("==================== 当前月1好到下N月底的每天数据 =========================");
        log.debug(" 前N月|后N月 -- 前N月|后N月所有时间 " + LocalDateTimeUtil.getDateDaysUpList(0, 2).toString());
        log.debug(" 前N月|后N月 -- 前N月|后N月所有时间 " + LocalDateTimeUtil.getDateDaysUpList(2, 2).toString());

    }


    private static void test3() {
        log.debug("==================获取指定开始时间和结束时间内的所有时间 ====================");
        LocalDateTime startTime = LocalDateTime.of(2020, 4, 20, 0, 0);
        LocalDateTime endTime = LocalDateTime.of(2020, 4, 25, 0, 0);
        log.debug("1-包含开始和结束时间" + getBetweenList(startTime, endTime, 1));
        log.debug("2-包含结束-不包含开始时间" + getBetweenList(startTime, endTime, 2));
        log.debug("3-包含开始-不包含结束时间" + getBetweenList(startTime, endTime, 3));
        log.debug("4-不包含开始和结束时间" + getBetweenList(startTime, endTime, 4));
        log.debug("================== LocalDateTime与String日期互相转换 ====================");
        log.debug(parse("2020-01-20 17:07:05").toString());
        log.debug(parse(LocalDateTime.now()));
        log.debug("================== 时间特殊格式方法转换 ==================");
        log.debug(parse(LocalDateTime.now(), YYYY));
        log.debug(parse(LocalDateTime.now(), YYYY_MM));
        log.debug(parse(LocalDateTime.now(), YYYY_MM_DD));
        log.debug(parse(LocalDateTime.now(), YYYY_MM_DD_HH));
        log.debug(parse(LocalDateTime.now(), YYYY_MM_DD_HH_MM));
        log.debug(parse(LocalDateTime.now(), YYYY_MM_DD_HH_MM_SS));
        log.debug(parse(LocalDateTime.now(), YYYY_MM_DD_HH_MM_SS_SSS));
        log.debug(parse(new Date(), YYYY_MM_DD));
        log.debug(parse("2020", YYYY).toString());
        log.debug(parse("2020-06", YYYY_MM).toString());
        log.debug(parse("2020-06-06", YYYY_MM_DD).toString());
        log.debug(parse("2020-06-06 00", YYYY_MM_DD_HH).toString());
        log.debug(parse("2020-06-06 00:00", YYYY_MM_DD_HH_MM).toString());
        log.debug(parse(LocalDateTime.now(), YYYY_MM));
        log.debug("==================获取整时时间,舍弃分秒为0 ==================");
        log.debug(parse(getTheHour(LocalDateTime.now())));
        log.debug("==================获取整分时间,舍弃秒为0");
        log.debug(parse(getTheMinute(LocalDateTime.now())));
        log.debug(parse(getTheMinute(LocalDateTime.of(2020, 1, 1, 12, 50))));
        log.debug("==================获取指定时间内的每月");
        log.debug(getMonths(subtract(LocalDateTime.now(), 1, ChronoUnit.YEARS), LocalDateTime.now()).toString());
        log.debug("==================获取指定天的24小时");
        List<String> day24Hour = getDay24Hour(LocalDateTime.now());
        log.debug(day24Hour.toString());
    }
}
