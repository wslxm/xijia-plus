package com.ws.lay.adminconsole.dao;

import com.ws.lay.adminconsole.entity.User;
import com.ws.ldy.admincore.dao.BaseDao;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends BaseDao<User, Integer> {

}
