package io.github.wslxm.springbootplus2.manage.sys.service;


import com.baomidou.mybatisplus.extension.service.IService;
import io.github.wslxm.springbootplus2.manage.sys.model.dto.MenuDTO;
import io.github.wslxm.springbootplus2.manage.sys.model.entity.Menu;
import io.github.wslxm.springbootplus2.manage.sys.model.query.MenuQuery;
import io.github.wslxm.springbootplus2.manage.sys.model.vo.MenuVO;

import java.util.List;

/**
 * 菜单
 *
 * @author 王松
 * @WX-QQ 1720696548
 * @date 2019/11/13 15:10
 */
public interface MenuService extends IService<Menu> {


    /**
     * 列表查询
     *
     * @param query query
     * @return java.util.List<io.github.wslxm.springbootplus2.manage.admin.model.vo.MenuVO>
     * @version 1.0.0
     */
    List<MenuVO> list(MenuQuery query);


    /**
     * 添加(超管默认分配改菜单)
     *
     * @param dto dto
     * @return java.lang.String
     * @version 1.0.0
     */
    String insert(MenuDTO dto);


    /**
     * 编辑
     *
     * @param id  id
     * @param dto dto
     * @return java.lang.Boolean
     * @version 1.0.0
     */
    Boolean upd(String id, MenuDTO dto);


    /**
     * 删除菜单并删除菜单的所有下级 以及 菜单和角色的绑定关系数据, 返回ids 让前端可以判断删除了多少条数据
     *
     * @param menuId menuId
     * @return java.util.List<java.lang.String>
     * @version 1.0.0
     */
    List<String> del(String menuId);


    /**
     * 获取导航树菜单列表,  查询 Tree 菜单 -->  不返回没有权限的数据
     *
     * <p>
     * 1- 查询用户所有角色菜单数据（非禁用角色+ 非禁用菜单）, 没有返回错误提示
     * 2- 存在查询当前用户角色的所有菜单权限|  Sort排序 | 未禁用的，没有数据返回错误提示
     * 3- 递归把List数据处理成树菜单结构数据
     * --return ：第一层数据为顶级菜单 root=1, 下级为 tree数据
     * <p>
     *
     * @return java.util.List<io.github.wslxm.springbootplus2.manage.admin.model.vo.MenuVO>
     * @version 1.0.0
     */
    List<MenuVO> findTree();


}