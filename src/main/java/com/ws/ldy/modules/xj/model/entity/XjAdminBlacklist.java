package com.ws.ldy.modules.xj.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ws.ldy.others.base.model.BaseEntity;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.ToString;

/**
 * 黑名单
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 
 * </p>
 * @author  wangsong
 * @email  1720696548@qq.com
 * @date  2020-11-27 22:44:49
 */
@Data
@ToString(callSuper = true)
@TableName("t_xj_admin_blacklist")
@ApiModel(value = "XjAdminBlacklist 对象", description = "黑名单")
public class XjAdminBlacklist extends BaseEntity {

    private static final long serialVersionUID = 0L;
    
    /** 
     * 1-白名单(* 表示允许除黑名单外的所有ip请求, 否则只允许白名单中的ip请求) 2-黑名单(禁止ip访问) 
     */
    @TableField(value = "type")
    private Integer type;

    /** 
     * 白名单ip/黑名单ip 
     */
    @TableField(value = "ip")
    private String ip;

    /** 
     * 备注 
     */
    @TableField(value = "`desc`")
    private String desc;

    /** 
     * 禁用(0-启用 1-禁用) 
     */
    @TableField(value = "`disable`")
    private Integer disable;

}

