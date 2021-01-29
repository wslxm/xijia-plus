package com.ws.ldy.modules.yw.pets.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ws.ldy.modules.sys.base.model.BaseEntity;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.ToString;

/**
 * 页面访问记录表
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 
 * </p>
 * @author  wangsong
 * @email  1720696548@qq.com
 * @date  2020-12-29 10:54:16
 */
@Data
@ToString(callSuper = true)
@TableName("t_pets_page_visit")
@ApiModel(value = "PetsPageVisit 对象", description = "页面访问记录表")
public class PetsPageVisit extends BaseEntity {

    private static final long serialVersionUID = -503731071540662272L;
    
    /** 
     * 页面名称 
     */
    @TableField(value = "page_name")
    private String pageName;

    /** 
     * 页面url地址 
     */
    @TableField(value = "page_url")
    private String pageUrl;

    /** 
     * 访问用户id 
     */
    @TableField(value = "user_id")
    private String userId;

    /** 
     * 访问用户ip 
     */
    @TableField(value = "ip")
    private String ip;

}

