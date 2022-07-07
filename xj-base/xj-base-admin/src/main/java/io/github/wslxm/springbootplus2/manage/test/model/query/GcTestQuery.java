package io.github.wslxm.springbootplus2.manage.test.model.query;

import io.github.wslxm.springbootplus2.core.base.model.BaseQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * 代码生成测试表 Query
 *
 * <p>
 * ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655
 * </p>

 * @author ws
 * @email 1720696548@qq.com
 * @date 2022-06-30 11:07:08
 */
@Data
@ToString(callSuper = true)
@ApiModel(value = "GcTestQuery 对象", description = "代码生成测试表")
public class GcTestQuery extends BaseQuery {

    private static final long serialVersionUID = -702322858851962880L;
    
    @ApiModelProperty(value = "名称 (文本)", position = 0)
    private String name;

    @ApiModelProperty(value = "年龄 (数字)", position = 1)
    private Double age;

    @ApiModelProperty(value = "性别 (单选--字典)", position = 2)
    private Integer sex;

    @ApiModelProperty(value = "爱好 (多选--字典)", position = 3)
    private String like;

}

