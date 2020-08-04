package com.ws.ldy.modules.admin.service.impl;

import com.ws.ldy.others.base.service.impl.BaseIServiceImpl;
import com.ws.ldy.others.generatecode.mapper.DataBaseMapper;
import com.ws.ldy.others.generatecode.model.vo.TableFieldVO;
import com.ws.ldy.others.generatecode.model.vo.TableVO;
import com.ws.ldy.others.generatecode.service.DataBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *    DataBase数据库操作，这里继承 BaseAdminServiceImpl 只是为了使用 mapper.dataBaseDao
 *
 * @author 王松
 * @WX-QQ 1720696548
 * @date 2019/11/20 11:01
 */
@Service
public class DataBaseServiceImpl extends BaseIServiceImpl implements DataBaseService {

    @Autowired
    private DataBaseMapper dataBaseMapper;

    @Override
    public List<TableVO> findTable() {
        //查询
//        String sql = "SELECT TABLE_NAME ,TABLE_COMMENT  FROM information_schema.TABLES  WHERE table_schema='spring-boot-plus2'";
//        List<Map<String, Object>> tables = jdbcTemplate.queryForList(sql);
//        //返回对象
//        List<Map<String, Object>> respTableList = new ArrayList<>();
//        tables.forEach(tableMap -> {
//            Map<String, Object> respTableMap = new HashMap<>();
//            respTableMap.put("name", tableMap.get("TABLE_NAME"));
//            respTableMap.put("comment", tableMap.get("TABLE_COMMENT"));
//            respTableList.add(respTableMap);
//        });
        return dataBaseMapper.findTable("spring-boot-plus2");
    }

    /**
     *   查询数据库下指定表的数据-字段名/类型/备注
     *
     * @return java.util.List<java.lang.String>
     * @date 2019/11/20 10:41
     */
    @Override
    public List<TableFieldVO> findTableField(String table) {
//        String sql = "select " +
//                " column_name name, data_type type,column_comment `desc`,column_type typeDetail" +
//                " from  information_schema.columns " +
//                " where table_name = '" + table +"'"+
//                " and table_schema='spring-boot-plus2'" +
//                " order by ordinal_position asc";//和数据库字段顺序对应
//        List<Map<String, Object>> tables = jdbcTemplate.queryForList(sql);
        return dataBaseMapper.findTableField(table, "spring-boot-plus2");
    }
}
