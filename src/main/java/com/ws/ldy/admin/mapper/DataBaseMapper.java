package com.ws.ldy.admin.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * TODO  hql操作@Repository组解放置实现类，引用也使用 DataBaseDaoImpl
 *
 * @author 王松
 * @WX-QQ 1720696548
 * @date 2019/11/20 11:09
 */
public interface DataBaseMapper {

    /***
     * TODO  查询数据库的所有表
     * @date 2019/11/20 10:41
     * @return java.util.List<java.lang.String>
     */
    //@Select("show tables") 没有表注释
    @Select("SELECT TABLE_NAME name,TABLE_COMMENT comment FROM information_schema.TABLES WHERE table_schema='spring-boot-plus2'")
    public List<Map<String, String>> findTable();


    /**
     * TODO  查询数据库下指定表的数据-字段名/类型/备注
     *
     * @return java.util.List<java.lang.String>
     * @date 2019/11/20 10:41
     */
    @Select(" select " +
            " column_name name,\n" +
            " data_type type,\n" +
            " column_comment `desc`,\n" +
            " column_type typeDetail\n" +
            " from information_schema.columns \n" +
            " where table_name = #{table} \n" +
            " and table_schema='spring-boot-plus2'")
    public List<Map<String, String>> findTableField(@Param("table") String table);
}
