package com.ws.ldy.admindemoweb.entity;

import com.ws.ldy.admincore.entity.BaseEntity;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;
/**
 * TODO  代码生成器自动生成，请自定义描叙
 * @author  wangsong
 * @WX-QQ  1720696548
 * @date  Fri Nov 22 21:29:24 CST 2019
 */
@Data
@Entity
@Table(name = "t_test")
@DynamicUpdate(value = true)
public class Test extends BaseEntity {

    private static final long serialVersionUID = 0L;
    
    /**  */
    @Column(name = "`desc`")
    private String desc;
    /**  */
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

}

