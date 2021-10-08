package com.github.wslxm.springbootplus2.manage.xj.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.wslxm.springbootplus2.manage.xj.model.dto.XjAdminBlacklistDTO;
import com.github.wslxm.springbootplus2.manage.xj.model.entity.XjAdminBlacklist;
import com.github.wslxm.springbootplus2.manage.xj.model.query.XjAdminBlacklistQuery;
import com.github.wslxm.springbootplus2.manage.xj.model.vo.XjAdminBlacklistVO;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 黑名单
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 
 * </p>
 * @author wangsong
 * @email 1720696548@qq.com
 * @date 2020-11-27 22:44:49
 */
public interface XjAdminBlacklistService extends IService<XjAdminBlacklist> {

    IPage<XjAdminBlacklistVO> list(XjAdminBlacklistQuery query);

    String insert(XjAdminBlacklistDTO dto);

    Boolean upd(String id, @RequestBody @Validated XjAdminBlacklistDTO dto);

    Boolean del(String id);
}

