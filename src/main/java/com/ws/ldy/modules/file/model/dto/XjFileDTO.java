package com.ws.ldy.modules.file.model.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ws.ldy.others.base.model.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 常用工具文件管理
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 
 * </p>
 * @author  wangsong
 * @email  1720696548@qq.com
 * @date  2020-12-17 16:21:46
 */
@Data
@ToString(callSuper = true)
@ApiModel(value = "XjFileDTO 对象", description = "常用工具文件管理")
public class XjFileDTO extends BaseDto {

    private static final long serialVersionUID = 0L;
    
    @ApiModelProperty(notes = "文件名(标题)" ,position = 0)
    @Length(min=1, max=128,message = "文件名 必须小于128位")
    private String name;

    @ApiModelProperty(notes = "文件描叙" ,position = 1)
    @Length(min=1, max=512,message = "文件描叙 必须小于512位")
    private String desc;

    @ApiModelProperty(notes = "文件大小" ,position = 2)
    @Range(min=0, max=1410065407,message = "文件大小 必须小于1410065407")
    private Double size;

    @ApiModelProperty(notes = "文件格式(后缀)" ,position = 3)
    @Length(min=1, max=32,message = "文件格式 必须小于32位")
    private String suffix;

    @ApiModelProperty(notes = "文件url" ,position = 4)
    private String url;

    @ApiModelProperty(notes = "文件类型(1-开发工具 2-源码  3-文档   4-图片 5-音频 6-视频 7-sql)" ,position = 5)
    @Range(min=0, max=99L,message = "文件类型 必须小于99")
    private Integer type;

}

