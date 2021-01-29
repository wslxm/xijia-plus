package com.ws.ldy.modules.yw.pets.model.vo;

import com.ws.ldy.modules.sys.base.model.BaseVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;


/**
 * 用户表
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 
 * </p>
 * @author wangsong
 * @email 1720696548@qq.com
 * @date 2020-12-29 11:03:46
 */
@Data
@ToString(callSuper = true)
@ApiModel(value = "PetsUserVO 对象", description = "用户表")
public class PetsUserVO extends BaseVo {

    private static final long serialVersionUID = -503733462482685961L;


    @ApiModelProperty(notes = "创建时间", position = 0)
    private LocalDateTime createTime;

    @ApiModelProperty(notes = "姓名", position = 0)
    private String fullName;

    @ApiModelProperty(notes = "电话", position = 1)
    private String phone;

    @ApiModelProperty(notes = "身份证号", position = 2)
    private String idCard;

    @ApiModelProperty(notes = "身份证前", position = 3)
    private String idCardFrontPic;

    @ApiModelProperty(notes = "身份证后", position = 4)
    private String idCardAfterPic;

    @ApiModelProperty(notes = "省", position = 5)
    private String province;

    @ApiModelProperty(notes = "市", position = 6)
    private String city;

    @ApiModelProperty(notes = "区", position = 7)
    private String area;

    @ApiModelProperty(notes = "用户微信openId", position = 8)
    private String wxOpenId;

    @ApiModelProperty(notes = "用户头像 (默认使用微信头像)", position = 9)
    private String wxHeadPic;

    @ApiModelProperty(notes = "用户昵称( 默认微信昵称)", position = 10)
    private String wxName;

    @ApiModelProperty(notes = "用户性别( 默认微信性别)", position = 10)
    private Integer wxGender;

    @ApiModelProperty(notes = "1-禁用-黑名单 / 0-启用", position = 11)
    private Integer disable;

    // ===================== 分页查询 统计参数 ============================
    @ApiModelProperty(notes = "分页查询-统计参数--审核次数", position = 12)
    private Integer declareNum;

    @ApiModelProperty(notes = "分页查询-统计参数--宠物数量", position = 13)
    private Integer petsNum;

    @ApiModelProperty(notes = "分页查询-统计参数--缴费次数", position = 14)
    private Integer payNum;

}

