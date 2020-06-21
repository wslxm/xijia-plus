package com.ws.ldy.admin.baseModel;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.ws.ldy.base.convert.Convert;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * TODO  通用Entity ,获取反序列类生成UUID
 *
 * @author 王松
 * @WX-QQ 1720696548
 * @date 2019/10/31 21:12
 *
 * <p>
 */
@Data   // set,get
@NoArgsConstructor  // 无参构造
@EqualsAndHashCode(callSuper = true) //生成equals(Object other) 和 hashCode()方法
public class BaseAdminEntity extends Convert {

    /**
     * 数据库id
     */
    @TableId(type = IdType.AUTO) //自增
    private Integer id;

//
//    /**
//     * 逻辑删除字段（0-正常，1-已删除）
//     */
//    private int deleted = 0;
}
