package com.ws.lay.adminconsole.service;


import com.ws.lay.adminconsole.entity.Menu;
import com.ws.lay.adminconsole.service.base.BaseService;

import java.util.List;
/**
 * TODO  菜单
 * @author 王松
 * @WX-QQ 1720696548
 * @date  2019/11/13 15:10 
 */
public  interface MenuService extends BaseService<Menu, Integer> {

    /**
     * TODO   获取数菜单列表
     * @date  2019/11/13 14:45
     * @return
     */
    public List<Menu> getMenuTree(Integer uid);

}