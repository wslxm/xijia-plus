package com.ws.ldy.modules.admin.model.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.ws.ldy.others.base.model.BaseVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


/**
 *   字典表
 *
 * @author wangsong
 * @WX-QQ 1720696548
 * @date Sun Nov 24 11:23:12 CST 2019
 */
@Data
@ToString(callSuper = true)
@ApiModel(value = "AdminDictionaryVO", description = "字典表")
public class AdminDictionaryVO extends BaseVo {

    private static final long serialVersionUID = 0L;

    @ApiModelProperty(value = "字典类型")
    private String code;

    @ApiModelProperty(value = "字典名称")
    private String name;

    @ApiModelProperty(value = "父Id")
    private String pid;

    @ApiModelProperty(value = "描叙")
    private String desc;

    @ApiModelProperty(value = "排序")
    private Integer sort;

    @ApiModelProperty(value = "版本号")
    private Integer version;

    @ApiModelProperty(value = "禁用（0-否，1-是）")
    private Integer disable;

    @ApiModelProperty(value = "子级")
    List<AdminDictionaryVO> dictList;

    @ApiModelProperty(value = "子级,key=code")
    Map<String, AdminDictionaryVO> dictMap;
//    @ApiModelProperty(value = "子级,key=code")
//    List<LinkedHashMap> dictMap;

    /**
     * 分组字典对象,前端缓存对象
     */
    @Data
    @ToString(callSuper = true)
    @ApiModel(value = "findCodeGroup", description = "字典findCodeGroup方法VO数据")
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)//不返回空数据
    static public class FindCodeGroup {

        @ApiModelProperty(value = "字典Id") //全为空，判断下级使用
        private String id;

        @ApiModelProperty(value = "字典类型")
        private String code;

        @ApiModelProperty(value = "字典名称")
        private String name;

        @ApiModelProperty(value = "父Id")
        private String pid;

        @ApiModelProperty(value = "排序")
        private Integer sort;

//        @ApiModelProperty(value = "版本号")//除了版本号的字段,其他全为空
//        private Integer version;

        //除了版本号的字段,其他数据该字段全为空
        @ApiModelProperty(value = "子级,key=code")
        LinkedHashMap<String, FindCodeGroup> dictMap;
    }
}

