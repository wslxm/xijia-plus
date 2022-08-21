package io.github.wslxm.springbootplus2.manage.sys.model.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.LinkedHashMap;

/**
 * 分组字典对象,前端缓存对象
 * <p>
 * 注解 @JsonSerialize： 表示不返回空数据
 * </P>
 *
 * @author wangsong
 * @version 1.0.0
 * @email 1720696548@qq.com
 * @date 2021/12/27 17:28
 */
@Data
@ToString(callSuper = true)
@ApiModel(value = "DictionaryCodeGroup", description = "字典findCodeGroup方法VO数据")
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL )
public class DictionaryCodeGroup implements Serializable {

    private static final long serialVersionUID = -3021322187688178780L;
    /**
     * 全为空，判断下级使用
     */
    @ApiModelProperty(value = "字典Id")
    private String id;

    @ApiModelProperty(value = "字典类型")
    private String code;

    @ApiModelProperty(value = "字典名称")
    private String name;

    @ApiModelProperty(value = "父Id")
    private String pid;

    @ApiModelProperty(value = "排序")
    private Integer sort;

    @ApiModelProperty(value = "扩展字段1")
    private String ext1;

    @ApiModelProperty(value = "扩展字段2")
    private String ext2;

    @ApiModelProperty(value = "扩展字段3")
    private String ext3;

    @ApiModelProperty(value = "子级,key=code")
    LinkedHashMap<String, DictionaryCodeGroup> dictMap;
}
