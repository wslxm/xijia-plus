package io.github.wslxm.springbootplus2.manage.test.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(value = "GcTest 对象", description = "代码生成测试表")
public class GcTest extends BaseEntity {

    private static final long serialVersionUID = -728163044278013952L;
    
    @ApiModelProperty(notes = "名称 (文本)" ,position = 0)
    @TableField(value = "`name`")
    private String name;

    @ApiModelProperty(notes = "年龄 (数字)" ,position = 1)
    @TableField(value = "age")
    private Double age;

    @ApiModelProperty(notes = "性别 (单选--字典)" ,position = 2)
    @TableField(value = "sex")
    private Integer sex;

    @ApiModelProperty(notes = "爱好 (多选--字典)" ,position = 3)
    @TableField(value = "`like`")
    private String like;

    @ApiModelProperty(notes = "城市 (下拉选--字典)" ,position = 4)
    @TableField(value = "city")
    private Integer city;

    @ApiModelProperty(notes = "禁用 (开关--字典)" ,position = 5)
    @TableField(value = "`disable`")
    private Integer disable;

    @ApiModelProperty(notes = "单图文件" ,position = 6)
    @TableField(value = "head_url")
    private String headUrl;

    @ApiModelProperty(notes = "多图文件 (多图-默认限制10张图片, 字符串分割存储)" ,position = 7)
    @TableField(value = "head_files")
    private String headFiles;

    @ApiModelProperty(notes = "视频文件 (视频-默认多上传)" ,position = 8)
    @TableField(value = "video_files")
    private String videoFiles;

    @ApiModelProperty(notes = "任意文件 (任意文件上传-默认多上传)" ,position = 9)
    @TableField(value = "files")
    private String files;

    @ApiModelProperty(notes = "时间 (默认 yyyy-MM-dd HH:mm:ss 格式)" ,position = 10)
    @TableField(value = "`time`")
    private LocalDateTime time;

    @ApiModelProperty(notes = "时间-小时 (默认 hh:mm 字串)" ,position = 11)
    @TableField(value = "time_two")
    private String timeTwo;

    @ApiModelProperty(notes = "更多信息-text (大文本)" ,position = 12)
    @TableField(value = "text")
    private String text;

    @ApiModelProperty(notes = "更多信息-fwb (富文本)" ,position = 13)
    @TableField(value = "text_two")
    private String textTwo;

    @ApiModelProperty(notes = "更多信息-md (md编辑器)" ,position = 14)
    @TableField(value = "text_three")
    private String textThree;

    @ApiModelProperty(notes = "级联选择器  (字符串分割存储)" ,position = 15)
    @TableField(value = "cascader")
    private String cascader;

    @ApiModelProperty(notes = "数组框 (字符串分割存储)" ,position = 16)
    @TableField(value = "array")
    private String array;

    @ApiModelProperty(notes = "图标 " ,position = 17)
    @TableField(value = "`icon`")
    private String icon;

    @ApiModelProperty(notes = "颜色选择器" ,position = 18)
    @TableField(value = "color")
    private String color;

    @ApiModelProperty(notes = "地址选择器" ,position = 19)
    @TableField(value = "map")
    private String map;

}

