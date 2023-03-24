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
 * base--gc--代码生成测试表 DTO
 *
 * <p>
 * ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655
 * </p>
 *
 * @author ws
 * @email 1720696548@qq.com
 * @date 2023-03-23 14:05:48
 */
@Data
@ToString(callSuper = true)
@ApiModel(value = "GcTestDTO 对象", description = "base--gc--代码生成测试表")
public class GcTestDTO extends BaseDto {

    private static final long serialVersionUID = -1638784090419990528L;

    @ApiModelProperty(value = "名称 (文本)", position = 0)
    @Length(min = 0, max = 64, message = "名称  必须>=0 和 <=64位")
    private String name;

    @ApiModelProperty(value = "年龄 (数字)", position = 1)
    @DecimalMin(value = "0", message = "年龄  必须 >= 0")
    @DecimalMax(value = "999999999999999999.99", message = "年龄  必须 <= 999999999999999999.99")
    private Double age;

    @ApiModelProperty(value = "性别 (单选--字典)", position = 2)
    @Range(min = 0, max = 999999999L, message = "性别  必须>=0 和 <=999999999")
    private Integer sex;

    @ApiModelProperty(value = "爱好 (多选--字典)", position = 3)
    @Length(min = 0, max = 64, message = "爱好  必须>=0 和 <=64位")
    private String like;

    @ApiModelProperty(value = "城市 (下拉选--字典)", position = 4)
    @Range(min = 0, max = 999999999L, message = "城市  必须>=0 和 <=999999999")
    private Integer city;

    @ApiModelProperty(value = "禁用 (开关--字典)", position = 5)
    @Range(min = 0, max = 999999999L, message = "禁用  必须>=0 和 <=999999999")
    private Integer disable;

    @ApiModelProperty(value = "单图文件", position = 6)
    @Length(min = 0, max = 256, message = "单图文件 必须>=0 和 <=256位")
    private String headUrl;

    @ApiModelProperty(value = "多图文件 (多图-默认限制10张图片, 字符串分割存储)", position = 7)
    @Length(min = 0, max = 1024, message = "多图文件  必须>=0 和 <=1024位")
    private String headFiles;

    @ApiModelProperty(value = "视频文件 (视频-默认多上传)", position = 8)
    @Length(min = 0, max = 1024, message = "视频文件  必须>=0 和 <=1024位")
    private String videoFiles;

    @ApiModelProperty(value = "任意文件 (任意文件上传-默认多上传)", position = 9)
    @Length(min = 0, max = 1024, message = "任意文件  必须>=0 和 <=1024位")
    private String files;

    @ApiModelProperty(value = "时间 (默认 yyyy-MM-dd hh:mm:ss 格式)", position = 10)
    private LocalDateTime time;

    @ApiModelProperty(value = "时间-小时 (默认 hh:mm 字串)", position = 11)
    @Length(min = 0, max = 16, message = "时间-小时  必须>=0 和 <=16位")
    private String timeTwo;

    @ApiModelProperty(value = "更多信息-text (大文本)", position = 12)
    @Length(min = 0, max = 128, message = "更多信息-text  必须>=0 和 <=128位")
    private String text;

    @ApiModelProperty(value = "更多信息-fwb (富文本)", position = 13)
    @Length(min = 0, max = 999, message = "更多信息-fwb  必须>=0 和 <=999位")
    private String textTwo;

    @ApiModelProperty(value = "更多信息-md (md编辑器)", position = 14)
    @Length(min = 0, max = 999, message = "更多信息-md  必须>=0 和 <=999位")
    private String textThree;

    @ApiModelProperty(value = "级联选择器  (字符串分割存储)", position = 15)
    @Length(min = 0, max = 256, message = "级联选择器   必须>=0 和 <=256位")
    private String cascader;

    @ApiModelProperty(value = "数组框 (字符串分割存储)", position = 16)
    @Length(min = 0, max = 256, message = "数组框  必须>=0 和 <=256位")
    private String array;

    @ApiModelProperty(value = "图标 ", position = 17)
    @Length(min = 0, max = 256, message = "图标  必须>=0 和 <=256位")
    private String icon;

    @ApiModelProperty(value = "颜色选择器", position = 18)
    @Length(min = 0, max = 256, message = "颜色选择器 必须>=0 和 <=256位")
    private String color;

    @ApiModelProperty(value = "地址选择器", position = 19)
    @Length(min = 0, max = 256, message = "地址选择器 必须>=0 和 <=256位")
    private String map;

}
