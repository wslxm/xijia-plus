package io.github.wslxm.springbootplus2.manage.sys.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import io.github.wslxm.springbootplus2.manage.sys.model.dto.RoleDTO;
import io.github.wslxm.springbootplus2.manage.sys.model.entity.Role;
import io.github.wslxm.springbootplus2.manage.sys.model.query.RoleQuery;
import io.github.wslxm.springbootplus2.manage.sys.model.vo.RoleVO;

/**
 * 角色
 *
 * @author 王松
 * @WX-QQ 1720696548
 * @date 2019/11/13 15:10
 */
public interface RoleService extends IService<Role> {


    /**
     * 列表查询
     *
     * @param query query
     * @return com.baomidou.mybatisplus.core.metadata.IPage<io.github.wslxm.springbootplus2.manage.admin.model.vo.RoleVO>
     * @version 1.0.0
     */
    IPage<RoleVO> findPage(RoleQuery query);


    /**
     * 添加角色-默认有所有URL 权限
     *
     * @param dto dto
     * @return java.lang.String
     * @version 1.0.0
     */
    String insert(RoleDTO dto);


    /**
     * 编辑角色信息
     *
     * @param id  id
     * @param dto dto
     * @return java.lang.Boolean
     * @version 1.0.0
     */
    Boolean upd(String id, RoleDTO dto);


    /**
     * 删除角色并删除角色关联的  (权限+菜单+用户) 的关系数据
     *
     * @param roleId roleId
     * @return boolean
     * @version 1.0.0
     */
    boolean del(String roleId);


//    /**
//     * 分配所有角色拥有所有权限
//     *
//     * @return boolean
//     * @version 1.0.0
//     */
//    boolean roleAuthAll();


//    /**
//     * 分配角色url权限
//     *
//     * @param dto dto
//     * @return boolean
//     * @version 1.0.0
//     */
//    boolean roleUrlAuth(RoleAuthDTO dto);


    /**
     * 获取超管角色
     *
     * @return io.github.wslxm.springbootplus2.manage.admin.model.entity.Role
     * @version 1.0.0
     */
    Role findSysRole();

}