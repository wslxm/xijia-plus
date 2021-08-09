package com.ws.ldy.manage.xj.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ws.ldy.manage.xj.model.dto.XjAdminConfigDTO;
import com.ws.ldy.manage.xj.model.entity.XjAdminConfig;

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


    XjAdminConfig findByCode(String code);

    boolean  insert(XjAdminConfigDTO dto);

    boolean  upd(XjAdminConfigDTO dto);
}

