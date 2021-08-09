package com.test.springbootplus2.manage.test1.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import com.ws.ldy.core.base.model.BaseEntity;

/**
 * 测试表
 * <p>
 * ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655
 * </p>
 * @author  ws
 * @email  1720696548@qq.com
 * @date  2021-08-06 17:04:37
 */
@Data
@ToString(callSuper = true)
@TableName("t_test_user")
@ApiModel(value = "TestUser 对象", description = "测试表")
public class TestUser extends BaseEntity {

    private static final long serialVersionUID = -583549602234503190L;
    
    /** 
     * 用户名 
     */
    @TableField(value = "username")
    private String username;

    /** 
     * 密码 
     */
    @TableField(value = "password")
    private String password;

}

