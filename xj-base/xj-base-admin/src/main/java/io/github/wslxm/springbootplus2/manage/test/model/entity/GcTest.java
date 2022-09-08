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
 * @date 2022-09-09 01:40:19
 */
@Data
@ToString(callSuper = true)
@TableName("t_gc_test")
@ApiModel(value = "GcTest 对象", description = "代码生成测试表")
public class GcTest extends BaseEntity {

    private static final long serialVersionUID = -727909752511795200L;
    
    @ApiModelProperty(notes = "时间 (默认 yyyy-MM-dd hh:mm:ss 格式)" ,position = 0)
    @TableField(value = "`time`")
    private LocalDateTime time;

    @ApiModelProperty(notes = "时间-小时 (默认 hh:mm 字串)" ,position = 1)
    @TableField(value = "time_two")
    private String timeTwo;

    @ApiModelProperty(notes = "更多信息-text (大文本)" ,position = 2)
    @TableField(value = "text")
    private String text;

    @ApiModelProperty(notes = "更多信息-fwb (富文本)" ,position = 3)
    @TableField(value = "text_two")
    private String textTwo;

    @ApiModelProperty(notes = "级联选择器  (字符串分割存储)" ,position = 4)
    @TableField(value = "cascader")
    private String cascader;

    @ApiModelProperty(notes = "地址选择器" ,position = 5)
    @TableField(value = "map")
    private String map;

}

