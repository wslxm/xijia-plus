package io.github.wslxm.springbootplus2.manage.sys.model.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;


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
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class DictionaryCodeGroup implements Serializable {

    private static final long serialVersionUID = -3021322187688178780L;
    /**
     * 字典Id 全为空，判断下级使用
     */

    private String id;

    /**
     * 字典类型
     */
    private String code;

    /**
     * 字典名称
     */
    private String name;

    /**
     * 父Id
     */
    private String pid;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 扩展字段1
     */
    private String ext1;

    /**
     * 扩展字段2
     */
    private String ext2;

    /**
     * 扩展字段3
     */
    private String ext3;

    /**
     * 子级,key=code
     */
    LinkedHashMap<String, DictionaryCodeGroup> dictMap;
}
