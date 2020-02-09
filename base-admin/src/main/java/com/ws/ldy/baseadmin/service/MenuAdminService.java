package com.ws.ldy.baseadmin.service;


import com.ws.ldy.baseadmin.entity.MenuAdmin;
import com.ws.ldy.baseadmin.entity.UserAdmin;
import com.ws.ldy.admincore.service.BaseServiceApi;

import java.util.List;

/**
 * TODO  菜单
 *
 * @author 王松
 * @WX-QQ 1720696548
 * @date 2019/11/13 15:10
 */
public interface MenuAdminService extends BaseServiceApi<MenuAdmin, Integer> {


    /**
     * TODO   获取数菜单列表
     *
     * @return
     * @date 2019/11/13 14:45
     */
    public List<MenuAdmin> getMenuTree(UserAdmin user);


    /***
     * TODO  感觉父id查询所有子节点数据
     * @param id
     * @date 2019/11/15 16:18
     * @return java.util.List<com.ws.ldy.adminconsole.entity.MenuAdmin>
     */
    public List<MenuAdmin> getIdNodeMenu(Integer id,Integer roleId, Integer type);

}