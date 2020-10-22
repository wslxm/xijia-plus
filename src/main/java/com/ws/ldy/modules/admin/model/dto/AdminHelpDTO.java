package com.ws.ldy.modules.admin.model.dto;

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
 * 帮助中心表
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 
 * </p>
 * @author  wangsong
 * @email  1720696548@qq.com
 * @date  2020-10-20 16:42:28
 */
@Data
@ToString(callSuper = true)
@ApiModel(value = "AdminHelpDTO 对象", description = "帮助中心表")
public class AdminHelpDTO extends BaseDto {

    private static final long serialVersionUID = 0L;
    
    @ApiModelProperty(notes = "图标 | 封面图(保留字段)" ,position = 0)
    private String icon;

    @ApiModelProperty(notes = "分类(字典code)" ,position = 1)
    @Range(min=0, max=9L,message = "分类 必须小于999")
    private Integer category;


    @ApiModelProperty(notes = "文档版本" ,position = 2)
    @Range(min=0, max=9L,message = "分类 必须小于999")
    private Integer helpVersion;

    @ApiModelProperty(notes = "标题" ,position = 3)
    @Length(min=1, max=64,message = "标题 必须小于64位")
    private String title;

    @ApiModelProperty(notes = "内容 (富文本|文本+图片+视频)" ,position = 4)
    private String content;

    @ApiModelProperty(notes = "排序" ,position = 5)
    @Range(min=0, max=9L,message = "排序 必须小于9")
    private Integer sort;

    @ApiModelProperty(notes = "浏览量(每次Id查询+1)" ,position = 6)
    @Range(min=0, max=2147483647L,message = "浏览量 必须小于2147483647")
    private Integer browseNum;

}

