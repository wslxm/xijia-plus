package io.github.wslxm.springbootplus2.manage.test.model.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Range;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import io.github.wslxm.springbootplus2.core.base.model.BaseQuery;

/**
 * 代码生成测试表
 * <p>
 * ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655
 * </p>
 * @author  ws
 * @email  1720696548@qq.com
 * @date  2021-11-06 08:16:29
 */
@Data
@ToString(callSuper = true)
@ApiModel(value = "GcTestQuery 对象", description = "代码生成测试表")
public class GcTestQuery extends BaseQuery {

    private static final long serialVersionUID = -616756376106045446L;
    
}
