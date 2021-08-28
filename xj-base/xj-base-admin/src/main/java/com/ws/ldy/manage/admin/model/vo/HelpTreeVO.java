//package com.ws.ldy.manage.admin.model.vo;
//
//import com.fasterxml.jackson.annotation.JsonInclude;
//import com.ws.ldy.core.base.model.Convert;
//import io.swagger.annotations.ApiModel;
//import io.swagger.annotations.ApiModelProperty;
//import lombok.Data;
//import lombok.ToString;
//
//import java.util.List;
//
//@Data
//@ToString(callSuper = true)
//@ApiModel(value = "HelpTreeVO 对象", description = "帮助中心左菜单")
//@JsonInclude(value = JsonInclude.Include.NON_NULL) //不符合空字段
//public class HelpTreeVO extends Convert {
//
//    private static final long serialVersionUID = 0L;
//
//    @ApiModelProperty(notes = "菜单名称/ 内容标题名称", position = 0)
//    private String name;
//
//    @ApiModelProperty(notes = "内容Id/ 标题没有Id", position = 0)
//    private String id;
//
//    @ApiModelProperty(notes = "下级数据, 没有为null", position = 1)
//    private List<HelpTreeVO> ybHelpTreeVOS;
//}