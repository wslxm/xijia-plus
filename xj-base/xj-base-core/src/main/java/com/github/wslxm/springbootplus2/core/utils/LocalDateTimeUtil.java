package com.github.wslxm.springbootplus2.core.utils;

import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
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
 *  时间处理工具类
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
    private static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    private static final String YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";
    private static final String YYYY_MM_DD_HH = "yyyy-MM-dd HH";
    private static final String YYYY_MM_DD = "yyyy-MM-dd";
    private static final String YYYY_MM = "yyyy-MM";
    private static final String YYYY = "yyyy";
    /**
     * 时间转换方法
     */
    private static final DateTimeFormatter DF_YYYY_MM_DD_HH_MM_SS = DateTimeFormatter.ofPattern(YYYY_MM_DD_HH_MM_SS);
    private static final DateTimeFormatter DF_YYYY_MM_DD_HH_MM = DateTimeFormatter.ofPattern(YYYY_MM_DD_HH_MM);
    private static final DateTimeFormatter DF_YYYY_MM_DD_HH = DateTimeFormatter.ofPattern(YYYY_MM_DD_HH);
    private static final DateTimeFormatter DF_YYYY_MM_DD = DateTimeFormatter.ofPattern(YYYY_MM_DD);
    private static final DateTimeFormatter DF_YYYY_MM = DateTimeFormatter.ofPattern(YYYY_MM);
    private static final DateTimeFormatter DF_YYYY = DateTimeFormatter.ofPattern(YYYY);


    /**
     *  判断时间 小于
     *  <P>   t1 < t2 = true （如：2019-10-13 11:11:00 < 2020-11-13 13:13:00 = true）  </P>
     *  @author wangsong
     */
    public static boolean isBefore(LocalDateTime t1, LocalDateTime t2) {
        return t1.isBefore(t2);
    }

    /**
     *  判断时间 大于
     *  <P>   t1 > t2 = true  </P>
     *  @author wangsong
     */
    public static boolean isAfter(LocalDateTime t1, LocalDateTime t2) {
        return t1.isAfter(t2);
    }


    /**
     *  自构建 LocalDateTime ==> 年，月，日，时，分
     *  @author wangsong
     */
    public static LocalDateTime of(int year, int month, int dayOfMonth, int hour, int minute) {
        return LocalDateTime.of(year, month, dayOfMonth, hour, minute);
    }

    /**
     *  自构建 LocalDateTime ==> 年，月，日，时，分，秒，毫秒（精确到9位数）
     *  @author wangsong
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
     *  获取当前时间
     *  @author wangsong
     */
    public static LocalDateTime now() {
        return LocalDateTime.now();
    }

    /**
     *  获取指定时间是周几  1到7
     *  @author wangsong
     */
    public static int week(LocalDateTime time) {
        return time.getDayOfWeek().getValue();
    }


    /**
     *  获取加或减N月的第一天 00:00:00
     *  @author wangsong
     */
    public static LocalDateTime monthFirst(int num) {
        LocalDateTime newTime = plus(LocalDateTime.now(), num, ChronoUnit.MONTHS);
        newTime = newTime.with(TemporalAdjusters.firstDayOfMonth());
        return getDayStart(newTime);
    }

    /**
     *  获取加或减N月的最后一天 23:59:59:99999999
     *  @author wangsong
     */
    public static LocalDateTime monthLast(int num) {
        LocalDateTime newTime = plus(LocalDateTime.now(), num, ChronoUnit.MONTHS);
        newTime = newTime.with(TemporalAdjusters.lastDayOfMonth());
        return getDayEnd(newTime);
    }


    /**
     * 获取指定时间月第一天
     * @author wangsong
     */
    public static LocalDateTime getMonthOfFirst(LocalDateTime time) {
        LocalDateTime firstday = time.with(TemporalAdjusters.firstDayOfMonth());
        return LocalDateTime.of(firstday.toLocalDate(), LocalTime.MIN);
    }

    /**
     * 获取指定时间月最后一天
     * @author wangsong
     */
    public static LocalDateTime getMonthOfLast(LocalDateTime time) {
        LocalDateTime lastDay = time.with(TemporalAdjusters.lastDayOfMonth());
        return LocalDateTime.of(lastDay.toLocalDate(), LocalTime.MAX);
    }


    /**
     *  获取指定月 加或减N月的第一天 00:00:00
     *  @author wangsong
     */
    public static LocalDateTime getMonthOfFirst(LocalDateTime time, int num) {
        LocalDateTime newTime = plus(time, num, ChronoUnit.MONTHS);
        newTime = newTime.with(TemporalAdjusters.firstDayOfMonth());
        return getDayStart(newTime);
    }


    /**
     *  获取指定月 加或减N月的最后一天  23:59:59:99999999
     *  @author wangsong
     */
    public static LocalDateTime getMonthOfLast(LocalDateTime time, int num) {
        LocalDateTime newTime = plus(time, num, ChronoUnit.MONTHS);
        newTime = newTime.with(TemporalAdjusters.lastDayOfMonth());
        return getDayEnd(newTime);
    }


    /**
     *  获取加或减N周的第一天 00:00:00
     *  @author wangsong
     */
    public static LocalDateTime weekFirst(int num) {
        int week = week(LocalDateTime.now());
        LocalDateTime newTime = subtract(LocalDateTime.now(), week - 1L, ChronoUnit.DAYS);
        newTime = plus(newTime, num * 7L, ChronoUnit.DAYS);
        return getDayStart(newTime);
    }

    /**
     *  获取加或减N周的最后一天  23:59:59:99999999
     *  @author wangsong
     */
    public static LocalDateTime weekLast(int num) {
        int week = week(LocalDateTime.now());
        LocalDateTime newTime = plus(LocalDateTime.now(), 7L - week, ChronoUnit.DAYS);
        newTime = plus(newTime, num * 7L, ChronoUnit.DAYS);
        return getDayEnd(newTime);
    }


    /**
     *  获取指定年的第一天 00:00:00
     *  @author wangsong
     */
    public static LocalDateTime yearFirst(LocalDateTime time) {
        int year = time.getYear();
        return LocalDateTime.of(
                year,           // 年
                1,       // 月
                1,  // 天
                0,        // 时
                0,      // 分
                0,      // 秒
                0);   // 毫秒（这里精确到9位数）
    }

    /**
     *  获取指定年的最后一天  23:59:59:99999999
     *  @author wangsong
     */
    public static LocalDateTime yearLast(LocalDateTime time) {
        int year = time.getYear();
        return LocalDateTime.of(
                year,           // 年
                12,       // 月
                31,  // 天
                23,        // 时
                59,      // 分
                59,      // 秒
                999999);   // 毫秒（这里精确到6位数）
    }


    /**
     *  获取指定时间之后的日期
     *  <P>  根据field不同加不同值 , field为ChronoUnit.*
     *  秒   ChronoUnit.SECONDS
     *  分   ChronoUnit.MINUTES
     *  时   ChronoUnit.HOURS
     *  半天  ChronoUnit.HALF_DAYS
     *  天    ChronoUnit.DAYS
     *  月    ChronoUnit.MONTHS
     *  年    ChronoUnit.YEARS
     *  </P>
     * @author wangsong
     */
    public static LocalDateTime plus(LocalDateTime time, long number, TemporalUnit field) {
        return time.plus(number, field);
    }


    /**
     * 获取指定时间之前的日期
     * @author wangsong
     *  <P> 根据field不同减不同值 , field为ChronoUnit.*
     *  秒   ChronoUnit.SECONDS
     *  分   ChronoUnit.MINUTES
     *  时   ChronoUnit.HOURS
     *  半天  ChronoUnit.HALF_DAYS
     *  天    ChronoUnit.DAYS
     *  月    ChronoUnit.MONTHS
     *  年    ChronoUnit.YEARS
     *  </P>
     * @version 1.0.0
     */
    public static LocalDateTime subtract(LocalDateTime time, long number, TemporalUnit field) {
        return time.minus(number, field);
    }


    /**
     * 获取两个日期的差
     * @author wangsong
     *  <P>
     *  秒   ChronoUnit.SECONDS
     *  分   ChronoUnit.MINUTES
     *  时   ChronoUnit.HOURS
     *  半天  ChronoUnit.HALF_DAYS
     *  天    ChronoUnit.DAYS
     *  月    ChronoUnit.MONTHS
     *  年    ChronoUnit.YEARS
     *  </P>
     *  @param startTime 开始时间
     *  @param endTime   计算时间
     *  @param field 根据field不同减不同值 , field为ChronoUnit.*
     *  @return startTime小 endTime大 返回正数，则反之
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
     * @author wangsong
     * @param time
     * @date 2020/12/24 0024 15:10
     * @return java.time.LocalDateTime
     * @version 1.0.0
     */
    public static LocalDateTime getDayStart(LocalDateTime time) {
        return time.withHour(0)
                .withMinute(0)
                .withSecond(0)
                .withNano(0);
    }


    /**
     * 获取指定某一天的结束时间  23:59:59.999
     * @author wangsong
     */
    public static LocalDateTime getDayEnd(LocalDateTime time) {
        return time
                // .withDayOfMonth(1)    // 月
                // .withDayOfYear(2)     // 天
                .withHour(23)            // 时
                .withMinute(59)          // 分
                .withSecond(59)          // 秒
                .withNano(999999);    // 毫秒（这里精确到6位数）
    }


    /**
     * 获取指定时间的周一
     * @author wangsong
     */
    public static LocalDateTime getWeekOfFirst(LocalDateTime time) {
        return time.with(TemporalAdjusters.previous(DayOfWeek.SUNDAY)).
                plusDays(1).withHour(0).withMinute(0).withSecond(0);
    }


    /**
     * 获取指定时间的周日
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
     * @author wangsong
     * @return
     */
    public static synchronized String getTimeStr20() {
        String timeStamp = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
        Random random = new Random();
        timeStamp += (random.nextInt(10) + "") + (random.nextInt(10) + "") + (random.nextInt(10) + "");
        return timeStamp;
    }


    /**
     * 获取整点--  把指定时间的 分+秒设置为0
     * @author wangsong
     * @param time
     * @date 2020/12/24 0024 15:10
     * @return java.time.LocalDateTime
     * @version 1.0.0
     */
    public static LocalDateTime getTheHour(LocalDateTime time) {
        return time.withMinute(0)     // 分
                .withSecond(0)        // 秒
                .withNano(0);         // 毫秒（这里精确到9位数）
    }


    /**
     * 获取整分--  把指定时间的 秒设置为0
     * <P>
     *   如：
     *   2020-01-01 12:10  ===>  等于 2020-01-01 12:20
     *   2020-01-01 12:11  ===>  等于 2020-01-01 12:20
     *   2020-01-01 12:19  ===>  等于 2020-01-01 12:20
     * </P>
     * @author wangsong
     * @param time
     * @date 2020/12/24 0024 15:21
     * @return java.time.LocalDateTime
     * @version 1.0.0
     */
    public static LocalDateTime getTheMinute(LocalDateTime time) {
        return time.withSecond(0)     // 秒
                .withNano(0);         // 毫秒（这里精确到9位数）
    }


    //========================================================================================================
    //========================================================================================================
    //========================================================================================================
    //============================================== 转换相关 =================================================
    //========================================================================================================
    //========================================================================================================

    /**
     * LocalDateTime 转为 天 的字符串，如1号返回 01
     * @author wangsong
     */
    public static Integer parseDayInt() {
        return Integer.parseInt(parse(LocalDateTime.now(), "dd"));
    }


    /**
     *  Date 转 LocalDateTime
     *  @author wangsong
     */
    public static LocalDateTime parseLDT(Date date) {
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }

    /**
     *  LocalDateTime 转 Date
     *  @author wangsong
     */
    public static Date parseDate(LocalDateTime time) {
        return Date.from(time.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     *  LocalDateTime 转 毫秒
     *  @author wangsong
     */
    public static Long parseMillisecond(LocalDateTime time) {
        return time.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    /**
     *  LocalDateTime 转 秒
     *  @author wangsong
     */
    public static Long parseSecond(LocalDateTime time) {
        return time.atZone(ZoneId.systemDefault()).toInstant().getEpochSecond();
    }


    /**
     * String 类型转成 LocalDateTime ,必须为完整时间：2020-01-20 17:07:05
     * @author wangsong
     * @param timeStr
     * @date 2020/12/24 0024 15:21
     * @return java.time.LocalDateTime
     * @version 1.0.0
     */
    public static LocalDateTime parse(String timeStr) {
        return LocalDateTime.parse(timeStr, DF_YYYY_MM_DD_HH_MM_SS);
    }


    /**
     * LocalDateTime 转 字符串
     * <P>  yyyy-MM-dd HH:mm:ss:SSS  (HH是24小时制，而hh是12小时制, ss是秒，SSS是毫秒) </P>
     * @author wangsong
     */
    public static String parse(LocalDateTime time, String pattern) {
        return time.format(DateTimeFormatter.ofPattern(pattern));
    }


    /**
     * LocalDateTime转成String类型的时间
     * @author wangsong
     * @param time
     * @date 2020/12/24 0024 15:25
     * @return java.lang.String
     * @version 1.0.0
     */
    public static String parse(LocalDateTime time) {
        return DF_YYYY_MM_DD_HH_MM_SS.format(time);
    }


    /**
     * LocalDateTime 转字符串 yyyy-MM-dd 格式
     * @author wangsong
     */
    public static String parse_yyyyMMdd(LocalDateTime time) {
        if (time == null) {
            return null;
        }
        DateTimeFormatter df = DateTimeFormatter.ofPattern(YYYY_MM_DD);
        return df.format(time);
    }

    /**
     * LocalDateTime 转字符串 yyyy-MM-dd HH 格式
     * @author wangsong
     */
    public static String parse_yyyyMMddHH(LocalDateTime time) {
        if (time == null) {
            return null;
        }
        DateTimeFormatter df = DateTimeFormatter.ofPattern(YYYY_MM_DD_HH);
        return df.format(time);
    }


    /**
     * LocalDateTime 转字符串 yyyy-MM 格式
     * @author wangsong
     */
    public static String parse_yyyyMM(LocalDateTime time) {
        if (time == null) {
            return null;
        }
        DateTimeFormatter df = DateTimeFormatter.ofPattern(YYYY_MM);
        return df.format(time);
    }


    /**
     * Date 转 yyyy-MM-dd 字符串
     * @author wangsong
     * @param time
     */
    public static String parse_yyyyMMdd(Date time) {
        if (time == null) {
            return null;
        }
        SimpleDateFormat format = new SimpleDateFormat(YYYY_MM_DD);
        return format.format(time);
    }


    /**
     * 将时间戳转为当前时间
     * @author wangsong
     * @param timestamp
     * @date 2021/5/12 0012 17:13
     * @return java.lang.String
     * @version 1.0.0
     */
    public static LocalDateTime parseTimestamp(Long timestamp) {
        return LocalDateTime.ofEpochSecond(timestamp / 1000, 0, ZoneOffset.ofHours(8));
    }

    /**
     * 将时间转为时间戳
     * @author wangsong
     * @date 2021/5/12 0012 17:13
     * @return java.lang.String
     * @version 1.0.0
     */
    public static Long parseTimestamp(LocalDateTime time) {
        return time.toEpochSecond(ZoneOffset.ofHours(8));
    }

    /**
     * yyyy-MM-dd 时间字符串转 LocalDateTime
     * @author wangsong
     */
    public static LocalDateTime parse_yyyyMMdd(String timeStr) {
        SimpleDateFormat df = new SimpleDateFormat(YYYY_MM_DD);
        try {
            Date parse = df.parse(timeStr);
            LocalDateTime localDateTime = parse.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
            return localDateTime;
        } catch (ParseException e) {
            log.debug("error:{}", e);
        }
        return null;
    }


    //========================================================================================================
    //========================================================================================================
    //========================================================================================================
    //====================================== 获取时间段的每一天 =================================================
    //========================================================================================================
    //========================================================================================================


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
        String time = parse_yyyyMMdd(t);
        for (int i = 0; i < 24; i++) {
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
     * @param startNum 前n月，当前月开始为0
     * @param entNum  后n月，当前月就是为0
     * @author wangsong
     * @return java.util.List<com.lplb.common.utils.LocalDateTimeUtil.DateDays>
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
     *  <p> 包含开始月，不包含结束月 </>
     * @param startTime 开始月
     * @param endTime 结束月
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
                    times.add(parse_yyyyMM(startTime));
                } else {
                    break;
                }
            }
        }
        return times;
    }


    /**
     * 获取指定开始时间到指定结束时间的每一天, 包括开始时间，不包括结束时间，如：2020-5-16到2020-5-18 获得时间为：[2020-5-16,2020-5-17]
     * @author wangsong
     * @param startTime
     * @param endTime
     * @param type  1-包含开始和结束时间  2-包含结束-不包含开始时间  3-包含开始-不包含结束时间  4-不包含开始和结束时间
     * @date 2020/12/24 0024 15:16
     * @return java.util.List<java.time.LocalDateTime>
     * @version 1.0.0
     */
    public static List<LocalDateTime> getBetweenList(LocalDateTime startTime, LocalDateTime endTime, Integer type) {
        // 指定开始时间  00:00:00  // 指定结束时间  00:00:00
        LocalDateTime oldStartTime = getDayStart(startTime);
        LocalDateTime oldEndTime = getDayStart(endTime);
        // 1-包含开始和结束时间(默认)
        // 2-包含结束-不包含开始时间   // 开始时间+1天
        // 3-包含开始-不包含结束时间   // 结束时间-1天
        // 4-不包含开始和结束时间 // 开始时间+1天  or 结束时间-1天
        if (type == 2) {
            oldStartTime = plus(oldStartTime, 1, ChronoUnit.DAYS);
        } else if (type == 3) {
            oldEndTime = subtract(endTime, 1, ChronoUnit.DAYS);
        } else if (type == 4) {
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
     *  测试方法
     *
     * @param args
     * @return void
     * @author ws
     * @mail 1720696548@qq.com
     * @date 2020/4/24 0024 15:54
     */
    public static void main(String[] args) {
        log.debug("当前时间 ==> " + LocalDateTime.now());
        log.debug("当前时间秒数 ==> " + parseSecond(LocalDateTime.now()));
        log.debug("当前时间毫秒数 ==> " + parseMillisecond(LocalDateTime.now()));
        log.debug("今天是几号：" + parseDayInt());
        log.debug("===========================================================");


        log.debug("今天开始时间 ==> " + getDayStart(LocalDateTime.now()));
        log.debug("今天结束时间 ==> " + getDayEnd(LocalDateTime.now()));
        log.debug("构建自定义时间 ==> " + of(2020, 1, 1, 12, 00, 00, 999999999));
        log.debug("指定格式 ==>  " + parse(LocalDateTime.now(), "yyyy-MM-dd HH:mm:ss:SSS"));

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
        log.debug("输出:" + parse(start, "yyyy-MM-dd HH:mm:ss") + " -到- " + parse(end, "yyyy-MM-dd HH:mm:ss") + "的时间差");                                     //====> 年:1
        log.debug("年:" + betweenTwoTime(start, end, ChronoUnit.YEARS));      // ====> 年:1
        log.debug("月:" + betweenTwoTime(start, end, ChronoUnit.MONTHS));     // ====> 月:13
        log.debug("日:" + betweenTwoTime(start, end, ChronoUnit.DAYS));       // ====> 日:396
        log.debug("半日:" + betweenTwoTime(start, end, ChronoUnit.HALF_DAYS));// ====> 半日:792
        log.debug("小时:" + betweenTwoTime(start, end, ChronoUnit.HOURS));    // ====> 小时:9506
        log.debug("分钟:" + betweenTwoTime(start, end, ChronoUnit.MINUTES));  // ====> 分钟:570362
        log.debug("秒:" + betweenTwoTime(start, end, ChronoUnit.SECONDS));    // ====> 秒:34221720
        log.debug("毫秒:" + betweenTwoTime(start, end, ChronoUnit.MILLIS));   // ====> 毫秒:34221720000
        log.debug("================================== 时间大小 ===================================");
        log.debug("2019-10-13 11:11:00 < 2020-11-13 13:13:00 = " + isBefore(start, end)); //t1 < t2 = true
        log.debug("2019-10-13 11:11:00 > 2020-11-13 13:13:00 = " + isAfter(start, end));  //t1 > t2 = true

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
        log.debug(parse_yyyyMMddHH(LocalDateTime.now()));
        log.debug(parse_yyyyMMdd(LocalDateTime.now()));
        log.debug(parse_yyyyMMdd(new Date()));
        LocalDateTime dateTime = parse_yyyyMMdd("2020-06-06");
        log.debug(dateTime != null ? dateTime.toString() : "");
        //
        log.debug(parse_yyyyMM(LocalDateTime.now()));


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
