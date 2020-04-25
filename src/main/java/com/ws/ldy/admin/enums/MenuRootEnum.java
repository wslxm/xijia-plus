package com.ws.ldy.admin.enums;import com.alibaba.fastjson.JSON;import com.baomidou.mybatisplus.annotation.EnumValue;import com.baomidou.mybatisplus.core.enums.IEnum;import com.fasterxml.jackson.annotation.JsonIgnore;import com.fasterxml.jackson.annotation.JsonValue;import io.swagger.annotations.ApiModel;import lombok.Getter;/** * @author ws 商品类型 */@Getter@ApiModel(value = "MenuRootEnum", description = "菜单级别")public enum MenuRootEnum implements IEnum<Integer> {    menu_root_0(0, "null"), //无用字段-保证ordinal与value的顺序    menu_root_1(1, "系统"),    menu_root_2(2, "菜单"),    menu_root_3(3, "页面"),    ;    /**     * 枚举的第一个参数，数据库字段     */    @EnumValue    int value;    /**     * 枚举的第二个参数     */    String desc;    MenuRootEnum(int value, String desc) {        this.value = value;        this.desc = desc;    }    /**     * TODO 获取数据值-数据库值     */    public Integer getValue() {        return value;    }    /**     * TODO  主要用于展示-->  1、枚举数据在java代码中查看对象值时直接展示的内容， 2、 swagger 注释直接展示的内容     */    @Override    public String toString() {       // return this.name() + "[value=" + this.value + ",msg=" + this.desc + "]";       return this.name() + ":{\"value\":" + this.value + ",\"desc\":\"" + this.desc + "\"}";    }    /**     * TODO 自定义返回前端数据--> json数据-- {"value":2,"desc":"菜单"}     *     * @JsonValue 作用： 将该返回的参数序列化 --> 枚举返回参数     * @JsonIgnore 作用：在实体类向前台返回数据时用来忽略不想传递给前台的属性或接口, 因此用来过滤，使前端传递参数的时候就不会使用该方法进行反序列化枚举对象了     */    @JsonValue(value = true)    @JsonIgnore    public JSON respEnum() {        return JSON.parseObject("{\"value\":" + this.value + ",\"desc\":\"" + this.desc + "\"}");    }}