package com.ws.ldy.baseadmin.entity;

import com.ws.ldy.admincore.entity.BaseEntity;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

/**
 * TODO  代码生成器自动生成，请自定义描叙
 *
 * @author wangsong
 * @WX-QQ 1720696548
 * @date Sun Nov 24 11:23:12 CST 2019
 */
@Data
@Entity
@Table(name = "t_admin_dictionary")
@DynamicUpdate(value = true)
public class DictionaryAdmin extends BaseEntity {

    private static final long serialVersionUID = 0L;

    /**
     * 描叙
     */
    @Column(name = "`desc`")
    private String desc;
    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**
     * 搜索值
     */
    @Column(name = "`key`")
    private String key;
    /**
     * 字典类型
     */
    private String type;
    /**
     * 选择值
     */
    @Column(name = "`value`")
    private String value;

    /**
     * 树菜单列表需要name字段显示
     */
    @Transient
    private String name;
}

