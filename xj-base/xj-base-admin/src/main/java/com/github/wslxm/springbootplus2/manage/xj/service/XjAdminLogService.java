package com.github.wslxm.springbootplus2.manage.xj.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.wslxm.springbootplus2.manage.xj.model.entity.XjAdminLog;
import com.github.wslxm.springbootplus2.manage.xj.model.query.XjAdminLogQuery;
import com.github.wslxm.springbootplus2.manage.xj.model.vo.XjAdminLogVO;

/**
 * 操作记录表
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 
 * </p>
 * @author  wangsong
 * @email  1720696548@qq.com
 * @date  2020-10-28 20:44:32
 */
public interface XjAdminLogService extends IService<XjAdminLog> {

    IPage<XjAdminLogVO> list(XjAdminLogQuery query);

}

