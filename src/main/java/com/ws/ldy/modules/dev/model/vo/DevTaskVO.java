package com.ws.ldy.modules.dev.model.vo;

import com.ws.ldy.others.base.model.BaseVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 *   开发任务
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 
 * </p>
 * @author  wangsong
 * @email  1720696548@qq.com
 * @date  2020-06-27 11:14:57
 */
@Data
@ApiModel(value = "DevTaskVO 对象", description = "开发任务")
public class DevTaskVO extends BaseVo {

    private static final long serialVersionUID = 0L;
    
    @ApiModelProperty(notes = "创建人id ")
    private String createUser;

    @ApiModelProperty(notes = "指派给id")
    private String taskUserId;

    @ApiModelProperty(notes = "任务名")
    private String name;

    @ApiModelProperty(notes = "任务内容")
    private String content;

    @ApiModelProperty(notes = "项目（字典code）")
    private String item;

    @ApiModelProperty(notes = "任务类型(1-管理端 2-用户端 3-app端)")
    private String type;

    @ApiModelProperty(notes = "任务状态(0-未开始 1-正在进行 2-已完成 3-已撤销)")
    private Integer state;

    @ApiModelProperty(notes = "计划时间")
    private LocalDateTime plannedTime;

    @ApiModelProperty(notes = "完成时间")
    private LocalDateTime entTime;

    @ApiModelProperty(notes = "预计耗时(小时)")
    private Double estimateTime;

    @ApiModelProperty(notes = "实际耗时(小时)")
    private Double takeUpTime;

}

