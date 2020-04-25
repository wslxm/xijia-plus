package com.ws.ldy.common.utils;import java.math.BigDecimal;import java.math.MathContext;import java.math.RoundingMode;/** * TODO    BigDecimalUtil 小数处理工具类, 所有方法默认四舍五入, * <p> * BigDecimalUtil.MONEY_POINT  小数位数默认保留2，暂不支持自定义 * <p> * BigDecimalUtil.mc      ==> 四舍五入 * BigDecimalUtil.mcDown  ==> 舍弃 ===> 直接舍弃指定位数后的小数，如：1.9 = 1 * BigDecimalUtil.mcUp    ==> 上升 ===> 舍弃指定位数后的小数,舍弃后小数的最后一位只要大于0就加1 --> 如: 1.1 = 2 * * @author ws * @mail 1720696548@qq.com * @date 2020/4/24 0024 9:51 * @return */public class BigDecimalUtil {    /**     * TODO  货币保留两位小数     */    public static final int MONEY_POINT = 2;    // 四舍五入( HALF_UP 5向上取，HALF_DOWN 5向下取)    public static MathContext mc = new MathContext(MONEY_POINT + 1, RoundingMode.HALF_UP);    // 舍弃 ===> 直接舍弃指定位数后的小数（FLOOR = 地板）    public static MathContext mcDown = new MathContext(MONEY_POINT + 1, RoundingMode.FLOOR);    // 上升 ===> 舍弃指定位数后的小数, 舍弃后小数的最后一位只要 >0 就加1  （FLOOR = 天花板）    public static MathContext mcUp = new MathContext(MONEY_POINT + 1, RoundingMode.CEILING);    /**     * TODO BigDecimal 相加     */    public static BigDecimal add(BigDecimal v1, BigDecimal v2) {        return add(v1, v2, BigDecimalUtil.mc);    }    /**     * TODO BigDecimal 相减     */    public static BigDecimal subtract(BigDecimal v1, BigDecimal v2) {        return add(v1, v2, BigDecimalUtil.mc);    }    /**     * TODO BigDecimal 相乘     */    public static BigDecimal multiply(BigDecimal v1, BigDecimal v2) {        return v1.multiply(v2, BigDecimalUtil.mc);    }    /**     * TODO BigDecimal 相除     */    public static BigDecimal divide(BigDecimal v1, BigDecimal v2) {        return v1.divide(v2, BigDecimalUtil.mc);    }    //==========================================================================    //============================== 具体方法  ===================================    //==========================================================================    // TODO BigDecimal 相加    public static BigDecimal add(BigDecimal v1, BigDecimal v2, MathContext mc) {        if (v1 == null) {            v1 = new BigDecimal("0");        }        if (v2 == null) {            v2 = new BigDecimal("0");        }        return v1.add(v2, mc);    }    // TODO BigDecimal 相减    public static BigDecimal subtract(BigDecimal v1, BigDecimal v2, MathContext mc) {        if (v1 == null) {            v1 = new BigDecimal("0");        }        if (v2 == null) {            v2 = new BigDecimal("0");        }        return v1.subtract(v2, mc);    }    // TODO BigDecimal 相乘    public static BigDecimal multiply(BigDecimal v1, BigDecimal v2, MathContext mc) {        if (v1 == null) {            v1 = new BigDecimal("0");        }        if (v2 == null) {            v2 = new BigDecimal("0");        }        return v1.multiply(v2, mc);    }    // TODO BigDecimal 相除    public static BigDecimal divide(BigDecimal v1, BigDecimal v2, MathContext mc) {        if (v1 == null) {            v1 = new BigDecimal("0");        }        if (v2 == null) {            v2 = new BigDecimal("0");        }        return v1.divide(v2, mc);    }    /**     * TODO  测试方法     *     * @author ws     * @mail 1720696548@qq.com     * @date 2020/4/24 0024 17:17     */    public static void main(String[] args) {        System.out.println("四舍五入 1.50 + 1.504 = " + add(new BigDecimal("1.50"), new BigDecimal("1.504")));        System.out.println("四舍五入 1.50 + 1.505 = " + add(new BigDecimal("1.50"), new BigDecimal("1.505")));        System.out.println("舍弃 1.50 + 1.509 =" + add(new BigDecimal("1.50"), new BigDecimal("1.509"),BigDecimalUtil.mcDown));        System.out.println("舍弃 1.50 + 1.501 =" + add(new BigDecimal("1.50"), new BigDecimal("1.501"),BigDecimalUtil.mcDown));        System.out.println("上升 1.50 + 1.509 =" + add(new BigDecimal("1.50"), new BigDecimal("1.509"),BigDecimalUtil.mcUp));        System.out.println("上升 1.50 + 1.501 =" + add(new BigDecimal("1.50"), new BigDecimal("1.501"),BigDecimalUtil.mcUp));        //   add(加)subtract（减） multiply（乘） divide（除）    }    //    /**//     * TODO 格式化精度//     *//     * @param v//     * @param point 小数位数//     * @return double//     *///    public static BigDecimal format(double v, int point) {//        BigDecimal b = new BigDecimal(v);//        return b;//        // return b.setScale(point, BigDecimal.ROUND_HALF_UP).doubleValue();//    }//    /**//     * TODO//     *//     * @param v//     * @param point//     * @return//     *///    public static Double formatRoundUp(double v, int point) {//        NumberFormat nf = NumberFormat.getInstance();//        nf.setRoundingMode(RoundingMode.HALF_UP);//设置四舍五入//        nf.setMinimumFractionDigits(point);//设置最小保留几位小数//        nf.setMaximumFractionDigits(point);//设置最大保留几位小数//        return Double.valueOf(nf.format(v));//    }//    /**//     * TODO 格式化金额。带千位符//     *//     * @param v//     * @return//     *///    public static String moneyFormat(Double v) {//        DecimalFormat formater = new DecimalFormat();//        formater.setMaximumFractionDigits(2);//        formater.setGroupingSize(3);//        formater.setRoundingMode(RoundingMode.FLOOR);//        return formater.format(v.doubleValue());//    }////    /**//     * TODO 带小数的显示小数。不带小数的显示整数//     *//     * @param d//     * @return//     *///    public static String doubleTrans(Double d) {//        if (Math.round(d) - d == 0) {//            return String.valueOf((long) d.doubleValue());//        }//        return String.valueOf(d);//    }}