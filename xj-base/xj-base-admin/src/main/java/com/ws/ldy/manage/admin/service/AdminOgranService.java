package com.ws.ldy.manage.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ws.ldy.manage.admin.model.dto.AdminOgranDTO;
import com.ws.ldy.manage.admin.model.entity.AdminOgran;
import com.ws.ldy.manage.admin.model.query.AdminOgranQuery;
import com.ws.ldy.manage.admin.model.vo.AdminOgranVO;

import java.util.List;

/**
 * 基础表--组织机构
 * <p>
 * ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655
 * </p>
 * @author ws
 * @email 1720696548@qq.com
 * @date 2021-09-30 16:10:57
 */
public interface AdminOgranService extends IService<AdminOgran> {

    List<AdminOgranVO> list(AdminOgranQuery query);

    String insert(AdminOgranDTO dto);

    boolean upd(String id, AdminOgranDTO dto);

    boolean del(String id);

    /**
     * 通过当前的部门/公司id获取层级数据（上/下当前级部门/公司信息）
     * @author wangsong
     * @param id
     * @date 2021/10/1 0001 10:33
     * @return boolean
     * @version 1.0.0
     */
    AdminOgranVO fingNextOgrans(String id);
}

