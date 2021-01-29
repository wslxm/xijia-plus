package com.ws.ldy.modules.yw.pets.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ws.ldy.modules.sys.base.model.BaseEntity;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.ToString;

/**
 * 互助资金抽成配置表
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 
 * </p>
 * @author  wangsong
 * @email  1720696548@qq.com
 * @date  2020-12-29 10:52:46
 */
@Data
@ToString(callSuper = true)
@TableName("t_pets_capital_config")
@ApiModel(value = "PetsCapitalConfig 对象", description = "互助资金抽成配置表")
public class PetsCapitalConfig extends BaseEntity {

    private static final long serialVersionUID = -503730692379774983L;
    
    /** 
     * 平台抽成比率( 如：0.18 = 18 % ) 
     */
    @TableField(value = "platform_percentage")
    private Double platformPercentage;


    /**
     * 微信抽成比率( 如：0.006 = 0.6 % )
     */
    @TableField(value = "wx_percentage")
    private Double wxPercentage;

}

