package io.github.wslxm.springbootplus2.manage.test.model.query;



import lombok.Data;
import lombok.ToString;
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
 * @date 2022-09-09 18:26:48
 */
@Data
@ToString(callSuper = true)
public class GcTestQuery extends BaseQuery {

    private static final long serialVersionUID = -728163044626141184L;
    
    /**
 * 名称 (文本)" 
     */
    private String name;

    /**
 * 性别 (单选--字典)" 
     */
    private Integer sex;

    /**
 * 爱好 (多选--字典)" 
     */
    private String like;

    /**
 * 城市 (下拉选--字典)" 
     */
    private Integer city;

    /**
 * 禁用 (开关--字典)" 
     */
    private Integer disable;

    /**
 * 时间 (默认 yyyy-MM-dd hh:mm:ss 格式)" 
     */
    private String time;

    /**
 * 时间-小时 (默认 hh:mm 字串)" 
     */
    private String timeTwo;

    /**
 * 级联选择器  (字符串分割存储)" 
     */
    private String cascader;

}

