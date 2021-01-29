package com.ws.ldy.modules.yw.pets.model.vo;

import com.ws.ldy.modules.sys.base.model.BaseVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;


/**
 * 宠物表
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 
 * </p>
 * @author wangsong
 * @email 1720696548@qq.com
 * @date 2020-12-29 10:54:33
 */
@Data
@ToString(callSuper = true)
@ApiModel(value = "PetsPetInfoVO 对象", description = "宠物表")
public class PetsPetInfoVO extends BaseVo {

    private static final long serialVersionUID = -503731141262577674L;

    @ApiModelProperty(notes = "宠物主人账号id", position = 0)
    private String userId;

    @ApiModelProperty(notes = "宠物名称", position = 1)
    private String nickname;

    @ApiModelProperty(notes = "宠物年龄", position = 2)
    private Double age;

    @ApiModelProperty(notes = "宠物性别(字典 code)", position = 2)
    private Integer sex;

    @ApiModelProperty(notes = "宠物类别(宠物字典 code)", position = 3)
    private String type;

    @ApiModelProperty(notes = "宠物品种(宠物字典 code)", position = 4)
    private String breed;

    @ApiModelProperty(notes = "宠物头像", position = 5)
    private String headPic;

    @ApiModelProperty(notes = "宠物图片(索引 0- 正面 1-左侧 2-右侧 3-主人合照)", position = 6)
    private String photoPic;

    @ApiModelProperty(notes = "加入时间(首次缴费时间)", position = 7)
    private LocalDateTime joinTime;

    @ApiModelProperty(notes = "过期时间 (T+1)", position = 8)
    private LocalDateTime expirationTime;

    @ApiModelProperty(notes = "保单申请时间(过期后再续费将更新，用于计算连续天数)", position = 9)
    private LocalDateTime applyTime;

    @ApiModelProperty(notes = "累积获赠时长(时间单位 分)", position = 10)
    private Integer cumulativeTime;

    @ApiModelProperty(notes = "是否-默认( 如设置为默认,所有赠送时长都将分发给默认绑定宠物)", position = 11)
    private Boolean isDefault;

    @ApiModelProperty(notes = "是否-自动续约", position = 12)
    private Boolean isAutoRenew;


    @ApiModelProperty(notes = " 获帮助次数", position = 13)
    private Integer inHelpNum;

    @ApiModelProperty(notes = " 帮助别人次数", position = 13)
    private Integer helpNum;

    @ApiModelProperty(notes = "用户真实姓名", position = 14)
    private String fullName;

    @ApiModelProperty(notes = "昵称", position = 14)
    private String wxName;

    @ApiModelProperty(notes = "申报成功总次数", position = 15)
    private Integer declareNum;

    @ApiModelProperty(notes = "申报成功总金额", position = 16)
    private BigDecimal declareMoney;

}

