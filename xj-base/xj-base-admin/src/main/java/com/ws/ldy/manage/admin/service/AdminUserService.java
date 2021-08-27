package com.ws.ldy.manage.admin.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ws.ldy.manage.admin.model.dto.AdminUserDTO;
import com.ws.ldy.manage.admin.model.entity.AdminUser;
import com.ws.ldy.manage.admin.model.query.AdminUserQuery;
import com.ws.ldy.manage.admin.model.vo.AdminUserVO;

import java.util.List;

/**
 *   用户
 *
 * @author 王松
 * @WX-QQ 1720696548
 * @date 2019/11/13 15:10
 */
public interface AdminUserService extends IService<AdminUser> {


    IPage<AdminUserVO> list(AdminUserQuery query);


    /**
     * id查询，代当前有的角色List<String>
     * @param id
     * @return
     */
    public AdminUserVO findId(String id);

    /**
     * 根据角色Id查询指定用户
     * @author wangsong
     * @param roleId
     * @date 2020/8/9 0009 10:08
     * @return void
     * @version 1.0.0
     */
    public List<AdminUser> findByRoleId(String roleId);


    public Boolean insert(AdminUserDTO dto);


    public Boolean upd(String id, AdminUserDTO dto);


    List<AdminUserVO> listKeyData(String searchName, String terminal);

    /**
     * 登录，支持手机号+密码 和 账号+密码 登录
     * @param username
     * @param password
     * @return
     */
    public Boolean login(String username, String password);


    public Boolean bindWeChatMq(String username, String password, String openId);


    /**
     * 删除用户及用户关联的角色的关系数据
     * @param userId
     * @return
     */
    Boolean del(String userId);
}