package io.github.wslxm.springbootplus2.manage.test.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import io.github.wslxm.springbootplus2.core.base.model.BaseVo;

/**
 * 代码生成测试表
 * <p>
 * ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655
 * </p>
 * @author  ws
 * @email  1720696548@qq.com
 * @date  2021-11-04 11:37:04
 */
@Data
@ToString(callSuper = true)
@ApiModel(value = "GcTestVO 对象", description = "代码生成测试表")
public class GcTestVO extends BaseVo {

    private static final long serialVersionUID = -616082079045783563L;
    
    @ApiModelProperty(value = "名称 (文本)",position = 0)
    private String name;

    @ApiModelProperty(value = "年龄 (数字)",position = 1)
    private Double age;

    @ApiModelProperty(value = "性别 (单选--字典)",position = 2)
    private Integer sex;

    @ApiModelProperty(value = "爱好 (多选--字典)",position = 3)
    private String like;

    @ApiModelProperty(value = "城市 (下拉选--字典)",position = 4)
    private Integer city;

    @ApiModelProperty(value = "禁用 (开关--字典)",position = 5)
    private Integer disable;

    @ApiModelProperty(value = "头像 (文件上传)",position = 6)
    private String headUrl;

    @ApiModelProperty(value = "时间",position = 7)
    private LocalDateTime time;

}

