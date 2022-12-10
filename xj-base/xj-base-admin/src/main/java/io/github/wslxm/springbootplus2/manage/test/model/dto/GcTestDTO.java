package io.github.wslxm.springbootplus2.manage.test.model.dto;


import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import io.github.wslxm.springbootplus2.core.base.model.BaseDto;

/**
 * 代码生成测试表 DTO
 *
 * <p>
 * ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655
 * </p>
 *
 * @author ws
 * @email 1720696548@qq.com
 * @date 2022-09-09 18:26:48
 */
@Data
@ToString(callSuper = true)
public class GcTestDTO extends BaseDto {

    private static final long serialVersionUID = -728163044491923456L;

    /**
     * 名称 (文本)", position = 0)
     * private String name;
     * <p>
     * /**
     * 年龄 (数字)
     */
    private Double age;

    /**
     * 性别 (单选--字典)
     */
    private Integer sex;

    /**
     * 爱好 (多选--字典)
     */
    private String like;

    /**
     * 城市 (下拉选--字典)
     */
    private Integer city;

    /**
     * 禁用 (开关--字典)
     */
    private Integer disable;

    /**
     * 单图文件
     */
    private String headUrl;

    /**
     * 多图文件 (多图-默认限制10张图片, 字符串分割存储)
     */
    private String headFiles;

    /**
     * 视频文件 (视频-默认多上传)
     */
    private String videoFiles;

    /**
     * 任意文件 (任意文件上传-默认多上传)
     */
    private String files;

    /**
     * 时间 (默认 yyyy-MM-dd hh:mm:ss 格式)
     */
    private LocalDateTime time;

    /**
     * 时间-小时 (默认 hh:mm 字串)
     */
    private String timeTwo;

    /**
     * 更多信息-text (大文本)
     */
    private String text;

    /**
     * 更多信息-fwb (富文本)
     */
    private String textTwo;

    /**
     * 更多信息-md (md编辑器)
     */
    private String textThree;

    /**
     * 级联选择器  (字符串分割存储)
     */
    private String cascader;

    /**
     * 数组框 (字符串分割存储)
     */
    private String array;

    /**
     * 图标
     */
    private String icon;

    /**
     * 颜色选择器
     */
    private String color;

    /**
     * 地址选择器
     */
    private String map;

}

