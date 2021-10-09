package io.github.wslxm.springbootplus2.manage.admin.model.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.LinkedHashMap;

/**
 * 分组字典对象,前端缓存对象
 */
@Data
@ToString(callSuper = true)
@ApiModel(value = "AdminDictionaryCodeGroup", description = "字典findCodeGroup方法VO数据")
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)//不返回空数据
public class AdminDictionaryCodeGroup implements Serializable {

    private static final long serialVersionUID = -3021322187688178780L;
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

    // @ApiModelProperty(value = "版本号")//除了版本号的字段,其他全为空
    // private Integer version;

    // 除了版本号的字段,其他数据该字段全为空
    @ApiModelProperty(value = "子级,key=code")
    LinkedHashMap<String, AdminDictionaryCodeGroup> dictMap;
}
