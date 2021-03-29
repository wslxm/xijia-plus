package com.ws.ldy.common.utils;import com.baomidou.mybatisplus.core.enums.IEnum;/** *  通过枚举的key/code/value 值来获取枚举对象 * * @author 王松 * @mail 1720696548@qq.com * @date 2020/6/20 0020 17:19 */public class EnumUtil {    /**     * 通过code来获得枚举,T代表枚举,书写的公共类  <T extends IEnum>这表达类，表示不确定的东西，像Interface/class     *     * @param code      key/code/value 值     * @param enumClass 枚举对象     * @param <T>       枚举对象的具体值     * @return     */    public static <T extends IEnum<Integer>> T getByCode(Integer code, Class<T> enumClass) {        if (enumClass == null) {            return null;        }        for (T t : enumClass.getEnumConstants()) {            if (code.equals(t.getValue())) {                return t;            }        }        return null;    }    /**     * 测试代码     * <P>     * Base.Disable byCode = EnumUtil.getByCode(1, Base.Disable.class);     * log.debug(byCode.getValue() + ":" + byCode.getDesc());     * </P>     * @author ws     * @mail 1720696548@qq.com     * @date 2020/4/26 0026 17:18     */    public static void main(String[] args) {    }}