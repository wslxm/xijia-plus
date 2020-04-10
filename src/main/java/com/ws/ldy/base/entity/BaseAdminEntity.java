package com.ws.ldy.base.entity;

import com.ws.ldy.common.annotation.Delete;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.MappedSuperclass;

/**
 * TODO  通用Entity ,获取反序列类生成UUID
 *
 * @author 王松
 * @WX-QQ 1720696548
 * @date 2019/10/31 21:12
 *
 * <p>
 * TODO @MappedSuperclass 注解使用环境
 * 1.@MappedSuperclass注解使用在父类上面，是用来标识父类的作用
 * 2.@MappedSuperclass标识的类表示其不能映射到数据库表，因为其不是一个完整的实体类，但是它所拥有的属性能够映射在其子类对用的数据库表中
 * 3.@MappedSuperclass标识得类不能再有@Entity或@Table注解  但是可以使用@Id 和@Column注解
 */
@EqualsAndHashCode(callSuper = true) //生成equals(Object other) 和 hashCode()方法
@Data   // set,get
@NoArgsConstructor  // 无参构造
@MappedSuperclass   // Jpa-定义通用的父类字段
public class BaseAdminEntity extends Convert {
    /**
     * 逻辑删除字段（0-正常，1-已删除）
     */
    @Delete
    private int deleted = 0;

//    /**
//     * 数据库自增id
//     */
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int id;
//
//    /**
//     * uuid 生成id
//     */
//    @Id
//    // @GeneratedValue(strategy= GenerationType.IDENTITY)
//    @GeneratedValue(generator = "paymentableGenerator")
//    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
//    private int id;
}
