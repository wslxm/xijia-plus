package io.github.wslxm.springbootplus2.manage.gc.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import io.github.wslxm.springbootplus2.manage.gc.model.entity.XjAdminDatasource;
import io.github.wslxm.springbootplus2.manage.gc.model.query.XjAdminDatasourceQuery;
import io.github.wslxm.springbootplus2.manage.gc.model.vo.XjAdminDatasourceVO;

/**
 * 代码生成数据源维护表
 * <p>
 * ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655
 * </p>
 *
 * @author wangsong
 * @email 1720696548@qq.com
 * @date 2020-11-04 20:11:08
 */
public interface XjAdminDatasourceService extends IService<XjAdminDatasource> {

    /**
     * 列表查询
     *
     * @param query query
     * @return com.baomidou.mybatisplus.core.metadata.IPage<io.github.wslxm.springbootplus2.manage.gc.model.vo.XjAdminDatasourceVO>
     * @version 1.0.0
     */
    IPage<XjAdminDatasourceVO> findPage(XjAdminDatasourceQuery query);

    /**
     * id查询
     *
     * @param id id
     * @return com.baomidou.mybatisplus.core.metadata.IPage<io.github.wslxm.springbootplus2.manage.gc.model.vo.XjAdminDatasourceVO>
     * @version 1.0.0
     */
    XjAdminDatasourceVO findId(String id);
}

