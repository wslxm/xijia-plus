package com.ws.ldy.baseadmin.dao;

import com.ws.ldy.baseadmin.entity.DictionaryAdmin;
import com.ws.ldy.admincore.dao.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DictionaryAdminDao extends BaseDao<DictionaryAdmin, Integer> {

    /**
     * 根据字段Type 查询，
     * 参考: https://blog.csdn.net/chengqiuming/article/details/82528961
     * @return
     */
    List<DictionaryAdmin> findByType(String type);
}
