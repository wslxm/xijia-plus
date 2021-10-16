package io.github.wslxm.springbootplus2.manage.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.github.wslxm.springbootplus2.manage.admin.model.dto.AdminOrganDTO;
import io.github.wslxm.springbootplus2.manage.admin.model.entity.AdminOrgan;
import io.github.wslxm.springbootplus2.manage.admin.model.query.AdminOrganQuery;
import io.github.wslxm.springbootplus2.manage.admin.model.vo.AdminOrganVO;

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
public interface AdminOrganService extends IService<AdminOrgan> {

    List<AdminOrganVO> list(AdminOrganQuery query);

    String insert(AdminOrganDTO dto);

    boolean upd(String id, AdminOrganDTO dto);

    boolean del(String id);

    /**
     * 通过当前的部门/公司id获取层级数据（上/下当前级部门/公司信息）
     * @author wangsong
     * @param id
     * @date 2021/10/1 0001 10:33
     * @return boolean
     * @version 1.0.1
     */
    AdminOrganVO fingNextOrgans(String id);
}

