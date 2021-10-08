package com.github.wslxm.springbootplus2.manage.xj.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.wslxm.springbootplus2.manage.xj.model.dto.XjAdminConfigDTO;
import com.github.wslxm.springbootplus2.manage.xj.model.entity.XjAdminConfig;
import com.github.wslxm.springbootplus2.manage.xj.model.query.XjAdminConfigQuery;
import com.github.wslxm.springbootplus2.manage.xj.model.vo.XjAdminConfigVO;

/**
 * 系统全局数据信息配置表
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 
 * </p>
 * @author wangsong
 * @email 1720696548@qq.com
 * @date 2020-08-31 18:31:44
 */
public interface XjAdminConfigService extends IService<XjAdminConfig> {

    IPage<XjAdminConfigVO> list(XjAdminConfigQuery query);

    String  insert(XjAdminConfigDTO dto);

    boolean  upd(String id,XjAdminConfigDTO dto);

    XjAdminConfigVO findByCode(String code);

}

