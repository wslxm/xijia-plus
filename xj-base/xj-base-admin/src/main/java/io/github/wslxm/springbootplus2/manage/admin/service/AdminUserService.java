package io.github.wslxm.springbootplus2.manage.admin.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import io.github.wslxm.springbootplus2.manage.admin.model.dto.AdminUserDTO;
import io.github.wslxm.springbootplus2.manage.admin.model.entity.AdminUser;
import io.github.wslxm.springbootplus2.manage.admin.model.query.AdminUserQuery;
import io.github.wslxm.springbootplus2.manage.admin.model.vo.AdminUserVO;

import java.util.List;

/**
 * 用户
 *
 * @author 王松
 * @WX-QQ 1720696548
 * @date 2019/11/13 15:10
 */
public interface AdminUserService extends IService<AdminUser> {

    /**
     * 列表查询
     *
     * @param query query
     * @return com.baomidou.mybatisplus.core.metadata.IPage<io.github.wslxm.springbootplus2.manage.admin.model.vo.AdminUserVO>
     * @version 1.0.0
     */
    IPage<AdminUserVO> findPage(AdminUserQuery query);

    /**
     * 添加
     *
     * @param dto dto
     * @return java.lang.String
     * @version 1.0.0
     */
    String insert(AdminUserDTO dto);

    /**
     * 编辑
     *
     * @param id  id
     * @param dto dto
     * @return java.lang.Boolean
     * @version 1.0.0
     */
    Boolean upd(String id, AdminUserDTO dto);


    /**
     * 删除用户及用户关联的角色的关系数据
     *
     * @param id id
     * @return java.lang.Boolean
     * @version 1.0.0
     */
    Boolean del(String id);


    /**
     * id查询，同时查询用户下的角色[List<String>]
     *
     * @param id id
     * @return io.github.wslxm.springbootplus2.manage.admin.model.vo.AdminUserVO
     * @version 1.0.0
     */
    AdminUserVO findId(String id);


    /**
     * 根据角色Id查询指定用户
     *
     * @param roleId roleId
     * @return java.util.List<io.github.wslxm.springbootplus2.manage.admin.model.entity.AdminUser>
     * @version 1.0.0
     */
    List<AdminUser> findByRoleId(String roleId);


    /**
     * 查询关键数据(id/姓名/昵称/电话)
     *
     * @param searchName 昵称/姓名
     * @param terminal   终端
     * @return java.util.List<io.github.wslxm.springbootplus2.manage.admin.model.vo.AdminUserVO>
     * @version 1.0.0
     */
    List<AdminUserVO> listKeyData(String searchName, String terminal);

    /**
     * 登录
     *
     * @param username 手机号或者账号
     * @param password 密码
     * @param terminal 终端
     * @return java.lang.Boolean
     * @version 1.0.0
     */
    Boolean login(String username, String password, Integer terminal);

    /**
     * 重置用户密码
     *
     * @param id       id
     * @param password password
     * @return java.lang.Boolean
     * @version 1.0.0
     */
    Boolean updResetPassword(String id, String password);

    /**
     * 修改当前代理人的密码
     *
     * @param oldPassword oldPassword
     * @param password    password
     * @return java.lang.Boolean
     * @version 1.0.0
     */
    Boolean updByPassword(String oldPassword, String password);

}