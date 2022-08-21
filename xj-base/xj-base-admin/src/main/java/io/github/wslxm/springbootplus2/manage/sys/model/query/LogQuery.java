package io.github.wslxm.springbootplus2.manage.sys.model.query;

import io.github.wslxm.springbootplus2.core.base.model.BaseQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

/**
 * 操作记录表
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 
 * </p>
 * @author  wangsong
 * @email  1720696548@qq.com
 * @date  2020-10-28 20:44:32
 */
@Data
@ToString(callSuper = true)
@ApiModel(value = "LogQuery 对象", description = "操作记录表")
public class LogQuery extends BaseQuery {

    private static final long serialVersionUID = 0L;

    @ApiModelProperty(value = "请求人" ,position = 0)
    private String fullName;

    @ApiModelProperty(value = "请求uri" ,position = 5)
    private String uri;

    @ApiModelProperty(value = "类备注" ,position = 12)
    private String classDesc;

    @ApiModelProperty(value = "方法备注" ,position = 16)
    private String methodDesc;

    @ApiModelProperty(value = "1-请求成功 0-请求失败" ,position = 17)
    private Integer state;

    @ApiModelProperty(value = "请求方式(get-post)", position = 18)
    private String method;

    @ApiModelProperty(value = "请求时间开始", position = 19)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTimeStart;

    @ApiModelProperty(value = "请求时间结束", position = 20)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTimeEnd;
}

