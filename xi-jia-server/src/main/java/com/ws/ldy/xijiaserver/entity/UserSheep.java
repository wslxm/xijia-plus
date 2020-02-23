package com.ws.ldy.xijiaserver.entity;

import com.ws.ldy.admincore.entity.BaseEntity;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import io.swagger.annotations.ApiModelProperty;
import javax.persistence.*;
import java.util.Date;
/**
 * TODO  代码生成器自动生成，请自定义描叙
 * @author  wangsong
 * @email  1720696548
 * @date  Sun Feb 23 23:38:30 CST 2020
 */
@Data
@Entity
@Table(name = "t_sheep_user")
@DynamicUpdate(value = true)
public class UserSheep extends BaseEntity {

    private static final long serialVersionUID = 0L;
    
    @ApiModelProperty(notes = "id")
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    @ApiModelProperty(notes = "名称")
    @Column(name = "`name`")
    private String name;
    @ApiModelProperty(notes = "头像")
    private String head;
    @ApiModelProperty(notes = "等级")
    private Integer lv;
    @ApiModelProperty(notes = "金币")
    private Long gold;
    @ApiModelProperty(notes = "时间")
    @Column(name = "`time`")
    private Date time;

}

