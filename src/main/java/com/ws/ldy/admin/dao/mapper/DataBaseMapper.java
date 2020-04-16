package com.ws.ldy.admin.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * TODO  hql操作@Repository组解放置实现类，引用也使用 DataBaseDaoImpl
 *
 * @author 王松
 * @WX-QQ 1720696548
 * @date 2019/11/20 11:09
 */
//@Mapper
public interface DataBaseMapper extends BaseMapper {

    /***
     * TODO  查询数据库的所有表
     * @date 2019/11/20 10:41
     * @return java.util.List<java.lang.String>
     */
    public List<String> findTable();

    /**
     * TODO  查询数据库下指定表的数据-字段名/类型/备注
     *
     * @return java.util.List<java.lang.String>
     * @date 2019/11/20 10:41
     */
    public List<Map<String, String>> findTableField(String table);
}
