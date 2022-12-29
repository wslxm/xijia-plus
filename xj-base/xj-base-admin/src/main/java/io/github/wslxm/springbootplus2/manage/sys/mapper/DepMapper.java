package io.github.wslxm.springbootplus2.manage.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.github.wslxm.springbootplus2.manage.sys.model.entity.Dep;
import io.github.wslxm.springbootplus2.manage.sys.model.vo.DepVO;
import io.github.wslxm.springbootplus2.manage.sys.model.query.DepQuery;

import java.util.List;

/**
 * base--sys--组织机构 Mapper
 *
 * <p>
 * ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655
 * </p>
 *
 * @author ws
 * @email 1720696548@qq.com
 * @date 2022-12-29 09:57:30
 */
public interface DepMapper extends BaseMapper<Dep> {

    /**
     * id 查询
     *
     * @param id
     * @return List<Dep>
     */
    DepVO findId(String id);

    /**
     * 列表查询/分页查询
     *
     * @param page
     * @param query
     * @return java.util.List<Dep>
     */
    List<DepVO> list(IPage<DepVO> page, DepQuery query);

}

