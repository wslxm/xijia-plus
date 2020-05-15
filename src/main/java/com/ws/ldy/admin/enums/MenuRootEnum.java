package com.ws.ldy.admin.enums;import com.baomidou.mybatisplus.annotation.EnumValue;import com.baomidou.mybatisplus.core.enums.IEnum;import com.fasterxml.jackson.annotation.JsonFormat;import io.swagger.annotations.ApiModel;import lombok.Getter;/** * @author ws 商品类型 */@Getter@ApiModel(value = "MenuRootEnum", description = "菜单级别 \n" +        "    menu_root_0(0, \"null\"), //无用字段-保证ordinal与value的顺序\n" +        "    menu_root_1(1, \"系统\"),\n" +        "    menu_root_2(2, \"菜单\"),\n" +        "    menu_root_3(3, \"页面\"),")@JsonFormat(shape = JsonFormat.Shape.OBJECT)  //返回对象public enum MenuRootEnum implements IEnum<Integer> {    menu_root_0(0, "无"), //无用字段-保证ordinal与value的顺序    menu_root_1(1, "系统"),    menu_root_2(2, "菜单"),    menu_root_3(3, "页面"),    ;    /**     * 枚举的第一个参数，数据库字段     */    @EnumValue    int value;    /**     * 枚举的第二个参数     */    String desc;    MenuRootEnum(int value, String desc) {        this.value = value;        this.desc = desc;    }    /**     * TODO 获取数据值-数据库值     */    public Integer getValue() {        return value;    }    /**     * TODO  主要用于展示-->  1、枚举数据在java代码中查看对象值时直接展示的内容， 2、 swagger 注释直接展示的内容     */    @Override    public String toString() {        MenuRootEnum.class.getEnumConstants();        // return this.name() + "[value=" + this.value + ",msg=" + this.desc + "]";        return this.name() + ":{\"value\":" + this.value + ",\"desc\":\"" + this.desc + "\"}";    }}