package io.github.wslxm.springbootplus2.manage.test.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import io.github.wslxm.springbootplus2.core.base.model.BaseDto;

/**
 * 代码生成测试表 DTO
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
@ApiModel(value = "GcTestDTO 对象", description = "代码生成测试表")
public class GcTestDTO extends BaseDto {

    private static final long serialVersionUID = -728163044491923456L;
    
    @ApiModelProperty(value = "名称 (文本)" ,position = 0)
    private String name;

    @ApiModelProperty(value = "年龄 (数字)" ,position = 1)
    private Double age;

    @ApiModelProperty(value = "性别 (单选--字典)" ,position = 2)
    private Integer sex;

    @ApiModelProperty(value = "爱好 (多选--字典)" ,position = 3)
    private String like;

    @ApiModelProperty(value = "城市 (下拉选--字典)" ,position = 4)
    private Integer city;

    @ApiModelProperty(value = "禁用 (开关--字典)" ,position = 5)
    private Integer disable;

    @ApiModelProperty(value = "单图文件" ,position = 6)
    private String headUrl;

    @ApiModelProperty(value = "多图文件 (多图-默认限制10张图片, 字符串分割存储)" ,position = 7)
    private String headFiles;

    @ApiModelProperty(value = "视频文件 (视频-默认多上传)" ,position = 8)
    private String videoFiles;

    @ApiModelProperty(value = "任意文件 (任意文件上传-默认多上传)" ,position = 9)
    private String files;

    @ApiModelProperty(value = "时间 (默认 yyyy-MM-dd hh:mm:ss 格式)" ,position = 10)
    private LocalDateTime time;

    @ApiModelProperty(value = "时间-小时 (默认 hh:mm 字串)" ,position = 11)
    private String timeTwo;

    @ApiModelProperty(value = "更多信息-text (大文本)" ,position = 12)
    private String text;

    @ApiModelProperty(value = "更多信息-fwb (富文本)" ,position = 13)
    private String textTwo;

    @ApiModelProperty(value = "更多信息-md (md编辑器)" ,position = 14)
    private String textThree;

    @ApiModelProperty(value = "级联选择器  (字符串分割存储)" ,position = 15)
    private String cascader;

    @ApiModelProperty(value = "数组框 (字符串分割存储)" ,position = 16)
    private String array;

    @ApiModelProperty(value = "图标 " ,position = 17)
    private String icon;

    @ApiModelProperty(value = "颜色选择器" ,position = 18)
    private String color;

    @ApiModelProperty(value = "地址选择器" ,position = 19)
    private String map;

}

