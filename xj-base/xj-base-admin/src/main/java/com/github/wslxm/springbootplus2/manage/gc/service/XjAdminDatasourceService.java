package com.github.wslxm.springbootplus2.manage.gc.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.wslxm.springbootplus2.manage.gc.model.entity.XjAdminDatasource;
import com.github.wslxm.springbootplus2.manage.gc.model.query.XjAdminDatasourceQuery;
import com.github.wslxm.springbootplus2.manage.gc.model.vo.XjAdminDatasourceVO;

/**
 * 代码生成数据源维护表
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 
 * </p>
 * @author wangsong
 * @email 1720696548@qq.com
 * @date 2020-11-04 20:11:08
 */
public interface XjAdminDatasourceService extends IService<XjAdminDatasource> {

   IPage<XjAdminDatasourceVO> list(XjAdminDatasourceQuery query);

}

