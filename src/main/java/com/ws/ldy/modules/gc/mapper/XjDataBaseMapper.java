package com.ws.ldy.modules.gc.mapper;

import com.ws.ldy.modules.gc.model.vo.XjTableFieldVO;
import com.ws.ldy.modules.gc.model.vo.XjTableVO;
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
     * @version 1.0.0
     */
    List<XjTableVO> findTable(@Param("libraryName") String libraryName);

    /**
     *查询数据库下指定表的数据-字段名/类型/备注
     *
     * @return java.util.List<java.lang.String>
     * @date 2019/11/20 10:41
     */
    List<XjTableFieldVO> findTableField(@Param("table") String table, @Param("libraryName") String libraryName);
}
