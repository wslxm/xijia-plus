package com.ws.ldy.others.base.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.Version;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 通用Entity
 *
 * @author wangsong
 * @WX-QQ 1720696548
 * @date 2019/10/31 21:12
 *
 * <p>
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class BaseEntity extends Convert {

    /**
     * 数据库id
     */
    @TableId(type = IdType.AUTO) //自增, 雪花算法-->IdType.ASSIGN_ID,字符串Id使用
    private Integer id;
    /**
     * 创建人(ID)
     */
    private String createUser;
    /**
     * 创建时间(数据库策略--添加数据自动插入时间)
     */
    private LocalDateTime createTime;
    /**
     * 更新人(ID)
     */
    private String updateUser;
    /**
     * 更新时间(数据库策略--修改数据自动更新时间)
     */
    private LocalDateTime updateTime;
    /**
     * 逻辑删除字段 int 默认值为0，(0、正常，1、删除) (mybatis-plus 策略, 添加了 @TableLogic 注解自动为逻辑删除)
     */
    //@TableLogic
    private Integer deleted;
    /**
     * 乐观锁(mybatis-plus 策略, 使用id修改自动带版本号修改数据，version版本号如不同修改数据失败返回 0)
     */
    @Version
    private Integer version;
}
