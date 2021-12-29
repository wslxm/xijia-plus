package io.github.wslxm.springbootplus2.manage.test.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import io.github.wslxm.springbootplus2.core.base.model.BaseEntity;

/**
 * 代码生成测试表 Entity
 *
 * <p>
 * ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655
 * </p>
 * @author ws
 * @email 1720696548@qq.com
 * @date 2021-12-28 11:39:46
 */
@Data
@ToString(callSuper = true)
@TableName("t_gc_test")
@ApiModel(value = "GcTest 对象", description = "代码生成测试表")
public class GcTest extends BaseEntity {

    private static final long serialVersionUID = -635651704876044292L;
    
    /** 
     * 名称 (文本) 
     */
    @TableField(value = "`name`")
    private String name;

    /** 
     * 年龄 (数字) 
     */
    @TableField(value = "age")
    private Double age;

    /** 
     * 性别 (单选--字典) 
     */
    @TableField(value = "sex")
    private Integer sex;

    /** 
     * 爱好 (多选--字典) 
     */
    @TableField(value = "`like`")
    private String like;

    /** 
     * 城市 (下拉选--字典) 
     */
    @TableField(value = "city")
    private Integer city;

    /** 
     * 禁用 (开关--字典) 
     */
    @TableField(value = "`disable`")
    private Integer disable;

    /** 
     * 头像 (文件上传) 
     */
    @TableField(value = "head_url")
    private String headUrl;

    /** 
     * 时间 
     */
    @TableField(value = "`time`")
    private LocalDateTime time;

    /** 
     * 更多信息 
     */
    @TableField(value = "text")
    private String text;

}

