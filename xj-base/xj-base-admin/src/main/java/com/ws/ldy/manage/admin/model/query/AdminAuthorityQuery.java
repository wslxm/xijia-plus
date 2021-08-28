package com.ws.ldy.manage.admin.model.query;

import com.ws.ldy.core.base.model.BaseQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;


/**
 * 权限接口表
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655
 * </p>
 * @author wangsong
 * @email 1720696548@qq.com
 * @date 2020-08-22 13:25:02
 */
@Data
@ToString(callSuper = true)
@ApiModel(value = "AdminAuthorityQuery 对象", description = "权限接口表")
public class AdminAuthorityQuery extends BaseQuery {

    private static final long serialVersionUID = 0L;

    @ApiModelProperty(notes = "权限类Id(方法与类/层级关系展示)", position = 0)
    private String pid;

    @ApiModelProperty(notes = "终端(字典code, 如 0-管理端 1-用户端)", position = 5)
    private Integer type;

}
