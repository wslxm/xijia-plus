package io.github.wslxm.springbootplus2.manage.admin.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import io.github.wslxm.springbootplus2.manage.admin.model.dto.AdminRoleDTO;
import io.github.wslxm.springbootplus2.manage.admin.model.dto.role.RoleAuthDTO;
import io.github.wslxm.springbootplus2.manage.admin.model.entity.AdminRole;
import io.github.wslxm.springbootplus2.manage.admin.model.query.AdminRoleQuery;
import io.github.wslxm.springbootplus2.manage.admin.model.vo.AdminRoleVO;

/**
 * 角色
 *
 * @author 王松
 * @WX-QQ 1720696548
 * @date 2019/11/13 15:10
 */
public interface AdminRoleService extends IService<AdminRole> {


    /**
     * 列表查询
     *
     * @param query query
     * @return com.baomidou.mybatisplus.core.metadata.IPage<io.github.wslxm.springbootplus2.manage.admin.model.vo.AdminRoleVO>
     * @version 1.0.0
     */
    IPage<AdminRoleVO> findPage(AdminRoleQuery query);


    /**
     * 添加角色-默认有所有URL 权限
     *
     * @param dto dto
     * @return java.lang.String
     * @version 1.0.0
     */
    String insert(AdminRoleDTO dto);


    /**
     * 编辑角色信息
     *
     * @param id  id
     * @param dto dto
     * @return java.lang.Boolean
     * @version 1.0.0
     */
    Boolean upd(String id, AdminRoleDTO dto);


    /**
     * 删除角色并删除角色关联的  (权限+菜单+用户) 的关系数据
     *
     * @param roleId roleId
     * @return boolean
     * @version 1.0.0
     */
    boolean del(String roleId);


    /**
     * 分配所有角色拥有所有权限
     *
     * @return boolean
     * @version 1.0.0
     */
    boolean roleAuthAll();


    /**
     * 分配角色url权限
     *
     * @param dto dto
     * @return boolean
     * @version 1.0.0
     */
    boolean roleUrlAuth(RoleAuthDTO dto);


    /**
     * 获取超管角色
     *
     * @return io.github.wslxm.springbootplus2.manage.admin.model.entity.AdminRole
     * @version 1.0.0
     */
    AdminRole findSysRole();

}