package com.ws.ldy.modules.sys.xj.model.vo;

import com.ws.ldy.modules.sys.base.model.BaseVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

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
@ApiModel(value = "XjAdminHelpVO 对象", description = "帮助中心表")
public class XjAdminHelpVO extends BaseVo {

    private static final long serialVersionUID = 0L;
    
    @ApiModelProperty(notes = "图标 | 封面图(保留字段)" ,position = 0)
    private String icon;

    @ApiModelProperty(notes = "分类(字典code)" ,position = 1)
    private Integer category;

    @ApiModelProperty(notes = "文档版本" ,position = 2)
    private Integer helpVersion;

    @ApiModelProperty(notes = "标题" ,position = 3)
    private String title;

    @ApiModelProperty(notes = "内容 (富文本|文本+图片+视频)" ,position = 4)
    private String content;

    @ApiModelProperty(notes = "排序" ,position = 5)
    private Integer sort;

    @ApiModelProperty(notes = "浏览量(每次Id查询+1)" ,position = 6)
    private Integer browseNum;

}

