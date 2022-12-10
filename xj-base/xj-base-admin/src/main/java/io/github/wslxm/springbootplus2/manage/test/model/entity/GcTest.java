package io.github.wslxm.springbootplus2.manage.test.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;


import lombok.Data;
import lombok.ToString;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import io.github.wslxm.springbootplus2.core.base.model.BaseEntity;

/**
 * 代码生成测试表 Entity
 *
 * <p>
 * ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655
 * </p>

 * @author ws
 * @email 1720696548@qq.com
 * @date 2022-09-09 18:26:48
 */
@Data
@ToString(callSuper = true)
@TableName("t_gc_test")
public class GcTest extends BaseEntity {

    private static final long serialVersionUID = -728163044278013952L;
    
    /**
 * 名称 (文本)" 
     */
    @TableField(value = "`name`")
    private String name;

    /**
 * 年龄 (数字)" 
     */
    @TableField(value = "age")
    private Double age;

    /**
 * 性别 (单选--字典)" 
     */
    @TableField(value = "sex")
    private Integer sex;

    /**
 * 爱好 (多选--字典)" 
     */
    @TableField(value = "`like`")
    private String like;

    /**
 * 城市 (下拉选--字典)" 
     */
    @TableField(value = "city")
    private Integer city;

    /**
 * 禁用 (开关--字典)" 
     */
    @TableField(value = "`disable`")
    private Integer disable;

    /**
 * 单图文件" 
     */
    @TableField(value = "head_url")
    private String headUrl;

    /**
 * 多图文件 (多图-默认限制10张图片, 字符串分割存储)" 
     */
    @TableField(value = "head_files")
    private String headFiles;

    /**
 * 视频文件 (视频-默认多上传)" 
     */
    @TableField(value = "video_files")
    private String videoFiles;

    /**
 * 任意文件 (任意文件上传-默认多上传)" 
     */
    @TableField(value = "files")
    private String files;

    /**
 * 时间 (默认 yyyy-MM-dd hh:mm:ss 格式)" 
     */
    @TableField(value = "`time`")
    private LocalDateTime time;

    /**
 * 时间-小时 (默认 hh:mm 字串)" 
     */
    @TableField(value = "time_two")
    private String timeTwo;

    /**
 * 更多信息-text (大文本)" 
     */
    @TableField(value = "text")
    private String text;

    /**
 * 更多信息-fwb (富文本)" 
     */
    @TableField(value = "text_two")
    private String textTwo;

    /**
 * 更多信息-md (md编辑器)" 
     */
    @TableField(value = "text_three")
    private String textThree;

    /**
 * 级联选择器  (字符串分割存储)" 
     */
    @TableField(value = "cascader")
    private String cascader;

    /**
 * 数组框 (字符串分割存储)" 
     */
    @TableField(value = "array")
    private String array;

    /**
 * 图标 " 
     */
    @TableField(value = "`icon`")
    private String icon;

    /**
 * 颜色选择器" 
     */
    @TableField(value = "color")
    private String color;

    /**
 * 地址选择器" 
     */
    @TableField(value = "map")
    private String map;

}

