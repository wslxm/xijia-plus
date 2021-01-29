package com.ws.ldy.modules.yw.statistics.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ws.ldy.modules.sys.base.model.BaseEntity;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * 宠物城市总量表(每小时一次)
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 
 * </p>
 * @author wangsong
 * @email 1720696548@qq.com
 * @date 2020-12-29 10:50:29
 */
@Data
@ToString(callSuper = true)
@TableName("t_statistics_total_pet_city")
@ApiModel(value = "StatisticsTotalPetCity 对象", description = "宠物城市总量表(每小时一次)")
public class StatisticsTotalPetCity extends BaseEntity {

    private static final long serialVersionUID = -503730117319725071L;

    // 重写deleted, 关闭该表的逻辑删除
    private Integer deleted;

    /**
     *
     * 省
     */
    @TableField(value = "province")
    private String province;

    /**
     * 城市名 
     */
    @TableField(value = "city")
    private String city;

    /**
     * 当前城市宠物总数 
     */
    @TableField(value = "city_pet_total")
    private Long cityPetTotal;

    /**
     * 当前城市猫总数 
     */
    @TableField(value = "city_pet_cat_total")
    private Long cityPetCatTotal;

    /**
     * 当前城市狗总数 
     */
    @TableField(value = "city_pet_dog_total")
    private Long cityPetDogTotal;

    /**
     * 最后统计时间( 城市数 决定 数据条数) 
     */
    @TableField(value = "`time`")
    private LocalDateTime time;

}

