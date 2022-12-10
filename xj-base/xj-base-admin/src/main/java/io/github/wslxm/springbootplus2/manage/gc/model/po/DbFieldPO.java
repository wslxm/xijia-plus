package io.github.wslxm.springbootplus2.manage.gc.model.po;



import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * @author wangsong
 */
@Data
@ToString(callSuper = true)
public class DbFieldPO {
    /**
     * 字段名
     */
    private String name;
    /**
     * 字段描叙
     */
    private String desc;
    /**
     * 字段类型(字典)
     */
    private Integer vueFieldType;
    /**
     * 类型
     */
    private String type;
    /**
     * 类型+长度
     */
    private String typeDetail;
    /**
     * 是否选中(兼容layui)
     */
    private Boolean checked;
    /**
     * 是否选中(兼容vue)
     */
    private Boolean isChecked;
    /**
     * 是否搜索
     */
    private Boolean isSearch;
    /**
     * 是否必填(NO /YES)
     */
    private String isNull;
    /**
     * 选择字典
     */
    private List<String> dictCode;

}
