package com.ws.ldy.common.utils;import java.time.LocalDate;import java.time.LocalDateTime;import java.time.Period;import java.time.ZoneId;import java.time.format.DateTimeFormatter;import java.time.temporal.ChronoUnit;import java.time.temporal.TemporalAdjusters;import java.time.temporal.TemporalUnit;import java.util.Date;/** * TODO    时间处理工具类 * * @author ws * @mail 1720696548@qq.com * @date 2020/4/24 0024 9:54 * @return */public class LocalDateTimeUtil {    //获取当前时间的LocalDateTime对象    //LocalDateTime.now();    //根据年月日构建LocalDateTime    //LocalDateTime.of();    //比较日期先后    //LocalDateTime.now().isBefore(),    //LocalDateTime.now().isAfter(),    // TODO  获取当前时间    public static LocalDateTime now() {        return LocalDateTime.now();    }    // TODO  获取指定时间是周几    public static int week(LocalDateTime time) {        return time.getDayOfWeek().getValue();    }    // TODO  获取加或减N月的第一天    public static LocalDateTime monthFirst(int num) {        LocalDateTime newTime = plus(LocalDateTime.now(), num, ChronoUnit.MONTHS);        newTime = newTime.with(TemporalAdjusters.firstDayOfMonth());        return getDayStart(newTime);    }    // TODO  获取加或减N月的最后天    public static LocalDateTime monthLast(int num) {        LocalDateTime newTime = plus(LocalDateTime.now(), num, ChronoUnit.MONTHS);        newTime = newTime.with(TemporalAdjusters.lastDayOfMonth());        return getDayEnd(newTime);    }    // TODO 获取加或减N周的第一天    public static LocalDateTime weekFirst(int num) {        int week = week(LocalDateTime.now());        LocalDateTime newTime = subtract(LocalDateTime.now(), week - 1, ChronoUnit.DAYS);        newTime = plus(newTime, num * 7, ChronoUnit.DAYS);        //formatTime(, "yyyy-MM-dd HH:mm:ss:SSS");        return getDayStart(newTime);    }    // TODO  获取加或减N周的最后一天    public static LocalDateTime weekLast(int num) {        int week = week(LocalDateTime.now());        LocalDateTime newTime = plus(LocalDateTime.now(), 7 - week, ChronoUnit.DAYS);        newTime = plus(newTime, num * 7, ChronoUnit.DAYS);        return getDayEnd(newTime);    }    // TODO  判断时间 ==>  t1 < t2 = true （2019-10-13 11:11:00 < 2020-11-13 13:13:00 = true）    public static boolean isBefore(LocalDateTime t1, LocalDateTime t2) {        return t1.isBefore(t2);    }    // TODO  判断时间 ==>  t1 > t2 = true（2019-10-13 11:11:00 > 2020-11-13 13:13:00 = false）    public static boolean isAfter(LocalDateTime t1, LocalDateTime t2) {        return t1.isAfter(t2);    }    // TODO  自构建 LocalDateTime ==> 年，月，日，时，分    public static LocalDateTime of(int year, int month, int dayOfMonth, int hour, int minute) {        return LocalDateTime.of(year, month, dayOfMonth, hour, minute);    }    // TODO  自构建 LocalDateTime ==> 年，月，日，时，分，秒，毫秒（精确到9位数）    public static LocalDateTime of(int year, int month, int dayOfMonth, int hour, int minute, int second, int nanoOfSecond) {        return LocalDateTime.of(year, month, dayOfMonth, hour, minute, second, nanoOfSecond);    }    // TODO  Date 转 LocalDateTime    public static LocalDateTime convertDateToLDT(Date date) {        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());    }    // TODO  LocalDateTime 转 Date    public static Date convertLDTToDate(LocalDateTime time) {        return Date.from(time.atZone(ZoneId.systemDefault()).toInstant());    }    // TODO  获取指定日期的毫秒    public static Long getMilliByTime(LocalDateTime time) {        return time.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();    }    // TODO  获取指定日期的秒    public static Long getSecondsByTime(LocalDateTime time) {        return time.atZone(ZoneId.systemDefault()).toInstant().getEpochSecond();    }    // TODO 获取指定时间的指定格式 ==> yyyy-MM-dd HH:mm:ss:SSS  (HH是24小时制，而hh是12小时制, ss是秒，SSS是毫秒)    public static String formatTime(LocalDateTime time, String pattern) {        return time.format(DateTimeFormatter.ofPattern(pattern));    }    // TODO 日期加上一个数,根据field不同加不同值,field为ChronoUnit.*    public static LocalDateTime plus(LocalDateTime time, long number, TemporalUnit field) {        return time.plus(number, field);    }    // TODO 日期减去一个数,根据field不同减不同值,field参数为ChronoUnit.*    public static LocalDateTime subtract(LocalDateTime time, long number, TemporalUnit field) {        return time.minus(number, field);    }    /**     * TODO 获取两个日期的差  field参数为ChronoUnit.*     *     * @param startTime     * @param endTime     * @param field     单位(年月日时分秒)     **/    public static long betweenTwoTime(LocalDateTime startTime, LocalDateTime endTime, ChronoUnit field) {        Period period = Period.between(LocalDate.from(startTime), LocalDate.from(endTime));        if (field == ChronoUnit.YEARS) {            return period.getYears();        }        if (field == ChronoUnit.MONTHS) {            return period.getYears() * 12 + period.getMonths();        }        return field.between(startTime, endTime);    }    // TODO  获取指定某一天的开始时间 00:00:00    public static LocalDateTime getDayStart(LocalDateTime time) {        return time.withHour(0)                .withMinute(0)                .withSecond(0)                .withNano(0);    }    // TODO 获取指定某一天的结束时间  23:59:59.999    public static LocalDateTime getDayEnd(LocalDateTime time) {        return time                //.withDayOfMonth(1)    // 月                //.withDayOfYear(2)     // 天                .withHour(23)           // 时                .withMinute(59)         // 分                .withSecond(59)         // 秒                .withNano(999999999);   // 毫秒（这里精确到9位数）    }    /**     * TODO  测试方法     *     * @param args     * @return void     * @author ws     * @mail 1720696548@qq.com     * @date 2020/4/24 0024 15:54     */    public static void main(String[] args) {        System.out.println("当前时间 ==> " + LocalDateTime.now());        System.out.println("当前时间秒数 ==> " + getMilliByTime(LocalDateTime.now()));        System.out.println("当前时间毫秒数 ==> " + getSecondsByTime(LocalDateTime.now()));        System.out.println("===========================================================");        System.out.println("今天开始时间 ==> " + getDayStart(LocalDateTime.now()));        System.out.println("今天结束时间 ==> " + getDayEnd(LocalDateTime.now()));        System.out.println("构建自定义时间 ==> " + of(2020, 1, 1, 12, 00, 00, 999999999));        System.out.println("指定格式 ==>  " + formatTime(LocalDateTime.now(), "yyyy-MM-dd HH:mm:ss:SSS"));        System.out.println("=========================" + LocalDateTime.now() + " 之前 ==================================");        System.out.println("10秒前 ==> " + subtract(LocalDateTime.now(), 10, ChronoUnit.SECONDS));        System.out.println("10分前 ==> " + subtract(LocalDateTime.now(), 10, ChronoUnit.MINUTES));        System.out.println("一小时前 ==> " + subtract(LocalDateTime.now(), 1, ChronoUnit.HOURS));        System.out.println("半天前 ==> " + subtract(LocalDateTime.now(), 1, ChronoUnit.HALF_DAYS));        System.out.println("一天前 ==> " + subtract(LocalDateTime.now(), 1, ChronoUnit.DAYS));        System.out.println("一月前 ==> " + subtract(LocalDateTime.now(), 1, ChronoUnit.MONTHS));        System.out.println("一年前 ==> " + subtract(LocalDateTime.now(), 1, ChronoUnit.YEARS));        System.out.println("==========================" + LocalDateTime.now() + " 之后 ===================================");        System.out.println("10秒后 ==> " + plus(LocalDateTime.now(), 10, ChronoUnit.SECONDS));        System.out.println("10分后 ==> " + plus(LocalDateTime.now(), 10, ChronoUnit.MINUTES));        System.out.println("一小时后 ==> " + plus(LocalDateTime.now(), 1, ChronoUnit.HOURS));        System.out.println("半天后 ==> " + plus(LocalDateTime.now(), 1, ChronoUnit.HALF_DAYS));        System.out.println("一天后 ==> " + plus(LocalDateTime.now(), 1, ChronoUnit.DAYS));        System.out.println("一月后 ==> " + plus(LocalDateTime.now(), 1, ChronoUnit.MONTHS));        System.out.println("一年后 ==> " + plus(LocalDateTime.now(), 1, ChronoUnit.YEARS));        System.out.println("================================= 时间差 =====================================");        //  输出 ====>        LocalDateTime start = LocalDateTime.of(2019, 10, 13, 11, 11);        LocalDateTime end = LocalDateTime.of(2020, 11, 13, 13, 13);        System.out.println("输出:" + formatTime(start, "yyyy-MM-dd HH:mm:ss") + " -到- " + formatTime(end, "yyyy-MM-dd HH:mm:ss") + "的时间差");                                     //====> 年:1        System.out.println("年:" + betweenTwoTime(start, end, ChronoUnit.YEARS));      // ====> 年:1        System.out.println("月:" + betweenTwoTime(start, end, ChronoUnit.MONTHS));     // ====> 月:13        System.out.println("日:" + betweenTwoTime(start, end, ChronoUnit.DAYS));       // ====> 日:396        System.out.println("半日:" + betweenTwoTime(start, end, ChronoUnit.HALF_DAYS));// ====> 半日:792        System.out.println("小时:" + betweenTwoTime(start, end, ChronoUnit.HOURS));    // ====> 小时:9506        System.out.println("分钟:" + betweenTwoTime(start, end, ChronoUnit.MINUTES));  // ====> 分钟:570362        System.out.println("秒:" + betweenTwoTime(start, end, ChronoUnit.SECONDS));    // ====> 秒:34221720        System.out.println("毫秒:" + betweenTwoTime(start, end, ChronoUnit.MILLIS));   // ====> 毫秒:34221720000        System.out.println("================================== 时间大小 ===================================");        System.out.println("2019-10-13 11:11:00 < 2020-11-13 13:13:00 = " + isBefore(start, end)); //t1 < t2 = true        System.out.println("2019-10-13 11:11:00 > 2020-11-13 13:13:00 = " + isAfter(start, end));  //t1 > t2 = true        System.out.println("================================== 周几 ===================================");        LocalDateTime of1 = LocalDateTime.of(2020, 4, 20, 0, 0);        LocalDateTime of2 = LocalDateTime.of(2020, 4, 21, 0, 0);        LocalDateTime of3 = LocalDateTime.of(2020, 4, 22, 0, 0);        LocalDateTime of4 = LocalDateTime.of(2020, 4, 23, 0, 0);        LocalDateTime of5 = LocalDateTime.of(2020, 4, 24, 0, 0);        LocalDateTime of6 = LocalDateTime.of(2020, 4, 25, 0, 0);        LocalDateTime of7 = LocalDateTime.of(2020, 4, 26, 0, 0);        System.out.println(formatTime(of1, "yyyy-MM-dd") + " 是周 " + week(of1));        System.out.println(formatTime(of2, "yyyy-MM-dd") + " 是周 " + week(of2));        System.out.println(formatTime(of3, "yyyy-MM-dd") + " 是周 " + week(of3));        System.out.println(formatTime(of4, "yyyy-MM-dd") + " 是周 " + week(of4));        System.out.println(formatTime(of5, "yyyy-MM-dd") + " 是周 " + week(of5));        System.out.println(formatTime(of6, "yyyy-MM-dd") + " 是周 " + week(of6));        System.out.println(formatTime(of7, "yyyy-MM-dd") + " 是周 " + week(of7));        System.out.println("=========================== 周开头和结束 ===============================");        System.out.println(" 上周的周一是 " + weekFirst(-1));        System.out.println(" 上周的周末是 " + weekLast(-1));        System.out.println(" 本周的周一是 " + weekFirst(0));        System.out.println(" 本周的周末是 " + weekLast(0));        System.out.println(" 下周的周一是 " + weekFirst(1));        System.out.println(" 下周的周末是 " + weekLast(1));        System.out.println("=========================== 月开头和结束 ===============================");        System.out.println(" 上月的月初是 " + monthFirst(-1));        System.out.println(" 上月的月位是 " + monthLast(-1));        System.out.println(" 本月的月初是 " + monthFirst(0));        System.out.println(" 本月的月位是 " + monthLast(0));        System.out.println(" 下月的月初是 " + monthFirst(1));        System.out.println(" 下月的月位是 " + monthLast(1));    }}