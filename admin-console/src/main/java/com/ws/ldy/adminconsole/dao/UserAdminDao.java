package com.ws.ldy.adminconsole.dao;

import com.ws.ldy.adminconsole.entity.UserAdmin;
import com.ws.ldy.admincore.dao.BaseDao;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAdminDao extends BaseDao<UserAdmin, Integer> {

}
