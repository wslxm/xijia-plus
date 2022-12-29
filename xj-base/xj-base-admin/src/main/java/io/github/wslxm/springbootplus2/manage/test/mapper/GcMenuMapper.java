package io.github.wslxm.springbootplus2.manage.test.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.github.wslxm.springbootplus2.manage.test.model.entity.GcMenu;
import io.github.wslxm.springbootplus2.manage.test.model.vo.GcMenuVO;
import io.github.wslxm.springbootplus2.manage.test.model.query.GcMenuQuery;

import java.util.List;

/**
 * 基础表--菜单 Mapper
 *
 * <p>
 * ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655
 * </p>
 *
 * @author ws
 * @email 1720696548@qq.com
 * @date 2022-12-28 20:24:04
 */
public interface GcMenuMapper extends BaseMapper<GcMenu> {

    /**
     * id 查询
     *
     * @param id
     * @return List<GcMenu>
     */
    GcMenuVO findId(String id);

    /**
     * 列表查询/分页查询
     *
     * @param page
     * @param query
     * @return java.util.List<GcMenu>
     */
    List<GcMenuVO> list(IPage<GcMenuVO> page, GcMenuQuery query);

}

