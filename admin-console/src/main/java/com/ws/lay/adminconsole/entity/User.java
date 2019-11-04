package com.ws.lay.adminconsole.entity;


import com.ws.ldy.admincore.entity.BaseEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Data
@Entity
@Table(name = "user")
public class User extends BaseEntity {

    private static final long serialVersionUID = 7775349988783543870L;
    @Id
    private int id;

    private String user;

    private String pass;

}
