package com.ws.ldy.adminconsole.service.impl;

import com.sun.xml.internal.bind.v2.model.core.ID;
import com.ws.ldy.adminconsole.service.DataBaseService;
import com.ws.ldy.adminconsole.service.base.impl.BaseAdminConsoleServiceImpl;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * TODO   DataBase数据库操作，这里继承 BaseAdminServiceImpl 只是为了使用 dao.dataBaseDao
 * @author 王松
 * @WX-QQ 1720696548
 * @date  2019/11/20 11:01
 */
@Service
public class DataBaseServiceImpl  extends BaseAdminConsoleServiceImpl<T, ID> implements DataBaseService {

    @Override
    public List<String> findTable() {
        return dao.dataBaseDao.findTable();
    }

    @Override
    public  List<Map<String, String>> findTableField(String table) {
        return dao.dataBaseDao.findTableField(table);
    }
}
