package com.ws.ldy.modules.astory.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ws.ldy.others.base.model.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 段子表
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 
 * </p>
 * @author  wangsong
 * @email  1720696548@qq.com
 * @date  2020-12-20 22:04:11
 */
@Data
@ToString(callSuper = true)
@TableName("t_xj_astory")
@ApiModel(value = "XjAstory 对象", description = "段子表")
public class XjAstory extends BaseEntity {

    private static final long serialVersionUID = 0L;
    
    /** 
     * 标题 
     */
    @TableField(value = "title")
    private String title;

    /** 
     * 段子内容 
     */
    @TableField(value = "content")
    private String content;

    /** 
     * 类别id 
     */
    @TableField(value = "typeid")
    private Integer typeid;

    /** 
     * - 
     */
    @TableField(value = "hits")
    private Integer hits;

    /** 
     * 段子生产日期 
     */
    @TableField(value = "dateandtime")
    private LocalDateTime dateandtime;

}

