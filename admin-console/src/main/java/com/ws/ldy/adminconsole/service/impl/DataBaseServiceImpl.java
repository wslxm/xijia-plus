package com.ws.ldy.adminconsole.service.impl;

import com.ws.ldy.adminconsole.dao.DataBaseDao;
import com.ws.ldy.adminconsole.service.DataBaseService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * TODO   DataBase数据库操作，这里继承 BaseAdminServiceImpl 只是为了使用 dao.dataBaseDao
 *
 * @author 王松
 * @WX-QQ 1720696548
 * @date 2019/11/20 11:01
 */
@Service
public class DataBaseServiceImpl implements DataBaseService {

    @Resource
    DataBaseDao dataBaseDao;

    @Override
    public List<String> findTable() {
        return dataBaseDao.findTable();
    }

    @Override
    public List<Map<String, String>> findTableField(String table) {
        return dataBaseDao.findTableField(table);
    }
}
