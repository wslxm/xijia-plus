package com.ws.ldy.adminconsole.service.impl;

import com.ws.ldy.adminconsole.entity.DictionaryAdmin;
import com.ws.ldy.adminconsole.service.DictionaryAdminService;
import com.ws.ldy.adminconsole.service.base.impl.BaseAdminConsoleServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class DictionaryAdminServiceImpl extends BaseAdminConsoleServiceImpl<DictionaryAdmin,Integer> implements DictionaryAdminService {


    @Override
    public List<DictionaryAdmin> findByType(String type){
      return   dao.dictionaryAdminDao.findByType(type);
    }
}

