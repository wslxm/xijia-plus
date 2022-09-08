package io.github.wslxm.springbootplus2.manage.test.model.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.Length;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import io.github.wslxm.springbootplus2.core.base.model.BaseQuery;

/**
 * 代码生成测试表 Query
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
@ApiModel(value = "GcTestQuery 对象", description = "代码生成测试表")
public class GcTestQuery extends BaseQuery {

    private static final long serialVersionUID = -727909752700538880L;
    
    @ApiModelProperty(value = "时间 (默认 yyyy-MM-dd hh:mm:ss 格式)" ,position = 0)
    private String time;

    @ApiModelProperty(value = "时间-小时 (默认 hh:mm 字串)" ,position = 1)
    private String timeTwo;

    @ApiModelProperty(value = "级联选择器  (字符串分割存储)" ,position = 2)
    private String cascader;

}

