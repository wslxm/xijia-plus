package com.ws.ldy.admin.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ws.ldy.admin.model.entity.DictionaryAdmin;

import java.util.List;

//@Mapper
public interface DictionaryAdminMapper extends BaseMapper<DictionaryAdmin> {

    /**
     * 根据字段Type 查询，
     * 参考: https://blog.csdn.net/chengqiuming/article/details/82528961
     * @return
     */
    List<DictionaryAdmin> findByType(String type);
}
