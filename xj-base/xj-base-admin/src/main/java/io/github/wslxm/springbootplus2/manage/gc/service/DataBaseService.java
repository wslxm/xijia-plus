package io.github.wslxm.springbootplus2.manage.gc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.github.wslxm.springbootplus2.manage.gc.config.GcConfig;
import io.github.wslxm.springbootplus2.manage.gc.model.vo.TableFieldVO;
import io.github.wslxm.springbootplus2.manage.gc.model.vo.TableVO;

import java.util.List;

/**
 * 数据库相关数据查询，代码生成，ecxel sql处理内
 *
 * @author 王松
 * @WX-QQ 1720696548
 * @date 2019/11/20 10:40
 */
public interface DataBaseService extends IService {


    /**
     * 查询数据库的所有表
     *
     * @param dataSourceId dataSourceId
     * @return java.util.List<io.github.wslxm.springbootplus2.manage.gc.model.vo.TableVO>
     * @version 1.0.0
     */
    List<TableVO> findTable( String dataSourceId);


    /**
     * 查询数据库下指定表的数据-字段名/类型/备注
     *
     * @param table        table
     * @param dataSourceId dataSourceId
     * @return java.util.List<io.github.wslxm.springbootplus2.manage.gc.model.vo.TableFieldVO>
     * @version 1.0.0
     */
    List<TableFieldVO> findTableField(String table, String dataSourceId);
}
