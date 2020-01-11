package com.ws.ldy.adminconsole.service.impl;

import com.ws.ldy.adminconsole.dao.DictionaryAdminDao;
import com.ws.ldy.adminconsole.entity.DictionaryAdmin;
import com.ws.ldy.adminconsole.service.DictionaryAdminService;
import com.ws.ldy.admincore.service.impl.BaseServiceApiImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class DictionaryAdminServiceImpl extends BaseServiceApiImpl<DictionaryAdmin, Integer> implements DictionaryAdminService {

    @Autowired
    private DictionaryAdminDao dictionaryAdminDao;

    @Override
    public List<DictionaryAdmin> findByType(String type) {
        return dictionaryAdminDao.findByType(type);
    }
}

