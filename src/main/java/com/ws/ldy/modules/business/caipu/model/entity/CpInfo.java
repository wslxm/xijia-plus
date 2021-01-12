package com.ws.ldy.modules.business.caipu.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ws.ldy.modules.sys.base.model.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;



/**
 * 菜谱表
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 
 * </p>
 * @author  wangsong
 * @email  1720696548@qq.com
 * @date  2020-10-04 18:50:10
 */
@Data
@ToString(callSuper = true)
@TableName("t_xj_cp_info")
@ApiModel(value = "CpInfo 对象", description = "菜谱表")
public class CpInfo extends BaseEntity {

    private static final long serialVersionUID = 0L;
    
    @ApiModelProperty(notes = "源站原始id" ,position = 0)
    @TableField(value = "did")
    private Integer did;

    @ApiModelProperty(notes = "分类" ,position = 1)
    @TableField(value = "cid")
    private String cid;

    @ApiModelProperty(notes = "主分类" ,position = 2)
    @TableField(value = "zid")
    private String zid;

    @ApiModelProperty(notes = "菜谱标题" ,position = 3)
    @TableField(value = "title")
    private String title;

    @ApiModelProperty(notes = "菜谱缩略图" ,position = 4)
    @TableField(value = "thumb")
    private String thumb;

    @ApiModelProperty(notes = "视频简绍" ,position = 5)
    @TableField(value = "videourl")
    private String videourl;

    @ApiModelProperty(notes = "菜谱简介" ,position = 6)
    @TableField(value = "`desc`")
    private String desc;

    @ApiModelProperty(notes = "难度（0初级、1中级、3高级" ,position = 7)
    @TableField(value = "difficulty")
    private Integer difficulty;

    @ApiModelProperty(notes = "耗时" ,position = 8)
    @TableField(value = "costtime")
    private String costtime;

    @ApiModelProperty(notes = "提示" ,position = 9)
    @TableField(value = "tip")
    private String tip;

    @ApiModelProperty(notes = "材料" ,position = 10)
    @TableField(value = "yl")
    private String yl;

    @ApiModelProperty(notes = "分量" ,position = 11)
    @TableField(value = "fl")
    private String fl;

    @ApiModelProperty(notes = "步骤" ,position = 12)
    @TableField(value = "steptext")
    private String steptext;

    @ApiModelProperty(notes = "步骤图片，用#符号分隔" ,position = 13)
    @TableField(value = "steppic")
    private String steppic;

    @ApiModelProperty(notes = "评分" ,position = 14)
    @TableField(value = "grade")
    private Integer grade;

    @ApiModelProperty(notes = "多少人做过" ,position = 15)
    @TableField(value = "up")
    private Integer up;

    @ApiModelProperty(notes = "浏览数" ,position = 16)
    @TableField(value = "viewnum")
    private Integer viewnum;

    @ApiModelProperty(notes = "收藏数" ,position = 17)
    @TableField(value = "favnum")
    private Integer favnum;

    @ApiModelProperty(notes = "" ,position = 18)
    @TableField(value = "outdate")
    private String outdate;

    @ApiModelProperty(notes = "" ,position = 19)
    @TableField(value = "status")
    private Integer status;

}

