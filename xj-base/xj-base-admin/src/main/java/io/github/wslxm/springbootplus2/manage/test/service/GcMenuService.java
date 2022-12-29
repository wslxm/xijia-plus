package io.github.wslxm.springbootplus2.manage.test.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.github.wslxm.springbootplus2.manage.test.model.entity.GcMenu;
import io.github.wslxm.springbootplus2.manage.test.model.vo.GcMenuVO;
import io.github.wslxm.springbootplus2.manage.test.model.dto.GcMenuDTO;
import io.github.wslxm.springbootplus2.manage.test.model.query.GcMenuQuery;
import java.util.List;

/**
 * 基础表--菜单 Service
 *
 * <p>
 * ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655
 * </p>

 * @author ws
 * @email 1720696548@qq.com
 * @date 2022-12-28 20:24:04
 */
public interface GcMenuService extends IService<GcMenu> {

    /**
     * 树结构数据查询
     *
     * @param query query
     * @return 分页列表数据
     */
    List<GcMenuVO> tree(GcMenuQuery query);

    /**
     * 列表查询
     *
     * @param query query
     * @return 分页列表数据
     */
    IPage<GcMenuVO> findPage(GcMenuQuery query);

    /**
     * id 查询
     *
     * @param id id
     * @return GcMenuVO
     */
    GcMenuVO findId(String id);

    /**
     * 添加
     *
     * @param dto dto
     * @return 主键id
     */
    String insert(GcMenuDTO dto);

    /**
     * id 编辑
     *
     * @param id id
     * @param dto dto
     * @return boolean
     */
    boolean upd( String id, GcMenuDTO dto);

    /**
     * 删除 (同时删除下级数据)
     *
     * @param id id
     * @return boolean
     */
    boolean del(String id);

}

