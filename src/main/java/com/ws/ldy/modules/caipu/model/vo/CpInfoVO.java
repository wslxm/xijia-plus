package com.ws.ldy.modules.caipu.model.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ws.ldy.others.base.model.BaseVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;

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
@ApiModel(value = "CpInfoVO 对象", description = "菜谱表")
public class CpInfoVO extends BaseVo {

    private static final long serialVersionUID = 0L;
    
    @ApiModelProperty(notes = "源站原始id" ,position = 0)
    private Integer did;

    @ApiModelProperty(notes = "分类" ,position = 1)
    private String cid;

    @ApiModelProperty(notes = "主分类" ,position = 2)
    private String zid;

    @ApiModelProperty(notes = "菜谱标题" ,position = 3)
    private String title;

    @ApiModelProperty(notes = "菜谱缩略图" ,position = 4)
    private String thumb;

    @ApiModelProperty(notes = "视频简绍" ,position = 5)
    private String videourl;

    @ApiModelProperty(notes = "菜谱简介" ,position = 6)
    private String desc;

    @ApiModelProperty(notes = "难度（0初级、1中级、3高级" ,position = 7)
    private Integer difficulty;

    @ApiModelProperty(notes = "耗时" ,position = 8)
    private String costtime;

    @ApiModelProperty(notes = "提示" ,position = 9)
    private String tip;

    @ApiModelProperty(notes = "材料" ,position = 10)
    private String yl;

    @ApiModelProperty(notes = "分量" ,position = 11)
    private String fl;

    @ApiModelProperty(notes = "步骤" ,position = 12)
    private String steptext;

    @ApiModelProperty(notes = "步骤图片，用#符号分隔" ,position = 13)
    private String steppic;

    @ApiModelProperty(notes = "评分" ,position = 14)
    private Integer grade;

    @ApiModelProperty(notes = "多少人做过" ,position = 15)
    private Integer up;

    @ApiModelProperty(notes = "浏览数" ,position = 16)
    private Integer viewnum;

    @ApiModelProperty(notes = "收藏数" ,position = 17)
    private Integer favnum;

    @ApiModelProperty(notes = "" ,position = 18)
    private String outdate;

    @ApiModelProperty(notes = "" ,position = 19)
    private Integer status;

}

