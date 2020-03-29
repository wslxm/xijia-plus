package com.ws.ldy.admin.entity;

import com.ws.ldy.base.entity.BaseEntity;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

/**
 * TODO  角色  
 * @author 王松
 * @WX-QQ 1720696548
 * @date  2019/11/14 20:48 
 */
@Data
@Entity
@Table(name = "t_admin_role")
@DynamicUpdate(value = true)
public class RoleAdmin extends BaseEntity {


    private static final long serialVersionUID = 5684918982089765949L;
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    /**
     * 角色名称
     */
    private String name;
    /**
     * 描叙
     */
    @Column(name = "`desc`")
    private String desc;
}

