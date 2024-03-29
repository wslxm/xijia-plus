package io.github.wslxm.springbootplus2.manage.gc.mapper;

import io.github.wslxm.springbootplus2.manage.gc.model.vo.XjTableFieldVO;
import io.github.wslxm.springbootplus2.manage.gc.model.vo.XjTableVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *  hql操作@Repository组解放置实现类，引用也使用 DataBaseDaoImpl
 *
 * @author 王松
 * @WX-QQ 1720696548
 * @date 2019/11/20 11:09
 */
public interface XjDataBaseMapper {


    /**
     * 查询数据库所有表
     * @author wangsong
     * @param libraryName
     * @date 2020/7/30 0030 11:59
     * @return java.util.List<XjTableVO>
     * @version 1.0.1
     */
    List<XjTableVO> findTable(@Param("libraryName") String libraryName);

    /**
     *查询数据库下指定表的数据-字段名/类型/备注
     *
     * @return java.util.List<java.lang.String>
     * @date 2019/11/20 10:41
     */
    List<XjTableFieldVO> findTableField(@Param("table") String table, @Param("libraryName") String libraryName);


    /**
     * 物理删除 指定表的 逻辑删除数据
     *
     * @param table 表名称
     * @param updTime 最后修改时间/或第一次添加时间， 如果数据库时间小于该值,则删除，updTime=七天前的当前时间，则只物理删除七天前的数据
     * @return java.util.List<java.lang.String>
     * @date 2019/11/20 10:41
     */
    Boolean deleteByTable(@Param("table") String table, @Param("updTime") String updTime);


    /**
     * 物理删除 n天前的数据
     *
     * @date 2019/11/20 10:41
     */
    Boolean deleteByDayFront(@Param("table") String table, @Param("updTime") String updTime);
}
