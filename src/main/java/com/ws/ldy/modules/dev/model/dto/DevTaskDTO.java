package com.ws.ldy.modules.dev.model.dto;

import com.ws.ldy.others.base.model.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * 开发任务
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655
 * </p>
 * @author  wangsong
 * @email  1720696548@qq.com
 * @date  2020-08-02 01:14:57
 */
@Data
@ApiModel(value = "DevTaskDTO 对象", description = "开发任务")
public class DevTaskDTO extends BaseDto {

    private static final long serialVersionUID = 0L;

    @ApiModelProperty(notes = "指派给id")
    @NotBlank(message = "指派给id 不能为空")
    @Length(min=1, max=32,message = "指派给id 必须小于32位")
    private String taskUserId;

    @ApiModelProperty(notes = "项目(字典表code)")
    @NotBlank(message = "项目 不能为空")
    @Length(min=1, max=32,message = "项目 必须小于32位")
    private String item;

    @ApiModelProperty(notes = "任务名")
    @NotBlank(message = "任务名 不能为空")
    @Length(min=1, max=64,message = "任务名 必须小于64位")
    private String name;

    @ApiModelProperty(notes = "任务内容")
    @NotBlank(message = "任务内容 不能为空")
    private String content;

    @ApiModelProperty(notes = "任务类型(1-管理端 2-用户端 3-app端  4-所有端 )")
    @NotNull(message = "任务类型 不能为空")
    @Range(min=0, max=9L,message = "任务类型 必须小于9")
    private Integer type;

//    @ApiModelProperty(notes = "任务状态(0-未开始 1-正在进行 2-已完成 3-已撤销)")
//    @NotNull(message = "任务状态 不能为空")
//    @Range(min=0, max=9L,message = "任务状态 必须小于9")
//    private Integer state;

    @ApiModelProperty(notes = "计划完成时间")
    @NotNull(message = "计划完成时间 不能为空")
    private LocalDateTime plannedTime;

//    @ApiModelProperty(notes = "实际完成时间")
//    private LocalDateTime entTime;

    @ApiModelProperty(notes = "预计耗时(小时)")
    @NotNull(message = "预计耗时 不能为空 ")
    @Range(min=0, max=99999999999L,message = "预计耗时 必须小于99999999999")
    private Double estimateTime;

//    @ApiModelProperty(notes = "实际耗时(小时)")
//    private Double takeUpTime;

}
