package io.github.wslxm.springbootplus2.manage.gc.mapper;

import io.github.wslxm.springbootplus2.manage.gc.model.vo.TableFieldVO;
import io.github.wslxm.springbootplus2.manage.gc.model.vo.TableVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * hql操作@Repository组解放置实现类，引用也使用 DataBaseDaoImpl
 *
 * @author 王松
 * @WX-QQ 1720696548
 * @date 2019/11/20 11:09
 */
public interface DataBaseMapper {


    /**
     * 查询数据库所有表
     *
     * @param libraryName libraryName
     * @return java.util.List<io.github.wslxm.springbootplus2.manage.gc.model.vo.TableVO>
     * @version 1.0.0
     */
    List<TableVO> findTable(@Param("libraryName") String libraryName);


    /**
     * 查询数据库下指定表的数据-字段名/类型/备注
     *
     * @param table       table
     * @param libraryName libraryName
     * @return java.util.List<io.github.wslxm.springbootplus2.manage.gc.model.vo.TableFieldVO>
     * @version 1.0.0
     */
    List<TableFieldVO> findTableField(@Param("table") String table, @Param("libraryName") String libraryName);


    /**
     * 物理删除 指定表的 逻辑删除数据
     *
     * @param table   表名称
     * @param updTime 最后修改时间/或第一次添加时间， 如果数据库时间小于该值,则删除，updTime=七天前的当前时间，则只物理删除七天前的数据
     * @return java.lang.Boolean
     * @version 1.0.0
     */
    Boolean deleteByTable(@Param("table") String table, @Param("updTime") String updTime);


    /**
     * 物理删除 n天前的数据
     *
     * @param table   table
     * @param updTime updTime
     * @return java.lang.Boolean
     * @version 1.0.0
     */
    Boolean deleteByDayFront(@Param("table") String table, @Param("updTime") String updTime);
}
